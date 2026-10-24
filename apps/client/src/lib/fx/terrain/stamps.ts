/**
 * Small painted pieces the terrain is dressed with (ADR 0074): tree crowns by
 * species and season (elm, poplar, pine, willow, jujube, mulberry), grass
 * tufts, flowers, pebbles, rocks and crags, shrubs, reeds and ford stones,
 * plus the tileable overlays (grass blades, rock, paving slabs, grain). Our
 * own procedural art, painted once per resolution and landscape. Light comes
 * from the upper left everywhere.
 */
import type { Look } from "./landscape.ts";
import { css, hash, mixRGB, type RGB, scaleRGB } from "./noise.ts";
import type { TreeKind } from "./plan.ts";

export interface Stamp {
  readonly c: HTMLCanvasElement;
  /** Anchor (the piece's centre on the ground), in canvas pixels. */
  readonly ax: number;
  readonly ay: number;
}

export interface Library {
  readonly px: number;
  readonly crowns: Record<TreeKind, Stamp[]>;
  readonly shadow: Stamp;
  readonly tufts: Stamp[];
  readonly dry: Stamp[];
  readonly flowers: Stamp[];
  readonly pebbles: Stamp[];
  readonly rocks: Stamp[];
  readonly crags: Stamp[];
  readonly shrubs: Stamp[];
  readonly reeds: Stamp[];
  readonly stones: Stamp[];
  readonly grass: HTMLCanvasElement;
  readonly dirt: HTMLCanvasElement;
  readonly rock: HTMLCanvasElement;
  readonly pave: HTMLCanvasElement;
  readonly grain: HTMLCanvasElement;
  /** Canvas bytes held by the library. */
  readonly bytes: number;
}

/** Crown radius the sprites are painted at, in tiles (scaled per tree). */
export const CROWN_R = 0.26;

function canvas(w: number, h = w): [HTMLCanvasElement, CanvasRenderingContext2D] {
  const c = document.createElement("canvas");
  c.width = Math.max(1, Math.ceil(w));
  c.height = Math.max(1, Math.ceil(h));
  return [c, c.getContext("2d") as CanvasRenderingContext2D];
}

interface Leaf {
  dark: RGB;
  mid: RGB;
  light: RGB;
}

const SUMMER: Record<TreeKind, Leaf> = {
  elm: { dark: [44, 60, 34], mid: [70, 90, 48], light: [108, 124, 66] },
  poplar: { dark: [50, 68, 38], mid: [80, 100, 54], light: [122, 136, 76] },
  pine: { dark: [34, 48, 34], mid: [52, 70, 46], light: [82, 100, 64] },
  willow: { dark: [76, 92, 48], mid: [118, 132, 72], light: [164, 170, 104] },
  jujube: { dark: [54, 70, 38], mid: [84, 102, 54], light: [122, 134, 74] },
  mulberry: { dark: [42, 60, 34], mid: [66, 88, 46], light: [100, 120, 62] },
};

const AUTUMN: Leaf[] = [
  { dark: [100, 82, 44], mid: [146, 120, 60], light: [186, 160, 96] },
  { dark: [110, 98, 48], mid: [158, 142, 70], light: [196, 182, 114] },
  { dark: [102, 70, 42], mid: [138, 96, 56], light: [176, 136, 84] },
  { dark: [70, 76, 42], mid: [108, 110, 60], light: [150, 146, 88] },
];

function leafFor(kind: TreeKind, look: Look, v: number): Leaf {
  const base = SUMMER[kind];
  if (look.leaves === "fresh" && kind !== "pine") {
    const t: RGB = [128, 156, 74];
    return {
      dark: mixRGB(base.dark, t, 0.25),
      mid: mixRGB(base.mid, t, 0.35),
      light: mixRGB(base.light, [176, 196, 110], 0.4),
    };
  }
  if (look.leaves === "autumn" && kind !== "pine") {
    const pick = AUTUMN[
      (v + (kind === "poplar" ? 1 : kind === "willow" ? 3 : 0)) % AUTUMN.length
    ] as Leaf;
    return pick;
  }
  if (look.region === "frontier" || look.region === "loess") {
    // A drier, dustier green.
    return {
      dark: mixRGB(base.dark, [70, 70, 50], 0.2),
      mid: mixRGB(base.mid, [110, 108, 72], 0.2),
      light: mixRGB(base.light, [150, 146, 98], 0.2),
    };
  }
  return base;
}

/** A lobed outline, closed, around (cx, cy). */
function lobed(
  g: CanvasRenderingContext2D,
  cx: number,
  cy: number,
  r: number,
  lobes: number,
  p1: number,
  p2: number,
  depth = 0.1,
): void {
  g.beginPath();
  for (let k = 0; k <= 72; k++) {
    const a = (k / 72) * Math.PI * 2;
    const rr =
      r *
      (1 -
        depth * 0.6 +
        depth * Math.sin(lobes * a + p1) +
        depth * 0.5 * Math.sin((lobes + 3) * a + p2));
    const x = cx + Math.cos(a) * rr;
    const y = cy + Math.sin(a) * rr;
    if (k === 0) g.moveTo(x, y);
    else g.lineTo(x, y);
  }
  g.closePath();
}

