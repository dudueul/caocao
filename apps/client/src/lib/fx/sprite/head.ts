/**
 * The head (ADR 0073): big, round and chunky, so the headgear, the beard and
 * the accent read at tile size. A skull with a jaw by face shape, hair combed
 * up to a topknot, eyes and brows by kind, the beard library of the portraits
 * drawn as masses (the Han moustache with hanging ends, 關羽's long beard,
 * 張飛's tiger whiskers, 曹操's slim tail), and the marks that show at this
 * size (夏侯惇's patch, scars, an earring). Every point sits on a small head
 * model turned with the head, so the front, back and side frames agree.
 */
import { GOLD } from "../portrait/palette.ts";
import type { BeardKind, FaceShape } from "../portrait/types.ts";
import {
  alpha,
  blob,
  capsule,
  cel,
  circle,
  curve,
  dot,
  ellipse,
  type G,
  hullShape,
  INK,
  inside,
  LW,
  LW2,
  line,
  mix,
  outline,
  polygon,
  type Shape,
} from "./draw.ts";
import { type Pt, rotX, rotY, rotZ, type V3 } from "./geom.ts";
import { hatBack, hatFront } from "./headgear.ts";
import type { SpriteLook } from "./look.ts";
import type { Proj } from "./person.ts";
import { BODY, type Joints } from "./rig.ts";

/** A head turned in body space: local u (toward the left ear), v (down), w (forward). */
export class HeadSpace {
  readonly R: number;
  constructor(
    readonly P: Proj,
    readonly c: V3,
    readonly rot: (v: V3) => V3,
    R: number,
    readonly look: SpriteLook,
    readonly J: Joints,
    readonly ks = 0.2,
  ) {
    this.R = R;
  }

  vec(u: number, v: number, w: number): V3 {
    return this.rot([u, v, w]);
  }

  pt(u: number, v: number, w: number): Pt {
    return this.P.s(this.c, this.rot([u, v, w]), this.ks);
  }

  /** How far a local point lies toward the viewer, from the head's centre. */
  dep(u: number, v: number, w: number): number {
    return this.P.dv(this.rot([u, v, w]));
  }

  /** A point on a sphere of radius r at azimuth th (0 = the face, + toward the left ear) and height v. */
  sph(th: number, v: number, r: number): [number, number, number] {
    const h = Math.sqrt(Math.max(0, r * r - v * v));
    return [h * Math.sin(th), v, h * Math.cos(th)];
  }

  /** The azimuth that faces the viewer. */
  facingTh(): number {
    const du = this.P.dv(this.rot([1, 0, 0]));
    const dw = this.P.dv(this.rot([0, 0, 1]));
    return Math.atan2(du, dw);
  }

  /** How squarely the face looks at the viewer (1 front, 0 profile, −1 away). */
  faceOn(): number {
    return this.P.dv(this.rot([0, 0, 1]));
  }

  circle(u: number, v: number, w: number, r: number): Shape {
    const [x, y] = this.pt(u, v, w);
    return circle(x, y, r);
  }
}

/** Jaw spheres by face shape: the chin's centre and radius, the jaw corners' (in head radii). */
const JAWS: Record<
  FaceShape,
  { chin: [number, number, number, number]; jaw: [number, number, number, number] }
> = {
  oval: { chin: [0, 0.6, 0.32, 0.44], jaw: [0.5, 0.4, 0.2, 0.44] },
  long: { chin: [0, 0.72, 0.3, 0.4], jaw: [0.47, 0.48, 0.18, 0.42] },
  square: { chin: [0, 0.6, 0.3, 0.46], jaw: [0.6, 0.5, 0.12, 0.42] },
  round: { chin: [0, 0.5, 0.26, 0.55], jaw: [0.58, 0.36, 0.12, 0.52] },
  tri: { chin: [0, 0.7, 0.33, 0.32], jaw: [0.44, 0.42, 0.2, 0.4] },
  jowly: { chin: [0, 0.6, 0.3, 0.48], jaw: [0.66, 0.52, 0.05, 0.46] },
  gaunt: { chin: [0, 0.7, 0.3, 0.37], jaw: [0.44, 0.48, 0.18, 0.37] },
};

function circlePts(c: Pt, r: number, n = 14): Pt[] {
  const out: Pt[] = [];
  for (let i = 0; i < n; i++) {
    const a = (i / n) * Math.PI * 2;
    out.push([c[0] + Math.cos(a) * r, c[1] + Math.sin(a) * r]);
  }
  return out;
}

