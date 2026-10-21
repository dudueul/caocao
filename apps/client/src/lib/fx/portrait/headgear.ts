/**
 * Headgear, chosen per person (ADR 0070) and built on the head model so it
 * turns with the head. Office caps follow the Han dress treatise (續漢書
 * 輿服志): the 進賢冠 with ridges by rank, the 武冠 of military officials, the
 * 鶡冠 with its pheasant tails for 中郎將, the 貂蟬冠 of the 侍中 with its gold
 * cicada and sable tail, the 法冠 on its iron pillar, the emperor's 冕.
 * Cloth caps (帢, 幘 in their kinds, 幅巾, 綸巾, the yellow cloth), bands and
 * wraps, bare heads, hats, helmets and crowns. The `back` pass draws what
 * hangs behind the head (tails, ribbons, the far feather) before the body.
 */
import {
  alpha,
  cel,
  clamp,
  curve,
  dot,
  ellipse,
  feather,
  fill,
  furRoll,
  type G,
  INK,
  inside,
  LW,
  LW2,
  lamellae,
  line,
  mix,
  type Pt,
  path,
  polyPts,
  ribbon,
  rng,
  stroke,
} from "./draw.ts";
import { crownPt, hair, sideLocks, topknot } from "./hair.ts";
import { DEG, type Head } from "./head.ts";
import { has, type Look } from "./look.ts";
import {
  ACCENTS,
  BLACK_IRON,
  BLACK_IRON_DARK,
  BLACK_IRON_LIGHT,
  BRONZE,
  BRONZE_DARK,
  BRONZE_LIGHT,
  FEATHER_DARK,
  FELT,
  FELT_DARK,
  FELT_LIGHT,
  FUR,
  FUR_PALE,
  GAUZE,
  GAUZE_DARK,
  GAUZE_LIGHT,
  GAUZE_THREAD,
  GOLD,
  GOLD_DARK,
  GOLD_LIGHT,
  GREEN,
  GREEN_DARK,
  GREEN_LIGHT,
  HAN_RED_DARK,
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
  SEAL_JADE,
  SEAL_JADE_DARK,
  SEAL_JADE_LIGHT,
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
  TIGER_DARK,
  VERMILION,
  YELLOW,
  YELLOW_DARK,
  YELLOW_FOLD,
  YELLOW_LIGHT,
} from "./palette.ts";
import type { Hat } from "./types.ts";

type Prof = (y: number) => [number, number, number];
type Tri = readonly [string, string, string];

/**
 * A cap's profile over the skull: an elliptical shell k times the skull's
 * size, kv times as tall, rounder (q > 2) or more pointed (q < 2).
 */
function shell(H: Head, k: number, kv: number, q = 2, lift = 0): Prof {
  return (y) => {
    const u = Math.min(1, Math.abs((y + lift - H.yc) / (H.a * kv)));
    const s = (1 - u ** q) ** (1 / q);
    return [H.Rc * k * s, H.Fc * k * s, H.Bc * k * s];
  };
}

function shellTop(H: Head, kv: number, lift = 0): number {
  return H.yc - H.a * kv - lift;
}

/** Meridian lines over a cap, from its top to the brim, on the side facing us. */
function meridians(
  g: G,
  H: Head,
  prof: Prof,
  top: number,
  brim: number,
  step: number,
  color: string,
  w: number,
): void {
  for (let d = -180 + step / 2; d < 180; d += step) {
    const th = d * DEG;
    if (H.facing(th) < 0.05) continue;
    const pts: Pt[] = [];
    for (let i = 1; i <= 8; i++) {
      const y = top + ((brim - top) * i) / 8;
      const [a, f, b] = prof(y);
      const p = H.ring1(th, y, a, f, b);
      pts.push([p.x, p.y]);
    }
    g.strokeStyle = color;
    g.lineWidth = w;
    g.beginPath();
    g.moveTo((pts[0] as Pt)[0], (pts[0] as Pt)[1]);
    for (const q of pts) g.lineTo(q[0], q[1]);
    g.stroke();
  }
}

/** A band round the head between two levels, on a profile. */
function bandOn(H: Head, prof: Prof, y0: number, y1: number): Path2D {
  const [a0, f0, b0] = prof(y0);
  const [a1, f1, b1] = prof(y1);
  const top = H.ring(y0, a0, f0, b0);
  const bot = H.ring(y1, a1, f1, b1);
  return path((p) => {
    p.moveTo((top[0] as Pt)[0], (top[0] as Pt)[1]);
    for (const q of top) p.lineTo(q[0], q[1]);
    for (let i = bot.length - 1; i >= 0; i--) p.lineTo((bot[i] as Pt)[0], (bot[i] as Pt)[1]);
    p.closePath();
  });
}

/** The shadow a brim casts on the brow. */
function brimShadow(g: G, L: Look, y: number, depth = 7): void {
  const H = L.H;
  inside(g, H.outline(), () => {
    g.fillStyle = alpha(L.skin[1], 0.8);
    const pts = H.ring(y, H.Rc * 1.1, H.Fc * 1.1, H.Bc * 1.1);
    g.beginPath();
    g.moveTo((pts[0] as Pt)[0], (pts[0] as Pt)[1] - 2);
    for (const q of pts) g.lineTo(q[0], q[1] - 2);
    for (let i = pts.length - 1; i >= 0; i--)
      g.lineTo((pts[i] as Pt)[0], (pts[i] as Pt)[1] + depth);
    g.closePath();
    g.fill();
  });
}

/** Draw a flat ornament facing out from the head at azimuth θ: squashed by its turn. */
function facePlate(g: G, H: Head, th: number, y: number, k: number, draw: () => void): boolean {
  const p = H.proj(th, y, k, false);
  const c = H.facing(th);
  if (c < 0.12) return false;
  g.save();
  g.translate(p.x, p.y);
  g.transform(c, 0, 0, 1, 0, 0);
  draw();
  g.restore();
  return true;
}

/** The back direction on screen (unit x): where tails and tassels stream. */
function backDir(H: Head): number {
  return -(0.35 + 0.65 * Math.abs(H.s)) * (H.s >= 0 ? 1 : -1);
}

/** Chin cords from the cap down the cheeks, tied under the chin. */
function chinCords(g: G, L: Look, color: string, y0: number): void {
  const H = L.H;
  const chin = H.pt(0, H.ycb + 3);
  for (const sd of [-1, 1]) {
    const a = H.proj(sd * 84 * DEG, y0, 1.06, false);
    if (a.c < -0.1) continue;
    const m = H.pt(H.clampVisible(sd * 66 * DEG, 40), 44, 1.02);
    g.lineCap = "round";
    curve(g, alpha(INK, 0.85), 3, a.x, a.y, m[0], m[1], chin[0] + sd * 3, chin[1]);
    curve(g, color, 1.5, a.x, a.y, m[0], m[1], chin[0] + sd * 3, chin[1]);
  }
  cel(
    g,
    ellipse(chin[0], chin[1] + 2, 3.4, 2.6),
    color,
    mix(color, "#000000", 0.4),
    1,
    undefined,
    1.2,
  );
}

// ---------------------------------------------------------------- helmets

type HelmStyle =
  | "lam"
  | "band"
  | "lac"
  | "fur"
  | "gilt"
  | "open"
  | "black"
  | "steel"
  | "pelt"
  | "horn"
  | "yellow";

const HELM_OF: Partial<Record<Hat, HelmStyle>> = {
  helmLam: "lam",
  helmBand: "band",
  helmLac: "lac",
  helmFur: "fur",
  helmGilt: "gilt",
  helmOpen: "open",
  helmBlack: "black",
  helmSteel: "steel",
  helmPelt: "pelt",
  helmHorn: "horn",
  helmYellow: "yellow",
};