function blob(g: CanvasRenderingContext2D, x: number, y: number, r: number, fill: string): void {
  g.fillStyle = fill;
  g.beginPath();
  g.arc(x, y, r, 0, Math.PI * 2);
  g.fill();
}

/** A broadleaf crown seen from above: a dark mass, leafy clumps lit from the upper left. */
function broadleaf(R: number, leaf: Leaf, seed: number, kind: TreeKind): Stamp {
  const size = Math.ceil(R * 2.5) + 4;
  const [c, g] = canvas(size);
  const cx = size / 2;
  const cy = size / 2;
  const h = (k: number) => hash(seed, k, 101);
  const sparse = kind === "jujube";
  const coarse = kind === "mulberry";
  const lobes = 6 + Math.floor(h(1) * 5);
  lobed(g, cx, cy, R * (sparse ? 0.9 : 1), lobes, h(2) * 6, h(3) * 6, sparse ? 0.16 : 0.1);
  g.fillStyle = css(leaf.dark);
  g.fill();
  g.save();
  lobed(g, cx, cy, R * (sparse ? 0.9 : 1), lobes, h(2) * 6, h(3) * 6, sparse ? 0.16 : 0.1);
  g.clip();
  // Clumps, pulled toward the light.
  const n = sparse ? 6 : coarse ? 7 : 10;
  for (let i = 0; i < n; i++) {
    const a = h(10 + i) * Math.PI * 2;
    const d = R * 0.55 * Math.sqrt(h(30 + i));
    const r = R * (coarse ? 0.36 : 0.28) * (0.8 + h(50 + i) * 0.5);
    const x = cx + Math.cos(a) * d - R * 0.1;
    const y = cy + Math.sin(a) * d - R * 0.12;
    blob(g, x + r * 0.18, y + r * 0.22, r, css(leaf.dark, 0.6));
    blob(g, x, y, r * 0.92, css(leaf.mid, 0.95));
  }
  for (let i = 0; i < (sparse ? 4 : 7); i++) {
    const a = Math.PI * (1.0 + h(70 + i) * 0.75);
    const d = R * (0.2 + h(80 + i) * 0.45);
    const r = R * (0.1 + h(90 + i) * 0.1);
    blob(g, cx + Math.cos(a) * d, cy + Math.sin(a) * d, r, css(leaf.light, 0.75));
  }
  // Leaf grain.
  for (let i = 0; i < 40; i++) {
    const a = h(200 + i) * Math.PI * 2;
    const d = R * Math.sqrt(h(260 + i));
    const r = Math.max(0.7, R * 0.05);
    blob(
      g,
      cx + Math.cos(a) * d,
      cy + Math.sin(a) * d,
      r,
      css(i % 3 ? leaf.dark : leaf.light, 0.35),
    );
  }
  if (sparse)
    for (let i = 0; i < 5; i++) {
      // Gaps where the ground shows through a jujube.
      const a = h(300 + i) * Math.PI * 2;
      const d = R * (0.3 + h(310 + i) * 0.5);
      g.globalCompositeOperation = "destination-out";
      blob(g, cx + Math.cos(a) * d, cy + Math.sin(a) * d, R * 0.12, "rgba(0,0,0,0.8)");
      g.globalCompositeOperation = "source-over";
    }
  // The far side in shade.
  const rim = g.createRadialGradient(cx - R * 0.35, cy - R * 0.4, R * 0.2, cx, cy, R * 1.15);
  rim.addColorStop(0, "rgba(255,250,220,0.10)");
  rim.addColorStop(0.55, "rgba(0,0,0,0)");
  rim.addColorStop(1, "rgba(10,18,8,0.45)");
  g.fillStyle = rim;
  g.fillRect(0, 0, size, size);
  g.restore();
  return { c, ax: cx, ay: cy };
}

/** A pine from above: whorls of needle clusters, darker and bluer. */
function pine(R: number, leaf: Leaf, seed: number): Stamp {
  const size = Math.ceil(R * 2.5) + 4;
  const [c, g] = canvas(size);
  const cx = size / 2;
  const cy = size / 2;
  const h = (k: number) => hash(seed, k, 103);
  const star = (r: number, spikes: number, rot: number, fill: string, ox = 0, oy = 0) => {
    g.beginPath();
    for (let k = 0; k <= spikes * 2; k++) {
      const a = rot + (k / (spikes * 2)) * Math.PI * 2;
      const rr = k % 2 === 0 ? r : r * (0.62 + h(k) * 0.1);
      const x = cx + ox + Math.cos(a) * rr;
      const y = cy + oy + Math.sin(a) * rr;
      if (k === 0) g.moveTo(x, y);
      else g.lineTo(x, y);
    }
    g.closePath();
    g.fillStyle = fill;
    g.fill();
  };
  star(R * 0.95, 11 + Math.floor(h(1) * 5), h(2) * 3, css(leaf.dark));
  star(R * 0.72, 9, h(3) * 3, css(leaf.mid), -R * 0.06, -R * 0.08);
  star(R * 0.45, 7, h(4) * 3, css(mixRGB(leaf.mid, leaf.light, 0.5)), -R * 0.1, -R * 0.12);
  blob(g, cx - R * 0.08, cy - R * 0.1, R * 0.14, css(leaf.light, 0.8));
  return { c, ax: cx, ay: cy };
}

