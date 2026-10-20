/**
 * The route a unit walks in the move animation: the cheapest steps by terrain,
 * around enemies, avoiding zones of control until the last tile. Display only;
 * the rules alone decide where a unit may go.
 */
import {
  affinityAt,
  type BattleState,
  type Cell,
  hostile,
  isZoc,
  type Ruleset,
  type Unit,
} from "@tk/rules";

const STEPS = [
  [0, -1],
  [1, 0],
  [0, 1],
  [-1, 0],
] as const;

export function walkPath(state: BattleState, rules: Ruleset, unit: Unit, to: Cell): Cell[] {
  const w = state.map.width;
  const h = state.map.height;
  const start = unit.y * w + unit.x;
  const goal = to.y * w + to.x;
  if (start === goal) return [{ x: unit.x, y: unit.y }];
  const enemy = new Set(
    state.units
      .filter((u) => u.hp > 0 && hostile(state, u.side, unit.side))
      .map((u) => u.y * w + u.x),
  );
  const dist = new Map<number, number>([[start, 0]]);
  const prev = new Map<number, number>();
  const open = [start];
  while (open.length > 0) {
    let best = 0;
    for (let i = 1; i < open.length; i++)
      if ((dist.get(open[i] ?? 0) ?? 0) < (dist.get(open[best] ?? 0) ?? 0)) best = i;
    const cur = open.splice(best, 1)[0] ?? goal;
    if (cur === goal) break;
    const cx = cur % w;
    const cy = (cur - cx) / w;
    for (const [dx, dy] of STEPS) {
      const x = cx + dx;
      const y = cy + dy;
      if (x < 0 || y < 0 || x >= w || y >= h) continue;
      const k = y * w + x;
      if (enemy.has(k)) continue;
      const step = affinityAt(rules, state, unit, x, y).move;
      if (step === null) continue;
      const zoc = k !== goal && isZoc(state, unit.side, x, y) ? 100 : 0;
      const d = (dist.get(cur) ?? 0) + step + zoc;
      if (d < (dist.get(k) ?? Number.POSITIVE_INFINITY)) {
        dist.set(k, d);
        prev.set(k, cur);
        open.push(k);
      }
    }
  }
  if (!prev.has(goal)) return [{ x: unit.x, y: unit.y }, to];
  const out: Cell[] = [];
  for (let k: number | undefined = goal; k !== undefined; k = prev.get(k)) {
    out.push({ x: k % w, y: Math.floor(k / w) });
    if (k === start) break;
  }
  return out.reverse();
}
