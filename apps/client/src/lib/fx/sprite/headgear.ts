/**
 * Headgear for the figures (ADR 0073), after the portraits' (ADR 0070): the
 * same hat for the same man, simplified to the shapes that read at tile
 * size. Helmets take their outline from the portraits' helmet table (dome
 * height and shape, cheek flaps, neck guard, finial, tassel, band, material);
 * caps, cloths, wraps, hats and crowns are built on the head model, so each
 * turns with the head. `hatBack` draws what hangs behind the head before it.
 */

import { DYES } from "../portrait/headgear.ts";
import {
  ACCENTS,
  BLACK_IRON,
  BLACK_IRON_DARK,
  BLACK_IRON_LIGHT,
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  FELT,
  FELT_DARK,
  FELT_LIGHT,
  FUR,
  FUR_PALE,
  GAUZE,
  GAUZE_DARK,
  GAUZE_LIGHT,
  GOLD,
  GOLD_DARK,
  GOLD_LIGHT,
  GREEN,
  GREEN_DARK,
  GREEN_LIGHT,
  HEMP,
  HEMP_DARK,
  HEMP_LIGHT,
  IRON,
  IRON_DARK,
  IRON_LIGHT,
  JADE,
  JADE_DARK,
  LACQUER,
  LACQUER_DARK,
  LACQUER_LIGHT,
  LACQUER_RED,
  LEATHER,
  LEATHER_DARK,
  LEATHER_LIGHT,
  MIAN_BLACK,
  MIAN_RED,
  PELT,
  PELT_DARK,
  PELT_LIGHT,
  RAG,
  RAG_DARK,
  RAG_LIGHT,
  RED_CAP,
  RED_CAP_DARK,
  RED_CAP_LIGHT,
  SILK_CAP,
  SILK_DARK,
  SILK_LIGHT,
  STEEL,
  STEEL_DARK,
  STEEL_LIGHT,
  STRAW,
  STRAW_DARK,
  STRAW_LIGHT,
  TIGER,
  VERMILION,
  YELLOW,
  YELLOW_DARK,
  YELLOW_LIGHT,
} from "../portrait/palette.ts";
import {
  alpha,
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
  ribbon,
  type Shape,
  type Tri,
  trace,
} from "./draw.ts";
import type { Pt } from "./geom.ts";
import type { HeadSpace } from "./head.ts";
import { topknot } from "./head.ts";

// ---------------------------------------------------------------- shapes on the head

type Prof = (v: number) => number;

/** A shell's radius by height: k skull radii wide, kv as tall, rounder (q > 2) or more pointed. */
function shell(R: number, k: number, kv: number, q = 2): Prof {
  const top = R * k * kv;
  return (v) => {
    const u = Math.min(1, Math.abs(v / top));
    return R * k * (1 - u ** q) ** (1 / q);
  };
}

/** A cap's body from its top down to the brim: the hull of rings. */
function dome(H: HeadSpace, prof: Prof, top: number, brim: number, n = 7): Shape {
  const pts: Pt[] = [H.pt(0, top, 0)];
  for (let i = 1; i <= n; i++) {
    const v = top + ((brim - top) * i) / n;
    const r = prof(v);
    for (let j = 0; j < 20; j++) {
      const a = (j / 20) * Math.PI * 2;
      pts.push(H.pt(Math.sin(a) * r, v, Math.cos(a) * r));
    }
  }
  return hullShape(pts);
}

/** Points along a ring at height v, over the arc facing the viewer (wider by `extra`). */
function arc(H: HeadSpace, v: number, r: number, extra = 0, n = 16): Pt[] {
  const mid = H.facingTh();
  const out: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const a = mid - Math.PI / 2 - extra + ((Math.PI + 2 * extra) * i) / n;
    out.push(H.pt(Math.sin(a) * r, v, Math.cos(a) * r));
  }
  return out;
}

/** A band round the head between two heights, where it faces the viewer. */
function band(H: HeadSpace, v0: number, v1: number, r0: number, r1: number): Shape {
  return polygon([...arc(H, v0, r0), ...arc(H, v1, r1).reverse()]);
}

/** A patch of surface between azimuths and heights, and how far toward the viewer it lies. */
function patch(
  H: HeadSpace,
  th0: number,
  th1: number,
  v0: number,
  v1: number,
  r0: number,
  r1: number,
): { s: Shape; d: number } {
  const top: Pt[] = [];
  const bot: Pt[] = [];
  let d = 0;
  const n = 10;
  for (let i = 0; i <= n; i++) {
    const a = th0 + ((th1 - th0) * i) / n;
    top.push(H.pt(Math.sin(a) * r0, v0, Math.cos(a) * r0));
    bot.push(H.pt(Math.sin(a) * r1, v1, Math.cos(a) * r1));
    d += H.dep(Math.sin(a) * r1, v1, Math.cos(a) * r1);
  }
  return { s: polygon([...top, ...bot.reverse()]), d: d / (n + 1) };
}

/** A feather or tail from a point on the head, its tip at (du, dv) on the sheet. */
function plume(g: G, base: Pt, du: number, dv: number, w: number, col: Tri, tip?: string): void {
  const c: Pt = [base[0] + du * 0.25, base[1] + dv * 0.75];
  const b: Pt = [base[0] + du, base[1] + dv];
  const f = ribbon(base, c, b, w * 0.5, w);
  cel(g, f, col[0], col[1], 0.01, col[2], LW2);
  if (tip) {
    const t = ribbon(
      [base[0] + du * 0.62, base[1] + dv * 0.7],
      [base[0] + du * 0.8, base[1] + dv * 0.86],
      b,
      w * 1.02,
      w * 1.02,
    );
    inside(g, f, () => {
      g.fillStyle = tip;
      g.fill(t.p);
    });
  }
}

/** Which way the back of the head lies on the sheet (tails and tassels stream that way). */
function backX(H: HeadSpace): number {
  const a = H.pt(0, 0, -1);
  const b = H.pt(0, 0, 0);
  const dx = a[0] - b[0];
  return Math.abs(dx) < 0.25 * H.R ? -1 : Math.sign(dx);
}