/** The head's outline: the skull and the jaw. */
export function headShape(H: HeadSpace): Shape {
  const R = H.R;
  const L = H.look;
  const J = JAWS[L.face];
  const w = L.faceW;
  const pts: Pt[] = circlePts(H.pt(0, 0, 0), R, 24);
  const [, cy, cz, cr] = J.chin;
  pts.push(...circlePts(H.pt(0, cy * R, cz * R), cr * R));
  const [jx, jy, jz, jr] = J.jaw;
  for (const s of [-1, 1]) pts.push(...circlePts(H.pt(s * jx * R * w, jy * R, jz * R), jr * R * w));
  return hullShape(pts);
}

/** Where the face's surface is, at a height (for the mouth and the chin). */
function faceDepth(L: SpriteLook, v: number): number {
  const [, cy, cz, cr] = JAWS[L.face].chin;
  const dv = v - cy;
  return cz + Math.sqrt(Math.max(0, cr * cr - dv * dv));
}

// ---------------------------------------------------------------- hair

/** The hairline: low over the brow, past the temples, under the ears, low at the nape. */
function hairline(th: number, R: number): number {
  const a = Math.abs(th);
  const s = (x: number, a0: number, a1: number) => {
    const t = Math.min(1, Math.max(0, (x - a0) / (a1 - a0)));
    return t * t * (3 - 2 * t);
  };
  return R * (-0.36 + 0.5 * s(a, 0.55, 1.6) + 0.9 * s(a, 1.65, 2.35));
}

/**
 * The hair's region, as seen: the hairline where it faces the viewer, closed
 * along the head's outline on the crown's side (or the whole loop, seen from
 * above).
 */
function hairShape(H: HeadSpace, grow = 1.05): Shape {
  const R = H.R * grow;
  const n = 72;
  const pts: Pt[] = [];
  const deps: number[] = [];
  for (let i = 0; i < n; i++) {
    const th = (i / n) * Math.PI * 2;
    const v = Math.min(R * 0.98, hairline(th, H.R));
    const p = H.sph(th, v, R);
    pts.push(H.pt(...p));
    deps.push(H.dep(...p));
  }
  if (deps.every((d) => d > 0)) return polygon(pts);
  // The longest run of the hairline that faces the viewer, with its ends where it turns away.
  let best: Pt[] = [];
  for (let s = 0; s < n; s++) {
    if (!((deps[s] as number) > 0 && (deps[(s + n - 1) % n] as number) <= 0)) continue;
    const run: Pt[] = [];
    const edge = (a: number, b: number): Pt => {
      const da = deps[a] as number;
      const db = deps[b] as number;
      const t = da / (da - db || 1);
      const pa = pts[a] as Pt;
      const pb = pts[b] as Pt;
      return [pa[0] + (pb[0] - pa[0]) * t, pa[1] + (pb[1] - pa[1]) * t];
    };
    run.push(edge((s + n - 1) % n, s));
    let i = s;
    while ((deps[i % n] as number) > 0 && run.length <= n) {
      run.push(pts[i % n] as Pt);
      i++;
    }
    run.push(edge((i - 1) % n, i % n));
    if (run.length > best.length) best = run;
  }
  const c = H.pt(0, 0, 0);
  const crown = H.pt(0, -R, 0);
  if (best.length < 2) {
    // The hairline is all out of sight: the hair is all we see, or none of it.
    return H.dep(0, -R, 0) > 0 || H.dep(0, 0, -R) > 0
      ? circle(c[0], c[1], R * 1.1)
      : polygon([c, c, c]);
  }
  // Round the outline from the run's end back to its start, through the crown's side.
  const ang = (p: Pt) => Math.atan2(p[1] - c[1], p[0] - c[0]);
  const a0 = ang(best[best.length - 1] as Pt);
  const a1 = ang(best[0] as Pt);
  const ac = ang(crown);
  const norm = (x: number) => ((x % (Math.PI * 2)) + Math.PI * 2) % (Math.PI * 2);
  const ccw = norm(ac - a0) < norm(a1 - a0);
  const sweep = ccw ? norm(a1 - a0) : -norm(a0 - a1);
  const arc: Pt[] = [];
  for (let i = 1; i < 24; i++) {
    const a = a0 + (sweep * i) / 24;
    arc.push([c[0] + Math.cos(a) * R * 1.08, c[1] + Math.sin(a) * R * 1.08]);
  }
  return polygon([...best, ...arc]);
}

