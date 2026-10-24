/**
 * Chapter 2 (여포) battles (ADR 0059): maps, armies, goals, talk and rewards.
 * 복양 and 완성 are escapes (조조 must reach the edge), 완성 decides 전위's
 * fate, and 하비 floods when an officer opens the sluice (ADR 0062). 진궁
 * fights for 여포 only if 서주 was put to the sword, and only then does 진등
 * keep his gate shut at 하비 (ADR 0060). Our own maps and numbers (ADR 0002).
 */
import type { Cell, Gambit, Goal, TerrainId, TileChange } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { Cond, StoryBattleDef, StoryUnit } from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
const winBy = (unit: string): Goal => ({ side: 0, when: { kind: "defeat", units: [unit] } });
const holdUntil = (round: number): Goal => ({ side: 0, when: { kind: "hold", round } });
/** 조조 himself must stand on one of these tiles. */
const escapeTo = (...xy: [number, number][]): Goal => ({
  side: 0,
  when: { kind: "reach", side: 0, cells: cells(...xy), units: ["cao-cao"] },
});
const revenge: Cond = { flag: "xuzhou", is: "revenge" };
const mercy: Cond = { flag: "xuzhou", is: "mercy" };

/** An enemy unit; troop templates take an explicit id. */
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

/** A side-0 unit that comes with the battle. */
function guest(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> = {},
): StoryUnit {
  return { officer, classId, level, side: 0, x, y, ...extra };
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

const hold = { orders: ORDERS.hold };
const guard = { orders: ORDERS.guard };
const heal = { orders: ORDERS.healThenHold };
/** Press the attack: strike whatever is in reach, else close in (장수's men storming the gate). */
const CHARGE: readonly Gambit[] = [
  { when: { kind: "enemyInReach" }, behavior: { kind: "attackBest" } },
  { when: { kind: "always" }, behavior: { kind: "advance" } },
];
const charge = { orders: CHARGE };
/** 호거아 at 완성: he stole 전위's halberds, and now he goes for the man himself. */
const HUNT_DIAN_WEI: readonly Gambit[] = [
  {
    when: { kind: "enemyInReach", family: "martialArtist" },
    behavior: { kind: "attackFamily", family: "martialArtist" },
  },
  ...CHARGE,
];

/** The same terrain on every listed tile. */
const turn = (terrain: TerrainId, xy: readonly [number, number][]): TileChange[] =>
  xy.map(([x, y]) => ({ x, y, terrain }));

// ---------------------------------------------------------------- maps

/**
 * 팽성 (Pengcheng), as 『수경주』 has it: the 사수 runs south past the east
 * wall; the 획수 comes from the west, turns north at the south-west corner, runs
 * up the west side and along the north wall into the 사수 at the north-east
 * corner. Hills south of the city. 도겸's army stands before the west bridge;
 * the road east to 담 crosses the 사수 by the east gate.
 */
const PENGCHENG_MAP: MapDef = {
  id: "pengcheng",
  name: "팽성",
  rows: [
    "gg...ff...gg..g.ww.g",
    "g...fff..wwwwwwwww..",
    "gg..ff...w######ww..",
    "g...f....w#vvvv#ww..",
    "...ff....w#vFvv#ww..",
    "......gg.bvvvvvvbb..",
    ".....ggg.w#vvvv#ww..",
    "..hh...g.w##v###ww..",
    ".hhh...ffd......ww..",
    "......fffw.hhhh.ww.g",
    "wwwwwwwwwwhhhh..ww..",
    ".vv..g....hhh...ww..",
  ],
};

/**
 * 복양 성안 (inside Puyang): walled compounds and houses. 조조 came in by the
 * east gate and set it on fire to show he would not turn back; the way out is
 * back through that burning gate. 여포 rides in by the west gate, 장료 by the south.
 */
const PUYANG_CITY: MapDef = {
  id: "puyang-city",
  name: "복양 성안",
  rows: [
    "##################",
    "#vv.v...vv..v....#",
    "#...##.#.##.##...#",
    "#.v.##...##.##..v.",
    "#.................",
    "#v##.vv.##.vv.##.#",
    "#.##.vv.##.vv.##.#",
    "........f........#",
    "#vv.##.v##.##.vv.#",
    "#...##.v##.##....#",
    "#.v............v.#",
    "#######..#########",
  ],
};

/**
 * 승씨 (乘氏, Chengshi), where 조조 camped after 거야 (『자치통감』 권61). Our thin
 * camp with the great dike west of it and deep woods south of the dike (『위서』);
 * the north arm of the 제수 along the top into the 거야 marsh, the 하수 (菏水)
 * along the bottom. 여포 comes up from the south-east, from 동민.
 */
const DINGTAO_MAP: MapDef = {
  id: "dingtao",
  name: "승씨",
  rows: [
    "wwwwwwwwwwwwwwwwwwww",
    "..g.h#cc#..gg..wwwww",
    "....hcccc..gggg...ww",
    ".g..hcccc...ggg....w",
    "....hcccc.....gg....",
    "....h#cc#..gg.......",
    "..g.h......gg...hh..",
    "....h..gggg.....hh..",
    "fffff..gggg..gg.....",
    "ffffff........ggg...",
    "fffff...gg.....g....",
    "ffff...gggg.........",
    "ff.....gggg....gg...",
    "wwwwwwwwwwwwwddwwwww",
  ],
};

/**
 * 완성 (Wancheng): 조조 camped on the 육수 outside the town (『삼국지』 장수전).
 * The town's east wall and gate on the left; our palisade with its west gate
 * (전위's gate) facing the town and a back gate; the 육수 running south past
 * the town's east side, bending west along its south side. A ford in the
 * middle; upstream, the 과리진 (瓜里津) bridges on the road east to 무음.
 */
const WANCHENG_MAP: MapDef = {
  id: "wancheng",
  name: "완성",
  rows: [
    "vv#.......f...ww..",
    "v.#..v........bb.g",
    "vv#.h.......f.ww..",
    "v.#...######..ww..",
    "v.....#cccc#..ww..",
    "vv....#cccc#.fww..",
    "v.#...ccccc#..dd..",
    "vv#...#ccccc..ww.g",
    "v.#...#cccc#..ww..",
    "vv#...######.fww..",
    "v.#.v.......f.ww..",
    "wwwwwwwwwwwwwwww..",
  ],
};

/**
 * 기현 (Qi, 蘄 in 패국): the 환수 (渙水) flows south-east past the town's
 * south side (『수경주』 권30), crossed by the old stone bridge below the town's
 * south-west corner and by fords up and down stream. 조조 comes from the west.
 */
const QI_MAP: MapDef = {
  id: "qi",
  name: "기현",
  rows: [
    ".ff.ww....hhh.....",
    "fff.dd....hh######",
    ".....ww.....#vvvv#",
    "..gg..ww....#vvvv#",
    ".ggg...ww...vvvFv#",
    ".......ww...#vvvv#",
    "..ff....ww..#vvvv#",
    ".fff.....bb.###v##",
    "..........ww..g...",
    "..hh.......ww.ggg.",
    ".hhh........dd..g.",
    ".............ww...",
  ],
};

/**
 * 안중 (Anzhong): "between the two rivers" (『수경주』 권29). The 단수 (湍水)
 * along the south, the 열수 (涅水) along the north into the 안중항 pond, whose
 * dam is the narrows 유표's men hold; below it the 열수 runs down to the 단수.
 * 장수 comes after us from 양 in the west; low terraces along both banks.
 */
const ANZHONG_MAP: MapDef = {
  id: "anzhong",
  name: "안중",
  rows: [
    "g..hh...gg..wwwwww",
    "wwwwwwwwwwwwwwwwww",
    "hhh..hhh..hhwwwwww",
    "h......f....wwwwww",
    "...gg.....ffhhhhhh",
    "................w.",
    "......gg......g.w.",
    "h.....ff.....f..d.",
    "hhh..hhh..hhh...w.",
    "g.hhh..hhhh..hh.w.",
    "wwwwwwwwwwwwwwwwww",
    "g.hh..gg..hh..g..g",
  ],
};

/**
 * 하비 (Xiapi), as 『수경주』 권25 has it: the 사수 down the west side; the 기수
 * (沂水) comes from the north and parts at the north wall, one arm west into
 * the 사수, the other (the 소기수) round the east side and along the south past
 * the 이교 (圯) into the 사수. Three walls: 여포 holds the inner city; the south
 * gate is 백문. The dike with the sluice (7,2) on the 기수; the 태산 men cross
 * the 소기수 by the east bridge.
 */
const XIAPI_MAP: MapDef = {
  id: "xiapi",
  name: "하비",
  rows: [
    "..ww...hh.....ww.hh.",
    "..wwwwwwwwwwwwwwwwww",
    "..ww.hhbhhhhhhhhh..w",
    "..ww...............w",
    "..ww..g..####v####.w",
    "..ww.gg..#vv...vv#.w",
    "..dd.....#v.....v#.w",
    "..ww.....#..vFF..#.w",
    "..bb.....v..vFF..#.w",
    "..ww...ff#vv.v.vv#.w",
    "..ww..fff####v####.b",
    "..ww...............w",
    "..ww..gg...hh......w",
    "..wwwwwwwwwwwbwwwwww",
  ],
};

/**
 * The low ground round 하비's walls, under water once the sluice is open. The
 * raised roads stay dry: west to the west gate, south to the south gate, and
 * along the south bank and up the east side to the east gate that 진등 may open.
 */
const XIAPI_FLOOD: TileChange[] = [
  ...turn("water", [
    ...[3, 4, 5, 6, 7, 9, 10, 11].flatMap((y): [number, number][] => [
      [7, y],
      [8, y],
    ]),
    ...[9, 10, 11, 12, 14, 15, 16, 17, 18].map((x): [number, number] => [x, 3]),
    ...[9, 10, 11, 12, 14, 15, 16, 17].map((x): [number, number] => [x, 11]),
    ...[4, 5, 6, 7].map((y): [number, number] => [18, y]),
  ]),
  ...turn("ford", [
    [13, 3],
    [13, 11],
  ]),
];

// ---------------------------------------------------------------- battles

export const PENGCHENG: StoryBattleDef = {
  id: "pengcheng",
  name: "팽성의 원한",
  place: "서주 팽성",
  summary: "아버지의 원수 도겸의 군이 팽성 앞에 진을 쳤다. 서주의 장수 조표를 베어라.",
  map: PENGCHENG_MAP,
  weather: "cloudy",
  landscape: { region: "huai", season: "autumn" },
  roundLimit: 20,
  sides: ["조조군", "서주군"],
  slots: cells([1, 5], [1, 6], [0, 5], [0, 6], [1, 4], [1, 7], [0, 4], [0, 7]),
  max: 6,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    // 조표 holds the west gate behind the bridge over the 획수; 도겸 is in the city.
    foe("cao-bao", "cavalry", 12, 10, 5, guard),
    foe("tao-qian", "strategist", 11, 12, 4, guard),
    foe("xu", "infantry", 10, 6, 4, { id: "xu-infantry-1" }),
    foe("xu", "infantry", 10, 6, 6, { id: "xu-infantry-2" }),
    foe("xu", "infantry", 10, 5, 8, { id: "xu-infantry-3" }),
    foe("xu", "infantry", 11, 7, 5, { id: "xu-infantry-4", ...hold }),
    foe("xu", "archer", 10, 8, 3, { id: "xu-archer-1", ...hold }),
    foe("xu", "archer", 10, 8, 7, { id: "xu-archer-2", ...hold }),
    foe("xu", "cavalry", 10, 12, 8, { id: "xu-cavalry-1" }),
    foe("xu", "strategist", 10, 11, 5, { id: "xu-strategist-1", ...heal }),
    // From 담, over the 사수 by the east gate.
    foe("xu", "infantry", 11, 19, 4, { id: "xu-infantry-5", arrives: 3 }),
    foe("xu", "cavalry", 11, 19, 6, { id: "xu-cavalry-2", arrives: 3 }),
  ],
  goals: [loseIf("cao-cao"), winBy("cao-bao")],
  goalText: { win: "조표 격파", lose: "조조 퇴각" },
  rewards: { gold: 800, items: ["scale"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "cao-bao",
          mood: "smile",
          text: "흰 깃발을 앞세우고 왔구나. 제 무덤 자리를 보러 왔느냐!",
        },
        {
          who: "tao-qian",
          mood: "grim",
          text: "장개 그자가 저지른 일로… 서주가 피를 뒤집어쓰는구나.",
        },
        { who: "xiahou-dun", mood: "angry", text: "맹덕, 원수의 성이 바로 저기요. 길을 열겠소!" },
        {
          who: "cao-cao",
          mood: "angry",
          text: "아버님의 피값을 받으러 왔다. 조표를 베고 팽성을 무너뜨려라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "xu-infantry-5" },
      lines: [{ text: "사수 건너 동쪽에서 서주군 원군이 달려온다!" }],
    },
    {
      when: { at: "defeated", unit: "cao-bao" },
      lines: [
        { who: "cao-bao", mood: "hurt", text: "도 사군… 성을… 지키십시오…!" },
        { text: "조표가 무너지자 서주군은 흩어졌다. 도겸은 남은 군사를 이끌고 담으로 달아났다." },
      ],
    },
  ],
};

