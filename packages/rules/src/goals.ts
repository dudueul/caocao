/**
 * Goals and arrivals (ADR 0047). A goal lets a side win before the others are
 * routed: defeat named units, hold out until a round, or reach a tile. Reserve
 * units join at the start of their round, on their own tile or the nearest free
 * one they can stand on.
 */
import { affinityAt, inBounds, isAlive, NEIGHBOURS, unitAt } from "./board.ts";
import type { BattleEvent, BattleState, Cell, Goal, Ruleset, Unit } from "./types.ts";

/** Is this goal met right now? `hold` goals are met once their round has begun. */
export function goalMet(state: BattleState, goal: Goal): boolean {
  const w = goal.when;
  switch (w.kind) {
    case "defeat":
      return w.units.every((id) => {
        const u = state.units.find((v) => v.id === id);
        return u !== undefined && !isAlive(u);
      });
    case "hold":
      return state.round >= w.round;
    case "reach":
      return w.cells.some((c) => {
        const u = unitAt(state, c.x, c.y);
        return u !== undefined && u.side === w.side;
      });
  }
}

/** The first met goal whose side still has living units, as its index. */
export function decidingGoal(state: BattleState): number | null {
  for (let i = 0; i < state.goals.length; i++) {
    const g = state.goals[i] as Goal;
    const alive = state.units.some((u) => u.side === g.side && isAlive(u));
    if (alive && goalMet(state, g)) return i;
  }
  return null;
}

/** Ends the battle if a goal is met. Returns true when it did. */
export function endByGoal(state: BattleState, events: BattleEvent[]): boolean {
  const goal = decidingGoal(state);
  if (goal === null) return false;
  state.winner = (state.goals[goal] as Goal).side;
  events.push({ kind: "battleEnd", winner: state.winner, goal });
  return true;
}

/**
 * Where an arriving unit lands: its own tile if free and passable for it,
 * otherwise the nearest such tile, searched outward in neighbour order.
 */
export function landingCell(state: BattleState, rules: Ruleset, unit: Unit): Cell | null {
  const w = state.map.width;
  const seen = new Set<number>([unit.y * w + unit.x]);
  let frontier: Cell[] = [{ x: unit.x, y: unit.y }];
  while (frontier.length > 0) {
    for (const c of frontier) {
      if (!unitAt(state, c.x, c.y) && affinityAt(rules, state, unit, c.x, c.y).move !== null)
        return c;
    }
    const next: Cell[] = [];
    for (const c of frontier)
      for (const n of NEIGHBOURS) {
        const x = c.x + n.dx;
        const y = c.y + n.dy;
        if (!inBounds(state, x, y) || seen.has(y * w + x)) continue;
        seen.add(y * w + x);
        next.push({ x, y });
      }
    frontier = next;
  }
  return null;
}

/** Brings in every reserve unit due this round. */
export function bringArrivals(state: BattleState, rules: Ruleset, events: BattleEvent[]): void {
  const due = state.reserve.filter((a) => a.round <= state.round);
  if (due.length === 0) return;
  state.reserve = state.reserve.filter((a) => a.round > state.round);
  for (const a of due) {
    const at = landingCell(state, rules, a.unit);
    if (!at) continue; // nowhere to stand: the unit never arrives
    const unit: Unit = { ...a.unit, x: at.x, y: at.y };
    state.units.push(unit);
    events.push({ kind: "arrived", unit: unit.id, at });
  }
  state.units.sort((a, b) => (a.id < b.id ? -1 : a.id > b.id ? 1 : 0));
}