const NO_HAIR = new Set(["shaved"]);
/** Headgear that leaves the topknot bare. */
const BARE_KNOT = new Set(["band", "knot", "wrap", "crownSmall", "crownJade", "crownTails"]);

function hair(g: G, H: HeadSpace, skull: Shape): void {
  const L = H.look;
  if (NO_HAIR.has(L.hat)) return;
  const [base, dark, light] = L.hair;
  const hs = hairShape(H);
  g.save();
  g.clip(skull.p);
  cel(g, hs, base, dark, 0.025, light, 0);
  // A fine line where the hair meets the face, and combed strands toward the topknot.
  inside(g, hs, () => {
    const top = H.pt(0, -H.R * 1.05, -H.R * 0.1);
    for (const th of [-1.2, -0.6, 0, 0.6, 1.2]) {
      const [u, v, w] = H.sph(H.facingTh() + th, hairline(H.facingTh() + th, H.R), H.R * 1.05);
      if (H.dep(u, v, w) < 0) continue;
      const a = H.pt(u, v, w);
      curve(
        g,
        alpha(light, 0.5),
        LW2 * 0.8,
        a[0],
        a[1],
        (a[0] + top[0]) / 2 + 0.01,
        (a[1] + top[1]) / 2,
        top[0],
        top[1],
      );
    }
  });
  g.lineWidth = LW2;
  g.strokeStyle = INK;
  g.stroke(hs.p);
  g.restore();
}

/** The topknot (髻) on the crown, tied with a cord or cloth. */
export function topknot(g: G, H: HeadSpace, tie: string | null): void {
  const R = H.R;
  const [base, dark, light] = H.look.hair;
  const k = H.circle(0, -R * 1.0, -R * 0.12, R * 0.3);
  cel(g, k, base, dark, 0.015, light);
  if (tie) {
    const a = H.pt(-R * 0.28, -R * 0.86, -R * 0.12);
    const b = H.pt(R * 0.28, -R * 0.86, -R * 0.12);
    line(g, INK, 0.04, a[0], a[1], b[0], b[1]);
    line(g, tie, 0.026, a[0], a[1], b[0], b[1]);
  }
}

// ---------------------------------------------------------------- face

function eye(g: G, H: HeadSpace, s: -1 | 1): void {
  const L = H.look;
  const R = H.R;
  const J = H.J;
  const th = s * 0.42;
  const [u, v, w] = H.sph(th, R * 0.2, R);
  const on = H.dep(u, v, w) / R;
  if (on < 0.18) return;
  if (s > 0 && L.marks.has("patch")) {
    // 夏侯惇 lost his left eye to an arrow: a patch, its cord round the head.
    const [x, y] = H.pt(u, v, w);
    cel(
      g,
      ellipse(x, y, R * 0.17 * Math.max(0.4, on), R * 0.15),
      "#2a2420",
      "#141010",
      0.008,
      "#4a4038",
      LW2,
    );
    const a = H.pt(...H.sph(th + 0.9, R * 0.02, R * 1.02));
    const b = H.pt(...H.sph(th - 0.5, R * -0.05, R * 1.02));
    line(g, "#2a2420", 0.012, x, y, a[0], a[1]);
    line(g, "#2a2420", 0.012, x, y, b[0], b[1]);
    return;
  }
  const [x, y] = H.pt(u, v, w);
  const sx = Math.max(0.35, on);
  let rx = R * 0.12 * sx;
  let ry = R * 0.15;
  const kind = L.eye;
  if (kind === "narrow" || kind === "phoenix") ry *= 0.62;
  else if (kind === "almond" || kind === "gentle") ry *= 0.85;
  else if (kind === "large") {
    rx *= 1.12;
    ry *= 1.12;
  } else if (kind === "small") {
    rx *= 0.78;
    ry *= 0.8;
  }
  const state = J.eyes;
  if (state === "shut" || state === "squeeze") {
    const d = state === "squeeze" ? 0.5 : 0;
    // Shut: a curved lid line; squeezed: a chevron.
    if (state === "squeeze")
      line(
        g,
        INK,
        0.018,
        x - rx * 1.2 * s,
        y - ry * 0.6,
        x + rx * 0.6 * s,
        y + d * 0,
        x - rx * 1.2 * s,
        y + ry * 0.6,
      );
    else curve(g, INK, 0.017, x - rx * 1.2, y, x, y + ry * 0.7, x + rx * 1.2, y);
    return;
  }
  if (state === "wide") ry *= 1.15;
  cel(g, ellipse(x, y, rx, ry), "#1c1214", undefined, 0, undefined, 0);
  if (kind !== "narrow" && kind !== "small")
    dot(g, "#5a3a2a", x + rx * 0.05, y + ry * 0.3, Math.min(rx, ry) * 0.55);
  dot(g, "#ffffff", x + rx * 0.35, y - ry * 0.38, Math.min(rx, ry) * 0.42);
  // A heavy or hooded lid, a half-shut weak eye.
  const lid = state === "low" ? 0.55 : kind === "heavy" || kind === "hooded" ? 0.4 : 0;
  if (lid > 0) {
    const L2 = polygon([
      [x - rx * 1.4, y - ry * 1.4],
      [x + rx * 1.4, y - ry * 1.4],
      [x + rx * 1.4, y - ry + ry * 2 * lid],
      [x - rx * 1.4, y - ry + ry * 2 * lid],
    ]);
    inside(g, ellipse(x, y, rx * 1.05, ry * 1.05), () => {
      g.fillStyle = L.skin[1];
      g.fill(L2.p);
    });
    line(g, INK, 0.012, x - rx * 1.1, y - ry + ry * 2 * lid, x + rx * 1.1, y - ry + ry * 2 * lid);
  }
  if (kind === "phoenix")
    line(g, INK, 0.012, x + rx * s * 0.6, y - ry * 0.3, x + rx * s * 1.5, y - ry * 1.1);
}