/** Two cloth tails hanging from the back of the head. */
function tails(g: G, H: HeadSpace, col: Tri, v: number, length = 1.0, spread = 0.35): void {
  const R = H.R;
  const at = H.pt(0, v, -R * 1.02);
  const bx = backX(H);
  for (const k of [0, 1]) {
    const dx = bx * R * (spread + k * 0.25);
    cel(
      g,
      ribbon(
        at,
        [at[0] + dx, at[1] + R * 0.3],
        [at[0] + dx * 1.2, at[1] + R * length],
        R * 0.2,
        R * 0.16,
      ),
      k ? col[1] : col[0],
      k ? mix(col[1], "#000000", 0.2) : col[1],
      0.008,
      undefined,
      LW2,
    );
  }
}

// ---------------------------------------------------------------- helmets

/** Every helmeted figure's outline is the portraits' (HELMS); the sprite keeps the big shapes. */
function helmet(g: G, H: HeadSpace, back: boolean): void {
  const L = H.look;
  const S = L.helm;
  if (!S) return;
  const R = H.R;
  const style = S.style;
  const mat: Tri = L.marks.has("redlac")
    ? [LACQUER_RED, "#6e2019", "#d0685a"]
    : L.marks.has("hide") || S.mat === "hide"
      ? [LEATHER, LEATHER_DARK, LEATHER_LIGHT]
      : S.mat === "steel"
        ? [STEEL, STEEL_DARK, STEEL_LIGHT]
        : S.mat === "lac"
          ? [LACQUER, LACQUER_DARK, LACQUER_LIGHT]
          : S.mat === "black" || L.marks.has("heavy")
            ? [BLACK_IRON, BLACK_IRON_DARK, BLACK_IRON_LIGHT]
            : S.mat === "pelt"
              ? [PELT, PELT_DARK, PELT_LIGHT]
              : [IRON, IRON_DARK, IRON_LIGHT];
  const metal: Tri = L.gilt ? [GOLD, GOLD_DARK, GOLD_LIGHT] : [BRONZE, BRONZE_DARK, BRONZE_LIGHT];
  const tasselCol: Tri = (() => {
    const a = L.accent;
    if (a && L.accent !== ACCENTS.brocade) return a;
    if (style === "steel") return ["#f1efe8", "#c9c5ba", "#ffffff"];
    if (style === "gilt" || style === "open" || style === "plume")
      return [VERMILION, "#8e2614", "#f07a5c"];
    if (style === "black") return ["#2a2626", "#141212", "#55504e"];
    return [L.P.tassel, L.P.robeDark, mix(L.P.tassel, "#ffffff", 0.35)];
  })();
  const k = S.k * 1.04;
  const brim = style === "cloth" || style === "cap" ? -0.3 * R : -0.2 * R;
  const prof = shell(R, k, S.kv * 1.05, S.q);
  const top = -R * k * S.kv * 1.05;
  const flapR = R * k * 1.03;
  const low = S.cheeks === "short" ? 0.25 * R : S.cheeks === "hood" ? 0.9 * R : 0.48 * R;
  const neckLow = S.neck === "long" ? 0.62 * R : 0.38 * R;
  const pieces: { s: Shape; d: number; lam: boolean }[] = [];
  if (S.cheeks !== "none") {
    if (S.cheeks === "hood")
      pieces.push({
        ...patch(H, 0.9, Math.PI * 2 - 0.9, brim, low, flapR, flapR * 1.25),
        lam: true,
      });
    else
      for (const s of [-1, 1])
        pieces.push({ ...patch(H, s * 1.2, s * 1.85, brim, low, flapR, flapR * 1.04), lam: true });
  }
  if (S.neck !== "none" && S.cheeks !== "hood")
    pieces.push({
      ...patch(H, 2.1, Math.PI * 2 - 2.1, brim, neckLow, flapR, flapR * 1.08),
      lam: true,
    });
  // A dyed headcloth under a small helmet, falling behind the ears.
  const cloth: Tri | null =
    style === "cloth"
      ? L.accent && L.accent !== ACCENTS.brocade && L.accent !== ACCENTS.white
        ? L.accent
        : ((DYES[L.seed % DYES.length] as Tri) ?? DYES[0])
      : null;
  if (cloth)
    pieces.push({
      ...patch(H, 1.5, Math.PI * 2 - 1.5, brim, 0.62 * R, flapR, flapR * 1.08),
      lam: false,
    });
  for (const p of pieces) {
    if (back !== p.d < 0) continue;
    if (p.lam) lamellar(g, p.s, mat, L.P.lace);
    else if (cloth) cel(g, p.s, cloth[0], cloth[1], 0.015, cloth[2]);
  }
  if (back) {
    tassel(g, H, S.tassel, tasselCol, top, S.finial);
    if (style === "feather") for (const s of [-1, 1]) heTail(g, H, s, top, true);
    if (style === "tube" || style === "plume") tubePlume(g, H, style, top, true);
    return;
  }
  const d = dome(H, prof, top, brim);
  cel(g, d, mat[0], mat[1], 0.03, mat[2]);
  inside(g, d, () => {
    const crown = H.pt(0, top, 0);
    if (S.dome === "lines" || S.dome === "strips") {
      const mid = H.facingTh();
      for (let i = -3; i <= 3; i++) {
        const a = mid + i * 0.42;
        const r = prof(brim);
        const b = H.pt(Math.sin(a) * r, brim, Math.cos(a) * r);
        line(
          g,
          alpha(S.dome === "strips" ? IRON_DARK : mat[1], 0.9),
          S.dome === "strips" ? 0.02 : 0.009,
          crown[0],
          crown[1],
          b[0],
          b[1],
        );
      }
    } else if (S.dome === "lamellae" || S.dome === "ribs") {
      for (let i = 1; i <= 3; i++) {
        const v = top + ((brim - top) * i) / 4;
        const a = arc(H, v, prof(v));
        g.strokeStyle = S.dome === "ribs" ? LACQUER_RED : alpha("#16181c", 0.7);
        g.lineWidth = 0.01;
        g.beginPath();
        trace(g, a);
        g.stroke();
      }
    } else if (S.dome === "rivets") {
      for (const p of arc(H, brim - R * 0.12, prof(brim - R * 0.12), 0, 8))
        dot(g, mat[2], p[0], p[1], 0.008);
    }
    if (style === "pelt")
      for (let i = 0; i < 7; i++) {
        const p = H.pt(...H.sph(H.facingTh() + (i - 3) * 0.4, top * (0.3 + (i % 3) * 0.2), R * k));
        dot(g, PELT_DARK, p[0], p[1], 0.012);
      }
  });
  // The brow band.
  const bandCol: Tri | null =
    S.band === "metal"
      ? metal
      : S.band === "plain"
        ? [mat[1], mix(mat[1], "#000000", 0.3), mat[0]]
        : S.band === "red"
          ? [LACQUER_RED, "#6e2019", "#d0685a"]
          : S.band === "yellow"
            ? [YELLOW, YELLOW_DARK, YELLOW_LIGHT]
            : null;
  if (bandCol)
    cel(
      g,
      band(H, brim - R * 0.16, brim + R * 0.02, prof(brim - R * 0.16) * 1.03, prof(brim) * 1.04),
      bandCol[0],
      bandCol[1],
      0.01,
      bandCol[2],
      LW2,
    );
  if (S.band === "fur")
    furRoll(g, arc(H, brim - R * 0.05, prof(brim) * 1.05, 0.2, 10), R * 0.12, FUR);
  if (cloth)
    cel(
      g,
      band(H, brim, brim + R * 0.1, prof(brim) * 1.04, prof(brim) * 1.06),
      cloth[0],
      cloth[1],
      0.008,
      cloth[2],
      LW2,
    );
  // The finial: a knob, or a tall spike.
  const crown = H.pt(0, top, 0);
  if (S.finial === "knob")
    cel(
      g,
      circle(crown[0], crown[1] - R * 0.08, R * 0.1),
      metal[0],
      metal[1],
      0.008,
      metal[2],
      LW2,
    );
  else if (S.finial === "spike") {
    const tip = H.pt(0, top - R * (style === "spike" ? 0.6 : 0.42), 0);
    cel(
      g,
      polygon([[crown[0] - R * 0.08, crown[1]], [crown[0] + R * 0.08, crown[1]], tip]),
      metal[0],
      metal[1],
      0.006,
      metal[2],
      LW2,
    );
  }
  if (style === "horn")
    for (const s of [-1, 1]) {
      const b = H.pt(s * R * 0.8, top * 0.55, R * 0.1);
      const tip: Pt = [b[0] + s * R * 0.45, b[1] - R * 0.65];
      cel(
        g,
        ribbon(b, [b[0] + s * R * 0.5, b[1] - R * 0.1], tip, R * 0.22, R * 0.03),
        "#e8dcc0",
        "#b9a988",
        0.008,
        "#ffffff",
        LW2,
      );
    }
  if (style === "feather") for (const s of [-1, 1]) heTail(g, H, s, top, false);
  if (style === "tube" || style === "plume") tubePlume(g, H, style, top, false);
  if (S.tassel !== "none" && H.faceOn() > 0.5) tassel(g, H, S.tassel, tasselCol, top, S.finial);
}

