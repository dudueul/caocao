/**
 * The twelve terrains (ADR 0023) and the P0 family × terrain affinity table:
 * movement cost (null = impassable) and an effect‰ on ATK and DEF.
 * Starting tuning, our own numbers; see docs/remake/RULES.md §4.
 */
import type { Affinity, TerrainDef, TerrainId } from "@tk/rules";

export const TERRAIN_IDS: readonly TerrainId[] = [
  "plain",
  "grass",
  "forest",
  "hills",
  "mountain",
  "ford",
  "water",
  "bridge",
  "village",
  "fort",
  "wall",
  "camp",
];

function terrain(id: TerrainId, name: string, cover: number, heal: number): TerrainDef {
  return { id, name, cover, heal };
}

export const TERRAINS: Readonly<Record<TerrainId, TerrainDef>> = {
  plain: terrain("plain", "평지", 0, 0),
  grass: terrain("grass", "초원", 0, 0),
  forest: terrain("forest", "숲", 150, 0),
  hills: terrain("hills", "언덕", 100, 0),
  mountain: terrain("mountain", "산", 100, 0),
  ford: terrain("ford", "여울", 0, 0),
  water: terrain("water", "강", 0, 0),
  bridge: terrain("bridge", "다리", 0, 0),
  village: terrain("village", "마을", 100, 100),
  fort: terrain("fort", "성채", 200, 100),
  wall: terrain("wall", "성벽", 0, 0),
  camp: terrain("camp", "진영", 50, 50),
};

/** One character per terrain, for maps written as rows of text. */
export const TERRAIN_CODES: Readonly<Record<string, TerrainId>> = {
  ".": "plain",
  g: "grass",
  f: "forest",
  h: "hills",
  m: "mountain",
  d: "ford",
  w: "water",
  b: "bridge",
  v: "village",
  F: "fort",
  "#": "wall",
  c: "camp",
};

/** Columns, in TERRAIN_IDS order: plain grass forest hills mountain ford water bridge village fort wall camp. */
type Row = readonly [number | null, number][];

function row(cells: Row): Record<TerrainId, Affinity> {
  if (cells.length !== TERRAIN_IDS.length) throw new Error("affinity row needs 12 cells");
  const out = {} as Record<TerrainId, Affinity>;
  TERRAIN_IDS.forEach((id, i) => {
    const [move, effect] = cells[i] as [number | null, number];
    out[id] = { move, effect };
  });
  return out;
}

const X = null;

// biome-ignore format: one row per family reads as a table
export const AFFINITY: Readonly<Record<string, Readonly<Record<TerrainId, Affinity>>>> = {
  //                 plain        grass        forest      hills        mountain     ford        water    bridge       village      fort         wall     camp
  infantry:      row([[1, 1000], [1, 1000], [2, 1000], [2, 1050], [3, 1000], [2, 900], [X, 1000], [1, 1000], [1, 1000], [1, 1100], [X, 1000], [1, 1000]]),
  archer:        row([[1, 1000], [1, 1000], [2, 900], [2, 1100], [3, 1050], [2, 800], [X, 1000], [1, 1000], [1, 1000], [1, 1100], [X, 1000], [1, 1000]]),
  cavalry:       row([[1, 1100], [1, 1100], [2, 900], [2, 1000], [X, 1000], [3, 800], [X, 1000], [1, 1000], [1, 1000], [1, 1000], [X, 1000], [1, 1000]]),
  strategist:    row([[1, 1000], [1, 1000], [2, 1000], [2, 1000], [3, 1000], [2, 900], [X, 1000], [1, 1000], [1, 1050], [1, 1100], [X, 1000], [1, 1050]]),
  martialArtist: row([[1, 1000], [1, 1000], [1, 1050], [1, 1050], [2, 1000], [2, 900], [X, 1000], [1, 1000], [1, 1000], [1, 1000], [X, 1000], [1, 1000]]),
  catapult:      row([[1, 1000], [1, 1000], [3, 800], [3, 1000], [X, 1000], [3, 700], [X, 1000], [1, 1000], [1, 1000], [1, 1200], [X, 1000], [1, 1000]]),
  // Gates never move; a gate set in a fort or wall line stands firmer (ADR 0069).
  structure:     row([[1, 1000], [1, 1000], [1, 1000], [1, 1000], [1, 1000], [1, 1000], [X, 1000], [1, 1000], [1, 1000], [1, 1200], [1, 1200], [1, 1000]]),
};
