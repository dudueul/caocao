/**
 * Chapter 4 (하북 평정) scenes and camps, staged on tile maps (ADR 0063). The
 * choice lives here: before the march to 백랑산, whether 곽가 comes along
 * (`guojia`: went / stayed, ADR 0068). Every scene plays on every path the
 * chapters before allow: 장료 stands with us unless 여포 was spared, 전위 only
 * if he lived through 완성, 진궁 only after mercy at 서주. Our own words, maps
 * and blocking throughout (ADR 0002); 조조's poems are paraphrased, never
 * quoted.
 *
 * Staging conventions (as in chapter 2): an actor who speaks has the
 * speaker's officer id as its id; extras are named after their army
 * (`cao-s1`, `yuan-s1`) and wear its look; an extra who speaks a named
 * person's line says it through `actor`. Lines from the other
 * side carry `side: 1`, and 원담's people at 업, allies then, `side: 2`.
 */
import {
  act,
  actor,
  all,
  CAMP_GROUND,
  camera,
  emote,
  enter,
  face,
  fx,
  leave,
  pause,
  say,
  sound,
  strike,
  tell,
  walk,
  when,
} from "./stage.ts";
import type {
  Actor,
  CampDef,
  CampGround,
  CampTalk,
  Cmd,
  Cond,
  Facing4,
  Line,
  Mood,
  PropDef,
  PropKind,
  SceneDef,
  StageDef,
  StageLight,
} from "./story.ts";

const mercy: Cond = { flag: "xuzhou", is: "mercy" };
const lived: Cond = { flag: "dianwei", is: "lived" };
const spared: Cond = { flag: "lubu", is: "spared" };
/** 장료 is ours unless 여포 was spared (then he left at 백문루). */
const notSpared: Cond = { flag: "lubu", not: "spared" };
const went: Cond = { flag: "guojia", is: "went" };
const stayed: Cond = { flag: "guojia", is: "stayed" };

// ---------------------------------------------------------------- small helpers

type At = [number, number];

const light = (l: StageLight): Cmd => ({ do: "light", light: l });
/** One command, only when `c` holds. */
const on = (c: Cond, cmd: Cmd): Cmd => ({ ...cmd, if: c });
/** A line from the other side: 원씨, 오환, 고간. */
const foe = (who: string, text: string, mood?: Mood, extra: Partial<Line> = {}): Cmd =>
  say(who, text, mood, { side: 1, ...extra });
/** A line from 원담's people while they fight beside us (orange, ADR 0066). */
const orange = (who: string, text: string, mood?: Mood): Cmd => say(who, text, mood, { side: 2 });
/** An extra in an army's colours. */
const extra = (
  id: string,
  officer: string,
  side: number,
  at?: At,
  facing?: Facing4,
  more: Partial<Actor> = {},
): Actor => actor(id, side, at, facing, { officer, ...more });
const prop = (kind: PropKind, x: number, y: number, more: Partial<PropDef> = {}): PropDef => ({
  kind,
  at: { x, y },
  ...more,
});
const each = (ids: readonly string[], f: (id: string) => Cmd): Cmd => all(...ids.map(f));
/** Mounted officers who kneel, bow or sit stand as infantry. */
const onFoot = { classId: "infantry" };
const theirs = { side: 1 };

// ---------------------------------------------------------------- stages

/**
 * The 황하 at 창정, as on the battle map: our bank on the south-east (right),
 * the ford, 원소's palisaded camp on the north-west bank (left).
 */
const HUANGHE_CROSSING: StageDef = {
  id: "cangting-crossing",
  light: "day",
  weather: "wind",
  landscape: { region: "plain", season: "spring" },
  rows: [
    "gggg..wwww..gggg",
    "cc....wwww...ggg",
    "c.....wwww.....g",
    "......wwww......",
    "......dddd......",
    "......wwww......",
    "c.....wwww.....g",
    "cc....wwww....gg",
    "ggg...wwww...ggg",
  ],
  props: [
    prop("fence", 5, 1),
    prop("fence", 5, 2),
    prop("fence", 5, 6),
    prop("fence", 5, 7),
    prop("tent", 2, 1, theirs),
    prop("tent", 1, 6, theirs),
    prop("banner", 3, 1, theirs),
    prop("banner", 3, 7, theirs),
    prop("screen", 0, 4),
    prop("brazier", 0, 2),
    prop("banner", 14, 2),
    prop("banner", 14, 6),
    prop("horse", 13, 7, { solid: true }),
  ],
};

/** 조조's hall at 허: a desk on the dais, carpet widening toward it. */
const XU_SEAT: StageDef = {
  id: "xu-seat",
  light: "day",
  rows: [
    "##############",
    "#___rrrrrr___#",
    "#____rrrr____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "#_____rr_____#",
    "######rr######",
  ],
  props: [
    prop("screen", 4, 1),
    prop("screen", 9, 1),
    prop("table", 6, 2),
    prop("table", 7, 2),
    prop("banner", 3, 1),
    prop("banner", 10, 1),
    prop("lantern", 1, 1),
    prop("lantern", 12, 1),
    prop("pillar", 2, 4),
    prop("pillar", 11, 4),
    prop("brazier", 1, 7),
    prop("brazier", 12, 7),
  ],
};

/**
 * Two places, a wall between: our camp in the woods of 여남 on the left, and
 * 원소's sickroom at 업 on the right, his couch under the lamps.
 */
const RUNAN_SICKROOM: StageDef = {
  id: "runan-ye-sickroom",
  light: "dusk",
  // The outdoor half: our camp in the woods of 여남 just after the battle (9th month, 201).
  landscape: { region: "huai", season: "autumn" },
  rows: [
    "ffgggfff##########",
    "fg....gg#________#",
    "g.......#________#",
    "........#________#",
    "........#________#",
    "........#________#",
    "g......g#________#",
    "gg....gg#________#",
    "ggg..ggg#####__###",
  ],
  props: [
    prop("tent", 1, 2),
    prop("banner", 6, 1),
    prop("brazier", 5, 6),
    prop("horse", 1, 6, { solid: true }),
    prop("cart", 6, 7),
    // 원소's couch: he lies on it, so it does not block.
    prop("bed", 12, 2, { solid: false }),
    prop("screen", 11, 1),
    prop("screen", 13, 1),
    prop("table", 15, 2),
    prop("lantern", 9, 1),
    prop("lantern", 16, 1),
    prop("brazier", 9, 7),
    prop("brazier", 16, 7),
  ],
};

/** 여양: 원담's town behind its wall, the fields below, the 황하 we crossed. */
const LIYANG_TOWN: StageDef = {
  id: "liyang-town",
  light: "day",
  weather: "cloudy",
  landscape: { region: "plain", season: "spring" },
  rows: [
    "vvv==========vvv",
    "v==============v",
    "================",
    "v==============v",
    "vvv==========vvv",
    "#######==#######",
    "gg............gg",
    "g..............g",
    "wwwwwwwwwwwwwwww",
  ],
  props: [
    prop("gate", 7, 5, { solid: false }),
    prop("gate", 8, 5, { solid: false }),
    prop("banner", 3, 1, theirs),
    prop("banner", 12, 1, theirs),
    prop("brazier", 2, 2),
    prop("brazier", 13, 2),
    prop("lantern", 6, 0),
    prop("lantern", 9, 0),
    prop("banner", 2, 7),
    prop("banner", 13, 7),
  ],
};

/** Our command tent after 여양: the map table in the middle, the door at the bottom. */
const LIYANG_TENT: StageDef = {
  id: "liyang-tent",
  light: "day",
  walls: "tent",
  rows: [
    "gggggggggggggg",
    "g####____####g",
    "g#__________#g",
    "g#__________#g",
    "g#__________#g",
    "g#__________#g",
    "g#__________#g",
    "g####____####g",
    "gggg......gggg",
  ],
  props: [
    prop("table", 6, 3),
    prop("table", 7, 3),
    prop("rack", 2, 2),
    prop("chest", 11, 2),
    prop("brazier", 2, 6),
    prop("brazier", 11, 6),
    prop("banner", 4, 8),
    prop("banner", 9, 8),
  ],
};

/**
 * 업 in spring 204: the 장하 at the top left with a sluice, the trench ring
 * (column 10) outside the wall, a causeway to the shut west gate, the city on
 * the right with 심배 on its tower, our siege camp at the bottom left.
 */
const YE_TRENCH: StageDef = {
  id: "ye-trench",
  light: "day",
  // The trench dug and flooded in the fifth month.
  landscape: { region: "plain", season: "summer" },
  rows: [
    "wwwwwwwggg.g#===",
    "wwwwwww....g#=vv",
    "gggggggggg.gF===",
    "gg......gg.gF=vv",
    "g........g.g#===",
    ".........g.g#=vv",
    "..........b.====",
    "ccc......g.g#=vv",
    "cccc....gg.g#===",
    "gccgg..ggg.g#vv=",
  ],
  props: [
    prop("sluice", 7, 1, { solid: true }),
    prop("gate", 12, 6, { solid: true }),
    prop("banner", 14, 2, theirs),
    prop("banner", 14, 8, theirs),
    prop("brazier", 14, 4),
    prop("tent", 1, 7),
    prop("tent", 3, 8),
    prop("banner", 1, 5),
    prop("horse", 4, 9, { solid: true }),
  ],
};
/** The trench, dug deep overnight: the ring and the channel from the 장하. */
const TRENCH = [
  ...[0, 1, 2, 3, 4, 5, 7, 8, 9].map((y) => ({ x: 10, y, terrain: "ford" as const })),
  { x: 8, y: 1, terrain: "ford" as const },
  { x: 9, y: 1, terrain: "ford" as const },
];

/**
 * North of 업: 원소's grave mound among pines, stone officials and the altar
 * before it, the spirit road down to the city's north gate.
 */
const YE_TOMB: StageDef = {
  id: "ye-tomb",
  light: "dusk",
  weather: "wind",
  landscape: { region: "plain", season: "autumn" },
  rows: [
    "fffffhhhhfffff",
    "ffffhhhhhhffff",
    "ffgghhhhhhggff",
    "fggg..==..gggf",
    "gg....==....gg",
    "g.....==.....g",
    "g.....==.....g",
    "gg....==....gg",
    "######==######",
    "vvv========vvv",
  ],
  props: [
    prop("table", 6, 3),
    prop("table", 7, 3),
    prop("statue", 4, 3),
    prop("statue", 9, 3),
    prop("lantern", 5, 3),
    prop("lantern", 8, 3),
    prop("gate", 6, 8, { solid: false }),
    prop("gate", 7, 8, { solid: false }),
    prop("banner", 3, 7),
    prop("banner", 10, 7),
  ],
};

/** 남피 in the first month: the town and its gapped wall, the river frozen hard, our camp. */
const NANPI_ICE: StageDef = {
  id: "nanpi-ice",
  light: "night",
  weather: "wind",
  // The first month: winter, the river frozen hard.
  landscape: { region: "plain", season: "winter" },
  rows: [
    "vvv==vvvvvv==vvv",
    "================",
    "###.########.###",
    "g..............g",
    "dddddddddddddddd",
    "dddddddddddddddd",
    "g..............g",
    "g..............g",
    "gg............gg",
    "ggg..........ggg",
  ],
  props: [
    prop("banner", 1, 1, theirs),
    prop("banner", 14, 1, theirs),
    prop("lantern", 4, 1),
    prop("lantern", 11, 1),
    prop("tent", 1, 8),
    prop("tent", 14, 8),
    prop("brazier", 4, 7),
    prop("brazier", 11, 7),
    prop("banner", 3, 6),
    prop("banner", 12, 6),
  ],
};

/** The same river at dawn, the ice broken through in places. */
const NANPI_DAWN: StageDef = {
  ...NANPI_ICE,
  id: "nanpi-dawn",
  light: "dusk",
  weather: "cloudy",
  rows: NANPI_ICE.rows.map((r, y) =>
    y === 4 ? "dddwwddddddwwddd" : y === 5 ? "ddddwwddddddwddd" : r,
  ),
};

/**
 * The 태항 road in winter, as we climbed it from 업: a switchback up from the
 * south-east through the mountains to a ledge, then the pass to the north-west.
 */
