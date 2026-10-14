/**
 * Baseline utility AI (ARCHITECTURE.md §3): score every legal command for the
 * side to act and take the best; ties go to the first in legal-command order.
 * It uses only exact previews (no Prng), so its choice is deterministic and
 * identical on client and server. Weights are starting values for playtests.
 *
 * Gambits (ADR 0030) let a player program "condition → behaviour" rules per
 * unit; the AI follows them for absent seats and auto-battle, and falls back to
 * its own scoring when no gambit applies.
 */
import { legalCommands } from "./battle.ts";
import { classOf, isAlive, NEIGHBOURS, terrainAt, unitById } from "./board.ts";
import { hitChance, previewAttack, strikeDamage } from "./combat.ts";
import { permille } from "./math.ts";
import { reachable } from "./move.ts";
import {
  statusChance,
  stratagemDamage,
  stratagemDef,
  stratagemHeal,
  stratagemTargets,
} from "./stratagem.ts";
import { forMode } from "./tuning.ts";
import type {
  BattleState,
  Cell,
  Command,
  Gambit,
  GambitBehavior,
  GambitCondition,
  Ruleset,
  Unit,
} from "./types.ts";

const W = {
  damage: 10,
  counterTaken: 8,
  killBase: 300,
  heal: 8,
  status: 3,
  approach: 40,
  cover: 1,
  lowHpHealTile: 200,
  /** Cover and healing tiles only matter within this distance of an enemy. */
  threatRange: 7,
  /** Penalty per point of expected damage enemies could deal here next turn. */
  danger: 6,
  /** Extra approach pressure per round, so cautious sides eventually engage. */
  boldnessPerRound: 8,
  /** Cost per MP spent, so small effects don't drain a strategist. */
  mp: 15,
  /** Score of a command that achieves nothing; below any wait. */
  useless: -900_000,
};

/** For each enemy of `side`: the tiles it could attack next turn. */
export type ThreatMap = ReadonlyArray<{ enemy: Unit; tiles: ReadonlySet<number> }>;

export function threatMap(state: BattleState, rules: Ruleset, side = state.active): ThreatMap {
  const out: { enemy: Unit; tiles: Set<number> }[] = [];
  const w = state.map.width;
  for (const enemy of state.units) {
    if (!isAlive(enemy) || enemy.side === side) continue;
    const tiles = new Set<number>();
    const range = classOf(rules, enemy).range;
    for (const r of reachable(state, rules, enemy))
      for (const o of range) {
        const x = r.x + o.dx;
        const y = r.y + o.dy;
        if (x >= 0 && y >= 0 && x < w && y < state.map.height) tiles.add(y * w + x);
      }
    out.push({ enemy, tiles });
  }
  return out;
}

/**
 * Danger zone for the UI (ADR 0030): every tile some enemy of `side` could
 * attack next turn, sorted by row, then column.
 */
export function dangerTiles(state: BattleState, baseRules: Ruleset, side: number): Cell[] {
  const rules = forMode(baseRules, state.mode);
  const all = new Set<number>();
  for (const t of threatMap(state, rules, side)) for (const k of t.tiles) all.add(k);
  const w = state.map.width;
  // Skip tiles no family can stand on (walls, water for most rulesets).
  const standable = (k: number) => {
    const terrain = state.map.tiles[k];
    return Object.values(rules.affinity).some(
      (row) => terrain !== undefined && row[terrain].move !== null,
    );
  };
  return [...all]
    .filter(standable)
    .sort((a, b) => a - b)
    .map((k) => ({ x: k % w, y: Math.floor(k / w) }));
}

/** Expected damage `me` could take on its current tile from enemies next turn. */
function danger(
  rules: Ruleset,
  s: BattleState,
  me: Unit,
  threats: ThreatMap,
  skip: string,
): number {
  const key = me.y * s.map.width + me.x;
  let total = s.burning.some((b) => b.x === me.x && b.y === me.y) ? rules.tuning.burnDamage : 0;
  for (const t of threats) {
    if (t.enemy.id === skip || !t.tiles.has(key)) continue;
    total += permille(
      strikeDamage(rules, s, t.enemy, me, "first", false),
      hitChance(rules, s, t.enemy, me),
    );
  }
  return Math.min(total, me.hp);
}

