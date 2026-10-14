/**
 * Officers for the P0 sandbox. Names are historical (public domain); every
 * rating is our own, on the 1–100 scale of ADR 0020, set in steps of 5 from the
 * rubric in this package's README until the full officer pass. Nothing here
 * comes from Koei or Nexon data (ADR 0002).
 */
import type { Stats } from "@tk/rules";

export interface OfficerDef {
  readonly id: string;
  /** Display name (Korean reading). */
  readonly name: string;
  readonly hanja: string;
  readonly stats: Stats;
}

/** 무력, 통솔, 지력, 민첩, 운. */
type Ratings = readonly [number, number, number, number, number];

function officer(id: string, name: string, hanja: string, ratings: Ratings): OfficerDef {
  const [martial, command, intellect, agility, fortune] = ratings;
  return { id, name, hanja, stats: { martial, command, intellect, agility, fortune } };
}

// biome-ignore format: one officer per line reads as a table
const LIST: readonly OfficerDef[] = [
  //                                         무력 통솔 지력 민첩 운
  officer("cao-cao",      "조조",   "曹操",   [70, 90, 90, 70, 85]),
  officer("xu-chu",       "허저",   "許褚",   [95, 60, 30, 70, 65]),
  officer("yue-jin",      "악진",   "樂進",   [80, 75, 50, 65, 60]),
  officer("xiahou-yuan",  "하후연", "夏侯淵", [80, 70, 50, 85, 55]),
  officer("xun-you",      "순유",   "荀攸",   [25, 55, 90, 50, 70]),
  officer("liu-ye",       "유엽",   "劉曄",   [35, 55, 80, 45, 60]),
  officer("chunyu-qiong", "순우경", "淳于瓊", [65, 65, 40, 50, 30]),
  officer("gao-lan",      "고람",   "高覽",   [75, 65, 40, 60, 55]),
  officer("zhang-he",     "장합",   "張郃",   [85, 80, 65, 75, 70]),
  officer("qu-yi",        "국의",   "麴義",   [75, 75, 45, 60, 40]),
  officer("ju-shou",      "저수",   "沮授",   [20, 70, 90, 45, 35]),
  officer("wen-chou",     "문추",   "文醜",   [90, 65, 25, 70, 45]),
];

export const OFFICERS: Readonly<Record<string, OfficerDef>> = Object.fromEntries(
  LIST.map((o) => [o.id, o]),
);
