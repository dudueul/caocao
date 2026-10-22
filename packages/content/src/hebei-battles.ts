/**
 * Chapter 4 (하북 평정) battles (ADR 0067): maps, armies, goals, talk and
 * rewards. 업 has gates to break (ADR 0069), a trench that floods on round 5
 * and 원담 fighting beside us as an ally (ADR 0066); 남피's river ice gives
 * way; 호관 is one gate in a wall across the pass; at 백랑산 the 오환 are
 * caught unready only if 곽가 came (ADR 0068). Lines for officers a carried-over
 * campaign may lack (장료, 여포, 전위, 진궁, 곽가) follow the flags. Our own
 * maps and numbers (ADR 0002).
 */
import type { Cell, Goal, TerrainId, TileChange } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { Cond, StoryBattleDef, StoryUnit } from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
/** One goal naming every unit that must fall (원담 and 원상 at 여양). */
const winBy = (...units: string[]): Goal => ({ side: 0, when: { kind: "defeat", units } });

// What the chapters before decided (ADR 0060); a fresh start assumes history.
/** 여포 was hanged and 장료 serves us (history). */
const notSpared: Cond = { flag: "lubu", not: "spared" };
/** 여포 was spared at 백문루 and serves us; 장료 left for good. */
const spared: Cond = { flag: "lubu", is: "spared" };
/** 서주 was spared and 진궁 stayed with us. */
const mercy: Cond = { flag: "xuzhou", is: "mercy" };
/** 전위 came out of 완성 alive. */
const lived: Cond = { flag: "dianwei", is: "lived" };
/** 곽가 marched to 백랑산 (history): the 오환 are caught unready. */
const went: Cond = { flag: "guojia", is: "went" };
/** 곽가 went home to rest: the march was slow and the 오환 are ready. */
const stayed: Cond = { flag: "guojia", is: "stayed" };

/** An enemy unit; troop templates and gates take an explicit id. */
function foe(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<StoryUnit> = {},
): StoryUnit {
  return { officer, classId, level, side: 1, x, y, ...extra };
}

/** Another lord's man fighting beside us: orange, moved by his own AI (ADR 0066). */
function ally(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> = {},
): StoryUnit {
  return { officer, classId, level, side: 2, x, y, ...extra };
}

/** A work that holds its tile until broken (ADR 0069). */
const gate = (id: string, level: number, x: number, y: number): StoryUnit =>
  foe("gate", "gate", level, x, y, { id });

const hold = { orders: ORDERS.hold };
const guard = { orders: ORDERS.guard };
const heal = { orders: ORDERS.healThenHold };

/** The same terrain on every listed tile. */
const turn = (terrain: TerrainId, xy: readonly [number, number][]): TileChange[] =>
  xy.map(([x, y]) => ({ x, y, terrain }));
const range = (from: number, to: number): number[] =>
  Array.from({ length: to - from + 1 }, (_, i) => from + i);

// ---------------------------------------------------------------- maps

/**
 * 창정 (Cangting): the 황하 between us and 원소, crossed by a bridge (4–5,5)
 * and two fords; 원소's palisaded camp on the east bank, open at three gaps.
 */
const CANGTING_MAP: MapDef = {
  id: "cangting",
  name: "창정",
  rows: [
    "gg..ww.....hh.....",
    "g...dd....hhh.....",
    "....ww...g.....gg.",
    ".hh.ww..ggg.##.###",
    "hhh.ww...g..#cccc#",
    "....bb......ccccc#",
    "....ww......#cccc#",
    "ff..ww..gg..#cccc#",
    "fff.ww..ggg.##.###",
    "....dd...g........",
    "gg..ww.......ff...",
    "ggg.ww......fff...",
  ],
};

/** 여남 (Runan): wooded hills, and 유비's village with its hall (14,6) in the east. */
const RUNAN_MAP: MapDef = {
  id: "runan",
  name: "여남",
  rows: [
    "hhhh..ff.....hhmmm",
    "hh...fff..g...hhmm",
    "h.....f..ggg..ffhm",
    "..g......gg..fff.h",
    ".ggg..hh.....f.vv.",
    "......hhh...vvvvv.",
    "..f....h....vvFvv.",
    ".fff.......ffvvv..",
    "..ff...gg..ff...hh",
    "......ggg......hhh",
    "hh.......ff...hhmm",
    "hhhh....fff..hhmmm",
  ],
};

/**
 * 여양 (Liyang): the walled town on the 황하, its west gate (11,4) and south
 * gate (15,7) open; open fields to the south, and the road east that 원상's
 * relief rides in on.
 */
const LIYANG_MAP: MapDef = {
  id: "liyang",
  name: "여양",
  rows: [
    "wwwwwwwwwwwwwwwwww",
    "..wwwww...wwwwwwww",
    "...........#######",
    "..gg.......#vvvvv#",
    ".ggg..g....vvvFvv#",
    "......gg...#vvvvv#",
    "..hh.......#vvvvv#",
    ".hhh...g...####v##",
    "......ggg.........",
    "..ff.....gg....gg.",
    ".fff....ggg...ggg.",
    "...........hh.....",
  ],
};

/**
 * 업 (Ye): the city in the north-east with its keep (17–18,1–2), the west gate
 * (11,4) and the south gate (15,9); a dry trench rings the walls (x 10 and
 * y 10) with a causeway (b) before each gate; our siege camp in the south-west.
 */
const YE_MAP: MapDef = {
  id: "ye",
  name: "업",
  rows: [
    "hhh..gg...g#########",
    "hh...gg...g#v..v.FF#",
    "h...ff....g#.v...FF#",
    "....ff..g.g#..v..v.#",
    "...gg.....bF..v...v#",
    ".ggg......g#v..F...#",
    "..........g#.vv....#",
    "..hh..ff..g#v....vv#",
    ".hhh..ff..g#..v..v.#",
    "..........g####F####",
    "cccc......gggggbgggg",
    "cccc................",
    "ccFc.......gg....hh.",
    "cccc......ggg...hhh.",
  ],
};

