/**
 * Core data model of the battle rules (docs/remake/RULES.md). Everything here is
 * plain, serializable data with integer numbers only, so `hashState` can hash it
 * and the referee can store it (ADR 0004, ADR 0006).
 */
import type { PrngState } from "./prng.ts";

// ---------------------------------------------------------------- content (ruleset)

/** The twelve class families (ADR 0021). */
export type FamilyId =
  | "infantry"
  | "spearman"
  | "archer"
  | "crossbow"
  | "cavalry"
  | "horseArcher"
  | "catapult"
  | "martialArtist"
  | "bandit"
  | "strategist"
  | "taoist"
  | "dancer";

/** Counter triangle groups: infantry ▶ cavalry ▶ archer ▶ infantry (ADR 0021). */
export type CounterGroup = "infantry" | "cavalry" | "archer";

/** Game modes; each may override tuning (ADR 0029). */
export type ModeId = "sandbox" | "story" | "arena" | "world" | "raid";

/** About twelve terrain types (ADR 0023). */
export type TerrainId =
  | "plain"
  | "grass"
  | "forest"
  | "hills"
  | "mountain"
  | "ford"
  | "water"
  | "bridge"
  | "village"
  | "fort"
  | "wall"
  | "camp";

export type WeatherKind = "sunny" | "cloudy" | "rain" | "wind";

/** Relative tile offset, used for attack ranges and stratagem areas. */
export interface Offset {
  readonly dx: number;
  readonly dy: number;
}

export interface Cell {
  readonly x: number;
  readonly y: number;
}

/** Officer stats, 1–100 (ADR 0020): 무력, 통솔, 지력, 민첩, 운. */
export interface Stats {
  readonly martial: number;
  readonly command: number;
  readonly intellect: number;
  readonly agility: number;
  readonly fortune: number;
}

export interface ClassDef {
  readonly id: string;
  readonly name: string;
  readonly family: FamilyId;
  readonly tier: 1 | 2 | 3;
  readonly hp: number;
  readonly mp: number;
  readonly move: number;
  readonly atk: number;
  readonly def: number;
  /** Tiles this class can attack, relative to its own tile. */
  readonly range: readonly Offset[];
  /** 민첩 gap needed for a double attack (ADR 0025): 20, or 10 for martial artists. */
  readonly doubleAttackGap: number;
  /** False for catapults: their attacks never trigger counters (ADR 0025). */
  readonly provokesCounter: boolean;
  /** Skirmishers ignore the first zone-of-control tile (ADR 0026). */
  readonly skirmisher: boolean;
  readonly stratagems: readonly string[];
}

export interface TerrainDef {
  readonly id: TerrainId;
  readonly name: string;
  /** Evasion‰ granted to any unit standing here (ADR 0023). */
  readonly cover: number;
  /** ‰ of max HP healed at the start of each round (0 = none). */
  readonly heal: number;
}

/** Per family × terrain: movement cost (null = impassable) and effect‰ on ATK/DEF. */
export interface Affinity {
  readonly move: number | null;
  readonly effect: number;
}

export type StratagemSchool = "fire" | "water" | "earth" | "wind" | "support" | "disruption";
export type StatusKind = "confusion";

export interface StratagemDef {
  readonly id: string;
  readonly name: string;
  readonly school: StratagemSchool;
  readonly mp: number;
  /** Target cells the caster may choose, relative to the caster's tile. */
  readonly range: readonly Offset[];
  /** Cells affected, relative to the chosen target cell. */
  readonly area: readonly Offset[];
  readonly effect: "damage" | "heal" | "status";
  readonly targets: "enemies" | "allies";
  readonly power: number;
  readonly status?: StatusKind;
  readonly statusRounds?: number;
  /** ‰ multipliers by the target tile's terrain and by weather (default 1000). */
  readonly terrain?: Partial<Record<TerrainId, number>>;
  readonly weather?: Partial<Record<WeatherKind, number>>;
  /** What a 천운 windfall does, and what a 불운 mishap does (ADR 0024). */
  readonly windfall: "power" | "refund";
  readonly mishap: "fizzle" | "backfire";
}

