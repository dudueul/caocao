/** Read-only queries over the battle state: tiles, units, ranges. */
import type {
  Affinity,
  BattleState,
  Cell,
  ClassDef,
  Offset,
  Ruleset,
  TerrainId,
  Unit,
} from "./types.ts";

export const NEIGHBOURS: readonly Offset[] = [
  { dx: 0, dy: -1 },
  { dx: 1, dy: 0 },
  { dx: 0, dy: 1 },
  { dx: -1, dy: 0 },
];

export function inBounds(state: BattleState, x: number, y: number): boolean {
  return x >= 0 && y >= 0 && x < state.map.width && y < state.map.height;
}

export function terrainAt(state: BattleState, x: number, y: number): TerrainId {
  const t = state.map.tiles[y * state.map.width + x];
  if (t === undefined) throw new RangeError(`no tile at ${x},${y}`);
  return t;
}

export function classOf(rules: Ruleset, unit: Unit): ClassDef {
  const c = rules.classes[unit.classId];
  if (!c) throw new Error(`unknown class ${unit.classId}`);
  return c;
}

export function affinityAt(
  rules: Ruleset,
  state: BattleState,
  unit: Unit,
  x: number,
  y: number,
): Affinity {
  const family = classOf(rules, unit).family;
  const row = rules.affinity[family];
  if (!row) throw new Error(`no terrain affinity for family ${family}`);
  return row[terrainAt(state, x, y)];
}

export function isAlive(unit: Unit): boolean {
  return unit.hp > 0;
}

export function unitById(state: BattleState, id: string): Unit {
  const u = state.units.find((v) => v.id === id);
  if (!u) throw new Error(`unknown unit ${id}`);
  return u;
}

export function unitAt(state: BattleState, x: number, y: number): Unit | undefined {
  return state.units.find((u) => isAlive(u) && u.x === x && u.y === y);
}

/** Sides with living units that fight; gates and other works don't count (ADR 0069). */
export function livingSides(state: BattleState): number[] {
  const sides: number[] = [];
  for (const u of state.units)
    if (isAlive(u) && !u.structure && !sides.includes(u.side)) sides.push(u.side);
  return sides.sort((a, b) => a - b);
}

/** Are sides `a` and `b` on one team (ADR 0066)? A side is always its own friend. */
export function friendly(state: BattleState, a: number, b: number): boolean {
  return a === b || (state.teams[a] ?? a) === (state.teams[b] ?? b);
}

export function hostile(state: BattleState, a: number, b: number): boolean {
  return !friendly(state, a, b);
}

/** The team's leader: its lowest-numbered side. */
export function leaderOf(state: BattleState, side: number): number {
  for (let s = 0; s < state.sides; s++) if (friendly(state, s, side)) return s;
  return side;
}

/**
 * Leaders whose sides still have living units: the teams still in the
 * battle. Allies fight on, but a team whose leader is routed has lost.
 */
export function livingLeaders(state: BattleState): number[] {
  return livingSides(state).filter((s) => leaderOf(state, s) === s);
}

/** Is `target` inside the range offsets measured from `from`? */
export function inRange(range: readonly Offset[], from: Cell, target: Cell): boolean {
  const dx = target.x - from.x;
  const dy = target.y - from.y;
  return range.some((o) => o.dx === dx && o.dy === dy);
}

/** Tiles next to a living enemy of `side` (zones of control, ADR 0026). */
export function isZoc(state: BattleState, side: number, x: number, y: number): boolean {
  return NEIGHBOURS.some((n) => {
    const u = unitAt(state, x + n.dx, y + n.dy);
    return u !== undefined && !u.structure && hostile(state, u.side, side);
  });
}

export function cellsAround(center: Cell, offsets: readonly Offset[]): Cell[] {
  return offsets.map((o) => ({ x: center.x + o.dx, y: center.y + o.dy }));
}
