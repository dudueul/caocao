import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  chooseCommand,
  classOf,
  createBattle,
  hashState,
  Prng,
  seedFromString,
  terrainAt,
} from "@tk/rules";
import {
  ARENA_FIELD,
  ARENA_POOL,
  ARMY_SIZE,
  arenaSetup,
  DEPLOY_SLOTS,
  DRAFT_ORDER,
  OFFICERS,
  P0_RULES,
  parseMap,
} from "../src/index.ts";

/** A random but legal draft: seats take turns in DRAFT_ORDER. */
function randomDraft(seed: string): [string[], string[]] {
  const prng = new Prng(seedFromString(seed));
  const left = ARENA_POOL.map((e) => e.id);
  const armies: [string[], string[]] = [[], []];
  for (const seat of DRAFT_ORDER) {
    const [pick] = left.splice(prng.int(left.length), 1);
    armies[seat].push(pick as string);
  }
  return armies;
}

function play(start: BattleState): BattleState {
  let s = start;
  for (let i = 0; i < 3000 && s.winner === null; i++) {
    const c = chooseCommand(s, P0_RULES);
    if (!c) break;
    const r = apply(s, P0_RULES, c);
    if (!r.ok) throw new Error(r.reason);
    s = r.state;
  }
  return s;
}

describe("Arena content (ADR 0039, ADR 0041)", () => {
  it("the pool has 16 distinct officers with valid classes, enough for a 12-pick draft", () => {
    const ids = ARENA_POOL.map((e) => e.id);
    expect(new Set(ids).size).toBe(16);
    for (const e of ARENA_POOL) {
      expect(OFFICERS[e.id]).toBeDefined();
      expect(P0_RULES.classes[e.classId]).toBeDefined();
    }
    expect(DRAFT_ORDER).toHaveLength(2 * ARMY_SIZE);
    expect(DRAFT_ORDER.filter((s) => s === 0)).toHaveLength(ARMY_SIZE);
  });

  it("the field is mirrored left to right, and so are the deploy tiles", () => {
    for (const row of ARENA_FIELD.rows) expect(row).toBe([...row].reverse().join(""));
    const map = parseMap(ARENA_FIELD);
    DEPLOY_SLOTS[0].forEach((c, i) => {
      expect(DEPLOY_SLOTS[1][i]).toEqual({ x: map.width - 1 - c.x, y: c.y });
    });
  });

  it("every class can stand on every deploy tile", () => {
    const s = createBattle(P0_RULES, arenaSetup({ seed: "slots", armies: randomDraft("slots") }));
    for (const u of s.units) {
      const family = classOf(P0_RULES, u).family;
      expect(P0_RULES.affinity[family]?.[terrainAt(s, u.x, u.y)].move).not.toBeNull();
    }
  });

  it("drafted battles finish, and neither seat is favoured by much (AI vs AI)", () => {
    const wins = [0, 0, 0];
    const N = 24;
    for (let i = 0; i < N; i++) {
      const s = play(
        createBattle(P0_RULES, arenaSetup({ seed: `arena-${i}`, armies: randomDraft(`d-${i}`) })),
      );
      expect(s.winner).not.toBeNull();
      hashState(s);
      wins[(s.winner as number) + 1] = (wins[(s.winner as number) + 1] ?? 0) + 1;
    }
    // [draws, seat A, seat B]: each seat should win at least a quarter of the battles.
    expect(wins[1]).toBeGreaterThanOrEqual(N / 4);
    expect(wins[2]).toBeGreaterThanOrEqual(N / 4);
  });
});
