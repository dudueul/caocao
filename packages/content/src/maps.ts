/** Maps are rows of text, one TERRAIN_CODES character per tile, row 0 at the top. */
import type { BattleMap, TerrainId } from "@tk/rules";
import { TERRAIN_CODES } from "./terrain.ts";

export interface MapDef {
  readonly id: string;
  readonly name: string;
  readonly rows: readonly string[];
}

export function parseMap(def: MapDef): BattleMap {
  const width = def.rows[0]?.length ?? 0;
  const tiles: TerrainId[] = [];
  def.rows.forEach((row, y) => {
    if (row.length !== width) throw new Error(`map ${def.id}: row ${y} is not ${width} wide`);
    for (const ch of row) {
      const t = TERRAIN_CODES[ch];
      if (t === undefined) throw new Error(`map ${def.id}: unknown terrain code "${ch}"`);
      tiles.push(t);
    }
  });
  return { width, height: def.rows.length, tiles };
}

/**
 * 오소 (Wuchao) grain depot, 200: a palisaded camp east of a river with one
 * bridge and two fords; grain fields and woods on the western approach.
 */
export const WUCHAO: MapDef = {
  id: "wuchao",
  name: "오소",
  rows: [
    "mmh..ff...w.gggg",
    "mh...ff..gd.g###",
    "h..gg....gw.#ccc",
    "..gg..hh..w.#cFc",
    ".ff...hh..w..ccc",
    ".ff.......b..ccc",
    "....v.....w..ccc",
    "..........w.#ccc",
    ".gg..ff...w.#ccc",
    ".gg..ff..gd.g###",
    "..h......gw.gggg",
    ".hh.......w.gggg",
  ],
};
