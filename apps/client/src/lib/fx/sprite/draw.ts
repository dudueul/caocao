/**
 * Drawing helpers for the sprite painter, in the portraits' cel style (ADR
 * 0053, ADR 0073): a flat fill, one shadow band on the lower left and one
 * highlight on the upper right (light from the upper right), an ink outline.
 * Shapes are built in tiles on the sheet (x right, y down, the feet at the
 * origin) and carry their bounds, so each frame is trimmed to what it drew.
 */
import { hull, type Pt } from "./geom.ts";

export type G = CanvasRenderingContext2D;

export const INK = "#2a1a12";
/** Ink line width, in tiles (about 2 px at 96 px a tile). */
export const LW = 0.022;
/** Fine ink for details inside shapes. */
export const LW2 = 0.013;

/** A path and its bounds. */
export interface Shape {
  p: Path2D;
  b: [number, number, number, number];
}

/** Everything drawn into the current frame, for trimming. */
export const drawn = { x0: 0, y0: 0, x1: 0, y1: 0, any: false };

export function resetDrawn(): void {
  drawn.any = false;
  drawn.x0 = drawn.y0 = Number.POSITIVE_INFINITY;
  drawn.x1 = drawn.y1 = Number.NEGATIVE_INFINITY;
}

export function track(b: readonly number[], pad = 0): void {
  drawn.any = true;
  drawn.x0 = Math.min(drawn.x0, (b[0] ?? 0) - pad);
  drawn.y0 = Math.min(drawn.y0, (b[1] ?? 0) - pad);
  drawn.x1 = Math.max(drawn.x1, (b[2] ?? 0) + pad);
  drawn.y1 = Math.max(drawn.y1, (b[3] ?? 0) + pad);
}

function boundsOf(pts: readonly Pt[]): [number, number, number, number] {
  let x0 = Number.POSITIVE_INFINITY;
  let y0 = Number.POSITIVE_INFINITY;
  let x1 = Number.NEGATIVE_INFINITY;
  let y1 = Number.NEGATIVE_INFINITY;
  for (const [x, y] of pts) {
    if (x < x0) x0 = x;
    if (y < y0) y0 = y;
    if (x > x1) x1 = x;
    if (y > y1) y1 = y;
  }
  return [x0, y0, x1, y1];
}

/** Moves along points on a path or the context (no closing). */
export function trace(p: Path2D | CanvasRenderingContext2D, pts: readonly Pt[], dy = 0): void {
  for (let i = 0; i < pts.length; i++) {
    const [x, y] = pts[i] as Pt;
    if (i === 0) p.moveTo(x, y + dy);
    else p.lineTo(x, y + dy);
  }
}

/** A closed polygon. */
export function polygon(pts: readonly Pt[]): Shape {
  const p = new Path2D();
  const [x0, y0] = pts[0] ?? [0, 0];
  p.moveTo(x0, y0);
  for (let i = 1; i < pts.length; i++) {
    const q = pts[i] as Pt;
    p.lineTo(q[0], q[1]);
  }
  p.closePath();
  return { p, b: boundsOf(pts) };
}

/** A smooth closed curve near the points (quadratics through the midpoints). */
export function blob(pts: readonly Pt[]): Shape {
  const p = new Path2D();
  const n = pts.length;
  const at = (i: number) => pts[((i % n) + n) % n] as Pt;
  const mid = (i: number): Pt => {
    const a = at(i);
    const b = at(i + 1);
    return [(a[0] + b[0]) / 2, (a[1] + b[1]) / 2];
  };
  const m0 = mid(n - 1);
  p.moveTo(m0[0], m0[1]);
  for (let i = 0; i < n; i++) {
    const c = at(i);
    const m = mid(i);
    p.quadraticCurveTo(c[0], c[1], m[0], m[1]);
  }
  p.closePath();
  return { p, b: boundsOf(pts) };
}

