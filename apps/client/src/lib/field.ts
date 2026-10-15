/**
 * PixiJS battlefield (ADR 0007): terrain, overlays (reach, targets, danger),
 * units and effects. It draws placeholder shapes, or the local classic skin's
 * terrain and animated sprites when the owner has exported one (ADR 0042).
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
  Graphics,
  Sprite,
  Text,
  type TextStyleOptions,
  type Texture,
} from "pixi.js";
import { walkPath } from "./path.ts";
import type { Session } from "./session.svelte.ts";
import type { ClassicSkin, Facing, Pose } from "./skin.svelte.ts";
import { FATE, SCHOOL_COLOR } from "./text.ts";

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

const SIDE_COLOR = [0x2f6fd8, 0xc8463c, 0x3ca05a, 0xb08a2e];
const BG = 0x1b1a17;
const FONT = "'Noto Sans KR', 'Apple SD Gothic Neo', 'Malgun Gothic', system-ui, sans-serif";

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
}

interface UnitSprite {
  root: Container;
  /** Placeholder disc, or the side-coloured base under a skinned figure. */
  body: Graphics;
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

export class Field {
  private app = new Application();
  private world = new Container();
  private tiles = new Container();
  private terrain = new Graphics();
  private overlay = new Graphics();
  private fire = new Graphics();
  private units = new Container();
  private fx = new Container();
  private sprites = new Map<string, UnitSprite>();
  private tile = 48;
  private ox = 0;
  private oy = 0;
  private playing = false;
  private mapKey = "";
  private shown: BattleState | null = null;
  private view: OverlayView | null = null;
  private mounted = false;
  private skin: ClassicSkin | null = null;
  /** The 1998 map picture under the current battle, once loaded. */
  private backdrop: { id: number; texture: Texture } | null = null;
  private loadingSets = new Set<number>();

  constructor(private readonly session: Session) {}

  async mount(host: HTMLElement): Promise<void> {
    await this.app.init({
      resizeTo: host,
      background: BG,
      antialias: true,
      autoDensity: true,
      resolution: Math.min(window.devicePixelRatio || 1, 2),
    });
    host.appendChild(this.app.canvas);
    this.units.sortableChildren = true;
    this.world.addChild(this.tiles, this.terrain, this.fire, this.overlay, this.units, this.fx);
    this.app.stage.addChild(this.world);
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

  /** Switches between placeholder art and the classic skin. */
  setSkin(skin: ClassicSkin | null): void {
    if (skin === this.skin) return;
    this.skin = skin;
    this.mapKey = "";
    if (this.mounted && this.view) this.render(this.shown ?? this.session.state, this.view);
  }

  destroy(): void {
    this.session.player = null;
    this.mounted = false;
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
    }
    this.drawOverlay(view);
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
    const state = this.shown ?? this.session.state;
    this.shown = state;
    const { width: W, height: H } = this.app.screen;
    const i = this.session.insets;
    const availW = Math.max(100, W - i.left - i.right - 16);
    const availH = Math.max(100, H - i.top - i.bottom - 16);
    let tile = Math.floor(Math.min(availW / state.map.width, availH / state.map.height));
    if (this.skin) {
      // The classic art is 48 px: whole and half steps keep it crisp; below that it is smoothed.
      const base = this.skin.manifest.tile;
      tile = tile >= base * 1.5 ? base * 1.5 : tile >= base ? base : tile - (tile % 2);
      this.skin.setFilter(tile >= base ? "nearest" : "linear");
    }
    this.tile = Math.max(20, Math.min(72, tile));
    this.ox = Math.floor(i.left + 8 + (availW - this.tile * state.map.width) / 2);
    this.oy = Math.floor(i.top + 8 + (availH - this.tile * state.map.height) / 2);
    this.world.position.set(this.ox, this.oy);
    // For tests and tools: where the map sits on the canvas, in CSS pixels.
    this.app.canvas.dataset.geometry = JSON.stringify({
      tile: this.tile,
      ox: this.ox,
      oy: this.oy,
    });
    this.session.geometry = { tile: this.tile, ox: this.ox, oy: this.oy };
    this.drawTerrain(state);
    for (const s of this.sprites.values()) s.root.destroy({ children: true });
    this.sprites.clear();
    this.syncUnits(state, this.view?.ghost ?? null);
    this.drawFire(state);
    if (this.view) this.drawOverlay(this.view);
  }

