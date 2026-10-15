/**
 * PixiJS battlefield (ADR 0007): terrain, overlays (reach, targets, danger),
 * units and effects. The presentation (ADR 0044) adds soft terrain edges, water
 * shimmer, weather, lighting and colour grading, a cinematic camera, particles
 * and skill cut-ins. Units are our own medallion tokens, or the local classic
 * skin's animated sprites when the owner has exported one (ADR 0042).
 * It only reads the session and animates rules events; it decides nothing.
 */

import {
  type BattleEvent,
  type BattleState,
  type Cell,
  hasBlock,
  type TerrainId,
  type Unit,
} from "@tk/rules";
import {
  Application,
  Container,
  DisplacementFilter,
  type Filter,
  Graphics,
  Sprite,
  Text,
  type TextStyleOptions,
  type Texture,
} from "pixi.js";
import { AdjustmentFilter, AdvancedBloomFilter } from "pixi-filters";
import { Ambient } from "./fx/ambient.ts";
import { ChibiRig, lookFor } from "./fx/chibi.ts";
import * as fx from "./fx/effects.ts";
import { Particles } from "./fx/particles.ts";
import { ourTerrain, paintTerrain, skinTerrain, type TerrainSet } from "./fx/terrain.ts";
import { type FxTextures, makeTextures } from "./fx/textures.ts";
import { tokenTexture } from "./fx/tokens.ts";
import { walkPath } from "./path.ts";
import type { Session } from "./session.svelte.ts";
import type { ClassicSkin, Facing, Pose } from "./skin.svelte.ts";
import { FATE, SCHOOL_COLOR } from "./text.ts";

/** Flat terrain colours (the deploy mini-map and tile chips). */
export const TERRAIN_COLOR: Record<TerrainId, number> = {
  plain: 0x9aa565,
  grass: 0x7fae4e,
  forest: 0x3f6b35,
  hills: 0xa48d5c,
  mountain: 0x6d6259,
  ford: 0x7fb2c2,
  water: 0x2f6f9f,
  bridge: 0x9b7653,
  village: 0xc9b28a,
  fort: 0x8f8a80,
  wall: 0x4a4744,
  camp: 0xb9a46c,
};

const SIDE_COLOR = [0x3b7be8, 0xd64a3c, 0x3ca05a, 0xb08a2e];
const BG = 0x14130f;
const FONT = "'Noto Sans KR', 'Apple SD Gothic Neo', 'Malgun Gothic', system-ui, sans-serif";

/** Colour grade per weather: warm and lively in sun, cool and dim in rain. */
const GRADE = {
  sunny: { saturation: 1.12, contrast: 1.06, brightness: 1.03, red: 1.04, green: 1, blue: 0.95 },
  cloudy: { saturation: 0.92, contrast: 1.02, brightness: 0.95, red: 0.98, green: 1, blue: 1.03 },
  rain: { saturation: 0.82, contrast: 1.04, brightness: 0.86, red: 0.93, green: 0.98, blue: 1.08 },
  wind: { saturation: 1.04, contrast: 1.05, brightness: 1.0, red: 1.01, green: 1.02, blue: 0.98 },
} as const;

/** What the overlay layer shows; computed by the session. */
export interface OverlayView {
  reach: Cell[];
  attackable: Cell[];
  pending: Cell | null;
  aimCells: Cell[];
  aimColor: number;
  target: Cell | null;
  area: Cell[];
  danger: Cell[];
  hover: Cell | null;
  ready: Cell[];
  ghost: { id: string; to: Cell } | null;
  /** Where the selected unit stands (its pending tile, if any). */
  selected: Cell | null;
}

interface UnitSprite {
  root: Container;
  shadow: Sprite;
  /** Team ring at the feet of a skinned figure. */
  ring: Graphics;
  /** Our medallion token (with `?tokens`). */
  token: Sprite;
  /** Our rigged chibi figure (ADR 0045; hidden with the classic skin or `?tokens`). */
  rig: ChibiRig | null;
  /** The animated classic sprite (hidden without a skin). */
  figure: Sprite;
  hp: Graphics;
  mark: Text;
  /** ▲ buffs / ▼ debuffs at the top-left. */
  cond: Text;
  glyph: Text;
  /** Classic sprite set, or -1. */
  set: number;
  pose: PoseState | null;
  hpNow: number;
  /** The white "drain" left behind by damage, shrinking after a moment. */
  hpGhost: number;
  maxHp: number;
  ghostWait: number;
}

interface PoseState {
  key: string;
  frames: Texture[];
  fps: number;
  loop: boolean;
  elapsed: number;
}

/** Which way a figure looks: a facing, and whether side frames are mirrored. */
interface Dir {
  facing: Facing;
  flip: boolean;
}

const POSE_TIMING: Record<Pose, { fps: number; loop: boolean }> = {
  walk: { fps: 4, loop: true },
  stand: { fps: 1, loop: false },
  weak: { fps: 2, loop: true },
  attack: { fps: 12, loop: false },
  guard: { fps: 1, loop: false },
  hurt: { fps: 1, loop: false },
  cheer: { fps: 1, loop: false },
};

export interface FieldOptions {
  /** Medallion tokens instead of our rigged figures. */
  tokens?: boolean;
  /** Fewer effects and no full-screen filters (slow devices, `?lowfx`). */
  lite: boolean;
  /** No camera moves or screen shake (prefers-reduced-motion). */
  calm: boolean;
}

export class Field {
  private app = new Application();
  private world = new Container();
  private ground = new Container();
  private water = new Container();
  private edges = new Container();
  private grid = new Graphics();
  private fireGlow = new Graphics();
  private overlay = new Graphics();
  private pulse = new Graphics();
  private units = new Container();
  private marker = new Graphics();
  private labels = new Container();
  private flash = new Graphics();
  /** Clips the soft terrain edges and the weather to the map. */
  private edgeClip = new Graphics();
  private skyClip = new Graphics();
  private vignette = new Sprite();
  private particles = new Particles();
  private tex!: FxTextures;
  private ambient!: Ambient;
  private grade = new AdjustmentFilter();
  private waterFilter: DisplacementFilter | null = null;
  private waterMap: Sprite | null = null;
  private sprites = new Map<string, UnitSprite>();
  private tile = 48;
  private ox = 0;
  private oy = 0;
  private playing = false;
  /** A relayout asked for mid-animation waits until the animation ends. */
  private relayoutLater = false;
  private mapKey = "";
  private shown: BattleState | null = null;
  private view: OverlayView | null = null;
  private mounted = false;
  private skin: ClassicSkin | null = null;
  private skinSet: TerrainSet | null = null;
  /** The 1998 map picture under the current battle, once loaded. */
  private backdrop: { id: number; texture: Texture } | null = null;
  private loadingSets = new Set<number>();
  private clock = 0;
  private flameAcc = 0;
  private burning: Cell[] = [];
  private cam = { z: 1, pull: 0, fx: 0, fy: 0 };
  private shakeAmp = 0;
  private flashAlpha = 0;