/** Lamellae on a flap: the material, rows of plates, the side's lacing. */
function lamellar(g: G, s: Shape, mat: Tri, lace: string): void {
  cel(g, s, mat[0], mat[1], 0.02, mat[2]);
  inside(g, s, () => {
    const [x0, y0, x1, y1] = s.b;
    const rows = new Path2D();
    const laces = new Path2D();
    for (let y = y0 + 0.03; y < y1; y += 0.03) {
      rows.moveTo(x0, y);
      rows.lineTo(x1, y);
      for (let x = x0 + (Math.round(y * 100) % 2) * 0.012; x < x1; x += 0.05)
        laces.rect(x - 0.005, y - 0.009, 0.01, 0.01);
    }
    g.strokeStyle = alpha("#16181c", 0.6);
    g.lineWidth = 0.008;
    g.stroke(rows);
    g.fillStyle = lace;
    g.fill(laces);
  });
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke(s.p);
}

/** The tassel (纓) from the crown, streaming back. */
function tassel(g: G, H: HeadSpace, kind: string, col: Tri, top: number, finial: string): void {
  if (kind === "none") return;
  const R = H.R;
  const ty = top - (finial === "spike" ? R * 0.35 : R * 0.1);
  const at = H.pt(0, ty, -R * 0.05);
  const bx = backX(H);
  const long = kind === "long";
  cel(
    g,
    ribbon(
      at,
      [at[0] + bx * R * 0.55, at[1] - R * 0.15],
      [at[0] + bx * R * (long ? 0.85 : 0.6), at[1] + R * (long ? 0.85 : 0.25)],
      R * 0.28,
      R * 0.12,
    ),
    col[0],
    col[1],
    0.01,
    col[2],
    LW2,
  );
}

/** Two upright 鶡 tails on the crown (雙鶡尾，豎左右): white, dark-tipped. */
function heTail(g: G, H: HeadSpace, s: number, top: number, back: boolean): void {
  const R = H.R;
  const p = H.sph(s * 1.1, top * 0.45, R * 1.05);
  if (back !== H.dep(...p) < 0) return;
  const base = H.pt(...p);
  const out = Math.sign(base[0] - H.pt(0, 0, 0)[0]) || s;
  plume(g, base, out * R * 0.35, -R * 1.25, R * 0.26, ["#ece5d6", "#a89e8a", "#ffffff"], "#3b2d24");
}