/** A helmet (兜鍪): a dome over cheek and neck flaps, a brow band, a finial and a tassel (纓). */
function helmet(g: G, L: Look, style: HelmStyle, back: boolean): void {
  const H = L.H;
  const { P } = L;
  const gilt =
    has(L, "gilt") ||
    style === "gilt" ||
    (L.tier2 && (L.fam === "cavalry" || L.fam === "infantry"));
  const officer = L.named || L.tier2;
  const brim = -26;
  const q =
    style === "band"
      ? 1.65
      : style === "steel"
        ? 1.45
        : style === "lac"
          ? 2.3
          : style === "black"
            ? 2.6
            : 2;
  const kv =
    style === "band"
      ? 1.14
      : style === "steel"
        ? 1.17
        : style === "open"
          ? 1.04
          : style === "lac"
            ? 1.02
            : 1.08;
  const k = style === "open" ? 1.1 : 1.13;
  const prof = shell(H, k, kv, q);
  const top = shellTop(H, kv);
  const [base, dark, light] =
    style === "steel"
      ? [STEEL, STEEL_DARK, STEEL_LIGHT]
      : style === "lac"
        ? [LACQUER, LACQUER_DARK, LACQUER_LIGHT]
        : style === "black" || has(L, "heavy")
          ? [BLACK_IRON, BLACK_IRON_DARK, BLACK_IRON_LIGHT]
          : [IRON, IRON_DARK, IRON_LIGHT];
  const metal: Tri = gilt ? [GOLD, GOLD_DARK, GOLD_LIGHT] : [BRONZE, BRONZE_DARK, BRONZE_LIGHT];
  const tasselCol =
    style === "steel" ? "#f1efe8" : style === "gilt" || style === "open" ? VERMILION : P.tassel;
  const tip = H.p3(0, top - 2, -2);
  const bx = backDir(H);
  if (back) {
    // The tassel streams back behind the head.
    if (style !== "horn" && style !== "pelt") {
      const tassel = path((p) => {
        p.moveTo(tip.x, tip.y - 6);
        p.quadraticCurveTo(tip.x + bx * 26, tip.y - 24, tip.x + bx * 40, tip.y - 8);
        p.quadraticCurveTo(tip.x + bx * 26, tip.y - 2, tip.x + bx * 44, tip.y + 12);
        p.quadraticCurveTo(tip.x + bx * 18, tip.y + 8, tip.x, tip.y + 2);
        p.closePath();
      });
      cel(g, tassel, tasselCol, mix(tasselCol, "#000000", 0.4), 4, mix(tasselCol, "#ffffff", 0.35));
    }
    return;
  }
  // Flaps: cheek flaps either side, the neck guard behind; an open helmet keeps only the neck guard.
  const flapProf: Prof = (y) => {
    const a = Math.max(H.rw(y), H.Rc * H.cran(Math.min(y, -2))) * 1.14;
    return [a, Math.max(H.df(y), H.Fc * 0.8) * 1.08, Math.max(H.db(y), H.Bc * 0.6) * 1.14];
  };
  const low = style === "black" ? 44 : 36;
  const flaps: Path2D[] = [];
  const neckFrom = style === "open" ? 100 : 112;
  for (const sd of [-1, 1]) {
    if (style !== "open") {
      const p = H.patch(sd * 58 * DEG, sd * 112 * DEG, brim - 2, low, flapProf);
      if (p) flaps.push(p);
    }
    const n = H.patch(sd * neckFrom * DEG, sd * 179 * DEG, brim, low + 4, flapProf);
    if (n) flaps.push(n);
  }
  for (const f of flaps)
    lamellae(g, f, P.lace, {
      x0: -120,
      x1: 120,
      y0: brim - 4,
      y1: low + 8,
      row: 10,
      plate: 7,
      sag: 3,
      base,
      dark,
      light,
    });
  // The dome.
  const dome = H.capBody(prof, top, brim);
  if (style === "band") {
    lamellae(g, dome, P.lace, {
      x0: -100,
      x1: 100,
      y0: top - 4,
      y1: brim + 4,
      row: 12,
      plate: 9,
      sag: -10,
      base,
      dark,
      light,
      lw: LW,
    });
  } else if (style === "pelt") {
    cel(g, dome, PELT, PELT_DARK, 9, PELT_LIGHT, LW);
    const r = rng(L.seed ^ 0x9e11);
    inside(g, dome, () => {
      // Rosettes of the leopard's coat.
      for (let i = 0; i < 26; i++) {
        const th = (-160 + r() * 320) * DEG;
        if (H.facing(th) < 0) continue;
        const y = top + 10 + r() * (brim - top - 12);
        const [a, f, b] = prof(y);
        const p = H.ring1(th, y, a, f, b);
        const s = 3 + r() * 2.5;
        g.strokeStyle = "#3a2410";
        g.lineWidth = 2;
        g.beginPath();
        g.arc(p.x, p.y, s, 0.3, Math.PI * 1.7);
        g.stroke();
        dot(g, alpha("#5a3818", 0.6), p.x, p.y, s * 0.5);
      }
    });
  } else {
    cel(g, dome, base, dark, 9, light, LW);
    inside(g, dome, () => {
      if (style === "lac") meridians(g, H, prof, top, brim, 24, alpha(LACQUER_RED, 0.95), 2.4);
      else meridians(g, H, prof, top, brim, 14, alpha("#1c1e22", 0.5), 1.4);
      const [a, f, b] = prof(top + 26);
      const hl = H.ring1(40 * DEG - H.phi * 0.4, top + 26, a, f, b);
      g.fillStyle = alpha(light, style === "steel" ? 0.85 : 0.5);
      g.beginPath();
      g.ellipse(hl.x, hl.y, 12, 6, -0.6, 0, Math.PI * 2);
      g.fill();
    });
  }
  // The brow band, or a roll of fur, or a yellow cloth.
  const band = bandOn(H, prof, brim - 13, brim);
  if (style === "fur") {
    const [a, f, b] = prof(brim - 4);
    const pts = H.ring(brim - 4, a * 1.02, f * 1.02, b * 1.02, 14);
    furRoll(g, pts, 10, FUR);
  } else if (style === "yellow") {
    cel(g, band, YELLOW, YELLOW_DARK, 4, YELLOW_LIGHT);
    inside(g, band, () => {
      const pts = H.ring(brim - 6, ...prof(brim - 6), 12);
      for (const [x, y] of pts)
        curve(g, alpha(YELLOW_FOLD, 0.85), 1.8, x + 4, y - 7, x, y, x - 4, y + 7);
    });
    // The knot at the side, its ends loose.
    const kn = H.proj(-100 * DEG, brim - 6, 1.16, false);
    if (kn.c > -0.4) {
      cel(
        g,
        ribbon([kn.x, kn.y], [kn.x - 14, kn.y + 14], [kn.x - 8, kn.y + 36], 10, 5),
        YELLOW,
        YELLOW_DARK,
        3,
        YELLOW_LIGHT,
      );
      cel(g, ellipse(kn.x, kn.y, 9, 8), YELLOW, YELLOW_DARK, 2, YELLOW_LIGHT);
    }
  } else if (gilt || officer) cel(g, band, metal[0], metal[1], 4, metal[2]);
  else if (style === "lac") cel(g, band, LACQUER_RED, "#6e2019", 4, "#c9584a");
  else cel(g, band, dark, "#33363c", 4, base);
  if (style === "lac" || style === "band" || style === "black") {
    const [a, f, b] = prof(brim - 6);
    for (const [x, y] of H.ring(brim - 6, a, f, b, 6).slice(1, -1))
      cel(g, ellipse(x, y, 2.4, 2.4), BRONZE, BRONZE_DARK, 1, undefined, 1);
  }
  // A ridge down the front for officers and gilt helmets.
  if ((officer || gilt) && style !== "band" && style !== "pelt" && style !== "fur") {
    const pts: Pt[] = [];
    const pts2: Pt[] = [];
    for (let i = 0; i <= 8; i++) {
      const y = top + 2 + ((brim - 12 - top - 2) * i) / 8;
      const [a, f, b] = prof(y);
      const p = H.ring1(-4 * DEG, y, a * 1.01, f * 1.01, b * 1.01);
      const q2 = H.ring1(5 * DEG, y, a * 1.01, f * 1.01, b * 1.01);
      pts.push([p.x, p.y]);
      pts2.push([q2.x, q2.y]);
    }
    if (H.facing(0) > 0.1)
      cel(g, polyPts([...pts, ...pts2.reverse()]), metal[0], metal[1], 2, metal[2], LW2);
  }
  // A horn crest curving forward from the top (文醜: our reading of the one-horned 獬豸).
  if (style === "horn") {
    const a = H.p3(0, top + 14, 10);
    const f = H.p3(0, top - 22, 52);
    const b = H.p3(0, top + 6, 92);
    const horn = ribbon([a.x, a.y], [f.x, f.y], [b.x, b.y], 22, 3, 16);
    cel(g, horn, "#e6dcc4", "#a99c80", 3, "#fbf5e6", LW);
    inside(g, horn, () => {
      for (let i = 1; i < 6; i++) {
        const u = i / 6;
        const x = a.x + (b.x - a.x) * u;
        const y = a.y + (f.y - a.y) * u * 1.4;
        line(g, alpha("#8a7c60", 0.7), 1.4, x - 6, y, x + 6, y - 3);
      }
    });
  }
  // A leopard's tail hanging from the crown of the pelt cover.
  if (style === "pelt") {
    const a = H.p3(0, top + 8, -30);
    const tail = ribbon([a.x, a.y], [a.x + bx * 30, a.y + 10], [a.x + bx * 30, a.y + 60], 13, 6);
    cel(g, tail, PELT, PELT_DARK, 3, PELT_LIGHT, LW2);
    inside(g, tail, () => {
      for (let i = 1; i < 6; i++) dot(g, "#3a2410", a.x + bx * (14 + i * 3), a.y + i * 10, 2.4);
    });
  }
  // Finial; steel helmets carry a tall spike.
  const fh = style === "steel" ? 22 : 10;
  cel(
    g,
    path((p) => p.roundRect(tip.x - 5, tip.y - fh, 10, fh + 2, 3)),
    metal[0],
    metal[1],
    2,
    metal[2],
  );
  if (style === "steel")
    cel(g, ellipse(tip.x, tip.y - fh - 3, 4, 4), metal[0], metal[1], 1, metal[2]);
  brimShadow(g, L, brim + 2);
}

// ---------------------------------------------------------------- court caps

/** The low black gauze cap under court caps (介幘), with a band. */
function gauzeCap(g: G, L: Look, brim = -38, kv = 1.0, q = 2.2, color = GAUZE): Prof {
  const H = L.H;
  const prof = shell(H, 1.07, kv, q);
  const top = shellTop(H, kv);
  const body = H.capBody(prof, top, brim);
  cel(g, body, color, GAUZE_DARK, 7, GAUZE_LIGHT, LW);
  inside(g, body, () => {
    for (let k = -160; k < 60; k += 6) line(g, alpha(GAUZE_THREAD, 0.18), 1, -90, k, 90, k - 40);
  });
  const band = bandOn(H, prof, brim - 9, brim);
  cel(g, band, "#3a3640", "#1c1a20", 3);
  brimShadow(g, L, brim + 2, 6);
  return prof;
}

