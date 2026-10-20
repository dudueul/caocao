/**
 * Story chapter 1: 황건의 난 (174–192). Cao Cao from his first post as a night
 * captain of Luoyang to the governorship of Yanzhou: the Yellow Turbans at
 * Changshe, the coalition against Dong Zhuo at Sishui Pass and the rout at
 * Xingyang, the Black Mountain bandits at Puyang and the Qingzhou Yellow
 * Turbans at Shouzhang. History is public domain (the Records of the Three
 * Kingdoms, the Book of the Later Han; a few scenes from the novel, said so in
 * the credits); every line, map and number here is our own (ADR 0002, ADR 0057).
 *
 * The scenes are staged by hand on small tile maps (ADR 0063): who stands
 * where, who comes in by which door, who bows, and where the camera looks.
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
  HALL,
  leave,
  pause,
  STREET,
  say,
  sound,
  strike,
  tell,
  walk,
} from "./stage.ts";
import type {
  Actor,
  CampTalk,
  ChapterDef,
  Cmd,
  Facing4,
  Mood,
  PropDef,
  PropKind,
  SceneDef,
  StageDef,
  StageLight,
  StoryBattleDef,
} from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
const winBy = (unit: string): Goal => ({ side: 0, when: { kind: "defeat", units: [unit] } });
const holdUntil = (round: number): Goal => ({ side: 0, when: { kind: "hold", round } });

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

function guest(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> = {},
): ScenarioUnit {
  return { officer, classId, level, side: 0, x, y, ...extra };
}

const hold = { orders: ORDERS.hold };
const guard = { orders: ORDERS.guard };
const heal = { orders: ORDERS.healThenHold };

// ---------------------------------------------------------------- maps

/** 낙양 북문 (Luoyang, the north gate): night streets, houses and the city wall. */
const LUOYANG: MapDef = {
  id: "luoyang",
  name: "낙양 북부",
  rows: [
    "####v#######",
    "....v.......",
    ".vv.....vv..",
    ".vv..f..vv..",
    "............",
    "..vv....vvv.",
    "..vv..f.vvv.",
    "............",
    ".vv..vv..ff.",
    ".vv..vv..ff.",
  ],
};

/** 장사 (Changshe): a small walled town in a sea of grass, the rebels camped all round it. */
const CHANGSHE: MapDef = {
  id: "changshe",
  name: "장사",
  rows: [
    "gggg..ggggg..ggggg",
    "g##v##ggggg..ggfff",
    "g#vvv#gg....gggfff",
    "gvvFvvggg...gggg..",
    "g#vvv#ggcc..ggg...",
    "g##v##gccc...gg...",
    "gggg.gggcc..ggg...",
    "..ggggggg...ggggg.",
    "..gg..ggg..cc.gg..",
    "......gg..ccc..ff.",
    "hh....g...cc...ff.",
    "hhh..........ffff.",
  ],
};

/** 사수관 (Sishui Pass): a road between mountains to a gated fort. */
const SISHUI: MapDef = {
  id: "sishui",
  name: "사수관",
  rows: [
    "mmmmmmhhh..hhmmmmm",
    "mmmmhhh......hmmmm",
    "mmhh.....ff....#cc",
    "mh.....f.ff....#cc",
    "h..............#cc",
    "...............Fcc",
    "..g............Fcc",
    "h.gg...........#cc",
    "mh....ff.......#cc",
    "mmh...ff....hh.#cc",
    "mmmhh......hhhmmmm",
    "mmmmmmhhh..hmmmmmm",
  ],
};

/** 형양 변수 (Xingyang, the Bian River): open country, woods and hills, the river behind. */
const XINGYANG: MapDef = {
  id: "xingyang",
  name: "변수",
  rows: [
    "..ff....hh....ww..",
    ".fff...hhh....ww.f",
    "..f..........dww..",
    "....gg.......ww..f",
    "...ggg..ff...ww...",
    "........ff..dw....",
    "..hh........ww..ff",
    ".hhh....gg..ww..ff",
    "..h.....gg..dw....",
    "....ff.......ww...",
    "...fff..hh...ww.hh",
    "....f...hh....w.hh",
  ],
};

/** 복양 (Puyang): our village to the south-west, the bandits' hill village to the north-east. */
const PUYANG: MapDef = {
  id: "puyang",
  name: "복양",
  rows: [
    "mmhh..ff....ffhhmm",
    "mhh..fff..v..ffhhm",
    "hh...ff..vvv...hhm",
    "h.....g...v.....hh",
    "..gg..........f.hh",
    "..ggg...ff...fff.h",
    "........ff...fff..",
    "..v........g......",
    ".vvv..hh...gg..ff.",
    "..v..hhh......fff.",
    "....hh.....hh..ff.",
    "..........hhh.....",
  ],
};

/** 수장 (Shouzhang): a camp on the open plain of Yanzhou, the river to the south. */
const SHOUZHANG: MapDef = {
  id: "shouzhang",
  name: "수장",
  rows: [
    "gggg....ffff....gggg",
    "gg.....ffff.......gg",
    "g.....hh..........gg",
    ".....hhh.....ff.....",
    "....................",
    "..ff.....cc.....ff..",
    "..ff....cccc....ff..",
    ".........cc.........",
    "....................",
    ".....ff.....hhh.....",
    "gg..fff......hh...gg",
    "ggg..............ggg",
    "gggg....wwww....gggg",
  ],
};

// ---------------------------------------------------------------- battles

const B1: StoryBattleDef = {
  id: "luoyang",
  name: "북문의 오색봉",
  place: "낙양 북부",
  summary: "금령을 어기고 밤길을 다니는 자를 잡아라. 상대는 권세 높은 환관 건석의 숙부다.",
  map: LUOYANG,
  weather: "cloudy",
  roundLimit: 12,
  sides: ["북부위", "건가"],
  slots: cells([4, 9], [3, 9], [7, 9], [8, 9], [3, 8], [4, 8]),
  max: 1,
  required: ["cao-cao"],
  guests: [
    guest("ward", "infantry", 2, 7, 7, { id: "ward-1" }),
    guest("ward", "archer", 2, 8, 7, { id: "ward-2" }),
  ],
  enemies: [
    foe("jian-tu", "infantry", 3, 4, 0, guard),
    foe("jianjia", "infantry", 2, 3, 2, { id: "jianjia-infantry-1" }),
    foe("jianjia", "infantry", 2, 6, 2, { id: "jianjia-infantry-2" }),
    foe("jianjia", "infantry", 2, 5, 4, { id: "jianjia-infantry-3" }),
    foe("jianjia", "infantry", 2, 4, 1, { id: "jianjia-infantry-4", ...guard }),
    foe("jianjia", "archer", 2, 7, 1, { id: "jianjia-archer-1" }),
    foe("jianjia", "archer", 2, 0, 1, { id: "jianjia-archer-2" }),
  ],
  goals: [loseIf("cao-cao"), winBy("jian-tu")],
  goalText: { win: "건도 체포", lose: "조조 퇴각" },
  rewards: { gold: 200, items: ["jade"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "jian-tu",
          mood: "smile",
          text: "북부위 따위가 감히 나를 막아? 내 조카가 누군지 모르느냐!",
        },
        { who: "cao-cao", mood: "angry", text: "금령은 귀천을 가리지 않는다. 오색봉을 들어라!" },
      ],
    },
    {
      when: { at: "defeated", unit: "jian-tu" },
      lines: [
        { who: "jian-tu", mood: "hurt", text: "네, 네 이놈… 조카가 가만두지 않을 것이다…!" },
        {
          text: "조조는 건도를 붙잡아 법대로 매를 쳤다. 그날 이후 낙양에서 금령을 어기는 자가 사라졌다.",
        },
      ],
    },
  ],
};

