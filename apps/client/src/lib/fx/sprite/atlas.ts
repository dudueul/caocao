/**
 * Sheets (ADR 0073): a figure's frames for one facing, painted at about twice
 * the displayed size, trimmed to what was drawn and packed into one texture.
 * Frames come in three groups: the idle ones every unit shows at once (stand
 * and the step), the action ones (attack, guard, hurt, cheer, slump), painted
 * ahead in idle time, and the scene poses. Sheets are cached by look, so
 * soldiers who would be painted alike share theirs; a sheet nobody shows is
 * kept a while, then freed.
 */
import { Rectangle, Texture } from "pixi.js";
import { drawn, resetDrawn } from "./draw.ts";
import type { SpriteLook } from "./look.ts";
import { paintFrame } from "./paint.ts";
import { type Facing, FRAMES, type PoseName, SCENE_POSES } from "./poses.ts";

/** Sheet pixels per tile: about twice a 48 px tile. */
export const U = 96;
/** The scratch cell: room for the longest reach in any facing, the feet at (OX, OY). */
const CELL_W = 3.6;
const CELL_H = 3.8;
const OX = 1.8;
const OY = 2.3;
/** A margin round each trimmed frame, in pixels. */
const PAD = 2;
/** Sheet widths by group, about square for their frame counts. */
const ATLAS_W: Record<"idle" | "action" | "scene", number> = {
  idle: 512,
  action: 768,
  scene: 1024,
};

export type Group = "idle" | "action" | "scene";

const GROUPS: Record<Group, readonly PoseName[]> = {
  idle: ["stand", "walk"],
  action: ["attack", "guard", "hurt", "cheer", "weak"],
  scene: SCENE_POSES,
};

export interface Frame {
  tex: Texture;
  /** The feet's place in the frame, as a fraction (the sprite's anchor). */
  ax: number;
  ay: number;
}

export interface Sheet {
  frames: Partial<Record<PoseName, Frame[]>>;
  bytes: number;
  ms: number;
  users: number;
  canvas: HTMLCanvasElement;
  texture: Texture;
}

/** Painting and memory, for the report and the gallery. */
export const sheetStats = {
  sheets: 0,
  frames: 0,
  bytes: 0,
  /** Paint time, all told, and the part done ahead in idle time. */
  ms: 0,
  aheadMs: 0,
  painted: 0,
  freed: 0,
  ahead: 0,
};

let scratch: HTMLCanvasElement | null = null;
let staging: HTMLCanvasElement | null = null;
/** How much of the staging canvas the last sheet used (only that is cleared). */
let stagedH = 2048;

function canvas(w: number, h: number): HTMLCanvasElement {
  const c = document.createElement("canvas");
  c.width = w;
  c.height = h;
  return c;
}

export function groupOf(pose: PoseName): Group {
  return GROUPS.idle.includes(pose) ? "idle" : GROUPS.action.includes(pose) ? "action" : "scene";
}

