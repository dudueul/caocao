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
import { classOf, friendly, hostile, isAlive, NEIGHBOURS, terrainAt, unitById } from "./board.ts";
import { attackPower, defensePower, hitChance, previewAttack, strikeDamage } from "./combat.ts";
import { conditionDef, hasBlock, stacksOf, stackWeight } from "./conditions.ts";
import { half, permille } from "./math.ts";
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
  StratagemDef,
  Unit,
} from "./types.ts";

const W = {
  damage: 10,
  counterTaken: 8,
  killBase: 300,
  heal: 8,
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
  /** Weight per HP-like point of a condition's expected effect. */
  condition: 8,
  /** Worth of removing one debuff stack. */
  cleansePerStack: 400,
  /** Score of a command that achieves nothing; below any wait. */
  useless: -900_000,
  /** Damage and kills on a unit our goals name count this many times over (ADR 0047). */
  goalTarget: 2,
  /** A unit the enemy's goals name weighs its exposure this many times over. */
  goalGuard: 2,
  /** Pull per movement point toward a tile our side must reach (ADR 0047, 0062), for one runner. */
  objective: 60,
  /** Standing on such a tile. */
  onObjective: 20_000,
};

/** Is `target` named in a "defeat" goal of `side` or its allies? */
function isGoalTarget(state: BattleState, side: number, target: string): boolean {
  return state.goals.some(
    (g) =>
      friendly(state, g.side, side) && g.when.kind === "defeat" && g.when.units.includes(target),
  );
}

/** How much more `me` should fear exposure: more if losing it loses the battle. */
function guardWeight(state: BattleState, me: Unit): number {
  return state.goals.some(
    (g) =>
      hostile(state, g.side, me.side) && g.when.kind === "defeat" && g.when.units.includes(me.id),
  )
    ? W.danger * W.goalGuard
    : W.danger;
}

/** For each enemy of `side`: the tiles it could attack next turn. */
export type ThreatMap = ReadonlyArray<{ enemy: Unit; tiles: ReadonlySet<number> }>;

export function threatMap(state: BattleState, rules: Ruleset, side = state.active): ThreatMap {
  const out: { enemy: Unit; tiles: Set<number> }[] = [];
  const w = state.map.width;
  for (const enemy of state.units) {
    if (!isAlive(enemy) || !hostile(state, enemy.side, side)) continue;
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
    if (!isAlive(u) || !hostile(state, u.side, unit.side)) continue;
    const d = Math.abs(u.x - unit.x) + Math.abs(u.y - unit.y);
    if (d < best) best = d;
  }
  return best === Number.MAX_SAFE_INTEGER ? 0 : best;
}

/** value × weight‰, rounded toward zero. */
function scaled(value: number, weight: number): number {
  return value < 0 ? -permille(-value, weight) : permille(value, weight);
}

/**
 * Worth of one more stack of `def.condition` on `t`, for the caster's side, in
 * HP-like points over its expected duration (ADR 0035). Further stacks count
 * less, a block that already holds adds nothing, and buffs on units far from
 * any enemy are worthless.
 */
