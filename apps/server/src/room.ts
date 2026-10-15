/**
 * One Arena room from invite to result (ADR 0039–0041): seats, the snake draft,
 * deployment, the alternating battle refereed by @tk/rules, and the activation
 * clock (ADR 0040). It knows nothing of Cloudflare: the MatchDO feeds it
 * messages, time, randomness and connection status, and delivers what it returns.
 */
import {
  ARENA_POOL,
  ARMY_SIZE,
  arenaSetup,
  DEPLOY_SLOTS,
  DRAFT_ORDER,
  P0_RULES,
  poolEntry,
} from "@tk/content";
import {
  type ClientMessage,
  type ClockView,
  cleanName,
  PROTOCOL_VERSION,
  type RoomPhase,
  type RoomView,
  redact,
  type Seat,
  type ServerMessage,
} from "@tk/protocol";
import {
  apply,
  type BattleEvent,
  type BattleState,
  type Command,
  chooseCommand,
  createBattle,
  type WeatherKind,
} from "@tk/rules";

export const TURN_MS = 45_000;
export const RESERVE_MS = 180_000;
export const PICK_MS = 30_000;
export const DEPLOY_MS = 60_000;
export const ROOM_TTL_MS = 24 * 3_600_000;
/** Batches kept in memory for reconnecting clients; older gaps get a snapshot. */
export const RECENT_BATCHES = 40;

const WEATHERS: readonly WeatherKind[] = ["sunny", "cloudy", "rain", "wind"];

export interface SeatData {
  name: string;
  /** SHA-256 of the seat's device key, hex (ADR 0038). */
  keyHash: string;
}

export interface BattleData {
  /** Secret: holds the Prng and the seed's effect (ADR 0041). */
  start: BattleState;
  state: BattleState;
  commands: Command[];
  seq: number;
  /** Clock of the running activation. */
  active: Seat;
  freeUntil: number;
  reserveMs: [number, number];
}

/** Everything the room persists. */
export interface RoomData {
  code: string;
  createdAt: number;
  updatedAt: number;
  phase: RoomPhase;
  seats: [SeatData | null, SeatData | null];
  picks: [string[], string[]];
  arrangement: [string[], string[]];
  ready: [boolean, boolean];
  /** Deadline of the current pick or of deployment. */
  deadline: number | null;
  battle: BattleData | null;
  result: { winner: number; reason: "battle" | "concede" } | null;
}

/** What the platform provides. */
export interface RoomEnv {
  now(): number;
  /** Multiplies every clock (1 in production; small values speed up local tests). */
  clockScale?: number;
  /** Uniform random integer in [0, n) from a secure source. */
  randomInt(n: number): number;
  isConnected(seat: Seat): boolean;
}

export type Out = { to: Seat | "all"; msg: ServerMessage };

interface Batch {
  seq: number;
  by: "player" | "ai";
  events: BattleEvent[];
  state: BattleState;
}

export class Room {
  /** Recent batches, memory only: lost when the object sleeps (clients then get a snapshot). */
  private recent: Batch[] = [];

  private readonly ms: { turn: number; reserve: number; pick: number; deploy: number };

  constructor(
    public data: RoomData,
    private readonly env: RoomEnv,
  ) {
    const k = env.clockScale ?? 1;
    const scale = (v: number) => Math.max(1, Math.round(v * k));
    this.ms = {
      turn: scale(TURN_MS),
      reserve: scale(RESERVE_MS),
      pick: scale(PICK_MS),
      deploy: scale(DEPLOY_MS),
    };
  }

  static create(code: string, now: number): RoomData {
    return {
      code,
      createdAt: now,
      updatedAt: now,
      phase: "waiting",
      seats: [null, null],
      picks: [[], []],
      arrangement: [[], []],
      ready: [false, false],
      deadline: null,
      battle: null,
      result: null,
    };
  }

  // ------------------------------------------------------------ views

