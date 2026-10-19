/**
 * Chapter flow (ADR 0049): a campaign plays its chapter's steps in order.
 * Scenes and camps are left by the player; a battle step is left only by
 * winning it (`settle` in battle.ts).
 */
import {
  CHAPTERS,
  type ChapterDef,
  type ChapterStep,
  ITEMS,
  type JoinDef,
  OFFICERS,
  P0_RULES,
} from "@tk/content";
import type { Ruleset } from "@tk/rules";
import { dropMisfits, type Result } from "./gear.ts";
import { promotionFor } from "./progression.ts";
import type { CampaignState, Officer } from "./types.ts";

export function chapterOf(state: CampaignState): ChapterDef {
  const ch = CHAPTERS[state.chapter];
  if (!ch) throw new Error(`unknown chapter ${state.chapter}`);
  return ch;
}

/** The step being played, or null once the chapter is over. */
export function currentStep(state: CampaignState): ChapterStep | null {
  return chapterOf(state).steps[state.step] ?? null;
}

export function isFinished(state: CampaignState): boolean {
  return currentStep(state) === null;
}

/** A new roster officer from a join entry: the officer's ratings, the listed gear on. */
export function recruit(j: JoinDef): Officer {
  const def = OFFICERS[j.officer];
  if (!def) throw new Error(`unknown officer ${j.officer}`);
  const o: Officer = {
    id: j.officer,
    classId: j.classId,
    level: j.level,
    exp: 0,
    stats: { ...def.stats },
    gear: { weapon: null, armour: null, accessory: null },
    guest: j.guest ?? false,
    battles: 0,
    defeated: 0,
  };
  for (const id of j.gear ?? []) {
    const item = ITEMS[id];
    if (item) o.gear[item.slot] = id;
  }
  return o;
}

export function newCampaign(chapterId: string): CampaignState {
  const ch = CHAPTERS[chapterId];
  if (!ch) throw new Error(`unknown chapter ${chapterId}`);
  return {
    version: 1,
    chapter: ch.id,
    step: 0,
    gold: ch.gold,
    inventory: {},
    roster: ch.roster.map(recruit),
    cleared: [],
  };
}

/** Ends the current scene: officers leave (their gear returns to the inventory), others join. */
export function finishScene(state: CampaignState): Result {
  const step = currentStep(state);
  if (step?.kind !== "scene") return { ok: false, reason: "not in a scene" };
  const next = structuredClone(state);
  for (const id of step.scene.leaves ?? []) {
    const o = next.roster.find((r) => r.id === id);
    if (!o) continue;
    for (const item of [o.gear.weapon, o.gear.armour, o.gear.accessory])
      if (item) next.inventory[item] = (next.inventory[item] ?? 0) + 1;
    next.roster = next.roster.filter((r) => r.id !== id);
  }
  for (const j of step.scene.joins ?? [])
    if (!next.roster.some((r) => r.id === j.officer)) next.roster.push(recruit(j));
  next.step += 1;
  return { ok: true, state: next };
}

export function leaveCamp(state: CampaignState): Result {
  if (currentStep(state)?.kind !== "camp") return { ok: false, reason: "not in a camp" };
  return { ok: true, state: { ...structuredClone(state), step: state.step + 1 } };
}

/** Promotes an officer to the next tier of its family (ADR 0048). Gear of the same family still fits. */
export function promote(
  state: CampaignState,
  officerId: string,
  rules: Ruleset = P0_RULES,
): Result {
  const o = state.roster.find((r) => r.id === officerId);
  if (!o) return { ok: false, reason: "unknown officer" };
  const to = promotionFor(o);
  if (!to || !rules.classes[to]) return { ok: false, reason: "not ready to promote" };
  const next = structuredClone(state);
  const p = next.roster.find((r) => r.id === officerId) as Officer;
  p.classId = to;
  return { ok: true, state: dropMisfits(next, officerId, rules) };
}
