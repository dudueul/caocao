/**
 * The four P0 stratagems (ADR 0024, ADR 0030). Damage is
 * power + 지력 − ⌊target 지력 / 2⌋, scaled by the target tile and the weather;
 * heals are power + ⌊지력 / 2⌋. Starting tuning, our own numbers.
 */
import type { StratagemDef } from "@tk/rules";
import { ring, SINGLE } from "./shapes.ts";

export const STRATAGEMS: Readonly<Record<string, StratagemDef>> = {
  fire: {
    id: "fire",
    name: "화계",
    school: "fire",
    mp: 8,
    range: ring(1, 3),
    area: SINGLE,
    effect: "damage",
    targets: "enemies",
    power: 20,
    terrain: { grass: 1200, forest: 1200, camp: 1100, ford: 600, bridge: 800 },
    weather: { sunny: 1100, rain: 500, wind: 1300 },
    windfall: "power",
    mishap: "backfire",
    ignites: true,
  },
  gale: {
    id: "gale",
    name: "풍계",
    school: "wind",
    mp: 6,
    range: ring(1, 2),
    area: SINGLE,
    effect: "damage",
    targets: "enemies",
    power: 5,
    terrain: { plain: 1100, grass: 1100, forest: 800 },
    weather: { wind: 1300, rain: 900 },
    windfall: "power",
    mishap: "fizzle",
    push: 2,
  },
  confuse: {
    id: "confuse",
    name: "혼란",
    school: "disruption",
    mp: 8,
    range: ring(1, 3),
    area: SINGLE,
    effect: "status",
    targets: "enemies",
    power: 0,
    status: "confusion",
    statusRounds: 1,
    windfall: "refund",
    mishap: "fizzle",
  },
  heal: {
    id: "heal",
    name: "치료",
    school: "support",
    mp: 6,
    range: ring(0, 2),
    area: SINGLE,
    effect: "heal",
    targets: "allies",
    power: 30,
    windfall: "power",
    mishap: "fizzle",
  },
};
