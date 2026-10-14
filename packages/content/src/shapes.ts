/**
 * Range and area shapes, as offsets from a unit's or target's tile. Built from
 * Manhattan rings so every shape is symmetric and sorted the same way each time.
 */
import type { Offset } from "@tk/rules";

/** Every offset whose Manhattan distance is in [min, max], sorted by dy, then dx. */
export function ring(min: number, max: number): Offset[] {
  const out: Offset[] = [];
  for (let dy = -max; dy <= max; dy++)
    for (let dx = -max; dx <= max; dx++) {
      const d = Math.abs(dx) + Math.abs(dy);
      if (d >= min && d <= max) out.push({ dx, dy });
    }
  return out;
}

/** The four orthogonal neighbours: melee reach. */
export const MELEE = ring(1, 1);
/** Distance exactly 2: bows can't shoot an adjacent tile. */
export const BOW = ring(2, 2);
/** Distance 2–3: siege engines lob over the front line. */
export const SIEGE = ring(2, 3);
/** The target tile alone. */
export const SINGLE = ring(0, 0);
