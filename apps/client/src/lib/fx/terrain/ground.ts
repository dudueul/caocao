/**
 * The ground as continuous fields (ADR 0074): per-tile values blended between
 * tile centres through a noisy warp, so grass fades into loess in irregular
 * patches and nothing meets along a tile edge. A height field gives hills
 * their mounds and mountains their ridges, lit from the upper left across
 * tile borders. Water gets banks, shallows and a little foam where it meets
 * land. Computed at low resolution (a few pixels per tile) and smoothed up.
 */
import type { Look } from "./landscape.ts";
import { fbm, type RGB, ridgedMF, smooth, vnoise } from "./noise.ts";
import { HILLS, type Plan } from "./plan.ts";

export interface GroundField {
  /** Field pixels per tile. */
  readonly L: number;
  readonly W: number;
  readonly H: number;
  /** Land colour and alpha (alpha falls away over water). */
  readonly land: Uint8ClampedArray;
  /** Water colour, opaque, under everything. */
  readonly water: Uint8ClampedArray;
  /** Overlay masks (RGBA, alpha only): grass blades, rock, paving. */
  readonly grassMask: Uint8ClampedArray;
  readonly rockMask: Uint8ClampedArray;
  readonly paveMask: Uint8ClampedArray;
  /** Where grit and dry grass show: open land, not water or ice. */
  readonly dirtMask: Uint8ClampedArray;
  /** Samples for placing detail. */
  readonly grass: Float32Array;
  readonly forest: Float32Array;
  readonly wet: Float32Array;
  readonly hill: Float32Array;
  readonly mount: Float32Array;
  readonly height: Float32Array;
  readonly built: Float32Array;
}

interface Mound {
  x: number;
  y: number;
  r: number;
  a: number;
}

const LIGHT = Math.SQRT1_2;
/** Coarse samples per tile for the slow, broad noises (warp, patches, tones). */
const G = 8;

/**
 * Mounds per hill tile: one dome near its middle, so every hill tile shows its
 * own rise, and a smaller knoll beside it; neighbouring domes overlap into
 * ridges with saddles between.
 */
function mounds(plan: Plan, relief: number): Mound[][] {
  const out: Mound[][] = [];
  for (let i = 0; i < plan.w * plan.h; i++) {
    const list: Mound[] = [];
    if (plan.cls[i] === HILLS) {
      const x = i % plan.w;
      const y = (i - x) / plan.w;
      for (let m = 0; m < 2; m++) {
        const jx = vnoise(x * 7.3 + m * 1.7, y * 7.3, 6) - 0.5;
        const jy = vnoise(x * 7.3, y * 7.3 + m * 1.9, 7) - 0.5;
        list.push({
          x: x + 0.5 + jx * (m === 0 ? 0.3 : 0.6),
          y: y + 0.5 + jy * (m === 0 ? 0.3 : 0.6),
          r:
            m === 0
              ? 0.58 + vnoise(x * 5.1, y * 5.1, 8) * 0.2
              : 0.32 + vnoise(x * 5.1 + 3, y * 5.1, 8) * 0.1,
          a: (m === 0 ? 0.78 : 0.2) * (0.85 + vnoise(x * 4.3, y * 4.3 + m, 9) * 0.3) * relief,
        });
      }
    }
    out.push(list);
  }
  return out;
}

function mixTo(c: Float64Array, r: number, g: number, b: number, t: number): void {
  if (t <= 0) return;
  c[0] = (c[0] as number) + (r - (c[0] as number)) * t;
  c[1] = (c[1] as number) + (g - (c[1] as number)) * t;
  c[2] = (c[2] as number) + (b - (c[2] as number)) * t;
}

/** Three tones of a material by two noises: broad patches and finer mottling. */
function tone(out: Float64Array, c: readonly [RGB, RGB, RGB], n1: number, n2: number): void {
  const t = n1 < 0.5 ? n1 * 2 : (n1 - 0.5) * 2;
  const a = n1 < 0.5 ? c[1] : c[0];
  const b = n1 < 0.5 ? c[0] : c[2];
  const k = 1 + (n2 - 0.5) * 0.18;
  out[0] = (a[0] + (b[0] - a[0]) * t) * k;
  out[1] = (a[1] + (b[1] - a[1]) * t) * k;
  out[2] = (a[2] + (b[2] - a[2]) * t) * k;
}

