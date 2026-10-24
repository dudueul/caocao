/**
 * Paints a whole map as one landscape (ADR 0074): the ground fields smoothed
 * up, overlays of grass blades, rock and paving through their masks, a fine
 * grain, then detail, forests, bridges, camps, walls and buildings, all placed
 * in world space so that neighbouring tiles join. The result is a few chunk
 * textures (at most 1024 px a side) for the land and one for the water under
 * it, which the shimmer filter moves. When a map shift changes some tiles,
 * only the chunks whose tiles' neighbourhoods changed are painted again.
 */
import { CanvasSource, type Container, Sprite, Texture } from "pixi.js";
import {
  drawBridges,
  drawFeature,
  drawFences,
  drawWalls,
  featureBounds,
  type Pen,
  type Rect,
} from "./draw.ts";
import { computeGround, type GroundField, landAlpha, sample } from "./ground.ts";
import { type Landscape, type Look, lookOf } from "./landscape.ts";
import { hash } from "./noise.ts";
import {
  BRIDGE,
  CAMP,
  FLOOR,
  FORD,
  FORT,
  type MapLike,
  type Plan,
  planMap,
  VILLAGE,
  WALL,
} from "./plan.ts";
import { CROWN_R, type Library, library, stamp } from "./stamps.ts";

export interface PaintOptions {
  /** Canvas pixels per tile (about twice the size on screen); 64, 96 or 128. */
  readonly px?: number;
  /** The battle's landscape; without one, the North China plain in summer. */
  readonly landscape?: Landscape | null;
}

/** Paint time and texture memory of the last paint, for `?debug`. */
export const terrainStats = {
  /** Whole paint, ms. */
  ms: 0,
  plan: 0,
  field: 0,
  chunks: 0,
  /** Chunks painted this time (all of them, or only those a shift touched). */
  painted: 0,
  px: 0,
  /** Bytes of the land and water textures. */
  bytes: 0,
  /** Bytes of the shared sprite library. */
  library: 0,
  /** Where the chunk painting went, ms: ground and overlays, detail, forests, built things. */
  ground: 0,
  detail: 0,
  forest: 0,
  built: 0,
};

/** Tiles per chunk side. */
const CHUNK = 8;

interface Chunk {
  readonly x0: number;
  readonly y0: number;
  readonly cw: number;
  readonly ch: number;
  readonly canvas: HTMLCanvasElement;
  readonly texture: Texture;
}

interface Painted {
  readonly key: string;
  readonly base: string;
  readonly plan: Plan;
  readonly chunks: Chunk[];
  readonly water: { canvas: HTMLCanvasElement; texture: Texture };
}

interface Job {
  readonly plan: Plan;
  readonly field: GroundField;
  readonly look: Look;
  readonly lib: Library;
  readonly px: number;
  readonly land: HTMLCanvasElement;
  readonly grassMask: HTMLCanvasElement;
  readonly rockMask: HTMLCanvasElement;
  readonly paveMask: HTMLCanvasElement;
  readonly dirtMask: HTMLCanvasElement;
  readonly floors: ReadonlyMap<string, HTMLCanvasElement[]>;
  readonly tmp: CanvasRenderingContext2D;
}

const owned = new WeakMap<Container, Painted>();

function canvas(w: number, h: number): HTMLCanvasElement {
  const c = document.createElement("canvas");
  c.width = Math.max(1, Math.round(w));
  c.height = Math.max(1, Math.round(h));
  return c;
}

function ctx(c: HTMLCanvasElement): CanvasRenderingContext2D {
  return c.getContext("2d") as CanvasRenderingContext2D;
}

function texture(c: HTMLCanvasElement): Texture {
  return new Texture({ source: new CanvasSource({ resource: c, autoGenerateMipmaps: true }) });
}

function fromPixels(data: Uint8ClampedArray, w: number, h: number): HTMLCanvasElement {
  const c = canvas(w, h);
  const img = new ImageData(new Uint8ClampedArray(data), w, h);
  ctx(c).putImageData(img, 0, 0);
  return c;
}

