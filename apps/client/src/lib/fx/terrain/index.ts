/**
 * Terrain painting (ADR 0044, ADR 0074). Our own terrain is painted as one
 * landscape whose tiles join with their neighbours (`paint.ts`); the owner's
 * local classic skin keeps its tile-and-splat path (`classic.ts`).
 */
import type { BattleMap } from "@tk/rules";
import type { Container } from "pixi.js";
import { paintClassic, type TerrainSet } from "./classic.ts";
import { type PaintOptions, paintLandscape } from "./paint.ts";

export { skinTerrain, type TerrainSet } from "./classic.ts";
export {
  DEFAULT_LANDSCAPE,
  type Landscape,
  lookOf,
  REGIONS,
  type Region,
  SEASONS,
  type Season,
} from "./landscape.ts";
export { type PaintOptions, quantizePx, renderLandscape, terrainStats } from "./paint.ts";

const OURS: TerrainSet = { size: 64, variants: new Map(), procedural: true };

/** Our own procedural terrain (no per-tile art: the painter works from the map). */
export function ourTerrain(): TerrainSet {
  return OURS;
}

/**
 * Lays the map into `ground`, `water` (under the shimmer filter) and `edges`.
 * Our own terrain paints the water into `water` and the land over it into
 * `edges`; the classic skin lays tiles and splats as before.
 */
export function paintTerrain(
  map: BattleMap,
  tile: number,
  set: TerrainSet,
  layers: { ground: Container; water: Container; edges: Container },
  opts: PaintOptions = {},
): void {
  if (set.procedural) paintLandscape(map, tile, set.variants, layers, opts);
  else paintClassic(map, tile, set, layers);
}
