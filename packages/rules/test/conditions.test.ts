import { describe, expect, it } from "bun:test";
import {
  addCondition,
  apply,
  attackPower,
  type BattleState,
  type Command,
  chooseCommand,
  conditionMultiplier,
  createBattle,
  defensePower,
  hitChance,
  legalCommands,
  previewAttack,
  previewStratagem,
  type Ruleset,
  reachable,
  type StratagemDef,
  stackWeight,
  statusChance,
  unitById,
} from "../src/index.ts";
import { ADJACENT, BOW, RULES, SELF_AND_ADJACENT, setup, unit } from "./fixtures.ts";

const cast = (o: Partial<StratagemDef> & Pick<StratagemDef, "id">): StratagemDef => ({
  name: o.id,
  school: "support",
  mp: 4,
  range: [...ADJACENT, ...BOW],
  area: [{ dx: 0, dy: 0 }],
  effect: "condition",
  targets: "enemies",
  power: 0,
  windfall: "power",
  mishap: "fizzle",
  ...o,
});

/** RULES where the strategist casts `extra` too; no fortune twists, so outcomes are exact. */
function withCasts(...extra: StratagemDef[]): Ruleset {
  const strategist = RULES.classes.strategist;
  if (!strategist) throw new Error("fixture");
  const stratagems = { ...RULES.stratagems };
  for (const s of extra) stratagems[s.id] = s;
  return {
    ...RULES,
    classes: {
      ...RULES.classes,
      strategist: {
        ...strategist,
        stratagems: [...strategist.stratagems, ...extra.map((s) => s.id)],
      },
    },
    stratagems,
    tuning: { ...RULES.tuning, twist: 0, statusMin: 1000, statusMax: 1000 },
  };
}

const RALLY = cast({
  id: "rallyCast",
  targets: "allies",
  range: SELF_AND_ADJACENT,
  condition: "rally",
  turns: [2, 4],
});
const POISON = cast({ id: "poisonCast", condition: "poison", turns: [2, 2] });
const SEAL = cast({ id: "sealCast", condition: "seal", turns: [2, 2] });
const BIND = cast({ id: "bindCast", condition: "bind", turns: [1, 1] });
const CLEANSE = cast({
  id: "cleanseCast",
  targets: "allies",
  range: SELF_AND_ADJACENT,
  effect: "heal",
  power: 10,
  cleanse: true,
});
const SMOKE = cast({
  id: "smoke",
  effect: "damage",
  power: 20,
  condition: "poison",
  turns: [3, 3],
});

function act(s: BattleState, rules: Ruleset, c: Command): BattleState {
  const r = apply(s, rules, c);
  if (!r.ok) throw new Error(r.reason);
  return r.state;
}
const castAt = (u: string, from: [number, number], id: string, at: [number, number]): Command => ({
  kind: "act",
  unit: u,
  to: { x: from[0], y: from[1] },
  action: { kind: "stratagem", id, at: { x: at[0], y: at[1] } },
});
const wait = (u: string, x: number, y: number): Command => ({
  kind: "act",
  unit: u,
  to: { x, y },
  action: { kind: "wait" },
});

function duel(rules: Ruleset, seed = "c", row = "........"): BattleState {
  return createBattle(
    rules,
    setup(
      [row, row],
      [
        unit("s", 0, "strategist", 0, 0),
        unit("ally", 0, "infantry", 1, 0),
        unit("e", 1, "infantry", 3, 0),
      ],
      {
        seed,
        policy: "sidePhases",
      },
    ),
  );
}

describe("discounted stacking (ADR 0035)", () => {
  it("each further stack of one condition counts half the one before", () => {
    expect([0, 1, 2, 3].map((k) => stackWeight(RULES, k))).toEqual([1000, 500, 250, 125]);
    const s = duel(RULES);
    const u = unitById(s, "ally");
    const mult: number[] = [];
    for (let i = 0; i < 3; i++) {
      addCondition(RULES, u, "rally", 3, false);
      mult.push(conditionMultiplier(RULES, u, "atk"));
    }
    expect(mult).toEqual([1200, 1300, 1350]);
  });

  it("at the stack limit a longer stack replaces the shortest; a shorter one is dropped", () => {
    const u = unitById(duel(RULES), "ally");
    for (const t of [3, 1, 2]) addCondition(RULES, u, "rally", t, false);
    addCondition(RULES, u, "rally", 1, false);
    expect(u.conditions.map((c) => c.turns)).toEqual([3, 1, 2]);
    addCondition(RULES, u, "rally", 4, false);
    expect(u.conditions.map((c) => c.turns)).toEqual([3, 4, 2]);
  });

  it("different conditions add up, and ATK/DEF never fall below the floor", () => {
    const u = unitById(duel(RULES), "ally");
    const s = duel(RULES);
    const base = attackPower(RULES, s, u);
    addCondition(RULES, u, "rally", 2, false);
    addCondition(RULES, u, "weaken", 2, false);
    expect(conditionMultiplier(RULES, u, "atk")).toBe(1000);
    for (let i = 0; i < 3; i++) addCondition(RULES, u, "weaken", 2, false);
    expect(conditionMultiplier(RULES, u, "def")).toBe(1000 - 200 - 100 - 50);
    const harsh: Ruleset = { ...RULES, tuning: { ...RULES.tuning, modifierFloor: 900 } };
    expect(conditionMultiplier(harsh, u, "def")).toBe(900);
    expect(base).toBeGreaterThan(0);
  });
});

