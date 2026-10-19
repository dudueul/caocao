/**
 * Battle lifecycle: create a battle, list legal commands, and apply a command.
 * `apply` is a pure function (state, command) → (new state, events): the client
 * runs it for solo play and the MatchDO runs it as the referee (ADR 0004).
 * Turn order is a policy (ADR 0003): side phases, or alternating single-unit
 * activations whose starting side alternates every round.
 */
import {
  classOf,
  inBounds,
  inRange,
  isAlive,
  livingSides,
  terrainAt,
  unitAt,
  unitById,
} from "./board.ts";
import { defeat, resolveAttack } from "./combat.ts";
import { endTurn, hasBlock } from "./conditions.ts";
import { bringArrivals, endByGoal } from "./goals.ts";
import { permille } from "./math.ts";
import { canReach, reachable } from "./move.ts";
import { Prng, seedFromString } from "./prng.ts";
import { canCast, castStratagem, stratagemDef } from "./stratagem.ts";
import { forMode } from "./tuning.ts";
import type {
  BattleEvent,
  BattleMap,
  BattleState,
  Command,
  Goal,
  ModeId,
  Ruleset,
  Stats,
  TurnPolicyId,
  Unit,
  WeatherKind,
} from "./types.ts";

export interface UnitSetup {
  id: string;
  side: number;
  name: string;
  classId: string;
  level: number;
  stats: Stats;
  atkBonus?: number;
  defBonus?: number;
  x: number;
  y: number;
  /** Joins at the start of this round (2 or later) instead of starting on the field (ADR 0047). */
  arrives?: number;
}

export interface BattleSetup {
  /** Selects per-mode tuning overrides (ADR 0029); default "sandbox". */
  mode?: ModeId;
  map: BattleMap;
  weather: WeatherKind;
  policy: TurnPolicyId;
  roundLimit: number;
  seed: string;
  units: UnitSetup[];
  /** Ways to win besides routing every enemy (ADR 0047). */
  goals?: Goal[];
}

export type ApplyResult =
  | { ok: true; state: BattleState; events: BattleEvent[] }
  | { ok: false; reason: string };

export function createBattle(baseRules: Ruleset, setup: BattleSetup): BattleState {
  const mode = setup.mode ?? "sandbox";
  const rules = forMode(baseRules, mode);
  const t = rules.tuning;
  if (setup.map.tiles.length !== setup.map.width * setup.map.height) {
    throw new Error("map tiles do not match width × height");
  }
  const all = setup.units
    .map((s) => {
      const cls = rules.classes[s.classId];
      if (!cls) throw new Error(`unknown class ${s.classId}`);
      const maxHp = cls.hp + s.stats.command + s.level * t.hpPerLevel;
      const maxMp = cls.mp + permille(s.stats.intellect, t.mpPerIntellect);
      return {
        id: s.id,
        side: s.side,
        name: s.name,
        classId: s.classId,
        level: s.level,
        stats: { ...s.stats },
        atkBonus: s.atkBonus ?? 0,
        defBonus: s.defBonus ?? 0,
        hp: maxHp,
        maxHp,
        mp: maxMp,
        maxMp,
        x: s.x,
        y: s.y,
        acted: false,
        conditions: [],
        broken: false,
        fate: null,
      };
    })
    .sort((a, b) => (a.id < b.id ? -1 : a.id > b.id ? 1 : 0));
  const arrivesOf = new Map(setup.units.map((s) => [s.id, s.arrives ?? 1]));
  const ids = new Set(all.map((u) => u.id));
  if (ids.size !== all.length) throw new Error("two units share an id");
  const units = all.filter((u) => (arrivesOf.get(u.id) ?? 1) <= 1);
  const reserve = all
    .filter((u) => (arrivesOf.get(u.id) ?? 1) > 1)
    .map((unit) => ({ round: arrivesOf.get(unit.id) ?? 2, unit }))
    .sort((a, b) => a.round - b.round || (a.unit.id < b.unit.id ? -1 : 1));
  for (const a of reserve) {
    if (a.unit.x < 0 || a.unit.y < 0 || a.unit.x >= setup.map.width || a.unit.y >= setup.map.height)
      throw new Error(`unit ${a.unit.id} arrives off the map`);
  }
  const goals = (setup.goals ?? []).map((g) => structuredClone(g));
  for (const g of goals) {
    if (g.when.kind === "defeat" && g.when.units.some((id) => !ids.has(id)))
      throw new Error("a goal names an unknown unit");
  }

  const seen = new Set<string>();
  for (const u of units) {
    const key = `${u.x},${u.y}`;
    if (seen.has(key)) throw new Error(`two units on ${key}`);
    if (u.x < 0 || u.y < 0 || u.x >= setup.map.width || u.y >= setup.map.height) {
      throw new Error(`unit ${u.id} is off the map`);
    }
    seen.add(key);
  }
  const sides = Math.max(...all.map((u) => u.side)) + 1;
  const state: BattleState = {
    version: 3,
    mode,
    map: { width: setup.map.width, height: setup.map.height, tiles: [...setup.map.tiles] },
    weather: setup.weather,
    policy: setup.policy,
    sides,
    round: 1,
    roundLimit: setup.roundLimit,
    active: 0,
    units,
    burning: [],
    goals,
    reserve,
    prng: seedFromString(setup.seed),
    winner: null,
  };
  state.active = firstSideOfRound(state);
  return state;
}