/** A willow from above: drooping strands round a soft crown, yellow-green. */
function willow(R: number, leaf: Leaf, seed: number): Stamp {
  const size = Math.ceil(R * 2.6) + 4;
  const [c, g] = canvas(size);
  const cx = size / 2;
  const cy = size / 2;
  const h = (k: number) => hash(seed, k, 107);
  lobed(g, cx, cy, R * 0.95, 9, h(1) * 6, h(2) * 6, 0.12);
  g.fillStyle = css(leaf.dark, 0.85);
  g.fill();
  g.lineCap = "round";
  for (let i = 0; i < 46; i++) {
    const a = h(10 + i) * Math.PI * 2;
    const r0 = R * 0.15 * h(60 + i);
    const r1 = R * (0.75 + h(110 + i) * 0.35);
    const bend = (h(160 + i) - 0.5) * 0.5;
    g.strokeStyle = css(i % 3 === 0 ? leaf.light : leaf.mid, 0.75);
    g.lineWidth = Math.max(1, R * 0.06);
    g.beginPath();
    g.moveTo(cx + Math.cos(a) * r0, cy + Math.sin(a) * r0);
    g.quadraticCurveTo(
      cx + Math.cos(a + bend) * r1 * 0.6,
      cy + Math.sin(a + bend) * r1 * 0.6 + R * 0.08,
      cx + Math.cos(a) * r1,
      cy + Math.sin(a) * r1 + R * 0.12,
    );
    g.stroke();
  }
  blob(g, cx - R * 0.2, cy - R * 0.24, R * 0.3, css(leaf.light, 0.35));
  return { c, ax: cx, ay: cy };
}

/**
 * A bare winter crown from above: a dense mass of fine grey-brown twigs round
 * forking branches, lit from the upper left, as full as a summer crown.
 */
function bare(R: number, seed: number, tone: RGB): Stamp {
  const size = Math.ceil(R * 2.5) + 4;
  const [c, g] = canvas(size);
  const cx = size / 2;
  const cy = size / 2;
  const h = (k: number) => hash(seed, k, 109);
  const dark = scaleRGB(tone, 0.62);
  const light = mixRGB(tone, [214, 204, 190], 0.45);
  // The twig mass: a lobed body, denser toward the middle.
  lobed(g, cx, cy, R * 0.96, 9 + Math.floor(h(1) * 4), h(2) * 6, h(3) * 6, 0.12);
  const body = g.createRadialGradient(cx, cy, R * 0.1, cx, cy, R);
  body.addColorStop(0, css(dark, 0.95));
  body.addColorStop(0.65, css(tone, 0.8));
  body.addColorStop(1, css(tone, 0.55));
  g.fillStyle = body;
  g.fill();
  g.save();
  lobed(g, cx, cy, R * 1.02, 9 + Math.floor(h(1) * 4), h(2) * 6, h(3) * 6, 0.12);
  g.clip();
  g.lineCap = "round";
  // Branches forking out from the trunk.
  const branch = (x: number, y: number, a: number, len: number, wid: number, depth: number) => {
    const ex = x + Math.cos(a) * len;
    const ey = y + Math.sin(a) * len;
    g.strokeStyle = css(scaleRGB(dark, 0.85 + depth * 0.08), 0.95);
    g.lineWidth = Math.max(0.7, wid);
    g.beginPath();
    g.moveTo(x, y);
    g.lineTo(ex, ey);
    g.stroke();
    if (depth >= 4) return;
    for (let i = 0; i < 2; i++)
      branch(
        ex,
        ey,
        a + (h(depth * 17 + i * 5 + Math.floor(len * 3)) - 0.5) * 1.2,
        len * 0.62,
        wid * 0.62,
        depth + 1,
      );
  };
  const n = 6 + Math.floor(h(4) * 3);
  for (let i = 0; i < n; i++)
    branch(cx, cy, (i / n) * Math.PI * 2 + h(5 + i) * 0.5, R * 0.36, R * 0.08, 1);
  // Fine twigs, lighter at the tips.
  for (let i = 0; i < 220; i++) {
    const a = h(40 + i) * Math.PI * 2;
    const r = R * (0.3 + 0.7 * Math.sqrt(h(300 + i)));
    const len = R * (0.08 + h(600 + i) * 0.12);
    const x = cx + Math.cos(a) * r;
    const y = cy + Math.sin(a) * r;
    const b = a + (h(900 + i) - 0.5) * 0.9;
    g.strokeStyle = css(i % 3 === 0 ? light : tone, i % 3 === 0 ? 0.55 : 0.7);
    g.lineWidth = Math.max(0.6, R * 0.022);
    g.beginPath();
    g.moveTo(x, y);
    g.lineTo(x + Math.cos(b) * len, y + Math.sin(b) * len);
    g.stroke();
  }
  // Lit from the upper left, shaded on the far side.
  const rim = g.createRadialGradient(cx - R * 0.4, cy - R * 0.45, R * 0.1, cx, cy, R * 1.1);
  rim.addColorStop(0, "rgba(255,246,228,0.22)");
  rim.addColorStop(0.5, "rgba(0,0,0,0)");
  rim.addColorStop(1, "rgba(18,12,8,0.45)");
  g.fillStyle = rim;
  g.fillRect(0, 0, size, size);
  g.restore();
  blob(g, cx, cy, R * 0.07, css(scaleRGB(dark, 0.7)));
  return { c, ax: cx, ay: cy };
}

