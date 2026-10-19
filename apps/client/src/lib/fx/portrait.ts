/**
 * Our own officer portraits: painted head-and-shoulders busts, drawn in code
 * on a 2D canvas in the style of the rigged figures (ADR 0045): flat cel
 * fills with one shadow and one highlight, ink outlines, light from the upper
 * right, a soft rim light, and the side's Han dye in the dress and backdrop.
 * Dress follows the late Eastern Han (ADR 0046, docs/remake/CHARACTER_LOOK.md).
 *
 * Named officers get their own face from the trait table below; any other id
 * gets a soldier's face from a hash of the id. The class drives the outfit and
 * headwear, the side drives the colours, the mood drives brows, eyes and mouth.
 */

export type Mood = "calm" | "smile" | "angry" | "grim" | "hurt";
export interface PortraitSpec {
  /** Officer id; any other id (a troop's) gets a soldier's face from its hash. */
  id: string;
  /** A class id: the six base classes, the tier-2 promotions, or a guess from the name. */
  classId?: string | undefined;
  /** Side index into the four Han dyes; defaults to the officer's own side. */
  side?: number | undefined;
  mood?: Mood | undefined;
}
export type Family =
  | "infantry"
  | "archer"
  | "cavalry"
  | "strategist"
  | "martialArtist"
  | "catapult";

export const MOODS: readonly Mood[] = ["calm", "smile", "angry", "grim", "hurt"];
export const FAMILIES: readonly Family[] = [
  "infantry",
  "archer",
  "cavalry",
  "strategist",
  "martialArtist",
  "catapult",
];

/** Internal size of a painted portrait (4:5); CSS scales it. */
export const PORTRAIT_W = 256;
export const PORTRAIT_H = 320;

// ---------------------------------------------------------------- palette

const INK = "#2a1a12";
const LW = 3;
const LW2 = 2;
const IRON = "#767b84";
const IRON_DARK = "#4c5058";
const IRON_LIGHT = "#a9aeb6";
const STEEL = "#9aa3ae";
const STEEL_DARK = "#66707c";
const STEEL_LIGHT = "#d3d9e0";
const BRONZE = "#c0913e";
const BRONZE_DARK = "#7f5c22";
const BRONZE_LIGHT = "#e8c47a";
const GOLD = "#d9a936";
const GOLD_DARK = "#8f6519";
const GOLD_LIGHT = "#f7dd8a";
const LEATHER = "#6b4529";
const LEATHER_DARK = "#4a2e1b";
const LEATHER_LIGHT = "#8d6440";
const HEMP = "#b8a37a";
const HEMP_DARK = "#8f7c56";
const HEMP_LIGHT = "#d3c39c";
const SILK_CAP = "#26304a";
const SILK_DARK = "#171d2e";
const SILK_LIGHT = "#4a5878";
const GAUZE = "#1f1d22";
const GAUZE_LIGHT = "#45424c";
const LACQUER = "#2b2421";
const LACQUER_DARK = "#171210";
const LACQUER_LIGHT = "#6a5a50";
const LACQUER_RED = "#a33328";
const FEATHER = "#8a6a44";
const FEATHER_DARK = "#5e452b";
const WHITE_CLOTH = "#f1ead9";
const WHITE_SHADE = "#cdc3ad";
const SCLERA = "#f8f2e7";
const MOUTH = "#5c2320";
const TEETH = "#f6f0e2";
const BLOOD = "#b3262a";
const SWEAT = "#cfe8f6";
const WOOD = "#7a5234";
const WOOD_DARK = "#54371f";
const BLADE = "#d9e0e7";
const BLADE_DARK = "#8f9aa6";
const GREEN = "#3f6b46";
const GREEN_DARK = "#2a4a30";
const GREEN_LIGHT = "#5f9a68";
/** The Yellow Turbans' head-cloth (黃巾): an ochre yellow, darker in the folds. */
const YELLOW = "#d8b23a";
const YELLOW_DARK = "#a07c22";
const YELLOW_LIGHT = "#f3da84";
const YELLOW_FOLD = "#7a5a14";
/** A bandit's rough head-wrap. */
const RAG = "#5c4431";
const RAG_DARK = "#3b2a1d";
const RAG_LIGHT = "#86684c";
/** Sun Jian's red cap (赤幘). */
const RED_CAP = "#b5382b";
const RED_CAP_DARK = "#74201a";
const RED_CAP_LIGHT = "#de6c5c";
/** A household servant's dark cap (蒼頭). */
const SERVANT_CAP = "#3a463f";
/** Undyed drab for bandits' clothes. */
const DRAB = "#7b6a50";
const DRAB_DARK = "#54462f";
const DRAB_LIGHT = "#9c8a6c";
/** Sheepskin, for the Liang frontier's fur collars. */
const FLEECE = "#cdb88f";
const FLEECE_DARK = "#97815a";
const FLEECE_LIGHT = "#efe2c2";
const BAMBOO = "#b9a052";
const BAMBOO_DARK = "#7c6a2f";
const BAMBOO_LIGHT = "#e2cf8c";

/** Side colours, as Han dyes (the same as the rigged figures): ours red, the enemy blue (ADR 0055). */
interface Pal {
  robe: string;
  robeDark: string;
  edge: string;
  lace: string;
  tassel: string;
}

const SIDES: Pal[] = [
  { robe: "#9b2f28", robeDark: "#6b1d18", edge: "#dccaa0", lace: "#d65a4c", tassel: "#d8392c" },
  { robe: "#2e4a78", robeDark: "#1f3354", edge: "#dccaa0", lace: "#7096d4", tassel: "#3f74d6" },
  { robe: "#3f6b46", robeDark: "#2a4a30", edge: "#dccaa0", lace: "#72b07b", tassel: "#43a85a" },
  { robe: "#a2772c", robeDark: "#6e501b", edge: "#efe3c0", lace: "#d8aa4c", tassel: "#e2b23c" },
];

/** Base, shadow, highlight. Fair and warm match the rigged figures' skin. */
const SKINS: Record<SkinTone, readonly [string, string, string]> = {
  fair: ["#f3cfab", "#dca67e", "#fde6cc"],
  warm: ["#e9c09a", "#cb9269", "#f7d8b6"],
  tan: ["#d8a67b", "#b37b52", "#ebc29c"],
  ruddy: ["#c97750", "#9c4e33", "#df9670"],
  pale: ["#f6dfc8", "#dcb89b", "#fff1e2"],
};

/** Base, shadow, sheen. */
const HAIRS: Record<HairTone, readonly [string, string, string]> = {
  black: ["#1e1816", "#100c0b", "#4a4250"],
  salt: ["#4a4542", "#2a2624", "#8e8984"],
  grey: ["#8d8780", "#625d58", "#c4beb6"],
  white: ["#e4dfd6", "#b5aea3", "#fbf8f2"],
};

// ---------------------------------------------------------------- traits

const EYE_KINDS = [
  "almond",
  "narrow",
  "phoenix",
  "round",
  "gentle",
  "heavy",
  "small",
  "fierce",
] as const;
const BEARDS = [
  "none",
  "stubble",
  "goatee",
  "short",
  "full",
  "long",
  "flowing",
  "wild",
  "whiskers",
  "wisp",
] as const;
const STACHES = ["none", "thin", "neat", "full", "droop", "bristle", "long"] as const;
const NOSES = ["straight", "broad", "hook", "small"] as const;
const HATS = [
  "helm",
  "ze",
  "lunjin",
  "court",
  "scarf",
  "knot",
  "crown",
  "rake",
  "huangjin",
  "wrap",
  "redze",
] as const;
/**
 * The dress of an army: Han regulars, Yellow Turban rebels in hemp and patched
 * leather, bandits in drab rags, a household's servants, the Liang frontier's
 * heavy armour and fleece.
 */
const DRESSES = ["han", "rebel", "bandit", "servant", "frontier"] as const;
const MARKS = [
  "patch",
  "ears",
  "rednose",
  "bells",
  "scar",
  "thickNeck",
  "smirk",
  "frown",
  "silver",
  "feathers",
  "lines",
  "bushy",
  "court",
  "gilt",
  "band",
  "jade",
  "civil",
  "brocade",
  "jowls",
  "scars",
  "staff",
  "club",
  "staves",
] as const;
const HAIR_TONES = ["black", "salt", "grey", "white"] as const;
const SKIN_TONES = ["fair", "warm", "tan", "ruddy", "pale"] as const;

type EyeKind = (typeof EYE_KINDS)[number];
type Beard = (typeof BEARDS)[number];
type Stache = (typeof STACHES)[number];
type Nose = (typeof NOSES)[number];
type Hat = (typeof HATS)[number];
type Mark = (typeof MARKS)[number];
type Dress = (typeof DRESSES)[number];
type HairTone = (typeof HAIR_TONES)[number];
type SkinTone = (typeof SKIN_TONES)[number];

interface Traits {
  cls: Family;
  /** The officer's own side, used when the caller gives none. */
  home: number;
  /** 0 youth, 1 grown, 2 mature, 3 old. */
  age: number;
  /** 0 slight, 1 normal, 2 big, 3 huge. */
  build: number;
  /** Face width and lower-face length (1 = normal). */
  w: number;
  len: number;
  /** 0..1: narrow to square jaw, pointed to broad chin, flat to high cheekbones. */
  jaw: number;
  chin: number;
  cheek: number;
  eye: EyeKind;
  eyeSize: number;
  /** Brow thickness, arch and tilt (outer end up), in px. */
  brow: number;
  arch: number;
  tilt: number;
  nose: Nose;
  mouth: number;
  stache: Stache;
  beard: Beard;
  hair: HairTone;
  skin: SkinTone;
  /** Headwear the officer always wears, whatever the class. */
  hat?: Hat;
  /** The officer's helmet, when the class wears one. */
  helm?: HelmStyle;
  /** The army's dress: Han regulars unless said otherwise. */
  dress: Dress;
  /**
   * Extras: bushy brows, the court cap (進賢冠) rather than the silk headcloth
   * when in robes, a lord's gold trim, and personal marks. `civil` wears no
   * armour whatever the class; `brocade` is a figured silk robe; `staff`,
   * `club` and `staves` are carried in front (a Taoist's bamboo staff, a
   * cudgel, the northern district's five-coloured staves).
   */
  marks: readonly Mark[];
}

const BASE: Traits = {
  cls: "infantry",
  home: 0,
  age: 1,
  build: 1,
  w: 1,
  len: 1,
  jaw: 0.5,
  chin: 0.5,
  cheek: 0.5,
  eye: "almond",
  eyeSize: 1,
  brow: 6,
  arch: 2,
  tilt: 2,
  nose: "straight",
  mouth: 24,
  stache: "neat",
  beard: "short",
  hair: "black",
  skin: "warm",
  dress: "han",
  marks: [],
};

type Extra = Mark | `hat:${Hat}` | `helm:${HelmStyle}` | `dress:${Dress}`;

function o(
  cls: Family,
  home: number,
  age: number,
  build: number,
  face: readonly [number, number, number, number, number],
  eye: EyeKind,
  eyeSize: number,
  brow: readonly [number, number, number],
  nose: Nose,
  mouth: number,
  stache: Stache,
  beard: Beard,
  hair: HairTone,
  skin: SkinTone,
  extras: readonly Extra[],
): Traits {
  const [w, len, jaw, chin, cheek] = face;
  const [thick, arch, tilt] = brow;
  const t: Traits = {
    cls,
    home,
    age,
    build,
    w,
    len,
    jaw,
    chin,
    cheek,
    eye,
    eyeSize,
    brow: thick,
    arch,
    tilt,
    nose,
    mouth,
    stache,
    beard,
    hair,
    skin,
    dress: "han",
    marks: extras.filter((x): x is Mark => !x.includes(":")),
  };
  const hat = extras.find((x) => x.startsWith("hat:"));
  if (hat) t.hat = hat.slice(4) as Hat;
  const helm = extras.find((x) => x.startsWith("helm:"));
  if (helm) t.helm = helm.slice(5) as HelmStyle;
  const dress = extras.find((x) => x.startsWith("dress:"));
  if (dress) t.dress = dress.slice(6) as Dress;
  return t;
}

/**
 * Named officers: historical people (public domain), drawn our own way from
 * well-known cues in the histories and the novel. Columns: class, home side,
 * age (0 youth to 3 old), build (0 slight to 3 huge); face width, lower-face
 * length, jaw, chin, cheekbones; eyes and their size; brow thickness, arch and
 * tilt; nose, mouth width; moustache, beard; hair, skin; extras (`hat:<kind>`
 * for headwear worn whatever the class, `helm:<style>`, `dress:<army>`, and
 * marks). Home side 0 is ours (red), 1 the enemy (blue) (ADR 0055).
 */