describe("durations rolled on landing", () => {
  it("each stack's duration is rolled from the range, the same for the same seed", () => {
    const R = withCasts(RALLY);
    const seen = new Set<number>();
    for (let i = 0; i < 40; i++) {
      const s = act(duel(R, `roll-${i}`), R, castAt("s", [0, 0], "rallyCast", [1, 0]));
      const turns = unitById(s, "ally").conditions[0]?.turns ?? 0;
      expect(turns).toBeGreaterThanOrEqual(2);
      expect(turns).toBeLessThanOrEqual(4);
      seen.add(turns);
      const again = act(duel(R, `roll-${i}`), R, castAt("s", [0, 0], "rallyCast", [1, 0]));
      expect(unitById(again, "ally").conditions).toEqual(unitById(s, "ally").conditions);
    }
    expect([...seen].sort()).toEqual([2, 3, 4]);
  });

  it("stacks count down separately at the end of the bearer's turns", () => {
    let s = duel(RULES);
    const ally = unitById(s, "ally");
    ally.conditions = [
      { id: "rally", turns: 1, fresh: false },
      { id: "rally", turns: 3, fresh: false },
    ];
    const r = apply(s, RULES, wait("ally", 1, 0));
    if (!r.ok) throw new Error(r.reason);
    s = r.state;
    expect(unitById(s, "ally").conditions).toEqual([{ id: "rally", turns: 2, fresh: false }]);
    expect(r.events).toContainEqual({ kind: "conditionEnded", unit: "ally", condition: "rally" });
  });

  it("a stack the caster puts on itself starts counting on its next turn", () => {
    const R = withCasts(cast({ ...RALLY, turns: [1, 1] }));
    const s = act(duel(R), R, castAt("s", [0, 0], "rallyCast", [0, 0]));
    expect(unitById(s, "s").conditions).toEqual([{ id: "rally", turns: 1, fresh: false }]);
  });

  it("units passed over by endPhase still use up a turn", () => {
    const s = duel(RULES);
    unitById(s, "ally").conditions = [{ id: "rally", turns: 1, fresh: false }];
    const next = act(s, RULES, { kind: "endPhase" });
    expect(unitById(next, "ally").conditions).toEqual([]);
  });
});