/** A tube on the crown holding one long pheasant tail, or a tall horsehair plume. */
function tubePlume(g: G, H: HeadSpace, style: string, top: number, back: boolean): void {
  const R = H.R;
  const bx = backX(H);
  const base = H.pt(0, top - R * 0.05, -R * 0.1);
  if (style === "tube") {
    if (back)
      plume(
        g,
        [base[0], base[1] - R * 0.3],
        bx * R * 0.9,
        -R * 0.9,
        R * 0.18,
        ["#c08440", "#7a4e22", "#e0b070"],
        "#3a2614",
      );
    else
      cel(
        g,
        polygon([
          [base[0] - R * 0.07, base[1]],
          [base[0] + R * 0.07, base[1]],
          [base[0] + R * 0.06, base[1] - R * 0.35],
          [base[0] - R * 0.06, base[1] - R * 0.35],
        ]),
        GOLD,
        GOLD_DARK,
        0.006,
        GOLD_LIGHT,
        LW2,
      );
    return;
  }
  if (!back) return;
  const red: Tri = [VERMILION, "#8e2614", "#f07a5c"];
  plume(g, base, bx * R * 0.25, -R * 0.9, R * 0.42, red);
  plume(g, [base[0] + bx * R * 0.2, base[1] - R * 0.8], bx * R * 0.7, R * 0.6, R * 0.4, red);
}

function furRoll(g: G, pts: Pt[], r: number, col: Tri): void {
  for (const [x, y] of pts) cel(g, circle(x, y, r), col[1], undefined, 0, undefined, LW);
  for (const [i, [x, y]] of pts.entries()) {
    cel(g, circle(x + r * 0.15, y - r * 0.2, r * 0.8), col[0], undefined, 0, undefined, 0);
    if (i % 2 === 0) dot(g, col[2], x + r * 0.3, y - r * 0.4, r * 0.25);
  }
}

// ---------------------------------------------------------------- caps and cloths

/** A cap's body with an optional band below, as a cel-shaded shape. */
function cap(
  g: G,
  H: HeadSpace,
  col: Tri,
  o: { k?: number; kv?: number; q?: number; brim?: number; band?: Tri | null; lw?: number } = {},
): { prof: Prof; top: number; brim: number; s: Shape } {
  const R = H.R;
  const k = o.k ?? 1.06;
  const kv = o.kv ?? 1;
  const prof = shell(R, k, kv, o.q ?? 2.2);
  const top = -R * k * kv;
  const brim = (o.brim ?? -0.25) * R;
  const s = dome(H, prof, top, brim);
  cel(g, s, col[0], col[1], 0.03, col[2], o.lw ?? LW);
  if (o.band)
    cel(
      g,
      band(H, brim - R * 0.13, brim + R * 0.02, prof(brim - R * 0.13) * 1.03, prof(brim) * 1.04),
      o.band[0],
      o.band[1],
      0.008,
      o.band[2],
      LW2,
    );
  return { prof, top, brim, s };
}

const GAUZE3: Tri = [GAUZE, GAUZE_DARK, GAUZE_LIGHT];

/** Gauze threads on a court cap. */
function mesh(g: G, s: Shape): void {
  inside(g, s, () => {
    const [x0, y0, x1, y1] = s.b;
    g.strokeStyle = alpha("#8d8798", 0.35);
    g.lineWidth = 0.006;
    for (let x = x0; x < x1; x += 0.025) {
      g.beginPath();
      g.moveTo(x, y0);
      g.lineTo(x + 0.04, y1);
      g.stroke();
    }
  });
}

/** 進賢冠: a black gauze cap and its tall front plate slanting back, ridged by rank. */
function jinxian(g: G, H: HeadSpace, ridges: number, back: boolean): void {
  const R = H.R;
  const quad = [
    H.pt(-R * 0.36, -R * 0.42, R * 0.85),
    H.pt(R * 0.36, -R * 0.42, R * 0.85),
    H.pt(R * 0.3, -R * 1.55, -R * 0.05),
    H.pt(-R * 0.3, -R * 1.55, -R * 0.05),
  ];
  const plateD = H.dep(0, -R, R * 0.4);
  const drawPlate = () => {
    const s = polygon(quad);
    cel(g, s, GAUZE, GAUZE_DARK, 0.012, GAUZE_LIGHT);
    mesh(g, s);
    inside(g, s, () => {
      for (let i = 0; i < ridges; i++) {
        const t = (i + 1) / (ridges + 1);
        const a = quad[0] as Pt;
        const b = quad[1] as Pt;
        const c = quad[2] as Pt;
        const d = quad[3] as Pt;
        line(
          g,
          alpha("#cfc8d8", 0.8),
          0.009,
          a[0] + (b[0] - a[0]) * t,
          a[1] + (b[1] - a[1]) * t,
          d[0] + (c[0] - d[0]) * t,
          d[1] + (c[1] - d[1]) * t,
        );
      }
    });
  };
  if (back) {
    if (plateD < 0) drawPlate();
    return;
  }
  const c = cap(g, H, GAUZE3, { k: 1.05, kv: 0.88, brim: -0.28 });
  mesh(g, c.s);
  cel(
    g,
    band(
      H,
      c.brim - R * 0.14,
      c.brim + R * 0.02,
      c.prof(c.brim - R * 0.14) * 1.04,
      c.prof(c.brim) * 1.05,
    ),
    "#3a3440",
    GAUZE_DARK,
    0.006,
    undefined,
    LW2,
  );
  if (plateD >= 0) drawPlate();
}

/** 武冠: a boxy cage of black gauze over a band; 鶡冠 adds the two pheasant tails, 貂蟬冠 a cicada and a sable tail. */
function wuguan(g: G, H: HeadSpace, extra: "he" | "diao" | null, back: boolean): void {
  const R = H.R;
  if (back) {
    if (extra === "he") for (const s of [-1, 1]) heTail(g, H, s, -R * 1.1, true);
    if (extra === "diao") {
      const p = H.sph(1.3, -R * 0.4, R * 1.1);
      if (H.dep(...p) < 0) sable(g, H.pt(...p), H);
    }
    return;
  }
  const c = cap(g, H, GAUZE3, { k: 1.1, kv: 1.05, q: 3.2, brim: -0.26 });
  mesh(g, c.s);
  cel(
    g,
    band(
      H,
      c.brim - R * 0.16,
      c.brim + R * 0.02,
      c.prof(c.brim - R * 0.16) * 1.04,
      c.prof(c.brim) * 1.05,
    ),
    "#3a3440",
    GAUZE_DARK,
    0.006,
    undefined,
    LW2,
  );
  if (extra === "he") for (const s of [-1, 1]) heTail(g, H, s, -R * 1.1, false);
  if (extra === "diao") {
    const f = H.pt(0, -R * 0.6, R * 0.95);
    if (H.dep(0, -R * 0.6, R * 0.95) > 0)
      cel(g, circle(f[0], f[1], R * 0.12), GOLD, GOLD_DARK, 0.006, GOLD_LIGHT, LW2);
    const p = H.sph(1.3, -R * 0.4, R * 1.1);
    if (H.dep(...p) >= 0) sable(g, H.pt(...p), H);
  }
}

