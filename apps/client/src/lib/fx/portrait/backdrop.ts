/**
 * The backdrop behind the bust (ADR 0070): the side's dye as a sky, the
 * faction's country at low contrast, a lighter value step behind the head so
 * the face reads first, and the hero's own motif in place of a shared halo
 * ring. Minor officers and soldiers get the landscape and a faint ring.
 */
import { alpha, curve, type G, line, mix, rng } from "./draw.ts";
import type { Look } from "./look.ts";
import { ACCENTS, GOLD_LIGHT } from "./palette.ts";
import { type Faction, type Motif, PORTRAIT_H, PORTRAIT_W } from "./types.ts";

interface Tone {
  far: string;
  near: string;
  light: string;
}

function tones(L: Look): Tone {
  const P = L.P;
  return {
    far: alpha(mix(P.robe, "#0c0a10", 0.35), 0.38),
    near: alpha(mix(P.robeDark, "#07060a", 0.45), 0.5),
    light: alpha(mix(P.lace, "#fff4dc", 0.55), 0.35),
  };
}

function banner(g: G, x: number, y: number, h: number, color: string, wind = 1): void {
  line(g, alpha("#1a1410", 0.5), 1.6, x, y, x, y - h);
  g.fillStyle = color;
  g.beginPath();
  g.moveTo(x, y - h);
  g.quadraticCurveTo(x + wind * 10, y - h - 2, x + wind * 18, y - h + 3);
  g.lineTo(x + wind * 14, y - h + 8);
  g.lineTo(x + wind * 18, y - h + 13);
  g.quadraticCurveTo(x + wind * 8, y - h + 11, x, y - h + 12);
  g.closePath();
  g.fill();
}

function hills(g: G, y: number, amp: number, color: string, seed: number, step = 32): void {
  const r = rng(seed);
  g.fillStyle = color;
  g.beginPath();
  g.moveTo(-10, PORTRAIT_H);
  g.lineTo(-10, y);
  for (let x = -10; x <= PORTRAIT_W + 20; x += step)
    g.quadraticCurveTo(x + step / 2, y - amp * (0.4 + r()), x + step, y + (r() - 0.5) * amp * 0.4);
  g.lineTo(PORTRAIT_W + 20, PORTRAIT_H);
  g.closePath();
  g.fill();
}

function wall(g: G, y: number, color: string, h = 22): void {
  g.fillStyle = color;
  g.fillRect(-10, y, PORTRAIT_W + 20, PORTRAIT_H);
  for (let x = -6; x < PORTRAIT_W + 10; x += 16) g.fillRect(x, y - 8, 9, 9);
  g.fillStyle = alpha("#000000", 0.08);
  for (let yy = y + 6; yy < y + h + 40; yy += 7) g.fillRect(-10, yy, PORTRAIT_W + 20, 1.2);
}

function water(g: G, y: number, t: Tone): void {
  g.fillStyle = t.light;
  g.fillRect(-10, y, PORTRAIT_W + 20, 40);
  for (let i = 0; i < 9; i++) {
    const yy = y + 6 + i * 5;
    line(g, alpha("#fff8ea", 0.18), 1.4, 10 + (i % 3) * 30, yy, 60 + (i % 4) * 40, yy);
    line(g, alpha("#fff8ea", 0.14), 1.2, 160 + (i % 2) * 30, yy, 230 + (i % 3) * 10, yy);
  }
}