function brow(g: G, H: HeadSpace, s: -1 | 1): void {
  const L = H.look;
  const R = H.R;
  const kind = L.brow;
  const frown = L.marks.has("frown") || H.J.mouth === "shout";
  const low = kind === "low" ? 0.05 : 0;
  const inner = H.sph(
    s * 0.15,
    R * (-0.08 + low + (kind === "knit" || frown ? 0.05 : 0)),
    R * 1.01,
  );
  const outer = H.sph(
    s * (kind === "sparse" ? 0.5 : 0.66),
    R * (-0.1 + low + (kind === "sword" ? -0.06 : kind === "knit" ? -0.03 : 0.02)),
    R * 1.01,
  );
  if (H.dep(...inner) < 0 && H.dep(...outer) < 0) return;
  const a = H.pt(...inner);
  const b = H.pt(...outer);
  const thick =
    kind === "bushy"
      ? 0.032
      : kind === "thin" || kind === "sparse"
        ? 0.013
        : kind === "knit"
          ? 0.026
          : 0.021;
  const col = mix(L.hair[0], "#000000", 0.2);
  if (kind === "curved") {
    const m = H.pt(...H.sph(s * 0.4, R * -0.16, R * 1.02));
    curve(g, col, thick * H.J.scale ** 0.5, a[0], a[1], m[0], m[1], b[0], b[1]);
  } else line(g, col, thick * H.J.scale ** 0.5, a[0], a[1], b[0], b[1]);
}

function nose(g: G, H: HeadSpace): void {
  const R = H.R;
  const on = H.faceOn();
  if (on < -0.1) return;
  const tip = H.pt(0, R * 0.42, R * 1.12);
  const l = H.pt(...H.sph(-0.13, R * 0.45, R * 0.98));
  const r = H.pt(...H.sph(0.13, R * 0.45, R * 0.98));
  const top = H.pt(...H.sph(0, R * 0.2, R));
  const col = H.look.marks.has("rednose") ? "#c2584a" : H.look.skin[0];
  if (on > 0.85) {
    // Seen from the front: only the shadow under the tip.
    curve(g, alpha(H.look.skin[1], 0.95), 0.016, l[0], l[1], tip[0], tip[1] + 0.012, r[0], r[1]);
    return;
  }
  cel(
    g,
    polygon([top, tip, on > 0.3 ? l : r, on > 0.3 ? r : l]),
    col,
    H.look.skin[1],
    0.008,
    undefined,
    LW2,
  );
}

