/**
 * Stratagems (RULES.md §5, ADR 0024): MP cost, elements scaled by terrain and
 * weather, conditions (ADR 0035), and the rare fortune twist.
 * Prng draw order: per target, by unit id: the landing roll (debuffs on enemies
 * only), then the duration roll if it landed; then twist, direction; then fate
 * rolls for anyone defeated.
 */
import {
  affinityAt,
  classOf,
  friendly,
  hostile,
  inBounds,
  inRange,
  isAlive,
  NEIGHBOURS,
  terrainAt,
  unitAt,
} from "./board.ts";
import { defeat, relationFactor } from "./combat.ts";
import { addCondition, conditionDef, hasBlock, removeDebuffs, rollTurns } from "./conditions.ts";
import { clamp, half, permille } from "./math.ts";
import type { Prng } from "./prng.ts";
import { forMode } from "./tuning.ts";
import type { BattleEvent, BattleState, Cell, Ruleset, StratagemDef, Unit } from "./types.ts";

const NEUTRAL_FORTUNE = 50;

export function stratagemDef(rules: Ruleset, id: string): StratagemDef {
  const s = rules.stratagems[id];
  if (!s) throw new Error(`unknown stratagem ${id}`);
  return s;
}

/** Living units in the area around `at` that this stratagem affects, sorted by id. */
export function stratagemTargets(
  state: BattleState,
  caster: Unit,
  def: StratagemDef,
  at: Cell,
): Unit[] {
  const out: Unit[] = [];
  for (const o of def.area) {
    const x = at.x + o.dx;
    const y = at.y + o.dy;
    if (!inBounds(state, x, y)) continue;
    const u = unitAt(state, x, y);
    if (u === undefined) continue;
    const ally = friendly(state, u.side, caster.side);
    if ((def.targets === "allies") === ally) out.push(u);
  }
  return out.sort((a, b) => (a.id < b.id ? -1 : a.id > b.id ? 1 : 0));
}

/** Can `caster`, standing on `from`, cast `def` at `at`? */
export function canCast(
  rules: Ruleset,
  state: BattleState,
  caster: Unit,
  def: StratagemDef,
  from: Cell,
  at: Cell,
): boolean {
  if (!classOf(rules, caster).stratagems.includes(def.id)) return false;
  if (caster.mp < def.mp) return false;
  if (hasBlock(rules, caster, "act") || hasBlock(rules, caster, "stratagem")) return false;
  if (!inBounds(state, at.x, at.y) || !inRange(def.range, from, at)) return false;
  // Evaluate targets as if the caster already stood on `from`.
  const moved = { ...caster, x: from.x, y: from.y };
  const shadow = { ...state, units: state.units.map((u) => (u.id === caster.id ? moved : u)) };
  return stratagemTargets(shadow, moved, def, at).length > 0;
}

export function stratagemDamage(
  rules: Ruleset,
  state: BattleState,
  def: StratagemDef,
  caster: Unit,
  target: Unit,
): number {
  const base = Math.max(1, def.power + caster.stats.intellect - half(target.stats.intellect));
  const byTerrain = permille(base, def.terrain?.[terrainAt(state, target.x, target.y)] ?? 1000);
  const byWeather = permille(byTerrain, def.weather?.[state.weather] ?? 1000);
  // Works (ADR 0069) take what the caster's family does to them, stratagems included,
  // so stone-throwers stay the best gate-breakers.
  if (!target.structure) return byWeather;
  return Math.max(
    1,
    permille(byWeather, relationFactor(rules, classOf(rules, caster), classOf(rules, target))),
  );
}

/** Must a condition from this cast roll to land on `target`? (debuffs on enemies only) */
function resistible(
  rules: Ruleset,
  state: BattleState,
  def: StratagemDef,
  caster: Unit,
  target: Unit,
): boolean {
  if (def.condition === undefined) return false;
  return (
    conditionDef(rules, def.condition).kind === "debuff" && hostile(state, target.side, caster.side)
  );
}

export function stratagemHeal(def: StratagemDef, caster: Unit): number {
  return def.power + half(caster.stats.intellect);
}

export function statusChance(rules: Ruleset, caster: Unit, target: Unit): number {
  const t = rules.tuning;
  return clamp(
    t.statusBase +
      t.statusPerIntellect * (caster.stats.intellect - target.stats.intellect) -
      t.statusPerFortune * target.stats.fortune,
    t.statusMin,
    t.statusMax,
  );
}