  constructor(
    private readonly session: Session,
    private readonly opts: FieldOptions = { lite: false, calm: false },
  ) {}

  async mount(host: HTMLElement): Promise<void> {
    await this.app.init({
      resizeTo: host,
      background: BG,
      antialias: true,
      autoDensity: true,
      resolution: Math.min(window.devicePixelRatio || 1, 2),
    });
    host.appendChild(this.app.canvas);
    this.tex = makeTextures();
    this.ambient = new Ambient(this.tex, this.opts.lite);
    this.vignette.texture = this.tex.vignette;
    this.vignette.alpha = 0.55;
    this.vignette.eventMode = "none";
    this.units.sortableChildren = true;
    this.world.addChild(
      this.ground,
      this.water,
      this.edges,
      this.grid,
      this.fireGlow,
      this.overlay,
      this.pulse,
      this.units,
      this.particles.layer,
      this.ambient.world,
      this.marker,
      this.labels,
      this.edgeClip,
      this.skyClip,
    );
    this.edges.mask = this.edgeClip;
    this.ambient.world.mask = this.skyClip;
    this.world.filters = [this.grade];
    if (!this.opts.lite) {
      this.particles.layer.filters = [
        new AdvancedBloomFilter({ threshold: 0.35, bloomScale: 1.3, brightness: 1, blur: 6 }),
      ];
      const noise = this.tex.noise;
      noise.source.style.addressMode = "repeat";
      noise.source.style.update();
      this.waterMap = new Sprite(noise);
      this.waterMap.renderable = false;
      this.world.addChild(this.waterMap);
      this.waterFilter = new DisplacementFilter({ sprite: this.waterMap, scale: 6 });
    }
    this.app.stage.addChild(this.world, this.ambient.screen, this.vignette, this.flash);
    this.app.stage.eventMode = "static";
    this.app.stage.hitArea = this.app.screen;
    this.app.stage.on("pointermove", (e) => {
      const c = this.cellAt(e.global.x, e.global.y);
      const h = this.session.hover;
      if ((c === null) !== (h === null) || (c && h && (c.x !== h.x || c.y !== h.y)))
        this.session.hover = c;
    });
    this.app.stage.on("pointerleave", () => (this.session.hover = null));
    this.app.stage.on("pointertap", (e) => {
      const c = this.cellAt(e.global.x, e.global.y);
      if (c) this.session.click(c);
    });
    this.app.renderer.on("resize", () => this.relayout());
    this.app.ticker.add(this.animate);
    this.mounted = true;
    this.session.player = (events, before, after) => this.play(events, before, after);
    this.relayout();
  }

  /**
   * Debug captures: stop the clock and advance it by hand, one frame at a time,
   * so a slow (software-rendered) browser still records smooth video.
   */
  manualClock(on: boolean): void {
    if (on) this.app.ticker.stop();
    else this.app.ticker.start();
  }

  step(ms: number): void {
    this.app.ticker.update(this.app.ticker.lastTime + ms);
  }

  /** Switches between our art and the classic skin. */
  setSkin(skin: ClassicSkin | null): void {
    if (skin === this.skin) return;
    this.skin = skin;
    this.skinSet = skin ? skinTerrain(skin.terrain, skin.manifest.tile) : null;
    this.mapKey = "";
    if (this.mounted && this.view) this.render(this.shown ?? this.session.state, this.view);
  }

  destroy(): void {
    this.session.player = null;
    this.mounted = false;
    this.particles.clear();
    this.app.destroy(true, { children: true });
  }

  /** Draws the state and overlay (units are left alone while an animation plays). */
  render(state: BattleState, view: OverlayView): void {
    this.view = view;
    if (!this.mounted) return;
    const i = this.session.insets;
    const wanted = this.skin ? this.session.backdrop : null;
    if (wanted !== null && this.backdrop?.id !== wanted) this.loadBackdrop(wanted);
    const back = wanted !== null && this.backdrop?.id === wanted ? wanted : "-";
    const mk = `${state.map.width}x${state.map.height}:${state.map.tiles.join("")}|${i.top},${i.right},${i.bottom},${i.left}|${this.skin ? 1 : 0}${back}`;
    if (mk !== this.mapKey) {
      this.mapKey = mk;
      this.shown = state;
      this.relayout();
      return;
    }
    if (!this.playing) {
      this.shown = state;
      this.syncUnits(state, view.ghost);
      this.drawFire(state);
      this.setWeather(state.weather);
    }
    this.drawOverlay(view);
  }

  private setWeather(kind: BattleState["weather"]): void {
    this.ambient.setWeather(kind);
    Object.assign(this.grade, GRADE[kind]);
  }

  private loadBackdrop(id: number): void {
    const skin = this.skin;
    if (!skin) return;
    skin
      .mapTexture(id)
      .then((texture) => {
        if (this.skin !== skin || this.session.backdrop !== id) return;
        this.backdrop = { id, texture };
        this.mapKey = "";
        if (this.mounted && this.view) this.render(this.session.state, this.view);
      })
      .catch(() => {});
  }

  // ------------------------------------------------------------ layout

