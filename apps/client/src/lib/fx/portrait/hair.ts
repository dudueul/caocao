/**
 * Hair on the head model: the mass over the skull down to a hairline that
 * runs round the head (forehead, temples, sideburns, over the ear, the nape),
 * the topknot (髻), the shaved scalp of the 烏桓 (髡頭) with its side locks,
 * and loosened hair.
 */
import {
  alpha,
  cel,
  curve,
  ellipse,
  type G,
  inside,
  LW,
  LW2,
  line,
  mix,
  type Pt,
  path,
  ribbon,
  rng,
} from "./draw.ts";
import { DEG, type Head } from "./head.ts";
import { has, type Look } from "./look.ts";

/** The hairline's level by azimuth (degrees from the face's centre line, either side). */
const HAIRLINE: readonly (readonly [number, number])[] = [
  [0, -56],
  [48, -53],
  [64, -44],
  [76, 2],
  [84, 6],
  [89, -10],
  [104, -12],
  [118, 28],
  [180, 38],
];

export function hairline(th: number, recede = 0): number {
  const d = Math.abs(th) / DEG;
  for (let i = 1; i < HAIRLINE.length; i++) {
    const [d1, y1] = HAIRLINE[i] as readonly [number, number];
    if (d <= d1) {
      const [d0, y0] = HAIRLINE[i - 1] as readonly [number, number];
      const y = y0 + ((y1 - y0) * (d - d0)) / (d1 - d0 || 1);
      // A receding hairline rises at the temples.
      return d < 70 ? y - recede * (6 + 8 * (d / 70)) : y;
    }
  }
  return 38;
}

/** The hair's outline: the skull's top from limb to limb, back along the hairline. */
export function hairPath(H: Head, recede: number, grow = 4): Path2D {
  const [a, f, b] = H.skullAt(-30, 1);
  const [tl, tr] = H.limbs(a, f, b);
  const yl = hairline(tl, recede);
  const yr = hairline(tr, recede);
  const top = H.yc - H.a;
  const left = H.edge("left", top, yl, grow);
  const right = H.edge("right", top, yr, grow);
  return path((p) => {
    p.moveTo(((left[0] as Pt)[0] + (right[0] as Pt)[0]) / 2, top - grow);
    for (const q of right) p.lineTo(q[0], q[1]);
    const n = 28;
    for (let i = 0; i <= n; i++) {
      const th = tr + ((tl - tr) * i) / n;
      const q = H.pt(th, hairline(th, recede), 1.02, false);
      p.lineTo(q[0], q[1]);
    }
    for (let i = left.length - 1; i >= 0; i--) {
      const q = left[i] as Pt;
      p.lineTo(q[0], q[1]);
    }
    p.closePath();
  });
}

/** The crown, where the topknot sits. */
export function crownPt(H: Head, dy = 0): Pt {
  const p = H.p3(0, H.yc - H.a - 4 + dy, -8);
  return [p.x, p.y];
}

function recedeOf(L: Look): number {
  return L.t.age >= 3 ? 1 : L.t.age === 2 && L.seed % 3 === 0 ? 0.4 : 0;
}

/** The hair under any headwear; bare heads get their sheen and topknot from `topknot`. */
export function hair(g: G, L: Look, bare: boolean): void {
  const H = L.H;
  if (L.hat === "shaved") {
    shaven(g, L);
    return;
  }
  const [hc, hd, hl] = L.hair;
  const h = hairPath(H, recedeOf(L), bare ? 5 : 3);
  cel(g, h, hc, hd, 7, undefined, bare ? LW : LW2);
  const [cx, cy] = crownPt(H);
  inside(g, h, () => {
    // Comb lines running up to the knot.
    for (let i = 0; i < 15; i++) {
      const th = (-150 + i * 20) * DEG;
      if (H.facing(th) < -0.1) continue;
      const y = hairline(th, recedeOf(L));
      const [x0, y0] = H.pt(th, y - 2, 1.02, false);
      const bend = (x0 + cx) / 2 + 6;
      curve(g, alpha(i % 3 ? hd : hl, 0.75), 1.6, x0, y0, bend, (y0 + cy) / 2 - 8, cx, cy);
    }
    if (bare) {
      // A broken sheen across the crown, toward the light.
      for (let i = 0; i < 9; i++) {
        const th = (-40 + i * 12) * DEG - H.phi * 0.3;
        const [x, y] = H.pt(th, -82 + Math.abs(i - 4) * 2, 1.03, false);
        line(g, alpha(hl, 0.8), 3.2, x - 3, y + 4, x + 3, y - 4);
      }
    }
  });
  if (has(L, "forelock")) {
    // A white streak from the brow.
    inside(g, h, () => {
      const [x, y] = H.pt(14 * DEG, -58, 1.02, false);
      cel(
        g,
        ribbon([x, y], [x - 4, y - 20], [cx, cy], 9, 4),
        "#e8e2d6",
        "#b9b0a2",
        2,
        undefined,
        0,
      );
    });
  }
}