/**
 * The 進賢冠: a front piece (展筩) of stiff black gauze rising from the brow
 * and leaning back, high at the front and low behind, its ribs (梁) one, two
 * or three by rank, set on the low gauze cap and pinned.
 */
function jinxian(g: G, L: Look, ridges: number, tall = 0): void {
  const H = L.H;
  const t = L.t;
  gauzeCap(g, L);
  const gilt = has(L, "gilt");
  const trim = gilt ? GOLD : "#a49eae";
  const W = H.W;
  const fz = 46 * W;
  const top = -124 - tall;
  const P3 = (lx: number, y: number, lz: number): Pt => {
    const p = H.p3(lx, y, lz);
    return [p.x, p.y];
  };
  // Corners: the front's bottom (wide, on the brow) and top (narrower, leaning back), the low back.
  const fb = (sd: number) => P3(sd * 25 * W, -46, fz);
  const ft = (sd: number) => P3(sd * 20 * W, top, fz - 28);
  const bt = (sd: number) => P3(sd * 20 * W, -100 - tall * 0.4, -26 * W);
  const bb = (sd: number) => P3(sd * 23 * W, -66, -34 * W);
  const gauzeFill = (pts: Pt[], a: number) => {
    const pn = polyPts(pts);
    g.fillStyle = alpha(GAUZE, a);
    g.fill(pn);
    inside(g, pn, () => {
      for (let k = -200; k < 60; k += 6) line(g, alpha(GAUZE_THREAD, 0.24), 1, -90, k, 90, k - 40);
    });
    stroke(g, INK, LW, pn);
    stroke(g, alpha(trim, 0.85), 1.4, pn);
  };
  // The far side, the front, the near side: by which side faces away.
  const near = H.s >= 0 ? -1 : 1;
  const far = -near;
  if (Math.abs(H.s) > 0.05) gauzeFill([fb(far), ft(far), bt(far), bb(far)], 0.5);
  gauzeFill([fb(-1), ft(-1), ft(1), fb(1)], 0.62);
  if (Math.abs(H.s) > 0.05) gauzeFill([fb(near), ft(near), bt(near), bb(near)], 0.7);
  // The top edge from the front back, and the ribs up the front and over the top.
  const tl = ft(-1);
  const tr = ft(1);
  for (let i = 0; i < ridges; i++) {
    const u = ridges === 1 ? 0 : -1 + (2 * i) / (ridges - 1);
    const lx = u * 12 * W;
    const a = P3(lx, -50, fz + 1);
    const b = P3(lx * 0.85, top, fz - 27);
    const c = P3(lx * 0.85, -100 - tall * 0.4, -24 * W);
    g.lineCap = "round";
    line(g, INK, 5.5, a[0], a[1], b[0], b[1], c[0], c[1]);
    line(g, trim, 2.4, a[0], a[1], b[0], b[1], c[0], c[1]);
    cel(g, ellipse(b[0], b[1] - 1, 3.6, 3), trim, mix(trim, "#000000", 0.4), 1, undefined, 1.4);
  }
  line(g, INK, 4, tl[0], tl[1], tr[0], tr[1]);
  line(g, trim, 1.6, tl[0], tl[1], tr[0], tr[1]);
  // A hairpin through the cap.
  const p0 = H.p3(-72 * W, -76, -8);
  const p1 = H.p3(72 * W, -76, -8);
  line(g, INK, 6, p0.x, p0.y, p1.x, p1.y);
  line(g, gilt ? GOLD_LIGHT : "#d8d2c0", 3, p0.x, p0.y, p1.x, p1.y);
  if (has(L, "clan")) chinCords(g, L, VERMILION, -40);
  else if (t.age >= 2 && L.seed % 2 === 0) chinCords(g, L, "#3a3640", -40);
}

/**
 * The 武冠 (武弁大冠): a tall shell of black lacquered gauze over the topknot,
 * squarish on top, the hair showing through, on a band, tied under the chin.
 */
function wuguan(g: G, L: Look): Prof {
  const H = L.H;
  const prof = shell(H, 1.1, 1.3, 3.2, 8);
  const top = shellTop(H, 1.3, 8);
  const brim = -36;
  const body = H.capBody(prof, top, brim);
  // The topknot shows through the gauze.
  topknot(g, L, null, "#000000", false, 1.1);
  g.fillStyle = alpha(GAUZE, 0.66);
  g.fill(body);
  inside(g, body, () => {
    for (let k = -200; k < 60; k += 5) line(g, alpha(GAUZE_THREAD, 0.25), 1, -100, k, 100, k - 50);
    for (let k = -200; k < 60; k += 5) line(g, alpha(GAUZE_THREAD, 0.12), 1, -100, k - 50, 100, k);
    const [a, f, b] = prof(top + 24);
    const hl = H.ring1(36 * DEG - H.phi * 0.4, top + 24, a, f, b);
    g.fillStyle = alpha(GAUZE_LIGHT, 0.7);
    g.beginPath();
    g.ellipse(hl.x, hl.y, 14, 8, -0.5, 0, Math.PI * 2);
    g.fill();
  });
  stroke(g, INK, LW, body);
  const band = bandOn(H, prof, brim - 10, brim);
  const gilt = has(L, "gilt");
  cel(
    g,
    band,
    gilt ? GOLD : "#2a2730",
    gilt ? GOLD_DARK : "#141218",
    3,
    gilt ? GOLD_LIGHT : undefined,
  );
  brimShadow(g, L, brim + 2, 6);
  chinCords(g, L, "#2a2730", brim - 2);
  return prof;
}

/** 鶡冠: the 武冠 with two pheasant tails standing up left and right (中郎將 and the guards). */
function heguan(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const top = shellTop(H, 1.26, 6);
  for (const sd of [-1, 1]) {
    const base = H.proj(sd * 80 * DEG, top + 34, 1.1, false);
    const behind = base.z < 0;
    if (behind !== back) continue;
    const out = base.x < H.proj(0, top + 30, 1, false).x ? -1 : 1;
    const b: Pt = [base.x, base.y];
    feather(
      g,
      b,
      [b[0] + out * 10, b[1] - 50],
      [b[0] + out * 24, b[1] - 100],
      15,
      "#c99a5a",
      FEATHER_DARK,
      10,
    );
  }
  if (!back) wuguan(g, L);
}

/** 貂蟬冠: the 武冠 with a gold cicada plaque at the front and a sable tail on the left (侍中). */
function diaochan(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const tailAt = H.proj(78 * DEG, -64, 1.14, false);
  const tail = () => {
    const a: Pt = [tailAt.x, tailAt.y];
    const dir = tailAt.x > 0 ? 1 : -1;
    const sable = ribbon(a, [a[0] + dir * 18, a[1] + 34], [a[0] + dir * 12, a[1] + 92], 16, 7);
    cel(g, sable, FUR[0], FUR[1], 3, FUR[2], LW2);
    inside(g, sable, () => {
      for (let i = 1; i < 8; i++)
        line(
          g,
          alpha(FUR[2], 0.7),
          1.3,
          a[0] + dir * 4,
          a[1] + i * 9,
          a[0] + dir * 10,
          a[1] + i * 9 + 6,
        );
    });
  };
  if (back) {
    if (tailAt.z < 0) tail();
    return;
  }
  wuguan(g, L);
  if (tailAt.z >= 0) tail();
  facePlate(g, H, 0, -64, 1.14, () => {
    const plaque = path((p) => {
      p.moveTo(0, -12);
      p.quadraticCurveTo(11, -12, 10, 2);
      p.quadraticCurveTo(8, 12, 0, 14);
      p.quadraticCurveTo(-8, 12, -10, 2);
      p.quadraticCurveTo(-11, -12, 0, -12);
      p.closePath();
    });
    cel(g, plaque, GOLD, GOLD_DARK, 3, GOLD_LIGHT, LW2);
    // The cicada: body and wings.
    cel(g, ellipse(0, 1, 3, 7), GOLD_DARK, undefined, 0, undefined, 1.2);
    curve(g, GOLD_DARK, 1.4, -2, -2, -8, 4, -5, 10);
    curve(g, GOLD_DARK, 1.4, 2, -2, 8, 4, 5, 10);
  });
}

/** 法冠 (獬豸冠): a tall gauze frame on an iron pillar curled at the top, for law officers. */
function faguan(g: G, L: Look): void {
  const H = L.H;
  gauzeCap(g, L, -38, 0.98, 2.4);
  const base = H.p3(0, -94, 18);
  const top = H.p3(0, -160, 26);
  const curl = H.p3(0, -168, 40);
  // Gauze panels either side of the pillar.
  for (const sd of [-1, 1]) {
    const a = H.p3(sd * 13 * H.W, -92, 18);
    const b = H.p3(sd * 11 * H.W, -150, 24);
    const pn = polyPts([
      [base.x, base.y],
      [a.x, a.y],
      [b.x, b.y],
      [top.x, top.y],
    ]);
    g.fillStyle = alpha(GAUZE, 0.6);
    g.fill(pn);
    stroke(g, INK, LW2, pn);
  }
  line(g, INK, 6, base.x, base.y, top.x, top.y);
  line(g, IRON_LIGHT, 2.6, base.x, base.y, top.x, top.y);
  g.lineCap = "round";
  curve(g, INK, 6, top.x, top.y, curl.x - 4, curl.y - 8, curl.x + 6, curl.y + 2);
  curve(g, IRON_LIGHT, 2.6, top.x, top.y, curl.x - 4, curl.y - 8, curl.x + 6, curl.y + 2);
}

