import type { CounterGroup, FamilyId, ModeId, Ruleset, Tuning } from "./types.ts";

/** Starting tuning from docs/remake/RULES.md (ADRs 0020–0025). */
export const DEFAULT_TUNING: Tuning = {
  hitBase: 850,
  hitPerAgility: 6,
  hitMin: 250,
  hitMax: 990,
  critBase: 40,
  critPerFortune: 4,
  critMin: 10,
  critMax: 350,
  critMultiplier: 1500,
  minDamage: 10,
  counterMultiplier: 800,
  escapeBase: 600,
  escapePerFortune: 4,
  escapeMax: 950,
  killedShare: 500,
  statusBase: 500,
  statusPerIntellect: 5,
  statusPerFortune: 3,
  statusMin: 50,
  statusMax: 900,
  twist: 40,
  windfallBase: 500,
  windfallPerFortune: 6,
  windfallMin: 150,
  windfallMax: 850,
  windfallMultiplier: 1500,
  hpPerLevel: 4,
  damageFloorPerLevel: 1500,
  damageMultiplier: 1000,
  mpPerIntellect: 500,
};

const BEATS: Readonly<Record<CounterGroup, CounterGroup>> = {
  infantry: "cavalry",
  cavalry: "archer",
  archer: "infantry",
};

/**
 * Builds the default relation matrix from counter-triangle groups (ADR 0021):
 * +bonus‰ against the group a family beats, −bonus‰ against the group that beats it.
 */
export function triangleRelations(
  groups: Readonly<Partial<Record<FamilyId, CounterGroup>>>,
  bonus: number,
): Record<string, Record<string, number>> {
  const out: Record<string, Record<string, number>> = {};
  for (const [a, ga] of Object.entries(groups)) {
    const row: Record<string, number> = {};
    for (const [d, gd] of Object.entries(groups)) {
      if (BEATS[ga] === gd) row[d] = 1000 + bonus;
      else if (BEATS[gd] === ga) row[d] = 1000 - bonus;
    }
    out[a] = row;
  }
  return out;
}

const modeCache = new WeakMap<Ruleset, Map<ModeId, Ruleset>>();

/** The ruleset with the mode's tuning overrides applied (memoized). */
export function forMode(rules: Ruleset, mode: ModeId): Ruleset {
  const override = rules.modes?.[mode];
  if (!override) return rules;
  let byMode = modeCache.get(rules);
  if (!byMode) {
    byMode = new Map();
    modeCache.set(rules, byMode);
  }
  let r = byMode.get(mode);
  if (!r) {
    r = { ...rules, tuning: { ...rules.tuning, ...override } };
    byMode.set(mode, r);
  }
  return r;
}