/** The faction's country, low and quiet behind the shoulders. */
function landscape(g: G, fac: Faction, L: Look): void {
  const t = tones(L);
  const s = L.seed;
  switch (fac) {
    case "cao":
      // The Yellow River plain under red banners.
      hills(g, 214, 6, t.far, s, 60);
      g.fillStyle = alpha("#e8d0a0", 0.22);
      g.fillRect(-10, 216, PORTRAIT_W + 20, 10);
      for (const x of [16, 36, 214, 236]) banner(g, x, 214, 30, alpha("#c0392b", 0.55));
      break;
    case "yuan":
      // The northern plain, a walled city, blue banners.
      hills(g, 212, 4, t.far, s, 70);
      g.fillStyle = t.near;
      g.fillRect(170, 190, 90, 26);
      for (let x = 172; x < 260; x += 12) g.fillRect(x, 184, 7, 7);
      for (const x of [20, 40, 60]) banner(g, x, 212, 34, alpha("#3f74d6", 0.5));
      break;
    case "lu":
      // The flooded fields of 下邳.
      water(g, 206, t);
      g.fillStyle = t.near;
      g.fillRect(180, 186, 80, 22);
      for (let x = 182; x < 260; x += 12) g.fillRect(x, 180, 7, 7);
      break;
    case "liu":
      hills(g, 200, 26, t.far, s);
      hills(g, 222, 14, t.near, s + 1);
      for (let i = 0; i < 14; i++) {
        const r = rng(s + i);
        const x = 4 + r() * 50;
        const y = 20 + r() * 70;
        g.fillStyle = alpha("#f2b0c0", 0.5);
        g.beginPath();
        g.arc(x, y, 3 + r() * 2, 0, Math.PI * 2);
        g.fill();
      }
      curve(g, alpha("#3a2418", 0.4), 2.4, -4, 10, 30, 40, 54, 90);
      break;
    case "wu":
      // The river and its sails.
      hills(g, 196, 18, t.far, s);
      water(g, 206, t);
      for (const [x, h] of [
        [26, 34],
        [220, 28],
      ] as const) {
        g.fillStyle = alpha("#f4ead2", 0.35);
        g.beginPath();
        g.moveTo(x, 210 - h);
        g.lineTo(x + 16, 206);
        g.lineTo(x - 2, 206);
        g.closePath();
        g.fill();
      }
      break;
    case "jing":
      hills(g, 176, 40, t.far, s, 40);
      hills(g, 206, 22, t.near, s + 3, 36);
      water(g, 222, t);
      break;
    case "dong": {
      // 涼州: loess cliffs and the wind.
      g.fillStyle = t.far;
      g.beginPath();
      g.moveTo(-10, 320);
      g.lineTo(-10, 170);
      g.lineTo(50, 170);
      g.lineTo(60, 196);
      g.lineTo(190, 196);
      g.lineTo(204, 160);
      g.lineTo(270, 160);
      g.lineTo(270, 320);
      g.closePath();
      g.fill();
      for (let i = 0; i < 6; i++)
        line(g, alpha("#f8e8c8", 0.16), 3, -10, 60 + i * 26, 270, 40 + i * 26);
      break;
    }
    case "han":
    case "zhong": {
      // Palace pillars and a roof eave; 袁術's gilded.
      const col = fac === "zhong" ? alpha("#d9a936", 0.45) : alpha("#8a2a20", 0.5);
      g.fillStyle = col;
      g.fillRect(4, 40, 18, 280);
      g.fillRect(234, 40, 18, 280);
      g.fillStyle = alpha("#1a1410", 0.35);
      g.beginPath();
      g.moveTo(-10, 30);
      g.lineTo(266, 30);
      g.lineTo(256, 46);
      g.lineTo(0, 46);
      g.closePath();
      g.fill();
      break;
    }
    case "yt":
      // 「黃天當立」: a yellow sky over dry fields.
      g.fillStyle = alpha("#e0b83a", 0.3);
      g.fillRect(0, 0, PORTRAIT_W, 220);
      hills(g, 214, 8, t.far, s, 50);
      break;
    case "xu":
      wall(g, 200, t.far);
      break;
    case "north": {
      // The steppe, felt tents facing east.
      hills(g, 214, 3, t.far, s, 90);
      for (const [x, w] of [
        [24, 34],
        [216, 30],
      ] as const) {
        g.fillStyle = t.near;
        g.beginPath();
        g.moveTo(x - w / 2, 214);
        g.quadraticCurveTo(x - w / 2, 214 - w * 0.7, x, 214 - w * 0.75);
        g.quadraticCurveTo(x + w / 2, 214 - w * 0.7, x + w / 2, 214);
        g.closePath();
        g.fill();
      }
      break;
    }
  }
}