/** Strings of round beads hanging from the edge a–b. */
function beadStrings(g: G, a: Pt, b: Pt, n: number, drop: number, bead: number): void {
  const cords = new Path2D();
  const rims = new Path2D();
  const bodies = new Path2D();
  const shines = new Path2D();
  for (let i = 0; i < n; i++) {
    const u = (i + 0.5) / n;
    const x = a[0] + (b[0] - a[0]) * u;
    const y = a[1] + (b[1] - a[1]) * u;
    cords.moveTo(x, y);
    cords.lineTo(x, y + drop);
    for (let yy = y + bead * 2; yy <= y + drop; yy += bead * 4.2) {
      rims.moveTo(x + bead + 0.9, yy);
      rims.arc(x, yy, bead + 0.9, 0, Math.PI * 2);
      bodies.moveTo(x + bead, yy);
      bodies.arc(x, yy, bead, 0, Math.PI * 2);
      shines.moveTo(x + 0.6 + bead * 0.45, yy - 0.8);
      shines.arc(x + 0.6, yy - 0.8, bead * 0.45, 0, Math.PI * 2);
    }
  }
  stroke(g, alpha("#2a2026", 0.85), 1.2, cords);
  g.fillStyle = INK;
  g.fill(rims);
  g.fillStyle = JADE_DARK;
  g.fill(bodies);
  g.fillStyle = JADE;
  g.fill(shines);
}

/**
 * The emperor's 冕: a board black above and red beneath (玄表纁裏), tilted
 * forward on a gauze cap; strings of white jade beads (旒) before and behind,
 * a jade pin, yellow silk balls (黈纊) by the ears.
 */
function mian(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const W = H.W;
  const fz = 60 * W;
  const bz = -58 * W;
  const hw = 42 * W;
  const fy = -110;
  const by = -126;
  const c = (lx: number, y: number, lz: number): Pt => {
    const p = H.p3(lx, y, lz);
    return [p.x, p.y];
  };
  const fn = c(-hw, fy, fz);
  const ff = c(hw, fy, fz);
  const bf = c(hw, by, bz);
  const bn = c(-hw, by, bz);
  const T = 6;
  if (back) {
    beadStrings(g, [bn[0], bn[1] + T], [bf[0], bf[1] + T], 7, 56, 2.4);
    return;
  }
  gauzeCap(g, L, -50, 1.08, 3);
  // The jade pin and the yellow silk balls.
  const p0 = c(-74 * W, -88, -4);
  const p1 = c(74 * W, -88, -4);
  line(g, INK, 6, p0[0], p0[1], p1[0], p1[1]);
  line(g, JADE, 3, p0[0], p0[1], p1[0], p1[1]);
  for (const [x0, y0] of [p0, p1]) {
    line(g, INK, 3.4, x0, y0, x0 + (x0 < 0 ? 8 : -2), 0);
    line(g, YELLOW_DARK, 1.6, x0, y0, x0 + (x0 < 0 ? 8 : -2), 0);
    cel(g, ellipse(x0 + (x0 < 0 ? 8 : -2), 6, 7, 7), YELLOW, YELLOW_DARK, 3, YELLOW_LIGHT);
  }
  const topF = polyPts([fn, ff, bf, bn]);
  cel(g, topF, MIAN_BLACK, "#0c0a0e", 5, "#4a4250", LW);
  cel(
    g,
    polyPts([fn, ff, [ff[0], ff[1] + T], [fn[0], fn[1] + T]]),
    MIAN_RED,
    HAN_RED_DARK,
    2,
    undefined,
    LW2,
  );
  const sideL = H.s >= 0 ? [bn, fn] : [ff, bf];
  cel(
    g,
    polyPts([
      sideL[0] as Pt,
      sideL[1] as Pt,
      [(sideL[1] as Pt)[0], (sideL[1] as Pt)[1] + T],
      [(sideL[0] as Pt)[0], (sideL[0] as Pt)[1] + T],
    ]),
    HAN_RED_DARK,
    "#3e110d",
    2,
    undefined,
    LW2,
  );
  beadStrings(g, [fn[0], fn[1] + T], [ff[0], ff[1] + T], 9, 64, 2.3);
}

// ---------------------------------------------------------------- cloth caps

/** The 帢 曹操 devised: a low soft silk cap, no ridges or flaps, a notch at the front, its colour the rank. */
function qia(g: G, L: Look): void {
  const H = L.H;
  const [c, cd, cl] = ACCENTS[L.t.accent ?? "black"];
  const prof = shell(H, 1.08, 1.03, 2.5);
  const top = shellTop(H, 1.03);
  const brim = -42;
  const body = H.capBody(prof, top, brim);
  cel(g, body, c, cd, 8, cl, LW);
  inside(g, body, () => {
    // Soft folds gathering toward the crown.
    for (const d of [-60, -25, 20, 55, 95]) {
      const th = d * DEG;
      if (H.facing(th) < 0.1) continue;
      const a = H.ring1(th, brim - 4, ...prof(brim - 4));
      const b = H.ring1(th * 0.6, top + 18, ...prof(top + 18));
      curve(g, alpha(cd, 0.8), 1.8, a.x, a.y, (a.x + b.x) / 2 + 4, (a.y + b.y) / 2, b.x, b.y);
    }
  });
  // The notch at the front: a seam and a small V cut at the brim.
  if (H.facing(0) > 0.15) {
    const s = H.ring1(0, top + 24, ...prof(top + 24));
    const m = H.ring1(0, brim - 1, ...prof(brim - 1));
    const l = H.ring1(-5 * DEG, brim - 1, ...prof(brim));
    const r = H.ring1(5 * DEG, brim - 1, ...prof(brim));
    line(g, alpha(cd, 1), 2, s.x, s.y, m.x, m.y - 8);
    const notch = polyPts([
      [l.x, l.y + 1],
      [m.x, m.y - 9],
      [r.x, r.y + 1],
    ]);
    fill(g, L.skin[0], notch);
    stroke(g, INK, LW2, notch);
  }
  brimShadow(g, L, brim + 2, 6);
}

/** 平上幘 and 赤幘: a cloth cap with a flat top and a raised back, on a band. */
function zeFlat(g: G, L: Look, col: Tri): Prof {
  const H = L.H;
  const prof = shell(H, 1.08, 1.12, 4, 4);
  const top = shellTop(H, 1.12, 4);
  const brim = -38;
  const body = H.capBody(prof, top, brim);
  cel(g, body, col[0], col[1], 7, col[2], LW);
  inside(g, body, () => {
    // The raised back (耳) shows as a seam rising behind.
    const a = H.ring1(-120 * DEG, brim - 10, ...prof(brim - 10));
    const b = H.ring1(-150 * DEG, top + 6, ...prof(top + 6));
    curve(g, alpha(col[1], 0.9), 2, a.x, a.y, a.x - 2, (a.y + b.y) / 2, b.x, b.y);
    // The flat top's edge.
    const ring = H.ring(top + 8, ...prof(top + 8), 16);
    g.strokeStyle = alpha(col[1], 0.8);
    g.lineWidth = 1.6;
    g.beginPath();
    g.moveTo((ring[0] as Pt)[0], (ring[0] as Pt)[1]);
    for (const q of ring) g.lineTo(q[0], q[1]);
    g.stroke();
  });
  const band = bandOn(H, prof, brim - 9, brim);
  cel(g, band, mix(col[0], "#000000", 0.25), mix(col[1], "#000000", 0.3), 3);
  brimShadow(g, L, brim + 2, 6);
  return prof;
}

/** 介幘: the cap with a peaked roof (屋) running front to back, civil officials' wear. */
function zeRoof(g: G, L: Look, col: Tri): void {
  const H = L.H;
  const prof = shell(H, 1.08, 1.0, 3);
  const top = shellTop(H, 1.0);
  const brim = -40;
  const body = H.capBody(prof, top, brim);
  cel(g, body, col[0], col[1], 7, col[2], LW);
  const band = bandOn(H, prof, brim - 9, brim);
  cel(g, band, mix(col[0], "#000000", 0.25), mix(col[1], "#000000", 0.3), 3);
  // The roof: a ridge above the crown, its two slopes down to the cap.
  const W = H.W;
  const rf = H.p3(0, top - 22, 26 * W);
  const rb = H.p3(0, top - 18, -40 * W);
  const sides = [-1, 1].map((sd) => {
    const f = H.p3(sd * 34 * W, top + 12, 24 * W);
    const b = H.p3(sd * 34 * W, top + 14, -38 * W);
    return {
      sd,
      z: H.p3(sd * 30, top, 0).z,
      pts: [
        [rf.x, rf.y],
        [rb.x, rb.y],
        [b.x, b.y],
        [f.x, f.y],
      ] as Pt[],
    };
  });
  sides.sort((a, b) => a.z - b.z);
  for (const s of sides)
    cel(
      g,
      polyPts(s.pts),
      s.sd > 0 ? col[0] : mix(col[0], "#000000", 0.25),
      col[1],
      3,
      s.sd > 0 ? col[2] : undefined,
      LW,
    );
  // The front gable.
  const fl = H.p3(-34 * W, top + 12, 24 * W);
  const fr = H.p3(34 * W, top + 12, 24 * W);
  if (Math.abs(fr.x - fl.x) > 6 && H.facing(0) > 0.2)
    cel(
      g,
      polyPts([
        [fl.x, fl.y],
        [rf.x, rf.y],
        [fr.x, fr.y],
      ]),
      mix(col[0], "#000000", 0.15),
      col[1],
      2,
      undefined,
      LW2,
    );
  brimShadow(g, L, brim + 2, 6);
}

