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
} from "../src/index.ts";
import { RULES, skirmish } from "./fixtures.ts";

function invariants(s: BattleState): void {
  const seen = new Set<string>();
  for (const u of s.units) {
    expect(u.hp).toBeGreaterThanOrEqual(0);
    expect(u.hp).toBeLessThanOrEqual(u.maxHp);
    expect(u.mp).toBeGreaterThanOrEqual(0);
    expect(u.mp).toBeLessThanOrEqual(u.maxMp);
    if (!isAlive(u)) {
      expect(u.fate).not.toBeNull();
      continue;
    }
    const key = `${u.x},${u.y}`;
    expect(seen.has(key)).toBe(false);
    seen.add(key);
    const family = classOf(RULES, u).family;
    expect(RULES.affinity[family]?.[terrainAt(s, u.x, u.y)].move).not.toBeNull();
  }
  hashState(s); // throws on any non-integer value in the state
}

describe("AI vs AI soak", () => {
  for (const policy of ["alternating", "sidePhases"] as const) {
    it(`${policy}: 12 battles finish with every invariant intact`, () => {
      for (let i = 0; i < 12; i++) {
        let s = createBattle(RULES, skirmish(`soak-${policy}-${i}`, policy));
        let steps = 0;
        while (s.winner === null && steps < 3000) {
          const c = chooseCommand(s, RULES);
          if (!c) break;
          const r = apply(s, RULES, c);
          expect(r.ok).toBe(true);
          if (!r.ok) break;
          s = r.state;
          invariants(s);
          steps++;
        }
        expect(s.winner).not.toBeNull();
      }
    });
  }
});

describe("determinism (ADR 0004)", () => {
  it("same seed and commands give the same final hash; replay reproduces it", () => {
    const start = createBattle(RULES, skirmish("golden-1", "alternating"));
    const a = runBattle(start, RULES);
    const b = runBattle(createBattle(RULES, skirmish("golden-1", "alternating")), RULES);
    expect(hashState(a.state)).toBe(hashState(b.state));
    expect(hashState(replay(start, RULES, a.commands))).toBe(hashState(a.state));
  });

  it("different seeds diverge", () => {
    const a = runBattle(createBattle(RULES, skirmish("seed-a", "alternating")), RULES);
    const b = runBattle(createBattle(RULES, skirmish("seed-b", "alternating")), RULES);
    expect(hashState(a.state)).not.toBe(hashState(b.state));
  });
});
