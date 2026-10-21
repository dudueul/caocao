/**
 * The head model: a skull and face described by horizontal cross-sections,
 * turned by a yaw angle and projected onto the canvas (ADR 0070). Every
 * feature, every strand of hair and every cap is placed by azimuth on this
 * head, so one code path draws the front, three-quarter left and right views
 * and the near profile.
 *
 * Head space: x to the viewer's right, y down, 0 at the eye line. Azimuth θ
 * is 0 at the face's centre line and positive toward the man's left (the
 * viewer's right when he faces us). Yaw φ > 0 turns the face to the viewer's
 * right.
 */
import { clamp, type Pt, path, smooth } from "./draw.ts";
import type { FaceShape, NoseKind, Traits } from "./types.ts";

export const DEG = Math.PI / 180;

/** A projected point: screen x and y (head space), depth toward the viewer, facing (cos). */
export interface Proj {
  x: number;
  y: number;
  z: number;
  c: number;
}

interface Shape {
  w: number;
  len: number;
  jaw: number;
  chin: number;
  cheek: number;
  jowl: number;
  gaunt: number;
}

/** Face shape presets over width, length, jaw, chin and cheekbones (0 narrow, 1 broad). */
const SHAPES: Record<FaceShape, Shape> = {
  oval: { w: 1, len: 1, jaw: 0.45, chin: 0.4, cheek: 0.5, jowl: 0, gaunt: 0 },
  long: { w: 0.94, len: 1.1, jaw: 0.42, chin: 0.35, cheek: 0.55, jowl: 0, gaunt: 0 },
  square: { w: 1.05, len: 0.98, jaw: 1, chin: 0.85, cheek: 0.45, jowl: 0, gaunt: 0 },
  round: { w: 1.1, len: 0.93, jaw: 0.8, chin: 0.75, cheek: 0.3, jowl: 0, gaunt: 0 },
  tri: { w: 0.98, len: 1.04, jaw: 0.15, chin: 0.1, cheek: 0.9, jowl: 0, gaunt: 0 },
  jowly: { w: 1.12, len: 1, jaw: 0.95, chin: 0.85, cheek: 0.2, jowl: 1, gaunt: 0 },
  gaunt: { w: 0.94, len: 1.08, jaw: 0.6, chin: 0.4, cheek: 1, jowl: 0, gaunt: 1 },
};

const NOSES: Record<NoseKind, { p: number; w: number; y: number; hook: number; tip: number }> = {
  straight: { p: 15, w: 11, y: 31, hook: 0, tip: 1 },
  broad: { p: 13, w: 16, y: 31, hook: 0, tip: 1.35 },
  hook: { p: 17, w: 11, y: 32, hook: 4, tip: 1 },
  small: { p: 10, w: 9, y: 29, hook: 0, tip: 0.8 },
  long: { p: 18, w: 11, y: 35, hook: 1, tip: 1 },
  bulb: { p: 16, w: 15, y: 32, hook: 0, tip: 1.5 },
};

/** Sampled azimuths for the silhouette: every 4°, finer near the centre line for the nose. */
const THETAS: readonly number[] = (() => {
  const out: number[] = [];
  for (let d = -180; d < 180; d += 4) if (Math.abs(d) > 16) out.push(d * DEG);
  for (let d = -16; d <= 16; d += 1.5) out.push(d * DEG);
  return out.sort((a, b) => a - b);
})();

export class Head {
  readonly phi: number;
  readonly s: number;
  readonly c: number;
  readonly W: number;
  readonly L: number;
  /** Cranium centre and semi-height (its top is the skull's top). */
  readonly yc = -30;
  readonly a = 68;
  readonly Rc: number;
  readonly Fc: number;
  readonly Bc: number;
  /** Cheekbones, jaw angle, chin: half-widths and levels. */
  readonly Rk: number;
  readonly yk: number;
  readonly Rj: number;
  readonly yj: number;
  readonly Rch: number;
  readonly ych: number;
  readonly ycb: number;
  readonly gaunt: number;
  readonly jowl: number;
  readonly chinOut: number;
  /** The brow ridge's depth; a heavy ridge for a few fierce men. */
  readonly browRidge: number;
  /** Nose: protrusion, angular half-width, tip level, hook, tip size. */
  readonly noseP: number;
  readonly noseW: number;
  readonly ny: number;
  readonly hook: number;
  readonly tip: number;
  readonly my: number;
  /** Silhouette: levels and the left and right screen extents at each. */
  readonly ys: number[] = [];
  readonly left: number[] = [];
  readonly right: number[] = [];

