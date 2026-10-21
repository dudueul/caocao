/**
 * Composing a portrait: resolve the spec into a look, place the head (turn,
 * tilt, crop, size) over the body, paint back to front, then the rim light
 * and the shadow (ADR 0053, ADR 0070).
 */
import { FAMILY_HINTS, generic, TIER2 } from "./armies.ts";
import { backdrop } from "./backdrop.ts";
import { beard, moustache } from "./beard.ts";
import { body, chest, SHOULDERS } from "./body.ts";
import { alpha, cel, curve, type G, hash, inside, mix, path } from "./draw.ts";
import {
  brows,
  earLobe,
  ears,
  eyes,
  lines,
  marks,
  mouth,
  nose,
  patch,
  skin,
  strand,
  wounds,
} from "./face.ts";
import { backGear, frontGear } from "./gear.ts";
import { DEG, Head, yawOf } from "./head.ts";
import { hairLayer, headgear, headgearBack } from "./headgear.ts";
import { has, type Look } from "./look.ts";
import { OFFICERS } from "./officers.ts";
import { GOLD, HAIRS, type Pal, SIDES, SKINS } from "./palette.ts";
import { FAMILIES, type Family, PORTRAIT_H, PORTRAIT_W, type PortraitSpec } from "./types.ts";

/** The family of a class id: a base class, a tier-2 promotion, or a guess from the name. */
export function classFamily(classId: string): Family {
  if ((FAMILIES as readonly string[]).includes(classId)) return classId as Family;
  const known = TIER2[classId];
  if (known) return known;
  const id = classId.toLowerCase();
  for (const [re, fam] of FAMILY_HINTS) if (re.test(id)) return fam;
  return "infantry";
}

function isTier2(classId: string | undefined): boolean {
  return classId !== undefined && !(FAMILIES as readonly string[]).includes(classId);
}

/** Works (ADR 0069) have no face: the gate class, or a gate-… id given without a class. */
export function isWork(spec: PortraitSpec): boolean {
  return spec.classId === "gate" || (spec.classId === undefined && spec.id.startsWith("gate-"));
}

/** Where the head sits on the canvas: the skull's centre at the eye line. */
const HY = 140;

export function resolve(spec: PortraitSpec): Look {
  const entry = OFFICERS[spec.id];
  const fam0 = spec.classId ? classFamily(spec.classId) : undefined;
  const t = entry ?? generic(spec.id, fam0);
  const fam = fam0 ?? t.cls;
  const tier2 = isTier2(spec.classId) || (!entry && spec.id.includes("elite"));
  const side = (((spec.side ?? t.home) % SIDES.length) + SIDES.length) % SIDES.length;
  const phi = yawOf(t.turn);
  return {
    t,
    fam,
    tier2,
    named: entry !== undefined,
    P: SIDES[side] ?? (SIDES[0] as Pal),
    side,
    mood: spec.mood ?? "calm",
    hat: t.hat,
    seed: hash(spec.id),
    skin: SKINS[t.skin],
    hair: HAIRS[t.hair],
    H: new Head(t, phi),
    flip: phi < 0,
    bodyTurn: Math.abs(phi) > 0.2 ? 1 : 0,
    away: t.turn === "back",
  };
}

/** The head's place: x of the skull's centre, the vertical offset of the crop. */
function placement(L: Look): { x: number; dy: number; scale: number } {
  const H = L.H;
  const t = L.t;
  const x = 128 - 32 * H.s;
  const dy = t.crop === "high" ? -14 : t.crop === "low" ? 18 : 0;
  const stoop = has(L, "stoop") ? 6 : 0;
  return { x, dy: dy + stoop, scale: t.head / 100 };
}

