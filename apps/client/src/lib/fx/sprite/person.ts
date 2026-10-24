/**
 * A person, painted in one key pose (ADR 0073): legs in trousers, leg wraps
 * and cloth shoes; the cross-collared robe in the side's dye, belted, knee
 * length for soldiers and ankle length with wide sleeves for officials; the
 * class's armour, vest or sash over it; arms, hands and the head. Dress
 * follows the portraits (ADR 0046, ADR 0070). Each part becomes a draw item
 * with a depth, so the near arm passes in front of the chest and the far arm
 * behind it, whichever way the figure faces.
 */
import {
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  GOLD,
  TIGER,
  WHITE_CLOTH,
} from "../portrait/palette.ts";
import {
  alpha,
  capsule,
  cel,
  circle,
  dot,
  type G,
  hullShape,
  INK,
  inside,
  LW,
  LW2,
  line,
  mix,
  polygon,
  type Shape,
  type Tri,
  trace,
} from "./draw.ts";
import { add, mix3, mul, type Pt, type V3, type View } from "./geom.ts";
import type { SpriteLook } from "./look.ts";
import { BODY, type Joints } from "./rig.ts";

export const TROUSERS: Tri = ["#b9aa86", "#8e805f", "#d3c7a6"];
export const WRAP: Tri = ["#7d6e50", "#5a4e38", "#9c8c6c"];
export const SHOE: Tri = ["#2f2622", "#1a1412", "#5a4a42"];
const FLEECE: Tri = ["#cdb88f", "#97815a", "#efe2c2"];
const FUR: Tri = ["#5a4636", "#33271d", "#8a7360"];

/** Something to draw, at a depth toward the viewer. */
export interface Item {
  d: number;
  f: (g: G) => void;
}

/** Projection for one figure (or one of an engine's crew, moved by `origin`): body space to the sheet. */
export class Proj {
  constructor(
    readonly V: View,
    readonly origin: V3 = [0, 0, 0],
    /** How much surface rings bow with depth (less than limbs do). */
    readonly ks = 0.3,
  ) {}

  p(v: V3): Pt {
    return this.V.p(add(v, this.origin));
  }

  d(v: V3): number {
    return this.V.d(add(v, this.origin));
  }

  /** A point on a surface: the centre projected as a limb, the offset as a surface. */
  s(c: V3, off: V3, ks = this.ks): Pt {
    const [x, y] = this.p(c);
    return [x + this.V.x(off), y + off[1] + ks * this.V.d(off)];
  }

  /** Depth of an offset direction (positive: toward the viewer). */
  dv(off: V3): number {
    return this.V.d(off);
  }
}

/** Points round a ring in the torso's frame. */
function ringPts(
  P: Proj,
  c: V3,
  ax: V3,
  az: V3,
  rx: number,
  rz: number,
  n = 18,
  from = 0,
  to = Math.PI * 2,
): Pt[] {
  const out: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const a = from + ((to - from) * i) / n;
    out.push(P.s(c, add(mul(ax, Math.sin(a) * rx), mul(az, Math.cos(a) * rz))));
  }
  return out;
}

/** The half of a ring that faces the viewer, as points round it. */
function frontArc(P: Proj, c: V3, ax: V3, az: V3, rx: number, rz: number, n = 16): Pt[] {
  // The angle facing the viewer: maximise the depth of (sin a·ax·rx + cos a·az·rz).
  const dx = P.dv(ax) * rx;
  const dz = P.dv(az) * rz;
  const mid = Math.atan2(dx, dz);
  return ringPts(P, c, ax, az, rx, rz, n, mid - Math.PI / 2, mid + Math.PI / 2);
}

/** A band round the body between two rings, on the side facing the viewer. */
function band(P: Proj, c0: V3, c1: V3, ax: V3, az: V3, r0: Pt, r1: Pt): Shape {
  const a = frontArc(P, c0, ax, az, r0[0], r0[1]);
  const b = frontArc(P, c1, ax, az, r1[0], r1[1]);
  return polygon([...a, ...b.reverse()]);
}