function crownsFor(kind: TreeKind, px: number, look: Look): Stamp[] {
  const R = CROWN_R * px;
  const out: Stamp[] = [];
  const n = kind === "elm" ? 6 : kind === "pine" ? 4 : 3;
  for (let v = 0; v < n; v++) {
    const seed = v * 31 + kind.length * 7;
    if (look.leaves === "bare" && kind !== "pine") {
      out.push(bare(R, seed, kind === "willow" ? [140, 132, 96] : [124, 110, 98]));
      continue;
    }
    const leaf = leafFor(kind, look, v);
    if (kind === "pine") out.push(pine(R, leaf, seed));
    else if (kind === "willow") out.push(willow(R, leaf, seed));
    else if (kind === "poplar") out.push(broadleaf(R * 0.72, leaf, seed, kind));
    else out.push(broadleaf(R, leaf, seed, kind));
  }
  return out;
}

function shadowStamp(): Stamp {
  const [c, g] = canvas(64);
  const gr = g.createRadialGradient(32, 32, 0, 32, 32, 32);
  gr.addColorStop(0, "rgba(16,14,8,0.55)");
  gr.addColorStop(0.6, "rgba(16,14,8,0.3)");
  gr.addColorStop(1, "rgba(16,14,8,0)");
  g.fillStyle = gr;
  g.fillRect(0, 0, 64, 64);
  return { c, ax: 32, ay: 32 };
}

function tuft(px: number, cols: RGB[], seed: number, tall: number): Stamp {
  const s = Math.ceil(px * 0.16 * tall) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 113);
  g.lineCap = "round";
  const n = 6 + Math.floor(h(1) * 5);
  const bx = s / 2;
  const by = s * 0.8;
  for (let i = 0; i < n; i++) {
    const len = px * (0.05 + h(10 + i) * 0.06) * tall;
    const lean = (h(20 + i) - 0.5) * 1.3;
    const x0 = bx + (h(30 + i) - 0.5) * px * 0.05;
    g.strokeStyle = css(cols[i % cols.length] as RGB, 0.85);
    g.lineWidth = Math.max(1, px / 95);
    g.beginPath();
    g.moveTo(x0, by);
    g.quadraticCurveTo(x0 + lean * len * 0.3, by - len * 0.6, x0 + lean * len * 0.7, by - len);
    g.stroke();
  }
  return { c, ax: bx, ay: by - px * 0.02 };
}

function flowers(px: number, seed: number): Stamp {
  const s = Math.ceil(px * 0.1) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 127);
  const cols: RGB[] = [
    [228, 214, 140],
    [236, 232, 214],
    [196, 168, 196],
  ];
  const col = cols[seed % cols.length] as RGB;
  for (let i = 0; i < 5; i++)
    blob(
      g,
      s / 2 + (h(i) - 0.5) * px * 0.07,
      s / 2 + (h(i + 9) - 0.5) * px * 0.07,
      Math.max(0.8, px / 100),
      css(col, 0.9),
    );
  return { c, ax: s / 2, ay: s / 2 };
}