/** The neck from under the jaw to the collar, its throat toward the face's side. */
function neck(g: G, L: Look): void {
  const H = L.H;
  const t = L.t;
  const k = (has(L, "thickNeck") ? 1.25 : 1) * Math.sqrt(SHOULDERS[t.build]);
  const [sk, skd] = L.skin;
  const rn = 27 * H.W * k;
  const top = H.p3(0, 30, -14).x;
  const bot = H.p3(0, 130, -16).x;
  const n = path((p) => {
    p.moveTo(top - rn, 6);
    p.bezierCurveTo(top - rn * 1.02, 60, bot - rn * 1.2, 100, bot - rn * 1.3, 136);
    p.lineTo(bot + rn * 1.3, 136);
    p.bezierCurveTo(bot + rn * 1.15, 100, top + rn * 1.05, 70, top + rn * 0.95, 30);
    p.closePath();
  });
  cel(g, n, sk, skd, 7);
  inside(g, n, () => {
    g.fillStyle = skd;
    g.save();
    g.translate(-4, 14);
    g.fill(H.outline());
    g.restore();
    g.fillRect(top - rn * 2, -20, rn * 1.1, 170);
    if (L.fam === "martialArtist" || t.build === "broad" || t.build === "huge")
      curve(g, alpha(skd, 0.9), 2.4, top - rn * 0.6, 50, top - rn * 0.1, 96, top + rn * 0.3, 128);
  });
}

function figure(g: G, L: Look): void {
  const { x, dy, scale } = placement(L);
  const roll = L.t.roll * DEG;
  g.lineJoin = "round";
  g.lineCap = "round";
  const headSpace = (draw: () => void) => {
    g.save();
    g.translate(x, HY + dy);
    g.translate(0, 110);
    g.rotate(roll);
    g.scale(scale, scale);
    g.translate(0, -110);
    draw();
    g.restore();
  };
  const gear = () => {
    g.save();
    g.translate(0, dy);
    backGear(g, L);
    g.restore();
  };
  // Gear slung on the back faces us when the man turns away.
  if (!L.away) gear();
  headSpace(() => headgearBack(g, L));
  g.save();
  g.translate(0, dy - 8);
  body(g, L);
  g.restore();
  headSpace(() => neck(g, L));
  g.save();
  g.translate(0, dy - 8);
  chest(g, L);
  g.restore();
  if (L.away) gear();
  headSpace(() => {
    skin(g, L);
    hairLayer(g, L);
    lines(g, L);
    nose(g, L);
    eyes(g, L);
    brows(g, L);
    marks(g, L);
    ears(g, L);
    beard(g, L);
    if (has(L, "earring")) {
      const lobe = earLobe(L);
      if (lobe) {
        const ring = path((p) => p.ellipse(lobe[0], lobe[1], 7, 9, 0.15, 0, Math.PI * 2));
        g.strokeStyle = "#2a1a12";
        g.lineWidth = 6;
        g.stroke(ring);
        g.strokeStyle = GOLD;
        g.lineWidth = 3.4;
        g.stroke(ring);
      }
    }
    mouth(g, L);
    moustache(g, L);
    if (has(L, "patch")) patch(g, L);
    headgear(g, L);
    if (has(L, "strand") && L.hat !== "knot") strand(g, L);
    if (L.mood === "hurt") wounds(g, L);
  });
  g.save();
  g.translate(0, dy);
  frontGear(g, L);
  g.restore();
}

/** The figure gets a margin wider than the rim light, so the rim stops at the frame. */
const M = 12;
let figCanvas: HTMLCanvasElement | null = null;
let rimCanvas: HTMLCanvasElement | null = null;

function canvas(w: number, h: number): HTMLCanvasElement | null {
  if (typeof document === "undefined") return null;
  const c = document.createElement("canvas");
  c.width = w;
  c.height = h;
  return c;
}

