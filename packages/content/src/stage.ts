/**
 * Staged scenes (ADR 0063) and the walkable camp (ADR 0064): stage tiles, the
 * shared stages, and small helpers that keep scene scripts short to write.
 * Every map, line and layout here is our own (ADR 0002).
 */
import type { Cell, TerrainId } from "@tk/rules";
import type {
  Actor,
  ActPose,
  Backdrop,
  CampGround,
  Cmd,
  Cond,
  Emote,
  Facing4,
  Line,
  Mood,
  StageDef,
  StageFx,
} from "./story.ts";
import { TERRAIN_CODES } from "./terrain.ts";

/** Indoor floors exist only on stages; battles never use them. */
export type FloorId = "boards" | "paving" | "carpet";
export type StageTile = TerrainId | FloorId;

/** One character per stage tile: the battle terrain codes plus three floors. */
export const STAGE_CODES: Readonly<Record<string, StageTile>> = {
  ...TERRAIN_CODES,
  _: "boards",
  "=": "paving",
  r: "carpet",
};

/** Tiles nobody walks on in a scene. */
export const STAGE_BLOCKED: ReadonlySet<StageTile> = new Set<StageTile>([
  "water",
  "wall",
  "mountain",
]);

export interface StageGrid {
  readonly width: number;
  readonly height: number;
  readonly tiles: readonly StageTile[];
}

export function parseStage(def: StageDef): StageGrid {
  const width = def.rows[0]?.length ?? 0;
  const tiles: StageTile[] = [];
  def.rows.forEach((row, y) => {
    if (row.length !== width) throw new Error(`stage ${def.id}: row ${y} is not ${width} wide`);
    for (const ch of row) {
      const t = STAGE_CODES[ch];
      if (t === undefined) throw new Error(`stage ${def.id}: unknown tile code "${ch}"`);
      tiles.push(t);
    }
  });
  return { width, height: def.rows.length, tiles };
}

/** Props that block walking unless they say otherwise. */
export const SOLID_PROPS: ReadonlySet<string> = new Set([
  "throne",
  "table",
  "pillar",
  "tent",
  "screen",
  "cart",
  "rack",
  "well",
  "fence",
  "statue",
  "bed",
  "brazier",
  "chest",
]);

/** Can an actor stand on this tile of the stage (ignoring other actors)? */
export function walkable(def: StageDef, grid: StageGrid, c: Cell): boolean {
  if (c.x < 0 || c.y < 0 || c.x >= grid.width || c.y >= grid.height) return false;
  if (STAGE_BLOCKED.has(grid.tiles[c.y * grid.width + c.x] as StageTile)) return false;
  return !(def.props ?? []).some(
    (p) => p.at.x === c.x && p.at.y === c.y && (p.solid ?? SOLID_PROPS.has(p.kind)),
  );
}

// ---------------------------------------------------------------- script helpers

type At = [number, number];
const cell = ([x, y]: At): Cell => ({ x, y });

export const say = (who: string, text: string, mood?: Mood, extra: Partial<Line> = {}): Cmd => ({
  do: "say",
  line: { who, text, ...(mood ? { mood } : {}), ...extra },
});
/** Narration: a line with no speaker. */
export const tell = (text: string): Cmd => ({ do: "say", line: { text } });
export const walk = (who: string, to: At, run = false): Cmd => ({
  do: "walk",
  who,
  to: cell(to),
  ...(run ? { run } : {}),
});
export const enter = (who: string, from: At, to: At, run = false): Cmd => ({
  do: "enter",
  who,
  from: cell(from),
  to: cell(to),
  ...(run ? { run } : {}),
});
export const leave = (who: string, to: At, run = false): Cmd => ({
  do: "leave",
  who,
  to: cell(to),
  ...(run ? { run } : {}),
});
export const place = (who: string, at: At, facing?: Facing4): Cmd => ({
  do: "place",
  who,
  at: cell(at),
  ...(facing ? { facing } : {}),
});
export const remove = (who: string): Cmd => ({ do: "remove", who });
export const face = (who: string, to: Facing4 | string): Cmd => ({ do: "face", who, to });
export const emote = (who: string, e: Emote): Cmd => ({ do: "emote", who, emote: e });
export const act = (who: string, pose: ActPose): Cmd => ({ do: "act", who, pose });
export const strike = (who: string, target: string, fells = false): Cmd => ({
  do: "strike",
  who,
  target,
  ...(fells ? { fells } : {}),
});
export const camera = (at: At | string, zoom?: number): Cmd => ({
  do: "camera",
  at: typeof at === "string" ? at : cell(at),
  ...(zoom ? { zoom } : {}),
});
export const pause = (ms: number): Cmd => ({ do: "wait", ms });
export const sound = (sfx: string): Cmd => ({ do: "sound", sfx });
export const fx = (kind: StageFx, at?: At | string): Cmd => ({
  do: "fx",
  fx: kind,
  ...(at === undefined ? {} : { at: typeof at === "string" ? at : cell(at) }),
});
export const fire = (...cells: At[]): Cmd => ({ do: "fire", cells: cells.map(cell) });
/** Runs its steps together and waits for all of them. */
export const all = (...cmds: Cmd[]): Cmd => ({ do: "all", cmds });
/** The same steps, only when `cond` holds. */
export const only = (cond: Cond, ...cmds: Cmd[]): Cmd => ({ do: "all", cmds, if: cond });
export const actor = (
  id: string,
  side: number,
  at?: At,
  facing?: Facing4,
  extra: Partial<Actor> = {},
): Actor => ({
  id,
  side,
  ...(at ? { at: cell(at) } : {}),
  ...(facing ? { facing } : {}),
  ...extra,
});

