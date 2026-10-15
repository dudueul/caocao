import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  legalCommands,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

const wait = (id: string, x: number, y: number): Command => ({
  kind: "act",
  unit: id,
  to: { x, y },
  action: { kind: "wait" },
});

function ok(state: BattleState, c: Command): BattleState {
  const r = apply(state, RULES, c);
  if (!r.ok) throw new Error(r.reason);
  return r.state;
}

describe("createBattle", () => {
  it("derives HP and MP from class, stats and level", () => {
    const s = createBattle(
      RULES,
      setup(
        ["...."],
        [
          unit("s", 0, "strategist", 0, 0, { command: 40, intellect: 90 }, { level: 10 }),
          unit("e", 1, "infantry", 3, 0),
        ],
      ),
    );
    const u = unitById(s, "s");
    expect(u.maxHp).toBe(110 + 40 + 10 * 4);
    expect(u.maxMp).toBe(20 + 45);
  });

  it("rejects two units on one tile", () => {
    expect(() =>
      createBattle(
        RULES,
        setup(["...."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 0, 0)]),
      ),
    ).toThrow();
  });
});

describe("turn policies (ADR 0003)", () => {
  const rows = ["......", "......"];
  const units = [
    unit("a1", 0, "infantry", 0, 0),
    unit("a2", 0, "infantry", 0, 1),
    unit("b1", 1, "infantry", 5, 0),
    unit("b2", 1, "infantry", 5, 1),
  ];

  it("alternating: one unit per activation, sides alternate, round starter alternates", () => {
    let s = createBattle(RULES, setup(rows, units, { policy: "alternating" }));
    expect(s.active).toBe(0);
    s = ok(s, wait("a1", 0, 0));
    expect(s.active).toBe(1);
    s = ok(s, wait("b1", 5, 0));
    expect(s.active).toBe(0);
    s = ok(s, wait("a2", 0, 1));
    s = ok(s, wait("b2", 5, 1));
    expect(s.round).toBe(2);
    expect(s.active).toBe(1);
  });

  it("side phases: a side moves all its units, endPhase passes the turn", () => {
    let s = createBattle(RULES, setup(rows, units, { policy: "sidePhases" }));
    s = ok(s, wait("a1", 0, 0));
    expect(s.active).toBe(0);
    s = ok(s, { kind: "endPhase" });
    expect(s.active).toBe(1);
    expect(legalCommands(s, RULES).at(-1)).toEqual({ kind: "endPhase" });
  });

  it("rejects commands for the wrong side, unreachable tiles and repeats", () => {
    const s = createBattle(RULES, setup(rows, units));
    expect(apply(s, RULES, wait("b1", 5, 0)).ok).toBe(false);
    expect(apply(s, RULES, wait("a1", 5, 1)).ok).toBe(false);
    const s2 = ok(s, wait("a1", 0, 0));
    const s3 = ok(s2, wait("b1", 5, 0));
    expect(apply(s3, RULES, wait("a1", 0, 0)).ok).toBe(false);
  });

  it("apply never mutates its input", () => {
    const s = createBattle(RULES, setup(rows, units));
    const before = hashState(s);
    apply(s, RULES, wait("a1", 1, 0));
    expect(hashState(s)).toBe(before);
  });
});

describe("round effects and battle end", () => {
  it("heals units on village or fort tiles at the start of a round", () => {
    let s = createBattle(
      RULES,
      setup(["v....", "....."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 4, 1)]),
    );
    s.units[0] = { ...(s.units[0] as BattleState["units"][number]), hp: 50 };
    s = ok(s, wait("a", 0, 0));
    s = ok(s, wait("b", 4, 1));
    expect(unitById(s, "a").hp).toBe(50 + Math.floor((unitById(s, "a").maxHp * 100) / 1000));
  });

  it("ends in a draw when the round limit passes", () => {
    let s = createBattle(
      RULES,
      setup(["....."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 4, 0)], {
        roundLimit: 1,
      }),
    );
    s = ok(s, wait("a", 0, 0));
    s = ok(s, wait("b", 4, 0));
    expect(s.winner).toBe(-1);
  });

  it("a confused unit can only wait, and recovers after waiting", () => {
    let s = createBattle(
      RULES,
      setup(["....."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 4, 0)]),
    );
    s.units[0] = {
      ...(s.units[0] as BattleState["units"][number]),
      conditions: [{ id: "confusion", turns: 1, fresh: false }],
    };
    expect(legalCommands(s, RULES)).toEqual([wait("a", 0, 0)]);
    s = ok(s, wait("a", 0, 0));
    expect(unitById(s, "a").conditions).toEqual([]);
  });
});
