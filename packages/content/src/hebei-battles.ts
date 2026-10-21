/**
 * Chapter 4 (하북 평정) battles (ADR 0067): maps, armies, goals, talk and
 * rewards. 업 has gates to break (ADR 0069), a trench that floods and 원담
 * fighting beside us as an ally (ADR 0066); 남피's river ice gives way; at
 * 백랑산 the 오환 are surprised only if 곽가 came (ADR 0068). Our own maps
 * and numbers (ADR 0002).
 *
 * PLACEHOLDER BATTLES: ids, goals and the special rules are in place; maps,
 * armies and balance are being written out in full.
 */
import type { Cell, Goal } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { Cond, StoryBattleDef, StoryUnit } from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
const winBy = (...units: string[]): Goal => ({ side: 0, when: { kind: "defeat", units } });
const went: Cond = { flag: "guojia", is: "went" };
const stayed: Cond = { flag: "guojia", is: "stayed" };

function foe(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> & { if?: Cond } = {},
): StoryUnit {
  return { officer, classId, level, side: 1, x, y, ...extra };
}

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

const PLAIN: MapDef = {
  id: "hebei-plain",
  name: "들판",
  rows: [
    "..............",
    "..............",
    "..............",
    "..........#...",
    "..............",
    "..........#...",
    "..............",
    "..............",
  ],
};

function stub(id: string, name: string, extra: Partial<StoryBattleDef> = {}): StoryBattleDef {
  return {
    id,
    name,
    place: name,
    summary: name,
    map: { ...PLAIN, id: `hebei-${id}` },
    weather: "cloudy",
    roundLimit: 20,
    sides: ["조조군", "원소군"],
    slots: cells([0, 3], [0, 4], [1, 3], [1, 4], [0, 2], [1, 2], [0, 5], [1, 5]),
    max: 7,
    required: ["cao-cao"],
    guests: [],
    enemies: [foe("yuan", "infantry", 18, 12, 2, { id: "yuan-infantry-1" })],
    goals: [loseIf("cao-cao")],
    goalText: { win: "승리", lose: "조조 퇴각" },
    rewards: { gold: 1500, items: [] },
    talk: [],
    ...extra,
  };
}

export const CANGTING = stub("cangting", "창정의 결전", {
  enemies: [
    foe("yuan-shao", "shockCavalry", 22, 12, 4),
    foe("yuan", "infantry", 18, 12, 2, { id: "yuan-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-shao")],
});

export const RUNAN = stub("runan", "여남의 유비", {
  sides: ["조조군", "유비군"],
  enemies: [
    foe("liu-bei", "cavalry", 22, 12, 4),
    foe("liu", "infantry", 18, 12, 2, { id: "liu-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("liu-bei")],
});

export const LIYANG = stub("liyang", "여양의 형제", {
  enemies: [foe("yuan-tan", "cavalry", 21, 12, 4), foe("yuan-shang", "cavalry", 21, 12, 6)],
  goals: [loseIf("cao-cao"), winBy("yuan-tan", "yuan-shang")],
});

export const YE = stub("ye", "업성 공략", {
  sides: ["조조군", "원상군", "원담군"],
  allies: [ally("yuan-tan", "cavalry", 21, 3, 7)],
  enemies: [
    foe("shen-pei", "tactician", 23, 13, 4, { orders: ORDERS.guard }),
    foe("gate", "gate", 20, 10, 3, { id: "gate-west" }),
    foe("gate", "gate", 20, 10, 5, { id: "gate-south" }),
  ],
  goals: [loseIf("cao-cao"), winBy("shen-pei")],
  shifts: [
    {
      id: "trench",
      when: { kind: "round", round: 5 },
      tiles: cells([8, 0], [8, 1], [8, 2]).map((c) => ({ ...c, terrain: "water" as const })),
      damage: 100,
    },
  ],
});

export const NANPI = stub("nanpi", "남피의 얼음", {
  enemies: [
    foe("yuan-tan", "cavalry", 23, 12, 4),
    foe("yuan", "infantry", 20, 12, 2, { id: "yuan-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("yuan-tan")],
});

export const HUGUAN = stub("huguan", "호관의 고간", {
  sides: ["조조군", "고간군"],
  enemies: [
    foe("gao-gan", "cavalry", 24, 13, 4, { orders: ORDERS.guard }),
    foe("gate", "gate", 22, 10, 4, { id: "gate-pass" }),
  ],
  goals: [loseIf("cao-cao"), winBy("gao-gan")],
});

export const BAILANG = stub("bailang", "백랑산의 오환", {
  sides: ["조조군", "오환"],
  absent: [{ officer: "guo-jia", note: "곽가는 허에서 병을 다스리고 있습니다", if: stayed }],
  enemies: [
    foe("ta-dun", "shockCavalry", 25, 12, 4),
    foe("wuhuan", "cavalry", 22, 12, 2, { id: "wuhuan-cavalry-1" }),
    foe("wuhuan", "cavalry", 22, 12, 6, { id: "wuhuan-cavalry-2", if: stayed }),
    foe("wuhuan", "cavalry", 22, 13, 6, { id: "wuhuan-cavalry-3", if: went, arrives: 4 }),
  ],
  goals: [loseIf("cao-cao"), winBy("ta-dun")],
});