// ---------------------------------------------------------------- shared stages

/** A palace hall: carpet up the middle to the dais, pillars either side. */
export const HALL: StageDef = {
  id: "hall",
  light: "day",
  rows: [
    "##############",
    "#____rrrr____#",
    "#____rrrr____#",
    "#____rrrr____#",
    "#____rrrr____#",
    "#____rrrr____#",
    "#____rrrr____#",
    "#____rrrr____#",
    "######rr######",
  ],
  props: [
    { kind: "throne", at: { x: 6, y: 1 } },
    { kind: "screen", at: { x: 7, y: 1 } },
    { kind: "pillar", at: { x: 3, y: 2 } },
    { kind: "pillar", at: { x: 10, y: 2 } },
    { kind: "pillar", at: { x: 3, y: 5 } },
    { kind: "pillar", at: { x: 10, y: 5 } },
    { kind: "lantern", at: { x: 1, y: 1 } },
    { kind: "lantern", at: { x: 12, y: 1 } },
  ],
};

/** A command tent in the field: a war table, banners at the door. */
export const COMMAND_TENT: StageDef = {
  id: "command-tent",
  light: "day",
  rows: [
    "gggggggggggggg",
    "g####____####g",
    "g#__________#g",
    "g#__________#g",
    "g#__________#g",
    "g#__________#g",
    "g####____####g",
    "g............g",
    "gggggggggggggg",
  ],
  props: [
    { kind: "table", at: { x: 6, y: 3 } },
    { kind: "table", at: { x: 7, y: 3 } },
    { kind: "rack", at: { x: 2, y: 2 } },
    { kind: "chest", at: { x: 11, y: 2 } },
    { kind: "brazier", at: { x: 2, y: 5 } },
    { kind: "brazier", at: { x: 11, y: 5 } },
    { kind: "banner", at: { x: 5, y: 7 } },
    { kind: "banner", at: { x: 8, y: 7 } },
  ],
};

/** A town street before a gate in the wall. */
export const STREET: StageDef = {
  id: "street",
  light: "day",
  rows: [
    "######==######",
    "vv....==....vv",
    "vv....==....vv",
    "......==......",
    "==============",
    "......==......",
    "vv....==....vv",
    "vv....==....vv",
    "......==......",
  ],
  props: [
    { kind: "gate", at: { x: 6, y: 0 }, solid: false },
    { kind: "gate", at: { x: 7, y: 0 }, solid: false },
    { kind: "well", at: { x: 3, y: 5 } },
    { kind: "cart", at: { x: 10, y: 3 } },
    { kind: "lantern", at: { x: 5, y: 3 } },
    { kind: "lantern", at: { x: 8, y: 5 } },
  ],
};

/** Open country with a road and a wood. */
export const FIELD: StageDef = {
  id: "field",
  light: "day",
  rows: [
    "ggggffgggggggg",
    "gggffff..ggggg",
    "gg..ff.....ggg",
    "g.............",
    "..............",
    "........hh....",
    "g......hhh..gg",
    "gg..........gg",
    "gggg.....ggggg",
  ],
  props: [
    { kind: "banner", at: { x: 3, y: 4 } },
    { kind: "horse", at: { x: 11, y: 3 }, solid: true },
  ],
};

