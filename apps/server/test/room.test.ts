import { describe, expect, it } from "bun:test";
import { ARENA_POOL, DRAFT_ORDER, P0_RULES } from "@tk/content";
import { PROTOCOL_VERSION, type Seat, type ServerMessage } from "@tk/protocol";
import { legalCommands, Prng, seedFromString } from "@tk/rules";
import {
  DEPLOY_MS,
  type Out,
  PICK_MS,
  RESERVE_MS,
  Room,
  type RoomEnv,
  TURN_MS,
} from "../src/room.ts";

class FakeEnv implements RoomEnv {
  t = 1_000_000;
  connected: [boolean, boolean] = [true, true];
  private prng = new Prng(seedFromString("env"));
  now() {
    return this.t;
  }
  randomInt(n: number) {
    return this.prng.int(n);
  }
  isConnected(seat: Seat) {
    return this.connected[seat];
  }
}

const KEY_A = "a".repeat(64);
const KEY_B = "b".repeat(64);
const hello = (name: string, lastSeq: number | null = null) => ({
  t: "hello" as const,
  v: PROTOCOL_VERSION,
  room: "7KQ2MX",
  key: "0".repeat(32),
  name,
  lastSeq,
  create: false,
});

function setup() {
  const env = new FakeEnv();
  const room = new Room(Room.create("7KQ2MX", env.now()), env);
  room.hello(hello("조조팬"), KEY_A);
  room.hello(hello("유비팬"), KEY_B);
  return { env, room };
}

const msgs = (out: Out[], t: ServerMessage["t"]) => out.map((o) => o.msg).filter((m) => m.t === t);

/** Drafts with each seat taking the first available entry. */
function draftAll(room: Room) {
  for (const seat of DRAFT_ORDER) {
    const available = room.view().draft?.available ?? [];
    room.message(seat, { t: "pick", entry: available[0] as string });
  }
}

describe("seats (ADR 0038)", () => {
  it("the first two keys take seats A and B; a third is refused; a known key reclaims its seat", () => {
    const env = new FakeEnv();
    const room = new Room(Room.create("7KQ2MX", env.now()), env);
    expect(room.hello(hello("조조팬"), KEY_A).seat).toBe(0);
    expect(room.data.phase).toBe("waiting");
    expect(room.hello(hello("유비팬"), KEY_B).seat).toBe(1);
    expect(room.data.phase).toBe("draft");
    expect(room.hello(hello("손권팬"), "c".repeat(64)).seat).toBeNull();
    const again = room.hello(hello("조조팬2"), KEY_A);
    expect(again.seat).toBe(0);
    expect(room.data.seats[0]?.name).toBe("조조팬2");
  });

  it("refuses an old protocol version, a wrong room code or a bad name", () => {
    const env = new FakeEnv();
    const room = new Room(Room.create("7KQ2MX", env.now()), env);
    expect(room.hello({ ...hello("a"), v: PROTOCOL_VERSION + 1 }, KEY_A).seat).toBeNull();
    expect(room.hello({ ...hello("a"), room: "ZZZZZZ" }, KEY_A).seat).toBeNull();
    expect(room.hello(hello("   "), KEY_A).seat).toBeNull();
  });
});

describe("draft and deploy (ADR 0039, ADR 0040)", () => {
  it("follows the snake order, refuses out-of-turn and taken picks", () => {
    const { room } = setup();
    expect(DRAFT_ORDER[0]).toBe(1);
    expect(msgs(room.message(0, { t: "pick", entry: "lu-bu" }), "rejected")).toHaveLength(1);
    room.message(1, { t: "pick", entry: "lu-bu" });
    expect(msgs(room.message(0, { t: "pick", entry: "lu-bu" }), "rejected")).toHaveLength(1);
    room.message(0, { t: "pick", entry: "guan-yu" });
    expect(room.view().draft?.picks).toEqual([["guan-yu"], ["lu-bu"]]);
  });

  it("a pick that runs out of time is made automatically, favouring a class the seat lacks", () => {
    const { env, room } = setup();
    room.message(1, { t: "pick", entry: "lu-bu" });
    env.t += PICK_MS;
    expect(room.nextAlarm()).toBeLessThanOrEqual(env.t);
    room.alarm();
    expect(room.data.picks[0]).toHaveLength(1);
  });

  it("deploy: arrange is a permutation of your own picks; both ready starts the battle", () => {
    const { room } = setup();
    draftAll(room);
    expect(room.data.phase).toBe("deploy");
    const mine = [...room.data.picks[0]];
    expect(
      msgs(
        room.message(0, { t: "arrange", order: [...mine.slice(1), "zhuge-liang-x"] }),
        "rejected",
      ),
    ).toHaveLength(1);
    room.message(0, { t: "arrange", order: [...mine].reverse() });
    expect(room.data.arrangement[0]).toEqual([...mine].reverse());
    room.message(0, { t: "ready" });
    expect(room.data.phase).toBe("deploy");
    const out = room.message(1, { t: "ready" });
    expect(room.data.phase).toBe("battle");
    const snap = msgs(out, "snapshot")[0];
    expect(snap && snap.t === "snapshot" && snap.state.prng).toEqual([0, 0, 0, 0]);
    expect(room.data.battle?.start.prng).not.toEqual([0, 0, 0, 0]);
  });

  it("deployment that runs out of time starts the battle as arranged", () => {
    const { env, room } = setup();
    draftAll(room);
    env.t += DEPLOY_MS;
    room.alarm();
    expect(room.data.phase).toBe("battle");
  });
});

