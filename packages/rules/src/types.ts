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

/** What a condition forbids while any of its stacks remains. */
export type ConditionBlock = "act" | "move" | "stratagem";

/**
 * A buff or debuff (ADR 0035). Its modifiers are per stack; each further stack of
 * the same condition counts `stackDiscount`‰ of the one before. Every stack has
 * its own duration, rolled when it lands.
 */
export interface ConditionDef {
  readonly id: string;
  readonly name: string;
  readonly kind: "buff" | "debuff";
  /** ‰ added to the ATK and DEF multipliers (signed). */
  readonly atk?: number;
  readonly def?: number;
  /** ‰ added to the bearer's hit chance when it strikes (signed). */
  readonly hit?: number;
  /** Tiles added to the bearer's move (signed). */
  readonly move?: number;
  /** ‰ of max HP lost at the end of each of the bearer's turns; never below 1 HP. */
  readonly dot?: number;
  readonly blocks?: ConditionBlock;
}

export interface StratagemDef {
  readonly id: string;
  readonly name: string;
  readonly school: StratagemSchool;
  readonly mp: number;
  /** Target cells the caster may choose, relative to the caster's tile. */
  readonly range: readonly Offset[];
  /** Cells affected, relative to the chosen target cell. */
  readonly area: readonly Offset[];
  /** Main effect; "condition" casts only apply `condition`. */
  readonly effect: "damage" | "heal" | "condition";
  readonly targets: "enemies" | "allies";
  readonly power: number;
  /** Condition applied to each target (a rider on damage or heal casts). Debuffs on enemies roll to land. */
  readonly condition?: string;
  /** Duration range in the bearer's turns, rolled per stack when it lands (ADR 0035). */
  readonly turns?: readonly [number, number];
  /** Removes every debuff stack from each target. */
  readonly cleanse?: boolean;
  /** ‰ multipliers by the target tile's terrain and by weather (default 1000). */
  readonly terrain?: Partial<Record<TerrainId, number>>;
  readonly weather?: Partial<Record<WeatherKind, number>>;
  /** What a 천운 windfall does, and what a 불운 mishap does (ADR 0024). */
  readonly windfall: "power" | "refund";
  readonly mishap: "fizzle" | "backfire";
  /** Push each target this many tiles away from the caster (ADR 0030). */
  readonly push?: number;
  /** Sets grass and forest in the area (and next to it) burning (ADR 0030). */
  readonly ignites?: boolean;
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
  /** A landed strike with relation‰ ≥ this breaks the target (ADR 0030). */
  readonly breakThreshold: number;
  /** Collision damage when a pushed unit hits an obstacle / another unit. */
  readonly pushWallDamage: number;
  readonly pushUnitDamage: number;
  /** Damage at round start to a unit on a burning tile, and burn duration. */
  readonly burnDamage: number;
  readonly burnRounds: number;
  /** Max MP = class MP + 지력 × mpPerIntellect‰ (ADR 0020). */
  readonly mpPerIntellect: number;
  /** Each further stack of one condition counts this ‰ of the one before (ADR 0035). */
  readonly stackDiscount: number;
  /** Most stacks of one condition a unit can carry; a new one replaces the shortest. */
  readonly maxStacks: number;
  /** Lowest ATK/DEF multiplier‰ that conditions can bring a unit to. */
  readonly modifierFloor: number;
}

export interface Ruleset {
  readonly classes: Readonly<Record<string, ClassDef>>;
  readonly terrains: Readonly<Record<TerrainId, TerrainDef>>;
  readonly affinity: Readonly<Record<string, Readonly<Record<TerrainId, Affinity>>>>;
  readonly stratagems: Readonly<Record<string, StratagemDef>>;
  readonly conditions: Readonly<Record<string, ConditionDef>>;
  /** Attacker family → defender family → damage‰; missing pairs are 1000 (ADR 0029). */
  readonly relations: Readonly<Record<string, Readonly<Record<string, number>>>>;
  readonly tuning: Tuning;
  /** Per-mode tuning overrides, merged over `tuning` by `forMode()`. */
  readonly modes?: Readonly<Partial<Record<ModeId, Partial<Tuning>>>>;
}

// ---------------------------------------------------------------- battle state

export type Fate = "escaped" | "captured" | "killed";

/** One stack of a condition on a unit. */
export interface ConditionStack {
  id: string;
  /** Turns left; the stack ends when this reaches 0. */
  turns: number;
  /** Landed during the bearer's own turn: it starts counting from the next one. */
  fresh: boolean;
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
  /** Condition stacks in the order they landed (ADR 0035). */
  conditions: ConditionStack[];
  /** Broken units can't counter until they act again (ADR 0030). */
  broken: boolean;
  fate: Fate | null;
}

export interface BurningTile {
  x: number;
  y: number;
  rounds: number;
}

export type TurnPolicyId = "sidePhases" | "alternating";

export interface BattleMap {
  width: number;
  height: number;
  /** Row-major terrain ids, row 0 at the top. */
  tiles: TerrainId[];
}

/**
 * A way to win before the other sides are routed (ADR 0047). Story battles use
 * them for "defeat the commander", "hold out" and "reach the depot".
 */
export type GoalCondition =
  /** Every listed unit has been defeated. */
  | { kind: "defeat"; units: string[] }
  /** Round `round` begins: the side held out until then. */
  | { kind: "hold"; round: number }
  /** A living unit of `side` (one of `units`, if listed) stands on one of `cells`. */
  | { kind: "reach"; side: number; cells: Cell[]; units?: string[] };

