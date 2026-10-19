/**
 * Experience, levels, stat growth and promotion (ADR 0048). Experience is read
 * from battle events, so a replayed battle earns exactly the same amounts.
 */
import { GROWTH, PROMOTIONS } from "@tk/content";
import type { BattleEvent, BattleState, Stats } from "@tk/rules";
import { PROGRESSION as P } from "./tuning.ts";
import type { Officer } from "./types.ts";

const clamp = (v: number, lo: number, hi: number) => Math.max(lo, Math.min(hi, v));

/** Stat changes, by stat; missing stats are 0. */
export type StatGains = { -readonly [K in keyof Stats]?: number };

export const STAT_KEYS: readonly (keyof Stats)[] = [
  "martial",
  "command",
  "intellect",
  "agility",
  "fortune",
];

/** Experience and defeats earned in a battle, by unit id. */
export interface Ledger {
  exp: Record<string, number>;
  defeated: Record<string, number>;
}

export function emptyLedger(): Ledger {
  return { exp: {}, defeated: {} };
}

/**
 * Adds what one command's events earned to `ledger`. `before` is the state the
 * command was applied to (levels and sides come from it, and from `after` for
 * units that arrived during the command).
 */
export function recordEvents(
  ledger: Ledger,
  before: BattleState,
  after: BattleState,
  events: readonly BattleEvent[],
): void {
  const find = (id: string) =>
    before.units.find((u) => u.id === id) ?? after.units.find((u) => u.id === id);
  const level = (id: string) => find(id)?.level ?? 1;
  const side = (id: string) => find(id)?.side ?? -1;
  const commanders = new Set(
    before.goals.flatMap((g) => (g.when.kind === "defeat" ? g.when.units : [])),
  );
  const add = (id: string, n: number) => {
    if (n > 0) ledger.exp[id] = (ledger.exp[id] ?? 0) + n;
  };
  const lastHit = new Map<string, string>();
  let caster: string | null = null;
  let castExp = 0;
  const endCast = () => {
    if (caster) add(caster, Math.min(castExp, P.castMax));
    caster = null;
    castExp = 0;
  };
  for (const e of events) {
    switch (e.kind) {
      case "strike": {
        if (!e.hit) break;
        lastHit.set(e.defender, e.attacker);
        const gap = level(e.defender) - level(e.attacker);
        add(e.attacker, clamp(P.strikeBase + P.strikePerLevel * gap, P.strikeMin, P.strikeMax));
        break;
      }
      case "stratagem":
        endCast();
        caster = e.caster;
        break;
      case "stratagemEffect": {
        if (!caster) break;
        const foe = side(e.unit) !== side(caster);
        if (foe && (e.damage > 0 || (e.condition !== null && !e.resisted))) {
          if (e.damage > 0) lastHit.set(e.unit, caster);
          const gap = level(e.unit) - level(caster);
          castExp += clamp(P.spellBase + P.spellPerLevel * gap, P.spellMin, P.spellMax);
        } else if (!foe && (e.heal > 0 || e.condition !== null)) {
          castExp += Math.min(P.supportMax, P.supportBase + Math.floor(e.heal / P.healDivisor));
        }
        break;
      }
      case "cleansed":
        if (caster && side(e.unit) === side(caster)) castExp += P.supportBase;
        break;
      case "pushed":
        if (caster && e.damage > 0) lastHit.set(e.unit, caster);
        break;
      case "defeated": {
        const by = lastHit.get(e.unit);
        if (!by || side(by) === side(e.unit)) break;
        const gap = level(e.unit) - level(by);
        const bonus = clamp(P.defeatBase + P.defeatPerLevel * gap, P.defeatMin, P.defeatMax);
        add(by, bonus + (commanders.has(e.unit) ? P.commanderBonus : 0));
        ledger.defeated[by] = (ledger.defeated[by] ?? 0) + 1;
        break;
      }
      case "moved":
      case "waited":
        endCast();
        break;
      default:
        break;
    }
  }
  endCast();
}

/** What reaching `level` in this class adds to each stat. */
export function growthAt(classId: string, level: number): StatGains {
  const rates = GROWTH[classId];
  if (!rates) return {};
  const out: StatGains = {};
  STAT_KEYS.forEach((k, i) => {
    const r = rates[i] ?? 0;
    const gain = Math.floor((level * r) / 1000) - Math.floor(((level - 1) * r) / 1000);
    if (gain > 0) out[k] = gain;
  });
  return out;
}

export interface LevelUp {
  level: number;
  gains: StatGains;
}

/** Adds experience to an officer (in place); returns each level gained with its stat gains. */
export function gainExp(o: Officer, amount: number): LevelUp[] {
  const ups: LevelUp[] = [];
  if (o.level >= P.levelCap) return ups;
  o.exp += amount;
  while (o.exp >= P.expPerLevel && o.level < P.levelCap) {
    o.exp -= P.expPerLevel;
    o.level += 1;
    const gains = growthAt(o.classId, o.level);
    const applied: StatGains = {};
    for (const k of STAT_KEYS) {
      const g = gains[k] ?? 0;
      const next = Math.min(P.statCap, o.stats[k] + g);
      if (next > o.stats[k]) applied[k] = next - o.stats[k];
      o.stats = { ...o.stats, [k]: next };
    }
    ups.push({ level: o.level, gains: applied });
  }
  if (o.level >= P.levelCap) o.exp = 0;
  return ups;
}

/** The class this officer could promote to now, if any (ADR 0048). */
export function promotionFor(o: Officer): string | null {
  const to = PROMOTIONS[o.classId];
  return to !== undefined && o.level >= P.promoteLevel ? to : null;
}
