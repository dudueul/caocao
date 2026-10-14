/**
 * Attack resolution (RULES.md §2): strike → counter → second strike, each strike
 * rolling hit then critical from the battle Prng (ADR 0022, ADR 0025).
 */
import { affinityAt, classOf, inRange, isAlive, terrainAt } from "./board.ts";
import { clamp, half, permille } from "./math.ts";
import type { Prng } from "./prng.ts";
import { forMode } from "./tuning.ts";
import type { BattleEvent, BattleState, ClassDef, Ruleset, StrikeRole, Unit } from "./types.ts";

/** ATK on the unit's current tile: (class + 무력 + weapon) × terrain effect‰. */
export function attackPower(rules: Ruleset, state: BattleState, u: Unit): number {
  const raw = classOf(rules, u).atk + u.stats.martial + u.atkBonus;
  return permille(raw, affinityAt(rules, state, u, u.x, u.y).effect);
}

/** DEF on the unit's current tile: (class + 통솔 + armour) × terrain effect‰. */
export function defensePower(rules: Ruleset, state: BattleState, u: Unit): number {
  const raw = classOf(rules, u).def + u.stats.command + u.defBonus;
  return permille(raw, affinityAt(rules, state, u, u.x, u.y).effect);
}

export function hitChance(rules: Ruleset, state: BattleState, a: Unit, d: Unit): number {
  const t = rules.tuning;
  const cover = rules.terrains[terrainAt(state, d.x, d.y)].cover;
  return clamp(
    t.hitBase + t.hitPerAgility * (a.stats.agility - d.stats.agility) - cover,
    t.hitMin,
    t.hitMax,
  );
}

export function critChance(rules: Ruleset, a: Unit, d: Unit): number {
  const t = rules.tuning;
  return clamp(
    t.critBase + t.critPerFortune * (a.stats.fortune - d.stats.fortune),
    t.critMin,
    t.critMax,
  );
}

/** Class-relation multiplier‰, attacker family vs defender family (ADR 0029). */
export function relationFactor(rules: Ruleset, a: ClassDef, d: ClassDef): number {
  return rules.relations[a.family]?.[d.family] ?? 1000;
}

/**
 * Named damage pipeline (ADR 0029). ATK and DEF already include terrain effect‰.
 *   base     max(level floor, ATK − ⌊DEF/2⌋)
 *   relation × class-relation‰
 *   role     × counter‰ for counter strikes
 *   critical × critical‰ when critical
 *   mode     × the mode's damage‰
 * Each step is floored. Future ability modifiers attach to a named stage.
 */
export type DamageStage = "base" | "relation" | "role" | "critical" | "mode";

export interface DamageStep {
  readonly stage: DamageStage;
  readonly value: number;
}

export function damageFloor(rules: Ruleset, attacker: Unit): number {
  return Math.max(
    rules.tuning.minDamage,
    permille(attacker.level, rules.tuning.damageFloorPerLevel),
  );
}

export function damageSteps(
  rules: Ruleset,
  state: BattleState,
  a: Unit,
  d: Unit,
  role: StrikeRole,
  crit: boolean,
): DamageStep[] {
  const t = rules.tuning;
  const steps: DamageStep[] = [];
  let v = Math.max(
    damageFloor(rules, a),
    attackPower(rules, state, a) - half(defensePower(rules, state, d)),
  );
  steps.push({ stage: "base", value: v });
  v = permille(v, relationFactor(rules, classOf(rules, a), classOf(rules, d)));
  steps.push({ stage: "relation", value: v });
  if (role === "counter") v = permille(v, t.counterMultiplier);
  steps.push({ stage: "role", value: v });
  if (crit) v = permille(v, t.critMultiplier);
  steps.push({ stage: "critical", value: v });
  v = permille(v, t.damageMultiplier);
  steps.push({ stage: "mode", value: v });
  return steps;
}