export const PUYANG_FIRE: StoryBattleDef = {
  id: "puyang-fire",
  name: "복양의 불길",
  place: "복양 성안",
  summary:
    "전씨의 밀서는 함정이었다. 불타는 복양성을 뚫고 조조를 불붙은 동문(★)으로 빠져나가게 하라.",
  map: PUYANG_CITY,
  weather: "wind",
  landscape: { region: "plain", season: "summer" },
  light: "night",
  roundLimit: 15,
  sides: ["조조군", "여포군"],
  // In the north of the city, where the 전씨 led us in.
  slots: cells([6, 1], [7, 1], [6, 2], [7, 3], [8, 3], [5, 1], [8, 2], [8, 1]),
  max: 6,
  required: ["cao-cao", "dian-wei"],
  guests: [],
  enemies: [
    foe("lu-bu", "cavalry", 15, 1, 7, { arrives: 2 }),
    foe("zhang-liao", "cavalry", 14, 8, 10, { arrives: 3 }),
    foe("gao-shun", "infantry", 14, 16, 6, hold),
    foe("chen-gong", "strategist", 13, 1, 6, { ...heal, if: revenge }),
    foe("lu", "infantry", 11, 6, 3, { id: "lu-infantry-1" }),
    foe("lu", "infantry", 11, 6, 6, { id: "lu-infantry-2" }),
    foe("lu", "infantry", 11, 11, 4, { id: "lu-infantry-3", ...hold }),
    foe("lu", "archer", 11, 2, 7, { id: "lu-archer-1", ...hold }),
    foe("lu", "archer", 11, 11, 3, { id: "lu-archer-2", ...hold }),
    foe("lu", "archer", 11, 13, 6, { id: "lu-archer-3", ...hold }),
    foe("lu", "cavalry", 11, 2, 10, { id: "lu-cavalry-1" }),
    foe("lu", "infantry", 11, 12, 10, { id: "lu-infantry-4", arrives: 3 }),
    foe("lu", "cavalry", 11, 6, 10, { id: "lu-cavalry-2", arrives: 3 }),
  ],
  goals: [loseIf("cao-cao"), escapeTo([17, 3], [17, 4])],
  goalText: { win: "조조가 동문(★)에 도달", lose: "조조 퇴각" },
  burning: [
    // The east gate 조조 fired himself; it burns all night (『헌제춘추』).
    { x: 17, y: 3, rounds: 15 },
    { x: 17, y: 4, rounds: 15 },
    { x: 9, y: 4, rounds: 4 },
    { x: 10, y: 4, rounds: 4 },
    { x: 14, y: 7, rounds: 3 },
    { x: 13, y: 8, rounds: 5 },
    { x: 13, y: 9, rounds: 5 },
    { x: 7, y: 7, rounds: 3 },
    { x: 9, y: 10, rounds: 4 },
    { x: 4, y: 5, rounds: 6 },
    { x: 4, y: 6, rounds: 6 },
    { x: 11, y: 10, rounds: 5 },
    { x: 12, y: 10, rounds: 5 },
  ],
  rewards: { gold: 800, items: ["lacquer"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          text: "동문에 불을 지르고 성 깊이 들어섰을 때, 거리마다 불길이 솟았다. 함정이었다.",
        },
        {
          who: "chen-gong",
          side: 1,
          mood: "grim",
          text: "문을 닫아라. 오늘 밤 조조는 이 성을 나가지 못한다.",
          if: revenge,
        },
        {
          who: "cao-cao",
          mood: "grim",
          text: "공대의 말이 옳았구나… 내 자만이 이 꼴을 만들었다.",
          if: mercy,
        },
        {
          who: "dian-wei",
          mood: "angry",
          text: "주공, 제 뒤에 붙으십시오. 동문까지 길을 뚫겠습니다!",
        },
        { who: "cao-cao", text: "멈추지 마라. 불타는 동문(★)으로 빠져나간다!" },
      ],
    },
    {
      when: { at: "round", round: 2 },
      lines: [
        { text: "연기 속에서 여포군 기병 하나가 조조의 투구를 움켜쥐었다. 얼굴을 모르는 것이다." },
        {
          who: "lu",
          name: "여포군 기병",
          side: 1,
          mood: "angry",
          text: "이봐! 조조는 어디 있느냐? 바른대로 대라!",
        },
        { who: "cao-cao", mood: "grim", text: "저, 저기 누런 말을 탄 자가 조조요!" },
        { text: "기병은 조조를 밀쳐 내고 누런 말을 쫓아 달려갔다." },
      ],
    },
    {
      when: { at: "arrived", unit: "lu-bu" },
      lines: [
        { text: "서쪽 거리에서 붉은 말 한 필이 불길을 뚫고 달려왔다. 여포다!" },
        {
          who: "lu-bu",
          side: 1,
          mood: "smile",
          text: "조조는 어디 있느냐! 그 목을 가져오는 자에게 천금을 주겠다!",
        },
        {
          who: "dian-wei",
          mood: "angry",
          text: "주공, 돌아보지 마십시오! 동문만 보고 달리십시오!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "zhang-liao" },
      lines: [
        { text: "남문으로 들어온 장료의 기병이 동문 쪽 골목으로 달려든다!" },
        { who: "gao-shun", mood: "angry", text: "동문을 막아라. 한 놈도 내보내지 마라!" },
      ],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [
        {
          text: "조조는 아직 타오르는 동문의 불길을 뚫고 나왔다. 왼손과 수염이 그을렸으나 목숨은 건졌다.",
        },
      ],
    },
  ],
};

