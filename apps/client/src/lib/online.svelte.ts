/**
 * One Arena room seen from this client (ADR 0037): the socket, reconnects with
 * the last batch seen, the room view (lobby, draft, deploy), the clock, and the
 * battle store fed by the referee's batches.
 */
import {
  type ClientMessage,
  type ClockView,
  PROTOCOL_VERSION,
  parseServerMessage,
  type RoomView,
  type Seat,
  type ServerMessage,
} from "@tk/protocol";
import { deviceKey, doneCreating, savedName, takeCreating } from "./identity.ts";
import { Session } from "./session.svelte.ts";

export type Link = "connecting" | "open" | "retrying" | "refused";

export class OnlineRoom {
  room = $state<RoomView | null>(null);
  seat = $state<Seat | null>(null);
  link = $state<Link>("connecting");
  error = $state<string | null>(null);
  clock = $state<ClockView | null>(null);
  /** True once the battle state has arrived. */
  battleReady = $state(false);
  /** Last message from the referee that the player should see (a refused action). */
  notice = $state<string | null>(null);
  readonly session = new Session();

  /** Server clock minus local clock, from `welcome` and `pong`. */
  private offset = 0;
  private ws: WebSocket | null = null;
  private lastSeq: number | null = null;
  private retries = 0;
  private closed = false;
  private retryTimer: ReturnType<typeof setTimeout> | undefined;
  private pingTimer: ReturnType<typeof setInterval> | undefined;

  constructor(readonly code: string) {
    this.session.remote = { send: (command) => this.send({ t: "command", command }) };
    this.session.sideNames = ["A", "B"];
    this.connect();
  }

  /** Server time now, by this client's estimate. */
  serverNow(): number {
    return Date.now() + this.offset;
  }

  inviteLink(): string {
    return `${location.origin}${location.pathname}#/r/${this.code}`;
  }

  private url(): string {
    const proto = location.protocol === "https:" ? "wss:" : "ws:";
    return `${proto}//${location.host}/api/rooms/${this.code}/ws`;
  }

  private connect(): void {
    if (this.closed) return;
    const ws = new WebSocket(this.url());
    this.ws = ws;
    ws.onopen = () => {
      this.retries = 0;
      this.link = "open";
      this.send({
        t: "hello",
        v: PROTOCOL_VERSION,
        room: this.code,
        key: deviceKey(),
        name: savedName() || "손님",
        lastSeq: this.lastSeq,
        create: takeCreating(this.code),
      });
    };
    ws.onmessage = (e) => {
      const m = parseServerMessage(String(e.data));
      if (m) this.handle(m);
    };
    ws.onclose = () => {
      if (this.ws !== ws || this.closed || this.link === "refused") return;
      this.link = "retrying";
      const wait = Math.min(10_000, 500 * 2 ** this.retries++);
      this.retryTimer = setTimeout(() => this.connect(), wait);
    };
    clearInterval(this.pingTimer);
    this.pingTimer = setInterval(() => this.send({ t: "ping", at: Date.now() }), 20_000);
  }

  private handle(m: ServerMessage): void {
    switch (m.t) {
      case "welcome":
        this.seat = m.seat;
        this.offset = m.serverTime - Date.now();
        this.session.humanSide = m.seat;
        doneCreating(this.code);
        this.setRoom(m.room);
        break;
      case "room":
        this.setRoom(m.room);
        break;
      case "snapshot":
        this.lastSeq = m.seq;
        this.clock = m.clock;
        this.session.loadSnapshot(m.state);
        this.battleReady = true;
        break;
      case "batch":
        this.lastSeq = m.seq;
        this.clock = m.clock;
        this.session.receive(m.events, m.state);
        break;
      case "rejected":
        if (this.seat === null) {
          this.link = "refused";
          this.error = m.reason;
          this.ws?.close();
        } else {
          this.notice = m.reason;
          this.session.rejected(m.reason);
        }
        break;
      case "pong":
        this.offset = m.serverTime - Math.round((m.at + Date.now()) / 2);
        break;
    }
  }

  private setRoom(room: RoomView): void {
    this.room = room;
    this.session.sideNames = room.players.map((p, i) => p?.name ?? (i === 0 ? "A" : "B"));
  }

  send(msg: ClientMessage): void {
    if (this.ws?.readyState === WebSocket.OPEN) this.ws.send(JSON.stringify(msg));
  }

  pick(entry: string): void {
    this.send({ t: "pick", entry });
  }

  arrange(order: string[]): void {
    this.send({ t: "arrange", order });
  }

  ready(): void {
    this.send({ t: "ready" });
  }

  concede(): void {
    this.send({ t: "concede" });
  }

  dispose(): void {
    this.closed = true;
    clearTimeout(this.retryTimer);
    clearInterval(this.pingTimer);
    this.ws?.close();
  }
}