// biome-ignore format: one officer per line reads as a table
const OFFICERS: Record<string, Traits> = {
  "cao-cao":      o("cavalry",       0, 2, 1, [0.95, 1.02, 0.40, 0.35, 0.70], "narrow",  1.00, [ 5, 1,  5], "straight", 24, "neat",    "goatee",   "black", "warm",  ["gilt", "lines"]),
  "xiahou-dun":   o("infantry",      0, 2, 2, [1.05, 1.00, 0.75, 0.60, 0.50], "fierce",  1.00, [ 8, 2,  4], "straight", 24, "full",    "full",     "black", "tan",   ["bushy", "patch", "helm:banded"]),
  "xiahou-yuan":  o("archer",        0, 2, 1, [0.93, 1.06, 0.35, 0.30, 0.80], "narrow",  1.00, [ 5, 2,  3], "straight", 24, "droop",   "none",     "black", "tan",   []),
  "xu-chu":       o("martialArtist", 0, 1, 3, [1.18, 0.95, 1.00, 0.85, 0.30], "small",   0.95, [ 9, 1,  0], "broad",    28, "full",    "short",    "black", "tan",   ["bushy", "thickNeck"]),
  "zhang-liao":   o("cavalry",       0, 1, 1, [1.00, 1.05, 0.60, 0.45, 0.60], "almond",  1.00, [ 7, 0,  4], "straight", 24, "neat",    "full",     "black", "warm",  ["frown", "helm:lamellar"]),
  "xu-huang":     o("infantry",      0, 2, 2, [1.07, 0.98, 0.95, 0.80, 0.50], "round",   0.90, [ 8, 0,  1], "broad",    24, "full",    "full",     "black", "tan",   ["helm:lacquer"]),
  "yue-jin":      o("infantry",      0, 1, 0, [0.90, 0.93, 0.35, 0.30, 0.70], "fierce",  1.08, [ 7, 2,  6], "straight", 21, "bristle", "stubble",  "black", "warm",  ["helm:lamellar"]),
  "li-dian":      o("infantry",      0, 1, 1, [1.00, 1.00, 0.40, 0.45, 0.50], "gentle",  1.00, [ 4, 3,  1], "straight", 24, "neat",    "short",    "black", "fair",  ["helm:banded"]),
  "yu-jin":       o("infantry",      0, 2, 1, [1.04, 0.98, 0.90, 0.75, 0.50], "narrow",  1.00, [ 8, 0,  0], "straight", 20, "neat",    "short",    "black", "warm",  ["frown", "lines", "helm:lacquer"]),
  "xun-you":      o("strategist",    0, 2, 1, [0.95, 1.08, 0.40, 0.30, 0.50], "heavy",   1.00, [ 4, 2, -2], "straight", 24, "thin",    "long",     "salt",  "fair",  ["lines"]),
  "guo-jia":      o("strategist",    0, 0, 1, [0.93, 1.00, 0.30, 0.25, 0.50], "narrow",  1.00, [ 4, 3,  3], "straight", 24, "thin",    "none",     "black", "pale",  ["smirk"]),
  "liu-ye":       o("catapult",      0, 1, 1, [1.00, 1.00, 0.50, 0.50, 0.50], "round",   0.95, [ 5, 3,  1], "straight", 24, "neat",    "goatee",   "black", "warm",  []),
  "xun-yu":       o("strategist",    0, 1, 1, [0.97, 1.03, 0.45, 0.40, 0.50], "almond",  1.00, [ 5, 2,  2], "straight", 24, "thin",    "goatee",   "black", "fair",  ["court"]),
  "cao-ren":      o("infantry",      0, 1, 2, [1.08, 0.97, 0.80, 0.65, 0.50], "round",   1.00, [ 8, 1,  2], "broad",    24, "full",    "short",    "black", "warm",  ["helm:banded"]),
  "guan-yu":      o("cavalry",       0, 2, 2, [1.00, 1.08, 0.60, 0.50, 0.60], "phoenix", 1.05, [ 8, 4,  5], "straight", 24, "long",    "flowing",  "black", "ruddy", ["hat:scarf"]),
  "zhang-he":     o("cavalry",       0, 1, 1, [0.98, 1.00, 0.45, 0.40, 0.50], "almond",  1.00, [ 5, 1,  5], "straight", 24, "thin",    "none",     "black", "fair",  ["helm:lacquer"]),
  "gao-lan":      o("infantry",      0, 2, 1, [1.04, 1.00, 0.80, 0.70, 0.85], "small",   1.00, [ 8, 0,  1], "broad",    24, "bristle", "stubble",  "black", "tan",   ["bushy", "scar", "helm:banded"]),
  "xu-you":       o("strategist",    0, 3, 1, [0.92, 1.05, 0.25, 0.15, 0.80], "narrow",  1.00, [ 4, 4,  2], "hook",     24, "droop",   "goatee",   "salt",  "warm",  ["smirk", "lines"]),
  "yuan-shao":    o("strategist",    1, 2, 2, [1.02, 1.04, 0.60, 0.50, 0.50], "almond",  1.05, [ 7, 1,  2], "straight", 24, "full",    "full",     "black", "fair",  ["court", "gilt"]),
  "yan-liang":    o("cavalry",       1, 2, 2, [1.10, 1.00, 0.85, 0.70, 0.50], "fierce",  1.00, [ 9, 2,  5], "broad",    28, "full",    "full",     "black", "tan",   ["bushy", "helm:lacquer"]),
  "wen-chou":     o("cavalry",       1, 2, 2, [1.06, 1.00, 0.75, 0.60, 0.70], "fierce",  0.95, [ 8, 2,  6], "straight", 24, "bristle", "wild",     "black", "tan",   ["bushy", "scar", "helm:banded"]),
  "chunyu-qiong": o("infantry",      1, 3, 2, [1.07, 0.96, 0.55, 0.60, 0.20], "heavy",   1.00, [ 6, 3, -3], "broad",    24, "droop",   "full",     "grey",  "warm",  ["bushy", "rednose", "lines", "helm:lamellar"]),
  "ju-shou":      o("strategist",    1, 2, 1, [0.95, 1.08, 0.45, 0.35, 0.80], "heavy",   1.00, [ 7, 0,  0], "straight", 24, "neat",    "long",     "salt",  "warm",  ["court", "frown"]),
  "tian-feng":    o("strategist",    1, 3, 1, [0.96, 1.05, 0.55, 0.45, 0.70], "narrow",  1.00, [ 8, 0, -1], "straight", 24, "full",    "long",     "grey",  "warm",  ["bushy", "court", "frown", "lines"]),
  "shen-pei":     o("strategist",    1, 2, 1, [1.02, 1.00, 0.70, 0.60, 0.50], "narrow",  1.00, [ 7, 1,  1], "straight", 24, "neat",    "full",     "black", "warm",  ["court", "frown"]),
  "guo-tu":       o("strategist",    1, 2, 1, [0.92, 1.07, 0.25, 0.20, 0.75], "narrow",  1.00, [ 4, 4,  4], "hook",     24, "droop",   "goatee",   "black", "warm",  ["smirk"]),
  "feng-ji":      o("strategist",    1, 2, 1, [1.05, 0.95, 0.50, 0.55, 0.30], "small",   1.00, [ 4, 3,  3], "straight", 24, "thin",    "goatee",   "black", "fair",  ["court", "smirk"]),
  "yuan-tan":     o("cavalry",       1, 0, 1, [0.98, 0.98, 0.40, 0.35, 0.50], "almond",  1.06, [ 6, 1,  2], "straight", 24, "none",    "none",     "black", "fair",  ["gilt"]),
  "han-meng":     o("infantry",      1, 2, 1, [1.05, 1.00, 0.85, 0.70, 0.75], "small",   1.00, [ 8, 0,  2], "broad",    24, "bristle", "short",    "black", "tan",   ["bushy", "scar", "helm:lacquer"]),
  "jiang-qi":     o("infantry",      1, 1, 1, [1.00, 1.03, 0.60, 0.50, 0.50], "almond",  1.00, [ 7, 1,  4], "straight", 24, "neat",    "full",     "black", "warm",  ["helm:banded"]),
  "qu-yi":        o("archer",        1, 2, 1, [0.98, 1.02, 0.65, 0.55, 0.85], "narrow",  1.00, [ 7, 1,  1], "straight", 24, "full",    "short",    "salt",  "tan",   ["scar", "lines"]),
  "liu-bei":      o("infantry",      1, 2, 1, [1.00, 1.00, 0.45, 0.50, 0.50], "gentle",  1.04, [ 5, 3, -1], "straight", 24, "neat",    "short",    "black", "fair",  ["ears"]),
  "liu-yan":      o("strategist",    0, 2, 1, [1.01, 1.00, 0.55, 0.50, 0.50], "heavy",   1.00, [ 6, 2,  0], "straight", 24, "neat",    "short",    "black", "warm",  ["court", "lines"]),
  "lu-bu":        o("cavalry",       3, 1, 2, [1.00, 1.04, 0.60, 0.45, 0.70], "fierce",  1.00, [ 8, 1,  7], "straight", 24, "none",    "none",     "black", "fair",  ["hat:crown", "feathers", "frown"]),
  "zhao-yun":     o("cavalry",       2, 0, 1, [0.97, 1.00, 0.40, 0.35, 0.50], "round",   1.02, [ 6, 1,  3], "straight", 24, "none",    "none",     "black", "fair",  ["silver"]),
  "zhang-fei":    o("infantry",      2, 1, 2, [1.12, 0.95, 0.85, 0.70, 0.40], "round",   1.12, [10, 2,  6], "broad",    28, "bristle", "whiskers", "black", "tan",   ["bushy", "helm:lacquer"]),
  "gan-ning":     o("infantry",      3, 1, 1, [0.97, 1.02, 0.55, 0.40, 0.75], "narrow",  1.00, [ 5, 1,  6], "straight", 24, "thin",    "stubble",  "black", "tan",   ["hat:rake", "bells", "smirk"]),
  "huang-zhong":  o("archer",        2, 3, 1, [1.00, 1.05, 0.55, 0.45, 0.70], "heavy",   1.00, [ 7, 2, -4], "straight", 24, "long",    "long",     "white", "warm",  ["bushy", "lines"]),
  "taishi-ci":    o("archer",        3, 1, 1, [1.00, 1.03, 0.65, 0.50, 0.50], "almond",  1.00, [ 7, 0,  3], "straight", 24, "neat",    "short",    "black", "warm",  []),
  "zhuge-liang":  o("strategist",    2, 1, 1, [0.96, 1.05, 0.40, 0.30, 0.50], "gentle",  1.00, [ 4, 2,  2], "straight", 24, "thin",    "goatee",   "black", "fair",  ["hat:lunjin"]),
  "zhou-yu":      o("strategist",    3, 0, 1, [0.96, 1.00, 0.42, 0.38, 0.50], "almond",  1.04, [ 5, 2,  3], "straight", 24, "none",    "none",     "black", "fair",  ["hat:crown", "jade"]),
  "dian-wei":     o("martialArtist", 0, 1, 3, [1.16, 1.02, 1.00, 0.90, 0.90], "fierce",  0.92, [10, 0,  3], "broad",    27, "bristle", "wild",     "black", "tan",   ["bushy", "thickNeck", "scar", "frown", "band"]),
  "ma-jun":       o("catapult",      0, 1, 1, [1.06, 0.94, 0.40, 0.55, 0.30], "round",   1.00, [ 4, 3,  0], "straight", 24, "none",    "stubble",   "black", "fair",  []),
  // Story chapter 1 (174–192): Cao Cao's people and the Han generals.
  "cao-hong":     o("cavalry",       0, 1, 2, [1.06, 0.98, 0.75, 0.60, 0.45], "round",   1.00, [ 7, 1,  3], "broad",    25, "neat",    "short",    "black", "warm",  ["helm:lamellar"]),
  "chen-gong":    o("strategist",    0, 2, 1, [0.94, 1.07, 0.50, 0.35, 0.80], "narrow",  1.00, [ 6, 0,  3], "straight", 22, "thin",    "goatee",   "black", "fair",  ["court", "frown"]),
  "bao-xin":      o("infantry",      0, 2, 1, [1.02, 1.02, 0.65, 0.55, 0.55], "almond",  1.00, [ 7, 1,  2], "straight", 24, "full",    "full",     "black", "warm",  ["lines", "bushy", "helm:banded"]),
  "wei-zi":       o("strategist",    0, 3, 1, [1.03, 0.99, 0.50, 0.55, 0.35], "gentle",  1.00, [ 5, 3, -2], "small",    24, "droop",   "long",     "salt",  "warm",  ["lines", "brocade", "hat:ze"]),
  "huangfu-song": o("cavalry",       0, 3, 1, [1.00, 1.06, 0.60, 0.50, 0.65], "heavy",   1.00, [ 7, 2, -2], "straight", 24, "long",    "long",     "grey",  "warm",  ["bushy", "lines", "gilt", "helm:lamellar"]),
  "zhu-jun":      o("infantry",      0, 2, 1, [1.00, 1.00, 0.70, 0.60, 0.60], "narrow",  1.00, [ 7, 0,  2], "straight", 22, "neat",    "full",     "black", "tan",   ["frown", "helm:lacquer"]),
  "sun-jian":     o("infantry",      0, 1, 1, [1.00, 1.00, 0.60, 0.45, 0.70], "fierce",  1.04, [ 7, 1,  6], "straight", 24, "full",    "stubble",  "black", "tan",   ["hat:redze"]),
  "xu-shao":      o("strategist",    0, 2, 0, [0.94, 1.06, 0.40, 0.30, 0.55], "gentle",  1.00, [ 4, 3,  1], "straight", 23, "thin",    "wisp",     "black", "fair",  ["court", "smirk"]),
  // Story chapter 1: the Jian household, the Yellow Turbans, Dong Zhuo's army, the Black Mountain.
  "jian-tu":      o("infantry",      1, 2, 2, [1.12, 0.98, 0.55, 0.70, 0.20], "small",   0.95, [ 5, 3,  4], "broad",    22, "thin",    "goatee",   "black", "fair",  ["civil", "brocade", "gilt", "jowls", "smirk", "hat:ze"]),
  "zhang-jiao":   o("strategist",    1, 2, 1, [0.94, 1.08, 0.40, 0.30, 0.80], "fierce",  1.08, [ 6, 2,  5], "straight", 22, "long",    "long",     "salt",  "warm",  ["staff", "lines", "hat:huangjin"]),
  "bo-cai":       o("infantry",      1, 2, 2, [1.06, 1.00, 0.80, 0.65, 0.70], "fierce",  1.00, [ 8, 1,  5], "broad",    25, "bristle", "wild",     "black", "tan",   ["bushy", "hat:huangjin", "dress:rebel"]),
  "dong-zhuo":    o("cavalry",       1, 2, 3, [1.20, 1.00, 0.90, 0.85, 0.30], "small",   0.95, [ 8, 1,  4], "broad",    27, "full",    "full",     "black", "ruddy", ["bushy", "jowls", "smirk", "gilt", "helm:lamellar", "dress:frontier"]),
  "hua-xiong":    o("infantry",      1, 1, 3, [1.14, 1.00, 0.95, 0.80, 0.60], "fierce",  0.96, [10, 1,  6], "broad",    27, "bristle", "full",     "black", "tan",   ["bushy", "thickNeck", "frown", "helm:crested", "dress:frontier"]),
  "xu-rong":      o("cavalry",       1, 2, 1, [0.98, 1.04, 0.55, 0.45, 0.65], "narrow",  1.00, [ 6, 0,  2], "straight", 22, "neat",    "short",    "black", "warm",  ["frown", "helm:lacquer"]),
  "bai-rao":      o("infantry",      1, 1, 2, [1.04, 1.00, 0.80, 0.60, 0.85], "fierce",  0.95, [ 8, 0,  5], "hook",     25, "droop",   "stubble",  "black", "tan",   ["bushy", "scars", "hat:wrap", "dress:bandit"]),
};

function has(t: Traits, m: Mark): boolean {
  return t.marks.includes(m);
}

export const PORTRAIT_OFFICERS: readonly string[] = Object.keys(OFFICERS);

// ---------------------------------------------------------------- class and id

const TIER2: Record<string, Family> = {
  heavyInfantry: "infantry",
  longbow: "archer",
  shockCavalry: "cavalry",
  tactician: "strategist",
  guardian: "martialArtist",
  thunderCart: "catapult",
};

const FAMILY_HINTS: readonly [RegExp, Family][] = [
  [/cav|horse|rider|lancer|knight|charger/, "cavalry"],
  [/arch|bow|shoot|marks|crossbow|sniper/, "archer"],
  [/strat|tact|sage|advis|schol|taoist|mage|shaman|bishop|scribe/, "strategist"],
  [/martial|brawl|fist|monk|wrestl|fighter|guard/, "martialArtist"],
  [/cata|siege|engine|treb|ballist|mangon|thrower|thunder|cart/, "catapult"],
  [/inf|foot|spear|pike|sword|halberd|soldier/, "infantry"],
];

/** The family of a class id: a base class, a tier-2 promotion, or a guess from the name. */
export function classFamily(classId: string): Family {
  if ((FAMILIES as readonly string[]).includes(classId)) return classId as Family;
  const known = TIER2[classId];
  if (known) return known;
  const id = classId.toLowerCase();
  for (const [re, fam] of FAMILY_HINTS) if (re.test(id)) return fam;
  return "infantry";
}

function isTier2(classId: string | undefined): boolean {
  return classId !== undefined && !(FAMILIES as readonly string[]).includes(classId);
}

function hash(s: string): number {
  let h = 2166136261;
  for (let i = 0; i < s.length; i++) {
    h ^= s.charCodeAt(i);
    h = Math.imul(h, 16777619);
  }
  return h >>> 0;
}

/** A small seeded generator (mulberry32), so a face is the same every time. */
function rng(seed: number): () => number {
  let a = seed >>> 0;
  return () => {
    a = (a + 0x6d2b79f5) >>> 0;
    let t = a;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

function pick<T>(r: () => number, xs: readonly T[]): T {
  return xs[Math.floor(r() * xs.length) % xs.length] as T;
}

/** An army's side, dress and what every one of its soldiers wears or carries. */
interface Army {
  home: number;
  dress: Dress;
  hat?: Hat;
  marks?: readonly Mark[];
}

/**
 * Story troops are named after their army (`huangjin-infantry-2`): cao-… and
 * ward-… (the Luoyang northern district's night watch, with Cao Cao's
 * five-coloured staves) are ours; yuan-…, huangjin-… and qingzhou-… (Yellow
 * Turbans in their yellow head-cloths), dong-… (Dong Zhuo's Liang troops),
 * heishan-… (Black Mountain bandits) and jianjia-… (the Jian household's
 * servants, in dark caps with cudgels) are the enemy's.
 */
const ARMIES = new Map<string, Army>([
  ["cao", { home: 0, dress: "han" }],
  ["ward", { home: 0, dress: "han", marks: ["staves"] }],
  ["yuan", { home: 1, dress: "han" }],
  ["huangjin", { home: 1, dress: "rebel", hat: "huangjin" }],
  ["qingzhou", { home: 1, dress: "rebel", hat: "huangjin" }],
  ["dong", { home: 1, dress: "frontier" }],
  ["heishan", { home: 1, dress: "bandit", hat: "wrap" }],
  ["jianjia", { home: 1, dress: "servant", hat: "ze", marks: ["club"] }],
]);

/** A soldier's face from the hash of the id: helmeted or capped by class, varied face and beard. */
function generic(id: string): Traits {
  const r = rng(hash(id));
  const lower = id.toLowerCase();
  let cls: Family = "infantry";
  for (const [re, fam] of FAMILY_HINTS)
    if (re.test(lower)) {
      cls = fam;
      break;
    }
  const army = ARMIES.get(lower.split("-")[0] ?? "");
  const home = army?.home ?? 0;
  const age = pick(r, [0, 1, 1, 1, 2, 2, 3]);
  const beard: Beard =
    age === 0
      ? pick(r, ["none", "none", "stubble", "goatee"])
      : pick(r, ["stubble", "goatee", "short", "short", "full", "none", "long"]);
  const stache: Stache =
    beard === "none" && age === 0
      ? pick(r, ["none", "none", "thin"])
      : pick(r, ["thin", "neat", "neat", "full", "droop", "bristle"]);
  const marks: Mark[] = [];
  if (r() < 0.18) marks.push("scar");
  if (age >= 2) marks.push("lines");
  if (r() < 0.2) marks.push("frown");
  if (r() < 0.3) marks.push("bushy");
  const t: Traits = {
    ...BASE,
    cls,
    home,
    age,
    build: pick(r, [0, 1, 1, 1, 2]),
    w: 0.93 + r() * 0.14,
    len: 0.94 + r() * 0.12,
    jaw: 0.25 + r() * 0.7,
    chin: 0.25 + r() * 0.5,
    cheek: 0.2 + r() * 0.7,
    eye: pick(r, ["almond", "narrow", "round", "small", "heavy", "fierce", "gentle"]),
    eyeSize: 0.94 + r() * 0.1,
    brow: 4 + Math.floor(r() * 5),
    arch: Math.floor(r() * 4),
    tilt: Math.floor(r() * 7) - 1,
    nose: pick(r, ["straight", "straight", "broad", "small", "hook"]),
    mouth: 21 + Math.floor(r() * 6),
    beard,
    stache,
    hair: age === 3 ? pick(r, ["salt", "grey"]) : age === 2 && r() < 0.3 ? "salt" : "black",
    skin: pick(r, ["fair", "warm", "warm", "tan"]),
    marks,
  };
  if (!army) return t;
  // The army's dress, drawn after the face so the Cao and Yuan faces stay as they were.
  t.dress = army.dress;
  if (army.hat) t.hat = army.hat;
  marks.push(...(army.marks ?? []));
  if (army.dress === "rebel" || army.dress === "bandit") {
    // Peasants and outlaws: weathered, rougher beards, more scars.
    t.skin = pick(r, ["warm", "tan", "tan", "ruddy"]);
    if (age > 0) t.beard = pick(r, ["stubble", "stubble", "short", "wild", "full", "goatee"]);
    if (r() < (army.dress === "bandit" ? 0.5 : 0.25)) marks.push("scar");
  }
  if (army.dress === "frontier") {
    // Liang province men: hard-bitten, weathered by the north-west wind.
    t.skin = pick(r, ["tan", "tan", "ruddy", "warm"]);
    t.build = Math.max(t.build, 1);
  }
  return t;
}

// ---------------------------------------------------------------- the look

interface Look {
  t: Traits;
  fam: Family;
  tier2: boolean;
  named: boolean;
  P: Pal;
  mood: Mood;
  hat: Hat;
  seed: number;
  skin: readonly [string, string, string];
  hair: readonly [string, string, string];
}

function hatFor(t: Traits, fam: Family, tier2: boolean): Hat {
  if (t.hat) return t.hat;
  switch (fam) {
    case "infantry":
    case "cavalry":
      return "helm";
    case "archer":
    case "catapult":
      return "ze";
    case "strategist":
      return has(t, "court") || tier2 ? "court" : "lunjin";
    case "martialArtist":
      return "knot";
  }
}

function resolve(spec: PortraitSpec): Look {
  const entry = OFFICERS[spec.id];
  const t: Traits = entry ?? generic(spec.id);
  const fam = spec.classId ? classFamily(spec.classId) : t.cls;
  const tier2 = isTier2(spec.classId) || (!entry && spec.id.includes("elite"));
  const side = (((spec.side ?? t.home) % SIDES.length) + SIDES.length) % SIDES.length;
  return {
    t,
    fam,
    tier2,
    named: entry !== undefined,
    P: SIDES[side] ?? (SIDES[0] as Pal),
    mood: spec.mood ?? "calm",
    hat: hatFor(t, fam, tier2),
    seed: hash(spec.id),
    skin: SKINS[t.skin],
    hair: HAIRS[t.hair],
  };
}

// ---------------------------------------------------------------- drawing helpers

type G = CanvasRenderingContext2D;

function path(draw: (p: Path2D) => void): Path2D {
  const p = new Path2D();
  draw(p);
  return p;
}

function poly(...pts: number[]): Path2D {
  return path((p) => {
    p.moveTo(pts[0] ?? 0, pts[1] ?? 0);
    for (let i = 2; i < pts.length; i += 2) p.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
    p.closePath();
  });
}

function ellipse(x: number, y: number, rx: number, ry: number, rot = 0): Path2D {
  return path((p) => p.ellipse(x, y, rx, ry, rot, 0, Math.PI * 2));
}

/** A smooth closed curve near the points (quadratic curves through the midpoints). */
function blob(pts: readonly (readonly [number, number])[]): Path2D {
  return path((p) => {
    const n = pts.length;
    const at = (i: number) => pts[((i % n) + n) % n] as readonly [number, number];
    const mid = (i: number): [number, number] => {
      const a = at(i);
      const b = at(i + 1);
      return [(a[0] + b[0]) / 2, (a[1] + b[1]) / 2];
    };
    const m0 = mid(n - 1);
    p.moveTo(m0[0], m0[1]);
    for (let i = 0; i < n; i++) {
      const c = at(i);
      const m = mid(i);
      p.quadraticCurveTo(c[0], c[1], m[0], m[1]);
    }
    p.closePath();
  });
}

/**
 * A cel-shaded shape: flat fill, a shadow band on the lower left (light from
 * the upper right), an optional highlight on the upper right, an ink outline.
 */
function cel(
  g: G,
  p: Path2D,
  fill: string,
  shade?: string,
  depth = 6,
  hi?: string,
  lw = LW2,
): void {
  g.save();
  g.clip(p);
  if (shade) {
    g.fillStyle = shade;
    g.fill(p);
    g.translate(depth, -depth * 0.75);
    g.fillStyle = fill;
    g.fill(p);
    g.translate(-depth, depth * 0.75);
  } else {
    g.fillStyle = fill;
    g.fill(p);
  }
  if (hi) {
    g.translate(-2.5, 2);
    g.strokeStyle = hi;
    g.lineWidth = 5;
    g.stroke(p);
  }
  g.restore();
  if (lw > 0) {
    g.lineWidth = lw;
    g.strokeStyle = INK;
    g.stroke(p);
  }
}

/** Draw inside a shape only. */
function inside(g: G, p: Path2D, draw: () => void): void {
  g.save();
  g.clip(p);
  draw();
  g.restore();
}

function stroke(g: G, color: string, width: number, p: Path2D): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.stroke(p);
}

function line(g: G, color: string, width: number, ...pts: number[]): void {
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i < pts.length; i += 2) g.lineTo(pts[i] ?? 0, pts[i + 1] ?? 0);
  g.stroke();
}

