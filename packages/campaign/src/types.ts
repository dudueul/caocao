/**
 * Campaign state (ADR 0049): plain, serializable data with integers only, so a
 * save is just this object as JSON and a future `PlayerDO` can re-check it.
 */
import type { Stats } from "@tk/rules";

export interface Gear {
  weapon: string | null;
  armour: string | null;
  accessory: string | null;
}

/** One officer of the Story roster. */
export interface Officer {
  id: string;
  classId: string;
  level: number;
  /** Experience toward the next level, 0–99. */
  exp: number;
  /** Ratings with the growth earned so far, without gear (1–100). */
  stats: Stats;
  gear: Gear;
  /** Guests fight for a while and then leave. */
  guest: boolean;
  /** Battles fought and enemies defeated, for the roster screen. */
  battles: number;
  defeated: number;
}

export interface CampaignState {
  /** Save format. */
  version: 1;
  chapter: string;
  /** Index of the chapter step being played. */
  step: number;
  gold: number;
  /** Spare items by id → count (equipped items are on the officers). */
  inventory: Record<string, number>;
  /** In the order they joined. */
  roster: Officer[];
  /** Ids of the battles won. */
  cleared: string[];
}
