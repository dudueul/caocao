/**
 * The stage renderer (ADR 0063, 0064): a small tile map acted on by our
 * rigged figures, for story scenes and the walkable camp. It follows the
 * battle field's way of working (a Pixi app, painted terrain, layers sorted
 * by row, particles, ambience, a colour grade) but is its own renderer: it
 * moves actors around, poses them, pans the camera and lights the scene. The
 * script runner (`script.ts`) and the camp screen tell it what to do; it
 * decides nothing about the story.
 */
import {
  type ActPose,
  type Emote,
  type Facing4,
  parseStage,
  SOLID_PROPS,
  type StageDef,
  type StageFx,
  type StageGrid,
  type StageLight,
  type StageTile,
} from "@tk/content";
import type { Cell, TerrainId, TileChange, WeatherKind } from "@tk/rules";
import { Application, Container, DisplacementFilter, Graphics, Sprite, Text } from "pixi.js";
import { AdjustmentFilter, AdvancedBloomFilter } from "pixi-filters";
import { audio, SFX_NAMES, type Sfx } from "../audio/index.ts";
import { Ambient } from "../fx/ambient.ts";
import { ChibiRig, type Look, lookFor, type RigPose } from "../fx/chibi.ts";
import * as fx from "../fx/effects.ts";
import { Particles } from "../fx/particles.ts";
import { type FxTextures, makeTextures } from "../fx/textures.ts";
import { emoteArt } from "./emotes.ts";
import { paintStage, type Setting, settingOf } from "./floors.ts";
import { findPath, same } from "./path.ts";
import { buildProp, faceHorse, flameTexture, type PropView } from "./props.ts";

/** Pixels per tile inside the world; the camera scales the world to the screen. */
export const T = 96;
/** Room kept above the top row for tall props (a gate tower, pillars), in world pixels. */
const HEADROOM = T * 0.7;
/** Figures are drawn a little larger than a tile: the stage is small, the actors matter. */
const FIG = 1.18;
const BG = 0x14130f;
const FONT = "'Noto Serif KR', 'Nanum Myeongjo', 'Noto Sans KR', serif";

/** Colour grade per weather, as on the battlefield. */
const WEATHER = {
  sunny: { saturation: 1.1, contrast: 1.05, brightness: 1.03, red: 1.03, green: 1, blue: 0.96 },
  cloudy: { saturation: 0.92, contrast: 1.02, brightness: 0.95, red: 0.98, green: 1, blue: 1.03 },
  rain: { saturation: 0.82, contrast: 1.04, brightness: 0.86, red: 0.93, green: 0.98, blue: 1.08 },
  wind: { saturation: 1.04, contrast: 1.05, brightness: 1.0, red: 1.01, green: 1.02, blue: 0.98 },
} as const;

/** Light over the stage: a grade on top of the weather's, and how strongly lamps glow. */
const LIGHT: Record<
  StageLight,
  { brightness: number; red: number; green: number; blue: number; saturation: number; glow: number }
> = {
  day: { brightness: 1, red: 1, green: 1, blue: 1, saturation: 1, glow: 0.12 },
  dusk: { brightness: 0.88, red: 1.12, green: 0.93, blue: 0.8, saturation: 1.02, glow: 0.6 },
  night: { brightness: 0.56, red: 0.72, green: 0.84, blue: 1.2, saturation: 0.62, glow: 1 },
};

/** Script sound names that differ from the synth's (ADR 0052). */
const SFX_ALIAS: Readonly<Record<string, Sfx>> = {
  march: "move",
  hooves: "hoof",
  critical: "crit",
  horn: "arrive",
  coins: "coin",
  lose: "loss",
};

/** One-shot poses and how long they take (seconds, as the rig plays them). */
const ONE_SHOT: Partial<Record<ActPose, number>> = {
  bow: 1.3,
  nod: 0.7,
  shake: 0.9,
  jump: 0.6,
  attack: 0.56,
  hurt: 0.45,
  guard: 0.5,
};
const HELD = new Set<ActPose>(["kneel", "down", "cheer"]);

export interface StageOptions {
  /** Fewer effects and no full-screen filters (slow devices, `?lowfx`). */
  lite: boolean;
  /** No camera moves, zoom or screen shake (prefers-reduced-motion). */
  calm: boolean;
}

/** Screen areas the stage should keep clear (the dialogue box, a top bar), in CSS px. */
export interface Insets {
  top: number;
  right: number;
  bottom: number;
  left: number;
}

/** Someone on the stage, as the stage needs to know them. */
export interface ActorSpec {
  id: string;
  /** Whose look: an officer or troop template id (beards and caps follow it). */
  officer: string;
  classId: string;
  side: number;
  /** Where they stand at the start; null = offstage. */
  at: Cell | null;
  facing: Facing4;
}

interface ActorView {
  spec: ActorSpec;
  root: Container;
  shadow: Sprite;
  rig: ChibiRig;
  /** The look standing up; cavalry get off their horses to kneel or lie down. */
  look: Look;
  afoot: boolean;
  cell: Cell;
  /** Position in tiles (fractional while moving). */
  pos: { x: number; y: number };
  facing: Facing4;
  held: ActPose | null;
  on: boolean;
  moving: boolean;
  /** Turned by the script since they last spoke (the player then leaves the facing alone). */
  turned: boolean;
  tag: Container | null;
}

interface Tween {
  dur: number;
  t: number;
  step: (k: number) => void;
  done: () => void;
}

interface Burning {
  cell: Cell;
  glow: Sprite;
}

const DIRS: Record<Facing4, Cell> = {
  up: { x: 0, y: -1 },
  down: { x: 0, y: 1 },
  left: { x: -1, y: 0 },
  right: { x: 1, y: 0 },
};

const isPair = (v: unknown): v is readonly [string, string] => Array.isArray(v);

/** Which way to look from one tile toward another. */
export function facingTo(from: Cell, to: Cell, fallback: Facing4 = "down"): Facing4 {
  const dx = to.x - from.x;
  const dy = to.y - from.y;
  if (dx === 0 && dy === 0) return fallback;
  if (Math.abs(dx) >= Math.abs(dy)) return dx > 0 ? "right" : "left";
  return dy > 0 ? "down" : "up";
}

export class Stage {
  private app = new Application();
  /** The camera: everything in world space. */
  private view = new Container();
  private world = new Container();
  /** The painted ground: hard tiles, water, and the soft edges (clipped to the stage). */
  private terrain = new Container();
  private ground = new Container();
  private water = new Container();
  private edges = new Container();
  private marks = new Container();
  private fireGlow = new Graphics();
  private things = new Container();
  private fxLayer = new Container();
  private glow = new Container();
  private above = new Container();
  private marker = new Graphics();
  private skyClip = new Graphics();
  private vignette = new Sprite();
  private flash = new Graphics();
  private particles = new Particles();
  private tex!: FxTextures;
  private ambient!: Ambient;
  private grade = new AdjustmentFilter();
  private waterFilter: DisplacementFilter | null = null;
  private waterMap: Sprite | null = null;
  private mounted = false;
  private destroyed = false;