function pebbles(px: number, look: Look, seed: number): Stamp {
  const s = Math.ceil(px * 0.12) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 131);
  const base = mixRGB(look.rock[0], look.loess[1], 0.4);
  for (let i = 0; i < 3; i++) {
    const x = s / 2 + (h(i) - 0.5) * px * 0.07;
    const y = s / 2 + (h(i + 5) - 0.5) * px * 0.05;
    const r = px * (0.008 + h(i + 9) * 0.012);
    g.fillStyle = "rgba(30,24,16,0.3)";
    g.beginPath();
    g.ellipse(x + r * 0.3, y + r * 0.35, r, r * 0.75, 0, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = css(scaleRGB(base, 0.9 + h(i + 13) * 0.2));
    g.beginPath();
    g.ellipse(x, y, r, r * 0.75, 0, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = "rgba(255,250,230,0.35)";
    g.beginPath();
    g.ellipse(x - r * 0.3, y - r * 0.25, r * 0.45, r * 0.3, 0, 0, Math.PI * 2);
    g.fill();
  }
  return { c, ax: s / 2, ay: s / 2 };
}

function rockShape(
  g: CanvasRenderingContext2D,
  x: number,
  y: number,
  r: number,
  base: RGB,
  seed: number,
): void {
  const h = (k: number) => hash(seed, k, 137);
  const n = 5 + Math.floor(h(1) * 3);
  const pts: [number, number][] = [];
  for (let k = 0; k < n; k++) {
    const a = (k / n) * Math.PI * 2 + h(2 + k) * 0.5;
    const rr = r * (0.7 + h(10 + k) * 0.4);
    pts.push([x + Math.cos(a) * rr, y + Math.sin(a) * rr * 0.8]);
  }
  const path = (ox: number, oy: number) => {
    g.beginPath();
    pts.forEach(([px_, py], k) => {
      if (k === 0) g.moveTo(px_ + ox, py + oy);
      else g.lineTo(px_ + ox, py + oy);
    });
    g.closePath();
  };
  path(r * 0.25, r * 0.3);
  g.fillStyle = "rgba(20,16,10,0.35)";
  g.fill();
  path(0, 0);
  g.fillStyle = css(scaleRGB(base, 0.82));
  g.fill();
  // The lit facet (upper left) and a dark seam.
  g.save();
  path(0, 0);
  g.clip();
  g.fillStyle = css(scaleRGB(base, 1.12));
  g.beginPath();
  g.moveTo(x - r * 1.2, y - r * 1.2);
  g.lineTo(x + r * (0.3 + h(30) * 0.3), y - r * 1.2);
  g.lineTo(x - r * (0.1 - h(31) * 0.3), y + r * 0.1);
  g.lineTo(x - r * 1.2, y + r * (0.2 + h(32) * 0.3));
  g.closePath();
  g.fill();
  g.restore();
  path(0, 0);
  g.strokeStyle = "rgba(30,24,18,0.35)";
  g.lineWidth = Math.max(0.8, r * 0.08);
  g.stroke();
}

function rock(px: number, look: Look, seed: number, size: number): Stamp {
  const s = Math.ceil(px * size * 2.6) + 4;
  const [c, g] = canvas(s);
  const base = mixRGB(look.rock[0], [150, 140, 124], 0.2);
  rockShape(g, s / 2, s / 2, px * size, base, seed);
  return { c, ax: s / 2, ay: s / 2 };
}

function crag(px: number, look: Look, seed: number): Stamp {
  const s = Math.ceil(px * 0.62) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 139);
  const base = mixRGB(look.rock[0], look.rock[1], 0.3);
  const n = 3 + Math.floor(h(1) * 3);
  const parts: [number, number, number][] = [];
  for (let i = 0; i < n; i++)
    parts.push([
      s / 2 + (h(2 + i) - 0.5) * px * 0.26,
      s / 2 + (h(9 + i) - 0.5) * px * 0.18,
      px * (0.06 + h(16 + i) * 0.07),
    ]);
  parts.sort((a, b) => a[1] - b[1]);
  parts.forEach(([x, y, r], i) => {
    rockShape(g, x, y, r, scaleRGB(base, 0.95 + h(30 + i) * 0.12), seed * 7 + i);
  });
  return { c, ax: s / 2, ay: s / 2 };
}

function shrub(px: number, look: Look, seed: number): Stamp {
  const s = Math.ceil(px * 0.24) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 149);
  const leaf = look.leaves === "bare" ? null : leafFor("elm", look, seed);
  const dark: RGB = leaf ? leaf.dark : [104, 90, 72];
  const mid: RGB = leaf ? leaf.mid : [136, 120, 96];
  const light: RGB = leaf ? leaf.light : [160, 146, 120];
  const r = px * (0.04 + h(1) * 0.03);
  blob(g, s / 2 + r * 0.4, s / 2 + r * 0.5, r * 1.1, "rgba(20,18,10,0.3)");
  for (let i = 0; i < 3; i++) {
    const x = s / 2 + (h(2 + i) - 0.5) * r * 1.2;
    const y = s / 2 + (h(6 + i) - 0.5) * r;
    blob(g, x, y, r * (0.65 + h(10 + i) * 0.3), css(dark));
    blob(g, x - r * 0.15, y - r * 0.18, r * 0.5, css(mid));
    blob(g, x - r * 0.3, y - r * 0.32, r * 0.22, css(light, 0.8));
  }
  return { c, ax: s / 2, ay: s / 2 };
}

