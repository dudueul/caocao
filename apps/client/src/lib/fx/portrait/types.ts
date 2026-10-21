/**
 * Types for the painted portraits (ADR 0053, ADR 0070): the public spec, and
 * the trait vocabulary every officer's row is written in.
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

/**
 * Who an officer is drawn with: the faction sets the landscape behind him
 * (the side still sets the dye). `cao` 曹操's people, `yuan` 袁紹's, `lu` 呂布's
 * and the 兗州 rebels, `liu` 劉備 and his brothers, `wu` 江東, `jing` 荊州,
 * `dong` 董卓 and 涼州, `han` the court and its generals, `yt` the Yellow
 * Turbans and outlaws, `xu` 徐州, `zhong` 袁術's 仲, `north` the 烏桓 and 遼東.
 */
export type Faction =
  | "cao"
  | "yuan"
  | "lu"
  | "liu"
  | "wu"
  | "jing"
  | "dong"
  | "han"
  | "yt"
  | "xu"
  | "zhong"
  | "north";

/**
 * Head turn: front, three-quarter right or left, near profile (right), or
 * looking back over the shoulder with the body turned away (周瑜, 周郎顧).
 */
export type Turn = "F" | "R3" | "L3" | "P" | "back";
/** Where the head sits: tall men high (caps at the top edge), short men low. */
export type Crop = "high" | "mid" | "low";
export type Build = "small" | "slight" | "lean" | "normal" | "broad" | "stout" | "huge";
export type FaceShape = "oval" | "long" | "square" | "round" | "tri" | "jowly" | "gaunt";

export type EyeKind =
  | "almond"
  | "narrow"
  | "phoenix"
  | "round"
  | "heavy"
  | "hooded"
  | "small"
  | "large"
  | "gentle";
export type BrowKind = "curved" | "sword" | "knit" | "bushy" | "thin" | "low" | "sparse";
export type NoseKind = "straight" | "broad" | "hook" | "small" | "long" | "bulb";

/**
 * Beards, drawn from strands. `tong` is the Han grammar: moustache ends hanging
 * straight down (同) with a small chin tuft. Reserved for one man each: `slim`
 * (曹操), `bag` (關羽), `tiger` (張飛), `fork` (文醜), `whiteLong` (黃忠).
 */
export type BeardKind =
  | "none"
  | "stub"
  | "mous"
  | "tong"
  | "goat"
  | "slim"
  | "three"
  | "spade"
  | "full"
  | "bag"
  | "tiger"
  | "wild"
  | "fork"
  | "chops"
  | "braid"
  | "wisp"
  | "whiteLong";
export type StacheKind = "none" | "thin" | "tong" | "bristle" | "long";

export type HairTone = "black" | "brown" | "salt" | "grey" | "white";
/** Skin, including 關羽's deep red-brown (重棗), 張飛's dark tan, a sallow and a flushed face. */
export type SkinTone =
  | "fair"
  | "warm"
  | "tan"
  | "ruddy"
  | "pale"
  | "jujube"
  | "dark"
  | "sallow"
  | "flushed";

/**
 * Headgear, chosen per person (ADR 0070), after the Han dress treatise where
 * an office is known. Court caps: 進賢冠 by ridges (`jx1`–`jx3`), 武冠, 鶡冠,
 * 貂蟬冠, 法冠, the 冕. Cloth caps: 帢, 平上幘, 介幘, 赤幘, 孫堅's red wool 幘,
 * 幅巾, 綸巾, the yellow cloth, a tall Taoist cloth. Bands and wraps, bare
 * heads, hats, helmets (lamellar, banded, lacquered, fur-rimmed, gilt, open,
 * black iron, bright steel, pelt-covered, horned, yellow-tied) and crowns.
 */
export type Hat =
  | "jx1"
  | "jx2"
  | "jx3"
  | "wuguan"
  | "heguan"
  | "diaochan"
  | "faguan"
  | "mian"
  | "qia"
  | "zeFlat"
  | "zeRoof"
  | "zeRed"
  | "zeFelt"
  | "fujin"
  | "lunjin"
  | "huangjin"
  | "taoist"
  | "band"
  | "wrap"
  | "brocade"
  | "hood"
  | "knot"
  | "shaved"
  | "li"
  | "huFelt"
  | "furHood"
  | "crownSmall"
  | "crownJade"
  | "crownTails"
  | "crownZhong"
  | "helmLam"
  | "helmBand"
  | "helmLac"
  | "helmFur"
  | "helmGilt"
  | "helmOpen"
  | "helmBlack"
  | "helmSteel"
  | "helmPelt"
  | "helmHorn"
  | "helmYellow";

/**
 * The dress of an army: Han regulars, Yellow Turban rebels in hemp and patched
 * leather, bandits in drab rags, a household's servants, the Liang frontier's
 * heavy armour and fleece; a province's levies in leather, the Bing frontier's
 * dark fur collars, the south's lacquered armour, the emperor's own robe, the
 * 烏桓's fur-edged felt and leather coats (no armour), and a recluse's hemp.
 */