  private relayout(): void {
    if (!this.mounted) return;
    if (this.playing) {
      this.relayoutLater = true;
      return;
    }
    this.relayoutLater = false;
    const state = this.shown ?? this.session.state;
    this.shown = state;
    const { width: W, height: H } = this.app.screen;
    const i = this.session.insets;
    const availW = Math.max(100, W - i.left - i.right - 16);
    const availH = Math.max(100, H - i.top - i.bottom - 16);
    let tile = Math.floor(Math.min(availW / state.map.width, availH / state.map.height));
    if (this.skin) {
      // The terrain art is 48 px: whole and half steps keep it crisp; below that it is smoothed.
      const base = this.skin.manifest.tile;
      tile = tile >= base * 1.5 ? base * 1.5 : tile >= base ? base : tile - (tile % 2);
      this.skin.setFilter(tile >= base ? "nearest" : "linear");
    }
    this.tile = Math.max(20, Math.min(72, tile));
    this.ox = Math.floor(i.left + 8 + (availW - this.tile * state.map.width) / 2);
    this.oy = Math.floor(i.top + 8 + (availH - this.tile * state.map.height) / 2);
    this.cam = { z: 1, pull: 0, fx: 0, fy: 0 };
    this.applyCamera();
    // For tests and tools: where the map sits on the canvas, in CSS pixels.
    this.app.canvas.dataset.geometry = JSON.stringify({
      tile: this.tile,
      ox: this.ox,
      oy: this.oy,
    });
    this.session.geometry = { tile: this.tile, ox: this.ox, oy: this.oy };
    this.vignette.width = W;
    this.vignette.height = H;
    this.drawTerrain(state);
    this.ambient.layout(this.tile * state.map.width, this.tile * state.map.height, this.tile, W, H);
    this.setWeather(state.weather);
    for (const s of this.sprites.values()) s.root.destroy({ children: true });
    this.sprites.clear();
    this.syncUnits(state, this.view?.ghost ?? null);
    this.drawFire(state);
    if (this.view) this.drawOverlay(this.view);
  }

  private cellAt(gx: number, gy: number): Cell | null {
    const s = this.shown;
    if (!s) return null;
    const p = this.world.toLocal({ x: gx, y: gy });
    const x = Math.floor(p.x / this.tile);
    const y = Math.floor(p.y / this.tile);
    return x >= 0 && y >= 0 && x < s.map.width && y < s.map.height ? { x, y } : null;
  }

  private center(c: Cell): { x: number; y: number } {
    return { x: c.x * this.tile + this.tile / 2, y: c.y * this.tile + this.tile / 2 };
  }

  // ------------------------------------------------------------ camera

  /** Places the world: zoom `z` about a focus point drawn toward the screen centre by `pull`. */
  private applyCamera(): void {
    const { width: W, height: H } = this.app.screen;
    const i = this.session.insets;
    const cx = i.left + (W - i.left - i.right) / 2;
    const cy = i.top + (H - i.top - i.bottom) / 2;
    const { z, pull, fx: fX, fy: fY } = this.cam;
    const sx = this.ox + fX;
    const sy = this.oy + fY;
    let x = sx + (cx - sx) * pull - fX * z;
    let y = sy + (cy - sy) * pull - fY * z;
    if (this.shakeAmp > 0.25) {
      x += (Math.random() * 2 - 1) * this.shakeAmp;
      y += (Math.random() * 2 - 1) * this.shakeAmp;
    }
    this.world.position.set(x, y);
    this.world.scale.set(z);
  }

  private cinematic(): boolean {
    return !this.opts.calm && (this.session.speed === 1 || this.session.speed === 2);
  }

  private focus(at: { x: number; y: number }, z: number, pull: number, ms: number): Promise<void> {
    const from = { ...this.cam };
    const ease = (k: number) => 1 - (1 - k) ** 3;
    return this.tween(this.ms(ms), (k) => {
      const e = ease(k);
      this.cam = {
        z: from.z + (z - from.z) * e,
        pull: from.pull + (pull - from.pull) * e,
        fx: from.fx + (at.x - from.fx) * e,
        fy: from.fy + (at.y - from.fy) * e,
      };
    });
  }

  private shake(px: number): void {
    if (!this.opts.calm && this.session.speed !== 0)
      this.shakeAmp = Math.max(this.shakeAmp, px * (this.tile / 48));
  }

  private flashScreen(alpha: number): void {
    if (!this.opts.calm && this.session.speed !== 0) this.flashAlpha = alpha;
  }

  // ------------------------------------------------------------ terrain

  private drawTerrain(state: BattleState): void {
    const t = this.tile;
    const { width: w, height: h } = state.map;
    for (const layer of [this.ground, this.water, this.edges]) {
      layer.cacheAsTexture(false);
      for (const c of layer.removeChildren()) c.destroy();
    }
    this.water.mask = null;
    for (const clip of [this.edgeClip, this.skyClip])
      clip
        .clear()
        .rect(0, 0, w * t, h * t)
        .fill(0xffffff);
    const skin = this.skin;
    const back = skin && this.backdrop?.id === this.session.backdrop ? this.backdrop : null;
    if (back) {
      // A whole 1998 battle map: its picture, with the rules' terrain grid under it.
      const s = new Sprite(back.texture);
      s.width = w * t;
      s.height = h * t;
      this.ground.addChild(s);
      // Its rivers shimmer: a second copy, displaced, shown only on water tiles.
      if (this.waterFilter) {
        const ripple = new Sprite(back.texture);
        ripple.width = w * t;
        ripple.height = h * t;
        const mask = new Graphics();
        for (let y = 0; y < h; y++)
          for (let x = 0; x < w; x++)
            if (state.map.tiles[y * w + x] === "water") mask.rect(x * t, y * t, t, t);
        mask.fill(0xffffff);
        this.water.addChild(ripple, mask);
        this.water.mask = mask;
      }
    } else {
      paintTerrain(state.map, t, this.skinSet ?? ourTerrain(), {
        ground: this.ground,
        water: this.water,
        edges: this.edges,
      });
    }
    this.water.filters = this.waterFilter ? [this.waterFilter as Filter] : [];
    if (this.waterMap) {
      this.waterMap.scale.set((t * 2.5) / 128);
      this.waterFilter?.scale.set(t * 0.09, t * 0.06);
    }
    // One texture each for the still layers, when it fits the GPU's limits.
    const res = this.app.renderer.resolution;
    if (w * t * res <= 4096 && h * t * res <= 4096)
      for (const layer of [this.ground, this.edges])
        if (layer.children.length > 0) layer.cacheAsTexture({ resolution: res });
    const g = this.grid;
    g.clear();
    for (let x = 0; x <= w; x++) g.moveTo(x * t, 0).lineTo(x * t, h * t);
    for (let y = 0; y <= h; y++) g.moveTo(0, y * t).lineTo(w * t, y * t);
    g.stroke({ width: 1, color: 0x000000, alpha: 0.09 });
    g.rect(0, 0, w * t, h * t).stroke({ width: 2, color: 0x000000, alpha: 0.35 });
  }

