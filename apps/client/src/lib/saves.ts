/**
 * Save slots in this browser (ADR 0051): three manual slots and an autosave.
 * Each holds the campaign state as JSON plus a little metadata for the list.
 * Reads are validated by `readSave`; storage errors never break the game.
 */
import { type CampaignState, chapterOf, currentStep, readSave, writeSave } from "@tk/campaign";
import { OFFICERS } from "@tk/content";

export type SlotId = "auto" | "1" | "2" | "3";
export const SLOTS: readonly SlotId[] = ["auto", "1", "2", "3"];

export interface SaveMeta {
  slot: SlotId;
  /** Milliseconds since 1970 (the browser's clock; display only). */
  savedAt: number;
  chapter: string;
  where: string;
  gold: number;
  lord: string;
}

const key = (slot: SlotId) => `tk.save.${slot}`;

/** Where the campaign stands, in words: the chapter and the step. */
export function describeStep(state: CampaignState): { chapter: string; where: string } {
  const ch = chapterOf(state);
  const step = currentStep(state);
  const where =
    step === null
      ? "완료"
      : step.kind === "scene"
        ? step.scene.title
        : step.kind === "battle"
          ? `${step.battle.name} 출진 전`
          : "군영";
  return { chapter: `${ch.name} ${ch.title}`, where };
}

function metaFor(slot: SlotId, state: CampaignState): SaveMeta {
  const lead = state.roster[0];
  return {
    slot,
    savedAt: Date.now(),
    ...describeStep(state),
    gold: state.gold,
    lord: lead ? `${OFFICERS[lead.id]?.name ?? lead.id} Lv ${lead.level}` : "",
  };
}

export function writeSlot(slot: SlotId, state: CampaignState): boolean {
  try {
    localStorage.setItem(
      key(slot),
      JSON.stringify({ meta: metaFor(slot, state), data: writeSave(state) }),
    );
    return true;
  } catch {
    return false;
  }
}

function raw(slot: SlotId): { meta: SaveMeta; data: string } | null {
  try {
    const text = localStorage.getItem(key(slot));
    if (!text) return null;
    const v = JSON.parse(text) as { meta?: SaveMeta; data?: unknown };
    if (!v.meta || typeof v.data !== "string") return null;
    return { meta: { ...v.meta, slot }, data: v.data };
  } catch {
    return null;
  }
}

export function readSlot(slot: SlotId): CampaignState | null {
  const r = raw(slot);
  if (!r) return null;
  const s = readSave(r.data);
  return s.ok ? s.state : null;
}

/** Metadata for each slot that holds a readable save. */
export function listSaves(): SaveMeta[] {
  return SLOTS.flatMap((slot) => {
    const r = raw(slot);
    return r && readSave(r.data).ok ? [r.meta] : [];
  });
}

/** The most recent save, for "continue". */
export function latestSave(): SaveMeta | null {
  return listSaves().reduce<SaveMeta | null>((a, b) => (!a || b.savedAt > a.savedAt ? b : a), null);
}

export function deleteSlot(slot: SlotId): void {
  try {
    localStorage.removeItem(key(slot));
  } catch {
    // Nothing to do: the slot stays.
  }
}

export function slotName(slot: SlotId): string {
  return slot === "auto" ? "자동 저장" : `슬롯 ${slot}`;
}
