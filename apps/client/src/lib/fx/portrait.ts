/**
 * Our own officer portraits (ADR 0053, ADR 0070): painted head-and-shoulders
 * busts, drawn in code on a 2D canvas in the style of the rigged figures (ADR
 * 0045). Each named officer is painted from his row in the trait table: his
 * own headgear, build, turn, beard, accent and props, from the histories first
 * and the novel second. Dress follows the late Eastern Han (ADR 0046,
 * docs/remake/CHARACTER_LOOK.md). The painter lives in `./portrait/`.
 */
export { PORTRAIT_OFFICERS } from "./portrait/officers.ts";
export {
  classFamily,
  portraitCanvas,
  portraitFaction,
  portraitFigure,
  portraitUrl,
} from "./portrait/paint.ts";
export {
  FAMILIES,
  type Family,
  MOODS,
  type Mood,
  PORTRAIT_H,
  PORTRAIT_W,
  type PortraitSpec,
} from "./portrait/types.ts";