const TAIHANG_ROAD: StageDef = {
  id: "taihang-road",
  light: "dusk",
  weather: "wind",
  landscape: { region: "taihang", season: "winter" },
  rows: [
    "m..hmmmmmmmmmmmm",
    "m...hmmmmmmmmmmm",
    "mmh.....hhhhmmmm",
    "mmh.........hmmm",
    "mm...........hmm",
    "mmmmhhh.......hm",
    "mmmmmmmh.......h",
    "mmmmmmmmmh......",
    "mmmmmmmmmmmh....",
  ],
  props: [
    prop("brazier", 8, 3),
    prop("cart", 10, 6),
    prop("horse", 3, 4, { solid: true }),
    prop("banner", 7, 2),
  ],
};

/**
 * 무종 in the summer rains: the drowned coast road and the sea on the left,
 * our camp and its carts, and the old track up into the hills of 노룡새.
 */
const WUZHONG_RAIN: StageDef = {
  id: "wuzhong-rain",
  light: "day",
  weather: "rain",
  landscape: { region: "plain", season: "summer" },
  rows: [
    "wwwddgggmmmmm.mm",
    "wwddd.gggmmmh.mm",
    "wwwd....ggmm.hmm",
    "wwdd.....ggm.mmm",
    "wwwd......gh.mmm",
    "wwdd........hmmm",
    "wwwd.......gmmmm",
    "wwdd..ccc..ggmmm",
    "wwwdd.ccc..gggmm",
    "wwwwdd....gggggm",
  ],
  props: [
    prop("tent", 6, 7),
    prop("tent", 8, 8),
    prop("cart", 9, 7),
    prop("cart", 10, 8),
    prop("grain", 5, 8),
    prop("banner", 7, 2),
    prop("banner", 10, 3),
  ],
};

/** Under 백랑산 after the battle: the hills we came down, the steppe, the 오환 tents. */
const BAILANG_STEPPE: StageDef = {
  id: "bailang-steppe",
  light: "day",
  weather: "wind",
  landscape: { region: "frontier", season: "autumn" },
  rows: [
    "hhhhgggggggggggg",
    "hhhggg.......ggg",
    "hhgg..........gg",
    "hg.............g",
    "hh..............",
    "hg.............g",
    "hhg...........gg",
    "hhhgg.......gggg",
    "hhhhgggggggggggg",
  ],
  props: [
    prop("tent", 13, 1, theirs),
    prop("tent", 14, 6, theirs),
    prop("banner", 11, 1, theirs),
    prop("horse", 14, 2, { solid: true }),
    prop("banner", 1, 5),
  ],
};

/**
 * Two places on one stage: the camp at 역주 at the foot (a sickbed by the
 * tent), and the path up 갈석산 to the cliff top over the eastern sea, islands
 * standing in the water.
 */
const JIESHI_CLIFF: StageDef = {
  id: "jieshi-cliff",
  light: "dusk",
  weather: "wind",
  landscape: { region: "frontier", season: "autumn" },
  rows: [
    "ffggggghhhmmmmwwww",
    "fgggghhgghmmmmwwmw",
    "gg..gh...hhhhmwwww",
    "g....g....hhhmwwww",
    "g...........hmwwww",
    "g..........gmmwmww",
    "gg.........ggmmwww",
    "gg..........ggmmww",
    "ggg........gggmmww",
    "gggg......ggggmmmw",
  ],
  props: [
    prop("tent", 2, 5),
    prop("bed", 4, 6, { solid: false }),
    prop("brazier", 6, 6),
    prop("banner", 7, 5),
    prop("lantern", 2, 7),
  ],
};

// ---------------------------------------------------------------- 1. 남은 불씨

export const PROLOGUE: SceneDef = {
  id: "hebei-prologue",
  title: "남은 불씨",
  place: "창정",
  when: "건안 6년(201) 봄",
  backdrop: "field",
  stage: HUANGHE_CROSSING,
  cast: [
    actor("yuan-shao", 1, [1, 4], "right", onFoot),
    actor("yuan-shang", 1, [2, 3], "right"),
    actor("feng-ji", 1, [2, 5], "right"),
    extra("yuan-s1", "yuan", 1, [4, 3], "right"),
    extra("yuan-s2", "yuan", 1, [4, 5], "right"),
    actor("cao-cao", 0, [12, 4], "left"),
    actor("xun-yu", 0, [13, 5], "left"),
    actor("guo-jia", 0, [13, 3], "left"),
    actor("xiahou-dun", 0, [11, 3], "left"),
    actor("xu-chu", 0, [11, 5], "left"),
    actor("zhang-liao", 0, [10, 3], "left", { if: notSpared }),
    actor("lu-bu", 0, [10, 3], "left", { if: spared }),
    actor("dian-wei", 0, [10, 5], "left", { if: lived }),
    extra("cao-s1", "cao", 0, [14, 4], "left"),
  ],
  script: [
    tell("건안 6년 봄. 관도에서 진 원소는 황하 북쪽으로 물러났다."),
    tell("그는 흩어진 군사를 다시 그러모아, 창정의 강가에 진을 쳤다."),
    camera("yuan-shao", 1700),
    act("yuan-shao", "hurt"),
    emote("yuan-shao", "sweat"),
    foe("yuan-shao", "관도에서 잃은 것은 군사뿐이다. 하북은 아직 내 것이다.", "grim"),
    walk("yuan-shang", [2, 4]),
    face("yuan-shang", "yuan-shao"),
    foe("yuan-shang", "아버님, 진중의 일은 제게 맡기시고 업으로 돌아가 쉬십시오.", "grim"),
    face("yuan-shao", "yuan-shang"),
    foe("yuan-shao", "상아, 너는 형들보다 나를 많이 닮았구나.", "smile"),
    foe("feng-ji", "현보 공자께서 곁에 계시니 하북은 걱정이 없습니다.", "smile"),
    tell("돌아온 원소가 가장 먼저 한 일은, 싸우지 말라 간했던 전풍을 죽이는 것이었다."),
    face("yuan-shao", "right"),
    foe("yuan-shao", "전풍이 살아 있었다면 '그것 보라' 했겠지.", "grim"),
    foe("yuan-shao", "이제 내 앞에서 그 말을 할 자는 없다.", "angry"),
    pause(700),
    foe("yuan-shao", "맹덕… 건너오너라. 이번에는 내가 기다리고 있다.", "grim"),
    camera([11, 4], 1400),
    tell("강 동남쪽 기슭에 조조의 깃발이 늘어섰다."),
    say("xiahou-dun", "강 건너 깃발이 관도 때의 반도 안 되오. 이번에 끝장을 냅시다.", "angry"),
    on(
      notSpared,
      say(
        "zhang-liao",
        "원소의 기병은 관도에서 꺾였습니다. 강만 건너면 들판은 우리 것입니다.",
        "calm",
      ),
    ),
    on(
      spared,
      say("lu-bu", "원소가 아직 숨이 붙어 있었나. 이번엔 내 화극으로 끝내 주지.", "smile"),
    ),
    emote("cao-cao", "..."),
    say("cao-cao", "원소는 병이 깊다 들었다. 차라리 남쪽의 유표를 먼저 칠까.", "calm"),
    walk("xun-yu", [12, 5]),
    face("xun-yu", "cao-cao"),
    face("cao-cao", "xun-yu"),
    say(
      "xun-yu",
      "아닙니다. 원소는 졌으나, 하북 사람들의 마음은 아직 그를 떠나지 않았습니다.",
      "grim",
    ),
    say("xun-yu", "지금 등을 돌리시면 그는 남은 군사를 모아 허를 노릴 것입니다.", "grim"),
    say("xun-yu", "숨을 고르기 전에 끝내셔야 합니다.", "calm"),
    say("guo-jia", "병든 범은 일어서기 전에 잡는 법이지요.", "smile"),
    on(lived, say("dian-wei", "강을 건너실 때는 제가 앞에 서겠습니다.", "calm")),
    act("cao-cao", "nod"),
    say("cao-cao", "문약의 말이 옳다.", "calm"),
    face("cao-cao", "left"),
    camera([7, 4], 1000),
    say("cao-cao", "본초와는 낙양에서 함께 말을 달리던 사이였다.", "grim"),
    say("cao-cao", "그러니 그 끝은 내 손으로 맺어 주마.", "grim"),
    sound("arrive"),
    say("cao-cao", "강을 건너라!", "angry"),
    walk("cao-cao", [9, 4]),
    all(sound("water"), fx("splash", [9, 4])),
    all(emote("yuan-s1", "!"), emote("yuan-s2", "!")),
    tell("건안 6년 4월, 조조는 황하를 건너 창정의 원소를 쳤다."),
  ],
};

// ---------------------------------------------------------------- 2. 남쪽의 그림자

export const BEFORE_RUNAN: SceneDef = {
  id: "hebei-before-runan",
  title: "남쪽의 그림자",
  place: "허",
  when: "건안 6년(201)",
  backdrop: "court",
  stage: XU_SEAT,
  cast: [
    actor("cao-cao", 0, [6, 1], "down"),
    actor("xun-yu", 0, [4, 3], "right"),
    actor("xun-you", 0, [4, 4], "right"),
    actor("xiahou-dun", 0, [4, 6], "right"),
    actor("cheng-yu", 0, [9, 3], "left"),
    actor("guo-jia", 0, [9, 4], "left"),
    actor("zhang-liao", 0, [9, 6], "left", { if: notSpared }),
    actor("lu-bu", 0, [9, 6], "left", { if: spared }),
    extra("cao", "cao", 0),
  ],
  script: [
    tell("창정에서 원소를 다시 꺾은 조조는 군사를 거두어 허로 돌아왔다."),
    camera([6, 4], 1200),
    say("xun-you", "유비가 원소를 떠나 여남으로 내려갔습니다.", "grim"),
    say("xun-you", "황건의 남은 무리인 유벽, 공도와 손을 잡았다 합니다.", "grim"),
    say("cao-cao", "채양을 보냈으니 곧 소식이 오겠지.", "calm"),
    sound("hoof"),
    enter("cao", [6, 8], [6, 5], true),
    act("cao", "kneel"),
    say("cao", "아뢰옵니다! 채양 장군이 여남에서 유비에게 패해 목숨을 잃었습니다!", "hurt", {
      name: "전령",
    }),
    all(emote("cao-cao", "!"), emote("xiahou-dun", "anger"), emote("guo-jia", "!")),
    say("xiahou-dun", "유비 그자가…! 맹덕, 내가 가서 목을 따 오겠소!", "angry"),
    say("cheng-yu", "여남에서 허까지는 며칠 길입니다.", "grim"),
    say("cheng-yu", "북쪽으로 깊이 들어가시면, 유비가 반드시 등을 찌를 겁니다.", "grim"),
    say("guo-jia", "원소는 창정에서 지고 병까지 깊습니다. 한동안은 일어서지 못합니다.", "calm"),
    say("guo-jia", "그사이 남쪽부터 치시지요.", "smile"),
    walk("cao-cao", [6, 3]),
    say("cao-cao", "유비… 넘어질 때마다 다시 일어서는 사내로구나.", "grim"),
    ...when(
      notSpared,
      say("zhang-liao", "운장도 그 곁으로 돌아갔다 들었습니다.", "grim"),
      face("cao-cao", "zhang-liao"),
      say("cao-cao", "…그렇다면 여남에서 다시 보겠구나.", "grim"),
    ),
    ...when(
      spared,
      emote("lu-bu", "anger"),
      say("lu-bu", "귀 큰 놈! 백문루에서 내 목을 매달라던 그놈이로군.", "angry"),
      face("cao-cao", "lu-bu"),
      say("cao-cao", "그 빚은 여남에서 받으시오.", "smile"),
    ),
    face("cao-cao", "down"),
    leave("cao", [6, 8]),
    say("cao-cao", "북쪽은 잠시 둔다. 내가 직접 여남으로 간다.", "angry"),
    face("xun-yu", "cao-cao"),
    act("xun-yu", "bow"),
    say("xun-yu", "허는 제가 지키겠습니다. 다녀오십시오.", "calm"),
    sound("arrive"),
  ],
};

// ---------------------------------------------------------------- 3. 형주로 간 사내

