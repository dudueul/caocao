/** Everything one portrait is painted from, resolved once per call. */
import type { Head } from "./head.ts";
import type { Pal } from "./palette.ts";
import type { Family, Hat, Mood, Traits } from "./types.ts";

export interface Look {
  t: Traits;
  fam: Family;
  tier2: boolean;
  /** A named officer (his own row), not a soldier from an army's kit. */
  named: boolean;
  P: Pal;
  side: number;
  mood: Mood;
  hat: Hat;
  seed: number;
  skin: readonly [string, string, string];
  hair: readonly [string, string, string];
  H: Head;
  /** The body turned to the viewer's left (drawn mirrored, the collar kept right over left). */
  flip: boolean;
  /** Body turn, 0 front to 1 three-quarter. */
  bodyTurn: number;
  /** The body turned away: the back of the robe toward the viewer. */
  away: boolean;
}

export function has(L: Look | Traits, m: Traits["marks"][number]): boolean {
  const t = "t" in L ? L.t : L;
  return t.marks.includes(m);
}