/** Paints a sheet: each frame into the scratch cell, trimmed, shelf-packed, then cropped. */
function paintSheet(L: SpriteLook, facing: Facing, group: Group): Sheet {
  const t0 = performance.now();
  scratch ??= canvas(Math.ceil(CELL_W * U), Math.ceil(CELL_H * U));
  staging ??= canvas(1024, 2048);
  const sg = scratch.getContext("2d") as CanvasRenderingContext2D;
  const ag = staging.getContext("2d") as CanvasRenderingContext2D;
  ag.setTransform(1, 0, 0, 1, 0, 0);
  ag.clearRect(0, 0, staging.width, stagedH);
  const rects: {
    pose: PoseName;
    x: number;
    y: number;
    w: number;
    h: number;
    ax: number;
    ay: number;
  }[] = [];
  // Skyline packing: each frame goes where the stack is lowest.
  const width = ATLAS_W[group];
  const sky = new Int32Array(width);
  const place = (w: number, h: number): [number, number] => {
    let bx = 0;
    let by = Number.POSITIVE_INFINITY;
    for (let x = 0; x + w <= width; x += 2) {
      let top = 0;
      for (let i = x; i < x + w; i++) if ((sky[i] as number) > top) top = sky[i] as number;
      if (top < by) {
        by = top;
        bx = x;
      }
    }
    for (let i = bx; i < Math.min(width, bx + w + 1); i++) sky[i] = by + h + 1;
    return [bx, by];
  };
  for (const pose of GROUPS[group])
    for (let i = 0; i < FRAMES[pose]; i++) {
      sg.setTransform(1, 0, 0, 1, 0, 0);
      sg.clearRect(0, 0, scratch.width, scratch.height);
      sg.setTransform(U, 0, 0, U, OX * U, OY * U);
      resetDrawn();
      paintFrame(sg, L, pose, i, facing);
      const x0 = Math.max(0, Math.floor((drawn.x0 + OX) * U) - PAD);
      const y0 = Math.max(0, Math.floor((drawn.y0 + OY) * U) - PAD);
      const x1 = Math.min(scratch.width, Math.ceil((drawn.x1 + OX) * U) + PAD);
      const y1 = Math.min(scratch.height, Math.ceil((drawn.y1 + OY) * U) + PAD);
      const w = drawn.any ? Math.min(width, Math.max(1, x1 - x0)) : 1;
      const h = drawn.any ? Math.max(1, y1 - y0) : 1;
      const [x, y] = place(w, h);
      ag.drawImage(scratch, x0, y0, w, h, x, y, w, h);
      rects.push({ pose, x, y, w, h, ax: (OX * U - x0) / w, ay: (OY * U - y0) / h });
    }
  const W = rects.reduce((m, r) => Math.max(m, r.x + r.w), 1);
  const H = rects.reduce((m, r) => Math.max(m, r.y + r.h), 1);
  stagedH = H + 1;
  const out = canvas(W, H);
  (out.getContext("2d") as CanvasRenderingContext2D).drawImage(staging, 0, 0, W, H, 0, 0, W, H);
  const texture = Texture.from(out);
  texture.source.scaleMode = "linear";
  const frames: Partial<Record<PoseName, Frame[]>> = {};
  for (const r of rects) {
    const list = frames[r.pose] ?? [];
    frames[r.pose] = list;
    list.push({
      tex: new Texture({ source: texture.source, frame: new Rectangle(r.x, r.y, r.w, r.h) }),
      ax: r.ax,
      ay: r.ay,
    });
  }
  const ms = performance.now() - t0;
  const bytes = W * H * 4;
  sheetStats.sheets++;
  sheetStats.frames += rects.length;
  sheetStats.bytes += bytes;
  sheetStats.ms += ms;
  sheetStats.painted++;
  return { frames, bytes, ms, users: 0, canvas: out, texture };
}

const sheets = new Map<string, Sheet>();
/** Sheets nobody shows, oldest first, kept for a while in case they come back. */
const idle: string[] = [];
/** Idle sheets are kept up to this many bytes (and this many sheets). */
const KEEP_IDLE_BYTES = 16 * 1024 * 1024;
const KEEP_IDLE = 96;

export function sheetKey(L: SpriteLook, facing: Facing, group: Group): string {
  return `${L.key}|${facing}|${group}`;
}

/** The sheet for a look, facing and group, painted on first use. */
export function sheetFor(L: SpriteLook, facing: Facing, group: Group): Sheet {
  const key = sheetKey(L, facing, group);
  let s = sheets.get(key);
  if (!s) {
    s = paintSheet(L, facing, group);
    sheets.set(key, s);
    // Painted, nobody shows it yet: it may be freed like any idle sheet.
    idle.push(key);
    trim();
  }
  return s;
}

export function acquire(key: string): void {
  const s = sheets.get(key);
  if (!s) return;
  s.users++;
  const i = idle.indexOf(key);
  if (i >= 0) idle.splice(i, 1);
}

