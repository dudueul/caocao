/**
 * Built terrain drawn as vectors in tile units (ADR 0074), after the late
 * Eastern Han: rammed-earth (판축) walls of yellow-brown loess with sloping
 * faces and a crenellated parapet, timber gate towers (門樓) and corner towers
 * (角樓) under grey-tiled hip roofs, walled courtyard compounds (tiled roofs for
 * the better-off, thatch and earth walls for the poor), watchtowers (望樓),
 * granaries (倉, 囷) and wells with a pulley frame, dry-land millet and wheat
 * strips, tents inside timber palisades on earth ramparts, and timber bridges
 * on posts. Our own shapes. Light comes from the upper left.
 */
import type { Look } from "./landscape.ts";
import { css, hash, mixRGB, type RGB, scaleRGB } from "./noise.ts";
import {
  CAMP,
  E,
  END,
  type Feature,
  type Fence,
  FORT,
  N,
  type Plan,
  S,
  TOWER,
  type TreeKind,
  VILLAGE,
  W,
  WALL,
} from "./plan.ts";
import { CROWN_R, type Library, stamp } from "./stamps.ts";

export interface Pen {
  readonly g: CanvasRenderingContext2D;
  readonly px: number;
  readonly look: Look;
  readonly lib: Library;
}

export interface Rect {
  readonly x0: number;
  readonly y0: number;
  readonly x1: number;
  readonly y1: number;
}

type Pt = readonly [number, number];

const TILE_ROOF = {
  light: [152, 150, 144],
  mid: [122, 120, 115],
  dark: [92, 90, 87],
  ridge: [66, 64, 61],
} as const;
const THATCH = {
  light: [172, 146, 98],
  mid: [142, 116, 76],
  dark: [104, 84, 54],
  ridge: [84, 66, 42],
} as const;
const TIMBER: RGB = [112, 74, 48];
const LACQUER: RGB = [128, 52, 38];
const PLASTER: RGB = [214, 200, 168];

interface Roof {
  readonly light: RGB;
  readonly mid: RGB;
  readonly dark: RGB;
  readonly ridge: RGB;
}

function poly(g: CanvasRenderingContext2D, pts: readonly Pt[]): void {
  g.beginPath();
  pts.forEach(([x, y], k) => {
    if (k === 0) g.moveTo(x, y);
    else g.lineTo(x, y);
  });
  g.closePath();
}

function fillPoly(g: CanvasRenderingContext2D, pts: readonly Pt[], fill: string): void {
  poly(g, pts);
  g.fillStyle = fill;
  g.fill();
}

function rect(
  g: CanvasRenderingContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  fill: string,
): void {
  g.fillStyle = fill;
  g.fillRect(x, y, w, h);
}

/** A soft shadow cast to the lower right. */
function shadow(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  lift: number,
  alpha = 0.26,
): void {
  const { g } = p;
  const dx = 0.05 + lift * 0.5;
  const dy = 0.06 + lift * 0.6;
  g.fillStyle = `rgba(22,18,10,${alpha})`;
  g.beginPath();
  g.moveTo(x + dx * 0.4, y + h);
  g.lineTo(x + w, y + dy * 0.2);
  g.lineTo(x + w + dx, y + dy);
  g.lineTo(x + w + dx, y + h + dy);
  g.lineTo(x + dx, y + h + dy);
  g.closePath();
  g.fill();
}

/** Lines across a roof plane: tile rows, or straw. */
function hatch(
  p: Pen,
  pts: readonly Pt[],
  along: "x" | "y",
  step: number,
  color: string,
  width: number,
): void {
  const { g } = p;
  let minX = 1e9;
  let minY = 1e9;
  let maxX = -1e9;
  let maxY = -1e9;
  for (const [x, y] of pts) {
    minX = Math.min(minX, x);
    minY = Math.min(minY, y);
    maxX = Math.max(maxX, x);
    maxY = Math.max(maxY, y);
  }
  g.save();
  poly(g, pts);
  g.clip();
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  if (along === "x")
    for (let x = minX + step / 2; x < maxX; x += step) {
      g.moveTo(x, minY);
      g.lineTo(x, maxY);
    }
  else
    for (let y = minY + step / 2; y < maxY; y += step) {
      g.moveTo(minX, y);
      g.lineTo(maxX, y);
    }
  g.stroke();
  g.restore();
}

/**
 * A hip roof over (x, y, w, h): four planes, the north and west lit, the south
 * and east in shade, a ridge along the longer side.
 */
function hipRoof(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  roof: Roof,
  thatch = false,
): void {
  const { g, px } = p;
  const wide = w >= h;
  const inset = (wide ? h : w) / 2;
  const r0: Pt = wide ? [x + inset, y + h / 2] : [x + w / 2, y + inset];
  const r1: Pt = wide ? [x + w - inset, y + h / 2] : [x + w / 2, y + h - inset];
  const fixN: Pt[] = wide ? [[x, y], [x + w, y], r1, r0] : [[x, y], [x + w, y], r0];
  const fixS: Pt[] = wide ? [[x, y + h], [x + w, y + h], r1, r0] : [[x, y + h], [x + w, y + h], r1];
  const fixW: Pt[] = wide ? [[x, y], [x, y + h], r0] : [[x, y], [x, y + h], r1, r0];
  const fixE: Pt[] = wide ? [[x + w, y], [x + w, y + h], r1] : [[x + w, y], [x + w, y + h], r1, r0];
  fillPoly(g, fixN, css(roof.light));
  fillPoly(g, fixW, css(mixRGB(roof.light, roof.mid, 0.45)));
  fillPoly(g, fixE, css(mixRGB(roof.mid, roof.dark, 0.5)));
  fillPoly(g, fixS, css(roof.dark));
  const line = Math.max(1, px / 90) / px;
  const step = thatch ? 0.022 : 0.034;
  const ink = thatch ? "rgba(70,52,30,0.22)" : "rgba(30,28,26,0.28)";
  hatch(p, fixN, "x", step, ink, line);
  hatch(p, fixS, "x", step, ink, line);
  hatch(p, fixW, "y", step, ink, line);
  hatch(p, fixE, "y", step, ink, line);
  // Ridge and hips.
  g.strokeStyle = css(roof.ridge);
  g.lineCap = "round";
  g.lineWidth = Math.max(0.025, line * 2.2);
  g.beginPath();
  g.moveTo(r0[0], r0[1]);
  g.lineTo(r1[0], r1[1]);
  g.stroke();
  g.lineWidth = line * 1.4;
  g.beginPath();
  for (const [cx, cy, r] of [
    [x, y, r0],
    [x + w, y, wide ? r1 : r0],
    [x, y + h, wide ? r0 : r1],
    [x + w, y + h, r1],
  ] as const) {
    g.moveTo(cx, cy);
    g.lineTo(r[0], r[1]);
  }
  g.stroke();
  if (!thatch) {
    // Upturned ridge ends.
    g.fillStyle = css(roof.ridge);
    for (const r of [r0, r1]) {
      g.beginPath();
      g.arc(r[0], r[1], Math.max(0.018, line * 2), 0, Math.PI * 2);
      g.fill();
    }
  }
  g.strokeStyle = "rgba(20,18,14,0.45)";
  g.lineWidth = line;
  g.strokeRect(x, y, w, h);
}

/** A gable roof: two planes either side of a ridge along its length. */
function gableRoof(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  roof: Roof,
  thatch: boolean,
): void {
  const { g, px } = p;
  const line = Math.max(1, px / 90) / px;
  const wide = w >= h;
  const step = thatch ? 0.022 : 0.034;
  const ink = thatch ? "rgba(70,52,30,0.24)" : "rgba(30,28,26,0.28)";
  if (thatch) {
    // Thatch: a soft, thick, rounded mat.
    g.fillStyle = css(roof.dark);
    g.beginPath();
    g.roundRect(x, y, w, h, Math.min(w, h) * 0.3);
    g.fill();
  }
  if (wide) {
    const a: Pt[] = [
      [x, y],
      [x + w, y],
      [x + w, y + h / 2],
      [x, y + h / 2],
    ];
    const b: Pt[] = [
      [x, y + h / 2],
      [x + w, y + h / 2],
      [x + w, y + h],
      [x, y + h],
    ];
    if (!thatch) {
      fillPoly(g, a, css(roof.light));
      fillPoly(g, b, css(roof.dark));
    } else {
      g.save();
      g.beginPath();
      g.roundRect(x, y, w, h, Math.min(w, h) * 0.3);
      g.clip();
      fillPoly(g, a, css(roof.light));
      fillPoly(g, b, css(roof.mid));
      g.restore();
    }
    hatch(p, a, "x", step, ink, line);
    hatch(p, b, "x", step, ink, line);
    g.strokeStyle = css(roof.ridge);
    g.lineWidth = Math.max(0.022, line * 2);
    g.beginPath();
    g.moveTo(x + (thatch ? w * 0.06 : 0), y + h / 2);
    g.lineTo(x + w - (thatch ? w * 0.06 : 0), y + h / 2);
    g.stroke();
    if (!thatch) {
      // Gable ends: a darker barge board.
      g.fillStyle = "rgba(40,36,30,0.35)";
      g.fillRect(x, y, line * 1.5, h);
      g.fillRect(x + w - line * 1.5, y, line * 1.5, h);
    }
  } else {
    const a: Pt[] = [
      [x, y],
      [x + w / 2, y],
      [x + w / 2, y + h],
      [x, y + h],
    ];
    const b: Pt[] = [
      [x + w / 2, y],
      [x + w, y],
      [x + w, y + h],
      [x + w / 2, y + h],
    ];
    g.save();
    if (thatch) {
      g.beginPath();
      g.roundRect(x, y, w, h, Math.min(w, h) * 0.3);
      g.clip();
    }
    fillPoly(g, a, css(thatch ? roof.light : mixRGB(roof.light, roof.mid, 0.3)));
    fillPoly(g, b, css(thatch ? roof.mid : roof.dark));
    g.restore();
    hatch(p, a, "y", step, ink, line);
    hatch(p, b, "y", step, ink, line);
    g.strokeStyle = css(roof.ridge);
    g.lineWidth = Math.max(0.022, line * 2);
    g.beginPath();
    g.moveTo(x + w / 2, y + (thatch ? h * 0.06 : 0));
    g.lineTo(x + w / 2, y + h - (thatch ? h * 0.06 : 0));
    g.stroke();
  }
  if (!thatch) {
    g.strokeStyle = "rgba(20,18,14,0.45)";
    g.lineWidth = line;
    g.strokeRect(x, y, w, h);
  }
}