  private drawFire(state: BattleState): void {
    const g = this.fireGlow;
    const t = this.tile;
    g.clear();
    this.burning = state.burning.map((b) => ({ x: b.x, y: b.y }));
    for (const b of state.burning)
      g.roundRect(b.x * t + 2, b.y * t + 2, t - 4, t - 4, t * 0.2).fill({
        color: 0xff6a1f,
        alpha: 0.28,
      });
  }

  // ------------------------------------------------------------ overlay

  private drawOverlay(v: OverlayView): void {
    const g = this.overlay;
    const t = this.tile;
    const r = t * 0.14;
    g.clear();
    const fill = (cells: readonly Cell[], color: number, alpha: number) => {
      for (const c of cells)
        g.roundRect(c.x * t + 2, c.y * t + 2, t - 4, t - 4, r).fill({ color, alpha });
    };
    const outline = (cells: readonly Cell[], color: number, width: number, alpha: number) => {
      for (const c of cells)
        g.roundRect(c.x * t + 2, c.y * t + 2, t - 4, t - 4, r).stroke({ width, color, alpha });
    };
    fill(v.danger, 0xff3b30, 0.26);
    fill(v.reach, 0x4d9bff, v.pending ? 0.2 : 0.36);
    outline(v.reach, 0xbfe0ff, 1.5, v.pending ? 0.3 : 0.7);
    fill(v.attackable, 0xff4d40, 0.22);
    outline(v.attackable, 0xff8577, 1.5, 0.8);
    fill(v.aimCells, v.aimColor, 0.42);
    outline(v.aimCells, 0xffffff, 1.5, 0.5);
    fill(v.area, v.aimColor, 0.3);
    for (const c of v.ready) {
      const p = this.center(c);
      // Figures get a ring at their feet; tokens get one around them.
      if (this.skin || !this.opts.tokens)
        g.ellipse(p.x, p.y + t * 0.3, t * 0.42, t * 0.17).stroke({
          width: 2,
          color: 0xfff3b0,
          alpha: 0.75,
        });
      else g.circle(p.x, p.y, t * 0.45).stroke({ width: 2, color: 0xfff3b0, alpha: 0.7 });
    }
    if (v.hover) outline([v.hover], 0xffffff, 1.5, 0.7);
    const p = this.pulse;
    p.clear();
    if (v.pending)
      p.roundRect(v.pending.x * t + 1.5, v.pending.y * t + 1.5, t - 3, t - 3, r).stroke({
        width: 3,
        color: 0xffe066,
      });
    if (v.target)
      p.roundRect(v.target.x * t + 1.5, v.target.y * t + 1.5, t - 3, t - 3, r).stroke({
        width: 3,
        color: 0xffffff,
      });
  }

  // ------------------------------------------------------------ units

  private sprite(u: Unit): UnitSprite {
    let s = this.sprites.get(u.id);
    if (s) return s;
    const t = this.tile;
    const root = new Container();
    const shadow = new Sprite(this.tex.shadow);
    shadow.anchor.set(0.5);
    const ring = new Graphics();
    const token = new Sprite();
    token.anchor.set(0.5);
    const figure = new Sprite();
    figure.anchor.set(0.5);
    figure.visible = false;
    const glyph = new Text({
      text: "",
      style: {
        ...textStyle(Math.round(t * 0.34), 0xffffff, true),
        dropShadow: { color: 0x000000, alpha: 0.6, blur: 2, distance: 1, angle: Math.PI / 2 },
      },
    });
    glyph.anchor.set(0.5);
    glyph.position.set(0, -t * 0.04);
    const hp = new Graphics();
    const mark = new Text({ text: "", style: textStyle(Math.round(t * 0.26), 0xffe066, true) });
    mark.anchor.set(0.5);
    mark.position.set(t * 0.3, -t * 0.32);
    const cond = new Text({ text: "", style: textStyle(Math.round(t * 0.24), 0xffffff, true) });
    cond.anchor.set(0.5);
    cond.position.set(-t * 0.3, -t * 0.32);
    root.addChild(shadow, ring, token, figure, glyph, hp, mark, cond);
    this.units.addChild(root);
    const set = this.skin ? this.skin.setFor(u.classId, u.id, u.side) : -1;
    s = {
      root,
      shadow,
      ring,
      token,
      rig: null,
      figure,
      hp,
      mark,
      cond,
      glyph,
      set,
      pose: null,
      hpNow: u.hp,
      hpGhost: u.hp,
      maxHp: u.maxHp,
      ghostWait: 0,
    };
    this.sprites.set(u.id, s);
    return s;
  }

  /** True when the unit is drawn with classic sprites (its sheets are loaded). */
  private skinned(s: UnitSprite): boolean {
    const skin = this.skin;
    if (!skin || s.set < 0) return false;
    if (skin.sheets(s.set)) return true;
    if (!this.loadingSets.has(s.set)) {
      this.loadingSets.add(s.set);
      skin
        .load(s.set)
        .then(() => {
          if (this.skin === skin && !this.playing && this.shown)
            this.syncUnits(this.shown, this.view?.ghost ?? null);
        })
        .catch(() => {});
    }
    return false;
  }