describe("what conditions do", () => {
  it("bind pins the unit, which can still strike in reach", () => {
    const s = duel(RULES);
    unitById(s, "ally").conditions = [{ id: "bind", turns: 1, fresh: false }];
    expect(reachable(s, RULES, unitById(s, "ally")).map((r) => [r.x, r.y])).toEqual([[1, 0]]);
  });

  it("seal stops stratagems; confusion stops everything but waiting, and counters", () => {
    const s = duel(RULES);
    unitById(s, "s").conditions = [{ id: "seal", turns: 1, fresh: false }];
    const cmds = legalCommands(s, RULES).filter((c) => c.kind === "act" && c.unit === "s");
    expect(cmds.some((c) => c.kind === "act" && c.action.kind === "stratagem")).toBe(false);
    unitById(s, "e").conditions = [{ id: "confusion", turns: 1, fresh: false }];
    const moved = { ...unitById(s, "ally"), x: 2 };
    expect(previewAttack(RULES, s, moved, unitById(s, "e")).counter).toBeNull();
  });

  it("haste adds move and hit; weaken lowers ATK and DEF", () => {
    const open = "..............";
    const s = createBattle(
      RULES,
      setup(
        [open, open, open],
        [unit("ally", 0, "infantry", 1, 1), unit("e", 1, "infantry", 13, 1)],
      ),
    );
    const ally = unitById(s, "ally");
    const e = unitById(s, "e");
    const hit = hitChance(RULES, s, ally, e);
    const reach = reachable(s, RULES, ally).length;
    ally.conditions = [{ id: "haste", turns: 1, fresh: false }];
    expect(hitChance(RULES, s, ally, e)).toBe(Math.min(RULES.tuning.hitMax, hit + 100));
    expect(reachable(s, RULES, ally).length).toBeGreaterThan(reach);
    const def = defensePower(RULES, s, e);
    e.conditions = [{ id: "weaken", turns: 1, fresh: false }];
    expect(defensePower(RULES, s, e)).toBe(Math.floor((def * 800) / 1000));
  });

  it("poison hurts at the end of the bearer's turn, discounted per stack, never below 1 HP", () => {
    let s = duel(RULES);
    const ally = unitById(s, "ally");
    ally.conditions = [
      { id: "poison", turns: 3, fresh: false },
      { id: "poison", turns: 3, fresh: false },
    ];
    const r = apply(s, RULES, wait("ally", 1, 0));
    if (!r.ok) throw new Error(r.reason);
    const expected = Math.floor((ally.maxHp * 150) / 1000);
    expect(r.events).toContainEqual({
      kind: "conditionDamage",
      unit: "ally",
      damage: expected,
      hpAfter: ally.maxHp - expected,
    });
    s = duel(RULES);
    unitById(s, "ally").hp = 3;
    unitById(s, "ally").conditions = [{ id: "poison", turns: 2, fresh: false }];
    expect(unitById(act(s, RULES, wait("ally", 1, 0)), "ally").hp).toBe(1);
  });

  it("cleanse removes debuffs and keeps buffs", () => {
    const R = withCasts(CLEANSE);
    const s = duel(R);
    unitById(s, "ally").conditions = [
      { id: "poison", turns: 2, fresh: false },
      { id: "rally", turns: 2, fresh: false },
      { id: "weaken", turns: 1, fresh: false },
    ];
    const r = apply(s, R, castAt("s", [0, 0], "cleanseCast", [1, 0]));
    if (!r.ok) throw new Error(r.reason);
    expect(unitById(r.state, "ally").conditions.map((c) => c.id)).toEqual(["rally"]);
    expect(r.events).toContainEqual({
      kind: "cleansed",
      unit: "ally",
      removed: ["poison", "weaken"],
    });
  });

  it("a damage cast with a rider still deals damage when the rider is resisted", () => {
    const R: Ruleset = {
      ...withCasts(SMOKE),
      tuning: { ...withCasts(SMOKE).tuning, statusMin: 0, statusMax: 0 },
    };
    const r = apply(duel(R), R, castAt("s", [2, 1], "smoke", [3, 0]));
    if (!r.ok) throw new Error(r.reason);
    const effect = r.events.find((e) => e.kind === "stratagemEffect");
    expect(effect).toMatchObject({ unit: "e", condition: null, resisted: true });
    expect(effect && effect.kind === "stratagemEffect" && effect.damage).toBeGreaterThan(0);
  });

  it("the preview shows the condition, its turn range and the landing chance", () => {
    const R = withCasts(SEAL);
    const s = duel(R);
    const caster = unitById(s, "s");
    const p = previewStratagem(R, s, caster, SEAL, { x: 3, y: 0 });
    expect(p.condition).toBe("seal");
    expect(p.turns).toEqual([2, 2]);
    expect(p.targets[0]?.chance).toBe(statusChance(R, caster, unitById(s, "e")));
  });
});

describe("AI and conditions", () => {
  it("doesn't buff allies that are far from every enemy", () => {
    const R = withCasts(RALLY);
    const s = createBattle(
      R,
      setup(
        ["...................."],
        [
          unit("s", 0, "strategist", 0, 0),
          unit("ally", 0, "infantry", 1, 0),
          unit("e", 1, "infantry", 19, 0),
        ],
      ),
    );
    const c = chooseCommand(s, R);
    expect(c?.kind === "act" && c.action.kind === "stratagem").toBe(false);
  });

  it("binds or seals rather than wasting a turn when no attack is possible", () => {
    const R = withCasts(BIND, SEAL, POISON);
    const s = createBattle(
      R,
      setup(
        ["........"],
        [unit("s", 0, "strategist", 0, 0, { intellect: 90 }), unit("e", 1, "strategist", 3, 0)],
      ),
    );
    const c = chooseCommand(s, R);
    expect(c?.kind === "act" && c.action.kind === "stratagem").toBe(true);
  });
});
