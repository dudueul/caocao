/**
 * Reads a map before it is painted (ADR 0074): how walls join, where gates and
 * castle grounds are, which tiles make a town or a camp, and where each house,
 * tower, tent and field goes. Pure and deterministic: the same map always gets
 * the same plan, so a tile's look depends only on the map and its position.
 *
 * Built things follow the late Eastern Han: rammed-earth walls with timber gate
 * towers and corner towers, walled courtyard compounds (tiled roofs for the
 * better-off, thatch for the poor), watchtowers, granaries and wells, millet
 * and wheat strips, timber palisades and earth ramparts round camps.
 */
import { hash } from "./noise.ts";

export interface MapLike {
  readonly width: number;
  readonly height: number;
  readonly tiles: readonly string[];
}

// Tile classes.
export const PLAIN = 0;
export const GRASS = 1;
export const FOREST = 2;
export const HILLS = 3;
export const MOUNTAIN = 4;
export const FORD = 5;
export const WATER = 6;
export const BRIDGE = 7;
export const VILLAGE = 8;
export const FORT = 9;
export const WALL = 10;
export const CAMP = 11;
/** A stage floor (boards, paving, carpet): laid as a hard tile from its own art. */
export const FLOOR = 12;

const CLASS: Record<string, number> = {
  plain: PLAIN,
  grass: GRASS,
  forest: FOREST,
  hills: HILLS,
  mountain: MOUNTAIN,
  ford: FORD,
  water: WATER,
  bridge: BRIDGE,
  village: VILLAGE,
  fort: FORT,
  wall: WALL,
  camp: CAMP,
};

// Wall joins.
export const N = 1;
export const E = 2;
export const S = 4;
export const W = 8;

/** A gate in a wall line: a timber gate tower over a fort, a lighter one over a village, or a gap. */
export type GateKind = "tower" | "town" | "open";

export interface Gate {
  readonly kind: GateKind;
  /** The way the wall runs through the gate ("ew": the wall runs east-west, the road north-south). */
  readonly run: "ew" | "ns";
  /** The gate's tiles, in order along the run, as indices. */
  readonly tiles: readonly number[];
}

export interface BridgeTile {
  /** The way the deck runs. */
  readonly axis: "ew" | "ns";
  /** Over water (or a ford); a dry causeway otherwise. */
  readonly wet: boolean;
  /** The deck continues into the next tile at each end (else it lands on a bank). */
  readonly before: boolean;
  readonly after: boolean;
}

export type TreeKind = "elm" | "poplar" | "pine" | "willow" | "jujube" | "mulberry";

export type Feature =
  | {
      readonly kind: "compound";
      readonly x: number;
      readonly y: number;
      readonly w: number;
      readonly h: number;
      /** Grey-tiled roofs; thatch otherwise. */
      readonly tiled: boolean;
      /** Buildings round the yard (1–3). */
      readonly rooms: number;
      readonly seed: number;
    }
  | {
      readonly kind: "keep";
      readonly x: number;
      readonly y: number;
      readonly w: number;
      readonly h: number;
      readonly seed: number;
    }
  | {
      readonly kind: "watchtower";
      readonly x: number;
      readonly y: number;
      readonly size: number;
      readonly storeys: number;
      /** On a rammed-earth base with its own small wall (a fort tile). */
      readonly walled: boolean;
      readonly seed: number;
    }
  | {
      readonly kind: "granary";
      readonly x: number;
      readonly y: number;
      readonly size: number;
      readonly round: boolean;
      readonly seed: number;
    }
  | { readonly kind: "well"; readonly x: number; readonly y: number; readonly seed: number }
  | {
      readonly kind: "fields";
      readonly x: number;
      readonly y: number;
      readonly w: number;
      readonly h: number;
      /** Strips run east-west (else north-south). */
      readonly ew: boolean;
      readonly seed: number;
    }
  | { readonly kind: "threshing"; readonly x: number; readonly y: number; readonly seed: number }
  | {
      readonly kind: "tree";
      readonly x: number;
      readonly y: number;
      readonly tree: TreeKind;
      readonly r: number;
      readonly seed: number;
    }
  | {
      readonly kind: "tent";
      readonly x: number;
      readonly y: number;
      readonly w: number;
      readonly h: number;
      readonly style: "ridge" | "round" | "command";
      readonly seed: number;
    }
  | {
      readonly kind: "stockade";
      readonly x: number;
      readonly y: number;
      readonly w: number;
      readonly h: number;
      readonly seed: number;
    }
  | {
      readonly kind: "prop";
      readonly x: number;
      readonly y: number;
      readonly prop: "fire" | "rack" | "banner" | "crates" | "cart" | "haystack";
      readonly seed: number;
    }
  | {
      readonly kind: "street";
      readonly x0: number;
      readonly y0: number;
      readonly x1: number;
      readonly y1: number;
      readonly width: number;
      readonly seed: number;
    }
  | {
      readonly kind: "lane";
      readonly pts: readonly (readonly [number, number])[];
      readonly seed: number;
    };