/** The state as if `unit` already stood on `to` (for previews from that tile). */
function shadow(state: BattleState, unit: Unit, to: { x: number; y: number }): [BattleState, Unit] {
  const moved: Unit = { ...unit, x: to.x, y: to.y };
  return [{ ...state, units: state.units.map((u) => (u.id === unit.id ? moved : u)) }, moved];
}

function nearestEnemyDistance(state: BattleState, unit: Unit): number {
  let best = Number.MAX_SAFE_INTEGER;
  for (const u of state.units) {
    if (!isAlive(u) || u.side === unit.side) continue;
    const d = Math.abs(u.x - unit.x) + Math.abs(u.y - unit.y);
    if (d < best) best = d;
  }
  return best === Number.MAX_SAFE_INTEGER ? 0 : best;
}

/** Ideal distance to the nearest enemy: just outside melee for ranged units. */
function preferredDistance(rules: Ruleset, unit: Unit): number {
  let max = 1;
  for (const o of classOf(rules, unit).range) max = Math.max(max, Math.abs(o.dx) + Math.abs(o.dy));
  return max;
}

const UNREACHABLE = Number.MAX_SAFE_INTEGER;
const fieldCache = new WeakMap<BattleState, Map<string, number[]>>();

/**
 * Movement cost from every tile to the nearest tile where `unit` could attack an
 * enemy, over its family's terrain costs, ignoring other units. The approach
 * pull follows it, so units walk to a bridge or ford instead of stalling on the
 * near bank of a river. Cached per state, side and class.
 */
function approachField(state: BattleState, rules: Ruleset, unit: Unit): number[] {
  const key = `${unit.side}|${unit.classId}`;
  let byKey = fieldCache.get(state);
  if (!byKey) {
    byKey = new Map();
    fieldCache.set(state, byKey);
  }
  const cached = byKey.get(key);
  if (cached) return cached;

  const { width: w, height: h } = state.map;
  const cls = classOf(rules, unit);
  const row = rules.affinity[cls.family];
  const cost = state.map.tiles.map((t) => row?.[t].move ?? null);
  const dist: number[] = new Array(w * h).fill(UNREACHABLE);
  // Bucket queue by distance: costs are small integers, so this is exact and ordered.
  const buckets: number[][] = [[]];
  for (const e of state.units) {
    if (!isAlive(e) || e.side === unit.side) continue;
    for (const o of cls.range) {
      const x = e.x - o.dx;
      const y = e.y - o.dy;
      if (x < 0 || y < 0 || x >= w || y >= h) continue;
      const k = y * w + x;
      if (cost[k] === null || dist[k] === 0) continue;
      dist[k] = 0;
      buckets[0]?.push(k);
    }
  }
  for (let d = 0; d < buckets.length; d++) {
    for (const k of buckets[d] ?? []) {
      if (dist[k] !== d) continue;
      const x = k % w;
      const y = (k - x) / w;
      const enter = cost[k] as number;
      for (const n of NEIGHBOURS) {
        const nx = x + n.dx;
        const ny = y + n.dy;
        if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
        const nk = ny * w + nx;
        // Walking from the neighbour onto k costs k's terrain.
        if (cost[nk] === null || d + enter >= (dist[nk] as number)) continue;
        dist[nk] = d + enter;
        while (buckets.length <= d + enter) buckets.push([]);
        buckets[d + enter]?.push(nk);
      }
    }
  }
  byKey.set(key, dist);
  return dist;
}

/** How far `unit` standing on `at` is from attacking (0 = it can attack now). */
function approachGap(state: BattleState, rules: Ruleset, unit: Unit, at: Cell): number {
  const d = approachField(state, rules, unit)[at.y * state.map.width + at.x] as number;
  if (d !== UNREACHABLE) return d;
  const moved = { ...unit, x: at.x, y: at.y };
  return Math.abs(nearestEnemyDistance(state, moved) - preferredDistance(rules, moved));
}

