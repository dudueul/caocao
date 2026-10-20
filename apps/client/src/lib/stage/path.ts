/**
 * Paths across a stage (ADR 0063): the shortest walk on open tiles, four ways.
 * Other actors never block the way; a taken destination makes the walker stop
 * on the last free tile before it. The start and the destination may be off
 * the walkable ground (a gate in the wall, the map edge).
 */
import type { Cell } from "@tk/rules";

const STEPS: readonly Cell[] = [
  { x: 0, y: -1 },
  { x: 1, y: 0 },
  { x: 0, y: 1 },
  { x: -1, y: 0 },
];

export const same = (a: Cell, b: Cell): boolean => a.x === b.x && a.y === b.y;

/** A small binary heap of [cost, state] pairs, cheapest first. */
function push(h: [number, number][], e: [number, number]): void {
  h.push(e);
  let i = h.length - 1;
  while (i > 0) {
    const p = (i - 1) >> 1;
    if ((h[p] as [number, number])[0] <= e[0]) break;
    h[i] = h[p] as [number, number];
    i = p;
  }
  h[i] = e;
}

function pop(h: [number, number][]): [number, number] {
  const top = h[0] as [number, number];
  const last = h.pop() as [number, number];
  if (h.length > 0) {
    let i = 0;
    for (;;) {
      const l = i * 2 + 1;
      const r = l + 1;
      let m = i;
      let mv = last[0];
      if (l < h.length && (h[l] as [number, number])[0] < mv) {
        m = l;
        mv = (h[l] as [number, number])[0];
      }
      if (r < h.length && (h[r] as [number, number])[0] < mv) m = r;
      if (m === i) break;
      h[i] = h[m] as [number, number];
      i = m;
    }
    h[i] = last;
  }
  return top;
}

/**
 * The tiles from `from` to `to`, both included: the shortest way, and of the
 * shortest ways the one with the fewest turns. `open` says whether a tile can
 * be walked on; `extra` adds a cost to stepping on a tile (someone standing
 * there), so walkers go around people unless there is no other way. When no
 * way exists at all, the walker goes straight there, across whatever is in
 * between (a script may ask for a walk the map does not allow), unless
 * `strict`, when the walker stays put.
 */
export function findPath(
  from: Cell,
  to: Cell,
  width: number,
  height: number,
  open: (c: Cell) => boolean,
  strict = false,
  extra: (c: Cell) => number = () => 0,
): Cell[] {
  if (same(from, to)) return [from];
  const inside = (x: number, y: number) => x >= 0 && y >= 0 && x < width && y < height;
  // A state is a tile and the way the walker came into it (4 = where it starts).
  const n = width * height * 5;
  const dist = new Float64Array(n).fill(Number.POSITIVE_INFINITY);
  const prev = new Int32Array(n).fill(-1);
  const goal = to.y * width + to.x;
  const start = (from.y * width + from.x) * 5 + 4;
  dist[start] = 0;
  const heap: [number, number][] = [[0, start]];
  while (heap.length > 0) {
    const [d, st] = pop(heap);
    if (d > (dist[st] as number)) continue;
    const cell = Math.floor(st / 5);
    if (cell === goal) {
      const out: Cell[] = [];
      for (let s = st; s >= 0; s = prev[s] as number) {
        const c = Math.floor(s / 5);
        out.push({ x: c % width, y: Math.floor(c / width) });
      }
      return out.reverse();
    }
    const dir = st % 5;
    const cx = cell % width;
    const cy = Math.floor(cell / width);
    for (let k = 0; k < 4; k++) {
      const step = STEPS[k] as Cell;
      const nx = cx + step.x;
      const ny = cy + step.y;
      if (!inside(nx, ny)) continue;
      const nc = ny * width + nx;
      const c = { x: nx, y: ny };
      if (nc !== goal && !open(c)) continue;
      // Ten a step, one a turn, and a hair more for stepping away from the goal.
      const turn = dir !== 4 && dir !== k ? 1 : 0;
      const toward =
        (step.x !== 0 && step.x === Math.sign(to.x - cx)) ||
        (step.y !== 0 && step.y === Math.sign(to.y - cy));
      const cost = d + 10 + turn + (toward ? 0 : 0.01) + (nc === goal ? 0 : extra(c));
      const ns = nc * 5 + k;
      if (cost < (dist[ns] as number)) {
        dist[ns] = cost;
        prev[ns] = st;
        push(heap, [cost, ns]);
      }
    }
  }
  return strict ? [from] : straight(from, to);
}

/** An L-shaped line of tiles: across, then down. */
export function straight(from: Cell, to: Cell): Cell[] {
  const out: Cell[] = [from];
  let { x, y } = from;
  while (x !== to.x) {
    x += Math.sign(to.x - x);
    out.push({ x, y });
  }
  while (y !== to.y) {
    y += Math.sign(to.y - y);
    out.push({ x, y });
  }
  return out;
}

/** The nearest open, free tile next to `c` as seen from `from` (to stand by someone). */
export function besideOf(
  c: Cell,
  from: Cell,
  open: (c: Cell) => boolean,
  free: (c: Cell) => boolean,
): Cell | null {
  let best: Cell | null = null;
  let bestD = Number.POSITIVE_INFINITY;
  for (const d of STEPS) {
    const n = { x: c.x + d.x, y: c.y + d.y };
    if (!same(n, from) && (!open(n) || !free(n))) continue;
    const dist = Math.abs(n.x - from.x) + Math.abs(n.y - from.y);
    if (dist < bestD) {
      best = n;
      bestD = dist;
    }
  }
  return best;
}
