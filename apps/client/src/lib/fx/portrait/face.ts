/**
 * The face on the head model: skin and its light, eyes, brows, nose, mouth,
 * ears, the lines of age and temper, and personal marks. Every feature is
 * placed by azimuth, so the far eye narrows and the nose moves with the turn.
 */
import {
  alpha,
  cel,
  clamp,
  curve,
  dot,
  ellipse,
  type G,
  INK,
  inside,
  LW,
  LW2,
  line,
  mix,
  type Pt,
  path,
  ribbon,
} from "./draw.ts";
import { DEG } from "./head.ts";
import { has, type Look } from "./look.ts";
import { BLOOD, LEATHER_DARK, MOUTH, SCLERA, SWEAT, TEETH } from "./palette.ts";
import type { BrowKind, EyeKind, Mood } from "./types.ts";

interface MoodShape {
  open: number;
  lower: number;
  inner: number;
  browIn: number;
  browOut: number;
}

export const MOOD_SHAPES: Record<Mood, MoodShape> = {
  calm: { open: 1, lower: 1, inner: 0, browIn: 0, browOut: 0 },
  smile: { open: 0.82, lower: -0.25, inner: 0, browIn: -3, browOut: -1 },
  angry: { open: 0.95, lower: 0.85, inner: 0.65, browIn: 8, browOut: -4 },
  grim: { open: 0.6, lower: 0.9, inner: 0.25, browIn: 3, browOut: 2 },
  hurt: { open: 0.8, lower: 0.95, inner: -0.25, browIn: -6, browOut: 3 },
};

/** Eye kinds: angular half-span (°), height, outer-corner lift, crease, lash weight, iris size, white below. */
const EYES: Record<
  EyeKind,
  {
    span: number;
    h: number;
    tilt: number;
    crease: boolean;
    lash: number;
    iris: number;
    low: number;
  }
> = {
  almond: { span: 14, h: 8, tilt: 1.5, crease: true, lash: 3.2, iris: 1, low: 0 },
  narrow: { span: 15, h: 5.2, tilt: 3, crease: false, lash: 3.4, iris: 0.95, low: 0 },
  phoenix: { span: 16, h: 5.4, tilt: 6.5, crease: true, lash: 3.6, iris: 0.9, low: 0 },
  round: { span: 13, h: 11, tilt: 0, crease: true, lash: 3.4, iris: 0.82, low: 0.25 },
  heavy: { span: 14, h: 6, tilt: -1, crease: true, lash: 3.8, iris: 1, low: 0 },
  hooded: { span: 14, h: 7, tilt: 1, crease: true, lash: 3.6, iris: 0.72, low: 0.45 },
  small: { span: 11, h: 5.6, tilt: 0.5, crease: false, lash: 3.2, iris: 0.85, low: 0 },
  large: { span: 15, h: 10, tilt: 1, crease: true, lash: 3.4, iris: 1.12, low: 0 },
  gentle: { span: 14, h: 7.6, tilt: -3, crease: true, lash: 2.8, iris: 1.05, low: 0 },
};

const EYE_AZ = 31 * DEG;

/** Eye spacing varies a little from man to man (28°–34° either side of the centre line). */
function eyeAz(L: Look): number {
  return EYE_AZ + ((L.seed % 7) - 3) * 0.9 * DEG;
}

/** The man's left eye: drawn as a patch for 夏侯惇. */
function patched(L: Look, side: number): boolean {
  return side > 0 && has(L, "patch");
}

// ---------------------------------------------------------------- skin

