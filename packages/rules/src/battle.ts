/**
 * Battle lifecycle: create a battle, list legal commands, and apply a command.
 * `apply` is a pure function (state, command) → (new state, events): the client
 * runs it for solo play and the MatchDO runs it as the referee (ADR 0004).
 * Turn order is a policy (ADR 0003): side phases, or alternating single-unit
 * activations whose starting side alternates every round.
 */
import {
  classOf,
  hostile,
  inBounds,
  inRange,
  isAlive,
  livingLeaders,
  livingSides,
  terrainAt,
  unitAt,
  unitById,
} from "./board.ts";
import { defeat, resolveAttack } from "./combat.ts";
import { endTurn, hasBlock } from "./conditions.ts";
import {
  acceptChance,
  duelBlocked,
  duelsAllowed,
  endDuel,
  fireDuels,
  playBout,
  refused,
  STANCES,
  scriptedFor,
} from "./duel.ts";
import { bringArrivals, endByGoal } from "./goals.ts";
import { permille } from "./math.ts";
import { canReach, reachable } from "./move.ts";
import { Prng, seedFromString } from "./prng.ts";
import { checkShifts, fireShifts } from "./shifts.ts";
import { canCast, castStratagem, stratagemDef } from "./stratagem.ts";
import { forMode } from "./tuning.ts";
import type {
  BattleEvent,
  BattleMap,
  BattleState,
  BurningTile,
  Command,
  Goal,
  ModeId,
  Ruleset,
  ScriptedDuel,
  Shift,
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
  /** A named officer, who can fight a 일기토 (ADR 0071). */
  named?: boolean;
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
  /** Map changes during the battle: floods, opened gates (ADR 0062). */
  shifts?: Shift[];
  /** Tiles already on fire when the battle begins (복양's burning streets, ADR 0062). */
  burning?: BurningTile[];
  /** Each side's team; sides on one team are allies (ADR 0066). Default: every side alone. */
  teams?: number[];
  /** The order sides take their turns in; default: by side number. */
  order?: number[];
  /** Duels the story stages, ending as history did (ADR 0071). */
  duels?: ScriptedDuel[];
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
        ...(cls.family === "structure" ? { structure: true as const } : {}),
        ...(s.named && cls.family !== "structure" ? { named: true as const } : {}),
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
  const range = Array.from({ length: sides }, (_, i) => i);
  const teams = range.map((s) => setup.teams?.[s] ?? s);
  const order = (setup.order ?? range).filter((s) => s < sides);
  if (order.length !== sides || range.some((s) => !order.includes(s)))
    throw new Error("turn order must list every side once");
  const state: BattleState = {
    version: 7,
    mode,
    map: { width: setup.map.width, height: setup.map.height, tiles: [...setup.map.tiles] },
    weather: setup.weather,
    policy: setup.policy,
    sides,
    teams,
    order,
    round: 1,
    roundLimit: setup.roundLimit,
    active: 0,
    units,
    burning: [],
    goals,
    reserve,
    shifts: [],
    duels: checkDuels(setup.duels ?? [], ids),
    duel: null,
    prng: seedFromString(setup.seed),
    winner: null,
  };
  const shifts = (setup.shifts ?? []).map((sh) => structuredClone(sh));
  checkShifts(state, rules, shifts);
  state.shifts = shifts;
  const fire = new Map<number, BurningTile>();
  for (const b of setup.burning ?? []) {
    if (b.x < 0 || b.y < 0 || b.x >= setup.map.width || b.y >= setup.map.height)
      throw new Error("a burning tile is off the map");
    if (!Number.isSafeInteger(b.rounds) || b.rounds < 1)
      throw new Error("fire must burn ≥ 1 round");
    fire.set(b.y * setup.map.width + b.x, { x: b.x, y: b.y, rounds: b.rounds });
  }
  state.burning = [...fire.entries()].sort((a, b) => a[0] - b[0]).map(([, b]) => b);
  state.active = firstSideOfRound(state);
  return state;
}

/** Story duels must name units of the battle; ids are unique; a played duel has one challenger. */
function checkDuels(list: readonly ScriptedDuel[], ids: ReadonlySet<string>): ScriptedDuel[] {
  const seen = new Set<string>();
  for (const d of list) {
    if (seen.has(d.id)) throw new Error(`duel ${d.id} twice`);
    seen.add(d.id);
    if (d.a.length === 0) throw new Error(`duel ${d.id} has no challenger`);
    for (const id of [...d.a, d.b]) if (!ids.has(id)) throw new Error(`duel ${d.id} names ${id}`);
    if (d.a.includes(d.b)) throw new Error(`duel ${d.id} pits a unit against itself`);
    if (d.play && d.a.length !== 1) throw new Error(`played duel ${d.id} needs one challenger`);
    if (d.play && d.when.kind !== "adjacent")
      throw new Error(`played duel ${d.id} is offered when adjacent`);
    if (d.when.kind === "arrived" && !ids.has(d.when.unit))
      throw new Error(`duel ${d.id} waits for an unknown unit`);
    if (d.when.kind === "round" && (!Number.isSafeInteger(d.when.round) || d.when.round < 1))
      throw new Error(`duel ${d.id} has a bad round`);
  }
  return list.map((d) => structuredClone(d));
}

/** Living units of `side` that have not acted this round. */
export function readyUnits(state: BattleState, side: number): Unit[] {
  return state.units.filter((u) => u.side === side && isAlive(u) && !u.acted && !u.structure);
}