/** A tapered limb between two joints. */
function limb(P: Proj, a: V3, b: V3, ra: number, rb: number): Shape {
  return capsule(P.p(a), P.p(b), ra, rb);
}

// ---------------------------------------------------------------- legs

function leg(g: G, L: SpriteLook, J: Joints, P: Proj, s: -1 | 1): void {
  const k = J.scale;
  const hip = s < 0 ? J.hipR : J.hipL;
  const knee = s < 0 ? J.knR : J.knL;
  const foot = s < 0 ? J.ftR : J.ftL;
  const ankle = add(foot, [0, -0.045 * k, -0.01 * k]);
  const near = P.d(knee) >= P.d(s < 0 ? J.knL : J.knR);
  const tr = near
    ? TROUSERS
    : ([TROUSERS[1], mix(TROUSERS[1], "#000000", 0.2), TROUSERS[1]] as Tri);
  const rider = L.mounted;
  cel(g, limb(P, hip, knee, 0.058 * k, 0.05 * k), tr[0], tr[1], 0.02, tr[2]);
  if (rider) {
    // Riders' boots of leather to the knee.
    cel(g, limb(P, knee, ankle, 0.047 * k, 0.04 * k), "#4a3a2e", "#2e231b", 0.015, "#6e5a4a");
  } else {
    const wrap = limb(P, knee, ankle, 0.045 * k, 0.038 * k);
    cel(g, wrap, WRAP[0], WRAP[1], 0.015, WRAP[2]);
    inside(g, wrap, () => {
      for (let i = 1; i < 4; i++) {
        const q = P.p(mix3(knee, ankle, i / 4));
        line(g, alpha(WRAP[1], 0.9), LW2, q[0] - 0.05, q[1] - 0.012, q[0] + 0.05, q[1] + 0.012);
      }
    });
  }
  // The shoe: from the heel forward.
  const toe = add(foot, [s * 0.012 * k, -0.02 * k, 0.07 * k]);
  const heel = add(foot, [0, -0.025 * k, -0.025 * k]);
  cel(g, capsule(P.p(heel), P.p(toe), 0.036 * k, 0.034 * k), SHOE[0], SHOE[1], 0.012, SHOE[2]);
}

// ---------------------------------------------------------------- torso and robe

interface Torso {
  shoulder: { c: V3; rx: number; rz: number };
  belly: { c: V3; rx: number; rz: number };
  waist: { c: V3; rx: number; rz: number };
  hem: { c: V3; rx: number; rz: number };
}

function torsoRings(L: SpriteLook, J: Joints): Torso {
  const k = J.scale;
  const sw = L.shoulders;
  const long = L.fam === "strategist" && !L.mounted;
  const ay = J.ay;
  const shoulder = {
    c: add(J.chest, mul(ay, 0.02 * k)),
    rx: BODY.shoulderX * sw * k * 1.02,
    rz: 0.1 * k * Math.sqrt(sw),
  };
  const belly = {
    c: add(J.chest, mul(ay, 0.15 * k)),
    rx: 0.146 * k * Math.max(sw * 0.92, L.belly),
    rz: 0.118 * k * L.belly,
  };
  const waist = {
    c: add(J.pelvis, mul(ay, -0.03 * k)),
    rx: 0.14 * k * L.belly,
    rz: 0.108 * k * L.belly,
  };
  // The hem follows the knees a little, so a stride swings the skirt.
  const knees = mix3(J.knR, J.knL, 0.5);
  const hemY = long ? -0.025 * k : J.pelvis[1] + 0.17 * k;
  const hc: V3 = [
    J.pelvis[0] * 0.4 + knees[0] * 0.6,
    L.mounted ? J.pelvis[1] + 0.1 * k : Math.min(hemY, knees[1] + (long ? 0.2 : 0.04) * k),
    J.pelvis[2] * 0.4 + knees[2] * 0.6,
  ];
  const flare = long ? 1.38 : L.coarse ? 1.12 : 1.2;
  const hem = { c: hc, rx: 0.135 * k * flare * Math.max(1, L.belly * 0.95), rz: 0.12 * k * flare };
  return { shoulder, belly, waist, hem };
}