/** Starting tuning numbers from RULES.md; all integers, probabilities in ‰. */
export interface Tuning {
  readonly hitBase: number;
  readonly hitPerAgility: number;
  readonly hitMin: number;
  readonly hitMax: number;
  readonly critBase: number;
  readonly critPerFortune: number;
  readonly critMin: number;
  readonly critMax: number;
  readonly critMultiplier: number;
  readonly minDamage: number;
  readonly counterMultiplier: number;
  readonly escapeBase: number;
  readonly escapePerFortune: number;
  readonly escapeMax: number;
  readonly killedShare: number;
  readonly statusBase: number;
  readonly statusPerIntellect: number;
  readonly statusPerFortune: number;
  readonly statusMin: number;
  readonly statusMax: number;
  readonly twist: number;
  readonly windfallBase: number;
  readonly windfallPerFortune: number;
  readonly windfallMin: number;
  readonly windfallMax: number;
  readonly windfallMultiplier: number;
  /** Max HP = class HP + 통솔 + level × hpPerLevel (ADR 0022). */
  readonly hpPerLevel: number;
  /** Damage floor = max(minDamage, level × damageFloorPerLevel‰) (ADR 0029). */
  readonly damageFloorPerLevel: number;
  /** Final damage multiplier‰ of the "mode" pipeline stage (ADR 0029). */
  readonly damageMultiplier: number;
  /** Max MP = class MP + 지력 × mpPerIntellect‰ (ADR 0020). */
  readonly mpPerIntellect: number;
}

export interface Ruleset {
  readonly classes: Readonly<Record<string, ClassDef>>;
  readonly terrains: Readonly<Record<TerrainId, TerrainDef>>;
  readonly affinity: Readonly<Record<string, Readonly<Record<TerrainId, Affinity>>>>;
  readonly stratagems: Readonly<Record<string, StratagemDef>>;
  /** Attacker family → defender family → damage‰; missing pairs are 1000 (ADR 0029). */
  readonly relations: Readonly<Record<string, Readonly<Record<string, number>>>>;
  readonly tuning: Tuning;
  /** Per-mode tuning overrides, merged over `tuning` by `forMode()`. */
  readonly modes?: Readonly<Partial<Record<ModeId, Partial<Tuning>>>>;
}

// ---------------------------------------------------------------- battle state

export type Fate = "escaped" | "captured" | "killed";

export interface Status {
  kind: StatusKind;
  rounds: number;
}

export interface Unit {
  id: string;
  side: number;
  name: string;
  classId: string;
  level: number;
  stats: Stats;
  /** Equipment bonuses (weapon to ATK, armour to DEF). */
  atkBonus: number;
  defBonus: number;
  hp: number;
  maxHp: number;
  mp: number;
  maxMp: number;
  x: number;
  y: number;
  acted: boolean;
  status: Status | null;
  fate: Fate | null;
}

export type TurnPolicyId = "sidePhases" | "alternating";

export interface BattleMap {
  width: number;
  height: number;
  /** Row-major terrain ids, row 0 at the top. */
  tiles: TerrainId[];
}

export interface BattleState {
  version: 1;
  mode: ModeId;
  map: BattleMap;
  weather: WeatherKind;
  policy: TurnPolicyId;
  sides: number;
  round: number;
  roundLimit: number;
  /** Side whose turn it is (phase owner, or the side to activate next). */
  active: number;
  /** Units sorted by id. Defeated units stay, with hp 0 and a fate. */
  units: Unit[];
  prng: PrngState;
  /** Set when the battle is over: the winning side, or -1 for a draw. */
  winner: number | null;
}

// ---------------------------------------------------------------- commands and events

export type Action =
  | { kind: "attack"; target: string }
  | { kind: "stratagem"; id: string; at: Cell }
  | { kind: "wait" };

/** One command = one unit's whole action: move to `to`, then act. */
export type Command =
  | { kind: "act"; unit: string; to: Cell; action: Action }
  | { kind: "endPhase" };

export type StrikeRole = "first" | "counter" | "second";

export type BattleEvent =
  | { kind: "moved"; unit: string; from: Cell; to: Cell }
  | {
      kind: "strike";
      attacker: string;
      defender: string;
      role: StrikeRole;
      hit: boolean;
      crit: boolean;
      damage: number;
      hpAfter: number;
    }
  | { kind: "defeated"; unit: string; fate: Fate }
  | { kind: "stratagem"; caster: string; id: string; at: Cell }
  | {
      kind: "stratagemEffect";
      unit: string;
      damage: number;
      heal: number;
      status: StatusKind | null;
      resisted: boolean;
      hpAfter: number;
    }
  | { kind: "twist"; caster: string; outcome: "windfall" | "mishap"; effect: string }
  | { kind: "waited"; unit: string }
  | { kind: "terrainHeal"; unit: string; amount: number; hpAfter: number }
  | { kind: "statusEnded"; unit: string; status: StatusKind }
  | { kind: "turn"; side: number; round: number }
  | { kind: "battleEnd"; winner: number };