export function release(key: string): void {
  const s = sheets.get(key);
  if (!s) return;
  s.users = Math.max(0, s.users - 1);
  if (s.users > 0) return;
  idle.push(key);
  trim();
}

function idleBytes(): number {
  let n = 0;
  for (const k of idle) n += sheets.get(k)?.bytes ?? 0;
  return n;
}

function trim(): void {
  while (idle.length > KEEP_IDLE || (idle.length > 1 && idleBytes() > KEEP_IDLE_BYTES)) {
    const old = idle.shift() as string;
    const o = sheets.get(old);
    if (!o || o.users > 0) continue;
    sheets.delete(old);
    for (const list of Object.values(o.frames)) for (const f of list ?? []) f.tex.destroy(false);
    o.texture.destroy(true);
    sheetStats.sheets--;
    sheetStats.bytes -= o.bytes;
    sheetStats.freed++;
  }
}

// ---------------------------------------------------------------- painting ahead

/** Who asked for a sheet ahead: it takes the sheet when it is painted, if it still wants it. */
export interface Owner {
  wants(facing: Facing): boolean;
  adopt(key: string): void;
}

const queue: { L: SpriteLook; facing: Facing; group: Group; owner: Owner }[] = [];
let pumping = false;

/** Paints a sheet before it is needed, when the browser is idle, for `owner` to keep. */
export function ahead(L: SpriteLook, facing: Facing, group: Group, owner: Owner): void {
  const key = sheetKey(L, facing, group);
  if (sheets.has(key)) {
    owner.adopt(key);
    return;
  }
  if (queue.some((q) => q.owner === owner && q.facing === facing && q.group === group)) return;
  queue.push({ L, facing, group, owner });
  pump();
}

type Idle = (cb: (d: { timeRemaining(): number }) => void, o?: { timeout: number }) => number;

function pump(): void {
  if (pumping || queue.length === 0 || typeof window === "undefined") return;
  pumping = true;
  const ric = (window as unknown as { requestIdleCallback?: Idle }).requestIdleCallback;
  const run = (deadline?: { timeRemaining(): number }) => {
    pumping = false;
    // At least one sheet each time (the browser may never be idle while a battle
    // animates), more while there is time to spare; skipping the unwanted is free.
    let painted = 0;
    while (queue.length > 0 && (painted === 0 || (deadline?.timeRemaining() ?? 0) > 20)) {
      const next = queue.shift();
      if (!next) break;
      // Gone, or turned another way since: not needed after all.
      if (!next.owner.wants(next.facing)) continue;
      const key = sheetKey(next.L, next.facing, next.group);
      if (!sheets.has(key)) {
        const t = performance.now();
        sheetFor(next.L, next.facing, next.group);
        sheetStats.ahead++;
        sheetStats.aheadMs += performance.now() - t;
        painted++;
      }
      next.owner.adopt(key);
    }
    pump();
  };
  if (ric) ric(run, { timeout: 250 });
  else setTimeout(() => run(), 30);
}

/** Debug captures and the report: the counters from zero (the cache stays). */
export function resetSheetStats(): void {
  sheetStats.frames = 0;
  sheetStats.ms = 0;
  sheetStats.aheadMs = 0;
  sheetStats.painted = 0;
  sheetStats.freed = 0;
  sheetStats.ahead = 0;
}

/** What the cache holds now. */
export function cacheReport(): {
  sheets: number;
  bytes: number;
  /** Bytes of the sheets on screen now. */
  inUse: number;
  idle: number;
  queued: number;
} {
  let bytes = 0;
  let inUse = 0;
  for (const s of sheets.values()) {
    bytes += s.bytes;
    if (s.users > 0) inUse += s.bytes;
  }
  return { sheets: sheets.size, bytes, inUse, idle: idle.length, queued: queue.length };
}