export function skin(g: G, L: Look): void {
  const H = L.H;
  const [sk, skd, skl] = L.skin;
  const head = H.outline();
  g.fillStyle = sk;
  g.fill(head);
  inside(g, head, () => {
    // Form shadow on the side away from the light (upper right).
    const top = H.ys[0] as number;
    const bot = H.ycb + 4;
    g.fillStyle = skd;
    g.beginPath();
    g.moveTo(-200, top - 10);
    for (let y = top; y <= bot; y += 4) {
      const x = H.terminator(Math.min(y, H.ycb - 1));
      // The shadow bends round the jaw toward the chin.
      const bend = y > H.yj ? ((y - H.yj) / (H.ycb - H.yj)) * 10 : 0;
      g.lineTo(x + bend, y);
    }
    g.lineTo(-200, bot + 20);
    g.closePath();
    g.fill();
    // Eye sockets beside the bridge, under the lip.
    for (const sd of [-1, 1]) {
      const p = H.proj(sd * 13 * DEG, -3);
      if (p.c > 0.1) {
        g.fillStyle = alpha(skd, sd < 0 ? 0.9 : 0.55);
        g.beginPath();
        g.ellipse(p.x, p.y, 5, 8, 0.2 * sd, 0, Math.PI * 2);
        g.fill();
      }
    }
    const lip = H.proj(0, H.my + 10);
    g.fillStyle = alpha(skd, 0.55);
    g.beginPath();
    g.ellipse(lip.x, lip.y, 6, 2.2, 0, 0, Math.PI * 2);
    g.fill();
    // Highlights where the face turns to the light: cheekbone, brow, chin.
    const th = clamp(42 * DEG - H.phi, -10 * DEG, 70 * DEG);
    const ck = H.proj(th, 13);
    const br = H.proj(th * 0.6, -24);
    const ch = H.proj(th * 0.4, H.ych + 2);
    g.fillStyle = alpha(skl, 0.9);
    g.beginPath();
    g.ellipse(ck.x, ck.y, 5, 8, 0.3, 0, Math.PI * 2);
    g.fill();
    g.beginPath();
    g.ellipse(br.x, br.y, 10, 4, -0.1, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = alpha(skl, 0.6);
    g.beginPath();
    g.ellipse(ch.x, ch.y, 5, 3, 0, 0, Math.PI * 2);
    g.fill();
  });
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.lineJoin = "round";
  g.stroke(head);
}

// ---------------------------------------------------------------- eyes

function eye(g: G, L: Look, side: number, closed: boolean): void {
  const H = L.H;
  const t = L.t;
  const k = EYES[t.eye];
  const m = MOOD_SHAPES[L.mood];
  const size = (t.eyeSize / 100) * (1 - t.age * 0.03);
  const az = eyeAz(L);
  const thc = side * az;
  const centre = H.proj(thc, 0);
  if (centre.c < 0.12) return;
  const span = k.span * 1.18 * size * DEG;
  const pi = H.proj(side * (az - span * 0.85), 0);
  const po = H.proj(side * (az + span), 0);
  // Foreshortened toward the limb: narrower and a little shorter.
  const near = clamp(centre.c, 0, 1);
  const w = Math.abs(po.x - pi.x);
  if (w < 2.5) return;
  const dir = po.x > pi.x ? 1 : -1;
  const h = k.h * size * (0.82 + 0.18 * near);
  const xo = po.x;
  const yo = 1 - k.tilt * size * (0.8 + 0.2 * near);
  const xi = pi.x;
  const yi = 2;
  const x = (xo + xi) / 2;
  const y = 1;
  const skd = L.skin[1];
  if (closed) {
    curve(g, INK, k.lash, xo, yo + 1, x, y - h * 0.3, xi, yi);
    curve(g, alpha(skd, 1), 1.6, xo + dir * 2, yo + 5, x + dir * 2, y + 6, xi - dir * 2, y + 4);
    line(g, alpha(skd, 1), 1.4, xo + dir * 1, yo - 4, xo + dir * 6, yo - 7);
    return;
  }
  const squint = has(L, "squint") ? 0.7 : 1;
  const open = h * m.open * squint;
  const lower = m.lower + k.low * 0.6;
  const upper = path((p) => {
    p.moveTo(xo, yo);
    p.bezierCurveTo(
      xo - dir * w * 0.2,
      y - open * 1.55,
      xi + dir * w * 0.25,
      y - open * (1.3 - m.inner),
      xi,
      yi,
    );
  });
  const shape = new Path2D(upper);
  shape.quadraticCurveTo(x, y + h * 0.95 * lower + 1, xo, yo);
  shape.closePath();
  g.fillStyle = SCLERA;
  g.fill(shape);
  inside(g, shape, () => {
    const r = Math.min(h * 1.05 * k.iris, w * 0.34) * (0.86 + 0.14 * near);
    // The eyes look ahead of the face, a little toward the viewer; a sidelong glance looks away.
    const look = has(L, "sidelong") ? -0.26 : clamp(H.s * 0.3, -0.2, 0.2) + 0.04;
    const ix = x + w * look;
    // White below the iris (sanpaku) for the hooded and round eyes.
    const iy = y - open * (0.15 + k.low * 0.45);
    g.fillStyle = "#4b2d1f";
    g.beginPath();
    g.arc(ix, iy, r, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = "#24140e";
    g.beginPath();
    g.arc(ix, iy - r * 0.35, r * 0.95, Math.PI, Math.PI * 2);
    g.fill();
    g.fillStyle = "#120a07";
    g.beginPath();
    g.arc(ix, iy, r * 0.45, 0, Math.PI * 2);
    g.fill();
    g.strokeStyle = alpha("#6a3a28", 0.35);
    g.lineWidth = h * 0.8;
    g.stroke(upper);
    dot(
      g,
      "#ffffff",
      ix + r * 0.38,
      iy - r * 0.38,
      Math.max(1.4, r * (t.eye === "large" ? 0.4 : 0.3)),
    );
    dot(g, alpha("#ffffff", 0.7), ix - r * 0.35, iy + r * 0.4, Math.max(0.8, r * 0.14));
  });
  g.lineCap = "round";
  const lash = k.lash * (0.85 + 0.15 * near);
  g.strokeStyle = INK;
  g.lineWidth = t.eye === "heavy" ? lash * 1.3 : lash;
  g.stroke(upper);
  line(g, INK, k.lash * 0.8, xo, yo, xo + dir * 4, yo + (k.tilt > 4 ? -1 : 2.5));
  curve(g, alpha("#7a4630", 0.75), 1.5, xo - dir, yo + 1, x, y + h * 0.95 * lower + 1, xi, yi);
  if (k.crease && m.open > 0.7)
    curve(
      g,
      alpha(skd, 1),
      1.6,
      xo - dir * w * 0.1,
      yo - h * 0.9,
      x,
      y - open * 1.7 - 3,
      xi + dir * w * 0.2,
      yi - h * 0.75,
    );
  if (t.eye === "heavy" || t.eye === "hooded")
    // The heavy upper lid's fold.
    curve(
      g,
      alpha(skd, 1),
      2,
      xo - dir * w * 0.05,
      yo - h * 0.6,
      x,
      y - open * 1.35 - 4,
      xi,
      yi - h * 0.4,
    );
  if (t.age >= 3 || has(L, "bags"))
    curve(
      g,
      alpha(skd, 0.9),
      1.4,
      xo - dir * 2,
      yo + h * 0.9,
      x,
      y + h * 1.7,
      xi + dir * 2,
      yi + h * 0.6,
    );
}

export function eyes(g: G, L: Look): void {
  const closed = L.mood === "hurt";
  const H = L.H;
  const skd = L.skin[1];
  if (has(L, "deepset") || has(L, "brow")) {
    // Deep sockets in shadow under a heavy brow.
    for (const side of [-1, 1]) {
      const p = H.proj(side * eyeAz(L), -2);
      if (p.c < 0.15) continue;
      const w = 15 * (0.55 + 0.45 * p.c);
      g.fillStyle = has(L, "deepset") ? mix(skd, "#3a2418", 0.35) : alpha(skd, 0.85);
      g.beginPath();
      g.ellipse(
        p.x,
        p.y - 3,
        w * (has(L, "deepset") ? 1.15 : 1),
        has(L, "deepset") ? 13 : 9,
        0,
        0,
        Math.PI * 2,
      );
      g.fill();
    }
  }
  // The far eye first; the near eye, squeezed shut when hurt.
  const order = L.H.phi >= 0 ? [1, -1] : [-1, 1];
  for (const side of order) {
    if (patched(L, side)) continue;
    const near = side === order[1];
    eye(g, L, side, closed && near);
  }
}

// ---------------------------------------------------------------- brows

const BROWS: Record<BrowKind, { th: number; arch: number; tilt: number; y: number }> = {
  curved: { th: 1.15, arch: 4.5, tilt: 1, y: -1 },
  sword: { th: 1, arch: 1, tilt: 6, y: 0 },
  knit: { th: 1.05, arch: 1.5, tilt: 3, y: 2 },
  bushy: { th: 1.2, arch: 2, tilt: 2, y: 0 },
  thin: { th: 0.5, arch: 4, tilt: 2, y: -4 },
  low: { th: 1.25, arch: 0.5, tilt: 0, y: 3 },
  sparse: { th: 0.7, arch: 2, tilt: -3, y: 0 },
};

function brow(g: G, L: Look, side: number): void {
  const H = L.H;
  const t = L.t;
  const kind = BROWS[t.brow];
  const m = MOOD_SHAPES[L.mood];
  const c = H.facing(side * 30 * DEG);
  if (c < 0.05) return;
  const base = -16 + kind.y + (has(L, "brow") ? 3 : 0);
  const T = 6.5 * kind.th * (has(L, "brow") ? 1.35 : 1) * (0.75 + 0.25 * clamp(c, 0, 1));
  const knit = t.brow === "knit" ? 3 : 0;
  const frown = has(L, "frown") ? 2 : 0;
  const cocked = has(L, "cocked") && side > 0 ? -5 : 0;
  const yi = base + m.browIn + knit + frown + cocked * 0.4;
  const yo = base + m.browOut - kind.tilt + cocked;
  const d = eyeAz(L) - EYE_AZ;
  const pi = H.pt(side * ((9 - knit) * DEG + d * 0.6), yi);
  const pm = H.pt(side * (28 * DEG + d), base - kind.arch + cocked * 0.8);
  const po = H.pt(side * (50 * DEG + d), yo);
  const n = 10;
  const q2 = (a: number, b: number, cc: number, u: number) =>
    (1 - u) * (1 - u) * a + 2 * (1 - u) * u * b + u * u * cc;
  const thick = (u: number) => {
    switch (t.brow) {
      case "curved":
        return T * (0.55 + 0.65 * Math.sin(u * Math.PI));
      case "sword":
        return T * (1.05 - 0.8 * u);
      case "thin":
        return T * (0.8 + 0.4 * Math.sin(u * Math.PI));
      case "low":
        return T * (1 - 0.25 * u);
      default:
        return T * (1 - 0.35 * u);
    }
  };
  const lo: Pt[] = [];
  const hi: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const u = i / n;
    const x = q2(pi[0], pm[0], po[0], u);
    const y = q2(pi[1], pm[1], po[1], u);
    const w = thick(u);
    lo.push([x, y + w * 0.45]);
    let ty = y - w * 0.55;
    let tx = x;
    if ((t.brow === "bushy" || t.age >= 3) && i % 2 === 1 && i < n) {
      tx += (po[0] > pi[0] ? 1 : -1) * 4;
      ty -= 2.5 + w * 0.25;
    }
    hi.push([tx, ty]);
  }
  const [hc, hd] = L.hair;
  if (t.brow === "sparse") {
    // Thin separate hairs.
    for (let i = 0; i < n; i++) {
      const a = lo[i] as Pt;
      const b = hi[i + 1] as Pt;
      line(g, hc, 1.8, a[0], a[1], b[0] + (po[0] > pi[0] ? 2 : -2), b[1]);
    }
    return;
  }
  const p = path((pp) => {
    pp.moveTo((lo[0] as Pt)[0], (lo[0] as Pt)[1]);
    for (const q of lo) pp.lineTo(q[0], q[1]);
    for (let i = hi.length - 1; i >= 0; i--) pp.lineTo((hi[i] as Pt)[0], (hi[i] as Pt)[1]);
    pp.closePath();
  });
  g.fillStyle = hc;
  g.fill(p);
  if (L.t.hair === "white" || L.t.hair === "grey") {
    g.lineWidth = 1.2;
    g.strokeStyle = hd;
    g.stroke(p);
  }
}

export function brows(g: G, L: Look): void {
  for (const side of [-1, 1]) brow(g, L, side);
  if (has(L, "brow")) {
    // The heavy ridge catches the light above the brows.
    const H = L.H;
    const a = H.pt(-44 * DEG, -27);
    const m = H.pt(0, -30);
    const c = H.pt(44 * DEG, -27);
    curve(g, alpha(L.skin[2], 0.95), 4.4, a[0], a[1], m[0], m[1] - 3, c[0], c[1]);
    curve(g, INK, 1.6, a[0] - 2, a[1] - 3, m[0], m[1] - 7, c[0] + 2, c[1] - 3);
  }
  if (L.t.brow === "knit" || has(L, "frown") || L.mood === "angry") {
    // The crease between knitted brows.
    const H = L.H;
    const a = H.pt(-3 * DEG, -18);
    const b = H.pt(3 * DEG, -18);
    const c = alpha(L.skin[1], 1);
    line(g, c, 1.8, a[0], a[1], a[0] + 1, a[1] + 9);
    line(g, c, 1.4, b[0], b[1] + 1, b[0], b[1] + 8);
  }
}

// ---------------------------------------------------------------- nose

export function nose(g: G, L: Look): void {
  const H = L.H;
  const t = L.t;
  const [, skd, skl] = L.skin;
  const ny = H.ny;
  const tip = H.proj(0, ny);
  const root = H.proj(0, -6);
  const mid = H.proj(0, ny * 0.45);
  const tw = H.tip;
  const sg = Math.abs(H.s) < 0.1 ? 0 : Math.sign(H.s);
  if (has(L, "broken")) {
    // A bridge broken and badly set: a bump and a kink, a pale scar across it.
    const k = H.proj(0, ny * 0.4);
    g.lineCap = "round";
    curve(g, INK, 2.6, k.x - 3, k.y - 9, k.x + 7, k.y - 2, k.x + 2, k.y + 8);
    line(g, alpha("#a0523c", 0.95), 2.4, k.x - 8, k.y + 2, k.x + 9, k.y - 4);
    line(g, alpha("#f2c6a6", 0.95), 1.2, k.x - 8, k.y, k.x + 9, k.y - 6);
  }
  // Shadow down the shaded (left) side of the bridge, and under the nose.
  g.fillStyle = alpha(skd, 0.95);
  g.beginPath();
  g.moveTo(root.x - 3, root.y);
  g.quadraticCurveTo(mid.x - 4, mid.y, tip.x - 6 * tw, tip.y - 2);
  g.quadraticCurveTo(tip.x - 10 * tw, tip.y + 3, tip.x - 2, tip.y + 4);
  g.quadraticCurveTo(mid.x - 1, mid.y, root.x + 1, root.y);
  g.closePath();
  g.fill();
  g.beginPath();
  g.ellipse(tip.x - 2, tip.y + 6, 9 * tw, 2.6, 0, 0, Math.PI * 2);
  g.fill();
  // The profile line on the side the face turns to, or a soft tip from the front.
  if (sg !== 0) {
    if (t.nose === "hook") {
      const hk = H.proj(sg * 2 * DEG, ny * 0.5);
      curve(
        g,
        INK,
        LW2,
        root.x + sg * 2,
        root.y + 12,
        hk.x + sg * 2,
        hk.y,
        tip.x + sg * 2,
        tip.y - 2,
        tip.x + sg * 2,
        tip.y + 2,
        tip.x - sg * 2,
        tip.y + 3,
      );
    } else
      curve(
        g,
        INK,
        LW2,
        tip.x + sg * 1,
        tip.y - 7,
        tip.x + sg * 3.5,
        tip.y - 1,
        tip.x - sg * 1,
        tip.y + 3,
      );
  } else
    curve(g, INK, LW2, tip.x - 4 * tw, tip.y + 2, tip.x, tip.y + 5.5, tip.x + 4 * tw, tip.y + 2);
  // Wings: the left one always, the right one when the face looks at us.
  for (const sd of [-1, 1]) {
    const w = H.proj(sd * H.noseW * 1.25, ny + 1);
    if (w.c < 0.35) continue;
    if (sd > 0 && sg > 0) continue;
    const out = sd < 0 ? -1 : 1;
    curve(
      g,
      INK,
      LW2,
      w.x - out * 1,
      w.y + 3,
      w.x + out * 2.5 * tw,
      w.y + 2,
      w.x + out * 2 * tw,
      w.y - 4,
    );
    dot(g, "#5a2e22", (w.x + tip.x) / 2, tip.y + 3, 1.6);
  }
  if (t.nose === "bulb" || has(L, "rednose")) {
    g.fillStyle = alpha("#d04a3a", 0.5);
    g.beginPath();
    g.ellipse(tip.x - 2, tip.y - 1, 8 * tw, 6.5, 0, 0, Math.PI * 2);
    g.fill();
  }
  line(g, alpha(skl, 0.95), 2.4, (root.x + mid.x) / 2 + 3, root.y + 8, tip.x + 1, tip.y - 8);
  dot(g, alpha("#ffffff", 0.55), tip.x + 1, tip.y - 2, 1.8);
}

// ---------------------------------------------------------------- mouth

export function mouthPts(L: Look): { xl: number; xr: number; mx: number; my: number; mw: number } {
  const H = L.H;
  const span = (L.t.mouth / 24) * 17 * DEG;
  const a = H.proj(-span, H.my);
  const b = H.proj(span, H.my);
  const c = H.proj(0, H.my);
  return { xl: a.x, xr: b.x, mx: c.x, my: c.y, mw: b.x - a.x };
}

export function mouth(g: G, L: Look): void {
  const { xl, xr, mx, my, mw } = mouthPts(L);
  const [, skd] = L.skin;
  const smirk = has(L, "smirk");
  const lip = alpha(skd, 1);
  const mood = L.mood === "calm" && has(L, "roar") ? "angry" : L.mood;
  switch (mood) {
    case "calm": {
      if (has(L, "gap")) {
        // A crooked grin with a tooth missing.
        const m = path((p) => {
          p.moveTo(xl - 3, my - 2);
          p.quadraticCurveTo(mx, my + 2, xr + 3, my - 6);
          p.quadraticCurveTo(mx + 2, my + 10, xl - 3, my - 2);
          p.closePath();
        });
        g.fillStyle = TEETH;
        g.fill(m);
        inside(g, m, () => {
          g.fillStyle = MOUTH;
          g.fillRect(mx + 1, my - 8, 5, 16);
          for (let x = xl + 4; x < xr; x += 5)
            line(g, alpha("#9a8c78", 0.9), 1, x, my - 4, x, my + 6);
        });
        g.lineWidth = 2.2;
        g.strokeStyle = INK;
        g.stroke(m);
        line(g, alpha(skd, 0.8), 2.4, mx - 4, my + 8.5, mx + 4, my + 8);
        break;
      }
      curve(g, INK, 2.4, xl, my, mx, my + 1.5, xr, my - (smirk ? 4 : 0.5));
      if (smirk) curve(g, INK, 1.6, xr - 1, my - 4, xr + 2, my - 4, xr + 3, my - 6);
      line(g, alpha(skd, 0.8), 2.4, mx - 4, my + 6.5, mx + 4, my + 6);
      break;
    }
    case "smile": {
      const m = path((p) => {
        p.moveTo(xl, my - 2);
        p.quadraticCurveTo(mx, my + 1, xr, my - 4);
        p.quadraticCurveTo(mx + 2, my + 11, xl, my - 2);
        p.closePath();
      });
      g.fillStyle = MOUTH;
      g.fill(m);
      inside(g, m, () => {
        g.fillStyle = TEETH;
        g.fillRect(xl - 2, my - 6, mw + 4, 7.5);
        if (has(L, "gap")) {
          g.fillStyle = MOUTH;
          g.fillRect(mx + 1, my - 6, 4, 8);
        }
      });
      g.lineWidth = 2.2;
      g.strokeStyle = INK;
      g.stroke(m);
      curve(g, lip, 2.2, xl - 3, my - 5, xl - 2, my - 1, xl + 1, my + 2);
      line(g, lip, 2.4, mx - 4, my + 11, mx + 5, my + 10.5);
      break;
    }
    case "angry": {
      const wide = L.mood === "calm" ? 0.7 : 1;
      const m = path((p) => {
        p.moveTo(xl - 1, my + 3);
        p.quadraticCurveTo(mx, my - 5, xr + 1, my + 1);
        p.quadraticCurveTo(mx + 1, my + 4 + 12 * wide, xl - 1, my + 3);
        p.closePath();
      });
      g.fillStyle = MOUTH;
      g.fill(m);
      inside(g, m, () => {
        g.fillStyle = TEETH;
        g.fillRect(xl - 3, my - 6, mw + 6, 7);
        g.fillRect(xl - 3, my + 8, mw + 6, 8);
        for (let x = xl + 4; x < xr; x += 5)
          line(g, alpha("#9a8c78", 0.9), 1, x, my - 6, x, my + 1);
      });
      g.lineWidth = 2.4;
      g.strokeStyle = INK;
      g.stroke(m);
      line(g, lip, 2.2, xl - 3, my + 7, xl - 6, my + 12);
      break;
    }
    case "grim": {
      curve(g, INK, 2.6, xl, my + 2.5, mx, my - 1, xr, my + 1.5);
      line(g, lip, 2.6, mx - 6, my + 8, mx + 4, my + 8);
      curve(g, lip, 1.6, mx - 4, my + 15, mx, my + 13, mx + 4, my + 15);
      break;
    }
    case "hurt": {
      const m = path((p) => {
        p.moveTo(xl - 2, my + 2);
        p.quadraticCurveTo(mx, my - 3, xr + 2, my - 2);
        p.lineTo(xr + 1, my + 4);
        p.quadraticCurveTo(mx, my + 9, xl - 1, my + 7);
        p.closePath();
      });
      g.fillStyle = TEETH;
      g.fill(m);
      inside(g, m, () => {
        line(g, alpha("#6a5a48", 0.9), 1.6, xl - 2, my + 3.5, xr + 2, my + 1.5);
        for (let x = xl + 3; x < xr; x += 5)
          line(g, alpha("#9a8c78", 0.9), 1, x, my - 3, x, my + 8);
      });
      g.lineWidth = 2.2;
      g.strokeStyle = INK;
      g.stroke(m);
      break;
    }
  }
}

// ---------------------------------------------------------------- lines of age and temper

export function lines(g: G, L: Look): void {
  const H = L.H;
  const t = L.t;
  const c = alpha(L.skin[1], 1);
  const { xl, xr, my } = mouthPts(L);
  const lined = t.age >= 2 || has(L, "lines");
  for (const sd of [-1, 1]) {
    const w = H.proj(sd * H.noseW * 1.5, H.ny + 1);
    if (w.c < 0.3) continue;
    const cx = sd < 0 ? xl : xr;
    if (lined || (t.age >= 1 && sd < 0))
      curve(
        g,
        alpha(L.skin[1], lined ? 1 : 0.5),
        lined ? 1.8 : 1.3,
        w.x + sd * 2,
        w.y,
        cx + sd * 6,
        my - 6,
        cx + sd * 4,
        my + 6,
      );
  }
  if (lined) {
    // Under the eye.
    const u = H.pt(-30 * DEG, 10);
    curve(g, c, 1.4, u[0] - 8, u[1], u[0] - 2, u[1] + 3, u[0] + 6, u[1]);
  }
  if (t.age >= 3 || has(L, "lines")) {
    // Crow's feet and forehead lines.
    for (const sd of [-1, 1]) {
      const p = H.proj(sd * 50 * DEG, 0);
      if (p.c < 0.25) continue;
      line(g, c, 1.3, p.x + sd * 2, p.y - 2, p.x + sd * 8, p.y - 5);
      line(g, c, 1.3, p.x + sd * 2, p.y + 2, p.x + sd * 8, p.y + 3);
    }
    for (const dy of [-34, -41]) {
      const a = H.pt(-22 * DEG, dy);
      const m = H.pt(0, dy - 2);
      const b = H.pt(22 * DEG, dy);
      curve(g, alpha(L.skin[1], 0.8), 1.3, a[0], a[1], m[0], m[1] - 1, b[0], b[1]);
    }
  }
  if (t.age >= 3) {
    const ch = H.pt(0, H.ych - 2);
    curve(g, c, 1.4, ch[0] - 8, ch[1], ch[0], ch[1] + 4, ch[0] + 7, ch[1]);
  }
  // Warm cheeks for the young, for smiles, for drinkers and the flushed.
  const flush = L.t.skin === "flushed" ? 0.55 : 0;
  const warm =
    flush || (has(L, "rednose") ? 0.45 : t.age === 0 ? 0.3 : L.mood === "smile" ? 0.25 : 0.12);
  g.fillStyle = alpha("#e0786a", warm);
  for (const sd of [-1, 1]) {
    const p = H.proj(sd * 38 * DEG, 22);
    if (p.c < 0.2) continue;
    g.beginPath();
    g.ellipse(p.x, p.y, 11 * clamp(p.c + 0.2, 0.5, 1), 6, 0, 0, Math.PI * 2);
    g.fill();
  }
  if (L.mood === "angry") {
    const p = H.proj(0, 8);
    g.fillStyle = alpha("#d0584a", 0.16);
    g.beginPath();
    g.ellipse(p.x, p.y, 46 * H.W, 30, 0, 0, Math.PI * 2);
    g.fill();
  }
  if (L.mood === "grim") {
    inside(g, H.outline(), () => {
      const p = H.proj(0, -6);
      g.fillStyle = alpha(L.skin[1], 0.55);
      g.beginPath();
      g.ellipse(p.x, p.y, 42 * H.W, 9, 0, 0, Math.PI * 2);
      g.fill();
    });
  }
}

// ---------------------------------------------------------------- ears

/** An ear on each side that shows: the near one in three-quarter view, both from the front. */
export function ears(g: G, L: Look): void {
  const H = L.H;
  const big = has(L, "ears");
  const [sk, skd, skl] = L.skin;
  const lobe = (big ? 58 : 34 + L.t.age * 1.5) * H.L;
  for (const sd of [-1, 1]) {
    const at = H.proj(sd * 92 * DEG, 8);
    // The ear faces forward and out; it shows unless the head hides it.
    const face = Math.cos(sd * 70 * DEG + H.phi);
    if (at.z < -H.Rc * 0.35 || face < -0.15) continue;
    const k = clamp(0.35 + 0.75 * face, 0.3, 1);
    const top = H.proj(sd * 92 * DEG, -10);
    const out = sd * (at.x < 0 ? -1 : 1) < 0 ? -sd : sd;
    const dir = at.x + out < at.x ? -1 : 1;
    const ex = top.x + dir * 2;
    const w = 20 * k * H.W;
    const e = path((p) => {
      p.moveTo(ex, -8);
      p.bezierCurveTo(ex + dir * w * 0.5, -18, ex + dir * w * 1.2, -8, ex + dir * w, 10);
      p.bezierCurveTo(
        ex + dir * w * 0.95,
        lobe * 0.7,
        ex + dir * w * (big ? 0.9 : 0.7),
        lobe,
        ex + dir * w * 0.25,
        lobe - 2,
      );
      p.quadraticCurveTo(ex - dir * 1, 22, ex, -8);
      p.closePath();
    });
    cel(g, e, sk, skd, 5, skl);
    curve(
      g,
      alpha(skd, 1),
      2.2,
      ex + dir * w * 0.3,
      -4,
      ex + dir * w * 0.8,
      2,
      ex + dir * w * 0.6,
      18,
      ex + dir * w * 0.4,
      22,
      ex + dir * w * 0.3,
      14,
    );
  }
}

/** Where the earring hangs: the visible ear's lobe. */
export function earLobe(L: Look): Pt | null {
  const H = L.H;
  const sd = H.phi > 0.05 ? -1 : H.phi < -0.05 ? 1 : -1;
  const at = H.proj(sd * 92 * DEG, 8);
  if (at.z < -H.Rc * 0.35) return null;
  const k = clamp(0.35 + 0.75 * Math.cos(sd * 70 * DEG + H.phi), 0.3, 1);
  const dir = at.x < 0 ? -1 : 1;
  const lobe = (has(L, "ears") ? 58 : 34) * H.L;
  return [at.x + dir * 20 * k * H.W * 0.5, lobe + 6];
}

// ---------------------------------------------------------------- marks

export function marks(g: G, L: Look): void {
  const H = L.H;
  if (has(L, "mole")) {
    const p = H.proj(-24 * DEG, H.my - 8);
    if (p.c > 0.2) dot(g, "#3a2418", p.x, p.y, 3.4);
  }
  if (has(L, "scar") || has(L, "scars")) {
    const a = H.proj(40 * DEG, 30);
    if (a.c > 0.2) {
      line(g, alpha("#a0523c", 0.9), 2.2, a.x - 4, a.y - 6, a.x + 5, a.y + 6);
      line(g, alpha("#f2c6a6", 0.9), 1, a.x - 3, a.y - 7, a.x + 6, a.y + 5);
    }
  }
  if (has(L, "scars")) {
    const a = H.pt(-34 * DEG, -34);
    const b = H.pt(-8 * DEG, 4);
    line(g, alpha("#a0523c", 0.95), 2.6, a[0], a[1], b[0], b[1]);
    line(g, alpha("#f2c6a6", 0.9), 1.1, a[0] + 1, a[1] - 1, b[0] + 1, b[1] - 1);
    for (const k of [0.3, 0.55, 0.8]) {
      const x = a[0] + (b[0] - a[0]) * k;
      const y = a[1] + (b[1] - a[1]) * k;
      line(g, alpha("#a0523c", 0.8), 1.4, x - 4, y + 2, x + 4, y - 2);
    }
  }
  if (H.jowl > 0) {
    const c = alpha(L.skin[1], 1);
    const a = H.pt(-34 * DEG, 36);
    const b = H.pt(-30 * DEG, 58);
    curve(g, c, 2, a[0], a[1], a[0] - 2, (a[1] + b[1]) / 2, b[0] + 4, b[1] + 6);
    const ch = H.pt(0, H.ycb - 4);
    curve(g, c, 2, ch[0] - 14, ch[1], ch[0], ch[1] + 8, ch[0] + 14, ch[1]);
  }
}

/** 夏侯惇's patch over his lost left eye, its strap round the head. */
export function patch(g: G, L: Look): void {
  const H = L.H;
  const p = H.proj(eyeAz(L), 1);
  if (p.c < 0.05) return;
  const a = H.pt(-70 * DEG, -30);
  const b = H.pt(80 * DEG, 14);
  line(g, INK, 5, a[0], a[1], b[0], b[1]);
  line(g, LEATHER_DARK, 3, a[0], a[1], b[0], b[1]);
  const w = 11 * clamp(0.5 + p.c * 0.5, 0.5, 1);
  cel(g, ellipse(p.x, p.y, w, 9, 0.2), "#2a201c", "#120d0b", 3, "#4a3c34");
  line(g, alpha("#a0523c", 0.9), 2, p.x + 5, p.y + 11, p.x + 9, p.y + 21);
}

/** A cut on the cheek and a drop of sweat. */
export function wounds(g: G, L: Look): void {
  const H = L.H;
  const th = clamp(36 * DEG - H.phi * 0.5, -40 * DEG, 50 * DEG);
  const a = H.proj(th, 16);
  line(g, INK, 4, a.x - 7, a.y - 6, a.x + 7, a.y + 6);
  line(g, BLOOD, 2.4, a.x - 7, a.y - 6, a.x + 7, a.y + 6);
  dot(g, BLOOD, a.x + 3, a.y + 12, 2);
  const [, r] = H.extentAt(-40);
  const x = r + 14;
  const d = path((p) => {
    p.moveTo(x, -40);
    p.quadraticCurveTo(x + 8, -24, x + 6, -18);
    p.arc(x, -18, 6, 0, Math.PI);
    p.quadraticCurveTo(x - 8, -24, x, -40);
    p.closePath();
  });
  cel(g, d, SWEAT, "#8fbcd8", 2, "#ffffff");
}

/** A loose lock of hair slipped onto the brow. */
export function strand(g: G, L: Look): void {
  const H = L.H;
  const a = H.pt(14 * DEG, -46);
  const b = H.pt(24 * DEG, -32);
  const c = H.pt(16 * DEG, -14);
  const [hc, hd, hl] = L.hair;
  cel(g, ribbon(a, b, c, 8, 1.5), hc, hd, 2, hl, 1.6);
}