export function scoreCommand(
  state: BattleState,
  rules: Ruleset,
  command: Command,
  threats: ThreatMap = threatMap(state, rules),
): number {
  if (command.kind === "endPhase") return -1_000_000;
  const unit = unitById(state, command.unit);
  const [s, me] = shadow(state, unit, command.to);
  const tile = rules.terrains[terrainAt(s, me.x, me.y)];
  const threatened = nearestEnemyDistance(s, me) <= W.threatRange;
  let score = threatened ? tile.cover * W.cover : 0;
  if (tile.heal > 0 && me.hp * 2 < me.maxHp) score += W.lowHpHealTile;

  const action = command.action;
  if (action.kind === "attack") {
    const target = unitById(s, action.target);
    const p = previewAttack(rules, s, me, target);
    const perStrike = Math.floor(
      (p.hit * (p.damage * (1000 - p.crit) + p.critDamage * p.crit)) / 1_000_000,
    );
    const strikes = p.double ? 2 : 1;
    const dealt = Math.min(target.hp, perStrike * strikes);
    score += dealt * W.damage;
    if (p.damage * strikes >= target.hp) score += permille(W.killBase + target.maxHp, p.hit);
    const killsFirst = p.damage >= target.hp;
    if (p.counter && !killsFirst) {
      // A landed breaking strike cancels the counter (ADR 0030).
      const counterOdds = p.breaks ? 1000 - p.hit : 1000;
      const expected = permille(permille(p.counter.damage, p.counter.hit), counterOdds);
      score -= expected * W.counterTaken;
    }
    // Exposure after the exchange; a likely kill removes that enemy's threat.
    return score - danger(rules, s, me, threats, killsFirst ? target.id : "") * W.danger;
  }
  if (action.kind === "stratagem") {
    const def = stratagemDef(rules, action.id);
    const targets = stratagemTargets(s, me, def, action.at);
    let value = 0;
    for (const t of targets) {
      if (def.effect === "damage") {
        const dmg = Math.min(t.hp, stratagemDamage(s, def, me, t));
        value += dmg * W.damage;
        if (dmg >= t.hp) value += W.killBase + t.maxHp;
      } else if (def.effect === "heal") {
        value += Math.min(t.maxHp - t.hp, stratagemHeal(def, me)) * W.heal;
      } else if (t.status === null) {
        value += permille(t.maxHp, statusChance(rules, me, t)) * W.status;
      }
    }
    // A cast that achieves nothing (healing full HP, confusing the confused) is never worth MP.
    if (value <= 0) return W.useless;
    return score + value - def.mp * W.mp - danger(rules, s, me, threats, "") * W.danger;
  }
  // Wait: approach an attack position, weighed against exposure.
  const gap = approachGap(state, rules, unit, command.to);
  const approach = W.approach + W.boldnessPerRound * state.round;
  return score - gap * approach - danger(rules, s, me, threats, "") * W.danger;
}

// ---------------------------------------------------------------- gambits (ADR 0030)

type Act = Extract<Command, { kind: "act" }>;

function hpBelow(u: Unit, permilleOfMax: number): boolean {
  return u.hp * 1000 < u.maxHp * permilleOfMax;
}

function attacks(rules: Ruleset, state: BattleState, cmds: readonly Act[], family?: string): Act[] {
  return cmds.filter((c) => {
    if (c.action.kind !== "attack") return false;
    const target = unitById(state, c.action.target);
    return family === undefined || classOf(rules, target).family === family;
  });
}