/** 孫堅's red woollen 幘 (赤罽幘): a soft rounded cap of red felted wool with a rolled brim. */
function zeFelt(g: G, L: Look): void {
  const H = L.H;
  const prof = shell(H, 1.1, 1.05, 2.1, 2);
  const top = shellTop(H, 1.05, 2);
  const brim = -36;
  const body = H.capBody(prof, top, brim);
  cel(g, body, RED_CAP, RED_CAP_DARK, 8, RED_CAP_LIGHT, LW);
  const r = rng(L.seed ^ 0x3ed);
  inside(g, body, () => {
    // Felted wool: soft flecks.
    for (let i = 0; i < 160; i++) {
      const x = -80 + r() * 160;
      const y = top + r() * (brim - top);
      dot(g, alpha(i % 2 ? RED_CAP_LIGHT : RED_CAP_DARK, 0.4), x, y, 1);
    }
  });
  // The rolled brim.
  const [a, f, b] = prof(brim - 5);
  const pts = H.ring(brim - 5, a * 1.04, f * 1.04, b * 1.04, 20);
  const roll = path((p) => {
    for (const [x, y] of pts) {
      p.moveTo(x + 7, y);
      p.ellipse(x, y, 7, 6, 0, 0, Math.PI * 2);
    }
  });
  stroke(g, INK, 4, roll);
  fill(g, RED_CAP_DARK, roll);
  for (const [x, y] of pts) dot(g, RED_CAP, x + 1, y - 1.5, 5);
  brimShadow(g, L, brim + 4, 6);
}

/**
 * 幅巾: a width of cloth wrapped over the head, knotted at the back with its
 * ends hanging; `loose` worn pushed back off the brow (郭嘉, 陳登), `silk`
 * edged in gold (袁紹's 縑巾), dark (賈詡).
 */
function fujin(
  g: G,
  L: Look,
  back: boolean,
  col: Tri,
  opts: { loose?: boolean; trim?: string; tall?: number },
): void {
  const H = L.H;
  const lift = opts.loose ? 10 : 0;
  const kv = 1.08 + (opts.tall ?? 0);
  const prof = shell(H, 1.1, kv, 2.1, lift);
  const top = shellTop(H, kv, lift);
  const brim = opts.loose ? -54 : -42;
  const knot = H.proj(180 * DEG, -64, 1.14, false);
  const bx = backDir(H);
  if (back) {
    // The ends hanging down the back.
    for (const k of [0, 1]) {
      const a: Pt = [knot.x + bx * 4, knot.y + 4];
      const tail = ribbon(
        a,
        [a[0] + bx * (18 + k * 8), a[1] + 40],
        [a[0] + bx * (6 + k * 18), a[1] + 96 + k * 10],
        14,
        10,
      );
      cel(g, tail, col[0], col[1], 4, col[2]);
    }
    return;
  }
  const body = H.capBody(prof, top, brim);
  cel(g, body, col[0], col[1], 8, col[2], LW);
  inside(g, body, () => {
    // Folds sweeping back to the knot.
    for (let i = 0; i < 5; i++) {
      const th = (-50 + i * 30) * DEG;
      if (H.facing(th) < 0.1) continue;
      const a = H.ring1(th, brim - 3, ...prof(brim - 3));
      const b = H.ring1(-150 * DEG, -76 + i * 3, ...prof(-76));
      curve(
        g,
        alpha(col[1], 0.85),
        1.8,
        a.x,
        a.y,
        (a.x + b.x) / 2,
        Math.min(a.y, b.y) - 16,
        b.x,
        b.y,
      );
    }
  });
  if (opts.trim) {
    const band = bandOn(H, prof, brim - 6, brim);
    cel(g, band, opts.trim, mix(opts.trim, "#000000", 0.4), 2);
  }
  if (knot.z > -30) cel(g, ellipse(knot.x, knot.y, 12, 10, 0.4), col[0], col[1], 3, col[2]);
  brimShadow(g, L, brim + 2, 6);
}

/** 綸巾: a tall soft headcloth of dark silk, two long ribbons falling either side behind. */
function lunjin(g: G, L: Look, back: boolean): void {
  const H = L.H;
  if (back) {
    for (const sd of [-1, 1]) {
      const at = H.proj(sd * 104 * DEG, -70, 1.14, false);
      const out = at.x < H.proj(0, -70, 1, false).x ? -1 : 1;
      const a: Pt = [at.x, at.y];
      const rib = ribbon(a, [a[0] + out * 40, a[1] + 60], [a[0] + out * 34, a[1] + 190], 11, 9);
      cel(g, rib, SILK_CAP, SILK_DARK, 4, SILK_LIGHT);
    }
    return;
  }
  const knot = H.proj(170 * DEG, -84, 1.15, false);
  const prof = shell(H, 1.1, 1.36, 2.4, 6);
  const top = shellTop(H, 1.36, 6);
  const brim = -40;
  const body = H.capBody(prof, top, brim);
  cel(g, body, SILK_CAP, SILK_DARK, 8, SILK_LIGHT, LW);
  inside(g, body, () => {
    for (let i = 0; i < 6; i++) {
      const th = (-75 + i * 30) * DEG;
      if (H.facing(th) < 0.1) continue;
      const a = H.ring1(th, brim - 4, ...prof(brim - 4));
      const b = H.ring1(th * 0.4 + 170 * DEG * 0.3, top + 10, ...prof(top + 10));
      curve(
        g,
        alpha(i % 2 ? SILK_DARK : SILK_LIGHT, 0.8),
        1.8,
        a.x,
        a.y,
        (a.x + b.x) / 2 + 6,
        (a.y + b.y) / 2 - 8,
        b.x,
        b.y,
      );
    }
  });
  const band = bandOn(H, prof, brim - 11, brim);
  cel(g, band, mix(SILK_CAP, SILK_LIGHT, 0.45), SILK_DARK, 3, SILK_LIGHT);
  if (knot.z > -20)
    cel(g, ellipse(knot.x, knot.y, 13, 11, 0.4), SILK_CAP, SILK_DARK, 4, SILK_LIGHT);
  brimShadow(g, L, brim + 2, 6);
}

/** The Yellow Turbans' head-cloth (黃巾): wrapped over the crown and topknot, a twisted band, knotted behind. */
function yellowCloth(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const bx = backDir(H);
  const knot = H.proj(-120 * DEG * Math.sign(H.s || 1), -30, 1.16, false);
  if (back) {
    for (const k of [0, 1]) {
      const a: Pt = [knot.x, knot.y];
      const tail = ribbon(
        a,
        [a[0] + bx * (24 + k * 6), a[1] + 16 + k * 10],
        [a[0] + bx * (30 - k * 14), a[1] + 62 + k * 16],
        14 - k * 2,
        8,
      );
      cel(g, tail, YELLOW, YELLOW_DARK, 4, YELLOW_LIGHT);
    }
    return;
  }
  const prof = shell(H, 1.12, 1.06, 2);
  const top = shellTop(H, 1.06);
  const brim = -36;
  const body = H.capBody(prof, top, brim);
  cel(g, body, YELLOW, YELLOW_DARK, 9, YELLOW_LIGHT, LW);
  inside(g, body, () => {
    for (let i = 0; i < 5; i++) {
      const th = (-60 + i * 30) * DEG;
      if (H.facing(th) < 0.1) continue;
      const a = H.ring1(th, brim - 6, ...prof(brim - 6));
      const b = H.ring1(-140 * DEG, top + 20 + i * 4, ...prof(top + 20));
      curve(
        g,
        alpha(YELLOW_FOLD, 0.7),
        2.2,
        a.x,
        a.y,
        (a.x + b.x) / 2,
        Math.min(a.y, b.y) - 14,
        b.x,
        b.y,
      );
      curve(
        g,
        alpha(YELLOW_LIGHT, 0.8),
        1.4,
        a.x + 2,
        a.y - 3,
        (a.x + b.x) / 2 + 2,
        Math.min(a.y, b.y) - 17,
        b.x + 2,
        b.y - 3,
      );
    }
  });
  // The gathered cloth over the topknot.
  const [cx, cy] = crownPt(H, -4);
  cel(g, ellipse(cx, cy, 16, 11, -0.2), YELLOW, YELLOW_DARK, 4, YELLOW_LIGHT);
  const band = bandOn(H, prof, brim - 14, brim + 1);
  cel(g, band, mix(YELLOW, YELLOW_DARK, 0.2), YELLOW_DARK, 4, YELLOW_LIGHT, LW);
  inside(g, band, () => {
    for (const [x, y] of H.ring(brim - 6, ...prof(brim - 6), 12))
      curve(g, alpha(YELLOW_FOLD, 0.85), 2, x + 6, y - 9, x + 1, y - 1, x - 4, y + 9);
  });
  if (knot.z > -40)
    cel(g, ellipse(knot.x, knot.y, 12, 10, 0.5), YELLOW, YELLOW_DARK, 3, YELLOW_LIGHT);
  brimShadow(g, L, brim + 2, 6);
}

