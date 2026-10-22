import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  replay,
  runBattle,
  type Shift,
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

/** The low row floods once side 0 stands on the sluice at (5,0). */
const flood: Shift = {
  id: "flood",
  when: { kind: "reach", side: 0, cells: [{ x: 5, y: 0 }] },
  tiles: [0, 1, 2, 3].map((x) => ({ x, y: 2, terrain: "water" as const })),
  damage: 200,
};

describe("map shifts (ADR 0062)", () => {
  it("a unit reaching the trigger floods the tiles and sweeps units off them", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......", "......", "......"],
        [
          unit("runner", 0, "cavalry", 4, 0),
          unit("foe", 1, "infantry", 1, 2),
          unit("other", 1, "infantry", 0, 0),
        ],
        { shifts: [flood] },
      ),
    );
    expect(s.version).toBe(7);
    expect(s.shifts.map((x) => x.id)).toEqual(["flood"]);
    const before = unitById(s, "foe").hp;
    const r = ok(s, wait("runner", 5, 0));
    s = r.state;
    expect(s.shifts).toEqual([]);
    expect(s.map.tiles.slice(12, 16)).toEqual(["water", "water", "water", "water"]);
    const shifted = r.events.find((e) => e.kind === "shifted");
    expect(shifted).toMatchObject({ kind: "shifted", shift: "flood", by: "runner" });
    const swept = r.events.find((e) => e.kind === "swept");
    const foe = unitById(s, "foe");
    expect(swept).toEqual({
      kind: "swept",
      unit: "foe",
      from: { x: 1, y: 2 },
      to: { x: foe.x, y: foe.y },
      damage: before - foe.hp,
      hpAfter: foe.hp,
    });
    expect(foe.y).toBeLessThan(2);
    expect(foe.hp).toBe(before - Math.floor((foe.maxHp * 200) / 1000));
  });

  it("a timed shift fires when its round begins, and a sweep never takes the last HP", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......", "......"],
        [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 3, 1)],
        {
          shifts: [
            {
              id: "gate",
              when: { kind: "round", round: 2 },
              tiles: [{ x: 3, y: 1, terrain: "wall" }],
              damage: 1000,
            },
          ],
        },
      ),
    );
    s = ok(s, wait("a", 0, 0)).state;
    const r = ok(s, wait("b", 3, 1));
    s = r.state;
    expect(s.round).toBe(2);
    expect(s.map.tiles[6 + 3]).toBe("wall");
    const b = unitById(s, "b");
    expect(b.hp).toBe(1);
    expect(b.x !== 3 || b.y !== 1).toBe(true);
    expect(r.events.findIndex((e) => e.kind === "shifted")).toBeLessThan(
      r.events.findIndex((e) => e.kind === "turn"),
    );
  });

  it("water puts out fire on the flooded tiles", () => {
    const s = createBattle(
      RULES,
      setup(
        ["......", "......", "......"],
        [unit("runner", 0, "cavalry", 4, 0), unit("foe", 1, "infantry", 0, 0)],
        {
          shifts: [flood],
        },
      ),
    );
    s.burning = [
      { x: 2, y: 2, rounds: 2 },
      { x: 4, y: 2, rounds: 2 },
    ];
    const r = ok(s, wait("runner", 5, 0));
    expect(r.state.burning).toEqual([{ x: 4, y: 2, rounds: 2 }]);
  });

  it("only the trigger's side sets it off", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......", "......", "......"],
        [unit("a", 0, "infantry", 0, 0), unit("foe", 1, "cavalry", 4, 0)],
        { shifts: [flood] },
      ),
    );
    s = ok(s, wait("a", 0, 0)).state;
    s = ok(s, wait("foe", 5, 0)).state;
    expect(s.shifts.map((x) => x.id)).toEqual(["flood"]);
  });

  it("rejects malformed shifts", () => {
    const base = (shifts: Shift[]) =>
      setup(["...", "..."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 2, 1)], {
        shifts,
      });
    const tile = { x: 1, y: 1, terrain: "water" as const };
    expect(() =>
      createBattle(
        RULES,
        base([
          { id: "x", when: { kind: "round", round: 2 }, tiles: [{ ...tile, x: 9 }], damage: 0 },
        ]),
      ),
    ).toThrow("off the map");
    expect(() =>
      createBattle(
        RULES,
        base([{ id: "x", when: { kind: "round", round: 1 }, tiles: [tile], damage: 0 }]),
      ),
    ).toThrow("round 2");
    expect(() =>
      createBattle(
        RULES,
        base([
          { id: "x", when: { kind: "round", round: 2 }, tiles: [tile], damage: 0 },
          { id: "x", when: { kind: "round", round: 3 }, tiles: [tile], damage: 0 },
        ]),
      ),
    ).toThrow("two shifts");
    expect(() =>
      createBattle(
        RULES,
        base([{ id: "x", when: { kind: "round", round: 2 }, tiles: [tile], damage: 2000 }]),
      ),
    ).toThrow("0–1000");
  });

  it("the AI sends one runner to the trigger, and replays reach the same hash", () => {
    const start = createBattle(
      RULES,
      setup(
        ["......#.", "......#.", "........"],
        [
          unit("a", 0, "cavalry", 0, 0),
          unit("a2", 0, "infantry", 0, 1),
          unit("b", 1, "infantry", 7, 2, {}, { level: 5 }),
          unit("b2", 1, "infantry", 7, 0),
        ],
        { seed: "shift", shifts: [flood] },
      ),
    );
    const run = runBattle(start, RULES);
    expect(run.state.shifts).toEqual([]);
    expect(hashState(replay(start, RULES, run.commands))).toBe(hashState(run.state));
  });
});

describe("reach for named units, and fire at the start (ADR 0062)", () => {
  it("a reach goal that names units ignores the others", () => {
    let s = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("lord", 0, "infantry", 0, 0),
          unit("aide", 0, "cavalry", 4, 0),
          unit("foe", 1, "infantry", 3, 0),
        ],
        {
          goals: [
            { side: 0, when: { kind: "reach", side: 0, cells: [{ x: 5, y: 0 }], units: ["lord"] } },
          ],
        },
      ),
    );
    s = ok(s, wait("aide", 5, 0)).state;
    expect(s.winner).toBeNull();
  });

  it("the AI runs the named unit to the goal", () => {
    const start = createBattle(
      RULES,
      setup(
        ["........", "........"],
        [
          unit("lord", 0, "cavalry", 0, 0),
          unit("aide", 0, "infantry", 5, 1),
          unit("foe", 1, "infantry", 7, 1, {}, { level: 3 }),
        ],
        {
          seed: "escape",
          goals: [
            { side: 0, when: { kind: "reach", side: 0, cells: [{ x: 7, y: 0 }], units: ["lord"] } },
          ],
        },
      ),
    );
    const run = runBattle(start, RULES);
    expect(run.state.winner).toBe(0);
  });

  it("tiles can be burning when the battle begins", () => {
    const s = createBattle(
      RULES,
      setup(["....", "...."], [unit("a", 0, "infantry", 0, 0), unit("b", 1, "infantry", 3, 1)], {
        burning: [
          { x: 2, y: 1, rounds: 3 },
          { x: 1, y: 0, rounds: 2 },
        ],
      }),
    );
    expect(s.burning).toEqual([
      { x: 1, y: 0, rounds: 2 },
      { x: 2, y: 1, rounds: 3 },
    ]);
  });
});