/** The trench round 업's walls, filled from the 장하 on round 5; the causeways stay dry. */
const YE_TRENCH: TileChange[] = turn("water", [
  ...range(0, 10)
    .filter((y) => y !== 4)
    .map((y): [number, number] => [10, y]),
  ...range(11, 19)
    .filter((x) => x !== 15)
    .map((x): [number, number] => [x, 10]),
]);

/**
 * 남피 (Nanpi): the frozen river (x 6–8) with a bridge at each end, and
 * 원담's town on the far bank, its wall open at three gaps.
 */
const NANPI_MAP: MapDef = {
  id: "nanpi",
  name: "남피",
  rows: [
    "hh....ddd....gg...",
    "h.....ddd...######",
    "..gg..bbb...#vvvvv",
    ".ggg..ddd...vvvvvv",
    "......ddd...#vvvvv",
    "..ff..ddd...#vvFvv",
    ".fff..ddd.....vvvv",
    "......ddd...#vvvvv",
    "..hh..ddd...#vvvvv",
    ".hhh..bbb...vvvvvv",
    "......ddd...######",
    "gg....ddd....gg...",
  ],
};

/** The river ice gives way: every ford tile of the river turns to water; the bridges hold. */
const NANPI_THAW: TileChange[] = turn(
  "water",
  range(0, 11)
    .filter((y) => y !== 2 && y !== 9)
    .flatMap((y) => [6, 7, 8].map((x): [number, number] => [x, y])),
);

/**
 * 호관 (Huguan): the pass through the 태항 mountains, shut by a wall (x 12)
 * from ridge to ridge with one gate (12,5); behind it two towers (13,3) and
 * (13,7) and 고간's keep.
 */
const HUGUAN_MAP: MapDef = {
  id: "huguan",
  name: "호관",
  rows: [
    "hhhmmmmmmmmm#mmmmm",
    "hh..hhmmmmmh#hmmmm",
    "...g..hhmmhh#..hmm",
    "..ggg...hhh.#F...h",
    "...g.......h#.FF..",
    "......f.....F.FF..",
    "..ff.ff....h#.....",
    ".fff....hhh.#F...h",
    "......hhmmhh#..hmm",
    "..gg.hhmmmmh#hmmmm",
    "hh..hhmmmmmm#mmmmm",
    "hhhhmmmmmmmm#mmmmm",
  ],
};

/** 백랑산 (Bailang): our band on the hill to the west; the steppe and the 오환 camp to the east. */
const BAILANG_MAP: MapDef = {
  id: "bailang",
  name: "백랑산",
  rows: [
    "mmhhh..gggg...gggg",
    "mhhhh.ggggg..ggggg",
    "hhhhh..ggg...gg..g",
    "hhhh.....gg....cc.",
    "hhhhh..g.ggg..cccc",
    "hhhhhh.gg.g...cccc",
    "hhhhh....ggg..cccc",
    "hhhh...g.......cc.",
    "hhhhh..gg..hh....g",
    "hhhh..ggg..hhh..gg",
    "mhhhh..gg.....gggg",
    "mmhhh...g...gggggg",
  ],
};

// ---------------------------------------------------------------- battles

