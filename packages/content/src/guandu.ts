/**
 * Story chapter 3: 관도 (Guandu, 199–200). Five battles from Cao Cao's side,
 * following the recorded campaign: the relief of Baima, the bait at Yanjin, the
 * siege of the Guandu camp, the night raid on Wuchao and the rout of Yuan Shao.
 * The scenes are staged on their own tile maps (ADR 0063), and the roster follows
 * chapter 2's branches: 여포 instead of 장료 if he was spared, 진궁 after mercy
 * at 서주, 전위 if he lived (ADR 0060, 0061). History is public domain; every
 * line, map and number here is our own (ADR 0002, ADR 0049).
 */
import type { Cell, Goal } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import {
  act,
  actor,
  all,
  CAMP_GROUND,
  camera,
  emote,
  enter,
  face,
  fire,
  fx,
  leave,
  pause,
  say,
  sound,
  tell,
  walk,
} from "./stage.ts";
import type {
  Actor,
  CampTalk,
  ChapterDef,
  Cmd,
  Cond,
  Facing4,
  Landscape,
  Line,
  Mood,
  PropDef,
  PropKind,
  SceneDef,
  StageDef,
  StageLight,
  StoryBattleDef,
} from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));

// What chapter 2 decided (ADR 0060). A fresh start assumes history: revenge, fell, executed.
/** 여포 was spared at 백문루 and serves us; 장료 left for good. */
const spared: Cond = { flag: "lubu", is: "spared" };
/** 여포 was hanged and 장료 serves us (history). */
const notSpared: Cond = { flag: "lubu", not: "spared" };
/** 서주 was spared and 진궁 stayed with us. */
const mercy: Cond = { flag: "xuzhou", is: "mercy" };
/** 전위 came out of 완성 alive. */
const lived: Cond = { flag: "dianwei", is: "lived" };
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
const winBy = (unit: string): Goal => ({ side: 0, when: { kind: "defeat", units: [unit] } });

/** An enemy unit; troop templates take an explicit id. */
function foe(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> = {},
): ScenarioUnit {
  return { officer, classId, level, side: 1, x, y, ...extra };
}

const hold = { orders: ORDERS.hold };
const guard = { orders: ORDERS.guard };
const heal = { orders: ORDERS.healThenHold };

// ---------------------------------------------------------------- maps
// North is up on every map, and each lies as the record has it (ADR 0075; docs/remake/BATTLEFIELDS.md).

/**
 * 백마 (Baima), the fourth month of 200: the Yellow River runs north-east across the top,
 * with the bank of 여양 and its hill showing in the north-west. The 백마 ferry crosses
 * north-west of the walled town, which stands south-east of it with its gate to the west.
 * 안량 crossed there and camps at the ferry head, against the town's north-west corner;
 * 조조 comes from the west, from the feint at 연진. A stretch of dyke guards the bank west
 * of the ferry.
 */
const BAIMA: MapDef = {
  id: "baima",
  name: "백마",
  rows: [
    "mhhg.wwwwddwwwwwww",
    "wwwwwwwwwddwwwwwww",
    "wwwwwhh.ccccc.....",
    "......ffcccc#####.",
    "..hh........#vvv#.",
    ".hhh........vvFv#.",
    "............#vvv#.",
    "..ff...g....#####.",
    ".fff..gg..........",
    "......g...........",
    "..gg............h.",
    ".........g....hhh.",
  ],
};

/**
 * 연진 (Yanjin), days later: the river and its ferry along the top, where 원소's riders
 * crossed. The road from 백마 runs west along the bank, strewn with our wagons, and a dyke
 * set back from the river shuts it off from the south: 조조's camp lies behind it, below
 * the southern slope (남판 南阪), out of the riders' sight.
 */
const YANJIN: MapDef = {
  id: "yanjin",
  name: "연진",
  rows: [
    "wwwddwwwwwwwwwwwww",
    "wwwddwwwwww.......",
    "ff..........g.....",
    "f...c.......g.....",
    "......cc....c.....",
    "..g.....c..cc...g.",
    ".gg..........c....",
    "hhhhhh.hhhhh.hhhhh",
    "..g.......c.......",
    "ff.........ccc....",
    "fff..g.....ccc..gg",
    "ff......gg.......g",
  ],
};

/**
 * 관도 (Guandu), the ninth month: the 관도 water, the canal (거수 渠水) that becomes the
 * 홍구 (鴻溝) further down, runs east across the field, north of 조조's rampart. His camp holds
 * the crossing, with the 관도 terrace in it, a mound of his own and a long trench inside the
 * east rampart against 원소's tunnels; the 역수 (役水) runs behind it and the 포전 (圃田) marsh
 * lies west. Across the water stand 원소's earth mounds, with his camps east of them along the
 * sand ridges.
 */
const GUANDU_CAMP: MapDef = {
  id: "guandu-camp",
  name: "관도 진채",
  rows: [
    "..hh..hh...cc.cccc",
    ".hhhh.hhhh.ccccccc",
    "..hh...hh....cc.cc",
    ".h.h..h.h.....g...",
    "wwwwdwwwwwwwwwwwww",
    "dccccccd#.........",
    "wccFcccd#..g......",
    "gcchcccd#.....h...",
    "wcccccccc....hhh..",
    "dcccFccd#.......f.",
    "wccccccd#..g...ff.",
    "####c####....gfff.",
    "wwwwbwwwwwwwwwwwww",
  ],
};

/**
 * 오소 (Wuchao), the tenth month, at dawn: 순우경's depot at 고시 (故市), north of the
 * 제수 (濟水) where it runs east into the 오소 marsh. 조조 comes up from the south by the
 * byroads, and 원소's riders follow from his camp to the south-west, behind us. The quick
 * battle keeps its own map (maps.ts WUCHAO).
 */
const WUCHAO_DEPOT: MapDef = {
  id: "wuchao-depot",
  name: "오소",
  rows: [
    "ffh..#ccccccc#.gwd",
    "fhh..#cccccFc#gwww",
    "ff...#ccccccc#.dww",
    "f....g##...##g.gww",
    "..g............dwg",
    "wwwwwdwwwbwwwdwwww",
    "..gg........g..dgw",
    ".ff...v.....ff..gw",
    ".ff.........ff...g",
    "....hh............",
    "..g.hh....g...ff..",
    "ff.........gg.fff.",
    "fff..............g",
    "ff....g........ggg",
  ],
};

/**
 * 원소 본진 (Yuan Shao's main camp), the tenth month: his camps strung along the sand
 * ridges north of the 관도 water, the main one at the east end, and the Yellow River he
 * fled across beyond them (drawn close). 조조 comes up from the south over the 관도 crossing.
 */
const YUAN_CAMP: MapDef = {
  id: "yuan-camp",
  name: "원소 본진",
  rows: [
    "wwwwwwwwwwwwwdddwwww",
    "..gg..........#####w",
    ".gg..hh.......#cccc#",
    "....hhhh..c...#cFcc#",
    "..c..hh..ccc...cccc#",
    ".ff......hh...#cccc#",
    "fff...........###.##",
    "ff......g.........g.",
    "....hh....gg........",
    "...hhh..........ff..",
    ".g........hh...fff..",
    "..g..........g......",
    "wwwwddwwwwwwwwwwwwww",
  ],
};

/**
 * Where and when (ADR 0075): every field of 관도 lies on the Yellow River plain. Seasons follow
 * the weather of the lunar month: 백마 and 연진 in the fourth are spring, the rest in the
 * eighth to tenth are autumn.
 */
const SPRING: Landscape = { region: "plain", season: "spring" };
const AUTUMN: Landscape = { region: "plain", season: "autumn" };

// ---------------------------------------------------------------- battles

const B1: StoryBattleDef = {
  id: "baima",
  name: "백마 구원",
  place: "백마",
  summary: "안량이 백마성을 에워쌌다. 성 안의 유연이 버티는 동안 안량을 베어라.",
  map: BAIMA,
  weather: "sunny",
  landscape: SPRING,
  roundLimit: 20,
  sides: ["조조군", "원소군", "백마 수비군"],
  // From the west, where the feint at 연진 drew 원소 off.
  slots: cells([0, 3], [1, 3], [0, 4], [1, 4], [0, 5], [0, 6], [1, 6], [0, 7]),
  max: 7,
  required: ["cao-cao", "guan-yu"],
  guests: [],
  allies: [{ officer: "liu-yan", classId: "infantry", level: 16, side: 2, x: 14, y: 5 }],
  // 안량 camps at the ferry head, between the crossing and the town; his men press the
  // west gate and the east wall, and a screen faces west, toward us.
  enemies: [
    foe("yan-liang", "heavyInfantry", 16, 12, 2, hold),
    foe("yuan", "infantry", 13, 10, 5, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 13, 7, 5, { id: "yuan-infantry-2" }),
    foe("yuan", "archer", 13, 8, 4, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 13, 17, 4, { id: "yuan-archer-2" }),
    foe("yuan", "cavalry", 13, 6, 7, { id: "yuan-cavalry-1" }),
    foe("yuan", "strategist", 12, 10, 2, { id: "yuan-strategist-1", ...heal }),
    foe("yuan", "catapult", 12, 9, 3, { id: "yuan-catapult-1", ...hold }),
    foe("yuan", "cavalry", 13, 17, 9, { id: "yuan-cavalry-2", arrives: 4 }),
    foe("yuan", "cavalry", 13, 17, 8, { id: "yuan-cavalry-3", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), loseIf("liu-yan"), winBy("yan-liang")],
  // 관우 (our guest) cuts 안량 down among his men: the 일기토 on him is history's (ADR 0071).
  duels: [
    {
      id: "baima-yan-liang",
      a: ["guan-yu"],
      b: "yan-liang",
      when: { kind: "adjacent" },
      play: true,
      outcome: "a",
      fatal: true,
    },
  ],
  goalText: { win: "안량 격파", lose: "조조 또는 유연 퇴각" },
  rewards: { gold: 800, items: ["great-ji", "lamellar"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "yan-liang",
          mood: "angry",
          text: "조조가 서쪽으로 갔다더니, 이놈들은 어디서 나타났느냐! 성부터 짓밟아라!",
        },
        { who: "cao-cao", text: "목표는 안량 하나다. 유연이 버티는 동안 적장의 목을 쳐라!" },
        { who: "liu-yan", mood: "grim", text: "구원군이다! 성문을 조금만 더 지켜라!" },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-cavalry-2" },
      lines: [
        { text: "동쪽 들판에서 안량의 기병대가 돌아왔다!" },
        { who: "zhang-liao", text: "기병이 뒤를 칩니다. 진형을 좁히십시오!", if: notSpared },
        {
          who: "lu-bu",
          mood: "smile",
          text: "등 뒤로 기병이라. 마침 잘됐군, 저놈들은 내가 맡지.",
          if: spared,
        },
      ],
    },
    {
      when: { at: "duel", duel: "baima-yan-liang" },
      lines: [
        { text: "관우는 멀리 안량의 깃발과 일산을 바라보더니, 말에 채찍을 더했다." },
        { who: "guan-yu", mood: "angry", text: "안량! 그 목을 받겠다!" },
        { who: "yan-liang", mood: "angry", text: "누구냐! 감히 내 일산 아래로 뛰어들다니!" },
      ],
    },
    // The fall, however it comes; the duel's end tells how 관우 did it.
    {
      when: { at: "defeated", unit: "yan-liang" },
      lines: [
        { who: "yan-liang", mood: "hurt", text: "하북의 안량이… 이렇게…" },
        { text: "안량이 쓰러지자 원소군은 그 기세에 질려 무너졌다." },
      ],
    },
    {
      when: { at: "duelEnd", duel: "baima-yan-liang" },
      lines: [
        { text: "관우는 만군 가운데서 안량을 찔러 쓰러뜨리고, 그 목을 베어 돌아왔다." },
        { text: "원소의 장수들 가운데 그를 막아선 자는 아무도 없었다." },
      ],
    },
  ],
};

