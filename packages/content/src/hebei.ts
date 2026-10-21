/**
 * Story chapter 4: 하북 평정 (201–207), ADR 0067. After 관도: 원소's last army
 * at 창정, 유비 driven from 여남, 원소's sons at 여양, the siege of 업 with its
 * gates (ADR 0069), 원담 broken on the ice at 남피, 고간 in the 태항 pass at
 * 호관, and the desert march to 백랑산 against the 오환. One choice decides
 * whether 곽가 marches north with us (ADR 0068). History is public domain;
 * every line, map and number here is our own (ADR 0002). The battles live in
 * hebei-battles.ts and the scenes and camps in hebei-scenes.ts.
 */
import { BAILANG, CANGTING, HUGUAN, LIYANG, NANPI, RUNAN, YE } from "./hebei-battles.ts";
import {
  AFTER_BAILANG,
  AFTER_LIYANG,
  AFTER_NANPI,
  AFTER_RUNAN,
  AFTER_YE,
  BEFORE_BAILANG,
  BEFORE_HUGUAN,
  BEFORE_LIYANG,
  BEFORE_NANPI,
  BEFORE_RUNAN,
  BEFORE_YE,
  CAMP_1,
  CAMP_2,
  CAMP_3,
  CAMP_4,
  EPILOGUE,
  PROLOGUE,
} from "./hebei-scenes.ts";
import type { ChapterDef } from "./story.ts";

export const HEBEI: ChapterDef = {
  id: "hebei",
  number: 4,
  name: "4장",
  title: "하북 평정",
  years: "201–207",
  summary:
    "관도에서 무너진 원씨의 하북. 원소의 아들들은 서로 싸우고, 조조는 북쪽 끝 사막까지 말을 달린다.",
  gold: 1500,
  floor: 18,
  // What history did in chapter 2, for a fresh start here (ADR 0060).
  assumes: { xuzhou: "revenge", dianwei: "fell", lubu: "executed" },
  ending: {
    title: "하북 평정 · 완",
    lead: "북방은 모두 조조의 손에 들어왔다. 이제 남쪽, 장강 너머로 눈길이 향한다.",
  },
  roster: [
    {
      officer: "cao-cao",
      classId: "shockCavalry",
      level: 22,
      gear: ["hundred-dao", "scale", "tiger-tally"],
    },
    { officer: "xiahou-dun", classId: "shockCavalry", level: 21, gear: ["great-ji", "scale"] },
    { officer: "xiahou-yuan", classId: "longbow", level: 21, gear: ["iron-bow", "lacquer"] },
    { officer: "cao-ren", classId: "heavyInfantry", level: 20, gear: ["refined-dao", "scale"] },
    { officer: "cao-hong", classId: "shockCavalry", level: 20, gear: ["horse-lance", "scale"] },
    { officer: "xun-yu", classId: "tactician", level: 20, gear: ["crane-fan", "padded-robe"] },
    { officer: "xun-you", classId: "tactician", level: 20, gear: ["crane-fan", "padded-robe"] },
    { officer: "guo-jia", classId: "tactician", level: 20, gear: ["crane-fan", "padded-robe"] },
    { officer: "cheng-yu", classId: "strategist", level: 19, gear: ["crane-fan", "robe"] },
    { officer: "xu-chu", classId: "guardian", level: 21, gear: ["refined-dao", "lacquer"] },
    {
      officer: "zhang-liao",
      classId: "shockCavalry",
      level: 21,
      gear: ["great-ji", "scale"],
      if: { flag: "lubu", not: "spared" },
    },
    { officer: "xu-huang", classId: "heavyInfantry", level: 20, gear: ["refined-dao", "scale"] },
    { officer: "yu-jin", classId: "infantry", level: 19, gear: ["horse-lance", "scale"] },
    { officer: "yue-jin", classId: "infantry", level: 19, gear: ["refined-dao", "scale"] },
    { officer: "li-dian", classId: "archer", level: 18, gear: ["strong-bow", "lacquer"] },
    { officer: "zhang-he", classId: "cavalry", level: 20, gear: ["horse-lance", "lamellar"] },
    { officer: "gao-lan", classId: "infantry", level: 19, gear: ["ring-dao", "lamellar"] },
    { officer: "liu-ye", classId: "catapult", level: 19, gear: ["iron-shot", "leather"] },
    { officer: "xu-you", classId: "strategist", level: 18, gear: ["feather-fan", "robe"] },
  ],
  steps: [
    { kind: "scene", scene: PROLOGUE },
    { kind: "battle", battle: CANGTING },
    { kind: "scene", scene: BEFORE_RUNAN },
    { kind: "battle", battle: RUNAN },
    { kind: "scene", scene: AFTER_RUNAN },
    { kind: "camp", camp: CAMP_1 },
    { kind: "scene", scene: BEFORE_LIYANG },
    { kind: "battle", battle: LIYANG },
    { kind: "scene", scene: AFTER_LIYANG },
    { kind: "scene", scene: BEFORE_YE },
    { kind: "battle", battle: YE },
    { kind: "scene", scene: AFTER_YE },
    { kind: "camp", camp: CAMP_2 },
    { kind: "scene", scene: BEFORE_NANPI },
    { kind: "battle", battle: NANPI },
    { kind: "scene", scene: AFTER_NANPI },
    { kind: "camp", camp: CAMP_3 },
    { kind: "scene", scene: BEFORE_HUGUAN },
    { kind: "battle", battle: HUGUAN },
    { kind: "camp", camp: CAMP_4 },
    { kind: "scene", scene: BEFORE_BAILANG },
    { kind: "battle", battle: BAILANG },
    { kind: "scene", scene: AFTER_BAILANG },
    { kind: "scene", scene: EPILOGUE },
  ],
};
