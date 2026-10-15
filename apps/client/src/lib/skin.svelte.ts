/**
 * The local-only classic skin (ADR 0042): 1998 PC 조조전 sprites, portraits and
 * terrain exported from the owner's own copy into apps/client/classic/. Only the
 * dev server serves that folder, so production builds always fall back to our
 * placeholder art.
 */
import type { TerrainId } from "@tk/rules";
import { Assets, Rectangle, Texture, type TextureSource } from "pixi.js";

export type Facing = "down" | "up" | "side";
export type Pose = "walk" | "stand" | "weak" | "attack" | "guard" | "hurt" | "cheer";
export type Filter = "nearest" | "linear";

export interface SkinManifest {
  tile: number;
  /** Side-view frames face left; mirror them to face right. */
  sideFacesLeft: boolean;
  sheets: Record<"mov" | "atk" | "spc", { size: number; count: number }>;
  anims: {
    walk: Record<Facing, number[]>;
    stand: Record<Facing, number[]>;
    weak: number[];
    attack: Record<Facing, number[]>;
    guard: Record<Facing, number[]>;
    hurt: number[];
    cheer: number[];
  };
  classes: Record<string, number>;
  officerSets: Record<string, number>;
  officerFaces: Record<string, number>;
  terrain: { file: string; order: TerrainId[]; counts: number[]; theme: number };
  maps: { id: number; name: string; width: number; height: number }[];
}

export interface UnitSheets {
  mov: Texture[];
  atk: Texture[];
  spc: Texture[];
}

export interface ClassicMapGrid {
  id: number;
  name: string;
  width: number;
  height: number;
  terrain: TerrainId[];
}

const BASE = "/classic";

function slice(base: Texture, size: number, count: number): Texture[] {
  return Array.from(
    { length: count },
    (_, i) => new Texture({ source: base.source, frame: new Rectangle(i * size, 0, size, size) }),
  );
}

async function pixelTexture(url: string): Promise<Texture> {
  const t: Texture = await Assets.load(url);
  t.source.scaleMode = "nearest";
  return t;
}

export class ClassicSkin {
  private sheetCache = new Map<number, Promise<UnitSheets>>();
  private loadedSheets = new Map<number, UnitSheets>();
  private mapCache = new Map<number, Promise<Texture>>();
  private sources = new Set<TextureSource>();
  private filter: Filter = "nearest";
  readonly terrain = new Map<TerrainId, Texture[]>();

  constructor(
    readonly manifest: SkinManifest,
    atlas: Texture,
  ) {
    this.track(atlas);
    const t = manifest.tile;
    manifest.terrain.order.forEach((id, row) => {
      const n = manifest.terrain.counts[row] ?? 0;
      this.terrain.set(
        id,
        Array.from(
          { length: n },
          (_, col) =>
            new Texture({ source: atlas.source, frame: new Rectangle(col * t, row * t, t, t) }),
        ),
      );
    });
  }

  /** Crisp pixels at whole-number scales; smooth filtering when the art is shrunk. */
  setFilter(mode: Filter): void {
    if (mode === this.filter) return;
    this.filter = mode;
    for (const source of this.sources) applyFilter(source, mode);
  }

  private track(t: Texture): Texture {
    this.sources.add(t.source);
    applyFilter(t.source, this.filter);
    return t;
  }

  /** The sprite set for a unit: the officer's own, else the class set in side colour (blue = side 0). */
  setFor(classId: string, officerId: string, side: number): number {
    const own = this.manifest.officerSets[officerId];
    if (own !== undefined) return own;
    const base = this.manifest.classes[classId] ?? 0;
    return base + (side === 0 ? 2 : 0);
  }

  /** Sheets already loaded (synchronous use while drawing). */
  sheets(set: number): UnitSheets | undefined {
    return this.loadedSheets.get(set);
  }

  load(set: number): Promise<UnitSheets> {
    let p = this.sheetCache.get(set);
    if (!p) {
      const m = this.manifest.sheets;
      const tag = String(set).padStart(3, "0");
      p = Promise.all([
        pixelTexture(`${BASE}/units/unit_${tag}_mov.png`),
        pixelTexture(`${BASE}/units/unit_${tag}_atk.png`),
        pixelTexture(`${BASE}/units/unit_${tag}_spc.png`),
      ]).then(([mov, atk, spc]) => {
        for (const t of [mov, atk, spc]) this.track(t);
        const s = {
          mov: slice(mov, m.mov.size, m.mov.count),
          atk: slice(atk, m.atk.size, m.atk.count),
          spc: slice(spc, m.spc.size, m.spc.count),
        };
        this.loadedSheets.set(set, s);
        return s;
      });
      this.sheetCache.set(set, p);
    }
    return p;
  }

  /** The frames of one pose (side frames as drawn, facing left). */
  frames(sheets: UnitSheets, pose: Pose, facing: Facing): Texture[] {
    const a = this.manifest.anims;
    const pick = (sheet: Texture[], ids: readonly number[]) =>
      ids.flatMap((i) => (sheet[i] ? [sheet[i]] : []));
    switch (pose) {
      case "walk":
        return pick(sheets.mov, a.walk[facing]);
      case "stand":
        return pick(sheets.mov, a.stand[facing]);
      case "weak":
        return pick(sheets.mov, a.weak);
      case "attack":
        return pick(sheets.atk, a.attack[facing]);
      case "guard":
        return pick(sheets.spc, a.guard[facing]);
      case "hurt":
        return pick(sheets.spc, a.hurt);
      case "cheer":
        return pick(sheets.spc, a.cheer);
    }
  }

  faceUrl(officerId: string): string | null {
    const f = this.manifest.officerFaces[officerId];
    return f === undefined ? null : `${BASE}/faces/face_${String(f).padStart(3, "0")}.png`;
  }

  mapUrl(id: number): string {
    return `${BASE}/maps/map_${String(id).padStart(2, "0")}.png`;
  }

  async mapGrid(id: number): Promise<ClassicMapGrid> {
    const r = await fetch(`${BASE}/maps/map_${String(id).padStart(2, "0")}.json`);
    if (!r.ok) throw new Error(`classic map ${id} missing`);
    return (await r.json()) as ClassicMapGrid;
  }

  mapTexture(id: number): Promise<Texture> {
    let p = this.mapCache.get(id);
    if (!p) {
      p = pixelTexture(this.mapUrl(id)).then((t) => this.track(t));
      this.mapCache.set(id, p);
    }
    return p;
  }
}

function applyFilter(source: TextureSource, mode: Filter): void {
  source.style.scaleMode = mode;
  source.style.update();
}

/** The skin, once loaded (null without it). Svelte components read this for portraits. */
export const classic = $state<{ skin: ClassicSkin | null }>({ skin: null });

let loading: Promise<ClassicSkin | null> | null = null;

export function loadClassicSkin(): Promise<ClassicSkin | null> {
  loading ??= (async () => {
    if (!import.meta.env.DEV) return null;
    try {
      const r = await fetch(`${BASE}/manifest.json`);
      if (!r.ok) return null;
      const manifest = (await r.json()) as SkinManifest;
      const atlas = await pixelTexture(`${BASE}/${manifest.terrain.file}`);
      classic.skin = new ClassicSkin(manifest, atlas);
      return classic.skin;
    } catch {
      return null;
    }
  })();
  return loading;
}