function reeds(px: number, look: Look, seed: number): Stamp {
  const s = Math.ceil(px * 0.22) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 151);
  const dry = look.season === "autumn" || look.season === "winter";
  const cols: RGB[] = dry
    ? [
        [176, 156, 104],
        [150, 128, 84],
        [196, 180, 128],
      ]
    : [
        [122, 140, 72],
        [96, 116, 58],
        [150, 160, 92],
      ];
  g.lineCap = "round";
  const bx = s / 2;
  const by = s * 0.85;
  for (let i = 0; i < 9; i++) {
    const len = px * (0.08 + h(i) * 0.08);
    const lean = (h(i + 20) - 0.5) * 0.6;
    const x0 = bx + (h(i + 40) - 0.5) * px * 0.08;
    g.strokeStyle = css(cols[i % 3] as RGB, 0.9);
    g.lineWidth = Math.max(1, px / 90);
    g.beginPath();
    g.moveTo(x0, by);
    g.lineTo(x0 + lean * len, by - len);
    g.stroke();
    if (i % 3 === 0) {
      g.fillStyle = css(dry ? [120, 96, 66] : [110, 92, 60], 0.9);
      g.beginPath();
      g.ellipse(x0 + lean * len, by - len, px * 0.008, px * 0.018, lean, 0, Math.PI * 2);
      g.fill();
    }
  }
  return { c, ax: bx, ay: by - px * 0.03 };
}

function stone(px: number, look: Look, seed: number): Stamp {
  const s = Math.ceil(px * 0.2) + 4;
  const [c, g] = canvas(s);
  const h = (k: number) => hash(seed, k, 157);
  const r = px * (0.04 + h(1) * 0.025);
  const x = s / 2;
  const y = s / 2;
  // A ring of foam where the current meets it.
  g.strokeStyle = "rgba(232,236,226,0.55)";
  g.lineWidth = Math.max(1, px / 70);
  g.beginPath();
  g.ellipse(x - r * 0.1, y - r * 0.05, r * 1.35, r * 1.05, 0, Math.PI * 0.9, Math.PI * 2.1);
  g.stroke();
  const base = mixRGB(look.rock[0], [140, 138, 128], 0.3);
  g.fillStyle = css(scaleRGB(base, 0.7));
  g.beginPath();
  g.ellipse(x + r * 0.12, y + r * 0.14, r, r * 0.8, 0, 0, Math.PI * 2);
  g.fill();
  g.fillStyle = css(base);
  g.beginPath();
  g.ellipse(x, y, r, r * 0.78, 0, 0, Math.PI * 2);
  g.fill();
  g.fillStyle = "rgba(255,252,240,0.4)";
  g.beginPath();
  g.ellipse(x - r * 0.3, y - r * 0.28, r * 0.45, r * 0.3, -0.4, 0, Math.PI * 2);
  g.fill();
  return { c, ax: x, ay: y };
}

/** Draws at (x, y) and its wrapped copies, so a pattern tiles without seams. */
function wrapped(
  P: number,
  x: number,
  y: number,
  m: number,
  draw: (x: number, y: number) => void,
): void {
  for (const ox of [-P, 0, P])
    for (const oy of [-P, 0, P]) {
      const X = x + ox;
      const Y = y + oy;
      if (X < -m || Y < -m || X > P + m || Y > P + m) continue;
      draw(X, Y);
    }
}

function grassPattern(px: number, look: Look): HTMLCanvasElement {
  const P = px * 2;
  const [c, g] = canvas(P);
  const dark = scaleRGB(look.grass[1], 0.72);
  const light = mixRGB(look.grass[2], [210, 206, 150], 0.25);
  g.lineCap = "round";
  const n = Math.round(4 * 260);
  for (let i = 0; i < n; i++) {
    const x = hash(i, 1, 163) * P;
    const y = hash(i, 2, 163) * P;
    const len = px * (0.022 + hash(i, 3, 163) * 0.035);
    const lean = (hash(i, 4, 163) - 0.5) * 0.9;
    const col = i % 3 === 0 ? light : dark;
    g.strokeStyle = css(col, i % 3 === 0 ? 0.4 : 0.48);
    g.lineWidth = Math.max(0.8, px / 110);
    wrapped(P, x, y, len, (X, Y) => {
      g.beginPath();
      g.moveTo(X, Y);
      g.lineTo(X + lean * len, Y - len);
      g.stroke();
    });
  }
  return c;
}