function battle() {
  const s = setup();
  draftAll(s.room);
  s.room.message(0, { t: "ready" });
  s.room.message(1, { t: "ready" });
  return s;
}

describe("battle and clock (ADR 0040)", () => {
  it("only the active seat may command; a legal command produces a numbered batch without the Prng", () => {
    const { room } = battle();
    const b = room.data.battle;
    if (!b) throw new Error("no battle");
    expect(b.active).toBe(0);
    const cmd = legalCommands(b.state, P0_RULES)[0];
    if (!cmd) throw new Error("no command");
    expect(msgs(room.message(1, { t: "command", command: cmd }), "rejected")).toHaveLength(1);
    const out = room.message(0, { t: "command", command: cmd });
    const batch = msgs(out, "batch")[0];
    expect(batch && batch.t === "batch" && batch.seq).toBe(1);
    expect(batch && batch.t === "batch" && batch.state.prng).toEqual([0, 0, 0, 0]);
    expect(room.data.battle?.active).toBe(1);
  });

  it("illegal commands are rejected and change nothing", () => {
    const { room } = battle();
    const before = JSON.stringify(room.data.battle?.state);
    const out = room.message(0, {
      t: "command",
      command: { kind: "act", unit: "nobody", to: { x: 0, y: 0 }, action: { kind: "wait" } },
    });
    expect(msgs(out, "rejected")).toHaveLength(1);
    expect(JSON.stringify(room.data.battle?.state)).toBe(before);
  });

  it("time past 45 s drains the reserve; when both are gone the AI plays the activation", () => {
    const { env, room } = battle();
    const b = room.data.battle;
    if (!b) throw new Error("no battle");
    env.t += TURN_MS + 10_000;
    const cmd = legalCommands(b.state, P0_RULES)[0];
    if (!cmd) throw new Error("no command");
    room.message(0, { t: "command", command: cmd });
    expect(room.data.battle?.reserveMs).toEqual([RESERVE_MS - 10_000, RESERVE_MS]);
    // Seat B sleeps through its whole clock.
    env.t += TURN_MS + RESERVE_MS;
    expect(room.nextAlarm()).toBeLessThanOrEqual(env.t);
    const out = room.alarm();
    const batch = msgs(out, "batch")[0];
    expect(batch && batch.t === "batch" && batch.by).toBe("ai");
    expect(room.data.battle?.reserveMs[1]).toBe(0);
    expect(room.data.battle?.active).toBe(0);
  });

  it("a match left to the clock still finishes", () => {
    const { env, room } = battle();
    for (let i = 0; i < 2000 && room.data.phase === "battle"; i++) {
      env.t = room.nextAlarm();
      room.alarm();
    }
    expect(room.data.phase).toBe("over");
    expect(room.data.result?.reason).toBe("battle");
  });

  it("concede ends the match for the other seat", () => {
    const { room } = battle();
    room.message(1, { t: "concede" });
    expect(room.data.result).toEqual({ winner: 0, reason: "concede" });
  });
});

describe("reconnect (ADR 0037)", () => {
  it("returns the missed batches when they are still held, otherwise a snapshot", () => {
    const { room } = battle();
    for (let i = 0; i < 3; i++) {
      const b = room.data.battle;
      if (!b) throw new Error("no battle");
      const cmd = legalCommands(b.state, P0_RULES)[0];
      if (!cmd) throw new Error("no command");
      room.message(b.active, { t: "command", command: cmd });
    }
    const resumed = room.hello(hello("조조팬", 1), KEY_A).self.map((m) => m.t);
    expect(resumed).toEqual(["welcome", "batch", "batch"]);
    const fresh = room.hello(hello("조조팬", null), KEY_A).self.map((m) => m.t);
    expect(fresh).toEqual(["welcome", "snapshot"]);
    const current = room.hello(hello("조조팬", 3), KEY_A).self.map((m) => m.t);
    expect(current).toEqual(["welcome"]);
  });

  it("a room restored from storage (memory lost) sends a snapshot", () => {
    const { env, room } = battle();
    const b = room.data.battle;
    const cmd = b && legalCommands(b.state, P0_RULES)[0];
    if (!cmd) throw new Error("no command");
    room.message(0, { t: "command", command: cmd });
    const restored = new Room(structuredClone(room.data), env);
    expect(restored.hello(hello("조조팬", 0), KEY_A).self.map((m) => m.t)).toEqual([
      "welcome",
      "snapshot",
    ]);
  });

  it("presence changes are broadcast", () => {
    const { env, room } = setup();
    env.connected[1] = false;
    const out = room.presence();
    const view = out[0]?.msg;
    expect(view && view.t === "room" && view.room.players[1]?.connected).toBe(false);
    expect(ARENA_POOL.length).toBe(16);
  });
});

describe("hidden deployment", () => {
  it("each seat sees only its own arrangement until the battle starts", () => {
    const { room } = setup();
    draftAll(room);
    expect(room.view(0).deploy?.arrangement[0]).toHaveLength(6);
    expect(room.view(0).deploy?.arrangement[1]).toEqual([]);
    expect(room.view(1).deploy?.arrangement[0]).toEqual([]);
    const out = room.message(0, { t: "arrange", order: [...room.data.picks[0]].reverse() });
    const toB = out.find((o) => o.to === 1)?.msg;
    expect(toB && toB.t === "room" && toB.room.deploy?.arrangement[0]).toEqual([]);
  });
});