function ring(P: Proj, J: Joints, r: { c: V3; rx: number; rz: number }, n = 18): Pt[] {
  return ringPts(P, r.c, J.ax, J.az, r.rx, r.rz, n);
}

/** The robe below the belt: a skirt from the waist to the hem, its edge in the contrasting colour. */
function skirt(g: G, L: SpriteLook, J: Joints, P: Proj, T: Torso): void {
  const sk = hullShape([...ring(P, J, T.waist), ...ring(P, J, T.hem, 24)]);
  const [robe, dark, light] = L.robe;
  cel(g, sk, robe, dark, 0.035, light);
  const hemArc = frontArc(P, T.hem.c, J.ax, J.az, T.hem.rx, T.hem.rz, 20);
  inside(g, sk, () => {
    // The hem's contrasting edge.
    g.strokeStyle = L.edge[0];
    g.lineWidth = 0.05 * J.scale;
    g.beginPath();
    trace(g, hemArc);
    g.stroke();
    // The front panel's edge falling from the belt (right over left).
    if (P.dv(J.az) > 0.2) {
      const a = P.s(T.waist.c, add(mul(J.ax, -0.35 * T.waist.rx), mul(J.az, T.waist.rz * 0.94)));
      const b = P.s(T.hem.c, add(mul(J.ax, -0.45 * T.hem.rx), mul(J.az, T.hem.rz * 0.9)));
      line(g, alpha(dark, 0.9), LW2 * 1.2, a[0], a[1], b[0], b[1]);
    }
  });
  if (L.gilt && L.armour === "none") {
    inside(g, sk, () => {
      g.strokeStyle = GOLD;
      g.lineWidth = 0.014;
      g.beginPath();
      trace(g, hemArc, -0.03);
      g.stroke();
    });
  }
}

/** The upper body's shape, from the shoulders to the waist. */
function torsoShape(P: Proj, J: Joints, T: Torso, grow = 1): Shape {
  const sc = (r: { c: V3; rx: number; rz: number }) => ({
    c: r.c,
    rx: r.rx * grow,
    rz: r.rz * grow,
  });
  return hullShape([
    ...ring(P, J, sc(T.shoulder)),
    ...ring(P, J, sc(T.belly)),
    ...ring(P, J, sc(T.waist)),
  ]);
}

/** A point on the torso's front surface: across −1…1, down 0 (shoulders) … 1 (waist). */
function front(P: Proj, J: Joints, T: Torso, across: number, down: number): Pt {
  const a = T.shoulder;
  const b = T.waist;
  const c = mix3(a.c, b.c, down);
  const rx = a.rx + (b.rx - a.rx) * down;
  const rz = a.rz + (b.rz - a.rz) * down;
  const z = Math.sqrt(Math.max(0, 1 - across * across));
  return P.s(c, add(mul(J.ax, across * rx), mul(J.az, z * rz)));
}

function torso(g: G, L: SpriteLook, J: Joints, P: Proj, T: Torso): void {
  const k = J.scale;
  const facingUs = P.dv(J.az);
  const body = torsoShape(P, J, T);
  const [robe, dark, light] = L.robe;
  cel(g, body, robe, dark, 0.04, light);
  // The collar: an under-robe's white V and the outer lapel, right over left (交領右衽).
  if (facingUs > -0.15) {
    const vee = polygon([
      front(P, J, T, -0.3, 0),
      front(P, J, T, 0.3, 0),
      front(P, J, T, -0.02, 0.42),
    ]);
    inside(g, body, () => {
      if (!L.coarse) cel(g, vee, WHITE_CLOTH, "#cdc3ad", 0.012, undefined, LW2);
      else cel(g, vee, L.skin[0], L.skin[1], 0.012, undefined, LW2);
      const lapel = polygon([
        front(P, J, T, 0.22, -0.02),
        front(P, J, T, 0.42, 0.0),
        front(P, J, T, -0.5, 0.95),
        front(P, J, T, -0.72, 0.9),
      ]);
      cel(g, lapel, L.edge[0], L.edge[1], 0.012, undefined, LW2);
      const under = polygon([
        front(P, J, T, -0.24, -0.02),
        front(P, J, T, -0.42, 0.0),
        front(P, J, T, -0.06, 0.4),
        front(P, J, T, 0.0, 0.32),
      ]);
      cel(g, under, L.edge[0], L.edge[1], 0.012, undefined, LW2);
    });
  } else {
    // From behind: the back of the collar.
    inside(g, body, () => {
      const c = polygon([
        front(P, J, T, -0.3, -0.04),
        front(P, J, T, 0.3, -0.04),
        front(P, J, T, 0.26, 0.08),
        front(P, J, T, -0.26, 0.08),
      ]);
      cel(g, c, L.edge[0], L.edge[1], 0.01, undefined, LW2);
    });
  }
  if (L.marks.has("brocade") && L.armour === "none")
    inside(g, body, () => {
      for (let i = 0; i < 9; i++) {
        const q = front(P, J, T, -0.8 + (i % 3) * 0.8, 0.2 + Math.floor(i / 3) * 0.3);
        dot(g, alpha(L.edge[0], 0.55), q[0], q[1], 0.012 * k);
      }
    });
}

