/**
 * What the figures carry (ADR 0046, ADR 0073): the ring-pommel sabre and the
 * tall lacquered shield, the ji, the recurve bow and its quiver, the feather
 * fan; each hero's own weapon from his portrait (關羽's glaive, 張飛's snake
 * spear, 典韋's twin ji, 呂布's crescent halberd, 徐晃's axe); what the other
 * hand holds; what is slung on the back; and the strike's trail, painted
 * into the strike frame so a blow reads as fast without any tweening.
 */
import {
  BLADE,
  BLADE_DARK,
  BLADE_MID,
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  GOLD,
  GOLD_DARK,
  GOLD_LIGHT,
  LACQUER,
  LACQUER_DARK,
  LACQUER_RED,
  LEATHER,
  LEATHER_DARK,
  LEATHER_LIGHT,
  SEAL_JADE,
  SEAL_JADE_DARK,
  WOOD,
  WOOD_DARK,
} from "../portrait/palette.ts";
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
  INK,
  inkLine,
  inside,
  LW,
  LW2,
  line,
  mix,
  polygon,
  ribbon,
  type Shape,
  type Tri,
} from "./draw.ts";
import { add, mul, norm, type Pt, sub, type V3 } from "./geom.ts";
import type { SpriteLook } from "./look.ts";
import type { Item, Proj } from "./person.ts";
import type { Joints } from "./rig.ts";

const BLADE3: Tri = [BLADE_MID, BLADE_DARK, BLADE];
const WOOD3: Tri = [WOOD_DARK, "#3a2414", WOOD];
const HAFT_DARK: Tri = ["#2a2622", "#141210", "#55504e"];

/** A straight piece along a direction from a grip: the screen strip from `a` to `b` units along it. */
function along(P: Proj, grip: V3, dir: V3, a: number, b: number): [Pt, Pt] {
  return [P.p(add(grip, mul(dir, a))), P.p(add(grip, mul(dir, b)))];
}

/** A screen direction and its normal for a 3D direction at a point. */
function screenDir(P: Proj, at: V3, dir: V3): { u: Pt; n: Pt; l: number } {
  const a = P.p(at);
  const b = P.p(add(at, mul(dir, 0.1)));
  const dx = b[0] - a[0];
  const dy = b[1] - a[1];
  const l = Math.hypot(dx, dy) || 1e-6;
  return { u: [dx / l, dy / l], n: [-dy / l, dx / l], l: l / 0.1 };
}

/** A haft (shaft) as an inked stroke. */
function haft(g: G, a: Pt, b: Pt, w: number, col: Tri = WOOD3): void {
  cel(g, capsule(a, b, w, w), col[0], col[1], 0.006, col[2], LW2 * 1.2);
}

/** A blade shape in screen space: base at `p`, along u, with half-widths along it. */
function bladeShape(
  p: Pt,
  u: Pt,
  n: Pt,
  len: number,
  widths: readonly (readonly [number, number])[],
): Shape {
  const left: Pt[] = [];
  const right: Pt[] = [];
  for (const [t, w] of widths) {
    const x = p[0] + u[0] * len * t;
    const y = p[1] + u[1] * len * t;
    left.push([x + n[0] * w, y + n[1] * w]);
    right.push([x - n[0] * w, y - n[1] * w]);
  }
  return polygon([...left, ...right.reverse()]);
}

/** A single-edged blade: the back straight, the edge swelling, a point. */
function edged(p: Pt, u: Pt, n: Pt, len: number, w: number, curve2 = 0): Shape {
  const pts: Pt[] = [];
  const steps = 6;
  for (let i = 0; i <= steps; i++) {
    const t = i / steps;
    const bow = curve2 * Math.sin(Math.PI * t) * len * 0.15;
    pts.push([
      p[0] + u[0] * len * t + n[0] * (w * 0.5 + bow),
      p[1] + u[1] * len * t + n[1] * (w * 0.5 + bow),
    ]);
  }
  for (let i = steps; i >= 0; i--) {
    const t = i / steps;
    const bow = curve2 * Math.sin(Math.PI * t) * len * 0.15;
    const ww = t > 0.82 ? w * 0.5 * (1 - (t - 0.82) / 0.18) * 1.6 - w * 0.3 : -w * 0.5;
    pts.push([
      p[0] + u[0] * len * t + n[0] * (ww + bow),
      p[1] + u[1] * len * t + n[1] * (ww + bow),
    ]);
  }
  return polygon(pts);
}

function tasselAt(g: G, at: Pt, u: Pt, col: Tri, k: number): void {
  const n: Pt = [-u[1], u[0]];
  const s = polygon([
    [at[0] + n[0] * 0.025 * k, at[1] + n[1] * 0.025 * k],
    [at[0] - n[0] * 0.025 * k, at[1] - n[1] * 0.025 * k],
    [at[0] - n[0] * 0.04 * k - u[0] * 0.07 * k, at[1] - n[1] * 0.04 * k - u[1] * 0.07 * k],
    [at[0] + n[0] * 0.04 * k - u[0] * 0.07 * k, at[1] + n[1] * 0.04 * k - u[1] * 0.07 * k],
  ]);
  cel(g, s, col[0], col[1], 0.006, col[2], LW2);
}