export const DINGTAO: StoryBattleDef = {
  id: "dingtao",
  name: "정도의 둑",
  place: "연주 승씨",
  summary:
    "보리를 거두러 군사가 흩어진 사이 여포가 왔다. 복병과 함께 여포를 물리치거나 9라운드까지 버텨라.",
  map: DINGTAO_MAP,
  weather: "sunny",
  landscape: { region: "plain", season: "summer" },
  roundLimit: 20,
  sides: ["조조군", "여포군"],
  // The half of the army outside the dike, in and before the camp.
  slots: cells([6, 3], [7, 3], [6, 4], [7, 4], [6, 2], [7, 2], [8, 3], [8, 2], [5, 3]),
  max: 6,
  required: ["cao-cao"],
  // The hidden half behind the dike, who come over it once 여포 closes in.
  guests: [
    guest("cao", "infantry", 12, 2, 3, { id: "cao-infantry-1", arrives: 3 }),
    guest("cao", "infantry", 12, 2, 6, { id: "cao-infantry-2", arrives: 3 }),
    guest("cao", "archer", 12, 1, 4, { id: "cao-archer-1", arrives: 3 }),
    guest("cao", "cavalry", 12, 3, 7, { id: "cao-cavalry-1", arrives: 3 }),
  ],
  // Up from the south-east, from 동민, and from the camp ten li south.
  enemies: [
    foe("lu-bu", "cavalry", 16, 16, 11),
    foe("zhang-liao", "cavalry", 15, 14, 9),
    foe("gao-shun", "infantry", 15, 12, 10),
    foe("chen-gong", "strategist", 14, 18, 12, { ...heal, if: revenge }),
    foe("lu", "cavalry", 12, 17, 6, { id: "lu-cavalry-1" }),
    foe("lu", "cavalry", 12, 13, 12, { id: "lu-cavalry-2" }),
    foe("lu", "infantry", 12, 15, 7, { id: "lu-infantry-1" }),
    foe("lu", "infantry", 12, 11, 11, { id: "lu-infantry-2" }),
    foe("lu", "archer", 12, 17, 8, { id: "lu-archer-1" }),
    foe("lu", "archer", 12, 15, 12, { id: "lu-archer-2" }),
    foe("lu", "cavalry", 12, 13, 13, { id: "lu-cavalry-3", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("lu-bu"), holdUntil(9)],
  goalText: { win: "여포 격퇴 또는 9라운드까지 버티기", lose: "조조 퇴각" },
  rewards: { gold: 1000, items: ["strong-bow"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "군사 대부분이 보리를 거두러 나가, 진채에 남은 군사는 천 명이 되지 않았다." },
        {
          who: "lu-bu",
          side: 1,
          mood: "smile",
          text: "조조의 진채가 텅 비었구나! 단숨에 짓밟아라!",
        },
        {
          who: "chen-gong",
          side: 1,
          mood: "grim",
          text: "장군, 둑 뒤의 숲이 너무 조용합니다. 함부로 다가가지 마십시오.",
          if: revenge,
        },
        { who: "cao-cao", text: "진채를 지켜라. 둑 뒤의 군사가 일어설 때까지만 버티면 된다." },
      ],
    },
    {
      when: { at: "arrived", unit: "cao-infantry-1" },
      lines: [
        { text: "둑 뒤에서 북소리가 울리고, 숨어 있던 군사가 둑을 넘어 쏟아져 나왔다!" },
        {
          who: "cao-cao",
          mood: "smile",
          text: "지금이다! 숲의 군사는 여포의 옆구리를 쳐라!",
        },
        { who: "lu-bu", side: 1, mood: "angry", text: "복병이라고?! 물러서지 마라, 짓밟아라!" },
      ],
    },
    {
      when: { at: "arrived", unit: "lu-cavalry-3" },
      lines: [{ text: "남쪽 여울을 건너 여포의 기병이 더 달려온다!" }],
    },
    {
      when: { at: "round", round: 8 },
      lines: [{ who: "cao-cao", mood: "angry", text: "적의 기세가 꺾였다. 조금만 더 밀어붙여라!" }],
    },
    {
      when: { at: "defeated", unit: "lu-bu" },
      lines: [{ who: "lu-bu", side: 1, mood: "hurt", text: "흥… 오늘은 여기까지다. 물러나라!" }],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [
        {
          text: "여포는 남은 기병을 이끌고 동쪽으로 달아났다. 연주의 들판이 다시 조조의 것이 되었다.",
        },
      ],
    },
  ],
};