/** Damage of one landed strike: the last value of the pipeline. */
export function strikeDamage(
  rules: Ruleset,
  state: BattleState,
  a: Unit,
  d: Unit,
  role: StrikeRole,
  crit: boolean,
): number {
  const steps = damageSteps(rules, state, a, d, role, crit);
  return (steps[steps.length - 1] as DamageStep).value;
}

export function canCounter(rules: Ruleset, a: Unit, d: Unit): boolean {
  return (
    isAlive(d) &&
    d.status === null &&
    classOf(rules, a).provokesCounter &&
    inRange(classOf(rules, d).range, d, a)
  );
}

export function doublesAttack(rules: Ruleset, a: Unit, d: Unit): boolean {
  return a.stats.agility - d.stats.agility >= classOf(rules, a).doubleAttackGap;
}

/** Exact numbers for the attack preview (ADR 0025): no randomness involved. */
export interface AttackPreview {
  hit: number;
  crit: number;
  damage: number;
  critDamage: number;
  double: boolean;
  counter: { hit: number; crit: number; damage: number } | null;
  /** Pipeline of the first strike without a critical, for the forecast UI. */
  breakdown: DamageStep[];
}

export function previewAttack(
  baseRules: Ruleset,
  state: BattleState,
  a: Unit,
  d: Unit,
): AttackPreview {
  const rules = forMode(baseRules, state.mode);
  return {
    hit: hitChance(rules, state, a, d),
    crit: critChance(rules, a, d),
    damage: strikeDamage(rules, state, a, d, "first", false),
    critDamage: strikeDamage(rules, state, a, d, "first", true),
    double: doublesAttack(rules, a, d),
    counter: canCounter(rules, a, d)
      ? {
          hit: hitChance(rules, state, d, a),
          crit: critChance(rules, d, a),
          damage: strikeDamage(rules, state, d, a, "counter", false),
        }
      : null,
    breakdown: damageSteps(rules, state, a, d, "first", false),
  };
}

/** One strike: roll hit, then critical (only on a hit), apply damage. */
function strike(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  a: Unit,
  d: Unit,
  role: StrikeRole,
  events: BattleEvent[],
): void {
  const hit = prng.chance(hitChance(rules, state, a, d));
  const crit = hit && prng.chance(critChance(rules, a, d));
  const damage = hit ? strikeDamage(rules, state, a, d, role, crit) : 0;
  d.hp = Math.max(0, d.hp - damage);
  events.push({
    kind: "strike",
    attacker: a.id,
    defender: d.id,
    role,
    hit,
    crit,
    damage,
    hpAfter: d.hp,
  });
  if (d.hp === 0) defeat(rules, prng, d, events);
}

/** A defeated officer escapes, is captured or is killed (운, ADR 0020). */
export function defeat(rules: Ruleset, prng: Prng, u: Unit, events: BattleEvent[]): void {
  const t = rules.tuning;
  const escapeChance = clamp(t.escapeBase + t.escapePerFortune * u.stats.fortune, 0, t.escapeMax);
  u.fate = prng.chance(escapeChance)
    ? "escaped"
    : prng.chance(t.killedShare)
      ? "killed"
      : "captured";
  u.status = null;
  events.push({ kind: "defeated", unit: u.id, fate: u.fate });
}

/** Full attack exchange from the attacker's current tile. Mutates units. */
export function resolveAttack(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  a: Unit,
  d: Unit,
  events: BattleEvent[],
): void {
  // Decide double attack from stats before any HP changes (stats don't change mid-exchange).
  const double = doublesAttack(rules, a, d);
  strike(rules, state, prng, a, d, "first", events);
  if (!isAlive(d)) return;
  if (canCounter(rules, a, d)) {
    strike(rules, state, prng, d, a, "counter", events);
    if (!isAlive(a)) return;
  }
  if (double) strike(rules, state, prng, a, d, "second", events);
}