/** The ji's head: a spike and a side blade (戟), or crescents either side (方天畫戟, the novel). */
function jiHead(g: G, at: Pt, u: Pt, n: Pt, k: number, crescent: boolean, tassel: Tri): void {
  tasselAt(g, at, u, tassel, k);
  const spike = bladeShape(at, u, n, 0.17 * k, [
    [0, 0.016 * k],
    [0.4, 0.024 * k],
    [1, 0],
  ]);
  cel(g, spike, BLADE3[0], BLADE3[1], 0.006, BLADE3[2], LW2);
  const sides = crescent ? [-1, 1] : [1];
  for (const s of sides) {
    const b: Pt = [at[0] + u[0] * 0.05 * k, at[1] + u[1] * 0.05 * k];
    const tip: Pt = [
      b[0] + n[0] * s * 0.1 * k + u[0] * 0.02 * k,
      b[1] + n[1] * s * 0.1 * k + u[1] * 0.02 * k,
    ];
    const shape = crescent
      ? blob([
          b,
          [
            b[0] + n[0] * s * 0.09 * k + u[0] * 0.07 * k,
            b[1] + n[1] * s * 0.09 * k + u[1] * 0.07 * k,
          ],
          tip,
          [
            b[0] + n[0] * s * 0.06 * k - u[0] * 0.03 * k,
            b[1] + n[1] * s * 0.06 * k - u[1] * 0.03 * k,
          ],
        ])
      : polygon([
          b,
          [b[0] + u[0] * 0.035 * k, b[1] + u[1] * 0.035 * k],
          tip,
          [tip[0] - u[0] * 0.04 * k, tip[1] - u[1] * 0.04 * k],
        ]);
    cel(g, shape, BLADE3[0], BLADE3[1], 0.006, BLADE3[2], LW2);
  }
}