/** 張角's tall Taoist headcloth in yellow: a high square crown folded down the middle. */
function taoist(g: G, L: Look): void {
  const H = L.H;
  const prof = shell(H, 1.1, 1.42, 3.4, 8);
  const top = shellTop(H, 1.42, 8);
  const brim = -40;
  const body = H.capBody(prof, top, brim);
  cel(g, body, YELLOW, YELLOW_DARK, 9, YELLOW_LIGHT, LW);
  inside(g, body, () => {
    const a = H.ring1(0, top + 2, ...prof(top + 2));
    const b = H.ring1(0, brim - 8, ...prof(brim - 8));
    if (H.facing(0) > 0.1) {
      line(g, alpha(YELLOW_FOLD, 0.95), 2.4, a.x, a.y, b.x, b.y);
      line(g, alpha(YELLOW_LIGHT, 0.9), 1.4, a.x + 3, a.y, b.x + 3, b.y);
    }
    for (const d of [-55, 50]) {
      const th = d * DEG;
      if (H.facing(th) < 0.15) continue;
      const p = H.ring1(th, top + 6, ...prof(top + 6));
      const q = H.ring1(th, brim - 8, ...prof(brim - 8));
      line(g, alpha(YELLOW_FOLD, 0.6), 1.6, p.x, p.y, q.x, q.y);
    }
  });
  const band = bandOn(H, prof, brim - 10, brim);
  cel(g, band, YELLOW_DARK, YELLOW_FOLD, 3, YELLOW);
  // A talisman's red seal on the front.
  facePlate(g, H, 0, -78, 1.1, () => {
    cel(
      g,
      path((p) => p.roundRect(-6, -9, 12, 18, 2)),
      "#efe2c0",
      "#c9b88f",
      2,
      undefined,
      1.4,
    );
    line(g, "#b3262a", 1.6, 0, -6, 0, 6);
    line(g, "#b3262a", 1.6, -3, -2, 3, -2);
    line(g, "#b3262a", 1.6, -3, 3, 3, 3);
  });
  brimShadow(g, L, brim + 2, 6);
}

// ---------------------------------------------------------------- bands, wraps, scarves

/** A cloth band round the brow over a bare topknot, knotted at the side with its ends loose. */
function browBand(g: G, L: Look, col: Tri): void {
  const H = L.H;
  const prof = (y: number): [number, number, number] => H.skullAt(y, 1.07);
  const band = bandOn(H, prof, -50, -36);
  cel(g, band, col[0], col[1], 3, col[2], LW2);
  const kn = H.proj(-104 * DEG, -44, 1.1, false);
  if (kn.c > -0.6) {
    for (const k of [0, 1])
      cel(
        g,
        ribbon(
          [kn.x, kn.y],
          [kn.x - 16 - k * 6, kn.y + 8 + k * 6],
          [kn.x - 12 - k * 12, kn.y + 28 + k * 8],
          8,
          4,
        ),
        col[0],
        col[1],
        2,
        col[2],
      );
    cel(g, ellipse(kn.x, kn.y, 7, 6), col[0], col[1], 2, col[2]);
  }
}

/** A rough head-wrap tied low over the brow, frayed; white for 楊奉 (our reading of 白波). */
function headWrap(g: G, L: Look, back: boolean, col: Tri): void {
  const H = L.H;
  const bx = backDir(H);
  const knot = H.proj(180 * DEG, -36, 1.12, false);
  if (back) {
    for (const k of [0, 1]) {
      const a: Pt = [knot.x, knot.y];
      const tail = ribbon(
        a,
        [a[0] + bx * (22 + k * 8), a[1] + 14 + k * 8],
        [a[0] + bx * (24 - k * 14), a[1] + 58 + k * 10],
        13 - k * 2,
        9,
      );
      cel(g, tail, col[0], col[1], 4, col[2]);
    }
    return;
  }
  const prof = (y: number): [number, number, number] => H.skullAt(y, 1.08);
  const top: Pt[] = [];
  const bot = H.ring(-30, ...prof(-30), 20);
  const ring = H.ring(-60, ...prof(-60), 20);
  for (const [i, [x, y]] of ring.entries()) top.push([x, y + (i % 2 ? -3 : 3)]);
  const wrap = path((p) => {
    p.moveTo((top[0] as Pt)[0], (top[0] as Pt)[1]);
    for (const q of top) p.lineTo(q[0], q[1]);
    for (let i = bot.length - 1; i >= 0; i--) p.lineTo((bot[i] as Pt)[0], (bot[i] as Pt)[1]);
    p.closePath();
  });
  cel(g, wrap, col[0], col[1], 5, col[2], LW);
  inside(g, wrap, () => {
    for (const [i, [x, y]] of bot.entries())
      if (i % 4 === 2)
        curve(g, alpha(col[1], 0.8), 1.8, x + 3, y - 26, x - 4, y - 14, x + 2, y - 2);
  });
  if (knot.z > -40) cel(g, ellipse(knot.x, knot.y, 12, 10, 0.4), col[0], col[1], 3, col[2]);
  brimShadow(g, L, -28, 6);
}

/** A brocade headscarf (甘寧's), patterned in lozenges, knotted behind with long ends. */
function brocadeScarf(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const col = ACCENTS.brocade;
  const bx = backDir(H);
  const knot = H.proj(165 * DEG, -60, 1.14, false);
  if (back) {
    for (const k of [0, 1]) {
      const a: Pt = [knot.x, knot.y];
      cel(
        g,
        ribbon(
          a,
          [a[0] + bx * (24 + k * 10), a[1] + 30],
          [a[0] + bx * (14 + k * 20), a[1] + 110],
          13,
          8,
        ),
        col[0],
        col[1],
        4,
        col[2],
      );
    }
    return;
  }
  const prof = shell(H, 1.11, 1.08, 2.1);
  const top = shellTop(H, 1.08);
  const brim = -40;
  const body = H.capBody(prof, top, brim);
  cel(g, body, col[0], col[1], 8, col[2], LW);
  inside(g, body, () => {
    const thread = alpha(GOLD_LIGHT, 0.55);
    const lat = new Path2D();
    for (let k = -260; k < 260; k += 16) {
      lat.moveTo(k, -150);
      lat.lineTo(k + 120, -20);
      lat.moveTo(k + 120, -150);
      lat.lineTo(k, -20);
    }
    stroke(g, thread, 1.5, lat);
    for (let x = -100; x < 100; x += 16)
      for (let y = -142; y < -20; y += 16) dot(g, alpha("#5fb0b4", 0.8), x + 8, y + 8, 2.2);
  });
  const band = bandOn(H, prof, brim - 7, brim);
  cel(g, band, GOLD, GOLD_DARK, 2, GOLD_LIGHT);
  if (knot.z > -30) cel(g, ellipse(knot.x, knot.y, 13, 10, 0.4), col[0], col[1], 3, col[2]);
  brimShadow(g, L, brim + 2, 6);
}

/** 關羽's green headscarf: a hood of green cloth over the head and ears, knotted behind with two ends. */
function greenHood(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const bx = backDir(H);
  const knot = H.proj(170 * DEG, -58, 1.16, false);
  if (back) {
    for (const k of [0, 1]) {
      const a: Pt = [knot.x, knot.y];
      cel(
        g,
        ribbon(
          a,
          [a[0] + bx * (30 + k * 8), a[1] + 26 + k * 10],
          [a[0] + bx * (36 - k * 12), a[1] + 120 + k * 20],
          15,
          11,
        ),
        GREEN,
        GREEN_DARK,
        5,
        GREEN_LIGHT,
      );
    }
    return;
  }
  const prof: Prof = (y) => {
    if (y < -20) return shell(H, 1.14, 1.12, 2)(y);
    // Over the ears down to the jaw at the sides.
    const s = shell(H, 1.14, 1.12, 2)(-20);
    const u = clamp((y + 20) / 40, 0, 1);
    return [s[0] * (1 - u * 0.08), s[1] * (1 - u * 0.3), s[2] * (1 - u * 0.1)];
  };
  const top = shellTop(H, 1.12);
  const body = H.capBody(prof, top, -40);
  cel(g, body, GREEN, GREEN_DARK, 9, GREEN_LIGHT, LW);
  // The side pieces over the ears.
  for (const sd of [-1, 1]) {
    const side = H.patch(sd * 70 * DEG, sd * 150 * DEG, -42, 16, prof);
    if (side) cel(g, side, GREEN, GREEN_DARK, 6, GREEN_LIGHT, LW);
  }
  inside(g, body, () => {
    for (let i = 0; i < 4; i++) {
      const y = -50 - i * 14;
      const ring = H.ring(y, ...prof(y), 12);
      g.strokeStyle = alpha(GREEN_DARK, 0.85);
      g.lineWidth = 2;
      g.beginPath();
      g.moveTo((ring[0] as Pt)[0], (ring[0] as Pt)[1]);
      for (const q of ring) g.lineTo(q[0], q[1] + 4);
      g.stroke();
    }
  });
  if (knot.z > -40) cel(g, ellipse(knot.x, knot.y, 14, 12, 0.3), GREEN, GREEN_DARK, 4, GREEN_LIGHT);
  brimShadow(g, L, -38, 6);
}

