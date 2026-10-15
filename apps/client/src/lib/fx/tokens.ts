/**
 * Our own unit tokens for play without the classic skin: a glossy medallion in
 * the side's colour with a metal rim, painted once per colour.
 */
import { Texture } from "pixi.js";

const cache = new Map<string, Texture>();

const hex = (c: number, k = 1) => {
  const r = Math.min(255, Math.round(((c >> 16) & 255) * k));
  const g = Math.min(255, Math.round(((c >> 8) & 255) * k));
  const b = Math.min(255, Math.round((c & 255) * k));
  return `rgb(${r},${g},${b})`;
};

/** A 128 px medallion; `ranged` adds an inner ring. */
export function tokenTexture(color: number, ranged: boolean): Texture {
  const key = `${color}:${ranged}`;
  const hit = cache.get(key);
  if (hit) return hit;
  const c = document.createElement("canvas");
  c.width = 128;
  c.height = 128;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  // Metal rim.
  const rim = g.createLinearGradient(20, 10, 108, 118);
  rim.addColorStop(0, "#fff4cf");
  rim.addColorStop(0.35, "#d9b46a");
  rim.addColorStop(0.7, "#8a6a32");
  rim.addColorStop(1, "#f0d48e");
  g.fillStyle = rim;
  g.beginPath();
  g.arc(64, 64, 58, 0, Math.PI * 2);
  g.fill();
  // Face in the side's colour, lit from the top left.
  const face = g.createRadialGradient(46, 40, 6, 64, 64, 54);
  face.addColorStop(0, hex(color, 1.55));
  face.addColorStop(0.55, hex(color, 1));
  face.addColorStop(1, hex(color, 0.55));
  g.fillStyle = face;
  g.beginPath();
  g.arc(64, 64, 50, 0, Math.PI * 2);
  g.fill();
  if (ranged) {
    g.strokeStyle = "rgba(255,255,255,0.45)";
    g.lineWidth = 3;
    g.beginPath();
    g.arc(64, 64, 41, 0, Math.PI * 2);
    g.stroke();
  }
  // Gloss.
  const gloss = g.createLinearGradient(0, 14, 0, 70);
  gloss.addColorStop(0, "rgba(255,255,255,0.45)");
  gloss.addColorStop(1, "rgba(255,255,255,0)");
  g.fillStyle = gloss;
  g.beginPath();
  g.ellipse(64, 44, 38, 26, 0, Math.PI, 0);
  g.fill();
  const t = Texture.from(c);
  cache.set(key, t);
  return t;
}
