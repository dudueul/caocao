/**
 * A painted figure on the field, the stage or the camp (ADR 0073), behind the
 * small interface the rigged figures had: `root` (scaled to the tile),
 * `setSize`, `setFacing`, `setPose`, `update`, `setTint` and `look`. It shows
 * one painted frame at a time, stepping through a pose's key frames at the
 * classic's rates: nothing is tweened between frames. The step's bob and a
 * jump's arc move the whole frame.
 */
import { Container, Sprite } from "pixi.js";
import {
  acquire,
  ahead,
  type Frame,
  groupOf,
  type Owner,
  release,
  type Sheet,
  sheetFor,
  sheetKey,
  U,
} from "./atlas.ts";
import { type SpriteLook, spriteLook } from "./look.ts";
import type { Facing, PoseName } from "./poses.ts";

/** Every pose a caller can ask for: the field's and the stage's names, plus run, sit and point. */
export type FigurePose =
  | "ready"
  | "walk"
  | "run"
  | "stand"
  | "weak"
  | "attack"
  | "guard"
  | "hurt"
  | "cheer"
  | "bow"
  | "kneel"
  | "sit"
  | "point"
  | "down"
  | "nod"
  | "shake"
  | "jump";

/** Where the feet stand below the tile's centre, in tiles. */
export const FEET_Y = 0.32;
/** The step's bob, in tiles. */
const BOB = 0.022;
const TICK = 1000 / 12;

interface Step {
  pose: PoseName;
  index: number;
  ms: number;
}

interface Seq {
  steps: Step[];
  loop: boolean;
  /** One-shot actions run on the battle's clock; loops on the wall clock. */
  fast: boolean;
  bob: boolean;
  jump: boolean;
}

const one = (pose: PoseName, index = 0): Seq => ({
  steps: [{ pose, index, ms: Number.POSITIVE_INFINITY }],
  loop: false,
  fast: false,
  bob: false,
  jump: false,
});

/** The sequence for a pose: frames, how long each shows, and what follows. */
function sequence(pose: FigurePose, fps: number | undefined, look: SpriteLook): Seq {
  const t = (n: number) => n * TICK;
  switch (pose) {
    case "ready":
    case "walk":
    case "run": {
      const name: PoseName = pose === "run" ? "run" : "walk";
      const rate = fps ?? (pose === "run" ? 7 : 4);
      return {
        steps: [
          { pose: name, index: 0, ms: 1000 / rate },
          { pose: name, index: 1, ms: 1000 / rate },
        ],
        loop: true,
        fast: false,
        bob: true,
        jump: false,
      };
    }
    case "weak":
      return {
        steps: [
          { pose: "weak", index: 0, ms: 500 },
          { pose: "weak", index: 1, ms: 500 },
        ],
        loop: true,
        fast: false,
        bob: false,
        jump: false,
      };
    case "attack": {
      const ticks =
        look.grip === "bow" || look.grip === "crossbow"
          ? [1, 2, 1, 2]
          : look.grip === "engine"
            ? [3, 1, 1, 2]
            : [2, 1, 1, 1];
      return {
        steps: [
          ...ticks.map((n, i) => ({ pose: "attack" as PoseName, index: i, ms: t(n) })),
          { pose: "attack", index: 3, ms: Number.POSITIVE_INFINITY },
        ],
        loop: false,
        fast: true,
        bob: false,
        jump: false,
      };
    }
    case "bow":
      return {
        steps: [
          { pose: "bow", index: 0, ms: t(1.5) },
          { pose: "bow", index: 1, ms: t(12) },
          { pose: "bow", index: 0, ms: t(1.5) },
          { pose: "stand", index: 0, ms: Number.POSITIVE_INFINITY },
        ],
        loop: false,
        fast: true,
        bob: false,
        jump: false,
      };
    case "nod":
      return {
        steps: [
          { pose: "nod", index: 0, ms: t(2) },
          { pose: "stand", index: 0, ms: t(1.5) },
          { pose: "nod", index: 0, ms: t(2) },
          { pose: "stand", index: 0, ms: Number.POSITIVE_INFINITY },
        ],
        loop: false,
        fast: true,
        bob: false,
        jump: false,
      };
    case "shake":
      return {
        steps: [
          { pose: "shake", index: 0, ms: t(2) },
          { pose: "shake", index: 1, ms: t(2) },
          { pose: "shake", index: 0, ms: t(2) },
          { pose: "shake", index: 1, ms: t(2) },
          { pose: "stand", index: 0, ms: Number.POSITIVE_INFINITY },
        ],
        loop: false,
        fast: true,
        bob: false,
        jump: false,
      };
    case "jump":
      return {
        steps: [
          { pose: "jump", index: 0, ms: t(1.5) },
          { pose: "jump", index: 1, ms: t(5) },
          { pose: "jump", index: 0, ms: t(1.5) },
          { pose: "stand", index: 0, ms: Number.POSITIVE_INFINITY },
        ],
        loop: false,
        fast: true,
        bob: false,
        jump: true,
      };
    case "hurt":
    case "guard":
      return { ...one(pose), fast: true };
    default:
      return one(pose);
  }
}

export class SpriteFigure implements Owner {
  /** Positioned at the tile's centre by the caller; scaled to the tile size. */
  readonly root = new Container();
  private body = new Sprite();
  private facing: Facing = "down";
  private mirror = false;
  private size = 48;
  private seq: Seq;
  private step = 0;
  private time = 0;
  /** The time into a looping pose, offset per figure so a line of them does not march in step. */
  private clock: number;
  private held = new Set<string>();
  private shown: Frame | null = null;
  private pose: FigurePose = "stand";
  private fps: number | undefined;
  /** Destroyed: sheets painted ahead for it are no longer wanted. */
  private gone = false;

