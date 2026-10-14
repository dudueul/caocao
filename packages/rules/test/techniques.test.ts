import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleState,
  type Command,
  castStratagem,
  chooseCommand,
  createBattle,
  dangerTiles,
  type Gambit,
  Prng,
  previewAttack,
  type Ruleset,
  seedFromString,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

/** Every strike hits, none is critical, no fortune twists: outcomes are exact. */
const SURE: Ruleset = {
  ...RULES,
  tuning: { ...RULES.tuning, hitMin: 1000, hitMax: 1000, critMin: 0, critMax: 0, twist: 0 },
};
const GALE = RULES.stratagems.gale as NonNullable<(typeof RULES.stratagems)[string]>;

function act(s: BattleState, c: Command, rules = SURE): BattleState {
  const r = apply(s, rules, c);
  if (!r.ok) throw new Error(r.reason);
  return r.state;
}

describe("break on advantage (ADR 0030)", () => {
  const s = createBattle(
    SURE,
    setup(
      ["....."],
      [
        unit("inf", 0, "infantry", 0, 0),
        unit("cav", 1, "cavalry", 1, 0),
        unit("x", 1, "archer", 4, 0),
      ],
    ),
  );

  it("a landed advantage strike breaks the target and cancels its counter", () => {
    expect(previewAttack(SURE, s, unitById(s, "inf"), unitById(s, "cav")).breaks).toBe(true);
    const r = apply(s, SURE, {
      kind: "act",
      unit: "inf",
      to: { x: 0, y: 0 },
      action: { kind: "attack", target: "cav" },
    });
    expect(r.ok).toBe(true);
    if (!r.ok) return;
    const strikes = r.events.filter((e) => e.kind === "strike");
    expect(strikes.map((e) => (e.kind === "strike" ? e.role : ""))).not.toContain("counter");
    expect(strikes[0]?.kind === "strike" && strikes[0].broke).toBe(true);
    expect(unitById(r.state, "cav").broken).toBe(true);
  });

  it("the break ends when the broken unit acts", () => {
    let t = act(s, {
      kind: "act",
      unit: "inf",
      to: { x: 0, y: 0 },
      action: { kind: "attack", target: "cav" },
    });
    t = act(t, { kind: "act", unit: "cav", to: { x: 1, y: 0 }, action: { kind: "wait" } });
    expect(unitById(t, "cav").broken).toBe(false);
  });
});

describe("forced movement (ADR 0030)", () => {
  function gale(row: string, others: Parameters<typeof setup>[1] = []) {
    const s = createBattle(
      SURE,
      setup([row], [unit("s", 0, "strategist", 0, 0), unit("t", 1, "infantry", 1, 0), ...others]),
    );
    castStratagem(
      SURE,
      s,
      new Prng(seedFromString("g")),
      unitById(s, "s"),
      GALE,
      { x: 1, y: 0 },
      [],
    );
    return s;
  }

  it("pushes the target away from the caster", () => {
    const s = gale("......");
    expect(unitById(s, "t").x).toBe(3);
  });

  it("a wall (or impassable tile) stops the push and deals collision damage", () => {
    const plain = gale("......");
    const walled = gale("..#...");
    expect(unitById(walled, "t").x).toBe(1);
    expect(unitById(plain, "t").hp - unitById(walled, "t").hp).toBe(SURE.tuning.pushWallDamage);
  });

  it("pushing into another unit hurts both", () => {
    const s = gale("......", [unit("u", 1, "infantry", 2, 0)]);
    const u = unitById(s, "u");
    expect(unitById(s, "t").x).toBe(1);
    expect(u.maxHp - u.hp).toBe(SURE.tuning.pushUnitDamage);
  });
});

describe("fire hazards (ADR 0030)", () => {
  it("fire on forest burns it and adjacent grass, hurts occupants at round start, then dies out", () => {
    let s = createBattle(
      SURE,
      setup(
        ["..g..", ".gfg.", "..g.."],
        [unit("s", 0, "strategist", 0, 1), unit("t", 1, "infantry", 2, 1)],
      ),
    );
    const r = apply(s, SURE, {
      kind: "act",
      unit: "s",
      to: { x: 0, y: 1 },
      action: { kind: "stratagem", id: "fire", at: { x: 2, y: 1 } },
    });
    expect(r.ok).toBe(true);
    if (!r.ok) return;
    s = r.state;
    expect(s.burning.map((b) => `${b.x},${b.y}`)).toEqual(["2,0", "1,1", "2,1", "3,1", "2,2"]);
    const before = unitById(s, "t").hp;
    s = act(s, { kind: "act", unit: "t", to: { x: 2, y: 1 }, action: { kind: "wait" } });
    expect(before - unitById(s, "t").hp).toBe(SURE.tuning.burnDamage);
    expect(s.burning).toEqual([]);
  });
});

describe("danger zones (ADR 0030)", () => {
  it("lists every tile enemies of a side could attack next turn", () => {
    const s = createBattle(
      RULES,
      setup(
        ["#######", "#.....#", "#######"],
        [unit("me", 0, "infantry", 1, 1), unit("e", 1, "infantry", 5, 1)],
      ),
    );
    // The enemy (move 4) reaches columns 2..5 (ZOC stops it next to "me"),
    // so it threatens columns 1..5 of the corridor.
    const zone = dangerTiles(s, RULES, 0)
      .filter((c) => c.y === 1)
      .map((c) => c.x);
    expect(zone).toEqual([1, 2, 3, 4, 5]);
  });
});

describe("gambits (ADR 0030)", () => {
  it("retreat to a healing tile when HP is low", () => {
    const s = createBattle(
      SURE,
      setup(["v....", "....."], [unit("me", 0, "infantry", 1, 0), unit("e", 1, "infantry", 2, 0)]),
    );
    s.units = s.units.map((u) => (u.id === "me" ? { ...u, hp: 20 } : u));
    const g: Gambit[] = [
      { when: { kind: "selfHpBelow", permille: 400 }, behavior: { kind: "retreatToHeal" } },
    ];
    const c = chooseCommand(s, SURE, { me: g });
    expect(c).toEqual({ kind: "act", unit: "me", to: { x: 0, y: 0 }, action: { kind: "wait" } });
  });

  it("attack the weakest enemy in reach", () => {
    const s = createBattle(
      SURE,
      setup(
        ["...", "...", "..."],
        [
          unit("me", 0, "infantry", 1, 1),
          unit("strong", 1, "infantry", 0, 1),
          unit("weak", 1, "infantry", 2, 1),
        ],
      ),
    );
    s.units = s.units.map((u) => (u.id === "weak" ? { ...u, hp: 90 } : u));
    const c = chooseCommand(s, SURE, {
      me: [{ when: { kind: "always" }, behavior: { kind: "attackLowestHp" } }],
    });
    expect(c?.kind === "act" && c.action.kind === "attack" && c.action.target).toBe("weak");
  });

  it("falls back to normal scoring when no gambit applies", () => {
    const s = createBattle(
      SURE,
      setup(["....."], [unit("me", 0, "infantry", 0, 0), unit("e", 1, "infantry", 4, 0)]),
    );
    const g: Gambit[] = [{ when: { kind: "canKill" }, behavior: { kind: "attackBest" } }];
    expect(chooseCommand(s, SURE, { me: g })).toEqual(chooseCommand(s, SURE));
  });
});