  private drawUnit(u: Unit, s: UnitSprite, dir: Dir, state: BattleState): void {
    const t = this.tile;
    const cls = this.session.rules.classes[u.classId];
    const color = SIDE_COLOR[u.side] ?? 0x888888;
    const skinned = this.skinned(s);
    const rigged = !skinned && !this.opts.tokens;
    const resting = u.acted && state.winner === null;
    if (rigged) {
      const look = lookFor(u.classId, u.id, u.side);
      if (!s.rig || s.rig.look.kind !== look.kind || s.rig.look.side !== look.side) {
        s.rig?.root.destroy({ children: true });
        s.rig = new ChibiRig(look);
        s.root.addChildAt(s.rig.root, s.root.getChildIndex(s.figure));
      }
      s.rig.setSize(t);
    } else if (s.rig) {
      s.rig.root.destroy({ children: true });
      s.rig = null;
    }
    const figure = skinned || rigged;
    s.shadow.width = t * (figure ? 0.86 : 0.9);
    s.shadow.height = t * 0.34;
    s.shadow.position.set(figure ? 0 : t * 0.04, t * (figure ? 0.3 : 0.28));
    s.ring.clear();
    if (rigged && s.rig) {
      s.ring
        .ellipse(0, t * 0.3, t * 0.34, t * 0.13)
        .fill({ color, alpha: 0.22 })
        .stroke({ width: 2, color, alpha: 0.95 });
      this.setPose(s, idlePose(u, state, this.session), dir);
      s.rig.setTint(resting ? 0x8c8c8c : 0xffffff);
      s.pose = null;
    } else if (skinned) {
      s.ring
        .ellipse(0, t * 0.3, t * 0.34, t * 0.13)
        .fill({ color, alpha: 0.22 })
        .stroke({ width: 2, color, alpha: 0.9 });
      this.setPose(s, idlePose(u, state, this.session), dir);
      s.figure.tint = resting ? 0x8c8c8c : 0xffffff;
    } else {
      const ranged = cls?.range.some((o) => Math.abs(o.dx) + Math.abs(o.dy) > 1) ?? false;
      s.token.texture = tokenTexture(color, ranged);
      s.token.width = t * 0.82;
      s.token.height = t * 0.82;
      s.token.tint = resting ? 0x8a8a8a : 0xffffff;
      s.glyph.text = cls?.name.slice(0, 1) ?? "?";
      s.glyph.alpha = resting ? 0.6 : 1;
      s.pose = null;
    }
    s.figure.visible = skinned;
    s.token.visible = !figure;
    s.glyph.visible = !figure;
    if (s.hpNow !== u.hp || s.maxHp !== u.maxHp) {
      s.hpNow = u.hp;
      s.hpGhost = u.hp;
      s.maxHp = u.maxHp;
    }
    this.drawHp(s);
    const kinds = new Set(
      u.conditions.map((c) => this.session.rules.conditions[c.id]?.kind ?? "debuff"),
    );
    const confused = u.conditions.some(
      (c) => this.session.rules.conditions[c.id]?.blocks === "act",
    );
    s.mark.text = confused ? "?" : u.broken ? "!" : "";
    s.cond.text = `${kinds.has("buff") ? "▲" : ""}${kinds.has("debuff") ? "▼" : ""}`;
    s.cond.style.fill =
      kinds.has("buff") && kinds.has("debuff") ? 0xffe066 : kinds.has("buff") ? 0x7ee07a : 0xc58cff;
    s.root.alpha = 1;
  }

  private drawHp(s: UnitSprite): void {
    const t = this.tile;
    const w = t * 0.64;
    const h = Math.max(4, Math.round(t * 0.09));
    const y = t * 0.4;
    const r = h / 2;
    const share = s.maxHp > 0 ? Math.max(0, s.hpNow / s.maxHp) : 0;
    const ghost = s.maxHp > 0 ? Math.max(share, s.hpGhost / s.maxHp) : 0;
    const color = share > 0.5 ? 0x5ed36a : share > 0.25 ? 0xf2c14e : 0xe5533d;
    const g = s.hp;
    g.clear();
    g.roundRect(-w / 2 - 1.5, y - 1.5, w + 3, h + 3, r + 1.5).fill({
      color: 0x0b0a08,
      alpha: 0.85,
    });
    if (ghost > share && w * ghost >= 1) g.roundRect(-w / 2, y, w * ghost, h, r).fill(0xfff0d0);
    if (w * share >= 1) {
      g.roundRect(-w / 2, y, w * share, h, r).fill(color);
      g.roundRect(-w / 2, y, w * share, h * 0.45, r).fill({ color: 0xffffff, alpha: 0.28 });
    }
  }

  private syncUnits(state: BattleState, ghost: { id: string; to: Cell } | null): void {
    const alive = new Set<string>();
    const toward = enemyCentres(state);
    for (const u of state.units) {
      if (u.hp <= 0) continue;
      alive.add(u.id);
      const s = this.sprite(u);
      const at = ghost && ghost.id === u.id ? ghost.to : u;
      const aim = toward.get(u.side);
      this.drawUnit(u, s, aim ? dirTo(at, aim, this.skin) : DOWN, state);
      const p = this.center(at);
      s.root.position.set(p.x, p.y);
      s.root.scale.set(1);
      s.root.zIndex = p.y;
      s.root.visible = true;
    }
    for (const [id, s] of this.sprites)
      if (!alive.has(id)) {
        s.root.destroy({ children: true });
        this.sprites.delete(id);
      }
  }

  // ------------------------------------------------------------ sprite poses

  /** Shows a pose; an unchanged looping pose keeps its rhythm. */
  private setPose(s: UnitSprite, pose: Pose, dir: Dir, fps?: number): void {
    if (s.rig) {
      // Our figures are painted facing right: mirror them to face left.
      s.rig.setFacing(dir.facing, dir.facing === "side" && !dir.flip);
      s.rig.setPose(pose === "walk" ? (fps === undefined ? "ready" : "walk") : pose);
      return;
    }
    const skin = this.skin;
    const sheets = skin?.sheets(s.set);
    if (!skin || !sheets) return;
    const key = `${pose}:${dir.facing}:${fps ?? ""}`;
    const k = this.tile / (skin.manifest.tile * skin.scale);
    s.figure.scale.set(dir.flip && dir.facing === "side" ? -k : k, k);
    if (s.pose?.key === key && s.pose.loop) return;
    const frames = skin.frames(sheets, pose, dir.facing);
    const timing = POSE_TIMING[pose];
    s.pose = { key, frames, fps: fps ?? timing.fps, loop: timing.loop, elapsed: 0 };
    const first = frames[0];
    if (first) s.figure.texture = first;
  }

  /** Every frame: poses, HP drains, particles, weather, water, camera, pulses. */
  private animate = (): void => {
    const real = this.app.ticker.deltaMS;
    const fast = real * Math.max(1, this.session.speed);
    this.clock += real;
    const t = this.tile;
    for (const s of this.sprites.values()) {
      s.rig?.update(real, fast);
      const p = s.pose;
      if (p && p.frames.length > 1) {
        p.elapsed += p.loop ? real : fast;
        const i = Math.floor((p.elapsed * p.fps) / 1000);
        const tex = p.frames[p.loop ? i % p.frames.length : Math.min(i, p.frames.length - 1)];
        if (tex && s.figure.texture !== tex) s.figure.texture = tex;
      }
      if (s.hpGhost > s.hpNow) {
        if (s.ghostWait > 0) s.ghostWait -= fast;
        else s.hpGhost = Math.max(s.hpNow, s.hpGhost - (s.maxHp * fast) / 700);
        this.drawHp(s);
      }
    }
    this.particles.update(fast);
    this.ambient.update(real);
    // Burning tiles flicker with flames.
    this.flameAcc += fast;
    if (this.flameAcc > 110) {
      this.flameAcc = 0;
      if (this.session.speed !== 0)
        for (const b of this.burning) fx.flames(this.fxc(), this.center(b));
      this.fireGlow.alpha = 0.8 + Math.sin(this.clock / 90) * 0.2;
    }
    if (this.waterMap) {
      this.waterMap.x = ((this.clock / 1000) * t * 0.35) % (t * 2.5);
      this.waterMap.y = Math.sin(this.clock / 1400) * t * 0.2;
    }
    this.pulse.alpha = 0.65 + 0.35 * Math.sin(this.clock / 180);
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
    this.applyCamera();
  };