function mix4(
  t: Float32Array,
  i00: number,
  i10: number,
  i01: number,
  i11: number,
  w00: number,
  w10: number,
  w01: number,
  w11: number,
): number {
  return (
    (t[i00] as number) * w00 +
    (t[i10] as number) * w10 +
    (t[i01] as number) * w01 +
    (t[i11] as number) * w11
  );
}

/**
 * Works a row at a time (small hot functions optimise well in every engine,
 * Safari's included); the arrays live on the instance.
 */
class Builder {
  readonly W: number;
  readonly H: number;
  readonly grass: Float32Array;
  readonly forest: Float32Array;
  readonly wet: Float32Array;
  readonly deep: Float32Array;
  readonly ford: Float32Array;
  readonly hill: Float32Array;
  readonly mount: Float32Array;
  readonly pave: Float32Array;
  readonly yard: Float32Array;
  readonly camp: Float32Array;
  readonly built: Float32Array;
  readonly height: Float32Array;
  readonly ridge: Float32Array;
  readonly n1: Float32Array;
  readonly n3: Float32Array;
  readonly gAdd: Float32Array;
  readonly gCut: Float32Array;
  readonly gTone: Float32Array;
  readonly land: Uint8ClampedArray;
  readonly water: Uint8ClampedArray;
  readonly grassMask: Uint8ClampedArray;
  readonly rockMask: Uint8ClampedArray;
  readonly paveMask: Uint8ClampedArray;
  readonly dirtMask: Uint8ClampedArray;
  readonly CW: number;
  readonly CH: number;
  readonly cWX: Float32Array;
  readonly cWY: Float32Array;
  readonly cN1: Float32Array;
  readonly cN3: Float32Array;
  readonly cAdd: Float32Array;
  readonly cCut: Float32Array;
  readonly cTone: Float32Array;
  readonly cSwell: Float32Array;
  readonly colK: Int32Array;
  readonly colF: Float32Array;
  readonly hillMounds: Mound[][];
  readonly nearHill: Uint8Array;
  readonly col = new Float64Array(3);
  readonly ton = new Float64Array(3);

  constructor(
    readonly plan: Plan,
    readonly look: Look,
    readonly L: number,
  ) {
    const { w, h } = plan;
    this.W = w * L;
    this.H = h * L;
    const N = this.W * this.H;
    const f = () => new Float32Array(N);
    this.grass = f();
    this.forest = f();
    this.wet = f();
    this.deep = f();
    this.ford = f();
    this.hill = f();
    this.mount = f();
    this.pave = f();
    this.yard = f();
    this.camp = f();
    this.built = f();
    this.height = f();
    this.ridge = f();
    this.n1 = f();
    this.n3 = f();
    this.gAdd = f();
    this.gCut = f();
    this.gTone = f();
    const b = () => new Uint8ClampedArray(N * 4);
    this.land = b();
    this.water = b();
    this.grassMask = b();
    this.rockMask = b();
    this.paveMask = b();
    this.dirtMask = b();
    this.CW = w * G + 2;
    this.CH = h * G + 2;
    const CN = this.CW * this.CH;
    const c = () => new Float32Array(CN);
    this.cWX = c();
    this.cWY = c();
    this.cN1 = c();
    this.cN3 = c();
    this.cAdd = c();
    this.cCut = c();
    this.cTone = c();
    this.cSwell = c();
    this.colK = new Int32Array(this.W);
    this.colF = new Float32Array(this.W);
    for (let i = 0; i < this.W; i++) {
      const g = ((i + 0.5) / L) * G;
      const k = Math.min(this.CW - 2, Math.floor(g));
      this.colK[i] = k;
      this.colF[i] = g - k;
    }
    this.hillMounds = mounds(plan, look.density.relief);
    this.nearHill = new Uint8Array(w * h);
    for (let y = 0; y < h; y++)
      for (let x = 0; x < w; x++)
        for (let dy = -1; dy <= 1; dy++)
          for (let dx = -1; dx <= 1; dx++) {
            const nx = x + dx;
            const ny = y + dy;
            if (nx >= 0 && ny >= 0 && nx < w && ny < h && plan.cls[ny * w + nx] === HILLS)
              this.nearHill[y * w + x] = 1;
          }
  }