export function quantizePx(px: number): number {
  return px <= 72 ? 64 : px <= 112 ? 96 : 128;
}

/** Draws a field canvas smoothed up over a chunk, overlapping its edges so chunks meet without seams. */
function upscale(g: CanvasRenderingContext2D, src: HTMLCanvasElement, job: Job, c: Chunk): void {
  const L = job.field.L;
  const k = job.px / L;
  const m = 3;
  const sx = Math.max(0, c.x0 * L - m);
  const sy = Math.max(0, c.y0 * L - m);
  const ex = Math.min(src.width, (c.x0 + c.cw) * L + m);
  const ey = Math.min(src.height, (c.y0 + c.ch) * L + m);
  g.drawImage(
    src,
    sx,
    sy,
    ex - sx,
    ey - sy,
    (sx - c.x0 * L) * k,
    (sy - c.y0 * L) * k,
    (ex - sx) * k,
    (ey - sy) * k,
  );
}

/** A tileable pattern laid through a soft mask. */
function overlay(
  g: CanvasRenderingContext2D,
  job: Job,
  c: Chunk,
  pattern: HTMLCanvasElement,
  mask: HTMLCanvasElement,
  data: Uint8ClampedArray,
  alpha: number,
): void {
  if (!covers(job, c, data)) return;
  const t = job.tmp;
  const W = c.canvas.width;
  const H = c.canvas.height;
  t.setTransform(1, 0, 0, 1, 0, 0);
  t.globalCompositeOperation = "source-over";
  t.clearRect(0, 0, W, H);
  t.setTransform(1, 0, 0, 1, -c.x0 * job.px, -c.y0 * job.px);
  const pat = t.createPattern(pattern, "repeat");
  if (!pat) return;
  t.fillStyle = pat;
  t.fillRect(c.x0 * job.px, c.y0 * job.px, W, H);
  t.setTransform(1, 0, 0, 1, 0, 0);
  t.globalCompositeOperation = "destination-in";
  upscale(t, mask, job, c);
  t.globalCompositeOperation = "source-over";
  g.globalAlpha = alpha;
  g.drawImage(t.canvas, 0, 0, W, H, 0, 0, W, H);
  g.globalAlpha = 1;
}

/** Whether a mask shows anywhere in (or just around) a chunk. */
function covers(job: Job, c: Chunk, data: Uint8ClampedArray): boolean {
  const { L, W, H } = job.field;
  const x0 = Math.max(0, c.x0 * L - 3);
  const y0 = Math.max(0, c.y0 * L - 3);
  const x1 = Math.min(W, (c.x0 + c.cw) * L + 3);
  const y1 = Math.min(H, (c.y0 + c.ch) * L + 3);
  for (let y = y0; y < y1; y += 2)
    for (let x = x0; x < x1; x += 2) if ((data[(y * W + x) * 4 + 3] as number) > 4) return true;
  return false;
}

function inRect(b: Rect, r: Rect): boolean {
  return b.x1 >= r.x0 && b.x0 <= r.x1 && b.y1 >= r.y0 && b.y0 <= r.y1;
}

