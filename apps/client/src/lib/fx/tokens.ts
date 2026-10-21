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

/**
 * A city gate (ADR 0069), painted once per side colour: a stone gatehouse
 * with a tiled roof over two studded wooden doors, a pennant in the side's
 * colour. Drawn a little taller than its tile, its feet on the tile.
 */
export function gateTexture(color: number): Texture {
  const key = `gate:${color}`;
  const hit = cache.get(key);
  if (hit) return hit;
  const c = document.createElement("canvas");
  c.width = 128;
  c.height = 160;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  g.lineJoin = "round";
  // Stone piers and lintel.
  const stone = g.createLinearGradient(0, 40, 0, 156);
  stone.addColorStop(0, "#9a958a");
  stone.addColorStop(1, "#6e6a62");
  g.fillStyle = stone;
  g.strokeStyle = "#2a2420";
  g.lineWidth = 3;
  g.beginPath();
  g.rect(10, 52, 108, 104);
  g.fill();
  g.stroke();
  g.strokeStyle = "rgba(40,34,30,0.35)";
  g.lineWidth = 1.5;
  for (let y = 66; y < 156; y += 14) {
    g.beginPath();
    g.moveTo(10, y);
    g.lineTo(118, y);
    g.stroke();
    for (let x = 10 + ((y / 14) % 2) * 9; x < 118; x += 18) {
      g.beginPath();
      g.moveTo(x, y - 14);
      g.lineTo(x, y);
      g.stroke();
    }
  }
  // The arch and the doors.
  g.fillStyle = "#1c1612";
  g.beginPath();
  g.moveTo(30, 156);
  g.lineTo(30, 92);
  g.quadraticCurveTo(64, 62, 98, 92);
  g.lineTo(98, 156);
  g.closePath();
  g.fill();
  const wood = g.createLinearGradient(30, 0, 98, 0);
  wood.addColorStop(0, "#6d4527");
  wood.addColorStop(0.5, "#8a5a33");
  wood.addColorStop(1, "#5e3b21");
  g.fillStyle = wood;
  g.beginPath();
  g.moveTo(34, 156);
  g.lineTo(34, 94);
  g.quadraticCurveTo(64, 68, 94, 94);
  g.lineTo(94, 156);
  g.closePath();
  g.fill();
  g.strokeStyle = "#2a1a10";
  g.lineWidth = 2.5;
  g.beginPath();
  g.moveTo(64, 76);
  g.lineTo(64, 156);
  g.stroke();
  g.fillStyle = "#c9b48a";
  for (let y = 98; y < 152; y += 12)
    for (const x of [42, 54, 74, 86]) {
      g.beginPath();
      g.arc(x, y, 2.6, 0, Math.PI * 2);
      g.fill();
    }
  // Tiled roof.
  g.fillStyle = "#3a3330";
  g.strokeStyle = "#1a1512";
  g.lineWidth = 3;
  g.beginPath();
  g.moveTo(2, 56);
  g.quadraticCurveTo(64, 30, 126, 56);
  g.lineTo(112, 40);
  g.quadraticCurveTo(64, 20, 16, 40);
  g.closePath();
  g.fill();
  g.stroke();
  // Pennant in the side's colour.
  g.strokeStyle = "#2a1a10";
  g.lineWidth = 2;
  g.beginPath();
  g.moveTo(64, 26);
  g.lineTo(64, 2);
  g.stroke();
  g.fillStyle = hex(color, 1.1);
  g.beginPath();
  g.moveTo(65, 3);
  g.lineTo(92, 9);
  g.lineTo(65, 16);
  g.closePath();
  g.fill();
  const t = Texture.from(c);
  cache.set(key, t);
  return t;
}