export const AFTER_RUNAN: SceneDef = {
  id: "hebei-after-runan",
  title: "형주로 간 사내",
  place: "여남",
  when: "건안 7년(202)",
  backdrop: "camp",
  stage: RUNAN_SICKROOM,
  cast: [
    actor("cao-cao", 0, [4, 3], "down"),
    actor("xiahou-dun", 0, [5, 4], "left"),
    actor("cao-ren", 0, [3, 4], "right"),
    actor("xun-you", 0, [2, 3], "right"),
    actor("yuan-shao", 1, [12, 2], "down"),
    actor("yuan-shang", 1, [12, 3], "up"),
    actor("shen-pei", 1, [10, 3], "right"),
    actor("feng-ji", 1, [14, 3], "left"),
    actor("yuan-tan", 1),
  ],
  script: [
    act("yuan-shao", "down"),
    act("yuan-shang", "kneel"),
    tell("여남에서 다시 진 유비는, 남은 무리를 이끌고 형주의 유표에게 몸을 맡겼다."),
    camera([4, 4], 1500),
    say("xiahou-dun", "또 놓쳤소. 유비 그자는 지는 데에도 재주가 있구려.", "angry"),
    say("cao-cao", "유표의 그늘은 좁다. 그런 사내를 오래 품지는 못할 것이다.", "calm"),
    say(
      "xun-you",
      "유표는 제 땅을 지키는 데 만족하는 사람입니다. 당장 북쪽을 넘보진 않겠지요.",
      "calm",
    ),
    say("cao-cao", "그래. 하지만 유비는 다시 온다. 넘어질 때마다 더 단단해져서.", "grim"),
    face("cao-ren", "cao-cao"),
    say("cao-ren", "그가 다시 오면, 그때는 제가 성문 앞에 서겠습니다.", "calm"),
    act("cao-cao", "nod"),
    pause(600),
    camera([12, 3], 1600),
    tell("이듬해, 건안 7년 5월. 업."),
    tell("관도에서 진 뒤로 원소는 자리에서 일어나지 못했다."),
    foe("yuan-shao", "상아… 거기 있느냐.", "hurt"),
    foe("yuan-shang", "예, 아버님. 여기 있습니다.", "grim"),
    foe("yuan-shao", "관도의 강물 소리가… 아직도 들리는구나.", "hurt"),
    foe("yuan-shao", "맹덕에게… 하북만은…", "hurt"),
    pause(1400),
    tell("원소는 뒤를 정하지 못한 채 숨을 거두었다."),
    emote("yuan-shang", "tear"),
    foe("yuan-shang", "아버님…!", "hurt"),
    all(walk("shen-pei", [10, 5]), walk("feng-ji", [11, 5])),
    all(face("shen-pei", "feng-ji"), face("feng-ji", "shen-pei")),
    foe("shen-pei", "주공께서는 늘 현보 공자를 아끼셨소. 뜻은 분명하오.", "grim"),
    foe("feng-ji", "맏아들 현사 공자가 서면, 곽도와 신평이 우리를 두지 않을 것이오.", "grim"),
    foe("shen-pei", "유명을 받든 것으로 하겠소. 현보 공자를 세웁시다.", "calm"),
    walk("shen-pei", [11, 3]),
    face("shen-pei", "yuan-shang"),
    act("shen-pei", "kneel"),
    foe("shen-pei", "주공. 이제 하북은 주공의 것입니다.", "calm"),
    act("yuan-shang", "stand"),
    face("yuan-shang", "shen-pei"),
    foe("yuan-shang", "…아버님의 뜻이라면 받들겠소.", "grim"),
    sound("hoof"),
    enter("yuan-tan", [13, 8], [13, 5], true),
    emote("yuan-tan", "!"),
    foe("yuan-tan", "아버님…! 상이 네가 어찌 아버님의 자리에 서 있느냐!", "angry"),
    face("yuan-shang", "yuan-tan"),
    foe("yuan-shang", "형님, 아버님의 유명입니다.", "grim"),
    foe("yuan-tan", "유명? 심배와 봉기의 입에서 나온 유명이겠지.", "angry"),
    act("shen-pei", "stand"),
    face("shen-pei", "yuan-tan"),
    foe("shen-pei", "말씀을 삼가십시오, 공자.", "grim"),
    emote("yuan-tan", "anger"),
    foe("yuan-tan", "좋다. 장례를 마치면 나는 내 길을 가겠다.", "angry"),
    leave("yuan-tan", [13, 8]),
    tell("원담은 스스로 거기장군이라 일컫고 여양에 진을 쳤다."),
    tell("형제의 다툼은 그날 시작되었다."),
    camera([4, 3], 1500),
    tell("원소의 부음은 곧 조조에게도 닿았다."),
    face("cao-cao", "up"),
    emote("cao-cao", "..."),
    pause(800),
    say("cao-cao", "본초… 그대의 아들들이 그대의 땅을 찢는구나.", "grim"),
    say("xun-you", "하북의 대문이 둘이 되었습니다. 대문이 둘이면 집은 무너집니다.", "calm"),
  ],
};

// ---------------------------------------------------------------- 4. 원씨의 형제

export const BEFORE_LIYANG: SceneDef = {
  id: "hebei-before-liyang",
  title: "원씨의 형제",
  place: "여양",
  when: "건안 8년(203) 봄",
  backdrop: "field",
  stage: LIYANG_TOWN,
  cast: [
    actor("yuan-tan", 1, [7, 2], "down"),
    actor("feng-ji", 1, [9, 2], "left"),
    actor("guo-tu", 1, [5, 2], "right"),
    extra("yuan-s1", "yuan", 1, [6, 4], "down"),
    extra("yuan-s2", "yuan", 1, [9, 4], "down"),
    actor("yuan-shang", 1),
    extra("yuan-c1", "yuan-elite", 1, undefined, undefined, { classId: "cavalry" }),
    actor("cao-cao", 0),
    actor("guo-jia", 0),
    actor("zhang-he", 0),
  ],
  script: [
    tell("건안 7년 가을. 원상은 봉기를 형 원담에게 딸려 보냈다."),
    tell("돕는다는 명목이었으나, 실은 형을 지켜보게 하려는 것이었다."),
    tell("조조가 황하를 건너오자, 원담은 여양에 들어앉아 업에 군사를 더 청했다."),
    camera([7, 2], 1600),
    foe("yuan-tan", "군사는 언제 오느냐! 조조가 코앞에 와 있다!", "angry"),
    emote("feng-ji", "sweat"),
    foe("feng-ji", "업의 심 별가께서… 더 보낼 군사가 없다 하셨습니다.", "hurt"),
    face("yuan-tan", "feng-ji"),
    foe("yuan-tan", "업에는 군사가 넘치는데, 형의 여양에는 보낼 군사가 없다?", "angry"),
    walk("guo-tu", [6, 2]),
    foe("guo-tu", "공자, 이것은 공자를 조조의 칼에 맡기려는 뜻입니다.", "grim"),
    emote("yuan-tan", "anger"),
    foe("yuan-tan", "봉기, 네가 심배의 눈이고 귀였구나.", "angry"),
    walk("yuan-tan", [8, 2]),
    strike("yuan-tan", "feng-ji", true),
    sound("hit"),
    all(emote("yuan-s1", "!"), emote("yuan-s2", "!")),
    tell("원담은 그 자리에서 봉기를 베었다."),
    pause(700),
    sound("hoof"),
    all(enter("yuan-shang", [11, 0], [10, 2], true), enter("yuan-c1", [12, 0], [11, 1], true)),
    emote("yuan-shang", "!"),
    face("yuan-shang", "yuan-tan"),
    foe("yuan-shang", "형님! 봉기를 베셨습니까?", "angry"),
    face("yuan-tan", "yuan-shang"),
    foe("yuan-tan", "네가 보낸 감시꾼이었다. 이제 와서 무슨 볼일이냐.", "angry"),
    foe("yuan-shang", "여양이 무너지면 업도 위태롭습니다. 그래서 직접 왔습니다.", "grim"),
    foe("yuan-tan", "업이 위태로워서 왔다는 거로군. 형이 위태로워서가 아니라.", "grim"),
    foe("guo-tu", "두 분, 다툼은 조조를 물리친 다음에 하십시오.", "grim"),
    emote("yuan-shang", "..."),
    foe("yuan-shang", "…오늘만은 형님께 등을 맡기겠습니다.", "grim"),
    foe("yuan-tan", "오늘만이다.", "grim"),
    camera([6, 6], 1300),
    sound("move"),
    all(enter("cao-cao", [0, 7], [6, 7]), enter("guo-jia", [0, 6], [5, 6])),
    enter("zhang-he", [0, 7], [4, 7]),
    all(face("cao-cao", "up"), face("guo-jia", "up"), face("zhang-he", "up")),
    say("zhang-he", "원씨의 두 아들이 한 성에 들었습니다. 여양은 제가 잘 압니다.", "calm"),
    say("guo-jia", "형제가 나란히 섰군요. 오래 나란히 서 있을지는 모르겠습니다만.", "smile"),
    say("cao-cao", "둘이든 하나든 상관없다. 쳐서 흩어 놓아라!", "angry"),
    sound("arrive"),
  ],
};

// ---------------------------------------------------------------- 5. 형제의 칼

export const AFTER_LIYANG: SceneDef = {
  id: "hebei-after-liyang",
  title: "형제의 칼",
  place: "여양",
  when: "건안 8년(203)",
  backdrop: "camp",
  stage: LIYANG_TENT,
  cast: [
    actor("cao-cao", 0, [7, 2], "down"),
    actor("guo-jia", 0, [4, 3], "right"),
    actor("xun-you", 0, [4, 5], "right"),
    actor("xiahou-dun", 0, [9, 3], "left"),
    actor("cheng-yu", 0, [9, 5], "left"),
    actor("zhang-liao", 0, [10, 4], "left", { if: notSpared }),
    actor("lu-bu", 0, [10, 4], "left", { if: spared }),
    actor("chen-gong", 0, [3, 4], "right", { if: mercy }),
    // 원담's envoy, in his master's colours until he stays with us.
    actor("xin-pi", 2),
  ],
  script: [
    tell("여양에서 진 원담과 원상은 업으로 물러나 성문을 닫았다."),
    camera([7, 4], 1300),
    say("xiahou-dun", "업까지 몰아붙입시다. 지금이면 형제가 한꺼번에 무너지오.", "angry"),
    on(
      notSpared,
      say("zhang-liao", "적은 지쳤습니다. 기병을 앞세우면 업의 성 밑까지 하루 길입니다.", "calm"),
    ),
    on(spared, say("lu-bu", "업의 성벽쯤이야. 적토마로 한달음이오.", "smile")),
    walk("guo-jia", [5, 3]),
    face("guo-jia", "cao-cao"),
    say("guo-jia", "원양 장군, 지금 치면 형제는 다시 손을 잡습니다.", "calm"),
    say("guo-jia", "급히 치면 하나가 되고, 늦추면 서로를 칩니다.", "calm"),
    say("guo-jia", "원소가 누구에게 뒤를 맡길지 정하지 못하고 갔으니까요.", "smile"),
    say(
      "guo-jia",
      "남쪽의 유표를 치는 척하십시오. 등을 보이면 저들은 서로 칼을 겨눌 겁니다.",
      "smile",
    ),
    on(mercy, say("chen-gong", "형제가 싸우는 집에 손님이 끼면, 형제는 손님부터 칩니다.", "calm")),
    emote("cao-cao", "idea"),
    say("cao-cao", "봉효는 남의 집 싸움을 내다보는 데 재주가 있구나.", "smile"),
    say("cao-cao", "좋다. 군사를 남쪽 서평으로 돌린다.", "calm"),
    light("dusk"),
    tell("조조는 군사를 남쪽으로 돌려, 형주의 유표를 치러 가는 척했다."),
    tell("곽가의 말대로였다. 원상은 곧 형 원담을 쳤고, 원담은 평원으로 쫓겨 들어갔다."),
    light("day"),
    sound("hoof"),
    enter("xin-pi", [7, 8], [7, 5]),
    act("xin-pi", "bow"),
    orange("xin-pi", "영천의 신비, 좌치라 합니다. 원담 공의 뜻을 전하러 왔습니다.", "calm"),
    orange("xin-pi", "원담 공께서 명공께 항복하고 구원을 청하십니다.", "grim"),
    emote("xiahou-dun", "?"),
    walk("cheng-yu", [8, 4]),
    say("cheng-yu", "궁해서 온 것입니다. 숨이 돌아오면 다시 등을 돌릴 자입니다.", "grim"),
    face("cao-cao", "xin-pi"),
    say("cao-cao", "좌치, 원담을 믿어도 되겠소?", "calm"),
    orange("xin-pi", "믿음은 묻지 마십시오, 명공. 형세를 보십시오.", "calm"),
    orange("xin-pi", "형제가 서로 죽이는 사이에 하북은 텅 비었습니다.", "grim"),
    orange("xin-pi", "지금 업을 치시면, 원상은 업을 지키느라 형을 치지 못합니다.", "calm"),
    orange("xin-pi", "하북을 얻으실 때는 지금뿐입니다.", "calm"),
    say("xun-you", "신좌치의 말이 옳습니다. 하북이 다시 하나로 뭉치면 깨기 어렵습니다.", "calm"),
    say("cao-cao", "하하! 좋다. 원담을 돕는다.", "smile"),
    face("cao-cao", "guo-jia"),
    say("cao-cao", "(원담은 쓸 데까지 쓴다. 그다음 일은 그다음이다.)", "grim"),
    say("guo-jia", "(그렇다면 끈을 하나 매어 두시지요. 혼약 같은 끈 말입니다.)", "smile"),
    face("cao-cao", "xin-pi"),
    say("cao-cao", "내 아들 정과 원담 공의 딸을 혼인시키겠소. 그리 전하시오.", "calm"),
    act("xin-pi", "bow"),
    say("cao-cao", "아니, 전하는 일은 다른 이에게 맡기시오. 그대는 내 곁에 남으시오.", "smile"),
    emote("xin-pi", "!"),
    orange("xin-pi", "…명공을 따르겠습니다.", "calm"),
    act("xin-pi", "kneel"),
    tell("신비는 그날로 조조의 막하에 들었다."),
  ],
  joins: [{ officer: "xin-pi", classId: "strategist", level: 19, gear: ["crane-fan", "robe"] }],
};