function rockPattern(px: number, look: Look): HTMLCanvasElement {
  const P = px * 2;
  const [c, g] = canvas(P);
  const dark = scaleRGB(look.rock[1], 0.6);
  const light = scaleRGB(look.rock[0], 1.2);
  g.lineCap = "round";
  for (let i = 0; i < 70; i++) {
    let x = hash(i, 1, 167) * P;
    let y = hash(i, 2, 167) * P;
    const seg = 3 + Math.floor(hash(i, 3, 167) * 3);
    g.strokeStyle = css(dark, 0.32);
    g.lineWidth = Math.max(0.8, px / 80);
    const pts: [number, number][] = [[x, y]];
    for (let k = 0; k < seg; k++) {
      x += (hash(i, 10 + k, 167) - 0.3) * px * 0.08;
      y += (hash(i, 20 + k, 167) - 0.5) * px * 0.05;
      pts.push([x, y]);
    }
    const p0 = pts[0] as [number, number];
    wrapped(P, 0, 0, px * 0.4, (ox, oy) => {
      g.beginPath();
      g.moveTo(p0[0] + ox, p0[1] + oy);
      for (const [qx, qy] of pts) g.lineTo(qx + ox, qy + oy);
      g.stroke();
    });
  }
  for (let i = 0; i < 900; i++) {
    const x = hash(i, 4, 167) * P;
    const y = hash(i, 5, 167) * P;
    g.fillStyle = css(i % 2 ? light : dark, 0.16);
    g.fillRect(x, y, Math.max(1, px / 70), Math.max(1, px / 70));
  }
  return c;
}

/** Paving: rows of stone flags in a running bond, worn, a little moss in the joints. */
function pavePattern(px: number, look: Look): HTMLCanvasElement {
  const P = px * 3;
  const [c, g] = canvas(P);
  const rows = 13;
  const rh = P / rows;
  const joint = Math.max(1, px / 70);
  for (let r = 0; r < rows; r++) {
    const y = r * rh;
    const cuts: number[] = [];
    let x = hash(r, 1, 173) * px * 0.4;
    while (x < P) {
      cuts.push(x);
      x += px * (0.28 + hash(r, cuts.length + 3, 173) * 0.26);
    }
    // Close the row so the last flag wraps to the first.
    const first = cuts[0] as number;
    for (let k = 0; k < cuts.length; k++) {
      const a = cuts[k] as number;
      const b = k + 1 < cuts.length ? (cuts[k + 1] as number) : first + P;
      const t = hash(r, k + 50, 173);
      const fill =
        t < 0.3
          ? "rgba(255,248,226,0.12)"
          : t < 0.55
            ? "rgba(60,44,24,0.09)"
            : t < 0.7
              ? "rgba(150,120,70,0.10)"
              : "rgba(0,0,0,0)";
      g.fillStyle = fill;
      g.fillRect(a, y, b - a, rh);
      if (b > P) g.fillRect(a - P, y, b - a, rh);
      // A lit upper edge and a shaded lower edge on every flag.
      g.fillStyle = "rgba(255,250,230,0.14)";
      g.fillRect(a + joint, y + joint, b - a - joint * 2, joint);
      if (b > P) g.fillRect(a - P + joint, y + joint, b - a - joint * 2, joint);
    }
    g.fillStyle = "rgba(48,42,32,0.26)";
    g.fillRect(0, y, P, joint);
    for (const a of cuts) g.fillRect(a - joint / 2, y, joint, rh);
  }
  const moss = mixRGB(look.grass[1], [60, 70, 40], 0.4);
  for (let i = 0; i < 160; i++) {
    g.fillStyle = css(moss, 0.18);
    g.fillRect(
      hash(i, 1, 179) * P,
      Math.floor(hash(i, 2, 179) * rows) * rh - joint,
      px * 0.03,
      joint * 2,
    );
  }
  for (let i = 0; i < 9; i++) {
    g.strokeStyle = "rgba(50,44,34,0.3)";
    g.lineWidth = Math.max(0.8, px / 120);
    const x = hash(i, 3, 179) * P;
    const y = hash(i, 4, 179) * P;
    g.beginPath();
    g.moveTo(x, y);
    g.lineTo(x + px * 0.05, y + px * 0.03);
    g.lineTo(x + px * 0.08, y + px * 0.07);
    g.stroke();
  }
  return c;
}

/** Loess: dry grass flecks, grit and small cracks, laid over all open ground. */
function dirtPattern(px: number, look: Look): HTMLCanvasElement {
  const P = px * 2;
  const [c, g] = canvas(P);
  const dry: RGB =
    look.leaves === "bare" ? [150, 132, 96] : mixRGB(look.grass[0], [170, 156, 104], 0.5);
  const dark = scaleRGB(look.loess[1], 0.62);
  const light = mixRGB(look.loess[2], [240, 232, 206], 0.4);
  g.lineCap = "round";
  for (let i = 0; i < 520; i++) {
    const x = hash(i, 1, 191) * P;
    const y = hash(i, 2, 191) * P;
    const len = px * (0.015 + hash(i, 3, 191) * 0.03);
    const lean = (hash(i, 4, 191) - 0.5) * 1.6;
    g.strokeStyle = css(i % 4 === 0 ? light : dry, 0.4);
    g.lineWidth = Math.max(0.8, px / 120);
    wrapped(P, x, y, len, (X, Y) => {
      g.beginPath();
      g.moveTo(X, Y);
      g.lineTo(X + lean * len, Y - len);
      g.stroke();
    });
  }
  for (let i = 0; i < 700; i++) {
    const x = hash(i, 5, 191) * P;
    const y = hash(i, 6, 191) * P;
    const r = Math.max(0.6, px * (0.004 + hash(i, 7, 191) * 0.006));
    g.fillStyle = css(i % 3 === 0 ? light : dark, 0.3);
    g.fillRect(x, y, r, r);
  }
  for (let i = 0; i < 26; i++) {
    let x = hash(i, 8, 191) * P;
    let y = hash(i, 9, 191) * P;
    g.strokeStyle = css(dark, 0.18);
    g.lineWidth = Math.max(0.8, px / 110);
    g.beginPath();
    g.moveTo(x, y);
    for (let k = 0; k < 3; k++) {
      x += (hash(i, 10 + k, 191) - 0.5) * px * 0.1;
      y += (hash(i, 20 + k, 191) - 0.5) * px * 0.06;
      g.lineTo(x, y);
    }
    g.stroke();
  }
  return c;
}

