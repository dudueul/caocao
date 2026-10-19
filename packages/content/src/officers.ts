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
  /** A troop template, not a person: scenarios give each unit its own id (ADR 0049). */
  readonly generic?: boolean;
}

/** 무력, 통솔, 지력, 민첩, 운. */
type Ratings = readonly [number, number, number, number, number];

function officer(id: string, name: string, hanja: string, ratings: Ratings): OfficerDef {
  const [martial, command, intellect, agility, fortune] = ratings;
  return { id, name, hanja, stats: { martial, command, intellect, agility, fortune } };
}

function troop(id: string, name: string, hanja: string, ratings: Ratings): OfficerDef {
  return { ...officer(id, name, hanja, ratings), generic: true };
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
  // Arena pool (ADR 0041)
  officer("lu-bu",        "여포",   "呂布",   [100, 75, 25, 90, 30]),
  officer("guan-yu",      "관우",   "關羽",   [95, 90, 65, 75, 55]),
  officer("zhao-yun",     "조운",   "趙雲",   [90, 80, 65, 85, 90]),
  officer("zhang-fei",    "장비",   "張飛",   [95, 75, 35, 70, 50]),
  officer("xiahou-dun",   "하후돈", "夏侯惇", [85, 85, 55, 65, 60]),
  officer("gan-ning",     "감녕",   "甘寧",   [90, 75, 50, 85, 55]),
  officer("huang-zhong",  "황충",   "黃忠",   [90, 80, 50, 70, 60]),
  officer("taishi-ci",    "태사자", "太史慈", [90, 75, 55, 80, 45]),
  officer("zhuge-liang",  "제갈량", "諸葛亮", [35, 90, 100, 60, 75]),
  officer("zhou-yu",      "주유",   "周瑜",   [70, 90, 95, 70, 40]),
  officer("guo-jia",      "곽가",   "郭嘉",   [20, 50, 95, 55, 30]),
  officer("dian-wei",     "전위",   "典韋",   [95, 65, 30, 65, 35]),
  officer("ma-jun",       "마균",   "馬鈞",   [20, 40, 85, 40, 55]),
  // Story chapter 1, Guandu (ADR 0049)
  officer("zhang-liao",   "장료",   "張遼",   [90, 90, 75, 80, 65]),
  officer("xu-huang",     "서황",   "徐晃",   [85, 80, 60, 65, 60]),
  officer("li-dian",      "이전",   "李典",   [70, 75, 70, 60, 60]),
  officer("yu-jin",       "우금",   "于禁",   [75, 85, 55, 55, 40]),
  officer("xun-yu",       "순욱",   "荀彧",   [15, 60, 95, 45, 60]),
  officer("xu-you",       "허유",   "許攸",   [20, 40, 80, 45, 35]),
  officer("liu-yan",      "유연",   "劉延",   [50, 65, 55, 45, 50]),
  officer("yuan-shao",    "원소",   "袁紹",   [65, 80, 70, 55, 60]),
  officer("yan-liang",    "안량",   "顏良",   [90, 70, 30, 70, 40]),
  officer("tian-feng",    "전풍",   "田豐",   [15, 60, 95, 40, 25]),
  officer("shen-pei",     "심배",   "審配",   [45, 75, 75, 45, 40]),
  officer("guo-tu",       "곽도",   "郭圖",   [30, 50, 75, 50, 45]),
  officer("feng-ji",      "봉기",   "逢紀",   [25, 50, 75, 45, 40]),
  officer("yuan-tan",     "원담",   "袁譚",   [65, 65, 55, 60, 55]),
  officer("han-meng",     "한맹",   "韓猛",   [75, 60, 35, 60, 45]),
  officer("jiang-qi",     "장기",   "蔣奇",   [70, 60, 35, 55, 40]),
  officer("liu-bei",      "유비",   "劉備",   [75, 80, 75, 65, 90]),
  // Troop templates: each unit is named after its army and class.
  troop("yuan",           "원소군", "袁軍",   [60, 55, 45, 50, 40]),
  troop("yuan-elite",     "원소군 정예", "袁軍", [70, 65, 50, 60, 45]),
  troop("cao",            "조조군", "曹軍",   [60, 60, 45, 55, 45]),
];

export const OFFICERS: Readonly<Record<string, OfficerDef>> = Object.fromEntries(
  LIST.map((o) => [o.id, o]),
);