function mouth(g: G, H: HeadSpace): void {
  const L = H.look;
  const R = H.R;
  const v = R * 0.66;
  const w = faceDepth(L, v / R) * R;
  if (H.dep(0, v, w) < 0) return;
  const on = Math.max(0.3, H.faceOn());
  const [x, y] = H.pt(0, v, w);
  const open = H.J.mouth !== "shut" || L.marks.has("roar");
  if (open) {
    const big = H.J.mouth === "shout" || L.marks.has("roar");
    const m = ellipse(x, y + R * 0.02, R * (big ? 0.16 : 0.11) * on, R * (big ? 0.14 : 0.09));
    cel(g, m, "#5c2320", undefined, 0, undefined, LW2);
    inside(g, m, () => dot(g, "#b34a3e", x, y + R * 0.1, R * 0.08));
    return;
  }
  const hw = R * 0.13 * on;
  const smirk = L.marks.has("smirk") ? R * 0.04 : 0;
  curve(g, "#6a2a22", 0.014, x - hw, y - smirk * 0.3, x, y + R * 0.03, x + hw, y - smirk);
}

/** A cheek's flush, so faces read as faces at small size. */
function cheeks(g: G, H: HeadSpace): void {
  const R = H.R;
  for (const s of [-1, 1] as const) {
    const p = H.sph(s * 0.62, R * 0.42, R);
    if (H.dep(...p) < R * 0.3) continue;
    const [x, y] = H.pt(...p);
    g.fillStyle = "rgba(214,110,90,0.28)";
    g.beginPath();
    g.ellipse(x, y, R * 0.13 * Math.max(0.4, H.dep(...p) / R), R * 0.07, 0, 0, Math.PI * 2);
    g.fill();
  }
}

function ear(g: G, H: HeadSpace, s: -1 | 1, front: boolean): void {
  const R = H.R;
  const p: [number, number, number] = [s * R * 0.97, R * 0.2, -R * 0.06];
  const d = H.dep(...p);
  if (front !== d > -R * 0.15) return;
  if (d < -R * 0.75) return;
  const [x, y] = H.pt(...p);
  const big = H.look.marks.has("ears");
  const e = ellipse(x, y + (big ? R * 0.08 : 0), R * 0.14, R * (big ? 0.3 : 0.2));
  cel(g, e, H.look.skin[0], H.look.skin[1], 0.008, undefined, LW2);
  if (H.look.marks.has("earring") && Math.abs(d) < R * 0.8) {
    const ring = ellipse(x, y + R * 0.26, R * 0.07, R * 0.09);
    g.strokeStyle = INK;
    g.lineWidth = 0.02;
    g.stroke(ring.p);
    g.strokeStyle = GOLD;
    g.lineWidth = 0.011;
    g.stroke(ring.p);
  }
}

// ---------------------------------------------------------------- beards

/** The face's frame at the mouth: local (across, down) in head radii to the sheet. */
function faceFrame(H: HeadSpace, back = false): (a: number, d: number) => Pt {
  const R = H.R;
  const v = R * 0.66;
  const w = faceDepth(H.look, 0.66) * R;
  const [ox, oy] = H.pt(0, v, w);
  const e1 = H.pt(R, v, w);
  const e2 = H.pt(0, v + R, w);
  const ux = e1[0] - ox;
  const uy = e1[1] - oy;
  const vx = e2[0] - ox;
  const vy = e2[1] - oy;
  const s = back ? -1 : 1;
  return (a, d) => [ox + ux * a * s + vx * d, oy + uy * a * s + vy * d];
}