/** A side of a camp tile that gets a rampart and palisade. */
export interface Fence {
  readonly x: number;
  readonly y: number;
  /** N, E, S or W. */
  readonly side: number;
  /** An opening is left here. */
  readonly gap: boolean;
}

/** Per-tile values the ground field blends between tile centres. */
export interface Tables {
  readonly grass: Float32Array;
  readonly forest: Float32Array;
  readonly hill: Float32Array;
  readonly mount: Float32Array;
  /** How far inside a mountain range (0 at its edge, up to 1 deep inside). */
  readonly massif: Float32Array;
  readonly wet: Float32Array;
  readonly deep: Float32Array;
  readonly ford: Float32Array;
  readonly pave: Float32Array;
  readonly yard: Float32Array;
  readonly camp: Float32Array;
  readonly built: Float32Array;
}

export interface Plan {
  readonly w: number;
  readonly h: number;
  readonly ids: readonly string[];
  readonly cls: Uint8Array;
  /** Inside a walled place (castle grounds or a walled camp). */
  readonly enclosed: Uint8Array;
  /** Wall joins (N|E|S|W) on wall tiles; 16 marks a corner tower, 32 an end. */
  readonly wall: Uint8Array;
  /** Gate index + 1 per tile (0: not a gate). */
  readonly gateAt: Int16Array;
  readonly gates: readonly Gate[];
  readonly bridges: ReadonlyMap<number, BridgeTile>;
  /** Features sorted for drawing; each anchored to a tile. */
  readonly features: readonly Feature[];
  readonly anchors: readonly number[];
  readonly fences: readonly Fence[];
  readonly tables: Tables;
  /** What a tile's look depends on; a change means repainting around it. */
  readonly sig: readonly string[];
}

export const TOWER = 16;
export const END = 32;

const DIRS: readonly (readonly [number, number, number])[] = [
  [0, -1, N],
  [1, 0, E],
  [0, 1, S],
  [-1, 0, W],
];

function opposite(d: number): number {
  return d === N ? S : d === S ? N : d === E ? W : E;
}

