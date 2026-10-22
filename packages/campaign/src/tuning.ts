/**
 * Story progression tuning (ADR 0048): experience per action, level-ups and
 * promotion. Starting numbers, our own; all integers.
 */
export const PROGRESSION = {
  /** Experience for one level. */
  expPerLevel: 100,
  levelCap: 50,
  /** Tier 2 at this level (ADR 0021). */
  promoteLevel: 20,
  statCap: 100,
  /** A landed strike: base + per level of the target above the striker, clamped. */
  strikeBase: 13,
  strikePerLevel: 2,
  strikeMin: 4,
  strikeMax: 24,
  /** Defeating an enemy, on top of the strike. */
  defeatBase: 32,
  defeatPerLevel: 3,
  defeatMin: 10,
  defeatMax: 50,
  /** Extra for defeating a unit a goal names (the enemy commander). */
  commanderBonus: 30,
  /** Winning a 일기토, on top of the defeat (ADR 0071). */
  duelBonus: 30,
  /** A stratagem that harms or hinders an enemy, per target. */
  spellBase: 12,
  spellPerLevel: 2,
  spellMin: 3,
  spellMax: 20,
  /** Healing or helping an ally: base + heal / healDivisor, clamped. */
  supportBase: 8,
  healDivisor: 10,
  supportMax: 20,
  /** Most experience from one cast, however many targets it hits. */
  castMax: 30,
  /** Each officer still standing when the battle is won. */
  victoryBonus: 30,
  /** Selling returns this ‰ of the price. */
  sellBack: 500,
} as const;
