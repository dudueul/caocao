/**
 * The classic skin's terrain path (ADR 0042, ADR 0044): the owner's local 1998
 * tiles laid hard, then the natural terrains drawn again as feathered "splats"
 * a little larger than their tile, so edges blur instead of meeting in a hard
 * grid. Built structures stay crisp. Unchanged by ADR 0074; our own terrain is
 * painted by `paint.ts`.
 */
import type { BattleMap, TerrainId } from "@tk/rules";
import { type Container, Sprite, Texture } from "pixi.js";

/** Lower draws first; splats of higher terrains spill over lower ones. */
const SPILL: Partial<Record<TerrainId, number>> = {
  ford: 1,
  plain: 2,
  grass: 3,
  hills: 4,
  forest: 5,
  mountain: 6,
};
const STRUCTURE = new Set<TerrainId>(["bridge", "village", "fort", "wall", "camp"]);

export interface TerrainSet {
  /** Canvas size of one variant. */
  size: number;
  variants: Map<TerrainId, HTMLCanvasElement[]>;
  /**
   * Painted as one landscape by our own painter (ADR 0074); `variants` then
   * holds only extra hard tiles such as stage floors.
   */
  procedural?: boolean;
}

interface Built {
  hard: Map<TerrainId, Texture[]>;
  soft: Map<TerrainId, Texture[]>;
}

const built = new WeakMap<TerrainSet, Built>();

function hash(x: number, y: number, seed: number): number {
  let h = (x * 374761393 + y * 668265263 + seed * 1442695041) | 0;
  h = Math.imul(h ^ (h >>> 13), 1274126177);
  return ((h ^ (h >>> 16)) >>> 0) / 4294967296;
}

function canvas(size: number): [HTMLCanvasElement, CanvasRenderingContext2D] {
  const c = document.createElement("canvas");
  c.width = size;
  c.height = size;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  return [c, g];
}

/** The tile 1.5× its size, tiled around itself, with a noisy soft edge. */
function feather(src: HTMLCanvasElement, seed: number): HTMLCanvasElement {
  const s = src.width;
  const m = Math.round(s / 4);
  const W = s + m * 2;
  const [c, g] = canvas(W);
  for (let dy = -1; dy <= 1; dy++)
    for (let dx = -1; dx <= 1; dx++) g.drawImage(src, m + dx * s, m + dy * s);
  const img = g.getImageData(0, 0, W, W);
  const n = 6;
  for (let y = 0; y < W; y++)
    for (let x = 0; x < W; x++) {
      // Low-frequency noise wobbles the edge so it doesn't read as a rounded square.
      const fx = (x / W) * n;
      const fy = (y / W) * n;
      const ix = Math.floor(fx);
      const iy = Math.floor(fy);
      const tx = fx - ix;
      const ty = fy - iy;
      const a = hash(ix, iy, seed);
      const b = hash(ix + 1, iy, seed);
      const cc = hash(ix, iy + 1, seed);
      const d = hash(ix + 1, iy + 1, seed);
      const noise = a + (b - a) * tx + (cc - a) * ty + (a - b - cc + d) * tx * ty;
      const edge = Math.min(x, y, W - 1 - x, W - 1 - y) + (noise - 0.5) * m * 1.1;
      const k = Math.max(0, Math.min(1, edge / (m * 2)));
      const o = (y * W + x) * 4 + 3;
      img.data[o] = Math.round((img.data[o] as number) * k * k * (3 - 2 * k));
    }
  g.putImageData(img, 0, 0);
  return c;
}

function build(set: TerrainSet): Built {
  let b = built.get(set);
  if (b) return b;
  b = { hard: new Map(), soft: new Map() };
  for (const [id, list] of set.variants) {
    b.hard.set(
      id,
      list.map((c) => Texture.from(c)),
    );
    if (SPILL[id] !== undefined)
      b.soft.set(
        id,
        list.map((c, i) => Texture.from(feather(c, i * 31 + (SPILL[id] ?? 0) * 7))),
      );
  }
  built.set(set, b);
  return b;
}

function variant<T>(list: readonly T[] | undefined, x: number, y: number): T | undefined {
  if (!list || list.length === 0) return undefined;
  return list[(((x * 73856093) ^ (y * 19349663)) >>> 0) % list.length];
}

/**
 * Lays the map into `ground` (hard tiles), `water` (water tiles only, for the
 * shimmer filter) and `edges` (splats and structures).
 */
export function paintClassic(
  map: BattleMap,
  tile: number,
  set: TerrainSet,
  layers: { ground: Container; water: Container; edges: Container },
): void {
  const b = build(set);
  const at = (x: number, y: number) => map.tiles[y * map.width + x] as TerrainId;
  const put = (into: Container, tex: Texture | undefined, x: number, y: number, grow = 1) => {
    if (!tex) return;
    const s = new Sprite(tex);
    s.anchor.set(0.5);
    s.position.set(x * tile + tile / 2, y * tile + tile / 2);
    s.width = tile * grow;
    s.height = tile * grow;
    into.addChild(s);
  };
  const splats: { id: TerrainId; x: number; y: number }[] = [];
  for (let y = 0; y < map.height; y++)
    for (let x = 0; x < map.width; x++) {
      const id = at(x, y);
      const hard = variant(b.hard.get(id), x, y);
      put(id === "water" ? layers.water : layers.ground, hard, x, y);
      if (SPILL[id] !== undefined) splats.push({ id, x, y });
    }
  splats.sort((p, q) => (SPILL[p.id] ?? 0) - (SPILL[q.id] ?? 0) || p.y - q.y || p.x - q.x);
  // Every natural tile spills a little: borders between terrains and seams between
  // variants of the same terrain both soften.
  for (const p of splats) put(layers.edges, variant(b.soft.get(p.id), p.x, p.y), p.x, p.y, 1.5);
  for (let y = 0; y < map.height; y++)
    for (let x = 0; x < map.width; x++) {
      const id = at(x, y);
      if (STRUCTURE.has(id)) put(layers.edges, variant(b.hard.get(id), x, y), x, y);
    }
}

/** The classic skin's sampled tiles as a terrain set. */
export function skinTerrain(terrain: Map<TerrainId, Texture[]>, tile: number): TerrainSet {
  const variants = new Map<TerrainId, HTMLCanvasElement[]>();
  for (const [id, list] of terrain)
    variants.set(
      id,
      list.map((t) => {
        const [c, g] = canvas(tile);
        const res = t.source.resource as CanvasImageSource;
        g.drawImage(res, t.frame.x, t.frame.y, t.frame.width, t.frame.height, 0, 0, tile, tile);
        return c;
      }),
    );
  return { size: tile, variants };
}