export function planMap(map: MapLike, floors: ReadonlySet<string> = new Set()): Plan {
  const { width: w, height: h } = map;
  const n = w * h;
  const ids = map.tiles;
  const cls = new Uint8Array(n);
  for (let i = 0; i < n; i++) {
    const id = ids[i] as string;
    cls[i] = floors.has(id) ? FLOOR : (CLASS[id] ?? PLAIN);
  }
  const inside = (x: number, y: number) => x >= 0 && y >= 0 && x < w && y < h;
  const at = (x: number, y: number) => (inside(x, y) ? (cls[y * w + x] as number) : -1);
  const clampAt = (x: number, y: number) =>
    cls[Math.min(h - 1, Math.max(0, y)) * w + Math.min(w - 1, Math.max(0, x))] as number;

  // ---- gates: short runs of passable tiles between two wall tiles
  const gateable = (c: number) =>
    c === FORT || c === VILLAGE || c === PLAIN || c === GRASS || c === CAMP || c === FLOOR;
  const gateAt = new Int16Array(n);
  const gates: Gate[] = [];
  const runOf = (x: number, y: number, dx: number, dy: number): number[] | null => {
    // Walk back to the run's start, then forward to its end; walls must close both ends.
    let sx = x;
    let sy = y;
    for (let k = 0; k < 3 && gateable(at(sx - dx, sy - dy)); k++) {
      sx -= dx;
      sy -= dy;
    }
    // Walls close both ends, and at least one of them carries on along the run: a gate
    // in a wall line, not the inside of a small yard between two side walls.
    const beyond = (x0: number, y0: number) => at(x0, y0) === WALL || !inside(x0, y0);
    if (at(sx - dx, sy - dy) !== WALL) return null;
    const startRuns = beyond(sx - dx * 2, sy - dy * 2);
    const tiles: number[] = [];
    let cx = sx;
    let cy = sy;
    while (gateable(at(cx, cy)) && tiles.length < 4) {
      tiles.push(cy * w + cx);
      cx += dx;
      cy += dy;
    }
    if (at(cx, cy) !== WALL || tiles.length > 3) return null;
    if (!startRuns && !beyond(cx + dx, cy + dy)) return null;
    return tiles;
  };
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const i = y * w + x;
      if (gateAt[i] || !gateable(cls[i] as number)) continue;
      for (const [dx, dy, run] of [
        [1, 0, "ew"],
        [0, 1, "ns"],
      ] as const) {
        const tiles = runOf(x, y, dx, dy);
        if (!tiles) continue;
        // A run along the wall's own line only: the tiles beside it must not be walls too.
        if (tiles.some((t) => gateAt[t])) continue;
        const kinds = tiles.map((t) => cls[t] as number);
        const kind: GateKind = kinds.includes(FORT)
          ? "tower"
          : kinds.includes(VILLAGE)
            ? "town"
            : "open";
        gates.push({ kind, run, tiles });
        for (const t of tiles) gateAt[t] = gates.length;
        break;
      }
    }
  const isGate = (x: number, y: number) => inside(x, y) && gateAt[y * w + x] !== 0;
  const gateOf = (x: number, y: number) =>
    isGate(x, y) ? (gates[(gateAt[y * w + x] as number) - 1] as Gate) : null;

  // ---- wall joins
  const wall = new Uint8Array(n);
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const i = y * w + x;
      if (cls[i] !== WALL) continue;
      let m = 0;
      for (const [dx, dy, d] of DIRS) {
        const nx = x + dx;
        const ny = y + dy;
        if (!inside(nx, ny)) continue;
        if (at(nx, ny) === WALL) m |= d;
        else {
          const g = gateOf(nx, ny);
          // Gate towers carry the wall line through; a gap leaves the wall ending.
          if (g && g.kind !== "open" && (g.run === "ew") === (d === E || d === W)) m |= d;
        }
      }
      // A run that leaves the map keeps going instead of ending in a tower.
      for (const [dx, dy, d] of DIRS) {
        if (inside(x + dx, y + dy)) continue;
        const o = opposite(d);
        const side = d === N || d === S ? E | W : N | S;
        if (m & o && !(m & side)) m |= d;
      }
      const ns = m & (N | S);
      const ew = m & (E | W);
      const count = (m & N ? 1 : 0) + (m & E ? 1 : 0) + (m & S ? 1 : 0) + (m & W ? 1 : 0);
      if (count >= 3 || (ns && ew)) m |= TOWER;
      else if (count <= 1) m |= END;
      wall[i] = m;
    }

  // ---- castle grounds: walls (or gates) on most sides, both across and along
  const barrier = (x: number, y: number) => at(x, y) === WALL || isGate(x, y);
  const enclosed = new Uint8Array(n);
  const RAYS = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1],
    [1, 1],
    [1, -1],
    [-1, 1],
    [-1, -1],
  ] as const;
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const i = y * w + x;
      if (cls[i] === WALL || gateAt[i]) continue;
      let hits = 0;
      let across = false;
      let along = false;
      for (const [dx, dy] of RAYS) {
        let cx = x + dx;
        let cy = y + dy;
        while (inside(cx, cy) && !barrier(cx, cy)) {
          cx += dx;
          cy += dy;
        }
        if (!inside(cx, cy)) continue;
        hits++;
        if (dy === 0) across = true;
        if (dx === 0) along = true;
      }
      if (hits >= 4 && across && along) enclosed[i] = 1;
    }
  // Lone enclosed specks (a tile hugging a wall corner) don't make a castle.
  for (let i = 0; i < n; i++) {
    if (!enclosed[i]) continue;
    const x = i % w;
    const y = (i - x) / w;
    let near = 0;
    for (const [dx, dy] of DIRS)
      if (inside(x + dx, y + dy) && enclosed[(y + dy) * w + x + dx]) near++;
    if (near === 0) enclosed[i] = 0;
  }

  // ---- bridges
  const wetCls = (c: number) => c === WATER || c === FORD;
  const bridges = new Map<number, BridgeTile>();
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const i = y * w + x;
      if (cls[i] !== BRIDGE) continue;
      const land = (c: number) => c !== -1 && !wetCls(c);
      const okEW = land(clampAt(x - 1, y)) && land(clampAt(x + 1, y));
      const okNS = land(clampAt(x, y - 1)) && land(clampAt(x, y + 1));
      const wetNS = wetCls(at(x, y - 1)) || wetCls(at(x, y + 1));
      // A run of bridge tiles sets the way; else the dry ends; else across the water.
      const runEW = at(x - 1, y) === BRIDGE || at(x + 1, y) === BRIDGE;
      const runNS = at(x, y - 1) === BRIDGE || at(x, y + 1) === BRIDGE;
      const axis: "ew" | "ns" =
        runEW !== runNS
          ? runEW
            ? "ew"
            : "ns"
          : okEW && !okNS
            ? "ew"
            : okNS && !okEW
              ? "ns"
              : wetNS
                ? "ew"
                : "ns";
      const wet = DIRS.some(([dx, dy]) => wetCls(at(x + dx, y + dy)));
      const [dx, dy] = axis === "ew" ? [1, 0] : [0, 1];
      bridges.set(i, {
        axis,
        wet,
        before: clampAt(x - dx, y - dy) === BRIDGE,
        after: clampAt(x + dx, y + dy) === BRIDGE,
      });
    }
  // A bridge next to a wet bridge is wet too (the middle of a long bridge).
  for (let pass = 0; pass < 3; pass++)
    for (const [i, b] of bridges) {
      if (b.wet) continue;
      const x = i % w;
      const y = (i - x) / w;
      if (
        DIRS.some(([dx, dy]) => bridges.get((y + dy) * w + x + dx)?.wet && inside(x + dx, y + dy))
      )
        bridges.set(i, { ...b, wet: true });
    }

  // ---- features
  const features: Feature[] = [];
  const anchors: number[] = [];
  const add = (f: Feature, tile: number) => {
    features.push(f);
    anchors.push(tile);
  };
  const used = new Uint8Array(n);
  const stockade = new Uint8Array(n);

  const components = (pred: (i: number) => boolean): number[][] => {
    const seen = new Uint8Array(n);
    const out: number[][] = [];
    for (let i = 0; i < n; i++) {
      if (seen[i] || !pred(i)) continue;
      const comp: number[] = [];
      const stack = [i];
      seen[i] = 1;
      while (stack.length) {
        const c = stack.pop() as number;
        comp.push(c);
        const cx = c % w;
        const cy = (c - cx) / w;
        for (const [dx, dy] of DIRS) {
          const nx = cx + dx;
          const ny = cy + dy;
          if (!inside(nx, ny)) continue;
          const j = ny * w + nx;
          if (!seen[j] && pred(j)) {
            seen[j] = 1;
            stack.push(j);
          }
        }
      }
      comp.sort((a, b) => a - b);
      out.push(comp);
    }
    return out;
  };
  const cx = (i: number) => (i % w) + 0.5;
  const cy = (i: number) => Math.floor(i / w) + 0.5;
  /** Picks k tiles spread apart (farthest-point), deterministic. */
  const spread = (
    cands: readonly number[],
    k: number,
    seed: number,
    avoid: readonly number[] = [],
  ) => {
    const picked: number[] = [];
    const pool = [...cands].sort((a, b) => hash(a, seed, 3) - hash(b, seed, 3));
    while (picked.length < k && pool.length) {
      let best = 0;
      let bestD = -1;
      for (let p = 0; p < pool.length; p++) {
        const c = pool[p] as number;
        let d = 1e9;
        for (const q of [...picked, ...avoid])
          d = Math.min(d, (cx(c) - cx(q)) ** 2 + (cy(c) - cy(q)) ** 2);
        if (d === 1e9) d = 1e8 + hash(c, seed, 5);
        if (d > bestD + 1e-9) {
          bestD = d;
          best = p;
        }
      }
      picked.push(pool.splice(best, 1)[0] as number);
    }
    return picked;
  };
  const nearestTo = (cands: readonly number[], x: number, y: number) => {
    let best = cands[0] as number;
    let bd = 1e9;
    for (const c of cands) {
      const d = (cx(c) - x) ** 2 + (cy(c) - y) ** 2 + hash(c, 7, 7) * 0.01;
      if (d < bd) {
        bd = d;
        best = c;
      }
    }
    return best;
  };
  const centroid = (tiles: readonly number[]) => {
    let sx = 0;
    let sy = 0;
    for (const t of tiles) {
      sx += cx(t);
      sy += cy(t);
    }
    return [sx / tiles.length, sy / tiles.length] as const;
  };
  const jitter = (i: number, seed: number, amp: number) => (hash(i, seed, 41) - 0.5) * 2 * amp;

  // Forts that are not gates: a keep on a rammed-earth platform, a walled
  // watchtower, or a stockade in a camp.
  const forts = components((i) => cls[i] === FORT && !gateAt[i]);
  for (const comp of forts) {
    let minX = w;
    let minY = h;
    let maxX = 0;
    let maxY = 0;
    for (const t of comp) {
      const x = t % w;
      const y = (t - x) / w;
      minX = Math.min(minX, x);
      minY = Math.min(minY, y);
      maxX = Math.max(maxX, x);
      maxY = Math.max(maxY, y);
    }
    const bw = maxX - minX + 1;
    const bh = maxY - minY + 1;
    let campNear = 0;
    let around = 0;
    for (const t of comp) {
      const x = t % w;
      const y = (t - x) / w;
      for (let dy = -1; dy <= 1; dy++)
        for (let dx = -1; dx <= 1; dx++) {
          const c = at(x + dx, y + dy);
          if (c === -1 || c === FORT) continue;
          around++;
          if (c === CAMP) campNear++;
        }
    }
    const seed = comp[0] as number;
    for (const t of comp) used[t] = 1;
    const campish = campNear * 2 >= around;
    for (const t of comp) stockade[t] = campish ? 1 : 0;
    if (campish) {
      add({ kind: "stockade", x: minX, y: minY, w: bw, h: bh, seed }, seed);
    } else if (bw * bh === comp.length && bw <= 2 && bh <= 2 && comp.length > 1) {
      add({ kind: "keep", x: minX, y: minY, w: bw, h: bh, seed }, seed);
    } else if (comp.length === 1 && enclosed[seed]) {
      add({ kind: "keep", x: minX, y: minY, w: 1, h: 1, seed }, seed);
    } else {
      for (const t of comp)
        add(
          {
            kind: "watchtower",
            x: cx(t),
            y: cy(t),
            size: 0.5,
            storeys: 2 + (hash(t, 1, 9) < 0.5 ? 1 : 0),
            walled: true,
            seed: t,
          },
          t,
        );
    }
  }

  // Castle grounds: a few compounds among paved courtyards and streets.
  const castles = components((i) => enclosed[i] === 1 && cls[i] !== WALL && !gateAt[i]);
  const castleOf = new Int16Array(n).fill(-1);
  castles.forEach((comp, k) => {
    for (const t of comp) castleOf[t] = k;
  });
  for (const comp of castles) {
    const seed = comp[0] as number;
    const villages = comp.filter((t) => cls[t] === VILLAGE && !used[t]);
    const open = comp.filter((t) => !used[t] && (cls[t] === VILLAGE || cls[t] === PLAIN));
    if (villages.length === 0 && !comp.some((t) => cls[t] === FORT)) continue;
    // A walled camp keeps its tents and beaten earth: no streets or courtyards.
    if (comp.filter((t) => cls[t] === CAMP).length * 2 > comp.length) continue;
    // Streets from each gate into the grounds.
    for (const g of gates) {
      const first = g.tiles[0] as number;
      const last = g.tiles[g.tiles.length - 1] as number;
      const mid = (cx(first) + cx(last)) / 2;
      const midY = (cy(first) + cy(last)) / 2;
      for (const s of [-1, 1]) {
        const [dx, dy] = g.run === "ew" ? [0, s] : [s, 0];
        const fx = Math.floor(mid + dx * 0.9);
        const fy = Math.floor(midY + dy * 0.9);
        if (!inside(fx, fy) || castleOf[fy * w + fx] !== castleOf[seed]) continue;
        let len = 0;
        let px = fx;
        let py = fy;
        while (inside(px, py) && castleOf[py * w + px] === castleOf[seed] && len < 6) {
          len++;
          px += dx;
          py += dy;
        }
        const width = g.kind === "open" ? 0.5 : 0.62;
        add(
          {
            kind: "street",
            x0: mid,
            y0: midY,
            x1: mid + dx * (len + 0.4),
            y1: midY + dy * (len + 0.4),
            width,
            seed: first,
          },
          fy * w + fx,
        );
      }
    }
    const nb = Math.max(1, Math.round(villages.length / 6.5));
    const keeps = comp.filter((t) => cls[t] === FORT);
    const homes = spread(villages, nb, seed, keeps);
    for (const t of homes) {
      used[t] = 1;
      const bw = 0.86 + hash(t, 2, 11) * 0.2;
      const bh = 0.74 + hash(t, 3, 11) * 0.18;
      add(
        {
          kind: "compound",
          x: cx(t) + jitter(t, 4, 0.06) - bw / 2,
          y: cy(t) + jitter(t, 5, 0.05) - bh / 2,
          w: bw,
          h: bh,
          tiled: hash(t, 6, 11) < 0.85,
          rooms: 2 + (hash(t, 7, 11) < 0.5 ? 1 : 0),
          seed: t,
        },
        t,
      );
    }
    const free = open.filter((t) => !used[t] && cls[t] === VILLAGE);
    const pool = free.length ? free : open.filter((t) => !used[t]);
    if (pool.length) {
      const [mx, my] = centroid(comp);
      const t = nearestTo(pool, mx, my);
      used[t] = 1;
      addLandmark(t, comp.length >= 20 ? 3 : 2, hash(seed, 1, 13));
    }
    // A few trees in the courtyards.
    const treeTiles = spread(
      open.filter((t) => !used[t]),
      Math.floor(comp.length / 9),
      seed + 1,
      [...homes, ...keeps],
    );
    for (const t of treeTiles)
      add(
        {
          kind: "tree",
          x: cx(t) + jitter(t, 8, 0.3),
          y: cy(t) + jitter(t, 9, 0.3),
          tree: hash(t, 10, 13) < 0.5 ? "elm" : hash(t, 10, 14) < 0.5 ? "jujube" : "mulberry",
          r: 0.2 + hash(t, 11, 13) * 0.06,
          seed: t,
        },
        t,
      );
  }

  function addLandmark(t: number, storeys: number, pick: number) {
    const x = cx(t) + jitter(t, 12, 0.12);
    const y = cy(t) + jitter(t, 13, 0.1);
    if (pick < 0.34) add({ kind: "well", x, y, seed: t }, t);
    else if (pick < 0.67)
      add({ kind: "watchtower", x, y, size: 0.42, storeys, walled: false, seed: t }, t);
    else add({ kind: "granary", x, y, size: 0.42, round: hash(t, 14, 13) < 0.5, seed: t }, t);
  }

  // Open towns: houses capped at about one per three or four tiles, one landmark,
  // and the rest yards, millet and wheat strips and mulberry or jujube trees.
  const towns = components((i) => cls[i] === VILLAGE && !gateAt[i] && !enclosed[i] && !used[i]);
  for (const comp of towns) {
    const seed = comp[0] as number;
    const nh = Math.max(1, Math.round(comp.length / 3.5));
    const homes = spread(comp, nh, seed);
    const [mx, my] = centroid(comp);
    for (const t of homes) {
      used[t] = 1;
      const tiled = hash(t, 6, 17) < 0.28;
      const bw = (tiled ? 0.78 : 0.68) + hash(t, 2, 17) * 0.2;
      const bh = (tiled ? 0.66 : 0.58) + hash(t, 3, 17) * 0.16;
      add(
        {
          kind: "compound",
          x: cx(t) + jitter(t, 4, 0.08) - bw / 2,
          y: cy(t) + jitter(t, 5, 0.07) - bh / 2,
          w: bw,
          h: bh,
          tiled,
          rooms: 1 + (hash(t, 7, 17) < (tiled ? 0.7 : 0.35) ? 1 : 0),
          seed: t,
        },
        t,
      );
    }
    const rest = comp.filter((t) => !used[t]);
    if (rest.length && comp.length >= 2) {
      const t = nearestTo(rest, mx, my);
      used[t] = 1;
      const pick = comp.length < 4 ? hash(seed, 2, 19) * 0.34 : hash(seed, 2, 19);
      addLandmark(t, 2, pick);
    }
    const yards: number[] = [];
    for (const t of comp) {
      if (used[t]) continue;
      used[t] = 1;
      yards.push(t);
      const r = hash(t, 15, 17);
      if (r < 0.45)
        add(
          {
            kind: "fields",
            x: (t % w) + 0.04 + hash(t, 16, 17) * 0.06,
            y: Math.floor(t / w) + 0.04 + hash(t, 17, 17) * 0.06,
            w: 0.82 + hash(t, 18, 17) * 0.1,
            h: 0.82 + hash(t, 19, 17) * 0.1,
            ew: hash(t, 20, 17) < 0.5,
            seed: t,
          },
          t,
        );
      else if (r < 0.72) {
        const k = 2 + (hash(t, 21, 17) < 0.5 ? 1 : 0);
        for (let j = 0; j < k; j++)
          add(
            {
              kind: "tree",
              x: cx(t) + jitter(t * 7 + j, 22, 0.3),
              y: cy(t) + jitter(t * 7 + j, 23, 0.3),
              tree: hash(t, 24 + j, 17) < 0.6 ? "mulberry" : "jujube",
              r: 0.15 + hash(t, 27 + j, 17) * 0.05,
              seed: t * 7 + j,
            },
            t,
          );
      } else {
        add(
          {
            kind: "threshing",
            x: cx(t) + jitter(t, 30, 0.1),
            y: cy(t) + jitter(t, 31, 0.1),
            seed: t,
          },
          t,
        );
        add(
          {
            kind: "prop",
            x: cx(t) + jitter(t, 32, 0.3),
            y: cy(t) + jitter(t, 33, 0.3),
            prop: hash(t, 34, 17) < 0.6 ? "haystack" : "cart",
            seed: t,
          },
          t,
        );
      }
    }
    // Lanes: a path from each house and the landmark to the middle of the town.
    if (comp.length >= 2) {
      const hub = nearestTo(comp, mx, my);
      for (const t of homes) {
        if (t === hub) continue;
        const ax = cx(t);
        const ay = cy(t) + 0.32;
        const bx = cx(hub);
        const by = cy(hub);
        const midx = (ax + bx) / 2 + jitter(t, 35, 0.25);
        const midy = (ay + by) / 2 + jitter(t, 36, 0.25);
        add(
          {
            kind: "lane",
            pts: [
              [ax, ay],
              [midx, midy],
              [bx, by],
            ],
            seed: t,
          },
          t,
        );
      }
    }
    // Mulberry by the houses.
    for (const t of homes)
      if (hash(t, 37, 17) < 0.6)
        add(
          {
            kind: "tree",
            x: cx(t) + (hash(t, 38, 17) < 0.5 ? -0.42 : 0.42),
            y: cy(t) + jitter(t, 39, 0.25),
            tree: "mulberry",
            r: 0.15 + hash(t, 40, 17) * 0.04,
            seed: t * 3,
          },
          t,
        );
  }

  // Village tiles caught in odd places (a lone one inside a fort ring) still get a yard.
  for (let i = 0; i < n; i++)
    if (cls[i] === VILLAGE && !used[i] && !gateAt[i] && !enclosed[i])
      add({ kind: "threshing", x: cx(i), y: cy(i), seed: i }, i);

  // Camps: tents of several sizes, a command tent, fires, racks and banners,
  // and an earth rampart with a timber palisade round the outside.
  const fences: Fence[] = [];
  const camps = components((i) => cls[i] === CAMP);
  for (const comp of camps) {
    const seed = comp[0] as number;
    const [mx, my] = centroid(comp);
    const hq = nearestTo(comp, mx, my);
    for (const t of comp) {
      const x = t % w;
      const y = (t - x) / w;
      if (t === hq && comp.length >= 4) {
        add(
          { kind: "tent", x: cx(t), y: cy(t) - 0.04, w: 0.8, h: 0.56, style: "command", seed: t },
          t,
        );
        add({ kind: "prop", x: cx(t) + 0.36, y: cy(t) + 0.3, prop: "banner", seed: t }, t);
        continue;
      }
      const r = hash(t, 1, 23);
      const count = r < 0.15 ? 0 : r < 0.72 ? 1 : 2;
      const slots = [
        [0.3, 0.32],
        [0.7, 0.36],
        [0.34, 0.72],
        [0.7, 0.72],
      ] as const;
      const first = Math.floor(hash(t, 2, 23) * 4);
      for (let k = 0; k < count; k++) {
        const s = slots[(first + k * 2) % 4] as readonly [number, number];
        const round = hash(t, 3 + k, 23) < 0.3;
        const big = count === 1 ? 1.3 : 1;
        const tw =
          (round ? 0.36 + hash(t, 5 + k, 23) * 0.08 : 0.4 + hash(t, 5 + k, 23) * 0.12) * big;
        const th = round ? tw : (0.3 + hash(t, 7 + k, 23) * 0.08) * big;
        const turn = !round && hash(t, 8 + k, 23) < 0.35;
        add(
          {
            kind: "tent",
            x: x + (count === 1 ? 0.5 + jitter(t, 9, 0.12) : s[0] + jitter(t * 3 + k, 10, 0.05)),
            y: y + (count === 1 ? 0.48 + jitter(t, 11, 0.1) : s[1] + jitter(t * 3 + k, 12, 0.05)),
            w: turn ? th : tw,
            h: turn ? tw : th,
            style: round ? "round" : "ridge",
            seed: t * 4 + k,
          },
          t,
        );
      }
      if (count < 2 && hash(t, 13, 23) < 0.55) {
        const props = ["fire", "rack", "crates", "banner", "cart"] as const;
        add(
          {
            kind: "prop",
            x: x + 0.2 + hash(t, 14, 23) * 0.6,
            y: y + (count === 0 ? 0.5 : hash(t, 15, 23) < 0.5 ? 0.2 : 0.82),
            prop: props[Math.floor(hash(t, 16, 23) * props.length)] as (typeof props)[number],
            seed: t,
          },
          t,
        );
      }
    }
    // The rampart's sides; one opening each way the camp faces out.
    const sides: Fence[] = [];
    for (const t of comp) {
      const x = t % w;
      const y = (t - x) / w;
      for (const [dx, dy, d] of DIRS) {
        const c = at(x + dx, y + dy);
        if (c === -1 || c === CAMP || c === WALL || c === FORT || c === WATER) continue;
        if (isGate(x + dx, y + dy)) continue;
        sides.push({ x, y, side: d, gap: false });
      }
    }
    for (const d of [N, E, S, W]) {
      const mine = sides.filter((f) => f.side === d);
      if (mine.length < 2) continue;
      let best = mine[0] as Fence;
      for (const f of mine) if (hash(f.x, f.y, d + seed) > hash(best.x, best.y, d + seed)) best = f;
      const k = sides.indexOf(best);
      sides[k] = { ...best, gap: true };
    }
    fences.push(...sides);
  }

  // Willows by the water, now and then.
  for (let i = 0; i < n; i++) {
    const c = cls[i] as number;
    if (c !== PLAIN && c !== GRASS) continue;
    const x = i % w;
    const y = (i - x) / w;
    const waterSide = DIRS.find(([dx, dy]) => at(x + dx, y + dy) === WATER);
    if (!waterSide || hash(i, 1, 29) > 0.22) continue;
    const [dx, dy] = waterSide;
    add(
      {
        kind: "tree",
        x: x + 0.5 + dx * 0.3 + jitter(i, 2, 0.2) * (dx === 0 ? 1 : 0.2),
        y: y + 0.5 + dy * 0.3 + jitter(i, 3, 0.2) * (dy === 0 ? 1 : 0.2),
        tree: "willow",
        r: 0.2 + hash(i, 4, 29) * 0.05,
        seed: i,
      },
      i,
    );
  }

  // ---- ground tables
  const t = (): Float32Array => new Float32Array(n);
  const tables: Tables = {
    grass: t(),
    forest: t(),
    hill: t(),
    mount: t(),
    massif: t(),
    wet: t(),
    deep: t(),
    ford: t(),
    pave: t(),
    yard: t(),
    camp: t(),
    built: t(),
  };
  for (let i = 0; i < n; i++) {
    const c = cls[i] as number;
    const inCastle = enclosed[i] === 1;
    switch (c) {
      case GRASS:
        tables.grass[i] = inCastle ? 0.6 : 1;
        if (inCastle) tables.pave[i] = 0.35;
        break;
      case FOREST:
        tables.forest[i] = 1;
        tables.grass[i] = 0.75;
        break;
      case HILLS:
        tables.hill[i] = 1;
        tables.grass[i] = 0.5;
        break;
      case MOUNTAIN:
        tables.mount[i] = 1;
        tables.grass[i] = 0.15;
        break;
      case FORD:
        tables.wet[i] = 1;
        tables.deep[i] = 0.2;
        tables.ford[i] = 1;
        break;
      case WATER:
        tables.wet[i] = 1;
        tables.deep[i] = 1;
        break;
      case BRIDGE: {
        const b = bridges.get(i);
        if (b?.wet) {
          tables.wet[i] = 1;
          const x = i % w;
          const y = (i - x) / w;
          const water = DIRS.some(([dx, dy]) => at(x + dx, y + dy) === WATER);
          tables.deep[i] = water ? 0.8 : 0.2;
          tables.ford[i] = water ? 0 : 1;
        } else tables.yard[i] = 0.5;
        break;
      }
      case VILLAGE:
        if (gateAt[i]) tables.pave[i] = 0.6;
        else if (inCastle) tables.pave[i] = 1;
        else {
          tables.yard[i] = 1;
          tables.grass[i] = 0.3;
        }
        break;
      case FORT:
        if (stockade[i]) tables.camp[i] = 1;
        else tables.pave[i] = inCastle || gateAt[i] ? 1 : 0.7;
        break;
      case WALL:
        tables.built[i] = 1;
        tables.grass[i] = 0.2;
        break;
      case CAMP:
        tables.camp[i] = 1;
        tables.grass[i] = 0.1;
        break;
      case PLAIN:
        if (inCastle) tables.pave[i] = 0.75;
        else if (gateAt[i]) tables.pave[i] = 0.55;
        else tables.grass[i] = 0.16;
        break;
      default:
        break;
    }
  }

  // Mountain ranges rise toward their middle: steps from the nearest tile that isn't mountain.
  {
    const dist = new Int16Array(n).fill(99);
    const queue: number[] = [];
    for (let i = 0; i < n; i++)
      if (cls[i] !== MOUNTAIN) {
        dist[i] = 0;
        queue.push(i);
      }
    for (let q = 0; q < queue.length; q++) {
      const c = queue[q] as number;
      const x = c % w;
      const y = (c - x) / w;
      for (let dy = -1; dy <= 1; dy++)
        for (let dx = -1; dx <= 1; dx++) {
          if (!inside(x + dx, y + dy)) continue;
          const j = (y + dy) * w + x + dx;
          if ((dist[j] as number) > (dist[c] as number) + 1) {
            dist[j] = (dist[c] as number) + 1;
            queue.push(j);
          }
        }
    }
    for (let i = 0; i < n; i++)
      if (cls[i] === MOUNTAIN) tables.massif[i] = Math.min(1, ((dist[i] as number) - 1) / 3);
  }

  // ---- order features for drawing: ground marks first, then by depth
  const layer = (f: Feature) =>
    f.kind === "street" || f.kind === "lane"
      ? 0
      : f.kind === "fields" || f.kind === "threshing"
        ? 1
        : 2;
  const depth = (f: Feature) =>
    "y" in f ? f.y + ("h" in f ? f.h : 0) : "y0" in f ? f.y0 : (f.pts[0]?.[1] ?? 0);
  const order = features.map((_, k) => k);
  order.sort((a, b) => {
    const fa = features[a] as Feature;
    const fb = features[b] as Feature;
    return layer(fa) - layer(fb) || depth(fa) - depth(fb) || a - b;
  });
  const sortedF = order.map((k) => features[k] as Feature);
  const sortedA = order.map((k) => anchors[k] as number);

  // ---- signatures: everything a tile's pixels depend on
  const featureSig = new Map<number, string[]>();
  sortedF.forEach((f, k) => {
    const a = sortedA[k] as number;
    const list = featureSig.get(a) ?? [];
    list.push(JSON.stringify(f));
    featureSig.set(a, list);
  });
  const sig: string[] = [];
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const parts: string[] = [];
      for (let dy = -2; dy <= 2; dy++)
        for (let dx = -2; dx <= 2; dx++) {
          const nx = Math.min(w - 1, Math.max(0, x + dx));
          const ny = Math.min(h - 1, Math.max(0, y + dy));
          const j = ny * w + nx;
          parts.push(`${cls[j]}${enclosed[j]}${gateAt[j]}${wall[j]}`);
        }
      for (let dy = -1; dy <= 1; dy++)
        for (let dx = -1; dx <= 1; dx++) {
          if (!inside(x + dx, y + dy)) continue;
          const j = (y + dy) * w + x + dx;
          const fs = featureSig.get(j);
          if (fs) parts.push(...fs);
          const b = bridges.get(j);
          if (b) parts.push(JSON.stringify(b));
          const g = gateAt[j] ? gates[(gateAt[j] as number) - 1] : null;
          if (g) parts.push(JSON.stringify(g));
        }
      for (const f of fences)
        if (Math.abs(f.x - x) <= 1 && Math.abs(f.y - y) <= 1)
          parts.push(`${f.x},${f.y},${f.side},${f.gap}`);
      sig.push(parts.join("|"));
    }

  return {
    w,
    h,
    ids,
    cls,
    enclosed,
    wall,
    gateAt,
    gates,
    bridges,
    features: sortedF,
    anchors: sortedA,
    fences,
    tables,
    sig,
  };
}