/** `side` wins as soon as `when` holds. Goals are checked in list order. */
export interface Goal {
  side: number;
  when: GoalCondition;
}

/** A unit that joins the battle at the start of a round (ADR 0047). */
export interface Arrival {
  round: number;
  unit: Unit;
}

/** One tile turned into another terrain by a shift (ADR 0062). */
export interface TileChange {
  x: number;
  y: number;
  terrain: TerrainId;
}

/** What sets a shift off. */
export type ShiftTrigger =
  /** Round `round` begins. */
  | { kind: "round"; round: number }
  /** A living unit of `side` (one of `units`, if listed) stands on one of `cells` (the sluice at Xiapi). */
  | { kind: "reach"; side: number; cells: Cell[]; units?: string[] };

/**
 * A change to the map during a battle (ADR 0062): a flood, an opened gate.
 * Units left on ground they can no longer stand on are swept to the nearest
 * tile they can, losing `damage`‰ of their max HP (never the last point).
 */
export interface Shift {
  id: string;
  when: ShiftTrigger;
  tiles: TileChange[];
  damage: number;
}

export interface BattleState {
  /**
   * State format; 5 since allies (ADR 0066), 4 since map shifts (ADR 0062),
   * 3 since goals and arrivals (ADR 0047), 2 since conditions (ADR 0035).
   */
  version: 5;
  mode: ModeId;
  map: BattleMap;
  weather: WeatherKind;
  policy: TurnPolicyId;
  sides: number;
  /**
   * Each side's team (ADR 0066): sides on one team are allies. They never
   * attack each other, pass through each other and help each other. A team
   * stays in the battle while its leader, its lowest-numbered side, has
   * living units.
   */
  teams: number[];
  /** The order sides take their turns in each round (ours, then allies, then the enemy). */
  order: number[];
  round: number;
  roundLimit: number;
  /** Side whose turn it is (phase owner, or the side to activate next). */
  active: number;
  /** Units sorted by id. Defeated units stay, with hp 0 and a fate. */
  units: Unit[];
  /** Tiles on fire, sorted by y then x. */
  burning: BurningTile[];
  /** Ways to win besides routing every enemy, checked in order (ADR 0047). */
  goals: Goal[];
  /** Units still to arrive, by round then id. */
  reserve: Arrival[];
  /** Map changes still to happen, in setup order; a shift leaves the list once it fires. */
  shifts: Shift[];
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
      /** This strike broke the defender (ADR 0030). */
      broke: boolean;
    }
  | { kind: "defeated"; unit: string; fate: Fate }
  | { kind: "stratagem"; caster: string; id: string; at: Cell }
  | {
      kind: "stratagemEffect";
      unit: string;
      damage: number;
      heal: number;
      /** Condition that landed, and its rolled duration. */
      condition: string | null;
      turns: number;
      resisted: boolean;
      hpAfter: number;
    }
  | { kind: "cleansed"; unit: string; removed: string[] }
  | { kind: "conditionDamage"; unit: string; damage: number; hpAfter: number }
  | { kind: "twist"; caster: string; outcome: "windfall" | "mishap"; effect: string }
  | {
      kind: "pushed";
      unit: string;
      from: Cell;
      to: Cell;
      collided: "wall" | "unit" | null;
      damage: number;
      hpAfter: number;
    }
  | { kind: "ignited"; at: Cell }
  | { kind: "burned"; unit: string; damage: number; hpAfter: number }
  | { kind: "waited"; unit: string }
  | { kind: "terrainHeal"; unit: string; amount: number; hpAfter: number }
  | { kind: "conditionEnded"; unit: string; condition: string }
  | { kind: "turn"; side: number; round: number }
  /** A reserve unit joined the battle at the start of a round. */
  | { kind: "arrived"; unit: string; at: Cell }
  /** A shift changed the map (ADR 0062); `by` is the unit that set it off, if one did. */
  | { kind: "shifted"; shift: string; tiles: TileChange[]; by: string | null }
  /** A shift left a unit on ground it can't stand on and carried it to `to`. */
  | { kind: "swept"; unit: string; from: Cell; to: Cell; damage: number; hpAfter: number }
  /** `goal` is the index of the goal that decided the battle, if one did. */
  | { kind: "battleEnd"; winner: number; goal?: number };

// ---------------------------------------------------------------- gambits (ADR 0030)

/** When a gambit applies, evaluated for the unit about to act. */
export type GambitCondition =
  | { kind: "always" }
  | { kind: "selfHpBelow"; permille: number }
  | { kind: "allyHpBelow"; permille: number }
  | { kind: "enemyInReach"; family?: FamilyId }
  | { kind: "canKill" };

/** What the unit does when its gambit applies. */
export type GambitBehavior =
  | { kind: "attackBest" }
  | { kind: "attackLowestHp" }
  | { kind: "attackFamily"; family: FamilyId }
  | { kind: "healAlly" }
  | { kind: "retreatToHeal" }
  | { kind: "advance" }
  | { kind: "hold" }
  /** Stay on this tile; strike or cast only from it (a commander in his fort, ADR 0047). */
  | { kind: "guard" };

/** One "condition → behaviour" rule; a unit's gambits are tried in order. */
export interface Gambit {
  readonly when: GambitCondition;
  readonly behavior: GambitBehavior;
}
