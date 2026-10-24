/**
 * Our figures (ADR 0073): sprite sheets painted in code in the portraits'
 * style (ADR 0053, ADR 0070), from the same trait table, moving to the
 * classic's frame grammar. Used by the battlefield, the staged scenes and the
 * walkable camp.
 *
 * - `look.ts`: what a unit looks like (officer or army kit, class, side dye);
 * - `poses.ts`: the key poses, frame counts and rates;
 * - `rig.ts`: solving a pose into joints;
 * - `person.ts`, `head.ts`, `headgear.ts`, `gear.ts`, `mount.ts`, `engine.ts`: the painting;
 * - `paint.ts`: one frame; `atlas.ts`: sheets and their cache; `figure.ts`: the figure.
 */
import { cacheReport, resetSheetStats, sheetStats } from "./atlas.ts";

export { cacheReport, groupOf, sheetFor, sheetStats, U } from "./atlas.ts";

/** For `?debug`: paint time and sheet memory of the figures. */
export const spriteDebug = { stats: sheetStats, cache: cacheReport, reset: resetSheetStats };
export { FEET_Y, type FigurePose, lookFor, SpriteFigure } from "./figure.ts";
export { isFigure, type SpriteLook, spriteLook } from "./look.ts";
export { paintFrame } from "./paint.ts";
export { BATTLE_POSES, type Facing, FRAMES, type PoseName, SCENE_POSES } from "./poses.ts";