function curve(g: G, color: string, width: number, ...pts: number[]): void {
  // A quadratic curve per pair after the first point: x0 y0 cx cy x1 y1 [cx cy x2 y2 ...].
  g.strokeStyle = color;
  g.lineWidth = width;
  g.beginPath();
  g.moveTo(pts[0] ?? 0, pts[1] ?? 0);
  for (let i = 2; i + 3 < pts.length; i += 4)
    g.quadraticCurveTo(pts[i] ?? 0, pts[i + 1] ?? 0, pts[i + 2] ?? 0, pts[i + 3] ?? 0);
  g.stroke();
}

function rgb(hex: string): [number, number, number] {
  const n = Number.parseInt(hex.slice(1), 16);
  return [(n >> 16) & 255, (n >> 8) & 255, n & 255];
}

/** Blend two #rrggbb colours. */
function mix(a: string, b: string, t: number): string {
  const x = rgb(a);
  const y = rgb(b);
  const c = (i: 0 | 1 | 2) =>
    Math.round(x[i] + (y[i] - x[i]) * t)
      .toString(16)
      .padStart(2, "0");
  return `#${c(0)}${c(1)}${c(2)}`;
}

function alpha(hex: string, a: number): string {
  const [r, g, b] = rgb(hex);
  return `rgba(${r},${g},${b},${a})`;
}

/** Iron lamellae: staggered rows of small plates laced with the side's cord. */
function lamellae(
  g: G,
  p: Path2D,
  lace: string,
  o: {
    x0: number;
    x1: number;
    y0: number;
    y1: number;
    row: number;
    plate: number;
    sag?: number;
    base?: string;
    dark?: string;
    light?: string;
  },
): void {
  const base = o.base ?? IRON;
  const dark = o.dark ?? IRON_DARK;
  cel(g, p, base, dark, 7, o.light ?? IRON_LIGHT, 0);
  const sag = o.sag ?? 0;
  const mid = (o.x0 + o.x1) / 2;
  const half = (o.x1 - o.x0) / 2;
  const yAt = (x: number, y: number) => y + sag * (1 - ((x - mid) / half) ** 2);
  // One path each for the rows, the plate edges and the lacing.
  const rows = new Path2D();
  const plates = new Path2D();
  const laces = new Path2D();
  let row = 0;
  for (let y = o.y0; y < o.y1; y += o.row, row++) {
    rows.moveTo(o.x0, yAt(o.x0, y));
    for (let x = o.x0 + 4; x <= o.x1 + 0.1; x += 4) rows.lineTo(x, yAt(x, y));
    for (let x = o.x0 + (row % 2) * (o.plate / 2); x < o.x1; x += o.plate) {
      const yy = yAt(x, y);
      plates.moveTo(x, yy);
      plates.lineTo(x, yy + o.row);
    }
    if (row % 2 === 0)
      for (let x = o.x0 + o.plate * 0.25; x < o.x1; x += o.plate * 2)
        laces.rect(x, yAt(x, y) - 1.5, o.plate * 0.5, 3);
  }
  inside(g, p, () => {
    stroke(g, alpha("#1c1e22", 0.75), 1.6, rows);
    stroke(g, alpha("#1c1e22", 0.5), 1, plates);
    g.fillStyle = lace;
    g.fill(laces);
  });
  g.lineWidth = LW2;
  g.strokeStyle = INK;
  g.stroke(p);
}

// ---------------------------------------------------------------- geometry

/** Where the head sits on the canvas: the midpoint of the eye line. */
const HX = 116;
const HY = 140;

interface Geo {
  X: (x: number) => number;
  Y: (y: number) => number;
  /** Far jaw corner, near jaw angle, chin bottom. */
  jx: number;
  jy: number;
  ax: number;
  ay: number;
  cx: number;
  cy: number;
  /** Eyes (near and far), nose tip, mouth centre and width. */
  e1: number;
  e2: number;
  nx: number;
  ny: number;
  mx: number;
  my: number;
  mw: number;
}

function geo(t: Traits): Geo {
  const X = (x: number) => x * t.w;
  const Y = (y: number) => (y > 0 ? y * t.len : y);
  // Heavy jowls fill out the jaw and drop the chin.
  const j = t.marks.includes("jowls") ? 1 : 0;
  return {
    X,
    Y,
    jx: X(42 + t.jaw * 9 + j * 6),
    jy: Y(52 + t.jaw * 3 + j * 8),
    ax: X(-25 - t.jaw * 8 - j * 8),
    ay: Y(44 + t.jaw * 8 + j * 8),
    cx: X(10 + t.chin * 4),
    cy: Y(79 + j * 4),
    e1: X(-5),
    e2: X(34),
    nx: X(31),
    ny: Y(31),
    mx: X(25),
    my: Y(51),
    mw: t.mouth * (0.9 + t.w * 0.1),
  };
}

function headPath(t: Traits, q: Geo): Path2D {
  const { X, Y } = q;
  const c = t.cheek;
  return path((p) => {
    p.moveTo(X(-4), -90);
    p.bezierCurveTo(X(32), -90, X(54), -62, X(56), -22);
    p.quadraticCurveTo(X(58), -8, X(54), 1);
    p.quadraticCurveTo(X(56 + c * 3), 8, X(56 + c * 3), 18);
    p.bezierCurveTo(X(56 + c * 2), Y(34), q.jx + 5, q.jy - 10, q.jx, q.jy);
    p.quadraticCurveTo(X(30 + t.chin * 8), Y(73), X(22 + t.chin * 5), Y(78));
    p.quadraticCurveTo(X(12), Y(83), X(0 - t.chin * 6), Y(78));
    p.quadraticCurveTo(X(-16), Y(70), q.ax, q.ay);
    p.quadraticCurveTo(X(-40), Y(32), X(-48), Y(18));
    p.bezierCurveTo(X(-64), 4, X(-72), -40, X(-56), -66);
    p.bezierCurveTo(X(-44), -84, X(-24), -90, X(-4), -90);
    p.closePath();
  });
}

// ---------------------------------------------------------------- background

function background(g: G, L: Look): void {
  const P = L.P;
  const sky = g.createLinearGradient(0, 0, 0, PORTRAIT_H);
  sky.addColorStop(0, mix(P.robe, "#f4e6c8", 0.28));
  sky.addColorStop(0.55, mix(P.robe, "#0c0a10", 0.25));
  sky.addColorStop(1, mix(P.robeDark, "#07060a", 0.55));
  g.fillStyle = sky;
  g.fillRect(0, 0, PORTRAIT_W, PORTRAIT_H);
  // A soft disc of light behind the head.
  const glow = g.createRadialGradient(150, 110, 10, 140, 130, 150);
  glow.addColorStop(0, alpha("#fff4dc", 0.55));
  glow.addColorStop(0.45, alpha(mix(P.lace, "#fff4dc", 0.4), 0.32));
  glow.addColorStop(1, alpha("#fff4dc", 0));
  g.fillStyle = glow;
  g.fillRect(0, 0, PORTRAIT_W, PORTRAIT_H);
  g.save();
  g.globalAlpha = 0.5;
  g.strokeStyle = mix(P.edge, GOLD_LIGHT, 0.5);
  g.lineWidth = 2;
  g.beginPath();
  g.arc(134, 128, 108, 0, Math.PI * 2);
  g.stroke();
  g.globalAlpha = 0.18;
  g.lineWidth = 7;
  g.beginPath();
  g.arc(134, 128, 96, 0, Math.PI * 2);
  g.stroke();
  g.restore();
  // Faint ink-wash strokes, the same for each id.
  const r = rng(L.seed ^ 0x9e3779b9);
  g.save();
  g.globalAlpha = 0.09;
  g.strokeStyle = "#fff8ea";
  g.lineCap = "round";
  for (let i = 0; i < 4; i++) {
    const y = 30 + r() * 220;
    g.lineWidth = 10 + r() * 22;
    g.beginPath();
    g.moveTo(-20, y + r() * 30);
    g.quadraticCurveTo(128, y - 30 + r() * 60, 280, y + r() * 40 - 20);
    g.stroke();
  }
  g.restore();
}

// ---------------------------------------------------------------- body

/** Shoulder width by build. */
function sw(t: Traits): number {
  return [0.92, 1, 1.07, 1.14][t.build] ?? 1;
}

function torsoPath(s: number): Path2D {
  const bx = (x: number) => 128 + (x - 128) * s;
  return path((p) => {
    p.moveTo(bx(74), 222);
    p.bezierCurveTo(bx(56), 244, bx(26), 250, bx(6), 270);
    p.quadraticCurveTo(bx(-14), 290, bx(-18), 340);
    p.lineTo(bx(276), 340);
    p.quadraticCurveTo(bx(276), 288, bx(250), 268);
    p.bezierCurveTo(bx(230), 252, bx(196), 244, bx(166), 230);
    p.closePath();
  });
}

/** The robe's colours: the side's dye, or undyed hemp, drab rags, a servant's faded livery. */
interface Cloth {
  robe: string;
  dark: string;
  light: string;
  edge: string;
  edgeDark: string;
  /** Coarse cloth: a visible weave and an open neck, no white under-robe. */
  coarse: boolean;
}

function clothOf(L: Look): Cloth {
  const { P, fam, t } = L;
  if (t.dress === "bandit")
    return {
      robe: DRAB,
      dark: DRAB_DARK,
      light: DRAB_LIGHT,
      edge: DRAB_DARK,
      edgeDark: "#3a2f1f",
      coarse: true,
    };
  if (fam === "martialArtist" || (t.dress === "rebel" && fam !== "strategist"))
    return {
      robe: HEMP,
      dark: HEMP_DARK,
      light: HEMP_LIGHT,
      edge: HEMP_DARK,
      edgeDark: "#6e5f41",
      coarse: true,
    };
  if (t.dress === "servant") {
    const robe = mix(P.robe, HEMP, 0.4);
    return {
      robe,
      dark: mix(P.robeDark, HEMP_DARK, 0.4),
      light: mix(robe, "#ffffff", 0.2),
      edge: HEMP,
      edgeDark: HEMP_DARK,
      coarse: false,
    };
  }
  return {
    robe: P.robe,
    dark: P.robeDark,
    light: mix(P.robe, "#ffffff", 0.22),
    edge: P.edge,
    edgeDark: mix(P.edge, "#5a4a30", 0.35),
    coarse: false,
  };
}

function body(g: G, L: Look): void {
  const { t } = L;
  const s = sw(t);
  const bx = (x: number) => 128 + (x - 128) * s;
  const C = clothOf(L);
  const torso = torsoPath(s);
  cel(g, torso, C.robe, C.dark, 10, C.light, LW);
  // A broad shadow on the near side, turned from the light.
  inside(g, torso, () => {
    g.fillStyle = C.dark;
    g.beginPath();
    g.moveTo(-40, 240);
    g.lineTo(bx(58), 236);
    g.bezierCurveTo(bx(40), 270, bx(52), 300, bx(40), 340);
    g.lineTo(-40, 340);
    g.closePath();
    g.fill();
    if (C.coarse)
      // Coarse weave.
      for (let x = -20; x < 280; x += 6) line(g, alpha(C.dark, 0.3), 1, x, 230, x - 20, 340);
    if (has(t, "brocade")) brocade(g, C);
  });
  // The robe's collar: right over left (交領右衽). The outer lapel runs from
  // the wearer's left (our right) down across to the wearer's right.
  // Back of the collar, behind the neck.
  cel(
    g,
    path((p) => {
      p.moveTo(bx(62), 250);
      p.quadraticCurveTo(bx(64), 226, bx(80), 214);
      p.lineTo(bx(146), 220);
      p.quadraticCurveTo(bx(158), 228, bx(162), 240);
      p.lineTo(bx(150), 246);
      p.lineTo(bx(84), 246);
      p.closePath();
    }),
    C.edge,
    C.edgeDark,
    5,
  );
}

/** Figured silk (綺): a lozenge lattice woven a shade lighter, with a dot in each lozenge. */
function brocade(g: G, C: Cloth): void {
  const thread = alpha(mix(C.robe, GOLD_LIGHT, 0.45), 0.55);
  const lattice = new Path2D();
  for (let k = -360; k < 400; k += 22) {
    lattice.moveTo(k, 220);
    lattice.lineTo(k + 130, 350);
    lattice.moveTo(k + 130, 220);
    lattice.lineTo(k, 350);
  }
  stroke(g, thread, 1.4, lattice);
  // The lines cross where (x - 2) / 11 and (y - 10) / 11 have the same parity; a dot in each lozenge.
  g.fillStyle = thread;
  for (let j = 20; j <= 31; j++)
    for (let i = -2 + ((j + 1) % 2); i <= 26; i += 2) {
      g.beginPath();
      g.arc(2 + 11 * i, 10 + 11 * j, 1.8, 0, Math.PI * 2);
      g.fill();
    }
}

/** Front of the collar and everything worn over the chest; drawn after the neck. */
function chest(g: G, L: Look): void {
  const { fam, t } = L;
  const s = sw(t);
  const bx = (x: number) => 128 + (x - 128) * s;
  const C = clothOf(L);
  const hemp = C.coarse;
  const edge = C.edge;
  const edgeDark = C.edgeDark;
  const robeDark = C.dark;
  const open = hemp ? 14 : 0;
  // Inner lapel (wearer's right), mostly under the outer one.
  const innerLapel = path((p) => {
    p.moveTo(bx(70), 240);
    p.quadraticCurveTo(bx(84), 248, bx(92), 252);
    p.quadraticCurveTo(bx(108), 280, bx(124 + open), 304);
    p.lineTo(bx(106 + open), 318);
    p.quadraticCurveTo(bx(84), 284, bx(60), 254);
    p.closePath();
  });
  // The white under-robe's collar shows inside the V.
  const vee = path((p) => {
    p.moveTo(bx(90), 248);
    p.quadraticCurveTo(bx(118), 262, bx(152), 240);
    p.lineTo(bx(156), 250);
    p.quadraticCurveTo(bx(140), 290, bx(122 + open), 310);
    p.quadraticCurveTo(bx(104), 280, bx(90), 248);
    p.closePath();
  });
  if (!hemp) {
    cel(g, vee, WHITE_CLOTH, WHITE_SHADE, 5);
    inside(g, vee, () => {
      curve(g, alpha(WHITE_SHADE, 0.9), 2, bx(96), 252, bx(118), 272, bx(150), 246);
    });
  } else {
    // Open neck of the work clothes: skin and collarbone.
    const [sk, skd] = L.skin;
    cel(g, vee, sk, skd, 6);
    inside(g, vee, () => {
      curve(g, alpha(skd, 0.9), 2, bx(100), 266, bx(118), 270, bx(136), 262);
    });
  }
  cel(g, innerLapel, edge, edgeDark, 4);
  // Outer lapel (wearer's left), crossing to the wearer's right.
  const outerLapel = path((p) => {
    p.moveTo(bx(150), 236);
    p.quadraticCurveTo(bx(166), 238, bx(180), 244);
    p.quadraticCurveTo(bx(150), 300, bx(96 + open), 344);
    p.lineTo(bx(66 + open), 344);
    p.quadraticCurveTo(bx(124), 292, bx(150), 236);
    p.closePath();
  });
  cel(g, outerLapel, edge, edgeDark, 5, mix(edge, "#ffffff", 0.4));
  if (L.tier2 || has(t, "gilt"))
    inside(g, outerLapel, () => {
      curve(g, GOLD, 3, bx(176), 246, bx(146), 300, bx(92 + open), 344);
    });
  // Class dress over the robe. Civilians and servants wear no armour, whatever the class.
  const unarmoured = has(t, "civil") || t.dress === "servant";
  const waist = () =>
    // A wide sash at the bottom edge.
    cel(
      g,
      path((p) => {
        p.moveTo(bx(-20), 318);
        p.quadraticCurveTo(bx(128), 330, bx(280), 312);
        p.lineTo(bx(280), 340);
        p.lineTo(bx(-20), 340);
        p.closePath();
      }),
      robeDark,
      mix(robeDark, "#000000", 0.3),
      5,
    );
  switch (fam) {
    case "infantry":
    case "cavalry":
      if (unarmoured) waist();
      else {
        armour(g, L, bx, true);
        if (t.dress === "frontier") fleece(g, bx);
      }
      break;
    case "catapult":
      if (!unarmoured) armour(g, L, bx, false);
      rope(g, bx);
      break;
    case "archer":
      vest(g, L, bx);
      break;
    case "martialArtist":
      sash(g, L, bx);
      if (L.tier2 && !unarmoured) armour(g, L, bx, false);
      break;
    case "strategist":
      waist();
      break;
  }
}

