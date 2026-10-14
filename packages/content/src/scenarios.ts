/**
 * Scenarios: a map, two or more sides and their officers, ready to become a
 * `BattleSetup` for `createBattle`. P0 has one sandbox scenario.
 */
import type { BattleSetup, ModeId, TurnPolicyId, WeatherKind } from "@tk/rules";
import { type MapDef, parseMap, WUCHAO } from "./maps.ts";
import { OFFICERS } from "./officers.ts";

export interface ScenarioUnit {
  readonly officer: string;
  readonly classId: string;
  readonly level: number;
  readonly side: number;
  readonly x: number;
  readonly y: number;
}

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
}

export const WUCHAO_RAID: ScenarioDef = {
  id: "wuchao-raid",
  name: "오소 습격",
  year: 200,
  summary:
    "Sandbox. Cao Cao's raiders cross the river to burn Yuan Shao's grain depot at Wuchao. The roster draws freely on the Guandu campaign.",
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
    { officer: "chunyu-qiong", classId: "infantry", level: 10, side: 1, x: 14, y: 5 },
    { officer: "gao-lan", classId: "infantry", level: 10, side: 1, x: 13, y: 4 },
    { officer: "zhang-he", classId: "cavalry", level: 10, side: 1, x: 13, y: 6 },
    { officer: "qu-yi", classId: "archer", level: 10, side: 1, x: 14, y: 3 },
    { officer: "ju-shou", classId: "strategist", level: 10, side: 1, x: 15, y: 6 },
    { officer: "wen-chou", classId: "martialArtist", level: 10, side: 1, x: 14, y: 7 },
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
    units: s.units.map((u) => {
      const officer = OFFICERS[u.officer];
      if (!officer) throw new Error(`scenario ${s.id}: unknown officer ${u.officer}`);
      return {
        id: officer.id,
        side: u.side,
        name: officer.name,
        classId: u.classId,
        level: u.level,
        stats: officer.stats,
        x: u.x,
        y: u.y,
      };
    }),
  };
}