// ---------------------------------------------------------------- 6. 장하의 물길

const DIGGERS = ["cao-s1", "cao-s2", "cao-s3"];

export const BEFORE_YE: SceneDef = {
  id: "hebei-before-ye",
  title: "장하의 물길",
  place: "업",
  when: "건안 9년(204) 여름",
  backdrop: "river",
  stage: YE_TRENCH,
  cast: [
    actor("shen-pei", 1, [12, 2], "left"),
    extra("yuan-w1", "yuan", 1, [12, 3], "left"),
    actor("cao-cao", 0, [6, 5], "right"),
    actor("xu-you", 0, [4, 4], "right"),
    actor("guo-jia", 0, [5, 6], "right"),
    actor("yue-jin", 0, [7, 4], "right"),
    actor("liu-ye", 0, [4, 6], "right"),
    actor("dian-wei", 0, [7, 6], "right", { if: lived }),
    actor("chen-gong", 0, [4, 5], "right", { if: mercy }),
    extra("cao-s1", "cao", 0, [9, 2], "right"),
    extra("cao-s2", "cao", 0, [9, 4], "right"),
    extra("cao-s3", "cao", 0, [9, 8], "right"),
    actor("yuan-tan", 2),
    extra("tan-s1", "yuan", 2),
    extra("tan-s2", "yuan", 2),
    extra("cao", "cao", 0),
  ],
  script: [
    tell("건안 9년 봄. 원상은 다시 평원의 원담을 치러 나가며, 업을 심배에게 맡겼다."),
    tell("조조는 그 빈틈을 놓치지 않고 업을 에워쌌다."),
    camera("shen-pei", 1600),
    foe("shen-pei", "흙산을 쌓든 땅굴을 파든 마음대로 해 보아라.", "smile"),
    foe("shen-pei", "업의 성벽은 원공께서 손수 쌓으신 것이다.", "grim"),
    camera([6, 5], 1300),
    tell("흙산은 성벽에 닿지 못했고, 땅굴은 성 안에서 마주 판 참호에 막혔다."),
    say("yue-jin", "땅굴 끝에 참호가 기다리고 있었습니다. 심배는 만만한 자가 아닙니다.", "grim"),
    walk("xu-you", [6, 4]),
    face("xu-you", "cao-cao"),
    face("cao-cao", "xu-you"),
    say("xu-you", "맹덕, 흙으로 안 되면 물을 쓰시오.", "smile"),
    face("xu-you", "up"),
    camera([7, 1], 1300),
    say("xu-you", "장하가 저기 흐르고 있소. 성을 빙 둘러 도랑을 파고 물을 끌어들이시오.", "smile"),
    say("xu-you", "업은 물 위에 뜬 섬이 될 거요.", "smile"),
    camera([6, 5], 1300),
    face("xu-you", "cao-cao"),
    emote("cao-cao", "idea"),
    say("cao-cao", "좋소, 자원. 그대 말대로 하겠소.", "smile"),
    say("xu-you", "하하, 이것으로 업도 내가 바치는 셈이오. 잊지 마시오.", "smile"),
    emote("guo-jia", "..."),
    camera([9, 5], 1100),
    each(DIGGERS, (id) => act(id, "attack")),
    all(sound("earth"), fx("dust", [10, 2]), fx("dust", [10, 4]), fx("dust", [10, 8])),
    tell("조조는 성을 둘러 도랑을 팠다. 처음에는 얕아서, 사람이 건너뛸 만했다."),
    camera("shen-pei", 1500),
    emote("shen-pei", "note"),
    foe("shen-pei", "하하하! 저런 도랑으로 무엇을 하겠다는 거냐.", "smile"),
    light("night"),
    camera([9, 5], 1000),
    tell("그날 밤, 조조의 군사는 횃불도 없이 도랑을 팠다."),
    each(DIGGERS, (id) => act(id, "attack")),
    all(sound("earth"), fx("dust", [10, 1]), fx("dust", [10, 5]), fx("dust", [10, 9])),
    { do: "tiles", tiles: TRENCH },
    light("dusk"),
    tell("날이 밝자 도랑은 깊이가 두 길, 너비도 두 길이었다."),
    camera("shen-pei", 1500),
    all(emote("shen-pei", "!"), emote("yuan-w1", "sweat")),
    foe("shen-pei", "하룻밤 새에…!", "hurt"),
    light("day"),
    camera([6, 5], 1300),
    say("liu-ye", "물이 들면 성문 앞 둑길만 남습니다. 벽력거는 그 길로 끌고 가지요.", "calm"),
    on(lived, say("dian-wei", "성문이 열리면 제가 가장 먼저 들어가겠습니다.", "angry")),
    on(mercy, say("chen-gong", "심배는 성이 물에 잠겨도 항복하지 않을 사람입니다.", "grim")),
    sound("move"),
    all(
      enter("yuan-tan", [0, 5], [3, 5]),
      enter("tan-s1", [0, 6], [2, 6]),
      enter("tan-s2", [0, 4], [2, 4]),
    ),
    face("cao-cao", "yuan-tan"),
    orange("yuan-tan", "조공! 약속대로 왔소. 상이 놈이 평원을 버리고 이리로 오고 있소.", "angry"),
    orange("yuan-tan", "그놈은 내가 막겠소.", "angry"),
    say("cao-cao", "고맙소, 원담 공. 서북쪽을 맡아 주시오.", "calm"),
    face("guo-jia", "cao-cao"),
    say("guo-jia", "(원담의 눈이 업의 성문에서 떨어지지 않는군요.)", "grim"),
    say("cao-cao", "(업이 탐나는 게지. 그러니 더 열심히 싸울 것이다.)", "calm"),
    sound("hoof"),
    enter("cao", [5, 9], [5, 7], true),
    act("cao", "kneel"),
    say("cao", "원상이 구원군을 이끌고 서쪽 산길을 따라 돌아오고 있습니다!", "hurt", {
      name: "척후",
    }),
    face("cao-cao", "up"),
    emote("cao-cao", "idea"),
    say("cao-cao", "큰길로 왔다면 피했을 것이다.", "smile"),
    say("cao-cao", "산을 끼고 온다니, 스스로 갈 곳을 좁히는구나.", "smile"),
    face("cao-cao", "right"),
    say("cao-cao", "원상이 닿기 전에 업을 친다!", "angry"),
    sound("arrive"),
  ],
};

// ---------------------------------------------------------------- 7. 업의 주인

export const AFTER_YE: SceneDef = {
  id: "hebei-after-ye",
  title: "업의 주인",
  place: "업",
  when: "건안 9년(204) 8월",
  backdrop: "court",
  stage: YE_TOMB,
  cast: [
    actor("cao-cao", 0, [6, 5], "down", onFoot),
    actor("xu-chu", 0, [5, 4], "down"),
    actor("xun-you", 0, [3, 5], "right"),
    actor("xin-pi", 0, [4, 6], "right"),
    actor("cheng-yu", 0, [9, 5], "left"),
    actor("guo-jia", 0, [9, 6], "left"),
    actor("zhang-he", 0, [10, 4], "left", onFoot),
    actor("gao-lan", 0, [11, 5], "left"),
    actor("zhang-liao", 0, [3, 6], "right", { ...onFoot, if: notSpared }),
    actor("lu-bu", 0, [3, 6], "right", { ...onFoot, if: spared }),
    actor("shen-pei", 1),
    extra("cao-g1", "cao", 0),
    extra("chen-lin", "chen-lin", 1, undefined, undefined, { classId: "strategist" }),
    actor("xu-you", 0),
  ],
  script: [
    tell("장하의 물이 업을 에워싼 지 석 달. 성 안에서는 굶어 죽은 이가 절반을 넘었다."),
    tell("건안 9년 8월, 심배의 조카 심영이 밤에 성문을 열었다. 업이 무너졌다."),
    camera([6, 6], 1300),
    sound("move"),
    all(enter("shen-pei", [6, 9], [6, 7]), enter("cao-g1", [7, 9], [7, 7])),
    face("shen-pei", "cao-cao"),
    face("cao-g1", "shen-pei"),
    act("cao-g1", "attack"),
    emote("shen-pei", "anger"),
    foe("shen-pei", "무릎은 꿇지 않는다.", "angry"),
    say("cao-cao", "성문을 연 것은 그대의 조카 심영이었소.", "calm"),
    foe("shen-pei", "쓸모없는 아이였구나. 그 아이 하나 때문에 이리 되다니.", "grim"),
    say("cao-cao", "내가 성을 돌아볼 때, 화살을 퍽 많이도 쏘더군.", "smile"),
    foe("shen-pei", "더 많지 못했던 것이 한이오.", "grim"),
    emote("xin-pi", "anger"),
    walk("xin-pi", [5, 7]),
    face("xin-pi", "shen-pei"),
    say("xin-pi", "심배! 업에 있던 우리 집안 사람들을 옥에서 죽인 것이 네놈이었지!", "angry"),
    strike("xin-pi", "shen-pei"),
    face("shen-pei", "xin-pi"),
    foe("shen-pei", "주인을 등진 자의 집안이다. 살려 둘 까닭이 없었다.", "grim"),
    emote("xin-pi", "tear"),
    walk("xin-pi", [4, 6]),
    face("xin-pi", "right"),
    face("shen-pei", "cao-cao"),
    say("cao-cao", "심배, 그대의 충절이 아깝소. 나를 돕지 않겠소?", "calm"),
    foe("shen-pei", "나는 원씨의 신하로 살았고, 원씨의 신하로 죽소.", "calm"),
    foe("shen-pei", "내 주인은 북쪽에 계시오. 북쪽을 보고 죽게 해 주시오.", "calm"),
    pause(800),
    say("cao-cao", "…그리하라.", "grim"),
    walk("cao-cao", [8, 5]),
    face("cao-cao", "shen-pei"),
    walk("shen-pei", [6, 4]),
    walk("cao-g1", [7, 4]),
    face("shen-pei", "up"),
    camera([6, 3], 1400),
    act("shen-pei", "kneel"),
    tell("심배는 북쪽 원소의 무덤을 향해 무릎을 꿇고, 목을 내밀었다."),
    tell("끝까지 얼굴빛 하나 변하지 않았다."),
    pause(900),
    all(leave("shen-pei", [6, 9]), leave("cao-g1", [7, 9])),
    on(notSpared, say("zhang-liao", "…고순 장군도 백문루에서 저러했습니다.", "grim")),
    on(spared, emote("lu-bu", "...")),
    on(spared, say("lu-bu", "…목숨을 빌지 않는 자도 있군.", "grim")),
    camera([6, 6], 1300),
    all(enter("chen-lin", [6, 9], [6, 6]), enter("cao-g1", [7, 9], [7, 7])),
    act("chen-lin", "kneel"),
    face("cao-cao", "chen-lin"),
    say("cao-cao", "진림. 그대의 격문은 나 하나로 모자라 내 아버지와 할아버지까지 욕했지.", "grim"),
    say("chen-lin", "시위에 얹힌 화살은 과녁을 고르지 못하는 법입니다.", "grim", { side: 1 }),
    pause(600),
    say("cao-cao", "하하, 그 붓이 아깝구나. 이제부터는 내 곁에서 들어라.", "smile"),
    tell("조조는 원소의 격문을 쓴 진림을 용서하고, 그에게 문서를 맡겼다."),
    all(leave("chen-lin", [6, 9]), leave("cao-g1", [7, 9])),
    walk("cao-cao", [6, 4]),
    face("cao-cao", "up"),
    camera([6, 3], 1500),
    act("cao-cao", "kneel"),
    tell("조조는 원소의 무덤에 제사를 올리고, 소리 내어 울었다."),
    emote("cao-cao", "tear"),
    say("cao-cao", "본초. 처음 군사를 일으키던 날, 그대가 내게 물었지.", "hurt"),
    say("cao-cao", "일이 틀어지면 어디에 기대겠느냐고.", "hurt"),
    say("cao-cao", "그대는 황하와 북쪽 땅에 기대었고, 나는 천하의 사람에 기대었다.", "grim"),
    say("cao-cao", "…그 차이뿐이었다.", "hurt"),
    all(emote("zhang-he", "..."), emote("gao-lan", "...")),
    say("zhang-he", "옛 주인의 무덤 앞에 이렇게 서게 될 줄은 몰랐습니다.", "grim"),
    tell("조조는 원소의 아내를 위로하고, 집안의 재물과 보화를 돌려주게 했다."),
    act("cao-cao", "stand"),
    sound("move"),
    enter("xu-you", [7, 9], [7, 6]),
    camera([7, 5], 1400),
    say("xu-you", "아만! 내가 아니었다면 그대가 이 성문을 지날 수 있었겠소?", "smile"),
    tell("허유는 사람들 앞에서 조조를 어릴 적 이름으로 불렀다."),
    all(emote("xu-chu", "anger"), emote("cheng-yu", "anger"), emote("guo-jia", "sweat")),
    face("cao-cao", "xu-you"),
    say("cao-cao", "…그대 말이 맞소, 자원.", "smile"),
    say("xu-you", "하하하! 맹덕은 역시 은혜를 아는 사람이오!", "smile"),
    leave("xu-you", [7, 9]),
    tell("그 뒤로도 허유는 성문을 지날 때마다 같은 말을 되풀이했다."),
    pause(800),
    say("cheng-yu", "공을 세운 자가 공을 자랑하면, 그 공이 그를 죽입니다.", "grim"),
    face("cao-cao", "xu-chu"),
    say("cao-cao", "…허저.", "grim"),
    act("xu-chu", "bow"),
    walk("xu-chu", [5, 6]),
    leave("xu-chu", [6, 9]),
    pause(1000),
    tell("허유는 옥에 갇혔고, 그곳에서 죽임을 당했다."),
    face("cao-cao", "up"),
    camera([6, 2], 1200),
    say("cao-cao", "업은 얻었다. 그러나 하북은 아직이다.", "grim"),
  ],
  leaves: ["xu-you"],
};

