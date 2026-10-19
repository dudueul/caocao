/**
 * The six P0 tier-1 classes (ADR 0021) and their tier-2 promotions (ADR 0048).
 * Base values follow the medium number scale (ADR 0022): ATK = class + 무력 +
 * weapon, DEF = class + 통솔 + armour, max HP = class HP + 통솔 + 4 × level.
 * Starting tuning, our own numbers.
 */
import type { ClassDef, FamilyId, Offset } from "@tk/rules";
import { BOW, MELEE, SIEGE } from "./shapes.ts";

interface Base {
  name: string;
  tier?: 1 | 2 | 3;
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
    tier: b.tier ?? 1,
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
  // Tier 2 (ADR 0048): the same family and range, sturdier numbers, one more skill.
  heavyInfantry: cls("heavyInfantry", {
    name: "중보병",
    tier: 2,
    family: "infantry",
    hp: 200,
    move: 4,
    atk: 55,
    def: 70,
    range: MELEE,
    stratagems: ["ironWall", "battleCry"],
  }),
  longbow: cls("longbow", {
    name: "강궁병",
    tier: 2,
    family: "archer",
    hp: 150,
    move: 4,
    atk: 58,
    def: 42,
    range: BOW,
    stratagems: ["gallop"],
  }),
  shockCavalry: cls("shockCavalry", {
    name: "돌기병",
    tier: 2,
    family: "cavalry",
    hp: 190,
    move: 6,
    atk: 68,
    def: 55,
    range: MELEE,
    skirmisher: true,
    stratagems: ["gallop", "battleCry"],
  }),
  tactician: cls("tactician", {
    name: "군사",
    tier: 2,
    family: "strategist",
    hp: 140,
    mp: 30,
    move: 4,
    atk: 32,
    def: 38,
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
  guardian: cls("guardian", {
    name: "호위무사",
    tier: 2,
    family: "martialArtist",
    hp: 180,
    move: 5,
    atk: 62,
    def: 50,
    range: MELEE,
    doubleAttackGap: 10,
    stratagems: ["battleCry", "ironWall"],
  }),
  thunderCart: cls("thunderCart", {
    name: "벽력거",
    tier: 2,
    family: "catapult",
    hp: 165,
    move: 3,
    atk: 70,
    def: 42,
    range: SIEGE,
    provokesCounter: false,
  }),
};

/** Tier-1 class → its tier-2 promotion (ADR 0048). */
export const PROMOTIONS: Readonly<Record<string, string>> = {
  infantry: "heavyInfantry",
  archer: "longbow",
  cavalry: "shockCavalry",
  strategist: "tactician",
  martialArtist: "guardian",
  catapult: "thunderCart",
};

/**
 * Stat growth per level, in ‰ of a point (ADR 0048): reaching level L adds
 * ⌊L × rate / 1000⌋ − ⌊(L − 1) × rate / 1000⌋ to each stat, so growth is steady
 * and needs no dice. Order: 무력, 통솔, 지력, 민첩, 운. Tier 2 grows a little faster.
 */
// biome-ignore format: one class per line reads as a table
export const GROWTH: Readonly<Record<string, readonly [number, number, number, number, number]>> = {
  infantry:      [500, 800, 200, 300, 300],
  archer:        [500, 400, 300, 700, 300],
  cavalry:       [700, 500, 200, 500, 300],
  strategist:    [200, 400, 900, 300, 400],
  martialArtist: [800, 300, 200, 700, 300],
  catapult:      [500, 500, 600, 200, 300],
  heavyInfantry: [600, 900, 200, 300, 400],
  longbow:       [600, 400, 300, 800, 400],
  shockCavalry:  [800, 600, 200, 500, 400],
  tactician:     [200, 500, 1000, 300, 500],
  guardian:      [900, 400, 200, 800, 300],
  thunderCart:   [600, 500, 700, 200, 400],
};