/** Iron lamellae over a shape: rows of small plates, staggered, laced in the side's colour. */
function plates(
  g: G,
  s: Shape,
  P: Proj,
  J: Joints,
  L: SpriteLook,
  rows: { c: V3; rx: number; rz: number }[],
): void {
  const [base, dark, light] = L.plate;
  cel(g, s, base, dark, 0.035, light);
  inside(g, s, () => {
    // Rows, plate edges and the lacing, staggered row by row: one stroke or fill each.
    const rowPath = new Path2D();
    const edges = new Path2D();
    const laces = new Path2D();
    rows.forEach((r, i) => {
      const arc = frontArc(P, r.c, J.ax, J.az, r.rx, r.rz, 18);
      trace(rowPath, arc);
      for (let j = i % 2; j < arc.length - 1; j += 2) {
        const [x, y] = arc[j] as Pt;
        edges.moveTo(x, y);
        edges.lineTo(x, y - 0.03);
        if (j % 4 === (i % 2) * 2) laces.rect(x + 0.006, y - 0.01, 0.013, 0.012);
      }
    });
    g.strokeStyle = L.lacquer ? "#a33328" : alpha("#16181c", 0.75);
    g.lineWidth = 0.011;
    g.stroke(rowPath);
    g.strokeStyle = alpha("#16181c", 0.45);
    g.lineWidth = 0.007;
    g.stroke(edges);
    g.fillStyle = L.P.lace;
    g.fill(laces);
  });
  if (L.gilt) outline(g, s, GOLD, 0.012);
  outline(g, s);
}

function outline(g: G, s: Shape, color = INK, lw = LW): void {
  g.lineWidth = lw;
  g.strokeStyle = color;
  g.stroke(s.p);
}