  private cellAt(gx: number, gy: number): Cell | null {
    const s = this.shown;
    if (!s) return null;
    const x = Math.floor((gx - this.ox) / this.tile);
    const y = Math.floor((gy - this.oy) / this.tile);
    return x >= 0 && y >= 0 && x < s.map.width && y < s.map.height ? { x, y } : null;
  }

  private center(c: Cell): { x: number; y: number } {
    return { x: c.x * this.tile + this.tile / 2, y: c.y * this.tile + this.tile / 2 };
  }

  // ------------------------------------------------------------ terrain

  private drawTerrain(state: BattleState): void {
    const g = this.terrain;
    const t = this.tile;
    const { width: w, height: h } = state.map;
    g.clear();
    for (const c of this.tiles.removeChildren()) c.destroy();
    const skin = this.skin;
    const back = skin && this.backdrop?.id === this.session.backdrop ? this.backdrop : null;
    if (back) {
      // A whole 1998 battle map: its picture, with the rules' terrain grid under it.
      const s = new Sprite(back.texture);
      s.width = w * t;
      s.height = h * t;
      this.tiles.addChild(s);
    } else if (skin) {
      for (let y = 0; y < h; y++)
        for (let x = 0; x < w; x++) {
          const id = state.map.tiles[y * w + x] as TerrainId;
          const variants = skin.terrain.get(id) ?? [];
          const tex = variants[(((x * 73856093) ^ (y * 19349663)) >>> 0) % variants.length];
          if (!tex) {
            g.rect(x * t, y * t, t, t).fill(TERRAIN_COLOR[id]);
            continue;
          }
          const s = new Sprite(tex);
          s.position.set(x * t, y * t);
          s.width = t;
          s.height = t;
          this.tiles.addChild(s);
        }
    } else
      for (let y = 0; y < h; y++)
        for (let x = 0; x < w; x++) {
          const id = state.map.tiles[y * w + x] as TerrainId;
          const px = x * t;
          const py = y * t;
          g.rect(px, py, t, t).fill(TERRAIN_COLOR[id]);
          decorate(g, id, px, py, t, (x * 7 + y * 13) % 5);
        }
    for (let x = 0; x <= w; x++) g.moveTo(x * t, 0).lineTo(x * t, h * t);
    for (let y = 0; y <= h; y++) g.moveTo(0, y * t).lineTo(w * t, y * t);
    g.stroke({ width: 1, color: 0x000000, alpha: skin ? 0.1 : 0.18 });
  }

  private drawFire(state: BattleState): void {
    const g = this.fire;
    const t = this.tile;
    g.clear();
    for (const b of state.burning) {
      const px = b.x * t;
      const py = b.y * t;
      g.rect(px, py, t, t).fill({ color: 0xff5a1f, alpha: 0.35 });
      for (const [fx, h] of [
        [0.3, 0.45],
        [0.55, 0.6],
        [0.75, 0.4],
      ] as const) {
        g.moveTo(px + t * (fx - 0.1), py + t * 0.85)
          .lineTo(px + t * fx, py + t * (0.85 - h))
          .lineTo(px + t * (fx + 0.1), py + t * 0.85)
          .closePath()
          .fill({ color: 0xffb02e, alpha: 0.9 });
      }
    }
  }

  // ------------------------------------------------------------ overlay