/** A long weapon in the hand: haft and head. */
function pole(g: G, L: SpriteLook, J: Joints, P: Proj, grip: V3, dir: V3, kind: string): void {
  const k = J.scale;
  const long =
    kind === "glaive" || kind === "snake" ? 0.78 : kind === "staff" || kind === "jie" ? 0.62 : 0.68;
  const butt = kind === "axe" || kind === "dao" ? -0.22 : -0.3;
  const [a, b] = along(P, grip, dir, butt * k, long * k);
  const { u, n, l } = screenDir(P, grip, dir);
  const hk = k * Math.max(0.55, Math.min(1, l));
  haft(g, a, b, 0.017 * k, kind === "snake" ? HAFT_DARK : WOOD3);
  const tassel: Tri =
    L.weapon === "spear" && L.accent?.[0] === "#eef0ee"
      ? ["#f1efe8", "#c9c5ba", "#ffffff"]
      : [L.P.tassel, L.P.robeDark, mix(L.P.tassel, "#ffffff", 0.3)];
  switch (kind) {
    case "ji":
    case "jiPennon":
    case "crescent":
      jiHead(
        g,
        b,
        u,
        n,
        hk,
        kind === "crescent",
        kind === "crescent" ? ["#c0392b", "#7a1f17", "#e46a5a"] : tassel,
      );
      if (kind === "jiPennon") {
        const base: Pt = [b[0] - u[0] * 0.05 * k, b[1] - u[1] * 0.05 * k];
        cel(
          g,
          ribbon(
            base,
            [base[0] - n[0] * 0.12 * k + u[0] * 0.02, base[1] - n[1] * 0.12 * k + 0.04 * k],
            [base[0] - n[0] * 0.28 * k, base[1] - n[1] * 0.2 * k + 0.08 * k],
            0.07 * k,
            0.025 * k,
          ),
          L.P.robe,
          L.P.robeDark,
          0.008,
          mix(L.P.robe, "#ffffff", 0.3),
          LW2,
        );
      }
      break;
    case "spear": {
      tasselAt(g, b, u, tassel, hk);
      cel(
        g,
        bladeShape(b, u, n, 0.15 * hk, [
          [0, 0.012 * k],
          [0.35, 0.032 * k],
          [1, 0],
        ]),
        BLADE3[0],
        BLADE3[1],
        0.006,
        BLADE3[2],
        LW2,
      );
      break;
    }
    case "snake": {
      // 張飛's serpent spear (丈八蛇矛, the novel): a long wavy blade.
      const pts: Pt[] = [];
      const len = 0.22 * hk;
      for (let i = 0; i <= 8; i++) {
        const t = i / 8;
        const w = 0.022 * k * (1 - t * 0.7) + (i % 2 ? 0.012 * k : 0);
        pts.push([b[0] + u[0] * len * t + n[0] * w, b[1] + u[1] * len * t + n[1] * w]);
      }
      pts.push([b[0] + u[0] * len * 1.12, b[1] + u[1] * len * 1.12]);
      for (let i = 8; i >= 0; i--) {
        const t = i / 8;
        const w = 0.022 * k * (1 - t * 0.7) + (i % 2 ? 0 : 0.012 * k);
        pts.push([b[0] + u[0] * len * t - n[0] * w, b[1] + u[1] * len * t - n[1] * w]);
      }
      cel(g, polygon(pts), BLADE3[0], BLADE3[1], 0.006, BLADE3[2], LW2);
      break;
    }
    case "glaive": {
      // The long curved blade (偃月刀, the novel), its back toward the hand.
      const blade = edged(
        [b[0] - u[0] * 0.04 * k, b[1] - u[1] * 0.04 * k],
        u,
        n,
        0.3 * hk,
        0.075 * k,
        1,
      );
      cel(g, blade, BLADE3[0], BLADE3[1], 0.01, BLADE3[2], LW);
      cel(
        g,
        circle(b[0] - u[0] * 0.04 * k, b[1] - u[1] * 0.04 * k, 0.025 * k),
        GOLD,
        GOLD_DARK,
        0.006,
        GOLD_LIGHT,
        LW2,
      );
      break;
    }
    case "trident": {
      const base: Pt = [b[0], b[1]];
      for (const s of [-1, 0, 1]) {
        const tip: Pt = [
          base[0] + u[0] * (s ? 0.12 : 0.17) * hk + n[0] * s * 0.06 * k,
          base[1] + u[1] * (s ? 0.12 : 0.17) * hk + n[1] * s * 0.06 * k,
        ];
        const root: Pt = [base[0] + n[0] * s * 0.045 * k, base[1] + n[1] * s * 0.045 * k];
        cel(
          g,
          capsule(root, tip, 0.014 * k, 0.004 * k),
          BLADE3[0],
          BLADE3[1],
          0.004,
          BLADE3[2],
          LW2,
        );
      }
      cel(
        g,
        capsule(
          [base[0] - n[0] * 0.05 * k, base[1] - n[1] * 0.05 * k],
          [base[0] + n[0] * 0.05 * k, base[1] + n[1] * 0.05 * k],
          0.014 * k,
          0.014 * k,
        ),
        BRONZE,
        BRONZE_DARK,
        0.004,
        BRONZE_LIGHT,
        LW2,
      );
      break;
    }
    case "axe": {
      const at: Pt = [b[0] - u[0] * 0.08 * k, b[1] - u[1] * 0.08 * k];
      const head = blob([
        [at[0] + n[0] * 0.02 * k, at[1] + n[1] * 0.02 * k],
        [at[0] + n[0] * 0.13 * k - u[0] * 0.07 * k, at[1] + n[1] * 0.13 * k - u[1] * 0.07 * k],
        [at[0] + n[0] * 0.17 * k + u[0] * 0.04 * k, at[1] + n[1] * 0.17 * k + u[1] * 0.04 * k],
        [at[0] + n[0] * 0.13 * k + u[0] * 0.14 * k, at[1] + n[1] * 0.13 * k + u[1] * 0.14 * k],
        [at[0] + n[0] * 0.02 * k + u[0] * 0.07 * k, at[1] + n[1] * 0.02 * k + u[1] * 0.07 * k],
      ]);
      cel(g, head, BLADE3[0], BLADE3[1], 0.01, BLADE3[2], LW);
      break;
    }
    case "dao": {
      const blade = edged(b, u, n, 0.26 * hk, 0.06 * k, 0.8);
      cel(g, blade, BLADE3[0], BLADE3[1], 0.008, BLADE3[2], LW);
      break;
    }
    case "staff": {
      // Rings on the staff's head.
      for (const s of [-1, 1]) {
        const c: Pt = [b[0] + n[0] * s * 0.03 * k, b[1] + n[1] * s * 0.03 * k];
        const r = ellipse(c[0], c[1], 0.03 * k, 0.03 * k);
        g.lineWidth = 0.016;
        g.strokeStyle = INK;
        g.stroke(r.p);
        g.lineWidth = 0.009;
        g.strokeStyle = BRONZE;
        g.stroke(r.p);
      }
      cel(g, circle(b[0], b[1], 0.026 * k), BRONZE, BRONZE_DARK, 0.006, BRONZE_LIGHT, LW2);
      break;
    }
    case "jie": {
      // A staff of authority (節), its tiers of red yak tail.
      for (let i = 0; i < 3; i++) {
        const c: Pt = [b[0] - u[0] * i * 0.08 * k, b[1] - u[1] * i * 0.08 * k];
        cel(
          g,
          ellipse(c[0], c[1] + 0.02 * k, 0.045 * k, 0.03 * k),
          "#b3262a",
          "#6a1612",
          0.006,
          "#e05a4a",
          LW2,
        );
      }
      break;
    }
    default:
      break;
  }
}