const B2: StoryBattleDef = {
  id: "changshe",
  name: "장사의 불길",
  place: "영천 장사",
  summary: "황건적이 풀숲에 진을 치고 황보숭을 에워쌌다. 바람이 분다. 불을 지르고 파재를 쳐라.",
  map: CHANGSHE,
  weather: "wind",
  roundLimit: 18,
  sides: ["관군", "황건적"],
  slots: cells([0, 7], [1, 7], [0, 8], [1, 8], [0, 9], [1, 9], [2, 9], [3, 9]),
  max: 5,
  required: ["cao-cao"],
  guests: [guest("huangfu-song", "strategist", 9, 3, 3), guest("zhu-jun", "infantry", 8, 3, 2)],
  enemies: [
    foe("bo-cai", "infantry", 9, 9, 5, guard),
    foe("huangjin", "infantry", 6, 6, 2, { id: "huangjin-infantry-1" }),
    foe("huangjin", "infantry", 6, 6, 4, { id: "huangjin-infantry-2" }),
    foe("huangjin", "infantry", 6, 4, 6, { id: "huangjin-infantry-3" }),
    foe("huangjin", "infantry", 5, 8, 7, { id: "huangjin-infantry-4" }),
    foe("huangjin", "archer", 6, 8, 3, { id: "huangjin-archer-1" }),
    foe("huangjin", "archer", 5, 11, 9, { id: "huangjin-archer-2" }),
    foe("huangjin", "strategist", 6, 8, 5, { id: "huangjin-strategist-1", ...heal }),
    foe("huangjin", "cavalry", 6, 12, 6, { id: "huangjin-cavalry-1" }),
    foe("huangjin", "infantry", 6, 17, 7, { id: "huangjin-infantry-5", arrives: 4 }),
    foe("huangjin", "infantry", 6, 17, 8, { id: "huangjin-infantry-6", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), loseIf("huangfu-song"), winBy("bo-cai")],
  goalText: { win: "파재 격파", lose: "조조 또는 황보숭 퇴각" },
  rewards: { gold: 400, items: ["horse-lance"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        { who: "bo-cai", mood: "smile", text: "관군은 성에 갇힌 쥐 신세다. 창천은 이미 죽었다!" },
        {
          who: "huangfu-song",
          text: "적이 풀에 기대어 진을 쳤으니 불을 놓기 좋소. 바람도 우리 편이오.",
        },
        {
          who: "cao-cao",
          text: "기도위 조조, 황보 장군을 도우러 왔소! 성 밖에서 적을 몰아붙이겠소.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "huangjin-infantry-5" },
      lines: [{ text: "동쪽에서 황건적 무리가 더 몰려온다!" }],
    },
    {
      when: { at: "defeated", unit: "bo-cai" },
      lines: [
        { who: "bo-cai", mood: "hurt", text: "황천이… 일어설 날이… 이렇게…" },
        { text: "불길과 함께 쏟아져 나온 관군에 황건적은 무너졌다. 목이 떨어진 자만 수만이었다." },
      ],
    },
  ],
};

const B3: StoryBattleDef = {
  id: "sishui",
  name: "사수관의 술",
  place: "사수관",
  summary: "동탁의 맹장 화웅이 관문을 지킨다. 연합군의 장수들과 함께 관문을 열고 화웅을 베어라.",
  map: SISHUI,
  weather: "cloudy",
  roundLimit: 16,
  sides: ["연합군", "동탁군"],
  slots: cells([1, 4], [2, 4], [0, 5], [1, 5], [2, 5], [0, 6], [1, 6], [1, 7]),
  max: 5,
  required: ["cao-cao"],
  guests: [
    guest("guan-yu", "cavalry", 12, 3, 5),
    guest("zhang-fei", "cavalry", 11, 3, 6),
    guest("liu-bei", "infantry", 10, 3, 4),
    guest("sun-jian", "cavalry", 12, 2, 3),
  ],
  enemies: [
    foe("hua-xiong", "shockCavalry", 13, 15, 5, guard),
    foe("dong", "heavyInfantry", 10, 15, 6, { id: "dong-infantry-1", ...guard }),
    foe("dong", "infantry", 9, 12, 4, { id: "dong-infantry-2" }),
    foe("dong", "infantry", 9, 12, 6, { id: "dong-infantry-3" }),
    foe("dong", "infantry", 9, 10, 5, { id: "dong-infantry-4" }),
    foe("dong", "archer", 9, 12, 9, { id: "dong-archer-1", ...hold }),
    foe("dong", "archer", 9, 13, 9, { id: "dong-archer-2", ...hold }),
    foe("dong", "cavalry", 9, 11, 7, { id: "dong-cavalry-1" }),
    foe("dong", "cavalry", 9, 8, 4, { id: "dong-cavalry-2" }),
    foe("dong", "catapult", 9, 16, 4, { id: "dong-catapult-1", ...guard }),
    foe("lu-bu", "cavalry", 20, 16, 6, { arrives: 6, ...hold }),
  ],
  goals: [loseIf("cao-cao"), winBy("hua-xiong")],
  goalText: { win: "화웅 격파", lose: "조조 퇴각" },
  rewards: { gold: 600, items: ["refined-dao"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "hua-xiong",
          mood: "smile",
          text: "연합군이라더니 오합지졸이로구나. 다음은 누가 목을 내놓으러 왔느냐!",
        },
        { who: "guan-yu", text: "제가 나가 화웅의 목을 베어 오겠소." },
        {
          who: "cao-cao",
          mood: "smile",
          text: "따끈한 술 한 잔을 따라 두겠소. 술이 식기 전에 다녀오시오.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "lu-bu" },
      lines: [
        {
          who: "lu-bu",
          side: 1,
          mood: "angry",
          text: "화웅이 아직도 저 무리를 못 치웠단 말이냐. 이 여봉선이 나서 주마!",
        },
        { who: "cao-cao", mood: "grim", text: "여포다! 맞서지 말고 화웅부터 쳐라!" },
      ],
    },
    {
      when: { at: "defeated", unit: "hua-xiong" },
      lines: [
        { who: "hua-xiong", mood: "hurt", text: "이, 이럴 수가…!" },
        { who: "guan-yu", mood: "smile", text: "술이 아직 따뜻하군요." },
        { text: "화웅이 쓰러지자 사수관을 지키던 동탁군은 낙양 쪽으로 물러났다." },
      ],
    },
  ],
};

const B4: StoryBattleDef = {
  id: "xingyang",
  name: "변수의 추격",
  place: "형양 변수",
  summary: "홀로 동탁을 쫓다 서영의 매복에 걸렸다. 해가 질 때(8라운드)까지 버티며 조조를 지켜라.",
  map: XINGYANG,
  weather: "sunny",
  roundLimit: 10,
  sides: ["조조군", "동탁군"],
  slots: cells([5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6], [6, 4], [7, 4]),
  max: 5,
  required: ["cao-cao"],
  guests: [guest("wei-zi", "infantry", 8, 4, 5), guest("bao-xin", "cavalry", 10, 8, 6)],
  enemies: [
    foe("xu-rong", "cavalry", 13, 1, 2, hold),
    foe("dong", "cavalry", 10, 10, 1, { id: "dong-cavalry-1" }),
    foe("dong", "cavalry", 10, 6, 10, { id: "dong-cavalry-2" }),
    foe("dong", "cavalry", 10, 10, 9, { id: "dong-cavalry-3" }),
    foe("dong", "infantry", 9, 3, 3, { id: "dong-infantry-1" }),
    foe("dong", "infantry", 9, 2, 8, { id: "dong-infantry-2" }),
    foe("dong", "archer", 9, 8, 2, { id: "dong-archer-1" }),
    foe("dong", "archer", 9, 9, 8, { id: "dong-archer-2" }),
    foe("dong", "cavalry", 10, 0, 5, { id: "dong-cavalry-4", arrives: 3 }),
    foe("dong", "cavalry", 10, 0, 6, { id: "dong-cavalry-5", arrives: 3 }),
    foe("dong", "cavalry", 10, 0, 3, { id: "dong-cavalry-6", arrives: 5 }),
    foe("dong", "cavalry", 10, 0, 9, { id: "dong-cavalry-7", arrives: 5 }),
    foe("dong", "infantry", 10, 0, 4, { id: "dong-infantry-3", arrives: 5 }),
  ],
  goals: [loseIf("cao-cao"), holdUntil(8)],
  goalText: { win: "8라운드까지 생존", lose: "조조 퇴각" },
  rewards: { gold: 600, items: ["lacquer"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        { who: "xu-rong", text: "조조라는 자가 겁도 없이 쫓아왔구나. 하나도 살려 보내지 마라!" },
        { who: "bao-xin", mood: "grim", text: "매복이오! 맹덕, 진형을 좁히시오!" },
        {
          who: "cao-cao",
          mood: "angry",
          text: "날이 저물 때까지만 버텨라. 어둠이 우리를 숨겨 줄 것이다!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "dong-cavalry-4" },
      lines: [
        { who: "cao-cao", mood: "hurt", text: "윽… 말이 쓰러졌다…!" },
        {
          who: "cao-hong",
          mood: "angry",
          text: "천하에 이 조홍은 없어도 되지만, 공은 없어서는 안 됩니다! 제 말을 타십시오!",
        },
      ],
    },
    {
      when: { at: "round", round: 7 },
      lines: [{ text: "해가 서산에 걸렸다. 조금만 더 버티면 된다." }],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [{ text: "날이 저물자 조조는 조홍과 함께 변수를 건너 어둠 속으로 빠져나갔다." }],
    },
  ],
};

const B5: StoryBattleDef = {
  id: "puyang",
  name: "흑산의 무리",
  place: "동군 복양",
  summary: "흑산적 백요가 동군을 휩쓴다. 산 위 마을에 버틴 백요를 쳐서 동군을 지켜라.",
  map: PUYANG,
  weather: "sunny",
  roundLimit: 18,
  sides: ["조조군", "흑산적"],
  slots: cells([1, 10], [2, 10], [3, 10], [0, 9], [1, 9], [3, 9], [0, 11], [1, 11], [2, 11]),
  max: 7,
  required: ["cao-cao"],
  guests: [],
  enemies: [
    foe("bai-rao", "martialArtist", 12, 10, 2, guard),
    foe("heishan", "infantry", 10, 9, 2, { id: "heishan-infantry-1", ...hold }),
    foe("heishan", "infantry", 10, 11, 2, { id: "heishan-infantry-2", ...hold }),
    foe("heishan", "infantry", 10, 7, 5, { id: "heishan-infantry-3" }),
    foe("heishan", "infantry", 10, 12, 6, { id: "heishan-infantry-4" }),
    foe("heishan", "martialArtist", 10, 5, 4, { id: "heishan-brawler-1" }),
    foe("heishan", "martialArtist", 10, 13, 3, { id: "heishan-brawler-2" }),
    foe("heishan", "archer", 10, 10, 1, { id: "heishan-archer-1", ...hold }),
    foe("heishan", "archer", 10, 6, 1, { id: "heishan-archer-2", ...hold }),
    foe("heishan", "cavalry", 10, 4, 6, { id: "heishan-cavalry-1" }),
    foe("heishan", "infantry", 10, 16, 4, { id: "heishan-infantry-5", arrives: 4 }),
    foe("heishan", "infantry", 10, 17, 5, { id: "heishan-infantry-6", arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("bai-rao")],
  goalText: { win: "백요 격파", lose: "조조 퇴각" },
  rewards: { gold: 800, items: ["strong-bow"] },
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "bai-rao",
          mood: "smile",
          text: "관군 깃발이 또 왔구나. 산에서 내려다보면 다 보인다!",
        },
        {
          who: "xun-yu",
          text: "적은 산 위 마을에 의지해 있습니다. 궁수와 기병을 아래로 끌어낸 뒤 본채를 치십시오.",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "heishan-infantry-5" },
      lines: [{ text: "산 너머에서 흑산적이 더 내려온다!" }],
    },
    {
      when: { at: "defeated", unit: "bai-rao" },
      lines: [
        { who: "bai-rao", mood: "hurt", text: "산채가… 무너진다…!" },
        { text: "백요의 무리가 흩어지자 동군의 백성들이 집으로 돌아왔다." },
      ],
    },
  ],
};

const B6: StoryBattleDef = {
  id: "shouzhang",
  name: "수장의 겨울",
  place: "연주 수장",
  summary:
    "백만이라 일컫는 청주 황건이 몰려온다. 진채를 지키며 10라운드까지 버티면 적의 기세가 꺾인다.",
  map: SHOUZHANG,
  weather: "cloudy",
  roundLimit: 12,
  sides: ["조조군", "청주 황건"],
  slots: cells([9, 5], [10, 5], [8, 6], [9, 6], [10, 6], [11, 6], [9, 7], [10, 7], [8, 5], [11, 5]),
  max: 8,
  required: ["cao-cao"],
  guests: [guest("bao-xin", "cavalry", 12, 12, 6)],
  enemies: [
    foe("qingzhou", "infantry", 11, 3, 2, { id: "qingzhou-infantry-1" }),
    foe("qingzhou", "infantry", 11, 16, 2, { id: "qingzhou-infantry-2" }),
    foe("qingzhou", "infantry", 11, 2, 8, { id: "qingzhou-infantry-3" }),
    foe("qingzhou", "infantry", 11, 17, 8, { id: "qingzhou-infantry-4" }),
    foe("qingzhou", "cavalry", 11, 0, 4, { id: "qingzhou-cavalry-1" }),
    foe("qingzhou", "cavalry", 11, 19, 4, { id: "qingzhou-cavalry-2" }),
    foe("qingzhou", "archer", 11, 6, 3, { id: "qingzhou-archer-1" }),
    foe("qingzhou", "archer", 11, 13, 9, { id: "qingzhou-archer-2" }),
    foe("qingzhou", "strategist", 11, 10, 1, { id: "qingzhou-strategist-1", ...heal }),
    foe("qingzhou", "infantry", 11, 0, 7, { id: "qingzhou-infantry-5", arrives: 3 }),
    foe("qingzhou", "infantry", 11, 19, 7, { id: "qingzhou-infantry-6", arrives: 3 }),
    foe("qingzhou", "infantry", 11, 10, 0, { id: "qingzhou-infantry-7", arrives: 3 }),
    foe("qingzhou", "cavalry", 12, 0, 3, { id: "qingzhou-cavalry-3", arrives: 5 }),
    foe("qingzhou", "cavalry", 12, 19, 10, { id: "qingzhou-cavalry-4", arrives: 5 }),
    foe("qingzhou", "infantry", 12, 5, 12, { id: "qingzhou-infantry-8", arrives: 5 }),
    foe("qingzhou", "infantry", 12, 0, 9, { id: "qingzhou-infantry-9", arrives: 7 }),
    foe("qingzhou", "infantry", 12, 19, 9, { id: "qingzhou-infantry-10", arrives: 7 }),
    foe("qingzhou", "infantry", 12, 15, 12, { id: "qingzhou-infantry-11", arrives: 7 }),
  ],
  goals: [loseIf("cao-cao"), holdUntil(10)],
  goalText: { win: "10라운드까지 진채 사수", lose: "조조 퇴각" },
  rewards: { gold: 1000, items: ["seal-cord"] },
  music: "danger",
  talk: [
    {
      when: { at: "start" },
      lines: [
        {
          who: "bao-xin",
          text: "적의 수는 많으나 군량도 기율도 없소. 굳게 지키며 지치기를 기다립시다.",
        },
        { who: "chen-gong", text: "적이 사방에서 옵니다. 진채를 중심으로 둥글게 서십시오." },
        {
          who: "cao-cao",
          text: "진채를 지킨다. 저들이 지쳐 흩어질 때까지 한 걸음도 물러서지 마라!",
        },
      ],
    },
    {
      when: { at: "arrived", unit: "qingzhou-infantry-5" },
      lines: [{ text: "청주 황건의 새 무리가 사방에서 몰려온다!" }],
    },
    {
      when: { at: "defeated", unit: "bao-xin" },
      lines: [
        { who: "bao-xin", mood: "hurt", text: "맹덕… 어서 피하시오… 그대만은 살아야 하오…!" },
        { who: "cao-cao", mood: "grim", text: "포신…!" },
      ],
    },
    {
      when: { at: "round", round: 8 },
      lines: [{ who: "cao-cao", mood: "angry", text: "적의 걸음이 무거워졌다. 조금만 더!" }],
    },
    {
      when: { at: "end", winner: 0 },
      lines: [
        { text: "겨울이 깊어지자 군량이 떨어진 청주 황건은 더 버티지 못하고 무기를 내려놓았다." },
      ],
    },
  ],
};

// ---------------------------------------------------------------- stages (ADR 0063)

type At = [number, number];

const prop = (kind: PropKind, [x, y]: At, more: Partial<PropDef> = {}): PropDef => ({
  kind,
  at: { x, y },
  ...more,
});

/** A set's props moved down `dy` rows, for a stage that stacks two sets. */
const lower = (props: readonly PropDef[], dy: number): PropDef[] =>
  props.map((p) => ({ ...p, at: { x: p.at.x, y: p.at.y + dy } }));

/** An extra: a troop template in its army's colours. */
function soldier(
  id: string,
  troop: string,
  side: number,
  at?: At,
  facing?: Facing4,
  classId?: string,
): Actor {
  return actor(id, side, at, facing, { officer: troop, ...(classId ? { classId } : {}) });
}

/** The same step for several actors at once. */
const each = (ids: readonly string[], step: (id: string) => Cmd): Cmd => all(...ids.map(step));
const lighting = (light: StageLight): Cmd => ({ do: "light", light });

/**
 * Some scenes move between two places. Their stage stacks two 14 × 10 sets
 * with two rows of trees between; `SET` frames one set, `NEAR` is a close shot.
 */
const BETWEEN = ["ffffffffffffff", "ffffffffffffff"];
const SET = 2000;
const NEAR = 2800;

/** 허소's house: his room behind a screen, a door onto the yard, the lane below. */
const HOUSE_ROWS = [
  "ggg########ggg",
  "ggg#______#ggg",
  "ggg#______#ggg",
  "ggg#______#ggg",
  "ggg###__###ggg",
  "gg..........gg",
  "g...........fg",
  "g............g",
  "gg....==....gg",
  "gggg..==..gggg",
];
const HOUSE_PROPS: PropDef[] = [
  prop("screen", [6, 1]),
  prop("screen", [7, 1]),
  prop("lantern", [4, 1]),
  prop("lantern", [9, 1]),
  prop("chest", [9, 3]),
  prop("well", [2, 7]),
];

/**
 * 낙양 북부위 관아: the captain's hall and desk, the yard, the gate with the
 * five-coloured rods on racks either side, and the street outside.
 */
const WARD_ROWS = [
  "##############",
  "#..________..#",
  "#..________..#",
  "#..________..#",
  "#.....==.....#",
  "#.....==.....#",
  "######==######",
  "==============",
  "==============",
  "vvv.vvvvvv.vvv",
];
const WARD_PROPS: PropDef[] = [
  prop("table", [6, 2]),
  prop("table", [7, 2]),
  prop("screen", [7, 1]),
  prop("pillar", [3, 3]),
  prop("pillar", [10, 3]),
  prop("lantern", [1, 1]),
  prop("lantern", [12, 1]),
  prop("banner", [2, 4]),
  prop("banner", [11, 4]),
  prop("rack", [5, 5]),
  prop("rack", [8, 5]),
  prop("gate", [6, 6], { solid: false }),
  prop("gate", [7, 6], { solid: false }),
  prop("lantern", [5, 6]),
  prop("lantern", [8, 6]),
];

/** 거록: 장각's altar on the hill, the plain below where the faithful gather. */
const RALLY_ROWS = [
  "hhhhh====hhhhh",
  "hhhh.====.hhhh",
  "hhg..====..ghh",
  "gg..........gg",
  "g............g",
  "g............g",
  "g............g",
  "g............g",
  "gg..........gg",
  "gggg......gggg",
];
const RALLY_PROPS: PropDef[] = [
  prop("brazier", [5, 0]),
  prop("brazier", [8, 0]),
  prop("banner", [5, 2], { side: 1 }),
  prop("banner", [8, 2], { side: 1 }),
  prop("banner", [1, 3], { side: 1 }),
  prop("banner", [12, 3], { side: 1 }),
];
/** Where the faithful stand before the altar. */
const FAITHFUL: readonly At[] = [
  [3, 4],
  [5, 4],
  [8, 4],
  [10, 4],
  [4, 5],
  [6, 5],
  [7, 5],
  [9, 5],
  [2, 6],
  [5, 6],
  [8, 6],
  [11, 6],
];
const CROWD = FAITHFUL.map((_, i) => `huangjin-${i + 1}`);

/** 초현: the clan's village, the road past it, the field where the riders gather. */
const MUSTER_ROWS = [
  "vvvvvv.v.vvvvv",
  "vvvvvv.v.vvvvv",
  "..............",
  "==============",
  "..............",
  "gg..........gg",
  "ggg........ggg",
  "gggg......gggg",
  "gggggggggggggg",
  "gggggggggggggg",
];
const MUSTER_PROPS: PropDef[] = [
  prop("banner", [8, 5]),
  prop("horse", [11, 6], { solid: true }),
  prop("cart", [12, 4]),
];

/** 장사: the walled town with its south gate, 파재's tents in the grass, a wood to the south-west. */
const CHANGSHE_STAGE: StageDef = {
  id: "changshe-town",
  light: "dusk",
  weather: "wind",
  rows: [
    "gggggggggggggggg",
    "g########ggggggg",
    "g#=v==v=#ggggggg",
    "g#======#ggggggg",
    "g#======#ggggggg",
    "g#=v==v=#ggggggg",
    "g###==###ggggggg",
    "ff..==..gggggggg",
    "fff....ggggggggg",
    "ffff..gggggggggg",
  ],
  props: [
    prop("gate", [4, 6], { solid: false }),
    prop("gate", [5, 6], { solid: false }),
    prop("banner", [2, 4]),
    prop("tent", [11, 1], { side: 1 }),
    prop("tent", [14, 2], { side: 1 }),
    prop("tent", [10, 5], { side: 1 }),
    prop("tent", [14, 6], { side: 1 }),
    prop("tent", [12, 8], { side: 1 }),
    prop("brazier", [12, 3]),
    prop("banner", [9, 1], { side: 1 }),
  ],
};

/** 낙양 궁궐: the throne on its dais, the carpet down to the door. */
const COURT_ROWS = [
  "##############",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "#____rrrr____#",
  "######rr######",
];
const COURT_PROPS: PropDef[] = [
  prop("throne", [6, 1]),
  prop("screen", [7, 1]),
  prop("pillar", [3, 3]),
  prop("pillar", [10, 3]),
  prop("pillar", [3, 6]),
  prop("pillar", [10, 6]),
  prop("lantern", [1, 1]),
  prop("lantern", [12, 1]),
];

/** 진류: 위자's house behind its gate lane, the road east, open ground for a muster. */
const CHENLIU_ROWS = [
  "vvvvvv==vvvvvv",
  "vvvvvv==vvvvvv",
  "..............",
  "..............",
  "..............",
  "g............g",
  "gg..........gg",
  "ggg........ggg",
  "gggg......gggg",
  "gggggggggggggg",
];
const CHENLIU_PROPS: PropDef[] = [
  prop("banner", [9, 5]),
  prop("horse", [11, 6], { solid: true }),
  prop("grain", [1, 2]),
];

/** 사수관: the gated wall of the pass across the top between mountains, the coalition's lines below. */
const SISHUI_STAGE: StageDef = {
  id: "sishui-gate",
  light: "day",
  weather: "cloudy",
  rows: [
    "mmmmmFFFFFFmmmmm",
    "mmmm###==###mmmm",
    "mmhh........hhmm",
    "mh............hm",
    "h..............h",
    "g..............g",
    "g..............g",
    "g..............g",
    "gg............gg",
    "ggggg......ggggg",
  ],
  props: [
    prop("gate", [7, 1], { solid: false }),
    prop("gate", [8, 1], { solid: false }),
    prop("tent", [2, 5], { side: 2 }),
    prop("tent", [1, 8], { side: 2 }),
    prop("banner", [3, 8], { side: 2 }),
    prop("tent", [13, 5]),
    prop("tent", [14, 8]),
    prop("banner", [12, 8]),
    // the cup of warm wine
    prop("table", [10, 6]),
  ],
};

/** 산조: the coalition camp, 원소's seat on a carpeted dais, the lords' tents around. */
const SUANZAO_ROWS = [
  "gggggggggggggg",
  "gg...rrrr...gg",
  "g....rrrr....g",
  "g............g",
  "g............g",
  "g............g",
  "g............g",
  "g............g",
  "gg..........gg",
  "ggggg....ggggg",
];
const SUANZAO_PROPS: PropDef[] = [
  prop("throne", [6, 1]),
  prop("banner", [5, 1], { side: 2 }),
  prop("banner", [8, 1], { side: 2 }),
  prop("tent", [3, 1], { side: 2 }),
  prop("tent", [10, 1], { side: 2 }),
  prop("tent", [1, 4], { side: 2 }),
  prop("tent", [12, 4], { side: 2 }),
  prop("tent", [1, 7], { side: 2 }),
  prop("tent", [12, 7], { side: 2 }),
  prop("brazier", [4, 2]),
  prop("brazier", [9, 2]),
];

/** 동군: our camp on the plain; 백요's hill village smokes to the north-east. */
const DONGJUN_CAMP: StageDef = {
  id: "dongjun-camp",
  light: "day",
  rows: [
    "hhhhhhhhhvvvhm",
    "fhhhhhhhhhvhhm",
    "ff..........hh",
    "f.............",
    "..............",
    "..............",
    "..............",
    "g............g",
    "gg..........gg",
    "ggggg....ggggg",
  ],
  props: [
    prop("tent", [3, 8]),
    prop("tent", [10, 8]),
    prop("banner", [9, 3]),
    prop("brazier", [7, 7]),
    prop("horse", [12, 5], { solid: true }),
    prop("grain", [1, 7]),
  ],
};

/** 동군 관아: the governor's hall, a screen behind his place. */
const DONGJUN_HALL: StageDef = {
  id: "dongjun-hall",
  light: "day",
  rows: HALL.rows,
  props: [
    prop("screen", [6, 1]),
    prop("screen", [7, 1]),
    prop("pillar", [3, 2]),
    prop("pillar", [10, 2]),
    prop("pillar", [3, 5]),
    prop("pillar", [10, 5]),
    prop("lantern", [1, 1]),
    prop("lantern", [12, 1]),
    prop("rack", [1, 4]),
    prop("chest", [12, 4]),
  ],
};

/** 수장의 겨울 진영: a wooden likeness of 포신 on a paved altar, tents, the frozen plain. */
const WINTER_CAMP: StageDef = {
  id: "shouzhang-winter",
  light: "dusk",
  weather: "cloudy",
  rows: [
    "ggggffffffgggg",
    "gg..........gg",
    "g.....==.....g",
    "g.....==.....g",
    "g............g",
    "g............g",
    "g............g",
    "g............g",
    "gg..........gg",
    "gggg......gggg",
  ],
  props: [
    prop("statue", [6, 2]),
    prop("banner", [7, 2]),
    prop("brazier", [5, 2]),
    prop("brazier", [8, 2]),
    prop("tent", [2, 1]),
    prop("tent", [11, 1]),
    prop("tent", [1, 4]),
    prop("tent", [12, 4]),
  ],
};

// ---------------------------------------------------------------- scenes

const PROLOGUE: SceneDef = {
  id: "rise-prologue",
  title: "서장 · 난세의 간웅",
  place: "낙양",
  when: "희평 3년(174)",
  backdrop: "court",
  // 허소's house above, the ward office below.
  stage: {
    id: "xu-shao-house",
    light: "day",
    rows: [...HOUSE_ROWS, ...BETWEEN, ...WARD_ROWS],
    props: [...HOUSE_PROPS, ...lower(WARD_PROPS, 12)],
  },
  cast: [
    actor("cao-cao", 0, undefined, undefined, { classId: "infantry" }),
    actor("xu-shao", 2, [6, 2], "down", { classId: "strategist" }),
    soldier("ward-1", "ward", 0, [4, 16], "down"),
    soldier("ward-2", "ward", 0, [9, 16], "down"),
  ],
  script: [
    camera([7, 5], SET),
    tell(
      "한나라는 사백 년을 이어 왔으나, 궁궐에서는 환관들이 권세를 쥐고 지방에서는 굶주린 백성이 늘어 갔다.",
    ),
    enter("cao-cao", [6, 9], [6, 6]),
    tell(
      "패국 초현 사람 조조는 어려서부터 재주가 뛰어나고 기개가 남달랐으나, 사람들은 그를 제멋대로인 젊은이라 여겼다.",
    ),
    tell("조조는 사람을 잘 알아보기로 이름난 허소를 찾아가 자신을 평해 달라고 졸랐다."),
    walk("cao-cao", [6, 3]),
    act("cao-cao", "bow"),
    act("xu-shao", "shake"),
    face("xu-shao", "left"),
    emote("xu-shao", "..."),
    act("cao-cao", "kneel"),
    pause(800),
    face("xu-shao", "cao-cao"),
    camera("xu-shao", NEAR),
    say(
      "xu-shao",
      "그대는 태평한 세상이라면 유능한 신하가 되겠지만, 어지러운 세상이라면 간사한 영웅이 될 것이오.",
      undefined,
      { side: 2 },
    ),
    act("cao-cao", "stand"),
    all(act("cao-cao", "jump"), emote("cao-cao", "note")),
    say("cao-cao", "하하하! 간웅이라… 나쁘지 않군.", "smile"),
    emote("xu-shao", "sweat"),
    leave("cao-cao", [6, 9]),
    camera([7, 17], SET),
    tell("스무 살에 효렴으로 천거된 조조는 낙양 북부위가 되었다."),
    enter("cao-cao", [6, 20], [6, 16]),
    each(["ward-1", "ward-2"], (id) => face(id, "cao-cao")),
    each(["ward-1", "ward-2"], (id) => act(id, "bow")),
    face("cao-cao", "down"),
    say(
      "cao-cao",
      "관아 문 양쪽에 오색으로 칠한 몽둥이를 걸어라. 금령을 어기는 자는 누구든 이것으로 다스린다.",
    ),
    all(walk("ward-1", [4, 17]), walk("ward-2", [9, 17])),
    all(face("ward-1", "right"), face("ward-2", "left")),
    sound("equip"),
    each(["ward-1", "ward-2"], (id) => act(id, "nod")),
    camera([7, 18], NEAR),
    pause(800),
  ],
};

const BEFORE_LUOYANG: SceneDef = {
  id: "rise-before-luoyang",
  title: "금령의 밤",
  place: "낙양 북문",
  when: "희평 3년(174)",
  backdrop: "night",
  stage: { id: "ward-office-night", light: "night", rows: WARD_ROWS, props: WARD_PROPS },
  cast: [
    actor("cao-cao", 0, [6, 1], "down", { classId: "infantry" }),
    soldier("ward-1", "ward", 0, [4, 5], "down"),
    soldier("ward-2", "ward", 0, [9, 5], "down"),
    actor("jian-tu", 1),
    soldier("jianjia-1", "jianjia", 1),
    soldier("jianjia-2", "jianjia", 1),
  ],
  script: [
    camera([7, 6], 1300),
    all(enter("jianjia-1", [0, 8], [10, 8]), enter("jian-tu", [0, 7], [9, 7])),
    enter("jianjia-2", [0, 8], [8, 8]),
    all(emote("jian-tu", "note"), emote("jianjia-2", "note")),
    emote("ward-1", "!"),
    walk("ward-1", [6, 3], true),
    face("ward-1", "up"),
    act("ward-1", "kneel"),
    camera("cao-cao", 1600),
    say(
      "ward",
      "북부위 나리, 통행 금지를 어기고 활보하는 무리가 있습니다. 그런데… 건석 상시의 숙부라 합니다.",
      "grim",
      { actor: "ward-1", name: "북부위 위사", side: 0 },
    ),
    emote("cao-cao", "..."),
    walk("cao-cao", [5, 4]),
    face("cao-cao", "down"),
    say(
      "cao-cao",
      "황제를 곁에서 모시는 환관의 숙부라서 법을 피해 간다면, 이 몽둥이는 무엇 하러 걸어 두었느냐.",
    ),
    face("cao-cao", "ward-1"),
    act("ward-1", "stand"),
    say("cao-cao", "가자. 법은 지위를 묻지 않는다.", "angry"),
    walk("cao-cao", [7, 7]),
    all(walk("ward-1", [6, 7], true), walk("ward-2", [7, 6], true)),
    camera([8, 7], 1800),
    face("cao-cao", "jian-tu"),
    all(face("jian-tu", "cao-cao"), face("jianjia-2", "cao-cao")),
    emote("jian-tu", "?"),
    emote("jian-tu", "anger"),
  ],
};

const BROTHERS = ["xiahou-dun", "xiahou-yuan", "cao-ren", "cao-hong"];

const YELLOW_TURBANS: SceneDef = {
  id: "rise-yellow-turbans",
  title: "창천이 이미 죽었으니",
  place: "기주 거록",
  when: "광화 7년(184)",
  backdrop: "fire",
  // 거록 above; 초현, where the brothers gather, below.
  stage: {
    id: "julu-rally",
    light: "dusk",
    rows: [...RALLY_ROWS, ...BETWEEN, ...MUSTER_ROWS],
    props: [...RALLY_PROPS, ...lower(MUSTER_PROPS, 12)],
  },
  cast: [
    actor("zhang-jiao", 1, undefined, undefined, { classId: "strategist" }),
    ...FAITHFUL.map((at, i) => soldier(`huangjin-${i + 1}`, "huangjin", 1, at, "up")),
    actor("cao-cao", 0, [7, 16], "up"),
    actor("xiahou-dun", 0, undefined, undefined, { classId: "cavalry" }),
    actor("xiahou-yuan", 0, undefined, undefined, { classId: "archer" }),
    actor("cao-ren", 0, undefined, undefined, { classId: "infantry" }),
    actor("cao-hong", 0, undefined, undefined, { classId: "cavalry" }),
  ],
  script: [
    camera([7, 16], SET),
    tell(
      "건도의 일로 조조는 낙양에서 밀려나 지방 관리가 되었다. 그러나 그 일로 조조의 이름이 널리 알려졌다.",
    ),
    lighting("night"),
    camera([7, 4], SET),
    fire([1, 4], [12, 4]),
    sound("fire"),
    tell("십 년 뒤, 거록 사람 장각이 태평도를 일으켰다. 수십만의 신도가 누런 두건을 둘렀다."),
    enter("zhang-jiao", [6, 0], [6, 2]),
    face("zhang-jiao", "down"),
    camera("zhang-jiao", NEAR),
    act("zhang-jiao", "cheer"),
    say(
      "zhang-jiao",
      "창천은 이미 죽었으니 황천이 일어서리라! 갑자년에 천하가 크게 길하리라!",
      "angry",
      { side: 1 },
    ),
    all(
      sound("horn"),
      each(CROWD, (id) => act(id, "cheer")),
    ),
    fire([1, 7], [12, 7]),
    camera([7, 5], SET),
    tell("여덟 주가 한꺼번에 들고일어났다. 조정은 황보숭과 주준을 보내 영천의 황건적을 치게 했다."),
    lighting("dusk"),
    camera([7, 15], SET),
    sound("hooves"),
    all(
      enter("xiahou-dun", [6, 12], [6, 14]),
      enter("xiahou-yuan", [8, 12], [8, 14]),
      enter("cao-ren", [0, 15], [5, 15]),
      enter("cao-hong", [13, 15], [9, 15], true),
    ),
    each(BROTHERS, (id) => face(id, "cao-cao")),
    say("cao-cao", "기도위에 임명되었다. 고향의 형제들이 모두 따라나섰구나."),
    say("xiahou-dun", "맹덕, 우리 하후씨와 조씨 형제가 함께라면 황건 따위는 두렵지 않소.", "smile"),
    act("xiahou-yuan", "bow"),
    say("xiahou-yuan", "활은 제게 맡기십시오."),
    act("cao-ren", "bow"),
    say("cao-ren", "조인, 앞에서 방패가 되겠습니다."),
    act("cao-hong", "jump"),
    say("cao-hong", "조홍도 왔습니다! 형님 말 앞에서 달리겠습니다.", "smile"),
    act("cao-cao", "nod"),
    each(BROTHERS, (id) => act(id, "cheer")),
  ],
  joins: [
    { officer: "xiahou-dun", classId: "cavalry", level: 5, gear: ["iron-ji", "leather"] },
    { officer: "xiahou-yuan", classId: "archer", level: 5, gear: ["horn-bow", "leather"] },
    { officer: "cao-ren", classId: "infantry", level: 5, gear: ["ring-dao", "lamellar"] },
    { officer: "cao-hong", classId: "cavalry", level: 4, gear: ["iron-ji", "leather"] },
  ],
};

const BEFORE_CHANGSHE: SceneDef = {
  id: "rise-before-changshe",
  title: "풀숲의 진",
  place: "영천 장사",
  when: "광화 7년(184) 5월",
  backdrop: "field",
  stage: CHANGSHE_STAGE,
  cast: [
    actor("huangfu-song", 0, [6, 3], "right", { classId: "strategist" }),
    actor("zhu-jun", 0, [7, 4], "right"),
    soldier("ward-1", "ward", 0, [3, 3], "right"),
    soldier("ward-2", "ward", 0, [3, 4], "right"),
    soldier("huangjin-1", "huangjin", 1, [11, 3], "right"),
    soldier("huangjin-2", "huangjin", 1, [13, 3], "left"),
    soldier("huangjin-3", "huangjin", 1, [11, 6], "down"),
    soldier("huangjin-4", "huangjin", 1, [9, 4], "left"),
    actor("cao-cao", 0),
    actor("xiahou-dun", 0),
    actor("cao-ren", 0),
  ],
  script: [
    camera([8, 4], 1000),
    tell(
      "황보숭은 장사성에 들어갔다가 황건적의 장수 파재에게 포위당했다. 성 안의 군사는 적고 모두 두려워했다.",
    ),
    all(emote("ward-1", "sweat"), emote("ward-2", "sweat")),
    sound("wind"),
    camera("huangfu-song", 1700),
    face("huangfu-song", "zhu-jun"),
    face("zhu-jun", "huangfu-song"),
    say(
      "huangfu-song",
      "병법은 기이한 계책에 달렸지 수에 달리지 않소. 적은 풀숲에 진을 쳤고 오늘 밤 바람이 거세오.",
    ),
    act("zhu-jun", "nod"),
    emote("huangfu-song", "idea"),
    say(
      "huangfu-song",
      "불을 놓으면 저들은 반드시 어지러워질 것이오. 그때 안팎에서 치면 이길 수 있소.",
      "smile",
    ),
    camera([7, 5], 1000),
    emote("huangjin-4", "..."),
    sound("hooves"),
    all(
      enter("cao-cao", [4, 9], [4, 7]),
      enter("xiahou-dun", [5, 9], [5, 8]),
      enter("cao-ren", [3, 9], [3, 8]),
    ),
    emote("huangfu-song", "!"),
    walk("huangfu-song", [4, 3]),
    walk("huangfu-song", [4, 6]),
    face("huangfu-song", "down"),
    face("cao-cao", "up"),
    act("cao-cao", "bow"),
    camera([4, 6], 1700),
    say("cao-cao", "때맞춰 왔군요. 우리 군이 성 밖에서 적의 등을 치겠습니다."),
    act("huangfu-song", "nod"),
    camera([11, 4], 1500),
    emote("huangjin-1", "note"),
  ],
};

const SOLDIERS = ["cao-1", "cao-2", "cao-3", "cao-4"];

const DONG_ZHUO: SceneDef = {
  id: "rise-dong-zhuo",
  title: "낙양의 늑대",
  place: "낙양",
  when: "중평 6년(189)",
  backdrop: "court",
  // The palace above; 진류, where 위자 waits, below.
  stage: {
    id: "luoyang-court",
    light: "day",
    rows: [...COURT_ROWS, ...BETWEEN, ...CHENLIU_ROWS],
    props: [...COURT_PROPS, ...lower(CHENLIU_PROPS, 12)],
  },
  cast: [
    actor("xian-di", 2, [2, 2], "right", { classId: "strategist" }),
    actor("cao-cao", 0, [9, 5], "left", { classId: "infantry" }),
    soldier("dong-3", "dong", 1, [4, 8], "right", "heavyInfantry"),
    soldier("dong-4", "dong", 1, [9, 8], "left", "heavyInfantry"),
    actor("dong-zhuo", 1, undefined, undefined, { classId: "heavyInfantry" }),
    soldier("dong-1", "dong", 1, undefined, undefined, "heavyInfantry"),
    soldier("dong-2", "dong", 1, undefined, undefined, "heavyInfantry"),
    actor("wei-zi", 0),
    ...SOLDIERS.map((id) => soldier(id, "cao", 0, undefined, undefined, "infantry")),
    actor("yuan-shao", 2, undefined, undefined, { classId: "cavalry" }),
    soldier("yuan-1", "yuan", 2, undefined, undefined, "cavalry"),
  ],
  script: [
    camera([7, 4], SET),
    tell(
      "황건적은 흩어졌으나 한나라의 병은 깊어만 갔다. 영제가 세상을 떠나자 대장군 하진과 환관들이 서로를 죽였다.",
    ),
    sound("march"),
    all(enter("dong-zhuo", [7, 9], [7, 2]), enter("dong-1", [6, 9], [5, 3])),
    enter("dong-2", [7, 9], [8, 3]),
    each(["dong-zhuo", "dong-1", "dong-2"], (id) => face(id, "down")),
    all(emote("xian-di", "sweat"), emote("cao-cao", "!")),
    tell(
      "그 틈에 서량의 동탁이 군사를 이끌고 낙양에 들어왔다. 그는 어린 황제를 폐하고 진류왕을 세웠다.",
    ),
    face("dong-zhuo", "xian-di"),
    walk("xian-di", [6, 2]),
    face("xian-di", "down"),
    face("dong-zhuo", "cao-cao"),
    walk("cao-cao", [7, 5]),
    face("cao-cao", "up"),
    camera("dong-zhuo", NEAR),
    say("dong-zhuo", "조조라 했나. 효기교위 자리를 주겠다. 내 밑에서 일하라.", "smile", {
      side: 1,
    }),
    // He bows his thanks, turns to go, and only then lets his face show it.
    act("cao-cao", "bow"),
    face("cao-cao", "down"),
    emote("cao-cao", "..."),
    camera("cao-cao", 3000),
    say("cao-cao", "(이 자는 오래가지 못한다. 그 밑에 있다가는 함께 망할 뿐…)", "grim"),
    camera([7, 4], SET),
    leave("cao-cao", [7, 9]),
    camera([7, 16], SET),
    tell("조조는 이름을 바꾸고 샛길로 낙양을 빠져나가 동쪽으로 달아났다."),
    enter("cao-cao", [0, 16], [5, 16]),
    enter("wei-zi", [6, 12], [6, 16]),
    face("wei-zi", "cao-cao"),
    face("cao-cao", "wei-zi"),
    act("wei-zi", "bow"),
    say(
      "wei-zi",
      "진류의 위자라 합니다. 천하를 바로잡을 분은 공이라 믿습니다. 제 재산으로 군사를 모으십시오.",
    ),
    act("cao-cao", "bow"),
    sound("march"),
    all(
      enter("cao-1", [5, 21], [5, 19]),
      enter("cao-2", [6, 21], [6, 19]),
      enter("cao-3", [7, 21], [7, 19]),
      enter("cao-4", [8, 21], [8, 19]),
    ),
    tell("190년 봄, 관동의 제후들이 동탁을 치기 위해 일어났다. 맹주는 사세삼공의 명문 원소였다."),
    sound("hooves"),
    all(enter("yuan-shao", [13, 15], [9, 15]), enter("yuan-1", [13, 14], [10, 14])),
    face("yuan-shao", "cao-cao"),
    all(face("cao-cao", "yuan-shao"), face("wei-zi", "yuan-shao")),
    act("cao-cao", "bow"),
    camera("yuan-shao", NEAR),
    act("yuan-shao", "cheer"),
    say("yuan-shao", "의로운 군사가 모였으니 역적 동탁의 목은 머지않았소!", undefined, {
      side: 2,
    }),
    camera([7, 17], SET),
    all(
      sound("horn"),
      each(SOLDIERS, (id) => act(id, "cheer")),
    ),
  ],
};

const BEFORE_SISHUI: SceneDef = {
  id: "rise-before-sishui",
  title: "관문 앞의 술잔",
  place: "사수관 앞 연합군 진영",
  when: "초평 원년(190)",
  backdrop: "camp",
  stage: SISHUI_STAGE,
  // The lords stand either side of the road up to the gate; the road is where men die.
  cast: [
    actor("yuan-shao", 2, [4, 7], "up", { classId: "infantry" }),
    actor("sun-jian", 0, [5, 6], "up", { classId: "infantry" }),
    actor("cao-cao", 0, [9, 6], "up", { classId: "infantry" }),
    actor("liu-bei", 0, [10, 7], "up"),
    actor("guan-yu", 0, [11, 7], "up", { classId: "infantry" }),
    actor("zhang-fei", 0, [11, 8], "up", { classId: "infantry" }),
    actor("hua-xiong", 1, [7, 2], "down", { classId: "shockCavalry" }),
    soldier("dong-1", "dong", 1, [5, 2], "down"),
    soldier("dong-2", "dong", 1, [10, 2], "down"),
    soldier("yuan-1", "yuan", 2, undefined, undefined, "cavalry"),
  ],
  script: [
    camera([7, 3], 1700),
    sound("hooves"),
    enter("yuan-1", [7, 9], [7, 3], true),
    strike("hua-xiong", "yuan-1", true),
    act("hua-xiong", "cheer"),
    camera([7, 5], 1000),
    tell(
      "동탁의 장수 화웅이 사수관을 지키며 연합군의 장수들을 잇달아 베었다. 제후들은 얼굴빛이 변했다.",
    ),
    all(emote("yuan-shao", "sweat"), emote("liu-bei", "...")),
    walk("sun-jian", [5, 5]),
    say("sun-jian", "강동의 손견이오. 화웅에게 한 번 졌으나 두 번은 지지 않소."),
    // 관우 steps out from behind his brother into the road.
    walk("guan-yu", [11, 5]),
    walk("guan-yu", [8, 5]),
    face("guan-yu", "yuan-shao"),
    say("guan-yu", "평원의 유현덕 아래 있는 관우라 하오. 화웅의 목은 제가 가져오겠소."),
    emote("yuan-shao", "?"),
    emote("zhang-fei", "anger"),
    act("zhang-fei", "jump"),
    say("zhang-fei", "형님이 나가신다면 이 장비도 뒤를 따르겠소!", "angry"),
    face("liu-bei", "yuan-shao"),
    act("liu-bei", "bow"),
    say("liu-bei", "두 아우가 무례하나 그 용맹만은 믿을 만합니다."),
    // 조조 pours the wine that will still be warm when 관우 comes back.
    face("cao-cao", "right"),
    act("cao-cao", "nod"),
    face("cao-cao", "guan-yu"),
    say("cao-cao", "좋소. 우리 모두 함께 관문을 엽시다.", "smile"),
    each(["sun-jian", "guan-yu", "zhang-fei", "liu-bei", "cao-cao", "yuan-shao"], (id) =>
      face(id, "up"),
    ),
    camera("hua-xiong", 1700),
  ],
};

const BEFORE_XINGYANG: SceneDef = {
  id: "rise-before-xingyang",
  title: "홀로 서쪽으로",
  place: "산조",
  when: "초평 원년(190)",
  backdrop: "fire",
  stage: { id: "suanzao-night", light: "night", rows: SUANZAO_ROWS, props: SUANZAO_PROPS },
  cast: [
    actor("yuan-shao", 2, [6, 2], "down", { classId: "infantry" }),
    soldier("yuan-1", "yuan", 2, [5, 3], "down", "infantry"),
    soldier("yuan-2", "yuan", 2, [8, 3], "down", "infantry"),
    actor("yuan-shu", 2, [4, 4], "right", { classId: "infantry" }),
    actor("zhang-miao", 2, [9, 4], "left", { classId: "infantry" }),
    actor("cao-cao", 0, [4, 5], "right", { classId: "infantry" }),
    actor("wei-zi", 0, [4, 6], "right"),
    actor("bao-xin", 0, [9, 5], "left", { classId: "infantry" }),
  ],
  script: [
    camera([6, 4], 1000),
    tell(
      "동탁은 낙양을 불태우고 황제를 끌고 장안으로 옮겨 갔다. 그러나 연합군의 제후들은 서로 눈치만 볼 뿐 움직이지 않았다.",
    ),
    all(emote("yuan-shao", "..."), act("yuan-shu", "shake")),
    walk("cao-cao", [6, 5]),
    walk("cao-cao", [6, 4]),
    emote("cao-cao", "anger"),
    camera("cao-cao", 1600),
    say(
      "cao-cao",
      "의병을 일으킨 것은 역적을 치기 위함이 아니었소? 지금 한 번만 싸우면 천하가 정해지는데, 어찌 망설이시오!",
      "angry",
    ),
    face("yuan-shao", "left"),
    all(emote("yuan-shu", "sweat"), emote("zhang-miao", "sweat")),
    walk("bao-xin", [7, 5]),
    walk("bao-xin", [7, 4]),
    face("bao-xin", "cao-cao"),
    say("bao-xin", "맹덕의 말이 옳소. 나 포신이 함께 가겠소."),
    walk("wei-zi", [5, 6]),
    walk("wei-zi", [5, 4]),
    face("wei-zi", "cao-cao"),
    act("wei-zi", "bow"),
    say("wei-zi", "저도 군사를 이끌고 따르겠습니다."),
    face("cao-cao", "down"),
    camera([6, 5], 1000),
    sound("march"),
    all(leave("cao-cao", [6, 9]), leave("bao-xin", [7, 9]), leave("wei-zi", [5, 9])),
    tell(
      "조조는 홀로 군사를 이끌고 서쪽으로 동탁을 쫓았다. 형양 변수에 이르렀을 때 동탁의 장수 서영이 기다리고 있었다.",
    ),
  ],
};

const AFTER_XINGYANG: SceneDef = {
  id: "rise-after-xingyang",
  title: "술자리의 제후들",
  place: "산조",
  when: "초평 원년(190)",
  backdrop: "camp",
  stage: {
    id: "suanzao-feast",
    light: "dusk",
    rows: SUANZAO_ROWS,
    props: [...SUANZAO_PROPS, prop("table", [4, 5]), prop("table", [9, 5])],
  },
  cast: [
    actor("yuan-shao", 2, [6, 2], "down", { classId: "infantry" }),
    soldier("yuan-1", "yuan", 2, [5, 3], "down", "infantry"),
    soldier("yuan-2", "yuan", 2, [8, 3], "down", "infantry"),
    actor("yuan-shu", 2, [3, 5], "right", { classId: "infantry" }),
    actor("zhang-miao", 2, [10, 5], "left", { classId: "infantry" }),
    // On foot: 조조's horse fell at the river, and 조홍 gave him his own.
    actor("cao-cao", 0, undefined, undefined, { classId: "infantry" }),
    actor("cao-hong", 0, undefined, undefined, { classId: "infantry" }),
  ],
  script: [
    camera([6, 8], 1600),
    all(enter("cao-cao", [6, 9], [6, 7]), enter("cao-hong", [7, 9], [7, 7])),
    emote("cao-cao", "..."),
    tell("조조는 살아 돌아왔으나 군사는 거의 잃었다. 함께 싸운 위자는 변수에서 돌아오지 못했다."),
    face("cao-hong", "cao-cao"),
    camera([6, 4], 1000),
    all(emote("yuan-shu", "note"), emote("zhang-miao", "note"), act("yuan-shao", "cheer")),
    tell("산조로 돌아오니 제후들은 날마다 술자리를 벌이고 있었다."),
    act("yuan-shao", "stand"),
    walk("cao-cao", [6, 4]),
    all(emote("yuan-shu", "!"), emote("zhang-miao", "!")),
    emote("cao-cao", "anger"),
    camera("cao-cao", 1600),
    say(
      "cao-cao",
      "천하의 기대를 저버리고 이렇게 술만 마시고 있으니, 나는 그대들이 부끄럽소!",
      "angry",
    ),
    face("yuan-shao", "left"),
    all(emote("yuan-shu", "sweat"), emote("zhang-miao", "...")),
    face("cao-cao", "down"),
    camera([6, 5], 1000),
    all(leave("cao-cao", [6, 9]), leave("cao-hong", [7, 9])),
    all(leave("yuan-shu", [0, 5]), leave("zhang-miao", [13, 5])),
    tell("연합군은 얼마 지나지 않아 흩어졌다. 조조는 양주에서 다시 군사를 모았다."),
  ],
};

const BEFORE_PUYANG: SceneDef = {
  id: "rise-before-puyang",
  title: "나의 자방",
  place: "동군",
  when: "초평 2년(191)",
  backdrop: "camp",
  stage: DONGJUN_CAMP,
  cast: [
    actor("cao-cao", 0, [7, 4], "up", { classId: "infantry" }),
    actor("xiahou-dun", 0, [9, 5], "up", { classId: "infantry" }),
    actor("cao-ren", 0, [10, 5], "up"),
    actor("xun-yu", 0, undefined, undefined, { classId: "strategist" }),
    actor("yue-jin", 0, undefined, undefined, { classId: "infantry" }),
    actor("li-dian", 0, undefined, undefined, { classId: "archer" }),
    soldier("cao-1", "cao", 0, undefined, undefined, "infantry"),
    soldier("cao-2", "cao", 0, undefined, undefined, "infantry"),
  ],
  script: [
    camera([10, 1], 1700),
    all(fx("smoke", [10, 0]), fx("smoke", [11, 1])),
    tell("흑산적 백요의 무리 십여만이 동군을 휩쓸었다. 조조는 군사를 이끌고 동군으로 들어갔다."),
    camera([6, 4], 1300),
    enter("xun-yu", [0, 4], [5, 4]),
    face("cao-cao", "xun-yu"),
    act("xun-yu", "bow"),
    say(
      "xun-yu",
      "영천의 순욱이라 합니다. 원소 밑에 있어 보았으나 큰일을 이룰 사람은 아니었습니다.",
    ),
    emote("cao-cao", "!"),
    walk("cao-cao", [6, 4]),
    act("cao-cao", "bow"),
    say("cao-cao", "그대야말로 나의 자방이로다!", "smile"),
    emote("xiahou-dun", "?"),
    sound("march"),
    all(
      enter("yue-jin", [0, 5], [5, 5]),
      enter("cao-1", [0, 6], [4, 6]),
      enter("cao-2", [0, 7], [2, 7]),
    ),
    face("yue-jin", "cao-cao"),
    act("yue-jin", "kneel"),
    say("yue-jin", "악진, 고향에서 천 명을 모아 왔습니다. 앞장서게 해 주십시오."),
    enter("li-dian", [0, 3], [5, 3]),
    face("li-dian", "cao-cao"),
    act("li-dian", "bow"),
    say("li-dian", "이전이라 합니다. 숙부를 따라 군사를 이끌고 왔습니다."),
    act("cao-cao", "nod"),
    face("cao-cao", "up"),
    camera([10, 1], 1400),
  ],
  joins: [
    { officer: "xun-yu", classId: "strategist", level: 9, gear: ["feather-fan", "robe"] },
    { officer: "yue-jin", classId: "infantry", level: 9, gear: ["ring-dao", "lamellar"] },
    { officer: "li-dian", classId: "archer", level: 9, gear: ["horn-bow", "leather"] },
  ],
};

const AFTER_PUYANG: SceneDef = {
  id: "rise-after-puyang",
  title: "동군태수",
  place: "동군",
  when: "초평 2년(191)",
  backdrop: "field",
  stage: { ...STREET, id: "dongwuyang" },
  cast: [
    actor("cao-cao", 0),
    actor("xun-yu", 0),
    soldier("cao-1", "cao", 0, [4, 2], "right", "infantry"),
    soldier("cao-2", "cao", 0, [9, 2], "left", "infantry"),
  ],
  script: [
    camera([7, 3], 1300),
    sound("horn"),
    all(enter("cao-cao", [6, 0], [6, 4]), enter("xun-yu", [7, 0], [7, 4])),
    each(["cao-1", "cao-2"], (id) => act(id, "bow")),
    tell("백요를 무찌른 조조는 동군태수가 되어 동무양에 자리를 잡았다."),
    face("xun-yu", "cao-cao"),
    face("cao-cao", "xun-yu"),
    say(
      "xun-yu",
      "땅을 얻으셨으니 이제 백성을 품으셔야 합니다. 백성이 따르면 군사도 군량도 따라옵니다.",
    ),
    act("cao-cao", "nod"),
    face("cao-cao", "down"),
  ],
};

const BEFORE_SHOUZHANG: SceneDef = {
  id: "rise-before-shouzhang",
  title: "연주의 주인",
  place: "연주",
  when: "초평 3년(192)",
  backdrop: "field",
  stage: DONGJUN_HALL,
  cast: [
    actor("cao-cao", 0, [6, 2], "down", { classId: "infantry" }),
    actor("xun-yu", 0, [4, 3], "right"),
    actor("xiahou-dun", 0, [9, 3], "left", { classId: "infantry" }),
    actor("chen-gong", 0, undefined, undefined, { classId: "strategist" }),
    actor("bao-xin", 0, undefined, undefined, { classId: "infantry" }),
  ],
  script: [
    camera([6, 4], 1000),
    tell("청주의 황건 백만이 연주로 쏟아져 들어왔다. 연주자사 유대는 맞서 싸우다 목숨을 잃었다."),
    all(emote("xiahou-dun", "anger"), emote("xun-yu", "...")),
    enter("chen-gong", [6, 8], [6, 4]),
    act("chen-gong", "bow"),
    camera("chen-gong", 1600),
    say(
      "chen-gong",
      "동군의 진궁이라 합니다. 연주에 주인이 없으니 제가 연주의 관리들을 설득하겠습니다. 공께서 연주를 맡으십시오.",
    ),
    emote("cao-cao", "!"),
    act("xun-yu", "nod"),
    enter("bao-xin", [7, 8], [7, 4]),
    act("bao-xin", "bow"),
    say("bao-xin", "제북상 포신, 연주의 관리들과 함께 맹덕을 연주목으로 맞이하오."),
    pause(600),
    walk("cao-cao", [6, 3]),
    camera("cao-cao", 1600),
    say("cao-cao", "연주를 지키겠소. 황건을 막지 못하면 연주는 끝이오."),
    all(
      sound("horn"),
      each(["chen-gong", "bao-xin", "xun-yu", "xiahou-dun"], (id) => act(id, "bow")),
    ),
  ],
  joins: [
    { officer: "chen-gong", classId: "strategist", level: 11, gear: ["feather-fan", "robe"] },
  ],
};

const MOURNERS = ["xun-yu", "xiahou-dun", "chen-gong"];
/** Where the surrendered 청주 men kneel; each comes up their own column from the south edge. */
const SURRENDERED: readonly At[] = [
  [3, 7],
  [4, 6],
  [5, 7],
  [7, 7],
  [8, 6],
  [9, 7],
];
const QINGZHOU = SURRENDERED.map((_, i) => `qingzhou-${i + 1}`);

const EPILOGUE: SceneDef = {
  id: "rise-epilogue",
  title: "종장 · 청주병",
  place: "연주",
  when: "초평 3년(192) 겨울",
  backdrop: "dawn",
  stage: WINTER_CAMP,
  cast: [
    actor("cao-cao", 0, [7, 6], "down", { classId: "infantry" }),
    actor("xun-yu", 0, [5, 8], "up"),
    actor("xiahou-dun", 0, [7, 8], "up", { classId: "infantry" }),
    actor("chen-gong", 0, [9, 8], "up"),
    actor("yu-jin", 0, undefined, undefined, { classId: "infantry" }),
    ...QINGZHOU.map((id) => soldier(id, "qingzhou", 0)),
  ],
  script: [
    // Close on 조조 first: the likeness on the altar is not carved yet.
    camera([7, 7], 2000),
    tell("수장의 싸움에서 포신은 조조를 구하려다 목숨을 잃었다. 그의 시신은 끝내 찾지 못했다."),
    emote("cao-cao", "..."),
    say("cao-cao", "나무를 깎아 포신의 모습을 만들어라. 내 손으로 장례를 치르겠다.", "grim"),
    pause(800),
    camera([6, 3], 1600),
    walk("cao-cao", [6, 3]),
    all(walk("xun-yu", [5, 4]), walk("xiahou-dun", [7, 4]), walk("chen-gong", [8, 4])),
    each(MOURNERS, (id) => face(id, "up")),
    act("cao-cao", "kneel"),
    each(MOURNERS, (id) => act(id, "bow")),
    pause(1200),
    act("cao-cao", "stand"),
    all(
      face("cao-cao", "down"),
      each(MOURNERS, (id) => face(id, "down")),
    ),
    camera([6, 5], 1000),
    sound("march"),
    all(...SURRENDERED.map(([x, y], i) => enter(`qingzhou-${i + 1}`, [x, 9], [x, y]))),
    each(QINGZHOU, (id) => act(id, "kneel")),
    tell(
      "그해 겨울, 청주 황건은 조조에게 항복했다. 조조는 그 가운데 날랜 자를 골라 청주병이라 불렀다.",
    ),
    enter("yu-jin", [13, 5], [6, 5]),
    face("yu-jin", "up"),
    act("yu-jin", "kneel"),
    say("yu-jin", "포신 장군 밑에 있던 우금입니다. 이제 공을 따르겠습니다."),
    face("xun-yu", "cao-cao"),
    say("xun-yu", "연주는 공에게 옛날 고조의 관중과 같은 곳입니다. 이곳을 굳게 다지셔야 합니다."),
    camera("cao-cao", 1800),
    say("cao-cao", "허소의 말이 맞았군. 세상이 어지러우니 나는 이렇게 일어선다.", "smile"),
    camera([6, 5], 1000),
    tell(
      "이듬해, 조조의 아버지 조숭이 서주 땅에서 목숨을 잃는다. 연주의 주인이 된 조조 앞에 새로운 싸움이 기다리고 있었다.",
    ),
    tell("1장 황건의 난 · 끝"),
  ],
  joins: [{ officer: "yu-jin", classId: "infantry", level: 12, gear: ["iron-ji", "lamellar"] }],
};

// ---------------------------------------------------------------- camps

/** One thing an officer says in camp. */
const chat = (officer: string, text: string, mood?: Mood): CampTalk => ({
  officer,
  lines: [{ who: officer, text, ...(mood ? { mood } : {}) }],
});

/** Before 사수관: the army raised at 진류 with 위자's money, the coalition gathering. */
const TALK_1: CampTalk[] = [
  chat("xiahou-dun", "동탁의 목은 남에게 맡길 수 없소. 우리가 먼저 베어야 하오."),
  chat("xiahou-yuan", "위자 공 덕에 화살을 넉넉히 마련했습니다. 동탁의 기병도 문제없습니다."),
  chat("cao-ren", "진류에서 모은 군사들입니다. 아직 서툴지만 대열은 맞춰 두었습니다."),
  chat("cao-hong", "낙양에서 홀로 빠져나오시다니, 형님 배짱은 아무도 못 따릅니다!", "smile"),
];

/** After the rout at 변수 and the drinking at 산조: raising troops again in 양주. */
const TALK_2: CampTalk[] = [
  chat("xiahou-dun", "산조의 술꾼들과는 이제 끝이오. 변수의 빚은 우리 손으로 갚읍시다.", "angry"),
  chat("xiahou-yuan", "양주에서 새 병사들을 모았습니다. 활부터 다시 가르치겠습니다."),
  chat("cao-ren", "위자 공이 남긴 군사도 거두었습니다. 헛되이 죽게 하지 않겠습니다.", "grim"),
  chat("cao-hong", "말은 또 구하면 됩니다. 형님만 무사하시면 그걸로 됐습니다.", "smile"),
];

/** 동군태수 at last, with 순욱, 악진 and 이전 newly come. */
const TALK_3: CampTalk[] = [
  chat("xiahou-dun", "태수라니, 맹덕도 이제 어엿한 고을의 주인이 되었구려.", "smile"),
  chat("cao-hong", "동군 술맛이 꽤 괜찮습니다, 형님! 오늘은 한 잔 하시지요.", "smile"),
  chat("xun-yu", "동군의 창고를 살폈습니다. 올해 세금은 덜어 주시는 편이 좋겠습니다."),
  chat("yue-jin", "흑산적 잔당이 산으로 숨었습니다. 다시 내려오면 제가 막겠습니다."),
  chat("li-dian", "농사철입니다. 군사 절반은 밭으로 돌려보내 백성을 돕게 하시지요."),
];

// ---------------------------------------------------------------- chapter

export const RISE: ChapterDef = {
  id: "rise",
  number: 1,
  name: "1장",
  title: "황건의 난",
  years: "174–192",
  summary: "낙양의 젊은 북부위 조조. 황건의 난과 동탁의 폭정 속에서 그는 자신의 군대를 일으킨다.",
  gold: 300,
  floor: 1,
  next: "lubu",
  ending: {
    title: "황건의 난 · 완",
    lead: "청주의 황건 삼십만이 조조의 깃발 아래 들어왔다. 연주목 조조는 이제 천하를 다투는 이들 가운데 하나가 되었다.",
  },
  roster: [{ officer: "cao-cao", classId: "cavalry", level: 3, gear: ["ring-dao", "leather"] }],
  steps: [
    { kind: "scene", scene: PROLOGUE },
    { kind: "scene", scene: BEFORE_LUOYANG },
    { kind: "battle", battle: B1 },
    { kind: "scene", scene: YELLOW_TURBANS },
    { kind: "scene", scene: BEFORE_CHANGSHE },
    { kind: "battle", battle: B2 },
    { kind: "scene", scene: DONG_ZHUO },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-1",
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
    { kind: "scene", scene: BEFORE_SISHUI },
    { kind: "battle", battle: B3 },
    { kind: "scene", scene: BEFORE_XINGYANG },
    { kind: "battle", battle: B4 },
    { kind: "scene", scene: AFTER_XINGYANG },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-2",
        ground: CAMP_GROUND,
        shop: [
          "ring-dao",
          "refined-dao",
          "iron-ji",
          "horse-lance",
          "horn-bow",
          "strong-bow",
          "feather-fan",
          "lamellar",
          "scale",
          "leather",
          "lacquer",
          "robe",
          "jade",
        ],
        talk: TALK_2,
      },
    },
    { kind: "scene", scene: BEFORE_PUYANG },
    { kind: "battle", battle: B5 },
    { kind: "scene", scene: AFTER_PUYANG },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-3",
        ground: CAMP_GROUND,
        shop: [
          "refined-dao",
          "horse-lance",
          "strong-bow",
          "crane-fan",
          "scale",
          "lacquer",
          "padded-robe",
          "jade",
          "fine-horse",
        ],
        talk: TALK_3,
      },
    },
    { kind: "scene", scene: BEFORE_SHOUZHANG },
    { kind: "battle", battle: B6 },
    { kind: "scene", scene: EPILOGUE },
  ],
};
