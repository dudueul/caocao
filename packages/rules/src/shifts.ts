/**
 * Map shifts (ADR 0062): terrain that changes during a battle, set off by the
 * start of a round or by a side reaching a tile. The flood at Xiapi turns the
 * low ground to water once an officer opens the sluice; a traitor opens a gate.
 * Units the change leaves on ground they can't stand on are swept to the
 * nearest tile they can, and lose a share of their HP (never the last point).
 */
import { affinityAt, inBounds, isAlive, unitAt } from "./board.ts";
import { landingCell } from "./goals.ts";
import { permille } from "./math.ts";
import type { BattleEvent, BattleState, Ruleset, Shift, Unit } from "./types.ts";

/** The unit that sets off a "reach" shift right now, or null; "round" shifts are due from their round. */
function trigger(state: BattleState, shift: Shift): { due: boolean; by: Unit | null } {
  const w = shift.when;
  if (w.kind === "round") return { due: state.round >= w.round, by: null };
  for (const c of w.cells) {
    const u = unitAt(state, c.x, c.y);
    if (u && u.side === w.side && (!w.units || w.units.includes(u.id))) return { due: true, by: u };
  }
  return { due: false, by: null };
}

function fire(
  state: BattleState,
  rules: Ruleset,
  shift: Shift,
  by: Unit | null,
  events: BattleEvent[],
): void {
  const w = state.map.width;
  for (const t of shift.tiles) state.map.tiles[t.y * w + t.x] = t.terrain;
  // Water puts out fire.
  state.burning = state.burning.filter(
    (b) => !shift.tiles.some((t) => t.x === b.x && t.y === b.y && t.terrain === "water"),
  );
  events.push({
    kind: "shifted",
    shift: shift.id,
    tiles: shift.tiles.map((t) => ({ ...t })),
    by: by?.id ?? null,
  });
  // Units on changed tiles they can't stand on, in id order (the units list is sorted).
  const changed = new Set(shift.tiles.map((t) => t.y * w + t.x));
  for (const u of state.units) {
    if (!isAlive(u) || !changed.has(u.y * w + u.x)) continue;
    if (affinityAt(rules, state, u, u.x, u.y).move !== null) continue;
    const to = landingCell(state, rules, u);
    const from = { x: u.x, y: u.y };
    const damage = Math.min(u.hp - 1, permille(u.maxHp, shift.damage));
    u.hp -= damage;
    if (to) {
      u.x = to.x;
      u.y = to.y;
    }
    events.push({ kind: "swept", unit: u.id, from, to: to ?? from, damage, hpAfter: u.hp });
  }
}

/**
 * Fires every shift that is due, in setup order. A sweep can carry a unit onto
 * another shift's tile, so the list is checked again until nothing fires.
 */
export function fireShifts(state: BattleState, rules: Ruleset, events: BattleEvent[]): void {
  for (let fired = true; fired && state.shifts.length > 0; ) {
    fired = false;
    for (const shift of state.shifts) {
      const t = trigger(state, shift);
      if (!t.due) continue;
      state.shifts = state.shifts.filter((s) => s.id !== shift.id);
      fire(state, rules, shift, t.by, events);
      fired = true;
      break;
    }
  }
}

/** Throws if a shift is malformed: an id used twice, a tile off the map, an unknown terrain, a bad share. */
export function checkShifts(state: BattleState, rules: Ruleset, shifts: readonly Shift[]): void {
  const ids = new Set<string>();
  for (const s of shifts) {
    if (ids.has(s.id)) throw new Error(`two shifts named ${s.id}`);
    ids.add(s.id);
    if (!Number.isSafeInteger(s.damage) || s.damage < 0 || s.damage > 1000)
      throw new Error(`shift ${s.id}: damage must be 0–1000‰`);
    if (s.tiles.length === 0) throw new Error(`shift ${s.id} changes no tiles`);
    for (const t of s.tiles) {
      if (!inBounds(state, t.x, t.y)) throw new Error(`shift ${s.id}: tile off the map`);
      if (!rules.terrains[t.terrain])
        throw new Error(`shift ${s.id}: unknown terrain ${t.terrain}`);
    }
    if (s.when.kind === "reach") {
      if (s.when.cells.length === 0) throw new Error(`shift ${s.id}: no trigger tiles`);
      for (const c of s.when.cells)
        if (!inBounds(state, c.x, c.y)) throw new Error(`shift ${s.id}: trigger off the map`);
    } else if (!Number.isSafeInteger(s.when.round) || s.when.round < 2) {
      throw new Error(`shift ${s.id}: a timed shift needs round 2 or later`);
    }
  }
}
