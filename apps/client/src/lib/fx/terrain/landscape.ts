/**
 * Landscapes (ADR 0074): one palette and density table per region of the late
 * Eastern Han north and per season. A battle may name its landscape; without
 * one the painter uses the North China plain in summer. Our own colours, chosen
 * for a muted, real landscape: olive grass, dusty loess, dark woods,
 * grey-brown hills.
 */
import { mixRGB, type RGB } from "./noise.ts";

/**
 * - plain: the Yellow River and the North China plain: flat, dusty loess, silt-yellow water.
 * - huai: the rivers and lakes of Xu province: wetter, greener, reeds.
 * - basin: Nanyang: green, rivers and low hills.
 * - loess: the loess cliffs between Xingyang and Chenggao by the Yellow River.
 * - taihang: steep rock and loess mountains, gorges.
 * - frontier: Liaoxi: sparse grass, bare mountains, steppe.
 */
export type Region = "plain" | "huai" | "basin" | "loess" | "taihang" | "frontier";
export type Season = "spring" | "summer" | "autumn" | "winter";

export interface Landscape {
  readonly region: Region;
  readonly season: Season;
  /** Snow lies on the ground (only when the weather is snow). */
  readonly snow?: boolean;
  /**
   * Fords paint as ice. By default the northern rivers freeze in winter (the
   * plain, the loess country, the 태항 and the frontier; not the Huai or 남양).
   */
  readonly ice?: boolean;
}

export const REGIONS: readonly Region[] = [
  "plain",
  "huai",
  "basin",
  "loess",
  "taihang",
  "frontier",
];
export const SEASONS: readonly Season[] = ["spring", "summer", "autumn", "winter"];

export const DEFAULT_LANDSCAPE: Landscape = { region: "plain", season: "summer" };

/** How leaves look: tree crowns change with the season. */
export type Leaves = "fresh" | "full" | "autumn" | "bare";

export interface Density {
  /** Added to the grass field: more or less grass than the map says. */
  readonly grass: number;
  readonly tufts: number;
  readonly flowers: number;
  readonly shrubs: number;
  readonly rocks: number;
  readonly reeds: number;
  /** Trees by villages and water, and how full forests are. */
  readonly trees: number;
  /** Height of hills and mountains. */
  readonly relief: number;
  /** Share of pines among forest trees. */
  readonly pines: number;
}

export interface Look {
  readonly key: string;
  readonly region: Region;
  readonly season: Season;
  readonly loess: readonly [RGB, RGB, RGB];
  readonly grass: readonly [RGB, RGB, RGB];
  readonly hill: RGB;
  readonly rock: readonly [RGB, RGB];
  readonly forestFloor: RGB;
  readonly water: { readonly deep: RGB; readonly mid: RGB; readonly shallow: RGB };
  readonly bed: RGB;
  readonly ice: RGB;
  readonly yard: RGB;
  readonly pave: RGB;
  readonly street: RGB;
  readonly camp: RGB;
  /** Rammed earth (판축): walls, ramparts, compound walls. */
  readonly earth: RGB;
  readonly crops: { readonly a: RGB; readonly b: RGB; readonly furrow: RGB };
  readonly leaves: Leaves;
  readonly snow: boolean;
  readonly frozen: boolean;
  readonly density: Density;
}

interface RegionBase {
  loess: [RGB, RGB, RGB];
  grass: [RGB, RGB, RGB];
  hill: RGB;
  rock: [RGB, RGB];
  water: { deep: RGB; mid: RGB; shallow: RGB };
  earth: RGB;
  density: Density;
}