/** One-handed weapons: the ring-pommel sabre (環首刀), a sword, a hand ji, a cudgel, the staves. */
function oneHand(g: G, L: SpriteLook, J: Joints, P: Proj, grip: V3, dir: V3, kind: string): void {
  const k = J.scale;
  const { u, n, l } = screenDir(P, grip, dir);
  const lk = Math.max(0.45, Math.min(1.1, l));
  const hilt = P.p(add(grip, mul(dir, -0.05 * k)));
  switch (kind) {
    case "sabre":
    case "sword": {
      const pommel = P.p(add(grip, mul(dir, -0.085 * k)));
      if (kind === "sabre") {
        const r = ellipse(pommel[0], pommel[1], 0.026 * k, 0.026 * k);
        g.lineWidth = 0.022;
        g.strokeStyle = INK;
        g.stroke(r.p);
        g.lineWidth = 0.011;
        g.strokeStyle = BRONZE;
        g.stroke(r.p);
      }
      const guard = P.p(add(grip, mul(dir, 0.045 * k)));
      haft(g, hilt, guard, 0.017 * k, [LEATHER, LEATHER_DARK, LEATHER_LIGHT]);
      const gw = kind === "sword" ? 0.05 : 0.032;
      cel(
        g,
        capsule(
          [guard[0] - n[0] * gw * k, guard[1] - n[1] * gw * k],
          [guard[0] + n[0] * gw * k, guard[1] + n[1] * gw * k],
          0.013 * k,
          0.013 * k,
        ),
        BRONZE,
        BRONZE_DARK,
        0.004,
        BRONZE_LIGHT,
        LW2,
      );
      const base: Pt = [guard[0] + u[0] * 0.01, guard[1] + u[1] * 0.01];
      const len = (kind === "sword" ? 0.37 : 0.38) * k * lk;
      const blade =
        kind === "sword"
          ? bladeShape(base, u, n, len, [
              [0, 0.021 * k],
              [0.85, 0.019 * k],
              [1, 0],
            ])
          : edged(base, u, n, len, 0.05 * k, 0);
      cel(g, blade, BLADE3[0], BLADE3[1], 0.006, BLADE3[2], LW);
      if (L.gilt) dot(g, GOLD, guard[0], guard[1], 0.01 * k);
      return;
    }
    case "handJi": {
      // 曹操's hand ji (手戟), as in his portrait: a short haft and a ji head.
      const [a, b] = along(P, grip, dir, -0.06 * k, 0.17 * k);
      haft(g, a, b, 0.015 * k);
      jiHead(g, b, u, n, k * 0.75 * lk, false, [
        L.P.tassel,
        L.P.robeDark,
        mix(L.P.tassel, "#ffffff", 0.3),
      ]);
      return;
    }
    case "cudgel":
    case "staves": {
      const [a, b] = along(P, grip, dir, -0.08 * k, 0.34 * k);
      if (kind === "staves") {
        // The five-coloured staves of 曹操's night watch.
        haft(g, a, b, 0.019 * k, ["#b3262a", "#6a1612", "#e05a4a"]);
        const cols = ["#d8b23a", "#3f6b46", "#2e3f66", "#f1ead9"];
        cols.forEach((c, i) => {
          const t = 0.2 + i * 0.2;
          const p: Pt = [a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t];
          line(
            g,
            c,
            0.03 * k,
            p[0] - u[0] * 0.02,
            p[1] - u[1] * 0.02,
            p[0] + u[0] * 0.02,
            p[1] + u[1] * 0.02,
          );
        });
      } else haft(g, a, b, 0.024 * k);
      return;
    }
    default:
      return;
  }
}

/** The feather fan (羽扇). */
function fan(g: G, J: Joints, P: Proj, grip: V3, dir: V3): void {
  const k = J.scale;
  const [a, b] = along(P, grip, dir, -0.04 * k, 0.06 * k);
  haft(g, a, b, 0.012 * k, ["#c9a861", "#9a7c3e", "#e0c888"]);
  const c = P.p(add(grip, mul(dir, 0.14 * k)));
  const { u } = screenDir(P, grip, dir);
  const f = ellipse(c[0], c[1], 0.075 * k, 0.1 * k, Math.atan2(u[1], u[0]) - Math.PI / 2);
  cel(g, f, "#f6f2ea", "#d6d0c4", 0.012, "#ffffff");
  inside(g, f, () => {
    for (let i = -2; i <= 2; i++)
      line(
        g,
        "rgba(120,110,100,0.5)",
        0.006,
        b[0],
        b[1],
        c[0] + i * 0.03 * k + u[0] * 0.08,
        c[1] + u[1] * 0.08,
      );
  });
}

/** The composite recurve bow in the left hand, its string drawn to the right hand when aiming. */
function bow(
  g: G,
  _L: SpriteLook,
  J: Joints,
  P: Proj,
  long: boolean,
  drawn: boolean,
  loosed: boolean,
): void {
  const k = J.scale;
  const up = J.oDir ?? J.ay;
  const fwd = norm(sub(J.az, mul(up, up[0] * J.az[0] + up[1] * J.az[1] + up[2] * J.az[2])));
  const h = (long ? 0.3 : 0.24) * k;
  const g0 = J.haL;
  const tipA = add(g0, mul(up, -h));
  const tipB = add(g0, mul(up, h));
  const bend = (t: number, out: number) => add(add(g0, mul(up, t * h)), mul(fwd, out * k));
  // The limbs bow forward and their tips curl back (recurve).
  const pts: Pt[] = [
    P.p(add(tipA, mul(fwd, -0.03 * k))),
    P.p(bend(-0.8, 0.02)),
    P.p(bend(-0.4, 0.06)),
    P.p(bend(0, 0.07)),
    P.p(bend(0.4, 0.06)),
    P.p(bend(0.8, 0.02)),
    P.p(add(tipB, mul(fwd, -0.03 * k))),
  ];
  const a = P.p(tipA);
  const b = P.p(tipB);
  const string = drawn
    ? P.p(J.haR)
    : loosed
      ? P.p(add(g0, mul(fwd, -0.035 * k)))
      : P.p(add(g0, mul(fwd, -0.01 * k)));
  line(g, "rgba(240,236,220,0.95)", 0.007, a[0], a[1], string[0], string[1], b[0], b[1]);
  g.lineCap = "round";
  g.lineJoin = "round";
  const path = new Path2D();
  path.moveTo(pts[0]?.[0] ?? 0, pts[0]?.[1] ?? 0);
  for (let i = 1; i < pts.length - 1; i++) {
    const c = pts[i] as Pt;
    const d = pts[i + 1] as Pt;
    path.quadraticCurveTo(c[0], c[1], (c[0] + d[0]) / 2, (c[1] + d[1]) / 2);
  }
  const last = pts[pts.length - 1] as Pt;
  path.lineTo(last[0], last[1]);
  g.strokeStyle = INK;
  g.lineWidth = 0.036 * k;
  g.stroke(path);
  g.strokeStyle = long ? "#6e4424" : "#8a5a2c";
  g.lineWidth = 0.02 * k;
  g.stroke(path);
  for (const p of pts) dot(g, "rgba(0,0,0,0)", p[0], p[1], 0.02);
  if (drawn) {
    // The arrow on the string.
    const tail = P.p(J.haR);
    const head = P.p(add(g0, mul(fwd, 0.1 * k)));
    inkLine(g, "#d8c8a0", 0.01, tail[0], tail[1], head[0], head[1]);
    dot(g, BLADE_MID, head[0], head[1], 0.012 * k);
  }
}