  /** A bobbing gold chevron over the selected unit. */
  private drawMarker(): void {
    const g = this.marker;
    g.clear();
    const at = this.view?.selected;
    if (!at || this.playing) return;
    const t = this.tile;
    const p = this.center(at);
    const y = p.y - t * 0.66 + Math.sin(this.clock / 220) * t * 0.05;
    const w = t * 0.16;
    g.moveTo(p.x - w, y - w * 0.9)
      .lineTo(p.x, y + w * 0.2)
      .lineTo(p.x + w, y - w * 0.9)
      .lineTo(p.x, y - w * 0.45)
      .closePath()
      .fill(0xffd75e)
      .stroke({ width: 1.5, color: 0x5a3c00, alpha: 0.8 });
  }

  // ------------------------------------------------------------ animation

  private fxc(): fx.FxContext {
    return { p: this.particles, tex: this.tex, tile: this.tile };
  }

  private ms(base: number): number {
    const sp = this.session.speed;
    return sp === 0 ? 0 : base / sp;
  }

  private tween(duration: number, step: (k: number) => void): Promise<void> {
    if (duration <= 0) {
      step(1);
      return Promise.resolve();
    }
    return new Promise((resolve) => {
      let elapsed = 0;
      const tick = () => {
        elapsed += this.app.ticker.deltaMS;
        const k = Math.min(1, elapsed / duration);
        step(k);
        if (k >= 1) {
          this.app.ticker.remove(tick);
          resolve();
        }
      };
      this.app.ticker.add(tick);
    });
  }

  private wait(base: number): Promise<void> {
    return this.tween(this.ms(base), () => {});
  }

  /** A floating number or word that pops in, rises and fades. */
  private float(at: Cell, text: string, color: number, big = false): void {
    if (this.session.speed === 0) return;
    const t = this.tile;
    const size = Math.round(t * (big ? 0.56 : 0.4));
    const label = new Text({
      text,
      style: {
        fontFamily: FONT,
        fontSize: size,
        fontWeight: "900",
        fill: color,
        stroke: { color: 0x1a0f08, width: Math.max(3, Math.round(size / 5)), join: "round" },
        dropShadow: { color: 0x000000, alpha: 0.55, blur: 3, distance: 2, angle: Math.PI / 2 },
      },
    });
    label.anchor.set(0.5);
    const p = this.center(at);
    const y0 = p.y - t * 0.3;
    label.position.set(p.x, y0);
    this.labels.addChild(label);
    void this.tween(this.ms(1000), (k) => {
      const pop = k < 0.12 ? 1.7 - (k / 0.12) * 0.7 : 1;
      label.scale.set(pop);
      label.position.y = y0 - (1 - (1 - k) ** 2) * t * 0.7;
      label.alpha = k < 0.7 ? 1 : 1 - (k - 0.7) / 0.3;
    }).then(() => label.destroy());
  }

  /** A small recoil when a figure takes a hit. */
  private recoil(s: UnitSprite): Promise<void> {
    const x = s.root.position.x;
    const amp = this.tile * 0.07;
    return this.tween(this.ms(240), (k) => {
      if (!s.root.destroyed) s.root.position.x = x + Math.sin(k * Math.PI * 6) * amp * (1 - k);
    });
  }

  private hitText(e: Extract<BattleEvent, { kind: "strike" }>, at: (id: string) => Cell): void {
    const d = at(e.defender);
    if (!e.hit) {
      this.float(d, "빗나감", 0xb8c4d6);
      return;
    }
    this.float(d, e.crit ? `${e.damage}!` : `${e.damage}`, e.crit ? 0xffd23f : 0xffffff, e.crit);
    if (e.broke) this.float({ ...d, y: d.y - 0.45 }, "와해", 0xff9f43);
  }

