/** Story flags (ADR 0060): tests on what the player chose and what happened in battle. */
import type { Cond } from "./story.ts";

/** Does `cond` hold for these flags? No condition always holds. */
export function holds(cond: Cond | undefined, flags: Readonly<Record<string, string>>): boolean {
  if (!cond) return true;
  const v = flags[cond.flag];
  return "is" in cond ? v === cond.is : v !== cond.not;
}