// ---------------------------------------------------------------- 8. 얼어붙은 강

export const BEFORE_NANPI: SceneDef = {
  id: "hebei-before-nanpi",
  title: "얼어붙은 강",
  place: "남피",
  when: "건안 10년(205) 정월",
  backdrop: "night",
  stage: NANPI_ICE,
  cast: [
    actor("yuan-tan", 1, [7, 1], "down"),
    actor("guo-tu", 1, [5, 1], "right"),
    actor("xin-ping", 1, [9, 1], "left"),
    actor("cao-cao", 0, [7, 7], "up"),
    actor("cao-ren", 0, [5, 7], "up"),
    actor("guo-jia", 0, [6, 8], "up"),
    actor("xin-pi", 0, [9, 8], "up"),
    actor("yu-jin", 0, [9, 7], "up"),
    actor("zhang-liao", 0, [8, 8], "up", { if: notSpared }),
    actor("lu-bu", 0, [8, 8], "up", { if: spared }),
    actor("cao-chun", 0, undefined, undefined, { classId: "cavalry" }),
    extra("cao-r1", "cao", 0, undefined, undefined, { classId: "cavalry" }),
    extra("cao-r2", "cao", 0, undefined, undefined, { classId: "cavalry" }),
  ],
  script: [
    camera([7, 1], 1600),
    tell("업을 에워싼 동안, 원담은 감릉과 안평, 발해, 하간을 차례로 차지했다."),
    tell("원상이 중산으로 달아나자, 원담은 그를 쳐서 남은 무리마저 거두었다."),
    foe("yuan-tan", "조조는 업을 먹었다. 나는 하북의 나머지를 먹는다.", "smile"),
    foe("yuan-tan", "그게 무엇이 잘못이냐.", "smile"),
    face("xin-ping", "yuan-tan"),
    foe("xin-ping", "공자, 조공과는 약조가 있습니다. 혼약까지 맺지 않았습니까.", "grim"),
    face("yuan-tan", "xin-ping"),
    foe("yuan-tan", "약조는 그자가 업에 들어앉은 날 끝났다.", "angry"),
    foe("guo-tu", "강이 얼었고 성은 단단합니다. 이 겨울에는 조조도 오지 못합니다.", "calm"),
    camera([7, 7], 1400),
    tell("건안 10년 정월. 조조는 한겨울에 군사를 일으켰다."),
    say("cao-cao", "원담이 내가 업을 치는 틈에 고을들을 집어삼켰다.", "grim"),
    say("cao-cao", "혼약을 거두어라. 그 딸은 아비에게 돌려보내라.", "grim"),
    say("guo-jia", "혼약이란 깨기 쉬운 끈이라 말씀드렸지요.", "smile"),
    say("yu-jin", "강이 얼어 배를 띄울 수 없습니다. 백성을 불러 얼음을 깰까요?", "calm"),
    say("cao-cao", "아니다. 얼음이 버티는 동안은 얼음이 곧 다리다.", "calm"),
    say("cao-cao", "날이 풀리면 이 강이 우리를 삼킨다. 서둘러야 한다.", "grim"),
    on(
      notSpared,
      say("zhang-liao", "말굽에 짚을 감게 했습니다. 얼음 위에서도 미끄러지지 않을 겁니다.", "calm"),
    ),
    on(spared, say("lu-bu", "얼음이든 물이든, 적토마는 가리지 않소.", "smile")),
    face("cao-cao", "xin-pi"),
    say("cao-cao", "좌치, 그대의 형 신평이 저 성 안에 있다지.", "calm"),
    emote("xin-pi", "..."),
    say("xin-pi", "…형님은 원씨를 저버리지 못하는 분입니다.", "grim"),
    say("xin-pi", "싸움터에서 만나면, 저는 주공의 신하로 서겠습니다.", "grim"),
    say("cao-cao", "그대의 형은 해치지 말라고 일러 두겠다.", "calm"),
    act("xin-pi", "bow"),
    sound("hoof"),
    all(
      enter("cao-chun", [0, 7], [3, 7], true),
      enter("cao-r1", [0, 6], [2, 6], true),
      enter("cao-r2", [0, 8], [2, 8], true),
    ),
    face("cao-cao", "cao-chun"),
    say("cao-chun", "조순, 자화입니다! 호표기를 이끌고 왔습니다.", "calm"),
    face("cao-ren", "cao-chun"),
    say("cao-ren", "자화! 주공, 제 아우입니다.", "smile"),
    say("cao-cao", "호표기는 백인장들 가운데서 다시 골라 뽑은 자들이다.", "smile"),
    say("cao-cao", "자화, 그들을 그대에게 맡기겠다.", "smile"),
    say("cao-chun", "원담의 깃발은 호표기가 꺾겠습니다.", "angry"),
    face("cao-cao", "up"),
    camera([7, 4], 1000),
    say("cao-cao", "얼음이 녹기 전에 건넌다!", "angry"),
    sound("arrive"),
  ],
  joins: [{ officer: "cao-chun", classId: "cavalry", level: 20, gear: ["horse-lance", "scale"] }],
};

// ---------------------------------------------------------------- 9. 북쪽으로 간 형제

export const AFTER_NANPI: SceneDef = {
  id: "hebei-after-nanpi",
  title: "북쪽으로 간 형제",
  place: "남피",
  when: "건안 10년(205)",
  backdrop: "dawn",
  stage: NANPI_DAWN,
  cast: [
    actor("yuan-tan", 1, [7, 4], "down"),
    extra("wang-xiu", "wang-xiu", 1, [6, 4], "right", { classId: "strategist" }),
    actor("cao-chun", 0, [8, 6], "up"),
    extra("cao-r1", "cao", 0, [9, 6], "up", { classId: "cavalry" }),
    actor("cao-cao", 0),
    actor("xun-you", 0),
    actor("xin-pi", 0),
    actor("dian-wei", 0, undefined, undefined, { if: lived }),
    actor("chen-gong", 0, undefined, undefined, { if: mercy }),
    extra("cao", "cao", 0),
  ],
  script: [
    act("yuan-tan", "down"),
    act("wang-xiu", "kneel"),
    tell("남피의 싸움은 새벽까지 이어졌다."),
    tell("원담은 성을 버리고 달아나다, 조순의 호표기에 따라잡혔다."),
    camera([7, 4], 1600),
    emote("wang-xiu", "tear"),
    pause(900),
    sound("move"),
    all(
      enter("cao-cao", [7, 9], [7, 6]),
      enter("xun-you", [6, 9], [5, 7]),
      enter("xin-pi", [8, 9], [9, 7]),
      on(lived, enter("dian-wei", [5, 9], [6, 7])),
      on(mercy, enter("chen-gong", [4, 9], [4, 8])),
    ),
    on(lived, say("dian-wei", "주공, 이 근처는 얼음이 얇습니다. 발밑을 살피십시오.", "calm")),
    face("cao-chun", "cao-cao"),
    say("cao-chun", "원담은 호표기가 베었습니다.", "calm"),
    say("cao-chun", "저 사람은 밤새 시신 곁을 떠나지 않았습니다.", "calm"),
    face("cao-cao", "wang-xiu"),
    say("cao-cao", "그대는 누구냐.", "calm"),
    face("wang-xiu", "cao-cao"),
    say("wang-xiu", "원담의 별가 왕수라 합니다.", "grim", { side: 1 }),
    say("wang-xiu", "주인의 시신을 거두게 해 주신다면, 그다음엔 죽어도 한이 없습니다.", "hurt", {
      side: 1,
    }),
    pause(700),
    emote("cao-cao", "..."),
    say("cao-cao", "하북에는 이런 사람이 있었구나. 시신을 거두게 하라.", "calm"),
    act("wang-xiu", "bow"),
    tell("조조는 왕수를 용서하고, 원담의 장례를 치르게 했다."),
    say("xun-you", "원씨의 신하들은 끝까지 충직했습니다. 아들들만 그러지 못했지요.", "grim"),
    on(
      mercy,
      say("chen-gong", "왕수 같은 이가 하북에는 아직 많습니다. 그들을 거두십시오.", "grim"),
    ),
    face("xin-pi", "up"),
    emote("xin-pi", "..."),
    pause(800),
    sound("hoof"),
    enter("cao", [0, 6], [4, 6], true),
    act("cao", "kneel"),
    say("cao", "원상과 원희가 유주를 버리고 오환의 답돈에게 달아났습니다!", "hurt", {
      name: "전령",
    }),
    all(emote("xun-you", "!"), emote("cao-chun", "!")),
    say("xun-you", "원씨 형제가 오환의 기병을 등에 업으면, 북쪽은 끝나지 않습니다.", "grim"),
    face("cao-cao", "up"),
    camera([7, 1], 1300),
    say("cao-cao", "원씨의 불씨가 남아 있는 한, 북쪽 끝까지 간다.", "grim"),
  ],
};

// ---------------------------------------------------------------- 10. 태항산을 넘어