  private def: StageDef | null = null;
  private grid: StageGrid = { width: 1, height: 1, tiles: ["plain"] };
  private tiles: StageTile[] = ["plain"];
  private setting: Setting = "outdoor";
  private props: {
    view: PropView;
    at: Cell;
    solid: boolean;
    /** A wide halo and a bright core: the light a lamp or brazier gives. */
    light: [Sprite, Sprite] | null;
  }[] = [];
  private actors = new Map<string, ActorView>();
  private burning: Burning[] = [];
  private tweens = new Set<Tween>();
  /** Small per-frame jobs (emotes, glowing stations); false from one ends it. */
  private jobs = new Set<(ms: number) => boolean>();
  private clock = 0;
  private flameAcc = 0;
  private lightNow = { ...LIGHT.day };
  private weather: WeatherKind = "sunny";
  private insets: Insets = { top: 0, right: 0, bottom: 0, left: 0 };
  private cover = 0;
  /** Screen pixels per world pixel when the whole stage fits (or the smallest readable size). */
  private base = 1;
  private cam = { x: 0, y: 0, z: 1 };
  /**
   * A fixed camera target (`sticky`: a close-up, or the camp following 조조)
   * and the zoom asked for (null: the default framing).
   */
  private camGoal: {
    at: Cell | string | readonly [string, string] | null;
    z: number | null;
    sticky: boolean;
  } = {
    at: null,
    z: null,
    sticky: false,
  };
  /**
   * The default zoom: 1 (the whole stage fits), or closer for a stage much
   * taller than wide (two sets stacked), which starts framed to its width.
   */
  private defaultZoom = 1;
  /**
   * What the eye is on when the camera is not fixed: a speaker (with the one
   * they speak to), a walker, a pointed tile.
   */
  private attention: Cell | string | readonly [string, string] | null = null;
  private speakerId: string | null = null;
  private shakeAmp = 0;
  private flashAlpha = 0;
  private fastMode = false;

  /** Tapped or clicked: the tile (null off the stage) and the actor standing on it. */
  onTap: ((cell: Cell, actor: string | null) => void) | null = null;

  constructor(private readonly opts: StageOptions = { lite: false, calm: false }) {}

  async mount(host: HTMLElement): Promise<void> {
    await this.app.init({
      resizeTo: host,
      background: BG,
      antialias: true,
      autoDensity: true,
      resolution: Math.min(window.devicePixelRatio || 1, 2),
    });
    if (this.destroyed) {
      this.app.destroy(true, { children: true });
      return;
    }
    host.appendChild(this.app.canvas);
    this.tex = makeTextures();
    this.ambient = new Ambient(this.tex, this.opts.lite);
    this.vignette.texture = this.tex.vignette;
    this.vignette.eventMode = "none";
    this.things.sortableChildren = true;
    this.terrain.addChild(this.ground, this.water, this.edges);
    this.world.addChild(
      this.terrain,
      this.marks,
      this.fireGlow,
      this.things,
      this.ambient.world,
      this.skyClip,
    );
    this.ambient.world.mask = this.skyClip;
    this.world.filters = [this.grade];
    this.glow.blendMode = "add";
    this.fxLayer.addChild(this.particles.layer);
    this.view.addChild(this.world, this.glow, this.fxLayer, this.above);
    this.above.addChild(this.marker);
    if (!this.opts.lite) {
      this.particles.layer.filters = [
        new AdvancedBloomFilter({ threshold: 0.35, bloomScale: 1.2, brightness: 1, blur: 6 }),
      ];
      const noise = this.tex.noise;
      noise.source.style.addressMode = "repeat";
      noise.source.style.update();
      this.waterMap = new Sprite(noise);
      this.waterMap.renderable = false;
      this.world.addChild(this.waterMap);
      this.waterFilter = new DisplacementFilter({ sprite: this.waterMap, scale: 6 });
    }
    this.app.stage.addChild(this.view, this.ambient.screen, this.vignette, this.flash);
    this.app.stage.eventMode = "static";
    this.app.stage.hitArea = this.app.screen;
    this.app.stage.on("pointertap", (e) => {
      const p = this.view.toLocal({ x: e.global.x, y: e.global.y });
      const c = { x: Math.floor(p.x / T), y: Math.floor(p.y / T) };
      if (c.x < 0 || c.y < 0 || c.x >= this.grid.width || c.y >= this.grid.height) return;
      // A tap on a figure's body (above its tile) counts as a tap on its tile.
      const hit = this.actorNear(p.x / T, p.y / T);
      this.onTap?.(hit ? hit.cell : c, hit ? hit.spec.id : this.actorAt(c));
    });
    this.app.renderer.on("resize", () => this.layout());
    // After a lost and restored WebGL context the cached ground must be drawn
    // again, or the stage would come back black.
    this.app.canvas.addEventListener("webglcontextrestored", () => {
      requestAnimationFrame(() => {
        if (!this.mounted) return;
        for (const layer of [this.ground, this.edges])
          if (layer.isCachedAsTexture) layer.updateCacheTexture();
      });
    });
    this.app.ticker.add(this.animate);
    this.mounted = true;
    if (this.def) this.build();
  }

  /** Debug captures: stop the clock and step it by hand. */
  manualClock(on: boolean): void {
    if (!this.mounted) return;
    if (on) this.app.ticker.stop();
    else this.app.ticker.start();
  }

  step(ms: number): void {
    if (this.mounted) this.app.ticker.update(this.app.ticker.lastTime + ms);
  }

  /** Tears the stage down; safe to call more than once, or before `mount` has finished. */
  destroy(): void {
    if (this.destroyed) return;
    this.destroyed = true;
    this.onTap = null;
    const pending = [...this.tweens];
    this.tweens.clear();
    this.jobs.clear();
    this.actors.clear();
    this.props = [];
    this.burning = [];
    if (this.mounted) {
      this.mounted = false;
      this.app.ticker.remove(this.animate);
      this.particles.clear();
      this.app.destroy(true, { children: true });
    }
    // Whoever waits on a move goes on (and finds the stage gone).
    for (const tw of pending) tw.done();
  }

  /** Torn down (the screen went away). */
  get gone(): boolean {
    return this.destroyed;
  }

  /** Skip mode: every move finishes at once and nothing waits. */
  get fast(): boolean {
    return this.fastMode;
  }
  set fast(on: boolean) {
    this.fastMode = on;
  }

  setInsets(i: Insets): void {
    this.insets = i;
    if (this.mounted) this.layout();
  }

  /**
   * Screen pixels at the bottom covered for a while (the camp's dialogue box):
   * the camera keeps whoever it follows above them, without changing the size.
   */
  setCover(bottom: number): void {
    this.cover = bottom;
  }

  // ------------------------------------------------------------ the stage

  /**
   * Sets up a stage and its cast (actors with `at` stand there; the rest wait
   * offstage). `afoot`: nobody rides (the camp).
   */
  load(def: StageDef, cast: readonly ActorSpec[], afoot = false): void {
    if (this.destroyed) return;
    this.def = def;
    this.afoot = afoot;
    this.fastMode = false;
    this.grid = parseStage(def);
    this.tiles = [...this.grid.tiles];
    this.setting = settingOf(def, this.tiles);
    this.weather = def.weather ?? "sunny";
    this.lightNow = { ...LIGHT[def.light ?? "day"] };
    this.burning = [];
    this.attention = null;
    this.speakerId = null;
    this.camGoal = { at: null, z: null, sticky: false };
    for (const a of this.actors.values()) a.root.destroy({ children: true });
    this.actors.clear();
    this.castList = [...cast];
    if (this.mounted) this.build();
  }

  private castList: ActorSpec[] = [];
  private afoot = false;

