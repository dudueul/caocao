import { describe, expect, it } from "bun:test";
import { createBattle, previewAttack, relationFactor, unitById } from "@tk/rules";
import {
  OFFICERS,
  P0_RULES,
  P0_RULES_HASH,
  parseMap,
  ring,
  SCENARIOS,
  scenarioSetup,
  TERRAIN_IDS,
  validateRuleset,
  validateScenario,
  WUCHAO_RAID,
} from "../src/index.ts";

describe("P0 ruleset", () => {
  it("passes validation", () => {
    expect(validateRuleset(P0_RULES)).toEqual([]);
  });

  it("has the six P0 tier-1 classes (ADR 0021) and all twelve terrains (ADR 0023)", () => {
    expect(Object.keys(P0_RULES.classes).sort()).toEqual(
      ["archer", "catapult", "cavalry", "infantry", "martialArtist", "strategist"].sort(),
    );
    expect(Object.keys(P0_RULES.terrains).sort()).toEqual([...TERRAIN_IDS].sort());
    for (const row of Object.values(P0_RULES.affinity))
      expect(Object.keys(row).sort()).toEqual([...TERRAIN_IDS].sort());
  });

  it("keeps the counter triangle: infantry ▶ cavalry ▶ archer ▶ infantry at ±200‰", () => {
    const r = P0_RULES.relations;
    expect(r.infantry?.cavalry).toBe(1200);
    expect(r.cavalry?.archer).toBe(1200);
    expect(r.archer?.infantry).toBe(1200);
    expect(r.cavalry?.infantry).toBe(800);
    expect(r.catapult?.infantry).toBe(1200);
    expect(r.strategist).toBeUndefined();
  });

  it("matches RULES.md §4 example rows for cavalry", () => {
    const c = P0_RULES.affinity.cavalry;
    expect(c?.plain).toEqual({ move: 1, effect: 1100 });
    expect(c?.forest).toEqual({ move: 2, effect: 900 });
    expect(c?.mountain.move).toBeNull();
  });

  it("has a stable content hash", () => {
    expect(P0_RULES_HASH).toMatch(/^[0-9a-f]{16}$/);
  });

  it("ring() builds symmetric Manhattan shapes", () => {
    expect(ring(1, 1)).toHaveLength(4);
    expect(ring(2, 2)).toHaveLength(8);
    expect(ring(2, 3)).toHaveLength(20);
    expect(ring(0, 2)).toHaveLength(13);
  });
});

describe("validation catches broken content", () => {
  it("reports unknown stratagems, missing affinity rows and floats", () => {
    const infantry = P0_RULES.classes.infantry;
    if (!infantry) throw new Error("fixture");
    const broken = {
      ...P0_RULES,
      classes: {
        ...P0_RULES.classes,
        infantry: { ...infantry, stratagems: ["nope"] },
        bandit: { ...infantry, id: "bandit", family: "bandit" as const, atk: 1.5 },
      },
    };
    const problems = validateRuleset(broken);
    expect(problems.some((p) => p.includes("unknown stratagem nope"))).toBe(true);
    expect(problems.some((p) => p.includes("no affinity row for family bandit"))).toBe(true);
    expect(problems.some((p) => p.includes("non-integer"))).toBe(true);
  });

  it("reports units on impassable or shared tiles", () => {
    const [first, second] = WUCHAO_RAID.units;
    if (!first || !second) throw new Error("fixture");
    const broken = {
      ...WUCHAO_RAID,
      units: [
        { ...first, x: 10, y: 0 }, // water
        second,
        { ...second, officer: "gao-lan", side: 1 },
      ],
    };
    const problems = validateScenario(P0_RULES, broken);
    expect(problems.some((p) => p.includes("can't stand on water"))).toBe(true);
    expect(problems.some((p) => p.includes("tile already taken"))).toBe(true);
  });
});

describe("scenarios", () => {
  for (const s of Object.values(SCENARIOS)) {
    it(`${s.id} validates and starts a battle`, () => {
      expect(validateScenario(P0_RULES, s)).toEqual([]);
      const state = createBattle(P0_RULES, scenarioSetup(s, { seed: "start" }));
      expect(state.units).toHaveLength(s.units.length);
      expect(state.sides).toBe(s.sides.length);
    });
  }

  it("officer ratings are our own: integers 1–100 in steps of 5", () => {
    for (const o of Object.values(OFFICERS))
      for (const v of Object.values(o.stats)) {
        expect(v % 5).toBe(0);
        expect(v).toBeGreaterThanOrEqual(5);
        expect(v).toBeLessThanOrEqual(100);
      }
  });

  it("the Wuchao map is 16 × 12 with one bridge and two fords", () => {
    const map = parseMap(WUCHAO_RAID.map);
    expect([map.width, map.height]).toEqual([16, 12]);
    expect(map.tiles.filter((t) => t === "bridge")).toHaveLength(1);
    expect(map.tiles.filter((t) => t === "ford")).toHaveLength(2);
  });

  it("hits land on the medium scale (ADR 0022): about 3–5 hits to fell a unit", () => {
    const s = createBattle(P0_RULES, scenarioSetup(WUCHAO_RAID, { seed: "scale" }));
    const yueJin = unitById(s, "yue-jin");
    const gaoLan = unitById(s, "gao-lan");
    const moved = {
      ...s,
      units: s.units.map((u) => (u.id === "yue-jin" ? { ...u, x: 12, y: 4 } : u)),
    };
    const p = previewAttack(P0_RULES, moved, unitById(moved, "yue-jin"), gaoLan);
    const inf = P0_RULES.classes.infantry;
    if (!inf) throw new Error("fixture");
    expect(relationFactor(P0_RULES, inf, inf)).toBe(1000);
    const hits = Math.ceil(gaoLan.maxHp / p.damage);
    expect(hits).toBeGreaterThanOrEqual(3);
    expect(hits).toBeLessThanOrEqual(6);
    expect(yueJin.maxHp).toBeGreaterThanOrEqual(100);
    expect(yueJin.maxHp).toBeLessThanOrEqual(600);
  });
});
