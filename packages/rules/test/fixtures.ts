import {
  type Affinity,
  type BattleSetup,
  type ClassDef,
  DEFAULT_TUNING,
  type FamilyId,
  type Offset,
  type Ruleset,
  type Stats,
  type TerrainId,
  triangleRelations,
  type UnitSetup,
} from "../src/index.ts";

export const ADJACENT: Offset[] = [
  { dx: 0, dy: -1 },
  { dx: 1, dy: 0 },
  { dx: 0, dy: 1 },
  { dx: -1, dy: 0 },
];
/** Distance-2 ring plus diagonals: archers can't hit adjacent tiles. */
export const BOW: Offset[] = [
  { dx: 0, dy: -2 },
  { dx: 2, dy: 0 },
  { dx: 0, dy: 2 },
  { dx: -2, dy: 0 },
  { dx: 1, dy: 1 },
  { dx: 1, dy: -1 },
  { dx: -1, dy: 1 },
  { dx: -1, dy: -1 },
];
export const SELF_AND_ADJACENT: Offset[] = [{ dx: 0, dy: 0 }, ...ADJACENT];
const RING3: Offset[] = [];
for (let dx = -3; dx <= 3; dx++)
  for (let dy = -3; dy <= 3; dy++) {
    const d = Math.abs(dx) + Math.abs(dy);
    if (d >= 2 && d <= 3) RING3.push({ dx, dy });
  }

function cls(id: string, family: FamilyId, o: Partial<ClassDef>): ClassDef {
  return {
    id,
    name: id,
    family,
    tier: 1,
    hp: 150,
    mp: 0,
    move: 4,
    atk: 40,
    def: 40,
    range: ADJACENT,
    doubleAttackGap: 20,
    provokesCounter: true,
    skirmisher: false,
    stratagems: [],
    ...o,
  };
}

export const CLASSES: Record<string, ClassDef> = {
  infantry: cls("infantry", "infantry", { def: 50 }),
  archer: cls("archer", "archer", { range: BOW, hp: 120 }),
  cavalry: cls("cavalry", "cavalry", {
    move: 6,
    atk: 60,
    skirmisher: true,
  }),
  strategist: cls("strategist", "strategist", {
    hp: 110,
    mp: 20,
    atk: 20,
    def: 25,
    stratagems: ["fire", "heal", "confuse"],
  }),
  martialArtist: cls("martialArtist", "martialArtist", { doubleAttackGap: 10, atk: 45 }),
  catapult: cls("catapult", "catapult", {
    range: RING3,
    move: 3,
    provokesCounter: false,
  }),
};