/** A sable's tail hanging from the side of the cap (貂). */
function sable(g: G, at: Pt, H: HeadSpace): void {
  const R = H.R;
  cel(
    g,
    ribbon(
      at,
      [at[0] + R * 0.1, at[1] + R * 0.4],
      [at[0] + R * 0.05, at[1] + R * 1.0],
      R * 0.18,
      R * 0.12,
    ),
    "#3a2c24",
    "#1e1612",
    0.008,
    "#6a5446",
    LW2,
  );
}

/** 法冠: a black cap on a tall narrow pillar. */
function faguan(g: G, H: HeadSpace): void {
  const R = H.R;
  const c = cap(g, H, GAUZE3, { k: 1.04, kv: 0.9, brim: -0.28 });
  mesh(g, c.s);
  const b = H.pt(0, c.top + R * 0.05, 0);
  const s = polygon([
    [b[0] - R * 0.12, b[1]],
    [b[0] + R * 0.12, b[1]],
    [b[0] + R * 0.09, b[1] - R * 0.9],
    [b[0] - R * 0.09, b[1] - R * 0.95],
  ]);
  cel(g, s, GAUZE, GAUZE_DARK, 0.006, GAUZE_LIGHT, LW2);
  line(g, IRON_LIGHT, 0.008, b[0], b[1], b[0], b[1] - R * 0.9);
}

/** 冕: the emperor's flat board, black above and red below, its bead strings hanging. */
function mian(g: G, H: HeadSpace, back: boolean): void {
  const R = H.R;
  const y = -R * 1.25;
  const corners = [
    [-R * 0.75, y + R * 0.08, R * 1.05],
    [R * 0.75, y + R * 0.08, R * 1.05],
    [R * 0.75, y - R * 0.04, -R * 0.95],
    [-R * 0.75, y - R * 0.04, -R * 0.95],
  ] as const;
  const strings = (front: boolean) => {
    const z = front ? R * 1.05 : -R * 0.95;
    const dy = front ? R * 0.08 : -R * 0.04;
    if (back === H.dep(0, y, z) >= 0) return;
    for (let i = 0; i <= 6; i++) {
      const u = -R * 0.66 + (R * 1.32 * i) / 6;
      const a = H.pt(u, y + dy, z);
      line(g, alpha("#2a1a12", 0.9), 0.006, a[0], a[1], a[0], a[1] + R * 0.55);
      for (let j = 1; j <= 3; j++)
        dot(g, j % 2 ? "#e8e0c8" : "#c0392b", a[0], a[1] + j * R * 0.17, R * 0.05);
    }
  };
  if (back) {
    strings(false);
    strings(true);
    return;
  }
  const c = cap(g, H, GAUZE3, { k: 1.02, kv: 0.92, brim: -0.28 });
  mesh(g, c.s);
  const top = H.pt(0, y, 0);
  line(g, INK, 0.03, top[0], top[1], H.pt(0, c.top, 0)[0], H.pt(0, c.top, 0)[1]);
  const board = polygon(corners.map((p) => H.pt(p[0], p[1], p[2])));
  cel(g, board, MIAN_BLACK, MIAN_RED, 0.02, "#4a4050", LW);
  strings(false);
  strings(true);
}

/** 帢: 曹操's soft silk cap, low and plain, in his own colour, a seam at the front. */
function qia(g: G, H: HeadSpace): void {
  const R = H.R;
  const col = H.look.accent ?? (ACCENTS.black as Tri);
  const c = cap(g, H, col, { k: 1.08, kv: 0.98, q: 2.5, brim: -0.34 });
  if (H.faceOn() > 0.2) {
    const a = H.pt(...H.sph(0, c.brim, c.prof(c.brim) * 1.01));
    const b = H.pt(0, c.top * 0.6, c.prof(c.top * 0.6) * 1.01);
    line(g, col[1], 0.012, a[0], a[1], b[0], b[1]);
    dot(g, "#eef2ea", a[0], a[1] - R * 0.1, R * 0.07);
  }
}

/** 幘: a cloth cap on a band, flat-topped (平上) or roofed (介), with a raised back. */
function ze(g: G, H: HeadSpace, col: Tri, roof: boolean, back: boolean): void {
  const R = H.R;
  // The raised back (耳): two lobes rising behind the crown.
  const ears = () => {
    for (const s of [-1, 1]) {
      const p = H.sph(Math.PI + s * 0.35, -R * 0.75, R * 0.9);
      if (back !== H.dep(...p) < 0) continue;
      const b = H.pt(...p);
      cel(
        g,
        polygon([
          [b[0] - R * 0.18, b[1] + R * 0.1],
          [b[0] + R * 0.18, b[1] + R * 0.1],
          [b[0] + R * 0.14, b[1] - R * 0.42],
          [b[0] - R * 0.14, b[1] - R * 0.38],
        ]),
        col[0],
        col[1],
        0.008,
        col[2],
        LW2,
      );
    }
  };
  if (back) {
    ears();
    return;
  }
  const c = cap(g, H, col, {
    k: 1.07,
    kv: 0.82,
    q: 3.4,
    brim: -0.28,
    band: [col[1], mix(col[1], "#000000", 0.3), col[0]],
  });
  if (roof) {
    const ridge = [H.pt(0, c.top - R * 0.25, R * 0.4), H.pt(0, c.top - R * 0.25, -R * 0.5)];
    const l = H.pt(-R * 0.8, c.top * 0.6, 0);
    const r = H.pt(R * 0.8, c.top * 0.6, 0);
    cel(
      g,
      hullShape([...ridge, l, r, H.pt(0, c.top * 0.6, R * 0.7)]),
      col[0],
      col[1],
      0.012,
      col[2],
      LW2,
    );
  }
  ears();
}

