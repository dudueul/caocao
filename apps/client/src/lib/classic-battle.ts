/**
 * Sandbox battles on a 1998 map (ADR 0042, local skin only): the exported grid
 * gives our terrain ids per tile, so the rules run on it unchanged. The armies
 * keep their scenario roster and are placed near one anchor per side.
 */
import type { BattleSetup, Cell, Ruleset, TerrainId } from "@tk/rules";
import type { Session } from "./session.svelte.ts";
import { type ClassicMapGrid, classic, loadClassicSkin } from "./skin.svelte.ts";

export interface ClassicBattle {
  map: number;
  name: string;
  /** Where each side gathers, by side number. */
  anchors: readonly Cell[];
}

/** 백마: the raiders start by the north-west watchtower; the defenders hold the south-east camp. */
export const CLASSIC_BATTLES: readonly ClassicBattle[] = [
  {
    map: 16,
    name: "백마 (1998 지도)",
    anchors: [
      { x: 5, y: 3 },
      { x: 16, y: 17 },
    ],
  },
];

/** The scenario's setup moved onto a 1998 map. */
export function onClassicMap(
  setup: BattleSetup,
  grid: ClassicMapGrid,
  battle: ClassicBattle,
  rules: Ruleset,
): BattleSetup {
  const { width: w, height: h } = grid;
  const tiles: TerrainId[] = grid.terrain.slice();
  const taken = new Set<number>();
  const units = setup.units.map((u) => {
    const anchor = battle.anchors[u.side] ?? { x: 0, y: 0 };
    const family = rules.classes[u.classId]?.family;
    const row = family ? rules.affinity[family] : undefined;
    // Nearest free tile the unit can stand on, by distance, then row, then column.
    let best: Cell | null = null;
    let bestScore = Number.POSITIVE_INFINITY;
    for (let y = 0; y < h; y++)
      for (let x = 0; x < w; x++) {
        const t = tiles[y * w + x];
        if (taken.has(y * w + x) || !t || !row || row[t].move === null) continue;
        const score = (Math.abs(x - anchor.x) + Math.abs(y - anchor.y)) * 10000 + y * 100 + x;
        if (score < bestScore) {
          bestScore = score;
          best = { x, y };
        }
      }
    if (!best) throw new Error(`no room for ${u.id} on classic map ${grid.id}`);
    taken.add(best.y * w + best.x);
    return { ...u, x: best.x, y: best.y };
  });
  return { ...setup, map: { width: w, height: h, tiles }, units };
}

/** Loads a 1998 map grid into the session; the map id, or null without the skin or the file. */
export async function prepareClassicMap(
  session: Session,
  id: number | null,
): Promise<number | null> {
  const skin = classic.skin ?? (await loadClassicSkin());
  if (id === null || !skin || !CLASSIC_BATTLES.some((b) => b.map === id)) return null;
  if (!session.classicGrids.has(id)) {
    try {
      session.classicGrids.set(id, await skin.mapGrid(id));
    } catch {
      return null;
    }
  }
  return id;
}
