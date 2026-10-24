/**
 * Geometry for the sprite painter (ADR 0073): a small figure in body space,
 * turned to one of three facings and projected onto the sheet, so one pose
 * table draws the front, back and side frames alike.
 *
 * Body space, in tiles: the origin between the feet on the ground; x toward
 * the figure's left hand (the viewer's right when he faces us), y down, z
 * forward (the way he faces). The view turns body space by a yaw and shifts
 * points by their depth: nearer points sit lower, as seen from the map's
 * bird's-eye three-quarter view. Forward and backward shift more than sideways,
 * so a lunge reads in the front and back frames and the shoulders stay level
 * in the side frames.
 */

export type V3 = readonly [number, number, number];
export type Pt = readonly [number, number];

export const v3 = (x: number, y: number, z: number): V3 => [x, y, z];
export const add = (a: V3, b: V3): V3 => [a[0] + b[0], a[1] + b[1], a[2] + b[2]];
export const sub = (a: V3, b: V3): V3 => [a[0] - b[0], a[1] - b[1], a[2] - b[2]];
export const mul = (a: V3, k: number): V3 => [a[0] * k, a[1] * k, a[2] * k];
export const dot = (a: V3, b: V3): number => a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
export const len = (a: V3): number => Math.hypot(a[0], a[1], a[2]);
export const norm = (a: V3): V3 => {
  const l = len(a) || 1;
  return [a[0] / l, a[1] / l, a[2] / l];
};
export const cross = (a: V3, b: V3): V3 => [
  a[1] * b[2] - a[2] * b[1],
  a[2] * b[0] - a[0] * b[2],
  a[0] * b[1] - a[1] * b[0],
];
export const mix3 = (a: V3, b: V3, t: number): V3 => [
  a[0] + (b[0] - a[0]) * t,
  a[1] + (b[1] - a[1]) * t,
  a[2] + (b[2] - a[2]) * t,
];

/** Pitch about the x axis: a positive angle tips what is above the origin forward (+z). */
export function rotX(p: V3, a: number): V3 {
  const c = Math.cos(a);
  const s = Math.sin(a);
  return [p[0], p[1] * c + p[2] * s, -p[1] * s + p[2] * c];
}

/** Yaw about the vertical: a positive angle turns +z toward +x. */
export function rotY(p: V3, a: number): V3 {
  const c = Math.cos(a);
  const s = Math.sin(a);
  return [p[0] * c + p[2] * s, p[1], p[2] * c - p[0] * s];
}

/** Roll about the z axis: a positive angle tips what is above the origin toward +x. */
export function rotZ(p: V3, a: number): V3 {
  const c = Math.cos(a);
  const s = Math.sin(a);
  return [p[0] * c - p[1] * s, p[0] * s + p[1] * c, p[2]];
}

/** A turned, projected view of body space. */
export class View {
  readonly c: number;
  readonly s: number;
  /**
   * @param yaw 0 faces the viewer, π faces away, π/2 faces the viewer's right.
   * @param kf how far a point forward of the figure (toward the viewer) sits lower.
   * @param kl the same for a point to the figure's side.
   */
  constructor(
    readonly yaw: number,
    readonly kf: number,
    readonly kl: number,
  ) {
    this.c = Math.cos(yaw);
    this.s = Math.sin(yaw);
  }

  x(p: V3): number {
    return p[0] * this.c + p[2] * this.s;
  }

  /** Depth toward the viewer. */
  d(p: V3): number {
    return p[2] * this.c - p[0] * this.s;
  }

  y(p: V3): number {
    return p[1] + this.kf * p[2] * this.c - this.kl * p[0] * this.s;
  }

  p(p: V3): Pt {
    return [this.x(p), this.y(p)];
  }

  /** How squarely a surface whose outward normal points along `n` faces the viewer (−1…1). */
  facing(n: V3): number {
    return this.d(n);
  }
}

/** Two-bone reach: where the middle joint sits for a limb from `a` to `b`, bending toward `pole`. */
export function bend(a: V3, b: V3, l1: number, l2: number, pole: V3): V3 {
  const ab = sub(b, a);
  const d = Math.min(len(ab), (l1 + l2) * 0.999);
  const dir = norm(ab);
  // Along the limb by the law of cosines, then out toward the pole.
  const x = (l1 * l1 - l2 * l2 + d * d) / (2 * d || 1);
  const h = Math.sqrt(Math.max(0, l1 * l1 - x * x));
  const pp = sub(pole, mul(dir, dot(pole, dir)));
  const up = len(pp) < 1e-6 ? norm(cross(dir, [1, 0, 0])) : norm(pp);
  return add(add(a, mul(dir, x)), mul(up, h));
}

/** The end of a limb from `a` toward `b` that cannot reach past `l1 + l2`. */
export function reach(a: V3, b: V3, max: number): V3 {
  const ab = sub(b, a);
  const l = len(ab);
  return l <= max ? b : add(a, mul(ab, max / l));
}

/** Convex hull (monotone chain), counter-clockwise. */
export function hull(points: readonly Pt[]): Pt[] {
  const pts = [...points].sort((a, b) => a[0] - b[0] || a[1] - b[1]);
  if (pts.length < 3) return pts;
  const crossZ = (o: Pt, a: Pt, b: Pt) =>
    (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0]);
  const lower: Pt[] = [];
  for (const p of pts) {
    while (
      lower.length >= 2 &&
      crossZ(lower[lower.length - 2] as Pt, lower[lower.length - 1] as Pt, p) <= 0
    )
      lower.pop();
    lower.push(p);
  }
  const upper: Pt[] = [];
  for (let i = pts.length - 1; i >= 0; i--) {
    const p = pts[i] as Pt;
    while (
      upper.length >= 2 &&
      crossZ(upper[upper.length - 2] as Pt, upper[upper.length - 1] as Pt, p) <= 0
    )
      upper.pop();
    upper.push(p);
  }
  upper.pop();
  lower.pop();
  return lower.concat(upper);
}

/** Points round an ellipse in a horizontal plane (a ring about the vertical through `c`). */
export function ring(c: V3, rx: number, rz: number, n = 20, from = 0, to = Math.PI * 2): V3[] {
  const out: V3[] = [];
  for (let i = 0; i <= n; i++) {
    const a = from + ((to - from) * i) / n;
    out.push([c[0] + Math.sin(a) * rx, c[1], c[2] + Math.cos(a) * rz]);
  }
  return out;
}

export const lerp = (a: number, b: number, t: number): number => a + (b - a) * t;
export const clamp = (x: number, lo: number, hi: number): number => (x < lo ? lo : x > hi ? hi : x);