const BASE: Record<Region, RegionBase> = {
  plain: {
    loess: [
      [176, 162, 124],
      [162, 148, 112],
      [190, 176, 138],
    ],
    grass: [
      [128, 134, 76],
      [110, 120, 66],
      [146, 146, 86],
    ],
    hill: [156, 132, 88],
    rock: [
      [132, 120, 104],
      [94, 86, 76],
    ],
    water: { deep: [88, 98, 84], mid: [108, 116, 98], shallow: [134, 136, 110] },
    earth: [184, 158, 110],
    density: {
      grass: 0,
      tufts: 1,
      flowers: 0.6,
      shrubs: 0.6,
      rocks: 0.3,
      reeds: 0.7,
      trees: 1,
      relief: 1,
      pines: 0.15,
    },
  },
  huai: {
    loess: [
      [170, 154, 116],
      [156, 142, 106],
      [184, 168, 128],
    ],
    grass: [
      [108, 132, 70],
      [92, 118, 60],
      [128, 146, 82],
    ],
    hill: [138, 128, 82],
    rock: [
      [130, 124, 108],
      [104, 98, 88],
    ],
    water: { deep: [64, 94, 94], mid: [86, 114, 106], shallow: [128, 138, 106] },
    earth: [170, 148, 106],
    density: {
      grass: 0.12,
      tufts: 1.2,
      flowers: 0.8,
      shrubs: 0.8,
      rocks: 0.2,
      reeds: 1.7,
      trees: 1.3,
      relief: 0.8,
      pines: 0.1,
    },
  },
  basin: {
    loess: [
      [174, 156, 116],
      [160, 144, 108],
      [188, 172, 130],
    ],
    grass: [
      [104, 132, 70],
      [88, 116, 60],
      [124, 144, 80],
    ],
    hill: [134, 128, 80],
    rock: [
      [132, 124, 108],
      [106, 98, 88],
    ],
    water: { deep: [68, 100, 102], mid: [90, 118, 110], shallow: [130, 140, 108] },
    earth: [176, 152, 108],
    density: {
      grass: 0.1,
      tufts: 1.1,
      flowers: 0.8,
      shrubs: 0.9,
      rocks: 0.3,
      reeds: 1,
      trees: 1.2,
      relief: 0.9,
      pines: 0.2,
    },
  },
  loess: {
    loess: [
      [198, 172, 120],
      [184, 158, 110],
      [210, 188, 138],
    ],
    grass: [
      [146, 144, 88],
      [128, 130, 78],
      [162, 156, 98],
    ],
    hill: [182, 146, 96],
    rock: [
      [176, 150, 108],
      [140, 116, 84],
    ],
    water: { deep: [118, 108, 78], mid: [140, 128, 92], shallow: [166, 150, 108] },
    earth: [196, 166, 114],
    density: {
      grass: -0.1,
      tufts: 0.6,
      flowers: 0.3,
      shrubs: 0.5,
      rocks: 0.4,
      reeds: 0.4,
      trees: 0.6,
      relief: 1.1,
      pines: 0.1,
    },
  },
  taihang: {
    loess: [
      [176, 158, 118],
      [162, 146, 110],
      [188, 172, 132],
    ],
    grass: [
      [122, 126, 78],
      [106, 112, 68],
      [138, 138, 88],
    ],
    hill: [150, 130, 94],
    rock: [
      [132, 124, 112],
      [98, 92, 84],
    ],
    water: { deep: [72, 96, 102], mid: [94, 114, 112], shallow: [136, 140, 112] },
    earth: [178, 154, 110],
    density: {
      grass: -0.05,
      tufts: 0.8,
      flowers: 0.4,
      shrubs: 1,
      rocks: 1.4,
      reeds: 0.5,
      trees: 0.9,
      relief: 1.4,
      pines: 0.55,
    },
  },
  frontier: {
    loess: [
      [174, 164, 132],
      [160, 150, 120],
      [188, 178, 146],
    ],
    grass: [
      [152, 150, 100],
      [134, 134, 88],
      [168, 162, 110],
    ],
    hill: [160, 140, 100],
    rock: [
      [140, 130, 116],
      [110, 102, 92],
    ],
    water: { deep: [80, 100, 110], mid: [104, 120, 124], shallow: [146, 148, 130] },
    earth: [176, 158, 120],
    density: {
      grass: -0.12,
      tufts: 0.7,
      flowers: 0.3,
      shrubs: 0.3,
      rocks: 0.8,
      reeds: 0.3,
      trees: 0.4,
      relief: 1.1,
      pines: 0.4,
    },
  },
};