  /** The room as `seat` may see it: the other seat's deploy arrangement stays hidden. */
  view(seat: Seat | null = null): RoomView {
    const d = this.data;
    const players = d.seats.map((s, i) =>
      s ? { name: s.name, connected: this.env.isConnected(i as Seat) } : null,
    ) as RoomView["players"];
    const taken = new Set([...d.picks[0], ...d.picks[1]]);
    return {
      code: d.code,
      phase: d.phase,
      players,
      draft:
        d.phase === "draft"
          ? {
              available: ARENA_POOL.map((e) => e.id).filter((id) => !taken.has(id)),
              picks: [[...d.picks[0]], [...d.picks[1]]],
              order: [...DRAFT_ORDER],
              deadline: d.deadline ?? 0,
            }
          : null,
      deploy:
        d.phase === "deploy"
          ? {
              slots: [
                DEPLOY_SLOTS[0].map((c) => ({ ...c })),
                DEPLOY_SLOTS[1].map((c) => ({ ...c })),
              ],
              arrangement: [
                seat === 1 ? [] : [...d.arrangement[0]],
                seat === 0 ? [] : [...d.arrangement[1]],
              ],
              ready: [d.ready[0], d.ready[1]],
              deadline: d.deadline ?? 0,
            }
          : null,
      result: d.result ? { ...d.result } : null,
    };
  }

  clock(): ClockView | null {
    const b = this.data.battle;
    if (!b || this.data.phase !== "battle") return null;
    return {
      active: b.active,
      freeUntil: b.freeUntil,
      reserveMs: [b.reserveMs[0], b.reserveMs[1]],
      deadline: b.freeUntil + b.reserveMs[b.active],
    };
  }

  /** When the platform should call `alarm()` next. */
  nextAlarm(): number {
    const d = this.data;
    const expiry = d.updatedAt + ROOM_TTL_MS;
    if (d.phase === "draft" || d.phase === "deploy") return Math.min(d.deadline ?? expiry, expiry);
    const c = this.clock();
    return c ? Math.min(c.deadline, expiry) : expiry;
  }

  expired(): boolean {
    return this.env.now() >= this.data.updatedAt + ROOM_TTL_MS;
  }

  /** The room update, as each seat may see it. */
  private roomOuts(): Out[] {
    return [
      { to: 0, msg: { t: "room", room: this.view(0) } },
      { to: 1, msg: { t: "room", room: this.view(1) } },
    ];
  }

  // ------------------------------------------------------------ joining

  /**
   * A `hello`: takes or reclaims a seat. Returns the seat (or null if refused),
   * the messages for the joining socket, and the messages for everyone.
   */
  hello(
    msg: Extract<ClientMessage, { t: "hello" }>,
    keyHash: string,
  ): { seat: Seat | null; self: ServerMessage[]; out: Out[] } {
    const refuse = (reason: string) => ({
      seat: null,
      self: [{ t: "rejected" as const, reason }],
      out: [],
    });
    if (msg.v !== PROTOCOL_VERSION) return refuse("새 버전이 있습니다. 새로고침하세요.");
    if (msg.room !== this.data.code) return refuse("방 코드가 맞지 않습니다.");
    const name = cleanName(msg.name);
    if (!name) return refuse("이름은 1–12자입니다.");
    const d = this.data;
    let seat = d.seats.findIndex((s) => s?.keyHash === keyHash);
    if (seat < 0) {
      seat = d.seats.indexOf(null);
      if (seat < 0) return refuse("방이 가득 찼습니다.");
      d.seats[seat] = { name, keyHash };
    } else (d.seats[seat] as SeatData).name = name;
    const me = seat as Seat;
    this.touch();
    if (d.phase === "waiting" && d.seats[0] && d.seats[1]) this.startDraft();

    const self: ServerMessage[] = [
      { t: "welcome", seat: me, room: this.view(me), serverTime: this.env.now() },
    ];
    const b = d.battle;
    if (b && (d.phase === "battle" || d.phase === "over")) {
      const last = msg.lastSeq;
      const first = this.recent[0];
      if (last !== null && last < b.seq && first && first.seq <= last + 1) {
        for (const batch of this.recent)
          if (batch.seq > last) self.push({ t: "batch", ...batch, clock: this.clock() });
      } else if (last !== b.seq) {
        self.push({ t: "snapshot", seq: b.seq, state: redact(b.state), clock: this.clock() });
      }
    }
    return { seat: me, self, out: this.roomOuts() };
  }

