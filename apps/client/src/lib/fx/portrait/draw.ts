/**
 * Canvas helpers shared by the portrait painter: paths, cel shading (the
 * painted figures of ADR 0073 follow the same style), colour mixing, a seeded
 * generator, and the lamellar texture.
 */

export type G = CanvasRenderingContext2D;
export type Pt = readonly [number, number];

export const INK = "#2a1a12";
export const LW = 3;
export const LW2 = 2;

export function path(draw: (p: Path2D) => void): Path2D {
  const p = new Path2D();
  draw(p);
  return p;
}

export function poly(...pts: number[]): Path2D {
  return path((p) => {
    p.moveTo(pts[0] ?? 0, pts[1] ?? 0);
    for (let i = 2; i < pts.length; i += 2) p.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
    p.closePath();
  });
}

/** A closed polygon through points. */
export function polyPts(pts: readonly Pt[]): Path2D {
  return path((p) => {
    const [x0, y0] = pts[0] ?? [0, 0];
    p.moveTo(x0, y0);
    for (let i = 1; i < pts.length; i++) {
      const q = pts[i] as Pt;
      p.lineTo(q[0], q[1]);
    }
    p.closePath();
  });
}

export function ellipse(x: number, y: number, rx: number, ry: number, rot = 0): Path2D {
  return path((p) => p.ellipse(x, y, Math.max(0.1, rx), Math.max(0.1, ry), rot, 0, Math.PI * 2));
}

/** A smooth closed curve near the points (quadratic curves through the midpoints). */
export function blob(pts: readonly Pt[]): Path2D {
  return path((p) => {
    const n = pts.length;
    const at = (i: number) => pts[((i % n) + n) % n] as Pt;
    const mid = (i: number): [number, number] => {
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
  });
}

/** An open smooth curve through points (quadratics through the midpoints, ends exact). */
export function smoothOpen(p: Path2D, pts: readonly Pt[], move = true): void {
  const n = pts.length;
  if (n === 0) return;
  const a = pts[0] as Pt;
  if (move) p.moveTo(a[0], a[1]);
  else p.lineTo(a[0], a[1]);
  if (n < 3) {
    for (let i = 1; i < n; i++) p.lineTo((pts[i] as Pt)[0], (pts[i] as Pt)[1]);
    return;
  }
  for (let i = 1; i < n - 1; i++) {
    const c = pts[i] as Pt;
    const d = pts[i + 1] as Pt;
    const mx = i === n - 2 ? d[0] : (c[0] + d[0]) / 2;
    const my = i === n - 2 ? d[1] : (c[1] + d[1]) / 2;
    p.quadraticCurveTo(c[0], c[1], mx, my);
  }
}

/**
 * A cel-shaded shape: flat fill, a shadow band on the lower left (light from
 * the upper right), an optional highlight on the upper right, an ink outline.
 */
export function cel(
  g: G,
  p: Path2D,
  fill: string,
  shade?: string,
  depth = 6,
  hi?: string,
  lw = LW2,
): void {
  g.save();
  g.clip(p);
  if (shade) {
    g.fillStyle = shade;
    g.fill(p);
    g.translate(depth, -depth * 0.75);
    g.fillStyle = fill;
    g.fill(p);
    g.translate(-depth, depth * 0.75);
  } else {
    g.fillStyle = fill;
    g.fill(p);
  }
  if (hi) {
    g.translate(-2.5, 2);
    g.strokeStyle = hi;
    g.lineWidth = 5;
    g.stroke(p);
  }
  g.restore();
  if (lw > 0) {
    g.lineWidth = lw;
    g.strokeStyle = INK;
    g.stroke(p);
  }
}

/** Draw inside a shape only. */
export function inside(g: G, p: Path2D, draw: () => void): void {
  g.save();
  g.clip(p);
  draw();
  g.restore();
}

export function stroke(g: G, color: string, width: number, p: Path2D): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.stroke(p);
}