  /**
   * `ahead`: which sheets to paint in idle time once the figure settles: the
   * battle's actions by default, the scene poses too on a stage, none in the camp.
   */
  constructor(
    readonly look: SpriteLook,
    private readonly opts: { ahead?: readonly ("action" | "scene")[] } = {},
  ) {
    this.root.addChild(this.body);
    this.body.scale.set(1 / U);
    this.body.position.set(0, FEET_Y);
    this.clock = (look.seed % 8) * 63;
    this.seq = sequence("stand", undefined, look);
    this.root.on("destroyed", () => {
      this.gone = true;
      for (const k of this.held) release(k);
      this.held.clear();
    });
  }

  /** A sheet painted ahead is still wanted while the figure lives and faces that way. */
  wants(facing: Facing): boolean {
    return !this.gone && facing === this.facing;
  }

  adopt(key: string): void {
    if (this.gone || this.held.has(key)) return;
    this.held.add(key);
    acquire(key);
  }

  /** The tile size in pixels. */
  setSize(tile: number): void {
    this.size = tile;
    this.root.scale.set(this.mirror ? -tile : tile, tile);
  }

  /** Side frames are painted facing right; `mirror` turns them to face left. */
  setFacing(f: Facing, mirror: boolean): void {
    this.mirror = f === "side" && mirror;
    this.root.scale.set(this.mirror ? -this.size : this.size, this.size);
    if (f === this.facing && this.shown) return;
    this.facing = f;
    this.show();
    // Keep the small idle sheets of every facing used; let go of the other
    // facings' action and scene sheets (the cache keeps them a while).
    for (const k of [...this.held])
      if (!k.includes(`|${f}|`) && !k.endsWith("|idle")) {
        this.held.delete(k);
        release(k);
      }
  }

  /** A pose; `fps` sets a step's rate (the field walks at eight steps a second). */
  setPose(pose: FigurePose, fps?: number): void {
    if (pose === this.pose && fps === this.fps && this.seq.loop) return;
    const oneShot = ![
      "ready",
      "walk",
      "run",
      "weak",
      "stand",
      "kneel",
      "sit",
      "point",
      "down",
      "cheer",
    ].includes(pose);
    if (pose === this.pose && !oneShot && fps === this.fps) return;
    this.pose = pose;
    this.fps = fps;
    this.seq = sequence(pose, fps, this.look);
    this.step = 0;
    this.time = 0;
    this.show();
  }

  setTint(color: number): void {
    this.body.tint = color;
  }

  /** `real`: wall-clock ms; `fast`: ms scaled by the battle speed (for one-shot actions). */
  update(real: number, fast: number): void {
    if (!this.shown) this.show();
    const s = this.seq;
    const dt = s.fast ? fast : real;
    this.clock += real;
    if (s.loop) {
      const total = s.steps.reduce((a, st) => a + st.ms, 0);
      let t = ((this.clock % total) + total) % total;
      let i = 0;
      while (i < s.steps.length - 1 && t >= (s.steps[i] as Step).ms) {
        t -= (s.steps[i] as Step).ms;
        i++;
      }
      if (i !== this.step) {
        this.step = i;
        this.show();
      }
      const ms = (s.steps[i] as Step).ms;
      this.body.y = FEET_Y - (s.bob && t < ms * 0.45 ? BOB : 0);
      return;
    }
    this.time += dt;
    let changed = false;
    while (this.step < s.steps.length - 1 && this.time >= (s.steps[this.step] as Step).ms) {
      this.time -= (s.steps[this.step] as Step).ms;
      this.step++;
      changed = true;
    }
    if (changed) this.show();
    if (s.jump) {
      const st = s.steps[this.step] as Step;
      const air = st.pose === "jump" && st.index === 1;
      this.body.y =
        FEET_Y - (air ? Math.sin((Math.PI * Math.min(this.time, st.ms)) / st.ms) * 0.24 : 0);
    } else this.body.y = FEET_Y;
  }

  private frame(pose: PoseName, index: number): Frame | null {
    const group = groupOf(pose);
    const key = sheetKey(this.look, this.facing, group);
    const sheet: Sheet = sheetFor(this.look, this.facing, group);
    if (!this.held.has(key)) {
      this.held.add(key);
      acquire(key);
    }
    // What this figure will likely show next, painted while the browser is idle:
    // once it settles in a facing (not for every turn of a walk).
    if (!this.seq.bob || this.pose === "ready")
      for (const g of this.opts.ahead ?? ["action"])
        if (!this.held.has(sheetKey(this.look, this.facing, g)))
          ahead(this.look, this.facing, g, this);
    const list = sheet.frames[pose];
    return list?.[Math.min(index, list.length - 1)] ?? null;
  }

  private show(): void {
    const st = this.seq.steps[Math.min(this.step, this.seq.steps.length - 1)] as Step;
    const f = this.frame(st.pose, st.index);
    if (!f || f === this.shown) return;
    this.shown = f;
    this.body.texture = f.tex;
    this.body.anchor.set(f.ax, f.ay);
  }
}

const looks = new Map<string, SpriteLook>();

/** The look for a unit, memoised (the field asks for it on every redraw). */
export function lookFor(classId: string, unitId: string, side: number, afoot = false): SpriteLook {
  const key = `${unitId}|${classId}|${side}|${afoot ? 1 : 0}`;
  let l = looks.get(key);
  if (!l) {
    l = spriteLook(unitId, classId, side, { afoot });
    looks.set(key, l);
  }
  return l;
}