function paintChunk(job: Job, c: Chunk): void {
  const { plan, px, lib, look } = job;
  const t0 = performance.now();
  const g = ctx(c.canvas);
  g.setTransform(1, 0, 0, 1, 0, 0);
  g.globalAlpha = 1;
  g.globalCompositeOperation = "source-over";
  g.clearRect(0, 0, c.canvas.width, c.canvas.height);
  g.imageSmoothingEnabled = true;
  g.imageSmoothingQuality = "high";
  upscale(g, job.land, job, c);
  // Grit and dry grass over the open land, grass blades through the grass mask.
  overlay(g, job, c, lib.dirt, job.dirtMask, job.field.dirtMask, 0.8);
  overlay(g, job, c, lib.grass, job.grassMask, job.field.grassMask, 1);
  overlay(g, job, c, lib.rock, job.rockMask, job.field.rockMask, 0.8);
  overlay(g, job, c, lib.pave, job.paveMask, job.field.paveMask, 0.95);
  // A fine grain over all the land (not the open water).
  g.globalCompositeOperation = "source-atop";
  g.setTransform(1, 0, 0, 1, -c.x0 * px, -c.y0 * px);
  const grain = g.createPattern(lib.grain, "repeat");
  if (grain) {
    g.fillStyle = grain;
    g.fillRect(c.x0 * px, c.y0 * px, c.canvas.width, c.canvas.height);
  }
  g.globalCompositeOperation = "source-over";

  // From here on, drawing is in tile units.
  g.setTransform(px, 0, 0, px, -c.x0 * px, -c.y0 * px);
  const r: Rect = { x0: c.x0, y0: c.y0, x1: c.x0 + c.cw, y1: c.y0 + c.ch };
  const pen: Pen = { g, px, look, lib };
  const t1 = performance.now();
  floors(job, g, r);
  const decal = (k: string) =>
    k === "lane" || k === "street" || k === "fields" || k === "threshing";
  for (const f of plan.features)
    if (decal(f.kind) && inRect(featureBounds(f), r)) drawFeature(pen, f);
  details(job, g, r);
  const t2 = performance.now();
  drawBridges(pen, plan, r);
  forest(job, g, r);
  const t3 = performance.now();
  drawFences(pen, plan, r);
  drawWalls(pen, plan, r);
  for (const f of plan.features)
    if (!decal(f.kind) && inRect(featureBounds(f), r)) drawFeature(pen, f);
  const t4 = performance.now();
  terrainStats.ground += t1 - t0;
  terrainStats.detail += t2 - t1;
  terrainStats.forest += t3 - t2;
  terrainStats.built += t4 - t3;
}

function resetTimes(): void {
  terrainStats.ground = 0;
  terrainStats.detail = 0;
  terrainStats.forest = 0;
  terrainStats.built = 0;
}

/** Stage floors (boards, paving, carpet): laid crisp from their own art. */
function floors(job: Job, g: CanvasRenderingContext2D, r: Rect): void {
  const { plan } = job;
  for (let y = r.y0; y < r.y1; y++)
    for (let x = r.x0; x < r.x1; x++) {
      const i = y * plan.w + x;
      if (plan.cls[i] !== FLOOR) continue;
      const list = job.floors.get(plan.ids[i] as string);
      if (!list || list.length === 0) continue;
      const art = list[
        (((x * 73856093) ^ (y * 19349663)) >>> 0) % list.length
      ] as HTMLCanvasElement;
      g.drawImage(art, x, y, 1, 1);
    }
}