  constructor(t: Traits, phi: number) {
    this.phi = phi;
    this.s = Math.sin(phi);
    this.c = Math.cos(phi);
    const sh = SHAPES[t.face];
    const age = t.age;
    this.W = sh.w * (t.w / 100);
    this.L = sh.len * (t.len / 100);
    const W = this.W;
    const L = this.L;
    this.Rc = 60 * W;
    this.Fc = 54 * W;
    this.Bc = 64 * W;
    this.gaunt = sh.gaunt;
    this.jowl = sh.jowl + (t.marks.includes("jowls") ? 1 : 0);
    const big = t.marks.includes("jaw") ? 1 : 0;
    this.browRidge = t.marks.includes("brow") ? 9 : 3;
    // Age by proportion first (ADR 0070): the jaw grows, the cheek contour sinks, the nose lengthens.
    this.Rk = (53 + sh.cheek * 7) * W;
    this.yk = 10 + age * 2;
    this.Rj = (36 + sh.jaw * 17 + age * 1.5 + this.jowl * 7 + big * 14) * W;
    this.yj = (40 + sh.jaw * 8 + this.jowl * 6 + age * 1.5) * L;
    this.Rch = (12 + sh.chin * 12 + this.jowl * 6 + big * 12) * W;
    this.ych = (68 + this.jowl * 3) * L;
    this.ycb = (79 + sh.chin * 2 + this.jowl * 6 + big * 5) * L;
    this.chinOut = 2 + sh.chin * 3 + big * 3;
    const n = NOSES[t.nose];
    this.noseP = n.p * (1 + age * 0.03) * Math.min(W, 1.08);
    this.noseW = n.w * DEG * (0.9 + W * 0.1);
    this.ny = n.y * L * (1 + age * 0.015);
    this.hook = n.hook;
    this.tip = n.tip;
    this.my = (51 + age * 0.8) * L;
    this.sample();
  }

  cran(y: number): number {
    const u = (y - this.yc) / this.a;
    return Math.sqrt(Math.max(0, 1 - u * u));
  }

  /** Half-width of the head at level y (face front view). */
  rw(y: number): number {
    const top = this.Rc * this.cran(y);
    if (y <= 0) return top;
    if (y <= this.yk)
      return Math.max(top, this.Rk * smooth(y / this.yk) + top * (1 - smooth(y / this.yk)));
    if (y <= this.yj) {
      const u = (y - this.yk) / (this.yj - this.yk);
      // Hollow cheeks dip below the line from cheekbone to jaw.
      const dip = this.gaunt * 4 * Math.sin(u * Math.PI);
      return this.Rk + (this.Rj - this.Rk) * u ** 1.4 - dip;
    }
    if (y <= this.ych)
      return this.Rj + (this.Rch - this.Rj) * smooth((y - this.yj) / (this.ych - this.yj));
    if (y <= this.ycb) {
      const u = (y - this.ych) / (this.ycb - this.ych);
      return this.Rch * Math.sqrt(Math.max(0, 1 - u * u));
    }
    return 0;
  }

  /** Depth of the face's front at level y, without the nose, lips and chin. */
  df(y: number): number {
    if (y <= 0) return this.Fc * this.cran(y);
    const base = this.Fc * this.cran(0);
    if (y <= this.ych) return base - (y / this.ych) * 4;
    const u = (y - this.ych) / (this.ycb - this.ych);
    return (base - 4) * Math.sqrt(Math.max(0, 1 - u * u));
  }

  /** Depth of the back of the head at level y (the nape tucks in under the skull). */
  db(y: number): number {
    if (y <= 16) {
      const u = (y - this.yc) / (this.a + 14);
      return this.Bc * Math.sqrt(Math.max(0, 1 - u * u));
    }
    const at16 = this.Bc * Math.sqrt(Math.max(0, 1 - ((16 - this.yc) / (this.a + 14)) ** 2));
    return Math.max(this.rw(y) * 0.3, at16 * (1 - (y - 16) / 26));
  }

