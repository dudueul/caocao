import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  replay,
  runBattle,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

const wait = (id: string, x: number, y: number): Command => ({
  kind: "act",
  unit: id,
  to: { x, y },
  action: { kind: "wait" },
});

function ok(state: BattleState, c: Command) {
  const r = apply(state, RULES, c);
  if (!r.ok) throw new Error(r.reason);
  return r;
}

describe("goals (ADR 0047)", () => {
  it("a side wins by defeating the named unit, with others still standing", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("a", 0, "cavalry", 0, 0, { martial: 100, agility: 100 }, { level: 30 }),
          unit("boss", 1, "infantry", 2, 0, {}, { level: 1 }),
          unit("guard", 1, "infantry", 5, 0),
        ],
        { goals: [{ side: 0, when: { kind: "defeat", units: ["boss"] } }] },
      ),
    );
    unitById(s, "boss").hp = 1;
    const r = ok(s, {
      kind: "act",
      unit: "a",
      to: { x: 1, y: 0 },
      action: { kind: "attack", target: "boss" },
    });
    s = r.state;
    expect(s.winner).toBe(0);
    expect(r.events.at(-1)).toEqual({ kind: "battleEnd", winner: 0, goal: 0 });
    expect(unitById(s, "guard").hp).toBeGreaterThan(0);
  });

  it("losing the lord ends the battle for the other side", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("lord", 0, "infantry", 0, 0),
          unit("aide", 0, "infantry", 5, 0),
          unit("foe", 1, "cavalry", 2, 0, { martial: 100, agility: 100 }, { level: 30 }),
        ],
        {
          policy: "alternating",
          goals: [{ side: 1, when: { kind: "defeat", units: ["lord"] } }],
        },
      ),
    );
    unitById(s, "lord").hp = 1;
    s = ok(s, wait("lord", 0, 0)).state;
    s = ok(s, {
      kind: "act",
      unit: "foe",
      to: { x: 1, y: 0 },
      action: { kind: "attack", target: "lord" },
    }).state;
    expect(s.winner).toBe(1);
  });

  it("hold: the side wins when the round begins", () => {
    let s = createBattle(
      RULES,
      setup(["......"], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 5, 0)], {
        goals: [{ side: 0, when: { kind: "hold", round: 3 } }],
      }),
    );
    s = ok(s, wait("a", 0, 0)).state;
    s = ok(s, wait("b", 5, 0)).state;
    expect(s.round).toBe(2);
    expect(s.winner).toBeNull();
    s = ok(s, wait("b", 5, 0)).state; // round 2 starts with side 1 (alternating)
    const r = ok(s, wait("a", 0, 0));
    expect(r.state.round).toBe(3);
    expect(r.state.winner).toBe(0);
    expect(r.events.at(-1)).toEqual({ kind: "battleEnd", winner: 0, goal: 0 });
  });

  it("a hold goal beats the round-limit draw", () => {
    let s = createBattle(
      RULES,
      setup(["......"], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 5, 0)], {
        roundLimit: 2,
        goals: [{ side: 0, when: { kind: "hold", round: 3 } }],
      }),
    );
    s = ok(s, wait("a", 0, 0)).state;
    s = ok(s, wait("b", 5, 0)).state;
    s = ok(s, wait("b", 5, 0)).state;
    s = ok(s, wait("a", 0, 0)).state;
    expect(s.winner).toBe(0);
  });

  it("reach: a unit of the side standing on a goal cell wins", () => {
    const s = createBattle(
      RULES,
      setup(["......"], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 5, 0)], {
        goals: [{ side: 0, when: { kind: "reach", side: 0, cells: [{ x: 3, y: 0 }] } }],
      }),
    );
    const r = ok(s, wait("a", 3, 0));
    expect(r.state.winner).toBe(0);
  });

  it("goals are checked in order", () => {
    const s = createBattle(
      RULES,
      setup(["......"], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 5, 0)], {
        goals: [
          { side: 1, when: { kind: "reach", side: 0, cells: [{ x: 3, y: 0 }] } },
          { side: 0, when: { kind: "reach", side: 0, cells: [{ x: 3, y: 0 }] } },
        ],
      }),
    );
    expect(ok(s, wait("a", 3, 0)).state.winner).toBe(1);
  });

  it("rejects a goal naming an unknown unit", () => {
    expect(() =>
      createBattle(
        RULES,
        setup(["...."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 3, 0)], {
          goals: [{ side: 0, when: { kind: "defeat", units: ["nobody"] } }],
        }),
      ),
    ).toThrow();
  });
});

describe("arrivals (ADR 0047)", () => {
  const units = [
    unit("a", 0, "infantry", 0, 0),
    unit("b", 1, "infantry", 5, 0),
    unit("c", 1, "infantry", 5, 0, {}, { arrives: 2 }),
  ];

  it("reserve units join at the start of their round, beside an occupied tile", () => {
    let s = createBattle(RULES, setup(["......", "......"], units));
    expect(s.units.map((u) => u.id)).toEqual(["a", "b"]);
    expect(s.reserve.map((a) => a.unit.id)).toEqual(["c"]);
    s = ok(s, wait("a", 0, 0)).state;
    const r = ok(s, wait("b", 5, 0));
    s = r.state;
    expect(s.round).toBe(2);
    expect(s.reserve).toEqual([]);
    const arrived = r.events.find((e) => e.kind === "arrived");
    expect(arrived).toEqual({ kind: "arrived", unit: "c", at: { x: 5, y: 1 } });
    const c = unitById(s, "c");
    expect([c.x, c.y, c.acted, c.hp === c.maxHp]).toEqual([5, 1, false, true]);
    expect(s.units.map((u) => u.id)).toEqual(["a", "b", "c"]);
  });

  it("a routed side's reserve does not save it", () => {
    const s = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("a", 0, "cavalry", 0, 0, { martial: 100, agility: 100 }, { level: 30 }),
          unit("b", 1, "infantry", 2, 0),
          unit("c", 1, "infantry", 5, 0, {}, { arrives: 3 }),
        ],
      ),
    );
    unitById(s, "b").hp = 1;
    const r = ok(s, {
      kind: "act",
      unit: "a",
      to: { x: 1, y: 0 },
      action: { kind: "attack", target: "b" },
    });
    expect(r.state.winner).toBe(0);
  });

  it("is deterministic: replays reach the same hash", () => {
    const start = createBattle(
      RULES,
      setup(
        ["......", "......", "......"],
        [
          unit("a", 0, "infantry", 0, 0),
          unit("a2", 0, "archer", 0, 2),
          unit("b", 1, "infantry", 5, 0),
          unit("c", 1, "cavalry", 5, 2, {}, { arrives: 2 }),
        ],
        { seed: "arrive", goals: [{ side: 1, when: { kind: "defeat", units: ["a"] } }] },
      ),
    );
    const run = runBattle(start, RULES);
    expect(run.state.winner).not.toBeNull();
    expect(hashState(replay(start, RULES, run.commands))).toBe(hashState(run.state));
  });
});
