import { describe, expect, it } from "bun:test";
import {
  apply,
  createBattle,
  critChance,
  hitChance,
  previewAttack,
  type Ruleset,
  relationFactor,
  strikeDamage,
  unitById,
} from "../src/index.ts";
import { CLASSES, RULES, setup, unit } from "./fixtures.ts";

describe("ADR 0022 worked example", () => {
  // Tier-3 cavalry (class ATK 60) with 무력 97 and a +25 weapon, on neutral ground,
  // against tier-2 infantry (class DEF 50) with 통솔 70 and +20 armour.
  const rules: Ruleset = {
    ...RULES,
    classes: {
      ...CLASSES,
      cav3: { ...(CLASSES.cavalry as (typeof CLASSES)[string]), id: "cav3", atk: 60 },
      inf2: { ...(CLASSES.infantry as (typeof CLASSES)[string]), id: "inf2", def: 50 },
    },
    affinity: { ...RULES.affinity },
  };
  const s = createBattle(
    rules,
    setup(
      ["g....", "....."],
      [
        unit("guan", 0, "cav3", 0, 0, { martial: 97 }, { atkBonus: 25 }),
        unit("foot", 1, "inf2", 1, 0, { command: 70 }, { defBonus: 20 }),
      ],
    ),
  );
  const a = unitById(s, "guan");
  const d = unitById(s, "foot");

  it("base 112, ×800‰ for cavalry against infantry = 89, critical 133", () => {
    expect(relationFactor(rules, CLASSES.cavalry as never, CLASSES.infantry as never)).toBe(800);
    expect(strikeDamage(rules, s, a, d, "first", false)).toBe(89);
    expect(strikeDamage(rules, s, a, d, "first", true)).toBe(133);
  });

  it("counter strikes deal 800‰", () => {
    const normal = strikeDamage(rules, s, d, a, "first", false);
    expect(strikeDamage(rules, s, d, a, "counter", false)).toBe(Math.floor((normal * 800) / 1000));
  });
});

describe("hit and critical chances", () => {
  const s = createBattle(
    RULES,
    setup(
      [".f..."],
      [
        unit("a", 0, "infantry", 0, 0, { agility: 70, fortune: 90 }),
        unit("d", 1, "infantry", 1, 0, { agility: 50, fortune: 10 }),
      ],
    ),
  );
  const a = unitById(s, "a");
  const d = unitById(s, "d");

  it("hit‰ = 850 + 6·Δ민첩 − cover (forest 150), clamped", () => {
    expect(hitChance(RULES, s, a, d)).toBe(850 + 6 * 20 - 150);
    expect(hitChance(RULES, s, d, a)).toBe(850 - 6 * 20);
  });

  it("crit‰ = 40 + 4·Δ운, clamped to 10..350", () => {
    expect(critChance(RULES, a, d)).toBe(350);
    expect(critChance(RULES, d, a)).toBe(10);
  });
});