/** A river bank with a ford and a bridge. */
export const RIVERBANK: StageDef = {
  id: "riverbank",
  light: "day",
  rows: [
    "gggg..wwww..gg",
    "ggg...wwww...g",
    "gg....wwww....",
    "......bbbb....",
    "......wwww....",
    "g.....dwwd...g",
    "gg....wwww..gg",
    "ggg...wwww.ggg",
    "gggg..wwww.ggg",
  ],
  props: [{ kind: "cart", at: { x: 2, y: 4 } }],
};

/** An army camp: tents, a fire, horses. */
export const CAMPSITE: StageDef = {
  id: "campsite",
  light: "day",
  rows: [
    "gggggggggggggg",
    "gg..........gg",
    "g............g",
    "g............g",
    "g............g",
    "g............g",
    "g............g",
    "gg..........gg",
    "gggggggggggggg",
  ],
  props: [
    { kind: "tent", at: { x: 2, y: 2 } },
    { kind: "tent", at: { x: 11, y: 2 } },
    { kind: "tent", at: { x: 2, y: 6 } },
    { kind: "tent", at: { x: 11, y: 6 } },
    { kind: "brazier", at: { x: 6, y: 4 } },
    { kind: "banner", at: { x: 6, y: 1 } },
    { kind: "horse", at: { x: 12, y: 4 }, solid: true },
    { kind: "grain", at: { x: 1, y: 4 } },
  ],
};

/** The stage a scene uses when it has none of its own, by its backdrop. */
export const STAGE_FOR: Readonly<Record<Backdrop, StageDef>> = {
  court: HALL,
  camp: COMMAND_TENT,
  river: RIVERBANK,
  night: { ...STREET, id: "street-night", light: "night" },
  field: FIELD,
  fire: { ...STREET, id: "street-fire", light: "night" },
  dawn: { ...FIELD, id: "field-dawn", light: "dusk" },
};

/** Where speakers stand when a scene is staged automatically: two rows facing each other. */
const AUTO_SPOTS: readonly At[] = [
  [6, 4],
  [7, 4],
  [5, 5],
  [8, 5],
  [4, 6],
  [9, 6],
  [6, 6],
  [7, 6],
  [3, 5],
  [10, 5],
];

/**
 * A plain staging for a scene written as lines: its speakers stand in two
 * groups facing each other and speak in turn, the speaker stepping forward.
 */
export function staged(
  backdrop: Backdrop,
  lines: readonly Line[],
): { stage: StageDef; cast: Actor[]; script: Cmd[] } {
  const stage = STAGE_FOR[backdrop];
  const grid = parseStage(stage);
  const speakers: { id: string; side: number }[] = [];
  for (const l of lines)
    if (l.who && !speakers.some((s) => s.id === l.who))
      speakers.push({ id: l.who, side: l.side ?? 0 });
  const spots = AUTO_SPOTS.map(cell).filter((c) => walkable(stage, grid, c));
  const cast = speakers.map((s, i) => {
    const at = spots[i % spots.length] as Cell;
    return actor(s.id, s.side, [at.x, at.y], i % 2 === 0 ? "right" : "left");
  });
  const script: Cmd[] = lines.map((line) => ({ do: "say", line }) as Cmd);
  return { stage, cast, script };
}

// ---------------------------------------------------------------- the camp

/**
 * The walkable camp (ADR 0064): the merchant's stall, the armoury, the
 * records tent and the command tent around a fire; officers loiter near it.
 */
export const CAMP_GROUND: CampGround = {
  stage: {
    id: "camp-ground",
    light: "day",
    rows: [
      "gggggfffgggggggg",
      "gg............gg",
      "g..............g",
      "g..............g",
      "g..............g",
      "g..............g",
      "g..............g",
      "g..............g",
      "gg............gg",
      "gggggg....gggggg",
    ],
    props: [
      { kind: "tent", at: { x: 3, y: 2 }, side: 0 },
      { kind: "tent", at: { x: 12, y: 2 }, side: 0 },
      { kind: "tent", at: { x: 3, y: 6 }, side: 0 },
      { kind: "tent", at: { x: 12, y: 6 }, side: 0 },
      { kind: "banner", at: { x: 7, y: 1 } },
      { kind: "banner", at: { x: 8, y: 1 } },
      { kind: "brazier", at: { x: 7, y: 5 } },
      { kind: "rack", at: { x: 10, y: 2 } },
      { kind: "grain", at: { x: 1, y: 4 } },
      { kind: "cart", at: { x: 14, y: 4 } },
      { kind: "horse", at: { x: 14, y: 7 }, solid: true },
      { kind: "chest", at: { x: 5, y: 6 } },
    ],
  },
  start: { x: 7, y: 8 },
  stations: [
    { kind: "shop", at: { x: 3, y: 3 } },
    { kind: "armoury", at: { x: 12, y: 3 } },
    { kind: "records", at: { x: 3, y: 7 } },
    { kind: "council", at: { x: 12, y: 7 } },
  ],
  spots: [
    { x: 6, y: 4 },
    { x: 9, y: 4 },
    { x: 6, y: 6 },
    { x: 9, y: 6 },
    { x: 5, y: 3 },
    { x: 10, y: 3 },
    { x: 7, y: 3 },
    { x: 8, y: 7 },
    { x: 2, y: 5 },
    { x: 13, y: 5 },
    { x: 5, y: 8 },
    { x: 10, y: 8 },
    { x: 4, y: 4 },
    { x: 11, y: 4 },
    { x: 6, y: 2 },
    { x: 9, y: 2 },
  ],
};