/** A crossbow (弩) held level at the shoulder. */
function crossbow(g: G, J: Joints, P: Proj, grip: V3, dir: V3, loosed: boolean): void {
  const k = J.scale;
  const [a, b] = along(P, grip, dir, -0.08 * k, 0.24 * k);
  haft(g, a, b, 0.02 * k);
  const front = add(grip, mul(dir, 0.2 * k));
  const across = norm([dir[2], 0, -dir[0]]);
  const l = P.p(add(front, mul(across, -0.13 * k)));
  const r = P.p(add(front, mul(across, 0.13 * k)));
  const m = P.p(add(front, mul(dir, 0.03 * k)));
  const back = P.p(add(grip, mul(dir, loosed ? 0.17 * k : 0.04 * k)));
  line(g, "rgba(240,236,220,0.95)", 0.007, l[0], l[1], back[0], back[1], r[0], r[1]);
  const prod = new Path2D();
  prod.moveTo(l[0], l[1]);
  prod.quadraticCurveTo(m[0], m[1], r[0], r[1]);
  g.strokeStyle = INK;
  g.lineWidth = 0.032 * k;
  g.stroke(prod);
  g.strokeStyle = "#6e4424";
  g.lineWidth = 0.018 * k;
  g.stroke(prod);
  dot(g, BRONZE, a[0] + (b[0] - a[0]) * 0.3, a[1] + (b[1] - a[1]) * 0.3, 0.014 * k);
}

/** The tall lacquered shield with its cloud scroll, rimmed in the side's colour (its back from behind). */
function shield(g: G, L: SpriteLook, J: Joints, P: Proj, big: boolean): void {
  const k = J.scale;
  const w = (big ? 0.16 : 0.135) * k;
  const h = (big ? 0.27 : 0.21) * k;
  const c = add(add(J.haL, mul(J.az, 0.035 * k)), mul(J.ax, 0.045 * k));
  const nx = norm(add(J.ax, mul(J.az, -0.25)));
  const corners: V3[] = [
    add(add(c, mul(nx, -w)), mul(J.ay, -h)),
    add(add(c, mul(nx, w)), mul(J.ay, -h)),
    add(add(c, mul(nx, w)), mul(J.ay, h)),
    add(add(c, mul(nx, -w)), mul(J.ay, h)),
  ];
  const q = corners.map((p) => P.p(p));
  // Rounded corners: a blob through points pulled in from the corners.
  const pts: Pt[] = [];
  for (let i = 0; i < 4; i++) {
    const a = q[i] as Pt;
    const b = q[(i + 1) % 4] as Pt;
    pts.push(a, [a[0] + (b[0] - a[0]) * 0.5, a[1] + (b[1] - a[1]) * 0.5]);
  }
  const s = blob(pts.map((p, i) => (i % 2 === 0 ? p : p)));
  const faceUs = P.dv(J.az) > -0.05;
  if (!faceUs) {
    cel(g, s, WOOD_DARK, "#3a2414", 0.02, WOOD);
    const m = P.p(c);
    cel(
      g,
      capsule([m[0], m[1] - 0.05 * k], [m[0], m[1] + 0.05 * k], 0.014 * k, 0.014 * k),
      LEATHER,
      LEATHER_DARK,
      0.004,
      undefined,
      LW2,
    );
    return;
  }
  cel(g, s, LACQUER, LACQUER_DARK, 0.02, "#5a4a42");
  inside(g, s, () => {
    // The cloud scroll in red lacquer.
    const m = P.p(c);
    const sc = Math.max(0.4, Math.abs(P.dv(J.az)) * 0.6 + 0.4);
    for (const [dy, f] of [
      [-0.07, 1],
      [0.07, -1],
    ] as const) {
      g.strokeStyle = LACQUER_RED;
      g.lineWidth = 0.014 * k;
      g.beginPath();
      g.arc(
        m[0],
        m[1] + dy * k,
        0.04 * k * sc,
        f > 0 ? Math.PI : 0,
        f > 0 ? Math.PI * 2.4 : Math.PI * 1.4,
      );
      g.stroke();
    }
  });
  g.lineWidth = 0.022 * k;
  g.strokeStyle = L.gilt ? GOLD : L.P.lace;
  g.stroke(s.p);
  g.lineWidth = LW2;
  g.strokeStyle = INK;
  g.stroke(s.p);
}