export const BEFORE_HUGUAN: SceneDef = {
  id: "hebei-before-huguan",
  title: "태항산을 넘어",
  place: "태항산",
  when: "건안 11년(206) 정월",
  backdrop: "night",
  stage: TAIHANG_ROAD,
  cast: [
    actor("yue-jin", 0, [6, 3], "right"),
    actor("li-dian", 0, [5, 4], "right"),
    extra("cao-s1", "cao", 0),
    extra("cao-s2", "cao", 0),
    actor("cao-cao", 0),
    actor("xu-chu", 0),
    actor("guo-jia", 0),
    actor("dian-wei", 0, undefined, undefined, { if: lived }),
    actor("chen-gong", 0, undefined, undefined, { if: mercy }),
  ],
  script: [
    tell("원소의 조카 고간은 병주를 들어 항복했었다."),
    tell("그러나 조조가 북쪽에 매인 틈에 다시 등을 돌려, 태항산의 호관을 막았다."),
    tell("건안 11년 정월. 조조는 몸소 군사를 이끌고 눈 덮인 태항산을 넘었다."),
    sound("wind"),
    camera([12, 6], 1300),
    all(enter("cao-s1", [15, 7], [8, 5]), enter("cao-s2", [15, 8], [13, 6])),
    act("cao-s2", "down"),
    emote("cao-s2", "sweat"),
    all(enter("cao-cao", [15, 7], [9, 4]), enter("xu-chu", [15, 8], [10, 5])),
    all(enter("guo-jia", [15, 7], [11, 4]), on(lived, enter("dian-wei", [15, 8], [11, 5]))),
    on(mercy, enter("chen-gong", [15, 7], [12, 4])),
    act("cao-s2", "stand"),
    camera([8, 4], 1300),
    all(sound("earth"), fx("dust", [10, 6])),
    say("li-dian", "수레바퀴가 또 부서졌습니다. 오늘만 벌써 세 번째입니다.", "grim"),
    say("yue-jin", "앞쪽 고개는 얼음입니다. 말은 끌고, 사람은 기어서 넘어야 합니다.", "grim"),
    face("cao-cao", "up"),
    camera([7, 2], 1000),
    tell("북풍이 골짜기를 울렸다. 눈은 그치지 않았고, 묵어 갈 마을 하나 보이지 않았다."),
    say("cao-cao", "산이 높으니 길이 굽고, 길이 굽으니 바퀴가 부서지는구나.", "grim"),
    say("cao-cao", "바람에 나무가 운다. 어둠 속에서 짐승들이 우리를 지켜보고 있다.", "grim"),
    say("cao-cao", "물을 길으려면 얼음을 깨야 하고, 밥을 지으려면 눈을 녹여야 한다.", "grim"),
    all(emote("cao-s1", "..."), emote("cao-s2", "...")),
    light("night"),
    tell("그날 밤, 조조는 시를 지었다. 높은 산과 굽은 길, 슬피 우는 바람을 노래했다."),
    tell("집을 떠나온 군사들의 한숨과, 끝이 보이지 않는 먼 길을 노래했다."),
    say("cao-cao", "옛날 주공도 동쪽 원정길에서 이렇게 고향을 그렸겠지.", "grim"),
    act("guo-jia", "hurt"),
    emote("guo-jia", "sweat"),
    face("cao-cao", "guo-jia"),
    say("cao-cao", "봉효, 기침이 깊구나.", "grim"),
    say("guo-jia", "추위가 술보다 독하군요. 걱정 마십시오.", "smile"),
    on(lived, say("dian-wei", "주공, 제 갖옷을 두르십시오. 저는 추위를 모릅니다.", "calm")),
    on(
      mercy,
      say(
        "chen-gong",
        "고간은 원씨의 마지막 가지입니다. 병주를 얻으면 서쪽 근심이 사라집니다.",
        "calm",
      ),
    ),
    face("xu-chu", "cao-cao"),
    say("xu-chu", "주공, 불을 피웠습니다. 몸을 녹이십시오.", "smile"),
    walk("cao-cao", [9, 3]),
    face("cao-cao", "left"),
    camera([8, 3], 1500),
    say("cao-cao", "저 고개 너머가 호관이다. 고간은 산이 자기를 지켜 주리라 믿겠지.", "calm"),
    say("cao-cao", "그 믿음을 깨 주마.", "angry"),
  ],
};

// ---------------------------------------------------------------- 11. 노룡새의 옛길 (choice)

export const BEFORE_BAILANG: SceneDef = {
  id: "hebei-before-bailang",
  title: "노룡새의 옛길",
  place: "무종",
  when: "건안 12년(207) 여름",
  backdrop: "camp",
  stage: WUZHONG_RAIN,
  cast: [
    actor("cao-cao", 0, [7, 4], "down"),
    actor("guo-jia", 0, [5, 5], "right"),
    actor("cao-ren", 0, [5, 3], "right"),
    actor("xiahou-dun", 0, [8, 5], "left"),
    actor("zhang-liao", 0, [9, 5], "left", { if: notSpared }),
    actor("lu-bu", 0, [9, 5], "left", { if: spared }),
    actor("tian-chou", 0),
    extra("cao", "cao", 0),
  ],
  script: [
    tell("건안 12년 여름. 조조는 오환을 치러 북쪽 무종에 이르렀다."),
    sound("water"),
    camera([7, 4], 1300),
    say(
      "cao-ren",
      "주공, 멀리 사막까지 가신 사이에 유비가 유표를 부추겨 허를 치면 어찌합니까.",
      "grim",
    ),
    say("xiahou-dun", "허는 비어 있고 돌아갈 길은 멀소. 맹덕, 다시 생각하시오.", "grim"),
    act("guo-jia", "hurt"),
    emote("guo-jia", "sweat"),
    say("guo-jia", "유표는 앉아서 말만 하는 사람입니다.", "calm"),
    say(
      "guo-jia",
      "유비를 크게 쓰자니 다룰 자신이 없고, 작게 쓰자니 유비가 따르지 않지요.",
      "calm",
    ),
    say("guo-jia", "그러니 허를 비우셔도 근심할 것 없습니다.", "smile"),
    walk("guo-jia", [6, 4]),
    face("guo-jia", "cao-cao"),
    face("cao-cao", "guo-jia"),
    say("guo-jia", "싸움은 귀신같이 빨라야 합니다. 천 리 길에 짐이 무거우면 늦습니다.", "grim"),
    say("guo-jia", "가벼운 기병으로, 저들이 모를 때 치십시오.", "grim"),
    say("cao-cao", "봉효의 말이 옳다.", "calm"),
    sound("hoof"),
    enter("cao", [4, 0], [5, 2], true),
    act("cao", "kneel"),
    say("cao", "바닷가 길이 모두 물에 잠겼습니다!", "hurt", { name: "척후" }),
    say("cao", "얕아서 배도 못 띄우고, 깊어서 수레도 지나지 못합니다.", "hurt", { name: "척후" }),
    face("cao-cao", "left"),
    camera([3, 4], 1200),
    emote("cao-cao", "..."),
    say("cao-cao", "하늘이 길을 막는구나.", "grim"),
    leave("cao", [4, 0]),
    camera([12, 2], 1300),
    enter("tian-chou", [13, 0], [11, 5]),
    say("tian-chou", "길이 없는 것은 아닙니다.", "calm"),
    walk("tian-chou", [10, 4]),
    camera([8, 4], 1300),
    face("cao-cao", "tian-chou"),
    face("tian-chou", "cao-cao"),
    act("tian-chou", "bow"),
    say("tian-chou", "무종의 전주, 자태라 합니다.", "calm"),
    say(
      "tian-chou",
      "노룡새를 넘는 옛길이 있습니다. 이백 년 동안 아무도 다니지 않아 끊겼지요.",
      "calm",
    ),
    say(
      "tian-chou",
      "그래도 흔적은 남았습니다. 그 길로 가면 오환은 우리가 온 줄도 모를 겁니다.",
      "calm",
    ),
    say("cao-cao", "그대는 왜 나를 돕소?", "calm"),
    say(
      "tian-chou",
      "오환은 이 고을 사람들을 숱하게 죽였습니다. 그 원수를 잊은 날이 없습니다.",
      "grim",
    ),
    say("cao-cao", "좋다. 그대가 길잡이다.", "smile"),
    say("cao-cao", "길가에 팻말을 세워라. '여름에는 길이 막혀, 가을과 겨울에 다시 온다.'", "smile"),
    tell("오환의 척후는 그 팻말을 보고, 조조가 물러간 줄로 알았다."),
    on(notSpared, say("zhang-liao", "가벼운 기병이라면 제가 앞장서겠습니다.", "calm")),
    on(spared, say("lu-bu", "빠른 싸움이라면 적토마를 따를 말이 없지.", "smile")),
    act("guo-jia", "hurt"),
    emote("guo-jia", "sweat"),
    act("guo-jia", "kneel"),
    all(emote("cao-cao", "!"), emote("xiahou-dun", "!")),
    face("cao-cao", "guo-jia"),
    camera([6, 4], 1600),
    say("cao-cao", "봉효!", "hurt"),
    say("guo-jia", "괜찮습니다… 물과 바람이 맞지 않아 탈이 났을 뿐입니다.", "hurt"),
    say("cao-cao", "그 몸으로는 사막을 건널 수 없다.", "grim"),
    say("guo-jia", "이것은 제 계책입니다. 제가 가지 않으면…", "hurt"),
    camera("cao-cao", 1700),
    pause(800),
    {
      do: "choice",
      flag: "guojia",
      prompt: "병든 곽가를 어떻게 할 것인가?",
      options: [
        { value: "went", text: "봉효의 계책이 필요하다. 함께 간다" },
        { value: "stayed", text: "봉효는 허로 돌아가 몸을 돌보게 하라" },
      ],
    },
    // Went: he rides with us, and the baggage stays behind.
    ...when(
      went,
      act("guo-jia", "stand"),
      say("guo-jia", "끝은 제 눈으로 보겠습니다. 수레에 누워서라도 따라가겠습니다.", "smile"),
      pause(600),
      say("cao-cao", "…좋다. 치중은 모두 여기 둔다. 날랜 기병만 데리고 간다.", "grim"),
      emote("xiahou-dun", "!"),
      say("xiahou-dun", "치중을 다 버린다니…!", "angry"),
      say("guo-jia", "짐이 없어야 빠릅니다. 빠른 것이 곧 이기는 것이지요.", "smile"),
    ),
    // Stayed: he goes home with a letter left behind; the march keeps its carts.
    ...when(
      stayed,
      say("cao-cao", "봉효, 허로 돌아가라. 이것은 명이다.", "grim"),
      emote("guo-jia", "..."),
      act("guo-jia", "stand"),
      say("guo-jia", "…명을 받들겠습니다.", "grim"),
      sound("page"),
      say("guo-jia", "가시는 길에 이것을 펴 보십시오. 제가 드릴 것은 이것뿐입니다.", "calm"),
      act("guo-jia", "bow"),
      leave("guo-jia", [5, 9]),
      face("cao-cao", "down"),
      pause(700),
      say("cao-cao", "치중은 버리지 않는다. 봉효 없이 서두르다가는 사막에서 길을 잃는다.", "grim"),
      say("xiahou-dun", "늦어지면 오환이 눈치챌 텐데…", "grim"),
      say("cao-cao", "안다. 그래도 군사를 굶겨 죽일 수는 없다.", "grim"),
    ),
    face("cao-cao", "tian-chou"),
    say("cao-cao", "전주, 앞장서라. 노룡새로 간다!", "angry"),
    sound("arrive"),
    camera([12, 2], 1200),
    leave("tian-chou", [13, 0]),
    all(leave("cao-cao", [13, 0]), on(went, leave("guo-jia", [13, 0]))),
    on(went, tell("조조는 수레를 버리고, 날랜 기병만으로 노룡새의 옛길에 들어섰다.")),
    on(stayed, tell("조조의 군사는 수레를 끌고 노룡새의 옛길에 들어섰다. 걸음은 더뎠다.")),
  ],
  joins: [{ officer: "tian-chou", classId: "strategist", level: 19, gear: ["crane-fan", "robe"] }],
};

// ---------------------------------------------------------------- 12. 백랑산의 바람

const WUHUAN = ["wh-1", "wh-2", "wh-3", "wh-4"];

