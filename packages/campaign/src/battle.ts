/**
 * From a campaign to a battle and back (ADR 0049): the sortie picks roster
 * officers for the battle's slots, `storySetup` builds the `BattleSetup`, and
 * `settle` pays out experience, gold and items once the battle is won.
 */
import {
  holds,
  ITEMS,
  P0_RULES,
  parseMap,
  type ScenarioUnit,
  type StoryBattleDef,
  type StoryUnit,
  scenarioOrders,
  unitSetup,
} from "@tk/content";
import type {
  BattleSetup,
  BattleState,
  Cell,
  Gambit,
  Goal,
  Ruleset,
  Shift,
  Stats,
  UnitSetup,
} from "@tk/rules";
import { currentStep, stepOn } from "./flow.ts";
import { battleProfile } from "./gear.ts";
import { gainExp, type Ledger, type LevelUp, promotionFor, type StatGains } from "./progression.ts";
import { PROGRESSION } from "./tuning.ts";
import type { CampaignState } from "./types.ts";

export interface Placement {
  officer: string;
  at: Cell;
}

const same = (a: Cell, b: Cell) => a.x === b.x && a.y === b.y;

/** The battle being played, or null when the current step is not a battle. */
export function currentBattle(state: CampaignState): StoryBattleDef | null {
  const step = currentStep(state);
  return step?.kind === "battle" ? step.battle : null;
}

/** The default sortie: required officers first, then the roster in order, one per slot. */
export function defaultSortie(state: CampaignState, b: StoryBattleDef): Placement[] {
  const ids = [
    ...b.required.filter((id) => state.roster.some((o) => o.id === id)),
    ...state.roster.map((o) => o.id).filter((id) => !b.required.includes(id)),
  ].slice(0, Math.min(b.max, b.slots.length));
  return ids.map((officer, i) => ({ officer, at: b.slots[i] as Cell }));
}

/** Why this sortie can't go out, or null when it can. */
export function checkSortie(
  state: CampaignState,
  b: StoryBattleDef,
  sortie: readonly Placement[],
): string | null {
  if (sortie.length === 0) return "출진할 장수를 고르세요";
  if (sortie.length > b.max) return `최대 ${b.max}명까지 출진할 수 있습니다`;
  const seen = new Set<string>();
  for (const p of sortie) {
    if (!state.roster.some((o) => o.id === p.officer)) return "명단에 없는 장수입니다";
    if (seen.has(p.officer)) return "같은 장수를 두 번 고를 수 없습니다";
    seen.add(p.officer);
    if (!b.slots.some((c) => same(c, p.at))) return "배치할 수 없는 칸입니다";
    if (sortie.some((q) => q !== p && same(q.at, p.at))) return "한 칸에 한 명만 설 수 있습니다";
  }
  for (const id of b.required) if (!seen.has(id)) return "꼭 출진해야 하는 장수가 빠졌습니다";
  return null;
}

type Flags = Readonly<Record<string, string>>;

/** The guests and enemies this battle has with these flags, without their conditions. */
export function battleUnits(b: StoryBattleDef, flags: Flags): ScenarioUnit[] {
  return [...b.guests, ...b.enemies]
    .filter((u) => holds(u.if, flags))
    .map(({ if: _, ...u }: StoryUnit) => u);
}

/** The goals and map shifts this battle has with these flags. */
export function battleRules(b: StoryBattleDef, flags: Flags): { goals: Goal[]; shifts: Shift[] } {
  return {
    goals: b.goals
      .filter((g) => holds(g.if, flags))
      .map(({ if: _, ...g }) => structuredClone(g) as Goal),
    shifts: (b.shifts ?? [])
      .filter((sh) => holds(sh.if, flags))
      .map(({ if: _, ...sh }) => structuredClone(sh) as Shift),
  };
}