/** 孫堅's red wool cap (赤罽幘): rounded and fuzzy-edged. */
function zeFelt(g: G, H: HeadSpace): void {
  const R = H.R;
  const col: Tri = [RED_CAP, RED_CAP_DARK, RED_CAP_LIGHT];
  const c = cap(g, H, col, { k: 1.1, kv: 0.95, brim: -0.3 });
  for (const [x, y] of arc(H, c.brim, c.prof(c.brim) * 1.04, 0.2, 12))
    dot(g, RED_CAP_DARK, x, y, R * 0.07);
  for (const [x, y] of arc(H, c.brim - R * 0.03, c.prof(c.brim) * 1.04, 0.2, 12))
    dot(g, RED_CAP, x, y - 0.004, R * 0.05);
}

/** 幅巾 and 綸巾: a cloth wrapped over the head and knotted behind, its tails hanging. */
function wrapCloth(
  g: G,
  H: HeadSpace,
  col: Tri,
  back: boolean,
  o: { tails?: number; folds?: boolean; kv?: number } = {},
): void {
  const R = H.R;
  if (back) {
    if (H.dep(0, -R * 0.2, -R) < 0) tails(g, H, col, -R * 0.3, o.tails ?? 1.1);
    return;
  }
  const c = cap(g, H, col, { k: 1.09, kv: o.kv ?? 1.02, q: 2.2, brim: -0.26 });
  if (o.folds !== false)
    inside(g, c.s, () => {
      const top = H.pt(0, c.top * 0.8, -R * 0.3);
      for (const th of [-1, -0.5, 0, 0.5, 1]) {
        const a = H.facingTh() + th;
        const b = H.pt(Math.sin(a) * c.prof(c.brim), c.brim, Math.cos(a) * c.prof(c.brim));
        line(g, alpha(col[1], 0.8), 0.009, top[0], top[1], b[0], b[1]);
      }
    });
  if (H.dep(0, -R * 0.2, -R) >= 0) {
    const k = H.pt(0, -R * 0.35, -R * 1.05);
    cel(g, circle(k[0], k[1], R * 0.17), col[0], col[1], 0.008, col[2], LW2);
    tails(g, H, col, -R * 0.3, o.tails ?? 1.1);
  }
}

/** A band round the brow over the bare topknot, knotted behind. */
function browBand(g: G, H: HeadSpace, col: Tri, back: boolean, wide = false): void {
  const R = H.R;
  const v0 = wide ? -R * 0.55 : -R * 0.42;
  const v1 = -R * 0.18;
  if (back) {
    if (H.dep(0, v1, -R) < 0) tails(g, H, col, v1, 0.6, 0.25);
    return;
  }
  cel(
    g,
    band(H, v0, v1, R * 1.03 * Math.sqrt(1 - (v0 / R) ** 2) + R * 0.03, R * 1.05),
    col[0],
    col[1],
    0.01,
    col[2],
    LW2,
  );
  if (H.dep(0, v1, -R) >= 0) tails(g, H, col, v1, 0.6, 0.25);
}

/** 關羽's green hood: over the head and down behind to the shoulders. */
function greenHood(g: G, H: HeadSpace, back: boolean): void {
  const R = H.R;
  const col: Tri = [GREEN, GREEN_DARK, GREEN_LIGHT];
  const fall = patch(H, 1.25, Math.PI * 2 - 1.25, -R * 0.2, R * 0.95, R * 1.1, R * 1.25);
  if (back) {
    if (fall.d < 0) cel(g, fall.s, col[0], col[1], 0.02, col[2]);
    return;
  }
  if (fall.d >= 0) cel(g, fall.s, col[0], col[1], 0.02, col[2]);
  const c = cap(g, H, col, { k: 1.12, kv: 1.05, brim: -0.3 });
  inside(g, c.s, () => {
    for (let i = 1; i <= 3; i++) {
      const v = c.brim - (c.brim - c.top) * (i / 4);
      const a = arc(H, v, c.prof(v));
      g.strokeStyle = alpha(GREEN_DARK, 0.8);
      g.lineWidth = 0.009;
      g.beginPath();
      trace(g, a);
      g.stroke();
    }
  });
  for (const s of [-1, 1]) {
    const side = patch(H, s * 1.15, s * 1.85, c.brim, R * 0.55, R * 1.1, R * 1.12);
    if (side.d > -R * 0.1) cel(g, side.s, col[0], col[1], 0.015, col[2], LW2);
  }
}

/** 笠: a wide shallow straw hat. */
function strawHat(g: G, H: HeadSpace, back: boolean): void {
  if (back) return;
  const R = H.R;
  const brim = -R * 0.4;
  const pts: Pt[] = [H.pt(0, -R * 1.3, 0)];
  for (let j = 0; j < 28; j++) {
    const a = (j / 28) * Math.PI * 2;
    pts.push(H.pt(Math.sin(a) * R * 1.75, brim, Math.cos(a) * R * 1.75));
  }
  const s = hullShape(pts);
  cel(g, s, STRAW, STRAW_DARK, 0.03, STRAW_LIGHT);
  inside(g, s, () => {
    const apex = H.pt(0, -R * 1.3, 0);
    for (let j = 0; j < 14; j++) {
      const a = (j / 14) * Math.PI * 2;
      const b = H.pt(Math.sin(a) * R * 1.75, brim, Math.cos(a) * R * 1.75);
      line(g, alpha(STRAW_DARK, 0.6), 0.006, apex[0], apex[1], b[0], b[1]);
    }
  });
}

/** A Hu warrior's pointed felt cap with a fur brim. */
function huFelt(g: G, H: HeadSpace): void {
  const R = H.R;
  const c = cap(g, H, [FELT, FELT_DARK, FELT_LIGHT], { k: 1.06, kv: 1.55, q: 1.3, brim: -0.28 });
  furRoll(g, arc(H, c.brim, c.prof(c.brim) * 1.06, 0.2, 9), R * 0.13, FUR);
}