/** Living units of `side` that have not acted this round. */
export function readyUnits(state: BattleState, side: number): Unit[] {
  return state.units.filter((u) => u.side === side && isAlive(u) && !u.acted);
}

function firstSideOfRound(state: BattleState): number {
  const living = livingSides(state);
  if (living.length === 0) return 0;
  if (state.policy === "sidePhases") return living[0] as number;
  return living[(state.round - 1) % living.length] as number;
}

/** Every legal command for the side to act, in a deterministic order. */
export function legalCommands(state: BattleState, baseRules: Ruleset): Command[] {
  if (state.winner !== null) return [];
  const rules = forMode(baseRules, state.mode);
  const out: Command[] = [];
  for (const u of readyUnits(state, state.active)) {
    if (hasBlock(rules, u, "act")) {
      out.push({ kind: "act", unit: u.id, to: { x: u.x, y: u.y }, action: { kind: "wait" } });
      continue;
    }
    const cls = classOf(rules, u);
    for (const r of reachable(state, rules, u)) {
      const to = { x: r.x, y: r.y };
      out.push({ kind: "act", unit: u.id, to, action: { kind: "wait" } });
      for (const o of cls.range) {
        const target = unitAt(state, to.x + o.dx, to.y + o.dy);
        if (target && target.side !== u.side && target.id !== u.id) {
          out.push({ kind: "act", unit: u.id, to, action: { kind: "attack", target: target.id } });
        }
      }
      for (const id of cls.stratagems) {
        const def = stratagemDef(rules, id);
        for (const o of def.range) {
          const at = { x: to.x + o.dx, y: to.y + o.dy };
          if (canCast(rules, state, u, def, to, at)) {
            out.push({ kind: "act", unit: u.id, to, action: { kind: "stratagem", id, at } });
          }
        }
      }
    }
  }
  if (state.policy === "sidePhases" && out.length > 0) out.push({ kind: "endPhase" });
  return out;
}

export function apply(state: BattleState, baseRules: Ruleset, command: Command): ApplyResult {
  if (state.winner !== null) return { ok: false, reason: "battle is over" };
  const rules = forMode(baseRules, state.mode);
  const next = structuredClone(state);
  const prng = new Prng(next.prng);
  const events: BattleEvent[] = [];

  if (command.kind === "endPhase") {
    if (next.policy !== "sidePhases")
      return { ok: false, reason: "endPhase is only for side phases" };
    // Passed-over units still use up a turn of their conditions.
    for (const u of readyUnits(next, next.active)) {
      u.acted = true;
      endTurn(rules, u, events);
    }
  } else {
    const error = validateAct(next, rules, command);
    if (error) return { ok: false, reason: error };
    const unit = unitById(next, command.unit);
    unit.broken = false; // acting ends a break (ADR 0030)
    if (unit.x !== command.to.x || unit.y !== command.to.y) {
      events.push({
        kind: "moved",
        unit: unit.id,
        from: { x: unit.x, y: unit.y },
        to: command.to,
      });
      unit.x = command.to.x;
      unit.y = command.to.y;
    }
    const action = command.action;
    if (action.kind === "attack") {
      resolveAttack(rules, next, prng, unit, unitById(next, action.target), events);
    } else if (action.kind === "stratagem") {
      castStratagem(rules, next, prng, unit, stratagemDef(rules, action.id), action.at, events);
    } else {
      events.push({ kind: "waited", unit: unit.id });
    }
    unit.acted = true;
    endTurn(rules, unit, events);
  }

  advance(next, rules, prng, events);
  next.prng = prng.state;
  return { ok: true, state: next, events };
}