  private build(): void {
    const def = this.def;
    if (!def || this.destroyed) return;
    const pending = [...this.tweens];
    this.tweens.clear();
    for (const tw of pending) tw.done();
    this.jobs.clear();
    this.particles.clear();
    for (const c of this.things.removeChildren()) c.destroy({ children: true });
    for (const c of this.glow.removeChildren()) c.destroy();
    for (const c of this.marks.removeChildren()) c.destroy({ children: true });
    for (const c of this.above.removeChildren())
      if (c !== this.marker) c.destroy({ children: true });
    this.above.addChild(this.marker);
    this.fireGlow.clear();
    this.paintGround();
    this.props = [];
    for (const p of def.props ?? []) {
      const near = (dx: number, dy: number) =>
        (def.props ?? []).some(
          (q) => q.kind === p.kind && q.at.x === p.at.x + dx && q.at.y === p.at.y + dy,
        );
      const view = buildProp(p, near);
      if (view.kind === "horse") faceHorse(view, p.at.x >= this.grid.width / 2);
      view.root.scale.set(T);
      const g = this.ground_(p.at);
      view.root.position.set(g.x, g.y);
      // Props sort with the figures by row. A gateway is tall: whoever stands
      // on the tile behind it is drawn in front of it, not hidden by its roof.
      view.root.zIndex = (p.at.y + (view.behind ? -0.5 : 0.5)) * T - 1;
      this.things.addChild(view.root);
      let light: [Sprite, Sprite] | null = null;
      if (view.light) {
        const halo = new Sprite(this.tex.glow);
        halo.anchor.set(0.5);
        halo.tint = view.light.color;
        halo.width = view.light.reach * T * 2;
        halo.height = view.light.reach * T * 1.5;
        halo.position.set(g.x + view.light.dx * T, g.y + view.light.dy * T * 0.5);
        const core = new Sprite(this.tex.glow);
        core.anchor.set(0.5);
        core.tint = 0xffe2a8;
        core.width = T * 0.9;
        core.height = T * 0.9;
        core.position.set(g.x + view.light.dx * T, g.y + view.light.dy * T);
        this.glow.addChild(halo, core);
        light = [halo, core];
      }
      this.props.push({ view, at: p.at, solid: p.solid ?? SOLID_PROPS.has(p.kind), light });
    }
    this.ambient.setWeather(this.setting === "outdoor" ? this.weather : "sunny");
    this.actors.clear();
    for (const s of this.castList) this.addActor(s);
    this.applyLight();
    this.layout();
    this.snapCamera();
  }

  /** The point a figure or prop stands on: the tile's middle, a little low. */
  private ground_(c: { x: number; y: number }): { x: number; y: number } {
    return { x: c.x * T + T / 2, y: c.y * T + T / 2 + T * 0.32 };
  }

  private paintGround(): void {
    const { width: w, height: h } = this.grid;
    for (const layer of [this.ground, this.water, this.edges]) {
      layer.cacheAsTexture(false);
      for (const c of layer.removeChildren()) c.destroy({ children: true });
    }
    this.skyClip
      .clear()
      .rect(0, 0, w * T, h * T)
      .fill(0xffffff);
    // The soft edges spill past their tiles: clipped to the stage inside their
    // own layer, so the clip is cached along with them.
    const edges = new Container();
    const clip = new Graphics().rect(0, 0, w * T, h * T).fill(0xffffff);
    paintStage({ width: w, height: h, tiles: this.tiles }, T, this.setting, {
      ground: this.ground,
      water: this.water,
      edges,
    });
    edges.mask = clip;
    this.edges.addChild(edges, clip);
    this.water.filters = this.waterFilter ? [this.waterFilter] : [];
    if (this.waterMap) {
      this.waterMap.scale.set((T * 2.5) / 128);
      this.waterFilter?.scale.set(T * 0.09, T * 0.06);
    }
    this.cacheGround();
  }

  private cacheGround(): void {
    const { width: w, height: h } = this.grid;
    const res =
      this.app.renderer.resolution * Math.min(1.5, Math.max(0.75, this.base * this.cam.z));
    if (w * T * res <= 4096 && h * T * res <= 4096)
      for (const layer of [this.ground, this.edges])
        if (layer.children.length > 0) layer.cacheAsTexture({ resolution: res });
  }

  // ------------------------------------------------------------ layout and camera

  private layout(): void {
    if (!this.mounted) return;
    const { width: W, height: H } = this.app.screen;
    const i = this.insets;
    const availW = Math.max(100, W - i.left - i.right - 16);
    const availH = Math.max(100, H - i.top - i.bottom - 16);
    const fitW = availW / (this.grid.width * T);
    const fit = Math.min(fitW, availH / (this.grid.height * T + HEADROOM));
    // Figures stay readable on a phone: below that size the camera follows the action.
    const tile = Math.max(Math.min(fit * T, 128), Math.min(44, Math.min(W, H) / 7));
    const before = this.base;
    this.base = tile / T;
    // Two sets stacked in one tall stage: start framed to the width (at most twice as close).
    const tall = this.grid.height > this.grid.width * 1.4;
    this.defaultZoom = tall ? Math.max(1, Math.min(2, Math.min(fitW * T, 128) / tile)) : 1;
    this.vignette.width = W;
    this.vignette.height = H;
    this.ambient.layout(this.grid.width * T, this.grid.height * T, T, W, H);
    if (Math.abs(before - this.base) > 0.05) {
      for (const layer of [this.ground, this.edges]) layer.cacheAsTexture(false);
      this.cacheGround();
    }
    this.app.canvas.dataset.geometry = JSON.stringify({ tile: Math.round(tile) });
    this.snapCamera();
  }

  /** True when the whole stage is on screen at the default framing. */
  get fits(): boolean {
    if (!this.mounted) return true;
    const { width: W, height: H } = this.app.screen;
    const i = this.insets;
    return (
      this.grid.width * T * this.base <= W - i.left - i.right - 8 &&
      (this.grid.height * T + HEADROOM) * this.base <= H - i.top - i.bottom - 8
    );
  }

  private goalPoint(): { x: number; y: number } {
    const g = this.camGoal;
    const at = g.sticky ? g.at : this.attention;
    if (isPair(at)) {
      // Speaker and listener together when both fit on screen, else the speaker.
      const [a, b] = [this.actors.get(at[0]), this.actors.get(at[1])];
      if (a?.on && b?.on) {
        const { width: W, height: H } = this.app.screen;
        const i = this.insets;
        const s = this.base * this.cam.z;
        const fitsX = (Math.abs(a.pos.x - b.pos.x) + 1.2) * T * s < W - i.left - i.right;
        const fitsY = (Math.abs(a.pos.y - b.pos.y) + 1.6) * T * s < H - i.top - i.bottom;
        if (fitsX && fitsY)
          return {
            x: ((a.pos.x + b.pos.x) / 2) * T + T / 2,
            y: ((a.pos.y + b.pos.y) / 2) * T + T / 3,
          };
      }
      if (a?.on) return { x: a.pos.x * T + T / 2, y: a.pos.y * T + T / 3 };
      return this.restPoint();
    }
    if (typeof at === "string") {
      const a = this.actors.get(at);
      if (a?.on) return { x: a.pos.x * T + T / 2, y: a.pos.y * T + T / 2 };
    } else if (at) return { x: at.x * T + T / 2, y: at.y * T + T / 2 };
    return this.restPoint();
  }

  /** The zoom the camera heads for; calm players keep the default framing. */
  private zoomNow(): number {
    return this.opts.calm ? this.defaultZoom : (this.camGoal.z ?? this.defaultZoom);
  }

  /** Where the camera rests with nothing to look at: the top of a tall stage, else the first figure. */
  private restPoint(): { x: number; y: number } {
    const mid = { x: (this.grid.width * T) / 2, y: (this.grid.height * T - HEADROOM) / 2 };
    if (this.defaultZoom > 1) return { x: mid.x, y: -HEADROOM };
    for (const a of this.actors.values())
      if (a.on) return { x: a.pos.x * T + T / 2, y: a.pos.y * T + T / 2 };
    return mid;
  }