/** The battle setup for a sortie: roster officers with their gear, the guests and the enemy. */
export function storySetup(
  state: CampaignState,
  b: StoryBattleDef,
  sortie: readonly Placement[],
  seed: string,
): BattleSetup {
  const error = checkSortie(state, b, sortie);
  if (error) throw new Error(error);
  const mine: UnitSetup[] = sortie.map((p) => {
    const o = state.roster.find((r) => r.id === p.officer);
    if (!o) throw new Error(`unknown officer ${p.officer}`);
    const prof = battleProfile(o);
    const setup = unitSetup(
      { officer: o.id, classId: o.classId, level: o.level, side: 0, x: p.at.x, y: p.at.y },
      `battle ${b.id}`,
    );
    return { ...setup, stats: prof.stats, atkBonus: prof.atkBonus, defBonus: prof.defBonus };
  });
  const others = battleUnits(b, state.flags).map((u) => unitSetup(u, `battle ${b.id}`));
  const { goals, shifts } = battleRules(b, state.flags);
  return {
    mode: "story",
    map: parseMap(b.map),
    weather: b.weather,
    policy: "sidePhases",
    roundLimit: b.roundLimit,
    seed,
    units: [...mine, ...others],
    goals,
    shifts,
    burning: (b.burning ?? []).map((t) => ({ ...t })),
  };
}

/** AI orders for the enemy and the guests. */
export function storyOrders(
  b: StoryBattleDef,
  flags: Flags = {},
): Record<string, readonly Gambit[]> {
  return scenarioOrders({ units: battleUnits(b, flags) });
}

export interface OfficerReport {
  id: string;
  exp: number;
  levelBefore: number;
  level: number;
  ups: LevelUp[];
  /** Total stat gains over every level-up. */
  gains: StatGains;
  /** The class it may now promote to. */
  promotion: string | null;
}

export interface BattleReport {
  /** The battle's id. */
  battle: string;
  won: boolean;
  gold: number;
  items: string[];
  officers: OfficerReport[];
  /** Fates the battle decided (ADR 0061), flag → value. */
  fates: Record<string, string>;
}

/**
 * Settles a finished battle. A win pays experience (plus the victory bonus to
 * officers still standing), gold and items, and moves past the battle step; a
 * loss changes nothing, so the battle can be retried.
 */
export function settle(
  state: CampaignState,
  b: StoryBattleDef,
  sortie: readonly Placement[],
  final: BattleState,
  ledger: Ledger,
): { state: CampaignState; report: BattleReport } {
  const won = final.winner === 0;
  if (!won)
    return { state, report: { battle: b.id, won, gold: 0, items: [], officers: [], fates: {} } };
  const next = structuredClone(state);
  const officers: OfficerReport[] = [];
  for (const p of sortie) {
    const o = next.roster.find((r) => r.id === p.officer);
    if (!o) continue;
    const unit = final.units.find((u) => u.id === o.id);
    const standing = unit !== undefined && unit.hp > 0;
    const exp = (ledger.exp[o.id] ?? 0) + (standing ? PROGRESSION.victoryBonus : 0);
    const levelBefore = o.level;
    const ups = gainExp(o, exp);
    const gains: StatGains = {};
    for (const u of ups)
      for (const [k, v] of Object.entries(u.gains) as [keyof Stats, number][])
        gains[k] = (gains[k] ?? 0) + v;
    o.battles += 1;
    o.defeated += ledger.defeated[o.id] ?? 0;
    officers.push({
      id: o.id,
      exp,
      levelBefore,
      level: o.level,
      ups,
      gains,
      promotion: promotionFor(o),
    });
  }
  next.gold += b.rewards.gold;
  for (const id of b.rewards.items)
    if (ITEMS[id]) next.inventory[id] = (next.inventory[id] ?? 0) + 1;
  if (!next.cleared.includes(b.id)) next.cleared.push(b.id);
  const fates: Record<string, string> = {};
  for (const f of b.fates ?? []) {
    const u = final.units.find((v) => v.id === f.unit);
    fates[f.flag] = u !== undefined && u.hp > 0 ? f.standing : f.fallen;
  }
  Object.assign(next.flags, fates);
  return {
    state: stepOn(next),
    report: {
      battle: b.id,
      won,
      gold: b.rewards.gold,
      items: [...b.rewards.items],
      officers,
      fates,
    },
  };
}

/** The ruleset story battles run on. */
export const STORY_RULES: Ruleset = P0_RULES;
