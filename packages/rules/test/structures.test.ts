import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  legalCommands,
  previewAttack,
  type Ruleset,
  replay,
  runBattle,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

/** The fixture rules, with stone-throwers smashing gates. */
const R: Ruleset = {
  ...RULES,
  relations: {
    ...RULES.relations,
    catapult: { ...(RULES.relations.catapult ?? {}), structure: 2500 },
  },
};

function ok(state: BattleState, c: Command) {
  const r = apply(state, R, c);
  if (!r.ok) throw new Error(r.reason);
  return r;
}

/** A wall with a gate at (3,1); we start west of it, the enemy east. */
const ROWS = ["...#...", ".......", "...#..."];

describe("structures: breakable gates (ADR 0069)", () => {
  it("a gate is marked, blocks its tile and never gets a turn", () => {
    const s = createBattle(
      R,
      setup(ROWS, [
        unit("me", 0, "infantry", 0, 1),
        unit("gate", 1, "gate", 3, 1),
        unit("foe", 1, "infantry", 6, 1),
      ]),
    );
    expect(s.version).toBe(6);
    expect(unitById(s, "gate").structure).toBe(true);
    expect(unitById(s, "me").structure).toBeUndefined();
    const moves = legalCommands(s, R).filter((c) => c.kind === "act" && c.unit === "me");
    expect(moves.some((c) => c.kind === "act" && c.to.x > 3)).toBe(false);
    // No zone of control: standing next to the gate does not stop a unit.
    expect(moves.some((c) => c.kind === "act" && c.to.x === 2 && c.to.y === 0)).toBe(true);
  });

  it("the enemy's turn skips its gate, and a side left with only gates is routed", () => {
    let s = createBattle(
      R,
      setup(ROWS, [
        unit("me", 0, "cavalry", 1, 1, { martial: 100, agility: 100 }, { level: 30 }),
        unit("gate", 1, "gate", 3, 1),
        unit("foe", 1, "infantry", 1, 0),
      ]),
    );
    expect(legalCommands(s, R).every((c) => c.kind !== "act" || c.unit !== "gate")).toBe(true);
    unitById(s, "foe").hp = 1;
    s = ok(s, {
      kind: "act",
      unit: "me",
      to: { x: 1, y: 1 },
      action: { kind: "attack", target: "foe" },
    }).state;
    expect(s.winner).toBe(0);
  });

  it("a gate takes no conditions and strikes no counter; stone-throwers hit it hardest", () => {
    const s = createBattle(
      R,
      setup(ROWS, [
        unit("me", 0, "infantry", 2, 1),
        unit("cat", 0, "catapult", 0, 1),
        unit("gate", 1, "gate", 3, 1),
        unit("foe", 1, "infantry", 6, 1),
      ]),
    );
    const gate = unitById(s, "gate");
    const byFoot = previewAttack(R, s, unitById(s, "me"), gate);
    expect(byFoot.counter).toBeNull();
    const cat = { ...unitById(s, "cat"), x: 0, y: 1 };
    const byStone = previewAttack(R, s, cat, gate);
    expect(byStone.damage).toBeGreaterThan(byFoot.damage);
  });

  it("breaking the gate opens the way", () => {
    let s = createBattle(
      R,
      setup(ROWS, [
        unit("me", 0, "cavalry", 2, 1, { martial: 100, agility: 100 }, { level: 30 }),
        unit("gate", 1, "gate", 3, 1),
        unit("foe", 1, "infantry", 6, 1),
      ]),
    );
    unitById(s, "gate").hp = 1;
    s = ok(s, {
      kind: "act",
      unit: "me",
      to: { x: 2, y: 1 },
      action: { kind: "attack", target: "gate" },
    }).state;
    expect(unitById(s, "gate").hp).toBe(0);
    expect(s.winner).toBeNull();
    // Next round, the way through the gate's tile is open.
    while (s.active !== 0 && s.winner === null) {
      const c = legalCommands(s, R).find((x) => x.kind === "act" && x.action.kind === "wait");
      s = ok(s, c as Command).state;
    }
    expect(legalCommands(s, R).some((c) => c.kind === "act" && c.unit === "me" && c.to.x > 3)).toBe(
      true,
    );
  });

  it("the AI batters its way through, and replays reach the same hash", () => {
    const start = createBattle(
      R,
      setup(
        ["...#....", "........", "...#...."],
        [
          unit("me", 0, "infantry", 0, 1, {}, { level: 10 }),
          unit("cat", 0, "catapult", 0, 0, {}, { level: 10 }),
          unit("gate", 1, "gate", 3, 1),
          unit("foe", 1, "infantry", 6, 1),
        ],
        { seed: "gate", roundLimit: 40 },
      ),
    );
    const run = runBattle(start, R);
    expect(unitById(run.state, "gate").hp).toBe(0);
    expect(hashState(replay(start, R, run.commands))).toBe(hashState(run.state));
  });
});