  /** Keeps the view on the stage: no empty margins when it is larger than the screen. */
  private clampFocus(p: { x: number; y: number }, s: number): { x: number; y: number } {
    const { width: W, height: H } = this.app.screen;
    const i = this.insets;
    /**
     * One axis: a stage smaller than the free area (between the top bar and the
     * dialogue box) is centred in it; a larger one keeps its edges at the free
     * area's edges, so whoever the camera looks at is never under the box.
     */
    const axis = (v: number, lo: number, hi: number, a0: number, a1: number) => {
      if ((hi - lo) * s <= a1 - a0) return (lo + hi) / 2;
      const half = (a1 - a0) / 2 / s;
      return Math.max(lo + half, Math.min(hi - half, v));
    };
    return {
      x: axis(p.x, 0, this.grid.width * T, i.left, W - i.right),
      y: axis(p.y, -HEADROOM, this.grid.height * T, i.top, H - Math.max(i.bottom, this.cover)),
    };
  }

  private snapCamera(): void {
    if (!this.mounted) return;
    const z = this.zoomNow();
    const p = this.clampFocus(this.goalPoint(), this.base * z);
    this.cam = { x: p.x, y: p.y, z };
    this.applyCamera();
  }

  private applyCamera(): void {
    const { width: W, height: H } = this.app.screen;
    const i = this.insets;
    const s = this.base * this.cam.z;
    const p = this.clampFocus(this.cam, s);
    let x = i.left + (W - i.left - i.right) / 2 - p.x * s;
    let y = i.top + (H - i.top - i.bottom) / 2 - p.y * s;
    if (this.shakeAmp > 0.25) {
      x += (Math.random() * 2 - 1) * this.shakeAmp;
      y += (Math.random() * 2 - 1) * this.shakeAmp;
    }
    this.view.position.set(x, y);
    this.view.scale.set(s);
  }

  /** Eases the camera toward its goal; a pan lasts about half a second. */
  private moveCamera(ms: number): void {
    const z = this.zoomNow();
    const goal = this.clampFocus(this.goalPoint(), this.base * z);
    if (this.fastMode || this.opts.calm) {
      this.cam = { x: goal.x, y: goal.y, z };
    } else {
      const k = 1 - Math.exp(-ms / 170);
      this.cam.x += (goal.x - this.cam.x) * k;
      this.cam.y += (goal.y - this.cam.y) * k;
      this.cam.z += (z - this.cam.z) * k;
    }
    this.applyCamera();
  }

  /** Pans to a tile or an actor; `zoom` in ‰ (1000: the whole stage fits). */
  camera(at: Cell | string, zoom?: number): Promise<void> {
    const z = zoom === undefined ? this.camGoal.z : Math.max(0.5, Math.min(3, zoom / 1000));
    const zz = z ?? this.defaultZoom;
    // A close-up holds its target; a wide framing keeps following whoever acts
    // next (which matters on small screens, where the stage does not fit).
    const sticky = z !== null && zz > 1.001;
    this.camGoal = { at: sticky ? at : null, z, sticky };
    if (!sticky) this.attention = at;
    return this.wait(650);
  }

  /** Keeps an actor (or two talking) in view: the camp follows 조조. */
  follow(id: string | readonly [string, string] | null, snap = true): void {
    this.camGoal = { at: id, z: null, sticky: id !== null };
    if (snap) this.snapCamera();
  }

  private look(id: string): void {
    if (!this.camGoal.sticky) this.attention = id;
  }

  // ------------------------------------------------------------ the frame

  private animate = (): void => {
    const real = this.app.ticker.deltaMS;
    this.clock += real;
    for (const job of [...this.jobs]) if (!job(real)) this.jobs.delete(job);
    const secs = this.clock / 1000;
    for (const tw of [...this.tweens]) {
      tw.t += real;
      const k = this.fastMode ? 1 : Math.min(1, tw.t / tw.dur);
      tw.step(k);
      if (k >= 1) {
        this.tweens.delete(tw);
        tw.done();
      }
    }
    for (const a of this.actors.values()) {
      a.rig.update(real, real);
      a.root.position.set(a.pos.x * T + T / 2, a.pos.y * T + T / 2);
      a.root.zIndex = a.root.position.y;
      if (a.tag) a.tag.position.set(a.root.x, a.root.y - T * 1.12);
    }
    for (const p of this.props) {
      p.view.tick(secs);
      // A tall prop fades while someone stands behind it.
      const cover = p.view.cover;
      if (cover) {
        let hides = false;
        for (const a of this.actors.values()) {
          if (!a.on) continue;
          const dy = p.at.y - a.pos.y;
          if (dy > 0.4 && dy < cover.h + 0.3 && Math.abs(a.pos.x - p.at.x) < cover.w + 0.3) {
            hides = true;
            break;
          }
        }
        const want = hides ? 0.35 : 1;
        const r = p.view.root;
        if (r.alpha !== want) {
          r.alpha += (want - r.alpha) * Math.min(1, real / 120);
          if (Math.abs(r.alpha - want) < 0.02) r.alpha = want;
        }
      }
      if (p.light) {
        const flick =
          0.85 + Math.sin(secs * 7 + p.at.x) * 0.08 + Math.sin(secs * 17 + p.at.y) * 0.05;
        p.light[0].alpha = Math.min(1, this.lightNow.glow * flick);
        p.light[1].alpha = Math.min(1, 0.25 + this.lightNow.glow * flick * 0.75);
      }
    }
    this.particles.update(real);
    this.ambient.update(real);
    this.flameAcc += real;
    if (this.flameAcc > 110) {
      this.flameAcc = 0;
      for (const p of this.props)
        if (p.view.flame) {
          const g = this.ground_(p.at);
          this.tongues(g.x + p.view.flame.dx * T, g.y + p.view.flame.dy * T, 0.55, 1);
        }
      const ctx = this.fxc();
      for (const b of this.burning) {
        const c = this.centre(b.cell);
        this.tongues(c.x, c.y + T * 0.32, 1.1, 2);
        if (Math.random() < 0.5) fx.flames(ctx, c);
      }
      for (const b of this.burning)
        b.glow.alpha = 0.55 + Math.sin(this.clock / 90 + b.cell.x) * 0.2;
      this.fireGlow.alpha = 0.8 + Math.sin(this.clock / 90) * 0.2;
    }
    if (this.waterMap) {
      this.waterMap.x = ((this.clock / 1000) * T * 0.35) % (T * 2.5);
      this.waterMap.y = Math.sin(this.clock / 1400) * T * 0.2;
    }
    this.drawMarker();
    this.shakeAmp *= 0.86 ** (real / 16.7);
    if (this.flashAlpha > 0.01) {
      const { width: W, height: H } = this.app.screen;
      this.flash.clear().rect(0, 0, W, H).fill({ color: 0xffffff, alpha: this.flashAlpha });
      this.flashAlpha *= 0.82 ** (real / 16.7);
    } else if (this.flashAlpha > 0) {
      this.flashAlpha = 0;
      this.flash.clear();
    }
    this.moveCamera(real);
  };

  /** Flame tongues licking up from a point (a brazier's bowl, a burning tile). */
  private tongues(x: number, y: number, size: number, n: number): void {
    if (this.fastMode) return;
    this.particles.emit({
      texture: flameTexture(),
      x,
      y,
      count: n,
      spread: T * 0.22 * size,
      angle: [-1.75, -1.4],
      speed: [T * 0.35 * size, T * 0.7 * size],
      life: [380, 650],
      scale: [0.9 * size, 1.3 * size],
      endScale: 0.25,
      rotation: 0,
      tint: [0xffd27a, 0xffa040, 0xff7a2f],
      alpha: 0.85,
      additive: true,
    });
  }

