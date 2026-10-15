/**
 * P1 Arena content (ADR 0039, ADR 0041): the draft pool, the mirrored map with
 * six deploy tiles per seat, and the battle setup built from a finished draft.
 */
import type { BattleSetup, Cell, WeatherKind } from "@tk/rules";
import { type MapDef, parseMap } from "./maps.ts";
import { OFFICERS } from "./officers.ts";

export interface PoolEntry {
  /** Entry id; the officer id, which is also the unit id in battle. */
  readonly id: string;
  readonly classId: string;
}

/** Sixteen officers, each with a fixed tier-1 class: 3 + 3 + 3 + 3 + 2 + 2. */
export const ARENA_POOL: readonly PoolEntry[] = [
  { id: "lu-bu", classId: "cavalry" },
  { id: "guan-yu", classId: "cavalry" },
  { id: "zhao-yun", classId: "cavalry" },
  { id: "zhang-fei", classId: "infantry" },
  { id: "xiahou-dun", classId: "infantry" },
  { id: "gan-ning", classId: "infantry" },
  { id: "huang-zhong", classId: "archer" },
  { id: "taishi-ci", classId: "archer" },
  { id: "xiahou-yuan", classId: "archer" },
  { id: "zhuge-liang", classId: "strategist" },
  { id: "zhou-yu", classId: "strategist" },
  { id: "guo-jia", classId: "strategist" },
  { id: "xu-chu", classId: "martialArtist" },
  { id: "dian-wei", classId: "martialArtist" },
  { id: "liu-ye", classId: "catapult" },
  { id: "ma-jun", classId: "catapult" },
];

export const ARENA_LEVEL = 20;
export const ARENA_ROUND_LIMIT = 20;
export const ARMY_SIZE = 6;

/** Snake draft order by seat: seat B (1) picks first (ADR 0039, ADR 0041). */
export const DRAFT_ORDER: readonly (0 | 1)[] = [1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1];

/** A 14 × 12 field, mirrored left to right: a stream with fords and a bridge, forts by each base. */
export const ARENA_FIELD: MapDef = {
  id: "arena-field",
  name: "회전장",
  rows: [
    "mmh..ffff..hmm",
    "mh...fggf...hm",
    "....ggddgg....",
    ".F...hwwh...F.",
    "....hhwwhh....",
    "......bb......",
    "......bb......",
    "..ff..ww..ff..",
    "..ff.gwwg.ff..",
    "v...ggddgg...v",
    "mh....gg....hm",
    "mmh........hmm",
  ],
};

/** Deploy tiles by seat, in slot order; seat B's are seat A's mirrored. */
export const DEPLOY_SLOTS: readonly [readonly Cell[], readonly Cell[]] = (() => {
  const a: Cell[] = [
    { x: 2, y: 3 },
    { x: 1, y: 4 },
    { x: 2, y: 4 },
    { x: 1, y: 5 },
    { x: 2, y: 6 },
    { x: 1, y: 7 },
  ];
  const width = ARENA_FIELD.rows[0]?.length ?? 0;
  return [a, a.map((c) => ({ x: width - 1 - c.x, y: c.y }))];
})();

export function poolEntry(id: string): PoolEntry | undefined {
  return ARENA_POOL.find((e) => e.id === id);
}

export interface ArenaSetupOptions {
  seed: string;
  /** Each seat's six entry ids in deploy-slot order. */
  armies: readonly [readonly string[], readonly string[]];
  weather?: WeatherKind;
}

/** The battle for a finished draft: seat = side, alternating activations (ADR 0003). */
export function arenaSetup(o: ArenaSetupOptions): BattleSetup {
  const units: BattleSetup["units"] = [];
  o.armies.forEach((army, seat) => {
    if (army.length !== ARMY_SIZE) throw new Error(`seat ${seat} has ${army.length} officers`);
    army.forEach((id, slot) => {
      const entry = poolEntry(id);
      const officer = OFFICERS[id];
      const cell = DEPLOY_SLOTS[seat as 0 | 1][slot];
      if (!entry || !officer || !cell) throw new Error(`bad arena entry ${id}`);
      units.push({
        id,
        side: seat,
        name: officer.name,
        classId: entry.classId,
        level: ARENA_LEVEL,
        stats: officer.stats,
        x: cell.x,
        y: cell.y,
      });
    });
  });
  return {
    mode: "arena",
    map: parseMap(ARENA_FIELD),
    weather: o.weather ?? "cloudy",
    policy: "alternating",
    roundLimit: ARENA_ROUND_LIMIT,
    seed: o.seed,
    units,
  };
}