/** Armour over the robe: a lamellar cuirass with shoulder flaps, a chest piece, or a leather vest. */
function armour(g: G, L: SpriteLook, J: Joints, P: Proj, T: Torso): void {
  const k = J.scale;
  if (L.armour === "none") return;
  const ay = J.ay;
  if (L.armour === "vest") {
    // An archer's light leather vest and the quiver's strap.
    const v = torsoShape(
      P,
      J,
      { ...T, shoulder: { ...T.shoulder, c: add(T.shoulder.c, mul(ay, 0.07 * k)) } },
      1.04,
    );
    cel(g, v, "#6b4529", "#4a2e1b", 0.03, "#8d6440");
    if (P.dv(J.az) > -0.1)
      inside(g, v, () => {
        const a = front(P, J, T, 0.75, 0.05);
        const b = front(P, J, T, -0.8, 0.95);
        line(g, INK, 0.05, a[0], a[1], b[0], b[1]);
        line(g, "#4a2e1b", 0.035, a[0], a[1], b[0], b[1]);
      });
    return;
  }
  const grow = 1.07;
  const top = L.armour === "chest" ? 0.06 : 0.0;
  const cuirassRings = {
    shoulder: {
      c: add(T.shoulder.c, mul(ay, top * k)),
      rx: T.shoulder.rx * 0.98,
      rz: T.shoulder.rz,
    },
    belly: T.belly,
    waist: { c: add(T.waist.c, mul(ay, 0.04 * k)), rx: T.waist.rx * 1.05, rz: T.waist.rz * 1.05 },
    hem: T.hem,
  };
  const s = torsoShape(P, J, cuirassRings, grow);
  const rows: { c: V3; rx: number; rz: number }[] = [];
  for (let i = 1; i <= 7; i++) {
    const u = i / 8;
    const a = cuirassRings.shoulder;
    const b = cuirassRings.waist;
    rows.push({
      c: mix3(a.c, b.c, u),
      rx: (a.rx + (b.rx - a.rx) * u) * grow,
      rz: (a.rz + (b.rz - a.rz) * u) * grow,
    });
  }
  plates(g, s, P, J, L, rows);
  // A tasset of plates over the hips.
  if (L.armour === "cuirass" && !L.mounted) {
    const w0 = {
      c: add(T.waist.c, mul(ay, 0.02 * k)),
      rx: T.waist.rx * 1.12,
      rz: T.waist.rz * 1.12,
    };
    const w1 = { c: add(T.waist.c, mul(ay, 0.1 * k)), rx: T.waist.rx * 1.22, rz: T.waist.rz * 1.2 };
    const tas = hullShape([...ring(P, J, w0), ...ring(P, J, w1)]);
    plates(g, tas, P, J, L, [w1, { ...w1, c: add(w1.c, mul(ay, -0.035 * k)) }]);
  }
  // The belt over the armour, with its bronze hook.
  const belt = band(
    P,
    add(T.waist.c, mul(ay, -0.012 * k)),
    add(T.waist.c, mul(ay, 0.03 * k)),
    J.ax,
    J.az,
    [T.waist.rx * 1.12, T.waist.rz * 1.12],
    [T.waist.rx * 1.12, T.waist.rz * 1.12],
  );
  cel(g, belt, "#6b4529", "#4a2e1b", 0.012, "#8d6440", LW2);
  if (P.dv(J.az) > 0.1) {
    const h = front(P, J, T, 0, 1.03);
    cel(g, circle(h[0], h[1], 0.022 * k), BRONZE, BRONZE_DARK, 0.008, BRONZE_LIGHT, LW2);
  }
}

/** Shoulder flaps of plates (cuirass), drawn with the arm they cover. */
function shoulderFlap(g: G, L: SpriteLook, J: Joints, P: Proj, s: -1 | 1): void {
  if (L.armour !== "cuirass" && !(L.armour === "chest" && L.tier2)) return;
  const k = J.scale;
  const sh = s < 0 ? J.shR : J.shL;
  const el = s < 0 ? J.elR : J.elL;
  const c = mix3(sh, el, 0.28);
  const flap = capsule(P.p(add(sh, mul(J.ay, -0.01 * k))), P.p(c), 0.07 * k, 0.068 * k);
  const rows = [0.3, 0.6, 0.9].map((u) => ({
    c: mix3(sh, el, u * 0.45),
    rx: 0.07 * k,
    rz: 0.07 * k,
  }));
  plates(g, flap, P, J, L, rows);
}

