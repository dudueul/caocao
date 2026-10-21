/** The P0 ruleset: everything `@tk/rules` needs to run a battle. */
import { DEFAULT_TUNING, hashState, type Ruleset, triangleRelations } from "@tk/rules";
import { CLASSES } from "./classes.ts";
import { CONDITIONS } from "./conditions.ts";
import { STRATAGEMS } from "./stratagems.ts";
import { AFFINITY, TERRAINS } from "./terrain.ts";

/**
 * Against gates (ADR 0069): stone-throwers smash them, infantry and martial
 * artists hack at them, horsemen and archers barely scratch them.
 */
function withSiege(
  rel: Record<string, Record<string, number>>,
): Record<string, Record<string, number>> {
  const vs: Record<string, number> = {
    catapult: 2500,
    infantry: 1000,
    martialArtist: 1000,
    cavalry: 600,
    archer: 400,
    strategist: 600,
  };
  const out: Record<string, Record<string, number>> = {};
  for (const [a, row] of Object.entries(rel)) out[a] = { ...row };
  for (const [a, m] of Object.entries(vs)) out[a] = { ...(out[a] ?? {}), structure: m };
  return out;
}

export const P0_RULES: Ruleset = {
  classes: CLASSES,
  terrains: TERRAINS,
  affinity: AFFINITY,
  stratagems: STRATAGEMS,
  conditions: CONDITIONS,
  // Counter triangle (ADR 0021) as the default relation matrix (ADR 0029):
  // catapults fight as the archer group; martial artists and strategists sit outside.
  relations: withSiege(
    triangleRelations(
      { infantry: "infantry", cavalry: "cavalry", archer: "archer", catapult: "archer" },
      200,
    ),
  ),
  tuning: DEFAULT_TUNING,
};

/**
 * Content hash of the ruleset. Replays and the referee record it, so a log is
 * only re-simulated against the exact numbers it was played with (ADR 0004).
 */
export const P0_RULES_HASH: string = hashState(P0_RULES);