function conditionValue(
  rules: Ruleset,
  s: BattleState,
  caster: Unit,
  t: Unit,
  def: StratagemDef,
): number {
  if (def.condition === undefined || !isAlive(t)) return 0;
  const c = conditionDef(rules, def.condition);
  const [min, max] = def.turns ?? [1, 1];
  const turns = Math.max(1, Math.floor((min + max) / 2));
  const n = stacksOf(t, c.id);
  const w = n >= rules.tuning.maxStacks ? 0 : stackWeight(rules, n);
  const atk = attackPower(rules, s, t);
  let impact = 0; // signed: positive means the condition helps `t`
  impact += scaled(half(atk), scaled(c.atk ?? 0, w));
  impact += scaled(half(defensePower(rules, s, t)), scaled(c.def ?? 0, w));
  impact += scaled(half(atk), scaled(c.hit ?? 0, w));
  impact += 15 * scaled(c.move ?? 0, w);
  impact -= permille(t.maxHp, scaled(c.dot ?? 0, w));
  if (c.blocks && !hasBlock(rules, t, c.blocks)) {
    const cls = classOf(rules, t);
    if (c.blocks === "act") impact -= half(atk) + 20;
    else if (c.blocks === "move") impact -= cls.move * 8;
    else if (cls.stratagems.length > 0 && t.mp > 0) impact -= t.stats.intellect;
  }
  impact *= turns;
  const ally = friendly(s, t.side, caster.side);
  if (ally && nearestEnemyDistance(s, t) > W.threatRange) return 0;
  return Math.max(0, ally ? impact : -impact);
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
  const key = `${state.teams[unit.side] ?? unit.side}|${unit.classId}`;
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
    if (!isAlive(e) || !hostile(state, e.side, unit.side)) continue;
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

/**
 * Tiles `unit` could win or set something off by standing on: its side's
 * "reach" goals and the trigger tiles of its side's pending "reach" shifts
 * (the sluice at Xiapi, ADR 0062), when they don't name other units.
 */
function objectiveCells(state: BattleState, unit: Unit): Cell[] {
  const mine = (w: { side: number; units?: string[] }) =>
    w.side === unit.side && (!w.units || w.units.includes(unit.id));
  const out: Cell[] = [];
  for (const g of state.goals) {
    const w = g.when;
    if (w.kind === "reach" && g.side === unit.side && mine(w)) out.push(...w.cells);
  }
  for (const sh of state.shifts) {
    const w = sh.when;
    if (w.kind === "reach" && mine(w)) out.push(...w.cells);
  }
  return out;
}

const objectiveCache = new WeakMap<BattleState, Map<string, number[]>>();

/** Movement cost from every tile to the nearest objective tile of `unit`'s side, for its family. */
function objectiveField(state: BattleState, rules: Ruleset, unit: Unit): number[] | null {
  const goals = objectiveCells(state, unit);
  if (goals.length === 0) return null;
  const family = classOf(rules, unit).family;
  const key = `${family}|${goals.map((c) => `${c.x},${c.y}`).join(";")}`;
  let byKey = objectiveCache.get(state);
  if (!byKey) {
    byKey = new Map();
    objectiveCache.set(state, byKey);
  }
  const cached = byKey.get(key);
  if (cached) return cached;
  const { width: w, height: h } = state.map;
  const row = rules.affinity[family];
  const cost = state.map.tiles.map((t) => row?.[t].move ?? null);
  const dist: number[] = new Array(w * h).fill(UNREACHABLE);
  const buckets: number[][] = [[]];
  for (const c of goals) {
    const k = c.y * w + c.x;
    if (cost[k] === null || dist[k] === 0) continue;
    dist[k] = 0;
    buckets[0]?.push(k);
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

const runnerCache = new WeakMap<BattleState, Map<number, string | null>>();

/** The one unit of `side` that goes for the objective: the nearest by movement cost, then by id. */
function runnerOf(state: BattleState, rules: Ruleset, side: number): string | null {
  let bySide = runnerCache.get(state);
  if (!bySide) {
    bySide = new Map();
    runnerCache.set(state, bySide);
  }
  const cached = bySide.get(side);
  if (cached !== undefined) return cached;
  let best: string | null = null;
  let bestD = UNREACHABLE;
  for (const u of state.units) {
    if (u.side !== side || !isAlive(u)) continue;
    const field = objectiveField(state, rules, u);
    if (!field) continue;
    const d = field[u.y * state.map.width + u.x] as number;
    if (d < bestD) {
      best = u.id;
      bestD = d;
    }
  }
  bySide.set(side, best);
  return best;
}

/** Is `unit` its side's runner, with an objective it can reach? */
function isRunner(state: BattleState, rules: Ruleset, unit: Unit): boolean {
  if (runnerOf(state, rules, unit.side) !== unit.id) return false;
  const d = objectiveField(state, rules, unit)?.[unit.y * state.map.width + unit.x];
  return d !== undefined && d !== UNREACHABLE;
}

/**
 * The runner's pull toward the objective from `at` (0 for every other unit).
 * It grows each round like the approach pull, which the runner does without,
 * so an escaping lord keeps running instead of turning to fight.
 */
function objectiveScore(state: BattleState, rules: Ruleset, unit: Unit, at: Cell): number {
  if (!isRunner(state, rules, unit)) return 0;
  const field = objectiveField(state, rules, unit);
  const d = field?.[at.y * state.map.width + at.x] ?? UNREACHABLE;
  if (d === UNREACHABLE) return 0;
  return d === 0 ? W.onObjective : -d * (W.objective + W.boldnessPerRound * state.round);
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
  score += objectiveScore(state, rules, unit, command.to);

  const action = command.action;
  if (action.kind === "attack") {
    const target = unitById(s, action.target);
    const p = previewAttack(rules, s, me, target);
    const perStrike = Math.floor(
      (p.hit * (p.damage * (1000 - p.crit) + p.critDamage * p.crit)) / 1_000_000,
    );
    const strikes = p.double ? 2 : 1;
    const dealt = Math.min(target.hp, perStrike * strikes);
    const focus = isGoalTarget(s, me.side, target.id) ? W.goalTarget : 1;
    score += dealt * W.damage * focus;
    if (p.damage * strikes >= target.hp)
      score += permille(W.killBase + target.maxHp, p.hit) * focus;
    const killsFirst = p.damage >= target.hp;
    if (p.counter && !killsFirst) {
      // A landed breaking strike cancels the counter (ADR 0030).
      const counterOdds = p.breaks ? 1000 - p.hit : 1000;
      const expected = permille(permille(p.counter.damage, p.counter.hit), counterOdds);
      score -= expected * W.counterTaken;
    }
    // Exposure after the exchange; a likely kill removes that enemy's threat.
    return score - danger(rules, s, me, threats, killsFirst ? target.id : "") * guardWeight(s, me);
  }
  if (action.kind === "stratagem") {
    const def = stratagemDef(rules, action.id);
    const targets = stratagemTargets(s, me, def, action.at);
    let value = 0;
    for (const t of targets) {
      if (def.effect === "damage") {
        const dmg = Math.min(t.hp, stratagemDamage(rules, s, def, me, t));
        const focus = isGoalTarget(s, me.side, t.id) ? W.goalTarget : 1;
        value += dmg * W.damage * focus;
        if (dmg >= t.hp) value += (W.killBase + t.maxHp) * focus;
      } else if (def.effect === "heal") {
        value += Math.min(t.maxHp - t.hp, stratagemHeal(def, me)) * W.heal;
      }
      if (def.cleanse) {
        const debuffs = t.conditions.filter((c) => conditionDef(rules, c.id).kind === "debuff");
        value += debuffs.length * W.cleansePerStack;
      }
      if (def.condition !== undefined) {
        const chance =
          hostile(s, t.side, me.side) && conditionDef(rules, def.condition).kind === "debuff"
            ? statusChance(rules, me, t)
            : 1000;
        value += permille(conditionValue(rules, s, me, t, def), chance) * W.condition;
      }
    }
    // A cast that achieves nothing (healing full HP, binding the bound) is never worth MP.
    if (value <= 0) return W.useless;
    // Support casts move like a wait does, so they pay the same approach pull.
    const pull =
      def.targets === "allies" && !isRunner(state, rules, unit)
        ? approachGap(state, rules, unit, command.to)
        : 0;
    return (
      score +
      value -
      def.mp * W.mp -
      pull * (W.approach + W.boldnessPerRound * state.round) -
      danger(rules, s, me, threats, "") * guardWeight(s, me)
    );
  }
  // Wait: approach an attack position, weighed against exposure.
  const gap = isRunner(state, rules, unit) ? 0 : approachGap(state, rules, unit, command.to);
  const approach = W.approach + W.boldnessPerRound * state.round;
  return score - gap * approach - danger(rules, s, me, threats, "") * guardWeight(s, me);
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
        (u) =>
          friendly(state, u.side, unit.side) &&
          u.id !== unit.id &&
          isAlive(u) &&
          hpBelow(u, when.permille),
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
    case "guard": {
      // Never leaves its tile: strikes or casts from where it stands, else waits (ADR 0047).
      const inPlace = cmds.filter((c) => c.to.x === unit.x && c.to.y === unit.y);
      const acts = inPlace.filter((c) => c.action.kind !== "wait");
      return acts.length > 0 ? acts : inPlace;
    }
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
  if (!gambits || hasBlock(rules, unit, "act")) return cmds;
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