  /** Extra depth near the centre line: brow ridge, nose, lips, chin. */
  bump(th: number, y: number): number {
    let b = 0;
    if (y > -26 && y < -6) {
      const g = 1 - (th / (48 * DEG)) ** 2;
      if (g > 0) b += this.browRidge * this.W * g * Math.sin(((y + 26) / 20) * Math.PI);
    }
    const ny = this.ny;
    if (y > -10 && y < ny + 6) {
      const g = 1 - (th / this.noseW) ** 2;
      if (g > 0) {
        let n: number;
        if (y <= ny) {
          const u = (y + 10) / (ny + 10);
          n = 2 + (this.noseP - 2) * u ** 1.3 + this.hook * Math.sin(u * Math.PI);
        } else n = this.noseP * (1 - (y - ny) / 6) ** 0.6;
        b += n * g * g;
      }
    }
    const my = this.my;
    if (y > my - 8 && y < my + 9) {
      const g = 1 - (th / (26 * DEG)) ** 2;
      if (g > 0) b += 3 * g * Math.sin(((y - my + 8) / 17) * Math.PI);
    }
    if (y > this.ych - 12 && y < this.ycb) {
      const g = 1 - (th / (30 * DEG)) ** 2;
      if (g > 0)
        b +=
          this.chinOut * g * Math.sin(((y - this.ych + 12) / (this.ycb - this.ych + 12)) * Math.PI);
    }
    return b;
  }

  /** The point on the head at azimuth θ and level y, pushed out by k (1 = on the skin). */
  local(th: number, y: number, k = 1, bumps = true): [number, number] {
    const cs = Math.cos(th);
    const lx = this.rw(y) * Math.sin(th) * k;
    const d = cs >= 0 ? this.df(y) : this.db(y);
    const lz = (d * cs + (bumps ? this.bump(th, y) : 0)) * k;
    return [lx, lz];
  }

  /** Project a point given in head-local 3D (lx across, y down, lz toward the face's front). */
  p3(lx: number, y: number, lz: number): Proj {
    const x = lx * this.c + lz * this.s;
    const z = -lx * this.s + lz * this.c;
    const r = Math.hypot(lx, lz) || 1;
    return { x, y, z, c: z / r };
  }

  /** Project a point on the head's surface. */
  proj(th: number, y: number, k = 1, bumps = true): Proj {
    const [lx, lz] = this.local(th, y, k, bumps);
    const p = this.p3(lx, y, lz);
    p.c = Math.cos(th + this.phi);
    return p;
  }

  pt(th: number, y: number, k = 1, bumps = true): Pt {
    const p = this.proj(th, y, k, bumps);
    return [p.x, p.y];
  }

  /** Facing of azimuth θ toward the viewer (cos). */
  facing(th: number): number {
    return Math.cos(th + this.phi);
  }

  private sample(): void {
    const top = this.yc - this.a;
    const ths = THETAS;
    const sin = ths.map(Math.sin);
    const cos = ths.map(Math.cos);
    for (let y = top + 1; y <= this.ycb + 0.01; y += y < -20 ? 3 : 2) {
      const rw = this.rw(y);
      const f = this.df(y);
      const b = this.db(y);
      let lo = Number.POSITIVE_INFINITY;
      let hi = Number.NEGATIVE_INFINITY;
      for (let i = 0; i < ths.length; i++) {
        const cs = cos[i] as number;
        const th = ths[i] as number;
        const lx = rw * (sin[i] as number);
        const lz = (cs >= 0 ? f : b) * cs + (Math.abs(th) < 50 * DEG ? this.bump(th, y) : 0);
        const x = lx * this.c + lz * this.s;
        if (x < lo) lo = x;
        if (x > hi) hi = x;
      }
      this.ys.push(y);
      this.left.push(lo);
      this.right.push(hi);
    }
  }

  /** The head's outline (skin), from the silhouette. */
  outline(grow = 0): Path2D {
    const n = this.ys.length;
    return path((p) => {
      const top = this.yc - this.a - grow;
      const midTop = ((this.left[0] as number) + (this.right[0] as number)) / 2;
      p.moveTo(midTop, top);
      for (let i = 0; i < n; i++) p.lineTo((this.right[i] as number) + grow, this.ys[i] as number);
      const last = n - 1;
      p.lineTo(
        ((this.left[last] as number) + (this.right[last] as number)) / 2,
        (this.ys[last] as number) + grow * 0.5,
      );
      for (let i = n - 1; i >= 0; i--)
        p.lineTo((this.left[i] as number) - grow, this.ys[i] as number);
      p.closePath();
    });
  }

