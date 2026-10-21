import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  createBattle,
  hashState,
  legalCommands,
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

/** Ours (0), the enemy (1) and an ally (2) on our team; phases go 0 → 2 → 1. */
const ALLIED = { teams: [0, 1, 0], order: [0, 2, 1], policy: "sidePhases" as const };

describe("allies (ADR 0066)", () => {
  it("phases go ours → allies → enemy, and the state says who is on which team", () => {
    let s = createBattle(
      RULES,
      setup(
        ["........"],
        [
          unit("me", 0, "infantry", 0, 0),
          unit("ally", 2, "infantry", 2, 0),
          unit("foe", 1, "infantry", 7, 0),
        ],
        ALLIED,
      ),
    );
    expect([s.version, s.teams, s.order, s.active]).toEqual([6, [0, 1, 0], [0, 2, 1], 0]);
    let r = ok(s, wait("me", 0, 0));
    expect(r.events.at(-1)).toEqual({ kind: "turn", side: 2, round: 1 });
    s = ok(r.state, wait("ally", 2, 0)).state;
    expect(s.active).toBe(1);
    r = ok(s, wait("foe", 7, 0));
    expect(r.state.round).toBe(2);
    expect(r.state.active).toBe(0);
  });

  it("allies never attack each other, and move through each other but not through the enemy", () => {
    const s = createBattle(
      RULES,
      setup(
        [".....", "....."],
        [
          unit("me", 0, "infantry", 0, 0),
          unit("ally", 2, "infantry", 1, 0),
          unit("foe", 1, "infantry", 4, 1),
        ],
        ALLIED,
      ),
    );
    const cmds = legalCommands(s, RULES);
    expect(
      cmds.some(
        (c) => c.kind === "act" && c.action.kind === "attack" && c.action.target === "ally",
      ),
    ).toBe(false);
    expect(
      apply(s, RULES, {
        kind: "act",
        unit: "me",
        to: { x: 0, y: 1 },
        action: { kind: "attack", target: "ally" },
      }).ok,
    ).toBe(false);
    // Through the ally to (2,0): allowed; the ally exerts no zone of control.
    expect(cmds.some((c) => c.kind === "act" && c.to.x === 2 && c.to.y === 0)).toBe(true);
  });

  it("an ally's healing reaches our units", () => {
    const s = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("me", 0, "infantry", 0, 0),
          unit("medic", 2, "strategist", 1, 0),
          unit("foe", 1, "infantry", 5, 0),
        ],
        ALLIED,
      ),
    );
    unitById(s, "me").hp = 10;
    let t = ok(s, wait("me", 0, 0)).state;
    const r = ok(t, {
      kind: "act",
      unit: "medic",
      to: { x: 1, y: 0 },
      action: { kind: "stratagem", id: "heal", at: { x: 0, y: 0 } },
    });
    t = r.state;
    expect(unitById(t, "me").hp).toBeGreaterThan(10);
  });

  it("we win when the enemy is routed; we lose when we are routed, even with allies standing", () => {
    const win = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("me", 0, "cavalry", 0, 0, { martial: 100, agility: 100 }, { level: 30 }),
          unit("ally", 2, "infantry", 5, 0),
          unit("foe", 1, "infantry", 2, 0),
        ],
        ALLIED,
      ),
    );
    unitById(win, "foe").hp = 1;
    const w = ok(win, {
      kind: "act",
      unit: "me",
      to: { x: 1, y: 0 },
      action: { kind: "attack", target: "foe" },
    });
    expect(w.state.winner).toBe(0);

    const lose = createBattle(
      RULES,
      setup(
        ["......"],
        [
          unit("me", 0, "infantry", 0, 0),
          unit("ally", 2, "infantry", 5, 0),
          unit("foe", 1, "cavalry", 3, 0, { martial: 100, agility: 100 }, { level: 30 }),
        ],
        { ...ALLIED, order: [1, 0, 2] },
      ),
    );
    unitById(lose, "me").hp = 1;
    const l = ok(lose, {
      kind: "act",
      unit: "foe",
      to: { x: 1, y: 0 },
      action: { kind: "attack", target: "me" },
    });
    expect(l.state.winner).toBe(1);
  });

  it("rejects a turn order that misses a side", () => {
    expect(() =>
      createBattle(
        RULES,
        setup(
          ["...."],
          [
            unit("a", 0, "infantry", 0, 0),
            unit("b", 1, "infantry", 3, 0),
            unit("c", 2, "infantry", 1, 0),
          ],
          {
            teams: [0, 1, 0],
            order: [0, 1],
          },
        ),
      ),
    ).toThrow("turn order");
  });

  it("the AI plays allies against the enemy, and replays reach the same hash", () => {
    const start = createBattle(
      RULES,
      setup(
        ["........", "........", "........"],
        [
          unit("me", 0, "infantry", 0, 1),
          unit("ally", 2, "cavalry", 1, 0),
          unit("ally2", 2, "strategist", 1, 2),
          unit("foe", 1, "infantry", 7, 1),
          unit("foe2", 1, "archer", 7, 0),
        ],
        { ...ALLIED, seed: "allies" },
      ),
    );
    const run = runBattle(start, RULES);
    expect(run.state.winner).not.toBeNull();
    for (const c of run.commands)
      if (c.kind === "act" && c.action.kind === "attack") {
        const a = unitById(start, c.unit).side;
        const t = unitById(start, c.action.target).side;
        expect(a === 1 || t === 1).toBe(true);
      }
    expect(hashState(replay(start, RULES, run.commands))).toBe(hashState(run.state));
  });
});