/** A sheepskin collar round the cuirass's neckline, as the Liang frontier wore against the cold. */
function fleece(g: G, bx: (x: number) => number): void {
  const segs: [Pt, Pt, Pt][] = [
    [
      [bx(34), 266],
      [bx(80), 276],
      [bx(102), 298],
    ],
    [
      [bx(102), 298],
      [bx(124), 312],
      [bx(150), 296],
    ],
    [
      [bx(150), 296],
      [bx(186), 268],
      [bx(218), 259],
    ],
  ];
  // A roll of wool: overlapping tufts with an ink edge round the outside only.
  const pts: Pt[] = [];
  for (const [a, c, b] of segs)
    for (let i = 0; i < 8; i++) {
      const [x, y] = onCurve(a, c, b, i / 8);
      pts.push([x, y]);
    }
  pts.push([bx(218), 259]);
  const tufts = (r: number, dx: number, dy: number) =>
    path((p) => {
      for (const [i, [x, y]] of pts.entries()) {
        const rr = r + (i % 3 === 1 ? 1.5 : 0);
        p.moveTo(x + dx + rr, y + dy);
        p.arc(x + dx, y + dy, rr, 0, Math.PI * 2);
      }
    });
  const wool = tufts(10, 0, 0);
  stroke(g, INK, 5, wool);
  g.fillStyle = FLEECE_DARK;
  g.fill(wool);
  g.fillStyle = FLEECE;
  g.fill(tufts(7.5, 1.5, -2.5));
  // Curls of wool.
  for (const [i, [x, y]] of pts.entries()) {
    if (i % 2) continue;
    curve(g, FLEECE_LIGHT, 2, x - 3, y - 2, x + 1, y - 7, x + 5, y - 2);
    curve(g, alpha(FLEECE_DARK, 0.9), 1.6, x - 4, y + 4, x - 1, y + 8, x + 3, y + 5);
  }
}

/**
 * Iron lamellar cuirass and shoulder flaps (披膊), laced in the side's colour.
 * Rebels and bandits wear patched leather instead; the Liang frontier wears more of it.
 */
function armour(g: G, L: Look, bx: (x: number) => number, cuirass: boolean): void {
  const { P } = L;
  const steel = has(L.t, "silver");
  const rough = L.t.dress === "rebel" || L.t.dress === "bandit";
  const heavy = (L.tier2 && L.fam === "infantry") || L.t.dress === "frontier";
  const base = rough ? LEATHER : steel ? STEEL : IRON;
  const dark = rough ? LEATHER_DARK : steel ? STEEL_DARK : IRON_DARK;
  const light = rough ? LEATHER_LIGHT : steel ? STEEL_LIGHT : IRON_LIGHT;
  if (cuirass) {
    const plate = path((p) => {
      p.moveTo(bx(40), 268);
      p.quadraticCurveTo(bx(80), 278, bx(102), 300);
      p.quadraticCurveTo(bx(124), 314, bx(150), 298);
      p.quadraticCurveTo(bx(186), 270, bx(212), 262);
      p.lineTo(bx(250), 300);
      p.lineTo(bx(250), 340);
      p.lineTo(bx(10), 340);
      p.closePath();
    });
    lamellae(g, plate, P.lace, {
      x0: bx(0),
      x1: bx(260),
      y0: 262,
      y1: 342,
      row: 11,
      plate: 8,
      sag: 6,
      base,
      dark,
      light,
    });
    if (rough) patches(g, plate, L.seed, bx);
    // Leather edging along the neckline.
    g.save();
    g.beginPath();
    g.moveTo(bx(40), 268);
    g.quadraticCurveTo(bx(80), 278, bx(102), 300);
    g.quadraticCurveTo(bx(124), 314, bx(150), 298);
    g.quadraticCurveTo(bx(186), 270, bx(212), 262);
    g.strokeStyle = INK;
    g.lineWidth = 8;
    g.stroke();
    g.strokeStyle = L.tier2 || has(L.t, "gilt") ? GOLD : LEATHER;
    g.lineWidth = 5;
    g.stroke();
    g.restore();
  }
  // Shoulder flaps: three overlapping rows each side (two for rebels, four for heavy armour).
  const rows = heavy ? 4 : rough ? 2 : 3;
  for (let i = rows - 1; i >= 0; i--) {
    const near = path((p) => {
      const y = 252 + i * 15;
      p.moveTo(bx(70 - i * 4), y - 6);
      p.quadraticCurveTo(bx(20 - i * 6), y - 16, bx(-16 - i * 6), y + 12);
      p.lineTo(bx(-12 - i * 6), y + 30);
      p.quadraticCurveTo(bx(28 - i * 6), y + 14, bx(76 - i * 4), y + 16);
      p.closePath();
    });
    lamellae(g, near, P.lace, {
      x0: bx(-40),
      x1: bx(90),
      y0: 236 + i * 15,
      y1: 300 + i * 15,
      row: 9,
      plate: 7,
      sag: -10,
      base,
      dark,
      light,
    });
    const far = path((p) => {
      const y = 250 + i * 14;
      p.moveTo(bx(196 + i * 3), y - 4);
      p.quadraticCurveTo(bx(236 + i * 5), y - 10, bx(270 + i * 4), y + 16);
      p.lineTo(bx(270 + i * 4), y + 32);
      p.quadraticCurveTo(bx(234 + i * 4), y + 12, bx(194 + i * 3), y + 16);
      p.closePath();
    });
    lamellae(g, far, P.lace, {
      x0: bx(180),
      x1: bx(290),
      y0: 236 + i * 14,
      y1: 300 + i * 14,
      row: 9,
      plate: 6,
      sag: -8,
      base,
      dark,
      light,
    });
  }
}

/** Patches of other hides sewn over worn leather, with coarse stitches. */
function patches(g: G, clip: Path2D, seed: number, bx: (x: number) => number): void {
  const r = rng(seed ^ 0x7a7c15);
  inside(g, clip, () => {
    for (let i = 0; i < 3; i++) {
      const x = bx(30 + i * 70 + r() * 30);
      const y = 300 + r() * 24;
      const w = 22 + r() * 14;
      const h = 16 + r() * 10;
      const tilt = (r() - 0.5) * 6;
      const p = poly(x, y + tilt, x + w, y - tilt, x + w + 2, y + h - tilt, x - 1, y + h + tilt);
      cel(g, p, i === 1 ? HEMP_DARK : "#8a6040", LEATHER_DARK, 3, undefined, 1.6);
      // Stitches round the edge.
      g.setLineDash([3, 3]);
      g.strokeStyle = alpha("#e8dcc0", 0.8);
      g.lineWidth = 1.2;
      g.save();
      g.translate(x + w / 2, y + h / 2);
      g.scale(0.78, 0.7);
      g.translate(-(x + w / 2), -(y + h / 2));
      g.stroke(p);
      g.restore();
      g.setLineDash([]);
    }
  });
}

/** Archers: a light leather vest and the quiver's strap across the chest. */
function vest(g: G, L: Look, bx: (x: number) => number): void {
  const v = path((p) => {
    p.moveTo(bx(30), 276);
    p.quadraticCurveTo(bx(80), 284, bx(102), 306);
    p.quadraticCurveTo(bx(124), 318, bx(150), 302);
    p.quadraticCurveTo(bx(190), 276, bx(226), 272);
    p.lineTo(bx(240), 340);
    p.lineTo(bx(20), 340);
    p.closePath();
  });
  lamellae(g, v, alpha(L.P.lace, 0.9), {
    x0: bx(0),
    x1: bx(260),
    y0: 270,
    y1: 342,
    row: 14,
    plate: 12,
    sag: 6,
    base: LEATHER,
    dark: LEATHER_DARK,
    light: LEATHER_LIGHT,
  });
  // The strap runs from the far shoulder down to the near hip.
  const strap = poly(bx(214), 250, bx(232), 258, bx(40), 344, bx(16), 340);
  cel(g, strap, LEATHER_DARK, "#2f1d11", 4, LEATHER);
  cel(g, ellipse(bx(150), 292, 9, 7, -0.4), BRONZE, BRONZE_DARK, 3, BRONZE_LIGHT);
}

/** Martial artists: a sash in the side's colour tied across the chest. */
function sash(g: G, L: Look, bx: (x: number) => number): void {
  const s = poly(bx(196), 246, bx(222), 254, bx(40), 344, bx(10), 336);
  cel(g, s, L.P.tassel, L.P.robeDark, 5, mix(L.P.tassel, "#ffffff", 0.35));
}

/** Stone-thrower crews: a coil of hauling rope over the near shoulder. */
function rope(g: G, bx: (x: number) => number): void {
  for (let i = 0; i < 3; i++) {
    const p = ellipse(bx(34 + i * 4), 296 + i * 3, 30, 14, -0.5);
    g.lineWidth = 9;
    g.strokeStyle = INK;
    g.stroke(p);
    g.lineWidth = 5;
    g.strokeStyle = i === 1 ? "#b29a6c" : "#cbb98f";
    g.stroke(p);
  }
}

// ---------------------------------------------------------------- head

function neck(g: G, L: Look, q: Geo): void {
  const { X, Y } = q;
  const k = has(L.t, "thickNeck") ? 1.25 : L.t.build >= 2 ? 1.1 : 1;
  const [sk, skd] = L.skin;
  const n = path((p) => {
    p.moveTo(X(12), Y(72));
    p.bezierCurveTo(X(16), Y(92), X(26 * k), 110, X(32 * k), 132);
    p.lineTo(X(-54 * k), 132);
    p.bezierCurveTo(X(-40 * k), 108, X(-38 * k), 60, X(-44), 10);
    p.closePath();
  });
  cel(g, n, sk, skd, 7);
  inside(g, n, () => {
    // The jaw's shadow on the neck.
    g.fillStyle = skd;
    g.save();
    g.translate(-4, 16);
    g.fill(headPath(L.t, q));
    g.restore();
    g.fillRect(X(-80), -20, X(48), 160);
    if (L.fam === "martialArtist" || L.t.build >= 2)
      curve(g, alpha(skd, 0.9), 2.4, X(-30), Y(40), X(-8), 100, X(10), 128);
  });
}

function ear(g: G, L: Look, q: Geo): void {
  const { X, Y } = q;
  const big = has(L.t, "ears");
  const lobe = big ? 56 : 34;
  const [sk, skd, skl] = L.skin;
  const e = path((p) => {
    p.moveTo(X(-28), -8);
    p.bezierCurveTo(X(-38), -18, X(-52), -8, X(-48), 10);
    p.bezierCurveTo(X(-46), Y(24), X(big ? -46 : -42), Y(lobe), X(-33), Y(lobe - 2));
    p.quadraticCurveTo(X(-27), Y(22), X(-28), -8);
    p.closePath();
  });
  cel(g, e, sk, skd, 5, skl);
  curve(g, alpha(skd, 1), 2.4, X(-34), -4, X(-44), 2, X(-40), Y(18), X(-36), Y(22), X(-34), Y(14));
}

/** The hair: a clean hairline, sideburns in front of the ear, the nape and the crown. */
function hairPath(q: Geo, top: number): Path2D {
  const { X, Y } = q;
  return path((p) => {
    p.moveTo(X(55), -50);
    p.quadraticCurveTo(X(40), -60, X(20), -61);
    p.quadraticCurveTo(X(2), -61, X(-12), -52);
    p.quadraticCurveTo(X(-22), -40, X(-25), -20);
    p.lineTo(X(-27), 2);
    p.lineTo(X(-33), 4);
    p.lineTo(X(-46), Y(14));
    p.quadraticCurveTo(X(-50), Y(26), X(-48), Y(36));
    p.quadraticCurveTo(X(-66), Y(20), X(-72), -10);
    p.bezierCurveTo(X(-80), -60, X(-52), top, X(-6), top);
    p.bezierCurveTo(X(28), top, X(58), -86, X(55), -50);
    p.closePath();
  });
}

/** Hair under the headwear, or bare and combed up to the topknot. */
function hairBase(g: G, L: Look, q: Geo): void {
  const { X } = q;
  const [hc, hd, hl] = L.hair;
  const bare = L.hat === "knot" || L.hat === "crown" || L.hat === "rake" || L.hat === "wrap";
  const h = hairPath(q, bare ? -102 : -96);
  cel(g, h, hc, hd, 7, undefined, bare ? LW : LW2);
  inside(g, h, () => {
    // Comb lines running up to the knot.
    for (let i = 0; i < 12; i++) {
      const x = X(52 - i * 8);
      const y = i < 7 ? -56 - Math.sin((i / 7) * Math.PI) * 5 : -46 + (i - 7) * 11;
      const bend = (x + X(-12)) / 2 + 6;
      curve(g, alpha(i % 3 ? hd : hl, 0.75), 1.6, x, y, bend, (y - 102) / 2 - 8, X(-12), -102);
    }
    if (bare) {
      // A broken sheen across the crown.
      for (let i = 0; i < 9; i++) {
        const u = i / 8;
        const x = X(44 - u * 100);
        const y = -76 - Math.sin(u * Math.PI) * 18;
        line(g, alpha(hl, 0.8), 3.2, x - 3, y + 4, x + 3, y - 4);
      }
    }
  });
}

interface MoodShape {
  open: number;
  lower: number;
  inner: number;
  browIn: number;
  browOut: number;
}

const MOOD_SHAPES: Record<Mood, MoodShape> = {
  calm: { open: 1, lower: 1, inner: 0, browIn: 0, browOut: 0 },
  smile: { open: 0.85, lower: -0.25, inner: 0, browIn: -3, browOut: -1 },
  angry: { open: 0.95, lower: 0.85, inner: 0.65, browIn: 8, browOut: -4 },
  grim: { open: 0.6, lower: 0.9, inner: 0.25, browIn: 3, browOut: 2 },
  hurt: { open: 0.8, lower: 0.95, inner: -0.25, browIn: -6, browOut: 3 },
};

const EYES: Record<
  EyeKind,
  { w: number; h: number; tilt: number; crease: boolean; lash: number; iris: number }
> = {
  almond: { w: 24, h: 8, tilt: 1.5, crease: true, lash: 3.2, iris: 1 },
  narrow: { w: 25, h: 5.6, tilt: 3, crease: false, lash: 3.4, iris: 0.95 },
  phoenix: { w: 29, h: 5.4, tilt: 5.5, crease: true, lash: 3.6, iris: 0.9 },
  round: { w: 22, h: 10, tilt: 0, crease: true, lash: 3.2, iris: 1 },
  gentle: { w: 24, h: 7.6, tilt: -2, crease: true, lash: 2.8, iris: 1.05 },
  heavy: { w: 24, h: 6.2, tilt: -1, crease: true, lash: 3.4, iris: 1 },
  small: { w: 18, h: 6, tilt: 0.5, crease: false, lash: 3.2, iris: 0.85 },
  fierce: { w: 25, h: 9, tilt: 3.5, crease: true, lash: 3.8, iris: 0.72 },
};

