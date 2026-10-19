/**
 * Story chapter 1: 관도 (Guandu, 199–200). Five battles from Cao Cao's side,
 * following the recorded campaign: the relief of Baima, the bait at Yanjin, the
 * siege of the Guandu camp, the night raid on Wuchao and the rout of Yuan Shao.
 * History is public domain; every line, map and number here is our own
 * (ADR 0002, ADR 0049).
 */
import type { Cell, Goal } from "@tk/rules";
import { type MapDef, WUCHAO } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { ChapterDef, StoryBattleDef } from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
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

/** 백마 (Baima): the walled town by the Yellow River; Yan Liang's camp to its south. */
const BAIMA: MapDef = {
  id: "baima",
  name: "백마",
  rows: [
    "wwwwwwwwwwwwwwwwww",
    "..gg....dd....wwww",
    ".ggg..ff.......gww",
    "......ff...#####.w",
    "..hh.......#vvv#..",
    ".hhh.......vvFv#..",
    "...........#vvv#..",
    "..ff...g...##v##..",
    ".fff..gg..........",
    "......g....cc.....",
    "..gg......cccc..h.",
    "..........ccc..hhh",
  ],
};

/** 연진 (Yanjin): the ford, the road strewn with Cao Cao's wagons, the southern slope. */
const YANJIN: MapDef = {
  id: "yanjin",
  name: "연진",
  rows: [
    "wwwwwwddwwwwwwwwww",
    "wwwww...wwwwwwmmmm",
    "gg.......g....hmmm",
    "g..ff.....g...hhmm",
    "..fff..c.....hh.hm",
    "......cc.......hhh",
    "..g...c..g.....h.h",
    ".gg.....cc....ff..",
    "..........c..fff..",
    "ff..g.........f...",
    "fff.....hh........",
    "ff.....hhh....gg..",
  ],
};

/** 관도 (Guandu): Cao Cao's palisaded camp facing Yuan Shao's earth mounds. */
const GUANDU_CAMP: MapDef = {
  id: "guandu-camp",
  name: "관도 진채",
  rows: [
    "ccc#....hh.....ccc",
    "ccc#...hhh....cccc",
    "cFc#....h.....cccc",
    "ccc...........gccc",
    "ccc#.....g.......c",
    "ccc#....hh........",
    "ccc#...hhh....g...",
    "ccc.....h.........",
    "ccc#.........hh...",
    "cFc#........hhh...",
    "ccc#.ff......h....",
    "ccc#ffff..........",
    "####fff...........",
  ],
};

/** 관도 북쪽 (north of Guandu): Yuan Shao's main camp with the river behind it. */
const YUAN_CAMP: MapDef = {
  id: "yuan-camp",
  name: "원소 본진",
  rows: [
    "wwwwwwwwwwwwwdddwwww",
    "..gg..........#####w",
    ".ggg..hh......#cccc#",
    "......hh......#cFcc#",
    "..ff...........cccc#",
    ".fff....g.....#cccc#",
    "........gg....###.##",
    "..hh..........g.....",
    ".hhh....ff..........",
    "........ff....hh....",
    "gg...........hhh..ff",
    "ggg..............fff",
    "gg......gg.......fff",
  ],
};

// ---------------------------------------------------------------- battles