export const AFTER_BAILANG: SceneDef = {
  id: "hebei-after-bailang",
  title: "백랑산의 바람",
  place: "백랑산",
  when: "건안 12년(207) 8월",
  backdrop: "field",
  stage: BAILANG_STEPPE,
  cast: [
    actor("cao-cao", 0, [4, 4], "right"),
    actor("xiahou-dun", 0, [3, 3], "right"),
    actor("zhang-he", 0, [3, 5], "right"),
    actor("tian-chou", 0, [4, 6], "right"),
    actor("guo-jia", 0, [2, 4], "right", { if: went }),
    extra("wh-1", "wuhuan", 1, [10, 3], "left", { classId: "infantry" }),
    extra("wh-2", "wuhuan", 1, [10, 5], "left", { classId: "infantry" }),
    extra("wh-3", "wuhuan", 1, [12, 3], "left", { classId: "infantry" }),
    extra("wh-4", "wuhuan", 1, [12, 5], "left", { classId: "infantry" }),
    actor("zhang-liao", 0, undefined, undefined, { if: notSpared }),
    actor("lu-bu", 0, undefined, undefined, { if: spared }),
    extra("cao", "cao", 0),
  ],
  script: [
    each(WUHUAN, (id) => act(id, "kneel")),
    on(went, act("guo-jia", "kneel")),
    tell("백랑산에서 오환의 대군은 무너졌다. 답돈은 싸움터에서 목숨을 잃었다."),
    tell("오환과 한인 이십여만 명이 무기를 버리고 항복했다."),
    camera([8, 4], 1200),
    sound("hoof"),
    on(notSpared, enter("zhang-liao", [15, 4], [5, 4], true)),
    on(spared, enter("lu-bu", [15, 4], [5, 4], true)),
    ...when(
      notSpared,
      say("zhang-liao", "답돈의 깃발입니다. 그의 목도 거두었습니다.", "calm"),
      say("cao-cao", "문원, 그대가 깃발을 들고 산을 내달릴 때 땅이 울리는 줄 알았다.", "smile"),
    ),
    ...when(
      spared,
      say("lu-bu", "답돈이라는 자, 내 화극을 세 합도 받지 못하더군.", "smile"),
      say("cao-cao", "백문루에서 포승을 푼 값을 오늘 받았소.", "smile"),
      emote("lu-bu", "note"),
    ),
    camera([8, 4], 1100),
    say("cao-cao", "항복한 자는 해치지 마라. 오환의 기병은 이제 우리의 기병이다.", "calm"),
    sound("hoof"),
    enter("cao", [8, 0], [7, 3], true),
    act("cao", "kneel"),
    say("cao", "원상과 원희가 수천 기를 이끌고 요동의 공손강에게 달아났습니다!", "hurt", {
      name: "척후",
    }),
    say("xiahou-dun", "요동까지 쫓읍시다! 이참에 공손강까지 꺾어 버리면 그만이오.", "angry"),
    say("zhang-he", "말을 달리면 열흘 길입니다. 명을 내려 주십시오.", "calm"),
    act("cao-cao", "shake"),
    say("cao-cao", "쫓지 않는다.", "calm"),
    all(emote("xiahou-dun", "?"), emote("zhang-he", "?")),
    ...when(
      went,
      emote("guo-jia", "sweat"),
      say("guo-jia", "주공의 생각이 맞습니다… 공손강은 원씨가 오는 것을 반기지 않습니다.", "hurt"),
      say("guo-jia", "우리가 밀면 그들은 손을 잡고, 물러서면 서로를 칠 것입니다.", "hurt"),
      face("cao-cao", "guo-jia"),
      say("cao-cao", "봉효, 말하지 마라. 이제 돌아가자.", "grim"),
    ),
    ...when(
      stayed,
      sound("page"),
      say("cao-cao", "봉효가 떠나며 남긴 글이 있다.", "calm"),
      say("guo-jia", "원씨 형제가 요동으로 달아나거든 쫓지 마십시오.", "calm", {
        name: "곽가의 편지",
      }),
      say(
        "guo-jia",
        "공손강은 그들을 두려워합니다. 밀지 않으면 그가 먼저 칼을 들 것입니다.",
        "calm",
        {
          name: "곽가의 편지",
        },
      ),
      say("cao-cao", "봉효는 허에 앉아서도 요동을 보는구나.", "smile"),
    ),
    face("cao-cao", "right"),
    say("cao-cao", "군사를 돌려라. 머지않아 공손강이 두 사람의 목을 보내올 것이다.", "calm"),
    emote("xiahou-dun", "..."),
    say("xiahou-dun", "…정말 그리 될지, 두고 보겠소.", "grim"),
    sound("wind"),
    camera([8, 4], 1000),
  ],
};

// ---------------------------------------------------------------- 13. 갈석에 올라

export const EPILOGUE: SceneDef = {
  id: "hebei-epilogue",
  title: "갈석에 올라",
  place: "역주 · 갈석산",
  when: "건안 12년(207) 가을",
  backdrop: "dawn",
  stage: JIESHI_CLIFF,
  cast: [
    actor("cao-cao", 0, [5, 7], "up", onFoot),
    actor("xiahou-dun", 0, [7, 7], "left", onFoot),
    actor("xun-you", 0, [3, 8], "up"),
    actor("zhang-he", 0, [8, 6], "left", onFoot),
    actor("xu-chu", 0, [5, 8], "up"),
    actor("zhang-liao", 0, [8, 8], "left", { ...onFoot, if: notSpared }),
    actor("lu-bu", 0, [8, 8], "left", { ...onFoot, if: spared }),
    // 곽가 lies on the sickbed at 역주.
    actor("guo-jia", 0, [4, 6], "down", { if: went }),
    extra("cao", "cao", 0),
  ],
  script: [
    on(went, act("guo-jia", "down")),
    tell("건안 12년 가을. 조조의 군사는 사막을 건너 돌아오는 길에 올랐다."),
    camera([5, 7], 1400),
    sound("hoof"),
    enter("cao", [0, 7], [3, 7], true),
    act("cao", "kneel"),
    say("cao", "요동의 공손강이 사자를 보냈습니다. 원상과 원희의 목입니다!", "calm", {
      name: "전령",
    }),
    all(emote("xiahou-dun", "!"), emote("zhang-he", "!"), emote("xun-you", "!")),
    face("xiahou-dun", "cao-cao"),
    say("xiahou-dun", "정말로…! 맹덕은 어찌 알았소?", "calm"),
    face("cao-cao", "xiahou-dun"),
    say("cao-cao", "공손강은 원씨를 늘 두려워했다.", "calm"),
    say("cao-cao", "우리가 밀면 손을 잡고, 늦추면 서로 죽인다. 형세가 그랬을 뿐이다.", "calm"),
    on(notSpared, say("zhang-liao", "원씨의 마지막 불씨가 꺼졌습니다.", "calm")),
    on(spared, say("lu-bu", "싸우지도 않고 목 둘을 얻다니. 이런 싸움도 있군.", "smile")),
    leave("cao", [0, 7], true),
    // Went: 곽가 dies on the way home, at 역주.
    ...when(
      went,
      camera([4, 6], 1700),
      walk("cao-cao", [4, 7]),
      face("cao-cao", "guo-jia"),
      act("cao-cao", "kneel"),
      say("guo-jia", "주공… 요동의 일은… 들었습니다.", "hurt"),
      say("guo-jia", "남쪽은… 함께 가지 못하겠군요.", "hurt"),
      say("cao-cao", "무슨 소리냐. 봉효, 너는 아직 젊다.", "hurt"),
      say("guo-jia", "술을… 한 잔만…", "smile"),
      pause(1500),
      tell("곽가는 역주에서 숨을 거두었다. 서른여덟이었다."),
      emote("cao-cao", "tear"),
      say("cao-cao", "봉효…!", "hurt"),
      all(emote("xiahou-dun", "tear"), emote("xun-you", "tear"), emote("xu-chu", "tear")),
      say("cao-cao", "그대들은 모두 나와 나이가 비슷하다. 봉효만이 젊었다.", "hurt"),
      say("cao-cao", "천하가 정해지면 뒷일을 맡기려 했는데…", "hurt"),
      say("cao-cao", "앞으로 길이 막힐 때마다, 나는 봉효를 찾게 되겠지.", "grim"),
      act("cao-cao", "stand"),
      walk("cao-cao", [5, 7]),
    ),
    // Stayed: a letter from 허.
    ...when(
      stayed,
      sound("page"),
      say("cao-cao", "허에서도 글이 왔다. 봉효의 글이다.", "smile"),
      say("guo-jia", "주공, 의원이 날마다 다녀가니 몸이 한결 가볍습니다.", "smile", {
        name: "곽가의 편지",
      }),
      say("guo-jia", "다만 술을 끊으라는 말만은 아직 따르지 못하고 있습니다.", "smile", {
        name: "곽가의 편지",
      }),
      emote("cao-cao", "note"),
      say("cao-cao", "봉효 녀석. 살아만 있으면 된다.", "smile"),
    ),
    tell("돌아오는 길에, 조조는 갈석산에 올랐다."),
    all(walk("cao-cao", [12, 3]), walk("xu-chu", [11, 4])),
    face("cao-cao", "right"),
    face("xu-chu", "cao-cao"),
    camera([14, 4], 1500),
    light("day"),
    all(sound("water"), sound("wind")),
    tell("동쪽으로 끝없는 바다가 펼쳐져 있었다."),
    say("cao-cao", "끝이 없구나. 해도 달도 저 물결 속에서 떠오르는 것 같다.", "calm"),
    tell("조조는 바다를 노래했다. 가을바람과 높은 물결, 그 속에서 떠오르는 해와 달을."),
    say("cao-cao", "천하를 담는 그릇도 저와 같아야 할 텐데.", "calm"),
    on(went, say("cao-cao", "봉효, 그대도 이 바다를 보았어야 했다.", "grim")),
    pause(800),
    face("cao-cao", "down"),
    camera([12, 3], 1300),
    say("cao-cao", "북쪽은 끝났다.", "calm"),
    say("cao-cao", "이제 남쪽이다. 장강 너머에도 이 바다만큼 넓은 땅이 있다.", "smile"),
    say("xu-chu", "주공, 바람이 찹니다.", "calm"),
    say("cao-cao", "가자. 남쪽으로.", "calm"),
    tell("4장 하북 평정 · 끝"),
  ],
  leaves: [{ officer: "guo-jia", if: went }],
};

// ---------------------------------------------------------------- camps (ADR 0064)

/** A camp ground like the usual one, with its own look; stations and spots stay put. */
function ground(
  id: string,
  change: Partial<StageDef>,
  addProps: readonly PropDef[] = [],
): CampGround {
  const base = CAMP_GROUND.stage;
  return {
    ...CAMP_GROUND,
    stage: { ...base, ...change, id, props: [...(change.props ?? base.props ?? []), ...addProps] },
  };
}

/** Camp talk: one or two lines for an officer, maybe only on some paths. */
function talk(officer: string, lines: readonly [string, Mood?][], cond?: Cond): CampTalk {
  return {
    officer,
    lines: lines.map(([text, mood]) => ({ who: officer, text, ...(mood ? { mood } : {}) })),
    ...(cond ? { if: cond } : {}),
  };
}

const rest = CAMP_GROUND.stage.rows.slice(1);

/** In the woods of 여남, after 유비 fled south. */
const GROUND_1 = ground(
  "runan-camp",
  {
    weather: "cloudy",
    landscape: { region: "huai", season: "autumn" },
    rows: ["fffffffggfffffff", ...rest],
  },
  [prop("banner", 2, 2), prop("banner", 13, 2)],
);

/** Under the south wall of 업, where we laid the siege, its gate open to us. */
const GROUND_2 = ground(
  "ye-camp",
  { landscape: { region: "plain", season: "autumn" }, rows: ["#######==#######", ...rest] },
  [
    prop("gate", 7, 0, { solid: false }),
    prop("gate", 8, 0, { solid: false }),
    prop("grain", 2, 2),
    prop("grain", 13, 2),
  ],
);

/** By the river at 남피, still frozen at the edges; braziers against the cold. */
const GROUND_3 = ground(
  "nanpi-camp",
  {
    light: "dusk",
    weather: "wind",
    landscape: { region: "plain", season: "winter" },
    rows: ["dddddddddddddddd", ...rest],
  },
  [prop("brazier", 2, 2), prop("brazier", 13, 2), prop("brazier", 2, 7)],
);