export const CANGTING: StoryBattleDef = {
  id: "cangting",
  name: "창정의 결전",
  place: "황하 창정",
  summary: "관도에서 달아난 원소가 창정에 남은 군사를 모았다. 황하를 건너 원소를 몰아내라.",
  map: CANGTING_MAP,
  weather: "sunny",
  roundLimit: 20,
  sides: ["조조군", "원소군"],
  slots: cells([1, 5], [2, 4], [2, 6], [3, 5], [3, 3], [3, 7], [1, 3], [1, 7], [0, 5]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("yuan-shao", "shockCavalry", 25, 15, 5, guard),
    foe("feng-ji", "strategist", 23, 14, 6, heal),
    foe("yuan-shang", "shockCavalry", 24, 10, 5),
    foe("yuan-elite", "shockCavalry", 22, 14, 3, { id: "yuan-elite-cavalry-1" }),
    foe("yuan-elite", "cavalry", 22, 14, 8, { id: "yuan-elite-cavalry-2" }),
    foe("yuan", "infantry", 22, 6, 5, { id: "yuan-infantry-1", ...hold }),
    foe("yuan", "infantry", 22, 7, 1, { id: "yuan-infantry-2", ...hold }),
    foe("yuan", "infantry", 22, 7, 9, { id: "yuan-infantry-3", ...hold }),
    foe("yuan", "archer", 21, 8, 3, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 21, 8, 7, { id: "yuan-archer-2", ...hold }),
    foe("yuan", "cavalry", 21, 10, 2, { id: "yuan-cavalry-1" }),
    foe("yuan", "cavalry", 21, 10, 9, { id: "yuan-cavalry-2" }),
    foe("yuan-elite", "heavyInfantry", 22, 12, 5, { id: "yuan-elite-infantry-1", ...guard }),
    foe("yuan-elite", "longbow", 21, 14, 4, { id: "yuan-elite-archer-1", ...hold }),
    foe("yuan", "cavalry", 22, 17, 1, { id: "yuan-cavalry-3", arrives: 4 }),
    foe("yuan", "cavalry", 22, 17, 10, { id: "yuan-cavalry-4", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-shao")],
  goalText: { win: "원소 격파", lose: "조조 퇴각" },
  rewards: { gold: 1200, items: ["seal-cord"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "황하 건너 창정에 원씨의 깃발이 다시 늘어섰다. 관도에서 흩어진 군사들이었다." },
        {
          who: "yuan-shao",
          mood: "angry",
          text: "관도의 일은 하늘이 잠시 눈을 감은 것이다. 이번에는 조조를 강물에 처넣어라!",
        },
        {
          who: "yuan-shang",
          mood: "smile",
          text: "아버님은 진채에서 지켜보십시오. 조조의 목은 제가 가져오겠습니다.",
        },
        {
          who: "xun-yu",
          text: "원소는 병들었고 군사는 겁에 질렸습니다. 숨 돌릴 틈을 주지 마십시오.",
        },
        {
          who: "cao-cao",
          text: "다리 하나에 여울 둘이다. 모두 건너라! 원소의 진채까지 단숨에 밀어붙인다!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-cavalry-3" },
      lines: [
        { text: "동쪽 들판에서 원소의 후군 기병이 달려온다!" },
        {
          who: "zhang-liao",
          text: "후군입니다. 진채를 치기 전에 옆구리를 막으십시오!",
          if: notSpared,
        },
        {
          who: "lu-bu",
          mood: "smile",
          text: "늦게 온 놈들이 먼저 죽는 법이지. 저쪽은 내가 맡겠소.",
          if: spared,
        },
      ],
    },
    {
      when: { at: "defeated", unit: "yuan-shang" },
      lines: [{ who: "yuan-shang", mood: "hurt", text: "이럴 수가… 아버님, 물러나셔야 합니다!" }],
    },
    {
      when: { at: "defeated", unit: "yuan-shao" },
      lines: [
        { who: "yuan-shao", mood: "hurt", text: "또… 또 조조란 말이냐… 기주로, 기주로 물러나라!" },
        { text: "원소는 말 위에서 피를 토하며 기주로 달아났다. 하북의 기둥이 기울기 시작했다." },
      ],
    },
  ],
};

export const RUNAN: StoryBattleDef = {
  id: "runan",
  name: "여남의 유비",
  place: "예주 여남",
  summary: "유비가 황건의 잔당과 손잡고 허의 남쪽을 노린다. 숲과 언덕을 지나 유비를 몰아내라.",
  map: RUNAN_MAP,
  weather: "cloudy",
  roundLimit: 20,
  sides: ["조조군", "유비군"],
  slots: cells([1, 5], [2, 5], [1, 4], [1, 6], [3, 5], [2, 4], [0, 5], [3, 6], [0, 6]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("liu-bei", "cavalry", 24, 14, 6, guard),
    foe("guan-yu", "shockCavalry", 25, 12, 5, guard),
    foe("zhang-fei", "heavyInfantry", 25, 9, 5),
    foe("zhao-yun", "cavalry", 24, 17, 4, { arrives: 3 }),
    foe("liu-pi", "infantry", 23, 8, 2),
    foe("gong-du", "infantry", 23, 8, 9),
    foe("huangjin", "infantry", 21, 7, 3, { id: "huangjin-infantry-1" }),
    foe("huangjin", "infantry", 21, 7, 8, { id: "huangjin-infantry-2" }),
    foe("huangjin", "archer", 21, 10, 2, { id: "huangjin-archer-1", ...hold }),
    foe("huangjin", "archer", 21, 10, 9, { id: "huangjin-archer-2", ...hold }),
    foe("liu", "infantry", 22, 11, 6, { id: "liu-infantry-1", ...hold }),
    foe("liu", "archer", 22, 13, 4, { id: "liu-archer-1", ...hold }),
    foe("liu", "cavalry", 22, 12, 8, { id: "liu-cavalry-1" }),
    foe("liu", "strategist", 22, 15, 5, { id: "liu-strategist-1", ...heal }),
  ],
  goals: [loseIf("cao-cao"), winBy("liu-bei")],
  goalText: { win: "유비 격파", lose: "조조 퇴각" },
  rewards: { gold: 1200, items: ["padded-robe"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          text: "여남의 숲과 언덕 사이로 유비의 깃발이 보였다. 황건의 잔당 유벽과 공도가 곁을 지켰다.",
        },
        {
          who: "liu-bei",
          side: 1,
          mood: "grim",
          text: "조조가 몸소 왔구나. 운장, 익덕, 여기서 밀리면 몸 둘 곳이 없다.",
        },
        {
          who: "guan-yu",
          side: 1,
          mood: "grim",
          text: "조공의 은혜는 백마에서 갚았소. 오늘은 형님의 장수로 서겠소.",
        },
        {
          who: "cao-cao",
          mood: "grim",
          text: "운장… 그대를 붙잡지 못한 것이 한이다. 그러나 오늘은 사정을 두지 않는다.",
        },
        {
          who: "zhang-fei",
          side: 1,
          mood: "angry",
          text: "말이 길다! 조조의 목은 이 장익덕이 거두어 가마!",
        },
        {
          who: "dian-wei",
          mood: "angry",
          text: "주공 곁은 이 전위가 지킵니다. 장비든 누구든 와 보라지요!",
          if: lived,
        },
      ],
    },
    {
      when: { at: "arrived", unit: "zhao-yun" },
      lines: [
        { text: "동쪽 숲길에서 한 무리의 기병이 달려 나왔다!" },
        {
          who: "zhao-yun",
          side: 1,
          mood: "angry",
          text: "조운이 여기 있다! 주공께는 한 걸음도 다가오지 못한다!",
        },
      ],
    },
    {
      when: { at: "defeated", unit: "guan-yu" },
      lines: [
        {
          who: "guan-yu",
          side: 1,
          mood: "grim",
          text: "조공, 은혜도 원한도 다음에 셈합시다. 형님을 지키러 가야겠소.",
        },
        { text: "관우는 칼을 거두고 말머리를 돌렸다. 조조는 그 뒤를 오래 바라보았다." },
      ],
    },
    {
      when: { at: "defeated", unit: "zhang-fei" },
      lines: [{ who: "zhang-fei", side: 1, mood: "hurt", text: "으윽… 오늘은 물러나 주마!" }],
    },
    {
      when: { at: "defeated", unit: "liu-bei" },
      lines: [
        {
          who: "liu-bei",
          side: 1,
          mood: "hurt",
          text: "여남마저 지킬 수 없구나… 형주의 유경승에게 몸을 맡기자.",
        },
        { text: "유비는 남은 군사를 이끌고 남쪽 형주로 달아났다. 허의 남쪽이 조용해졌다." },
      ],
    },
  ],
};