const B2: StoryBattleDef = {
  id: "yanjin",
  name: "연진의 미끼",
  place: "연진 남쪽 언덕",
  summary: "길에 버린 수레에 원소군이 달려든다. 대오가 흩어진 틈에 문추를 베어라.",
  map: YANJIN,
  weather: "cloudy",
  landscape: SPRING,
  roundLimit: 20,
  sides: ["조조군", "원소군"],
  // Our camp south of the dyke, out of sight of the road.
  slots: cells([12, 9], [13, 9], [11, 9], [12, 10], [13, 10], [11, 10], [14, 9], [10, 8], [14, 10]),
  max: 7,
  // 관우 stays one battle longer, for 문추 (ADR 0071).
  required: ["cao-cao", "guan-yu"],
  guests: [],
  // 문추 and 유비 wait by the ferry they crossed; their riders scatter over the wagons on the road.
  enemies: [
    foe("wen-chou", "cavalry", 18, 3, 3, hold),
    foe("liu-bei", "cavalry", 16, 4, 4, hold),
    foe("yuan", "infantry", 15, 2, 2, { id: "yuan-infantry-4", ...hold }),
    foe("yuan", "archer", 15, 1, 3, { id: "yuan-archer-3", ...hold }),
    foe("yuan", "cavalry", 14, 7, 4, { id: "yuan-cavalry-1" }),
    foe("yuan", "cavalry", 14, 8, 5, { id: "yuan-cavalry-2" }),
    foe("yuan", "infantry", 14, 11, 5, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 14, 13, 6, { id: "yuan-infantry-2" }),
    foe("yuan", "archer", 14, 6, 4, { id: "yuan-archer-1" }),
    foe("yuan", "archer", 14, 12, 4, { id: "yuan-archer-2" }),
    foe("yuan", "cavalry", 14, 3, 0, { id: "yuan-cavalry-3", arrives: 3 }),
    foe("yuan", "infantry", 14, 4, 0, { id: "yuan-infantry-3", arrives: 3 }),
  ],
  goals: [loseIf("cao-cao"), winBy("wen-chou")],
  duels: [
    {
      id: "yanjin-wen-chou",
      a: ["guan-yu"],
      b: "wen-chou",
      when: { kind: "adjacent" },
      play: true,
      outcome: "a",
      fatal: true,
    },
  ],
  goalText: { win: "문추 격파", lose: "조조 퇴각" },
  rewards: { gold: 1000, items: ["strong-bow", "jade"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "wen-chou",
          mood: "smile",
          text: "조조의 군량이 길바닥에 굴러다니는구나! 모조리 주워 담아라!",
        },
        {
          who: "liu-bei",
          side: 1,
          mood: "grim",
          text: "문 장군, 지나치게 쉽소. 조조가 이렇게 허술할 리 없소.",
        },
        {
          who: "cao-cao",
          mood: "smile",
          text: "지금이다. 말에 올라라! 수레에 매달린 놈들부터 쓸어버려라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-cavalry-3" },
      lines: [{ text: "황하를 건넌 원소군 후속 부대가 여울로 몰려온다!" }],
    },
    {
      when: { at: "defeated", unit: "liu-bei" },
      lines: [
        { who: "liu-bei", side: 1, mood: "hurt", text: "물러나라! 여기서 목숨을 버릴 수는 없다." },
      ],
    },
    {
      when: { at: "duel", duel: "yanjin-wen-chou" },
      lines: [
        { who: "wen-chou", mood: "angry", text: "네놈이 안량을 벤 관우냐! 그 목으로 갚아 주마!" },
        { who: "guan-yu", mood: "angry", text: "안량이 기다리고 있다. 너도 따라가거라." },
      ],
    },
    {
      when: { at: "defeated", unit: "wen-chou" },
      lines: [
        { who: "wen-chou", mood: "hurt", text: "미끼였단 말인가… 조조, 이 간사한 놈…!" },
        { text: "하북의 이름난 장수 안량과 문추가 잇달아 쓰러지자, 원소군의 기세가 크게 꺾였다." },
      ],
    },
    {
      when: { at: "duelEnd", duel: "yanjin-wen-chou" },
      lines: [{ who: "guan-yu", mood: "grim", text: "…이로써 조공께 진 빚은 다 갚았다." }],
    },
  ],
};

