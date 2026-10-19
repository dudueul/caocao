/**
 * Equipment, the shop and what gear adds in battle (ADR 0050). Every function
 * returns a new state or an error string; the state passed in is not changed.
 */
import { fits, ITEMS, type ItemDef, P0_RULES } from "@tk/content";
import type { Ruleset, Stats } from "@tk/rules";
import { STAT_KEYS } from "./progression.ts";
import { PROGRESSION } from "./tuning.ts";
import type { CampaignState, Gear, Officer } from "./types.ts";

export type Result = { ok: true; state: CampaignState } | { ok: false; reason: string };

const slotOf = (item: ItemDef): keyof Gear => item.slot;

function officerIn(state: CampaignState, id: string): Officer | undefined {
  return state.roster.find((o) => o.id === id);
}

function take(inv: Record<string, number>, id: string): void {
  const n = (inv[id] ?? 0) - 1;
  if (n > 0) inv[id] = n;
  else delete inv[id];
}

function put(inv: Record<string, number>, id: string): void {
  inv[id] = (inv[id] ?? 0) + 1;
}

/** Equips a spare item; whatever was in that slot goes back to the inventory. */
export function equip(
  state: CampaignState,
  officerId: string,
  itemId: string,
  rules: Ruleset = P0_RULES,
): Result {
  const item = ITEMS[itemId];
  if (!item) return { ok: false, reason: "unknown item" };
  if ((state.inventory[itemId] ?? 0) < 1) return { ok: false, reason: "not in the inventory" };
  const next = structuredClone(state);
  const o = officerIn(next, officerId);
  if (!o) return { ok: false, reason: "unknown officer" };
  if (!fits(rules, o.classId, itemId)) return { ok: false, reason: "this officer can't use it" };
  const slot = slotOf(item);
  const old = o.gear[slot];
  take(next.inventory, itemId);
  if (old) put(next.inventory, old);
  o.gear[slot] = itemId;
  return { ok: true, state: next };
}

export function unequip(state: CampaignState, officerId: string, slot: keyof Gear): Result {
  const next = structuredClone(state);
  const o = officerIn(next, officerId);
  if (!o) return { ok: false, reason: "unknown officer" };
  const old = o.gear[slot];
  if (!old) return { ok: false, reason: "nothing equipped" };
  o.gear[slot] = null;
  put(next.inventory, old);
  return { ok: true, state: next };
}

export function buy(state: CampaignState, itemId: string, shop: readonly string[]): Result {
  const item = ITEMS[itemId];
  if (!item || !shop.includes(itemId)) return { ok: false, reason: "not for sale here" };
  if (state.gold < item.price) return { ok: false, reason: "not enough gold" };
  const next = structuredClone(state);
  next.gold -= item.price;
  put(next.inventory, itemId);
  return { ok: true, state: next };
}

export function sellPrice(itemId: string): number {
  const item = ITEMS[itemId];
  return item ? Math.floor((item.price * PROGRESSION.sellBack) / 1000) : 0;
}

export function sell(state: CampaignState, itemId: string): Result {
  if ((state.inventory[itemId] ?? 0) < 1) return { ok: false, reason: "not in the inventory" };
  const next = structuredClone(state);
  take(next.inventory, itemId);
  next.gold += sellPrice(itemId);
  return { ok: true, state: next };
}

/** What an officer brings to battle: stats with gear (capped), and the ATK/DEF bonuses. */
export function battleProfile(o: Officer): { stats: Stats; atkBonus: number; defBonus: number } {
  const stats: { -readonly [K in keyof Stats]: number } = { ...o.stats };
  let atkBonus = 0;
  let defBonus = 0;
  for (const id of [o.gear.weapon, o.gear.armour, o.gear.accessory]) {
    const item = id ? ITEMS[id] : undefined;
    if (!item) continue;
    atkBonus += item.atk ?? 0;
    defBonus += item.def ?? 0;
    for (const k of STAT_KEYS)
      stats[k] = Math.min(PROGRESSION.statCap, stats[k] + (item.stats?.[k] ?? 0));
  }
  return { stats, atkBonus, defBonus };
}

/** Puts gear that no longer fits (after a class change) back into the inventory. */
export function dropMisfits(
  state: CampaignState,
  officerId: string,
  rules: Ruleset = P0_RULES,
): CampaignState {
  const next = structuredClone(state);
  const o = officerIn(next, officerId);
  if (!o) return next;
  for (const slot of ["weapon", "armour", "accessory"] as const) {
    const id = o.gear[slot];
    if (id && !fits(rules, o.classId, id)) {
      o.gear[slot] = null;
      put(next.inventory, id);
    }
  }
  return next;
}