function conditionHolds(
  rules: Ruleset,
  state: BattleState,
  unit: Unit,
  cmds: readonly Act[],
  when: GambitCondition,
): boolean {
  switch (when.kind) {
    case "always":
      return true;
    case "selfHpBelow":
      return hpBelow(unit, when.permille);
    case "allyHpBelow":
      return state.units.some(
        (u) => u.side === unit.side && u.id !== unit.id && isAlive(u) && hpBelow(u, when.permille),
      );
    case "enemyInReach":
      return attacks(rules, state, cmds, when.family).length > 0;
    case "canKill":
      return attacks(rules, state, cmds).some((c) => {
        if (c.action.kind !== "attack") return false;
        const [s, me] = shadow(state, unit, c.to);
        const target = unitById(s, c.action.target);
        return previewAttack(rules, s, me, target).damage >= target.hp;
      });
  }
}

/** Commands of one unit that carry out `behavior`; empty when it can't apply. */
function behaviorCandidates(
  rules: Ruleset,
  state: BattleState,
  unit: Unit,
  cmds: readonly Act[],
  behavior: GambitBehavior,
): Act[] {
  const waits = cmds.filter((c) => c.action.kind === "wait");
  switch (behavior.kind) {
    case "attackBest":
      return attacks(rules, state, cmds);
    case "attackFamily":
      return attacks(rules, state, cmds, behavior.family);
    case "attackLowestHp": {
      const all = attacks(rules, state, cmds);
      let lowest = Number.MAX_SAFE_INTEGER;
      for (const c of all)
        if (c.action.kind === "attack")
          lowest = Math.min(lowest, unitById(state, c.action.target).hp);
      return all.filter(
        (c) => c.action.kind === "attack" && unitById(state, c.action.target).hp === lowest,
      );
    }
    case "healAlly":
      return cmds.filter(
        (c) => c.action.kind === "stratagem" && stratagemDef(rules, c.action.id).effect === "heal",
      );
    case "retreatToHeal":
      return waits.filter((c) => rules.terrains[terrainAt(state, c.to.x, c.to.y)].heal > 0);
    case "advance": {
      let best = Number.MAX_SAFE_INTEGER;
      const dist = (c: Act) => approachGap(state, rules, unit, c.to);
      for (const c of waits) best = Math.min(best, dist(c));
      return waits.filter((c) => dist(c) === best);
    }
    case "hold":
      return waits.filter((c) => c.to.x === unit.x && c.to.y === unit.y);
  }
}

/** The unit's commands narrowed by its first applicable gambit (or all of them). */
function gambitCommands(
  rules: Ruleset,
  state: BattleState,
  unit: Unit,
  cmds: readonly Act[],
  gambits: readonly Gambit[] | undefined,
): readonly Act[] {
  if (!gambits || unit.status !== null) return cmds;
  for (const g of gambits) {
    if (!conditionHolds(rules, state, unit, cmds, g.when)) continue;
    const picked = behaviorCandidates(rules, state, unit, cmds, g.behavior);
    if (picked.length > 0) return picked;
  }
  return cmds;
}

/**
 * The AI's pick for the side to act, or null when the battle is over. Units with
 * gambits only consider the commands their first applicable gambit allows.
 */
export function chooseCommand(
  state: BattleState,
  baseRules: Ruleset,
  gambits: Readonly<Record<string, readonly Gambit[]>> = {},
): Command | null {
  const rules = forMode(baseRules, state.mode);
  const commands = legalCommands(state, rules);
  const threats = threatMap(state, rules);
  const byUnit = new Map<string, Act[]>();
  let fallback: Command | null = null;
  for (const c of commands) {
    if (c.kind !== "act") {
      fallback = c;
      continue;
    }
    const list = byUnit.get(c.unit);
    if (list) list.push(c);
    else byUnit.set(c.unit, [c]);
  }
  let best: Command | null = null;
  let bestScore = Number.MIN_SAFE_INTEGER;
  for (const [id, cmds] of byUnit) {
    for (const c of gambitCommands(rules, state, unitById(state, id), cmds, gambits[id])) {
      const sc = scoreCommand(state, rules, c, threats);
      if (sc > bestScore) {
        best = c;
        bestScore = sc;
      }
    }
  }
  return best ?? fallback;
}
