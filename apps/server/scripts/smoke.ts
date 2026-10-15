/**
 * Plays a whole Arena match against a running server over real WebSockets:
 * two clients join, draft, deploy, and battle with the AI choosing their moves;
 * one client drops mid-battle and reconnects.
 *
 *   bun apps/server/scripts/smoke.ts http://127.0.0.1:8787
 */
import { P0_RULES } from "@tk/content";
import {
  PROTOCOL_VERSION,
  parseServerMessage,
  ROOM_ALPHABET,
  type RoomView,
  type Seat,
  type ServerMessage,
} from "@tk/protocol";
import { type BattleState, chooseCommand } from "@tk/rules";

const base = (process.argv[2] ?? "http://127.0.0.1:8787").replace(/^http/, "ws");
/** `--idle`: seat B stops moving after the reconnect, so the clock and the AI must finish its turns. */
const idle = process.argv.includes("--idle");
const hex = (n: number) =>
  [...crypto.getRandomValues(new Uint8Array(n))]
    .map((b) => b.toString(16).padStart(2, "0"))
    .join("");
const code = [...crypto.getRandomValues(new Uint8Array(6))]
  .map((b) => ROOM_ALPHABET[b % ROOM_ALPHABET.length])
  .join("");

function fail(why: string): never {
  console.error(`FAIL: ${why}`);
  process.exit(1);
}

class Player {
  ws!: WebSocket;
  seat: Seat | null = null;
  room: RoomView | null = null;
  state: BattleState | null = null;
  seq = 0;
  batches = 0;
  readonly key = hex(16);
  private waiters: ((m: ServerMessage) => boolean)[] = [];

  constructor(
    readonly name: string,
    readonly create: boolean,
  ) {}

  connect(lastSeq: number | null = null): Promise<void> {
    return new Promise((resolve, reject) => {
      this.ws = new WebSocket(`${base}/api/rooms/${code}/ws`);
      this.ws.onopen = () => {
        this.send({
          t: "hello",
          v: PROTOCOL_VERSION,
          room: code,
          key: this.key,
          name: this.name,
          lastSeq,
          create: this.create,
        });
        resolve();
      };
      this.ws.onerror = () => reject(new Error("socket error"));
      this.ws.onmessage = (e) => this.onMessage(String(e.data));
    });
  }

  send(msg: unknown): void {
    this.ws.send(JSON.stringify(msg));
  }

  until(pred: (m: ServerMessage) => boolean, ms = 20_000): Promise<void> {
    return new Promise((resolve, reject) => {
      const timer = setTimeout(() => reject(new Error(`${this.name}: timed out`)), ms);
      this.waiters.push((m) => {
        if (!pred(m)) return false;
        clearTimeout(timer);
        resolve();
        return true;
      });
    });
  }

  private onMessage(raw: string): void {
    const m = parseServerMessage(raw);
    if (!m) fail(`unparsable message ${raw.slice(0, 80)}`);
    if (m.t === "welcome") {
      this.seat = m.seat;
      this.room = m.room;
    } else if (m.t === "room") this.room = m.room;
    else if (m.t === "snapshot") {
      this.state = m.state;
      this.seq = m.seq;
    } else if (m.t === "batch") {
      if (m.seq !== this.seq + 1) fail(`${this.name}: batch ${m.seq} after ${this.seq}`);
      if (m.state.prng.some((v) => v !== 0)) fail("a state leaked the Prng");
      this.state = m.state;
      this.seq = m.seq;
      this.batches++;
      if (m.by === "ai") this.aiBatches++;
    } else if (m.t === "rejected" && !this.expectRejection)
      fail(`${this.name} rejected: ${m.reason}`);
    this.waiters = this.waiters.filter((w) => !w(m));
    this.act();
  }

  expectRejection = false;

  /** Stops making moves (the server's clock must play for it). */
  asleep = false;
  aiBatches = 0;

  /** The last decision point this player acted on, so it acts once per point. */
  private actedOn = "";

  /** Picks, readies or moves when it is this player's turn. */
  act(): void {
    const r = this.room;
    if (!r || this.seat === null) return;
    const once = (point: string, msg: unknown) => {
      if (this.actedOn === point) return;
      this.actedOn = point;
      this.send(msg);
    };
    if (r.phase === "draft" && r.draft) {
      const n = r.draft.picks[0].length + r.draft.picks[1].length;
      if (r.draft.order[n] === this.seat)
        once(`pick${n}`, { t: "pick", entry: r.draft.available[0] });
    } else if (r.phase === "deploy" && r.deploy && !r.deploy.ready[this.seat]) {
      once("ready", { t: "ready" });
    } else if (
      r.phase === "battle" &&
      !this.asleep &&
      this.state?.winner === null &&
      this.state.active === this.seat
    ) {
      const c = chooseCommand(this.state, P0_RULES);
      if (c) once(`seq${this.seq}`, { t: "command", command: c });
    }
  }

  /** Forgets the last decision point (after a reconnect). */
  reset(): void {
    this.actedOn = "";
  }
}

const t0 = Date.now();
const stray = new Player("길잃은", false);
stray.expectRejection = true;
await stray.connect();
await stray.until((m) => m.t === "rejected");
stray.ws.close();
console.log("unknown room refused without create: ok");

const a = new Player("조조팬", true);
const b = new Player("유비팬", false);
await a.connect();
await a.until((m) => m.t === "welcome");
await b.connect();
await b.until((m) => m.t === "snapshot");
console.log(`room ${code}: drafted and deployed in ${Date.now() - t0} ms`);

await b.until(() => b.batches >= 6);
const seenBeforeDrop = b.seq;
b.ws.close();
await a.until(() => a.room?.players[1]?.connected === false || a.seq > seenBeforeDrop + 2, 60_000);
await b.connect(seenBeforeDrop);
await b.until((m) => m.t === "welcome");
console.log(`seat B dropped at batch ${seenBeforeDrop} and reconnected: ok`);
b.reset();
if (idle) b.asleep = true;
b.act();

await Promise.all([
  a.until(() => a.room?.phase === "over", 180_000),
  b.until(() => b.room?.phase === "over", 180_000),
]);
if (a.seq !== b.seq) fail(`clients disagree: ${a.seq} vs ${b.seq}`);
if (JSON.stringify(a.state) !== JSON.stringify(b.state)) fail("final states differ");
console.log(
  `match over after ${a.seq} batches (${a.aiBatches} by the clock's AI), winner seat ${a.room?.result?.winner}, ${Date.now() - t0} ms`,
);
if (idle && a.aiBatches === 0) fail("the clock never handed the idle seat to the AI");
a.ws.close();
b.ws.close();
process.exit(0);