/** A beard's mass, as an outline in the face's frame (head radii; the mouth at 0,0). */
function beardOutline(kind: BeardKind): Pt[] | null {
  switch (kind) {
    case "goat":
      return [
        [-0.1, 0.12],
        [0.1, 0.12],
        [0.06, 0.38],
        [0, 0.48],
        [-0.06, 0.38],
      ];
    case "slim":
      return [
        [-0.07, 0.14],
        [0.07, 0.14],
        [0.04, 0.5],
        [0.0, 0.78],
        [-0.04, 0.5],
      ];
    case "three":
      return [
        [-0.14, 0.12],
        [0.14, 0.12],
        [0.11, 0.42],
        [0, 0.66],
        [-0.11, 0.42],
      ];
    case "spade":
      return [
        [-0.5, -0.32],
        [-0.42, 0.24],
        [-0.3, 0.62],
        [0.3, 0.62],
        [0.42, 0.24],
        [0.5, -0.32],
        [0.3, 0.1],
        [-0.3, 0.1],
      ];
    case "full":
    case "wild":
      return [
        [-0.58, -0.4],
        [-0.5, 0.25],
        [-0.22, 0.66],
        [0, 0.82],
        [0.22, 0.66],
        [0.5, 0.25],
        [0.58, -0.4],
        [0.36, 0.08],
        [0, 0.18],
        [-0.36, 0.08],
      ];
    case "bag":
    case "whiteLong":
      return [
        [-0.5, -0.36],
        [-0.44, 0.3],
        [-0.3, 0.9],
        [-0.16, 1.45],
        [0, 1.72],
        [0.16, 1.45],
        [0.3, 0.9],
        [0.44, 0.3],
        [0.5, -0.36],
        [0.3, 0.08],
        [0, 0.16],
        [-0.3, 0.08],
      ];
    case "fork":
      return [
        [-0.5, -0.3],
        [-0.42, 0.3],
        [-0.22, 0.72],
        [-0.12, 0.98],
        [0, 0.66],
        [0.12, 0.98],
        [0.22, 0.72],
        [0.42, 0.3],
        [0.5, -0.3],
        [0.3, 0.1],
        [-0.3, 0.1],
      ];
    case "braid":
      return [
        [-0.3, -0.1],
        [-0.2, 0.3],
        [-0.07, 0.4],
        [-0.06, 1.0],
        [0.06, 1.0],
        [0.07, 0.4],
        [0.2, 0.3],
        [0.3, -0.1],
        [0, 0.14],
      ];
    case "chops":
      return null;
    case "tiger":
      return [
        [-0.62, -0.4],
        [-0.56, 0.2],
        [-0.3, 0.58],
        [0, 0.66],
        [0.3, 0.58],
        [0.56, 0.2],
        [0.62, -0.4],
        [0.36, 0.06],
        [-0.36, 0.06],
      ];
    default:
      return null;
  }
}

function beard(g: G, H: HeadSpace, behind: boolean): void {
  const L = H.look;
  const kind = L.beard;
  const on = H.faceOn();
  if (behind && on > -0.2) return;
  if (!behind && on < -0.2) return;
  const F = faceFrame(H, behind);
  const _R = H.R;
  const [base, dark, light] = L.hair;
  const wild = kind === "wild" || kind === "tiger";
  if (kind === "stub" && !behind) {
    const s = polygon([
      F(-0.5, -0.35),
      F(-0.4, 0.25),
      F(0, 0.42),
      F(0.4, 0.25),
      F(0.5, -0.35),
      F(0, -0.02),
    ]);
    inside(g, s, () => fill(g, s, alpha(L.hair[0], 0.28)));
  }
  const out = beardOutline(kind);
  if (out) {
    const pts = out.map(([a, d]) => F(a, d));
    const shape = wild ? polygon(spiky(pts, F)) : blob(pts);
    cel(g, shape, base, dark, 0.02, light);
    if (!behind)
      inside(g, shape, () => {
        // Strands.
        for (let i = -2; i <= 2; i++) {
          const a = F(i * 0.12, 0.25);
          const b = F(i * 0.16, kind === "bag" || kind === "whiteLong" ? 1.4 : 0.6);
          line(g, alpha(dark, 0.7), LW2 * 0.8, a[0], a[1], b[0], b[1]);
        }
      });
    if (kind === "braid" && !behind)
      for (const d of [0.55, 0.7, 0.85]) {
        const a = F(-0.07, d);
        const b = F(0.07, d + 0.04);
        line(g, INK, LW2, a[0], a[1], b[0], b[1]);
      }
  }
  if (behind) return;
  if (kind === "chops")
    for (const s of [-1, 1]) {
      const c = blob([F(s * 0.62, -0.42), F(s * 0.6, 0.1), F(s * 0.4, 0.24), F(s * 0.42, -0.2)]);
      cel(g, c, base, dark, 0.012, light, LW2);
    }
  // Moustaches: the Han grammar, ends hanging down; bristles for the wild.
  const stache = kind !== "none" && kind !== "stub" && kind !== "goat" && kind !== "braid";
  if (stache) {
    const hang =
      kind === "tong" ||
      kind === "three" ||
      kind === "slim" ||
      kind === "bag" ||
      kind === "whiteLong"
        ? 0.32
        : 0.12;
    for (const s of [-1, 1]) {
      const a = F(s * 0.04, -0.13);
      const m = F(s * 0.24, -0.1);
      const b = F(s * (kind === "tiger" ? 0.5 : 0.26), kind === "tiger" ? -0.2 : -0.1 + hang);
      g.lineCap = "round";
      curve(g, INK, 0.036 * H.J.scale, a[0], a[1], m[0], m[1], b[0], b[1]);
      curve(g, base, 0.022 * H.J.scale, a[0], a[1], m[0], m[1], b[0], b[1]);
    }
  }
  if (kind === "tong") {
    const tuft = polygon([F(-0.05, 0.13), F(0.05, 0.13), F(0, 0.34)]);
    cel(g, tuft, base, dark, 0.006, undefined, LW2);
  }
}