function grainPattern(): HTMLCanvasElement {
  const P = 256;
  const [c, g] = canvas(P);
  const img = g.createImageData(P, P);
  for (let i = 0; i < P * P; i++) {
    const v = hash(i, 7, 181);
    const o = i * 4;
    if (v < 0.5) {
      img.data[o] = 40;
      img.data[o + 1] = 32;
      img.data[o + 2] = 20;
      img.data[o + 3] = Math.round(v * 2 * 46);
    } else {
      img.data[o] = 255;
      img.data[o + 1] = 248;
      img.data[o + 2] = 225;
      img.data[o + 3] = Math.round((v - 0.5) * 2 * 34);
    }
  }
  g.putImageData(img, 0, 0);
  return c;
}

const libs = new Map<string, Library>();

export function library(px: number, look: Look): Library {
  const key = `${px}|${look.key}`;
  const hit = libs.get(key);
  if (hit) return hit;
  // Keep only a few around (a battle and a scene, or a gallery's worth).
  if (libs.size >= 6) {
    const first = libs.keys().next().value;
    if (first !== undefined) libs.delete(first);
  }
  const grassDark = scaleRGB(look.grass[1], 0.78);
  const grassLight = mixRGB(look.grass[2], [190, 196, 130], 0.3);
  const dryCols: RGB[] = [
    [168, 150, 100],
    [146, 126, 84],
    [186, 170, 120],
  ];
  const tuftCols: RGB[] =
    look.leaves === "bare"
      ? dryCols
      : [grassDark, look.grass[0], grassLight, scaleRGB(look.grass[1], 0.9)];
  const kinds: TreeKind[] = ["elm", "poplar", "pine", "willow", "jujube", "mulberry"];
  const crowns = {} as Record<TreeKind, Stamp[]>;
  for (const k of kinds) crowns[k] = crownsFor(k, px, look);
  const range = (n: number) => Array.from({ length: n }, (_, i) => i);
  const lib: Library = {
    px,
    crowns,
    shadow: shadowStamp(),
    tufts: range(8).map((i) => tuft(px, tuftCols, i, 0.8 + (i % 3) * 0.25)),
    dry: range(4).map((i) => tuft(px, dryCols, i + 20, 0.9)),
    flowers: range(3).map((i) => flowers(px, i)),
    pebbles: range(4).map((i) => pebbles(px, look, i)),
    rocks: range(6).map((i) => rock(px, look, i, 0.04 + (i % 3) * 0.025)),
    crags: range(4).map((i) => crag(px, look, i)),
    shrubs: range(4).map((i) => shrub(px, look, i)),
    reeds: range(4).map((i) => reeds(px, look, i)),
    stones: range(4).map((i) => stone(px, look, i)),
    grass: grassPattern(px, look),
    dirt: dirtPattern(px, look),
    rock: rockPattern(px, look),
    pave: pavePattern(px, look),
    grain: grainPattern(),
    bytes: 0,
  };
  let bytes = 0;
  const count = (c: HTMLCanvasElement) => {
    bytes += c.width * c.height * 4;
  };
  for (const k of kinds) for (const s of lib.crowns[k]) count(s.c);
  for (const list of [
    lib.tufts,
    lib.dry,
    lib.flowers,
    lib.pebbles,
    lib.rocks,
    lib.crags,
    lib.shrubs,
    lib.reeds,
    lib.stones,
  ])
    for (const s of list) count(s.c);
  for (const c of [lib.grass, lib.dirt, lib.rock, lib.pave, lib.grain]) count(c);
  const out = { ...lib, bytes };
  libs.set(key, out);
  return out;
}

/** Draws a stamp centred on (u, v) in tile units, scaled; the context is in tile units. */
export function stamp(
  g: CanvasRenderingContext2D,
  s: Stamp,
  u: number,
  v: number,
  px: number,
  k = 1,
): void {
  const w = (s.c.width / px) * k;
  const h = (s.c.height / px) * k;
  g.drawImage(s.c, u - (s.ax / px) * k, v - (s.ay / px) * k, w, h);
}
