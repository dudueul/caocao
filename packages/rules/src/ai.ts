/**
 * Baseline utility AI (ARCHITECTURE.md §3): score every legal command for the
 * side to act and take the best; ties go to the first in legal-command order.
 * It uses only exact previews (no Prng), so its choice is deterministic and
 * identical on client and server. Weights are starting values for playtests.
 */
import { legalCommands } from "./battle.ts";
import { classOf, isAlive, terrainAt, unitById } from "./board.ts";
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
import type { BattleState, Command, Ruleset, Unit } from "./types.ts";

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
};

/** For each enemy of the side to act: the tiles it could attack next turn. */
export type ThreatMap = ReadonlyArray<{ enemy: Unit; tiles: ReadonlySet<number> }>;

export function threatMap(state: BattleState, rules: Ruleset): ThreatMap {
  const out: { enemy: Unit; tiles: Set<number> }[] = [];
  const w = state.map.width;
  for (const enemy of state.units) {
    if (!isAlive(enemy) || enemy.side === state.active) continue;
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

/** Expected damage `me` could take on its current tile from enemies next turn. */
function danger(
  rules: Ruleset,
  s: BattleState,
  me: Unit,
  threats: ThreatMap,
  skip: string,
): number {
  const key = me.y * s.map.width + me.x;
  let total = 0;
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
    if (p.counter && !killsFirst)
      score -= permille(p.counter.damage, p.counter.hit) * W.counterTaken;
    // Exposure after the exchange; a likely kill removes that enemy's threat.
    return score - danger(rules, s, me, threats, killsFirst ? target.id : "") * W.danger;
  }
  if (action.kind === "stratagem") {
    const def = stratagemDef(rules, action.id);
    const targets = stratagemTargets(s, me, def, action.at);
    for (const t of targets) {
      if (def.effect === "damage") {
        const dmg = Math.min(t.hp, stratagemDamage(s, def, me, t));
        score += dmg * W.damage;
        if (dmg >= t.hp) score += W.killBase + t.maxHp;
      } else if (def.effect === "heal") {
        score += Math.min(t.maxHp - t.hp, stratagemHeal(def, me)) * W.heal;
      } else if (t.status === null) {
        score += permille(t.maxHp, statusChance(rules, me, t)) * W.status;
      }
    }
    return score - danger(rules, s, me, threats, "") * W.danger;
  }
  // Wait: approach the preferred distance, weighed against exposure.
  const gap = Math.abs(nearestEnemyDistance(s, me) - preferredDistance(rules, me));
  const approach = W.approach + W.boldnessPerRound * state.round;
  return score - gap * approach - danger(rules, s, me, threats, "") * W.danger;
}

/** The AI's pick for the side to act, or null when the battle is over. */
export function chooseCommand(state: BattleState, baseRules: Ruleset): Command | null {
  const rules = forMode(baseRules, state.mode);
  const commands = legalCommands(state, rules);
  const threats = threatMap(state, rules);
  let best: Command | null = null;
  let bestScore = Number.MIN_SAFE_INTEGER;
  for (const c of commands) {
    const sc = scoreCommand(state, rules, c, threats);
    if (sc > bestScore) {
      best = c;
      bestScore = sc;
    }
  }
  return best;
}