  private fxc(): fx.FxContext {
    return { p: this.particles, tex: this.tex, tile: T };
  }

  private centre(c: { x: number; y: number }): { x: number; y: number } {
    return { x: c.x * T + T / 2, y: c.y * T + T / 2 };
  }

  /** Runs `step` from 0 to 1 over `ms` (at once when skipping). */
  private tween(ms: number, step: (k: number) => void): Promise<void> {
    if (ms <= 0 || this.fastMode || !this.mounted) {
      step(1);
      return Promise.resolve();
    }
    return new Promise((resolve) => {
      this.tweens.add({ dur: ms, t: 0, step, done: resolve });
    });
  }

  wait(ms: number): Promise<void> {
    return this.tween(ms, () => {});
  }

  // ------------------------------------------------------------ actors

  private addActor(spec: ActorSpec): ActorView {
    const root = new Container();
    const shadow = new Sprite(this.tex.shadow);
    shadow.anchor.set(0.5);
    shadow.width = T * 0.8 * FIG;
    shadow.height = T * 0.3 * FIG;
    shadow.position.set(0, T * 0.31);
    root.addChild(shadow);
    let look = lookFor(spec.classId, spec.officer, spec.side);
    // Nobody rides into a hall or a tent, or about the camp.
    if (look.kind === "cavalry" && (this.setting !== "outdoor" || this.afoot))
      look = { ...look, kind: "infantry" };
    const rig = new ChibiRig(look);
    rig.setSize(T * FIG);
    rig.root.y = -T * 0.32 * (FIG - 1);
    root.addChild(rig.root);
    const at = spec.at ?? { x: 0, y: 0 };
    const a: ActorView = {
      spec,
      root,
      shadow,
      rig,
      look,
      afoot: true,
      cell: at,
      pos: { x: at.x, y: at.y },
      facing: spec.facing,
      held: null,
      on: spec.at !== null,
      moving: false,
      turned: false,
      tag: null,
    };
    root.visible = a.on;
    this.things.addChild(root);
    this.actors.set(spec.id, a);
    this.applyFacing(a);
    rig.setPose("stand");
    return a;
  }

  /** Swaps the figure when a rider gets down (to kneel or lie) or back up. */
  private setAfoot(a: ActorView, down: boolean): void {
    if (a.look.kind !== "cavalry") return;
    const want = down ? { ...a.look, kind: "infantry" as const } : a.look;
    if (a.rig.look.kind === want.kind) return;
    const idx = a.root.getChildIndex(a.rig.root);
    a.rig.root.destroy({ children: true });
    a.rig = new ChibiRig(want);
    a.rig.setSize(T * FIG);
    a.rig.root.y = -T * 0.32 * (FIG - 1);
    a.root.addChildAt(a.rig.root, idx);
    this.applyFacing(a);
  }

  private applyFacing(a: ActorView): void {
    const f = a.facing;
    if (f === "left" || f === "right") a.rig.setFacing("side", f === "left");
    else a.rig.setFacing(f, false);
  }

  private setPose(a: ActorView, pose: RigPose): void {
    a.rig.setPose(pose);
  }

  has(id: string): boolean {
    return this.actors.has(id);
  }

  /** Adds someone the cast did not list (the camp's officers). */
  addToStage(spec: ActorSpec): void {
    if (!this.mounted) this.castList.push(spec);
    else if (!this.actors.has(spec.id)) this.addActor(spec);
  }

  isOn(id: string): boolean {
    return this.actors.get(id)?.on ?? false;
  }

  cellOf(id: string): Cell | null {
    const a = this.actors.get(id);
    return a?.on ? a.cell : null;
  }

  facingOf(id: string): Facing4 | null {
    return this.actors.get(id)?.facing ?? null;
  }

  isMoving(id: string): boolean {
    return this.actors.get(id)?.moving ?? false;
  }

  /** The visible actor standing on a tile. */
  actorAt(c: Cell, except?: string): string | null {
    for (const a of this.actors.values())
      if (a.on && a.spec.id !== except && same(a.cell, c)) return a.spec.id;
    return null;
  }

  /** The figure under a point in tiles: its tile, or the body drawn above it. */
  private actorNear(px: number, py: number): ActorView | null {
    let best: ActorView | null = null;
    for (const a of this.actors.values()) {
      if (!a.on) continue;
      const dx = px - (a.pos.x + 0.5);
      const dy = py - (a.pos.y + 0.5);
      if (Math.abs(dx) < 0.4 && dy > -1.05 && dy < 0.45)
        if (!best || a.pos.y > best.pos.y) best = a;
    }
    return best;
  }

  /** Bare earth that raises dust underfoot (not floors, paving or water). */
  private dusty(c: Cell): boolean {
    const t = this.tiles[c.y * this.grid.width + c.x];
    return t === "plain" || t === "hills" || t === "camp" || t === "village" || t === "fort";
  }

  /** Can someone stand here (the ground and the props, not other actors)? */
  open(c: Cell): boolean {
    const def = this.def;
    if (!def) return false;
    if (c.x < 0 || c.y < 0 || c.x >= this.grid.width || c.y >= this.grid.height) return false;
    const t = this.tiles[c.y * this.grid.width + c.x];
    if (t === "water" || t === "wall" || t === "mountain") return false;
    return !this.props.some((p) => p.solid && same(p.at, c));
  }

  get size(): { width: number; height: number } {
    return { width: this.grid.width, height: this.grid.height };
  }

  place(id: string, at: Cell, facing?: Facing4): void {
    const a = this.actors.get(id);
    if (!a) return;
    a.cell = at;
    a.pos = { x: at.x, y: at.y };
    a.on = true;
    a.root.visible = true;
    a.root.alpha = 1;
    if (facing) {
      a.facing = facing;
      a.turned = true;
      this.applyFacing(a);
    }
  }

  async remove(id: string): Promise<void> {
    const a = this.actors.get(id);
    if (!a?.on) return;
    await this.tween(320, (k) => {
      a.root.alpha = 1 - k;
    });
    if (!this.live(a)) return;
    a.on = false;
    a.root.visible = false;
    a.root.alpha = 1;
    if (this.speakerId === id) this.speakerId = null;
  }

  face(id: string, to: Facing4 | string, byScript = true): void {
    const a = this.actors.get(id);
    if (!a) return;
    let f: Facing4;
    if (to === "up" || to === "down" || to === "left" || to === "right") f = to;
    else {
      const b = this.actors.get(to);
      if (!b?.on) return;
      f = facingTo(a.cell, b.cell, a.facing);
    }
    if (byScript) a.turned = true;
    if (f === a.facing) return;
    a.facing = f;
    this.applyFacing(a);
  }

  /** Clears the "turned by the script" mark (each time they speak). */
  untouched(id: string): boolean {
    const a = this.actors.get(id);
    if (!a) return false;
    const t = !a.turned;
    a.turned = false;
    return t;
  }

  /** Someone else stands here: walkers go around them when they can. */
  private crowd(a: ActorView): (c: Cell) => number {
    return (c) => (this.actorAt(c, a.spec.id) ? 1000 : 0);
  }