/** In the hills north of 호관, the mountains at our back. */
const GROUND_4 = ground(
  "huguan-camp",
  {
    weather: "wind",
    landscape: { region: "taihang", season: "winter" },
    rows: ["mmmmmmmhhmmmmmmm", ...rest],
  },
  [prop("brazier", 2, 2), prop("brazier", 13, 2)],
);

const SHOP_1 = [
  "hundred-dao",
  "great-ji",
  "iron-bow",
  "crane-fan",
  "bright-armour",
  "scale",
  "lacquer",
  "padded-robe",
  "fine-horse",
  "seal-cord",
];

/** 202, after 여남: 유비 gone to 형주, 원소 dead, his sons apart. */
export const CAMP_1: CampDef = {
  id: "hebei-camp-1",
  shop: SHOP_1,
  ground: GROUND_1,
  talk: [
    talk("xiahou-dun", [["유비는 또 달아났소. 저 사내는 지는 데에도 재주가 있구려.", "grim"]]),
    talk("cao-ren", [["유비가 형주에 있는 한, 남쪽 문은 늘 열려 있는 셈입니다.", "grim"]]),
    talk("guo-jia", [
      ["원소가 죽었으니 하북은 주인이 둘입니다. 둘이면 없느니만 못하지요.", "smile"],
    ]),
    talk("xun-you", [
      ["원상은 업에, 원담은 여양에 있습니다. 형제는 우리보다 서로를 먼저 볼 겁니다.", "calm"],
    ]),
    talk("xun-yu", [["창정에서 끝을 보셨으니, 이제 허의 창고를 채울 차례입니다.", "calm"]]),
    talk("zhang-he", [["원공께서 돌아가셨군요. 한때 섬긴 분이라 마음이 무겁습니다.", "grim"]]),
    talk("gao-lan", [
      ["원상 곁에는 심배가, 원담 곁에는 곽도가 있습니다. 둘은 원수지간이지요.", "calm"],
    ]),
    {
      officer: "xu-you",
      lines: [
        {
          who: "xu-you",
          mood: "smile",
          text: "원소가 내 말을 들었다면 지금도 살아 있었을 거요.",
        },
        { who: "xu-you", mood: "smile", text: "맹덕은 내 말을 들으니 이기는 게지. 하하!" },
        { who: "cao-cao", mood: "smile", text: "…그렇소, 자원." },
      ],
    },
    talk("xu-huang", [
      ["운장이 여남에 있었다지요. 고향 사람을 적으로 보는 일은 늘 쓸쓸합니다.", "grim"],
    ]),
    talk(
      "zhang-liao",
      [["운장은 끝내 유비 곁으로 돌아갔습니다. 그것이 그 사람이지요.", "grim"]],
      notSpared,
    ),
    talk("lu-bu", [["귀 큰 놈이 또 빠져나갔군. 다음엔 그 귀를 잡아끌고 오겠소.", "angry"]], spared),
    talk(
      "dian-wei",
      [["여남의 숲은 화살이 어디서 날아올지 몰라, 한시도 마음을 놓지 못했습니다."]],
      lived,
    ),
    talk(
      "chen-gong",
      [["원소가 죽어 하북의 선비들이 갈 곳을 잃었습니다. 그들을 거두십시오.", "calm"]],
      mercy,
    ),
    talk("cheng-yu", [["원담과 원상, 둘 다 살려 두면 둘 다 근심입니다.", "grim"]]),
    talk("liu-ye", [["벽력거를 손보고 있습니다. 하북의 성들은 높다지요.", "calm"]]),
    talk("xu-chu", [["원소가 죽었다니, 이제 누구랑 싸웁니까? 아들들이요? 하하!", "smile"]]),
  ],
};

/** 204, after 업: 심배 and 허유 dead, 원소's tomb, 원담 at our side for now. */
export const CAMP_2: CampDef = {
  id: "hebei-camp-2",
  shop: [...SHOP_1, "sunzi"],
  ground: GROUND_2,
  talk: [
    talk("xiahou-dun", [["업의 창고가 차고 넘치오. 이제야 군량 걱정을 덜었소.", "smile"]]),
    talk("xu-chu", [["주공의 명이었습니다. …저는 다른 말은 하지 않겠습니다.", "grim"]]),
    talk("guo-jia", [
      ["원담이 슬슬 이빨을 드러냅니다. 업을 우리에게 내준 것이 아깝겠지요.", "smile"],
    ]),
    talk("xin-pi", [["심배를 미워했습니다. 그래도 그는 끝까지 원씨의 신하였습니다.", "grim"]]),
    talk("zhang-he", [
      ["주공께서 원공의 무덤 앞에서 우셨지요. 사람들이 주공을 따르는 까닭입니다."],
    ]),
    talk("gao-lan", [
      ["업의 성문을 지날 때마다 이상합니다. 몇 해 전까지 저 안에서 녹을 먹었으니까요."],
    ]),
    talk("liu-ye", [
      ["벽력거가 성문을 부수던 소리, 들으셨습니까? 다음 성문도 맡겨 주십시오.", "smile"],
    ]),
    talk("xun-you", [
      ["업을 얻으셨으니 기주를 얻으신 겁니다. 올해는 하북의 세금을 덜어 주십시오.", "calm"],
    ]),
    talk("cheng-yu", [
      ["공을 내세우는 자는 언젠가 칼을 내세웁니다. 허유의 일은 옳았습니다.", "grim"],
    ]),
    talk("yue-jin", [["물에 잠긴 성은 처음 보았습니다. 성 안 백성들이 딱하더군요.", "grim"]]),
    talk("li-dian", [["업의 백성에게 곡식을 나누라는 명을 받았습니다. 굶주린 얼굴이 많습니다."]]),
    talk(
      "zhang-liao",
      [["원상은 산을 끼고 와서 스스로 길을 좁혔습니다. 주공의 말씀대로였습니다.", "calm"]],
      notSpared,
    ),
    talk("lu-bu", [["업의 성문쯤은 내 화극으로도 부술 수 있었을 텐데.", "smile"]], spared),
    talk("dian-wei", [["업의 성벽은 높았지만, 주공 곁에서는 높아 보이지 않았습니다."]], lived),
    talk(
      "chen-gong",
      [["진림을 살려 두신 건 잘하셨습니다. 붓 한 자루가 만 군사를 이길 때도 있습니다."]],
      mercy,
    ),
    talk("cao-hong", [["업의 비단이 산더미입니다! 형님, 이번엔 저도 한 필만…", "smile"]]),
  ],
};

/** 205, after 남피: 원담 dead, his brothers gone to the 오환; 조순 new. */
export const CAMP_3: CampDef = {
  id: "hebei-camp-3",
  shop: [...SHOP_1, "sunzi", "tiger-tally"],
  ground: GROUND_3,
  talk: [
    talk("cao-chun", [
      ["호표기는 주공의 이름에 부끄럽지 않게 달렸습니다. 다음 싸움도 맡겨 주십시오."],
    ]),
    talk("cao-ren", [["자화가 공을 세웠습니다. 형으로서 부끄럽지 않습니다.", "smile"]]),
    talk("xin-pi", [["남피 성 안에서… 형님의 소식은 끝내 듣지 못했습니다.", "grim"]]),
    {
      officer: "guo-jia",
      lines: [
        { who: "guo-jia", mood: "smile", text: "원씨 형제가 오환에게 갔으니, 이번엔 사막이군요." },
        { who: "guo-jia", mood: "hurt", text: "…콜록. 북쪽 바람은 술맛까지 얼려 버립니다." },
      ],
    },
    talk("xiahou-dun", [["얼음 꺼지는 소리가 아직도 귀에 남았소. 하마터면 빠질 뻔했지.", "grim"]]),
    talk("xun-you", [["왕수 같은 사람을 살려 두신 것은, 남피를 얻은 것보다 큰일입니다.", "calm"]]),
    talk("cheng-yu", [["고간이 조용합니다. 조용한 자가 더 무섭습니다.", "grim"]]),
    talk("zhang-he", [
      ["오환의 기병은 바람과 같습니다. 하북 사람들도 그들을 두려워했지요.", "grim"],
    ]),
    talk("yu-jin", [["얼음판에서 군사 셋을 잃었습니다. 물에 빠진 자를 건지려다가요.", "grim"]]),
    talk(
      "zhang-liao",
      [["답돈의 오환 기병은 이름이 높습니다. 한번 겨뤄 보고 싶습니다.", "calm"]],
      notSpared,
    ),
    talk(
      "lu-bu",
      [["오환의 기병이라… 병주에 있을 때 상대해 봤소. 말은 빠르나 꾀는 없지.", "smile"]],
      spared,
    ),
    talk(
      "dian-wei",
      [["추위에 옛 상처가 쑤십니다. 그래도 쌍극은 얼지 않았습니다.", "smile"]],
      lived,
    ),
    talk(
      "chen-gong",
      [["원담은 의리를 몰랐고, 왕수는 의리만 알았습니다. 주인 복이 없는 사람이지요.", "grim"]],
      mercy,
    ),
    talk("xu-chu", [["남피의 고기는 얼어서 씹히지가 않습니다! 그래도 맛은 좋습니다.", "smile"]]),
    talk("liu-ye", [["다음은 산길이라던데… 벽력거를 끌고 갈 일이 걱정입니다.", "grim"]]),
  ],
};

/** 206–207, after 호관: the 오환 ahead, the desert between, 곽가 coughing. */
export const CAMP_4: CampDef = {
  id: "hebei-camp-4",
  shop: [...SHOP_1, "sunzi", "tiger-tally", "jade"],
  ground: GROUND_4,
  talk: [
    {
      officer: "guo-jia",
      lines: [
        { who: "guo-jia", mood: "smile", text: "기침이요? 의원이 술을 끊으면 낫는다더군요." },
        { who: "guo-jia", mood: "smile", text: "그래서 낫지 않는 겁니다." },
        { who: "cao-cao", mood: "grim", text: "봉효, 농담할 일이 아니다." },
      ],
    },
    talk("xun-you", [["봉효의 얼굴빛이 좋지 않습니다. 주공께서 한번 살펴 주십시오.", "grim"]]),
    talk("xiahou-dun", [
      ["오환까지 간다니, 허는 누가 지키오? 맹덕, 너무 멀리 가는 거 아니오.", "grim"],
    ]),
    talk("cao-ren", [
      ["유비가 형주에 있습니다. 북쪽 깊이 들어가시는 동안 남쪽이 걱정입니다.", "grim"],
    ]),
    talk("cheng-yu", [["고간이 무너졌으니 병주도 우리 것입니다. 남은 것은 오환뿐입니다.", "calm"]]),
    talk("cao-chun", [["호표기는 사막도 달릴 수 있습니다. 말에게 먹일 물만 넉넉하다면요."]]),
    talk("zhang-he", [["유성까지는 길이 험합니다. 그 땅을 아는 길잡이가 있어야 합니다.", "calm"]]),
    talk(
      "zhang-liao",
      [["오환과 싸우려면 가벼워야 합니다. 무거운 군대는 사막에서 죽습니다.", "calm"]],
      notSpared,
    ),
    talk("lu-bu", [["사막이든 산이든, 적토마가 앞에 서면 길이 되오.", "smile"]], spared),
    talk("xin-pi", [["원씨 형제가 오환에 기대다니요. 원공께서 보셨다면 뭐라 하셨을지.", "grim"]]),
    talk("yue-jin", [["호관의 성문은 단단했습니다. 그래도 결국 열렸지요.", "smile"]]),
    talk("li-dian", [
      ["태항산의 추위는 다시 겪고 싶지 않습니다. 손가락이 아직도 저립니다.", "grim"],
    ]),
    talk("liu-ye", [["벽력거를 산길로 끌어올리느라 바퀴가 셋이나 부서졌습니다.", "grim"]]),
    talk(
      "dian-wei",
      [["사막으로 가신다면 저도 갑니다. 주공의 막사 앞은 어디든 제 자리입니다."]],
      lived,
    ),
    talk(
      "chen-gong",
      [["오환을 치시는 것은 옳습니다. 다만 봉효를 너무 무리하게 하지 마십시오.", "grim"]],
      mercy,
    ),
    talk("xu-chu", [["주공, 곽 군사가 술을 몰래 숨겨 두었습니다. 제가 거둬 올까요?", "smile"]]),
    talk("cao-hong", [["북쪽 끝까지 간다니, 돌아올 때쯤엔 수염이 다 얼겠습니다.", "smile"]]),
  ],
};