  coarseRow(cj: number): void {
    const v = cj / G;
    const CW = this.CW;
    for (let ci = 0; ci < CW; ci++) {
      const u = ci / G;
      const k = cj * CW + ci;
      this.cWX[k] =
        (vnoise(u * 0.9, v * 0.9, 11) - 0.5) * 0.46 + (vnoise(u * 2.7, v * 2.7, 12) - 0.5) * 0.2;
      this.cWY[k] =
        (vnoise(u * 0.9, v * 0.9, 13) - 0.5) * 0.46 + (vnoise(u * 2.7, v * 2.7, 14) - 0.5) * 0.2;
      this.cN1[k] = fbm(u * 0.45, v * 0.45, 51, 2);
      this.cN3[k] = fbm(u * 1.9, v * 1.9, 53, 2);
      // Grass patches out in the loess, bare patches in the grass.
      this.cAdd[k] = smooth(0.52, 0.74, fbm(u * 1.1, v * 1.1, 54, 2)) * 0.6;
      this.cCut[k] = smooth(0.64, 0.82, fbm(u * 1.5, v * 1.5, 55, 2)) * 0.6;
      this.cTone[k] = fbm(u * 0.6, v * 0.6, 56, 2);
      this.cSwell[k] = (fbm(u * 0.7, v * 0.7, 31, 2) - 0.5) * 0.08;
    }
  }

  /** Blends the tile tables through the warp and builds heights for one row. */
  row1(j: number): void {
    const { plan, L, W, CW } = this;
    const { w, h, tables: T } = plan;
    const v = (j + 0.5) / L;
    const gj = ((j + 0.5) / L) * G;
    const rk = Math.min(this.CH - 2, Math.floor(gj));
    const rf = gj - rk;
    const relief = this.look.density.relief;
    for (let i = 0; i < W; i++) {
      const u = (i + 0.5) / L;
      const p = j * W + i;
      const cf = this.colF[i] as number;
      const k = rk * CW + (this.colK[i] as number);
      const c00 = (1 - cf) * (1 - rf);
      const c10 = cf * (1 - rf);
      const c01 = (1 - cf) * rf;
      const c11 = cf * rf;
      const k1 = k + 1;
      const k2 = k + CW;
      const k3 = k2 + 1;
      this.n1[p] = mix4(this.cN1, k, k1, k2, k3, c00, c10, c01, c11);
      this.n3[p] = mix4(this.cN3, k, k1, k2, k3, c00, c10, c01, c11);
      this.gAdd[p] = mix4(this.cAdd, k, k1, k2, k3, c00, c10, c01, c11);
      this.gCut[p] = mix4(this.cCut, k, k1, k2, k3, c00, c10, c01, c11);
      this.gTone[p] = mix4(this.cTone, k, k1, k2, k3, c00, c10, c01, c11);
      const a = u + mix4(this.cWX, k, k1, k2, k3, c00, c10, c01, c11) - 0.5;
      const b = v + mix4(this.cWY, k, k1, k2, k3, c00, c10, c01, c11) - 0.5;
      const x0 = Math.floor(a);
      const y0 = Math.floor(b);
      let fx = a - x0;
      let fy = b - y0;
      fx = fx * fx * (3 - 2 * fx);
      fy = fy * fy * (3 - 2 * fy);
      const xa = x0 < 0 ? 0 : x0 >= w ? w - 1 : x0;
      const xb = x0 + 1 < 0 ? 0 : x0 + 1 >= w ? w - 1 : x0 + 1;
      const ya = y0 < 0 ? 0 : y0 >= h ? h - 1 : y0;
      const yb = y0 + 1 < 0 ? 0 : y0 + 1 >= h ? h - 1 : y0 + 1;
      const i00 = ya * w + xa;
      const i10 = ya * w + xb;
      const i01 = yb * w + xa;
      const i11 = yb * w + xb;
      const w00 = (1 - fx) * (1 - fy);
      const w10 = fx * (1 - fy);
      const w01 = (1 - fx) * fy;
      const w11 = fx * fy;
      this.grass[p] = mix4(T.grass, i00, i10, i01, i11, w00, w10, w01, w11);
      this.forest[p] = mix4(T.forest, i00, i10, i01, i11, w00, w10, w01, w11);
      const wv = mix4(T.wet, i00, i10, i01, i11, w00, w10, w01, w11);
      this.wet[p] = wv;
      this.deep[p] = mix4(T.deep, i00, i10, i01, i11, w00, w10, w01, w11);
      this.ford[p] = mix4(T.ford, i00, i10, i01, i11, w00, w10, w01, w11);
      const hv = mix4(T.hill, i00, i10, i01, i11, w00, w10, w01, w11);
      const mv = mix4(T.mount, i00, i10, i01, i11, w00, w10, w01, w11);
      this.hill[p] = hv;
      this.mount[p] = mv;
      this.pave[p] = mix4(T.pave, i00, i10, i01, i11, w00, w10, w01, w11);
      this.yard[p] = mix4(T.yard, i00, i10, i01, i11, w00, w10, w01, w11);
      this.camp[p] = mix4(T.camp, i00, i10, i01, i11, w00, w10, w01, w11);
      this.built[p] = mix4(T.built, i00, i10, i01, i11, w00, w10, w01, w11);

      // Heights: gentle swell everywhere, mounds on hills, ridges on mountains.
      let ht = mix4(this.cSwell, k, k1, k2, k3, c00, c10, c01, c11);
      if (hv > 0.01) ht += this.hillHeight(u, v, hv, relief);
      if (mv > 0.01) {
        // A massif that rises inland, broad ridges and gullies on it, finer spurs on top.
        const ms = mix4(T.massif, i00, i10, i01, i11, w00, w10, w01, w11);
        const r = ridgedMF(u * 0.9, v * 0.9, 41, 4);
        this.ridge[p] = r;
        ht += smooth(0.05, 0.5, mv) * (0.3 + ms * 1.0 + r * (1.5 + ms * 0.6)) * relief;
      }
      this.height[p] = ht - wv * 0.3;
    }
  }

