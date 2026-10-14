import { describe, expect, it } from "bun:test";
import {
  apply,
  type BattleEvent,
  castStratagem,
  createBattle,
  Prng,
  seedFromString,
  statusChance,
  stratagemDamage,
  unitById,
} from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

const FIRE = RULES.stratagems.fire as NonNullable<(typeof RULES.stratagems)[string]>;
const HEAL = RULES.stratagems.heal as NonNullable<(typeof RULES.stratagems)[string]>;

function duel(row: string, weather: "sunny" | "rain" | "cloudy" = "cloudy") {
  return createBattle(
    RULES,
    setup(
      [row],
      [
        unit("s", 0, "strategist", 0, 0, { intellect: 90, fortune: 50 }),
        unit("t", 1, "infantry", 2, 0, { intellect: 40, fortune: 50 }),
      ],
      { weather },
    ),
  );
}

describe("stratagem power (ADR 0024)", () => {
  it("fire: base + 지력a − ⌊지력d/2⌋, × terrain‰ × weather‰", () => {
    const base = 30 + 90 - 20;
    const plain = duel("....");
    expect(stratagemDamage(plain, FIRE, unitById(plain, "s"), unitById(plain, "t"))).toBe(base);
    const forest = duel("..f.", "sunny");
    expect(stratagemDamage(forest, FIRE, unitById(forest, "s"), unitById(forest, "t"))).toBe(
      Math.floor((Math.floor((base * 1200) / 1000) * 1100) / 1000),
    );
    const rain = duel("..d.", "rain");
    expect(stratagemDamage(rain, FIRE, unitById(rain, "s"), unitById(rain, "t"))).toBe(
      Math.floor((Math.floor((base * 600) / 1000) * 500) / 1000),
    );
  });

  it("status chance‰ = 500 + 5·Δ지력 − 3·운d, clamped", () => {
    const s = duel("....");
    expect(statusChance(RULES, unitById(s, "s"), unitById(s, "t"))).toBe(500 + 5 * 50 - 3 * 50);
  });

  it("heal never exceeds max HP", () => {
    const s = createBattle(
      RULES,
      setup(["...."], [unit("s", 0, "strategist", 0, 0), unit("e", 1, "infantry", 3, 0)]),
    );
    const r = apply(s, RULES, {
      kind: "act",
      unit: "s",
      to: { x: 0, y: 0 },
      action: { kind: "stratagem", id: "heal", at: { x: 0, y: 0 } },
    });
    expect(r.ok).toBe(true);
    if (!r.ok) return;
    const effect = r.events.find((e) => e.kind === "stratagemEffect");
    expect(effect && effect.kind === "stratagemEffect" ? effect.heal : -1).toBe(0);
    expect(HEAL.mp).toBe(5);
  });
});

describe("fortune twists (ADR 0024)", () => {
  function twists(casterFortune: number, casts: number) {
    let windfall = 0;
    let mishap = 0;
    for (let i = 0; i < casts; i++) {
      const s = createBattle(
        RULES,
        setup(
          ["...."],
          [
            unit("s", 0, "strategist", 0, 0, { fortune: casterFortune }),
            unit("t", 1, "infantry", 2, 0, { fortune: 50 }),
          ],
        ),
      );
      const caster = unitById(s, "s");
      const events: BattleEvent[] = [];
      castStratagem(
        RULES,
        s,
        new Prng(seedFromString(`twist-${i}`)),
        caster,
        FIRE,
        { x: 2, y: 0 },
        events,
      );
      for (const e of events) {
        if (e.kind !== "twist") continue;
        if (e.outcome === "windfall") windfall++;
        else mishap++;
      }
    }
    return { windfall, mishap };
  }

  it("happen on about 1 cast in 25", () => {
    const { windfall, mishap } = twists(50, 4000);
    const rate = windfall + mishap;
    expect(rate).toBeGreaterThan(110);
    expect(rate).toBeLessThan(210);
  });

  it("lean towards windfalls for a lucky caster", () => {
    const lucky = twists(100, 4000);
    const unlucky = twists(1, 4000);
    expect(lucky.windfall).toBeGreaterThan(lucky.mishap);
    expect(unlucky.mishap).toBeGreaterThan(unlucky.windfall);
  });
});
