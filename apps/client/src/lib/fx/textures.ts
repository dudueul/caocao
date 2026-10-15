/**
 * Effect textures painted once on canvases at start-up: soft lights, shadows,
 * sparks, smoke, leaves, rain and the like. All of it is our own drawing code,
 * so the effects look the same with or without the classic skin.
 */
import { Texture } from "pixi.js";

export interface FxTextures {
  /** Soft round light, white. */
  dot: Texture;
  /** Wide, faint halo. */
  glow: Texture;
  /** Contact shadow under a figure. */
  shadow: Texture;
  /** Thin streak with tapered ends (sparks, rain). */
  streak: Texture;
  /** Puff of smoke or dust. */
  smoke: Texture;
  leaf: Texture;
  /** Soft ring (ripples, auras). */
  ring: Texture;
  /** Crescent slash arc. */
  slash: Texture;
  /** Four-point star glint. */
  star: Texture;
  /** Dark corners for the screen edge. */
  vignette: Texture;
  /** Large soft cloud shadow. */
  cloud: Texture;
  /** Small rock. */
  rock: Texture;
  /** Long soft light shaft. */
  beam: Texture;
  /** A thin raindrop streak (points along +x). */
  drop: Texture;
  /** Tiling noise for water shimmer (displacement). */
  noise: Texture;
}

function paint(w: number, h: number, draw: (g: CanvasRenderingContext2D) => void): Texture {
  const c = document.createElement("canvas");
  c.width = w;
  c.height = h;
  const g = c.getContext("2d");
  if (g) draw(g);
  return Texture.from(c);
}

function radial(
  g: CanvasRenderingContext2D,
  x: number,
  y: number,
  r: number,
  stops: [number, string][],
): void {
  const grad = g.createRadialGradient(x, y, 0, x, y, r);
  for (const [k, c] of stops) grad.addColorStop(k, c);
  g.fillStyle = grad;
  g.fillRect(x - r, y - r, r * 2, r * 2);
}

/** Small deterministic noise for textures (display only). */
function hash(x: number, y: number, seed: number): number {
  let h = (x * 374761393 + y * 668265263 + seed * 2147483647) | 0;
  h = Math.imul(h ^ (h >>> 13), 1274126177);
  return ((h ^ (h >>> 16)) >>> 0) / 4294967296;
}