  hillHeight(u: number, v: number, hv: number, relief: number): number {
    const { w, h } = this.plan;
    const tx = u | 0;
    const ty = v | 0;
    if (!this.nearHill[(ty < h ? ty : h - 1) * w + (tx < w ? tx : w - 1)]) return 0;
    let hs = 0;
    for (let dy = -1; dy <= 1; dy++)
      for (let dx = -1; dx <= 1; dx++) {
        const nx = tx + dx;
        const ny = ty + dy;
        if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
        const list = this.hillMounds[ny * w + nx] as Mound[];
        for (let m = 0; m < list.length; m++) {
          const md = list[m] as Mound;
          const ddx = u - md.x;
          const ddy = v - md.y;
          const d2 = (ddx * ddx + ddy * ddy) / (md.r * md.r);
          if (d2 < 1) hs += md.a * (1 - d2) * (1 - d2);
        }
      }
    hs += (vnoise(u * 3, v * 3, 33) - 0.5) * 0.08 * relief;
    // The cluster as a whole rises too, so neighbouring hills read as one swell.
    return hs * smooth(0.1, 0.45, hv) + hv * hv * 0.16 * relief;
  }

  /** Light, colour, the water's edge and the masks for one row. */
  row2(j: number): void {
    const { L, W, H, look, col, ton } = this;
    const v = (j + 0.5) / L;
    const ht = this.height;
    const ju = j > 0 ? j - 1 : 0;
    const jd = j < H - 1 ? j + 1 : H - 1;
    const relief = look.density.relief;
    const dens = look.density;
    for (let i = 0; i < W; i++) {
      const u = (i + 0.5) / L;
      const p = j * W + i;
      const o = p * 4;
      const il = i > 0 ? i - 1 : 0;
      const ir = i < W - 1 ? i + 1 : W - 1;
      const gx = (((ht[j * W + ir] as number) - (ht[j * W + il] as number)) * L) / 2;
      const gy = (((ht[jd * W + i] as number) - (ht[ju * W + i] as number)) * L) / 2;
      const mk = this.mount[p] as number;
      const hk = this.hill[p] as number;
      const lam = (gx + gy) * LIGHT * (0.85 + mk * 0.4 + hk * 0.5);
      // A soft limit on the light, so steep slopes don't burn out.
      const shade = 1 + ((0.36 + mk * 0.26 + hk * 0.2) * lam) / (1 + Math.abs(lam));
      const steep = Math.min(1, Math.sqrt(gx * gx + gy * gy) * 0.5);
      const n1 = this.n1[p] as number;
      const n3 = this.n3[p] as number;
      const n2 = vnoise(u * 3.3, v * 3.3, 52);

      // Loess, then grass in patches.
      tone(ton, look.loess, n1, n2);
      col[0] = ton[0] as number;
      col[1] = ton[1] as number;
      col[2] = ton[2] as number;
      let g = (this.grass[p] as number) + dens.grass;
      g += (this.gAdd[p] as number) * (1 - g);
      g -= (this.gCut[p] as number) * g;
      const ga = smooth(0.36, 0.64, g + (n3 - 0.5) * 0.4);
      if (ga > 0) {
        tone(ton, look.grass, this.gTone[p] as number, n2);
        mixTo(col, ton[0] as number, ton[1] as number, ton[2] as number, ga);
      }
      const ff = look.forestFloor;
      mixTo(col, ff[0], ff[1], ff[2], smooth(0.3, 0.7, this.forest[p] as number) * 0.85);
      const hv = smooth(0.2, 0.62, this.hill[p] as number);
      if (hv > 0) {
        // Hills: their own ochre earth, a little grass in drifts on the tops.
        const k = 0.94 + n2 * 0.12;
        const hc = look.hill;
        tone(ton, look.grass, n1, n2);
        const t = 0.12 + 0.22 * n3;
        mixTo(
          col,
          (hc[0] + ((ton[0] as number) - hc[0]) * t) * k,
          (hc[1] + ((ton[1] as number) - hc[1]) * t) * k,
          (hc[2] + ((ton[2] as number) - hc[2]) * t) * k,
          hv * 0.95,
        );
        // A darker foot round the edge of the hills.
        const hr = this.hill[p] as number;
        const fz = (hr - 0.3) / 0.075;
        if (fz > -3 && fz < 3) {
          const foot = Math.exp(-fz * fz) * (0.65 + 0.35 * n2);
          col[0] = (col[0] as number) * (1 - foot * 0.2);
          col[1] = (col[1] as number) * (1 - foot * 0.22);
          col[2] = (col[2] as number) * (1 - foot * 0.25);
        }
      }
      const mv = smooth(0.15, 0.6, (this.mount[p] as number) + (n3 - 0.5) * 0.35);
      if (mv > 0) this.rock(p, mv, steep, n1, n2, relief);
      const ya = smooth(0.35, 0.62, (this.yard[p] as number) + (n3 - 0.5) * 0.3);
      if (ya > 0) {
        const k = 0.96 + n2 * 0.08;
        mixTo(col, look.yard[0] * k, look.yard[1] * k, look.yard[2], ya * 0.85);
      }
      const ca = smooth(0.35, 0.62, (this.camp[p] as number) + (n3 - 0.5) * 0.3);
      if (ca > 0) {
        const k = 0.95 + n2 * 0.1;
        mixTo(col, look.camp[0] * k, look.camp[1] * k, look.camp[2], ca * 0.85);
      }
      const pa = smooth(0.42, 0.58, (this.pave[p] as number) + (n3 - 0.5) * 0.35);
      if (pa > 0) {
        const k = 0.93 + n1 * 0.1 + (n2 - 0.5) * 0.06;
        mixTo(col, look.pave[0] * k, look.pave[1] * k, look.pave[2] * k, pa);
      }
      const ba = smooth(0.3, 0.7, this.built[p] as number);
      if (ba > 0)
        mixTo(col, look.earth[0] * 0.92, look.earth[1] * 0.9, look.earth[2] * 0.86, ba * 0.6);
      if (look.snow) {
        const sn =
          smooth(0.38, 0.6, fbm(u * 1.4, v * 1.4, 57, 2) + (1 - steep) * 0.2) * (1 - steep * 0.6);
        mixTo(col, 232, 234, 236, sn * 0.85);
      }
      col[0] = (col[0] as number) * shade;
      col[1] = (col[1] as number) * shade;
      col[2] = (col[2] as number) * shade * (0.98 + (shade - 1) * 0.1);
      if (hk > 0.1) {
        // Hills in any landscape: sunlit faces toward a warm straw, shaded ones toward umber.
        const hs = smooth(0.15, 0.55, hk) * (1 - mk);
        if (lam > 0) mixTo(col, 222, 204, 148, Math.min(0.4, lam * 0.2) * hs);
        else mixTo(col, 66, 56, 38, Math.min(0.35, -lam * 0.16) * hs);
      }

      const wraw = this.wet[p] as number;
      let alpha = 1;
      let wv = wraw;
      if (wraw > 0.12) {
        wv = wraw + (vnoise(u * 4.6, v * 4.6, 61) - 0.5) * 0.17;
        if (wv > 0.3) alpha = this.edge(p, u, v, wv, n2);
      }
      this.land[o] = col[0] as number;
      this.land[o + 1] = col[1] as number;
      this.land[o + 2] = col[2] as number;
      this.land[o + 3] = alpha * 255;
      this.river(p, o, u, v, wv, n1);
      this.grassMask[o + 3] =
        ga * (1 - mv) * (1 - pa) * (1 - ya * 0.6) * (1 - ca * 0.7) * alpha * 255;
      this.rockMask[o + 3] = mv * alpha * 255;
      this.paveMask[o + 3] = pa * alpha * 255;
      this.dirtMask[o + 3] = (alpha > 0.995 ? 1 : alpha * 0.25) * (1 - pa * 0.5) * 255;
      this.grass[p] = ga;
    }
  }

