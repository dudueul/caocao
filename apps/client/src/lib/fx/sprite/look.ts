/**
 * What a figure looks like (ADR 0073), read from the same data as its
 * portrait (ADR 0053, ADR 0070): a named officer's row in the trait table
 * (headgear, hair and beard, skin, build, accent and signature items), or a
 * soldier from his army's kit, varied by a hash of his id. The side's dye goes
 * on the robe. The look is a pure function of (unit id, class id, side), and
 * its key names everything the painter draws. Soldiers come in three looks
 * per army and class, so the ranks share their sheets.
 */
import { clothOf, SHOULDERS } from "../portrait/body.ts";
import { HELM_OF, HELMS, type HelmSpec, type HelmStyle } from "../portrait/headgear.ts";
import { OFFICERS } from "../portrait/officers.ts";
import { classFamily, isWork, resolve } from "../portrait/paint.ts";
import {
  ACCENTS,
  BLACK_IRON,
  BLACK_IRON_DARK,
  BLACK_IRON_LIGHT,
  IRON,
  IRON_DARK,
  IRON_LIGHT,
  LACQUER,
  LACQUER_DARK,
  LACQUER_LIGHT,
  LEATHER,
  LEATHER_DARK,
  LEATHER_LIGHT,
  type Pal,
  STEEL,
  STEEL_DARK,
  STEEL_LIGHT,
} from "../portrait/palette.ts";
import type {
  BeardKind,
  BrowKind,
  Build,
  EyeKind,
  FaceShape,
  Family,
  Hat,
  Mark,
  Traits,
} from "../portrait/types.ts";
import type { Tri } from "./draw.ts";

/** What the weapon hand holds. */
export type Weapon =
  | "sabre"
  | "sword"
  | "handJi"
  | "cudgel"
  | "staves"
  | "staff"
  | "jie"
  | "fan"
  | "ji"
  | "jiPennon"
  | "crescent"
  | "glaive"
  | "snake"
  | "spear"
  | "trident"
  | "axe"
  | "dao"
  | "twinJi"
  | "bow"
  | "longbow"
  | "crossbow"
  | "fists"
  | "none";

/** How the weapon is held, which picks the attack: a slash, a thrust or sweep, a shot, a punch. */
export type Grip = "one" | "pole" | "twin" | "bow" | "crossbow" | "fists" | "fan" | "engine";

/** What the other hand holds. */
export type Offhand =
  | "shield"
  | "bigShield"
  | "scroll"
  | "torch"
  | "pennon"
  | "lantern"
  | "jar"
  | "seal"
  | "cup"
  | "whip"
  | "none";

/** What is slung on the back. */
export type BackItem =
  | "quiver"
  | "quivers"
  | "plumes"
  | "parasol"
  | "zither"
  | "banners"
  | "ladder"
  | "none";

/** Armour over the robe: a full lamellar cuirass with shoulder flaps, a chest piece, a vest, or none. */
export type ArmourCut = "cuirass" | "chest" | "vest" | "none";

export interface SpriteLook {
  /** Everything the painter draws, as a cache key. */
  key: string;
  fam: Family;
  tier2: boolean;
  named: boolean;
  side: number;
  /** A rider shown on foot (indoors, kneeling, about the camp). */
  afoot: boolean;
  mounted: boolean;
  P: Pal;
  robe: Tri;
  edge: Tri;
  coarse: boolean;
  /** A hero's accent colour, on the sash, the collar or the cap. */
  accent: Tri | null;
  skin: Tri;
  hair: Tri;
  age: number;
  build: Build;
  /** Body proportions: overall scale, shoulder width, belly. */
  scale: number;
  shoulders: number;
  belly: number;
  face: FaceShape;
  faceW: number;
  eye: EyeKind;
  brow: BrowKind;
  beard: BeardKind;
  hat: Hat;
  helm: (HelmSpec & { style: HelmStyle }) | null;
  armour: ArmourCut;
  /** The armour's plates: base, shadow, light. */
  plate: Tri;
  /** Plates laced with a lacquered red edge (the south). */
  lacquer: boolean;
  gilt: boolean;
  /** Fur or fleece over the shoulders. */
  collar: "fur" | "fleece" | null;
  weapon: Weapon;
  grip: Grip;
  offhand: Offhand;
  back: BackItem;
  /** The horse's coat (base, shadow, light), when riding. */
  coat: Tri | null;
  /** Lamellar horse armour (tier-2 shock cavalry). */
  barded: boolean;
  marks: ReadonlySet<Mark>;
  /** A small per-id number for things that vary among soldiers. */
  seed: number;
}

const POLE = new Set<Weapon>([
  "ji",
  "jiPennon",
  "crescent",
  "glaive",
  "snake",
  "spear",
  "trident",
  "axe",
  "dao",
  "staff",
  "jie",
]);