function fill(g: G, s: Shape, color: string): void {
  g.fillStyle = color;
  g.fill(s.p);
}

/** Bristles out from a beard's outline. */
function spiky(pts: Pt[], F: (a: number, d: number) => Pt): Pt[] {
  const out: Pt[] = [];
  const c = F(0, 0.3);
  for (let i = 0; i < pts.length; i++) {
    const a = pts[i] as Pt;
    const b = pts[(i + 1) % pts.length] as Pt;
    out.push(a);
    const mx = (a[0] + b[0]) / 2;
    const my = (a[1] + b[1]) / 2;
    const dx = mx - c[0];
    const dy = my - c[1];
    const l = Math.hypot(dx, dy) || 1;
    out.push([mx + (dx / l) * 0.02, my + (dy / l) * 0.02]);
  }
  return out;
}

/** Scars and moles that show at this size. */
function marks(g: G, H: HeadSpace): void {
  const L = H.look;
  const R = H.R;
  if (H.faceOn() < 0.2) return;
  if (L.marks.has("scar") || L.marks.has("scars")) {
    const a = H.pt(...H.sph(-0.55, R * 0.05, R * 1.01));
    const b = H.pt(...H.sph(-0.3, R * 0.4, R * 1.01));
    line(g, "#9c3a30", 0.012, a[0], a[1], b[0], b[1]);
  }
  if (L.marks.has("mole")) {
    const [x, y] = H.pt(...H.sph(0.3, R * 0.5, R));
    dot(g, "#3a2420", x, y, R * 0.04);
  }
}

// ---------------------------------------------------------------- the head

/** The head's turn in body space for a pose: the torso's, then the head's own. */
export function headSpace(L: SpriteLook, J: Joints, P: Proj, sideTurn: number): HeadSpace {
  const R = BODY.headR * J.scale;
  const rot = (v: V3): V3 =>
    rotY(
      rotX(rotZ(rotY(rotX(rotZ(v, J.hr), J.hp), J.hy + sideTurn), J.tilt), J.lean * 0.5),
      J.twist,
    );
  return new HeadSpace(P, J.head, rot, R, L, J);
}

export function drawHead(g: G, H: HeadSpace): void {
  const L = H.look;
  const R = H.R;
  hatBack(g, H);
  beard(g, H, true);
  for (const s of [-1, 1] as const) ear(g, H, s, false);
  // The neck, under the jaw.
  const neck = H.P.p(H.J.neck);
  const jaw = H.pt(0, R * 0.7, 0);
  cel(g, capsule(neck, jaw, R * 0.32, R * 0.36), L.skin[1], undefined, 0, undefined, LW);
  const skull = headShape(H);
  const shaved = L.hat === "shaved";
  cel(g, skull, L.skin[0], L.skin[1], 0.03, L.skin[2]);
  if (H.faceOn() > -0.25) {
    cheeks(g, H);
    for (const s of [-1, 1] as const) {
      eye(g, H, s);
      brow(g, H, s);
    }
    nose(g, H);
    mouth(g, H);
    marks(g, H);
  }
  hair(g, H, skull);
  if (shaved && L.marks.has("locks"))
    for (const s of [-1, 1] as const) {
      const p = H.sph(s * 1.45, R * 0.1, R * 1.02);
      if (H.dep(...p) < -R * 0.3) continue;
      const a = H.pt(...p);
      const b = H.pt(p[0] * 1.05, R * 0.95, p[2]);
      cel(g, capsule(a, b, R * 0.09, R * 0.06), L.hair[0], L.hair[1], 0.008, undefined, LW2);
    }
  outline(g, skull, INK, LW);
  for (const s of [-1, 1] as const) ear(g, H, s, true);
  beard(g, H, false);
  if (BARE_KNOT.has(L.hat)) topknot(g, H, null);
  hatFront(g, H);
}