describe("counters and double attacks (ADR 0025)", () => {
  it("melee defenders counter adjacent attackers; archers at range draw no counter", () => {
    const s = createBattle(
      RULES,
      setup(
        ["....."],
        [
          unit("inf", 0, "infantry", 0, 0),
          unit("arc", 1, "archer", 2, 0),
          unit("foe", 1, "infantry", 1, 0),
        ],
      ),
    );
    expect(previewAttack(RULES, s, unitById(s, "inf"), unitById(s, "foe")).counter).not.toBeNull();
    expect(previewAttack(RULES, s, unitById(s, "arc"), unitById(s, "inf")).counter).toBeNull();
  });

  it("catapult attacks never provoke counters", () => {
    const s = createBattle(
      RULES,
      setup(["....."], [unit("cat", 0, "catapult", 0, 0), unit("cat2", 1, "catapult", 2, 0)]),
    );
    expect(previewAttack(RULES, s, unitById(s, "cat"), unitById(s, "cat2")).counter).toBeNull();
  });

  it("double attack at a 민첩 gap of 20 (martial artists 10)", () => {
    const s = createBattle(
      RULES,
      setup(
        ["....."],
        [
          unit("fast", 0, "infantry", 0, 0, { agility: 70 }),
          unit("almost", 0, "infantry", 2, 0, { agility: 69 }),
          unit("monk", 0, "martialArtist", 4, 0, { agility: 60 }),
          unit("foe", 1, "infantry", 1, 0, { agility: 50 }),
        ],
      ),
    );
    const foe = unitById(s, "foe");
    expect(previewAttack(RULES, s, unitById(s, "fast"), foe).double).toBe(true);
    expect(previewAttack(RULES, s, unitById(s, "almost"), foe).double).toBe(false);
    expect(previewAttack(RULES, s, unitById(s, "monk"), foe).double).toBe(true);
  });

  it("resolves strike → counter → second strike in order", () => {
    const s = createBattle(
      RULES,
      setup(
        ["...."],
        [
          unit("fast", 0, "infantry", 0, 0, { agility: 90 }),
          unit("foe", 1, "infantry", 1, 0, { agility: 50 }),
        ],
        { seed: "order" },
      ),
    );
    const r = apply(s, RULES, {
      kind: "act",
      unit: "fast",
      to: { x: 0, y: 0 },
      action: { kind: "attack", target: "foe" },
    });
    expect(r.ok).toBe(true);
    if (!r.ok) return;
    const roles = r.events.flatMap((e) => (e.kind === "strike" ? [e.role] : []));
    expect(roles).toEqual(["first", "counter", "second"]);
  });
});

describe("ADR 0029: relation matrix, modes, damage pipeline, level floor", () => {
  const twoUnits = (over: Parameters<typeof setup>[2] = {}, attackerLevel = 1) =>
    setup(
      ["...."],
      [
        unit("a", 0, "cavalry", 0, 0, {}, { level: attackerLevel }),
        unit("d", 1, "archer", 1, 0, { command: 50 }),
      ],
      over,
    );

  it("looks up family × family relations; missing pairs are neutral", () => {
    expect(relationFactor(RULES, CLASSES.cavalry as never, CLASSES.archer as never)).toBe(1200);
    expect(relationFactor(RULES, CLASSES.strategist as never, CLASSES.archer as never)).toBe(1000);
    const rules: Ruleset = { ...RULES, relations: { cavalry: { archer: 1500 } } };
    expect(relationFactor(rules, CLASSES.cavalry as never, CLASSES.archer as never)).toBe(1500);
  });

  it("runs the named stages in order and ends on the strike damage", () => {
    const s = createBattle(RULES, twoUnits());
    const p = previewAttack(RULES, s, unitById(s, "a"), unitById(s, "d"));
    expect(p.breakdown.map((x) => x.stage)).toEqual([
      "base",
      "relation",
      "role",
      "critical",
      "mode",
    ]);
    expect(p.breakdown.at(-1)?.value).toBe(p.damage);
  });

  it("applies per-mode tuning overrides chosen at battle creation", () => {
    const rules: Ruleset = { ...RULES, modes: { arena: { damageMultiplier: 500 } } };
    const sandbox = createBattle(rules, twoUnits());
    const arena = createBattle(rules, twoUnits({ mode: "arena" }));
    const full = previewAttack(
      rules,
      sandbox,
      unitById(sandbox, "a"),
      unitById(sandbox, "d"),
    ).damage;
    const halfDmg = previewAttack(rules, arena, unitById(arena, "a"), unitById(arena, "d")).damage;
    expect(arena.mode).toBe("arena");
    expect(halfDmg).toBe(Math.floor((full * 500) / 1000));
  });

  it("damage floor scales with the attacker's level: max(10, ⌊1.5 × level⌋)", () => {
    const heavy: Ruleset = {
      ...RULES,
      classes: {
        ...CLASSES,
        archer: { ...(CLASSES.archer as NonNullable<(typeof CLASSES)[string]>), def: 400 },
      },
    };
    const low = createBattle(heavy, twoUnits({}, 1));
    const high = createBattle(heavy, twoUnits({}, 40));
    expect(
      previewAttack(heavy, low, unitById(low, "a"), unitById(low, "d")).breakdown[0]?.value,
    ).toBe(10);
    expect(
      previewAttack(heavy, high, unitById(high, "a"), unitById(high, "d")).breakdown[0]?.value,
    ).toBe(60);
  });
});