  /** Bare rock above, scrub on the lower slopes. */
  rock(p: number, mv: number, steep: number, n1: number, n2: number, relief: number): void {
    const { look, col, ton } = this;
    const rr = this.ridge[p] as number;
    const rt = Math.min(1, rr * 1.3 - steep * 0.25 + 0.1);
    const a = look.rock[1];
    const b = look.rock[0];
    let cr = a[0] + (b[0] - a[0]) * rt;
    let cg = a[1] + (b[1] - a[1]) * rt;
    let cb = a[2] + (b[2] - a[2]) * rt;
    const low = 0.5 * smooth(0.9, 0.2, (this.height[p] as number) / Math.max(0.4, relief));
    tone(ton, look.grass, n1, n2);
    cr += ((ton[0] as number) * 0.8 - cr) * low;
    cg += ((ton[1] as number) * 0.8 - cg) * low;
    cb += ((ton[2] as number) * 0.8 - cb) * low;
    mixTo(col, cr, cg, cb, mv);
  }

  /** The water's edge: a damp bank, foam at the line, shallows fading into the river. Returns alpha. */
  edge(p: number, u: number, v: number, wv: number, n2: number): number {
    const { look, col } = this;
    const fd = this.ford[p] as number;
    let alpha = 1;
    const damp = smooth(0.32, 0.5, wv);
    mixTo(
      col,
      (col[0] as number) * 0.72,
      (col[1] as number) * 0.7,
      (col[2] as number) * 0.66,
      damp * 0.55,
    );
    if (wv > 0.5) {
      const t = smooth(0.5, 0.6, wv);
      const sh = look.frozen && fd > 0.5 ? look.ice : look.water.shallow;
      mixTo(col, sh[0], sh[1], sh[2], 0.6 + t * 0.4);
      alpha = (1 - t) * 0.6;
      if (fd > 0.02) {
        const fa = smooth(0.2, 0.8, fd) * smooth(0.5, 0.6, wv);
        if (look.frozen) {
          const ic = look.ice;
          const crack = 0.92 + vnoise(u * 6, v * 6, 63) * 0.1;
          mixTo(col, ic[0] * crack, ic[1] * crack, ic[2] * crack, fa);
          alpha = Math.max(alpha, fa * 0.93);
        } else {
          const bc = look.bed;
          const k = 0.9 + n2 * 0.2;
          mixTo(col, bc[0] * k, bc[1] * k, bc[2] * k, fa);
          alpha = Math.max(alpha, fa * (0.22 + vnoise(u * 2.5, v * 2.5, 64) * 0.2));
        }
      }
    }
    if (!(look.frozen && fd > 0.6)) {
      const z = (wv - 0.505) / 0.022;
      if (z > -3 && z < 3) {
        const fz = Math.exp(-z * z) * (0.3 + 0.7 * vnoise(u * 9, v * 9, 62));
        mixTo(col, 226, 230, 222, fz * 0.75);
        alpha = Math.max(alpha, fz * 0.85);
      }
    }
    return alpha;
  }