  private path(a: ActorView, to: Cell): Cell[] {
    const { width: w, height: h } = this.grid;
    const path = findPath(a.cell, to, w, h, (c) => this.open(c), false, this.crowd(a));
    // Others never block the way, but nobody walks onto someone else: stop before them.
    while (path.length > 1) {
      const last = path[path.length - 1] as Cell;
      if (!this.actorAt(last, a.spec.id)) break;
      path.pop();
    }
    return path;
  }

  /** Walks along the shortest way; `run` is faster. */
  async walk(id: string, to: Cell, run = false): Promise<void> {
    const a = this.actors.get(id);
    if (!a) return;
    if (!a.on) {
      this.place(id, to);
      return;
    }
    await this.walkAlong(a, this.path(a, to), run);
  }

  /** Appears at `from` (a gate, the map's edge), fading in, and walks to `to`. */
  async enter(id: string, from: Cell, to: Cell, run = false): Promise<void> {
    const a = this.actors.get(id);
    if (!a) return;
    a.cell = from;
    a.pos = { x: from.x, y: from.y };
    a.on = true;
    a.root.visible = true;
    a.root.alpha = 0;
    a.facing = facingTo(from, to, a.facing);
    this.applyFacing(a);
    this.look(id);
    const fade = this.tween(260, (k) => {
      a.root.alpha = k;
    });
    await Promise.all([fade, this.walkAlong(a, this.path(a, to), run)]);
    if (this.live(a)) a.root.alpha = 1;
  }

  /** Walks to `to` and is gone. */
  async leave(id: string, to: Cell, run = false): Promise<void> {
    const a = this.actors.get(id);
    if (!a?.on) return;
    const { width: w, height: h } = this.grid;
    const path = findPath(a.cell, to, w, h, (c) => this.open(c), false, this.crowd(a));
    await this.walkAlong(a, path, run);
    if (this.live(a)) await this.remove(id);
  }

  /** One step in a direction, if the tile is open and free (the camp's walking). */
  async stepTo(id: string, dir: Facing4): Promise<boolean> {
    const a = this.actors.get(id);
    if (!a?.on || a.moving) return false;
    const d = DIRS[dir];
    const n = { x: a.cell.x + d.x, y: a.cell.y + d.y };
    a.facing = dir;
    this.applyFacing(a);
    if (!this.open(n) || this.actorAt(n, id)) return false;
    await this.walkAlong(a, [a.cell, n], false, true);
    return true;
  }

  /** Walks a path the caller worked out (the camp's taps); `stop()` cuts it short. */
  async walkPath(id: string, path: Cell[], shouldStop?: () => boolean): Promise<void> {
    const a = this.actors.get(id);
    if (!a?.on) return;
    await this.walkAlong(a, path, false, true, shouldStop);
  }

  /** Back to standing after the camp's steps. */
  idle(id: string): void {
    const a = this.actors.get(id);
    if (a && !a.moving && !a.held) this.setPose(a, "stand");
  }

  /** A path for an actor as `walk` would take it; `around` keeps clear of everyone else. */
  pathFor(id: string, to: Cell, around = false): Cell[] {
    const a = this.actors.get(id);
    if (!a) return [];
    if (!around) return this.path(a, to);
    const free = (c: Cell) => this.open(c) && !this.actorAt(c, id);
    return findPath(a.cell, to, this.grid.width, this.grid.height, free, true);
  }

  private async walkAlong(
    a: ActorView,
    path: Cell[],
    run: boolean,
    keepPose = false,
    shouldStop?: () => boolean,
  ): Promise<void> {
    if (path.length < 2) return;
    a.moving = true;
    a.held = null;
    this.setAfoot(a, false);
    this.look(a.spec.id);
    const riding = a.rig.look.kind === "cavalry";
    const per = (run ? 175 : 300) * (riding ? 0.85 : 1);
    this.setPose(a, "walk");
    try {
      for (let i = 1; i < path.length; i++) {
        if (shouldStop?.()) break;
        const from = path[i - 1] as Cell;
        const to = path[i] as Cell;
        const f = facingTo(from, to, a.facing);
        if (f !== a.facing) {
          a.facing = f;
          this.applyFacing(a);
        }
        a.cell = to;
        if (i % 2 === 1 || riding) this.sound(riding ? "hoof" : "move", to, riding ? 0.4 : 0.32);
        if ((run || riding) && !this.fastMode && this.dusty(from)) {
          const g = this.ground_(from);
          fx.dust(this.fxc(), { x: g.x, y: g.y - T * 0.02 }, run ? 2 : 1);
        }
        await this.tween(per, (k) => {
          a.pos = { x: from.x + (to.x - from.x) * k, y: from.y + (to.y - from.y) * k };
        });
        if (!this.live(a)) return;
      }
    } finally {
      a.moving = false;
      if (!keepPose && this.live(a)) this.setPose(a, "stand");
    }
  }

  /** A pose: held ones (kneel, down, cheer) stay until the next pose or walk. */
  async act(id: string, pose: ActPose): Promise<void> {
    const a = this.actors.get(id);
    if (!a?.on) return;
    const down = pose === "kneel" || pose === "down";
    this.setAfoot(a, down);
    if (HELD.has(pose)) {
      a.held = pose;
      this.setPose(a, pose);
      if (pose === "down") {
        const g = this.ground_(a.cell);
        if (!this.fastMode && this.dusty(a.cell))
          fx.dust(this.fxc(), { x: g.x, y: g.y - T * 0.05 }, 3);
        void this.tween(450, (k) => {
          a.shadow.width = T * FIG * (0.8 + 0.35 * k);
        });
      }
      await this.wait(pose === "cheer" ? 300 : 450);
      return;
    }
    a.held = null;
    a.shadow.width = T * 0.8 * FIG;
    if (pose === "stand") {
      this.setPose(a, "stand");
      return;
    }
    this.setPose(a, pose);
    await this.wait((ONE_SHOT[pose] ?? 0.5) * 1000);
    if (this.live(a) && a.held === null) this.setPose(a, "stand");
  }

  /** `who` steps in and strikes `target`, who reels (and falls, with `fells`). */
  async strike(id: string, target: string, fells = false): Promise<void> {
    const a = this.actors.get(id);
    const b = this.actors.get(target);
    if (!a?.on || !b?.on) return;
    // Out of reach: run up beside the target first.
    if (Math.abs(a.cell.x - b.cell.x) + Math.abs(a.cell.y - b.cell.y) > 1)
      await this.walkAlong(a, this.path(a, b.cell), true);
    const both = () => this.live(a) && this.live(b);
    if (!both()) return;
    a.held = null;
    this.setAfoot(a, false);
    a.facing = facingTo(a.cell, b.cell, a.facing);
    this.applyFacing(a);
    b.facing = facingTo(b.cell, a.cell, b.facing);
    this.applyFacing(b);
    this.look(id);
    const home = { ...a.pos };
    const dx = (b.cell.x - a.cell.x) * 0.3;
    const dy = (b.cell.y - a.cell.y) * 0.3;
    this.setPose(a, "attack");
    await this.tween(220, (k) => {
      a.pos = { x: home.x + dx * k, y: home.y + dy * k };
    });
    if (!both()) return;
    // The blow lands.
    const p = this.centre(b.pos);
    this.sound(fells ? "crit" : "hit", b.cell);
    if (!this.fastMode) {
      fx.strikeHit(this.fxc(), p, Math.atan2(dy, dx), fells);
      this.shake(fells ? 9 : 4);
      if (fells) this.flashScreen(0.3);
    }
    b.held = null;
    this.setPose(b, "hurt");
    const bx = b.pos.x;
    void this.tween(260, (k) => {
      b.pos = { x: bx + Math.sin(k * Math.PI * 5) * 0.06 * (1 - k), y: b.pos.y };
    });
    await this.wait(260);
    await this.tween(200, (k) => {
      a.pos = { x: home.x + dx * (1 - k), y: home.y + dy * (1 - k) };
    });
    if (!both()) return;
    a.pos = home;
    this.setPose(a, "stand");
    if (fells) {
      this.sound("defeat", b.cell);
      await this.act(target, "down");
    } else {
      await this.wait(160);
      if (this.live(b) && b.held === null) this.setPose(b, "stand");
    }
  }