  /** Left and right extents of the silhouette at level y (interpolated). */
  extentAt(y: number): [number, number] {
    const ys = this.ys;
    if (y <= (ys[0] as number)) return [this.left[0] as number, this.right[0] as number];
    for (let i = 1; i < ys.length; i++) {
      const y1 = ys[i] as number;
      if (y <= y1) {
        const y0 = ys[i - 1] as number;
        const u = (y - y0) / (y1 - y0 || 1);
        const l = (this.left[i - 1] as number) * (1 - u) + (this.left[i] as number) * u;
        const r = (this.right[i - 1] as number) * (1 - u) + (this.right[i] as number) * u;
        return [l, r];
      }
    }
    const k = ys.length - 1;
    return [this.left[k] as number, this.right[k] as number];
  }

  /** The silhouette's left (or right) edge between two levels, top to bottom. */
  edge(side: "left" | "right", y0: number, y1: number, grow = 0): Pt[] {
    const out: Pt[] = [];
    const xs = side === "left" ? this.left : this.right;
    const sg = side === "left" ? -1 : 1;
    const [a0, b0] = this.extentAt(y0);
    out.push([(side === "left" ? a0 : b0) + sg * grow, y0]);
    for (let i = 0; i < this.ys.length; i++) {
      const y = this.ys[i] as number;
      if (y > y0 && y < y1) out.push([(xs[i] as number) + sg * grow, y]);
    }
    const [a1, b1] = this.extentAt(y1);
    out.push([(side === "left" ? a1 : b1) + sg * grow, y1]);
    return out;
  }

  // ------------------------------------------------------------ caps and rings

  /**
   * Azimuths of the left and right limbs of an elliptical cross-section
   * (half-width a, front depth f, back depth b): the visible arc runs from the
   * first to the second, through the front.
   */
  limbs(a: number, f: number, b: number): [number, number] {
    const s = this.s;
    const c = this.c;
    if (s >= 0) return [-Math.PI + Math.atan2(a * c, b * s), Math.atan2(a * c, f * s)];
    return [-Math.atan2(a * c, -f * s), Math.PI - Math.atan2(a * c, -b * s)];
  }

  /** A point on an elliptical cross-section at level y; `tilt` bows rings above the eyes upward at the front. */
  ring1(th: number, y: number, a: number, f: number, b: number, tilt = 0.22): Proj {
    const cs = Math.cos(th);
    const lx = a * Math.sin(th);
    const lz = (cs >= 0 ? f : b) * cs;
    const p = this.p3(lx, y, lz);
    const r = Math.max(a, f, b) || 1;
    p.y = y + y * tilt * (p.z / r);
    p.c = Math.cos(th + this.phi);
    return p;
  }

  /** The visible front arc of a ring, left limb to right limb. */
  ring(y: number, a: number, f: number, b: number, n = 24, tilt = 0.22): Pt[] {
    const [t0, t1] = this.limbs(a, f, b);
    const out: Pt[] = [];
    for (let i = 0; i <= n; i++) {
      const p = this.ring1(t0 + ((t1 - t0) * i) / n, y, a, f, b, tilt);
      out.push([p.x, p.y]);
    }
    return out;
  }

  /** The skull's cross-section at level y scaled by k: half-width, front and back depth. */
  skullAt(y: number, k: number): [number, number, number] {
    const cr = this.cran(Math.min(y, -2));
    return [this.Rc * cr * k, this.Fc * cr * k, this.Bc * cr * k];
  }

  /** Screen extents of an elliptical cross-section. */
  ext(a: number, f: number, b: number): [number, number] {
    const ac = a * this.c;
    if (this.s >= 0) return [-Math.hypot(ac, b * this.s), Math.hypot(ac, f * this.s)];
    return [-Math.hypot(ac, -f * this.s), Math.hypot(ac, -b * this.s)];
  }