/** Tufts, flowers, pebbles, rocks and crags, shrubs, reeds by the water, stones in fords. */
function details(job: Job, g: CanvasRenderingContext2D, r: Rect): void {
  const { plan, field, lib, look, px } = job;
  const d = look.density;
  const pick = <T>(list: readonly T[], h: number): T =>
    list[Math.floor(h * list.length) % list.length] as T;
  for (let y = Math.max(0, r.y0 - 1); y < Math.min(plan.h, r.y1 + 1); y++)
    for (let x = Math.max(0, r.x0 - 1); x < Math.min(plan.w, r.x1 + 1); x++) {
      const i = y * plan.w + x;
      const cls = plan.cls[i] as number;
      if (cls === WALL || cls === FLOOR) continue;
      const built =
        cls === VILLAGE || cls === FORT || cls === CAMP || cls === BRIDGE || plan.enclosed[i] === 1;
      for (let k = 0; k < 16; k++) {
        const gx = x * 4 + (k & 3);
        const gy = y * 4 + (k >> 2);
        const u = (gx + 0.5 + (hash(gx, gy, 301) - 0.5) * 0.9) / 4;
        const v = (gy + 0.5 + (hash(gx, gy, 302) - 0.5) * 0.9) / 4;
        if (u < r.x0 - 0.4 || u > r.x1 + 0.4 || v < r.y0 - 0.4 || v > r.y1 + 0.4) continue;
        const hr = hash(gx, gy, 303);
        const hs = hash(gx, gy, 304);
        const la = landAlpha(field, u, v);
        if (cls === FORD) {
          // Stepping stones across a ford (none on ice).
          if (!look.frozen && la < 0.7 && hr < 0.2)
            stamp(g, pick(lib.stones, hs), u, v, px, 0.85 + hs * 0.4);
          continue;
        }
        if (la < 0.45) continue;
        const wt = sample(field, field.wet, u, v);
        if (wt > 0.26 && wt < 0.47 && hr < 0.2 * d.reeds && !built) {
          stamp(g, pick(lib.reeds, hs), u, v, px, 0.8 + hs * 0.5);
          continue;
        }
        if (sample(field, field.forest, u, v) > 0.5) continue;
        if (built) {
          if (hr < 0.05) stamp(g, pick(lib.pebbles, hs), u, v, px);
          continue;
        }
        const mt = sample(field, field.mount, u, v);
        if (mt > 0.42) {
          const a = 0.08 * d.rocks;
          const b = a + 0.26 * d.rocks;
          const c = b + 0.12 * d.shrubs;
          if (hr < a) stamp(g, pick(lib.crags, hs), u, v, px, 0.7 + hs * 0.6);
          else if (hr < b) stamp(g, pick(lib.rocks, hs), u, v, px, 0.8 + hs * 0.6);
          else if (hr < c) stamp(g, pick(lib.shrubs, hs), u, v, px, 0.8 + hs * 0.4);
          continue;
        }
        const hl = sample(field, field.hill, u, v);
        if (hl > 0.42) {
          // Scrub, stones and tufts dress every hill.
          const a = 0.1 + 0.14 * d.shrubs;
          const b = a + 0.06 + 0.08 * d.rocks;
          if (hr < a) stamp(g, pick(lib.shrubs, hs), u, v, px, 0.8 + hs * 0.5);
          else if (hr < b) stamp(g, pick(lib.rocks, hs), u, v, px, 0.6 + hs * 0.5);
          else if (hr < b + 0.35)
            stamp(
              g,
              pick(look.leaves === "full" || look.leaves === "fresh" ? lib.tufts : lib.dry, hs),
              u,
              v,
              px,
            );
          continue;
        }
        const ga = sample(field, field.grass, u, v);
        if (ga > 0.5) {
          const a = 0.42 * d.tufts;
          const b = a + 0.06 * d.flowers;
          if (hr < a) stamp(g, pick(lib.tufts, hs), u, v, px, 0.85 + hs * 0.4);
          else if (hr < b) stamp(g, pick(lib.flowers, hs), u, v, px);
          else if (hr < b + 0.025 * d.shrubs)
            stamp(g, pick(lib.shrubs, hs), u, v, px, 0.7 + hs * 0.3);
        } else if (hr < 0.09) stamp(g, pick(lib.pebbles, hs), u, v, px);
        else if (hr < 0.09 + 0.22 * d.tufts)
          stamp(
            g,
            pick(look.leaves === "bare" || ga < 0.15 ? lib.dry : lib.tufts, hs),
            u,
            v,
            px,
            0.8,
          );
      }
    }
}