  /** Someone's socket closed; tells everyone who is still connected. */
  presence(): Out[] {
    return this.roomOuts();
  }

  // ------------------------------------------------------------ messages

  message(seat: Seat, msg: ClientMessage): Out[] {
    switch (msg.t) {
      case "pick":
        return this.pick(seat, msg.entry);
      case "arrange":
        return this.arrange(seat, msg.order);
      case "ready":
        return this.readyUp(seat);
      case "command":
        return this.command(seat, msg.command);
      case "concede":
        return this.concede(seat);
      case "ping":
        return [{ to: seat, msg: { t: "pong", at: msg.at, serverTime: this.env.now() } }];
      default:
        return [];
    }
  }

  /** Called at or after `nextAlarm()`: runs out the draft, deploy or activation clock. */
  alarm(): Out[] {
    const d = this.data;
    const now = this.env.now();
    if ((d.phase === "draft" || d.phase === "deploy") && d.deadline !== null && now >= d.deadline) {
      if (d.phase === "draft")
        return this.pick(this.pickingSeat(), this.autoPick(this.pickingSeat()));
      return this.startBattle();
    }
    const c = this.clock();
    if (c && now >= c.deadline) return this.aiActivation();
    return [];
  }

  // ------------------------------------------------------------ draft and deploy

  private startDraft(): void {
    this.data.phase = "draft";
    this.data.deadline = this.env.now() + this.ms.pick;
  }

  private pickingSeat(): Seat {
    const d = this.data;
    return DRAFT_ORDER[d.picks[0].length + d.picks[1].length] as Seat;
  }

  /** The seat's automatic pick: an entry of the class it has least of, in pool order. */
  private autoPick(seat: Seat): string {
    const d = this.data;
    const taken = new Set([...d.picks[0], ...d.picks[1]]);
    const mine = d.picks[seat].map((id) => poolEntry(id)?.classId);
    let best = "";
    let fewest = Number.MAX_SAFE_INTEGER;
    for (const e of ARENA_POOL) {
      if (taken.has(e.id)) continue;
      const n = mine.filter((c) => c === e.classId).length;
      if (n < fewest) {
        fewest = n;
        best = e.id;
      }
    }
    return best;
  }

  private pick(seat: Seat, entry: string): Out[] {
    const d = this.data;
    if (d.phase !== "draft") return this.reject(seat, "지금은 선택할 수 없습니다.");
    if (this.pickingSeat() !== seat) return this.reject(seat, "상대의 선택 차례입니다.");
    const taken = new Set([...d.picks[0], ...d.picks[1]]);
    if (!poolEntry(entry) || taken.has(entry))
      return this.reject(seat, "선택할 수 없는 장수입니다.");
    d.picks[seat].push(entry);
    this.touch();
    if (d.picks[0].length + d.picks[1].length === DRAFT_ORDER.length) {
      d.phase = "deploy";
      d.arrangement = [[...d.picks[0]], [...d.picks[1]]];
      d.ready = [false, false];
      d.deadline = this.env.now() + this.ms.deploy;
    } else d.deadline = this.env.now() + this.ms.pick;
    return this.roomOuts();
  }

  private arrange(seat: Seat, order: string[]): Out[] {
    const d = this.data;
    if (d.phase !== "deploy" || d.ready[seat])
      return this.reject(seat, "지금은 배치를 바꿀 수 없습니다.");
    const mine = [...d.picks[seat]].sort();
    const given = [...order].sort();
    if (given.length !== ARMY_SIZE || given.some((id, i) => id !== mine[i]))
      return this.reject(seat, "배치가 올바르지 않습니다.");
    d.arrangement[seat] = [...order];
    this.touch();
    return this.roomOuts();
  }