export interface StratagemPreview {
  /** Each affected unit: damage or heal before a twist, and the ‰ that the condition lands. */
  targets: { unit: string; damage: number; heal: number; chance: number }[];
  /** Condition applied, and its duration range in the bearer's turns. */
  condition: string | null;
  turns: readonly [number, number] | null;
  cleanse: boolean;
  /** ‰ that a fortune twist happens, and ‰ that a twist is a windfall (ADR 0024). */
  twist: number;
  windfall: number;
  push: number;
  ignites: boolean;
}

/** Forecast of a cast by `caster` (already on its casting tile) at `at`, for the UI. */
export function previewStratagem(
  baseRules: Ruleset,
  state: BattleState,
  caster: Unit,
  def: StratagemDef,
  at: Cell,
): StratagemPreview {
  const rules = forMode(baseRules, state.mode);
  const t = rules.tuning;
  const main = unitAt(state, at.x, at.y);
  const heal = (u: Unit) => Math.min(stratagemHeal(def, caster), u.maxHp - u.hp);
  return {
    targets: stratagemTargets(state, caster, def, at).map((u) => ({
      unit: u.id,
      damage: def.effect === "damage" ? stratagemDamage(rules, state, def, caster, u) : 0,
      heal: def.effect === "heal" ? heal(u) : 0,
      chance: resistible(rules, state, def, caster, u) ? statusChance(rules, caster, u) : 1000,
    })),
    condition: def.condition ?? null,
    turns: def.condition === undefined ? null : (def.turns ?? [1, 1]),
    cleanse: def.cleanse === true,
    twist: t.twist,
    windfall: clamp(
      t.windfallBase +
        t.windfallPerFortune * (caster.stats.fortune - (main?.stats.fortune ?? NEUTRAL_FORTUNE)),
      t.windfallMin,
      t.windfallMax,
    ),
    push: def.push ?? 0,
    ignites: def.ignites === true,
  };
}

interface Pending {
  unit: Unit;
  damage: number;
  heal: number;
  landed: boolean;
  turns: number;
}

export function castStratagem(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  caster: Unit,
  def: StratagemDef,
  at: Cell,
  events: BattleEvent[],
): void {
  const t = rules.tuning;
  caster.mp -= def.mp;
  events.push({ kind: "stratagem", caster: caster.id, id: def.id, at });

  const pending: Pending[] = stratagemTargets(state, caster, def, at).map((unit) => {
    const landed =
      def.condition !== undefined &&
      (!resistible(rules, state, def, caster, unit) ||
        prng.chance(statusChance(rules, caster, unit)));
    return {
      unit,
      damage: def.effect === "damage" ? stratagemDamage(rules, state, def, caster, unit) : 0,
      heal: def.effect === "heal" ? stratagemHeal(def, caster) : 0,
      landed,
      turns: landed ? rollTurns(prng, def.turns ?? [1, 1]) : 0,
    };
  });

  // Fortune twist (ADR 0024): rare, tilted by the caster's and main target's 운.
  let fizzle = false;
  let backfire = 0;
  let extraRounds = 0;
  if (prng.chance(t.twist)) {
    const main = unitAt(state, at.x, at.y);
    const targetFortune = main ? main.stats.fortune : NEUTRAL_FORTUNE;
    const windfall = prng.chance(
      clamp(
        t.windfallBase + t.windfallPerFortune * (caster.stats.fortune - targetFortune),
        t.windfallMin,
        t.windfallMax,
      ),
    );
    let effect: string;
    if (windfall && def.windfall === "power") {
      for (const p of pending) {
        p.damage = permille(p.damage, t.windfallMultiplier);
        p.heal = permille(p.heal, t.windfallMultiplier);
      }
      extraRounds = 1;
      effect = "power";
    } else if (windfall) {
      caster.mp = Math.min(caster.maxMp, caster.mp + def.mp);
      effect = "refund";
    } else if (def.mishap === "backfire" && def.effect === "damage" && pending.length > 0) {
      backfire = half(Math.max(...pending.map((p) => p.damage)));
      fizzle = true;
      effect = "backfire";
    } else {
      fizzle = true;
      effect = "fizzle";
    }
    events.push({
      kind: "twist",
      caster: caster.id,
      outcome: windfall ? "windfall" : "mishap",
      effect,
    });
  }

  if (!fizzle) {
    for (const p of pending) {
      const u = p.unit;
      if (!isAlive(u)) continue;
      let heal = 0;
      if (p.damage > 0) u.hp = Math.max(0, u.hp - p.damage);
      if (p.heal > 0) {
        heal = Math.min(p.heal, u.maxHp - u.hp);
        u.hp += heal;
      }
      if (def.cleanse && u.hp > 0) {
        const removed = removeDebuffs(rules, u);
        if (removed.length > 0) events.push({ kind: "cleansed", unit: u.id, removed });
      }
      const condition = p.landed && u.hp > 0 && def.condition !== undefined ? def.condition : null;
      const turns = condition ? p.turns + extraRounds : 0;
      // A stack landing on the caster during its own turn starts counting next turn.
      if (condition) addCondition(rules, u, condition, turns, u.id === caster.id);
      events.push({
        kind: "stratagemEffect",
        unit: u.id,
        damage: p.damage,
        heal,
        condition,
        turns,
        resisted: def.condition !== undefined && !p.landed,
        hpAfter: u.hp,
      });
      if (u.hp === 0) defeat(rules, prng, u, events);
    }
  }

  if (!fizzle && def.push) {
    for (const p of pending)
      if (isAlive(p.unit) && !p.unit.structure)
        push(rules, state, prng, caster, p.unit, def.push, events);
  }
  if (!fizzle && def.ignites) ignite(rules, state, def, at, events);

  if (backfire > 0) {
    caster.hp = Math.max(0, caster.hp - backfire);
    events.push({
      kind: "stratagemEffect",
      unit: caster.id,
      damage: backfire,
      heal: 0,
      condition: null,
      turns: 0,
      resisted: false,
      hpAfter: caster.hp,
    });
    if (caster.hp === 0) defeat(rules, prng, caster, events);
  }
}

