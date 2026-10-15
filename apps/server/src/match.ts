/**
 * MatchDO: one Durable Object per room (ADR 0041). It owns the room's sockets
 * (WebSocket Hibernation, so idle rooms cost nothing), persists the room after
 * every change, and keeps one alarm for the next clock deadline (ADR 0040).
 */
import { DurableObject } from "cloudflare:workers";
import { parseClientMessage, type Seat, type ServerMessage } from "@tk/protocol";
import { type Out, Room, type RoomData } from "./room.ts";

export interface Env {
  MATCH: DurableObjectNamespace<MatchDO>;
  ASSETS: Fetcher;
  /** Local testing only: speeds up every clock (e.g. "0.01"). Unset in production. */
  CLOCK_SCALE?: string;
}

interface Attachment {
  seat: Seat | null;
}

const OPEN = 1;

async function sha256Hex(text: string): Promise<string> {
  const digest = await crypto.subtle.digest("SHA-256", new TextEncoder().encode(text));
  return [...new Uint8Array(digest)].map((b) => b.toString(16).padStart(2, "0")).join("");
}

/** Uniform integer in [0, n) by rejection sampling over 32-bit values. */
function randomInt(n: number): number {
  const limit = Math.floor(0x100000000 / n) * n;
  const buf = new Uint32Array(1);
  for (;;) {
    crypto.getRandomValues(buf);
    const v = buf[0] as number;
    if (v < limit) return v % n;
  }
}

export class MatchDO extends DurableObject<Env> {
  private room: Room | null = null;
  private loaded = false;

  private async load(): Promise<Room | null> {
    if (!this.loaded) {
      const data = await this.ctx.storage.get<RoomData>("room");
      this.room = data ? this.wrap(data) : null;
      this.loaded = true;
    }
    return this.room;
  }

  private wrap(data: RoomData): Room {
    const scale = Number(this.env.CLOCK_SCALE ?? "1");
    return new Room(data, {
      now: () => Date.now(),
      clockScale: Number.isFinite(scale) && scale > 0 ? scale : 1,
      randomInt,
      isConnected: (seat) => this.sockets(seat).length > 0,
    });
  }

  private sockets(seat?: Seat): WebSocket[] {
    return this.ctx.getWebSockets().filter((ws) => {
      const a = ws.deserializeAttachment() as Attachment | null;
      return (
        ws.readyState === OPEN &&
        a !== null &&
        a.seat !== null &&
        (seat === undefined || a.seat === seat)
      );
    });
  }

  private send(ws: WebSocket, msg: ServerMessage): void {
    try {
      ws.send(JSON.stringify(msg));
    } catch {
      // The socket closed under us; webSocketClose will tidy up.
    }
  }

  private deliver(out: Out[]): void {
    for (const o of out)
      for (const ws of this.sockets(o.to === "all" ? undefined : o.to)) this.send(ws, o.msg);
  }

  private async save(room: Room): Promise<void> {
    await this.ctx.storage.put("room", room.data);
    await this.ctx.storage.setAlarm(room.nextAlarm());
  }

  override async fetch(request: Request): Promise<Response> {
    if (request.headers.get("Upgrade") !== "websocket")
      return new Response("expected a WebSocket", { status: 426 });
    const pair = new WebSocketPair();
    const [client, server] = [pair[0], pair[1]];
    this.ctx.acceptWebSocket(server);
    server.serializeAttachment({ seat: null } satisfies Attachment);
    return new Response(null, { status: 101, webSocket: client });
  }

  override async webSocketMessage(ws: WebSocket, raw: string | ArrayBuffer): Promise<void> {
    const msg = typeof raw === "string" ? parseClientMessage(raw) : null;
    if (!msg) return this.send(ws, { t: "rejected", reason: "잘못된 메시지입니다." });
    const attachment = ws.deserializeAttachment() as Attachment;

    if (msg.t === "hello") {
      let room = await this.load();
      if (!room) {
        if (!msg.create) return this.send(ws, { t: "rejected", reason: "방이 없습니다." });
        room = this.wrap(Room.create(msg.room, Date.now()));
        this.room = room;
      }
      const result = room.hello(msg, await sha256Hex(msg.key));
      if (result.seat !== null) ws.serializeAttachment({ seat: result.seat } satisfies Attachment);
      for (const m of result.self) this.send(ws, m);
      this.deliver(result.out);
      await this.save(room);
      return;
    }

    const room = await this.load();
    if (!room || attachment.seat === null)
      return this.send(ws, { t: "rejected", reason: "먼저 방에 들어오세요." });
    this.deliver(room.message(attachment.seat, msg));
    await this.save(room);
  }

  override async webSocketClose(ws: WebSocket, code: number, reason: string): Promise<void> {
    try {
      ws.close(code, reason);
    } catch {
      // Already closed.
    }
    const room = await this.load();
    if (room) this.deliver(room.presence());
  }

  override async webSocketError(ws: WebSocket): Promise<void> {
    await this.webSocketClose(ws, 1011, "error");
  }

  override async alarm(): Promise<void> {
    const room = await this.load();
    if (!room) return;
    if (room.expired()) {
      for (const ws of this.ctx.getWebSockets()) ws.close(1000, "room expired");
      await this.ctx.storage.deleteAll();
      this.room = null;
      return;
    }
    this.deliver(room.alarm());
    await this.save(room);
  }
}