/** A building: its south wall (plaster or earth, with a door), then its roof. */
function building(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  roof: "hip" | "gable" | "thatch",
  seed: number,
  earthWall = false,
): void {
  const { g, look } = p;
  const eave = 0.05;
  const wallH = Math.min(0.07, h * 0.2);
  shadow(p, x - eave, y - eave, w + eave * 2, h + eave, 0.06);
  const face = earthWall ? mixRGB(look.earth, [150, 126, 90], 0.3) : PLASTER;
  rect(g, x, y + h - wallH, w, wallH, css(face));
  rect(g, x, y + h - wallH * 0.25, w, wallH * 0.25, css(scaleRGB(face, 0.8)));
  // A door, and for wider buildings a lattice window.
  const dw = Math.min(0.08, w * 0.2);
  const dx = x + w * (0.35 + hash(seed, 1, 211) * 0.3) - dw / 2;
  rect(g, dx, y + h - wallH, dw, wallH, css([58, 40, 28]));
  if (w > 0.4)
    rect(g, x + w * 0.15, y + h - wallH * 0.8, w * 0.1, wallH * 0.4, "rgba(40,30,20,0.6)");
  const rx = x - eave;
  const ry = y - eave;
  const rw = w + eave * 2;
  const rh = h - wallH + eave * 0.6;
  if (roof === "hip") hipRoof(p, rx, ry, rw, rh, TILE_ROOF);
  else if (roof === "gable") gableRoof(p, rx, ry, rw, rh, TILE_ROOF, false);
  else gableRoof(p, rx, ry, rw, rh, THATCH, true);
}

// ------------------------------------------------------------ walls

/** Half the width of a wall's walkway, and its sloping faces (lit, shaded, and the tall south face). */
const TOP = 0.24;
const SIDE = 0.08;
const EAST = 0.16;
const FRONT = 0.2;

interface Piece {
  x: number;
  y: number;
  w: number;
  h: number;
}

/** The faces a rammed-earth block shows: lit north and west, shaded east, the tall south face. */
interface Faces {
  n: number;
  w: number;
  e: number;
  s: number;
}

interface Earth {
  top: RGB;
  lit: RGB;
  side: RGB;
  front: RGB;
}

function earthTones(look: Look): Earth {
  const e = look.earth;
  return {
    top: scaleRGB(mixRGB(e, [196, 176, 134], 0.2), 0.92),
    lit: scaleRGB(mixRGB(e, [220, 204, 160], 0.3), 1.04),
    side: scaleRGB(e, 0.5),
    front: scaleRGB(e, 0.68),
  };
}

function blockShadow(g: CanvasRenderingContext2D, q: Piece, f: Faces): void {
  g.fillRect(q.x - f.w + 0.08, q.y - f.n + 0.1, q.w + f.w + f.e + 0.08, q.h + f.n + f.s + 0.03);
  // A dark rim round the foot, so the block stands off the ground.
  g.fillRect(
    q.x - f.w - 0.018,
    q.y - f.n - 0.018,
    q.w + f.w + f.e + 0.036,
    q.h + f.n + f.s + 0.036,
  );
}

/**
 * Which way each free-standing wall faces out: away from the forts, houses and
 * camps it guards (a wall line across a pass). Per run of joined wall tiles,
 * from the built tiles near it; [0, 0] when there is no telling.
 */
const facing = new WeakMap<Plan, Map<number, readonly [number, number]>>();
function guardSide(plan: Plan): Map<number, readonly [number, number]> {
  const hit = facing.get(plan);
  if (hit) return hit;
  const { w, h } = plan;
  const out = new Map<number, readonly [number, number]>();
  const wallish = (i: number) => plan.cls[i] === WALL || plan.gateAt[i] !== 0;
  const built = (c: number) => c === FORT || c === VILLAGE || c === CAMP;
  const seen = new Uint8Array(w * h);
  for (let s0 = 0; s0 < w * h; s0++) {
    if (seen[s0] || !wallish(s0)) continue;
    const run: number[] = [];
    const stack = [s0];
    seen[s0] = 1;
    while (stack.length) {
      const c = stack.pop() as number;
      run.push(c);
      const x = c % w;
      const y = (c - x) / w;
      for (const [dx, dy] of [
        [1, 0],
        [-1, 0],
        [0, 1],
        [0, -1],
      ] as const) {
        const nx = x + dx;
        const ny = y + dy;
        if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
        const j = ny * w + nx;
        if (!seen[j] && wallish(j)) {
          seen[j] = 1;
          stack.push(j);
        }
      }
    }
    let wx = 0;
    let wy = 0;
    for (const c of run) {
      wx += c % w;
      wy += Math.floor(c / w);
    }
    wx /= run.length;
    wy /= run.length;
    const near = new Set<number>();
    for (const c of run) {
      const x = c % w;
      const y = (c - x) / w;
      for (let dy = -4; dy <= 4; dy++)
        for (let dx = -4; dx <= 4; dx++) {
          const nx = x + dx;
          const ny = y + dy;
          if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
          const j = ny * w + nx;
          if (!plan.gateAt[j] && built(plan.cls[j] as number) && !plan.enclosed[j]) near.add(j);
        }
    }
    let bx = 0;
    let by = 0;
    for (const j of near) {
      bx += (j % w) - wx;
      by += Math.floor(j / w) - wy;
    }
    const dir: readonly [number, number] = near.size ? [bx / near.size, by / near.size] : [0, 0];
    for (const c of run) out.set(c, dir);
  }
  facing.set(plan, out);
  return out;
}

function blockFaces(g: CanvasRenderingContext2D, q: Piece, f: Faces, t: Earth, line: number): void {
  if (f.n) rect(g, q.x - f.w, q.y - f.n, q.w + f.w + f.e, f.n + 0.001, css(t.lit));
  if (f.w)
    rect(g, q.x - f.w, q.y - f.n, f.w + 0.001, q.h + f.n + f.s, css(mixRGB(t.lit, t.front, 0.3)));
  if (f.e) {
    rect(g, q.x + q.w - 0.001, q.y - f.n, f.e, q.h + f.n + f.s, css(t.side));
    // Tamping courses along the shaded face.
    g.strokeStyle = "rgba(50,36,20,0.3)";
    g.lineWidth = line;
    g.beginPath();
    for (const k of [0.35, 0.7]) {
      g.moveTo(q.x + q.w + f.e * k, q.y - f.n);
      g.lineTo(q.x + q.w + f.e * k, q.y + q.h + f.s);
    }
    g.stroke();
  }
  if (f.s) {
    rect(g, q.x - f.w * 0.6, q.y + q.h - 0.001, q.w + f.w * 0.6 + f.e, f.s, css(t.front));
    g.strokeStyle = "rgba(50,36,20,0.3)";
    g.lineWidth = line;
    g.beginPath();
    for (let k = 1; k < 4; k++) {
      const yy = q.y + q.h + (f.s * k) / 4;
      g.moveTo(q.x - f.w * 0.6, yy);
      g.lineTo(q.x + q.w + f.e, yy);
    }
    g.stroke();
  }
}