const BACK_WEAPON: Partial<Record<string, Weapon>> = {
  ji: "ji",
  jiPennon: "jiPennon",
  crescent: "crescent",
  glaive: "glaive",
  snake: "snake",
  spear: "spear",
  trident: "trident",
  axe: "axe",
  dao: "dao",
  twinJi: "twinJi",
  // 呂布 rides 赤兔 with the 方天畫戟 (the novel); the portrait shows both.
  horse: "crescent",
};

const FRONT_WEAPON: Partial<Record<string, Weapon>> = {
  handJi: "handJi",
  sword: "sword",
  sabre: "sabre",
  staff: "staff",
  cudgel: "cudgel",
  staves: "staves",
  jie: "jie",
  spear: "spear",
  fan: "fan",
};

const FRONT_OFFHAND: Partial<Record<string, Offhand>> = {
  slips: "scroll",
  tablet: "scroll",
  scroll: "scroll",
  book: "scroll",
  edict: "scroll",
  plan: "scroll",
  map: "scroll",
  brush: "scroll",
  torch: "torch",
  pennon: "pennon",
  lantern: "lantern",
  jar: "jar",
  seal: "seal",
  jadeSeal: "seal",
  cup: "cup",
  whip: "whip",
  shield: "shield",
};

const BACK_ITEM: Partial<Record<string, BackItem>> = {
  quivers: "quivers",
  plumes: "plumes",
  parasol: "parasol",
  zither: "zither",
  banners: "banners",
  ladder: "ladder",
};

/** Horses' coats: bay, chestnut, black, grey, dun. */
const COATS: readonly Tri[] = [
  ["#8a5a34", "#5e3b1f", "#b07a4e"],
  ["#a5552c", "#6e3519", "#cc7a4c"],
  ["#3a302c", "#1e1816", "#625650"],
  ["#a9a49a", "#77736b", "#d6d2c8"],
  ["#b98f55", "#80602f", "#dcb67c"],
];
/** 赤兔 (the novel): a red horse. */
const RED_HARE: Tri = ["#a8402a", "#6a2416", "#d0705a"];
/** Our readings: 曹操 on a dark horse, 趙雲 (once 公孫瓚's man, of the white-horse riders) on a white one. */
const OWN_COAT: Readonly<Record<string, Tri>> = {
  "cao-cao": ["#2e2622", "#171210", "#5a4e48"],
  "zhao-yun": ["#e9e6df", "#b7b2a8", "#ffffff"],
};

const BUILD: Record<Build, { scale: number; belly: number }> = {
  small: { scale: 0.88, belly: 1 },
  slight: { scale: 0.97, belly: 0.9 },
  lean: { scale: 1.02, belly: 0.92 },
  normal: { scale: 1, belly: 1 },
  broad: { scale: 1.06, belly: 1.08 },
  stout: { scale: 1.02, belly: 1.32 },
  huge: { scale: 1.24, belly: 1.4 },
};

/** How many looks each army has per class: a little variety in the ranks, few sheets. */
const TROOP_VARIANTS = 3;

function hash(s: string): number {
  let h = 2166136261;
  for (let i = 0; i < s.length; i++) {
    h ^= s.charCodeAt(i);
    h = Math.imul(h, 16777619);
  }
  return h >>> 0;
}

function weaponOf(t: Traits, fam: Family, tier2: boolean): Weapon {
  const back = t.back ? BACK_WEAPON[t.back] : undefined;
  if (back && fam !== "archer" && fam !== "catapult") return back;
  const front = t.front ? FRONT_WEAPON[t.front] : undefined;
  switch (fam) {
    case "archer":
      return t.front === "crossbow" ? "crossbow" : tier2 ? "longbow" : "bow";
    case "martialArtist":
      return "fists";
    case "catapult":
      return "none";
    case "strategist":
      return front ?? "fan";
    case "cavalry":
      return front && front !== "fan" ? front : tier2 ? "spear" : "ji";
    default:
      return front && front !== "fan" ? front : "sabre";
  }
}

function gripOf(w: Weapon, fam: Family): Grip {
  if (fam === "catapult") return "engine";
  if (w === "bow" || w === "longbow") return "bow";
  if (w === "crossbow") return "crossbow";
  if (w === "fists") return "fists";
  if (w === "twinJi") return "twin";
  if (w === "fan") return "fan";
  return POLE.has(w) ? "pole" : "one";
}

function plateOf(t: Traits, marks: ReadonlySet<Mark>): { plate: Tri; lacquer: boolean } {
  if (t.dress === "rebel" || t.dress === "bandit" || t.dress === "levy")
    return { plate: [LEATHER, LEATHER_DARK, LEATHER_LIGHT], lacquer: false };
  if (t.dress === "south") return { plate: [LACQUER, LACQUER_DARK, LACQUER_LIGHT], lacquer: true };
  if (marks.has("heavy"))
    return { plate: [BLACK_IRON, BLACK_IRON_DARK, BLACK_IRON_LIGHT], lacquer: false };
  if (marks.has("silver")) return { plate: [STEEL, STEEL_DARK, STEEL_LIGHT], lacquer: false };
  return { plate: [IRON, IRON_DARK, IRON_LIGHT], lacquer: false };
}