/** Forests: one canopy of overlapping crowns; ragged, rounded crowns only on the mass's edge. */
function forest(job: Job, g: CanvasRenderingContext2D, r: Rect): void {
  const { plan, field, lib, look, px } = job;
  const d = 0.27;
  const gx0 = Math.floor((r.x0 - 1) / d);
  const gx1 = Math.ceil((r.x1 + 1) / d);
  const gy0 = Math.floor((r.y0 - 1) / (d * 0.88));
  const gy1 = Math.ceil((r.y1 + 1) / (d * 0.88));
  const crowns: {
    u: number;
    v: number;
    s: number;
    kind: keyof Library["crowns"];
    k: number;
    edge: boolean;
  }[] = [];
  const pines = look.density.pines;
  for (let gy = gy0; gy <= gy1; gy++)
    for (let gx = gx0; gx <= gx1; gx++) {
      const h1 = hash(gx, gy, 311);
      const h2 = hash(gx, gy, 312);
      const u = (gx + 0.5 + (gy & 1) * 0.5 + (h1 - 0.5) * 0.7) * d;
      const v = (gy + 0.5 + (h2 - 0.5) * 0.7) * d * 0.88;
      if (u < -0.3 || v < -0.3 || u > plan.w + 0.3 || v > plan.h + 0.3) continue;
      const f = sample(field, field.forest, u, v) + (hash(gx, gy, 313) - 0.5) * 0.3;
      if (f < 0.52) continue;
      if (landAlpha(field, u, v) < 0.5) continue;
      const h4 = hash(gx, gy, 314);
      const wet = sample(field, field.wet, u, v) > 0.15;
      const kind =
        wet && h4 < 0.6
          ? "willow"
          : h4 < pines
            ? "pine"
            : h4 < pines + 0.18
              ? "poplar"
              : h4 < pines + 0.27
                ? "jujube"
                : "elm";
      const edge = f < 0.74;
      crowns.push({
        u,
        v,
        s: (0.84 + hash(gx, gy, 315) * 0.34) * (edge ? 0.86 : 1),
        kind,
        k: Math.floor(hash(gx, gy, 316) * 16),
        edge,
      });
    }
  if (crowns.length === 0) return;
  crowns.sort((a, b) => a.v - b.v || a.u - b.u);
  // Shadows off the edge of the mass, a dark understorey to close the gaps, then the crowns.
  // Bare winter crowns cast their own shadows inside the wood too, so it keeps its depth.
  const bare = look.leaves === "bare";
  g.globalAlpha = 0.55;
  for (const c of crowns)
    if (c.edge || (bare && c.kind !== "pine"))
      stamp(g, lib.shadow, c.u + 0.1 * c.s, c.v + 0.12 * c.s, px, (CROWN_R * c.s * 1.25 * px) / 32);
  g.globalAlpha = 1;
  const under =
    look.leaves === "bare"
      ? "rgb(70,61,54)"
      : look.leaves === "autumn"
        ? "rgb(70,58,32)"
        : look.leaves === "fresh"
          ? "rgb(44,60,34)"
          : "rgb(34,46,28)";
  g.fillStyle = under;
  g.beginPath();
  for (const c of crowns) {
    g.moveTo(c.u + CROWN_R * c.s * 0.8, c.v);
    g.arc(c.u, c.v, CROWN_R * c.s * 0.8, 0, Math.PI * 2);
  }
  g.fill();
  for (const c of crowns) {
    const list = lib.crowns[c.kind];
    stamp(g, list[c.k % list.length] as (typeof list)[number], c.u, c.v, px, c.s);
  }
}

/** The river: its colours smoothed up, with a few streaks along the current. */
function paintWater(job: Job, c: HTMLCanvasElement, water: HTMLCanvasElement): void {
  const { plan } = job;
  const pw = c.width / plan.w;
  const g = ctx(c);
  g.setTransform(1, 0, 0, 1, 0, 0);
  g.imageSmoothingEnabled = true;
  g.imageSmoothingQuality = "high";
  g.drawImage(water, 0, 0, c.width, c.height);
  g.setTransform(pw, 0, 0, pw, 0, 0);
  g.lineCap = "round";
  const wet = (x: number, y: number) =>
    x >= 0 &&
    y >= 0 &&
    x < plan.w &&
    y < plan.h &&
    (plan.tables.wet[y * plan.w + x] as number) > 0.5;
  for (let y = 0; y < plan.h; y++)
    for (let x = 0; x < plan.w; x++) {
      const i = y * plan.w + x;
      if ((plan.tables.deep[i] as number) < 0.5) continue;
      const ew =
        (wet(x - 1, y) ? 1 : 0) + (wet(x + 1, y) ? 1 : 0) >=
        (wet(x, y - 1) ? 1 : 0) + (wet(x, y + 1) ? 1 : 0);
      for (let k = 0; k < 4; k++) {
        const u = x + hash(i, k, 321);
        const v = y + hash(i, k, 322);
        const len = 0.18 + hash(i, k, 323) * 0.3;
        const bend = (hash(i, k, 324) - 0.5) * 0.08;
        g.strokeStyle = k % 2 ? "rgba(230,236,226,0.13)" : "rgba(16,26,26,0.10)";
        g.lineWidth = 0.014 + hash(i, k, 325) * 0.012;
        g.beginPath();
        if (ew) {
          g.moveTo(u, v);
          g.quadraticCurveTo(u + len / 2, v + bend, u + len, v);
        } else {
          g.moveTo(u, v);
          g.quadraticCurveTo(u + bend, v + len / 2, u, v + len);
        }
        g.stroke();
      }
    }
}