/** The convex hull of points, smoothed a little. */
export function hullShape(pts: readonly Pt[], smooth = false): Shape {
  const h = hull(pts);
  return smooth ? blob(h) : polygon(h);
}

export function circle(x: number, y: number, r: number): Shape {
  const p = new Path2D();
  p.arc(x, y, Math.max(0.001, r), 0, Math.PI * 2);
  return { p, b: [x - r, y - r, x + r, y + r] };
}

export function ellipse(x: number, y: number, rx: number, ry: number, rot = 0): Shape {
  const p = new Path2D();
  p.ellipse(x, y, Math.max(0.001, rx), Math.max(0.001, ry), rot, 0, Math.PI * 2);
  const r = Math.max(rx, ry);
  return { p, b: [x - r, y - r, x + r, y + r] };
}

export function rect(x: number, y: number, w: number, h: number, r = 0): Shape {
  const p = new Path2D();
  p.roundRect(x, y, w, h, r);
  return { p, b: [x, y, x + w, y + h] };
}

/** A tapered capsule round two circles: limbs, hafts, sleeves. */
export function capsule(a: Pt, b: Pt, ra: number, rb: number): Shape {
  const dx = b[0] - a[0];
  const dy = b[1] - a[1];
  const d = Math.hypot(dx, dy);
  const p = new Path2D();
  const bb: [number, number, number, number] = [
    Math.min(a[0] - ra, b[0] - rb),
    Math.min(a[1] - ra, b[1] - rb),
    Math.max(a[0] + ra, b[0] + rb),
    Math.max(a[1] + ra, b[1] + rb),
  ];
  if (d <= Math.abs(ra - rb) + 1e-4) {
    const [c, r] = ra >= rb ? [a, ra] : [b, rb];
    p.arc(c[0], c[1], Math.max(0.001, r), 0, Math.PI * 2);
    return { p, b: bb };
  }
  const th = Math.atan2(dy, dx);
  const al = Math.acos(Math.max(-1, Math.min(1, (ra - rb) / d)));
  p.arc(a[0], a[1], Math.max(0.001, ra), th + al, th - al + Math.PI * 2);
  p.arc(b[0], b[1], Math.max(0.001, rb), th - al, th + al);
  p.closePath();
  return { p, b: bb };
}

/** A band along a quadratic curve, tapering from w0 to w1: ribbons, feathers, tails. */
export function ribbon(a: Pt, c: Pt, b: Pt, w0: number, w1: number, n = 10): Shape {
  const left: Pt[] = [];
  const right: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const u = i / n;
    const v = 1 - u;
    const x = v * v * a[0] + 2 * v * u * c[0] + u * u * b[0];
    const y = v * v * a[1] + 2 * v * u * c[1] + u * u * b[1];
    const tx = 2 * v * (c[0] - a[0]) + 2 * u * (b[0] - c[0]);
    const ty = 2 * v * (c[1] - a[1]) + 2 * u * (b[1] - c[1]);
    const l = Math.hypot(tx, ty) || 1;
    const w = (w0 + (w1 - w0) * u) / 2;
    left.push([x - (ty / l) * w, y + (tx / l) * w]);
    right.push([x + (ty / l) * w, y - (tx / l) * w]);
  }
  return polygon([...left, ...right.reverse()]);
}

/** Shapes smaller than this (in tiles, both ways) are painted flat. */
const SMALL = 0.075;

/** Shadow and highlight offsets, in tiles. */
const HI_DX = -0.014;
const HI_DY = 0.011;
const HI_W = 0.026;

/**
 * A cel-shaded shape: flat fill, a shadow band on the lower left (`d` deep),
 * a highlight along the upper right, an ink outline.
 */