  /** Animates one command's events from `before`, then shows `after`. */
  async play(events: BattleEvent[], before: BattleState, after: BattleState): Promise<void> {
    if (!this.mounted) return;
    this.playing = true;
    this.overlay.clear();
    this.pulse.clear();
    this.syncUnits(before, null);
    const pos = new Map(before.units.map((u) => [u.id, { x: u.x, y: u.y } as Cell]));
    const at = (id: string): Cell => pos.get(id) ?? { x: 0, y: 0 };
    const px = (id: string) => this.center(at(id));
    const quiet = () => this.session.speed === 0;
    const setHp = (id: string, value: number) => {
      const s = this.sprites.get(id);
      if (!s) return;
      if (value < s.hpNow) {
        s.hpGhost = Math.max(s.hpGhost, s.hpNow);
        s.ghostWait = 260;
      } else s.hpGhost = value;
      s.hpNow = value;
      this.drawHp(s);
    };
    /** The unit's sprite if it is an animated figure (classic sprite or our rig). */
    const skinned = (id: string): UnitSprite | null => {
      const s = this.sprites.get(id);
      return s && (s.rig || this.skinned(s)) ? s : null;
    };
    const tint = (s: UnitSprite, c: number) => {
      if (s.rig) s.rig.setTint(c);
      else s.figure.tint = c;
    };
    /** Moves a sprite between two tiles, keeping the draw order by row. */
    const slide = (s: UnitSprite, from: Cell, to: Cell, ms: number, hop = 0) => {
      const a = this.center(from);
      const b = this.center(to);
      return this.tween(this.ms(ms), (k) => {
        if (s.root.destroyed) return;
        s.root.position.set(
          a.x + (b.x - a.x) * k,
          a.y + (b.y - a.y) * k - Math.sin(k * Math.PI) * hop,
        );
        s.root.zIndex = a.y + (b.y - a.y) * k;
      });
    };
    let zoomed = false;
    const zoomOut = async () => {
      if (!zoomed) return;
      zoomed = false;
      await this.focus({ x: this.cam.fx, y: this.cam.fy }, 1, 0, 260);
    };
    try {
      for (const e of events) {
        if (zoomed && e.kind !== "strike" && e.kind !== "defeated") await zoomOut();
        switch (e.kind) {
          case "moved": {
            const s = this.sprites.get(e.unit);
            const u = before.units.find((v) => v.id === e.unit);
            const path = u
              ? walkPath(before, this.session.rules, { ...u, x: e.from.x, y: e.from.y }, e.to)
              : [e.from, e.to];
            const walking = s && skinned(e.unit);
            let dir = DOWN;
            for (let i = 1; i < path.length && s; i++) {
              const a = path[i - 1] ?? e.from;
              const b = path[i] ?? e.to;
              dir = dirTo(a, b, this.skin);
              if (walking) this.setPose(s, "walk", dir, 8);
              if (!quiet())
                fx.dust(
                  this.fxc(),
                  { x: this.center(a).x, y: this.center(a).y + this.tile * 0.32 },
                  1,
                );
              await slide(s, a, b, walking ? 130 : 110, walking ? 0 : this.tile * 0.12);
            }
            if (s && walking) this.setPose(s, "stand", dir);
            pos.set(e.unit, e.to);
            break;
          }
          case "pushed": {
            const s = this.sprites.get(e.unit);
            if (s && skinned(e.unit)) this.setPose(s, "hurt", DOWN);
            if (s) await slide(s, e.from, e.to, 200);
            pos.set(e.unit, e.to);
            if (!quiet())
              fx.dust(
                this.fxc(),
                { x: this.center(e.to).x, y: this.center(e.to).y + this.tile * 0.3 },
                4,
              );
            if (e.damage > 0) {
              this.shake(3);
              setHp(e.unit, e.hpAfter);
              this.float(e.to, `-${e.damage}`, 0xff8a7a);
            }
            break;
          }
          case "strike": {
            const a0 = at(e.attacker);
            const d0 = at(e.defender);
            const ap = this.center(a0);
            const dp = this.center(d0);
            const angle = Math.atan2(dp.y - ap.y, dp.x - ap.x);
            if (!zoomed && this.cinematic()) {
              zoomed = true;
              await this.focus({ x: (ap.x + dp.x) / 2, y: (ap.y + dp.y) / 2 }, 1.18, 0.35, 220);
            }
            const fig = skinned(e.attacker);
            const att = this.sprites.get(e.attacker);
            const def = this.sprites.get(e.defender);
            const impact = async () => {
              if (e.hit) {
                if (!quiet()) fx.strikeHit(this.fxc(), dp, angle, e.crit);
                setHp(e.defender, e.hpAfter);
                this.shake(e.crit ? 8 : 3);
                if (e.crit) this.flashScreen(0.35);
                if (def) void this.recoil(def);
                if (e.crit) await this.wait(70);
              } else if (!quiet()) fx.strikeMiss(this.fxc(), dp, angle);
              this.hitText(e, at);
            };
            const cls =
              this.session.rules.classes[
                before.units.find((u) => u.id === e.attacker)?.classId ?? ""
              ];
            const ranged = Math.abs(a0.x - d0.x) + Math.abs(a0.y - d0.y) > 1;
            const shot = ranged && cls ? (cls.family === "catapult" ? "stone" : "arrow") : null;
            /** Ranged attacks: the arrow or stone leaves partway through and lands on the impact. */
            const fly = async (lead: number) => {
              if (!shot) return this.wait(lead);
              await this.wait(lead - 130);
              if (!quiet()) fx.projectile(this.fxc(), ap, dp, shot === "stone" ? 260 : 130, shot);
              await this.wait(shot === "stone" ? 260 : 130);
            };
            if (fig && att) {
              // Figures: the swing, then the defender recoils or parries.
              const toward = dirTo(a0, d0, this.skin);
              const back = dirTo(d0, a0, this.skin);
              att.root.zIndex = 1e6;
              this.setPose(fig, "attack", toward);
              await fly(shot === "stone" ? 360 : 240);
              const dfig = skinned(e.defender);
              if (dfig) this.setPose(dfig, e.hit ? "hurt" : "guard", back);
              await impact();
              await this.wait(320);
              this.setPose(fig, "stand", toward);
              att.root.zIndex = att.root.position.y;
              const left = this.sprites.get(e.defender)?.hpNow ?? 0;
              if (dfig && left > 0) this.setPose(dfig, "stand", back);
              await this.wait(100);
              break;
            }
            // Tokens: a quick lunge and back (or a shot from where they stand).
            if (shot) await fly(240);
            else if (att) {
              att.root.zIndex = 1e6;
              await this.tween(this.ms(130), (k) =>
                att.root.position.set(
                  ap.x + (dp.x - ap.x) * 0.38 * k,
                  ap.y + (dp.y - ap.y) * 0.38 * k,
                ),
              );
            }
            await impact();
            if (att) {
              await this.tween(this.ms(160), (k) =>
                att.root.position.set(
                  ap.x + (dp.x - ap.x) * 0.38 * (1 - k),
                  ap.y + (dp.y - ap.y) * 0.38 * (1 - k),
                ),
              );
              att.root.zIndex = ap.y;
            }
            await this.wait(140);
            break;
          }
          case "defeated": {
            const s = this.sprites.get(e.unit);
            const p = px(e.unit);
            this.float(at(e.unit), FATE[e.fate], 0xffb3a7, true);
            if (!quiet()) fx.defeat(this.fxc(), p);
            if (s && skinned(e.unit)) {
              this.setPose(s, "hurt", DOWN);
              await this.tween(this.ms(480), (k) => {
                s.root.alpha = Math.floor(k * 8) % 2 ? 0.3 : 1;
                tint(s, Math.floor(k * 8) % 2 ? 0xffffff : 0xffb0a0);
              });
              await this.tween(this.ms(300), (k) => (s.root.alpha = 1 - k));
            } else if (s)
              await this.tween(this.ms(420), (k) => {
                s.root.alpha = 1 - k;
                s.root.scale.set(1 - k * 0.4);
              });
            break;
          }
          case "stratagem": {
            const def = this.session.rules.stratagems[e.id];
            const color = def ? SCHOOL_COLOR[def.school] : 0xffffff;
            const caster = before.units.find((u) => u.id === e.caster);
            if (def && caster && this.cinematic()) {
              this.session.cutin = {
                unit: caster.id,
                name: caster.name,
                skill: def.name,
                color,
                side: caster.side,
                mine: caster.side === this.session.humanSide,
              };
              await this.tween(this.ms(this.session.speed === 1 ? 950 : 1300), (k) => {
                this.session.cutinAt = k;
              });
              this.session.cutin = null;
            }
            const s = skinned(e.caster);
            if (s) this.setPose(s, "cheer", DOWN);
            if (!quiet()) fx.castSigil(this.fxc(), px(e.caster), color);
            this.float(at(e.caster), def?.name ?? e.id, 0xffffff);
            await this.wait(260);
            if (def && !quiet()) {
              const cells = def.area.map((o) =>
                this.center({ x: e.at.x + o.dx, y: e.at.y + o.dy }),
              );
              fx.schoolBurst(this.fxc(), def.school, cells);
              if (def.school === "earth" || def.school === "fire")
                this.shake(def.school === "earth" ? 6 : 3);
              if (def.school === "fire") this.flashScreen(0.12);
            }
            await this.wait(def?.school === "earth" ? 460 : 340);
            break;
          }
          case "stratagemEffect": {
            const c = at(e.unit);
            const p = this.center(c);
            const above = { x: c.x, y: c.y - 0.45 };
            const s = this.sprites.get(e.unit);
            if (e.damage > 0) {
              const fig = skinned(e.unit);
              if (fig) this.setPose(fig, "hurt", DOWN);
              if (s) void this.recoil(s);
              this.float(c, `${e.damage}`, 0xff7a2f);
            }
            if (e.heal > 0) {
              if (!quiet()) fx.heal(this.fxc(), p);
              this.float(c, `+${e.heal}`, 0x7ee07a);
            }
            if (e.condition) {
              const def = this.session.rules.conditions[e.condition];
              const buff = def?.kind === "buff";
              if (!quiet()) fx.aura(this.fxc(), p, buff ? 0xffe27a : 0xb57cff, buff);
              this.float(
                above,
                `${def?.name ?? e.condition} ${e.turns}`,
                buff ? 0x7ee07a : 0xc58cff,
              );
            } else if (e.resisted) this.float(above, "저항", 0xb8c4d6);
            setHp(e.unit, e.hpAfter);
            await this.wait(170);
            break;
          }
          case "cleansed":
            if (!quiet()) fx.aura(this.fxc(), px(e.unit), 0x9be7ff, true);
            this.float(at(e.unit), "정화", 0x9be7ff);
            break;
          case "conditionDamage":
            if (!quiet()) fx.aura(this.fxc(), px(e.unit), 0x9b5cff, false);
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `${e.damage}`, 0xb57cff);
            await this.wait(170);
            break;
          case "twist": {
            const good = e.outcome === "windfall";
            if (!quiet()) fx.aura(this.fxc(), px(e.caster), good ? 0xffd23f : 0x8a8a8a, good);
            if (good) this.flashScreen(0.18);
            this.float(at(e.caster), good ? "천운!" : "불운!", 0xffd23f, true);
            await this.wait(420);
            break;
          }
          case "ignited":
            this.burning.push({ ...e.at });
            this.fireGlow
              .roundRect(
                e.at.x * this.tile + 2,
                e.at.y * this.tile + 2,
                this.tile - 4,
                this.tile - 4,
                this.tile * 0.2,
              )
              .fill({ color: 0xff6a1f, alpha: 0.28 });
            break;
          case "burned":
            if (!quiet()) fx.flames(this.fxc(), px(e.unit));
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `${e.damage}`, 0xff7a2f);
            break;
          case "terrainHeal":
            if (!quiet()) fx.aura(this.fxc(), px(e.unit), 0x8cf58a, true);
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `+${e.amount}`, 0x7ee07a);
            break;
          case "battleEnd":
            if (!quiet())
              for (const u of after.units)
                if (u.hp > 0 && u.side === e.winner) fx.heal(this.fxc(), this.center(at(u.id)));
            break;
          default:
            break;
        }
      }
    } finally {
      await zoomOut();
      this.playing = false;
      this.shown = after;
      if (this.relayoutLater) this.relayout();
      else {
        this.syncUnits(after, null);
        this.drawFire(after);
      }
    }
  }
}