/** Sides with living units, in turn order. */
function livingInOrder(state: BattleState): number[] {
  const living = new Set(livingSides(state));
  return state.order.filter((s) => living.has(s));
}

function firstSideOfRound(state: BattleState): number {
  const living = livingInOrder(state);
  if (living.length === 0) return 0;
  if (state.policy === "sidePhases") return living[0] as number;
  return living[(state.round - 1) % living.length] as number;
}

/** The battle is over when at most one team's leader still has living units (ADR 0066). */
function endByRout(state: BattleState, events: BattleEvent[]): boolean {
  const leaders = livingLeaders(state);
  if (leaders.length > 1) return false;
  state.winner = leaders[0] ?? -1;
  events.push({ kind: "battleEnd", winner: state.winner });
  return true;
}

/** Every legal command for the side to act, in a deterministic order. */
export function legalCommands(state: BattleState, baseRules: Ruleset): Command[] {
  if (state.winner !== null) return [];
  const rules = forMode(baseRules, state.mode);
  const out: Command[] = [];
  if (state.duel) {
    for (const stance of STANCES) out.push({ kind: "bout", stance });
    if (!state.duel.script) out.push({ kind: "withdraw" });
    return out;
  }
  const duels = duelsAllowed(rules);
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
        if (target && hostile(state, target.side, u.side) && target.id !== u.id) {
          out.push({ kind: "act", unit: u.id, to, action: { kind: "attack", target: target.id } });
        }
      }
      if (duels && u.named) {
        for (const d of [
          { dx: 0, dy: -1 },
          { dx: 1, dy: 0 },
          { dx: 0, dy: 1 },
          { dx: -1, dy: 0 },
        ]) {
          const target = unitAt(state, to.x + d.dx, to.y + d.dy);
          if (target && duelBlocked(rules, state, u, target, to) === null)
            out.push({ kind: "act", unit: u.id, to, action: { kind: "duel", target: target.id } });
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

  if (next.duel) {
    const duel = next.duel;
    if (command.kind !== "bout" && command.kind !== "withdraw")
      return { ok: false, reason: "a duel is under way" };
    if (command.kind === "withdraw" && duel.script)
      return { ok: false, reason: "history does not let this duel be left" };
    if (command.kind === "bout" && !STANCES.includes(command.stance))
      return { ok: false, reason: "unknown stance" };
    const over =
      command.kind === "withdraw" || playBout(rules, next, prng, duel, command.stance, events);
    if (over) {
      const challenger = unitById(next, duel.a);
      endDuel(rules, next, duel, command.kind === "withdraw" ? duel.a : null, events);
      challenger.acted = true;
      endTurn(rules, challenger, events);
      advance(next, rules, prng, events);
    }
    next.prng = prng.state;
    return { ok: true, state: next, events };
  }
  if (command.kind === "bout" || command.kind === "withdraw")
    return { ok: false, reason: "no duel under way" };

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
    } else if (action.kind === "duel") {
      const target = unitById(next, action.target);
      const script = scriptedFor(next, unit.id, target.id);
      const accepted = script !== null || prng.chance(acceptChance(rules, unit, target));
      events.push({
        kind: "challenge",
        a: unit.id,
        b: target.id,
        accepted,
        script: script?.id ?? null,
      });
      if (accepted) {
        // The battle waits while the duel is fought, bout by bout.
        next.duel = { a: unit.id, b: target.id, bout: 0, script: script?.id ?? null };
        next.prng = prng.state;
        return { ok: true, state: next, events };
      }
      refused(rules, next, target, events);
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
    if (!hostile(state, target.side, unit.side)) return "cannot attack an ally";
    if (!inRange(classOf(rules, unit).range, c.to, target)) return "target out of range";
  } else if (a.kind === "stratagem") {
    const def = rules.stratagems[a.id];
    if (!def) return "unknown stratagem";
    if (!canCast(rules, state, unit, def, c.to, a.at)) return "cannot cast here";
  } else if (a.kind === "duel") {
    const target = state.units.find((u) => u.id === a.target);
    if (!target) return "no such target";
    const blocked = duelBlocked(rules, state, unit, target, c.to);
    if (blocked) return blocked;
  }
  return null;
}

/** After an action: end the battle, or pass the turn per the turn policy. */
function advance(state: BattleState, rules: Ruleset, prng: Prng, events: BattleEvent[]): void {
  fireShifts(state, rules, events);
  fireDuels(rules, state, prng, events);
  if (endByGoal(state, events)) return;
  if (endByRout(state, events)) return;
  const at = state.order.indexOf(state.active);
  if (state.policy === "sidePhases") {
    if (readyUnits(state, state.active).length > 0) return;
    const nextSide = state.order.slice(at + 1).find((s) => readyUnits(state, s).length > 0);
    if (nextSide !== undefined) {
      state.active = nextSide;
      events.push({ kind: "turn", side: nextSide, round: state.round });
      return;
    }
  } else {
    const prev = state.active;
    const n = state.order.length;
    for (let step = 1; step <= n; step++) {
      const s = state.order[(at + step) % n] as number;
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
  const before = events.length;
  bringArrivals(state, rules, events);
  const arrived = events.slice(before).flatMap((e) => (e.kind === "arrived" ? [e.unit] : []));
  fireShifts(state, rules, events);
  fireDuels(rules, state, prng, events, arrived);
  if (endByGoal(state, events)) return;
  if (endByRout(state, events)) return;
  for (const u of state.units) {
    if (!isAlive(u) || u.structure) continue;
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