/** The belt and its hook (unarmoured), a martial artist's sash, a strategist's long sash ends. */
function belt(g: G, L: SpriteLook, J: Joints, P: Proj, T: Torso): void {
  const k = J.scale;
  const ay = J.ay;
  if (L.armour === "cuirass" || L.armour === "chest") return;
  const ma = L.fam === "martialArtist";
  const acc: Tri = L.accent && !["black", "white"].includes(L.accent[0]) ? L.accent : L.edge;
  const col: Tri = ma
    ? L.accent
      ? L.accent
      : [L.P.tassel, L.P.robeDark, mix(L.P.tassel, "#ffffff", 0.3)]
    : L.fam === "strategist"
      ? acc
      : ["#6b4529", "#4a2e1b", "#8d6440"];
  const w = ma || L.fam === "strategist" ? 0.035 : 0.022;
  const s = band(
    P,
    add(T.waist.c, mul(ay, -w * k)),
    add(T.waist.c, mul(ay, w * k)),
    J.ax,
    J.az,
    [T.waist.rx * 1.06, T.waist.rz * 1.06],
    [T.waist.rx * 1.07, T.waist.rz * 1.07],
  );
  cel(g, s, col[0], col[1], 0.012, col[2], LW2);
  if (L.accent?.[0] === TIGER)
    inside(g, s, () => {
      const a = frontArc(P, T.waist.c, J.ax, J.az, T.waist.rx, T.waist.rz, 10);
      for (const [x, y] of a) line(g, INK, 0.012, x - 0.01, y - 0.03, x + 0.008, y + 0.03);
    });
  if (P.dv(J.az) > 0.1) {
    if (L.fam === "strategist" || ma) {
      // The sash's long ends hang at the front.
      const a = front(P, J, T, -0.15, 1.02);
      for (const dx of [-0.02, 0.025]) {
        const end = polygon([
          [a[0] + dx - 0.016 * k, a[1]],
          [a[0] + dx + 0.016 * k, a[1]],
          [a[0] + dx * 1.6 + 0.014 * k, a[1] + 0.13 * k],
          [a[0] + dx * 1.6 - 0.014 * k, a[1] + 0.13 * k],
        ]);
        cel(g, end, col[0], col[1], 0.008, undefined, LW2);
      }
    } else {
      const h = front(P, J, T, 0.05, 1.0);
      cel(g, circle(h[0], h[1], 0.02 * k), BRONZE, BRONZE_DARK, 0.007, BRONZE_LIGHT, LW2);
    }
  }
}

/** A roll of fleece or dark fur round the shoulders. */
function collar(g: G, L: SpriteLook, J: Joints, P: Proj, T: Torso): void {
  if (!L.collar) return;
  const k = J.scale;
  const [base, dark, light] = L.collar === "fur" ? FUR : FLEECE;
  const c = add(T.shoulder.c, mul(J.ay, -0.005 * k));
  const pts = ringPts(P, c, J.ax, J.az, T.shoulder.rx * 0.82, T.shoulder.rz * 1.05, 14);
  const tufts = pts.map(([x, y]) => circle(x, y, 0.04 * k));
  for (const t of tufts) cel(g, t, dark, undefined, 0, undefined, LW);
  for (const [i, [x, y]] of pts.entries()) {
    cel(g, circle(x + 0.005, y - 0.006, 0.033 * k), base, undefined, 0, undefined, 0);
    if (i % 2 === 0) dot(g, light, x + 0.012, y - 0.014, 0.01 * k);
  }
}

/** A bare right shoulder and arm (許褚 stripped to fight, the novel): drawn on the arm. */
function bareArm(L: SpriteLook, s: -1 | 1): boolean {
  return L.marks.has("bare") && s < 0;
}

// ---------------------------------------------------------------- arms

function arm(g: G, L: SpriteLook, J: Joints, P: Proj, s: -1 | 1, hand = true): void {
  const k = J.scale;
  const sh = s < 0 ? J.shR : J.shL;
  const el = s < 0 ? J.elR : J.elL;
  const ha = s < 0 ? J.haR : J.haL;
  const [robe, dark, light] = L.robe;
  const skin = L.skin;
  const wide = L.fam === "strategist";
  const ma = L.fam === "martialArtist";
  const bare = bareArm(L, s);
  const sleeve: Tri = bare ? skin : [robe, dark, light];
  cel(g, limb(P, sh, el, 0.052 * k, 0.047 * k), sleeve[0], sleeve[1], 0.02, sleeve[2]);
  const wrist = mix3(el, ha, 0.78);
  if (ma) {
    // Sleeves pushed up: a bare forearm, wrapped at the wrist.
    cel(g, limb(P, el, wrist, 0.042 * k, 0.038 * k), skin[0], skin[1], 0.015, skin[2]);
    const w = L.tier2
      ? (["#8d6440", "#5a3a22", "#b08860"] as Tri)
      : (["#efe8d8", "#c9c2b4", "#ffffff"] as Tri);
    cel(
      g,
      limb(P, mix3(el, ha, 0.5), mix3(el, ha, 0.92), 0.043 * k, 0.043 * k),
      w[0],
      w[1],
      0.012,
      w[2],
    );
  } else if (wide) {
    // Wide sleeves with a contrasting cuff.
    cel(g, limb(P, el, wrist, 0.048 * k, 0.07 * k), sleeve[0], sleeve[1], 0.02, sleeve[2]);
    cel(
      g,
      limb(P, mix3(el, ha, 0.68), wrist, 0.066 * k, 0.072 * k),
      L.edge[0],
      L.edge[1],
      0.01,
      undefined,
      LW2,
    );
  } else {
    cel(g, limb(P, el, wrist, 0.046 * k, 0.042 * k), sleeve[0], sleeve[1], 0.018, sleeve[2]);
    if (!bare) {
      const cuff = limb(P, mix3(el, ha, 0.62), wrist, 0.045 * k, 0.045 * k);
      if (L.armour === "cuirass") cel(g, cuff, L.plate[0], L.plate[1], 0.01, L.plate[2], LW2);
      else cel(g, cuff, L.edge[0], L.edge[1], 0.01, undefined, LW2);
    }
  }
  if (hand) handAt(g, L, J, P, s);
}