export function makeTextures(): FxTextures {
  return {
    dot: paint(32, 32, (g) =>
      radial(g, 16, 16, 16, [
        [0, "rgba(255,255,255,1)"],
        [0.35, "rgba(255,255,255,0.7)"],
        [1, "rgba(255,255,255,0)"],
      ]),
    ),
    glow: paint(128, 128, (g) =>
      radial(g, 64, 64, 64, [
        [0, "rgba(255,255,255,0.55)"],
        [0.5, "rgba(255,255,255,0.18)"],
        [1, "rgba(255,255,255,0)"],
      ]),
    ),
    shadow: paint(64, 32, (g) => {
      g.save();
      g.scale(1, 0.5);
      radial(g, 32, 32, 32, [
        [0, "rgba(0,0,0,0.6)"],
        [0.6, "rgba(0,0,0,0.3)"],
        [1, "rgba(0,0,0,0)"],
      ]);
      g.restore();
    }),
    streak: paint(32, 6, (g) => {
      const grad = g.createLinearGradient(0, 0, 32, 0);
      grad.addColorStop(0, "rgba(255,255,255,0)");
      grad.addColorStop(0.7, "rgba(255,255,255,1)");
      grad.addColorStop(1, "rgba(255,255,255,0)");
      g.fillStyle = grad;
      g.beginPath();
      g.ellipse(16, 3, 16, 2.5, 0, 0, Math.PI * 2);
      g.fill();
    }),
    smoke: paint(64, 64, (g) => {
      for (let i = 0; i < 7; i++) {
        const a = (i / 7) * Math.PI * 2;
        const r = 10 + hash(i, 1, 3) * 8;
        radial(g, 32 + Math.cos(a) * 10, 32 + Math.sin(a) * 10, r + 10, [
          [0, "rgba(255,255,255,0.35)"],
          [1, "rgba(255,255,255,0)"],
        ]);
      }
      radial(g, 32, 32, 22, [
        [0, "rgba(255,255,255,0.5)"],
        [1, "rgba(255,255,255,0)"],
      ]);
    }),
    leaf: paint(14, 8, (g) => {
      g.fillStyle = "#ffffff";
      g.beginPath();
      g.ellipse(7, 4, 6.5, 3, 0, 0, Math.PI * 2);
      g.fill();
      g.strokeStyle = "rgba(0,0,0,0.25)";
      g.beginPath();
      g.moveTo(1, 4);
      g.lineTo(13, 4);
      g.stroke();
    }),
    ring: paint(128, 128, (g) => {
      const grad = g.createRadialGradient(64, 64, 40, 64, 64, 62);
      grad.addColorStop(0, "rgba(255,255,255,0)");
      grad.addColorStop(0.6, "rgba(255,255,255,0.9)");
      grad.addColorStop(1, "rgba(255,255,255,0)");
      g.fillStyle = grad;
      g.fillRect(0, 0, 128, 128);
    }),
    slash: paint(128, 128, (g) => {
      // A crescent: a bright arc that thins out at both ends.
      g.translate(64, 64);
      for (let i = 0; i < 24; i++) {
        const k = i / 23;
        const a0 = -Math.PI * 0.75 + k * Math.PI * 1.1;
        const w = Math.sin(k * Math.PI) * 9;
        g.strokeStyle = `rgba(255,255,255,${0.15 + 0.85 * Math.sin(k * Math.PI)})`;
        g.lineWidth = w;
        g.beginPath();
        g.arc(0, 0, 46, a0, a0 + Math.PI * 0.06);
        g.stroke();
      }
    }),
    star: paint(48, 48, (g) => {
      radial(g, 24, 24, 10, [
        [0, "rgba(255,255,255,1)"],
        [1, "rgba(255,255,255,0)"],
      ]);
      g.fillStyle = "rgba(255,255,255,0.9)";
      for (const [w, h] of [
        [2, 24],
        [24, 2],
      ] as const) {
        g.beginPath();
        g.ellipse(24, 24, w, h, 0, 0, Math.PI * 2);
        g.fill();
      }
    }),
    vignette: paint(256, 256, (g) =>
      radial(g, 128, 128, 182, [
        [0, "rgba(0,0,0,0)"],
        [0.55, "rgba(0,0,0,0)"],
        [1, "rgba(0,0,0,0.75)"],
      ]),
    ),
    cloud: paint(256, 160, (g) => {
      for (let i = 0; i < 9; i++) {
        const x = 50 + hash(i, 2, 7) * 156;
        const y = 50 + hash(i, 3, 7) * 60;
        radial(g, x, y, 40 + hash(i, 4, 7) * 30, [
          [0, "rgba(0,0,0,0.5)"],
          [1, "rgba(0,0,0,0)"],
        ]);
      }
    }),
    rock: paint(16, 16, (g) => {
      g.fillStyle = "#ffffff";
      g.beginPath();
      g.moveTo(3, 11);
      g.lineTo(5, 4);
      g.lineTo(11, 3);
      g.lineTo(14, 9);
      g.lineTo(10, 14);
      g.closePath();
      g.fill();
      g.fillStyle = "rgba(0,0,0,0.35)";
      g.beginPath();
      g.moveTo(10, 14);
      g.lineTo(14, 9);
      g.lineTo(9, 8);
      g.closePath();
      g.fill();
    }),
    drop: paint(40, 2, (g) => {
      const grad = g.createLinearGradient(0, 0, 40, 0);
      grad.addColorStop(0, "rgba(255,255,255,0)");
      grad.addColorStop(1, "rgba(255,255,255,0.9)");
      g.fillStyle = grad;
      g.fillRect(0, 0, 40, 2);
    }),
    beam: paint(64, 256, (g) => {
      const across = g.createLinearGradient(0, 0, 64, 0);
      across.addColorStop(0, "rgba(255,255,255,0)");
      across.addColorStop(0.5, "rgba(255,255,255,1)");
      across.addColorStop(1, "rgba(255,255,255,0)");
      g.fillStyle = across;
      g.fillRect(0, 0, 64, 256);
      g.globalCompositeOperation = "destination-in";
      const along = g.createLinearGradient(0, 0, 0, 256);
      along.addColorStop(0, "rgba(0,0,0,0)");
      along.addColorStop(0.3, "rgba(0,0,0,1)");
      along.addColorStop(0.7, "rgba(0,0,0,1)");
      along.addColorStop(1, "rgba(0,0,0,0)");
      g.fillStyle = along;
      g.fillRect(0, 0, 64, 256);
    }),
    noise: paint(128, 128, (g) => {
      // Smooth tiling value noise in red and green (displacement x and y).
      const img = g.createImageData(128, 128);
      const cell = 16;
      const n = 128 / cell;
      const v = (x: number, y: number, s: number) => hash(((x % n) + n) % n, ((y % n) + n) % n, s);
      const smooth = (t: number) => t * t * (3 - 2 * t);
      for (let y = 0; y < 128; y++)
        for (let x = 0; x < 128; x++) {
          const cx = Math.floor(x / cell);
          const cy = Math.floor(y / cell);
          const fx = smooth((x % cell) / cell);
          const fy = smooth((y % cell) / cell);
          const o = (y * 128 + x) * 4;
          for (const [ch, s] of [
            [0, 11],
            [1, 23],
          ] as const) {
            const a = v(cx, cy, s) + (v(cx + 1, cy, s) - v(cx, cy, s)) * fx;
            const b = v(cx, cy + 1, s) + (v(cx + 1, cy + 1, s) - v(cx, cy + 1, s)) * fx;
            img.data[o + ch] = Math.round((a + (b - a) * fy) * 255);
          }
          img.data[o + 2] = 128;
          img.data[o + 3] = 255;
        }
      g.putImageData(img, 0, 0);
    }),
  };
}
