/**
 * Movement: Dijkstra over per-family terrain costs (ADR 0023) with soft zones of
 * control (ADR 0026). Entering a tile next to an enemy ends movement, except that
 * skirmishers (cavalry, horse archers, bandits) may pass through one such tile.
 * Enemies block their tile; allies can be passed through but not stopped on.
 */
import { affinityAt, classOf, inBounds, isZoc, NEIGHBOURS, unitAt } from "./board.ts";
import { conditionModifier, hasBlock } from "./conditions.ts";
import type { BattleState, Cell, Ruleset, Unit } from "./types.ts";

export interface Reach {
  readonly x: number;
  readonly y: number;
  readonly cost: number;
}

interface Node {
  x: number;
  y: number;
  cost: number;
  skipUsed: number;
  stopped: boolean;
}

/** Tiles the unit can end its move on (including its own tile), sorted by y, then x. */
export function reachable(state: BattleState, rules: Ruleset, unit: Unit): Reach[] {
  const cls = classOf(rules, unit);
  // Conditions change the move budget; a binding condition (마비) pins the unit.
  const budget = hasBlock(rules, unit, "move")
    ? 0
    : Math.max(0, cls.move + conditionModifier(rules, unit, "move"));
  const width = state.map.width;
  const best = new Map<number, number>();
  const frontier: Node[] = [{ x: unit.x, y: unit.y, cost: 0, skipUsed: 0, stopped: false }];
  best.set((unit.y * width + unit.x) * 2, 0);
  const endCost = new Map<number, number>();

  while (frontier.length > 0) {
    // Deterministic pick: lowest cost, then y, x, skipUsed.
    let pick = 0;
    for (let i = 1; i < frontier.length; i++) {
      const a = frontier[i] as Node;
      const b = frontier[pick] as Node;
      if (
        a.cost < b.cost ||
        (a.cost === b.cost &&
          (a.y < b.y || (a.y === b.y && (a.x < b.x || (a.x === b.x && a.skipUsed < b.skipUsed)))))
      ) {
        pick = i;
      }
    }
    const node = frontier.splice(pick, 1)[0] as Node;
    const tile = node.y * width + node.x;
    const occupant = unitAt(state, node.x, node.y);
    if (occupant === undefined || occupant.id === unit.id) {
      const prev = endCost.get(tile);
      if (prev === undefined || node.cost < prev) endCost.set(tile, node.cost);
    }
    if (node.stopped) continue;

    for (const n of NEIGHBOURS) {
      const x = node.x + n.dx;
      const y = node.y + n.dy;
      if (!inBounds(state, x, y)) continue;
      const other = unitAt(state, x, y);
      if (other !== undefined && other.side !== unit.side) continue;
      const step = affinityAt(rules, state, unit, x, y).move;
      if (step === null) continue;
      const cost = node.cost + step;
      if (cost > budget) continue;
      let skipUsed = node.skipUsed;
      let stopped = false;
      if (isZoc(state, unit.side, x, y)) {
        if (cls.skirmisher && skipUsed === 0) skipUsed = 1;
        else stopped = true;
      }
      const key = (y * width + x) * 2 + skipUsed;
      const seen = best.get(key);
      if (seen !== undefined && seen <= cost) continue;
      best.set(key, cost);
      frontier.push({ x, y, cost, skipUsed, stopped });
    }
  }

  const out: Reach[] = [];
  for (const [tile, cost] of endCost)
    out.push({ x: tile % width, y: Math.floor(tile / width), cost });
  return out.sort((a, b) => a.y - b.y || a.x - b.x);
}

export function canReach(state: BattleState, rules: Ruleset, unit: Unit, to: Cell): boolean {
  return reachable(state, rules, unit).some((r) => r.x === to.x && r.y === to.y);
}
