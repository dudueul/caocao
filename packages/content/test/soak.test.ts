import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  chooseCommand,
  classOf,
  createBattle,
  hashState,
  isAlive,
  replay,
  runBattle,
  terrainAt,
} from "@tk/rules";
import { P0_RULES, scenarioOrders, scenarioSetup, WUCHAO_RAID } from "../src/index.ts";

const ORDERS = scenarioOrders(WUCHAO_RAID);

function invariants(s: BattleState): void {
  const seen = new Set<string>();
  for (const u of s.units) {
    expect(u.hp).toBeGreaterThanOrEqual(0);
    expect(u.hp).toBeLessThanOrEqual(u.maxHp);
    expect(u.mp).toBeGreaterThanOrEqual(0);
    if (!isAlive(u)) continue;
    const key = `${u.x},${u.y}`;
    expect(seen.has(key)).toBe(false);
    seen.add(key);
    const family = classOf(P0_RULES, u).family;
    expect(P0_RULES.affinity[family]?.[terrainAt(s, u.x, u.y)].move).not.toBeNull();
  }
}

describe("Wuchao sandbox, AI vs AI", () => {
  for (const policy of ["sidePhases", "alternating"] as const) {
    it(`${policy}: 6 battles end with every invariant intact`, () => {
      for (let i = 0; i < 6; i++) {
        let s = createBattle(P0_RULES, scenarioSetup(WUCHAO_RAID, { seed: `soak-${i}`, policy }));
        let steps = 0;
        while (s.winner === null && steps < 4000) {
          const c = chooseCommand(s, P0_RULES, ORDERS);
          if (!c) break;
          const r = apply(s, P0_RULES, c);
          expect(r.ok).toBe(true);
          if (!r.ok) break;
          s = r.state;
          invariants(s);
          steps++;
        }
        expect(s.winner).not.toBeNull();
        hashState(s);
      }
    });
  }

  it("defenders with hold orders stay in the camp until the raiders come near", () => {
    let s = createBattle(P0_RULES, scenarioSetup(WUCHAO_RAID, { seed: "hold" }));
    const startX = new Map(s.units.map((u) => [u.id, u.x]));
    // Round 1: the raiders are far away, so no defender moves.
    while (s.round === 1 && s.winner === null) {
      const c = chooseCommand(s, P0_RULES, ORDERS);
      if (!c) break;
      const r = apply(s, P0_RULES, c);
      if (!r.ok) throw new Error(r.reason);
      s = r.state;
    }
    for (const u of s.units) if (u.side === 1) expect(u.x).toBe(startX.get(u.id) as number);
  });

  it("replaying the command log reproduces the final state", () => {
    const start = createBattle(P0_RULES, scenarioSetup(WUCHAO_RAID, { seed: "replay" }));
    const run = runBattle(start, P0_RULES);
    expect(hashState(replay(start, P0_RULES, run.commands))).toBe(hashState(run.state));
  });
});
