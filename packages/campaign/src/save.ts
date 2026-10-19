/**
 * Save files (ADR 0051): a save is the campaign state as JSON. Anything read
 * back is untrusted (another version, a hand edit, a damaged store), so
 * `readSave` checks every field and returns a reason instead of throwing.
 */
import { CHAPTERS, fits, ITEMS, OFFICERS, P0_RULES } from "@tk/content";
import type { Ruleset, Stats } from "@tk/rules";
import { STAT_KEYS } from "./progression.ts";
import { PROGRESSION } from "./tuning.ts";
import type { CampaignState, Gear, Officer } from "./types.ts";

export const SAVE_VERSION = 1;

export function writeSave(state: CampaignState): string {
  return JSON.stringify(state);
}

type Read<T> = { ok: true; state: T } | { ok: false; reason: string };

const isObj = (v: unknown): v is Record<string, unknown> =>
  typeof v === "object" && v !== null && !Array.isArray(v);
const isInt = (v: unknown, lo: number, hi: number): v is number =>
  typeof v === "number" && Number.isSafeInteger(v) && v >= lo && v <= hi;

function readStats(v: unknown): Stats | null {
  if (!isObj(v)) return null;
  const out = {} as Record<keyof Stats, number>;
  for (const k of STAT_KEYS) {
    const n = v[k];
    if (!isInt(n, 1, PROGRESSION.statCap)) return null;
    out[k] = n;
  }
  return out;
}

function readGear(v: unknown, classId: string, rules: Ruleset): Gear | null {
  if (!isObj(v)) return null;
  const out: Gear = { weapon: null, armour: null, accessory: null };
  for (const slot of ["weapon", "armour", "accessory"] as const) {
    const id = v[slot];
    if (id === null || id === undefined) continue;
    if (typeof id !== "string" || ITEMS[id]?.slot !== slot || !fits(rules, classId, id))
      return null;
    out[slot] = id;
  }
  return out;
}

function readOfficer(v: unknown, rules: Ruleset): Officer | string {
  if (!isObj(v)) return "an officer is not an object";
  const { id, classId, level, exp, guest, battles, defeated } = v;
  if (typeof id !== "string" || !OFFICERS[id] || OFFICERS[id]?.generic) return "unknown officer";
  if (typeof classId !== "string" || !rules.classes[classId]) return `${id}: unknown class`;
  if (!isInt(level, 1, PROGRESSION.levelCap)) return `${id}: bad level`;
  if (!isInt(exp, 0, PROGRESSION.expPerLevel - 1)) return `${id}: bad experience`;
  if (typeof guest !== "boolean") return `${id}: bad guest flag`;
  if (!isInt(battles, 0, 1_000_000) || !isInt(defeated, 0, 1_000_000)) return `${id}: bad record`;
  const stats = readStats(v.stats);
  if (!stats) return `${id}: bad stats`;
  const gear = readGear(v.gear, classId, rules);
  if (!gear) return `${id}: bad gear`;
  return { id, classId, level, exp, stats, gear, guest, battles, defeated };
}

/** Parses and checks a save; never throws. */
export function readSave(text: string, rules: Ruleset = P0_RULES): Read<CampaignState> {
  let v: unknown;
  try {
    v = JSON.parse(text);
  } catch {
    return { ok: false, reason: "not JSON" };
  }
  if (!isObj(v)) return { ok: false, reason: "not an object" };
  if (v.version !== SAVE_VERSION) return { ok: false, reason: "unknown save version" };
  const chapter = typeof v.chapter === "string" ? CHAPTERS[v.chapter] : undefined;
  if (!chapter) return { ok: false, reason: "unknown chapter" };
  if (!isInt(v.step, 0, chapter.steps.length)) return { ok: false, reason: "bad step" };
  if (!isInt(v.gold, 0, 1_000_000_000)) return { ok: false, reason: "bad gold" };
  if (!isObj(v.inventory)) return { ok: false, reason: "bad inventory" };
  const inventory: Record<string, number> = {};
  for (const [id, n] of Object.entries(v.inventory)) {
    if (!ITEMS[id] || !isInt(n, 1, 9999)) return { ok: false, reason: `bad item ${id}` };
    inventory[id] = n;
  }
  if (!Array.isArray(v.roster) || v.roster.length === 0) return { ok: false, reason: "bad roster" };
  const roster: Officer[] = [];
  for (const raw of v.roster) {
    const o = readOfficer(raw, rules);
    if (typeof o === "string") return { ok: false, reason: o };
    if (roster.some((r) => r.id === o.id)) return { ok: false, reason: `${o.id} twice` };
    roster.push(o);
  }
  if (!Array.isArray(v.cleared) || !v.cleared.every((c) => typeof c === "string"))
    return { ok: false, reason: "bad cleared list" };
  return {
    ok: true,
    state: {
      version: 1,
      chapter: chapter.id,
      step: v.step,
      gold: v.gold,
      inventory,
      roster,
      cleared: [...new Set(v.cleared as string[])],
    },
  };
}