export const WANCHENG: StoryBattleDef = {
  id: "wancheng",
  name: "완성의 밤",
  place: "완성",
  summary:
    "장수가 등을 돌렸다. 전위가 문을 지키는 동안 조조를 육수 건너 동쪽 끝(★)까지 피신시켜라.",
  map: WANCHENG_MAP,
  weather: "cloudy",
  landscape: { region: "basin", season: "winter" },
  light: "night",
  roundLimit: 15,
  sides: ["조조군", "장수군"],
  slots: cells([10, 4], [6, 6], [7, 6], [8, 5], [8, 7], [9, 8], [7, 4], [10, 7]),
  max: 5,
  required: ["cao-cao", "dian-wei"],
  guests: [],
  // 장수's men came out of the town in arms, on the road through our camp (『오서』).
  enemies: [
    foe("zhang-xiu", "cavalry", 15, 3, 7),
    foe("jia-xu", "strategist", 15, 3, 6, guard),
    foe("hu-cheer", "martialArtist", 14, 4, 6, { orders: HUNT_DIAN_WEI }),
    foe("zhang", "infantry", 12, 5, 4, { id: "zhang-infantry-1" }),
    foe("zhang", "infantry", 12, 5, 8, { id: "zhang-infantry-2", ...charge }),
    foe("zhang", "infantry", 12, 4, 7, { id: "zhang-infantry-3", ...charge }),
    foe("zhang", "archer", 12, 3, 4, { id: "zhang-archer-1" }),
    foe("zhang", "archer", 12, 3, 8, { id: "zhang-archer-2" }),
    foe("zhang", "cavalry", 12, 7, 1, { id: "zhang-cavalry-1" }),
    foe("zhang", "archer", 12, 12, 10, { id: "zhang-archer-3", ...hold }),
    foe("zhang", "archer", 12, 12, 0, { id: "zhang-archer-4", ...hold }),
    foe("zhang", "infantry", 12, 4, 5, {
      id: "zhang-infantry-4",
      arrives: 2,
      orders: HUNT_DIAN_WEI,
    }),
    foe("zhang", "infantry", 12, 4, 8, {
      id: "zhang-infantry-6",
      arrives: 2,
      orders: HUNT_DIAN_WEI,
    }),
    foe("zhang", "cavalry", 12, 5, 0, { id: "zhang-cavalry-2", arrives: 3 }),
    foe("zhang", "infantry", 12, 10, 1, { id: "zhang-infantry-5", arrives: 3 }),
    foe("zhang", "cavalry", 12, 6, 10, { id: "zhang-cavalry-3", arrives: 4 }),
  ],
  goals: [
    loseIf("cao-cao"),
    escapeTo(...[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].map((y): [number, number] => [17, y])),
  ],
  goalText: { win: "조조가 동쪽 끝(★)에 도달", lose: "조조 퇴각" },
  fates: [
    {
      flag: "dianwei",
      unit: "dian-wei",
      standing: "lived",
      fallen: "fell",
      notes: {
        standing: "전위는 온몸에 상처를 입었으나 끝내 살아남았다.",
        fallen: "전위는 진채 문을 지키다 쓰러졌다. 끝내 한 걸음도 물러서지 않았다.",
      },
    },
  ],
  burning: [
    { x: 9, y: 4, rounds: 3 },
    { x: 10, y: 8, rounds: 4 },
    { x: 7, y: 2, rounds: 5 },
    { x: 12, y: 4, rounds: 4 },
  ],
  rewards: { gold: 800, items: [] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "깊은 밤, 완성 곳곳에서 불길과 함성이 솟았다. 장수가 다시 등을 돌린 것이다." },
        {
          who: "zhang-xiu",
          mood: "angry",
          text: "우리 집안을 욕보인 조조다! 한 놈도 살려 보내지 마라!",
        },
        { who: "jia-xu", text: "저 문만 열면 진채는 끝입니다. 문을 지키는 자부터 치십시오." },
        {
          who: "hu-cheer",
          mood: "smile",
          text: "흐흐, 쌍극은 내가 치워 두었다. 맨손으로 얼마나 버티나 보자!",
        },
        { who: "dian-wei", mood: "angry", text: "주공은 어서 동쪽으로! 이 문은 전위가 지킵니다!" },
        {
          who: "cao-cao",
          mood: "grim",
          text: "악래… 육수 건너(★)에서 기다리겠다. 반드시 살아서 오라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "zhang-cavalry-2" },
      lines: [{ text: "북쪽에서 장수의 기병이 진채 뒤로 돌아 들어온다!" }],
    },
    {
      when: { at: "defeated", unit: "dian-wei" },
      lines: [
        { who: "dian-wei", mood: "hurt", text: "주공… 무사히… 건너셨습니까…" },
        { text: "전위가 쓰러졌다. 그러나 적병들은 한참 동안 그 곁을 지나지 못했다." },
      ],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [{ text: "조조는 절영을 몰아 육수를 건넜다. 등 뒤에서 완성이 붉게 타올랐다." }],
    },
  ],
};

