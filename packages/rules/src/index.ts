export { chooseCommand, dangerTiles, scoreCommand, type ThreatMap, threatMap } from "./ai.ts";
export {
  type ApplyResult,
  apply,
  type BattleSetup,
  createBattle,
  legalCommands,
  readyUnits,
  type UnitSetup,
} from "./battle.ts";
export {
  affinityAt,
  classOf,
  inRange,
  isAlive,
  isZoc,
  terrainAt,
  unitAt,
  unitById,
} from "./board.ts";
export {
  type AttackPreview,
  attackPower,
  breaksOnHit,
  critChance,
  type DamageStage,
  type DamageStep,
  damageFloor,
  damageSteps,
  defensePower,
  hitChance,
  previewAttack,
  relationFactor,
  strikeDamage,
} from "./combat.ts";
export {
  addCondition,
  conditionDef,
  conditionModifier,
  conditionMultiplier,
  hasBlock,
  stacksOf,
  stackWeight,
} from "./conditions.ts";
export { canonical, hashState } from "./hash.ts";
export { canReach, type Reach, reachable } from "./move.ts";
export { Prng, type PrngState, seedFromString } from "./prng.ts";
export { replay, runBattle, type SimResult } from "./sim.ts";
export {
  canCast,
  castStratagem,
  previewStratagem,
  type StratagemPreview,
  statusChance,
  stratagemDamage,
  stratagemDef,
  stratagemHeal,
  stratagemTargets,
} from "./stratagem.ts";
export { DEFAULT_TUNING, forMode, triangleRelations } from "./tuning.ts";
export type * from "./types.ts";