/**
 * The topknot (髻) on the crown, tied with a cord or cloth; `loose` lets it
 * come undone with strands falling.
 */
export function topknot(
  g: G,
  L: Look,
  tie: string | null,
  tieDark: string,
  loose = false,
  size = 1,
): void {
  const H = L.H;
  const [hc, hd, hl] = L.hair;
  const [cx, cy] = crownPt(H, -6);
  if (loose) {
    // Strands escaping the knot.
    for (const k of [-1, 1])
      cel(
        g,
        ribbon([cx + k * 4, cy], [cx + k * 26, cy + 8], [cx + k * 30, cy + 44], 7, 2),
        hc,
        hd,
        2,
        hl,
        1.6,
      );
  }
  const bun = ellipse(cx, cy - 4 * size, (loose ? 21 : 17) * size, (loose ? 11 : 13) * size, -0.15);
  cel(g, bun, hc, hd, 4, hl);
  if (tie) {
    const band = path((p) => p.roundRect(cx - 15 * size, cy + 2, 30 * size, 8, 3));
    cel(g, band, tie, tieDark, 3, mix(tie, "#ffffff", 0.35));
    const tails = path((p) => {
      p.moveTo(cx - 13, cy + 6);
      p.quadraticCurveTo(cx - 32, cy + 2, cx - 40, cy + 20);
      p.quadraticCurveTo(cx - 28, cy + 14, cx - 14, cy + 12);
      p.closePath();
    });
    cel(g, tails, tie, tieDark, 3);
  }
}

/**
 * The shaved head of the 烏桓 (髡頭, 後漢書 烏桓傳): the scalp bare to the
 * stubble, the hair's colour only a shadow where it would grow, a sheen on the
 * crown. Side locks (`locks`) hang from the temples: our reading of how much
 * was left, which the record does not say.
 */
function shaven(g: G, L: Look): void {
  const H = L.H;
  const [sk, skd, skl] = L.skin;
  const [hc] = L.hair;
  const h = hairPath(H, 0, 1);
  g.fillStyle = sk;
  g.fill(h);
  inside(g, h, () => {
    g.fillStyle = skd;
    g.beginPath();
    g.moveTo(-200, -140);
    for (let y = -110; y <= 50; y += 5) g.lineTo(H.terminator(Math.max(y, -96)), y);
    g.lineTo(-200, 60);
    g.closePath();
    g.fill();
    g.fillStyle = alpha(hc, L.t.hair === "black" || L.t.hair === "brown" ? 0.2 : 0.3);
    g.fill(h);
    const r = rng(L.seed ^ 0x2b1d);
    g.fillStyle = alpha(hc, 0.4);
    for (let i = 0; i < 260; i++) g.fillRect(-90 + r() * 180, -104 + r() * 150, 1.1, 1.1);
    const [x, y] = H.pt(30 * DEG - H.phi * 0.5, -76, 1, false);
    g.fillStyle = alpha(skl, 0.85);
    g.beginPath();
    g.ellipse(x, y, 16, 7, -0.35, 0, Math.PI * 2);
    g.fill();
  });
  // Only the skull's outer edge is inked; the hairline stays soft.
  const [a, f, b] = H.skullAt(-30, 1);
  const [tl, tr] = H.limbs(a, f, b);
  const top = H.yc - H.a;
  const left = H.edge("left", top, hairline(tl), 1);
  const right = H.edge("right", top, hairline(tr), 1);
  const outer = path((p) => {
    for (let i = left.length - 1; i >= 0; i--) {
      const q = left[i] as Pt;
      if (i === left.length - 1) p.moveTo(q[0], q[1]);
      else p.lineTo(q[0], q[1]);
    }
    for (const q of right) p.lineTo(q[0], q[1]);
  });
  g.lineWidth = LW;
  g.strokeStyle = "#2a1a12";
  g.stroke(outer);
}

/** Side locks hanging from the temples in front of the ears, on a shaved head. */
export function sideLocks(g: G, L: Look): void {
  const H = L.H;
  const [hc, hd, hl] = L.hair;
  for (const sd of [-1, 1]) {
    const at = H.proj(sd * 80 * DEG, -22, 1.02, false);
    if (at.c < -0.05) continue;
    const out = at.x < 0 ? -1 : 1;
    const a: Pt = [at.x, at.y];
    const c: Pt = [at.x + out * 8, at.y + 34];
    const b: Pt = [at.x + out * 4, at.y + 70];
    const lock = ribbon(a, c, b, 9, 4);
    cel(g, lock, hc, hd, 2, hl, LW2);
    inside(g, lock, () => {
      for (let i = 1; i < 6; i++) {
        const y = at.y + i * 12;
        line(g, alpha(hd, 0.9), 1.4, at.x + out * 2 - 4, y, at.x + out * 6 + 4, y + 5);
      }
    });
    // A bead of bronze at the end.
    cel(g, ellipse(b[0], b[1] + 2, 4, 5), "#c0913e", "#7f5c22", 2, "#e8c47a", 1.6);
  }
}