/** The figure for a unit: its officer's or army's look, its class's kit, its side's dye. */
export function spriteLook(
  unitId: string,
  classId: string,
  side: number,
  opts: { afoot?: boolean } = {},
): SpriteLook {
  // A soldier is painted as one of three of his army's men for his class, so
  // the ranks share sheets; a named officer is himself.
  const named = OFFICERS[unitId] !== undefined;
  const army = unitId.split("-")[0] ?? unitId;
  const canon = named
    ? unitId
    : `${army}${unitId.includes("elite") ? "-elite" : ""}-${classId}-v${hash(unitId) % TROOP_VARIANTS}`;
  const L = resolve({ id: canon, classId, side });
  const t = L.t;
  const fam = classFamily(classId);
  const tier2 = L.tier2;
  const marks = new Set(t.marks);
  const C = clothOf(L);
  const unarmoured =
    marks.has("civil") ||
    t.dress === "servant" ||
    t.dress === "steppe" ||
    t.dress === "hemp" ||
    t.dress === "imperial";
  let armour: ArmourCut = "none";
  if (!unarmoured) {
    if (fam === "infantry" || fam === "cavalry") armour = "cuirass";
    else if (fam === "catapult" || (fam === "martialArtist" && tier2)) armour = "chest";
    else if (fam === "archer") armour = tier2 ? "chest" : "vest";
  } else if (fam === "archer" && t.dress !== "steppe") armour = "vest";
  const { plate, lacquer } = plateOf(t, marks);
  const weapon = weaponOf(t, fam, tier2);
  const grip = gripOf(weapon, fam);
  const front = t.front ? FRONT_OFFHAND[t.front] : undefined;
  let offhand: Offhand = "none";
  if (grip === "one" && weapon !== "staves")
    offhand =
      fam === "infantry"
        ? tier2
          ? "bigShield"
          : "shield"
        : front && front !== "shield"
          ? front
          : "none";
  if (grip === "fan" && front) offhand = front;
  if (front === "shield" && grip === "one") offhand = tier2 ? "bigShield" : "shield";
  if (grip === "pole" && (front === "torch" || front === "whip" || front === "pennon"))
    offhand = "none";
  let back: BackItem = (t.back && BACK_ITEM[t.back]) || "none";
  if (back === "none" && (grip === "bow" || grip === "crossbow")) back = "quiver";
  const afoot = opts.afoot ?? false;
  const mounted = fam === "cavalry" && !afoot;
  const seed = hash(canon);
  const coat = mounted
    ? t.back === "horse"
      ? RED_HARE
      : (OWN_COAT[unitId] ?? (COATS[seed % COATS.length] as Tri))
    : null;
  const helmStyle = HELM_OF[t.hat];
  const helm = helmStyle ? { ...HELMS[helmStyle], style: helmStyle } : null;
  const gilt = marks.has("gilt") || helmStyle === "gilt" || (tier2 && fam !== "catapult");
  const accent = t.accent ? (ACCENTS[t.accent] as Tri) : null;
  const B = BUILD[t.build];
  const collar =
    t.dress === "frontier"
      ? "fleece"
      : t.dress === "bing" || t.dress === "steppe" || marks.has("fur")
        ? "fur"
        : null;
  const look: Omit<SpriteLook, "key"> = {
    fam,
    tier2,
    named: L.named,
    side: L.side,
    afoot,
    mounted,
    P: L.P,
    robe: [C.robe, C.dark, C.light],
    edge: [C.edge, C.edgeDark, C.edge],
    coarse: C.coarse,
    accent,
    skin: L.skin,
    hair: L.hair,
    age: t.age,
    build: t.build,
    scale: B.scale,
    shoulders: SHOULDERS[t.build],
    belly: B.belly,
    face: t.face,
    faceW: t.w / 100,
    eye: t.eye,
    brow: t.brow,
    beard: t.beard,
    hat: t.hat,
    helm,
    armour,
    plate,
    lacquer,
    gilt,
    collar,
    weapon,
    grip,
    offhand,
    back,
    coat,
    barded: mounted && tier2,
    marks,
    seed: seed % 997,
  };
  const keyed = { ...look, marks: [...marks].sort().join(",") };
  return { ...look, key: JSON.stringify(keyed) };
}

/** Gates and other works are not figures (ADR 0069). */
export function isFigure(unitId: string, classId: string): boolean {
  return !isWork({ id: unitId, classId });
}