  private drawOverlay(v: OverlayView): void {
    const g = this.overlay;
    const t = this.tile;
    g.clear();
    const fill = (cells: readonly Cell[], color: number, alpha: number) => {
      for (const c of cells) g.rect(c.x * t + 1, c.y * t + 1, t - 2, t - 2).fill({ color, alpha });
    };
    const outline = (c: Cell, color: number, width: number, alpha = 1) =>
      g
        .rect(c.x * t + width / 2, c.y * t + width / 2, t - width, t - width)
        .stroke({ width, color, alpha });
    fill(v.danger, 0xff3b30, 0.3);
    fill(v.reach, 0x3d8bff, v.pending ? 0.22 : 0.45);
    for (const c of v.reach) outline(c, 0x9cc4ff, 1, v.pending ? 0.3 : 0.6);
    fill(v.attackable, 0xff4d40, 0.25);
    for (const c of v.attackable) outline(c, 0xff6b5e, 1.5, 0.8);
    fill(v.aimCells, v.aimColor, 0.45);
    fill(v.area, v.aimColor, 0.3);
    for (const c of v.ready) {
      const p = this.center(c);
      // Skinned figures get a ring at their feet instead of around them.
      if (this.skin)
        g.ellipse(p.x, p.y + t * 0.3, t * 0.42, t * 0.17).stroke({
          width: 2,
          color: 0xfff3b0,
          alpha: 0.75,
        });
      else g.circle(p.x, p.y, t * 0.43).stroke({ width: 2, color: 0xfff3b0, alpha: 0.7 });
    }
    if (v.pending) outline(v.pending, 0xffe066, 3);
    if (v.target) outline(v.target, 0xffffff, 3);
    if (v.hover) outline(v.hover, 0xffffff, 1.5, 0.7);
  }

  // ------------------------------------------------------------ units

  private sprite(u: Unit): UnitSprite {
    let s = this.sprites.get(u.id);
    if (s) return s;
    const t = this.tile;
    const root = new Container();
    const body = new Graphics();
    const figure = new Sprite();
    figure.anchor.set(0.5);
    figure.visible = false;
    const glyph = new Text({ text: "", style: textStyle(Math.round(t * 0.36), 0xffffff, true) });
    glyph.anchor.set(0.5);
    glyph.position.set(0, -t * 0.03);
    const hp = new Graphics();
    const mark = new Text({ text: "", style: textStyle(Math.round(t * 0.26), 0xffe066, true) });
    mark.anchor.set(0.5);
    mark.position.set(t * 0.3, -t * 0.3);
    const cond = new Text({ text: "", style: textStyle(Math.round(t * 0.24), 0xffffff, true) });
    cond.anchor.set(0.5);
    cond.position.set(-t * 0.3, -t * 0.3);
    root.addChild(body, figure, glyph, hp, mark, cond);
    this.units.addChild(root);
    const set = this.skin ? this.skin.setFor(u.classId, u.id, u.side) : -1;
    s = { root, body, figure, hp, mark, cond, glyph, set, pose: null };
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
    const r = t * 0.36;
    const cls = this.session.rules.classes[u.classId];
    const color = SIDE_COLOR[u.side] ?? 0x888888;
    const skinned = this.skinned(s);
    s.body.clear();
    if (skinned) {
      s.body
        .ellipse(0, t * 0.3, t * 0.34, t * 0.13)
        .fill({ color, alpha: 0.45 })
        .stroke({ width: 1.5, color, alpha: 0.95 });
      this.setPose(s, idlePose(u, state, this.session), dir);
      s.figure.tint = u.acted && state.winner === null ? 0x8c8c8c : 0xffffff;
    } else {
      s.body.circle(0, 0, r).fill(color);
      s.body.circle(0, 0, r).stroke({ width: 2, color: 0x000000, alpha: 0.45 });
      if (cls?.range.some((o) => Math.abs(o.dx) + Math.abs(o.dy) > 1))
        s.body.circle(0, 0, r * 0.78).stroke({ width: 1.5, color: 0xffffff, alpha: 0.5 });
      s.glyph.text = cls?.name.slice(0, 1) ?? "?";
      s.pose = null;
    }
    s.figure.visible = skinned;
    s.glyph.visible = !skinned;
    this.drawHp(s, u.hp, u.maxHp);
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
    s.root.alpha = u.acted && !skinned ? 0.6 : 1;
  }