function makeJob(
  plan: Plan,
  look: Look,
  px: number,
  floorArt: ReadonlyMap<string, HTMLCanvasElement[]>,
  tw: number,
  th: number,
): Job {
  const L = Math.max(12, Math.min(24, Math.round(px / 4)));
  const field = computeGround(plan, look, L);
  return {
    plan,
    field,
    look,
    lib: library(px, look),
    px,
    land: fromPixels(field.land, field.W, field.H),
    grassMask: fromPixels(field.grassMask, field.W, field.H),
    rockMask: fromPixels(field.rockMask, field.W, field.H),
    paveMask: fromPixels(field.paveMask, field.W, field.H),
    dirtMask: fromPixels(field.dirtMask, field.W, field.H),
    floors: floorArt,
    tmp: ctx(canvas(tw * px, th * px)),
  };
}

/**
 * The whole landscape as one 2D canvas, water and land together, without Pixi
 * (the dev gallery and tools). `px` pixels per tile.
 */
export function renderLandscape(map: MapLike, opts: PaintOptions = {}): HTMLCanvasElement {
  const t0 = performance.now();
  resetTimes();
  const look = lookOf(opts.landscape ?? null);
  const px = quantizePx(opts.px ?? 96);
  const plan = planMap(map);
  const tf = performance.now();
  const job = makeJob(plan, look, px, new Map(), map.width, map.height);
  const td = performance.now();
  const land = canvas(map.width * px, map.height * px);
  paintChunk(job, {
    x0: 0,
    y0: 0,
    cw: map.width,
    ch: map.height,
    canvas: land,
    texture: Texture.EMPTY,
  });
  const pw = Math.max(32, Math.round(px / 2));
  const water = canvas(map.width * pw, map.height * pw);
  paintWater(job, water, fromPixels(job.field.water, job.field.W, job.field.H));
  const out = canvas(map.width * px, map.height * px);
  const g = ctx(out);
  g.imageSmoothingEnabled = true;
  g.imageSmoothingQuality = "high";
  g.drawImage(water, 0, 0, out.width, out.height);
  g.drawImage(land, 0, 0);
  terrainStats.plan = tf - t0;
  terrainStats.field = td - tf;
  terrainStats.chunks = 1;
  terrainStats.painted = 1;
  terrainStats.px = px;
  terrainStats.bytes = land.width * land.height * 4 + water.width * water.height * 4;
  terrainStats.library = job.lib.bytes;
  terrainStats.ms = performance.now() - t0;
  return out;
}

/**
 * Frees a painting's textures, a moment later: the frame that drew them may
 * still hold them bound.
 */
function destroy(p: Painted): void {
  setTimeout(() => {
    for (const c of p.chunks) c.texture.destroy(true);
    p.water.texture.destroy(true);
  }, 1000);
}

/**
 * Paints `map` into the layers: the water under everything (in `water`, for
 * the shimmer), the land over it (in `edges`). `floors` are stage floor tiles
 * laid from their own art. Painting again into the same layers reuses what
 * did not change.
 */
