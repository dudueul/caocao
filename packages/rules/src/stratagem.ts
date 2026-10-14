/**
 * Stratagems (RULES.md §5, ADR 0024): MP cost, elements scaled by terrain and
 * weather, status rolls against 지력 and 운, and the rare fortune twist.
 * Prng draw order: status rolls (per target, by unit id), twist, direction,
 * then fate rolls for anyone defeated.
 */
import {
  affinityAt,
  classOf,
  inBounds,
  inRange,
  isAlive,
  NEIGHBOURS,
  terrainAt,
  unitAt,
} from "./board.ts";
import { defeat } from "./combat.ts";
import { clamp, half, permille } from "./math.ts";
import type { Prng } from "./prng.ts";
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
    const ally = u.side === caster.side;
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
  if (caster.mp < def.mp || caster.status !== null) return false;
  if (!inBounds(state, at.x, at.y) || !inRange(def.range, from, at)) return false;
  // Evaluate targets as if the caster already stood on `from`.
  const moved = { ...caster, x: from.x, y: from.y };
  const shadow = { ...state, units: state.units.map((u) => (u.id === caster.id ? moved : u)) };
  return stratagemTargets(shadow, moved, def, at).length > 0;
}

export function stratagemDamage(
  state: BattleState,
  def: StratagemDef,
  caster: Unit,
  target: Unit,
): number {
  const base = Math.max(1, def.power + caster.stats.intellect - half(target.stats.intellect));
  const byTerrain = permille(base, def.terrain?.[terrainAt(state, target.x, target.y)] ?? 1000);
  return permille(byTerrain, def.weather?.[state.weather] ?? 1000);
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

interface Pending {
  unit: Unit;
  damage: number;
  heal: number;
  landed: boolean;
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

  const pending: Pending[] = stratagemTargets(state, caster, def, at).map((unit) => ({
    unit,
    damage: def.effect === "damage" ? stratagemDamage(state, def, caster, unit) : 0,
    heal: def.effect === "heal" ? stratagemHeal(def, caster) : 0,
    landed: def.effect === "status" ? prng.chance(statusChance(rules, caster, unit)) : true,
  }));

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
      const status = def.effect === "status" && p.landed && def.status ? def.status : null;
      if (status) u.status = { kind: status, rounds: (def.statusRounds ?? 1) + extraRounds };
      events.push({
        kind: "stratagemEffect",
        unit: u.id,
        damage: p.damage,
        heal,
        status,
        resisted: def.effect === "status" && !p.landed,
        hpAfter: u.hp,
      });
      if (u.hp === 0) defeat(rules, prng, u, events);
    }
  }

  if (!fizzle && def.push) {
    for (const p of pending)
      if (isAlive(p.unit)) push(rules, state, prng, caster, p.unit, def.push, events);
  }
  if (!fizzle && def.ignites) ignite(rules, state, def, at, events);

  if (backfire > 0) {
    caster.hp = Math.max(0, caster.hp - backfire);
    events.push({
      kind: "stratagemEffect",
      unit: caster.id,
      damage: backfire,
      heal: 0,
      status: null,
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