/** Push `u` up to `tiles` away from `from` along the main axis (ADR 0030). */
function push(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  from: Unit,
  u: Unit,
  tiles: number,
  events: BattleEvent[],
): void {
  const ddx = u.x - from.x;
  const ddy = u.y - from.y;
  const horizontal = Math.abs(ddx) >= Math.abs(ddy);
  const dx = horizontal ? Math.sign(ddx) : 0;
  const dy = horizontal ? 0 : Math.sign(ddy);
  if (dx === 0 && dy === 0) return;
  const start = { x: u.x, y: u.y };
  let collided: "wall" | "unit" | null = null;
  let damage = 0;
  for (let i = 0; i < tiles; i++) {
    const nx = u.x + dx;
    const ny = u.y + dy;
    if (!inBounds(state, nx, ny) || affinityAt(rules, state, u, nx, ny).move === null) {
      collided = "wall";
      damage = rules.tuning.pushWallDamage;
      break;
    }
    const other = unitAt(state, nx, ny);
    if (other) {
      collided = "unit";
      damage = rules.tuning.pushUnitDamage;
      other.hp = Math.max(0, other.hp - damage);
      events.push({
        kind: "pushed",
        unit: other.id,
        from: { x: nx, y: ny },
        to: { x: nx, y: ny },
        collided: "unit",
        damage,
        hpAfter: other.hp,
      });
      if (other.hp === 0) defeat(rules, prng, other, events);
      break;
    }
    u.x = nx;
    u.y = ny;
  }
  u.hp = Math.max(0, u.hp - damage);
  events.push({
    kind: "pushed",
    unit: u.id,
    from: start,
    to: { x: u.x, y: u.y },
    collided,
    damage,
    hpAfter: u.hp,
  });
  if (u.hp === 0) defeat(rules, prng, u, events);
}

const FLAMMABLE = new Set(["grass", "forest"]);

/** Grass and forest in the area, and next to it, burn for `burnRounds` (ADR 0030). */
function ignite(
  rules: Ruleset,
  state: BattleState,
  def: StratagemDef,
  at: Cell,
  events: BattleEvent[],
): void {
  const tiles = new Map<number, Cell>();
  const add = (x: number, y: number) => {
    if (inBounds(state, x, y) && FLAMMABLE.has(terrainAt(state, x, y))) {
      tiles.set(y * state.map.width + x, { x, y });
    }
  };
  for (const o of def.area) {
    const x = at.x + o.dx;
    const y = at.y + o.dy;
    if (!inBounds(state, x, y) || !FLAMMABLE.has(terrainAt(state, x, y))) continue;
    add(x, y);
    for (const n of NEIGHBOURS) add(x + n.dx, y + n.dy);
  }
  for (const [, c] of [...tiles].sort((a, b) => a[0] - b[0])) {
    const existing = state.burning.find((b) => b.x === c.x && b.y === c.y);
    if (existing) existing.rounds = Math.max(existing.rounds, rules.tuning.burnRounds);
    else state.burning.push({ x: c.x, y: c.y, rounds: rules.tuning.burnRounds });
    events.push({ kind: "ignited", at: c });
  }
  state.burning.sort((a, b) => a.y - b.y || a.x - b.x);
}