/** Paint a portrait onto a fresh canvas, or null when there is no DOM. */
export function portraitCanvas(spec: PortraitSpec): HTMLCanvasElement | null {
  const out = canvas(PORTRAIT_W, PORTRAIT_H);
  figCanvas ??= canvas(PORTRAIT_W + 2 * M, PORTRAIT_H + 2 * M);
  rimCanvas ??= canvas(PORTRAIT_W + 2 * M, PORTRAIT_H + 2 * M);
  if (!out || !figCanvas || !rimCanvas) return null;
  const g = out.getContext("2d");
  const f = figCanvas.getContext("2d");
  const r = rimCanvas.getContext("2d");
  if (!g || !f || !r) return null;
  const fw = figCanvas.width;
  const fh = figCanvas.height;
  const L = resolve(spec);
  const place = placement(L);
  backdrop(g, L, place.x, HY + place.dy);
  f.setTransform(1, 0, 0, 1, 0, 0);
  f.clearRect(0, 0, fw, fh);
  f.translate(M, M);
  figure(f, L);
  f.setTransform(1, 0, 0, 1, 0, 0);
  // Rim light: a band just inside the silhouette's edges, cool on the left
  // (light from behind) and warm on the right (the key light).
  for (const [dx, dy, wdx, wdy, col, a] of [
    [3, 0, 8, 1, mix(L.P.lace, "#ffffff", 0.45), 0.55],
    [-2.5, 2, -6, 5, "#fff0cc", 0.5],
  ] as const) {
    r.globalCompositeOperation = "source-over";
    r.clearRect(0, 0, fw, fh);
    r.drawImage(figCanvas, dx, dy);
    r.globalCompositeOperation = "destination-in";
    r.drawImage(figCanvas, 0, 0);
    r.globalCompositeOperation = "destination-out";
    r.drawImage(figCanvas, wdx, wdy);
    r.globalCompositeOperation = "source-in";
    r.fillStyle = col;
    r.fillRect(0, 0, fw, fh);
    f.globalAlpha = a;
    f.drawImage(rimCanvas, 0, 0);
    f.globalAlpha = 1;
  }
  g.save();
  g.shadowColor = "rgba(0,0,0,0.45)";
  g.shadowBlur = 12;
  g.shadowOffsetX = -4;
  g.shadowOffsetY = 4;
  g.drawImage(figCanvas, -M, -M);
  g.restore();
  const fade = g.createLinearGradient(0, 250, 0, PORTRAIT_H);
  fade.addColorStop(0, "rgba(8,6,10,0)");
  fade.addColorStop(1, "rgba(8,6,10,0.45)");
  g.fillStyle = fade;
  g.fillRect(0, 250, PORTRAIT_W, PORTRAIT_H - 250);
  return out;
}

/** The faction an officer is drawn with, for galleries; undefined for soldiers. */
export function portraitFaction(id: string): string | undefined {
  return OFFICERS[id]?.fac;
}

/** The figure alone on a transparent canvas, without backdrop or light: for silhouette checks. */
export function portraitFigure(spec: PortraitSpec): HTMLCanvasElement | null {
  const out = canvas(PORTRAIT_W, PORTRAIT_H);
  const g = out?.getContext("2d");
  if (!out || !g || isWork(spec)) return null;
  figure(g, resolve(spec));
  return out;
}

const cache = new Map<string, string>();

/**
 * PNG data URL of the bust, painted once per (id, class family, side, mood)
 * and cached. Never throws: returns "" when no canvas is available, and for
 * works (a gate in the attack forecast), so no soldier's face stands in for one.
 */
export function portraitUrl(spec: PortraitSpec): string {
  if (isWork(spec)) return "";
  try {
    const fam = spec.classId ? classFamily(spec.classId) : "";
    const t2 = isTier2(spec.classId) ? "+" : "";
    const key = `${spec.id}|${fam}${t2}|${spec.side ?? ""}|${spec.mood ?? "calm"}`;
    const hit = cache.get(key);
    if (hit !== undefined) return hit;
    const c = portraitCanvas(spec);
    const url = c ? c.toDataURL("image/png") : "";
    if (url) cache.set(key, url);
    return url;
  } catch {
    return "";
  }
}