/** A hood of pale fur. */
function furHood(g: G, H: HeadSpace, back: boolean): void {
  const R = H.R;
  const fall = patch(H, 1.3, Math.PI * 2 - 1.3, -R * 0.2, R * 0.8, R * 1.12, R * 1.25);
  if (back) {
    if (fall.d < 0) cel(g, fall.s, FUR_PALE[0], FUR_PALE[1], 0.02, FUR_PALE[2]);
    return;
  }
  if (fall.d >= 0) cel(g, fall.s, FUR_PALE[0], FUR_PALE[1], 0.02, FUR_PALE[2]);
  const c = cap(g, H, FUR_PALE as Tri, { k: 1.14, kv: 1.04, brim: -0.22 });
  furRoll(g, arc(H, c.brim, c.prof(c.brim) * 1.08, 0.4, 11), R * 0.12, FUR_PALE);
}

/** A small crown on the topknot: gold, or jade with a pin. */
function crownSmall(g: G, H: HeadSpace, jade: boolean): void {
  const R = H.R;
  topknot(g, H, null);
  const col: Tri = jade ? [JADE, JADE_DARK, "#ffffff"] : [GOLD, GOLD_DARK, GOLD_LIGHT];
  const c = H.pt(0, -R * 1.02, -R * 0.12);
  cel(
    g,
    polygon([
      [c[0] - R * 0.26, c[1] + R * 0.12],
      [c[0] + R * 0.26, c[1] + R * 0.12],
      [c[0] + R * 0.22, c[1] - R * 0.2],
      [c[0] - R * 0.22, c[1] - R * 0.2],
    ]),
    col[0],
    col[1],
    0.006,
    col[2],
    LW2,
  );
  line(g, INK, 0.016, c[0] - R * 0.45, c[1] - R * 0.02, c[0] + R * 0.45, c[1] - R * 0.06);
  line(
    g,
    jade ? "#f4f0e0" : GOLD_LIGHT,
    0.009,
    c[0] - R * 0.45,
    c[1] - R * 0.02,
    c[0] + R * 0.45,
    c[1] - R * 0.06,
  );
}

/** 呂布's gold crown with two long pheasant tails sweeping up and back (the novel). */
function crownTails(g: G, H: HeadSpace, back: boolean): void {
  const R = H.R;
  const c = H.pt(0, -R * 1.02, -R * 0.1);
  if (back) {
    for (const s of [-1, 1])
      plume(
        g,
        [c[0] + s * R * 0.12, c[1] - R * 0.1],
        s * R * 1.25,
        -R * 1.5,
        R * 0.2,
        ["#c08440", "#7a4e22", "#e0b070"],
        "#3a2614",
      );
    return;
  }
  topknot(g, H, null);
  const cr = polygon([
    [c[0] - R * 0.34, c[1] + R * 0.14],
    [c[0] - R * 0.36, c[1] - R * 0.2],
    [c[0] - R * 0.16, c[1] - R * 0.06],
    [c[0], c[1] - R * 0.36],
    [c[0] + R * 0.16, c[1] - R * 0.06],
    [c[0] + R * 0.36, c[1] - R * 0.2],
    [c[0] + R * 0.34, c[1] + R * 0.14],
  ]);
  cel(g, cr, GOLD, GOLD_DARK, 0.008, GOLD_LIGHT, LW2);
  dot(g, "#b3263a", c[0], c[1] - R * 0.02, R * 0.07);
}

/** 袁術's tall gilded crown hung with pearls (our reading). */
function crownZhong(g: G, H: HeadSpace, back: boolean): void {
  const R = H.R;
  if (back) return;
  const c = cap(g, H, GAUZE3, {
    k: 1.06,
    kv: 1.0,
    brim: -0.28,
    band: [GOLD, GOLD_DARK, GOLD_LIGHT],
  });
  const b = H.pt(0, c.top * 0.5, R * 0.7);
  const front = polygon([
    [b[0] - R * 0.42, b[1] + R * 0.25],
    [b[0] + R * 0.42, b[1] + R * 0.25],
    [b[0] + R * 0.3, b[1] - R * 0.75],
    [b[0], b[1] - R * 0.95],
    [b[0] - R * 0.3, b[1] - R * 0.75],
  ]);
  if (H.faceOn() > -0.3) {
    cel(g, front, GOLD, GOLD_DARK, 0.012, GOLD_LIGHT);
    dot(g, "#3f7fb3", b[0], b[1] - R * 0.3, R * 0.11);
    for (const s of [-1, 1])
      for (let j = 0; j < 3; j++)
        dot(g, "#f4f0e0", b[0] + s * R * 0.5, b[1] + R * 0.15 + j * R * 0.16, R * 0.06);
  }
}

/** The yellow cloth (黃巾): over the crown and the topknot, a twisted band, knotted behind. */
function yellowCloth(g: G, H: HeadSpace, back: boolean): void {
  wrapCloth(g, H, [YELLOW, YELLOW_DARK, YELLOW_LIGHT], back, { tails: 0.9 });
}

/** A tall Taoist cloth cap, yellow, with a talisman. */
function taoist(g: G, H: HeadSpace): void {
  const R = H.R;
  const c = cap(g, H, [YELLOW, YELLOW_DARK, YELLOW_LIGHT], {
    k: 1.1,
    kv: 1.5,
    q: 3.2,
    brim: -0.28,
    band: [YELLOW_DARK, "#7a5a14", YELLOW],
  });
  if (H.faceOn() > 0.3) {
    const p = H.pt(0, c.top * 0.55, R * 0.95);
    cel(
      g,
      polygon([
        [p[0] - R * 0.11, p[1] - R * 0.16],
        [p[0] + R * 0.11, p[1] - R * 0.16],
        [p[0] + R * 0.11, p[1] + R * 0.16],
        [p[0] - R * 0.11, p[1] + R * 0.16],
      ]),
      "#efe2c0",
      "#c9b88f",
      0.004,
      undefined,
      LW2 * 0.7,
    );
    line(g, "#b3262a", 0.007, p[0], p[1] - R * 0.1, p[0], p[1] + R * 0.1);
  }
}