export const LIYANG: StoryBattleDef = {
  id: "liyang",
  name: "여양의 형제",
  place: "황하 여양",
  summary: "원담이 여양성을 지키고, 원상이 구원하러 달려온다. 두 형제를 모두 물리쳐라.",
  map: LIYANG_MAP,
  weather: "cloudy",
  roundLimit: 20,
  sides: ["조조군", "원씨군"],
  slots: cells([1, 8], [2, 8], [3, 8], [2, 9], [0, 8], [4, 8], [3, 7], [0, 9], [2, 7]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    // 원담 in the town.
    foe("yuan-tan", "cavalry", 25, 14, 4, guard),
    foe("guo-tu", "strategist", 24, 13, 5, heal),
    foe("yuan-elite", "heavyInfantry", 23, 13, 4, { id: "yuan-elite-infantry-1", ...hold }),
    foe("yuan", "infantry", 23, 11, 4, { id: "yuan-infantry-1", ...guard }),
    foe("yuan", "infantry", 23, 15, 7, { id: "yuan-infantry-2", ...guard }),
    foe("yuan", "archer", 22, 12, 3, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 22, 16, 6, { id: "yuan-archer-2", ...hold }),
    // Before the walls.
    foe("yuan", "infantry", 23, 9, 4, { id: "yuan-infantry-3", ...hold }),
    foe("yuan", "archer", 22, 10, 6, { id: "yuan-archer-3", ...hold }),
    foe("yuan", "cavalry", 23, 8, 8, { id: "yuan-cavalry-1" }),
    // 원상 rides in from the east.
    foe("yuan-shang", "shockCavalry", 25, 17, 9, { arrives: 3 }),
    foe("yuan-elite", "shockCavalry", 23, 17, 8, { id: "yuan-elite-cavalry-1", arrives: 3 }),
    foe("yuan-elite", "cavalry", 23, 17, 10, { id: "yuan-elite-cavalry-2", arrives: 3 }),
    foe("yuan", "infantry", 23, 16, 9, { id: "yuan-infantry-4", arrives: 3 }),
    foe("yuan", "archer", 22, 17, 11, { id: "yuan-archer-4", arrives: 5 }),
    foe("yuan", "cavalry", 23, 16, 11, { id: "yuan-cavalry-2", arrives: 5 }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-tan", "yuan-shang")],
  goalText: { win: "원담과 원상 격파", lose: "조조 퇴각" },
  rewards: { gold: 1500, items: ["siege-stone"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "원소가 죽은 이듬해. 원담이 여양성에 들어앉아 황하를 지켰다." },
        {
          who: "yuan-tan",
          mood: "angry",
          text: "아우는 업에서 무얼 하는 게냐! 아버님 자리를 차지하더니 형은 내버려 둘 셈이냐!",
        },
        { who: "guo-tu", mood: "grim", text: "업의 구원군이 곧 닿을 것입니다. 조금만 버티십시오." },
        {
          who: "cao-cao",
          mood: "smile",
          text: "형제가 따로 노는구나. 아우가 오기 전에 성을 흔들어 두어라!",
        },
        {
          who: "chen-gong",
          text: "구원군은 동쪽에서 올 것입니다. 성을 치더라도 옆구리를 비워 두지 마십시오.",
          if: mercy,
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-shang" },
      lines: [
        { text: "동쪽 들판으로 원상의 구원군이 달려왔다!" },
        {
          who: "yuan-shang",
          mood: "angry",
          text: "형님, 성에 숨어 있지 말고 나와 싸우시오! 구하러 온 것만도 고맙게 여기시오!",
        },
        {
          who: "yuan-tan",
          mood: "angry",
          text: "현보, 늦게 와서 생색부터 내느냐! 네 할 일이나 해라!",
        },
        {
          who: "cao-cao",
          mood: "smile",
          text: "형제가 칼끝을 서로에게 겨누는구나. 둘을 함께 쳐라. 하나도 놓치지 마라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-archer-4" },
      lines: [{ text: "원상의 후속 부대가 동쪽에서 더 들어온다!" }],
    },
    {
      when: { at: "defeated", unit: "yuan-shang" },
      lines: [
        { who: "yuan-shang", mood: "hurt", text: "형님이 성에서 나오지 않으니… 업으로 돌아간다!" },
      ],
    },
    {
      when: { at: "defeated", unit: "yuan-tan" },
      lines: [
        { who: "yuan-tan", mood: "hurt", text: "아우는 끝내 나를 돕지 않는구나… 성을 버려라!" },
      ],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [
        { text: "원담과 원상은 각기 다른 길로 달아났다. 형제의 군은 다시 하나로 모이지 않았다." },
      ],
    },
  ],
};

export const YE: StoryBattleDef = {
  id: "ye",
  name: "업성 공략",
  place: "기주 업",
  summary:
    "업을 해자로 에워쌌다. 5라운드에 장하의 물이 해자를 채운다. 포차로 성문을 부수고 성채의 심배를 쳐라.",
  map: YE_MAP,
  weather: "sunny",
  roundLimit: 25,
  sides: ["조조군", "원상군", "원담군"],
  slots: cells(
    [2, 11],
    [1, 11],
    [3, 11],
    [2, 10],
    [3, 12],
    [1, 12],
    [0, 11],
    [3, 10],
    [1, 10],
    [2, 13],
  ),
  max: 8,
  // The stone-thrower must come: the gates are his work (ADR 0069).
  required: ["cao-cao", "liu-ye"],
  guests: [],
  allies: [
    ally("yuan-tan", "cavalry", 23, 4, 3, hold),
    ally("yuan", "infantry", 21, 3, 2, { id: "yuan-ally-1", ...hold }),
    ally("yuan", "infantry", 21, 5, 4, { id: "yuan-ally-2", ...hold }),
    ally("yuan", "archer", 21, 3, 4, { id: "yuan-ally-3", ...hold }),
  ],
  enemies: [
    foe("shen-pei", "tactician", 28, 18, 1, guard),
    gate("gate-west", 28, 11, 4),
    gate("gate-south", 28, 15, 9),
    // The garrison: the keep, the gates and the walls.
    foe("yuan-elite", "heavyInfantry", 26, 17, 1, { id: "yuan-elite-infantry-1", ...guard }),
    foe("yuan-elite", "heavyInfantry", 26, 18, 2, { id: "yuan-elite-infantry-2", ...guard }),
    foe("yuan", "strategist", 25, 17, 2, { id: "yuan-strategist-1", ...heal }),
    foe("yuan-elite", "heavyInfantry", 26, 15, 3, { id: "yuan-elite-infantry-3", ...hold }),
    foe("yuan-elite", "longbow", 25, 12, 3, { id: "yuan-elite-archer-1", ...hold }),
    foe("yuan-elite", "longbow", 25, 14, 8, { id: "yuan-elite-archer-2", ...hold }),
    foe("yuan", "infantry", 25, 12, 4, { id: "yuan-infantry-4", ...hold }),
    foe("yuan", "infantry", 25, 15, 8, { id: "yuan-infantry-5", ...hold }),
    foe("yuan", "catapult", 25, 15, 5, { id: "yuan-catapult-1", ...hold }),
    // 심배's sortie, outside the gates until the trench floods.
    foe("yuan", "infantry", 25, 9, 3, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 25, 9, 5, { id: "yuan-infantry-2" }),
    foe("yuan-elite", "cavalry", 25, 8, 4, { id: "yuan-elite-cavalry-1" }),
    foe("yuan", "infantry", 25, 14, 11, { id: "yuan-infantry-3" }),
    foe("yuan-elite", "cavalry", 25, 16, 12, { id: "yuan-elite-cavalry-2" }),
    foe("yuan", "archer", 24, 13, 12, { id: "yuan-archer-1" }),
    // 원상's relief from the north-west.
    foe("yuan-shang", "shockCavalry", 27, 0, 1, { arrives: 3 }),
    foe("ma-yan", "cavalry", 26, 1, 0, { arrives: 3 }),
    foe("yuan", "cavalry", 24, 0, 2, { id: "yuan-cavalry-3", arrives: 3 }),
    foe("yuan", "cavalry", 24, 2, 0, { id: "yuan-cavalry-4", arrives: 3 }),
    foe("zhang-yi", "cavalry", 26, 0, 0, { arrives: 4 }),
    foe("yin-kai", "infantry", 25, 0, 3, { arrives: 4 }),
    foe("yuan", "archer", 24, 0, 4, { id: "yuan-archer-3", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("shen-pei")],
  goalText: { win: "심배 격파", lose: "조조 퇴각" },
  shifts: [{ id: "trench", when: { kind: "round", round: 5 }, tiles: YE_TRENCH, damage: 120 }],
  rewards: { gold: 2000, items: ["bright-armour"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "업성 둘레에 해자가 깊이 파였다. 장하의 물길은 이미 해자 어귀까지 끌어다 놓았다." },
        {
          who: "shen-pei",
          mood: "smile",
          text: "흙산도 땅굴도 다 막아 냈다. 도랑 하나로 업이 무너질 성싶으냐!",
        },
        {
          who: "yuan-tan",
          side: 2,
          text: "조공, 북쪽 길은 내가 막겠소. 아우 놈이 구하러 오면 내 손으로 상대하리다.",
        },
        {
          who: "cao-cao",
          text: "5라운드에 장하의 물을 해자에 푼다. 그전에 성 밖으로 나온 적을 쓸어 내라!",
        },
        {
          who: "liu-ye",
          mood: "smile",
          text: "성문은 제 돌수레에 맡기십시오. 몇 번이면 열립니다.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-shang" },
      lines: [
        { text: "북서쪽 언덕 너머로 원상의 구원군이 나타났다!" },
        {
          who: "yuan-shang",
          mood: "angry",
          text: "형님이 조조의 개가 되었다더니 참말이구나! 업을 구하라!",
        },
        {
          who: "yuan-tan",
          side: 2,
          mood: "angry",
          text: "닥쳐라! 아버님의 자리를 가로챈 놈이 누구더냐!",
        },
      ],
    },
    {
      when: { at: "shifted", shift: "trench" },
      lines: [
        { text: "장하의 둑을 터뜨리자 물이 해자로 쏟아져 들어왔다! 성 밖의 적이 물에 갇혔다." },
        { who: "shen-pei", mood: "grim", text: "물이…! 성 밖의 군사를 어서 들여라!" },
        {
          who: "cao-cao",
          mood: "smile",
          text: "이제 성으로 가는 길은 둑길 둘뿐이다. 성문을 부숴라!",
        },
      ],
    },
    {
      when: { at: "defeated", unit: "gate-west" },
      lines: [
        { text: "성문이 부서졌다!" },
        { who: "cao-cao", mood: "angry", text: "서문이 열렸다. 성채까지 밀고 들어가라!" },
      ],
    },
    {
      when: { at: "defeated", unit: "gate-south" },
      lines: [
        { text: "성문이 부서졌다!" },
        { who: "cao-cao", mood: "angry", text: "남문이 열렸다. 심배를 놓치지 마라!" },
      ],
    },
    {
      when: { at: "defeated", unit: "yuan-shang" },
      lines: [
        { who: "yuan-shang", mood: "hurt", text: "업을… 버릴 수밖에 없단 말이냐…" },
        { text: "원상은 구원군을 버리고 중산으로 달아났다." },
      ],
    },
    {
      when: { at: "defeated", unit: "shen-pei" },
      lines: [
        { who: "shen-pei", mood: "hurt", text: "업은… 원씨의 성이다. 끝까지…!" },
        { text: "심배가 사로잡히자 업성의 깃발이 하나둘 내려졌다." },
      ],
    },
  ],
};

export const NANPI: StoryBattleDef = {
  id: "nanpi",
  name: "남피의 얼음",
  place: "발해 남피",
  summary:
    "원담이 등을 돌려 남피에 들어앉았다. 강이 얼어 있는 동안 건너라. 얼음이 풀리면 다리 둘뿐이다.",
  map: NANPI_MAP,
  weather: "cloudy",
  light: "dusk",
  roundLimit: 20,
  sides: ["조조군", "원담군"],
  slots: cells([1, 5], [2, 4], [1, 4], [3, 4], [1, 3], [2, 3], [4, 4], [0, 4], [0, 5]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("yuan-tan", "shockCavalry", 28, 15, 5, guard),
    foe("guo-tu", "tactician", 26, 16, 4, guard),
    foe("xin-ping", "strategist", 25, 16, 6, guard),
    foe("yuan-elite", "heavyInfantry", 26, 14, 5, { id: "yuan-elite-infantry-3", ...hold }),
    // The bridgeheads and the far bank.
    foe("yuan-elite", "heavyInfantry", 26, 9, 2, { id: "yuan-elite-infantry-1", ...guard }),
    foe("yuan-elite", "heavyInfantry", 26, 9, 9, { id: "yuan-elite-infantry-2", ...guard }),
    foe("yuan-elite", "longbow", 25, 10, 4, { id: "yuan-elite-archer-1", ...hold }),
    foe("yuan-elite", "longbow", 25, 10, 7, { id: "yuan-elite-archer-2", ...hold }),
    foe("yuan-elite", "cavalry", 25, 11, 5, { id: "yuan-elite-cavalry-1" }),
    foe("yuan-elite", "cavalry", 25, 11, 6, { id: "yuan-elite-cavalry-2" }),
    // The town's gaps.
    foe("yuan", "infantry", 25, 12, 3, { id: "yuan-infantry-1", ...guard }),
    foe("yuan", "infantry", 25, 13, 6, { id: "yuan-infantry-2", ...guard }),
    foe("yuan", "infantry", 25, 12, 9, { id: "yuan-infantry-3", ...guard }),
    foe("yuan", "cavalry", 25, 17, 0, { id: "yuan-cavalry-1", arrives: 3 }),
    foe("yuan", "cavalry", 25, 17, 11, { id: "yuan-cavalry-2", arrives: 3 }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-tan")],
  goalText: { win: "원담 격파", lose: "조조 퇴각" },
  shifts: [{ id: "thaw", when: { kind: "round", round: 5 }, tiles: NANPI_THAW, damage: 150 }],
  rewards: { gold: 2000, items: ["great-ji"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "정월의 남피. 꽁꽁 언 강 너머에 원담의 성이 웅크리고 있었다." },
        {
          who: "yuan-tan",
          mood: "angry",
          text: "조조에게 머리를 숙인 것은 한때의 계책이었다. 하북은 원씨의 땅이다!",
        },
        {
          who: "guo-tu",
          text: "강이 얼어 있는 동안은 적도 건너옵니다. 강가를 굳게 지키십시오.",
        },
        {
          who: "cao-cao",
          text: "얼음이 버틸 때 건너야 한다. 날이 풀리면 건널 곳은 다리 둘뿐이다!",
        },
      ],
    },
    {
      when: { at: "round", round: 2 },
      lines: [
        {
          who: "xin-pi",
          mood: "grim",
          text: "형님! 원담은 끝났습니다. 성문을 열고 나오십시오!",
        },
        {
          who: "xin-ping",
          mood: "grim",
          text: "좌치야… 너는 네 길을 가거라. 나는 원씨의 녹을 먹은 몸이다.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-cavalry-1" },
      lines: [{ text: "남피성 뒤쪽에서 원담의 기병이 더 달려 나온다!" }],
    },
    {
      when: { at: "shifted", shift: "thaw" },
      lines: [
        { text: "쩍, 쩌억— 얼음이 갈라졌다! 검은 강물이 얼음 위의 군사를 삼켰다." },
        { who: "yuan-tan", mood: "smile", text: "하하! 하늘도 남피를 돕는구나!" },
        { who: "cao-cao", mood: "angry", text: "다리로 건너라! 다리 둘만 지키면 길은 있다!" },
      ],
    },
    {
      when: { at: "defeated", unit: "yuan-tan" },
      lines: [
        {
          who: "yuan-tan",
          mood: "hurt",
          text: "살려 다오… 나를 놓아주면 부귀를 누리게 해 주마…!",
        },
        { text: "달아나던 원담은 조순의 호표기에게 따라잡혀 쓰러졌다." },
        { who: "cao-chun", mood: "angry", text: "호표기가 원담을 베었다! 남피는 우리 것이다!" },
      ],
    },
  ],
};

export const HUGUAN: StoryBattleDef = {
  id: "huguan",
  name: "호관의 고간",
  place: "병주 호관",
  summary: "고간이 태항산의 호관을 막아섰다. 좁은 길을 올라 포차로 관문을 부수고 고간을 쳐라.",
  map: HUGUAN_MAP,
  weather: "wind",
  roundLimit: 20,
  sides: ["조조군", "고간군"],
  slots: cells([1, 5], [2, 4], [2, 5], [1, 4], [3, 5], [0, 5], [1, 3], [1, 6], [0, 4]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("gao-gan", "heavyInfantry", 30, 15, 4, guard),
    gate("gate-pass", 30, 12, 5),
    // Behind the wall: the towers, the gate and the keep.
    foe("bing", "longbow", 27, 13, 3, { id: "bing-archer-1", ...hold }),
    foe("bing", "longbow", 27, 13, 7, { id: "bing-archer-2", ...hold }),
    foe("bing", "heavyInfantry", 27, 13, 5, { id: "bing-infantry-1", ...guard }),
    foe("bing", "catapult", 27, 14, 5, { id: "bing-catapult-1", ...guard }),
    foe("bing", "heavyInfantry", 27, 14, 6, { id: "bing-infantry-2", ...hold }),
    foe("bing", "strategist", 27, 16, 5, { id: "bing-strategist-1", ...heal }),
    foe("bing", "cavalry", 27, 16, 6, { id: "bing-cavalry-1" }),
    // Holding the pass before the wall.
    foe("bing", "heavyInfantry", 27, 10, 3, { id: "bing-infantry-3", ...hold }),
    foe("bing", "heavyInfantry", 27, 10, 7, { id: "bing-infantry-4", ...hold }),
    foe("bing", "longbow", 26, 9, 5, { id: "bing-archer-3", ...hold }),
    foe("bing", "cavalry", 26, 7, 4, { id: "bing-cavalry-2" }),
    foe("bing", "cavalry", 26, 7, 6, { id: "bing-cavalry-3" }),
    // 고간's reinforcements, inside the pass.
    foe("bing", "infantry", 27, 17, 4, { id: "bing-infantry-5", arrives: 4 }),
    foe("bing", "infantry", 27, 17, 6, { id: "bing-infantry-6", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("gao-gan")],
  goalText: { win: "고간 격파", lose: "조조 퇴각" },
  rewards: { gold: 2200, items: ["fox-fur"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "얼어붙은 태항산 길 끝, 호관의 성벽이 골짜기를 가로막았다." },
        {
          who: "gao-gan",
          mood: "angry",
          text: "외숙의 땅을 순순히 내줄 것 같으냐! 이 관은 새도 넘지 못한다!",
        },
        {
          who: "cao-cao",
          text: "길은 저 관문 하나뿐이다. 성문을 깨뜨려라. 돌수레를 앞으로!",
        },
        {
          who: "liu-ye",
          text: "바람이 관 쪽으로 붑니다. 돌을 던지기에도, 불을 놓기에도 좋습니다.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "bing-infantry-5" },
      lines: [{ text: "관 안쪽으로 병주의 군사가 더 들어왔다!" }],
    },
    {
      when: { at: "defeated", unit: "gate-pass" },
      lines: [
        { text: "성문이 부서졌다!" },
        { who: "gao-gan", mood: "grim", text: "관문이… 뚫렸단 말이냐!" },
        { who: "cao-cao", mood: "angry", text: "관이 열렸다. 고간을 놓치지 마라!" },
      ],
    },
    {
      when: { at: "defeated", unit: "gao-gan" },
      lines: [
        { who: "gao-gan", mood: "hurt", text: "병주도 끝이구나… 흉노 땅으로라도 가야겠다." },
        { text: "고간은 관을 버리고 북쪽으로 달아났다. 병주가 조조의 손에 들어왔다." },
      ],
    },
  ],
};

/** 백랑산, 곽가 at our side: the 오환 are scattered, 답돈's camp is not yet in order. */
const BAILANG_UNREADY: StoryUnit[] = [
  foe("ta-dun", "shockCavalry", 29, 16, 5, hold),
  foe("yuan-shang", "cavalry", 26, 15, 4, hold),
  foe("yuan-xi", "cavalry", 25, 15, 6, hold),
  foe("wuhuan", "cavalry", 26, 11, 2, { id: "wuhuan-cavalry-1" }),
  foe("wuhuan", "cavalry", 26, 11, 8, { id: "wuhuan-cavalry-2" }),
  foe("wuhuan", "cavalry", 26, 13, 5, { id: "wuhuan-cavalry-3", ...hold }),
  foe("wuhuan", "cavalry", 26, 14, 3, { id: "wuhuan-cavalry-4", ...hold }),
  foe("wuhuan", "cavalry", 26, 14, 7, { id: "wuhuan-cavalry-5", ...hold }),
  foe("wuhuan", "cavalry", 26, 17, 1, { id: "wuhuan-cavalry-6", arrives: 3 }),
  foe("wuhuan", "cavalry", 26, 17, 10, { id: "wuhuan-cavalry-7", arrives: 4 }),
  foe("wuhuan", "cavalry", 26, 17, 8, { id: "wuhuan-cavalry-8", arrives: 5 }),
].map((u) => ({ ...u, if: went }));

/** 백랑산, 곽가 at 허: the slow march was seen, and the horde is drawn up and waiting. */
const BAILANG_READY: StoryUnit[] = [
  foe("ta-dun", "shockCavalry", 29, 16, 5, hold),
  foe("yuan-shang", "cavalry", 26, 14, 4),
  foe("yuan-xi", "cavalry", 25, 14, 6),
  foe("lou-ban", "cavalry", 27, 12, 3),
  foe("su-puyan", "cavalry", 27, 12, 7),
  foe("wuhuan", "cavalry", 26, 9, 3, { id: "wuhuan-cavalry-1" }),
  foe("wuhuan", "cavalry", 26, 9, 7, { id: "wuhuan-cavalry-2" }),
  foe("wuhuan", "cavalry", 26, 11, 1, { id: "wuhuan-cavalry-3" }),
  foe("wuhuan", "cavalry", 26, 11, 9, { id: "wuhuan-cavalry-4" }),
  foe("wuhuan", "cavalry", 26, 12, 5, { id: "wuhuan-cavalry-5" }),
  foe("wuhuan", "cavalry", 26, 13, 2, { id: "wuhuan-cavalry-6" }),
  foe("wuhuan", "cavalry", 26, 13, 8, { id: "wuhuan-cavalry-7" }),
  foe("wuhuan", "cavalry", 26, 15, 5, { id: "wuhuan-cavalry-8", ...hold }),
  foe("wuhuan", "cavalry", 26, 15, 3, { id: "wuhuan-cavalry-11", ...hold }),
  foe("wuhuan", "cavalry", 26, 17, 2, { id: "wuhuan-cavalry-9", arrives: 2 }),
  foe("wuhuan", "cavalry", 26, 17, 9, { id: "wuhuan-cavalry-10", arrives: 3 }),
].map((u) => ({ ...u, if: stayed }));

export const BAILANG: StoryBattleDef = {
  id: "bailang",
  name: "백랑산의 오환",
  place: "유성 백랑산",
  summary: "노룡새의 옛길을 지나 백랑산에 올랐다. 오환의 대군 속으로 달려 내려가 답돈을 베어라.",
  map: BAILANG_MAP,
  weather: "wind",
  roundLimit: 25,
  sides: ["조조군", "오환"],
  slots: cells([1, 5], [2, 4], [2, 6], [3, 5], [1, 4], [1, 6], [3, 4], [3, 6], [2, 5]),
  max: 7,
  required: ["cao-cao"],
  absent: [{ officer: "guo-jia", note: "곽가는 허에서 병을 다스리고 있습니다", if: stayed }],
  guests: [],
  enemies: [...BAILANG_UNREADY, ...BAILANG_READY],
  goals: [loseIf("cao-cao"), winBy("ta-dun")],
  // The vanguard's man cuts 답돈 down: 장료, as the record has it, or 여포 if he was spared (ADR 0071).
  duels: [
    {
      id: "bailang-zhang-liao",
      a: ["zhang-liao"],
      b: "ta-dun",
      when: { kind: "adjacent" },
      play: true,
      outcome: "a",
      fatal: true,
      if: notSpared,
    },
    {
      id: "bailang-lu-bu",
      a: ["lu-bu"],
      b: "ta-dun",
      when: { kind: "adjacent" },
      play: true,
      outcome: "a",
      fatal: true,
      if: spared,
    },
  ],
  goalText: { win: "답돈 격파", lose: "조조 퇴각" },
  rewards: { gold: 2500, items: ["fine-horse", "tiger-tally"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      if: went,
      lines: [
        { text: "백랑산 꼭대기에서 내려다보니, 오환의 대군은 아직 진도 갖추지 못하고 있었다." },
        {
          who: "ta-dun",
          mood: "angry",
          text: "조조가 산 위에 있다고? 길도 없는 곳을 어떻게 넘어왔단 말이냐!",
        },
        {
          who: "guo-jia",
          mood: "smile",
          text: "주공, 적은 아직 말에 오르지도 못했습니다. 지금이 아니면 안 됩니다.",
          if: went,
        },
        {
          who: "cao-cao",
          mood: "smile",
          text: "내 깃발을 선봉에 내준다. 저 깃발을 따라 단숨에 내리쳐라!",
        },
        {
          who: "zhang-liao",
          mood: "angry",
          text: "주공의 깃발을 받았습니다. 장료가 앞장서겠습니다. 나를 따르라!",
          if: notSpared,
        },
        {
          who: "lu-bu",
          mood: "smile",
          text: "깃발 따위는 필요 없소. 적토마가 길을 열 테니 따라오기나 하시오!",
          if: spared,
        },
      ],
    },
    {
      when: { at: "start" },
      if: stayed,
      lines: [
        { text: "백랑산 아래 들판에 오환의 기병이 겹겹이 늘어서 있었다. 이쪽을 기다린 것이다." },
        {
          who: "ta-dun",
          mood: "smile",
          text: "느릿느릿 잘도 왔구나, 조조. 이 들판이 네 무덤이다!",
        },
        {
          who: "cao-cao",
          mood: "grim",
          text: "봉효의 편지대로구나. 느린 군사는 들키는 법… 그래도 물러설 길은 없다.",
        },
        {
          who: "zhang-liao",
          mood: "angry",
          text: "적의 진이 두텁다면 한가운데를 뚫을 뿐입니다. 선봉은 제게 주십시오!",
          if: notSpared,
        },
        {
          who: "lu-bu",
          mood: "smile",
          text: "많을수록 좋지. 오랑캐 말이 몇 마리든 적토마를 따라오지는 못하오!",
          if: spared,
        },
      ],
    },
    {
      when: { at: "arrived", unit: "wuhuan-cavalry-6" },
      if: went,
      lines: [{ text: "뒤늦게 소식을 들은 오환 기병이 동쪽에서 달려온다!" }],
    },
    {
      when: { at: "arrived", unit: "wuhuan-cavalry-9" },
      if: stayed,
      lines: [{ text: "오환의 기병이 동쪽에서 끝없이 몰려온다!" }],
    },
    {
      when: { at: "defeated", unit: "yuan-shang" },
      lines: [
        { who: "yuan-shang", mood: "hurt", text: "오환마저 무너지는가… 형님, 요동으로 갑시다!" },
      ],
    },
    {
      when: { at: "duel", duel: "bailang-zhang-liao" },
      if: notSpared,
      lines: [
        { who: "ta-dun", mood: "angry", text: "한나라 장수 놈이 감히 선우 앞에 창을 겨누느냐!" },
        {
          who: "zhang-liao",
          mood: "angry",
          text: "주공의 깃발 아래 장문원이다. 네 목을 받으러 왔다!",
          if: went,
        },
        {
          who: "zhang-liao",
          mood: "angry",
          text: "장문원이다. 진이 아무리 두터워도 네 목 하나면 끝난다!",
          if: stayed,
        },
      ],
    },
    {
      when: { at: "duel", duel: "bailang-lu-bu" },
      if: spared,
      lines: [
        { who: "ta-dun", mood: "angry", text: "붉은 말… 네가 여포냐! 오환의 선우가 상대해 주마!" },
        { who: "lu-bu", mood: "smile", text: "선우든 무엇이든, 방천극 앞에선 다 같은 목이다." },
      ],
    },
    // The fall, however it comes; the duel's end gives the victor's shout.
    {
      when: { at: "defeated", unit: "ta-dun" },
      lines: [
        { who: "ta-dun", mood: "hurt", text: "산에서… 귀신처럼 내려오다니…", if: went },
        { who: "ta-dun", mood: "hurt", text: "기다리고 있었건만… 이렇게 무너지다니…", if: stayed },
        {
          text: "답돈이 쓰러지자 오환의 기병은 말머리를 돌려 흩어졌다. 항복하는 자가 들판을 메웠다.",
        },
      ],
    },
    {
      when: { at: "duelEnd", duel: "bailang-zhang-liao" },
      if: notSpared,
      lines: [
        {
          who: "zhang-liao",
          mood: "angry",
          text: "답돈의 목을 베었다! 오환은 들어라, 선우는 죽었다!",
        },
      ],
    },
    {
      when: { at: "duelEnd", duel: "bailang-lu-bu" },
      if: spared,
      lines: [
        { who: "lu-bu", mood: "smile", text: "오랑캐의 왕이라더니 별것 아니군. 다음은 누구냐!" },
      ],
    },
  ],
};