export const QI: StoryBattleDef = {
  id: "qi",
  name: "기현의 싸움",
  place: "패국 기현",
  summary: "원술은 회수를 건너 달아나고 장수들만 남았다. 빗속에서 강을 건너 교유를 베어라.",
  map: QI_MAP,
  weather: "rain",
  landscape: { region: "huai", season: "autumn" },
  roundLimit: 20,
  sides: ["조조군", "원술군"],
  slots: cells([3, 6], [3, 7], [2, 6], [2, 7], [3, 5], [4, 7], [2, 8], [3, 8], [2, 5]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  // 원술's generals hold the town's bank of the 환수 at its crossings.
  enemies: [
    foe("qiao-rui", "infantry", 17, 15, 5, guard),
    foe("zhang-xun", "cavalry", 16, 11, 7, hold),
    foe("ji-ling", "heavyInfantry", 17, 13, 6, hold),
    foe("zhong", "infantry", 14, 11, 6, { id: "zhong-infantry-1" }),
    foe("zhong", "infantry", 14, 14, 9, { id: "zhong-infantry-2" }),
    foe("zhong", "archer", 14, 9, 3, { id: "zhong-archer-1", ...hold }),
    foe("zhong", "archer", 14, 12, 8, { id: "zhong-archer-2", ...hold }),
    foe("zhong", "cavalry", 14, 8, 1, { id: "zhong-cavalry-1" }),
    foe("zhong", "strategist", 14, 14, 4, { id: "zhong-strategist-1", ...heal }),
    foe("zhong", "infantry", 15, 15, 7, { id: "zhong-infantry-4", ...guard }),
    foe("zhong", "infantry", 15, 17, 10, { id: "zhong-infantry-5", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("qiao-rui")],
  goalText: { win: "교유 격파", lose: "조조 퇴각" },
  rewards: { gold: 1200, items: ["great-ji"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "qiao-rui",
          text: "폐하께서 회수를 건너시는 동안 기현을 지키라 하셨다. 강을 내주지 마라!",
        },
        { who: "ji-ling", mood: "angry", text: "조조의 목은 이 기령이 거두겠다!" },
        {
          who: "guo-jia",
          mood: "smile",
          text: "원술이 장수들을 버리고 달아났으니, 버려진 군사는 오래 버티지 못합니다.",
        },
        { who: "cao-cao", text: "가짜 황제의 장수들이다. 다리를 건너 교유를 베어라!" },
      ],
    },
    {
      when: { at: "arrived", unit: "zhong-infantry-5" },
      lines: [{ text: "기현 동남쪽에서 원술군이 더 다가온다!" }],
    },
    {
      when: { at: "defeated", unit: "ji-ling" },
      lines: [{ who: "ji-ling", mood: "hurt", text: "이 빗속에서… 물러나라, 물러나!" }],
    },
    {
      when: { at: "defeated", unit: "qiao-rui" },
      lines: [
        { who: "qiao-rui", mood: "hurt", text: "우리를 버리고 간 자를 위해… 여기서 죽는구나…" },
        {
          text: "교유가 쓰러지자 원술의 장수들은 흩어졌다. 스스로 황제라 한 원술의 기세도 꺾였다.",
        },
      ],
    },
  ],
};

export const ANZHONG: StoryBattleDef = {
  id: "anzhong",
  name: "안중의 땅굴",
  place: "남양 안중",
  summary: "앞은 유표의 군이 막고 뒤에서는 장수가 쫓아온다. 땅굴로 보낸 복병과 함께 장수를 쳐라.",
  map: ANZHONG_MAP,
  weather: "cloudy",
  landscape: { region: "basin", season: "summer" },
  roundLimit: 20,
  sides: ["조조군", "장수·유표군"],
  slots: cells([8, 5], [9, 5], [8, 4], [9, 6], [8, 6], [10, 5], [7, 5], [10, 4], [9, 4]),
  max: 7,
  required: ["cao-cao"],
  // Out of the tunnel under the dam: two flank 장수 from the river banks, two come out behind 유표's men.
  guests: [
    guest("cao", "infantry", 14, 3, 2, { id: "cao-infantry-1", arrives: 3 }),
    guest("cao", "infantry", 14, 3, 9, { id: "cao-infantry-3", arrives: 3 }),
    guest("cao", "infantry", 14, 17, 4, { id: "cao-infantry-2", arrives: 3 }),
    guest("cao", "archer", 14, 15, 8, { id: "cao-archer-1", arrives: 3 }),
  ],
  enemies: [
    foe("zhang-xiu", "cavalry", 17, 0, 5, guard),
    foe("jia-xu", "strategist", 16, 0, 3, guard),
    foe("hu-cheer", "martialArtist", 16, 4, 6),
    foe("zhang", "infantry", 15, 4, 4, { id: "zhang-infantry-1" }),
    foe("zhang", "infantry", 15, 5, 5, { id: "zhang-infantry-2" }),
    foe("zhang", "cavalry", 15, 3, 3, { id: "zhang-cavalry-1" }),
    foe("zhang", "archer", 15, 2, 6, { id: "zhang-archer-1", ...hold }),
    // 유표's men on the 안중 dam and the neck below it.
    foe("jing", "infantry", 14, 14, 5, { id: "jing-infantry-1", ...hold }),
    foe("jing", "infantry", 14, 14, 7, { id: "jing-infantry-2", ...hold }),
    foe("jing", "archer", 14, 15, 6, { id: "jing-archer-1", ...hold }),
    foe("jing", "strategist", 14, 15, 4, { id: "jing-strategist-1", ...heal }),
  ],
  goals: [loseIf("cao-cao"), winBy("zhang-xiu")],
  goalText: { win: "장수 격파", lose: "조조 퇴각" },
  rewards: { gold: 1200, items: ["iron-bow"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { who: "zhang-xiu", mood: "smile", text: "앞은 유표, 뒤는 나다. 조조는 독 안에 든 쥐다!" },
        {
          who: "jia-xu",
          mood: "grim",
          text: "장군, 지나치게 쉽습니다. 조조는 퇴로를 닦아 두고서야 싸우는 자입니다.",
        },
        {
          who: "cao-cao",
          mood: "smile",
          text: "적이 돌아가는 군사를 막고 죽을 땅에서 싸우려 드니, 이길 것을 알겠다.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "cao-infantry-1" },
      lines: [
        { text: "밤새 둑 밑으로 판 땅굴을 빠져나온 군사가 적의 등 뒤에서 일어났다!" },
        { who: "zhang-xiu", mood: "angry", text: "뒤에서 군사가?! 언제 둑을 넘었단 말이냐!" },
      ],
    },
    {
      when: { at: "defeated", unit: "zhang-xiu" },
      lines: [
        { who: "zhang-xiu", mood: "hurt", text: "문화의 말을 들었어야 했다… 물러나라!" },
        { text: "장수와 유표의 군은 앞뒤로 무너졌다. 조조는 무사히 허로 돌아왔다." },
      ],
    },
  ],
};

export const XIAPI: StoryBattleDef = {
  id: "xiapi",
  name: "하비 수공",
  place: "서주 하비",
  summary:
    "여포가 하비성에 틀어박혔다. 북쪽 둑의 수문(★)을 열어 성을 물에 잠기게 하고 여포를 쳐라.",
  map: XIAPI_MAP,
  weather: "cloudy",
  landscape: { region: "huai", season: "winter" },
  roundLimit: 22,
  sides: ["조조군", "여포군", "유비군"],
  slots: cells([5, 6], [6, 7], [5, 7], [6, 8], [5, 8], [6, 9], [5, 9], [4, 7], [4, 8]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  allies: [
    ally("liu-bei", "infantry", 17, 6, 12),
    ally("guan-yu", "cavalry", 19, 7, 12),
    ally("zhang-fei", "cavalry", 18, 5, 12),
  ],
  enemies: [
    foe("lu-bu", "shockCavalry", 20, 13, 7, hold),
    foe("chen-gong", "tactician", 18, 14, 8, { ...heal, if: revenge }),
    foe("gao-shun", "heavyInfantry", 18, 10, 8, guard),
    // 고순's 함진영 stand where they are put; they never ride out after 조조.
    foe("xianzhen", "heavyInfantry", 16, 11, 8, { id: "xianzhen-infantry-1", ...guard }),
    foe("xianzhen", "heavyInfantry", 16, 13, 9, { id: "xianzhen-infantry-2", ...guard }),
    foe("zhang-liao", "cavalry", 18, 12, 9, hold),
    foe("hou-cheng", "infantry", 17, 13, 5, guard),
    foe("song-xian", "archer", 17, 12, 6, hold),
    foe("wei-xu", "infantry", 17, 7, 2, guard),
    foe("lu", "archer", 16, 8, 3, { id: "lu-archer-1", ...hold }),
    // 장패's 태산 men come down from 개양 in the north and cross the 소기수 by the east bridge.
    foe("zang-ba", "cavalry", 18, 19, 10, { arrives: 5 }),
    foe("lu", "cavalry", 16, 18, 11, { id: "lu-cavalry-1", arrives: 5 }),
  ],
  goals: [loseIf("cao-cao"), winBy("lu-bu")],
  goalText: { win: "여포 격파", lose: "조조 퇴각" },
  shifts: [
    {
      id: "flood",
      when: { kind: "reach", side: 0, cells: cells([7, 2]) },
      tiles: XIAPI_FLOOD,
      damage: 150,
    },
    {
      id: "east-gate",
      when: { kind: "round", round: 5 },
      tiles: turn("plain", [[17, 8]]),
      damage: 0,
      if: mercy,
    },
  ],
  rewards: { gold: 2000, items: ["bright-armour"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        { text: "얼어붙은 하비성. 여포는 성문을 닫아걸고 나오지 않았다." },
        {
          who: "lu-bu",
          side: 1,
          mood: "smile",
          text: "내게는 적토마와 방천화극이 있다. 누가 감히 이 성에 발을 들이겠느냐!",
        },
        {
          who: "chen-gong",
          side: 1,
          mood: "grim",
          text: "장군, 성 밖으로 나가 적의 진을 흔드셔야 합니다. 갇혀 있으면 죽습니다.",
          if: revenge,
        },
        {
          who: "liu-bei",
          side: 0,
          text: "관우와 장비를 데려왔습니다. 이번에는 여포를 놓치지 마십시오.",
        },
        {
          who: "guo-jia",
          text: "북쪽 둑의 수문(★)을 여십시오. 기수와 사수가 성을 삼킬 것입니다.",
        },
        { who: "cao-cao", text: "수문을 열어라! 물이 차면 여포의 기병도 발이 묶인다." },
      ],
    },
    {
      when: { at: "shifted", shift: "flood" },
      lines: [
        { text: "수문이 열리자 기수와 사수의 물이 성벽 아래로 쏟아져 들어왔다!" },
        { who: "lu-bu", side: 1, mood: "angry", text: "물이…! 말이 나갈 길이 없지 않으냐!" },
        {
          who: "cao-cao",
          mood: "smile",
          text: "이제 성으로 가는 길은 둑길뿐이다. 둑길을 따라 성문을 쳐라!",
        },
      ],
    },
    {
      when: { at: "shifted", shift: "east-gate" },
      if: mercy,
      lines: [
        { who: "chen-deng", side: 0, text: "진등입니다! 동문을 열었습니다. 어서 들어오십시오!" },
        { who: "cao-cao", mood: "smile", text: "원룡, 그대가 서주의 마음을 열어 주었구려." },
      ],
    },
    {
      when: { at: "arrived", unit: "zang-ba" },
      lines: [{ who: "zang-ba", mood: "angry", text: "하비가 위태롭다! 태산의 군사가 왔다!" }],
    },
    {
      when: { at: "defeated", unit: "gao-shun" },
      lines: [{ who: "gao-shun", mood: "hurt", text: "함진영은… 물러서지 않는다…" }],
    },
    {
      when: { at: "defeated", unit: "lu-bu" },
      lines: [
        { who: "lu-bu", side: 1, mood: "hurt", text: "적토마야… 여기까지란 말이냐…" },
        { text: "여포는 백문루로 물러났다. 물에 갇힌 성안에서 장수들이 하나둘 등을 돌렸다." },
      ],
    },
  ],
};