/**
 * What officers say in camp when the camp has nothing of its own for them:
 * a line or two in character. Our own words.
 */
export const CAMP_LINES: Readonly<Record<string, readonly Line[]>> = {
  "xiahou-dun": [
    {
      who: "xiahou-dun",
      text: "맹덕, 다음 싸움에는 나를 앞에 세우시오. 뒤에서 기다리는 건 성미에 맞지 않소.",
    },
  ],
  "xiahou-yuan": [
    {
      who: "xiahou-yuan",
      text: "군량은 제가 챙겨 두었습니다. 사흘 길은 쉬지 않고 달릴 수 있습니다.",
    },
  ],
  "cao-ren": [
    { who: "cao-ren", text: "진영의 목책을 다시 둘렀습니다. 밤에 습격해 와도 버틸 수 있습니다." },
  ],
  "cao-hong": [
    {
      who: "cao-hong",
      mood: "smile",
      text: "형님, 말이 살이 올랐습니다. 이번엔 누구보다 먼저 달려 보이겠습니다!",
    },
  ],
  "xun-yu": [
    { who: "xun-yu", text: "근본을 굳게 하는 자가 천하를 얻습니다. 군량과 백성을 잊지 마십시오." },
  ],
  "yue-jin": [
    { who: "yue-jin", text: "성벽이 높아도 누군가는 먼저 올라야 합니다. 그게 제 몫입니다." },
  ],
  "li-dian": [
    {
      who: "li-dian",
      text: "병서를 다시 읽고 있었습니다. 싸움은 칼보다 머리로 먼저 하는 법이지요.",
    },
  ],
  "yu-jin": [
    {
      who: "yu-jin",
      text: "군율이 서지 않은 군대는 이길 수 없습니다. 오늘도 대열을 맞춰 두었습니다.",
    },
  ],
  "chen-gong": [
    {
      who: "chen-gong",
      mood: "grim",
      text: "연주의 선비들은 공을 지켜보고 있습니다. 칼만으로는 그들의 마음을 얻지 못합니다.",
    },
  ],
  "dian-wei": [
    { who: "dian-wei", text: "주공께서 주무시는 동안은 제가 장막 앞을 지킵니다. 염려 마십시오." },
  ],
  "xu-chu": [
    {
      who: "xu-chu",
      mood: "smile",
      text: "배가 든든하면 힘이 납니다! 주공 곁은 이 허저가 맡겠습니다.",
    },
  ],
  "guo-jia": [
    {
      who: "guo-jia",
      mood: "smile",
      text: "술 한 잔이면 계책이 하나 떠오르지요. 주공, 한 잔 더 하시겠습니까?",
    },
  ],
  "xun-you": [
    {
      who: "xun-you",
      text: "적은 서두르고 우리는 기다립니다. 기다리는 쪽이 이기는 싸움도 있습니다.",
    },
  ],
  "xu-huang": [{ who: "xu-huang", text: "도끼를 갈아 두었습니다. 명령만 내리십시오." }],
  "zhang-liao": [
    { who: "zhang-liao", text: "기병은 바람과 같아야 합니다. 머무르면 죽고, 달리면 삽니다." },
  ],
  "cheng-yu": [
    {
      who: "cheng-yu",
      mood: "grim",
      text: "인정은 때로 독이 됩니다. 주공께서는 마음을 단단히 하십시오.",
    },
  ],
  "lu-bu": [
    { who: "lu-bu", mood: "smile", text: "적토마도 쉬니 몸이 근질거리는군. 다음은 누구의 목이오?" },
  ],
  "cao-cao": [{ who: "cao-cao", text: "천하의 인재가 모이는 곳이 곧 천하의 중심이다." }],
};
