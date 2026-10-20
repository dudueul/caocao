/**
 * Chapter flow (ADR 0049): a campaign plays its chapter's steps in order.
 * Scenes and camps are left by the player; a battle step is left only by
 * winning it (`settle` in battle.ts). Steps whose condition fails are passed
 * over, and scene choices set story flags (ADR 0060).
 */
import {
  CHAPTERS,
  type ChapterDef,
  type ChapterStep,
  type Cmd,
  holds,
  ITEMS,
  type JoinDef,
  type Leave,
  OFFICERS,
  P0_RULES,
  type SceneDef,
} from "@tk/content";
import type { Ruleset } from "@tk/rules";
import { dropMisfits, type Result } from "./gear.ts";
import { promotionFor, raiseTo } from "./progression.ts";
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

/** The first step at or after `from` whose condition holds (the step count when none does). */
export function stepFrom(
  ch: ChapterDef,
  from: number,
  flags: Readonly<Record<string, string>>,
): number {
  let i = from;
  while (i < ch.steps.length && !holds(ch.steps[i]?.if, flags)) i += 1;
  return i;
}

/** Moves a (cloned) campaign on to its next step that applies. */
export function stepOn(next: CampaignState): CampaignState {
  next.step = stepFrom(chapterOf(next), next.step + 1, next.flags);
  return next;
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
  const flags = { ...(ch.assumes ?? {}) };
  return {
    version: 2,
    chapter: ch.id,
    step: stepFrom(ch, 0, flags),
    gold: ch.gold,
    inventory: {},
    roster: ch.roster.filter((j) => holds(j.if, flags)).map(recruit),
    cleared: [],
    flags,
    gone: [],
  };
}

/** The chapter after this campaign's, once it exists (ADR 0056). */
export function nextChapter(state: CampaignState): ChapterDef | null {
  const next = chapterOf(state).next;
  return next ? (CHAPTERS[next] ?? null) : null;
}

/**
 * Carries a finished campaign into another chapter (ADR 0056): the roster,
 * gold, items and record go along; guests leave with their gear returned;
 * officers below the chapter's floor are raised to it with growth; officers
 * the chapter starts with join if they are missing.
 */
export function continueTo(state: CampaignState, chapterId: string): Result {
  const ch = CHAPTERS[chapterId];
  if (!ch) return { ok: false, reason: "unknown chapter" };
  if (!isFinished(state)) return { ok: false, reason: "this chapter is not finished" };
  const next = structuredClone(state);
  for (const o of next.roster.filter((r) => r.guest))
    for (const item of [o.gear.weapon, o.gear.armour, o.gear.accessory])
      if (item) next.inventory[item] = (next.inventory[item] ?? 0) + 1;
  next.roster = next.roster.filter((r) => !r.guest);
  for (const o of next.roster) raiseTo(o, ch.floor);
  for (const j of ch.roster) {
    if (next.roster.some((r) => r.id === j.officer)) continue;
    if (next.gone.includes(j.officer) || !holds(j.if, next.flags)) continue;
    const o = recruit(j);
    raiseTo(o, ch.floor);
    next.roster.push(o);
  }
  next.chapter = ch.id;
  next.step = stepFrom(ch, 0, next.flags);
  return { ok: true, state: next };
}

/** Picks made in a scene's choices, flag → value. */
export type Picks = Readonly<Record<string, string>>;

/**
 * Walks a scene script with the flags as they stand, applying each choice it
 * reaches (a later step may test an earlier pick). Returns the flags, gold and
 * items the choices give, or the flag of the first choice left unanswered.
 */
export function walkChoices(
  scene: SceneDef,
  flags: Readonly<Record<string, string>>,
  picks: Picks,
): { flags: Record<string, string>; gold: number; items: string[] } | { missing: string } {
  const out = { flags: { ...flags }, gold: 0, items: [] as string[] };
  const visit = (cmds: readonly Cmd[]): string | null => {
    for (const c of cmds) {
      if (!holds(c.if, out.flags)) continue;
      if (c.do === "all") {
        const m = visit(c.cmds);
        if (m) return m;
      } else if (c.do === "choice") {
        const opt = c.options.find((o) => o.value === picks[c.flag]);
        if (!opt) return c.flag;
        out.flags[c.flag] = opt.value;
        out.gold += opt.gold ?? 0;
        out.items.push(...(opt.items ?? []));
      }
    }
    return null;
  };
  const missing = visit(scene.script);
  return missing ? { missing } : out;
}

const leaveId = (l: Leave) => (typeof l === "string" ? l : l.officer);

/**
 * Ends the current scene with the player's picks: its choices set their flags
 * and pay their spoils, then officers leave for good (their gear returns to
 * the inventory) and others join.
 */
export function finishScene(state: CampaignState, picks: Picks = {}): Result {
  const step = currentStep(state);
  if (step?.kind !== "scene") return { ok: false, reason: "not in a scene" };
  const walked = walkChoices(step.scene, state.flags, picks);
  if ("missing" in walked) return { ok: false, reason: `choice ${walked.missing} not made` };
  const next = structuredClone(state);
  next.flags = walked.flags;
  next.gold += walked.gold;
  for (const id of walked.items) if (ITEMS[id]) next.inventory[id] = (next.inventory[id] ?? 0) + 1;
  for (const l of step.scene.leaves ?? []) {
    if (typeof l !== "string" && !holds(l.if, next.flags)) continue;
    const id = leaveId(l);
    const o = next.roster.find((r) => r.id === id);
    if (o) {
      for (const item of [o.gear.weapon, o.gear.armour, o.gear.accessory])
        if (item) next.inventory[item] = (next.inventory[item] ?? 0) + 1;
      next.roster = next.roster.filter((r) => r.id !== id);
    }
    if (!next.gone.includes(id)) next.gone.push(id);
  }
  for (const j of step.scene.joins ?? []) {
    if (!holds(j.if, next.flags) || next.roster.some((r) => r.id === j.officer)) continue;
    next.roster.push(recruit(j));
    next.gone = next.gone.filter((id) => id !== j.officer);
  }
  return { ok: true, state: stepOn(next) };
}

export function leaveCamp(state: CampaignState): Result {
  if (currentStep(state)?.kind !== "camp") return { ok: false, reason: "not in a camp" };
  return { ok: true, state: stepOn(structuredClone(state)) };
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
