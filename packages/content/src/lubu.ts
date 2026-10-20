/**
 * Story chapter 2: 여포 (193–198), ADR 0059. From the death of Cao Cao's
 * father to the fall of Lü Bu at Xiapi: the revenge war on Xuzhou, the revolt
 * of Yanzhou and the fire at Puyang, the dike at Dingtao, the emperor brought
 * to Xu, the night at Wancheng, Yuan Shu's generals at Qi, the tunnels at
 * Anzhong, and the flood at Xiapi. Two choices (서주, 백문루) and one fate
 * decided by play (전위 at 완성) branch the story (ADR 0060, 0061).
 * History is public domain; every line, map and number here is our own
 * (ADR 0002). The battles live in lubu-battles.ts and the scenes and camps in
 * lubu-scenes.ts; this file puts them in order.
 */
import { ANZHONG, DINGTAO, PENGCHENG, PUYANG_FIRE, QI, WANCHENG, XIAPI } from "./lubu-battles.ts";
import {
  AFTER_ANZHONG,
  AFTER_DINGTAO,
  AFTER_PUYANG,
  AFTER_WANCHENG,
  BEFORE_ANZHONG,
  BEFORE_DINGTAO,
  BEFORE_PENGCHENG,
  BEFORE_PUYANG,
  BEFORE_QI,
  BEFORE_XIAPI,
  CAMP_1,
  CAMP_2,
  CAMP_3,
  CAMP_4,
  DIAN_WEI,
  EMPEROR,
  EPILOGUE,
  FEAST,
  PROLOGUE,
  REVOLT,
  WHITE_GATE,
  XUZHOU,
} from "./lubu-scenes.ts";
import type { ChapterDef } from "./story.ts";

export const LUBU: ChapterDef = {
  id: "lubu",
  number: 2,
  name: "2장",
  title: "여포",
  years: "193–198",
  summary:
    "아버지를 잃은 조조는 복수의 칼을 든다. 그 사이 연주가 등을 돌리고, 천하무적 여포가 앞을 가로막는다.",
  gold: 600,
  floor: 10,
  next: "guandu",
  ending: {
    title: "여포 · 완",
    lead: "하비의 물이 빠지자 중원에 조조와 맞설 이는 북쪽의 원소 하나만 남았다.",
  },
  roster: [
    { officer: "cao-cao", classId: "cavalry", level: 12, gear: ["refined-dao", "lamellar"] },
    { officer: "xiahou-dun", classId: "cavalry", level: 11, gear: ["iron-ji", "lamellar"] },
    { officer: "xiahou-yuan", classId: "archer", level: 11, gear: ["horn-bow", "leather"] },
    { officer: "cao-ren", classId: "infantry", level: 11, gear: ["ring-dao", "lamellar"] },
    { officer: "cao-hong", classId: "cavalry", level: 10, gear: ["iron-ji", "leather"] },
    { officer: "xun-yu", classId: "strategist", level: 11, gear: ["feather-fan", "robe"] },
    { officer: "yue-jin", classId: "infantry", level: 10, gear: ["ring-dao", "lamellar"] },
    { officer: "li-dian", classId: "archer", level: 10, gear: ["horn-bow", "leather"] },
    { officer: "chen-gong", classId: "strategist", level: 11, gear: ["feather-fan", "robe"] },
    { officer: "yu-jin", classId: "infantry", level: 12, gear: ["iron-ji", "lamellar"] },
  ],
  steps: [
    { kind: "scene", scene: PROLOGUE },
    { kind: "scene", scene: BEFORE_PENGCHENG },
    { kind: "battle", battle: PENGCHENG },
    { kind: "scene", scene: XUZHOU },
    { kind: "scene", scene: REVOLT },
    { kind: "scene", scene: DIAN_WEI },
    { kind: "camp", camp: CAMP_1 },
    { kind: "scene", scene: BEFORE_PUYANG },
    { kind: "battle", battle: PUYANG_FIRE },
    { kind: "scene", scene: AFTER_PUYANG },
    { kind: "scene", scene: BEFORE_DINGTAO },
    { kind: "battle", battle: DINGTAO },
    { kind: "scene", scene: AFTER_DINGTAO },
    { kind: "camp", camp: CAMP_2 },
    { kind: "scene", scene: EMPEROR },
    { kind: "scene", scene: FEAST },
    { kind: "battle", battle: WANCHENG },
    { kind: "scene", scene: AFTER_WANCHENG },
    { kind: "camp", camp: CAMP_3 },
    { kind: "scene", scene: BEFORE_QI },
    { kind: "battle", battle: QI },
    { kind: "scene", scene: BEFORE_ANZHONG },
    { kind: "battle", battle: ANZHONG },
    { kind: "scene", scene: AFTER_ANZHONG },
    { kind: "camp", camp: CAMP_4 },
    { kind: "scene", scene: BEFORE_XIAPI },
    { kind: "battle", battle: XIAPI },
    { kind: "scene", scene: WHITE_GATE },
    { kind: "scene", scene: EPILOGUE },
  ],
};