interface SeasonShift {
  /** Grass is pulled toward this colour by `grassBy`. */
  grassTo: RGB;
  grassBy: number;
  loessTo: RGB;
  loessBy: number;
  waterTo: RGB;
  waterBy: number;
  crops: { a: RGB; b: RGB; furrow: RGB };
  leaves: Leaves;
  flowers: number;
  grass: number;
}

const SEASON: Record<Season, SeasonShift> = {
  spring: {
    grassTo: [126, 152, 82],
    grassBy: 0.35,
    loessTo: [176, 162, 124],
    loessBy: 0.1,
    waterTo: [110, 130, 120],
    waterBy: 0.1,
    crops: { a: [124, 146, 78], b: [146, 156, 92], furrow: [150, 134, 100] },
    leaves: "fresh",
    flowers: 1.3,
    grass: 0.04,
  },
  summer: {
    grassTo: [110, 124, 66],
    grassBy: 0,
    loessTo: [182, 165, 121],
    loessBy: 0,
    waterTo: [110, 120, 100],
    waterBy: 0,
    crops: { a: [134, 142, 74], b: [188, 168, 96], furrow: [150, 132, 96] },
    leaves: "full",
    flowers: 1,
    grass: 0,
  },
  autumn: {
    grassTo: [178, 152, 88],
    grassBy: 0.5,
    loessTo: [186, 164, 118],
    loessBy: 0.1,
    waterTo: [116, 120, 108],
    waterBy: 0.12,
    crops: { a: [198, 170, 96], b: [168, 138, 88], furrow: [146, 124, 90] },
    leaves: "autumn",
    flowers: 0.3,
    grass: -0.03,
  },
  winter: {
    grassTo: [154, 134, 98],
    grassBy: 0.72,
    loessTo: [170, 158, 132],
    loessBy: 0.25,
    waterTo: [116, 128, 132],
    waterBy: 0.3,
    crops: { a: [148, 128, 98], b: [132, 114, 88], furrow: [116, 100, 80] },
    leaves: "bare",
    flowers: 0,
    grass: -0.06,
  },
};

const cache = new Map<string, Look>();

/** The painter's colours and densities for a landscape (the default without one). */
export function lookOf(l: Landscape | undefined | null): Look {
  const land = l ?? DEFAULT_LANDSCAPE;
  const frozen =
    land.ice ?? (land.season === "winter" && land.region !== "huai" && land.region !== "basin");
  const key = `${land.region}:${land.season}:${land.snow ? 1 : 0}${frozen ? 1 : 0}`;
  const hit = cache.get(key);
  if (hit) return hit;
  const b = BASE[land.region];
  const s = SEASON[land.season];
  const g = (c: RGB) => mixRGB(c, s.grassTo, s.grassBy);
  const lo = (c: RGB) => mixRGB(c, s.loessTo, s.loessBy);
  const wa = (c: RGB) => mixRGB(c, s.waterTo, s.waterBy);
  const loess: [RGB, RGB, RGB] = [lo(b.loess[0]), lo(b.loess[1]), lo(b.loess[2])];
  const look: Look = {
    key,
    region: land.region,
    season: land.season,
    loess,
    grass: [g(b.grass[0]), g(b.grass[1]), g(b.grass[2])],
    hill: mixRGB(b.hill, s.grassTo, s.grassBy * 0.25),
    rock: b.rock,
    forestFloor: mixRGB(g(b.grass[1]), [60, 58, 40], 0.45),
    water: { deep: wa(b.water.deep), mid: wa(b.water.mid), shallow: wa(b.water.shallow) },
    bed: mixRGB(loess[1], [120, 124, 112], 0.45),
    ice: [214, 224, 226],
    yard: mixRGB(loess[0], b.earth, 0.5),
    pave: mixRGB([166, 158, 140], loess[0], 0.42),
    street: mixRGB(loess[1], b.earth, 0.3),
    camp: mixRGB(loess[1], [150, 134, 104], 0.4),
    earth: b.earth,
    crops: s.crops,
    leaves: s.leaves,
    snow: !!land.snow,
    frozen,
    density: {
      ...b.density,
      grass: b.density.grass + s.grass,
      flowers: b.density.flowers * s.flowers,
    },
  };
  cache.set(key, look);
  return look;
}