  /** The river below: deeper and darker away from the banks. */
  river(p: number, o: number, u: number, v: number, wv: number, n1: number): void {
    const wl = this.look.water;
    let wr = wl.shallow[0];
    let wg = wl.shallow[1];
    let wb = wl.shallow[2];
    if (wv > 0.45) {
      const dp = smooth(0.25, 0.95, this.deep[p] as number) * smooth(0.5, 0.9, wv);
      const m = Math.min(1, dp * 1.6);
      wr += (wl.mid[0] - wr) * m;
      wg += (wl.mid[1] - wg) * m;
      wb += (wl.mid[2] - wb) * m;
      const dd = smooth(0.45, 1, dp);
      wr += (wl.deep[0] - wr) * dd;
      wg += (wl.deep[1] - wg) * dd;
      wb += (wl.deep[2] - wb) * dd;
      const st = vnoise(u * 2.2 + v * 0.6, v * 2.2 - u * 0.4, 66);
      const wk = 0.94 + n1 * 0.08 + (st - 0.5) * 0.07;
      wr *= wk;
      wg *= wk;
      wb *= wk;
    }
    this.water[o] = wr;
    this.water[o + 1] = wg;
    this.water[o + 2] = wb;
    this.water[o + 3] = 255;
  }
}

export function computeGround(plan: Plan, look: Look, L: number): GroundField {
  const b = new Builder(plan, look, L);
  for (let cj = 0; cj < b.CH; cj++) b.coarseRow(cj);
  for (let j = 0; j < b.H; j++) b.row1(j);
  for (let j = 0; j < b.H; j++) b.row2(j);
  return {
    L,
    W: b.W,
    H: b.H,
    land: b.land,
    water: b.water,
    grassMask: b.grassMask,
    rockMask: b.rockMask,
    paveMask: b.paveMask,
    dirtMask: b.dirtMask,
    grass: b.grass,
    forest: b.forest,
    wet: b.wet,
    hill: b.hill,
    mount: b.mount,
    height: b.height,
    built: b.built,
  };
}