function validateAct(
  state: BattleState,
  rules: Ruleset,
  c: Extract<Command, { kind: "act" }>,
): string | null {
  const unit = state.units.find((u) => u.id === c.unit);
  if (!unit) return "unknown unit";
  if (!isAlive(unit)) return "unit is defeated";
  if (unit.side !== state.active) return "not this side's turn";
  if (unit.acted) return "unit has already acted";
  if (!inBounds(state, c.to.x, c.to.y)) return "destination off the map";
  if (hasBlock(rules, unit, "act")) {
    if (c.to.x !== unit.x || c.to.y !== unit.y || c.action.kind !== "wait") {
      return "a unit that can't act can only wait";
    }
    return null;
  }
  if (!canReach(state, rules, unit, c.to)) return "destination not reachable";
  const a = c.action;
  if (a.kind === "attack") {
    const target = state.units.find((u) => u.id === a.target);
    if (!target || !isAlive(target)) return "no such target";
    if (target.side === unit.side) return "cannot attack an ally";
    if (!inRange(classOf(rules, unit).range, c.to, target)) return "target out of range";
  } else if (a.kind === "stratagem") {
    const def = rules.stratagems[a.id];
    if (!def) return "unknown stratagem";
    if (!canCast(rules, state, unit, def, c.to, a.at)) return "cannot cast here";
  }
  return null;
}

/** After an action: end the battle, or pass the turn per the turn policy. */
function advance(state: BattleState, rules: Ruleset, prng: Prng, events: BattleEvent[]): void {
  if (endByGoal(state, events)) return;
  const living = livingSides(state);
  if (living.length <= 1) {
    state.winner = living[0] ?? -1;
    events.push({ kind: "battleEnd", winner: state.winner });
    return;
  }
  if (state.policy === "sidePhases") {
    if (readyUnits(state, state.active).length > 0) return;
    const nextSide = living.find((s) => s > state.active && readyUnits(state, s).length > 0);
    if (nextSide !== undefined) {
      state.active = nextSide;
      events.push({ kind: "turn", side: nextSide, round: state.round });
      return;
    }
  } else {
    const prev = state.active;
    for (let step = 1; step <= state.sides; step++) {
      const s = (prev + step) % state.sides;
      if (readyUnits(state, s).length > 0) {
        state.active = s;
        if (s !== prev) events.push({ kind: "turn", side: s, round: state.round });
        return;
      }
    }
  }
  newRound(state, rules, prng, events);
}

function newRound(state: BattleState, rules: Ruleset, prng: Prng, events: BattleEvent[]): void {
  state.round += 1;
  if (endByGoal(state, events)) return;
  if (state.round > state.roundLimit) {
    state.winner = -1;
    events.push({ kind: "battleEnd", winner: -1 });
    return;
  }
  // Fire burns whoever stands in it, then dies down (ADR 0030).
  for (const b of state.burning) {
    const u = unitAt(state, b.x, b.y);
    if (!u) continue;
    const damage = Math.min(u.hp, rules.tuning.burnDamage);
    u.hp -= damage;
    events.push({ kind: "burned", unit: u.id, damage, hpAfter: u.hp });
    if (u.hp === 0) defeat(rules, prng, u, events);
  }
  for (const b of state.burning) b.rounds -= 1;
  state.burning = state.burning.filter((b) => b.rounds > 0);
  if (endByGoal(state, events)) return;
  bringArrivals(state, rules, events);
  const living = livingSides(state);
  if (living.length <= 1) {
    state.winner = living[0] ?? -1;
    events.push({ kind: "battleEnd", winner: state.winner });
    return;
  }
  for (const u of state.units) {
    if (!isAlive(u)) continue;
    u.acted = false;
    const heal = rules.terrains[terrainAt(state, u.x, u.y)].heal;
    if (heal > 0 && u.hp < u.maxHp) {
      const amount = Math.min(u.maxHp - u.hp, Math.max(1, permille(u.maxHp, heal)));
      u.hp += amount;
      events.push({ kind: "terrainHeal", unit: u.id, amount, hpAfter: u.hp });
    }
  }
  state.active = firstSideOfRound(state);
  events.push({ kind: "turn", side: state.active, round: state.round });
}