  /**
   * A cap's body: a solid of revolution over the head whose cross-section at
   * level y is given by `prof` (half-width, front, back), from its top down to
   * a brim ring at `brim`. The outline runs up the left limb, over the top,
   * down the right limb, and back along the brim's visible front arc.
   */
  capBody(
    prof: (y: number) => [number, number, number],
    top: number,
    brim: number,
    tilt = 0.22,
  ): Path2D {
    const L: Pt[] = [];
    const R: Pt[] = [];
    const steps = 22;
    for (let i = 0; i <= steps; i++) {
      const y = top + ((brim - top) * i) / steps;
      const [a, f, b] = prof(y);
      const [lo, hi] = this.ext(a, f, b);
      L.push([lo, y]);
      R.push([hi, y]);
    }
    const [a, f, b] = prof(brim);
    const arc = this.ring(brim, a, f, b, 24, tilt);
    return path((p) => {
      const t0 = L[0] as Pt;
      p.moveTo((t0[0] + (R[0] as Pt)[0]) / 2, top);
      for (const q of R) p.lineTo(q[0], q[1]);
      for (let i = arc.length - 1; i >= 0; i--) {
        const q = arc[i] as Pt;
        p.lineTo(q[0], q[1]);
      }
      for (let i = L.length - 1; i >= 0; i--) {
        const q = L[i] as Pt;
        p.lineTo(q[0], q[1]);
      }
      p.closePath();
    });
  }

  /** A band between two rings (the visible front), e.g. a cap's brow band. */
  band(y0: number, y1: number, k0: number, k1: number, tilt = 0.22): Path2D {
    const [a0, f0, b0] = this.skullAt(y0, k0);
    const [a1, f1, b1] = this.skullAt(y1, k1);
    const top = this.ring(y0, a0, f0, b0, 24, tilt);
    const bot = this.ring(y1, a1, f1, b1, 24, tilt);
    return path((p) => {
      const s0 = top[0] as Pt;
      p.moveTo(s0[0], s0[1]);
      for (const q of top) p.lineTo(q[0], q[1]);
      for (let i = bot.length - 1; i >= 0; i--) {
        const q = bot[i] as Pt;
        p.lineTo(q[0], q[1]);
      }
      p.closePath();
    });
  }

  /**
   * A patch on a surface of revolution between azimuths θ0..θ1 and levels
   * y0..y1, its cross-section from `prof`. Parts that turn away are pressed
   * onto the limb, so a far flap shows as a sliver at the edge. Null when the
   * patch faces wholly away.
   */
  patch(
    th0: number,
    th1: number,
    y0: number,
    y1: number,
    prof: (y: number) => [number, number, number],
    tilt = 0.22,
  ): Path2D | null {
    const [pa, pf, pb] = prof((y0 + y1) / 2);
    const [l0, l1] = this.limbs(pa, pf, pb);
    const lo = Math.max(Math.min(th0, th1), l0);
    const hi = Math.min(Math.max(th0, th1), l1);
    // Wrap-around: a patch over the back (θ near ±π) is tested on both sides.
    if (hi - lo < 0.5 * DEG) return null;
    const n = 10;
    const top: Pt[] = [];
    const bot: Pt[] = [];
    for (let i = 0; i <= n; i++) {
      const th = lo + ((hi - lo) * i) / n;
      const [a, f, b] = prof(y0);
      const p = this.ring1(th, y0, a, f, b, tilt);
      top.push([p.x, p.y]);
      const [a2, f2, b2] = prof(y1);
      const q = this.ring1(th, y1, a2, f2, b2, tilt);
      bot.push([q.x, q.y]);
    }
    return path((p) => {
      const s0 = top[0] as Pt;
      p.moveTo(s0[0], s0[1]);
      for (const q of top) p.lineTo(q[0], q[1]);
      for (let i = bot.length - 1; i >= 0; i--) {
        const q = bot[i] as Pt;
        p.lineTo(q[0], q[1]);
      }
      p.closePath();
    });
  }

  /** The screen x of the light's terminator at level y (the shadow side is the viewer's left). */
  terminator(y: number): number {
    const [l, r] = this.extentAt(y);
    return (l + r) / 2 - ((r - l) / 2) * 0.55;
  }

  /** Clamp an azimuth into the visible range of the skull at level y. */
  clampVisible(th: number, y: number): number {
    const [a, f, b] = this.skullAt(y, 1);
    const [l0, l1] = this.limbs(Math.max(a, 1), Math.max(f, 1), Math.max(b, 1));
    return clamp(th, l0, l1);
  }
}

/** The yaw for a turn. */
export function yawOf(turn: string): number {
  switch (turn) {
    case "R3":
      return 30 * DEG;
    case "L3":
      return -30 * DEG;
    case "P":
    case "back":
      return 68 * DEG;
    default:
      return 0;
  }
}