/** What the other hand holds: documents, a torch, a pennon, a lantern, a wine jar, a seal, a cup, a whip. */
function offItem(g: G, L: SpriteLook, J: Joints, P: Proj): void {
  const k = J.scale;
  const h = P.p(J.haL);
  switch (L.offhand) {
    case "shield":
    case "bigShield":
      shield(g, L, J, P, L.offhand === "bigShield");
      return;
    case "scroll":
      cel(
        g,
        capsule(
          [h[0] - 0.05 * k, h[1] - 0.01],
          [h[0] + 0.05 * k, h[1] + 0.01],
          0.022 * k,
          0.022 * k,
        ),
        "#e8dcc0",
        "#b9a988",
        0.006,
        "#ffffff",
        LW2,
      );
      return;
    case "seal":
      cel(
        g,
        polygon([
          [h[0] - 0.035 * k, h[1] - 0.06 * k],
          [h[0] + 0.035 * k, h[1] - 0.06 * k],
          [h[0] + 0.035 * k, h[1]],
          [h[0] - 0.035 * k, h[1]],
        ]),
        SEAL_JADE,
        SEAL_JADE_DARK,
        0.006,
        "#f2faf1",
        LW2,
      );
      return;
    case "cup":
      cel(
        g,
        polygon([
          [h[0] - 0.03 * k, h[1] - 0.05 * k],
          [h[0] + 0.03 * k, h[1] - 0.05 * k],
          [h[0] + 0.02 * k, h[1]],
          [h[0] - 0.02 * k, h[1]],
        ]),
        BRONZE,
        BRONZE_DARK,
        0.006,
        BRONZE_LIGHT,
        LW2,
      );
      return;
    case "jar":
      cel(
        g,
        ellipse(h[0], h[1] + 0.03 * k, 0.05 * k, 0.06 * k),
        "#7a5a3a",
        "#4e3822",
        0.01,
        "#a08060",
        LW2,
      );
      return;
    case "torch":
    case "lantern":
    case "pennon":
    case "whip": {
      const top = P.p(add(J.haL, mul(J.ay, -0.2 * k)));
      const bot = P.p(add(J.haL, mul(J.ay, 0.06 * k)));
      haft(g, bot, top, 0.012 * k);
      if (L.offhand === "torch") {
        cel(
          g,
          blob([
            [top[0] - 0.03 * k, top[1]],
            [top[0], top[1] - 0.1 * k],
            [top[0] + 0.035 * k, top[1] - 0.01 * k],
            [top[0], top[1] + 0.02 * k],
          ]),
          "#ffb347",
          "#e0602a",
          0.008,
          "#fff0a0",
          LW2,
        );
      } else if (L.offhand === "lantern") {
        cel(
          g,
          rectAt(top[0], top[1] + 0.04 * k, 0.045 * k, 0.06 * k),
          "#f0c060",
          "#c08030",
          0.008,
          "#fff0b0",
          LW2,
        );
      } else if (L.offhand === "pennon") {
        cel(
          g,
          polygon([
            top,
            [top[0] + 0.14 * k, top[1] + 0.03 * k],
            [top[0] + 0.02 * k, top[1] + 0.08 * k],
          ]),
          GOLD,
          GOLD_DARK,
          0.008,
          GOLD_LIGHT,
          LW2,
        );
      } else {
        curve(
          g,
          INK,
          0.01,
          top[0],
          top[1],
          top[0] + 0.08 * k,
          top[1] - 0.02,
          top[0] + 0.1 * k,
          top[1] + 0.08 * k,
        );
      }
      return;
    }
    default:
      return;
  }
}

function rectAt(x: number, y: number, hw: number, hh: number): Shape {
  return polygon([
    [x - hw, y - hh],
    [x + hw, y - hh],
    [x + hw, y + hh],
    [x - hw, y + hh],
  ]);
}

/** The weapon in the right hand (and the left, for a pair), as a draw item. */
export function weaponItems(
  L: SpriteLook,
  J: Joints,
  P: Proj,
): { weapon?: (g: G) => void; weaponD?: number; offhand?: (g: G) => void; offhandD?: number } {
  const out: {
    weapon?: (g: G) => void;
    weaponD?: number;
    offhand?: (g: G) => void;
    offhandD?: number;
  } = {};
  const k = J.scale;
  const w = L.weapon;
  const dir = J.wDir;
  const mid = (hand: V3, d: V3 | null, reachK: number) =>
    P.d(add(hand, mul(d ?? [0, 0, 0], reachK * k)));
  if (L.grip === "bow") {
    const drawn = J.bow === "drawn";
    const loosed = J.bow === "loosed";
    out.offhand = (g) => bow(g, L, J, P, w === "longbow", drawn, loosed);
    out.offhandD = P.d(J.haL) + 0.01;
    return out;
  }
  if (L.grip === "crossbow" && dir) {
    out.weapon = (g) => crossbow(g, J, P, J.haR, dir, J.smear?.kind === "shot");
    out.weaponD = mid(J.haR, dir, 0.1) + 0.02;
    return out;
  }
  if (dir) {
    if (L.grip === "pole") {
      out.weapon = (g) => pole(g, L, J, P, J.haR, dir, w);
      out.weaponD = mid(J.haR, dir, 0.2);
    } else if (L.grip === "twin") {
      out.weapon = (g) => pole(g, L, J, P, J.haR, dir, "ji");
      out.weaponD = mid(J.haR, dir, 0.15);
    } else if (L.grip === "fan") {
      out.weapon = (g) => fan(g, J, P, J.haR, dir);
      out.weaponD = mid(J.haR, dir, 0.1) + 0.01;
    } else if (L.grip === "one") {
      out.weapon = (g) => oneHand(g, L, J, P, J.haR, dir, w);
      out.weaponD = mid(J.haR, dir, 0.15);
    }
  }
  if (L.grip === "twin" && J.oDir) {
    const o = J.oDir;
    out.offhand = (g) => pole(g, L, J, P, J.haL, o, "ji");
    out.offhandD = mid(J.haL, o, 0.15);
  } else if (L.offhand !== "none") {
    out.offhand = (g) => offItem(g, L, J, P);
    out.offhandD =
      L.offhand === "shield" || L.offhand === "bigShield"
        ? P.d(J.haL) + 0.04 * Math.sign(P.dv(J.az) || 1)
        : P.d(J.haL) + 0.005;
  }
  return out;
}

