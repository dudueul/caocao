/**
 * Terrain painting with natural edges. Each terrain has a few texture variants
 * (the classic skin's sampled tiles, or our own procedural paintings when there
 * is no skin). Tiles are laid down hard, then the natural terrains are drawn
 * again as feathered "splats" a little larger than their tile, so grass spills
 * over the river bank and forest edges blur into plain instead of meeting in a
 * hard grid. Built structures (bridges, villages, forts, walls, camps) stay crisp.
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
export function paintTerrain(
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

// ---------------------------------------------------------------- our own terrain art

type Paint = (g: CanvasRenderingContext2D, s: number, v: number) => void;

function speckle(
  g: CanvasRenderingContext2D,
  s: number,
  v: number,
  colors: string[],
  count: number,
  size: number,
): void {
  for (let i = 0; i < count; i++) {
    g.fillStyle = colors[i % colors.length] as string;
    const r = size * (0.5 + hash(i, v, 5));
    g.beginPath();
    g.arc(hash(i, v, 1) * s, hash(i, v, 2) * s, r, 0, Math.PI * 2);
    g.fill();
  }
}

/** A seamless base: flat colour plus soft blotches that wrap at the edges. */
function base(g: CanvasRenderingContext2D, s: number, v: number, color: string, blotch: string[]) {
  g.fillStyle = color;
  g.fillRect(0, 0, s, s);
  for (let i = 0; i < 10; i++) {
    const x = hash(i, v, 8) * s;
    const y = hash(i, v, 9) * s;
    const r = s * (0.15 + hash(i, v, 10) * 0.2);
    for (const ox of [-s, 0, s])
      for (const oy of [-s, 0, s]) {
        const grad = g.createRadialGradient(x + ox, y + oy, 0, x + ox, y + oy, r);
        grad.addColorStop(0, blotch[i % blotch.length] as string);
        grad.addColorStop(1, "rgba(0,0,0,0)");
        g.fillStyle = grad;
        g.fillRect(x + ox - r, y + oy - r, r * 2, r * 2);
      }
  }
}

function blades(g: CanvasRenderingContext2D, s: number, v: number, colors: string[], n: number) {
  g.lineWidth = Math.max(1, s / 48);
  g.lineCap = "round";
  for (let i = 0; i < n; i++) {
    const x = hash(i, v, 21) * s;
    const y = hash(i, v, 22) * s;
    const h = s * (0.05 + hash(i, v, 23) * 0.06);
    g.strokeStyle = colors[i % colors.length] as string;
    g.beginPath();
    g.moveTo(x, y);
    g.quadraticCurveTo(x + h * 0.3, y - h * 0.6, x + h * (hash(i, v, 24) - 0.5), y - h);
    g.stroke();
  }
}

function tree(g: CanvasRenderingContext2D, x: number, y: number, r: number) {
  const sh = g.createRadialGradient(
    x + r * 0.35,
    y + r * 0.45,
    0,
    x + r * 0.35,
    y + r * 0.45,
    r * 1.1,
  );
  sh.addColorStop(0, "rgba(10,25,10,0.55)");
  sh.addColorStop(1, "rgba(10,25,10,0)");
  g.fillStyle = sh;
  g.fillRect(x - r, y - r, r * 2.6, r * 2.6);
  const body = g.createRadialGradient(x - r * 0.35, y - r * 0.4, r * 0.1, x, y, r);
  body.addColorStop(0, "#6fa24a");
  body.addColorStop(0.55, "#3c6e2e");
  body.addColorStop(1, "#22441e");
  g.fillStyle = body;
  g.beginPath();
  for (let k = 0; k < 9; k++) {
    const a = (k / 9) * Math.PI * 2;
    const rr = r * (0.82 + 0.18 * Math.sin(k * 2.3 + x));
    g.lineTo(x + Math.cos(a) * rr, y + Math.sin(a) * rr);
  }
  g.closePath();
  g.fill();
}

function house(g: CanvasRenderingContext2D, x: number, y: number, w: number) {
  const h = w * 0.55;
  g.fillStyle = "rgba(0,0,0,0.3)";
  g.fillRect(x + w * 0.1, y + h * 0.3, w, h);
  g.fillStyle = "#d9c7a0";
  g.fillRect(x, y + h * 0.25, w, h * 0.75);
  g.fillStyle = "#6b4a33";
  g.fillRect(x + w * 0.42, y + h * 0.6, w * 0.16, h * 0.4);
  const roof = g.createLinearGradient(x, y, x, y + h * 0.45);
  roof.addColorStop(0, "#b05a3c");
  roof.addColorStop(1, "#7a3524");
  g.fillStyle = roof;
  g.beginPath();
  g.moveTo(x - w * 0.12, y + h * 0.35);
  g.lineTo(x + w * 0.5, y - h * 0.15);
  g.lineTo(x + w * 1.12, y + h * 0.35);
  g.closePath();
  g.fill();
}