const TERRAINS: TerrainId[] = [
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

function affinityRow(overrides: Partial<Record<TerrainId, Affinity>>): Record<TerrainId, Affinity> {
  const row = {} as Record<TerrainId, Affinity>;
  for (const t of TERRAINS) row[t] = { move: 1, effect: 1000 };
  row.forest = { move: 2, effect: 1000 };
  row.hills = { move: 2, effect: 1000 };
  row.mountain = { move: 3, effect: 1000 };
  row.ford = { move: 2, effect: 1000 };
  row.water = { move: null, effect: 1000 };
  row.wall = { move: null, effect: 1000 };
  return { ...row, ...overrides };
}

export const RULES: Ruleset = {
  classes: CLASSES,
  terrains: {
    plain: { id: "plain", name: "plain", cover: 0, heal: 0 },
    grass: { id: "grass", name: "grass", cover: 0, heal: 0 },
    forest: { id: "forest", name: "forest", cover: 150, heal: 0 },
    hills: { id: "hills", name: "hills", cover: 100, heal: 0 },
    mountain: { id: "mountain", name: "mountain", cover: 100, heal: 0 },
    ford: { id: "ford", name: "ford", cover: 0, heal: 0 },
    water: { id: "water", name: "water", cover: 0, heal: 0 },
    bridge: { id: "bridge", name: "bridge", cover: 0, heal: 0 },
    village: { id: "village", name: "village", cover: 100, heal: 100 },
    fort: { id: "fort", name: "fort", cover: 200, heal: 100 },
    wall: { id: "wall", name: "wall", cover: 0, heal: 0 },
    camp: { id: "camp", name: "camp", cover: 50, heal: 50 },
  },
  affinity: {
    infantry: affinityRow({}),
    archer: affinityRow({ fort: { move: 1, effect: 1100 } }),
    cavalry: affinityRow({
      plain: { move: 1, effect: 1100 },
      forest: { move: 3, effect: 900 },
      mountain: { move: null, effect: 1000 },
    }),
    strategist: affinityRow({}),
    martialArtist: affinityRow({}),
    catapult: affinityRow({ mountain: { move: null, effect: 1000 } }),
  },
  stratagems: {
    fire: {
      id: "fire",
      name: "fire",
      school: "fire",
      mp: 6,
      range: [...ADJACENT, ...BOW],
      area: [{ dx: 0, dy: 0 }],
      effect: "damage",
      targets: "enemies",
      power: 30,
      terrain: { forest: 1200, grass: 1200, ford: 600, water: 600 },
      weather: { sunny: 1100, rain: 500 },
      windfall: "power",
      mishap: "backfire",
    },
    heal: {
      id: "heal",
      name: "heal",
      school: "support",
      mp: 5,
      range: SELF_AND_ADJACENT,
      area: [{ dx: 0, dy: 0 }],
      effect: "heal",
      targets: "allies",
      power: 40,
      windfall: "power",
      mishap: "fizzle",
    },
    confuse: {
      id: "confuse",
      name: "confuse",
      school: "disruption",
      mp: 6,
      range: [...ADJACENT, ...BOW],
      area: [{ dx: 0, dy: 0 }],
      effect: "status",
      targets: "enemies",
      power: 0,
      status: "confusion",
      statusRounds: 1,
      windfall: "refund",
      mishap: "fizzle",
    },
  },
  relations: triangleRelations(
    { infantry: "infantry", archer: "archer", cavalry: "cavalry", catapult: "archer" },
    200,
  ),
  tuning: DEFAULT_TUNING,
};

const CODE: Record<string, TerrainId> = {
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

export function makeMap(rows: string[]): BattleSetup["map"] {
  const tiles: TerrainId[] = [];
  for (const row of rows)
    for (const ch of row) {
      const t = CODE[ch];
      if (!t) throw new Error(`bad map char ${ch}`);
      tiles.push(t);
    }
  return { width: rows[0]?.length ?? 0, height: rows.length, tiles };
}

export const AVERAGE: Stats = { martial: 50, command: 50, intellect: 50, agility: 50, fortune: 50 };

export function unit(
  id: string,
  side: number,
  classId: string,
  x: number,
  y: number,
  stats: Partial<Stats> = {},
  extra: Partial<UnitSetup> = {},
): UnitSetup {
  return { id, side, name: id, classId, level: 1, stats: { ...AVERAGE, ...stats }, x, y, ...extra };
}

export function setup(
  rows: string[],
  units: UnitSetup[],
  over: Partial<BattleSetup> = {},
): BattleSetup {
  return {
    map: makeMap(rows),
    weather: "cloudy",
    policy: "alternating",
    roundLimit: 30,
    seed: "test",
    units,
    ...over,
  };
}

/** A mixed 6-vs-6 skirmish on varied terrain, for soak and golden tests. */
export function skirmish(seed: string, policy: BattleSetup["policy"]): BattleSetup {
  const rows = [
    "..gg..ff....",
    ".fgg..ff.hh.",
    "..v...dd..h.",
    "......dd....",
    ".hh...bb..F.",
    ".hh...dd..c.",
    "..ff..dd....",
    "..ff........",
  ];
  const a = ["infantry", "archer", "cavalry", "strategist", "martialArtist", "catapult"];
  const units: UnitSetup[] = [];
  a.forEach((c, i) => {
    units.push(
      unit(`a${i}`, 0, c, 0 + (i % 2), 1 + i, { agility: 45 + i * 3, fortune: 40 + i * 5 }),
    );
    units.push(
      unit(`b${i}`, 1, c, 11 - (i % 2), 1 + i, { agility: 50 - i * 2, fortune: 60 - i * 4 }),
    );
  });
  return setup(rows, units, { seed, policy, roundLimit: 40 });
}