// ---------------------------------------------------------------- slung on the back

export function backItem(L: SpriteLook, J: Joints, P: Proj): ((g: G) => void) | undefined {
  const k = J.scale;
  const at = (x: number, y: number, z: number): V3 =>
    add(add(add(J.chest, mul(J.ax, x * k)), mul(J.ay, y * k)), mul(J.az, z * k));
  switch (L.back) {
    case "quiver":
    case "quivers":
      return (g) => {
        const spots = L.back === "quivers" ? [-1, 1] : [-1];
        for (const s of spots) {
          const top = at(s * 0.1, -0.06, -0.11);
          const bot = at(s * 0.13, 0.2, -0.1);
          const a = P.p(top);
          const b = P.p(bot);
          for (const dx of [-0.018, 0, 0.018]) {
            const f = P.p(at(s * 0.1 + dx, -0.13, -0.11));
            line(g, INK, 0.008, a[0] + dx, a[1], f[0], f[1]);
            dot(g, "#efe8d8", f[0], f[1], 0.014 * k);
          }
          cel(
            g,
            capsule(a, b, 0.035 * k, 0.03 * k),
            LEATHER,
            LEATHER_DARK,
            0.008,
            LEATHER_LIGHT,
            LW2,
          );
          cel(
            g,
            capsule(
              a,
              [a[0] + (b[0] - a[0]) * 0.15, a[1] + (b[1] - a[1]) * 0.15],
              0.038 * k,
              0.038 * k,
            ),
            L.P.robeDark,
            mix(L.P.robeDark, "#000000", 0.3),
            0.004,
            undefined,
            LW2,
          );
        }
      };
    case "plumes":
      // 甘寧's plumes and bells on his back (負毦帶鈴).
      return (g) => {
        for (const s of [-1, 1]) {
          const b = P.p(at(s * 0.08, -0.02, -0.1));
          cel(
            g,
            ribbon(
              b,
              [b[0] + s * 0.05, b[1] - 0.15 * k],
              [b[0] + s * 0.12 * k, b[1] - 0.3 * k],
              0.03 * k,
              0.05 * k,
            ),
            "#f0e6d0",
            "#b9a988",
            0.006,
            "#ffffff",
            LW2,
          );
          dot(g, GOLD, b[0] + s * 0.02, b[1] + 0.03, 0.018 * k);
        }
      };
    case "parasol": {
      // 顏良's command parasol (麾蓋), on a pole from his back.
      return (g) => {
        const bot = P.p(at(-0.04, 0.1, -0.13));
        const top = P.p(at(-0.04, -0.62, -0.13));
        haft(g, bot, top, 0.012 * k);
        cel(
          g,
          polygon([
            [top[0] - 0.25 * k, top[1] + 0.05 * k],
            [top[0], top[1] - 0.06 * k],
            [top[0] + 0.25 * k, top[1] + 0.05 * k],
          ]),
          L.P.robe,
          L.P.robeDark,
          0.01,
          mix(L.P.robe, "#ffffff", 0.3),
        );
        for (let i = -2; i <= 2; i++)
          line(
            g,
            GOLD,
            0.008,
            top[0] + i * 0.1 * k,
            top[1] + 0.06 * k,
            top[0] + i * 0.1 * k,
            top[1] + 0.11 * k,
          );
      };
    }
    case "zither":
      // 周瑜's zither, slung across his back.
      return (g) => {
        const a = P.p(at(-0.15, -0.06, -0.12));
        const b = P.p(at(0.17, 0.24, -0.12));
        cel(g, capsule(a, b, 0.04 * k, 0.035 * k), "#6a4a2a", "#3e2a16", 0.008, "#8e6a44", LW2);
        line(g, "#efe8d8", 0.005, a[0], a[1], b[0], b[1]);
      };
    case "banners":
      // 于禁's banners on his back.
      return (g) => {
        for (const s of [-1, 1]) {
          const bot = P.p(at(s * 0.07, 0.05, -0.12));
          const top = P.p(at(s * 0.12, -0.5, -0.12));
          haft(g, bot, top, 0.01 * k);
          cel(
            g,
            polygon([
              top,
              [top[0] + s * 0.11 * k, top[1] + 0.03 * k],
              [top[0] + s * 0.1 * k, top[1] + 0.16 * k],
              [top[0], top[1] + 0.15 * k],
            ]),
            L.P.robe,
            L.P.robeDark,
            0.008,
            mix(L.P.robe, "#ffffff", 0.3),
            LW2,
          );
        }
      };
    case "ladder":
      // 樂進, first up the walls: a short scaling ladder.
      return (g) => {
        const rails = [-0.07, 0.07].map(
          (x) => [P.p(at(x, 0.15, -0.13)), P.p(at(x * 1.2, -0.42, -0.13))] as const,
        );
        for (const [a, b] of rails) haft(g, a, b, 0.01 * k);
        for (let i = 1; i < 5; i++) {
          const t = i / 5;
          const [a0, a1] = rails[0] as readonly [Pt, Pt];
          const [b0, b1] = rails[1] as readonly [Pt, Pt];
          inkLine(
            g,
            WOOD,
            0.01 * k,
            a0[0] + (a1[0] - a0[0]) * t,
            a0[1] + (a1[1] - a0[1]) * t,
            b0[0] + (b1[0] - b0[0]) * t,
            b0[1] + (b1[1] - b0[1]) * t,
          );
        }
      };
    default:
      return undefined;
  }
}