/** A parapet along an edge of the walkway: a low wall with merlons and crenels. */
function parapet(
  g: CanvasRenderingContext2D,
  x: number,
  y: number,
  len: number,
  horiz: boolean,
  t: Earth,
): void {
  const d = 0.065;
  const step = 0.13;
  const m = 0.075;
  if (horiz) {
    rect(g, x, y, len, d, css(scaleRGB(t.top, 0.82)));
    for (let s = x + 0.025; s < x + len - m * 0.6; s += step) {
      rect(g, s + 0.016, y + 0.02, m, d, "rgba(28,20,10,0.45)");
      rect(g, s, y, m, d, css(scaleRGB(t.top, 1.14)));
      rect(g, s, y, m, 0.014, "rgba(255,248,226,0.3)");
    }
  } else {
    rect(g, x, y, d, len, css(scaleRGB(t.top, 0.82)));
    for (let s = y + 0.025; s < y + len - m * 0.6; s += step) {
      rect(g, x + 0.02, s + 0.016, d, m, "rgba(28,20,10,0.45)");
      rect(g, x, s, d, m, css(scaleRGB(t.top, 1.14)));
      rect(g, x, s, 0.014, m, "rgba(255,248,226,0.3)");
    }
  }
}

/**
 * Wall bodies: thick rammed earth with sloping faces (lit to the north and
 * west, in shade to the east and south), a walkway, crenellated parapets on
 * the outer side, and bastions (馬面) standing out along straight runs.
 */
export function drawWalls(p: Pen, plan: Plan, r: Rect): void {
  const { g, look, px } = p;
  const { w: mw, h: mh } = plan;
  const t = earthTones(look);
  const line = Math.max(1, px / 90) / px;
  const outside = (x: number, y: number) =>
    x < 0 || y < 0 || x >= mw || y >= mh || !plan.enclosed[y * mw + x];
  const joins = (i: number): number => {
    if (plan.cls[i] === WALL) return plan.wall[i] as number;
    const gi = plan.gateAt[i] as number;
    if (!gi) return 0;
    const gate = plan.gates[gi - 1];
    if (!gate || gate.kind === "open") return 0;
    return gate.run === "ew" ? E | W : N | S;
  };
  const FACES: Faces = { n: SIDE, w: SIDE, e: EAST, s: FRONT };
  const guard = guardSide(plan);
  /**
   * The outer sides of a wall tile: [west or north, east or south]. A castle
   * wall faces away from its grounds; a free-standing one away from what it
   * guards; with no telling, both sides get crenels.
   */
  const outer = (x: number, y: number, vertical: boolean): [boolean, boolean] => {
    const a = vertical ? outside(x - 1, y) : outside(x, y - 1);
    const b = vertical ? outside(x + 1, y) : outside(x, y + 1);
    if (a !== b) return [a, b];
    if (!a) return [true, true];
    const d = guard.get(y * mw + x) ?? [0, 0];
    const k = vertical ? d[0] : d[1];
    return k > 0.3 ? [true, false] : k < -0.3 ? [false, true] : [true, true];
  };
  const pieces: { q: Piece; axis: "ew" | "ns" | "c"; tile: number }[] = [];
  const tiles: { x: number; y: number; m: number; i: number }[] = [];
  for (let y = Math.max(0, r.y0 - 1); y < Math.min(mh, r.y1 + 1); y++)
    for (let x = Math.max(0, r.x0 - 1); x < Math.min(mw, r.x1 + 1); x++) {
      const i = y * mw + x;
      const m = joins(i);
      if (!m && plan.cls[i] !== WALL) continue;
      tiles.push({ x, y, m, i });
      const cx = x + 0.5;
      const cy = y + 0.5;
      pieces.push({ q: { x: cx - TOP, y: cy - TOP, w: TOP * 2, h: TOP * 2 }, axis: "c", tile: i });
      if (m & N)
        pieces.push({ q: { x: cx - TOP, y, w: TOP * 2, h: 0.5 - TOP }, axis: "ns", tile: i });
      if (m & S)
        pieces.push({
          q: { x: cx - TOP, y: cy + TOP, w: TOP * 2, h: 0.5 - TOP },
          axis: "ns",
          tile: i,
        });
      if (m & W)
        pieces.push({ q: { x, y: cy - TOP, w: 0.5 - TOP, h: TOP * 2 }, axis: "ew", tile: i });
      if (m & E)
        pieces.push({
          q: { x: cx + TOP, y: cy - TOP, w: 0.5 - TOP, h: TOP * 2 },
          axis: "ew",
          tile: i,
        });
    }
  // Bastions on straight runs, standing out on the outer side (west or north on a wall with none).
  const bastions: { q: Piece; f: Faces; side: number }[] = [];
  for (const { x, y, m, i } of tiles) {
    if (plan.cls[i] !== WALL || m & (TOWER | END)) continue;
    const straightNS = (m & (N | S)) === (N | S) && !(m & (E | W));
    const straightEW = (m & (E | W)) === (E | W) && !(m & (N | S));
    if (!straightNS && !straightEW) continue;
    if ((x * 7 + y * 13) % 3 !== 0) continue;
    const cx = x + 0.5;
    const cy = y + 0.5;
    const out = 0.17;
    const half = 0.2;
    if (straightNS) {
      const [wOut, eOut] = outer(x, y, true);
      if (wOut || !eOut)
        bastions.push({
          q: { x: cx - TOP - SIDE - out, y: cy - half, w: out + SIDE, h: half * 2 },
          f: { n: SIDE, w: SIDE, e: 0, s: FRONT * 0.75 },
          side: W,
        });
      else
        bastions.push({
          q: { x: cx + TOP, y: cy - half, w: EAST + out, h: half * 2 },
          f: { n: SIDE, w: 0, e: EAST, s: FRONT * 0.75 },
          side: E,
        });
    } else {
      const [nOut, sOut] = outer(x, y, false);
      if (nOut || !sOut)
        bastions.push({
          q: { x: cx - half, y: cy - TOP - SIDE - out, w: half * 2, h: out + SIDE },
          f: { n: SIDE, w: SIDE, e: EAST, s: 0 },
          side: N,
        });
      else
        bastions.push({
          q: { x: cx - half, y: cy + TOP, w: half * 2, h: FRONT + out * 0.6 },
          f: { n: 0, w: SIDE, e: EAST, s: FRONT },
          side: S,
        });
    }
  }
  // Shadows on the ground, the faces, then the walkways on top.
  g.fillStyle = "rgba(24,16,8,0.32)";
  for (const { q } of pieces) blockShadow(g, q, FACES);
  for (const b of bastions) blockShadow(g, b.q, b.f);
  for (const { q } of pieces) blockFaces(g, q, FACES, t, line);
  for (const { q } of pieces) rect(g, q.x, q.y, q.w, q.h, css(t.top));
  for (const b of bastions) {
    blockFaces(g, b.q, b.f, t, line);
    rect(g, b.q.x, b.q.y, b.q.w, b.q.h, css(t.top));
  }
  // Putlog holes in the south faces.
  g.fillStyle = "rgba(40,28,16,0.45)";
  for (const { q, tile, axis } of pieces)
    if (axis !== "ns")
      for (let k = 0; k < 2; k++) {
        const xx = q.x + ((k + 0.5) / 2) * q.w + (hash(tile, k, 221) - 0.5) * 0.04;
        g.fillRect(xx, q.y + q.h + FRONT * 0.45, 0.02, 0.018);
      }
  // A worn path down the middle of the walkway, unbroken from tile to tile.
  g.fillStyle = "rgba(255,244,214,0.12)";
  for (const { q, axis } of pieces) {
    if (axis === "ew") g.fillRect(q.x, q.y + q.h * 0.36, q.w, q.h * 0.28);
    else if (axis === "ns") g.fillRect(q.x + q.w * 0.36, q.y, q.w * 0.28, q.h);
    else g.fillRect(q.x + q.w * 0.36, q.y + q.h * 0.36, q.w * 0.28, q.h * 0.28);
  }
  // Parapets on the outer side (both sides of a wall with no inside or no outside).
  for (const { x, y, m, i } of tiles) {
    if (plan.gateAt[i]) continue;
    const cx = x + 0.5;
    const cy = y + 0.5;
    const horiz = !!(m & (E | W)) || !(m & (N | S));
    const vert = !!(m & (N | S));
    const x0 = m & W ? x : cx - TOP;
    const x1 = m & E ? x + 1 : cx + TOP;
    const y0 = m & N ? y : cy - TOP;
    const y1 = m & S ? y + 1 : cy + TOP;
    if (horiz) {
      const [n, s] = outer(x, y, false);
      if (n) parapet(g, x0, cy - TOP, x1 - x0, true, t);
      if (s) parapet(g, x0, cy + TOP - 0.065, x1 - x0, true, t);
    }
    if (vert) {
      const [w, e] = outer(x, y, true);
      if (w) parapet(g, cx - TOP, y0, y1 - y0, false, t);
      if (e) parapet(g, cx + TOP - 0.065, y0, y1 - y0, false, t);
    }
  }
  for (const b of bastions) {
    const q = b.q;
    if (b.side === W) parapet(g, q.x, q.y, q.h, false, t);
    else if (b.side === E) parapet(g, q.x + q.w - 0.065, q.y, q.h, false, t);
    else if (b.side === N) parapet(g, q.x, q.y, q.w, true, t);
    else parapet(g, q.x, q.y + q.h - 0.065, q.w, true, t);
  }

  // Corner towers (角樓): timber, on a broader base, grey-tiled roofs.
  for (let y = Math.max(0, r.y0 - 1); y < Math.min(mh, r.y1 + 1); y++)
    for (let x = Math.max(0, r.x0 - 1); x < Math.min(mw, r.x1 + 1); x++) {
      const i = y * mw + x;
      if (plan.cls[i] !== WALL) continue;
      const m = plan.wall[i] as number;
      if (m & TOWER) cornerTower(p, x + 0.5, y + 0.5, i);
      else if (m & END && !(m & (N | E | S | W))) cornerTower(p, x + 0.5, y + 0.5, i);
    }
  // Gate towers (門樓) over the gate passages.
  for (const gate of plan.gates) {
    if (gate.kind === "open") continue;
    const first = gate.tiles[0] as number;
    const last = gate.tiles[gate.tiles.length - 1] as number;
    const fx = first % mw;
    const fy = Math.floor(first / mw);
    const lx = last % mw;
    const ly = Math.floor(last / mw);
    if (lx < r.x0 - 1 || fx > r.x1 || ly < r.y0 - 1 || fy > r.y1) continue;
    gateTower(p, fx, fy, lx, ly, gate.run, gate.kind === "tower", first);
  }
}