  private drawHp(s: UnitSprite, hp: number, maxHp: number): void {
    const t = this.tile;
    const w = t * (this.skin ? 0.62 : 0.74);
    const h = Math.max(3, t * 0.08);
    const y = t * (this.skin ? 0.4 : 0.36);
    const share = maxHp > 0 ? hp / maxHp : 0;
    s.hp.clear();
    s.hp.rect(-w / 2, y, w, h).fill({ color: 0x000000, alpha: 0.6 });
    s.hp
      .rect(-w / 2, y, w * share, h)
      .fill(share > 0.5 ? 0x5ed36a : share > 0.25 ? 0xf2c14e : 0xe5533d);
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
    const skin = this.skin;
    const sheets = skin?.sheets(s.set);
    if (!skin || !sheets) return;
    const key = `${pose}:${dir.facing}:${fps ?? ""}`;
    const k = this.tile / skin.manifest.tile;
    s.figure.scale.set(dir.flip && dir.facing === "side" ? -k : k, k);
    if (s.pose?.key === key && s.pose.loop) return;
    const frames = skin.frames(sheets, pose, dir.facing);
    const timing = POSE_TIMING[pose];
    s.pose = { key, frames, fps: fps ?? timing.fps, loop: timing.loop, elapsed: 0 };
    const first = frames[0];
    if (first) s.figure.texture = first;
  }

  private animate = (): void => {
    const real = this.app.ticker.deltaMS;
    const fast = real * Math.max(1, this.session.speed);
    for (const s of this.sprites.values()) {
      const p = s.pose;
      if (!p || p.frames.length < 2) continue;
      p.elapsed += p.loop ? real : fast;
      const i = Math.floor((p.elapsed * p.fps) / 1000);
      const tex = p.frames[p.loop ? i % p.frames.length : Math.min(i, p.frames.length - 1)];
      if (tex && s.figure.texture !== tex) s.figure.texture = tex;
    }
  };

  // ------------------------------------------------------------ animation

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

  private float(at: Cell, text: string, color: number, big = false): void {
    if (this.session.speed === 0) return;
    const t = this.tile;
    const label = new Text({
      text,
      style: textStyle(Math.round(t * (big ? 0.5 : 0.36)), color, true),
    });
    label.anchor.set(0.5);
    const p = this.center(at);
    label.position.set(p.x, p.y - t * 0.2);
    this.fx.addChild(label);
    void this.tween(this.ms(900), (k) => {
      label.position.y = p.y - t * 0.2 - k * t * 0.6;
      label.alpha = 1 - k * k;
    }).then(() => label.destroy());
  }

  private burst(at: Cell, color: number): Promise<void> {
    const g = new Graphics();
    const p = this.center(at);
    this.fx.addChild(g);
    return this.tween(this.ms(320), (k) => {
      g.clear();
      g.circle(p.x, p.y, this.tile * (0.2 + 0.5 * k)).fill({ color, alpha: 0.6 * (1 - k) });
    }).then(() => g.destroy());
  }

  /** A small recoil when a skinned figure takes a hit. */
  private shake(s: UnitSprite): Promise<void> {
    const x = s.root.position.x;
    const amp = this.tile * 0.06;
    return this.tween(this.ms(220), (k) => {
      s.root.position.x = x + Math.sin(k * Math.PI * 6) * amp * (1 - k);
    });
  }

  private strikeText(e: Extract<BattleEvent, { kind: "strike" }>, at: (id: string) => Cell): void {
    const d = at(e.defender);
    if (!e.hit) {
      this.float(d, "빗나감", 0xb8c4d6);
      return;
    }
    this.float(d, e.crit ? `${e.damage}!` : `${e.damage}`, e.crit ? 0xffd23f : 0xffffff, e.crit);
    if (e.broke) this.float({ ...d, y: d.y - 0.4 }, "와해", 0xff9f43);
  }

