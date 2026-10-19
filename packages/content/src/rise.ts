/**
 * Story chapter 1: 황건의 난 (174–192). Cao Cao from his first post as a night
 * captain of Luoyang to the governorship of Yanzhou: the Yellow Turbans at
 * Changshe, the coalition against Dong Zhuo at Sishui Pass and the rout at
 * Xingyang, the Black Mountain bandits at Puyang and the Qingzhou Yellow
 * Turbans at Shouzhang. History is public domain (the Records of the Three
 * Kingdoms, the Book of the Later Han; a few scenes from the novel, said so in
 * the credits); every line, map and number here is our own (ADR 0002, ADR 0057).
 */
import type { Cell, Goal } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { ChapterDef, StoryBattleDef } from "./story.ts";

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
  // Chapter 2 (여포) is still being written; the story carries on at Guandu.
  next: "guandu",
  ending: {
    title: "황건의 난 · 완",
    lead: "청주의 황건 삼십만이 조조의 깃발 아래 들어왔다. 연주목 조조는 이제 천하를 다투는 이들 가운데 하나가 되었다.",
  },
  roster: [{ officer: "cao-cao", classId: "cavalry", level: 3, gear: ["ring-dao", "leather"] }],
  steps: [
    {
      kind: "scene",
      scene: {
        id: "rise-prologue",
        title: "서장 · 난세의 간웅",
        place: "낙양",
        when: "희평 3년(174)",
        backdrop: "court",
        lines: [
          {
            text: "한나라는 사백 년을 이어 왔으나, 궁궐에서는 환관들이 권세를 쥐고 지방에서는 굶주린 백성이 늘어 갔다.",
          },
          {
            text: "패국 초현 사람 조조는 어려서부터 재주가 뛰어나고 기개가 남달랐으나, 사람들은 그를 제멋대로인 젊은이라 여겼다.",
          },
          { text: "조조는 사람을 잘 알아보기로 이름난 허소를 찾아가 자신을 평해 달라고 졸랐다." },
          {
            who: "xu-shao",
            side: 2,
            text: "그대는 태평한 세상이라면 유능한 신하가 되겠지만, 어지러운 세상이라면 간사한 영웅이 될 것이오.",
          },
          { who: "cao-cao", mood: "smile", text: "하하하! 간웅이라… 나쁘지 않군." },
          { text: "스무 살에 효렴으로 천거된 조조는 낙양 북부위가 되었다." },
          {
            who: "cao-cao",
            text: "관아 문 양쪽에 오색으로 칠한 몽둥이를 걸어라. 금령을 어기는 자는 누구든 이것으로 다스린다.",
          },
        ],
      },
    },
    {
      kind: "scene",
      scene: {
        id: "rise-before-luoyang",
        title: "금령의 밤",
        place: "낙양 북문",
        when: "희평 3년(174)",
        backdrop: "night",
        lines: [
          {
            who: "ward",
            name: "북부위 위사",
            side: 0,
            mood: "grim",
            text: "북부위 나리, 통행 금지를 어기고 활보하는 무리가 있습니다. 그런데… 건석 상시의 숙부라 합니다.",
          },
          {
            who: "cao-cao",
            text: "황제를 곁에서 모시는 환관의 숙부라서 법을 피해 간다면, 이 몽둥이는 무엇 하러 걸어 두었느냐.",
          },
          { who: "cao-cao", mood: "angry", text: "가자. 법은 지위를 묻지 않는다." },
        ],
      },
    },
    { kind: "battle", battle: B1 },
    {
      kind: "scene",
      scene: {
        id: "rise-yellow-turbans",
        title: "창천이 이미 죽었으니",
        place: "기주 거록",
        when: "광화 7년(184)",
        backdrop: "fire",
        lines: [
          {
            text: "건도의 일로 조조는 낙양에서 밀려나 지방 관리가 되었다. 그러나 그 일로 조조의 이름이 널리 알려졌다.",
          },
          {
            text: "십 년 뒤, 거록 사람 장각이 태평도를 일으켰다. 수십만의 신도가 누런 두건을 둘렀다.",
          },
          {
            who: "zhang-jiao",
            side: 1,
            mood: "angry",
            text: "창천은 이미 죽었으니 황천이 일어서리라! 갑자년에 천하가 크게 길하리라!",
          },
          {
            text: "여덟 주가 한꺼번에 들고일어났다. 조정은 황보숭과 주준을 보내 영천의 황건적을 치게 했다.",
          },
          { who: "cao-cao", text: "기도위에 임명되었다. 고향의 형제들이 모두 따라나섰구나." },
          {
            who: "xiahou-dun",
            mood: "smile",
            text: "맹덕, 우리 하후씨와 조씨 형제가 함께라면 황건 따위는 두렵지 않소.",
          },
          { who: "xiahou-yuan", text: "활은 제게 맡기십시오." },
          { who: "cao-ren", text: "조인, 앞에서 방패가 되겠습니다." },
          { who: "cao-hong", mood: "smile", text: "조홍도 왔습니다! 형님 말 앞에서 달리겠습니다." },
        ],
        joins: [
          { officer: "xiahou-dun", classId: "cavalry", level: 5, gear: ["iron-ji", "leather"] },
          { officer: "xiahou-yuan", classId: "archer", level: 5, gear: ["horn-bow", "leather"] },
          { officer: "cao-ren", classId: "infantry", level: 5, gear: ["ring-dao", "lamellar"] },
          { officer: "cao-hong", classId: "cavalry", level: 4, gear: ["iron-ji", "leather"] },
        ],
      },
    },
    {
      kind: "scene",
      scene: {
        id: "rise-before-changshe",
        title: "풀숲의 진",
        place: "영천 장사",
        when: "광화 7년(184) 5월",
        backdrop: "field",
        lines: [
          {
            text: "황보숭은 장사성에 들어갔다가 황건적의 장수 파재에게 포위당했다. 성 안의 군사는 적고 모두 두려워했다.",
          },
          {
            who: "huangfu-song",
            text: "병법은 기이한 계책에 달렸지 수에 달리지 않소. 적은 풀숲에 진을 쳤고 오늘 밤 바람이 거세오.",
          },
          {
            who: "huangfu-song",
            mood: "smile",
            text: "불을 놓으면 저들은 반드시 어지러워질 것이오. 그때 안팎에서 치면 이길 수 있소.",
          },
          { who: "cao-cao", text: "때맞춰 왔군요. 우리 군이 성 밖에서 적의 등을 치겠습니다." },
        ],
      },
    },
    { kind: "battle", battle: B2 },
    {
      kind: "scene",
      scene: {
        id: "rise-dong-zhuo",
        title: "낙양의 늑대",
        place: "낙양",
        when: "중평 6년(189)",
        backdrop: "court",
        lines: [
          {
            text: "황건적은 흩어졌으나 한나라의 병은 깊어만 갔다. 영제가 세상을 떠나자 대장군 하진과 환관들이 서로를 죽였다.",
          },
          {
            text: "그 틈에 서량의 동탁이 군사를 이끌고 낙양에 들어왔다. 그는 어린 황제를 폐하고 진류왕을 세웠다.",
          },
          {
            who: "dong-zhuo",
            side: 1,
            mood: "smile",
            text: "조조라 했나. 효기교위 자리를 주겠다. 내 밑에서 일하라.",
          },
          {
            who: "cao-cao",
            mood: "grim",
            text: "(이 자는 오래가지 못한다. 그 밑에 있다가는 함께 망할 뿐…)",
          },
          { text: "조조는 이름을 바꾸고 샛길로 낙양을 빠져나가 동쪽으로 달아났다." },
          {
            who: "wei-zi",
            text: "진류의 위자라 합니다. 천하를 바로잡을 분은 공이라 믿습니다. 제 재산으로 군사를 모으십시오.",
          },
          {
            text: "190년 봄, 관동의 제후들이 동탁을 치기 위해 일어났다. 맹주는 사세삼공의 명문 원소였다.",
          },
          {
            who: "yuan-shao",
            side: 2,
            text: "의로운 군사가 모였으니 역적 동탁의 목은 머지않았소!",
          },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-1",
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
        id: "rise-before-sishui",
        title: "관문 앞의 술잔",
        place: "사수관 앞 연합군 진영",
        when: "초평 원년(190)",
        backdrop: "camp",
        lines: [
          {
            text: "동탁의 장수 화웅이 사수관을 지키며 연합군의 장수들을 잇달아 베었다. 제후들은 얼굴빛이 변했다.",
          },
          { who: "sun-jian", text: "강동의 손견이오. 화웅에게 한 번 졌으나 두 번은 지지 않소." },
          {
            who: "guan-yu",
            text: "평원의 유현덕 아래 있는 관우라 하오. 화웅의 목은 제가 가져오겠소.",
          },
          { who: "zhang-fei", mood: "angry", text: "형님이 나가신다면 이 장비도 뒤를 따르겠소!" },
          { who: "liu-bei", text: "두 아우가 무례하나 그 용맹만은 믿을 만합니다." },
          { who: "cao-cao", mood: "smile", text: "좋소. 우리 모두 함께 관문을 엽시다." },
        ],
      },
    },
    { kind: "battle", battle: B3 },
    {
      kind: "scene",
      scene: {
        id: "rise-before-xingyang",
        title: "홀로 서쪽으로",
        place: "산조",
        when: "초평 원년(190)",
        backdrop: "fire",
        lines: [
          {
            text: "동탁은 낙양을 불태우고 황제를 끌고 장안으로 옮겨 갔다. 그러나 연합군의 제후들은 서로 눈치만 볼 뿐 움직이지 않았다.",
          },
          {
            who: "cao-cao",
            mood: "angry",
            text: "의병을 일으킨 것은 역적을 치기 위함이 아니었소? 지금 한 번만 싸우면 천하가 정해지는데, 어찌 망설이시오!",
          },
          { who: "bao-xin", text: "맹덕의 말이 옳소. 나 포신이 함께 가겠소." },
          { who: "wei-zi", text: "저도 군사를 이끌고 따르겠습니다." },
          {
            text: "조조는 홀로 군사를 이끌고 서쪽으로 동탁을 쫓았다. 형양 변수에 이르렀을 때 동탁의 장수 서영이 기다리고 있었다.",
          },
        ],
      },
    },
    { kind: "battle", battle: B4 },
    {
      kind: "scene",
      scene: {
        id: "rise-after-xingyang",
        title: "술자리의 제후들",
        place: "산조",
        when: "초평 원년(190)",
        backdrop: "camp",
        lines: [
          {
            text: "조조는 살아 돌아왔으나 군사는 거의 잃었다. 함께 싸운 위자는 변수에서 돌아오지 못했다.",
          },
          { text: "산조로 돌아오니 제후들은 날마다 술자리를 벌이고 있었다." },
          {
            who: "cao-cao",
            mood: "angry",
            text: "천하의 기대를 저버리고 이렇게 술만 마시고 있으니, 나는 그대들이 부끄럽소!",
          },
          { text: "연합군은 얼마 지나지 않아 흩어졌다. 조조는 양주에서 다시 군사를 모았다." },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-2",
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
      },
    },
    {
      kind: "scene",
      scene: {
        id: "rise-before-puyang",
        title: "나의 자방",
        place: "동군",
        when: "초평 2년(191)",
        backdrop: "camp",
        lines: [
          {
            text: "흑산적 백요의 무리 십여만이 동군을 휩쓸었다. 조조는 군사를 이끌고 동군으로 들어갔다.",
          },
          {
            who: "xun-yu",
            text: "영천의 순욱이라 합니다. 원소 밑에 있어 보았으나 큰일을 이룰 사람은 아니었습니다.",
          },
          { who: "cao-cao", mood: "smile", text: "그대야말로 나의 자방이로다!" },
          { who: "yue-jin", text: "악진, 고향에서 천 명을 모아 왔습니다. 앞장서게 해 주십시오." },
          { who: "li-dian", text: "이전이라 합니다. 숙부를 따라 군사를 이끌고 왔습니다." },
        ],
        joins: [
          { officer: "xun-yu", classId: "strategist", level: 9, gear: ["feather-fan", "robe"] },
          { officer: "yue-jin", classId: "infantry", level: 9, gear: ["ring-dao", "lamellar"] },
          { officer: "li-dian", classId: "archer", level: 9, gear: ["horn-bow", "leather"] },
        ],
      },
    },
    { kind: "battle", battle: B5 },
    {
      kind: "scene",
      scene: {
        id: "rise-after-puyang",
        title: "동군태수",
        place: "동군",
        when: "초평 2년(191)",
        backdrop: "field",
        lines: [
          { text: "백요를 무찌른 조조는 동군태수가 되어 동무양에 자리를 잡았다." },
          {
            who: "xun-yu",
            text: "땅을 얻으셨으니 이제 백성을 품으셔야 합니다. 백성이 따르면 군사도 군량도 따라옵니다.",
          },
        ],
      },
    },
    {
      kind: "camp",
      camp: {
        id: "rise-camp-3",
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
      },
    },
    {
      kind: "scene",
      scene: {
        id: "rise-before-shouzhang",
        title: "연주의 주인",
        place: "연주",
        when: "초평 3년(192)",
        backdrop: "field",
        lines: [
          {
            text: "청주의 황건 백만이 연주로 쏟아져 들어왔다. 연주자사 유대는 맞서 싸우다 목숨을 잃었다.",
          },
          {
            who: "chen-gong",
            text: "동군의 진궁이라 합니다. 연주에 주인이 없으니 제가 연주의 관리들을 설득하겠습니다. 공께서 연주를 맡으십시오.",
          },
          { who: "bao-xin", text: "제북상 포신, 연주의 관리들과 함께 맹덕을 연주목으로 맞이하오." },
          { who: "cao-cao", text: "연주를 지키겠소. 황건을 막지 못하면 연주는 끝이오." },
        ],
        joins: [
          { officer: "chen-gong", classId: "strategist", level: 11, gear: ["feather-fan", "robe"] },
        ],
      },
    },
    { kind: "battle", battle: B6 },
    {
      kind: "scene",
      scene: {
        id: "rise-epilogue",
        title: "종장 · 청주병",
        place: "연주",
        when: "초평 3년(192) 겨울",
        backdrop: "dawn",
        lines: [
          {
            text: "수장의 싸움에서 포신은 조조를 구하려다 목숨을 잃었다. 그의 시신은 끝내 찾지 못했다.",
          },
          {
            who: "cao-cao",
            mood: "grim",
            text: "나무를 깎아 포신의 모습을 만들어라. 내 손으로 장례를 치르겠다.",
          },
          {
            text: "그해 겨울, 청주 황건은 조조에게 항복했다. 조조는 그 가운데 날랜 자를 골라 청주병이라 불렀다.",
          },
          { who: "yu-jin", text: "포신 장군 밑에 있던 우금입니다. 이제 공을 따르겠습니다." },
          {
            who: "xun-yu",
            text: "연주는 공에게 옛날 고조의 관중과 같은 곳입니다. 이곳을 굳게 다지셔야 합니다.",
          },
          {
            who: "cao-cao",
            mood: "smile",
            text: "허소의 말이 맞았군. 세상이 어지러우니 나는 이렇게 일어선다.",
          },
          {
            text: "이듬해, 조조의 아버지 조숭이 서주 땅에서 목숨을 잃는다. 연주의 주인이 된 조조 앞에 새로운 싸움이 기다리고 있었다.",
          },
          { text: "1장 황건의 난 · 끝" },
        ],
        joins: [
          { officer: "yu-jin", classId: "infantry", level: 12, gear: ["iron-ji", "lamellar"] },
        ],
      },
    },
  ],
};
