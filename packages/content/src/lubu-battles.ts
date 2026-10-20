/**
 * Chapter 2 (여포) battles (ADR 0059): maps, armies, goals, talk and rewards.
 * 복양 and 완성 are escapes (조조 must reach the edge), 완성 decides 전위's
 * fate, and 하비 floods when an officer opens the sluice (ADR 0062).
 * Our own maps and numbers (ADR 0002).
 *
 * PLACEHOLDER BATTLES: ids, goals and the special rules are in place; maps,
 * armies and balance are being written out in full.
 */
import type { Cell, Goal } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import { ORDERS, type ScenarioUnit } from "./scenarios.ts";
import type { Cond, StoryBattleDef } from "./story.ts";

const cells = (...xy: [number, number][]): Cell[] => xy.map(([x, y]) => ({ x, y }));
const loseIf = (unit: string): Goal => ({ side: 1, when: { kind: "defeat", units: [unit] } });
const winBy = (unit: string): Goal => ({ side: 0, when: { kind: "defeat", units: [unit] } });
const escapeTo = (...xy: [number, number][]): Goal => ({
  side: 0,
  when: { kind: "reach", side: 0, cells: cells(...xy), units: ["cao-cao"] },
});
const revenge: Cond = { flag: "xuzhou", is: "revenge" };

function foe(
  officer: string,
  classId: string,
  level: number,
  x: number,
  y: number,
  extra: Partial<ScenarioUnit> & { if?: Cond } = {},
): ScenarioUnit & { if?: Cond } {
  return { officer, classId, level, side: 1, x, y, ...extra };
}

const PLAIN: MapDef = {
  id: "lubu-plain",
  name: "들판",
  rows: [
    "............",
    "............",
    "............",
    "............",
    "............",
    "............",
    "............",
    "............",
  ],
};

function stub(id: string, name: string, extra: Partial<StoryBattleDef> = {}): StoryBattleDef {
  return {
    id,
    name,
    place: name,
    summary: name,
    map: { ...PLAIN, id: `lubu-${id}` },
    weather: "cloudy",
    roundLimit: 20,
    sides: ["조조군", "적군"],
    slots: cells([0, 3], [0, 4], [1, 3], [1, 4], [0, 2], [1, 2], [0, 5], [1, 5]),
    max: 6,
    required: ["cao-cao"],
    guests: [],
    enemies: [
      foe("lu", "infantry", 8, 10, 3, { id: "lu-infantry-1" }),
      foe("lu", "infantry", 8, 10, 5, { id: "lu-infantry-2" }),
    ],
    goals: [loseIf("cao-cao")],
    goalText: { win: "승리", lose: "조조 퇴각" },
    rewards: { gold: 500, items: [] },
    talk: [],
    ...extra,
  };
}

export const PENGCHENG = stub("pengcheng", "팽성의 원한", {
  enemies: [
    foe("cao-bao", "cavalry", 11, 10, 4),
    foe("xu", "infantry", 9, 10, 2, { id: "xu-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("cao-bao")],
});

export const PUYANG_FIRE = stub("puyang-fire", "복양의 불길", {
  light: "night",
  required: ["cao-cao", "dian-wei"],
  enemies: [
    foe("lu-bu", "cavalry", 16, 9, 1, { orders: ORDERS.hold }),
    foe("chen-gong", "strategist", 13, 11, 1, { if: revenge }),
    foe("lu", "infantry", 9, 10, 6, { id: "lu-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), escapeTo([11, 7])],
  burning: [{ x: 5, y: 3, rounds: 4 }],
});

export const DINGTAO = stub("dingtao", "정도의 둑", {
  enemies: [
    foe("lu-bu", "cavalry", 16, 10, 4),
    foe("lu", "infantry", 9, 10, 2, { id: "lu-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("lu-bu")],
});

export const WANCHENG = stub("wancheng", "완성의 밤", {
  light: "night",
  required: ["cao-cao", "dian-wei"],
  enemies: [
    foe("zhang-xiu", "cavalry", 15, 6, 0),
    foe("zhang", "infantry", 10, 6, 7, { id: "zhang-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), escapeTo([11, 3], [11, 4])],
  fates: [{ flag: "dianwei", unit: "dian-wei", standing: "lived", fallen: "fell" }],
});

export const QI = stub("qi", "기현의 싸움", {
  weather: "rain",
  enemies: [
    foe("qiao-rui", "infantry", 15, 10, 4),
    foe("zhong", "infantry", 10, 10, 2, { id: "zhong-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("qiao-rui")],
});

export const ANZHONG = stub("anzhong", "안중의 땅굴", {
  enemies: [
    foe("zhang-xiu", "cavalry", 16, 10, 4),
    foe("jing", "infantry", 10, 10, 2, { id: "jing-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("zhang-xiu")],
});

export const XIAPI = stub("xiapi", "하비 수공", {
  enemies: [
    foe("lu-bu", "shockCavalry", 18, 10, 4, { orders: ORDERS.guard }),
    foe("lu", "infantry", 10, 10, 2, { id: "lu-infantry-1" }),
  ],
  goals: [loseIf("cao-cao"), winBy("lu-bu")],
  shifts: [
    {
      id: "flood",
      when: { kind: "reach", side: 0, cells: cells([6, 0]) },
      tiles: cells([8, 3], [8, 4], [8, 5]).map((c) => ({ ...c, terrain: "water" as const })),
      damage: 150,
    },
  ],
});
