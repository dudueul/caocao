/**
 * Emotes over a figure's head (ADR 0063): a speech bubble with ! ? … a heart,
 * a note or a light bulb, or a free mark beside the head: a sweat drop, an
 * anger mark, a tear. Painted on canvases once; our own drawing.
 */
import type { Emote } from "@tk/content";
import { Texture } from "pixi.js";

const S = 96;
const INK = "#2a1a12";

export interface EmoteArt {
  tex: Texture;
  /** Drawn in a bubble above the head (else beside the head, without one). */
  bubble: boolean;
}

function canvas(): [HTMLCanvasElement, CanvasRenderingContext2D] {
  const c = document.createElement("canvas");
  c.width = S;
  c.height = S;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  g.lineJoin = "round";
  g.lineCap = "round";
  return [c, g];
}

/** A white speech bubble whose tail points down and to the left, toward the head. */
function bubble(g: CanvasRenderingContext2D) {
  g.beginPath();
  g.roundRect(8, 6, 80, 64, 26);
  g.moveTo(26, 66);
  g.lineTo(18, 90);
  g.lineTo(44, 68);
  g.closePath();
  g.fillStyle = "#fffaf0";
  g.fill();
  g.lineWidth = 4;
  g.strokeStyle = INK;
  g.stroke();
  // Cover the seam between the body and the tail.
  g.fillStyle = "#fffaf0";
  g.fillRect(24, 62, 18, 8);
}

function drop(g: CanvasRenderingContext2D, x: number, y: number, r: number, fill: string) {
  g.beginPath();
  g.moveTo(x, y - r * 1.9);
  g.bezierCurveTo(x + r * 0.9, y - r * 0.6, x + r, y - r * 0.1, x + r, y + r * 0.2);
  g.arc(x, y + r * 0.2, r, 0, Math.PI);
  g.bezierCurveTo(x - r, y - r * 0.1, x - r * 0.9, y - r * 0.6, x, y - r * 1.9);
  g.closePath();
  g.fillStyle = fill;
  g.fill();
  g.lineWidth = 3.5;
  g.strokeStyle = INK;
  g.stroke();
  g.fillStyle = "rgba(255,255,255,0.75)";
  g.beginPath();
  g.ellipse(x - r * 0.35, y + r * 0.05, r * 0.18, r * 0.35, -0.3, 0, Math.PI * 2);
  g.fill();
}

const PAINT: Record<Emote, (g: CanvasRenderingContext2D) => boolean> = {
  "!": (g) => {
    bubble(g);
    g.fillStyle = "#d8392c";
    g.beginPath();
    g.moveTo(40, 16);
    g.lineTo(56, 16);
    g.lineTo(51, 48);
    g.lineTo(45, 48);
    g.closePath();
    g.fill();
    g.beginPath();
    g.arc(48, 58, 6, 0, Math.PI * 2);
    g.fill();
    return true;
  },
  "?": (g) => {
    bubble(g);
    g.strokeStyle = "#2f6fd0";
    g.lineWidth = 9;
    g.beginPath();
    g.arc(48, 30, 12, Math.PI * 1.05, Math.PI * 2.35);
    g.quadraticCurveTo(47, 40, 48, 47);
    g.stroke();
    g.fillStyle = "#2f6fd0";
    g.beginPath();
    g.arc(48, 59, 5.5, 0, Math.PI * 2);
    g.fill();
    return true;
  },
  "...": (g) => {
    bubble(g);
    g.fillStyle = "#4a3a30";
    for (const x of [30, 48, 66]) {
      g.beginPath();
      g.arc(x, 40, 5.5, 0, Math.PI * 2);
      g.fill();
    }
    return true;
  },
  heart: (g) => {
    bubble(g);
    g.beginPath();
    g.moveTo(48, 60);
    g.bezierCurveTo(22, 44, 26, 18, 48, 30);
    g.bezierCurveTo(70, 18, 74, 44, 48, 60);
    g.closePath();
    g.fillStyle = "#e0405a";
    g.fill();
    g.lineWidth = 3;
    g.strokeStyle = "#8a1a2a";
    g.stroke();
    g.fillStyle = "rgba(255,255,255,0.6)";
    g.beginPath();
    g.ellipse(38, 32, 4, 6, -0.6, 0, Math.PI * 2);
    g.fill();
    return true;
  },
  note: (g) => {
    bubble(g);
    g.fillStyle = "#3a3a6a";
    g.strokeStyle = "#3a3a6a";
    g.lineWidth = 5;
    g.beginPath();
    g.ellipse(38, 54, 9, 7, -0.4, 0, Math.PI * 2);
    g.fill();
    g.beginPath();
    g.moveTo(46, 52);
    g.lineTo(46, 18);
    g.quadraticCurveTo(60, 24, 64, 36);
    g.stroke();
    return true;
  },
  idea: (g) => {
    bubble(g);
    g.strokeStyle = "#e0a020";
    g.lineWidth = 3.5;
    for (const a of [-2.6, -2.0, -1.57, -1.1, -0.5]) {
      g.beginPath();
      g.moveTo(48 + Math.cos(a) * 20, 34 + Math.sin(a) * 20);
      g.lineTo(48 + Math.cos(a) * 27, 34 + Math.sin(a) * 27);
      g.stroke();
    }
    g.beginPath();
    g.arc(48, 34, 13, Math.PI * 0.8, Math.PI * 2.2);
    g.lineTo(54, 52);
    g.lineTo(42, 52);
    g.closePath();
    g.fillStyle = "#ffd84a";
    g.fill();
    g.lineWidth = 3;
    g.strokeStyle = INK;
    g.stroke();
    g.fillStyle = "#8a8070";
    g.fillRect(42, 53, 12, 8);
    return true;
  },
  sweat: (g) => {
    drop(g, 50, 52, 18, "#8fd0ff");
    return false;
  },
  tear: (g) => {
    drop(g, 34, 40, 9, "#9fd8ff");
    drop(g, 56, 70, 7, "#9fd8ff");
    return false;
  },
  anger: (g) => {
    // The cross-shaped vein mark: four bulging arcs around a centre.
    g.strokeStyle = "#d8262c";
    g.lineWidth = 8;
    for (let i = 0; i < 4; i++) {
      const a = (i * Math.PI) / 2 + Math.PI / 4;
      const cx = 48 + Math.cos(a) * 15;
      const cy = 48 + Math.sin(a) * 15;
      g.beginPath();
      g.arc(cx, cy, 14, a + Math.PI * 0.55, a + Math.PI * 1.45);
      g.stroke();
    }
    return false;
  },
};

const cache = new Map<Emote, EmoteArt>();

export function emoteArt(e: Emote): EmoteArt {
  const hit = cache.get(e);
  if (hit) return hit;
  const [c, g] = canvas();
  const b = PAINT[e](g);
  const a = { tex: Texture.from(c), bubble: b };
  cache.set(e, a);
  return a;
}
