// index.ts — CAOCAAO serverless game server.
// [RE] Rebuild of the original Node Socket.IO backend. Wire contract:
//   analysis/protocol_events.md + docs/lab/07-protocol-reconstruction.md
// Socket.IO v4 events carry JSON arrays [event, payload]; REST mirrors the
// recovered 492-endpoint catalog (analysis/rest_endpoints.txt) as POST with
// [user_id, ...args] bodies (Session.addUserIdObjects convention).

import { DurableObject } from "cloudflare:workers";
import { encodeOpen, decodeFrame, sid, type Handshake } from "./engineio";
import { decodeIo, connectAck, event, ack as ioAck, type IoPacket } from "./socketio";
import {
  LoginPayload, EnterWorldPayload, WantRoomPayload, BroadcastPayload,
} from "./schema";

export interface Env {
  GAME_SERVER: DurableObjectNamespace;
  DB: D1Database;
}

const HS: Omit<Handshake, "sid"> = {
  pingInterval: 25000,   // [RE] SocketIOComponent defaults
  pingTimeout: 20000,
  maxPayload: 1_000_000,
};

type Session = {
  ws: WebSocket;
  sid: string;
  userId?: string;
  authKey?: string;
  logined: boolean;
  rooms: Set<string>;
};

export default {
  async fetch(request: Request, env: Env): Promise<Response> {
    const url = new URL(request.url);

    if (url.pathname === "/socket.io/" && request.headers.get("Upgrade") === "websocket") {
      const id = env.GAME_SERVER.idFromName("global");
      return env.GAME_SERVER.get(id).fetch(request);
    }

    // REST: mirror the recovered catalog. args = [user_id, ...rest].
    if (url.pathname.startsWith("/") && request.method === "POST") {
      const id = env.GAME_SERVER.idFromName("global");
      const resp = await env.GAME_SERVER.get(id).fetch(
        new Request("https://do/rest" + url.pathname, {
          method: "POST",
          body: await request.text(),
        }));
      if (resp.status !== 404) return resp;
    }

    if (url.pathname === "/healthz") return new Response("ok");
    return new Response("not found", { status: 404 });
  },
};

export class GameServer extends DurableObject {
  private sessions = new Map<WebSocket, Session>();

  override async fetch(request: Request): Promise<Response> {
    const url = new URL(request.url);

    if (request.headers.get("Upgrade") === "websocket") {
      const pair = new WebSocketPair();
      const [client, server] = Object.values(pair) as [WebSocket, WebSocket];
      this.ctx.acceptWebSocket(server);
      const session: Session = {
        ws: server, sid: sid(), logined: false, rooms: new Set(),
      };
      this.sessions.set(server, session);
      server.send(encodeOpen({ ...HS, sid: session.sid }));
      this.ctx.setWebSocketAutoResponse(new WebSocketRequestResponsePair("2", "3"));
      return new Response(null, { status: 101, webSocket: client });
    }

    if (url.pathname.startsWith("/rest")) {
      return this.handleRest(url.pathname.slice("/rest".length), request);
    }
    return new Response("not found", { status: 404 });
  }

  // ---- REST ---------------------------------------------------------------

  private async handleRest(path: string, request: Request): Promise<Response> {
    let body: unknown[];
    try {
      body = await request.json();
      if (!Array.isArray(body)) return json({ error: "bad_args" }, 400);
    } catch {
      return json({ error: "bad_json" }, 400);
    }
    const userId = String(body[0] ?? "");
    const args = body.slice(1);

    switch (path) {
      // [RE] /user/* from rest_endpoints.txt — the login handshake REST part
      case "/user/login": {
        // args: [device_id?, npa_token?]; idempotent guest registration.
        const target = userId || String(args[0] ?? "");
        const row = await this.ensureUser(target);
        return json({ userId: row.user_id, authKey: row.auth_key, maintenance: false });
      }
      case "/user/getUserData":
        return json(await this.getUserData(userId));
      case "/user/updateHistory":
        // [RE] HeartBeats consumes {success, seq, notice, entrustTurn/Battle}
        return json({ success: true, seq: 0, notice: "", entrustTurn: false, entrustBattle: false });
      default:
        // [RE] every recovered endpoint lands here in later milestones
        // (guild/, arena/, battle/...); explicit 404 keeps the contract honest.
        return new Response("not implemented: " + path, { status: 404 });
    }
  }

  private async ensureUser(userId: string) {
    const db = (this.env as Env).DB;
    if (userId) {
      const found = await db
        .prepare("SELECT user_id, auth_key FROM players WHERE user_id = ?1")
        .bind(userId).first<{ user_id: string; auth_key: string }>();
      if (found) return found;
    }
    const newId = userId || sid(8);
    const authKey = sid(24);
    await db.prepare(
      "INSERT INTO players (user_id, auth_key, created_at) VALUES (?1, ?2, ?3)")
      .bind(newId, authKey, Date.now()).run();
    return { user_id: newId, auth_key: authKey };
  }

  private async getUserData(userId: string) {
    const db = (this.env as Env).DB;
    const row = await db.prepare(
      "SELECT user_id, created_at FROM players WHERE user_id = ?1")
      .bind(userId).first<{ user_id: string; created_at: number }>();
    if (!row) return { error: "notFoundUserId" };   // [RE] literal exists in metadata
    return { userId: row.user_id, createdAt: row.created_at };
  }