const B3: StoryBattleDef = {
  id: "guandu-camp",
  name: "관도 사수",
  place: "관도 진채",
  summary: "흙산 위 망루에서 화살이 쏟아진다. 9라운드가 밝을 때까지 진채를 지켜라.",
  map: GUANDU_CAMP,
  weather: "cloudy",
  landscape: AUTUMN,
  roundLimit: 12,
  sides: ["조조군", "원소군"],
  slots: cells([4, 8], [3, 8], [5, 8], [2, 8], [4, 7], [5, 7], [2, 6], [6, 9], [2, 9], [1, 7]),
  max: 8,
  required: ["cao-cao"],
  guests: [],
  // Archers on the earth mounds across the water, foot at the crossing and outside the east
  // gate. 고람 comes from the camps east of the mounds and must cross at 관도 itself;
  // 장합's riders come up our bank from downstream.
  enemies: [
    foe("shen-pei", "strategist", 16, 5, 1, heal),
    foe("yuan", "archer", 15, 1, 3, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 15, 3, 3, { id: "yuan-archer-2", ...hold }),
    foe("yuan", "archer", 15, 6, 3, { id: "yuan-archer-3", ...hold }),
    foe("yuan", "archer", 15, 14, 7, { id: "yuan-archer-4", ...hold }),
    foe("yuan", "catapult", 15, 14, 1, { id: "yuan-catapult-1" }),
    foe("yuan", "infantry", 15, 4, 2, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 15, 11, 8, { id: "yuan-infantry-2" }),
    foe("gao-lan", "infantry", 17, 17, 1, { arrives: 3 }),
    foe("yuan", "infantry", 15, 17, 2, { id: "yuan-infantry-3", arrives: 3 }),
    foe("yuan", "cavalry", 15, 17, 0, { id: "yuan-cavalry-1", arrives: 3 }),
    foe("zhang-he", "cavalry", 18, 17, 6, { arrives: 5 }),
    foe("yuan", "cavalry", 15, 17, 8, { id: "yuan-cavalry-2", arrives: 5 }),
    foe("yuan", "cavalry", 15, 17, 10, { id: "yuan-cavalry-3", arrives: 5 }),
  ],
  goals: [loseIf("cao-cao"), { side: 0, when: { kind: "hold", round: 9 } }],
  goalText: { win: "9라운드까지 진채 사수", lose: "조조 퇴각" },
  rewards: { gold: 1200, items: ["sunzi"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { who: "shen-pei", text: "높은 곳에서 쏘아라. 조조는 굴속에 갇힌 쥐다." },
        {
          who: "liu-ye",
          mood: "smile",
          text: "제 수레가 돌을 던질 차례입니다. 망루부터 부숴 드리지요.",
        },
        {
          who: "cao-cao",
          text: "진채를 지킨다. 아홉 번째 라운드가 밝을 때까지 한 걸음도 물러서지 마라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "gao-lan" },
      lines: [{ who: "gao-lan", mood: "angry", text: "진채 문을 부숴라! 조조를 끌어내라!" }],
    },
    {
      when: { at: "arrived", unit: "zhang-he" },
      lines: [
        { who: "zhang-he", text: "주공의 명이다. 오늘 안에 조조의 진채를 깬다." },
        { who: "xiahou-dun", mood: "angry", text: "장합이 왔다! 문을 굳게 지켜라!" },
      ],
    },
    {
      when: { at: "round", round: 7 },
      lines: [{ who: "cao-cao", mood: "angry", text: "조금만 더 버텨라! 적의 기세가 흔들린다!" }],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [
        {
          text: "원소군은 끝내 진채를 깨지 못하고 물러났다. 그러나 조조군의 군량도 바닥을 드러내고 있었다.",
        },
      ],
    },
  ],
};

const B4: StoryBattleDef = {
  id: "wuchao",
  name: "오소 야습",
  place: "오소",
  summary: "원소군의 깃발을 들고 밤길을 달려왔다. 군량고(★)에 불을 지르거나 순우경을 베어라.",
  map: WUCHAO_DEPOT,
  weather: "wind",
  landscape: AUTUMN,
  roundLimit: 15,
  sides: ["조조군", "원소군"],
  // Up from the south by the byroads.
  slots: cells([9, 11], [10, 11], [8, 11], [11, 12], [9, 12], [8, 13], [7, 12], [10, 13]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("chunyu-qiong", "infantry", 19, 9, 0, guard),
    foe("yuan", "infantry", 16, 10, 2, { id: "yuan-infantry-1", ...guard }),
    foe("yuan", "infantry", 16, 9, 2, { id: "yuan-infantry-2", ...guard }),
    foe("yuan", "infantry", 16, 8, 2, { id: "yuan-infantry-3", ...guard }),
    foe("yuan", "archer", 16, 12, 1, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 16, 7, 1, { id: "yuan-archer-2", ...hold }),
    foe("yuan", "cavalry", 16, 9, 4, { id: "yuan-cavalry-1" }),
    foe("yuan", "archer", 16, 4, 3, { id: "yuan-archer-3" }),
    foe("yuan", "strategist", 15, 7, 0, { id: "yuan-strategist-1", ...heal }),
    // 원소's riders from his camp to the south-west: behind us.
    foe("jiang-qi", "cavalry", 17, 2, 13, { arrives: 3 }),
    foe("yuan", "cavalry", 16, 3, 13, { id: "yuan-cavalry-2", arrives: 3 }),
    foe("yuan", "cavalry", 16, 3, 12, { id: "yuan-cavalry-3", arrives: 3 }),
  ],
  goals: [
    loseIf("cao-cao"),
    { side: 0, when: { kind: "reach", side: 0, cells: cells([11, 1]) } },
    winBy("chunyu-qiong"),
  ],
  goalText: { win: "군량고(★) 도달 또는 순우경 격파", lose: "조조 퇴각" },
  rewards: { gold: 1500, items: ["bright-armour", "iron-shot"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "chunyu-qiong",
          mood: "angry",
          text: "뭐라? 원공의 군사라더니 조조라고? 꿈에서도 못 할 소리… 진채를 닫아라!",
        },
        {
          who: "cao-cao",
          mood: "angry",
          text: "군량고에 불을 질러라! 길을 막는 자는 순우경이라도 베어라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "jiang-qi" },
      lines: [
        {
          name: "전령",
          who: "cao",
          side: 0,
          mood: "grim",
          text: "적 기병이 뒤에서 다가옵니다! 군을 나누어 막으셔야…",
        },
        {
          who: "cao-cao",
          mood: "angry",
          text: "적이 바로 등 뒤에 닿거든 그때 말하라! 앞만 보고 쳐라!",
        },
      ],
    },
    {
      when: { at: "defeated", unit: "chunyu-qiong" },
      lines: [{ who: "yue-jin", mood: "angry", text: "순우경을 사로잡았다! 오소는 우리 것이다!" }],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [{ text: "군량고에 불길이 치솟았다. 원소군 만 대의 군량이 밤하늘을 붉게 물들였다." }],
    },
  ],
};

const B5: StoryBattleDef = {
  id: "guandu-final",
  name: "관도 결전",
  place: "원소 본진",
  summary: "오소가 불타 원소군이 흔들린다. 본진으로 쳐들어가 원소를 몰아내라.",
  map: YUAN_CAMP,
  weather: "sunny",
  landscape: AUTUMN,
  roundLimit: 20,
  sides: ["조조군", "원소군", "귀순군"],
  // Over the 관도 crossing, from the south.
  slots: cells(
    [4, 10],
    [5, 10],
    [3, 10],
    [5, 11],
    [4, 11],
    [6, 11],
    [3, 11],
    [6, 10],
    [2, 10],
    [7, 11],
    [7, 10],
  ),
  max: 9,
  required: ["cao-cao"],
  guests: [],
  // 장합 and 고람 come back from our camp, which they were sent to storm, to join us.
  allies: [
    { officer: "zhang-he", classId: "cavalry", level: 19, side: 2, x: 13, y: 10, arrives: 3 },
    { officer: "gao-lan", classId: "infantry", level: 18, side: 2, x: 12, y: 11, arrives: 3 },
  ],
  enemies: [
    foe("yuan-shao", "shockCavalry", 21, 16, 3, guard),
    foe("ju-shou", "tactician", 18, 17, 2, hold),
    foe("guo-tu", "strategist", 18, 15, 2, heal),
    foe("feng-ji", "strategist", 17, 17, 5, heal),
    foe("yuan-elite", "heavyInfantry", 19, 14, 4, { id: "yuan-elite-infantry-1", ...guard }),
    foe("yuan-elite", "heavyInfantry", 19, 17, 6, { id: "yuan-elite-infantry-2", ...guard }),
    foe("yuan-elite", "longbow", 17, 15, 5, { id: "yuan-elite-archer-1", ...hold }),
    foe("yuan", "catapult", 17, 16, 4, { id: "yuan-catapult-1", ...hold }),
    // The outer camps on the sand ridges, between us and the main camp.
    foe("yuan-tan", "cavalry", 18, 12, 5),
    foe("yuan", "archer", 17, 10, 3, { id: "yuan-archer-1" }),
    foe("yuan", "cavalry", 17, 11, 7, { id: "yuan-cavalry-1" }),
    foe("yuan", "infantry", 17, 14, 8, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 17, 9, 6, { id: "yuan-infantry-2" }),
    foe("yuan-elite", "shockCavalry", 18, 19, 7, { id: "yuan-elite-cavalry-1", arrives: 5 }),
    foe("yuan-elite", "shockCavalry", 18, 19, 8, { id: "yuan-elite-cavalry-2", arrives: 5 }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-shao")],
  goalText: { win: "원소 격파", lose: "조조 퇴각" },
  rewards: { gold: 2000, items: ["tiger-tally"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "yuan-shao",
          mood: "angry",
          text: "조조 따위에게 내가 질 리 없다! 곽도, 봉기, 진을 굳게 지켜라!",
        },
        { who: "ju-shou", mood: "grim", text: "주공… 신의 말을 한 번만 들으셨더라면…" },
        { who: "cao-cao", text: "오소는 불탔고 원소의 군은 흔들린다. 오늘 하북의 주인을 가린다!" },
      ],
    },
    {
      when: { at: "arrived", unit: "zhang-he" },
      lines: [
        {
          who: "zhang-he",
          side: 0,
          text: "조공! 장합과 고람이 공성 무기를 불사르고 왔습니다. 함께 원소를 칩시다!",
        },
        {
          who: "cao-cao",
          mood: "smile",
          text: "어두운 주인을 떠나 밝은 쪽을 택했구려. 그대들이 오니 천군을 얻은 듯하오!",
        },
        { who: "guo-tu", mood: "angry", text: "장합 이 배신자가…!" },
      ],
    },
    {
      when: { at: "arrived", unit: "yuan-elite-cavalry-1" },
      lines: [{ who: "yuan-tan", mood: "angry", text: "아버님을 지켜라! 정예 기병은 앞으로!" }],
    },
    {
      when: { at: "defeated", unit: "yuan-shao" },
      lines: [
        { who: "yuan-shao", mood: "hurt", text: "물러나라… 황하를 건너라!" },
        { text: "원소는 갑옷도 걸치지 못한 채 팔백 기를 이끌고 황하를 건너 달아났다." },
      ],
    },
  ],
};

// ---------------------------------------------------------------- stages (ADR 0063)

type At = [number, number];

const prop = (kind: PropKind, x: number, y: number, more: Partial<PropDef> = {}): PropDef => ({
  kind,
  at: { x, y },
  ...more,
});
/** A palisade: one fence post on each of these columns of a row. */
const palisade = (y: number, ...xs: number[]): PropDef[] => xs.map((x) => prop("fence", x, y));
/** An extra: a troop template in an army's colours, with its own name in the script. */
const extra = (
  id: string,
  officer: string,
  side: number,
  at?: At,
  facing?: Facing4,
  classId?: string,
): Actor => actor(id, side, at, facing, { officer, ...(classId ? { classId } : {}) });
/** These steps, one after another, only when `cond` holds. */
const when = (cond: Cond, ...cmds: Cmd[]): Cmd[] => cmds.map((c) => ({ ...c, if: cond }));
const light = (l: StageLight): Cmd => ({ do: "light", light: l });
const ours = { side: 0 };
const theirs = { side: 1 };
/** Indoors and on foot, mounted officers stand as infantry. */
const onFoot = { classId: "infantry" };

/** 허도: 조조's hall in winter, carpet up to his seat, braziers burning. */
const XU_COURT: StageDef = {
  id: "xu-court",
  light: "day",
  rows: [
    "##############",
    "#____rrrr____#",
    "#____rrrr____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "######==######",
  ],
  props: [
    prop("throne", 6, 1),
    prop("screen", 7, 1),
    prop("brazier", 4, 1),
    prop("brazier", 9, 1),
    prop("pillar", 3, 3),
    prop("pillar", 10, 3),
    prop("pillar", 3, 6),
    prop("pillar", 10, 6),
    prop("lantern", 1, 1),
    prop("lantern", 12, 1),
    prop("banner", 1, 7),
    prop("banner", 12, 7),
  ],
};

/** West of 백마: the ferry on the Yellow River, our camp, and the besieged town's west gate. */
const BAIMA_FERRY: StageDef = {
  id: "baima-ferry",
  light: "day",
  landscape: SPRING,
  rows: [
    "wwwwwwwwwwwwwwww",
    "wwwwwwwwbbwwwwww",
    "gg......bb..####",
    "g...........#=v#",
    "f...........#=v#",
    "ff..........==v#",
    "f...........#vv#",
    "gg..........#vv#",
    "ggg.....gg..####",
  ],
  props: [
    prop("gate", 12, 5, { solid: false }),
    prop("banner", 1, 3),
    prop("horse", 1, 7),
    prop("banner", 10, 3, theirs),
    prop("banner", 11, 8, theirs),
    prop("lantern", 14, 3),
  ],
};

/**
 * Inside 백마's west gate, its only one: the road out to the ferry and 하북, the sealed
 * gifts left by the gatehouse.
 */
const BAIMA_GATE: StageDef = {
  id: "baima-gate",
  light: "day",
  landscape: SPRING,
  rows: [
    "#vv..v....v.vv",
    "#v............",
    "#............v",
    "==============",
    "==============",
    "#.............",
    "#g...........v",
    "#gg........gvv",
    "#ggg.......ggg",
  ],
  props: [
    prop("gate", 0, 3, { solid: false }),
    prop("gate", 0, 4, { solid: false }),
    prop("lantern", 1, 2),
    prop("lantern", 1, 5),
    prop("chest", 2, 1),
    prop("chest", 3, 1),
    prop("banner", 3, 6),
    prop("well", 2, 6),
  ],
};

/**
 * South of the 연진 ferry: the road strewn with wagons and grain, and the dyke (the
 * southern slope) across the field, our men resting below it out of sight.
 */
const YANJIN_SLOPE: StageDef = {
  id: "yanjin-slope",
  light: "day",
  weather: "cloudy",
  landscape: SPRING,
  rows: [
    "wwwwwwddwwwwww",
    "gww...dd...wwg",
    "gg............",
    "g............g",
    "ff............",
    "ffhhhhhhhhhhhh",
    "f.............",
    "g............g",
    "gg.........ggg",
  ],
  props: [
    prop("cart", 5, 3),
    prop("cart", 8, 4),
    prop("grain", 4, 4),
    prop("grain", 7, 3),
    prop("grain", 6, 4),
    prop("horse", 12, 7),
    prop("horse", 13, 7),
    prop("banner", 11, 6),
  ],
};

/** The same road at dusk after the fight: the wagons gathered, the sealed gifts by the road. */
const YANJIN_DUSK: StageDef = {
  ...YANJIN_SLOPE,
  id: "yanjin-dusk",
  light: "dusk",
  props: [
    prop("cart", 4, 3),
    prop("cart", 5, 6),
    prop("chest", 10, 2),
    prop("chest", 11, 2),
    prop("lantern", 9, 2),
    prop("horse", 12, 6),
    prop("banner", 11, 6),
  ],
};

/**
 * 관도 in the eighth month: our palisade on the south bank of the 관도 water, the crossing
 * before its gate, 원소's tents stretched along the far bank.
 */
const GUANDU_PALISADE: StageDef = {
  id: "guandu-palisade",
  light: "day",
  landscape: AUTUMN,
  rows: [
    "gggggggggggggg",
    "g............g",
    "wwwwwwddwwwwww",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccc==cccccc",
  ],
  props: [
    prop("tent", 2, 0, theirs),
    prop("tent", 7, 0, theirs),
    prop("tent", 12, 0, theirs),
    prop("banner", 4, 0, theirs),
    prop("banner", 10, 0, theirs),
    ...palisade(3, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13),
    prop("gate", 6, 3, { solid: false }),
    prop("gate", 7, 3, { solid: false }),
    prop("banner", 4, 4),
    prop("banner", 9, 4),
    prop("tent", 1, 6),
    prop("tent", 12, 6),
    prop("rack", 2, 4),
    prop("cart", 10, 7),
    prop("grain", 3, 7),
    prop("grain", 4, 7),
  ],
};

/** 관도 in the ninth month: 원소's earth mounds across the water from our palisade, archers on top. */
const GUANDU_MOUNDS: StageDef = {
  id: "guandu-mounds",
  light: "day",
  weather: "cloudy",
  landscape: AUTUMN,
  rows: [
    "ghhhgggggghhhg",
    "hhhhhggggghhhh",
    "ghhhg....ghhhg",
    "wwwwwwddwwwwww",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccc==cccccc",
  ],
  props: [
    prop("banner", 1, 0, theirs),
    prop("banner", 12, 0, theirs),
    ...palisade(4, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13),
    prop("gate", 6, 4, { solid: false }),
    prop("gate", 7, 4, { solid: false }),
    prop("tent", 1, 7),
    prop("tent", 12, 7),
    // 유엽's stone-thrower.
    prop("cart", 8, 6),
    prop("rack", 11, 6),
    prop("grain", 2, 6),
  ],
};

/** 관도 at night: 조조's tent (a tent stage: cloth walls), the guards at its door, the camp gate. */
const GUANDU_NIGHT: StageDef = {
  id: "guandu-tent-night",
  walls: "tent",
  light: "night",
  rows: [
    "cccc######cccc",
    "cccc#____#cccc",
    "cccc#____#cccc",
    "cccc##__##cccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
    "cccccccccccccc",
  ],
  props: [
    prop("bed", 5, 1),
    prop("table", 8, 1),
    prop("lantern", 7, 1),
    prop("brazier", 3, 5),
    prop("brazier", 10, 5),
    prop("tent", 1, 4),
    prop("tent", 12, 4),
    // The palisade on the bottom row: a gate is drawn tall and would hide whoever stands behind it.
    ...palisade(8, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13),
    prop("gate", 6, 8, { solid: false }),
    prop("gate", 7, 8, { solid: false }),
    prop("lantern", 4, 7),
    prop("lantern", 9, 7),
  ],
};

/**
 * The night road to 오소: up from the south through the woods, a 원소 picket at the bridge
 * over the 제수, and the depot palisade beyond it, as on the battle map.
 */
const WUCHAO_ROAD: StageDef = {
  id: "wuchao-road",
  light: "night",
  weather: "wind",
  landscape: AUTUMN,
  rows: [
    "cccccccccccccc",
    "cccccccccccccc",
    "ff....gg....ff",
    "wwwwwwbbwwwwww",
    "ff.g......g.ff",
    "fg..........gf",
    "ff..........ff",
    "fffg......gfff",
    "ffff......ffff",
  ],
  props: [
    // The depot's stockade along its south side, its gate over the bridge.
    ...palisade(1, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13),
    prop("gate", 6, 1, { solid: false }),
    prop("gate", 7, 1, { solid: false }),
    prop("grain", 1, 0),
    prop("grain", 3, 0),
    prop("grain", 10, 0),
    prop("grain", 12, 0),
    prop("grain", 13, 0),
    prop("banner", 4, 0, theirs),
    prop("banner", 9, 0, theirs),
    // The picket's post at the bridgehead.
    prop("brazier", 9, 5),
    prop("banner", 9, 4, theirs),
  ],
};

/** 원소's great tent, and his siege engines outside facing 관도. */
const YUAN_TENT: StageDef = {
  id: "yuan-tent",
  walls: "tent",
  light: "night",
  rows: [
    "##########gggg",
    "#rrrrrrrr#gggg",
    "#rrrrrrrr#gfgg",
    "#rrrrrrrr#gggg",
    "####rr####gggg",
    "..............",
    "..............",
    "..............",
    "gggggggggggggg",
  ],
  props: [
    prop("throne", 4, 1),
    prop("screen", 5, 1),
    prop("brazier", 1, 1),
    prop("brazier", 8, 1),
    prop("banner", 3, 5, theirs),
    prop("banner", 6, 5, theirs),
    prop("tent", 12, 1, theirs),
    prop("cart", 9, 6),
    prop("cart", 12, 6),
  ],
};

/** The field after the battle: 원소's camp abandoned, the river he fled across. */
const FINAL_FIELD: StageDef = {
  id: "final-field",
  light: "dusk",
  landscape: AUTUMN,
  rows: [
    "wwwwwwwwwwwwww",
    "wwwwddwwwwwwww",
    "gg..dd....gggg",
    "g............g",
    "..............",
    "..............",
    "..............",
    "gg..........gg",
    "ggg........ggg",
  ],
  props: [
    prop("tent", 2, 3, theirs),
    prop("tent", 11, 3, theirs),
    prop("banner", 3, 2, theirs),
    prop("banner", 10, 2, theirs),
    prop("cart", 12, 6),
    prop("chest", 6, 3),
    prop("banner", 1, 6),
  ],
};

// ---------------------------------------------------------------- scenes

const PROLOGUE: SceneDef = {
  id: "prologue",
  title: "서장 · 하북의 그림자",
  place: "허도",
  when: "건안 4년(199) 겨울",
  backdrop: "court",
  stage: XU_COURT,
  cast: [
    actor("cao-cao", 0, [6, 2], "down", onFoot),
    actor("xu-chu", 0, [8, 2], "down"),
    actor("xun-yu", 0, [5, 3], "right"),
    actor("xun-you", 0, [5, 4], "right"),
    actor("xiahou-yuan", 0, [8, 3], "left"),
    actor("xu-huang", 0, [8, 4], "left"),
    actor("zhang-liao", 0, [8, 5], "left", { ...onFoot, if: notSpared }),
    // 여포 stands apart, by the door.
    actor("lu-bu", 0, [9, 7], "left", { ...onFoot, if: spared }),
    extra("runner", "cao", 0),
  ],
  script: [
    camera([7, 5], 1000),
    tell("건안 4년, 하북의 원소가 공손찬을 무너뜨리고 네 주를 손에 넣었다."),
    tell("그는 십만의 보병과 만 필의 기병을 모아, 황하 남쪽에서 천자를 모신 조조를 노렸다."),
    camera("cao-cao", 1300),
    walk("xun-yu", [6, 4]),
    face("xun-yu", "up"),
    act("xun-yu", "bow"),
    say(
      "xun-yu",
      "원소의 군세가 크다 하나, 명공께서는 도량과 결단, 용병과 덕에서 모두 앞서십니다.",
    ),
    say("xun-yu", "다만 우리 군량이 적으니 오래 끌 수는 없습니다.", "grim"),
    emote("cao-cao", "note"),
    say(
      "cao-cao",
      "원소는 뜻은 크나 지혜가 모자라고, 겉은 사나우나 속은 겁이 많다. 군사가 많아도 법도가 서지 않았지.",
      "smile",
    ),
    walk("xun-yu", [5, 3]),
    face("xun-yu", "right"),
    walk("cao-cao", [6, 3]),
    camera([6, 4], 1000),
    say("cao-cao", "관도에 진을 친다. 황하를 건너오는 자는 한 놈도 돌려보내지 않겠다."),
    all(
      act("xun-yu", "bow"),
      act("xun-you", "bow"),
      act("xiahou-yuan", "bow"),
      act("xu-huang", "bow"),
      act("xu-chu", "bow"),
      { ...act("zhang-liao", "bow"), if: notSpared },
    ),
    // 여포 swore an oath at 백문루; nobody here has forgotten whom he served before.
    ...when(
      spared,
      say("lu-bu", "원소의 십만이라. 백문루에서 맹세한 대로, 내 창으로 길을 열어 드리지.", "smile"),
      all(
        face("xiahou-yuan", "lu-bu"),
        face("xu-huang", "lu-bu"),
        face("xun-you", "lu-bu"),
        face("xu-chu", "lu-bu"),
      ),
      all(emote("xu-chu", "anger"), emote("xiahou-yuan", "sweat")),
      camera("lu-bu", 1400),
      pause(800),
      face("cao-cao", "lu-bu"),
      say("cao-cao", "그 맹세, 잊지 마시오. 나는 정원도 동탁도 아니오.", "smile"),
      emote("lu-bu", "..."),
      act("lu-bu", "bow"),
      all(
        face("xiahou-yuan", "left"),
        face("xu-huang", "left"),
        face("xun-you", "right"),
        face("xu-chu", "down"),
        face("cao-cao", "down"),
      ),
      camera([6, 4], 1000),
    ),
    pause(500),
    enter("runner", [6, 8], [6, 6], true),
    act("runner", "kneel"),
    tell("이듬해 봄, 원소의 선봉 안량이 황하를 건너 백마성을 에워쌌다."),
    emote("cao-cao", "!"),
  ],
};

const BEFORE_BAIMA: SceneDef = {
  id: "before-baima",
  title: "성동격서",
  place: "백마 서쪽",
  when: "건안 5년(200) 4월",
  backdrop: "field",
  stage: BAIMA_FERRY,
  cast: [
    actor("cao-cao", 0, [3, 4], "right"),
    actor("xun-you", 0, [2, 3], "right"),
    actor("xu-chu", 0, [2, 5], "right"),
    actor("zhang-liao", 0, [4, 6], "up", { if: notSpared }),
    actor("lu-bu", 0, [4, 6], "up", { if: spared }),
    // 관우 stands apart, looking north across the river to where his brother is.
    actor("guan-yu", 0, [6, 2], "up", { classId: "cavalry" }),
    actor("liu-yan", 0, [13, 4], "left"),
    extra("defender", "cao", 0, [13, 5], "left"),
    extra("yuan-1", "yuan", 1, [11, 5], "right"),
    extra("yuan-2", "yuan", 1, [11, 3], "right"),
    extra("yuan-3", "yuan", 1, [10, 6], "right", "archer"),
  ],
  script: [
    camera("cao-cao", 1300),
    walk("xun-you", [3, 3]),
    face("xun-you", "down"),
    face("cao-cao", "up"),
    say(
      "xun-you",
      "적이 많으니 정면으로 부딪쳐서는 안 됩니다. 연진으로 가 황하를 건너려는 듯 보이십시오.",
    ),
    emote("xun-you", "idea"),
    say(
      "xun-you",
      "원소는 반드시 군을 서쪽으로 돌릴 것입니다. 그 틈에 가벼운 군사로 백마를 치면 안량을 잡을 수 있습니다.",
    ),
    act("cao-cao", "nod"),
    say("cao-cao", "좋다. 원소가 서쪽을 보는 사이, 우리는 동쪽에서 안량의 목을 친다.", "smile"),
    face("guan-yu", "down"),
    walk("guan-yu", [4, 4]),
    face("guan-yu", "left"),
    face("cao-cao", "right"),
    say(
      "guan-yu",
      "조공께 입은 은혜를 아직 갚지 못했소. 안량은 이 관우가 맡겠소.",
      undefined,
      ours,
    ),
    ...when(
      notSpared,
      walk("zhang-liao", [4, 5]),
      face("zhang-liao", "up"),
      say("zhang-liao", "관 장군과 함께 선봉에 서겠습니다."),
    ),
    ...when(
      spared,
      say(
        "lu-bu",
        "안량? 하북에선 그런 자도 장수라 부르는가. 운장이 못 베면 내가 베어 주지.",
        "smile",
      ),
      face("guan-yu", "lu-bu"),
      emote("guan-yu", "..."),
      face("guan-yu", "left"),
    ),
    camera([12, 5], 1300),
    all(act("yuan-1", "attack"), fx("dust", [12, 5]), sound("hit")),
    act("defender", "hurt"),
    tell("백마성 안에서는 동군태수 유연이 성문을 굳게 닫고 버티고 있었다."),
    camera("liu-yan", 1500),
    say("liu-yan", "구원군은 아직인가… 성문이 오래 버티지 못한다!", "grim", ours),
  ],
  joins: [
    {
      officer: "guan-yu",
      classId: "cavalry",
      level: 18,
      gear: ["iron-ji", "lamellar"],
      guest: true,
    },
  ],
};

const AFTER_BAIMA: SceneDef = {
  id: "after-baima",
  title: "한수정후",
  place: "백마",
  when: "건안 5년 4월",
  backdrop: "field",
  stage: BAIMA_GATE,
  cast: [
    actor("guan-yu", 0, [6, 3], "down", { classId: "cavalry" }),
    actor("cao-cao", 0, [6, 5], "up"),
    actor("xu-chu", 0, [5, 6], "up"),
    actor("xu-huang", 0, [4, 4], "right"),
    actor("zhang-liao", 0, [8, 4], "left", { if: notSpared }),
    actor("xiahou-yuan", 0, [8, 6], "up"),
    actor("yu-jin", 0),
    actor("yue-jin", 0),
  ],
  script: [
    camera([6, 4], 1300),
    say("cao-cao", "운장, 그대의 무용은 천하에 짝이 없구려.", "smile"),
    act("guan-yu", "bow"),
    say("cao-cao", "조정에 아뢰어 그대를 한수정후로 삼게 하겠소.", "smile"),
    emote("guan-yu", "..."),
    say("guan-yu", "…받은 은혜가 또 하나 늘었구려.", "grim", ours),
    ...when(
      notSpared,
      face("zhang-liao", "cao-cao"),
      say("zhang-liao", "운장은 공을 세워 은혜를 갚은 뒤에 떠나겠다 했습니다.", "grim"),
    ),
    ...when(
      spared,
      face("xu-huang", "cao-cao"),
      say("xu-huang", "운장은 공을 세워 은혜를 갚으면 떠날 사람입니다.", "grim"),
    ),
    face("cao-cao", "guan-yu"),
    say("cao-cao", "알고 있다. 그 뜻이 의로우니 더 아깝구나.", "grim"),
    pause(600),
    camera([7, 4], 1000),
    tell(
      "조조는 백마의 백성을 거두어 황하를 따라 서쪽으로 물러났다. 크게 노한 원소는 문추와 유비에게 그 뒤를 쫓게 했다.",
    ),
    all(enter("yu-jin", [6, 8], [10, 4], true), enter("yue-jin", [7, 8], [11, 4], true)),
    face("cao-cao", "down"),
    all(act("yu-jin", "kneel"), act("yue-jin", "kneel")),
    say("yu-jin", "연진 쪽 적진을 흔들고 돌아왔습니다. 악진 장군도 함께입니다."),
    act("cao-cao", "nod"),
  ],
  joins: [
    { officer: "yu-jin", classId: "infantry", level: 15, gear: ["iron-ji", "lamellar"] },
    { officer: "yue-jin", classId: "infantry", level: 15, gear: ["ring-dao", "lamellar"] },
  ],
};

const BEFORE_YANJIN: SceneDef = {
  id: "before-yanjin",
  title: "길 위의 수레",
  place: "연진 남쪽 언덕",
  when: "건안 5년 4월",
  backdrop: "river",
  stage: YANJIN_SLOPE,
  cast: [
    actor("cao-cao", 0, [10, 6], "up"),
    actor("xun-you", 0, [11, 7], "up"),
    actor("xu-chu", 0, [9, 7], "up"),
    actor("guan-yu", 0, [12, 6], "left", { classId: "cavalry" }),
    // 우금 keeps watch over the road.
    actor("yu-jin", 0, [8, 5], "up"),
    extra("rider-1", "yuan", 1, undefined, undefined, "cavalry"),
    extra("rider-2", "yuan", 1, undefined, undefined, "cavalry"),
  ],
  script: [
    camera([7, 4], 1000),
    tell(
      "조조는 남쪽 언덕 아래에 군을 멈추고 말안장을 풀게 했다. 군량과 수레는 길 위에 그대로 늘어놓았다.",
    ),
    sound("hooves"),
    all(enter("rider-1", [6, 0], [5, 2]), enter("rider-2", [7, 0], [8, 2]), fx("dust", [7, 1])),
    emote("yu-jin", "!"),
    walk("yu-jin", [10, 5], true),
    face("yu-jin", "down"),
    camera("cao-cao", 1300),
    say("yu-jin", "적 기병이 점점 늘어납니다! 어서 진채로 물러나야 합니다!", "grim"),
    emote("xun-you", "note"),
    say("xun-you", "물러나다니요. 저것은 적을 낚을 미끼입니다.", "smile"),
    face("cao-cao", "up"),
    say("cao-cao", "적이 수레에 달려들어 대오가 흩어질 때까지 기다려라. 그때 단숨에 내려간다."),
    face("guan-yu", "cao-cao"),
    say("guan-yu", "문추는 안량과 이름을 나란히 하던 자요. 그는 내가 맡겠소.", undefined, ours),
    camera([6, 3], 1300),
    all(walk("rider-1", [4, 4]), walk("rider-2", [7, 3])),
    emote("rider-2", "heart"),
    pause(600),
    camera("cao-cao", 1300),
    emote("cao-cao", "..."),
  ],
};

/** 관우 leaves after 연진, one battle later than he did before ADR 0071, to be there for 문추. */
const PARTING: SceneDef = {
  id: "yanjin-parting",
  title: "각자의 주인",
  place: "연진",
  when: "건안 5년 4월",
  backdrop: "river",
  stage: YANJIN_DUSK,
  cast: [
    actor("guan-yu", 0, [7, 2], "down", { classId: "cavalry" }),
    actor("cao-cao", 0, [7, 4], "up"),
    actor("xu-chu", 0, [6, 5], "up"),
    actor("xu-huang", 0, [5, 3], "right"),
    actor("zhang-liao", 0, [9, 3], "left", { if: notSpared }),
    actor("xun-you", 0, [8, 5], "up"),
  ],
  script: [
    camera([7, 3], 1300),
    tell("싸움이 끝난 저녁, 붙잡힌 원소군이 문추의 뒤에 유비가 있었다고 털어놓았다."),
    emote("guan-yu", "!"),
    pause(600),
    act("guan-yu", "bow"),
    say(
      "guan-yu",
      "은혜는 갚았소. 형님께서 원소의 진중에 계시다 하니, 그리로 가야 하오.",
      undefined,
      ours,
    ),
    emote("xu-chu", "anger"),
    walk("xu-chu", [6, 4]),
    face("cao-cao", "xu-chu"),
    act("cao-cao", "shake"),
    emote("xu-chu", "sweat"),
    walk("xu-chu", [6, 5]),
    all(face("xu-chu", "up"), face("cao-cao", "up")),
    pause(800),
    say("cao-cao", "…붙잡지 않겠소. 사람마다 섬기는 주인이 있는 법이니.", "grim"),
    ...when(
      notSpared,
      walk("zhang-liao", [8, 2]),
      face("zhang-liao", "guan-yu"),
      say("zhang-liao", "운장, 몸 성히 가시오. 다음엔 싸움터에서 보겠구려.", "grim"),
      act("guan-yu", "nod"),
    ),
    face("guan-yu", "up"),
    sound("hooves"),
    all(leave("guan-yu", [7, 0]), camera([7, 1], 1300)),
    walk("cao-cao", [10, 3]),
    face("cao-cao", "up"),
    camera([10, 2], 1500),
    tell("관우는 받은 상을 모두 봉해 두고, 작별의 글을 남긴 채 떠났다."),
    emote("cao-cao", "..."),
  ],
  leaves: ["guan-yu"],
};

const AFTER_YANJIN: SceneDef = {
  id: "after-yanjin",
  title: "관도로",
  place: "관도",
  when: "건안 5년 8월",
  backdrop: "camp",
  stage: GUANDU_PALISADE,
  cast: [
    actor("cao-cao", 0, [6, 4], "up"),
    actor("xun-you", 0, [7, 4], "up"),
    actor("xu-chu", 0, [5, 5], "up"),
    actor("xiahou-dun", 0, undefined, undefined, { classId: "cavalry" }),
    actor("guo-jia", 0),
    actor("liu-ye", 0),
    actor("li-dian", 0),
  ],
  script: [
    camera([6, 3], 1300),
    say("cao-cao", "두 번 싸워 두 장수를 베었다. 하지만 원소의 본대는 아직 그대로다.", "smile"),
    face("xun-you", "cao-cao"),
    say("xun-you", "이제 관도로 돌아가 굳게 지키셔야 합니다. 원소는 대군을 몰고 올 것입니다."),
    face("xun-you", "up"),
    camera([7, 0], 1300),
    sound("horn"),
    tell("8월, 원소는 관도 북쪽에 진을 펼쳤다. 동서로 수십 리에 걸친 진채였다."),
    camera([6, 5], 1000),
    sound("hooves"),
    enter("xiahou-dun", [6, 8], [6, 6], true),
    all(face("cao-cao", "down"), face("xun-you", "down"), face("xu-chu", "down")),
    say("xiahou-dun", "후방은 정리했습니다. 이제 이 하후돈도 앞에 서겠소."),
    enter("guo-jia", [7, 8], [7, 6]),
    emote("guo-jia", "note"),
    say(
      "guo-jia",
      "원소는 계책은 많으나 결단을 못 합니다. 버티기만 하면 기회는 반드시 옵니다.",
      "smile",
    ),
    all(enter("liu-ye", [6, 8], [5, 7]), enter("li-dian", [7, 8], [8, 7])),
    face("liu-ye", "cao-cao"),
    say("liu-ye", "제가 돌을 던지는 수레를 만들어 두었습니다. 쓸 날이 곧 오겠지요."),
    face("li-dian", "cao-cao"),
    say("li-dian", "군량 수레와 함께 왔습니다. 많지는 않습니다만."),
    act("cao-cao", "nod"),
    all(
      act("xiahou-dun", "bow"),
      act("guo-jia", "bow"),
      act("liu-ye", "bow"),
      act("li-dian", "bow"),
    ),
  ],
  joins: [
    {
      officer: "xiahou-dun",
      classId: "cavalry",
      level: 17,
      gear: ["horse-lance", "lamellar"],
    },
    { officer: "guo-jia", classId: "strategist", level: 16, gear: ["feather-fan", "robe"] },
    { officer: "liu-ye", classId: "catapult", level: 16, gear: ["stone-shot", "leather"] },
    { officer: "li-dian", classId: "archer", level: 16, gear: ["horn-bow", "leather"] },
  ],
};

const BEFORE_GUANDU: SceneDef = {
  id: "before-guandu",
  title: "흙산과 망루",
  place: "관도 진채",
  when: "건안 5년 9월",
  backdrop: "camp",
  stage: GUANDU_MOUNDS,
  cast: [
    actor("cao-cao", 0, [6, 6], "up"),
    actor("xu-chu", 0, [6, 7], "up"),
    actor("xiahou-dun", 0, [3, 6], "up"),
    actor("liu-ye", 0, [9, 7], "up"),
    extra("shield-1", "cao", 0, [3, 5], "up"),
    extra("shield-2", "cao", 0, [10, 5], "up"),
    extra("crew", "cao", 0, [9, 6], "up"),
    extra("archer-1", "yuan", 1, [2, 1], "down", "archer"),
    extra("archer-2", "yuan", 1, [11, 1], "down", "archer"),
  ],
  script: [
    camera([6, 1], 1200),
    tell(
      "원소군은 흙을 쌓아 산을 만들고 그 위에 망루를 세웠다. 화살이 비처럼 진채 안으로 쏟아졌다.",
    ),
    all(act("archer-1", "attack"), act("archer-2", "attack"), sound("arrow")),
    all(
      fx("dust", [3, 5]),
      fx("dust", [10, 5]),
      act("shield-1", "guard"),
      act("shield-2", "guard"),
    ),
    sound("arrow"),
    act("shield-2", "hurt"),
    camera("cao-cao", 1300),
    walk("xiahou-dun", [5, 6], true),
    face("xiahou-dun", "cao-cao"),
    say(
      "xiahou-dun",
      "병사들이 방패를 머리에 이고 다닙니다. 이대로는 오래 버티지 못하오!",
      "angry",
    ),
    walk("liu-ye", [8, 7]),
    face("liu-ye", "cao-cao"),
    say("liu-ye", "벽력처럼 돌을 던지는 수레입니다. 저 망루쯤은 부술 수 있습니다.", "smile"),
    camera([10, 3], 1000),
    act("crew", "attack"),
    sound("stone"),
    all(fx("smoke", "archer-2"), fx("shake"), act("archer-2", "down")),
    all(act("shield-1", "cheer"), act("shield-2", "cheer"), emote("xiahou-dun", "!")),
    camera("cao-cao", 1300),
    say("cao-cao", "좋다. 진채를 지킨다. 원소가 지칠 때까지."),
  ],
};

const AFTER_GUANDU: SceneDef = {
  id: "after-guandu",
  title: "허유의 귀순",
  place: "관도 진채",
  when: "건안 5년 10월",
  backdrop: "night",
  stage: GUANDU_NIGHT,
  cast: [
    actor("cao-cao", 0, [6, 2], "down", onFoot),
    // The guard at 조조's door: 허저, and 전위 beside him if he came out of 완성.
    actor("xu-chu", 0, [5, 4], "down"),
    actor("dian-wei", 0, [8, 4], "down", { if: lived }),
    actor("chen-gong", 0, [11, 6], "left", { if: mercy }),
    // A speaking extra takes its troop id, so its lines come from the figure.
    extra("cao", "cao", 0, [5, 6], "down"),
    actor("xu-you", 0),
  ],
  script: [
    camera("cao-cao", 1500),
    emote("cao-cao", "..."),
    say("cao-cao", "군량이 한 달도 남지 않았다. 허도로 물러나야 하는가…", "grim"),
    say(
      "xun-yu",
      "지금 물러나면 형세가 무너집니다. 천하의 승부는 바로 이때입니다. 기이한 계책을 쓸 때가 반드시 옵니다.",
      undefined,
      { name: "순욱의 편지" },
    ),
    act("cao-cao", "nod"),
    camera([6, 6], 1200),
    enter("xu-you", [6, 8], [6, 6]),
    tell("그날 밤, 원소의 모사 허유가 홀로 조조의 진채를 찾아왔다."),
    face("cao", "xu-you"),
    emote("cao", "!"),
    say("cao", "멈춰라! 웬 놈이냐?", undefined, { name: "파수병", side: 0 }),
    say("xu-you", "남양의 허자원이 왔다고 맹덕에게 전하게.", "smile", ours),
    walk("cao", [7, 4], true),
    face("cao", "up"),
    act("cao", "kneel"),
    say("cao", "주공! 원소 진영의 허유라는 자가 찾아왔습니다!", undefined, {
      name: "파수병",
      side: 0,
    }),
    all(emote("cao-cao", "!"), act("cao-cao", "jump")),
    all(
      walk("cao-cao", [6, 5], true),
      walk("xu-chu", [5, 5]),
      { ...walk("dian-wei", [7, 5]), if: lived },
      camera([6, 5], 1400),
    ),
    tell("조조는 신을 신을 겨를도 없이 맨발로 뛰어나갔다."),
    act("cao-cao", "cheer"),
    say("cao-cao", "자원! 그대가 와 주었으니 내 일은 이루어진 거나 다름없소!", "smile"),
    ...when(
      lived,
      face("dian-wei", "xu-you"),
      say("dian-wei", "가까이 오지 마시오. 품에 칼이라도 숨겼다면 목이 먼저 떨어질 거요.", "grim"),
      emote("xu-you", "sweat"),
    ),
    act("cao-cao", "stand"),
    camera("xu-you", 1500),
    say("xu-you", "맹덕, 군량이 얼마나 남았소?", "smile", ours),
    say("cao-cao", "아직 일 년은 버틸 수 있소.", "smile"),
    act("xu-you", "shake"),
    say("xu-you", "농담은 그만두시오.", undefined, ours),
    emote("cao-cao", "sweat"),
    pause(900),
    say("cao-cao", "…실은 한 달 치뿐이오.", "grim"),
    emote("xu-you", "idea"),
    say(
      "xu-you",
      "오소에 원소의 군량 만 대가 쌓여 있소. 지키는 순우경은 술에 빠져 있지. 가벼운 군사로 불을 지르면 사흘 안에 원소는 스스로 무너질 것이오.",
      undefined,
      ours,
    ),
    ...when(
      mercy,
      walk("chen-gong", [7, 6]),
      face("chen-gong", "cao-cao"),
      say(
        "chen-gong",
        "허유는 욕심은 많아도 어리석지 않습니다. 처자가 옥에 갇혔으니 돌아갈 곳도 없지요.",
      ),
    ),
    camera("cao-cao", 1300),
    say("cao-cao", "내가 직접 간다. 원소군의 깃발을 들고, 말에는 재갈을 물려라!", "angry"),
    all(act("xu-chu", "bow"), act("cao", "bow"), { ...act("dian-wei", "bow"), if: lived }),
  ],
  joins: [{ officer: "xu-you", classId: "strategist", level: 15, gear: ["feather-fan", "robe"] }],
};

const BEFORE_WUCHAO: SceneDef = {
  id: "before-wuchao",
  title: "밤길",
  place: "오소로 가는 길",
  when: "건안 5년 10월",
  backdrop: "night",
  stage: WUCHAO_ROAD,
  cast: [
    // The picket and the soldier who answers him speak, so they go by their troop ids.
    extra("yuan", "yuan", 1, [8, 4], "down"),
    extra("cao", "cao", 0),
    extra("soldier-2", "cao", 0),
    actor("cao-cao", 0),
    actor("xu-chu", 0),
    actor("yue-jin", 0),
  ],
  script: [
    camera([7, 5], 1200),
    tell(
      "조조는 보병과 기병 오천을 이끌고 밤길을 달렸다. 병사들은 원소군의 깃발을 들고 나뭇가지를 한 다발씩 짊어졌다.",
    ),
    sound("march"),
    all(enter("cao", [6, 8], [6, 5]), enter("soldier-2", [7, 8], [7, 5])),
    all(enter("cao-cao", [6, 8], [6, 6]), enter("xu-chu", [7, 8], [7, 6])),
    enter("yue-jin", [6, 8], [6, 7]),
    walk("yuan", [6, 4]),
    face("yuan", "down"),
    emote("yuan", "?"),
    say("yuan", "거기 서라! 어느 부대냐?", undefined, { name: "원소군 초병", side: 1 }),
    say("cao", "원공의 명이오. 순우 장군을 도와 오소를 지키러 가는 길이오.", undefined, {
      name: "병사",
      side: 0,
    }),
    emote("yuan", "..."),
    act("yuan", "nod"),
    walk("yuan", [8, 4]),
    face("yuan", "down"),
    tell("누가 물으면 원공의 명으로 오소를 지키러 간다고 답했다. 의심하는 자는 아무도 없었다."),
    all(
      walk("cao", [6, 2]),
      walk("soldier-2", [7, 2]),
      walk("cao-cao", [6, 3]),
      walk("xu-chu", [7, 3]),
      walk("yue-jin", [6, 4]),
    ),
    camera("cao-cao", 1400),
    face("xu-chu", "cao-cao"),
    say("xu-chu", "주공 곁은 이 허저가 지킵니다. 마음 놓고 불을 지르십시오.", "smile"),
    act("cao-cao", "nod"),
    face("cao-cao", "up"),
    camera([7, 1], 1200),
    pause(700),
  ],
};

const AFTER_WUCHAO: SceneDef = {
  id: "after-wuchao",
  title: "무너지는 하북",
  place: "원소 본진",
  when: "건안 5년 10월",
  backdrop: "fire",
  stage: YUAN_TENT,
  cast: [
    actor("yuan-shao", 1, [4, 2], "down", onFoot),
    actor("guo-tu", 1, [6, 2], "left"),
    extra("guard", "yuan-elite", 1, [2, 3], "right"),
    extra("messenger", "yuan", 1),
    actor("zhang-he", 1, [10, 6], "down"),
    actor("gao-lan", 1, [12, 7], "left"),
  ],
  script: [
    camera("yuan-shao", 1300),
    sound("horn"),
    enter("messenger", [4, 8], [4, 3], true),
    act("messenger", "kneel"),
    tell("오소가 불탔다는 소식에 원소군은 크게 흔들렸다."),
    all(emote("yuan-shao", "!"), act("yuan-shao", "jump")),
    walk("guo-tu", [5, 2]),
    all(face("guo-tu", "yuan-shao"), face("yuan-shao", "guo-tu")),
    tell("곽도는 자기 계책이 틀린 것을 감추려 장합이 기뻐한다고 원소에게 거짓을 고했다."),
    emote("yuan-shao", "anger"),
    say("yuan-shao", "장합이 감히…! 당장 불러들여라!", "angry", theirs),
    all(walk("messenger", [10, 5], true), camera([10, 6], 1300)),
    face("messenger", "down"),
    act("messenger", "kneel"),
    face("zhang-he", "up"),
    pause(600),
    say("zhang-he", "주공께서 내 말을 듣지 않으시더니, 이제는 나를 의심하시는가.", "grim", theirs),
    walk("gao-lan", [11, 6]),
    face("gao-lan", "zhang-he"),
    say("gao-lan", "돌아가면 죽을 뿐이오. 공성 무기를 불사르고 조조에게 갑시다.", "grim", theirs),
    act("zhang-he", "nod"),
    leave("messenger", [0, 6], true),
    fire([9, 6], [12, 6]),
    all(sound("fire"), fx("smoke", [9, 6]), fx("smoke", [12, 6])),
    all(leave("zhang-he", [10, 8]), leave("gao-lan", [11, 8])),
    light("dusk"),
    tell("결전의 아침이 밝았다."),
  ],
};

const EPILOGUE: SceneDef = {
  id: "epilogue",
  title: "종장 · 불태운 편지",
  place: "관도",
  when: "건안 5년 10월",
  backdrop: "dawn",
  stage: FINAL_FIELD,
  cast: [
    actor("cao-cao", 0, [6, 5], "up"),
    actor("xun-you", 0, [4, 6], "up"),
    actor("xu-chu", 0, [5, 6], "up"),
    actor("guo-jia", 0, [8, 6], "up"),
    extra("soldier", "cao", 0, [7, 3], "left"),
    actor("zhang-he", 0, undefined, undefined, { classId: "cavalry" }),
    actor("gao-lan", 0),
  ],
  script: [
    camera([6, 4], 1000),
    tell("관도의 싸움에서 원소군은 칠만이 넘게 죽거나 항복했다."),
    walk("soldier", [6, 4]),
    face("soldier", "down"),
    act("soldier", "kneel"),
    tell(
      "조조는 원소의 진채에서 편지 한 묶음을 찾아냈다. 허도와 군중의 많은 이가 몰래 원소에게 보낸 편지였다.",
    ),
    all(emote("xu-chu", "anger"), emote("guo-jia", "!"), emote("xun-you", "...")),
    pause(800),
    camera("cao-cao", 1400),
    say(
      "cao-cao",
      "원소가 강할 때는 나조차 스스로를 지키지 못할까 두려웠다. 하물며 다른 이들이랴.",
    ),
    walk("soldier", [7, 3]),
    face("soldier", "left"),
    fire([6, 3]),
    all(sound("fire"), fx("smoke", [6, 3])),
    tell("조조는 편지를 읽지 않고 모두 불태우게 했다."),
    face("guo-jia", "cao-cao"),
    say("guo-jia", "이제 하북을 평정할 일만 남았습니다.", "smile"),
    sound("hooves"),
    all(enter("zhang-he", [13, 5], [7, 5]), enter("gao-lan", [13, 4], [8, 4])),
    face("cao-cao", "right"),
    all(act("zhang-he", "kneel"), act("gao-lan", "kneel")),
    say("zhang-he", "장합, 오늘부터 조공의 깃발 아래 서겠습니다.", undefined, ours),
    all(act("zhang-he", "stand"), act("gao-lan", "stand")),
    face("cao-cao", "up"),
    camera([5, 1], 1200),
    say("cao-cao", "원소는 아직 살아 있다. 하지만 오늘로 천하의 저울은 기울었다.", "smile"),
    tell("2년 뒤 원소는 병으로 세상을 떠났고, 조조는 마침내 북방을 하나로 묶었다."),
    tell("3장 관도 · 끝"),
  ],
  joins: [
    { officer: "zhang-he", classId: "cavalry", level: 19, gear: ["horse-lance", "lamellar"] },
    { officer: "gao-lan", classId: "infantry", level: 18, gear: ["ring-dao", "lamellar"] },
  ],
};

// ---------------------------------------------------------------- camp talk

const talk = (officer: string, text: string, mood?: Mood, cond?: Cond): CampTalk => ({
  officer,
  lines: [{ who: officer, text, ...(mood ? { mood } : {}) } satisfies Line],
  ...(cond ? { if: cond } : {}),
});

/** After 백마: 관우 (한수정후 now) stays for one more fight; 우금 and 악진 are back. */
const TALK_1: readonly CampTalk[] = [
  talk("guan-yu", "한수정후라… 과분한 이름이오. 받은 만큼은 갚고 떠나겠소.", "grim"),
  talk("xu-chu", "관우가 안량을 베는 걸 봤습니다. 다음 목은 이 허저 몫입니다!", "smile"),
  talk("xiahou-yuan", "백마 백성까지 거두어 가니 행렬이 깁니다. 뒤는 제가 살피지요."),
  talk("xu-huang", "운장과는 고향이 가까워 정이 깊었지요. 언젠가는 적으로 보겠군요.", "grim"),
  talk("xun-you", "원소는 화가 나면 서두릅니다. 서두르는 적은 미끼를 잘 뭅니다."),
  talk("yu-jin", "악진 장군과 강을 건너 원소의 진채 서른 곳을 불살랐습니다."),
  talk("yue-jin", "불 지르는 일이라면 언제든 맡겨 주십시오. 손이 빠릅니다.", "smile"),
  talk(
    "zhang-liao",
    "운장은 떠날 날을 세고 있을 겁니다. 그런 사람이라 붙잡고 싶은 것이지요.",
    "grim",
    notSpared,
  ),
  talk("lu-bu", "안량의 목을 관우에게 빼앗겼군. 다음엔 내가 먼저 달리겠소.", "angry", spared),
  talk(
    "dian-wei",
    "완성에서 입은 상처는 다 아물었습니다. 장막 앞은 제 자리입니다.",
    undefined,
    lived,
  ),
  talk(
    "chen-gong",
    "원소의 모사들은 서로 공을 다툽니다. 그 틈이 우리의 길입니다.",
    undefined,
    mercy,
  ),
];

/** 관도, the eighth month: 원소's camp stretches out of sight. */
const TALK_2: readonly CampTalk[] = [
  talk("xiahou-dun", "원소의 진채가 끝이 안 보이는군. 그래도 겁낼 것 없소, 맹덕."),
  talk("guo-jia", "원소에게는 질 까닭이 열 가지나 있지요. 하나씩 세어 드릴까요?", "smile"),
  talk("liu-ye", "수레 바퀴에 기름을 쳐 두었습니다. 돌이 날 날이 머지않았지요.", "smile"),
  talk("li-dian", "군량은 아껴 쓰라 일러 두었습니다. 그래도 오래가진 못합니다.", "grim"),
  talk("xun-you", "적은 많고 우리는 적습니다. 그러니 싸울 곳은 우리가 고릅니다."),
  talk("yu-jin", "목책을 두 겹으로 둘렀습니다. 쉽게는 못 넘어올 겁니다."),
  talk("zhang-liao", "적의 기병이 많습니다. 성급히 나가 싸우면 안 됩니다.", undefined, notSpared),
  talk("xu-huang", "운장은 끝내 형님 곁으로 갔군요. 다음엔 적으로 보겠지요.", "grim"),
  talk("lu-bu", "진채에 틀어박혀 기다리기만 하라니. 몸이 근질거려 못 견디겠소.", "angry", spared),
];

/** The night 허유 came: the raid on 오소 is ready. */
const TALK_3: readonly CampTalk[] = [
  talk("xu-you", "오소 가는 길은 내가 알지. 순우경은 오늘 밤에도 취해 있을 거요.", "smile"),
  talk("xu-chu", "말에 재갈을 다 물렸습니다. 숨소리 하나 새지 않을 겁니다."),
  talk("yue-jin", "오소의 진채 문은 제가 맨 먼저 넘겠습니다."),
  talk("xun-you", "진채는 조홍 장군과 제가 지킵니다. 마음 놓고 다녀오십시오."),
  talk("guo-jia", "허유를 의심하는 자가 많지만, 지금은 믿을 때입니다."),
  talk("xiahou-dun", "주공께서 직접 가신다니… 맹덕, 꼭 살아서 돌아오시오.", "grim"),
  talk(
    "chen-gong",
    "오소가 불타면 원소는 이 진채를 칠 겁니다. 문을 굳게 닫아 두지요.",
    undefined,
    mercy,
  ),
  talk("dian-wei", "오늘 밤은 주공 곁을 한 걸음도 떠나지 않겠습니다.", undefined, lived),
  talk("lu-bu", "몰래 불이나 지르러 간다니. 정면으로 치면 될 것을.", undefined, spared),
  talk(
    "zhang-liao",
    "밤길의 초소는 소리 없이 지나가야 합니다. 앞은 제가 서지요.",
    undefined,
    notSpared,
  ),
];

/** 오소 has burned; the last battle is at hand. */
const TALK_4: readonly CampTalk[] = [
  talk("xiahou-dun", "원소의 진이 흔들리고 있소. 오늘 끝장을 냅시다!", "angry"),
  talk("guo-jia", "원소는 결단하지 못합니다. 그 망설임이 오늘 그를 무너뜨리지요.", "smile"),
  {
    officer: "xu-you",
    lines: [
      { who: "xu-you", mood: "smile", text: "내 말이 맞았지요? 맹덕, 이 공은 잊지 마시오." },
      { who: "cao-cao", mood: "smile", text: "잊을 리가 있겠소, 자원." },
    ],
  },
  talk("yue-jin", "오소의 불길, 보셨습니까? 이번엔 원소의 본진 차례입니다!", "smile"),
  talk("xu-huang", "도끼를 갈아 두었습니다. 오늘은 원소의 깃대를 찍겠습니다."),
  talk("li-dian", "원소의 장수들이 등을 돌린다는 소문입니다. 하북이 무너집니다."),
  talk("lu-bu", "드디어 정면 승부로군. 원소의 목은 내가 가져오겠소.", "smile", spared),
  talk("zhang-liao", "적이 무너질 때 가장 빨리 달리는 자가 공을 세웁니다.", undefined, notSpared),
  talk("dian-wei", "원소의 정예가 아무리 많아도 주공께는 손끝 하나 못 댑니다.", undefined, lived),
  talk(
    "chen-gong",
    "하북의 인재들이 원소를 떠날 겁니다. 너그러이 받아 주십시오.",
    undefined,
    mercy,
  ),
];

// ---------------------------------------------------------------- chapter

export const GUANDU: ChapterDef = {
  id: "guandu",
  number: 3,
  name: "3장",
  title: "관도",
  years: "199–200",
  summary: "하북의 원소가 십만 대군을 일으켰다. 조조는 관도에서 운명을 건 싸움에 나선다.",
  gold: 500,
  floor: 14,
  next: "hebei",
  // What history did in chapter 2, for a fresh start here (ADR 0060).
  assumes: { xuzhou: "revenge", dianwei: "fell", lubu: "executed" },
  ending: {
    title: "관도 · 완",
    lead: "다섯 번의 싸움 끝에 하북의 대군이 무너졌다. 조조는 이 싸움으로 북방의 패권을 손에 넣는다.",
  },
  roster: [
    { officer: "cao-cao", classId: "cavalry", level: 16, gear: ["refined-dao", "lamellar"] },
    { officer: "xu-chu", classId: "martialArtist", level: 15, gear: ["ring-dao", "leather"] },
    { officer: "xiahou-yuan", classId: "archer", level: 15, gear: ["horn-bow", "leather"] },
    {
      officer: "zhang-liao",
      classId: "cavalry",
      level: 15,
      gear: ["iron-ji", "lamellar"],
      if: notSpared,
    },
    { officer: "xu-huang", classId: "infantry", level: 14, gear: ["ring-dao", "lamellar"] },
    { officer: "xun-you", classId: "strategist", level: 14, gear: ["feather-fan", "robe"] },
  ],
  steps: [
    { kind: "scene", scene: PROLOGUE },
    { kind: "scene", scene: BEFORE_BAIMA },
    { kind: "battle", battle: B1 },
    { kind: "scene", scene: AFTER_BAIMA },
    {
      kind: "camp",
      camp: {
        id: "camp-1",
        ground: CAMP_GROUND,
        shop: [
          "ring-dao",
          "iron-ji",
          "horn-bow",
          "feather-fan",
          "lamellar",
          "leather",
          "robe",
          "jade",
        ],
        talk: TALK_1,
      },
    },
    { kind: "scene", scene: BEFORE_YANJIN },
    { kind: "battle", battle: B2 },
    { kind: "scene", scene: PARTING },
    { kind: "scene", scene: AFTER_YANJIN },
    {
      kind: "camp",
      camp: {
        id: "camp-2",
        ground: CAMP_GROUND,
        shop: [
          "ring-dao",
          "refined-dao",
          "iron-ji",
          "horse-lance",
          "horn-bow",
          "strong-bow",
          "feather-fan",
          "stone-shot",
          "lamellar",
          "scale",
          "leather",
          "lacquer",
          "robe",
          "padded-robe",
          "jade",
          "fine-horse",
        ],
        talk: TALK_2,
      },
    },
    { kind: "scene", scene: BEFORE_GUANDU },
    { kind: "battle", battle: B3 },
    { kind: "scene", scene: AFTER_GUANDU },
    {
      kind: "camp",
      camp: {
        id: "camp-3",
        ground: CAMP_GROUND,
        shop: [
          "refined-dao",
          "horse-lance",
          "strong-bow",
          "crane-fan",
          "iron-shot",
          "scale",
          "lacquer",
          "padded-robe",
          "jade",
          "fine-horse",
          "seal-cord",
        ],
        talk: TALK_3,
      },
    },
    { kind: "scene", scene: BEFORE_WUCHAO },
    { kind: "battle", battle: B4 },
    { kind: "scene", scene: AFTER_WUCHAO },
    {
      kind: "camp",
      camp: {
        id: "camp-4",
        ground: CAMP_GROUND,
        shop: [
          "refined-dao",
          "horse-lance",
          "strong-bow",
          "crane-fan",
          "iron-shot",
          "scale",
          "lacquer",
          "padded-robe",
          "jade",
          "fine-horse",
          "seal-cord",
          "sunzi",
        ],
        talk: TALK_4,
      },
    },
    { kind: "battle", battle: B5 },
    { kind: "scene", scene: EPILOGUE },
  ],
};