  /** Animates one command's events from `before`, then shows `after`. */
  async play(events: BattleEvent[], before: BattleState, after: BattleState): Promise<void> {
    if (!this.mounted) return;
    this.playing = true;
    this.overlay.clear();
    this.syncUnits(before, null);
    const hp = new Map(before.units.map((u) => [u.id, { hp: u.hp, maxHp: u.maxHp }]));
    const pos = new Map(before.units.map((u) => [u.id, { x: u.x, y: u.y } as Cell]));
    const setHp = (id: string, value: number) => {
      const h = hp.get(id);
      const s = this.sprites.get(id);
      if (!h || !s) return;
      h.hp = value;
      this.drawHp(s, value, h.maxHp);
    };
    const at = (id: string): Cell => pos.get(id) ?? { x: 0, y: 0 };
    const skinned = (id: string): UnitSprite | null => {
      const s = this.sprites.get(id);
      return s && this.skinned(s) ? s : null;
    };
    /** Slides a sprite between two tiles, keeping the draw order by row. */
    const slide = (s: UnitSprite, from: Cell, to: Cell, ms: number) => {
      const a = this.center(from);
      const b = this.center(to);
      return this.tween(this.ms(ms), (k) => {
        s.root.position.set(a.x + (b.x - a.x) * k, a.y + (b.y - a.y) * k);
        s.root.zIndex = s.root.position.y;
      });
    };
    try {
      for (const e of events) {
        switch (e.kind) {
          case "moved": {
            const s = this.sprites.get(e.unit);
            const u = before.units.find((v) => v.id === e.unit);
            const path = u
              ? walkPath(before, this.session.rules, { ...u, x: e.from.x, y: e.from.y }, e.to)
              : [e.from, e.to];
            const walking = s && this.skinned(s);
            let dir = DOWN;
            for (let i = 1; i < path.length && s; i++) {
              const a = path[i - 1] ?? e.from;
              const b = path[i] ?? e.to;
              dir = dirTo(a, b, this.skin);
              if (walking) this.setPose(s, "walk", dir, 8);
              await slide(s, a, b, walking ? 130 : Math.max(60, 260 / (path.length - 1)));
            }
            if (s && walking) this.setPose(s, "stand", dir);
            pos.set(e.unit, e.to);
            break;
          }
          case "pushed": {
            const s = this.sprites.get(e.unit);
            if (s && this.skinned(s)) this.setPose(s, "hurt", DOWN);
            if (s) await slide(s, e.from, e.to, 200);
            pos.set(e.unit, e.to);
            if (e.damage > 0) {
              setHp(e.unit, e.hpAfter);
              this.float(e.to, `-${e.damage}`, 0xff8a7a);
            }
            break;
          }
          case "strike": {
            const a0 = at(e.attacker);
            const d0 = at(e.defender);
            const fig = skinned(e.attacker);
            if (fig) {
              // Classic sprites: the swing, then the defender recoils or parries.
              const toward = dirTo(a0, d0, this.skin);
              const back = dirTo(d0, a0, this.skin);
              fig.root.zIndex = 1e6;
              this.setPose(fig, "attack", toward);
              await this.wait(240);
              const def = skinned(e.defender);
              if (def) this.setPose(def, e.hit ? "hurt" : "guard", back);
              if (def && e.hit) void this.shake(def);
              this.strikeText(e, at);
              if (e.hit) setHp(e.defender, e.hpAfter);
              await this.wait(320);
              this.setPose(fig, "stand", toward);
              fig.root.zIndex = fig.root.position.y;
              if (def && (hp.get(e.defender)?.hp ?? 0) > 0) this.setPose(def, "stand", back);
              await this.wait(100);
              break;
            }
            const s = this.sprites.get(e.attacker);
            const a = this.center(a0);
            const b = this.center(d0);
            if (s) {
              await this.tween(this.ms(120), (k) =>
                s.root.position.set(a.x + (b.x - a.x) * 0.3 * k, a.y + (b.y - a.y) * 0.3 * k),
              );
            }
            if (e.hit) setHp(e.defender, e.hpAfter);
            this.strikeText(e, at);
            if (s)
              await this.tween(this.ms(140), (k) =>
                s.root.position.set(
                  a.x + (b.x - a.x) * 0.3 * (1 - k),
                  a.y + (b.y - a.y) * 0.3 * (1 - k),
                ),
              );
            await this.tween(this.ms(120), () => {});
            break;
          }
          case "defeated": {
            const s = this.sprites.get(e.unit);
            this.float(at(e.unit), FATE[e.fate], 0xffb3a7, true);
            if (s && this.skinned(s)) {
              this.setPose(s, "hurt", DOWN);
              await this.tween(
                this.ms(480),
                (k) => (s.root.alpha = Math.floor(k * 8) % 2 ? 0.25 : 1),
              );
              await this.tween(this.ms(260), (k) => (s.root.alpha = 1 - k));
            } else if (s) await this.tween(this.ms(380), (k) => (s.root.alpha = 1 - k));
            break;
          }
          case "stratagem": {
            const def = this.session.rules.stratagems[e.id];
            const s = skinned(e.caster);
            if (s) this.setPose(s, "cheer", DOWN);
            this.float(at(e.caster), def?.name ?? e.id, 0xffffff);
            await this.burst(e.at, def ? SCHOOL_COLOR[def.school] : 0xffffff);
            break;
          }
          case "stratagemEffect": {
            const c = at(e.unit);
            const above = { x: c.x, y: c.y - 0.4 };
            const s = skinned(e.unit);
            if (s && e.damage > 0) {
              this.setPose(s, "hurt", DOWN);
              void this.shake(s);
            }
            if (e.heal > 0) this.float(c, `+${e.heal}`, 0x7ee07a);
            if (e.damage > 0) this.float(c, `${e.damage}`, 0xff7a2f);
            if (e.condition) {
              const def = this.session.rules.conditions[e.condition];
              const label = `${def?.name ?? e.condition} ${e.turns}`;
              this.float(above, label, def?.kind === "buff" ? 0x7ee07a : 0xc58cff);
            } else if (e.resisted) this.float(above, "저항", 0xb8c4d6);
            setHp(e.unit, e.hpAfter);
            await this.tween(this.ms(160), () => {});
            break;
          }
          case "cleansed":
            this.float(at(e.unit), "정화", 0x9be7ff);
            break;
          case "conditionDamage":
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `${e.damage}`, 0xb57cff);
            await this.tween(this.ms(160), () => {});
            break;
          case "twist":
            this.float(at(e.caster), e.outcome === "windfall" ? "천운!" : "불운!", 0xffd23f, true);
            await this.tween(this.ms(400), () => {});
            break;
          case "ignited":
            this.fire
              .rect(e.at.x * this.tile, e.at.y * this.tile, this.tile, this.tile)
              .fill({ color: 0xff5a1f, alpha: 0.35 });
            break;
          case "burned":
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `${e.damage}`, 0xff7a2f);
            break;
          case "terrainHeal":
            setHp(e.unit, e.hpAfter);
            this.float(at(e.unit), `+${e.amount}`, 0x7ee07a);
            break;
          default:
            break;
        }
      }
    } finally {
      this.playing = false;
      this.shown = after;
      this.syncUnits(after, null);
      this.drawFire(after);
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

/** Simple per-terrain marks so tiles read without art. */
function decorate(g: Graphics, id: TerrainId, x: number, y: number, t: number, v: number): void {
  const dark = { color: 0x000000, alpha: 0.22 };
  const light = { color: 0xffffff, alpha: 0.28 };
  switch (id) {
    case "grass":
      for (const [a, b] of [
        [0.25, 0.35],
        [0.6, 0.25],
        [0.4, 0.7],
        [0.75, 0.65],
      ] as const)
        g.moveTo(x + t * a, y + t * b).lineTo(x + t * (a + 0.03), y + t * (b - 0.1));
      g.stroke({ width: 1.5, ...dark });
      break;
    case "forest":
      for (const [a, b] of [
        [0.3, 0.35],
        [0.68, 0.32],
        [0.48, 0.68],
      ] as const)
        g.circle(x + t * a, y + t * b, t * 0.17).fill({ color: 0x24451f, alpha: 0.85 });
      break;
    case "hills":
      g.moveTo(x + t * 0.15, y + t * 0.7)
        .quadraticCurveTo(x + t * 0.4, y + t * 0.3, x + t * 0.65, y + t * 0.7)
        .stroke({ width: 2, ...dark });
      break;
    case "mountain":
      g.moveTo(x + t * 0.12, y + t * 0.82)
        .lineTo(x + t * 0.45, y + t * 0.2)
        .lineTo(x + t * 0.8, y + t * 0.82)
        .closePath()
        .fill({ color: 0x4c443e, alpha: 0.9 });
      g.moveTo(x + t * 0.37, y + t * 0.35)
        .lineTo(x + t * 0.45, y + t * 0.2)
        .lineTo(x + t * 0.53, y + t * 0.35)
        .closePath()
        .fill(light);
      break;
    case "water":
    case "ford":
      for (const b of [0.35, 0.65])
        g.moveTo(x + t * 0.15, y + t * b)
          .quadraticCurveTo(x + t * 0.32, y + t * (b - 0.08), x + t * 0.5, y + t * b)
          .quadraticCurveTo(x + t * 0.68, y + t * (b + 0.08), x + t * 0.85, y + t * b);
      g.stroke({ width: 1.5, ...light });
      if (id === "ford")
        for (const [a, b] of [
          [0.3, 0.5],
          [0.7, 0.45],
        ] as const)
          g.circle(x + t * a, y + t * b, t * 0.06).fill({ color: 0x8a7a60, alpha: 0.9 });
      break;
    case "bridge":
      for (const b of [0.2, 0.4, 0.6, 0.8]) g.moveTo(x + t * b, y).lineTo(x + t * b, y + t);
      g.stroke({ width: 1.5, ...dark });
      break;
    case "village":
      g.rect(x + t * 0.3, y + t * 0.45, t * 0.4, t * 0.3).fill({ color: 0x8b5a3c, alpha: 0.9 });
      g.moveTo(x + t * 0.25, y + t * 0.47)
        .lineTo(x + t * 0.5, y + t * 0.25)
        .lineTo(x + t * 0.75, y + t * 0.47)
        .closePath()
        .fill({ color: 0x6b3b2a, alpha: 0.9 });
      break;
    case "fort":
      g.rect(x + t * 0.2, y + t * 0.35, t * 0.6, t * 0.45).fill({ color: 0x5e5a54, alpha: 0.9 });
      for (const a of [0.2, 0.42, 0.64])
        g.rect(x + t * a, y + t * 0.25, t * 0.14, t * 0.12).fill({ color: 0x5e5a54, alpha: 0.9 });
      break;
    case "wall":
      for (const b of [0.33, 0.66]) g.moveTo(x, y + t * b).lineTo(x + t, y + t * b);
      for (const a of [0.25 + (v % 2) * 0.25, 0.75])
        g.moveTo(x + t * a, y).lineTo(x + t * a, y + t);
      g.stroke({ width: 1, ...light });
      break;
    case "camp":
      g.moveTo(x + t * 0.25, y + t * 0.75)
        .lineTo(x + t * 0.5, y + t * 0.3)
        .lineTo(x + t * 0.75, y + t * 0.75)
        .closePath()
        .fill({ color: 0xe8dcc0, alpha: 0.85 });
      g.moveTo(x + t * 0.5, y + t * 0.3)
        .lineTo(x + t * 0.5, y + t * 0.75)
        .stroke({ width: 1, ...dark });
      break;
    default:
      if (v === 0) g.circle(x + t * 0.7, y + t * 0.3, t * 0.04).fill(dark);
  }
}