/** The hero's own sign, behind the head (cx, cy). */
function motif(g: G, m: Motif, L: Look, cx: number, cy: number): void {
  const t = tones(L);
  g.save();
  switch (m) {
    case "mirror": {
      // A cracked bronze mirror (夏侯惇 smashed mirrors in anger, 注:魏略).
      g.strokeStyle = alpha("#c0913e", 0.55);
      g.lineWidth = 10;
      g.beginPath();
      g.arc(cx, cy, 100, 0, Math.PI * 2);
      g.stroke();
      for (const a of [0.6, 2.4, 4.1, 5.3])
        line(
          g,
          alpha("#3a2414", 0.6),
          2.4,
          cx + Math.cos(a) * 92,
          cy + Math.sin(a) * 92,
          cx + Math.cos(a + 0.1) * 112,
          cy + Math.sin(a + 0.1) * 112,
        );
      break;
    }
    case "sun": {
      // 程昱's dream of holding up the sun (注:魏書).
      g.fillStyle = alpha("#fff0c0", 0.4);
      g.beginPath();
      g.arc(cx + 6, cy - 40, 74, 0, Math.PI * 2);
      g.fill();
      break;
    }
    case "incense": {
      // 荀彧's fragrance (later lore): a thin curl of incense.
      g.lineCap = "round";
      curve(g, alpha("#fff8ea", 0.4), 3, 210, 300, 250, 220, 214, 160, 180, 100, 222, 40);
      curve(g, alpha("#fff8ea", 0.25), 2, 40, 300, 10, 230, 44, 170, 74, 110, 36, 50);
      break;
    }
    case "bars":
      for (let x = 8; x < PORTRAIT_W; x += 36) {
        g.fillStyle = alpha("#1a1410", 0.35);
        g.fillRect(x, 0, 9, PORTRAIT_H);
      }
      break;
    case "river": {
      // The Yellow River at dusk (沮授 on its bank).
      g.fillStyle = alpha("#f0a050", 0.25);
      g.fillRect(0, 150, PORTRAIT_W, 60);
      water(g, 200, t);
      break;
    }
    case "burning":
    case "fire":
    case "grassFire": {
      const r = rng(L.seed ^ 0xf17e);
      const base = m === "grassFire" ? 230 : 250;
      for (let i = 0; i < 9; i++) {
        const x = (i / 8) * PORTRAIT_W + (r() - 0.5) * 20;
        const h = (m === "grassFire" ? 40 : 90) * (0.6 + r() * 0.6);
        g.fillStyle = alpha(i % 2 ? "#f08a2a" : "#ffd26a", 0.35);
        g.beginPath();
        g.moveTo(x - 18, base);
        g.quadraticCurveTo(x - 14, base - h * 0.6, x, base - h);
        g.quadraticCurveTo(x + 14, base - h * 0.6, x + 18, base);
        g.closePath();
        g.fill();
      }
      if (m === "burning") {
        g.fillStyle = t.near;
        g.fillRect(186, 150, 70, 100);
        g.fillStyle = alpha("#ffb050", 0.4);
        g.fillRect(204, 190, 34, 60);
      }
      break;
    }
    case "drum": {
      // 蔡陽's war drum (the novel's three beats).
      g.fillStyle = alpha("#8a2a20", 0.45);
      g.beginPath();
      g.ellipse(214, 150, 40, 46, 0, 0, Math.PI * 2);
      g.fill();
      g.strokeStyle = alpha("#f0d8a0", 0.5);
      g.lineWidth = 3;
      g.beginPath();
      g.ellipse(214, 150, 30, 36, 0, 0, Math.PI * 2);
      g.stroke();
      break;
    }
    case "battlements":
      wall(g, 196, t.far);
      for (const [x, a] of [
        [40, -0.6],
        [70, -0.4],
        [200, 0.5],
      ] as const)
        line(g, alpha("#1a1410", 0.6), 2, x, 196, x + Math.sin(a) * 30, 196 - Math.cos(a) * 30);
      break;
    case "snow": {
      const r = rng(L.seed ^ 0x5a0);
      for (let i = 0; i < 60; i++) {
        g.fillStyle = alpha("#ffffff", 0.35 + r() * 0.3);
        g.beginPath();
        g.arc(r() * PORTRAIT_W, r() * 260, 1 + r() * 2.2, 0, Math.PI * 2);
        g.fill();
      }
      break;
    }
    case "streaks":
      // 夏侯淵's speed: 「三日五百，六日一千」.
      for (let i = 0; i < 9; i++)
        line(
          g,
          alpha("#fff8ea", 0.22),
          3 + (i % 3),
          -10,
          40 + i * 26,
          120 + (i % 4) * 30,
          36 + i * 26,
        );
      break;
    case "tents":
      g.fillStyle = alpha("#ffd8a0", 0.35);
      g.beginPath();
      g.arc(220, 120, 30, 0, Math.PI * 2);
      g.fill();
      break;
    case "pass":
      // 盧龍塞: the pass 田疇 led the army through.
      g.fillStyle = t.far;
      g.beginPath();
      g.moveTo(-10, 320);
      g.lineTo(-10, 60);
      g.lineTo(40, 110);
      g.lineTo(60, 220);
      g.lineTo(60, 320);
      g.closePath();
      g.moveTo(266, 320);
      g.lineTo(266, 50);
      g.lineTo(214, 120);
      g.lineTo(196, 220);
      g.lineTo(196, 320);
      g.closePath();
      g.fill();
      break;
    case "wall":
      wall(g, 168, t.far, 60);
      break;
    case "wheel": {
      // 韓猛's supply carts, burnt by 徐晃.
      g.strokeStyle = alpha("#1a1410", 0.4);
      g.lineWidth = 6;
      g.beginPath();
      g.arc(222, 150, 44, 0, Math.PI * 2);
      g.stroke();
      for (let i = 0; i < 8; i++) {
        const a = (i / 8) * Math.PI * 2;
        line(g, alpha("#1a1410", 0.4), 3, 222, 150, 222 + Math.cos(a) * 44, 150 + Math.sin(a) * 44);
      }
      break;
    }
    case "lattice":
      // 賈詡 kept his door shut.
      g.strokeStyle = alpha("#1a1410", 0.3);
      g.lineWidth = 3;
      for (let x = 8; x < PORTRAIT_W; x += 22)
        line(g, alpha("#1a1410", 0.3), 3, x, 0, x, PORTRAIT_H);
      for (let y = 10; y < PORTRAIT_H; y += 22)
        line(g, alpha("#1a1410", 0.3), 3, 0, y, PORTRAIT_W, y);
      break;
    case "moon":
    case "plum":
      break;
  }
  g.restore();
}