function cornerTower(p: Pen, cx: number, cy: number, seed: number): void {
  const { g, look } = p;
  const b = 0.4;
  const earth = look.earth;
  shadow(p, cx - b, cy - b, b * 2, b * 2, 0.22, 0.28);
  rect(
    g,
    cx - b - 0.03,
    cy - b - 0.03,
    b * 2 + 0.06,
    b * 2 + 0.06 + FRONT,
    css(scaleRGB(earth, 0.7)),
  );
  rect(g, cx - b, cy - b, b * 2, b * 2, css(mixRGB(earth, [206, 186, 140], 0.35)));
  // The timber storey: a brown gallery under the eaves, then the roof.
  const s = 0.3 + hash(seed, 1, 223) * 0.04;
  rect(g, cx - s - 0.02, cy - s + 0.02, s * 2 + 0.04, s * 2 + 0.04, css(TIMBER));
  for (const [ox, oy] of [
    [-s, -s],
    [s - 0.035, -s],
    [-s, s - 0.01],
    [s - 0.035, s - 0.01],
  ] as const)
    rect(g, cx + ox, cy + oy + 0.03, 0.035, 0.035, css(LACQUER));
  hipRoof(p, cx - s - 0.05, cy - s - 0.06, s * 2 + 0.1, s * 2 + 0.06, TILE_ROOF);
  hipRoof(p, cx - s * 0.55, cy - s * 0.6 - 0.04, s * 1.1, s * 1.05, TILE_ROOF);
}

function gateTower(
  p: Pen,
  fx: number,
  fy: number,
  lx: number,
  ly: number,
  run: "ew" | "ns",
  big: boolean,
  seed: number,
): void {
  const { g } = p;
  const ew = run === "ew";
  const x0 = fx;
  const y0 = fy;
  const x1 = lx + 1;
  const y1 = ly + 1;
  const cx = (x0 + x1) / 2;
  const cy = (y0 + y1) / 2;
  // The passage through the wall, dark under the tower.
  if (ew) {
    rect(g, cx - 0.16, cy - TOP - SIDE, 0.32, SIDE, "rgba(26,18,12,0.85)");
    rect(g, cx - 0.16, cy + TOP, 0.32, FRONT + 0.01, "rgba(26,18,12,0.92)");
    rect(g, cx - 0.17, cy + TOP, 0.025, FRONT, css(TIMBER));
    rect(g, cx + 0.145, cy + TOP, 0.025, FRONT, css(TIMBER));
  } else {
    rect(g, cx - TOP - SIDE, cy - 0.16, SIDE, 0.32, "rgba(26,18,12,0.85)");
    rect(g, cx + TOP, cy - 0.16, EAST, 0.32, "rgba(26,18,12,0.85)");
  }
  const len = Math.max(0.9, (ew ? x1 - x0 : y1 - y0) - 0.08);
  const dep = big ? 0.64 : 0.54;
  const bw = ew ? len : dep;
  const bh = ew ? dep : len;
  const bx = cx - bw / 2;
  const by = cy - bh / 2 - 0.04;
  shadow(p, bx, by, bw, bh, big ? 0.3 : 0.2, 0.3);
  // Timber gallery round the storey, lacquered posts at the corners.
  rect(g, bx + 0.03, by + 0.06, bw - 0.06, bh - 0.02, css(TIMBER));
  for (let k = 0; k <= 4; k++) {
    const t = k / 4;
    if (ew) rect(g, bx + 0.04 + t * (bw - 0.11), by + bh - 0.03, 0.03, 0.04, css(LACQUER));
    else rect(g, bx + bw - 0.06, by + 0.06 + t * (bh - 0.12), 0.04, 0.03, css(LACQUER));
  }
  hipRoof(p, bx - 0.03, by - 0.03, bw + 0.06, bh - 0.04, TILE_ROOF);
  if (big) {
    // A second storey: a smaller roof over the first.
    const s = 0.62;
    rect(g, cx - (bw * s) / 2 + 0.02, by + bh * 0.18 + 0.02, bw * s - 0.04, bh * s, css(TIMBER));
    hipRoof(p, cx - (bw * s) / 2, by + bh * 0.14 - 0.04, bw * s, bh * s, TILE_ROOF);
  }
  void seed;
}

// ------------------------------------------------------------ bridges

/** Timber bridges: plank decks on beams, standing on rows of posts in the water. */
export function drawBridges(p: Pen, plan: Plan, r: Rect): void {
  const { g, px } = p;
  const line = Math.max(1, px / 90) / px;
  const wood: RGB = [146, 112, 76];
  for (const [i, b] of plan.bridges) {
    const x = i % plan.w;
    const y = Math.floor(i / plan.w);
    if (x < r.x0 - 1 || x > r.x1 || y < r.y0 - 1 || y > r.y1) continue;
    const ew = b.axis === "ew";
    const half = 0.3;
    // Along the deck: from the bank (a little onto it) to the next tile.
    const a0 = b.before ? 0 : -0.16;
    const a1 = b.after ? 1 : 1.16;
    const dx0 = ew ? x + a0 : x + 0.5 - half;
    const dy0 = ew ? y + 0.5 - half : y + a0;
    const dw = ew ? a1 - a0 : half * 2;
    const dh = ew ? half * 2 : a1 - a0;
    if (b.wet) {
      g.fillStyle = "rgba(10,16,18,0.32)";
      g.fillRect(dx0 + 0.07, dy0 + 0.11, dw, dh);
    }
    // Posts: a bent every third of a tile, standing out past the deck.
    for (let k = 0; k < 3; k++) {
      const t = (k + 0.5) / 3;
      for (const s of [-1, 1]) {
        const px0 = ew ? x + t - 0.03 : x + 0.5 + s * (half + 0.02) - 0.03;
        const py0 = ew ? y + 0.5 + s * (half + 0.02) - 0.03 : y + t - 0.03;
        rect(g, px0 + 0.03, py0 + 0.04, 0.06, 0.06, "rgba(10,14,16,0.35)");
        rect(g, px0, py0, 0.06, 0.06, css([84, 60, 40]));
        rect(g, px0, py0, 0.06, 0.02, css([120, 92, 62]));
      }
    }
    // Planks across the deck.
    const n = 16;
    for (let k = 0; k < n; k++) {
      const t0 = k / n;
      const tone = 0.88 + hash(i, k, 227) * 0.2;
      if (ew)
        rect(
          g,
          x + a0 + t0 * (a1 - a0),
          dy0 + 0.04,
          (a1 - a0) / n,
          dh - 0.08,
          css(scaleRGB(wood, tone)),
        );
      else
        rect(
          g,
          dx0 + 0.04,
          y + a0 + t0 * (a1 - a0),
          dw - 0.08,
          (a1 - a0) / n,
          css(scaleRGB(wood, tone)),
        );
    }
    g.strokeStyle = "rgba(50,34,20,0.5)";
    g.lineWidth = line;
    g.beginPath();
    for (let k = 1; k < n; k++) {
      const t0 = k / n;
      if (ew) {
        g.moveTo(x + a0 + t0 * (a1 - a0), dy0 + 0.04);
        g.lineTo(x + a0 + t0 * (a1 - a0), dy0 + dh - 0.04);
      } else {
        g.moveTo(dx0 + 0.04, y + a0 + t0 * (a1 - a0));
        g.lineTo(dx0 + dw - 0.04, y + a0 + t0 * (a1 - a0));
      }
    }
    g.stroke();
    // Beams along each side, with a rail.
    const beam = css([96, 68, 44]);
    if (ew) {
      rect(g, dx0, dy0, dw, 0.05, beam);
      rect(g, dx0, dy0 + dh - 0.05, dw, 0.05, beam);
      rect(g, dx0, dy0 + dh, dw, 0.03, "rgba(20,14,8,0.35)");
    } else {
      rect(g, dx0, dy0, 0.05, dh, beam);
      rect(g, dx0 + dw - 0.05, dy0, 0.05, dh, beam);
      rect(g, dx0 + dw, dy0, 0.03, dh, "rgba(20,14,8,0.35)");
    }
  }
}