// ---------------------------------------------------------------- hats

/** 笠: a wide straw hat over a cloth cap, the recluse's. */
function strawHat(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const R = 104 * H.W;
  const y = -76;
  const brimAll = path((p) => {
    for (let i = 0; i <= 48; i++) {
      const th = (-180 + i * 7.5) * DEG;
      const q = H.ring1(th, y, R, R, R, 0.28);
      if (i === 0) p.moveTo(q.x, q.y);
      else p.lineTo(q.x, q.y);
    }
    p.closePath();
  });
  if (back) {
    cel(g, brimAll, STRAW, STRAW_DARK, 6, STRAW_LIGHT, LW);
    inside(g, brimAll, () => {
      for (let i = 0; i < 36; i++) {
        const th = i * 10 * DEG;
        const a = H.ring1(th, y, R * 0.3, R * 0.3, R * 0.3, 0.28);
        const b = H.ring1(th, y, R, R, R, 0.28);
        line(g, alpha(STRAW_DARK, 0.6), 1.2, a.x, a.y, b.x, b.y);
      }
    });
    return;
  }
  // The front of the brim over the brow, then the crown.
  inside(
    g,
    path((p) => p.rect(-200, y - 4, 400, 200)),
    () => {
      cel(g, brimAll, STRAW, STRAW_DARK, 6, STRAW_LIGHT, LW);
    },
  );
  const apex = H.p3(0, -132, 0);
  const [a, f, b] = [R * 0.36, R * 0.36, R * 0.36];
  const base = H.ring(y - 2, a, f, b, 16, 0.28);
  const cone = path((p) => {
    p.moveTo(apex.x, apex.y);
    for (const q of base) p.lineTo(q[0], q[1]);
    p.closePath();
  });
  cel(g, cone, STRAW, STRAW_DARK, 6, STRAW_LIGHT, LW);
  inside(g, cone, () => {
    for (const [x, yy] of base) line(g, alpha(STRAW_DARK, 0.7), 1.2, apex.x, apex.y, x, yy);
  });
  // Ties under the chin.
  chinCords(g, L, "#6b5a3a", -40);
}

/** A 胡 warrior's tall pointed felt cap, its point bent back, over a shaggy fur brim. */
function huFelt(g: G, L: Look): void {
  const H = L.H;
  const bx = backDir(H);
  const brim = -40;
  const prof = shell(H, 1.08, 1.0, 2);
  const [a, f, b] = prof(brim);
  const ring = H.ring(brim, a, f, b, 16);
  const tip = H.p3(0, -150, -30);
  const felt = path((p) => {
    p.moveTo((ring[0] as Pt)[0], (ring[0] as Pt)[1]);
    const l = ring[0] as Pt;
    const r = ring[ring.length - 1] as Pt;
    p.quadraticCurveTo(l[0] - 6, -100, tip.x + bx * 10, tip.y);
    p.quadraticCurveTo(tip.x + bx * 18, tip.y + 12, tip.x + bx * 4, tip.y + 18);
    p.quadraticCurveTo(r[0] + 6, -100, r[0], r[1]);
    for (let i = ring.length - 1; i >= 0; i--) p.lineTo((ring[i] as Pt)[0], (ring[i] as Pt)[1]);
    p.closePath();
  });
  cel(g, felt, FELT, FELT_DARK, 8, FELT_LIGHT, LW);
  inside(g, felt, () => {
    curve(
      g,
      alpha(FELT_DARK, 0.95),
      2,
      (ring[12] as Pt)[0],
      brim - 4,
      (ring[12] as Pt)[0] - 4,
      -100,
      tip.x + bx * 8,
      tip.y + 8,
    );
  });
  furRoll(g, H.ring(brim - 2, a * 1.03, f * 1.03, b * 1.03, 14), 12, FUR);
  brimShadow(g, L, brim + 4, 8);
}

/** A hood of pale fur over the head and ears, a dark fur trim round the face (蘇僕延; our reading). */
function furHood(g: G, L: Look, back: boolean): void {
  const H = L.H;
  if (back) {
    // The hood's sides fall to the shoulders behind the jaw.
    const [l, r] = H.extentAt(10);
    const hood = path((p) => {
      p.moveTo(l - 14, -40);
      p.quadraticCurveTo(l - 26, 40, l - 8, 104);
      p.lineTo(r + 8, 104);
      p.quadraticCurveTo(r + 26, 40, r + 14, -40);
      p.closePath();
    });
    cel(g, hood, FUR_PALE[0], FUR_PALE[1], 8, FUR_PALE[2], LW);
    return;
  }
  const prof = shell(H, 1.2, 1.1, 2);
  const top = shellTop(H, 1.1);
  const body = H.capBody(prof, top, -46);
  cel(g, body, FUR_PALE[0], FUR_PALE[1], 9, FUR_PALE[2], LW);
  const r = rng(L.seed ^ 0x77);
  inside(g, body, () => {
    for (let i = 0; i < 70; i++) {
      const x = -90 + r() * 180;
      const y = top + r() * 60;
      line(g, alpha(FUR_PALE[1], 0.7), 1.3, x, y, x - 2, y + 6);
    }
  });
  // The trim round the face: brow, cheeks, under the chin.
  const pts: Pt[] = [];
  for (let i = 0; i <= 16; i++) {
    const u = -1 + (2 * i) / 16;
    const th = H.clampVisible(u * 88 * DEG, -40);
    const y = -48 + (1 - Math.cos((u * Math.PI) / 2)) * 60;
    pts.push(H.pt(th, y, 1.12, false));
  }
  furRoll(g, pts, 10, FUR);
}

// ---------------------------------------------------------------- crowns

/** A small crown over the topknot (小冠): gilt, or jade (周瑜). */
function crownSmall(g: G, L: Look, jade: boolean): void {
  const H = L.H;
  const [cx, cy0] = crownPt(H, -6);
  const cy = cy0 - 2;
  g.save();
  g.translate(cx, cy);
  g.scale(1.3, 1.3);
  g.translate(-cx, -cy);
  const c = jade
    ? (["#bcd8c4", "#7fa58c", "#eef7f0"] as const)
    : ([GOLD, GOLD_DARK, GOLD_LIGHT] as const);
  const cr = path((p) => {
    p.moveTo(cx - 18, cy + 8);
    p.lineTo(cx - 20, cy - 12);
    p.quadraticCurveTo(cx - 16, cy - 30, cx, cy - 30);
    p.quadraticCurveTo(cx + 16, cy - 30, cx + 20, cy - 12);
    p.lineTo(cx + 18, cy + 8);
    p.quadraticCurveTo(cx, cy + 14, cx - 18, cy + 8);
    p.closePath();
  });
  cel(g, cr, c[0], c[1], 5, c[2], LW);
  inside(g, cr, () => {
    line(g, alpha(c[1], 0.9), 2, cx, cy - 28, cx, cy + 10);
    curve(g, alpha(c[1], 0.9), 2, cx - 18, cy, cx, cy + 6, cx + 18, cy);
  });
  const p0 = H.p3(-30, cy + 2, 0);
  const p1 = H.p3(30, cy - 4, 0);
  line(g, INK, 5, p0.x, p0.y, p1.x, p1.y);
  line(g, jade ? "#f1ead9" : GOLD_LIGHT, 2.4, p0.x, p0.y, p1.x, p1.y);
  g.restore();
}

/** 呂布's gold hair crown (束髮金冠) with its two long pheasant tails (the novel, ch. 5). */
function crownTails(g: G, L: Look, back: boolean): void {
  const H = L.H;
  const [cx, cy] = crownPt(H, -10);
  if (back) {
    feather(
      g,
      [cx - 4, cy - 6],
      [cx - 70, cy - 24],
      [cx - 128, cy + 96],
      20,
      "#c08440",
      "#3a2614",
      18,
    );
    feather(
      g,
      [cx + 6, cy - 6],
      [cx + 80, cy - 26],
      [cx + 140, cy + 88],
      19,
      "#c08440",
      "#3a2614",
      18,
    );
    return;
  }
  const cr = path((p) => {
    p.moveTo(cx - 26, cy + 10);
    p.lineTo(cx - 28, cy - 18);
    p.lineTo(cx - 14, cy - 6);
    p.lineTo(cx, cy - 34);
    p.lineTo(cx + 14, cy - 6);
    p.lineTo(cx + 28, cy - 18);
    p.lineTo(cx + 26, cy + 10);
    p.quadraticCurveTo(cx, cy + 18, cx - 26, cy + 10);
    p.closePath();
  });
  cel(g, cr, GOLD, GOLD_DARK, 5, GOLD_LIGHT, LW);
  cel(g, ellipse(cx, cy - 2, 6, 6), "#b3263a", "#6a1022", 2, "#f08aa0");
  const p0 = H.p3(-40, cy + 6, 0);
  const p1 = H.p3(40, cy, 0);
  line(g, INK, 6, p0.x, p0.y, p1.x, p1.y);
  line(g, GOLD_LIGHT, 3, p0.x, p0.y, p1.x, p1.y);
}

/**
 * 袁術's crown for his empire of 仲 (our reading): a tall front of black gauze
 * on a gilt frame, a row of pearls, a gold mountain plaque with a great jewel,
 * and strings of pearls with jade pendants hanging from the pins.
 */