export function cel(
  g: G,
  s: Shape,
  fill: string,
  shade?: string,
  d = 0.03,
  hi?: string,
  lw = LW,
): void {
  track(s.b, lw);
  // Small shapes read as flat colour: skip the clip (it is the costly part).
  if (s.b[2] - s.b[0] < SMALL && s.b[3] - s.b[1] < SMALL) {
    g.fillStyle = fill;
    g.fill(s.p);
    if (lw > 0) {
      g.lineWidth = lw;
      g.strokeStyle = INK;
      g.stroke(s.p);
    }
    return;
  }
  g.save();
  g.clip(s.p);
  if (shade) {
    g.fillStyle = shade;
    g.fill(s.p);
    g.translate(d, -d * 0.75);
    g.fillStyle = fill;
    g.fill(s.p);
    g.translate(-d, d * 0.75);
  } else {
    g.fillStyle = fill;
    g.fill(s.p);
  }
  if (hi) {
    g.translate(HI_DX, HI_DY);
    g.strokeStyle = hi;
    g.lineWidth = HI_W;
    g.stroke(s.p);
  }
  g.restore();
  if (lw > 0) {
    g.lineWidth = lw;
    g.strokeStyle = INK;
    g.stroke(s.p);
  }
}

/** Fill without shading or ink (a mark painted on a surface). */
export function fill(g: G, s: Shape, color: string): void {
  track(s.b);
  g.fillStyle = color;
  g.fill(s.p);
}

/** Draw inside a shape only. */
export function inside(g: G, s: Shape, draw: () => void): void {
  g.save();
  g.clip(s.p);
  draw();
  g.restore();
}

export function outline(g: G, s: Shape, color = INK, lw = LW): void {
  track(s.b, lw);
  g.lineWidth = lw;
  g.strokeStyle = color;
  g.stroke(s.p);
}

export function line(g: G, color: string, width: number, ...pts: number[]): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i < pts.length; i += 2) g.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
  g.stroke();
  for (let i = 0; i < pts.length; i += 2) {
    const x = pts[i] ?? 0;
    const y = pts[i + 1] ?? 0;
    track([x, y, x, y], width);
  }
}

/** A quadratic stroke: x0 y0 cx cy x1 y1. */
export function curve(
  g: G,
  color: string,
  width: number,
  x0: number,
  y0: number,
  cx: number,
  cy: number,
  x1: number,
  y1: number,
): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(x0, y0);
  g.quadraticCurveTo(cx, cy, x1, y1);
  g.stroke();
  track([Math.min(x0, x1, cx), Math.min(y0, y1, cy), Math.max(x0, x1, cx), Math.max(y0, y1, cy)]);
}

export function dot(g: G, color: string, x: number, y: number, r: number): void {
  g.fillStyle = color;
  g.beginPath();
  g.arc(x, y, Math.max(0.001, r), 0, Math.PI * 2);
  g.fill();
  track([x - r, y - r, x + r, y + r]);
}

/** An inked stroke: a dark line under a coloured one (hafts, strings, cords). */
export function inkLine(g: G, color: string, width: number, ...pts: number[]): void {
  line(g, INK, width + LW * 1.4, ...pts);
  line(g, color, width, ...pts);
}

function rgb(hex: string): [number, number, number] {
  const n = Number.parseInt(hex.slice(1), 16);
  return [(n >> 16) & 255, (n >> 8) & 255, n & 255];
}

/** Blend two #rrggbb colours. */
export function mix(a: string, b: string, t: number): string {
  const x = rgb(a);
  const y = rgb(b);
  const c = (i: 0 | 1 | 2) =>
    Math.round(x[i] + (y[i] - x[i]) * t)
      .toString(16)
      .padStart(2, "0");
  return `#${c(0)}${c(1)}${c(2)}`;
}

export function alpha(hex: string, a: number): string {
  const [r, g, b] = rgb(hex);
  return `rgba(${r},${g},${b},${a})`;
}

/** Base, shadow, light. */
export type Tri = readonly [string, string, string];

/** A colour's shadow and light. */
export function tri(base: string, dark?: string, light?: string): Tri {
  return [base, dark ?? mix(base, "#1a1010", 0.38), light ?? mix(base, "#ffffff", 0.32)];
}