  /** Still on this stage (not torn down by a new scene or a reload while it acted). */
  private live(a: ActorView): boolean {
    return !this.destroyed && this.actors.get(a.spec.id) === a;
  }

  // ------------------------------------------------------------ marks over heads

  /**
   * Marks who is speaking (a bobbing chevron and a little hop), or nobody; the
   * camera keeps them, and the one they speak to, in view.
   */
  speaking(id: string | null, listener?: string | null): void {
    this.speakerId = id;
    if (!id) return;
    const a = this.actors.get(id);
    if (!a?.on) return;
    if (!this.camGoal.sticky) this.attention = listener ? [id, listener] : id;
    if (a.moving || a.held || this.fastMode) return;
    const y0 = a.pos.y;
    void this.tween(260, (k) => {
      if (!a.moving) a.pos = { x: a.pos.x, y: y0 - Math.sin(k * Math.PI) * 0.08 };
    }).then(() => {
      if (!a.moving) a.pos = { x: a.pos.x, y: y0 };
    });
  }

  private drawMarker(): void {
    const g = this.marker;
    g.clear();
    const a = this.speakerId ? this.actors.get(this.speakerId) : null;
    if (!a?.on) return;
    const x = a.pos.x * T + T / 2;
    const lying = a.held === "down";
    const y =
      a.pos.y * T + T / 2 - T * (lying ? 0.35 : 1.1) + Math.sin(this.clock / 220) * T * 0.04;
    const w = T * 0.11;
    g.moveTo(x - w, y - w * 0.9)
      .lineTo(x, y + w * 0.25)
      .lineTo(x + w, y - w * 0.9)
      .lineTo(x, y - w * 0.4)
      .closePath()
      .fill(0xffd75e)
      .stroke({ width: 2, color: 0x5a3c00, alpha: 0.85 });
  }

  /** An emote pops over the head, holds about a second and fades. */
  emote(id: string, e: Emote): Promise<void> {
    const a = this.actors.get(id);
    if (!a?.on || this.fastMode) return Promise.resolve();
    const art = emoteArt(e);
    const s = new Sprite(art.tex);
    s.anchor.set(art.bubble ? 0.2 : 0.5, art.bubble ? 0.95 : 0.5);
    const size = T * (art.bubble ? 0.5 : 0.32);
    const lying = a.held === "down";
    const place = () => {
      const x = a.pos.x * T + T / 2;
      const y = a.pos.y * T + T / 2;
      if (art.bubble) s.position.set(x + T * 0.1, y - T * (lying ? 0.3 : 1.0));
      else if (e === "tear") s.position.set(x + T * 0.14, y - T * (lying ? 0.1 : 0.55));
      else s.position.set(x + T * 0.28, y - T * (lying ? 0.25 : 0.95));
    };
    place();
    this.above.addChild(s);
    if (e === "!" || e === "anger") this.sound("select", a.cell, 0.5);
    const total = 1350;
    let t = 0;
    const tick = (ms: number): boolean => {
      if (s.destroyed || !this.live(a)) {
        if (!s.destroyed) s.destroy();
        return false;
      }
      t += ms;
      place();
      const pop = t < 160 ? 0.3 + (t / 160) * 0.95 : t < 260 ? 1.25 - ((t - 160) / 100) * 0.25 : 1;
      const k = size / 96;
      s.scale.set(k * pop);
      if (e === "sweat") s.y += Math.min(1, t / total) * T * 0.12;
      if (e === "tear") s.y += ((t % 900) / 900) * T * 0.15;
      if (e === "anger") s.scale.set(k * pop * (1 + Math.sin(t / 70) * 0.08));
      s.alpha = t > total - 280 ? Math.max(0, (total - t) / 280) : 1;
      if (t >= total || this.fastMode) {
        s.destroy();
        return false;
      }
      return true;
    };
    this.jobs.add(tick);
    return this.wait(650);
  }

  /** A small name plate over an actor (the camp shows who can be talked to). */
  tag(id: string, text: string | null): void {
    const a = this.actors.get(id);
    if (!a) return;
    if (a.tag) {
      a.tag.destroy({ children: true });
      a.tag = null;
    }
    if (!text || !this.mounted) return;
    const box = new Container();
    const label = new Text({
      text,
      style: {
        fontFamily: FONT,
        fontSize: 22,
        fontWeight: "700",
        fill: 0xfff3d0,
        stroke: { color: 0x1a120a, width: 4, join: "round" },
      },
    });
    label.anchor.set(0.5, 1);
    const w = label.width + 18;
    const h = label.height + 6;
    const bg = new Graphics()
      .roundRect(-w / 2, -h - 2, w, h, h / 2)
      .fill({ color: 0x1a140c, alpha: 0.72 })
      .stroke({ width: 1.5, color: 0xe8c26a, alpha: 0.7 });
    box.addChild(bg, label);
    label.position.set(0, -4);
    this.above.addChild(box);
    a.tag = box;
    box.position.set(a.root.x, a.root.y - T * 1.12);
  }

  /** Stations in the camp: a soft glow on the tile and a wooden sign with its name. */
  station(at: Cell, name: string): void {
    if (!this.mounted) return;
    const c = this.centre(at);
    const glow = new Sprite(this.tex.glow);
    glow.anchor.set(0.5);
    glow.tint = 0xffd27a;
    glow.width = T * 1.5;
    glow.height = T * 0.9;
    glow.position.set(c.x, c.y + T * 0.1);
    glow.blendMode = "add";
    this.marks.addChild(glow);
    const ring = new Graphics()
      .ellipse(c.x, c.y + T * 0.2, T * 0.36, T * 0.15)
      .stroke({ width: 3, color: 0xffe08a, alpha: 0.75 });
    this.marks.addChild(ring);
    const phase = at.x + at.y;
    this.jobs.add(() => {
      if (glow.destroyed || ring.destroyed) return false;
      const k = 0.5 + 0.5 * Math.sin(this.clock / 420 + phase);
      glow.alpha = 0.35 + 0.35 * k;
      ring.alpha = 0.45 + 0.4 * k;
      return true;
    });
    // The sign: a lacquered board on a post by the tile, with the station's name.
    const sign = new Container();
    const text = new Text({
      text: name,
      style: {
        fontFamily: FONT,
        fontSize: 26,
        fontWeight: "900",
        fill: 0xfff0c8,
        letterSpacing: 2,
        stroke: { color: 0x2a1408, width: 3, join: "round" },
      },
    });
    text.anchor.set(0.5);
    const w = text.width + 22;
    const h = text.height + 8;
    const top = -T * 0.78;
    const board = new Graphics()
      .ellipse(0, 0, T * 0.12, T * 0.04)
      .fill({ color: 0x000000, alpha: 0.3 })
      .rect(-3, top, 6, -top)
      .fill(0x54371f)
      .roundRect(-w / 2, top - h / 2, w, h, 6)
      .fill(0x6b2a1c)
      .stroke({ width: 3, color: 0xd8a84e });
    text.position.set(0, top);
    sign.addChild(board, text);
    const g = this.ground_(at);
    sign.position.set(g.x + T * 0.5, g.y - T * 0.05);
    sign.zIndex = sign.y;
    this.things.addChild(sign);
  }