// ---------------------------------------------------------------- trails

/** The strike's trail: drawn over everything, light and fast. */
export function smearItem(L: SpriteLook, J: Joints, P: Proj): Item | null {
  const S = J.smear;
  if (!S) return null;
  const k = J.scale;
  const reachOf = (): number => {
    switch (L.grip) {
      case "pole":
        return L.weapon === "glaive" || L.weapon === "snake" ? 0.86 : 0.76;
      case "twin":
        return 0.62;
      case "one":
        return 0.44;
      case "fan":
        return 0.22;
      default:
        return 0.1;
    }
  };
  return {
    d: 100,
    f: (g) => {
      const R = reachOf() * k;
      const hand = J.haR;
      const fromHand = S.fromHand ?? hand;
      const dir = J.wDir ?? J.az;
      if (S.kind === "arc" || S.kind === "gust") {
        const a = P.p(add(fromHand, mul(S.fromDir, R)));
        const b = P.p(add(hand, mul(dir, R)));
        const piv = P.p(J.shR);
        const mx = (a[0] + b[0]) / 2;
        const my = (a[1] + b[1]) / 2;
        const ox = mx - piv[0];
        const oy = my - piv[1];
        const ol = Math.hypot(ox, oy) || 1;
        const bulge = Math.hypot(b[0] - a[0], b[1] - a[1]) * 0.45;
        const c: Pt = [mx + (ox / ol) * bulge, my + (oy / ol) * bulge];
        const ci: Pt = [mx + (ox / ol) * bulge * 0.62, my + (oy / ol) * bulge * 0.62];
        const p = new Path2D();
        p.moveTo(a[0], a[1]);
        p.quadraticCurveTo(c[0], c[1], b[0], b[1]);
        p.quadraticCurveTo(ci[0], ci[1], a[0], a[1]);
        p.closePath();
        const col = S.kind === "gust" ? "#d8f0ff" : "#ffffff";
        g.save();
        g.fillStyle = alpha(col, S.kind === "gust" ? 0.5 : 0.72);
        g.fill(p);
        g.strokeStyle = alpha(S.kind === "gust" ? "#7ec8f0" : "#ffe9a8", 0.9);
        g.lineWidth = 0.012;
        g.stroke(p);
        g.restore();
        if (S.kind === "gust")
          for (let i = 0; i < 3; i++) {
            const t = 0.3 + i * 0.25;
            const q: Pt = [
              a[0] + (b[0] - a[0]) * t + (ox / ol) * 0.12 * k,
              a[1] + (b[1] - a[1]) * t + (oy / ol) * 0.12 * k,
            ];
            g.strokeStyle = alpha("#bfe6ff", 0.85);
            g.lineWidth = 0.012;
            g.beginPath();
            g.arc(q[0], q[1], 0.04 * k, 0, Math.PI * 1.5);
            g.stroke();
          }
        return;
      }
      if (S.kind === "thrust" || S.kind === "punch") {
        const tipV = S.kind === "punch" ? hand : add(hand, mul(dir, R));
        const tip = P.p(tipV);
        const back = P.p(add(tipV, mul(dir, -(S.kind === "punch" ? 0.3 : 0.5) * k)));
        const { n } = screenDir(P, tipV, dir);
        for (const o of [-1, 0, 1]) {
          const off = o * (S.kind === "punch" ? 0.045 : 0.04) * k;
          g.strokeStyle = alpha("#ffffff", o === 0 ? 0.9 : 0.6);
          g.lineWidth = o === 0 ? 0.02 : 0.012;
          g.beginPath();
          g.moveTo(back[0] + n[0] * off, back[1] + n[1] * off);
          g.lineTo(tip[0] + n[0] * off * 0.5, tip[1] + n[1] * off * 0.5);
          g.stroke();
        }
        // A flash at the point.
        const star = new Path2D();
        for (let i = 0; i < 8; i++) {
          const a = (i / 8) * Math.PI * 2;
          const r = (i % 2 ? 0.03 : 0.075) * k;
          const x = tip[0] + Math.cos(a) * r;
          const y = tip[1] + Math.sin(a) * r;
          if (i) star.lineTo(x, y);
          else star.moveTo(x, y);
        }
        star.closePath();
        g.fillStyle = alpha("#fff6c8", 0.95);
        g.fill(star);
        return;
      }
      if (S.kind === "shot") {
        const from = P.p(add(J.haL, mul(J.az, 0.08 * k)));
        const to = P.p(add(J.haL, mul(J.az, 0.45 * k)));
        g.strokeStyle = alpha("#ffffff", 0.85);
        g.lineWidth = 0.014;
        g.beginPath();
        g.moveTo(from[0], from[1]);
        g.lineTo(to[0], to[1]);
        g.stroke();
        dot(g, alpha("#fff6c8", 0.9), from[0], from[1], 0.03 * k);
      }
    },
  };
}