export function paintLandscape(
  map: MapLike,
  tile: number,
  floorArt: ReadonlyMap<string, HTMLCanvasElement[]>,
  layers: { ground: Container; water: Container; edges: Container },
  opts: PaintOptions = {},
): void {
  const t0 = performance.now();
  const look = lookOf(opts.landscape ?? null);
  const px = quantizePx(opts.px ?? 96);
  const base = `${px}|${look.key}|${map.width}x${map.height}|${[...floorArt.keys()].join(",")}`;
  const key = `${base}|${map.tiles.join(",")}`;
  let prev = owned.get(layers.ground);
  if (!prev || prev.key !== key) {
    const tp = performance.now();
    resetTimes();
    const plan = planMap(map, new Set(floorArt.keys()));
    const tf = performance.now();
    const job = makeJob(plan, look, px, floorArt, CHUNK, CHUNK);
    const td = performance.now();
    const { field, lib } = job;
    const reuse = prev && prev.base === base ? prev : null;
    let chunks: Chunk[];
    let painted = 0;
    if (reuse) {
      // A map shift: paint again only where a tile's neighbourhood changed.
      chunks = reuse.chunks;
      for (const c of chunks) {
        let dirty = false;
        for (let y = c.y0; y < c.y0 + c.ch && !dirty; y++)
          for (let x = c.x0; x < c.x0 + c.cw; x++)
            if (reuse.plan.sig[y * plan.w + x] !== plan.sig[y * plan.w + x]) {
              dirty = true;
              break;
            }
        if (!dirty) continue;
        paintChunk(job, c);
        c.texture.source.update();
        painted++;
      }
    } else {
      if (prev) destroy(prev);
      chunks = [];
      for (let y0 = 0; y0 < map.height; y0 += CHUNK)
        for (let x0 = 0; x0 < map.width; x0 += CHUNK) {
          const cw = Math.min(CHUNK, map.width - x0);
          const ch = Math.min(CHUNK, map.height - y0);
          const cv = canvas(cw * px, ch * px);
          const c: Chunk = { x0, y0, cw, ch, canvas: cv, texture: Texture.EMPTY };
          paintChunk(job, c);
          chunks.push({ ...c, texture: texture(cv) });
          painted++;
        }
    }
    const pw = Math.max(32, Math.round(px / 2));
    let water = reuse?.water;
    const sameWater =
      reuse?.plan.tables.wet.every((v, i) => v === plan.tables.wet[i]) &&
      reuse.plan.tables.deep.every((v, i) => v === plan.tables.deep[i]);
    if (!water || !sameWater) {
      const wc = water?.canvas ?? canvas(map.width * pw, map.height * pw);
      paintWater(job, wc, fromPixels(field.water, field.W, field.H));
      if (water) water.texture.source.update();
      else water = { canvas: wc, texture: texture(wc) };
    }
    prev = { key, base, plan, chunks, water };
    owned.set(layers.ground, prev);
    const t1 = performance.now();
    terrainStats.plan = tf - tp;
    terrainStats.field = td - tf;
    terrainStats.chunks = chunks.length;
    terrainStats.painted = painted;
    terrainStats.px = px;
    terrainStats.bytes =
      chunks.reduce((n, c) => n + c.canvas.width * c.canvas.height * 4, 0) +
      water.canvas.width * water.canvas.height * 4;
    terrainStats.library = lib.bytes;
    terrainStats.ms = t1 - t0;
  }
  // Lay the textures at the current tile size.
  const ws = new Sprite(prev.water.texture);
  ws.width = map.width * tile;
  ws.height = map.height * tile;
  layers.water.addChild(ws);
  for (const c of prev.chunks) {
    const s = new Sprite(c.texture);
    s.position.set(c.x0 * tile, c.y0 * tile);
    s.width = c.cw * tile;
    s.height = c.ch * tile;
    layers.edges.addChild(s);
  }
}