  /** A gold ring at someone's feet: who the player walks with. */
  mark(id: string): void {
    const a = this.actors.get(id);
    if (!a) return;
    const ring = new Graphics()
      .ellipse(0, T * 0.31, T * 0.36, T * 0.14)
      .fill({ color: 0xffd75e, alpha: 0.18 })
      .stroke({ width: 3, color: 0xffd75e, alpha: 0.85 });
    a.root.addChildAt(ring, 1);
  }

  /** A ring on the tile 조조 is walking to. */
  target(at: Cell | null): void {
    const old = this.marks.getChildByLabel("target");
    old?.destroy();
    if (!at || !this.mounted) return;
    const c = this.centre(at);
    const g = new Graphics()
      .ellipse(c.x, c.y + T * 0.28, T * 0.3, T * 0.12)
      .stroke({ width: 3, color: 0xffffff, alpha: 0.7 });
    g.label = "target";
    this.marks.addChild(g);
  }

  // ------------------------------------------------------------ staging

  /** A sound effect, panned to where it happens. */
  sound(name: string, where?: Cell, gain = 1): void {
    if (this.fastMode) return;
    const sfx =
      SFX_ALIAS[name] ?? ((SFX_NAMES as readonly string[]).includes(name) ? (name as Sfx) : null);
    if (!sfx) return;
    const w = this.grid.width;
    audio.play(sfx, {
      gain,
      ...(where ? { pan: Math.max(-1, Math.min(1, ((where.x + 0.5) / w) * 2 - 1)) * 0.6 } : {}),
    });
  }

  private shake(px: number): void {
    if (!this.opts.calm && !this.fastMode) this.shakeAmp = Math.max(this.shakeAmp, px);
  }

  private flashScreen(alpha: number): void {
    if (!this.fastMode) this.flashAlpha = this.opts.calm ? alpha * 0.4 : alpha;
  }

  private pointOf(at: Cell | string | undefined): { x: number; y: number } | null {
    if (at === undefined) return null;
    if (typeof at === "string") {
      const a = this.actors.get(at);
      return a?.on ? this.centre(a.pos) : null;
    }
    return this.centre(at);
  }

  /** A burst on a tile or an actor, or across the whole view. */
  async effect(kind: StageFx, at?: Cell | string): Promise<void> {
    if (this.fastMode || !this.mounted) return;
    if (kind === "flash") {
      this.flashScreen(0.7);
      this.sound("cutin");
      return this.wait(180);
    }
    if (kind === "shake") {
      this.shake(12);
      this.sound("earth", undefined, 0.6);
      return this.wait(350);
    }
    const p = this.pointOf(at);
    const spots = p
      ? [p]
      : [0.2, 0.5, 0.8].flatMap((sx) =>
          [0.3, 0.7].map((sy) => ({ x: this.grid.width * T * sx, y: this.grid.height * T * sy })),
        );
    const ctx = this.fxc();
    for (const q of spots)
      switch (kind) {
        case "smoke":
          ctx.p.emit({
            texture: this.tex.smoke,
            x: q.x,
            y: q.y,
            count: 9,
            spread: T * 0.35,
            angle: [-2.2, -0.9],
            speed: [T * 0.2, T * 0.6],
            drag: 0.3,
            life: [1400, 2400],
            scale: 0.6,
            endScale: 2.6,
            alpha: 0.55,
            tint: [0x4a423c, 0x6a605a, 0x2e2826],
          });
          break;
        case "sparks":
          fx.strikeHit(ctx, q, -Math.PI / 2, true);
          break;
        case "splash":
          fx.schoolBurst(ctx, "water", [q]);
          break;
        case "dust":
          ctx.p.emit({
            texture: this.tex.smoke,
            x: q.x,
            y: q.y + T * 0.25,
            count: 12,
            spread: T * 0.4,
            angle: [-Math.PI, 0],
            speed: [T * 0.3, T * 0.9],
            drag: 0.12,
            life: [600, 1100],
            scale: 0.5,
            endScale: 2.4,
            alpha: 0.45,
            tint: 0xd8c9a4,
          });
          break;
      }
    if (kind === "sparks") this.sound("hit", undefined, 0.6);
    if (kind === "splash") this.sound("water", undefined, 0.7);
    return this.wait(260);
  }

  /** Tiles alight for the rest of the scene: flames, smoke and a glow. */
  fire(cells: readonly Cell[]): void {
    if (!this.mounted) return;
    for (const c of cells) {
      if (this.burning.some((b) => same(b.cell, c))) continue;
      // Scorched ground with embers glowing in it.
      const p0 = this.centre(c);
      this.fireGlow
        .ellipse(p0.x, p0.y + T * 0.08, T * 0.52, T * 0.4)
        .fill({ color: 0x2a1408, alpha: 0.3 })
        .ellipse(p0.x, p0.y + T * 0.12, T * 0.34, T * 0.24)
        .fill({ color: 0xff6a1f, alpha: 0.35 });
      const glow = new Sprite(this.tex.glow);
      glow.anchor.set(0.5);
      glow.tint = 0xff8a3c;
      glow.width = T * 3;
      glow.height = T * 2.4;
      const p = this.centre(c);
      glow.position.set(p.x, p.y);
      this.glow.addChild(glow);
      this.burning.push({ cell: { x: c.x, y: c.y }, glow });
    }
    this.sound("fire", cells[0]);
  }

  /** Changes tiles (a flood, a gate thrown open): the new ground fades in. */
  async retile(changes: readonly TileChange[]): Promise<void> {
    for (const c of changes)
      if (c.x >= 0 && c.y >= 0 && c.x < this.grid.width && c.y < this.grid.height)
        this.tiles[c.y * this.grid.width + c.x] = c.terrain as TerrainId;
    if (!this.mounted) return;
    // The old ground stays on top and fades away over the new.
    const snapshot = new Container();
    for (const layer of [this.ground, this.water, this.edges]) {
      layer.cacheAsTexture(false);
      const copy = new Container();
      for (const ch of [...layer.children]) copy.addChild(ch);
      snapshot.addChild(copy);
    }
    this.paintGround();
    if (this.fastMode) {
      snapshot.destroy({ children: true });
      return;
    }
    this.terrain.addChild(snapshot);
    await this.tween(700, (k) => {
      snapshot.alpha = 1 - k;
    });
    snapshot.destroy({ children: true });
  }

  /** Day, dusk or night, eased in. */
  async light(l: StageLight): Promise<void> {
    const from = { ...this.lightNow };
    const to = LIGHT[l];
    await this.tween(1200, (k) => {
      const e = k * k * (3 - 2 * k);
      for (const key of Object.keys(to) as (keyof typeof to)[])
        this.lightNow[key] = from[key] + (to[key] - from[key]) * e;
      this.applyLight();
    });
  }

  private applyLight(): void {
    const w = WEATHER[this.setting === "outdoor" ? this.weather : "sunny"];
    const l = this.lightNow;
    this.grade.saturation = w.saturation * l.saturation;
    this.grade.contrast = w.contrast;
    this.grade.brightness = w.brightness * l.brightness;
    this.grade.red = w.red * l.red;
    this.grade.green = w.green * l.green;
    this.grade.blue = w.blue * l.blue;
    this.vignette.alpha = 0.4 + 0.38 * l.glow;
    // Sun shafts and motes belong to the day.
    if (this.ambient) this.ambient.world.alpha = this.weather === "sunny" ? 1 - l.glow * 0.9 : 1;
    this.fireGlow.visible = true;
  }
}