export function fill(g: G, color: string, p: Path2D): void {
  g.fillStyle = color;
  g.fill(p);
}

export function line(g: G, color: string, width: number, ...pts: number[]): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i < pts.length; i += 2) g.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
  g.stroke();
}

/** A quadratic curve per pair after the first point: x0 y0 cx cy x1 y1 [cx cy x2 y2 ...]. */
export function curve(g: G, color: string, width: number, ...pts: number[]): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i + 3 < pts.length; i += 4)
    g.quadraticCurveTo(pts[i] ?? 0, pts[i + 1] ?? 0, pts[i + 2] ?? 0, pts[i + 3] ?? 0);
  g.stroke();
}

export function dot(g: G, color: string, x: number, y: number, r: number): void {
  g.fillStyle = color;
  g.beginPath();
  g.arc(x, y, Math.max(0.1, r), 0, Math.PI * 2);
  g.fill();
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

export function hash(s: string): number {
  let h = 2166136261;
  for (let i = 0; i < s.length; i++) {
    h ^= s.charCodeAt(i);
    h = Math.imul(h, 16777619);
  }
  return h >>> 0;
}

/** A small seeded generator (mulberry32), so a face is the same every time. */
export function rng(seed: number): () => number {
  let a = seed >>> 0;
  return () => {
    a = (a + 0x6d2b79f5) >>> 0;
    let t = a;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

export function pick<T>(r: () => number, xs: readonly T[]): T {
  return xs[Math.floor(r() * xs.length) % xs.length] as T;
}

export function lerp(a: number, b: number, t: number): number {
  return a + (b - a) * t;
}

export function clamp(x: number, lo: number, hi: number): number {
  return x < lo ? lo : x > hi ? hi : x;
}

export function smooth(t: number): number {
  const u = clamp(t, 0, 1);
  return u * u * (3 - 2 * u);
}

/** A point on a quadratic curve and the unit normal there. */
export function onCurve(a: Pt, c: Pt, b: Pt, u: number): [number, number, number, number] {
  const v = 1 - u;
  const x = v * v * a[0] + 2 * v * u * c[0] + u * u * b[0];
  const y = v * v * a[1] + 2 * v * u * c[1] + u * u * b[1];
  const dx = 2 * v * (c[0] - a[0]) + 2 * u * (b[0] - c[0]);
  const dy = 2 * v * (c[1] - a[1]) + 2 * u * (b[1] - c[1]);
  const l = Math.hypot(dx, dy) || 1;
  return [x, y, -dy / l, dx / l];
}

/** A band along a quadratic curve, tapering from width w0 to w1: feathers, ribbons, strands. */
export function ribbon(a: Pt, c: Pt, b: Pt, w0: number, w1: number, n = 14): Path2D {
  const left: Pt[] = [];
  const right: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const u = i / n;
    const [x, y, nx, ny] = onCurve(a, c, b, u);
    const w = (w0 + (w1 - w0) * u) / 2;
    left.push([x + nx * w, y + ny * w]);
    right.push([x - nx * w, y - ny * w]);
  }
  return path((p) => {
    const first = left[0] as Pt;
    p.moveTo(first[0], first[1]);
    for (const [x, y] of left) p.lineTo(x, y);
    for (let i = right.length - 1; i >= 0; i--) {
      const q = right[i] as Pt;
      p.lineTo(q[0], q[1]);
    }
    p.closePath();
  });
}

/** A barred tail feather along a curve. */
export function feather(
  g: G,
  a: Pt,
  c: Pt,
  b: Pt,
  w: number,
  light: string,
  dark: string,
  bars: number,
): void {
  const f = ribbon(a, c, b, w, 2);
  cel(g, f, light, mix(light, dark, 0.5), 2, mix(light, "#ffffff", 0.4));
  inside(g, f, () => {
    for (let i = 1; i < bars; i++) {
      const [x, y, nx, ny] = onCurve(a, c, b, i / bars);
      line(
        g,
        i % 2 ? dark : alpha("#fff6e0", 0.8),
        3,
        x + nx * w,
        y + ny * w + 2,
        x - nx * w,
        y - ny * w - 2,
      );
    }
  });
}

/** Iron lamellae: staggered rows of small plates laced with the side's cord. */
export function lamellae(
  g: G,
  p: Path2D,
  lace: string,
  o: {
    x0: number;
    x1: number;
    y0: number;
    y1: number;
    row: number;
    plate: number;
    sag?: number;
    base: string;
    dark: string;
    light: string;
    /** The line under each row; lacquered plates show a red edge. */
    rowLine?: string | undefined;
    lw?: number;
  },
): void {
  cel(g, p, o.base, o.dark, 7, o.light, 0);
  const x0 = Math.min(o.x0, o.x1);
  const x1 = Math.max(o.x0, o.x1);
  const sag = o.sag ?? 0;
  const mid = (x0 + x1) / 2;
  const half = (x1 - x0) / 2 || 1;
  const yAt = (x: number, y: number) => y + sag * (1 - ((x - mid) / half) ** 2);
  const rows = new Path2D();
  const plates = new Path2D();
  const laces = new Path2D();
  let row = 0;
  for (let y = o.y0; y < o.y1; y += o.row, row++) {
    rows.moveTo(x0, yAt(x0, y));
    for (let x = x0 + 4; x <= x1 + 0.1; x += 4) rows.lineTo(x, yAt(x, y));
    for (let x = x0 + (row % 2) * (o.plate / 2); x < x1; x += o.plate) {
      const yy = yAt(x, y);
      plates.moveTo(x, yy);
      plates.lineTo(x, yy + o.row);
    }
    if (row % 2 === 0)
      for (let x = x0 + o.plate * 0.25; x < x1; x += o.plate * 2)
        laces.rect(x, yAt(x, y) - 1.5, o.plate * 0.5, 3);
  }
  inside(g, p, () => {
    stroke(g, o.rowLine ? alpha(o.rowLine, 0.9) : alpha("#1c1e22", 0.75), 1.6, rows);
    stroke(g, alpha("#1c1e22", 0.5), 1, plates);
    g.fillStyle = lace;
    g.fill(laces);
  });
  g.lineWidth = o.lw ?? LW2;
  g.strokeStyle = INK;
  g.stroke(p);
}

/**
 * A roll of shaggy fur along the points: star-shaped tufts of guard hair over
 * a dark undercoat, with an ink edge round the outside.
 */
export function furRoll(
  g: G,
  pts: readonly Pt[],
  r: number,
  col: readonly [string, string, string],
): void {
  const tufts = (k: number, dx: number, dy: number) =>
    path((p) => {
      for (const [i, [x, y]] of pts.entries()) {
        const n = 16;
        for (let j = 0; j < n; j++) {
          const a = (j / n) * Math.PI * 2 + i * 1.3;
          const rr = r * k * (j % 2 ? 0.6 : 1.05 + ((i + j) % 3) * 0.14);
          const px = x + dx + Math.cos(a) * rr;
          const py = y + dy + Math.sin(a) * rr;
          if (j === 0) p.moveTo(px, py);
          else p.lineTo(px, py);
        }
        p.closePath();
      }
    });
  const coat = tufts(1, 0, 0);
  stroke(g, INK, 4, coat);
  g.fillStyle = col[1];
  g.fill(coat);
  g.fillStyle = col[0];
  g.fill(tufts(0.78, 1.5, -2));
  for (const [i, [x, y]] of pts.entries()) {
    const a = i * 1.3;
    const dx = Math.cos(a) * 3;
    line(g, col[2], 1.6, x + 2 + dx, y - r * 0.55, x + 4 + dx * 1.6, y + r * 0.25);
    if (i % 2 === 0) line(g, alpha(col[2], 0.8), 1.3, x - 4, y - 3, x - 7, y + 5);
  }
}