export type Dress =
  | "han"
  | "rebel"
  | "bandit"
  | "servant"
  | "frontier"
  | "levy"
  | "bing"
  | "south"
  | "imperial"
  | "steppe"
  | "hemp";

/** Held in front, below the chin line. */
export type Prop =
  | "handJi"
  | "fan"
  | "slips"
  | "tablet"
  | "cup"
  | "seal"
  | "jadeSeal"
  | "scroll"
  | "staff"
  | "map"
  | "cash"
  | "purse"
  | "tassel"
  | "crossbow"
  | "torch"
  | "sword"
  | "rope"
  | "jar"
  | "chain"
  | "fish"
  | "compass"
  | "model"
  | "jie"
  | "brush"
  | "reins"
  | "ingot"
  | "cane"
  | "plan"
  | "shield"
  | "rod"
  | "book"
  | "pennon"
  | "lantern"
  | "plaque"
  | "edict"
  | "coins"
  | "sabre"
  | "bells"
  | "whip"
  | "bridle"
  | "fetter"
  | "cudgel"
  | "staves"
  | "talisman"
  | "spear";

/** Behind the shoulders, cropped by the frame. */
export type Back =
  | "ji"
  | "jiPennon"
  | "twinJi"
  | "axe"
  | "glaive"
  | "snake"
  | "bow"
  | "bowHigh"
  | "quivers"
  | "plumes"
  | "parasol"
  | "zither"
  | "banners"
  | "ladder"
  | "trident"
  | "crescent"
  | "spear"
  | "dao"
  | "horse";

/** A hero's own sign in place of the shared halo ring. */
export type Motif =
  | "mirror"
  | "sun"
  | "incense"
  | "bars"
  | "river"
  | "burning"
  | "drum"
  | "fire"
  | "battlements"
  | "snow"
  | "streaks"
  | "tents"
  | "pass"
  | "wall"
  | "wheel"
  | "lattice"
  | "grassFire"
  | "moon"
  | "plum";

export type Accent =
  | "purple"
  | "green"
  | "red"
  | "white"
  | "gold"
  | "brocade"
  | "black"
  | "tiger"
  | "vermilion"
  | "jade"
  | "yellow"
  | "teal"
  | "silver";

/**
 * Marks: `patch` (夏侯惇's left eye), `ears` (劉備's lobes), `rednose`, `scar`,
 * `scars`, `thickNeck`, `smirk`, `frown`, `cocked` (one brow up), `lines`,
 * `gilt` (a lord's gold), `brocade` (figured silk), `jowls`, `earring`,
 * `sidelong` (eyes glance away), `strand` (a loose lock), `heavy` (black
 * armour with a neck guard), `clan` (a vermilion cap cord, the 劉 clan),
 * `stoop`, `roar` (mouth open even at rest), `bare` (one shoulder bare),
 * `civil` (no armour whatever the class), `silver` (bright armour), `fur` (a
 * dark fur collar over the robe), `dust` (a dusty, greyed robe),
 * `locks` (side locks on a shaved head), `pelt` (spotted pelt), `forelock`
 * (a white forelock), `sanpaku` (white below the iris), `squint`, `bags`.
 */
export type Mark =
  | "patch"
  | "ears"
  | "rednose"
  | "scar"
  | "scars"
  | "thickNeck"
  | "smirk"
  | "frown"
  | "cocked"
  | "lines"
  | "gilt"
  | "brocade"
  | "jowls"
  | "earring"
  | "sidelong"
  | "strand"
  | "heavy"
  | "clan"
  | "stoop"
  | "roar"
  | "bare"
  | "locks"
  | "forelock"
  | "sanpaku"
  | "squint"
  | "bags"
  | "dust"
  | "fur"
  | "civil"
  | "silver";

/** Where a trait comes from (ADR 0070): the histories, the novel, later lore, or our reading. */
export type Tag = "record" | "novel" | "later" | "ours";

export interface Traits {
  cls: Family;
  /** The officer's own side, used when the caller gives none. */
  home: number;
  fac: Faction;
  /** 0 youth, 1 grown, 2 mature, 3 old. */
  age: number;
  build: Build;
  crop: Crop;
  turn: Turn;
  /** Head tilt in degrees, + toward the viewer's right. */
  roll: number;
  /** Head size in percent (88–110). */
  head: number;
  face: FaceShape;
  /** Face width and lower-face length in percent (100 normal), over the face shape's own. */
  w: number;
  len: number;
  eye: EyeKind;
  /** Eye size in percent. */
  eyeSize: number;
  brow: BrowKind;
  nose: NoseKind;
  /** Mouth width in px. */
  mouth: number;
  beard: BeardKind;
  stache: StacheKind;
  hair: HairTone;
  skin: SkinTone;
  hat: Hat;
  dress: Dress;
  accent?: Accent;
  front?: Prop;
  back?: Back;
  motif?: Motif;
  marks: readonly Mark[];
  /** Which traits come from where; anything unlisted is our reading. */
  src: Readonly<Partial<Record<"hat" | "beard" | "build" | "face" | "prop" | "dress", Tag>>>;
}