  // ---- WebSocket / socket.io ----------------------------------------------

  override async webSocketMessage(ws: WebSocket, message: string | ArrayBuffer): Promise<void> {
    if (typeof message !== "string") return;
    const frame = decodeFrame(message);
    if (!frame) return;

    switch (frame.type) {
      case "ping": ws.send("3"); break;             // edge auto-response may cover this
      case "message": {
        const pkt = decodeIo(frame.data);
        if (pkt) await this.handleIo(ws, pkt);
        break;
      }
      case "close": this.drop(ws); break;
    }
  }

  override async webSocketClose(ws: WebSocket): Promise<void> {
    const s = this.sessions.get(ws);
    if (!s) return;
    for (const room of s.rooms) this.broadcastTo(room, event("playerLeft", { sid: s.sid }), ws);
    this.drop(ws);
  }

  private drop(ws: WebSocket): void {
    this.sessions.delete(ws);
  }

  private async handleIo(ws: WebSocket, pkt: IoPacket): Promise<void> {
    const s = this.sessions.get(ws);
    if (!s) return;

    switch (pkt.kind) {
      case "connect":
        ws.send(connectAck({ sid: s.sid }));
        break;

      case "event":
        try {
          await this.handleEvent(ws, s, pkt.eventName ?? "", pkt.args, pkt.ackId);
        } catch (e) {
          // [RE] SessionImpl emits "error" on protocol failures
          ws.send(event("error", { message: String(e) }));
        }
        break;

      case "ack":
      case "disconnect":
      case "connectError":
        break;
    }
  }

  private async handleEvent(
    ws: WebSocket, s: Session, name: string, args: unknown[], ackId?: number,
  ): Promise<void> {
    const payload = args[0] ?? {};
    const reply = (ackData: unknown[]) =>
      ackId !== undefined ? ws.send(ioAck(ackId, ackData)) : ws.send(event(name, ...ackData));

    switch (name) {
      // [RE] SessionImpl$$UpdateSocketLogin: ["login", {userId, authKey}]
      case "login": {
        const p = LoginPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        s.userId = String(p.data.userId);
        s.authKey = p.data.authKey;
        const db = (this.env as Env).DB;
        const row = await db.prepare("SELECT auth_key FROM players WHERE user_id = ?1")
          .bind(s.userId).first<{ auth_key: string }>();
        if (!row || row.auth_key !== p.data.authKey) {
          return reply([{ error: "authFail" }]);
        }
        s.logined = true;
        return reply([{ userId: s.userId }]);          // → OnSocketLogined path
      }

      // [RE] SessionImpl$$EnterSocketServerWorldRoom
      case "enterToWorld": {
        if (!s.logined) return reply([{ error: "notLogined" }]);
        const p = EnterWorldPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = roomName(p.data);
        s.rooms.add(room);
        this.broadcastTo(room, event("userJoined", { userId: s.userId }), ws);
        return reply([{ room_id: room, world_id: p.data.world_id ?? 0 }]);
      }

      // [RE] matchmaking: want_room_id / room lifecycle (MultiplayManager)
      case "want_room_id": {
        const p = WantRoomPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = `${p.data.room_type}:${sid(6)}`;
        s.rooms.add(room);
        return reply([{ room_id: room, room_type: p.data.room_type }]);
      }

      case "leaveRoom": {
        const roomId = String((payload as { room_id?: string })?.room_id ?? "");
        s.rooms.delete(roomId);
        this.broadcastTo(roomId, event("playerLeft", { userId: s.userId }), ws);
        return reply([{ room_id: roomId }]);
      }

      // [RE] Chat.Chatting$$EmitChattingMessage → broadcastTo channel
      case "broadcastTo": {
        const p = BroadcastPayload.safeParse(payload);
        if (!p.success) return reply([{ error: "bad_payload" }]);
        const room = p.data.room_id ?? "";
        this.broadcastTo(room, event("chattingMessage", {
          type: p.data.type ?? 0, subType: p.data.sub_type ?? 0,
          msg: p.data.msg, userId: s.userId,
        }));
        return reply([{ success: true }]);
      }

      // [RE] world history sync (RequestGetWorldData params)
      case "updateHistory":
        return reply([{ success: true, seq: 0 }]);

      default:
        // Unknown events: [RE] original SessionImpl → "error" envelope
        return reply([{ error: "notFoundEventId", event: name }]);
    }
  }

  private broadcastTo(room: string, text: string, except?: WebSocket): void {
    for (const [ws, sess] of this.sessions) {
      if (ws !== except && sess.rooms.has(room)) ws.send(text);
    }
  }
}

function roomName(p: { world_id?: number; guild_id?: number; room_type?: string }): string {
  if (p.guild_id !== undefined) return `guild:${p.guild_id}`;
  if (p.world_id !== undefined) return `world:${p.world_id}`;
  return `${p.room_type ?? "default"}:global`;
}

function json(obj: unknown, status = 200): Response {
  return new Response(JSON.stringify(obj), {
    status,
    headers: { "content-type": "application/json" },
  });
}