function stones(g: CanvasRenderingContext2D, s: number, rows: number, light: string, dark: string) {
  const bh = s / rows;
  for (let r = 0; r < rows; r++) {
    const off = r % 2 ? s / 4 : 0;
    for (let c = -1; c < 3; c++) {
      const x = c * (s / 2) + off;
      const y = r * bh;
      const grad = g.createLinearGradient(x, y, x, y + bh);
      grad.addColorStop(0, light);
      grad.addColorStop(1, dark);
      g.fillStyle = grad;
      g.fillRect(x + 1, y + 1, s / 2 - 2, bh - 2);
    }
  }
}

const PAINT: Record<TerrainId, Paint> = {
  plain: (g, s, v) => {
    base(g, s, v, "#b8ad74", ["rgba(205,196,140,0.35)", "rgba(160,150,96,0.25)"]);
    speckle(g, s, v, ["rgba(120,105,70,0.28)", "rgba(240,232,190,0.35)"], 30, s / 80);
    blades(g, s, v, ["rgba(120,140,70,0.6)", "rgba(150,160,80,0.5)"], 10);
  },
  grass: (g, s, v) => {
    base(g, s, v, "#79a548", ["rgba(95,140,50,0.6)", "rgba(150,185,80,0.4)"]);
    blades(g, s, v, ["#5b8a33", "#9cc25e", "#4c7a2b"], 40);
  },
  forest: (g, s, v) => {
    base(g, s, v, "#4d7a35", ["rgba(40,80,30,0.6)"]);
    const spots = [
      [0.28, 0.3],
      [0.72, 0.26],
      [0.5, 0.62],
      [0.2, 0.78],
      [0.82, 0.74],
    ] as const;
    for (const [i, [x, y]] of spots.entries())
      tree(g, s * (x + (hash(i, v, 31) - 0.5) * 0.08), s * y, s * (0.2 + hash(i, v, 32) * 0.06));
  },
  hills: (g, s, v) => {
    base(g, s, v, "#a39a5e", ["rgba(170,160,100,0.35)"]);
    // Two rounded rises, lit from the upper left, with grass on their crowns.
    for (const [x, y, rx, ry] of [
      [0.36, 0.6, 0.34, 0.24],
      [0.7, 0.4, 0.28, 0.2],
    ] as const) {
      g.fillStyle = "rgba(60,50,25,0.35)";
      g.beginPath();
      g.ellipse(s * (x + 0.04), s * (y + 0.05), s * rx, s * ry, 0, 0, Math.PI * 2);
      g.fill();
      const grad = g.createRadialGradient(
        s * (x - rx * 0.35),
        s * (y - ry * 0.5),
        0,
        s * x,
        s * y,
        s * rx,
      );
      grad.addColorStop(0, "#d8cf8e");
      grad.addColorStop(0.6, "#a99d5e");
      grad.addColorStop(1, "#7f7444");
      g.fillStyle = grad;
      g.beginPath();
      g.ellipse(s * x, s * y, s * rx, s * ry, 0, 0, Math.PI * 2);
      g.fill();
    }
    blades(g, s, v, ["rgba(110,140,60,0.7)", "rgba(140,160,70,0.6)"], 14);
  },
  mountain: (g, s, v) => {
    base(g, s, v, "#857a6c", ["rgba(100,92,80,0.6)"]);
    const peak = (cx: number, w: number, h: number) => {
      const top = s * (0.9 - h);
      g.fillStyle = "#6c6156";
      g.beginPath();
      g.moveTo(s * (cx - w), s * 0.92);
      g.lineTo(s * cx, top);
      g.lineTo(s * (cx + w), s * 0.92);
      g.closePath();
      g.fill();
      g.fillStyle = "#a59a8a";
      g.beginPath();
      g.moveTo(s * (cx - w), s * 0.92);
      g.lineTo(s * cx, top);
      g.lineTo(s * (cx - w * 0.15), s * 0.92);
      g.closePath();
      g.fill();
      g.fillStyle = "#f2f0ea";
      g.beginPath();
      g.moveTo(s * (cx - w * 0.28), top + s * h * 0.28);
      g.lineTo(s * cx, top);
      g.lineTo(s * (cx + w * 0.28), top + s * h * 0.28);
      g.closePath();
      g.fill();
    };
    peak(0.33 + (hash(1, v, 41) - 0.5) * 0.1, 0.3, 0.7);
    peak(0.7 + (hash(2, v, 41) - 0.5) * 0.1, 0.26, 0.55);
  },
  ford: (g, s, v) => {
    base(g, s, v, "#6fa6ad", ["rgba(190,175,120,0.55)", "rgba(80,140,160,0.5)"]);
    speckle(g, s, v, ["rgba(150,135,95,0.8)", "rgba(120,110,80,0.7)"], 8, s / 22);
    g.strokeStyle = "rgba(255,255,255,0.35)";
    g.lineWidth = Math.max(1, s / 40);
    for (const b of [0.3, 0.7]) {
      g.beginPath();
      g.moveTo(0, s * b);
      g.bezierCurveTo(s * 0.3, s * (b - 0.06), s * 0.6, s * (b + 0.06), s, s * b);
      g.stroke();
    }
  },
  water: (g, s, v) => {
    const grad = g.createLinearGradient(0, 0, s, s);
    grad.addColorStop(0, "#2f74a6");
    grad.addColorStop(1, "#245c8a");
    g.fillStyle = grad;
    g.fillRect(0, 0, s, s);
    base(g, s, v, "rgba(0,0,0,0)", ["rgba(80,150,200,0.35)", "rgba(20,60,100,0.35)"]);
    g.strokeStyle = "rgba(220,240,255,0.25)";
    g.lineWidth = Math.max(1, s / 48);
    for (let i = 0; i < 4; i++) {
      const y = s * (0.15 + i * 0.24 + (hash(i, v, 51) - 0.5) * 0.08);
      const x = s * hash(i, v, 52) * 0.5;
      g.beginPath();
      g.moveTo(x, y);
      g.quadraticCurveTo(x + s * 0.12, y - s * 0.04, x + s * 0.24, y);
      g.stroke();
    }
  },
  bridge: (g, s, v) => {
    PAINT.water(g, s, v);
    g.fillStyle = "rgba(0,0,0,0.35)";
    g.fillRect(s * 0.1, s * 0.06, s * 0.84, s * 0.92);
    for (let i = 0; i < 6; i++) {
      const y = s * (0.04 + i * 0.155);
      const grad = g.createLinearGradient(0, y, 0, y + s * 0.13);
      grad.addColorStop(0, "#b48a5c");
      grad.addColorStop(1, "#7d5a38");
      g.fillStyle = grad;
      g.fillRect(s * 0.08, y, s * 0.84, s * 0.13);
    }
    g.fillStyle = "#5a3f27";
    g.fillRect(s * 0.06, 0, s * 0.06, s);
    g.fillRect(s * 0.88, 0, s * 0.06, s);
  },
  village: (g, s, v) => {
    PAINT.grass(g, s, v);
    g.fillStyle = "rgba(170,150,100,0.6)";
    g.fillRect(0, s * 0.72, s, s * 0.12);
    house(g, s * 0.12, s * 0.22, s * 0.42);
    house(g, s * 0.55, s * 0.42, s * 0.36);
  },
  fort: (g, s, v) => {
    base(g, s, v, "#8d8578", ["rgba(110,104,95,0.5)"]);
    stones(g, s, 4, "#a9a194", "#7a7368");
    g.fillStyle = "rgba(0,0,0,0.3)";
    g.fillRect(s * 0.3, s * 0.45, s * 0.4, s * 0.55);
    g.fillStyle = "#3c2f24";
    g.beginPath();
    g.moveTo(s * 0.34, s);
    g.lineTo(s * 0.34, s * 0.6);
    g.quadraticCurveTo(s * 0.5, s * 0.42, s * 0.66, s * 0.6);
    g.lineTo(s * 0.66, s);
    g.closePath();
    g.fill();
    g.fillStyle = "#c33b2f";
    g.fillRect(s * 0.5, s * 0.08, s * 0.18, s * 0.12);
    g.fillStyle = "#4a3a2c";
    g.fillRect(s * 0.48, s * 0.06, s * 0.03, s * 0.34);
  },
  wall: (g, s) => {
    stones(g, s, 5, "#8f887e", "#5e5850");
    g.fillStyle = "rgba(255,255,255,0.12)";
    g.fillRect(0, 0, s, s * 0.08);
  },
  camp: (g, s, v) => {
    PAINT.plain(g, s, v);
    const tent = (x: number, y: number, w: number) => {
      g.fillStyle = "rgba(0,0,0,0.3)";
      g.beginPath();
      g.ellipse(x + w * 0.55, y + w * 0.78, w * 0.55, w * 0.14, 0, 0, Math.PI * 2);
      g.fill();
      g.fillStyle = "#e9dcbc";
      g.beginPath();
      g.moveTo(x, y + w * 0.75);
      g.lineTo(x + w * 0.5, y);
      g.lineTo(x + w, y + w * 0.75);
      g.closePath();
      g.fill();
      g.fillStyle = "#b9a982";
      g.beginPath();
      g.moveTo(x + w * 0.5, y);
      g.lineTo(x + w, y + w * 0.75);
      g.lineTo(x + w * 0.55, y + w * 0.75);
      g.closePath();
      g.fill();
      g.fillStyle = "#5c4630";
      g.fillRect(x + w * 0.44, y + w * 0.45, w * 0.12, w * 0.3);
    };
    tent(s * 0.1, s * 0.15, s * 0.5);
    tent(s * 0.48, s * 0.4, s * 0.44);
  },
};

let ours: TerrainSet | null = null;

/** Our own procedural terrain paintings (64 px, four variants each). */
export function ourTerrain(): TerrainSet {
  if (ours) return ours;
  const size = 64;
  const variants = new Map<TerrainId, HTMLCanvasElement[]>();
  for (const id of Object.keys(PAINT) as TerrainId[])
    variants.set(
      id,
      [0, 1, 2, 3].map((v) => {
        const [c, g] = canvas(size);
        PAINT[id](g, size, v);
        return c;
      }),
    );
  ours = { size, variants };
  return ours;
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