// ------------------------------------------------------------ camps

/** Earth ramparts (壘) with timber palisades (柵) round the camp's outer sides. */
export function drawFences(p: Pen, plan: Plan, r: Rect): void {
  const { g, look } = p;
  const earth = mixRGB(look.earth, look.camp, 0.3);
  const segs: { ax: number; ay: number; bx: number; by: number; f: Fence }[] = [];
  for (const f of plan.fences) {
    if (f.x < r.x0 - 1 || f.x > r.x1 || f.y < r.y0 - 1 || f.y > r.y1) continue;
    const ins = 0.09;
    const { x, y } = f;
    if (f.side === N) segs.push({ ax: x, ay: y + ins, bx: x + 1, by: y + ins, f });
    else if (f.side === S) segs.push({ ax: x, ay: y + 1 - ins, bx: x + 1, by: y + 1 - ins, f });
    else if (f.side === W) segs.push({ ax: x + ins, ay: y, bx: x + ins, by: y + 1, f });
    else segs.push({ ax: x + 1 - ins, ay: y, bx: x + 1 - ins, by: y + 1, f });
  }
  const parts = (s: (typeof segs)[number]): [number, number][] =>
    s.f.gap
      ? [
          [0, 0.3],
          [0.7, 1],
        ]
      : [[0, 1]];
  g.lineCap = "round";
  // The rampart: a low mound, lit on its upper-left flank.
  for (const pass of [0, 1, 2]) {
    g.strokeStyle =
      pass === 0
        ? "rgba(24,18,10,0.22)"
        : pass === 1
          ? css(scaleRGB(earth, 0.82))
          : css(scaleRGB(earth, 1.06));
    g.lineWidth = pass === 0 ? 0.16 : pass === 1 ? 0.13 : 0.06;
    const ox = pass === 0 ? 0.04 : pass === 2 ? -0.02 : 0;
    const oy = pass === 0 ? 0.06 : pass === 2 ? -0.025 : 0;
    g.beginPath();
    for (const s of segs)
      for (const [t0, t1] of parts(s)) {
        g.moveTo(s.ax + (s.bx - s.ax) * t0 + ox, s.ay + (s.by - s.ay) * t0 + oy);
        g.lineTo(s.ax + (s.bx - s.ax) * t1 + ox, s.ay + (s.by - s.ay) * t1 + oy);
      }
    g.stroke();
  }
  // The palisade: sharpened posts in a row.
  for (const s of segs)
    for (const [t0, t1] of parts(s)) {
      const len = Math.hypot(s.bx - s.ax, s.by - s.ay) * (t1 - t0);
      const n = Math.max(2, Math.round(len / 0.06));
      for (let k = 0; k <= n; k++) {
        const t = t0 + ((t1 - t0) * k) / n;
        const x = s.ax + (s.bx - s.ax) * t;
        const y = s.ay + (s.by - s.ay) * t;
        g.fillStyle = "rgba(20,14,8,0.35)";
        g.beginPath();
        g.arc(x + 0.02, y + 0.03, 0.024, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = css(scaleRGB(TIMBER, 0.85 + hash(s.f.x * 7 + k, s.f.y, 229) * 0.3));
        g.beginPath();
        g.arc(x, y, 0.022, 0, Math.PI * 2);
        g.fill();
        g.fillStyle = "rgba(230,200,150,0.5)";
        g.beginPath();
        g.arc(x - 0.006, y - 0.007, 0.009, 0, Math.PI * 2);
        g.fill();
      }
    }
}

// ------------------------------------------------------------ features

export function featureBounds(f: Feature): Rect {
  switch (f.kind) {
    case "lane": {
      let x0 = 1e9;
      let y0 = 1e9;
      let x1 = -1e9;
      let y1 = -1e9;
      for (const [x, y] of f.pts) {
        x0 = Math.min(x0, x);
        y0 = Math.min(y0, y);
        x1 = Math.max(x1, x);
        y1 = Math.max(y1, y);
      }
      return { x0: x0 - 0.3, y0: y0 - 0.3, x1: x1 + 0.3, y1: y1 + 0.3 };
    }
    case "street":
      return {
        x0: Math.min(f.x0, f.x1) - 0.5,
        y0: Math.min(f.y0, f.y1) - 0.5,
        x1: Math.max(f.x0, f.x1) + 0.5,
        y1: Math.max(f.y0, f.y1) + 0.5,
      };
    case "compound":
    case "keep":
    case "fields":
    case "stockade":
      return { x0: f.x - 0.2, y0: f.y - 0.3, x1: f.x + f.w + 0.4, y1: f.y + f.h + 0.4 };
    case "tent":
      return { x0: f.x - f.w, y0: f.y - f.h, x1: f.x + f.w + 0.2, y1: f.y + f.h + 0.2 };
    case "watchtower":
      return { x0: f.x - 0.6, y0: f.y - 0.7, x1: f.x + 0.9, y1: f.y + 0.9 };
    default:
      return { x0: f.x - 0.5, y0: f.y - 0.5, x1: f.x + 0.6, y1: f.y + 0.6 };
  }
}

export function drawFeature(p: Pen, f: Feature): void {
  switch (f.kind) {
    case "lane":
      lane(p, f.pts, f.seed);
      return;
    case "street":
      street(p, f.x0, f.y0, f.x1, f.y1, f.width);
      return;
    case "fields":
      fields(p, f.x, f.y, f.w, f.h, f.ew, f.seed);
      return;
    case "threshing":
      threshing(p, f.x, f.y);
      return;
    case "compound":
      compound(p, f.x, f.y, f.w, f.h, f.tiled, f.rooms, f.seed);
      return;
    case "keep":
      keep(p, f.x, f.y, f.w, f.h, f.seed);
      return;
    case "watchtower":
      watchtower(p, f.x, f.y, f.size, f.storeys, f.walled, f.seed);
      return;
    case "granary":
      granary(p, f.x, f.y, f.size, f.round, f.seed);
      return;
    case "well":
      well(p, f.x, f.y);
      return;
    case "tree":
      tree(p, f.x, f.y, f.tree, f.r, f.seed);
      return;
    case "tent":
      tent(p, f.x, f.y, f.w, f.h, f.style, f.seed);
      return;
    case "stockade":
      stockade(p, f.x, f.y, f.w, f.h, f.seed);
      return;
    case "prop":
      prop(p, f.x, f.y, f.prop, f.seed);
      return;
  }
}

function lane(p: Pen, pts: readonly Pt[], seed: number): void {
  const { g, look } = p;
  const [a, m, b] = pts as [Pt, Pt, Pt];
  const path = () => {
    g.beginPath();
    g.moveTo(a[0], a[1]);
    g.quadraticCurveTo(m[0], m[1], b[0], b[1]);
  };
  g.lineCap = "round";
  path();
  g.strokeStyle = css(mixRGB(look.yard, look.loess[2], 0.5), 0.35);
  g.lineWidth = 0.2;
  g.stroke();
  path();
  g.strokeStyle = css(scaleRGB(look.loess[2], 1.03), 0.55);
  g.lineWidth = 0.11;
  g.stroke();
  // Two cart ruts.
  path();
  g.strokeStyle = "rgba(90,70,44,0.18)";
  g.lineWidth = 0.012;
  g.setLineDash([0.06, 0.03 + hash(seed, 1, 231) * 0.02]);
  g.stroke();
  g.setLineDash([]);
}

function street(p: Pen, x0: number, y0: number, x1: number, y1: number, width: number): void {
  const { g, look, px } = p;
  const ew = Math.abs(x1 - x0) > Math.abs(y1 - y0);
  const ax = Math.min(x0, x1);
  const ay = Math.min(y0, y1);
  const w = ew ? Math.abs(x1 - x0) : width;
  const h = ew ? width : Math.abs(y1 - y0);
  const rx = ew ? ax : x0 - width / 2;
  const ry = ew ? y0 - width / 2 : ay;
  const stone = mixRGB(look.pave, [196, 188, 170], 0.3);
  rect(g, rx, ry, w, h, css(stone, 0.85));
  // Long slabs laid across the way, with kerbs.
  const line = Math.max(1, px / 90) / px;
  g.strokeStyle = "rgba(60,52,40,0.35)";
  g.lineWidth = line;
  g.beginPath();
  const step = 0.17;
  if (ew)
    for (let x = rx + step; x < rx + w; x += step) {
      g.moveTo(x, ry);
      g.lineTo(x, ry + h);
    }
  else
    for (let y = ry + step; y < ry + h; y += step) {
      g.moveTo(rx, y);
      g.lineTo(rx + w, y);
    }
  g.stroke();
  const kerb = css(scaleRGB(stone, 0.78));
  if (ew) {
    rect(g, rx, ry, w, 0.025, kerb);
    rect(g, rx, ry + h - 0.025, w, 0.025, kerb);
  } else {
    rect(g, rx, ry, 0.025, h, kerb);
    rect(g, rx + w - 0.025, ry, 0.025, h, kerb);
  }
}

/** Dry-land strips of millet and wheat, with earth balks between. */
function fields(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  ew: boolean,
  seed: number,
): void {
  const { g, look, px } = p;
  const crops = look.crops;
  const n = 4 + Math.floor(hash(seed, 1, 233) * 3);
  const line = Math.max(1, px / 100) / px;
  const balk = css(mixRGB(look.yard, look.loess[2], 0.4), 0.9);
  g.save();
  g.beginPath();
  g.roundRect(x, y, w, h, 0.06);
  g.clip();
  rect(g, x, y, w, h, balk);
  for (let k = 0; k < n; k++) {
    const t0 = k / n;
    const t1 = (k + 1) / n;
    const pick = hash(seed, 10 + k, 233);
    const col = pick < 0.45 ? crops.a : pick < 0.85 ? crops.b : crops.furrow;
    const sx = ew ? x + 0.02 : x + t0 * w + 0.012;
    const sy = ew ? y + t0 * h + 0.012 : y + 0.02;
    const sw = ew ? w - 0.04 : (t1 - t0) * w - 0.024;
    const sh = ew ? (t1 - t0) * h - 0.024 : h - 0.04;
    rect(g, sx, sy, sw, sh, css(scaleRGB(col, 0.95 + hash(seed, 20 + k, 233) * 0.1), 0.92));
    // Rows of the crop.
    g.strokeStyle = css(scaleRGB(col, 0.72), 0.45);
    g.lineWidth = line;
    g.beginPath();
    const step = 0.03;
    if (ew)
      for (let yy = sy + step / 2; yy < sy + sh; yy += step) {
        g.moveTo(sx, yy);
        g.lineTo(sx + sw, yy);
      }
    else
      for (let xx = sx + step / 2; xx < sx + sw; xx += step) {
        g.moveTo(xx, sy);
        g.lineTo(xx, sy + sh);
      }
    g.stroke();
  }
  g.restore();
}

function threshing(p: Pen, x: number, y: number): void {
  const { g, look } = p;
  g.fillStyle = css(scaleRGB(look.yard, 1.06), 0.85);
  g.beginPath();
  g.ellipse(x, y, 0.3, 0.24, 0, 0, Math.PI * 2);
  g.fill();
  g.strokeStyle = css(scaleRGB(look.yard, 0.85), 0.6);
  g.lineWidth = 0.015;
  g.stroke();
}

/** A walled courtyard compound: an earth wall round a yard, a hall on the north side, wings. */
function compound(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  tiled: boolean,
  rooms: number,
  seed: number,
): void {
  const { g, look } = p;
  const t = 0.045;
  const earth = tiled ? mixRGB(look.earth, [210, 196, 160], 0.35) : scaleRGB(look.earth, 0.95);
  shadow(p, x, y, w, h, 0.02, 0.22);
  // The yard.
  rect(g, x, y, w, h, css(scaleRGB(look.yard, tiled ? 1.07 : 1.02), 0.9));
  const roof: "hip" | "gable" | "thatch" = tiled
    ? hash(seed, 1, 237) < 0.4
      ? "hip"
      : "gable"
    : "thatch";
  // Wings first (they sit further south), then the hall.
  const hd = Math.max(0.3, h * 0.6);
  const hx = x + 0.045;
  const hy = y + 0.03;
  const hw = w - 0.09;
  const wingW = Math.min(0.3, w * 0.32);
  const wy = hy + hd + 0.03;
  const wd = y + h - 0.07 - wy;
  const west = hash(seed, 2, 237) < 0.5;
  // The compound wall, with a gate in the south side.
  const gate = x + w * (0.4 + hash(seed, 3, 237) * 0.2);
  const face = css(scaleRGB(earth, 0.72));
  const top = css(earth);
  rect(g, x, y, w, t, top);
  rect(g, x, y, t, h, top);
  rect(g, x + w - t, y, t, h, top);
  rect(g, x, y + h - t, gate - 0.07 - x, t, top);
  rect(g, gate + 0.07, y + h - t, x + w - gate - 0.07, t, top);
  rect(g, x, y + h, gate - 0.07 - x, 0.03, face);
  rect(g, gate + 0.07, y + h, x + w - gate - 0.07, 0.03, face);
  if (tiled) {
    // Grey tile coping on the wall.
    g.fillStyle = css(TILE_ROOF.mid);
    g.fillRect(x, y, w, t * 0.45);
    g.fillRect(x, y, t * 0.45, h);
    g.fillRect(x + w - t * 0.45, y, t * 0.45, h);
    // A small gate roof.
    hipRoof(p, gate - 0.09, y + h - 0.07, 0.18, 0.09, TILE_ROOF);
  }
  if (rooms >= 2 && wd > 0.14) {
    const wx = west ? x + 0.05 : x + w - 0.05 - wingW;
    building(p, wx, wy, wingW, wd, roof === "hip" ? "gable" : roof, seed + 5, !tiled);
  }
  if (rooms >= 3 && wd > 0.14) {
    const wx = west ? x + w - 0.05 - wingW : x + 0.05;
    building(p, wx, wy, wingW, wd, roof === "hip" ? "gable" : roof, seed + 9, !tiled);
  }
  building(p, hx, hy, hw, hd, roof, seed, !tiled);
  // Something in the yard: a jar, a woodpile or a little tree.
  const pick = hash(seed, 4, 237);
  const yx = x + w * (0.35 + hash(seed, 5, 237) * 0.3);
  const yy = Math.min(y + h - 0.12, wy + 0.1);
  if (pick < 0.35) {
    g.fillStyle = css([92, 66, 46]);
    g.beginPath();
    g.arc(yx, yy, 0.028, 0, Math.PI * 2);
    g.fill();
  } else if (pick < 0.6) {
    rect(g, yx - 0.06, yy - 0.02, 0.12, 0.04, css([128, 98, 66]));
  } else if (pick < 0.8) tree(p, yx, yy, "jujube", 0.11, seed);
}

/** A keep: a rammed-earth platform (台) with a great hall on it under a two-tier roof. */
function keep(p: Pen, x: number, y: number, w: number, h: number, seed: number): void {
  const { g, look } = p;
  const big = w * h >= 4;
  const ins = 0.07;
  const px0 = x + ins;
  const py0 = y + ins;
  const pw = w - ins * 2;
  const ph = h - ins * 2 - 0.08;
  const earth = look.earth;
  shadow(p, px0, py0, pw, ph + 0.1, 0.12, 0.28);
  rect(g, px0, py0, pw, ph + 0.12, css(scaleRGB(earth, 0.68)));
  rect(g, px0, py0, pw, ph, css(mixRGB(earth, [206, 186, 140], 0.3)));
  rect(g, px0, py0, pw, 0.03, css(scaleRGB(earth, 1.12)));
  rect(g, px0, py0, 0.03, ph, css(scaleRGB(earth, 1.06)));
  // Steps up the south face.
  const sx = x + w / 2 - 0.12;
  for (let k = 0; k < 4; k++)
    rect(g, sx, py0 + ph + k * 0.03, 0.24, 0.026, css(scaleRGB(earth, 0.95 - k * 0.05)));
  // Paving on the terrace.
  rect(g, px0 + 0.08, py0 + 0.08, pw - 0.16, ph - 0.12, css(look.pave, 0.5));
  const hw = pw * (big ? 0.66 : 0.72);
  const hh = ph * (big ? 0.5 : 0.56);
  const hx = x + w / 2 - hw / 2;
  const hy = py0 + ph * 0.16;
  // Lacquered columns under the eave, then the roofs.
  rect(g, hx + 0.03, hy + 0.05, hw - 0.06, hh - 0.02, css(TIMBER));
  for (let k = 0; k <= 5; k++)
    rect(g, hx + 0.04 + (k / 5) * (hw - 0.11), hy + hh - 0.05, 0.03, 0.05, css(LACQUER));
  hipRoof(p, hx - 0.05, hy - 0.04, hw + 0.1, hh, TILE_ROOF);
  hipRoof(p, hx + hw * 0.18, hy - 0.02 + hh * 0.08, hw * 0.64, hh * 0.62, TILE_ROOF);
  if (big) {
    // Side halls and a watchtower at the back corner.
    building(p, px0 + 0.1, py0 + ph * 0.66, pw * 0.22, ph * 0.26, "gable", seed + 3);
    building(
      p,
      px0 + pw - 0.1 - pw * 0.22,
      py0 + ph * 0.66,
      pw * 0.22,
      ph * 0.26,
      "gable",
      seed + 4,
    );
    watchtower(p, px0 + 0.22, py0 + 0.2, 0.3, 3, false, seed);
  }
}

/** A watchtower (望樓): storeys of timber galleries, each under its own tiled roof. */
function watchtower(
  p: Pen,
  x: number,
  y: number,
  size: number,
  storeys: number,
  walled: boolean,
  seed: number,
): void {
  const { g, look } = p;
  if (walled) {
    // A small rammed-earth enclosure round its foot.
    const b = 0.42;
    const t = 0.06;
    const earth = look.earth;
    shadow(p, x - b, y - b, b * 2, b * 2, 0.04, 0.2);
    rect(g, x - b, y - b, b * 2, b * 2, css(scaleRGB(look.yard, 1.02), 0.9));
    const top = css(mixRGB(earth, [206, 186, 140], 0.3));
    const face = css(scaleRGB(earth, 0.7));
    rect(g, x - b, y - b, b * 2, t, top);
    rect(g, x - b, y - b, t, b * 2, top);
    rect(g, x + b - t, y - b, t, b * 2, top);
    rect(g, x - b, y + b - t, b * 0.7, t, top);
    rect(g, x + b * 0.3, y + b - t, b * 0.7, t, top);
    rect(g, x - b, y + b, b * 0.7, 0.04, face);
    rect(g, x + b * 0.3, y + b, b * 0.7, 0.04, face);
  }
  const lift = 0.08 * storeys;
  // A long shadow: it is tall.
  g.fillStyle = "rgba(22,18,10,0.26)";
  g.beginPath();
  g.moveTo(x - size * 0.3, y + size * 0.35);
  g.lineTo(x + size * 0.35, y - size * 0.3);
  g.lineTo(x + size * 0.35 + lift * 2.2, y - size * 0.3 + lift * 2.6);
  g.lineTo(x - size * 0.3 + lift * 2.2, y + size * 0.35 + lift * 2.6);
  g.closePath();
  g.fill();
  rect(g, x - size * 0.36, y - size * 0.3, size * 0.72, size * 0.74, css(TIMBER));
  for (let k = 0; k < storeys; k++) {
    const s = size * (1 - k * 0.17);
    const oy = -k * 0.045;
    if (k > 0)
      rect(
        g,
        x - s * 0.42,
        y - s * 0.4 + oy + 0.03,
        s * 0.84,
        s * 0.84,
        css(scaleRGB(TIMBER, 1.1)),
      );
    hipRoof(p, x - s / 2, y - s / 2 + oy, s, s * 0.92, TILE_ROOF);
  }
  void seed;
}

/** A granary: a tall square store (倉) under a hip roof, or a round one (囷) with a conical roof. */
function granary(p: Pen, x: number, y: number, size: number, round: boolean, seed: number): void {
  const { g, look } = p;
  if (!round) {
    building(p, x - size * 0.55, y - size * 0.45, size * 1.1, size * 0.9, "hip", seed);
    // Vents along the ridge.
    rect(g, x - size * 0.2, y - size * 0.08, size * 0.4, 0.02, "rgba(30,26,22,0.55)");
    return;
  }
  const r = size * 0.5;
  g.fillStyle = "rgba(22,18,10,0.28)";
  g.beginPath();
  g.ellipse(x + 0.07, y + 0.09, r, r * 0.9, 0, 0, Math.PI * 2);
  g.fill();
  // The earth drum, then the roof.
  g.fillStyle = css(scaleRGB(look.earth, 0.8));
  g.beginPath();
  g.ellipse(x, y + 0.05, r * 0.88, r * 0.8, 0, 0, Math.PI * 2);
  g.fill();
  const thatch = hash(seed, 1, 239) < 0.6;
  const roof = thatch ? THATCH : TILE_ROOF;
  const gr = g.createRadialGradient(x - r * 0.35, y - r * 0.4, r * 0.05, x, y, r * 1.05);
  gr.addColorStop(0, css(roof.light));
  gr.addColorStop(0.6, css(roof.mid));
  gr.addColorStop(1, css(roof.dark));
  g.fillStyle = gr;
  g.beginPath();
  g.arc(x, y, r, 0, Math.PI * 2);
  g.fill();
  g.strokeStyle = thatch ? "rgba(80,58,30,0.3)" : "rgba(30,28,26,0.3)";
  g.lineWidth = 0.012;
  g.beginPath();
  for (let k = 0; k < 18; k++) {
    const a = (k / 18) * Math.PI * 2;
    g.moveTo(x + Math.cos(a) * r * 0.12, y + Math.sin(a) * r * 0.12);
    g.lineTo(x + Math.cos(a) * r, y + Math.sin(a) * r);
  }
  g.stroke();
  g.fillStyle = css(roof.ridge);
  g.beginPath();
  g.arc(x, y, r * 0.14, 0, Math.PI * 2);
  g.fill();
}

/** A well: a stone apron, the curb, a pulley frame under a small tiled roof. */
function well(p: Pen, x: number, y: number): void {
  const { g, look } = p;
  rect(g, x - 0.17, y - 0.15, 0.34, 0.32, css(mixRGB(look.pave, [190, 182, 166], 0.3), 0.9));
  rect(g, x - 0.1, y - 0.08, 0.2, 0.2, css([118, 112, 102]));
  rect(g, x - 0.1, y - 0.08, 0.2, 0.03, css([150, 144, 132]));
  rect(g, x - 0.06, y - 0.04, 0.12, 0.12, css([36, 44, 46]));
  rect(g, x - 0.035, y - 0.02, 0.05, 0.03, "rgba(170,190,190,0.35)");
  // Posts and the beam with its pulley, then the roof over them.
  rect(g, x - 0.14, y - 0.02, 0.03, 0.03, css(TIMBER));
  rect(g, x + 0.11, y - 0.02, 0.03, 0.03, css(TIMBER));
  shadow(p, x - 0.15, y - 0.12, 0.3, 0.12, 0.08, 0.22);
  gableRoof(p, x - 0.15, y - 0.13, 0.3, 0.12, TILE_ROOF, false);
}

function tree(p: Pen, x: number, y: number, kind: TreeKind, r: number, seed: number): void {
  const { lib, px, g } = p;
  const list = lib.crowns[kind];
  const s = list[seed % list.length];
  if (!s) return;
  const k = r / CROWN_R;
  g.globalAlpha = 0.55;
  stamp(g, lib.shadow, x + 0.08 * k, y + 0.1 * k, px, k * 0.55 * (px / 64) * 0.9);
  g.globalAlpha = 1;
  stamp(g, s, x, y, px, k);
}

/** Tents: ridge tents of hemp, round tents, and the command tent with a madder-red band. */
function tent(
  p: Pen,
  x: number,
  y: number,
  w: number,
  h: number,
  style: "ridge" | "round" | "command",
  seed: number,
): void {
  const { g, px } = p;
  const line = Math.max(1, px / 90) / px;
  const cloth: RGB = mixRGB([216, 204, 172], [196, 180, 146], hash(seed, 1, 241));
  const trims: RGB[] = [
    [150, 62, 48],
    [70, 76, 104],
    [156, 124, 64],
  ];
  const trim = trims[Math.floor(hash(seed, 2, 241) * trims.length)] as RGB;
  if (style === "round") {
    const r = w / 2;
    g.fillStyle = "rgba(22,18,10,0.28)";
    g.beginPath();
    g.ellipse(x + 0.05, y + 0.06, r, r * 0.92, 0, 0, Math.PI * 2);
    g.fill();
    const gr = g.createRadialGradient(x - r * 0.3, y - r * 0.35, r * 0.05, x, y, r);
    gr.addColorStop(0, css(scaleRGB(cloth, 1.08)));
    gr.addColorStop(1, css(scaleRGB(cloth, 0.72)));
    g.fillStyle = gr;
    g.beginPath();
    g.arc(x, y, r, 0, Math.PI * 2);
    g.fill();
    g.strokeStyle = "rgba(90,74,50,0.35)";
    g.lineWidth = line;
    g.beginPath();
    for (let k = 0; k < 8; k++) {
      const a = (k / 8) * Math.PI * 2;
      g.moveTo(x, y);
      g.lineTo(x + Math.cos(a) * r, y + Math.sin(a) * r);
    }
    g.stroke();
    g.fillStyle = css(scaleRGB(trim, 0.9));
    g.beginPath();
    g.arc(x, y, r * 0.16, 0, Math.PI * 2);
    g.fill();
    rect(g, x - r * 0.18, y + r * 0.72, r * 0.36, r * 0.28, "rgba(40,30,20,0.7)");
    return;
  }
  const wide = w >= h;
  const x0 = x - w / 2;
  const y0 = y - h / 2;
  // Guy ropes to pegs.
  g.strokeStyle = "rgba(70,56,36,0.45)";
  g.lineWidth = line;
  g.beginPath();
  for (const [cx, cy, dx, dy] of [
    [x0, y0, -1, -1],
    [x0 + w, y0, 1, -1],
    [x0, y0 + h, -1, 1],
    [x0 + w, y0 + h, 1, 1],
  ] as const) {
    g.moveTo(cx, cy);
    g.lineTo(cx + dx * 0.06, cy + dy * 0.06);
  }
  g.stroke();
  shadow(p, x0, y0, w, h, 0.08, 0.26);
  const roof: Roof = {
    light: scaleRGB(cloth, 1.08),
    mid: cloth,
    dark: scaleRGB(cloth, 0.66),
    ridge: style === "command" ? trim : scaleRGB(cloth, 0.55),
  };
  if (wide) {
    fillPoly(
      g,
      [
        [x0, y0],
        [x0 + w, y0],
        [x0 + w, y],
        [x0, y],
      ],
      css(roof.light),
    );
    fillPoly(
      g,
      [
        [x0, y],
        [x0 + w, y],
        [x0 + w, y0 + h],
        [x0, y0 + h],
      ],
      css(roof.dark),
    );
  } else {
    fillPoly(
      g,
      [
        [x0, y0],
        [x, y0],
        [x, y0 + h],
        [x0, y0 + h],
      ],
      css(roof.light),
    );
    fillPoly(
      g,
      [
        [x, y0],
        [x0 + w, y0],
        [x0 + w, y0 + h],
        [x, y0 + h],
      ],
      css(roof.dark),
    );
  }
  g.strokeStyle = css(roof.ridge);
  g.lineWidth = style === "command" ? 0.04 : Math.max(0.015, line * 1.5);
  g.beginPath();
  if (wide) {
    g.moveTo(x0, y);
    g.lineTo(x0 + w, y);
  } else {
    g.moveTo(x, y0);
    g.lineTo(x, y0 + h);
  }
  g.stroke();
  if (style === "command" || hash(seed, 3, 241) < 0.35) {
    // A dyed band round the hem.
    g.strokeStyle = css(trim, 0.85);
    g.lineWidth = 0.02;
    g.strokeRect(x0 + 0.01, y0 + 0.01, w - 0.02, h - 0.02);
  }
  // The door flap, on the south end or side.
  if (wide)
    fillPoly(
      g,
      [
        [x0 + w * 0.42, y0 + h],
        [x0 + w * 0.58, y0 + h],
        [x, y + h * 0.15],
      ],
      "rgba(48,36,24,0.75)",
    );
  else
    fillPoly(
      g,
      [
        [x - w * 0.18, y0 + h],
        [x + w * 0.18, y0 + h],
        [x, y0 + h * 0.78],
      ],
      "rgba(48,36,24,0.75)",
    );
  if (style === "command") {
    prop(p, x0 - 0.02, y0 + 0.02, "banner", seed);
    prop(p, x0 + w + 0.02, y0 + 0.02, "banner", seed + 1);
  }
}

/** A camp's stronghold: a timber palisade square round a command tent and a watchtower. */
function stockade(p: Pen, x: number, y: number, w: number, h: number, seed: number): void {
  const { g, look } = p;
  const ins = 0.08;
  const x0 = x + ins;
  const y0 = y + ins;
  const x1 = x + w - ins;
  const y1 = y + h - ins;
  rect(g, x0, y0, x1 - x0, y1 - y0, css(scaleRGB(look.camp, 1.04), 0.6));
  const post = (px_: number, py: number) => {
    g.fillStyle = "rgba(20,14,8,0.35)";
    g.beginPath();
    g.arc(px_ + 0.02, py + 0.03, 0.026, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = css(TIMBER);
    g.beginPath();
    g.arc(px_, py, 0.026, 0, Math.PI * 2);
    g.fill();
  };
  const gx = (x0 + x1) / 2;
  for (let t = 0; t <= 1.0001; t += 0.05) {
    post(x0 + (x1 - x0) * t, y0);
    post(x0, y0 + (y1 - y0) * t);
    post(x1, y0 + (y1 - y0) * t);
    const bx = x0 + (x1 - x0) * t;
    if (Math.abs(bx - gx) > 0.12) post(bx, y1);
  }
  watchtower(p, x0 + 0.2, y0 + 0.2, 0.32, 2, false, seed);
  tent(
    p,
    (x0 + x1) / 2 + 0.06,
    (y0 + y1) / 2 + 0.06,
    Math.min(0.56, (x1 - x0) * 0.6),
    0.36,
    "command",
    seed,
  );
}

function prop(
  p: Pen,
  x: number,
  y: number,
  kind: "fire" | "rack" | "banner" | "crates" | "cart" | "haystack",
  seed: number,
): void {
  const { g, px } = p;
  const line = Math.max(1, px / 90) / px;
  switch (kind) {
    case "fire": {
      const gr = g.createRadialGradient(x, y, 0, x, y, 0.16);
      gr.addColorStop(0, "rgba(255,150,60,0.35)");
      gr.addColorStop(1, "rgba(255,150,60,0)");
      g.fillStyle = gr;
      g.fillRect(x - 0.16, y - 0.16, 0.32, 0.32);
      g.fillStyle = "rgba(40,32,26,0.85)";
      g.beginPath();
      g.arc(x, y, 0.045, 0, Math.PI * 2);
      g.fill();
      for (let k = 0; k < 8; k++) {
        const a = (k / 8) * Math.PI * 2;
        g.fillStyle = css([120, 112, 100]);
        g.beginPath();
        g.arc(x + Math.cos(a) * 0.06, y + Math.sin(a) * 0.055, 0.016, 0, Math.PI * 2);
        g.fill();
      }
      g.fillStyle = "rgba(255,170,70,0.9)";
      g.beginPath();
      g.arc(x - 0.01, y, 0.016, 0, Math.PI * 2);
      g.fill();
      return;
    }
    case "rack": {
      rect(g, x - 0.12, y + 0.02, 0.24, 0.02, css(TIMBER));
      g.strokeStyle = css([96, 72, 48]);
      g.lineWidth = line * 1.2;
      g.beginPath();
      for (let k = 0; k < 5; k++) {
        const sx = x - 0.1 + k * 0.05;
        g.moveTo(sx, y + 0.05);
        g.lineTo(sx + 0.03, y - 0.1);
      }
      g.stroke();
      g.fillStyle = css([170, 170, 166]);
      for (let k = 0; k < 5; k++) g.fillRect(x - 0.075 + k * 0.05, y - 0.12, 0.012, 0.03);
      return;
    }
    case "banner": {
      const cols: RGB[] = [
        [158, 58, 44],
        [168, 132, 62],
        [64, 72, 108],
      ];
      const c = cols[Math.floor(hash(seed, 1, 251) * cols.length)] as RGB;
      g.fillStyle = "rgba(22,18,10,0.3)";
      g.fillRect(x + 0.04, y + 0.04, 0.13, 0.07);
      rect(g, x, y - 0.02, 0.02, 0.05, css(TIMBER));
      fillPoly(
        g,
        [
          [x + 0.02, y - 0.04],
          [x + 0.15, y - 0.03],
          [x + 0.14, y + 0.03],
          [x + 0.02, y + 0.02],
        ],
        css(c),
      );
      rect(g, x + 0.02, y - 0.04, 0.13, 0.012, "rgba(255,240,210,0.25)");
      return;
    }
    case "crates": {
      for (const [ox, oy] of [
        [0, 0],
        [0.09, 0.02],
        [0.04, -0.07],
      ] as const) {
        rect(g, x + ox + 0.02, y + oy + 0.025, 0.08, 0.07, "rgba(22,18,10,0.3)");
        rect(g, x + ox, y + oy, 0.08, 0.07, css([136, 104, 70]));
        rect(g, x + ox, y + oy, 0.08, 0.015, css([170, 136, 96]));
        rect(g, x + ox, y + oy, 0.015, 0.07, css([156, 122, 84]));
      }
      return;
    }
    case "cart": {
      const wood: RGB = [132, 98, 64];
      rect(g, x - 0.1, y - 0.05, 0.24, 0.12, "rgba(22,18,10,0.28)");
      rect(g, x - 0.13, y - 0.07, 0.22, 0.12, css(wood));
      g.strokeStyle = css(scaleRGB(wood, 0.7));
      g.lineWidth = line;
      g.strokeRect(x - 0.13, y - 0.07, 0.22, 0.12);
      g.beginPath();
      g.moveTo(x + 0.09, y - 0.05);
      g.lineTo(x + 0.22, y - 0.04);
      g.moveTo(x + 0.09, y + 0.03);
      g.lineTo(x + 0.22, y + 0.02);
      g.stroke();
      rect(g, x - 0.05, y - 0.1, 0.06, 0.03, css([60, 44, 30]));
      rect(g, x - 0.05, y + 0.05, 0.06, 0.03, css([60, 44, 30]));
      return;
    }
    case "haystack": {
      const straw: RGB = [196, 170, 106];
      g.fillStyle = "rgba(22,18,10,0.28)";
      g.beginPath();
      g.ellipse(x + 0.05, y + 0.06, 0.12, 0.1, 0, 0, Math.PI * 2);
      g.fill();
      const gr = g.createRadialGradient(x - 0.04, y - 0.05, 0.01, x, y, 0.12);
      gr.addColorStop(0, css(scaleRGB(straw, 1.1)));
      gr.addColorStop(1, css(scaleRGB(straw, 0.7)));
      g.fillStyle = gr;
      g.beginPath();
      g.ellipse(x, y, 0.11, 0.095, 0, 0, Math.PI * 2);
      g.fill();
      g.strokeStyle = "rgba(110,84,44,0.35)";
      g.lineWidth = line;
      g.beginPath();
      for (let k = 0; k < 6; k++) {
        const a = (k / 6) * Math.PI * 2 + 0.3;
        g.moveTo(x + Math.cos(a) * 0.03, y + Math.sin(a) * 0.03);
        g.lineTo(x + Math.cos(a) * 0.1, y + Math.sin(a) * 0.085);
      }
      g.stroke();
      return;
    }
  }
}
