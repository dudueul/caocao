/**
 * Conditions (ADR 0035): buffs and debuffs that stack with diminishing weight,
 * each stack carrying its own duration, rolled from the battle Prng when it lands.
 *
 * A "turn" is one of the bearer's own activations: stacks count down when the
 * bearer finishes acting (or is passed over by `endPhase`). A stack that lands
 * during the bearer's own turn (a self-buff) starts counting from its next one.
 */
import { clamp, permille } from "./math.ts";
import type { Prng } from "./prng.ts";
import type {
  BattleEvent,
  ConditionBlock,
  ConditionDef,
  ConditionStack,
  Ruleset,
  Unit,
} from "./types.ts";

export type ConditionField = "atk" | "def" | "hit" | "move" | "dot";

export function conditionDef(rules: Ruleset, id: string): ConditionDef {
  const c = rules.conditions[id];
  if (!c) throw new Error(`unknown condition ${id}`);
  return c;
}

/** Weight‰ of the k-th stack (0-based) of one condition: 1000, then × stackDiscount‰ each. */
export function stackWeight(rules: Ruleset, k: number): number {
  let w = 1000;
  for (let i = 0; i < k; i++) w = permille(w, rules.tuning.stackDiscount);
  return w;
}

/** value × weight‰, rounded toward zero so gains and losses are symmetric. */
function scaled(value: number, weight: number): number {
  return value < 0 ? -permille(-value, weight) : permille(value, weight);
}

/** How many stacks of each condition the unit carries, in first-landed order. */
function counts(u: Unit): Map<string, number> {
  const out = new Map<string, number>();
  for (const s of u.conditions) out.set(s.id, (out.get(s.id) ?? 0) + 1);
  return out;
}

/** Total of one modifier over all the unit's stacks, further stacks counting less. */
export function conditionModifier(rules: Ruleset, u: Unit, field: ConditionField): number {
  let total = 0;
  for (const [id, n] of counts(u)) {
    const value = conditionDef(rules, id)[field] ?? 0;
    if (value === 0) continue;
    for (let k = 0; k < n; k++) total += scaled(value, stackWeight(rules, k));
  }
  return total;
}

/** ATK or DEF multiplier‰ from conditions, never below `modifierFloor`. */
export function conditionMultiplier(rules: Ruleset, u: Unit, field: "atk" | "def"): number {
  return Math.max(rules.tuning.modifierFloor, 1000 + conditionModifier(rules, u, field));
}

export function hasBlock(rules: Ruleset, u: Unit, block: ConditionBlock): boolean {
  return u.conditions.some((s) => conditionDef(rules, s.id).blocks === block);
}

export function stacksOf(u: Unit, id: string): number {
  return u.conditions.filter((s) => s.id === id).length;
}

/** Rolls a stack's duration in [min, max] turns. */
export function rollTurns(prng: Prng, range: readonly [number, number]): number {
  const [min, max] = range;
  return min + prng.int(max - min + 1);
}

/**
 * Adds a stack. At `maxStacks`, the new stack replaces the one with the fewest
 * turns left, if it would last longer.
 */
export function addCondition(
  rules: Ruleset,
  u: Unit,
  id: string,
  turns: number,
  fresh: boolean,
): void {
  const stack: ConditionStack = { id, turns, fresh };
  if (stacksOf(u, id) < rules.tuning.maxStacks) {
    u.conditions.push(stack);
    return;
  }
  let shortest = -1;
  u.conditions.forEach((s, i) => {
    const best = u.conditions[shortest];
    if (s.id === id && (best === undefined || s.turns < best.turns)) shortest = i;
  });
  const old = u.conditions[shortest];
  if (old && old.turns < turns) u.conditions[shortest] = stack;
}

/** Removes every debuff stack; returns the ids removed, one per stack. */
export function removeDebuffs(rules: Ruleset, u: Unit): string[] {
  const removed = u.conditions.filter((s) => conditionDef(rules, s.id).kind === "debuff");
  u.conditions = u.conditions.filter((s) => conditionDef(rules, s.id).kind !== "debuff");
  return removed.map((s) => s.id);
}

/**
 * End of the bearer's turn: damage over time (never below 1 HP), then every
 * stack that wasn't fresh counts down; stacks at 0 end.
 */
export function endTurn(rules: Ruleset, u: Unit, events: BattleEvent[]): void {
  if (u.hp <= 0 || u.conditions.length === 0) return;
  const dot = conditionModifier(rules, u, "dot");
  if (dot > 0 && u.hp > 1) {
    const damage = clamp(permille(u.maxHp, dot), 1, u.hp - 1);
    u.hp -= damage;
    events.push({ kind: "conditionDamage", unit: u.id, damage, hpAfter: u.hp });
  }
  const kept: ConditionStack[] = [];
  for (const s of u.conditions) {
    if (s.fresh) {
      kept.push({ ...s, fresh: false });
      continue;
    }
    const turns = s.turns - 1;
    if (turns > 0) kept.push({ ...s, turns });
    else events.push({ kind: "conditionEnded", unit: u.id, condition: s.id });
  }
  u.conditions = kept;
}