function crownZhong(g: G, L: Look): void {
  const H = L.H;
  gauzeCap(g, L, -40, 1, 2.4);
  facePlate(g, H, 0, -54, 1.18, () => {
    const shellP = path((p) => {
      p.moveTo(-34, 4);
      p.lineTo(-30, -78);
      p.quadraticCurveTo(0, -92, 30, -78);
      p.lineTo(34, 4);
      p.quadraticCurveTo(0, -6, -34, 4);
      p.closePath();
    });
    g.fillStyle = alpha(GAUZE, 0.9);
    g.fill(shellP);
    inside(g, shellP, () => {
      for (let i = 0; i < 5; i++) {
        const x = -26 + i * 13;
        line(g, INK, 4, x, 2, x * 0.95, -82);
        line(g, GOLD, 2, x, 2, x * 0.95, -82);
      }
    });
    stroke(g, INK, LW + 2, shellP);
    stroke(g, GOLD, 2.6, shellP);
    for (let i = 0; i < 9; i++) {
      const u = i / 8;
      const x = -30 + 60 * u;
      const y = -78 - Math.sin(u * Math.PI) * 10;
      cel(g, ellipse(x, y - 2, 3.2, 3.2), "#f6f1e6", "#c9bfae", 1.2, undefined, 1.2);
    }
    const hill = path((p) => {
      p.moveTo(-18, -4);
      p.lineTo(-16, -26);
      p.lineTo(-7, -20);
      p.lineTo(0, -50);
      p.lineTo(7, -20);
      p.lineTo(16, -26);
      p.lineTo(18, -4);
      p.quadraticCurveTo(0, -8, -18, -4);
      p.closePath();
    });
    cel(g, hill, GOLD, GOLD_DARK, 4, GOLD_LIGHT, LW);
    cel(g, ellipse(0, -18, 6, 7), "#b3263a", "#6a1022", 2, "#f08aa0", LW2);
  });
  for (const sd of [-1, 1]) {
    const pin = H.proj(sd * 90 * DEG, -64, 1.14, false);
    if (pin.c < -0.3) continue;
    const pearls = new Path2D();
    for (let yy = pin.y + 8; yy < pin.y + 84; yy += 7) {
      pearls.moveTo(pin.x + 3.2, yy);
      pearls.arc(pin.x, yy, 3.2, 0, Math.PI * 2);
    }
    stroke(g, INK, 2.4, pearls);
    fill(g, "#efe8da", pearls);
    cel(g, ellipse(pin.x, pin.y + 90, 7, 5), SEAL_JADE, SEAL_JADE_DARK, 2, SEAL_JADE_LIGHT, 1.8);
    cel(g, ellipse(pin.x, pin.y, 6, 6), "#3f8f6e", "#1f5a42", 2, "#9fe0c0", LW2);
  }
}

// ---------------------------------------------------------------- dispatch

/** Bare heads: the hair shows to the topknot, under a band, a wrap or a crown. */
const BARE = new Set<Hat>([
  "band",
  "wrap",
  "knot",
  "crownSmall",
  "crownJade",
  "crownTails",
  "shaved",
]);

function bandCol(L: Look): Tri {
  const a = L.t.accent;
  return a ? ACCENTS[a] : [L.P.tassel, L.P.robeDark, mix(L.P.tassel, "#ffffff", 0.35)];
}

/** The hair and, on a bare head, the topknot with its tie; drawn before the features. */
export function hairLayer(g: G, L: Look): void {
  const hat = L.hat;
  const t = L.t;
  const bare = BARE.has(hat);
  hair(g, L, bare);
  if (!bare || hat === "shaved") return;
  switch (hat) {
    case "band": {
      const c = bandCol(L);
      topknot(g, L, c[0], c[1]);
      break;
    }
    case "wrap": {
      const c = wrapCol(L);
      topknot(g, L, c[0], c[1]);
      break;
    }
    case "knot": {
      const acc = t.accent ? ACCENTS[t.accent] : null;
      const tie =
        t.accent === "tiger"
          ? [TIGER, TIGER_DARK]
          : acc
            ? [acc[0], acc[1]]
            : [L.P.tassel, L.P.robeDark];
      const loose = has(L, "strand") && t.age >= 2;
      topknot(g, L, loose ? null : (tie[0] as string), tie[1] as string, loose, 1);
      if (t.accent === "tiger") {
        const [cx, cy] = crownPt(L.H, -6);
        for (let i = 0; i < 4; i++)
          line(g, "#2a1a12", 2.2, cx - 12 + i * 7, cy + 2, cx - 9 + i * 7, cy + 10);
      }
      break;
    }
    default:
      topknot(g, L, null, "#000000");
  }
}

/** What hangs behind the head; drawn before the body. */
export function headgearBack(g: G, L: Look): void {
  const hat = L.hat;
  const _H = L.H;
  const helm = HELM_OF[hat];
  if (helm) {
    helmet(g, L, helm, true);
    return;
  }
  switch (hat) {
    case "heguan":
      heguan(g, L, true);
      break;
    case "diaochan":
      diaochan(g, L, true);
      break;
    case "mian":
      mian(g, L, true);
      break;
    case "fujin":
      fujin(g, L, true, fujinCol(L), {});
      break;
    case "lunjin":
      lunjin(g, L, true);
      break;
    case "huangjin":
      yellowCloth(g, L, true);
      break;
    case "wrap":
      headWrap(g, L, true, wrapCol(L));
      break;
    case "brocade":
      brocadeScarf(g, L, true);
      break;
    case "hood":
      greenHood(g, L, true);
      break;
    case "li":
      strawHat(g, L, true);
      break;
    case "furHood":
      furHood(g, L, true);
      break;
    case "crownTails":
      crownTails(g, L, true);
      break;
    default:
      break;
  }
}

/** The 幅巾's cloth: undyed fine silk (縑), dark for 賈詡, figured for 田氏, hemp for a recluse. */
function fujinCol(L: Look): Tri {
  const a = L.t.accent;
  if (a === "black") return ["#262228", "#121014", "#4a4450"];
  if (a === "brocade") return ACCENTS.brocade;
  if (L.t.dress === "hemp") return [HEMP, HEMP_DARK, HEMP_LIGHT];
  return ["#e4d8bd", "#b9a988", "#f8f0dc"];
}

function wrapCol(L: Look): Tri {
  if (L.t.accent === "white") return ["#e9e4d8", "#b9b2a2", "#fbf8f0"];
  return [RAG, RAG_DARK, RAG_LIGHT];
}

/** The hair, then what goes on the head. */
export function headgear(g: G, L: Look): void {
  const hat = L.hat;
  const t = L.t;
  const accent = t.accent ? ACCENTS[t.accent] : null;
  if (hat === "shaved") {
    if (has(L, "locks")) sideLocks(g, L);
    return;
  }
  const helm = HELM_OF[hat];
  if (helm) {
    helmet(g, L, helm, false);
    return;
  }
  switch (hat) {
    case "jx1":
    case "jx2":
    case "jx3":
      jinxian(
        g,
        L,
        hat === "jx1" ? 1 : hat === "jx2" ? 2 : 3,
        L.t.crop === "high" && L.t.build === "lean" ? 8 : 0,
      );
      break;
    case "wuguan":
      wuguan(g, L);
      break;
    case "heguan":
      heguan(g, L, false);
      break;
    case "diaochan":
      diaochan(g, L, false);
      break;
    case "faguan":
      faguan(g, L);
      break;
    case "mian":
      mian(g, L, false);
      break;
    case "qia":
      qia(g, L);
      break;
    case "zeFlat":
      zeFlat(
        g,
        L,
        accent ??
          (L.fam === "archer"
            ? [L.P.robeDark, mix(L.P.robeDark, "#000000", 0.4), mix(L.P.robeDark, "#ffffff", 0.25)]
            : ["#2b2421", "#18120f", "#4a403a"]),
      );
      break;
    case "zeRed":
      zeFlat(g, L, [RED_CAP, RED_CAP_DARK, RED_CAP_LIGHT]);
      break;
    case "zeRoof":
      zeRoof(
        g,
        L,
        t.dress === "hemp" || t.dress === "servant"
          ? [HEMP, HEMP_DARK, HEMP_LIGHT]
          : ["#2b2421", "#18120f", "#4a403a"],
      );
      break;
    case "zeFelt":
      zeFelt(g, L);
      break;
    case "fujin":
      fujin(g, L, false, fujinCol(L), {
        loose: has(L, "strand"),
        ...(has(L, "gilt") ? { trim: GOLD } : {}),
      });
      break;
    case "lunjin":
      lunjin(g, L, false);
      break;
    case "huangjin":
      yellowCloth(g, L, false);
      break;
    case "taoist":
      taoist(g, L);
      break;
    case "band":
      browBand(g, L, bandCol(L));
      break;
    case "wrap":
      headWrap(g, L, false, wrapCol(L));
      break;
    case "brocade":
      brocadeScarf(g, L, false);
      break;
    case "hood":
      greenHood(g, L, false);
      break;
    case "knot":
      break;
    case "li":
      strawHat(g, L, false);
      break;
    case "huFelt":
      huFelt(g, L);
      break;
    case "furHood":
      furHood(g, L, false);
      break;
    case "crownSmall":
      crownSmall(g, L, false);
      break;
    case "crownJade":
      crownSmall(g, L, true);
      break;
    case "crownTails":
      crownTails(g, L, false);
      break;
    case "crownZhong":
      crownZhong(g, L);
      break;
    default:
      break;
  }
}
