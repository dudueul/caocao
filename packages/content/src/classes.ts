/**
 * The six P0 tier-1 classes (ADR 0021). Base values follow the medium number
 * scale (ADR 0022): ATK = class + 무력 + weapon, DEF = class + 통솔 + armour,
 * max HP = class HP + 통솔 + 4 × level. Starting tuning, our own numbers.
 */
import type { ClassDef, FamilyId, Offset } from "@tk/rules";
import { BOW, MELEE, SIEGE } from "./shapes.ts";

interface Base {
  name: string;
  family: FamilyId;
  hp: number;
  mp?: number;
  move: number;
  atk: number;
  def: number;
  range: readonly Offset[];
  doubleAttackGap?: number;
  provokesCounter?: boolean;
  skirmisher?: boolean;
  stratagems?: readonly string[];
}

function cls(id: string, b: Base): ClassDef {
  return {
    id,
    name: b.name,
    family: b.family,
    tier: 1,
    hp: b.hp,
    mp: b.mp ?? 0,
    move: b.move,
    atk: b.atk,
    def: b.def,
    range: b.range,
    doubleAttackGap: b.doubleAttackGap ?? 20,
    provokesCounter: b.provokesCounter ?? true,
    skirmisher: b.skirmisher ?? false,
    stratagems: b.stratagems ?? [],
  };
}

export const CLASSES: Readonly<Record<string, ClassDef>> = {
  infantry: cls("infantry", {
    name: "보병",
    family: "infantry",
    hp: 170,
    move: 4,
    atk: 45,
    def: 55,
    range: MELEE,
    stratagems: ["ironWall"],
  }),
  archer: cls("archer", {
    name: "궁병",
    family: "archer",
    hp: 130,
    move: 4,
    atk: 45,
    def: 35,
    range: BOW,
  }),
  cavalry: cls("cavalry", {
    name: "기병",
    family: "cavalry",
    hp: 160,
    move: 6,
    atk: 55,
    def: 45,
    range: MELEE,
    skirmisher: true,
    stratagems: ["gallop"],
  }),
  // 책사 carries 치료 and 정화 in P0, until 도사 (healers) arrive (ADR 0036).
  strategist: cls("strategist", {
    name: "책사",
    family: "strategist",
    hp: 120,
    mp: 20,
    move: 4,
    atk: 25,
    def: 30,
    range: MELEE,
    stratagems: [
      "fire",
      "rockfall",
      "flood",
      "gale",
      "poisonSmoke",
      "confuse",
      "bind",
      "seal",
      "heal",
      "cleanse",
    ],
  }),
  martialArtist: cls("martialArtist", {
    name: "무도가",
    family: "martialArtist",
    hp: 150,
    move: 5,
    atk: 50,
    def: 40,
    range: MELEE,
    doubleAttackGap: 10,
    stratagems: ["battleCry"],
  }),
  catapult: cls("catapult", {
    name: "포차",
    family: "catapult",
    hp: 140,
    move: 3,
    atk: 55,
    def: 35,
    range: SIEGE,
    provokesCounter: false,
  }),
};
