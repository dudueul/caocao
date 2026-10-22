/**
 * Scenarios: a map, two or more sides and their officers, ready to become a
 * `BattleSetup` for `createBattle`. P0 has one sandbox scenario.
 */
import type {
  BattleSetup,
  Gambit,
  Goal,
  ModeId,
  TurnPolicyId,
  UnitSetup,
  WeatherKind,
} from "@tk/rules";
import { CLASSES } from "./classes.ts";
import { type MapDef, parseMap, WUCHAO } from "./maps.ts";
import { OFFICERS } from "./officers.ts";

export interface ScenarioUnit {
  readonly officer: string;
  /** Unit id; required for troop templates (several units share one), else the officer id. */
  readonly id?: string;
  /** Joins at the start of this round instead of starting on the field (ADR 0047). */
  readonly arrives?: number;
  readonly classId: string;
  readonly level: number;
  readonly side: number;
  readonly x: number;
  readonly y: number;
  /** Standing orders for the AI when it controls this unit, as gambits (ADR 0030). */
  readonly orders?: readonly Gambit[];
}

/** Reusable AI orders for scenario units. */
export const ORDERS = {
  /** Stay put; strike whatever comes into reach. */
  hold: [
    { when: { kind: "enemyInReach" }, behavior: { kind: "attackBest" } },
    { when: { kind: "always" }, behavior: { kind: "hold" } },
  ],
  /** Never leave this tile; strike whatever comes into range of it (ADR 0047). */
  guard: [{ when: { kind: "always" }, behavior: { kind: "guard" } }],
  /** Heal an ally below 60% HP; otherwise hold. */
  healThenHold: [
    { when: { kind: "allyHpBelow", permille: 600 }, behavior: { kind: "healAlly" } },
    { when: { kind: "enemyInReach" }, behavior: { kind: "attackBest" } },
    { when: { kind: "always" }, behavior: { kind: "hold" } },
  ],
} as const satisfies Record<string, readonly Gambit[]>;

export interface ScenarioDef {
  readonly id: string;
  readonly name: string;
  readonly year: number;
  readonly summary: string;
  /** Side names, indexed by side number; side 0 is the player in PvE. */
  readonly sides: readonly string[];
  readonly map: MapDef;
  readonly weather: WeatherKind;
  readonly policy: TurnPolicyId;
  readonly roundLimit: number;
  readonly units: readonly ScenarioUnit[];
  /** Ways to win besides routing every enemy (ADR 0047). */
  readonly goals?: readonly Goal[];
}

export const WUCHAO_RAID: ScenarioDef = {
  id: "wuchao-raid",
  name: "오소 습격",
  year: 200,
  summary:
    "Sandbox. Cao Cao's raiders cross the river to burn Yuan Shao's grain depot at Wuchao; the defenders hold the camp. The roster draws freely on the Guandu campaign.",
  sides: ["조조군", "원소군"],
  map: WUCHAO,
  weather: "sunny",
  policy: "sidePhases",
  roundLimit: 30,
  units: [
    { officer: "cao-cao", classId: "cavalry", level: 10, side: 0, x: 3, y: 5 },
    { officer: "xu-chu", classId: "martialArtist", level: 10, side: 0, x: 3, y: 4 },
    { officer: "yue-jin", classId: "infantry", level: 10, side: 0, x: 3, y: 6 },
    { officer: "xiahou-yuan", classId: "archer", level: 10, side: 0, x: 2, y: 3 },
    { officer: "xun-you", classId: "strategist", level: 10, side: 0, x: 1, y: 6 },
    { officer: "liu-ye", classId: "catapult", level: 10, side: 0, x: 2, y: 7 },
    // The depot's defenders hold the camp until the raiders come into reach.
    {
      officer: "chunyu-qiong",
      classId: "infantry",
      level: 10,
      side: 1,
      x: 14,
      y: 5,
      orders: ORDERS.hold,
    },
    {
      officer: "gao-lan",
      classId: "infantry",
      level: 10,
      side: 1,
      x: 13,
      y: 4,
      orders: ORDERS.hold,
    },
    {
      officer: "zhang-he",
      classId: "cavalry",
      level: 10,
      side: 1,
      x: 13,
      y: 6,
      orders: ORDERS.hold,
    },
    { officer: "qu-yi", classId: "archer", level: 10, side: 1, x: 14, y: 3, orders: ORDERS.hold },
    {
      officer: "ju-shou",
      classId: "strategist",
      level: 10,
      side: 1,
      x: 15,
      y: 6,
      orders: ORDERS.healThenHold,
    },
    {
      officer: "wen-chou",
      classId: "martialArtist",
      level: 10,
      side: 1,
      x: 14,
      y: 7,
      orders: ORDERS.hold,
    },
  ],
};

export const SCENARIOS: Readonly<Record<string, ScenarioDef>> = {
  [WUCHAO_RAID.id]: WUCHAO_RAID,
};

export interface SetupOptions {
  seed: string;
  policy?: TurnPolicyId;
  weather?: WeatherKind;
  mode?: ModeId;
}

/** The `BattleSetup` for a scenario; unit ids are officer ids. */
export function scenarioSetup(s: ScenarioDef, o: SetupOptions): BattleSetup {
  return {
    mode: o.mode ?? "sandbox",
    map: parseMap(s.map),
    weather: o.weather ?? s.weather,
    policy: o.policy ?? s.policy,
    roundLimit: s.roundLimit,
    seed: o.seed,
    units: s.units.map((u) => unitSetup(u, `scenario ${s.id}`)),
    ...(s.goals ? { goals: structuredClone(s.goals) as Goal[] } : {}),
  };
}

/** One scenario unit as a `UnitSetup`; troop templates are named after their army and class. */
export function unitSetup(u: ScenarioUnit, where: string): UnitSetup {
  const officer = OFFICERS[u.officer];
  if (!officer) throw new Error(`${where}: unknown officer ${u.officer}`);
  const cls = CLASSES[u.classId];
  if (officer.generic && !u.id) throw new Error(`${where}: troop ${u.officer} needs an id`);
  return {
    id: u.id ?? officer.id,
    side: u.side,
    name: officer.generic ? `${officer.name} ${cls?.name ?? u.classId}` : officer.name,
    classId: u.classId,
    level: u.level,
    stats: officer.stats,
    x: u.x,
    y: u.y,
    ...(u.arrives ? { arrives: u.arrives } : {}),
    // Named officers can fight a 일기토; troops can't (ADR 0071).
    ...(officer.generic ? {} : { named: true }),
  };
}

/** AI orders by unit id, for `chooseCommand(state, rules, orders)`. */
export function scenarioOrders(s: {
  readonly units: readonly ScenarioUnit[];
}): Record<string, readonly Gambit[]> {
  const out: Record<string, readonly Gambit[]> = {};
  for (const u of s.units) if (u.orders) out[u.id ?? u.officer] = u.orders;
  return out;
}