  private readyUp(seat: Seat): Out[] {
    const d = this.data;
    if (d.phase !== "deploy") return this.reject(seat, "지금은 준비할 수 없습니다.");
    d.ready[seat] = true;
    this.touch();
    if (d.ready[0] && d.ready[1]) return this.startBattle();
    return this.roomOuts();
  }

  private startBattle(): Out[] {
    const d = this.data;
    const seed = Array.from({ length: 4 }, () => this.env.randomInt(0x7fffffff).toString(36)).join(
      "-",
    );
    const weather = WEATHERS[this.env.randomInt(WEATHERS.length)] ?? "cloudy";
    const start = createBattle(P0_RULES, arenaSetup({ seed, weather, armies: d.arrangement }));
    d.phase = "battle";
    d.deadline = null;
    d.battle = {
      start,
      state: start,
      commands: [],
      seq: 0,
      active: start.active as Seat,
      freeUntil: this.env.now() + this.ms.turn,
      reserveMs: [this.ms.reserve, this.ms.reserve],
    };
    this.recent = [];
    this.touch();
    return [
      ...this.roomOuts(),
      { to: "all", msg: { t: "snapshot", seq: 0, state: redact(start), clock: this.clock() } },
    ];
  }

  // ------------------------------------------------------------ battle

  private command(seat: Seat, command: Command): Out[] {
    const d = this.data;
    const b = d.battle;
    if (d.phase !== "battle" || !b) return this.reject(seat, "전투 중이 아닙니다.");
    if (b.active !== seat) return this.reject(seat, "상대의 차례입니다.");
    const now = this.env.now();
    b.reserveMs[seat] = Math.max(0, b.reserveMs[seat] - Math.max(0, now - b.freeUntil));
    return this.applyCommand(command, "player", seat);
  }

  /** The clock ran out: the AI plays this one activation (ADR 0040). */
  private aiActivation(): Out[] {
    const b = this.data.battle;
    if (!b) return [];
    b.reserveMs[b.active] = 0;
    const command = chooseCommand(b.state, P0_RULES);
    if (!command) return [];
    return this.applyCommand(command, "ai", b.active);
  }

  private applyCommand(command: Command, by: "player" | "ai", seat: Seat): Out[] {
    const d = this.data;
    const b = d.battle as BattleData;
    const r = apply(b.state, P0_RULES, command);
    if (!r.ok) return this.reject(seat, r.reason);
    b.state = r.state;
    b.commands.push(command);
    b.seq += 1;
    b.active = r.state.active as Seat;
    b.freeUntil = this.env.now() + this.ms.turn;
    this.touch();
    const out: Out[] = [];
    if (r.state.winner !== null) {
      d.phase = "over";
      d.result = { winner: r.state.winner, reason: "battle" };
    }
    const batch: Batch = { seq: b.seq, by, events: r.events, state: redact(r.state) };
    this.recent.push(batch);
    if (this.recent.length > RECENT_BATCHES) this.recent.shift();
    out.push({ to: "all", msg: { t: "batch", ...batch, clock: this.clock() } });
    if (d.phase === "over") out.push(...this.roomOuts());
    return out;
  }

  private concede(seat: Seat): Out[] {
    const d = this.data;
    if (d.phase === "over" || d.phase === "waiting")
      return this.reject(seat, "기권할 수 없습니다.");
    d.phase = "over";
    d.deadline = null;
    d.result = { winner: seat === 0 ? 1 : 0, reason: "concede" };
    this.touch();
    return this.roomOuts();
  }

  private reject(seat: Seat, reason: string): Out[] {
    return [{ to: seat, msg: { t: "rejected", reason } }];
  }

  private touch(): void {
    this.data.updatedAt = this.env.now();
  }
}