/** A field value at a point in tile units (bilinear). */
export function sample(f: GroundField, a: Float32Array, u: number, v: number): number {
  const x = u * f.L - 0.5;
  const y = v * f.L - 0.5;
  const x0 = Math.max(0, Math.min(f.W - 1, Math.floor(x)));
  const y0 = Math.max(0, Math.min(f.H - 1, Math.floor(y)));
  const x1 = Math.min(f.W - 1, x0 + 1);
  const y1 = Math.min(f.H - 1, y0 + 1);
  const fx = Math.max(0, Math.min(1, x - x0));
  const fy = Math.max(0, Math.min(1, y - y0));
  const a00 = a[y0 * f.W + x0] as number;
  const a10 = a[y0 * f.W + x1] as number;
  const a01 = a[y1 * f.W + x0] as number;
  const a11 = a[y1 * f.W + x1] as number;
  return a00 + (a10 - a00) * fx + (a01 - a00) * fy + (a00 - a10 - a01 + a11) * fx * fy;
}

/** Land alpha at a point (0 over open water). */
export function landAlpha(f: GroundField, u: number, v: number): number {
  const x = Math.max(0, Math.min(f.W - 1, Math.floor(u * f.L)));
  const y = Math.max(0, Math.min(f.H - 1, Math.floor(v * f.L)));
  return (f.land[(y * f.W + x) * 4 + 3] as number) / 255;
}