const B1: StoryBattleDef = {
  id: "baima",
  name: "백마 구원",
  place: "백마",
  summary: "안량이 백마성을 에워쌌다. 성 안의 유연이 버티는 동안 안량을 베어라.",
  map: BAIMA,
  weather: "sunny",
  roundLimit: 20,
  sides: ["조조군", "원소군"],
  slots: cells([0, 3], [1, 3], [0, 4], [1, 4], [0, 5], [0, 6], [1, 6], [0, 7]),
  max: 7,
  required: ["cao-cao", "guan-yu"],
  guests: [{ officer: "liu-yan", classId: "infantry", level: 16, side: 0, x: 13, y: 5 }],
  enemies: [
    foe("yan-liang", "heavyInfantry", 16, 11, 10, hold),
    foe("yuan", "infantry", 13, 9, 5, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 13, 13, 8, { id: "yuan-infantry-2" }),
    foe("yuan", "archer", 13, 8, 4, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 13, 16, 6, { id: "yuan-archer-2" }),
    foe("yuan", "cavalry", 13, 8, 7, { id: "yuan-cavalry-1" }),
    foe("yuan", "strategist", 12, 12, 10, { id: "yuan-strategist-1", ...heal }),
    foe("yuan", "catapult", 12, 10, 9, { id: "yuan-catapult-1", ...hold }),
    foe("yuan", "cavalry", 13, 17, 9, { id: "yuan-cavalry-2", arrives: 4 }),
    foe("yuan", "cavalry", 13, 17, 8, { id: "yuan-cavalry-3", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), loseIf("liu-yan"), winBy("yan-liang")],
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
        { who: "zhang-liao", text: "기병이 뒤를 칩니다. 진형을 좁히십시오!" },
      ],
    },
    {
      when: { at: "defeated", unit: "yan-liang" },
      lines: [
        { who: "guan-yu", mood: "angry", text: "안량! 그 목을 받겠다!" },
        { text: "관우가 만군 사이를 꿰뚫고 달려 안량을 베었다. 원소군은 그 기세에 질려 무너졌다." },
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
  roundLimit: 20,
  sides: ["조조군", "원소군"],
  slots: cells([13, 2], [13, 3], [14, 3], [13, 4], [14, 4], [15, 4], [14, 5], [15, 5], [13, 6]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("wen-chou", "cavalry", 18, 1, 2, hold),
    foe("liu-bei", "cavalry", 16, 2, 4, hold),
    foe("yuan", "infantry", 15, 2, 2, { id: "yuan-infantry-4", ...hold }),
    foe("yuan", "archer", 15, 0, 3, { id: "yuan-archer-3", ...hold }),
    foe("yuan", "cavalry", 14, 7, 4, { id: "yuan-cavalry-1" }),
    foe("yuan", "cavalry", 14, 6, 6, { id: "yuan-cavalry-2" }),
    foe("yuan", "infantry", 14, 8, 7, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 14, 10, 8, { id: "yuan-infantry-2" }),
    foe("yuan", "archer", 14, 4, 5, { id: "yuan-archer-1" }),
    foe("yuan", "archer", 14, 9, 5, { id: "yuan-archer-2" }),
    foe("yuan", "cavalry", 14, 6, 0, { id: "yuan-cavalry-3", arrives: 3 }),
    foe("yuan", "infantry", 14, 7, 0, { id: "yuan-infantry-3", arrives: 3 }),
  ],
  goals: [loseIf("cao-cao"), winBy("wen-chou")],
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
      when: { at: "defeated", unit: "wen-chou" },
      lines: [
        { who: "wen-chou", mood: "hurt", text: "미끼였단 말인가… 조조, 이 간사한 놈…!" },
        { text: "하북의 이름난 장수 안량과 문추가 잇달아 쓰러지자, 원소군의 기세가 크게 꺾였다." },
      ],
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
  roundLimit: 12,
  sides: ["조조군", "원소군"],
  slots: cells([1, 1], [2, 2], [2, 3], [1, 4], [2, 4], [1, 5], [2, 6], [2, 7], [1, 8], [2, 9]),
  max: 8,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("shen-pei", "strategist", 16, 8, 1, heal),
    foe("yuan", "archer", 15, 9, 0, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 15, 8, 5, { id: "yuan-archer-2", ...hold }),
    foe("yuan", "archer", 15, 9, 6, { id: "yuan-archer-3", ...hold }),
    foe("yuan", "archer", 15, 13, 9, { id: "yuan-archer-4", ...hold }),
    foe("yuan", "catapult", 15, 12, 4, { id: "yuan-catapult-1" }),
    foe("yuan", "infantry", 15, 6, 3, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 15, 6, 7, { id: "yuan-infantry-2" }),
    foe("gao-lan", "infantry", 17, 17, 5, { arrives: 3 }),
    foe("yuan", "infantry", 15, 17, 6, { id: "yuan-infantry-3", arrives: 3 }),
    foe("yuan", "cavalry", 15, 17, 7, { id: "yuan-cavalry-1", arrives: 3 }),
    foe("zhang-he", "cavalry", 18, 17, 4, { arrives: 5 }),
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
  map: WUCHAO,
  weather: "wind",
  roundLimit: 15,
  sides: ["조조군", "원소군"],
  slots: cells([3, 5], [3, 4], [3, 6], [2, 3], [2, 5], [1, 6], [2, 7], [1, 4]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("chunyu-qiong", "infantry", 19, 15, 5, guard),
    foe("yuan", "infantry", 16, 13, 4, { id: "yuan-infantry-1", ...guard }),
    foe("yuan", "infantry", 16, 13, 5, { id: "yuan-infantry-2", ...guard }),
    foe("yuan", "infantry", 16, 13, 6, { id: "yuan-infantry-3", ...guard }),
    foe("yuan", "archer", 16, 14, 2, { id: "yuan-archer-1", ...hold }),
    foe("yuan", "archer", 16, 14, 7, { id: "yuan-archer-2", ...hold }),
    foe("yuan", "cavalry", 16, 11, 5, { id: "yuan-cavalry-1" }),
    foe("yuan", "archer", 16, 12, 10, { id: "yuan-archer-3" }),
    foe("yuan", "strategist", 15, 15, 7, { id: "yuan-strategist-1", ...heal }),
    foe("jiang-qi", "cavalry", 17, 0, 11, { arrives: 3 }),
    foe("yuan", "cavalry", 16, 0, 10, { id: "yuan-cavalry-2", arrives: 3 }),
    foe("yuan", "cavalry", 16, 1, 10, { id: "yuan-cavalry-3", arrives: 3 }),
  ],
  goals: [
    loseIf("cao-cao"),
    { side: 0, when: { kind: "reach", side: 0, cells: cells([14, 3]) } },
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
  roundLimit: 20,
  sides: ["조조군", "원소군"],
  slots: cells(
    [0, 4],
    [1, 4],
    [0, 5],
    [0, 6],
    [1, 6],
    [2, 6],
    [3, 6],
    [0, 7],
    [1, 7],
    [0, 8],
    [1, 9],
  ),
  max: 9,
  required: ["cao-cao"],
  guests: [
    { officer: "zhang-he", classId: "cavalry", level: 19, side: 0, x: 13, y: 8, arrives: 3 },
    { officer: "gao-lan", classId: "infantry", level: 18, side: 0, x: 12, y: 9, arrives: 3 },
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
    foe("yuan-tan", "cavalry", 18, 12, 4),
    foe("yuan", "archer", 17, 9, 2, { id: "yuan-archer-1" }),
    foe("yuan", "cavalry", 17, 10, 6, { id: "yuan-cavalry-1" }),
    foe("yuan", "infantry", 17, 11, 8, { id: "yuan-infantry-1" }),
    foe("yuan", "infantry", 17, 8, 4, { id: "yuan-infantry-2" }),
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

// ---------------------------------------------------------------- chapter

export const GUANDU: ChapterDef = {
  id: "guandu",
  name: "1장",
  title: "관도",
  years: "199–200",
  summary: "하북의 원소가 십만 대군을 일으켰다. 조조는 관도에서 운명을 건 싸움에 나선다.",
  gold: 500,
  roster: [
    { officer: "cao-cao", classId: "cavalry", level: 16, gear: ["refined-dao", "lamellar"] },
    { officer: "xu-chu", classId: "martialArtist", level: 15, gear: ["ring-dao", "leather"] },
    { officer: "xiahou-yuan", classId: "archer", level: 15, gear: ["horn-bow", "leather"] },
    { officer: "zhang-liao", classId: "cavalry", level: 15, gear: ["iron-ji", "lamellar"] },
    { officer: "xu-huang", classId: "infantry", level: 14, gear: ["ring-dao", "lamellar"] },
    { officer: "xun-you", classId: "strategist", level: 14, gear: ["feather-fan", "robe"] },
  ],
  steps: [
    {
      kind: "scene",
      scene: {
        id: "prologue",
        title: "서장 · 하북의 그림자",
        place: "허도",
        when: "건안 4년(199) 겨울",
        backdrop: "court",
        lines: [
          { text: "건안 4년, 하북의 원소가 공손찬을 무너뜨리고 네 주를 손에 넣었다." },
          {
            text: "그는 십만의 보병과 만 필의 기병을 모아, 황하 남쪽에서 천자를 모신 조조를 노렸다.",
          },
          {
            who: "xun-yu",
            text: "원소의 군세가 크다 하나, 명공께서는 도량과 결단, 용병과 덕에서 모두 앞서십니다.",
          },
          { who: "xun-yu", mood: "grim", text: "다만 우리 군량이 적으니 오래 끌 수는 없습니다." },
          {
            who: "cao-cao",
            mood: "smile",
            text: "원소는 뜻은 크나 지혜가 모자라고, 겉은 사나우나 속은 겁이 많다. 군사가 많아도 법도가 서지 않았지.",
          },
          {
            who: "cao-cao",
            text: "관도에 진을 친다. 황하를 건너오는 자는 한 놈도 돌려보내지 않겠다.",
          },
          { text: "이듬해 봄, 원소의 선봉 안량이 황하를 건너 백마성을 에워쌌다." },
        ],
      },
    },
    {
      kind: "scene",
      scene: {
        id: "before-baima",
        title: "성동격서",
        place: "백마 서쪽",
        when: "건안 5년(200) 4월",
        backdrop: "field",
        lines: [
          {
            who: "xun-you",
            text: "적이 많으니 정면으로 부딪쳐서는 안 됩니다. 연진으로 가 황하를 건너려는 듯 보이십시오.",
          },
          {
            who: "xun-you",
            text: "원소는 반드시 군을 서쪽으로 돌릴 것입니다. 그 틈에 가벼운 군사로 백마를 치면 안량을 잡을 수 있습니다.",
          },
          {
            who: "cao-cao",
            mood: "smile",
            text: "좋다. 원소가 서쪽을 보는 사이, 우리는 동쪽에서 안량의 목을 친다.",
          },
          {
            who: "guan-yu",
            side: 0,
            text: "조공께 입은 은혜를 아직 갚지 못했소. 안량은 이 관우가 맡겠소.",
          },
          { who: "zhang-liao", text: "관 장군과 함께 선봉에 서겠습니다." },
          { text: "백마성 안에서는 동군태수 유연이 성문을 굳게 닫고 버티고 있었다." },
          {
            who: "liu-yan",
            side: 0,
            mood: "grim",
            text: "구원군은 아직인가… 성문이 오래 버티지 못한다!",
          },
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
      },
    },
    { kind: "battle", battle: B1 },
    {
      kind: "scene",
      scene: {
        id: "after-baima",
        title: "각자의 주인",
        place: "백마",
        when: "건안 5년 4월",
        backdrop: "field",
        lines: [
          { who: "cao-cao", mood: "smile", text: "운장, 그대의 무용은 천하에 짝이 없구려." },
          {
            who: "guan-yu",
            side: 0,
            text: "이제 은혜는 갚았소. 형님 유현덕께서 하북에 계시다 하니, 나는 그분께 돌아가야 하오.",
          },
          {
            who: "cao-cao",
            mood: "grim",
            text: "…붙잡지 않겠소. 사람마다 섬기는 주인이 있는 법이니.",
          },
          { text: "관우는 받은 상을 모두 봉해 두고, 작별의 글을 남긴 채 떠났다." },
          {
            text: "조조는 백마의 백성을 거두어 황하를 따라 서쪽으로 물러났다. 크게 노한 원소는 문추와 유비에게 그 뒤를 쫓게 했다.",
          },
          { who: "yu-jin", text: "연진 쪽 적진을 흔들고 돌아왔습니다. 악진 장군도 함께입니다." },
        ],
        leaves: ["guan-yu"],
        joins: [
          { officer: "yu-jin", classId: "infantry", level: 15, gear: ["iron-ji", "lamellar"] },
          { officer: "yue-jin", classId: "infantry", level: 15, gear: ["ring-dao", "lamellar"] },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "camp-1",
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
      },
    },
    {
      kind: "scene",
      scene: {
        id: "before-yanjin",
        title: "길 위의 수레",
        place: "연진 남쪽 언덕",
        when: "건안 5년 4월",
        backdrop: "river",
        lines: [
          {
            text: "조조는 남쪽 언덕 아래에 군을 멈추고 말안장을 풀게 했다. 군량과 수레는 길 위에 그대로 늘어놓았다.",
          },
          {
            who: "yu-jin",
            mood: "grim",
            text: "적 기병이 점점 늘어납니다! 어서 진채로 물러나야 합니다!",
          },
          { who: "xun-you", mood: "smile", text: "물러나다니요. 저것은 적을 낚을 미끼입니다." },
          {
            who: "cao-cao",
            text: "적이 수레에 달려들어 대오가 흩어질 때까지 기다려라. 그때 단숨에 내려간다.",
          },
        ],
      },
    },
    { kind: "battle", battle: B2 },
    {
      kind: "scene",
      scene: {
        id: "after-yanjin",
        title: "관도로",
        place: "관도",
        when: "건안 5년 8월",
        backdrop: "camp",
        lines: [
          {
            who: "cao-cao",
            mood: "smile",
            text: "두 번 싸워 두 장수를 베었다. 하지만 원소의 본대는 아직 그대로다.",
          },
          {
            who: "xun-you",
            text: "이제 관도로 돌아가 굳게 지키셔야 합니다. 원소는 대군을 몰고 올 것입니다.",
          },
          { text: "8월, 원소는 관도 북쪽에 진을 펼쳤다. 동서로 수십 리에 걸친 진채였다." },
          { who: "xiahou-dun", text: "후방은 정리했습니다. 이제 이 하후돈도 앞에 서겠소." },
          {
            who: "guo-jia",
            mood: "smile",
            text: "원소는 계책은 많으나 결단을 못 합니다. 버티기만 하면 기회는 반드시 옵니다.",
          },
          {
            who: "liu-ye",
            text: "제가 돌을 던지는 수레를 만들어 두었습니다. 쓸 날이 곧 오겠지요.",
          },
          { who: "li-dian", text: "군량 수레와 함께 왔습니다. 많지는 않습니다만." },
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
      },
    },
    {
      kind: "camp",
      camp: {
        id: "camp-2",
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
      },
    },
    {
      kind: "scene",
      scene: {
        id: "before-guandu",
        title: "흙산과 망루",
        place: "관도 진채",
        when: "건안 5년 9월",
        backdrop: "camp",
        lines: [
          {
            text: "원소군은 흙을 쌓아 산을 만들고 그 위에 망루를 세웠다. 화살이 비처럼 진채 안으로 쏟아졌다.",
          },
          {
            who: "xiahou-dun",
            mood: "angry",
            text: "병사들이 방패를 머리에 이고 다닙니다. 이대로는 오래 버티지 못하오!",
          },
          {
            who: "liu-ye",
            mood: "smile",
            text: "벽력처럼 돌을 던지는 수레입니다. 저 망루쯤은 부술 수 있습니다.",
          },
          { who: "cao-cao", text: "좋다. 진채를 지킨다. 원소가 지칠 때까지." },
        ],
      },
    },
    { kind: "battle", battle: B3 },
    {
      kind: "scene",
      scene: {
        id: "after-guandu",
        title: "허유의 귀순",
        place: "관도 진채",
        when: "건안 5년 10월",
        backdrop: "night",
        lines: [
          {
            who: "cao-cao",
            mood: "grim",
            text: "군량이 한 달도 남지 않았다. 허도로 물러나야 하는가…",
          },
          {
            name: "순욱의 편지",
            who: "xun-yu",
            text: "지금 물러나면 형세가 무너집니다. 천하의 승부는 바로 이때입니다. 기이한 계책을 쓸 때가 반드시 옵니다.",
          },
          { text: "그날 밤, 원소의 모사 허유가 홀로 조조의 진채를 찾아왔다." },
          { who: "xu-you", side: 0, mood: "smile", text: "맹덕, 군량이 얼마나 남았소?" },
          { who: "cao-cao", mood: "smile", text: "아직 일 년은 버틸 수 있소." },
          { who: "xu-you", side: 0, text: "농담은 그만두시오." },
          { who: "cao-cao", mood: "grim", text: "…실은 한 달 치뿐이오." },
          {
            who: "xu-you",
            side: 0,
            text: "오소에 원소의 군량 만 대가 쌓여 있소. 지키는 순우경은 술에 빠져 있지. 가벼운 군사로 불을 지르면 사흘 안에 원소는 스스로 무너질 것이오.",
          },
          {
            who: "cao-cao",
            mood: "angry",
            text: "내가 직접 간다. 원소군의 깃발을 들고, 말에는 재갈을 물려라!",
          },
        ],
        joins: [
          { officer: "xu-you", classId: "strategist", level: 15, gear: ["feather-fan", "robe"] },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "camp-3",
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
      },
    },
    {
      kind: "scene",
      scene: {
        id: "before-wuchao",
        title: "밤길",
        place: "오소로 가는 길",
        when: "건안 5년 10월",
        backdrop: "night",
        lines: [
          {
            text: "조조는 보병과 기병 오천을 이끌고 밤길을 달렸다. 병사들은 원소군의 깃발을 들고 나뭇가지를 한 다발씩 짊어졌다.",
          },
          {
            text: "누가 물으면 원공의 명으로 오소를 지키러 간다고 답했다. 의심하는 자는 아무도 없었다.",
          },
          {
            who: "xu-chu",
            mood: "smile",
            text: "주공 곁은 이 허저가 지킵니다. 마음 놓고 불을 지르십시오.",
          },
        ],
      },
    },
    { kind: "battle", battle: B4 },
    {
      kind: "scene",
      scene: {
        id: "after-wuchao",
        title: "무너지는 하북",
        place: "원소 본진",
        when: "건안 5년 10월",
        backdrop: "fire",
        lines: [
          { text: "오소가 불탔다는 소식에 원소군은 크게 흔들렸다." },
          { text: "곽도는 자기 계책이 틀린 것을 감추려 장합이 기뻐한다고 원소에게 거짓을 고했다." },
          {
            who: "zhang-he",
            side: 1,
            mood: "grim",
            text: "주공께서 내 말을 듣지 않으시더니, 이제는 나를 의심하시는가.",
          },
          {
            who: "gao-lan",
            side: 1,
            mood: "grim",
            text: "돌아가면 죽을 뿐이오. 공성 무기를 불사르고 조조에게 갑시다.",
          },
          { text: "결전의 아침이 밝았다." },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "camp-4",
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
      },
    },
    { kind: "battle", battle: B5 },
    {
      kind: "scene",
      scene: {
        id: "epilogue",
        title: "종장 · 불태운 편지",
        place: "관도",
        when: "건안 5년 10월",
        backdrop: "dawn",
        lines: [
          { text: "관도의 싸움에서 원소군은 칠만이 넘게 죽거나 항복했다." },
          {
            text: "조조는 원소의 진채에서 편지 한 묶음을 찾아냈다. 허도와 군중의 많은 이가 몰래 원소에게 보낸 편지였다.",
          },
          {
            who: "cao-cao",
            text: "원소가 강할 때는 나조차 스스로를 지키지 못할까 두려웠다. 하물며 다른 이들이랴.",
          },
          { text: "조조는 편지를 읽지 않고 모두 불태우게 했다." },
          { who: "guo-jia", mood: "smile", text: "이제 하북을 평정할 일만 남았습니다." },
          { who: "zhang-he", side: 0, text: "장합, 오늘부터 조공의 깃발 아래 서겠습니다." },
          {
            who: "cao-cao",
            mood: "smile",
            text: "원소는 아직 살아 있다. 하지만 오늘로 천하의 저울은 기울었다.",
          },
          { text: "2년 뒤 원소는 병으로 세상을 떠났고, 조조는 마침내 북방을 하나로 묶었다." },
          { text: "1장 관도 · 끝" },
        ],
        joins: [
          { officer: "zhang-he", classId: "cavalry", level: 19, gear: ["horse-lance", "lamellar"] },
          { officer: "gao-lan", classId: "infantry", level: 18, gear: ["ring-dao", "lamellar"] },
        ],
      },
    },
  ],
};

export const CHAPTERS: Readonly<Record<string, ChapterDef>> = { [GUANDU.id]: GUANDU };