const DOWN: Dir = { facing: "down", flip: false };

/** The way from one tile to another; side frames are mirrored to face right. */
function dirTo(from: Cell, to: Cell, skin: ClassicSkin | null): Dir {
  const dx = to.x - from.x;
  const dy = to.y - from.y;
  if (dx === 0 && dy === 0) return DOWN;
  if (Math.abs(dx) >= Math.abs(dy)) {
    const right = dx > 0;
    return { facing: "side", flip: (skin?.manifest.sideFacesLeft ?? true) ? right : !right };
  }
  return { facing: dy > 0 ? "down" : "up", flip: false };
}

/** Per side, the centre of its living enemies: idle figures face the foe. */
function enemyCentres(state: BattleState): Map<number, Cell> {
  const out = new Map<number, Cell>();
  const sides = new Set(state.units.map((u) => u.side));
  for (const side of sides) {
    const foes = state.units.filter((u) => u.hp > 0 && u.side !== side);
    if (foes.length === 0) continue;
    out.set(side, {
      x: foes.reduce((a, u) => a + u.x, 0) / foes.length,
      y: foes.reduce((a, u) => a + u.y, 0) / foes.length,
    });
  }
  return out;
}

/** The resting pose: winners cheer, the wounded and the confused sag, the ready march in place. */
function idlePose(u: Unit, state: BattleState, session: Session): Pose {
  if (state.winner !== null) return state.winner === u.side ? "cheer" : "weak";
  if (hasBlock(session.rules, u, "act") || u.hp * 10 < u.maxHp * 3) return "weak";
  return u.acted ? "stand" : "walk";
}

function textStyle(size: number, fill: number, bold: boolean): TextStyleOptions {
  return {
    fontFamily: FONT,
    fontSize: size,
    fill,
    fontWeight: bold ? "700" : "400",
    stroke: { color: 0x000000, width: Math.max(2, Math.round(size / 6)) },
  };
}