export function backdrop(g: G, L: Look, hx: number, hy: number): void {
  const P = L.P;
  const sky = g.createLinearGradient(0, 0, 0, PORTRAIT_H);
  sky.addColorStop(0, mix(P.robe, "#f4e6c8", 0.28));
  sky.addColorStop(0.55, mix(P.robe, "#0c0a10", 0.25));
  sky.addColorStop(1, mix(P.robeDark, "#07060a", 0.55));
  g.fillStyle = sky;
  g.fillRect(0, 0, PORTRAIT_W, PORTRAIT_H);
  landscape(g, L.t.fac, L);
  // The value step behind the head.
  const glow = g.createRadialGradient(hx + 20, hy - 20, 10, hx + 10, hy, 150);
  glow.addColorStop(0, alpha("#fff4dc", 0.55));
  glow.addColorStop(0.45, alpha(mix(P.lace, "#fff4dc", 0.4), 0.3));
  glow.addColorStop(1, alpha("#fff4dc", 0));
  g.fillStyle = glow;
  g.fillRect(0, 0, PORTRAIT_W, PORTRAIT_H);
  if (L.t.motif) motif(g, L.t.motif, L, hx + 6, hy - 10);
  else {
    const acc = L.t.accent ? ACCENTS[L.t.accent][0] : mix(P.edge, GOLD_LIGHT, 0.5);
    g.save();
    g.globalAlpha = L.named ? 0.45 : 0.25;
    g.strokeStyle = acc;
    g.lineWidth = 2;
    g.beginPath();
    g.arc(hx + 6, hy - 12, 106, 0, Math.PI * 2);
    g.stroke();
    g.restore();
  }
  const r = rng(L.seed ^ 0x9e3779b9);
  g.save();
  g.globalAlpha = 0.08;
  g.strokeStyle = "#fff8ea";
  g.lineCap = "round";
  for (let i = 0; i < 3; i++) {
    const y = 30 + r() * 160;
    g.lineWidth = 10 + r() * 22;
    g.beginPath();
    g.moveTo(-20, y + r() * 30);
    g.quadraticCurveTo(128, y - 30 + r() * 60, 280, y + r() * 40 - 20);
    g.stroke();
  }
  g.restore();
}