export function handAt(g: G, L: SpriteLook, J: Joints, P: Proj, s: -1 | 1): void {
  const k = J.scale;
  const ha = s < 0 ? J.haR : J.haL;
  const [x, y] = P.p(ha);
  const r = (L.fam === "martialArtist" ? 0.05 : BODY.hand) * k;
  cel(g, circle(x, y, r), L.skin[0], L.skin[1], 0.012, L.skin[2]);
}

// ---------------------------------------------------------------- the person

export interface PersonParts {
  /** Gear in the right hand and the left: drawn by the caller with their own depth. */
  weapon?: ((g: G) => void) | undefined;
  weaponD?: number | undefined;
  offhand?: ((g: G) => void) | undefined;
  offhandD?: number | undefined;
  /** Slung on the back. */
  back?: ((g: G) => void) | undefined;
  head: (g: G) => void;
}

/** A person's draw items: legs, skirt, torso and armour, arms, the head, gear. */
export function person(L: SpriteLook, J: Joints, P: Proj, parts: PersonParts, bias = 0): Item[] {
  const T = torsoRings(L, J);
  const items: Item[] = [];
  const dChest = P.d(J.chest) + bias;
  // Legs behind the skirt (a rider's near leg over the horse, the far one under it).
  for (const s of [-1, 1] as const) {
    const knee = s < 0 ? J.knR : J.knL;
    const d = L.mounted ? P.d(knee) + bias : dChest - 0.3 + P.d(knee) * 0.01;
    items.push({ d, f: (g) => leg(g, L, J, P, s) });
  }
  items.push({
    d: dChest,
    f: (g) => {
      if (!L.mounted || L.fam !== "cavalry") skirt(g, L, J, P, T);
      else skirt(g, L, J, P, { ...T, hem: { ...T.hem, rx: T.hem.rx * 1.1 } });
      torso(g, L, J, P, T);
      belt(g, L, J, P, T);
      armour(g, L, J, P, T);
      collar(g, L, J, P, T);
    },
  });
  if (parts.back) items.push({ d: dChest - 0.12 * P.dv(J.az) - 0.001, f: parts.back });
  for (const s of [-1, 1] as const) {
    const sh = s < 0 ? J.shR : J.shL;
    const ha = s < 0 ? J.haR : J.haL;
    const d = Math.max(P.d(sh), P.d(ha)) + bias + 0.005;
    items.push({
      d,
      f: (g) => {
        arm(g, L, J, P, s, false);
        shoulderFlap(g, L, J, P, s);
      },
    });
    // The hand over the grip, just after the gear it holds.
    const gear = s < 0 ? parts.weapon : parts.offhand;
    const gd = s < 0 ? parts.weaponD : parts.offhandD;
    if (gear) items.push({ d: (gd ?? d) + 0.0005, f: gear });
    items.push({ d: Math.max(d, (gd ?? d) + 0.001), f: (g) => handAt(g, L, J, P, s) });
  }
  items.push({ d: P.d(J.head) + bias + 0.06, f: parts.head });
  return items;
}