// ---------------------------------------------------------------- dispatch

function bandCol(H: HeadSpace): Tri {
  const L = H.look;
  return L.accent ?? [L.P.tassel, L.P.robeDark, mix(L.P.tassel, "#ffffff", 0.35)];
}

function fujinCol(H: HeadSpace): Tri {
  const L = H.look;
  if (L.accent === ACCENTS.black) return ["#262228", "#121014", "#4a4450"];
  if (L.accent === ACCENTS.brocade) return ACCENTS.brocade as Tri;
  if (L.coarse || L.marks.has("dust")) return [HEMP, HEMP_DARK, HEMP_LIGHT];
  return ["#e4d8bd", "#b9a988", "#f8f0dc"];
}

function zeCol(H: HeadSpace): Tri {
  const L = H.look;
  if (L.accent) return L.accent;
  if (L.fam === "archer")
    return [L.P.robeDark, mix(L.P.robeDark, "#000000", 0.4), mix(L.P.robeDark, "#ffffff", 0.25)];
  return ["#2b2421", "#18120f", "#4a403a"];
}

/** What hangs behind the head, drawn before it. */
export function hatBack(g: G, H: HeadSpace): void {
  const L = H.look;
  if (L.helm) {
    helmet(g, H, true);
    return;
  }
  switch (L.hat) {
    case "jx1":
    case "jx2":
    case "jx3":
      jinxian(g, H, Number(L.hat.slice(2)), true);
      break;
    case "heguan":
      wuguan(g, H, "he", true);
      break;
    case "diaochan":
      wuguan(g, H, "diao", true);
      break;
    case "mian":
      mian(g, H, true);
      break;
    case "zeFlat":
    case "zeRed":
      ze(g, H, L.hat === "zeRed" ? [RED_CAP, RED_CAP_DARK, RED_CAP_LIGHT] : zeCol(H), false, true);
      break;
    case "zeRoof":
      ze(
        g,
        H,
        L.coarse ? [HEMP, HEMP_DARK, HEMP_LIGHT] : ["#2b2421", "#18120f", "#4a403a"],
        true,
        true,
      );
      break;
    case "fujin":
      wrapCloth(g, H, fujinCol(H), true);
      break;
    case "lunjin":
      wrapCloth(g, H, [SILK_CAP, SILK_DARK, SILK_LIGHT], true, { tails: 1.5 });
      break;
    case "huangjin":
      yellowCloth(g, H, true);
      break;
    case "band":
      browBand(g, H, bandCol(H), true);
      break;
    case "wrap":
      browBand(
        g,
        H,
        L.accent === ACCENTS.white ? ["#e9e4d8", "#b9b2a2", "#fbf8f0"] : [RAG, RAG_DARK, RAG_LIGHT],
        true,
        true,
      );
      break;
    case "brocade":
      browBand(g, H, ACCENTS.brocade as Tri, true, true);
      break;
    case "hood":
      greenHood(g, H, true);
      break;
    case "furHood":
      furHood(g, H, true);
      break;
    case "crownTails":
      crownTails(g, H, true);
      break;
    case "li":
      strawHat(g, H, true);
      break;
    default:
      break;
  }
}

/** What sits on the head, drawn after it. */
export function hatFront(g: G, H: HeadSpace): void {
  const L = H.look;
  if (L.helm) {
    helmet(g, H, false);
    return;
  }
  switch (L.hat) {
    case "jx1":
    case "jx2":
    case "jx3":
      jinxian(g, H, Number(L.hat.slice(2)), false);
      break;
    case "wuguan":
      wuguan(g, H, null, false);
      break;
    case "heguan":
      wuguan(g, H, "he", false);
      break;
    case "diaochan":
      wuguan(g, H, "diao", false);
      break;
    case "faguan":
      faguan(g, H);
      break;
    case "mian":
      mian(g, H, false);
      break;
    case "qia":
      qia(g, H);
      break;
    case "zeFlat":
      ze(g, H, zeCol(H), false, false);
      break;
    case "zeRed":
      ze(g, H, [RED_CAP, RED_CAP_DARK, RED_CAP_LIGHT], false, false);
      break;
    case "zeRoof":
      ze(
        g,
        H,
        L.coarse ? [HEMP, HEMP_DARK, HEMP_LIGHT] : ["#2b2421", "#18120f", "#4a403a"],
        true,
        false,
      );
      break;
    case "zeFelt":
      zeFelt(g, H);
      break;
    case "fujin":
      wrapCloth(g, H, fujinCol(H), false);
      break;
    case "lunjin":
      wrapCloth(g, H, [SILK_CAP, SILK_DARK, SILK_LIGHT], false, { tails: 1.5 });
      break;
    case "huangjin":
      yellowCloth(g, H, false);
      break;
    case "taoist":
      taoist(g, H);
      break;
    case "band":
      browBand(g, H, bandCol(H), false);
      break;
    case "wrap":
      browBand(
        g,
        H,
        L.accent === ACCENTS.white ? ["#e9e4d8", "#b9b2a2", "#fbf8f0"] : [RAG, RAG_DARK, RAG_LIGHT],
        false,
        true,
      );
      break;
    case "brocade":
      browBand(g, H, ACCENTS.brocade as Tri, false, true);
      break;
    case "hood":
      greenHood(g, H, false);
      break;
    case "knot": {
      const tie = L.accent?.[0] === TIGER ? TIGER : (L.accent?.[0] ?? L.P.tassel);
      topknot(g, H, tie);
      break;
    }
    case "li":
      strawHat(g, H, false);
      break;
    case "huFelt":
      huFelt(g, H);
      break;
    case "furHood":
      furHood(g, H, false);
      break;
    case "crownSmall":
      crownSmall(g, H, false);
      break;
    case "crownJade":
      crownSmall(g, H, true);
      break;
    case "crownTails":
      crownTails(g, H, false);
      break;
    case "crownZhong":
      crownZhong(g, H, false);
      break;
    default:
      break;
  }
}