function eye(g: G, L: Look, x: number, y: number, near: boolean, closed: boolean): void {
  const k = EYES[L.t.eye];
  const m = MOOD_SHAPES[L.mood];
  const s = L.t.eyeSize;
  const w = k.w * s * (near ? 1 : 0.66);
  const h = k.h * s * (near ? 1 : 0.94);
  const dir = near ? -1 : 1;
  const xo = x + dir * w * 0.5;
  const yo = y - k.tilt * (near ? 1 : 0.8);
  const xi = x - dir * w * 0.5;
  const yi = y + 1;
  const skd = L.skin[1];
  if (closed) {
    // Squeezed shut.
    curve(g, INK, k.lash, xo, yo + 1, x, y - h * 0.3, xi, yi);
    curve(g, alpha(skd, 1), 1.6, xo + dir * 2, yo + 5, x + dir * 2, y + 6, xi - dir * 2, y + 4);
    line(g, alpha(skd, 1), 1.4, xo + dir * 1, yo - 4, xo + dir * 6, yo - 7);
    return;
  }
  const open = h * m.open;
  const upper = path((p) => {
    p.moveTo(xo, yo);
    p.bezierCurveTo(
      xo - dir * w * 0.2,
      y - open * 1.55,
      xi + dir * w * 0.25,
      y - open * (1.3 - m.inner),
      xi,
      yi,
    );
  });
  const shape = new Path2D(upper);
  shape.quadraticCurveTo(x, y + h * 0.95 * m.lower + 1, xo, yo);
  shape.closePath();
  g.fillStyle = SCLERA;
  g.fill(shape);
  inside(g, shape, () => {
    const r = Math.min(h * 1.05 * k.iris, w * 0.32) * (near ? 1 : 0.92);
    const ix = x + w * 0.1;
    const iy = y - open * 0.15;
    g.fillStyle = "#4b2d1f";
    g.beginPath();
    g.arc(ix, iy, r, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = "#24140e";
    g.beginPath();
    g.arc(ix, iy - r * 0.35, r * 0.95, Math.PI, Math.PI * 2);
    g.fill();
    g.fillStyle = "#120a07";
    g.beginPath();
    g.arc(ix, iy, r * 0.45, 0, Math.PI * 2);
    g.fill();
    // The lid's shadow across the top of the eye.
    g.strokeStyle = alpha("#6a3a28", 0.35);
    g.lineWidth = h * 0.8;
    g.stroke(upper);
    g.fillStyle = "#ffffff";
    g.beginPath();
    g.arc(ix + r * 0.38, iy - r * 0.38, Math.max(1.4, r * 0.3), 0, Math.PI * 2);
    g.fill();
    g.fillStyle = alpha("#ffffff", 0.7);
    g.beginPath();
    g.arc(ix - r * 0.35, iy + r * 0.4, Math.max(0.8, r * 0.14), 0, Math.PI * 2);
    g.fill();
  });
  // Lash line, thicker at the outer corner, with a small flick.
  g.lineCap = "round";
  stroke(g, INK, k.lash * (near ? 1 : 0.85), upper);
  line(g, INK, k.lash * 0.8, xo, yo, xo + dir * 4, yo + 2.5);
  // Lower lid.
  curve(
    g,
    alpha("#7a4630", 0.75),
    1.5,
    xo + dir * -1,
    yo + 1,
    x,
    y + h * 0.95 * m.lower + 1,
    xi,
    yi,
  );
  if (k.crease && m.open > 0.7)
    curve(
      g,
      alpha(skd, 1),
      1.6,
      xo - dir * w * 0.1,
      yo - h * 0.9,
      x,
      y - open * 1.7 - 3,
      xi + dir * w * 0.2,
      yi - h * 0.75,
    );
}

function brow(g: G, L: Look, x: number, y: number, near: boolean): void {
  const t = L.t;
  const m = MOOD_SHAPES[L.mood];
  const dir = near ? -1 : 1;
  const len = (near ? 30 : 19) * (0.94 + t.w * 0.06);
  const th = t.brow * (near ? 1 : 0.85);
  const xi = x - dir * len * 0.42;
  const xo = x + dir * len * 0.58;
  const yi = y + m.browIn + (has(t, "frown") ? 2 : 0);
  const yo = y + m.browOut - t.tilt;
  const bushy = has(t, "bushy");
  const old = t.age >= 3 && bushy;
  // Sample the lower and upper edges; bushy brows get tufts combed outward along the top.
  const ye = yo + (old ? 6 : 1);
  const n = 10;
  const q2 = (a: number, c: number, b: number, u: number) =>
    (1 - u) * (1 - u) * a + 2 * (1 - u) * u * c + u * u * b;
  const p = path((pp) => {
    for (let i = 0; i <= n; i++) {
      const u = i / n;
      const bx = q2(xi, x, xo, u);
      const by = q2(yi + th * 0.45, y - t.arch + th * 0.25, ye, u);
      if (i === 0) pp.moveTo(bx, by);
      else pp.lineTo(bx, by);
    }
    for (let i = n; i >= 0; i--) {
      const u = i / n;
      let tx = q2(xi, x + dir * len * 0.1, xo, u);
      let ty = q2(yi - th * 0.55, y - t.arch - th * 0.75, ye, u);
      if (bushy && i % 2 === 1 && i < n) {
        tx += dir * 4;
        ty -= 2.5 + th * 0.2;
      }
      pp.lineTo(tx, ty);
    }
    pp.closePath();
  });
  const [hc, hd] = L.hair;
  g.fillStyle = hc;
  g.fill(p);
  if (L.t.hair === "white" || L.t.hair === "grey") {
    g.lineWidth = 1.2;
    g.strokeStyle = hd;
    g.stroke(p);
  }
}

function nose(g: G, L: Look, q: Geo): void {
  const { nx, ny, X, Y } = q;
  const [, skd, skl] = L.skin;
  const t = L.t;
  const broad = t.nose === "broad" ? 1.35 : t.nose === "small" ? 0.75 : 1;
  // Shadow down the near side of the bridge.
  g.fillStyle = alpha(skd, 0.95);
  g.beginPath();
  g.moveTo(X(16), -6);
  g.quadraticCurveTo(X(20), Y(16), nx - 6 * broad, ny - 2);
  g.quadraticCurveTo(nx - 10 * broad, ny + 3, nx - 2, ny + 4);
  g.quadraticCurveTo(X(22), Y(14), X(19), -6);
  g.closePath();
  g.fill();
  // Under the nose.
  g.beginPath();
  g.ellipse(nx - 2, ny + 6, 9 * broad, 2.6, 0, 0, Math.PI * 2);
  g.fill();
  // Tip and wing.
  if (t.nose === "hook")
    curve(g, INK, LW2, X(27), Y(6), X(34), Y(14), nx + 2, ny - 2, nx + 2, ny + 2, nx - 2, ny + 3);
  else curve(g, INK, LW2, nx + 1, ny - 7, nx + 3, ny - 1, nx - 1, ny + 3);
  curve(g, INK, LW2, nx - 4, ny + 3.5, nx - 8 * broad, ny + 4, nx - 10 * broad, ny - 1);
  g.fillStyle = "#5a2e22";
  g.beginPath();
  g.ellipse(nx - 3, ny + 2.5, 2.4, 1.3, 0.2, 0, Math.PI * 2);
  g.fill();
  if (has(t, "rednose")) {
    g.fillStyle = alpha("#d04a3a", 0.5);
    g.beginPath();
    g.ellipse(nx - 3, ny - 1, 8 * broad, 6, 0, 0, Math.PI * 2);
    g.fill();
  }
  // Highlight on the bridge and tip.
  line(g, alpha(skl, 0.95), 2.4, X(24), 2, nx - 1, ny - 8);
  g.fillStyle = alpha("#ffffff", 0.55);
  g.beginPath();
  g.arc(nx - 1, ny - 2, 1.8, 0, Math.PI * 2);
  g.fill();
}

function mouth(g: G, L: Look, q: Geo): void {
  const { mx, my, mw } = q;
  const xl = mx - mw * 0.58;
  const xr = mx + mw * 0.42;
  const [, skd] = L.skin;
  const smirk = has(L.t, "smirk");
  const lip = alpha(skd, 1);
  switch (L.mood) {
    case "calm": {
      curve(g, INK, 2.4, xl, my, mx, my + 1.5, xr, my - (smirk ? 4 : 0.5));
      if (smirk) curve(g, INK, 1.6, xr - 1, my - 4, xr + 2, my - 4, xr + 3, my - 6);
      line(g, alpha(skd, 0.8), 2.4, mx - 4, my + 6.5, mx + 4, my + 6);
      break;
    }
    case "smile": {
      const m = path((p) => {
        p.moveTo(xl, my - 2);
        p.quadraticCurveTo(mx, my + 1, xr, my - 4);
        p.quadraticCurveTo(mx + 2, my + 11, xl, my - 2);
        p.closePath();
      });
      g.fillStyle = MOUTH;
      g.fill(m);
      inside(g, m, () => {
        g.fillStyle = TEETH;
        g.fillRect(xl - 2, my - 6, mw + 4, 7.5);
      });
      g.lineWidth = 2.2;
      g.strokeStyle = INK;
      g.stroke(m);
      curve(g, lip, 2.2, xl - 3, my - 5, xl - 2, my - 1, xl + 1, my + 2);
      line(g, lip, 2.4, mx - 4, my + 11, mx + 5, my + 10.5);
      break;
    }
    case "angry": {
      const m = path((p) => {
        p.moveTo(xl - 1, my + 3);
        p.quadraticCurveTo(mx, my - 5, xr + 1, my + 1);
        p.quadraticCurveTo(mx + 1, my + 16, xl - 1, my + 3);
        p.closePath();
      });
      g.fillStyle = MOUTH;
      g.fill(m);
      inside(g, m, () => {
        g.fillStyle = TEETH;
        g.fillRect(xl - 3, my - 6, mw + 6, 7);
        g.fillRect(xl - 3, my + 8, mw + 6, 8);
        g.strokeStyle = alpha("#9a8c78", 0.9);
        g.lineWidth = 1;
        for (let x = xl + 4; x < xr; x += 5)
          line(g, alpha("#9a8c78", 0.9), 1, x, my - 6, x, my + 1);
      });
      g.lineWidth = 2.4;
      g.strokeStyle = INK;
      g.stroke(m);
      line(g, lip, 2.2, xl - 3, my + 7, xl - 6, my + 12);
      break;
    }
    case "grim": {
      curve(g, INK, 2.6, xl, my + 2.5, mx, my - 1, xr, my + 1.5);
      line(g, lip, 2.6, mx - 6, my + 8, mx + 4, my + 8);
      curve(g, lip, 1.6, mx - 4, my + 15, mx, my + 13, mx + 4, my + 15);
      break;
    }
    case "hurt": {
      const m = path((p) => {
        p.moveTo(xl - 2, my + 2);
        p.quadraticCurveTo(mx, my - 3, xr + 2, my - 2);
        p.lineTo(xr + 1, my + 4);
        p.quadraticCurveTo(mx, my + 9, xl - 1, my + 7);
        p.closePath();
      });
      g.fillStyle = TEETH;
      g.fill(m);
      inside(g, m, () => {
        line(g, alpha("#6a5a48", 0.9), 1.6, xl - 2, my + 3.5, xr + 2, my + 1.5);
        for (let x = xl + 3; x < xr; x += 5)
          line(g, alpha("#9a8c78", 0.9), 1, x, my - 3, x, my + 8);
      });
      g.lineWidth = 2.2;
      g.strokeStyle = INK;
      g.stroke(m);
      break;
    }
  }
}

/** Age, frown and the cheek's warmth. */
function lines(g: G, L: Look, q: Geo): void {
  const { X, nx, ny, mx, my, mw } = q;
  const t = L.t;
  const skd = L.skin[1];
  const c = alpha(skd, 1);
  if (t.age >= 2 || has(t, "lines")) {
    curve(g, c, 1.8, nx - 12, ny + 1, mx - mw * 0.72, my - 6, mx - mw * 0.66, my + 5);
    curve(g, c, 1.4, X(-14), 9, X(-6), 13, X(4), 10);
  }
  if (t.age >= 3) {
    line(g, c, 1.4, X(-20), -2, X(-26), -6);
    line(g, c, 1.4, X(-20), 2, X(-27), 2);
    curve(g, c, 1.5, X(40), ny + 2, mx + mw * 0.55, my - 6, mx + mw * 0.5, my + 4);
    curve(g, c, 1.4, X(-8), q.Y(60), X(-2), q.Y(68), X(6), q.Y(70));
  }
  if (has(t, "frown") || L.mood === "angry") {
    line(g, c, 1.8, X(14), -18, X(16), -8);
    line(g, c, 1.4, X(19), -17, X(20), -10);
  }
  // Warm cheeks for the young, and for smiles.
  const warm = has(t, "rednose") ? 0.45 : t.age === 0 ? 0.3 : L.mood === "smile" ? 0.25 : 0.12;
  g.fillStyle = alpha("#e0786a", warm);
  g.beginPath();
  g.ellipse(X(-8), 24, 11, 6, 0, 0, Math.PI * 2);
  g.ellipse(X(44), 22, 6, 5, 0, 0, Math.PI * 2);
  g.fill();
  if (L.mood === "angry") {
    // Flushed across the brow and cheeks.
    g.fillStyle = alpha("#d0584a", 0.16);
    g.beginPath();
    g.ellipse(X(16), 8, X(44), 30, 0, 0, Math.PI * 2);
    g.fill();
  }
}

function face(g: G, L: Look, q: Geo): void {
  const t = L.t;
  const [sk, skd, skl] = L.skin;
  const head = headPath(t, q);
  const { X, Y } = q;
  g.fillStyle = sk;
  g.fill(head);
  inside(g, head, () => {
    // Form shadow on the near side.
    g.fillStyle = skd;
    g.beginPath();
    g.moveTo(X(-100), -110);
    g.lineTo(X(-26), -110);
    g.bezierCurveTo(X(-30), -50, X(-22), -26, X(-25), -8);
    g.bezierCurveTo(X(-26), 16, X(-16), Y(38), X(-4), Y(54));
    g.quadraticCurveTo(X(6), Y(68), X(8), Y(100));
    g.lineTo(X(-100), Y(100));
    g.closePath();
    g.fill();
    // Eye socket by the bridge, under the lip, under the chin.
    g.beginPath();
    g.ellipse(X(14), -2, 6, 9, 0.2, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = alpha(skd, 0.55);
    g.beginPath();
    g.ellipse(q.mx, q.my + 10, 6, 2.2, 0, 0, Math.PI * 2);
    g.fill();
    // Highlights on the far cheekbone and the brow.
    g.fillStyle = alpha(skl, 0.9);
    g.beginPath();
    g.ellipse(X(47), 13, 5, 8, 0.3, 0, Math.PI * 2);
    g.fill();
    g.beginPath();
    g.ellipse(X(40), -22, 10, 4, -0.1, 0, Math.PI * 2);
    g.fill();
    g.fillStyle = alpha(skl, 0.6);
    g.beginPath();
    g.ellipse(q.cx + 10, q.cy - 5, 5, 3, 0, 0, Math.PI * 2);
    g.fill();
  });
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke(head);
  hairBase(g, L, q);
  lines(g, L, q);
  nose(g, L, q);
  const hurt = L.mood === "hurt";
  if (!has(t, "patch")) eye(g, L, q.e2, 1, false, false);
  eye(g, L, q.e1, 2, true, hurt);
  if (L.mood === "grim") {
    inside(g, head, () => {
      g.fillStyle = alpha(skd, 0.55);
      g.beginPath();
      g.ellipse(X(14), -6, X(40), 9, 0, 0, Math.PI * 2);
      g.fill();
    });
  }
  brow(g, L, X(-6), -15, true);
  brow(g, L, X(37), -15, false);
  if (has(t, "scar") || has(t, "scars")) {
    line(g, alpha("#a0523c", 0.9), 2.2, X(40), Y(28), X(50), Y(40));
    line(g, alpha("#f2c6a6", 0.9), 1, X(41), Y(27), X(51), Y(39));
  }
  if (has(t, "scars")) {
    // An old cut through the near brow, and a nick across the bridge of the nose.
    line(g, alpha("#a0523c", 0.95), 2.6, X(-20), -34, X(4), 4);
    line(g, alpha("#f2c6a6", 0.9), 1.1, X(-19), -35, X(5), 3);
    for (const k of [0.3, 0.55, 0.8]) {
      const x = X(-20 + k * 24);
      const y = -34 + k * 38;
      line(g, alpha("#a0523c", 0.8), 1.4, x - 4, y + 2, x + 4, y - 2);
    }
    line(g, alpha("#a0523c", 0.9), 2, X(18), Y(10), X(30), Y(6));
  }
  if (has(t, "jowls")) {
    // Heavy jowls: folds from the cheeks to the jaw, and a second chin.
    const c = alpha(skd, 1);
    curve(g, c, 2, X(-22), Y(36), X(-24), Y(56), X(-10), Y(70));
    curve(g, c, 1.8, X(52), Y(34), X(52), Y(52), X(42), Y(64));
    curve(g, c, 2, X(-6), Y(86), X(14), Y(94), X(34), Y(84));
  }
}

const BEARD_SHAPE: Record<Beard, { thick: number; drop: number }> = {
  none: { thick: 0, drop: 0 },
  stubble: { thick: 0, drop: 0 },
  goatee: { thick: 0, drop: 0 },
  short: { thick: 4, drop: 2 },
  full: { thick: 9, drop: 22 },
  long: { thick: 8, drop: 66 },
  flowing: { thick: 12, drop: 158 },
  wild: { thick: 13, drop: 30 },
  whiskers: { thick: 12, drop: 20 },
  wisp: { thick: 0, drop: 0 },
};

/** The beard, in head space: over the jaw and down onto the chest. */
function beard(g: G, L: Look, q: Geo): void {
  const t = L.t;
  if (t.beard === "none") return;
  const { X, Y, mx, my, mw, cx, cy, ax, ay, jx, jy } = q;
  const [hc, hd, hl] = L.hair;
  const xl = mx - mw * 0.58;
  const xr = mx + mw * 0.42;
  const r = rng(L.seed ^ 0x51ed27);
  const cheekLine: [number, number][] = [
    [X(-20), Y(4)],
    [X(-14), Y(28)],
    [xl - 4, my],
    [xl + 2, my + 8],
    [mx + 1, my + 11],
    [xr + 2, my + 7],
    [xr + 4, my - 1],
    [X(46), Y(30)],
  ];
  if (t.beard === "stubble") {
    const p = blob([
      ...cheekLine,
      [X(58), Y(28)],
      [jx + 2, jy + 2],
      [cx + 12, cy + 3],
      [ax, ay + 2],
      [X(-32), Y(28)],
      [X(-25), Y(6)],
    ]);
    inside(g, headPath(t, q), () => {
      g.fillStyle = alpha(hc, 0.13);
      g.fill(p);
      g.fillStyle = alpha(hc, 0.4);
      const m = g.getTransform();
      for (let i = 0; i < 140; i++) {
        const x = X(-34) + r() * X(92);
        const y = Y(4) + r() * Y(80);
        const at = m.transformPoint(new DOMPoint(x, y));
        if (g.isPointInPath(p, at.x, at.y)) g.fillRect(x, y, 1.1, 1.1);
      }
    });
    return;
  }
  let pts: [number, number][];
  let tip: [number, number];
  if (t.beard === "goatee" || t.beard === "wisp") {
    // A wisp is a scholar's long, thin beard: a goatee grown down to the chest.
    const len = t.beard === "wisp" ? 46 + t.age * 6 : 12 + t.age * 4;
    tip = [cx + 6, cy + len + 6];
    pts = [
      [mx - 7, my + 8],
      [mx + 2, my + 10],
      [mx + 8, my + 7],
      [cx + 16, cy - 3],
      [cx + 11, cy + len],
      tip,
      [cx - 2, cy + len * 0.5],
      [cx - 4, cy - 2],
    ];
  } else {
    const { thick: T, drop } = BEARD_SHAPE[t.beard];
    const sway = t.beard === "flowing" ? -14 : 0;
    tip = [cx + 4 + sway, cy + T + drop];
    const outer: [number, number][] = [
      [X(57) + T * 0.2, Y(30)],
      [jx + T * 0.6, jy + T * 0.3],
    ];
    if (drop > 24)
      outer.push(
        [cx + 18 + T * 0.6, cy + drop * 0.35],
        [cx + 10 + T * 0.3 + sway * 0.5, cy + drop * 0.75],
      );
    else outer.push([cx + 16 + T * 0.4, cy + T * 0.5 + drop * 0.6]);
    outer.push(tip);
    if (drop > 24)
      outer.push(
        [cx - 6 - T * 0.2 + sway * 0.7, cy + drop * 0.7],
        [cx - 16 - T * 0.4 + sway * 0.2, cy + drop * 0.3],
      );
    else outer.push([cx - 12 - T * 0.4, cy + T * 0.4 + drop * 0.5]);
    outer.push([ax - T * 0.7, ay + T * 0.5], [X(-32) - T * 0.3, Y(30)], [X(-25), Y(8)]);
    if (t.beard === "wild" || t.beard === "whiskers") {
      // Tufts that bristle out round the edge, radiating from the mouth.
      const spike = t.beard === "whiskers" ? 20 : 13;
      const ox = mx - 6;
      const oy = my + 6;
      const rim = [cheekLine[7] as Pt, ...outer];
      for (let i = 0; i + 1 < rim.length; i++) {
        const [x0, y0] = rim[i] as Pt;
        const [x1, y1] = rim[i + 1] as Pt;
        for (let k = 0; k < 3; k++) {
          const u = (k + 0.5) / 3;
          const x = x0 + (x1 - x0) * u;
          const y = y0 + (y1 - y0) * u;
          const dl = Math.hypot(x - ox, y - oy) || 1;
          const dx = (x - ox) / dl;
          const dy = (y - oy) / dl;
          const len = spike * (0.6 + r() * 0.7);
          const tx = x + dx * len + dy * (r() - 0.5) * 8;
          const ty = y + dy * len - dx * (r() - 0.5) * 8;
          const tuft = path((p) => {
            p.moveTo(x - dy * 6, y + dx * 6);
            p.quadraticCurveTo(x + dx * len * 0.5 - dy * 3, y + dy * len * 0.5 + dx * 3, tx, ty);
            p.quadraticCurveTo(
              x + dx * len * 0.5 + dy * 3,
              y + dy * len * 0.5 - dx * 3,
              x + dy * 6,
              y - dx * 6,
            );
            p.closePath();
          });
          cel(g, tuft, hc, hd, 2, hl, 1.6);
        }
      }
    }
    pts = [...cheekLine, ...outer];
  }
  const p = blob(pts);
  cel(g, p, hc, hd, 6, hl, LW2);
  inside(g, p, () => {
    // Strands that flow down to the tip.
    const thin = t.beard === "goatee" || t.beard === "wisp";
    const n = t.beard === "flowing" ? 30 : t.beard === "wisp" ? 10 : thin ? 7 : 18;
    for (let i = 0; i < n; i++) {
      const u = r();
      const x = thin ? mx - 6 + u * 16 : X(-22) + u * X(80);
      const y = thin ? my + 8 : Y(30) + Math.abs(u - 0.45) * 50 + r() * 14;
      const k = 0.55 + r() * 0.4;
      const ex = x + (tip[0] - x) * k;
      const ey = y + (tip[1] - y) * k;
      const bend = (r() - 0.5) * 16;
      curve(
        g,
        alpha(i % 3 === 0 ? hl : hd, 0.75),
        1.5,
        x,
        y,
        (x + ex) / 2 + bend,
        (y + ey) / 2,
        ex,
        ey,
      );
    }
  });
}

const STACHE_SHAPE: Record<Stache, { thick: number; drop: number }> = {
  none: { thick: 0, drop: 0 },
  thin: { thick: 2.6, drop: 1 },
  neat: { thick: 4, drop: 2 },
  full: { thick: 6, drop: 4 },
  droop: { thick: 3.6, drop: 17 },
  bristle: { thick: 6.5, drop: 3 },
  long: { thick: 3.4, drop: 32 },
};

/** The moustache over the upper lip, in two halves either side of the philtrum. */
function moustache(g: G, L: Look, q: Geo): void {
  const t = L.t;
  if (t.stache === "none") return;
  const { mx, my, mw } = q;
  const [hc, hd, hl] = L.hair;
  const { thick: th, drop } = STACHE_SHAPE[t.stache];
  const x0 = mx + 1;
  const y0 = my - 8;
  const wide = t.stache === "full" || t.stache === "bristle" ? 4 : 0;
  for (const [d, reach] of [
    [1, mw * 0.42 + 3 + wide],
    [-1, mw * 0.58 + 4 + wide],
  ] as const) {
    const tx = x0 + d * reach;
    const ty = my - 1 + drop * (d > 0 ? 0.75 : 1);
    const bristle = t.stache === "bristle";
    const half = path((p) => {
      p.moveTo(x0 + d * 1.2, y0 - th * 0.6);
      if (bristle) {
        // A stiff brush: the lower edge in short spikes, flaring at the end.
        const ex = tx + d * 5;
        const ey = ty + 3;
        p.quadraticCurveTo(x0 + d * reach * 0.6, y0 - th * 1.1, ex, ey);
        for (let i = 5; i >= 1; i--) {
          const u = i / 6;
          const bx = x0 + d * (reach + 5) * u;
          const by = y0 + th * 0.5 + u * (ey - y0 - th * 0.5);
          p.lineTo(bx + d * 2, by + 4);
          p.lineTo(bx - d * 2, by);
        }
        p.lineTo(x0 + d * 1.2, y0 + th * 0.5);
      } else if (drop > 8) {
        p.bezierCurveTo(x0 + d * reach * 0.7, y0 - th, tx + d * 4, y0 + 2, tx, ty);
        p.bezierCurveTo(
          tx - d * 1,
          y0 + 6,
          x0 + d * reach * 0.6,
          y0 + th * 0.6,
          x0 + d * 1.2,
          y0 + th * 0.5,
        );
      } else {
        p.quadraticCurveTo(x0 + d * reach * 0.7, y0 - th * 0.9, tx, ty);
        p.quadraticCurveTo(x0 + d * reach * 0.6, y0 + th * 0.7, x0 + d * 1.2, y0 + th * 0.5);
      }
      p.closePath();
    });
    cel(g, half, hc, hd, 2, hl, 1.6);
  }
}

/** What goes on the head: helmet, caps, headcloths, crowns. */
function headwear(g: G, L: Look, q: Geo, back: boolean): void {
  const { X } = q;
  const { P, hat, t } = L;
  const gilt = has(t, "gilt") || (L.tier2 && (L.fam === "cavalry" || L.fam === "infantry"));
  switch (hat) {
    case "helm": {
      helmet(g, L, q, back, gilt);
      break;
    }
    case "ze":
    case "redze":
    case "court": {
      if (back) return;
      const cap =
        hat === "court"
          ? GAUZE
          : hat === "redze"
            ? RED_CAP
            : t.dress === "servant"
              ? SERVANT_CAP
              : L.fam === "archer"
                ? P.robeDark
                : "#2b2421";
      const capDark =
        hat === "court" ? "#0e0d10" : hat === "redze" ? RED_CAP_DARK : mix(cap, "#000000", 0.4);
      const capLight =
        hat === "court" ? GAUZE_LIGHT : hat === "redze" ? RED_CAP_LIGHT : mix(cap, "#ffffff", 0.25);
      // 幘: a cloth cap over the topknot with a raised back; lower under the court cap.
      const ze = path((p) => {
        p.moveTo(X(54), -40);
        if (hat === "court") {
          p.bezierCurveTo(X(52), -78, X(24), -92, X(-8), -92);
          p.bezierCurveTo(X(-46), -92, X(-74), -70, X(-66), -22);
        } else {
          p.bezierCurveTo(X(52), -80, X(24), -96, X(-6), -96);
          p.quadraticCurveTo(X(-30), -98, X(-40), -116);
          p.quadraticCurveTo(X(-62), -112, X(-66), -88);
          p.bezierCurveTo(X(-76), -66, X(-74), -40, X(-66), -22);
        }
        p.quadraticCurveTo(X(-36), -36, X(54), -40);
        p.closePath();
      });
      cel(g, ze, cap, capDark, 7, capLight, LW);
      if (hat !== "court")
        inside(g, ze, () => {
          curve(g, alpha(capDark, 0.9), 1.6, X(-36), -100, X(-48), -70, X(-60), -40);
        });
      const band = path((p) => {
        p.moveTo(X(55), -38);
        p.quadraticCurveTo(X(-30), -34, X(-67), -20);
        p.lineTo(X(-66), -32);
        p.quadraticCurveTo(X(-30), -48, X(54), -50);
        p.closePath();
      });
      const trim = L.tier2 || has(t, "gilt");
      cel(
        g,
        band,
        trim ? GOLD : hat === "court" ? "#3a3640" : P.edge,
        trim ? GOLD_DARK : hat === "court" ? "#1c1a20" : mix(P.edge, "#5a4a30", 0.35),
        3,
        trim ? GOLD_LIGHT : undefined,
      );
      if (hat === "court") courtCap(g, L, q);
      break;
    }
    case "lunjin": {
      if (back) {
        // Ribbons hanging behind.
        for (const k of [0, 1]) {
          const rp = path((p) => {
            p.moveTo(X(-58 + k * 8), -70);
            p.bezierCurveTo(X(-90 - k * 4), -20, X(-70 - k * 10), 40, X(-86 - k * 6), 110 + k * 16);
            p.lineTo(X(-72 - k * 6), 108 + k * 16);
            p.bezierCurveTo(X(-58 - k * 10), 40, X(-76 - k * 4), -20, X(-46 + k * 8), -70);
            p.closePath();
          });
          cel(g, rp, SILK_CAP, SILK_DARK, 4, SILK_LIGHT);
        }
        return;
      }
      // The silk headcloth (綸巾): wrapped round the head, gathered at the back.
      const cloth = path((p) => {
        p.moveTo(X(56), -38);
        p.bezierCurveTo(X(60), -90, X(24), -114, X(-14), -112);
        p.quadraticCurveTo(X(-34), -112, X(-44), -122);
        p.quadraticCurveTo(X(-66), -114, X(-72), -86);
        p.quadraticCurveTo(X(-80), -50, X(-68), -22);
        p.quadraticCurveTo(X(-30), -40, X(56), -38);
        p.closePath();
      });
      cel(g, cloth, SILK_CAP, SILK_DARK, 8, SILK_LIGHT, LW);
      inside(g, cloth, () => {
        for (let i = 0; i < 5; i++)
          curve(
            g,
            alpha(i % 2 ? SILK_DARK : SILK_LIGHT, 0.8),
            1.8,
            X(46 - i * 16),
            -46,
            X(30 - i * 18),
            -84,
            X(-20 - i * 8),
            -114,
          );
      });
      // The wrapped band across the brow, its end tucked in at the temple.
      const wrap = path((p) => {
        p.moveTo(X(57), -36);
        p.quadraticCurveTo(X(-30), -36, X(-69), -21);
        p.lineTo(X(-70), -34);
        p.quadraticCurveTo(X(-30), -52, X(56), -50);
        p.closePath();
      });
      cel(g, wrap, mix(SILK_CAP, SILK_LIGHT, 0.45), SILK_DARK, 3, SILK_LIGHT);
      curve(g, INK, 1.6, X(24), -50, X(18), -42, X(22), -35);
      // The knot at the back.
      cel(g, ellipse(X(-62), -76, 13, 11, 0.4), SILK_CAP, SILK_DARK, 4, SILK_LIGHT);
      break;
    }
    case "scarf": {
      // Guan Yu's green head-scarf, knotted at the back with two tails.
      if (back) {
        for (const k of [0, 1]) {
          const tail = path((p) => {
            p.moveTo(X(-60), -60 + k * 8);
            p.bezierCurveTo(X(-96), -40 + k * 10, X(-90), 10, X(-108 + k * 10), 50 + k * 20);
            p.lineTo(X(-94 + k * 10), 52 + k * 20);
            p.bezierCurveTo(X(-78), 10, X(-80), -30, X(-50), -52 + k * 8);
            p.closePath();
          });
          cel(g, tail, GREEN, GREEN_DARK, 5, GREEN_LIGHT);
        }
        return;
      }
      const sc = path((p) => {
        p.moveTo(X(57), -36);
        p.bezierCurveTo(X(60), -86, X(28), -108, X(-8), -108);
        p.bezierCurveTo(X(-48), -108, X(-76), -82, X(-74), -48);
        p.quadraticCurveTo(X(-74), -30, X(-66), -18);
        p.quadraticCurveTo(X(-30), -38, X(57), -36);
        p.closePath();
      });
      cel(g, sc, GREEN, GREEN_DARK, 9, GREEN_LIGHT, LW);
      inside(g, sc, () => {
        for (let i = 0; i < 4; i++)
          curve(
            g,
            alpha(GREEN_DARK, 0.85),
            2,
            X(50 - i * 4),
            -46 - i * 12,
            X(-10),
            -54 - i * 16,
            X(-66),
            -34 - i * 14,
          );
      });
      cel(g, ellipse(X(-64), -58, 14, 12, 0.3), GREEN, GREEN_DARK, 4, GREEN_LIGHT);
      break;
    }
    case "huangjin": {
      yellowCloth(g, q, back);
      break;
    }
    case "wrap": {
      headWrap(g, L, q, back);
      break;
    }
    case "knot":
    case "crown":
    case "rake": {
      if (back) {
        if (hat === "crown" && has(t, "feathers")) pheasant(g, X(-12), -112);
        if (hat === "rake")
          // A feather tucked into the headband's knot.
          feather(g, [X(-66), -40], [X(-80), -96], [X(-116), -116], 9, "#e9e2cf", "#7a6a4c", 10);
        return;
      }
      // The topknot (髻).
      const [hc, hd, hl] = L.hair;
      const bun = ellipse(X(-12), -106, 18, 13, -0.2);
      cel(g, bun, hc, hd, 4, hl);
      if (hat === "knot" || hat === "rake") {
        const tie = path((p) => p.roundRect(X(-28), -100, X(32), 9, 3));
        cel(g, tie, P.tassel, P.robeDark, 3, mix(P.tassel, "#ffffff", 0.35));
        const tails = path((p) => {
          p.moveTo(X(-26), -96);
          p.quadraticCurveTo(X(-46), -100, X(-54), -82);
          p.quadraticCurveTo(X(-42), -88, X(-28), -88);
          p.closePath();
        });
        cel(g, tails, P.tassel, P.robeDark, 3);
      }
      if (hat === "rake" || has(t, "band")) {
        // A headband in the side's colour.
        const hb = path((p) => {
          p.moveTo(X(56), -36);
          p.quadraticCurveTo(X(-20), -42, X(-70), -24);
          p.lineTo(X(-70), -34);
          p.quadraticCurveTo(X(-20), -54, X(55), -47);
          p.closePath();
        });
        cel(g, hb, P.tassel, P.robeDark, 3, mix(P.tassel, "#ffffff", 0.35));
      }
      if (hat === "crown" && has(t, "jade")) {
        // A small jade crown over the knot (小冠), pinned through.
        const cr = path((p) => {
          p.moveTo(X(-32), -96);
          p.lineTo(X(-34), -118);
          p.quadraticCurveTo(X(-30), -136, X(-12), -136);
          p.quadraticCurveTo(X(6), -136, X(8), -118);
          p.lineTo(X(6), -96);
          p.quadraticCurveTo(X(-13), -90, X(-32), -96);
          p.closePath();
        });
        cel(g, cr, "#bcd8c4", "#7fa58c", 5, "#eef7f0", LW);
        inside(g, cr, () => {
          line(g, alpha("#5f8a70", 0.9), 2, X(-12), -134, X(-12), -96);
          curve(g, alpha("#5f8a70", 0.9), 2, X(-32), -106, X(-13), -100, X(6), -106);
        });
        line(g, INK, 6, X(-48), -104, X(22), -110);
        line(g, "#f1ead9", 3, X(-48), -104, X(22), -110);
      } else if (hat === "crown") {
        // A gilt crown on the topknot (束髮冠), set with a jewel and pinned.
        const cr = path((p) => {
          p.moveTo(X(-40), -96);
          p.lineTo(X(-42), -124);
          p.lineTo(X(-28), -112);
          p.lineTo(X(-14), -138);
          p.lineTo(X(0), -112);
          p.lineTo(X(14), -124);
          p.lineTo(X(14), -96);
          p.quadraticCurveTo(X(-13), -88, X(-40), -96);
          p.closePath();
        });
        cel(g, cr, GOLD, GOLD_DARK, 5, GOLD_LIGHT, LW);
        inside(g, cr, () => curve(g, GOLD_DARK, 2, X(-40), -103, X(-13), -96, X(14), -103));
        cel(g, ellipse(X(-13), -110, 6, 6), "#7a3fa0", "#4a1f66", 2, "#c79be0");
        line(g, INK, 6, X(-50), -100, X(26), -108);
        line(g, GOLD_LIGHT, 3, X(-50), -100, X(26), -108);
      }
      break;
    }
  }
}

/**
 * The Yellow Turbans' head-cloth (黃巾): a yellow cloth wrapped over the crown
 * and the topknot, a twisted band round the brow, knotted at the back with two
 * short tails.
 */
function yellowCloth(g: G, q: Geo, back: boolean): void {
  const { X } = q;
  if (back) {
    for (const k of [0, 1]) {
      const tail = ribbon(
        [X(-64), -44 + k * 6],
        [X(-92 - k * 6), -26 + k * 10],
        [X(-98 + k * 14), 20 + k * 16],
        14 - k * 2,
        8,
      );
      cel(g, tail, YELLOW, YELLOW_DARK, 4, YELLOW_LIGHT);
      inside(g, tail, () =>
        curve(
          g,
          alpha(YELLOW_FOLD, 0.7),
          1.6,
          X(-66),
          -40,
          X(-90 - k * 6),
          -22,
          X(-96 + k * 14),
          18,
        ),
      );
    }
    return;
  }
  // The cloth over the crown, gathered up over the topknot.
  const cloth = path((p) => {
    p.moveTo(X(58), -38);
    p.bezierCurveTo(X(60), -78, X(36), -98, X(8), -102);
    p.bezierCurveTo(X(12), -114, X(0), -122, X(-15), -122);
    p.bezierCurveTo(X(-32), -122, X(-42), -112, X(-38), -101);
    p.bezierCurveTo(X(-62), -96, X(-78), -74, X(-74), -44);
    p.quadraticCurveTo(X(-74), -28, X(-66), -18);
    p.quadraticCurveTo(X(-30), -38, X(58), -38);
    p.closePath();
  });
  cel(g, cloth, YELLOW, YELLOW_DARK, 9, YELLOW_LIGHT, LW);
  inside(g, cloth, () => {
    // Creases fanning out round the head from the knot at the back, each with
    // a lit edge above it; and the cloth gathered round the topknot.
    const creases: readonly (readonly number[])[] = [
      [X(-62), -38, X(-4), -54, X(52), -54],
      [X(-60), -46, X(-22), -82, X(40), -76],
      [X(-62), -52, X(-44), -92, X(10), -98],
      [X(-66), -48, X(-70), -74, X(-46), -96],
      [X(4), -104, X(-14), -96, X(-36), -104],
    ];
    for (const c of creases) {
      curve(g, alpha(YELLOW_FOLD, 0.7), 2.2, ...c);
      curve(g, alpha(YELLOW_LIGHT, 0.8), 1.6, ...c.map((v, i) => v + (i % 2 ? -3 : 1)));
    }
    g.fillStyle = alpha(YELLOW_LIGHT, 0.55);
    g.beginPath();
    g.ellipse(X(34), -76, 12, 5, -0.7, 0, Math.PI * 2);
    g.ellipse(X(-10), -118, 7, 3.5, -0.3, 0, Math.PI * 2);
    g.fill();
  });
  // The twisted band round the brow.
  const band = path((p) => {
    p.moveTo(X(60), -34);
    p.quadraticCurveTo(X(-30), -32, X(-70), -16);
    p.lineTo(X(-72), -34);
    p.quadraticCurveTo(X(-30), -54, X(58), -52);
    p.closePath();
  });
  cel(g, band, mix(YELLOW, YELLOW_DARK, 0.2), YELLOW_DARK, 4, YELLOW_LIGHT, LW);
  inside(g, band, () => {
    for (let i = 0; i < 9; i++) {
      const u = i / 8;
      const x = X(56 - u * 126);
      const y = -44 + u * 12 - Math.sin(u * Math.PI) * 8;
      curve(g, alpha(YELLOW_FOLD, 0.85), 2, x + 6, y - 9, x + 1, y - 1, x - 4, y + 9);
    }
  });
  // The knot at the back, over the band.
  cel(g, ellipse(X(-68), -32, 12, 10, 0.5), YELLOW, YELLOW_DARK, 3, YELLOW_LIGHT);
  curve(g, alpha(YELLOW_FOLD, 0.9), 1.6, X(-74), -36, X(-68), -30, X(-62), -36);
}

/** A bandit's rough head-wrap: a broad strip of drab cloth tied low over the brow, frayed at the ends. */
function headWrap(g: G, L: Look, q: Geo, back: boolean): void {
  const { X } = q;
  if (back) {
    for (const k of [0, 1]) {
      const tail = ribbon(
        [X(-66), -30 + k * 4],
        [X(-90 - k * 8), -14 + k * 8],
        [X(-92 + k * 16), 30 + k * 10],
        13 - k * 2,
        9,
      );
      cel(g, tail, RAG, RAG_DARK, 4, RAG_LIGHT);
      // Frayed ends.
      const [x, y] = onCurve(
        [X(-66), -30 + k * 4],
        [X(-90 - k * 8), -14 + k * 8],
        [X(-92 + k * 16), 30 + k * 10],
        1,
      );
      for (let i = -2; i <= 2; i++)
        line(g, RAG_DARK, 1.6, x + i * 2, y, x + i * 3, y + 7 + (i % 2) * 3);
    }
    return;
  }
  // The bare topknot, tied with a scrap of the same cloth.
  const [hc, hd, hl] = L.hair;
  cel(g, ellipse(X(-12), -106, 18, 13, -0.2), hc, hd, 4, hl);
  cel(
    g,
    path((p) => p.roundRect(X(-26), -100, X(28), 8, 3)),
    RAG,
    RAG_DARK,
    3,
  );
  // The wrap: broad, ragged along the top edge.
  const wrap = path((p) => {
    p.moveTo(X(60), -30);
    p.quadraticCurveTo(X(-24), -34, X(-72), -14);
    p.lineTo(X(-74), -44);
    for (let i = 1; i <= 10; i++) {
      const u = i / 10;
      const x = X(-74 + u * 132);
      const y = -50 - Math.sin(u * Math.PI) * 12 + u * 2 + (i % 2 ? -3 : 2);
      p.lineTo(x, y);
    }
    p.closePath();
  });
  cel(g, wrap, RAG, RAG_DARK, 5, RAG_LIGHT, LW);
  inside(g, wrap, () => {
    // Coarse folds and a darned tear.
    for (let i = 0; i < 4; i++)
      curve(
        g,
        alpha(RAG_DARK, 0.8),
        1.8,
        X(50 - i * 34),
        -54 + i * 2,
        X(40 - i * 34),
        -44,
        X(46 - i * 34),
        -34 + i * 4,
      );
    line(g, alpha(RAG_LIGHT, 0.9), 1.4, X(14), -50, X(26), -46);
    line(g, alpha(RAG_LIGHT, 0.9), 1.4, X(16), -44, X(24), -52);
  });
  // The knot at the back of the head.
  cel(g, ellipse(X(-70), -28, 12, 10, 0.4), RAG, RAG_DARK, 3, RAG_LIGHT);
  // The wrap's shadow on the brow.
  inside(g, headPath(L.t, q), () => {
    g.fillStyle = alpha(L.skin[1], 0.8);
    g.beginPath();
    g.moveTo(X(62), -30);
    g.quadraticCurveTo(X(-24), -34, X(-76), -12);
    g.lineTo(X(-76), -6);
    g.quadraticCurveTo(X(-24), -27, X(62), -25);
    g.closePath();
    g.fill();
  });
}

type HelmStyle = "lamellar" | "banded" | "lacquer" | "open" | "crested";

function helmStyle(L: Look): HelmStyle {
  const t = L.t;
  if (has(t, "ears")) return "open";
  if (L.tier2 && L.fam === "infantry") return "crested";
  if (t.helm) return t.helm;
  if (has(t, "silver") || has(t, "gilt")) return "lamellar";
  return (
    (["lamellar", "banded", "lacquer", "lamellar", "banded"] as const)[L.seed % 5] ?? "lamellar"
  );
}

/** A helmet (兜鍪): a dome over cheek and neck flaps, a brow band, a finial, a tassel (纓). */
function helmet(g: G, L: Look, q: Geo, back: boolean, gilt: boolean): void {
  const { X } = q;
  const { P, t } = L;
  const officer = L.named || L.tier2;
  if (back) {
    // The far one of a cavalry officer's two pheasant plumes (鶡冠).
    if (L.fam === "cavalry" && officer) plume(g, X(30), -98, X(2), -150, X(-24), -168);
    return;
  }
  const style = helmStyle(L);
  const steel = has(t, "silver");
  const lacquer = style === "lacquer";
  const base = steel ? STEEL : lacquer ? LACQUER : IRON;
  const dark = steel ? STEEL_DARK : lacquer ? LACQUER_DARK : IRON_DARK;
  const light = steel ? STEEL_LIGHT : lacquer ? LACQUER_LIGHT : IRON_LIGHT;
  const metal = gilt ? GOLD : BRONZE;
  const metalDark = gilt ? GOLD_DARK : BRONZE_DARK;
  const metalLight = gilt ? GOLD_LIGHT : BRONZE_LIGHT;
  // Cheek and neck flaps; an open helmet has only the neck guard, behind the ear.
  const front = style === "open" ? -50 : -22;
  const flap = path((p) => {
    p.moveTo(X(front), -26);
    p.quadraticCurveTo(X(front + 2), 10, X(front - 4), 36);
    p.quadraticCurveTo(X(-52), 50, X(-80), 40);
    p.quadraticCurveTo(X(-84), 0, X(-72), -24);
    p.closePath();
  });
  lamellae(g, flap, P.lace, {
    x0: X(-90),
    x1: X(-10),
    y0: -24,
    y1: 52,
    row: 10,
    plate: 7,
    sag: 3,
    base,
    dark,
    light,
  });
  if (style !== "open") {
    const farFlap = poly(X(54), -26, X(62), -24, X(64), 6, X(57), 8);
    lamellae(g, farFlap, P.lace, {
      x0: X(50),
      x1: X(66),
      y0: -26,
      y1: 10,
      row: 9,
      plate: 6,
      base,
      dark,
      light,
    });
  }
  // A tall horsehair crest for heavy infantry, behind the dome.
  if (style === "crested") {
    const crest = path((p) => {
      p.moveTo(X(-4), -112);
      p.bezierCurveTo(X(10), -150, X(-30), -170, X(-70), -150);
      p.bezierCurveTo(X(-96), -138, X(-100), -110, X(-92), -84);
      p.bezierCurveTo(X(-80), -110, X(-50), -126, X(-24), -110);
      p.closePath();
    });
    cel(g, crest, P.tassel, P.robeDark, 6, mix(P.tassel, "#ffffff", 0.35));
    inside(g, crest, () => {
      for (let i = 0; i < 9; i++)
        curve(
          g,
          alpha(P.robeDark, 0.7),
          1.4,
          X(-6 - i * 3),
          -114,
          X(-30 - i * 6),
          -150 + i * 4,
          X(-90 + i * 4),
          -100 + i * 2,
        );
    });
  }
  // The dome.
  const dome = path((p) => {
    p.moveTo(X(60), -26);
    p.bezierCurveTo(X(62), -78, X(30), -110, X(-6), -110);
    p.bezierCurveTo(X(-46), -110, X(-80), -80, X(-76), -18);
    p.quadraticCurveTo(X(-30), -36, X(60), -26);
    p.closePath();
  });
  if (style === "banded") {
    // Rows of lamellae round the dome.
    lamellae(g, dome, P.lace, {
      x0: X(-80),
      x1: X(64),
      y0: -112,
      y1: -24,
      row: 12,
      plate: 9,
      sag: -12,
      base,
      dark,
      light,
    });
    g.lineWidth = LW;
    g.strokeStyle = INK;
    g.stroke(dome);
  } else {
    cel(g, dome, base, dark, 9, light, LW);
    inside(g, dome, () => {
      if (lacquer) {
        // Lacquered leather: red-lacquered ribs and a red band above the brow.
        for (let i = -3; i <= 3; i++)
          curve(
            g,
            alpha(LACQUER_RED, 0.9),
            2.4,
            X(-8 + i * 4),
            -110,
            X(-8 + i * 14),
            -76,
            X(-8 + i * 21),
            -36,
          );
        const red = path((p) => {
          p.moveTo(X(62), -36);
          p.quadraticCurveTo(X(-30), -46, X(-78), -26);
          p.lineTo(X(-78), -36);
          p.quadraticCurveTo(X(-30), -58, X(62), -47);
          p.closePath();
        });
        g.fillStyle = LACQUER_RED;
        g.fill(red);
        g.strokeStyle = BRONZE_LIGHT;
        g.lineWidth = 1.6;
        g.beginPath();
        for (let i = 0; i <= 28; i++) {
          const u = i / 28;
          const x = X(60 - u * 138);
          const y = -42 - Math.sin(u * Math.PI) * 9 + u * 10 + Math.sin(i * 1.6) * 2;
          if (i === 0) g.moveTo(x, y);
          else g.lineTo(x, y);
        }
        g.stroke();
      } else
        for (let i = -7; i <= 7; i++)
          curve(
            g,
            alpha("#1c1e22", 0.55),
            1.4,
            X(-8 + i * 3),
            -112,
            X(-8 + i * 9),
            -70,
            X(-8 + i * 11),
            -20,
          );
      g.fillStyle = alpha(light, lacquer ? 0.7 : 0.5);
      g.beginPath();
      g.ellipse(X(26), -86, 12, 6, -0.6, 0, Math.PI * 2);
      g.fill();
    });
  }
  // Brow band.
  const band = path((p) => {
    p.moveTo(X(61), -24);
    p.quadraticCurveTo(X(-30), -34, X(-77), -16);
    p.lineTo(X(-77), -28);
    p.quadraticCurveTo(X(-30), -48, X(61), -38);
    p.closePath();
  });
  if (gilt || officer) cel(g, band, metal, metalDark, 4, metalLight);
  else if (lacquer) cel(g, band, LACQUER_RED, "#6e2019", 4, "#c9584a");
  else cel(g, band, dark, "#33363c", 4, base);
  if (lacquer || style === "banded")
    for (let i = 0; i < 6; i++) {
      const u = i / 5;
      const x = X(56 - u * 128);
      const y = -31 - Math.sin(u * Math.PI) * 9 + u * 9;
      cel(g, ellipse(x, y, 2.4, 2.4), BRONZE, BRONZE_DARK, 1, undefined, 1);
    }
  // A ridge down the front of the dome for officers.
  if (officer && style !== "banded") {
    const ridge = path((p) => {
      p.moveTo(X(-10), -108);
      p.bezierCurveTo(X(14), -106, X(30), -80, X(32), -40);
      p.lineTo(X(42), -40);
      p.bezierCurveTo(X(40), -84, X(22), -110, X(-4), -112);
      p.closePath();
    });
    cel(g, ridge, metal, metalDark, 3, metalLight);
  }
  // Finial and tassel (纓).
  const tx = X(-8);
  const ty = -110;
  if (style !== "crested") {
    const tassel = path((p) => {
      p.moveTo(tx, ty - 6);
      p.quadraticCurveTo(tx - 20, ty - 30, tx - 34, ty - 18);
      p.quadraticCurveTo(tx - 24, ty - 10, tx - 40, ty + 2);
      p.quadraticCurveTo(tx - 16, ty + 2, tx, ty);
      p.closePath();
    });
    cel(g, tassel, P.tassel, P.robeDark, 4, mix(P.tassel, "#ffffff", 0.35));
  }
  cel(
    g,
    path((p) => p.roundRect(tx - 6, ty - 10, 12, 12, 3)),
    metal,
    metalDark,
    2,
  );
  if (L.fam === "cavalry" && officer) plume(g, X(-60), -76, X(-84), -120, X(-112), -134);
  // The brim's shadow on the brow.
  inside(g, headPath(t, q), () => {
    g.fillStyle = alpha(L.skin[1], 0.85);
    g.beginPath();
    g.moveTo(X(62), -24);
    g.quadraticCurveTo(X(-30), -34, X(-80), -16);
    g.lineTo(X(-80), -10);
    g.quadraticCurveTo(X(-30), -28, X(62), -19);
    g.closePath();
    g.fill();
  });
}

/** The court cap (進賢冠): a stiff front piece rising and sloping back, with ridges. */
function courtCap(g: G, L: Look, q: Geo): void {
  const { X } = q;
  const gilt = has(L.t, "gilt");
  const ridges = gilt || L.tier2 ? 3 : 2;
  const trim = gilt ? GOLD : "#8d8798";
  // The front piece (展筩): two panels of stiff black gauze, high at the front
  // and sloping back, joined across the top by the ridges (梁).
  const panel = (dx: number, dy: number) =>
    path((p) => {
      p.moveTo(X(42) + dx, -56 + dy);
      p.lineTo(X(48) + dx, -134 + dy);
      p.quadraticCurveTo(X(8) + dx, -128 + dy, X(-34) + dx, -100 + dy);
      p.lineTo(X(-38) + dx, -84 + dy);
      p.quadraticCurveTo(X(4) + dx, -100 + dy, X(42) + dx, -56 + dy);
      p.closePath();
    });
  for (const [dx, dy, a] of [
    [9, -5, 0.5],
    [0, 0, 0.72],
  ] as const) {
    const pn = panel(dx, dy);
    g.fillStyle = alpha(GAUZE, a);
    g.fill(pn);
    inside(g, pn, () => {
      for (let k = -140; k < 40; k += 6)
        line(g, alpha("#8d8798", 0.22), 1, X(-40), k, X(50), k - 40);
    });
    g.lineWidth = LW;
    g.strokeStyle = INK;
    g.stroke(pn);
    g.lineWidth = 1.6;
    g.strokeStyle = gilt ? GOLD : alpha("#8d8798", 0.8);
    g.stroke(pn);
  }
  for (let i = 0; i < ridges; i++) {
    const u = (i + 0.5) / ridges;
    const x = X(48 - u * 80);
    const y = -134 + u * 32 + u * u * 4;
    line(g, INK, 5, x, y, x + 9, y - 5);
    line(g, trim, 2.4, x, y, x + 9, y - 5);
  }
  // A hairpin through the cap.
  line(g, INK, 6, X(-58), -78, X(52), -90);
  line(g, gilt ? GOLD_LIGHT : "#d8d2c0", 3, X(-58), -78, X(52), -90);
  if (gilt) cel(g, ellipse(X(-58), -78, 5, 5), "#5aa58a", "#2e6a54", 2);
}

type Pt = readonly [number, number];

/** A point on a quadratic curve and the unit normal there. */
function onCurve(a: Pt, c: Pt, b: Pt, u: number): [number, number, number, number] {
  const v = 1 - u;
  const x = v * v * a[0] + 2 * v * u * c[0] + u * u * b[0];
  const y = v * v * a[1] + 2 * v * u * c[1] + u * u * b[1];
  const dx = 2 * v * (c[0] - a[0]) + 2 * u * (b[0] - c[0]);
  const dy = 2 * v * (c[1] - a[1]) + 2 * u * (b[1] - c[1]);
  const l = Math.hypot(dx, dy) || 1;
  return [x, y, -dy / l, dx / l];
}

/** A band along a curve, tapering from width w0 to w1: feathers, ribbons, tails. */
function ribbon(a: Pt, c: Pt, b: Pt, w0: number, w1: number, n = 18): Path2D {
  const left: Pt[] = [];
  const right: Pt[] = [];
  for (let i = 0; i <= n; i++) {
    const u = i / n;
    const [x, y, nx, ny] = onCurve(a, c, b, u);
    const w = (w0 + (w1 - w0) * u) / 2;
    left.push([x + nx * w, y + ny * w]);
    right.push([x - nx * w, y - ny * w]);
  }
  return path((p) => {
    const first = left[0] as Pt;
    p.moveTo(first[0], first[1]);
    for (const [x, y] of left) p.lineTo(x, y);
    for (const [x, y] of right.reverse()) p.lineTo(x, y);
    p.closePath();
  });
}

/** A barred tail feather along a curve. */
function feather(
  g: G,
  a: Pt,
  c: Pt,
  b: Pt,
  w: number,
  light: string,
  dark: string,
  bars: number,
): void {
  const f = ribbon(a, c, b, w, 2);
  cel(g, f, light, mix(light, dark, 0.5), 2, mix(light, "#ffffff", 0.4));
  inside(g, f, () => {
    for (let i = 1; i < bars; i++) {
      const [x, y, nx, ny] = onCurve(a, c, b, i / bars);
      line(
        g,
        i % 2 ? dark : alpha("#fff6e0", 0.8),
        3,
        x + nx * w,
        y + ny * w + 2,
        x - nx * w,
        y - ny * w - 2,
      );
    }
  });
}

/** A pheasant tail feather standing from the side of a cavalry officer's helmet (鶡冠). */
function plume(g: G, x0: number, y0: number, cx: number, cy: number, x1: number, y1: number): void {
  feather(g, [x0, y0], [cx, cy], [x1, y1], 9, FEATHER, FEATHER_DARK, 9);
}

/** Lu Bu's two long pheasant feathers, arching back from his crown. */
function pheasant(g: G, x: number, y: number): void {
  feather(g, [x - 4, y - 8], [x - 40, y - 64], [x - 150, y - 14], 15, "#c08440", "#3a2614", 18);
  feather(g, [x + 6, y - 8], [x + 70, y - 52], [x + 150, y + 4], 14, "#c08440", "#3a2614", 18);
}

/** Things held or hung in front: the feather fan, an official's tablet, bells. */
function front(g: G, L: Look): void {
  const { fam, hat, t } = L;
  // A Taoist's staff for Zhang Jiao and the rebels' preachers; a cudgel for the
  // foot (household thugs, and the night watch's five-coloured staves).
  const staff = has(t, "staff") || (t.dress === "rebel" && fam === "strategist");
  const foot = fam === "infantry" || fam === "martialArtist";
  const capped = hat === "court" || hat === "ze" || hat === "redze";
  if (staff) taoistStaff(g);
  else if (foot && (has(t, "club") || has(t, "staves"))) cudgel(g, has(t, "staves"));
  else if (fam === "strategist" && !capped) {
    // A white feather fan (羽扇) held up at the shoulder.
    g.save();
    g.translate(222, 292);
    g.rotate(-0.35);
    cel(
      g,
      path((p) => p.roundRect(-5, 10, 10, 50, 4)),
      "#8a5a2c",
      "#5a3a1c",
      2,
    );
    const fan = path((p) => {
      p.moveTo(0, 14);
      p.bezierCurveTo(-46, -10, -44, -84, 0, -96);
      p.bezierCurveTo(44, -84, 46, -10, 0, 14);
      p.closePath();
    });
    cel(g, fan, "#f8f4ec", "#d4cdbf", 6, "#ffffff", LW);
    inside(g, fan, () => {
      for (let i = -5; i <= 5; i++) line(g, alpha("#9a9080", 0.7), 1.4, 0, 12, i * 9, -96);
    });
    cel(
      g,
      path((p) => p.roundRect(-8, 6, 16, 10, 3)),
      "#3a2a20",
      "#20150f",
      2,
    );
    g.restore();
  } else if (fam === "strategist") {
    // A roll of bamboo slips (簡), tied with a cord in the side's colour.
    g.save();
    g.translate(212, 300);
    g.rotate(-0.42);
    const roll = path((p) => p.roundRect(-15, -48, 30, 96, 13));
    cel(g, roll, "#d6bb82", "#a8884e", 6, "#f2deb0", LW);
    inside(g, roll, () => {
      for (let x = -12; x < 14; x += 5) line(g, alpha("#7d6236", 0.55), 1.2, x, -50, x, 50);
    });
    for (const y of [-22, 20]) {
      line(g, INK, 6, -16, y, 16, y + 2);
      line(g, L.P.tassel, 3.4, -16, y, 16, y + 2);
    }
    cel(g, ellipse(0, -46, 13, 5), "#e8d3a2", "#bfa36c", 2);
    curve(g, alpha("#7d6236", 0.9), 1.2, -6, -46, 0, -50, 6, -46);
    g.restore();
  }
  if (has(t, "bells")) {
    // Gan Ning's bells.
    line(g, INK, 4, 150, 262, 196, 300);
    line(g, "#7a3a28", 2, 150, 262, 196, 300);
    for (const [x, y] of [
      [162, 276],
      [178, 290],
      [194, 304],
    ] as const) {
      const b = path((p) => {
        p.moveTo(x - 7, y + 10);
        p.quadraticCurveTo(x - 7, y - 2, x, y - 2);
        p.quadraticCurveTo(x + 7, y - 2, x + 7, y + 10);
        p.closePath();
      });
      cel(g, b, GOLD, GOLD_DARK, 3, GOLD_LIGHT);
      g.fillStyle = INK;
      g.beginPath();
      g.arc(x, y + 9, 2, 0, Math.PI * 2);
      g.fill();
    }
  }
}

/**
 * The nine-jointed bamboo staff (九節杖) Zhang Jiao healed with, held up at the
 * shoulder, a yellow streamer tied below the top.
 */
function taoistStaff(g: G): void {
  g.save();
  g.translate(226, 340);
  g.rotate(-0.1);
  const rod = path((p) => p.roundRect(-5, -292, 10, 300, 4));
  cel(g, rod, BAMBOO, BAMBOO_DARK, 3, BAMBOO_LIGHT, LW2);
  // Nine joints.
  for (let i = 0; i < 9; i++) {
    const y = -276 + i * 31;
    line(g, INK, 4.4, -6.5, y, 6.5, y);
    line(g, BAMBOO_LIGHT, 1.6, -5, y - 2, 5, y - 2);
  }
  // A yellow streamer tied under the top joint.
  for (const k of [0, 1]) {
    const tail = ribbon(
      [0, -262],
      [-22 - k * 8, -244 + k * 10],
      [-14 - k * 18, -206 + k * 14],
      9,
      5,
    );
    cel(g, tail, YELLOW, YELLOW_DARK, 3, YELLOW_LIGHT);
  }
  cel(
    g,
    path((p) => p.roundRect(-8, -268, 16, 9, 3)),
    YELLOW,
    YELLOW_DARK,
    2,
  );
  g.restore();
}

/**
 * A wooden cudgel over the shoulder; or one of the five-coloured staves (五色棒)
 * Cao Cao hung at the gates of Luoyang's northern district, banded in the five colours.
 */
function cudgel(g: G, staves: boolean): void {
  g.save();
  g.translate(222, 344);
  g.rotate(-0.22);
  const club = path((p) => {
    p.moveTo(-5, 0);
    p.lineTo(-9, -178);
    p.quadraticCurveTo(-9, -192, 0, -192);
    p.quadraticCurveTo(9, -192, 9, -178);
    p.lineTo(5, 0);
    p.closePath();
  });
  cel(g, club, WOOD, WOOD_DARK, 4, mix(WOOD, "#ffffff", 0.25), LW);
  if (staves)
    inside(g, club, () => {
      const five = ["#3f6b9a", "#b3362a", "#d8b23a", "#efe8d8", "#24201e"];
      for (let i = 0; i < 10; i++) {
        g.fillStyle = five[i % 5] ?? WOOD;
        g.fillRect(-12, -192 + i * 19, 24, 19);
      }
      g.fillStyle = alpha("#000000", 0.22);
      g.fillRect(-12, -200, 8, 210);
      g.fillStyle = alpha("#ffffff", 0.25);
      g.fillRect(4, -200, 2.5, 210);
    });
  else
    inside(g, club, () => {
      // Grain and a few knots.
      for (const x of [-3, 2]) line(g, alpha(WOOD_DARK, 0.7), 1.2, x, -6, x * 1.4, -186);
      cel(g, ellipse(-2, -120, 3, 5), WOOD_DARK, undefined, 0, undefined, 1);
      cel(g, ellipse(3, -64, 2.5, 4), WOOD_DARK, undefined, 0, undefined, 1);
    });
  g.lineWidth = LW;
  g.strokeStyle = INK;
  g.stroke(club);
  g.restore();
}

/** Behind the body: the ji for cavalry, the bow for archers. */
function backGear(g: G, L: Look): void {
  const { fam, P } = L;
  if (fam === "cavalry") {
    // The ji (戟) held upright behind the far shoulder: a spike and a side blade.
    g.save();
    g.translate(240, 330);
    g.rotate(-0.07);
    cel(
      g,
      path((p) => p.roundRect(-4, -300, 8, 310, 3)),
      WOOD_DARK,
      "#3a2414",
      2,
      WOOD,
      LW2,
    );
    const spike = path((p) => {
      p.moveTo(-4, -298);
      p.quadraticCurveTo(-6, -320, 0, -340);
      p.quadraticCurveTo(6, -320, 4, -298);
      p.closePath();
    });
    cel(g, spike, "#b9c2cc", BLADE_DARK, 2, BLADE);
    const side = path((p) => {
      p.moveTo(-3, -300);
      p.lineTo(-30, -306);
      p.quadraticCurveTo(-38, -300, -34, -292);
      p.lineTo(-3, -288);
      p.closePath();
    });
    cel(g, side, "#b9c2cc", BLADE_DARK, 2, BLADE);
    cel(g, poly(-8, -286, 8, -286, 11, -270, -11, -270), P.tassel, P.robeDark, 3);
    g.restore();
  } else if (fam === "archer") {
    const limb = path((p) => {
      p.moveTo(30, 340);
      p.quadraticCurveTo(4, 250, 24, 170);
      p.quadraticCurveTo(30, 150, 18, 140);
    });
    g.lineCap = "round";
    stroke(
      g,
      alpha("#f0ecdc", 0.9),
      2,
      path((p) => p.moveTo(18, 140)),
    );
    stroke(g, INK, 11, limb);
    stroke(g, "#8a5a2c", 6, limb);
    line(g, alpha("#f0ecdc", 0.9), 1.6, 18, 142, 56, 340);
  }
}

// ---------------------------------------------------------------- compose

function figure(g: G, L: Look): void {
  const q = geo(L.t);
  g.lineJoin = "round";
  g.lineCap = "round";
  backGear(g, L);
  g.save();
  g.translate(HX, HY);
  headwear(g, L, q, true);
  g.restore();
  g.save();
  g.translate(0, -8);
  body(g, L);
  g.restore();
  g.save();
  g.translate(HX, HY);
  neck(g, L, q);
  g.restore();
  g.save();
  g.translate(0, -8);
  chest(g, L);
  g.restore();
  g.save();
  g.translate(HX, HY);
  face(g, L, q);
  ear(g, L, q);
  beard(g, L, q);
  mouth(g, L, q);
  moustache(g, L, q);
  if (has(L.t, "patch")) patch(g, q);
  headwear(g, L, q, false);
  if (L.mood === "hurt") wounds(g, q);
  g.restore();
  front(g, L);
}

/** Xiahou Dun's patch over his lost left eye (the far eye in this view). */
function patch(g: G, q: Geo): void {
  const { X } = q;
  line(g, INK, 5, X(10), -40, X(60), 10);
  line(g, LEATHER_DARK, 3, X(10), -40, X(60), 10);
  line(g, INK, 5, X(-30), -30, X(26), -8);
  line(g, LEATHER_DARK, 3, X(-30), -30, X(26), -8);
  const p = ellipse(q.e2 + 1, 1, 11, 9, 0.2);
  cel(g, p, "#2a201c", "#120d0b", 3, "#4a3c34");
  line(g, alpha("#a0523c", 0.9), 2, q.e2 + 6, 12, q.e2 + 10, 22);
}

function wounds(g: G, q: Geo): void {
  const { X, Y } = q;
  // A cut on the far cheek.
  line(g, INK, 4, X(36), Y(14), X(50), Y(26));
  line(g, BLOOD, 2.4, X(36), Y(14), X(50), Y(26));
  g.fillStyle = BLOOD;
  g.beginPath();
  g.ellipse(X(46), Y(32), 1.8, 3, 0, 0, Math.PI * 2);
  g.fill();
  // A drop of sweat beside the head.
  const d = path((p) => {
    p.moveTo(X(70), -40);
    p.quadraticCurveTo(X(78), -24, X(76), -18);
    p.arc(X(70), -18, 6, 0, Math.PI);
    p.quadraticCurveTo(X(62), -24, X(70), -40);
    p.closePath();
  });
  cel(g, d, SWEAT, "#8fbcd8", 2, "#ffffff");
}

/** The figure gets a margin wider than the rim light, so the rim stops at the frame. */
const M = 12;
let figCanvas: HTMLCanvasElement | null = null;
let rimCanvas: HTMLCanvasElement | null = null;

function canvas(w: number, h: number): HTMLCanvasElement | null {
  if (typeof document === "undefined") return null;
  const c = document.createElement("canvas");
  c.width = w;
  c.height = h;
  return c;
}

/** Paint a portrait onto a fresh canvas, or null when there is no DOM. */
export function portraitCanvas(spec: PortraitSpec): HTMLCanvasElement | null {
  const out = canvas(PORTRAIT_W, PORTRAIT_H);
  figCanvas ??= canvas(PORTRAIT_W + 2 * M, PORTRAIT_H + 2 * M);
  rimCanvas ??= canvas(PORTRAIT_W + 2 * M, PORTRAIT_H + 2 * M);
  if (!out || !figCanvas || !rimCanvas) return null;
  const g = out.getContext("2d");
  const f = figCanvas.getContext("2d");
  const r = rimCanvas.getContext("2d");
  if (!g || !f || !r) return null;
  const fw = figCanvas.width;
  const fh = figCanvas.height;
  const L = resolve(spec);
  background(g, L);
  f.setTransform(1, 0, 0, 1, 0, 0);
  f.clearRect(0, 0, fw, fh);
  f.translate(M, M);
  figure(f, L);
  f.setTransform(1, 0, 0, 1, 0, 0);
  // Rim light: a band just inside the silhouette's edges, cool on the near
  // side (light from behind) and warm on the far side (the key light).
  for (const [dx, dy, wdx, wdy, col, a] of [
    [3, 0, 8, 1, mix(L.P.lace, "#ffffff", 0.45), 0.55],
    [-2.5, 2, -6, 5, "#fff0cc", 0.5],
  ] as const) {
    r.globalCompositeOperation = "source-over";
    r.clearRect(0, 0, fw, fh);
    r.drawImage(figCanvas, dx, dy);
    r.globalCompositeOperation = "destination-in";
    r.drawImage(figCanvas, 0, 0);
    r.globalCompositeOperation = "destination-out";
    r.drawImage(figCanvas, wdx, wdy);
    r.globalCompositeOperation = "source-in";
    r.fillStyle = col;
    r.fillRect(0, 0, fw, fh);
    f.globalAlpha = a;
    f.drawImage(rimCanvas, 0, 0);
    f.globalAlpha = 1;
  }
  // A soft shadow behind the figure, then the figure.
  g.save();
  g.shadowColor = "rgba(0,0,0,0.45)";
  g.shadowBlur = 12;
  g.shadowOffsetX = -4;
  g.shadowOffsetY = 4;
  g.drawImage(figCanvas, -M, -M);
  g.restore();
  // Bottom fade so the bust sits in the frame.
  const fade = g.createLinearGradient(0, 250, 0, PORTRAIT_H);
  fade.addColorStop(0, "rgba(8,6,10,0)");
  fade.addColorStop(1, "rgba(8,6,10,0.45)");
  g.fillStyle = fade;
  g.fillRect(0, 250, PORTRAIT_W, PORTRAIT_H - 250);
  return out;
}

const cache = new Map<string, string>();

/**
 * PNG data URL of the bust, painted once per (id, class family, side, mood)
 * and cached. Never throws: returns "" when no canvas is available.
 */
export function portraitUrl(spec: PortraitSpec): string {
  try {
    const L = resolve(spec);
    const side = SIDES.indexOf(L.P);
    const key = `${spec.id}|${L.fam}${L.tier2 ? "+" : ""}|${side}|${L.mood}`;
    const hit = cache.get(key);
    if (hit !== undefined) return hit;
    const c = portraitCanvas(spec);
    const url = c ? c.toDataURL("image/png") : "";
    if (url) cache.set(key, url);
    return url;
  } catch {
    return "";
  }
}
