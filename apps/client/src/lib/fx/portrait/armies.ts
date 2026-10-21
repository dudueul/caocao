/**
 * Story troops are named after their army (`huangjin-infantry-2`); each army
 * has a kit: its side, its dress, what its men wear on their heads by class,
 * what they carry. A soldier's face comes from a hash of his id, drawn only
 * from shapes no hero has reserved (ADR 0070).
 */
import { STACHE_FOR } from "./beard.ts";
import { hash, pick, rng } from "./draw.ts";
import type {
  Back,
  BeardKind,
  BrowKind,
  Build,
  Dress,
  EyeKind,
  FaceShape,
  Faction,
  Family,
  Hat,
  Mark,
  NoseKind,
  Prop,
  SkinTone,
  Traits,
  Turn,
} from "./types.ts";

/** Signatures that belong to one hero each; a generated soldier never draws them. */
export const RESERVED = {
  hats: new Set<Hat>([
    "qia",
    "diaochan",
    "faguan",
    "mian",
    "heguan",
    "crownSmall",
    "crownJade",
    "crownTails",
    "crownZhong",
    "zeFelt",
    "hood",
    "brocade",
    "taoist",
    "li",
    "furHood",
    "helmHorn",
    "helmPelt",
    "helmSteel",
    "helmHood",
    "helmFeather",
    "helmStrap",
    "helmFlat",
    "helmTube",
    "huFelt",
  ]),
  beards: new Set<BeardKind>(["slim", "bag", "tiger", "fork", "whiteLong", "braid"]),
  marks: new Set<Mark>(["patch", "ears", "rednose", "roar", "bare", "forelock", "clan", "cocked"]),
  props: new Set<Prop>([
    "handJi",
    "jie",
    "cane",
    "fish",
    "compass",
    "model",
    "jadeSeal",
    "edict",
    "tassel",
    "bells",
    "chain",
    "pennon",
  ]),
  backs: new Set<Back>([
    "twinJi",
    "parasol",
    "plumes",
    "zither",
    "quivers",
    "horse",
    "glaive",
    "snake",
    "crescent",
    "ladder",
    "banners",
    "bowHigh",
    "jiPennon",
  ]),
};

/** An army's side, dress, landscape, headwear by class, and what its soldiers carry. */
interface Army {
  home: number;
  fac: Faction;
  dress: Dress;
  /** Headwear by class; the first fits every class not listed. */
  hats: Partial<Record<Family | "any", readonly Hat[]>>;
  marks?: readonly Mark[];
  front?: Prop;
  /** Lean of the faces: weathered skins, rougher beards. */
  rough?: boolean;
  young?: boolean;
}

const HAN_HATS: Army["hats"] = {
  any: ["helmLam", "helmBand", "helmLac", "helmLam"],
  archer: ["zeFlat", "zeFlat", "helmOpen"],
  strategist: ["zeRoof", "jx1"],
  martialArtist: ["knot", "band"],
  catapult: ["zeFlat", "zeRoof"],
};

/**
 * cao-… and ward-… (the Luoyang northern district's night watch, in the red
 * caps of constables, with 曹操's five-coloured staves) are ours. The enemy:
 * yuan-…, huangjin-… and qingzhou-… (the yellow cloth), dong-… (董卓's 涼州
 * men in fur), heishan-… (黑山 bandits), jianjia-… (the 蹇 household's
 * servants with cudgels); xu-… (徐州 levies in leather and cloth caps), lu-…
 * (呂布's 并州 men), xianzhen-… (高順's 陷陣營 in black iron), zhang-… (張繡's
 * 涼州 men), jing-… (荊州 lacquer), zhong-… (袁術's gilt guards); wuhuan-…
 * (the 烏桓 horse: shaved heads, felt coats edged in leather, bows), bing-…
 * (高幹's 并州 frontiersmen, fur collars and banded helmets), liu-… (劉備's men
 * at 汝南, plain leather and open helmets like his own). gate-… units are
 * works (ADR 0069) and get no portrait.
 */
export const ARMIES = new Map<string, Army>([
  ["cao", { home: 0, fac: "cao", dress: "han", hats: HAN_HATS }],
  ["ward", { home: 0, fac: "han", dress: "han", hats: { any: ["zeRed"] }, front: "staves" }],
  [
    "yuan",
    {
      home: 1,
      fac: "yuan",
      dress: "han",
      hats: { ...HAN_HATS, any: ["helmBand", "helmLac", "helmLam", "helmBand"] },
    },
  ],
  ["huangjin", { home: 1, fac: "yt", dress: "rebel", hats: { any: ["huangjin"] }, rough: true }],
  [
    "qingzhou",
    {
      home: 1,
      fac: "yt",
      dress: "rebel",
      hats: { any: ["huangjin", "huangjin", "helmYellow"] },
      rough: true,
    },
  ],
  [
    "dong",
    {
      home: 1,
      fac: "dong",
      dress: "frontier",
      hats: { any: ["helmFur", "helmLam"], archer: ["zeFlat"], strategist: ["fujin"] },
      rough: true,
    },
  ],
  ["heishan", { home: 1, fac: "yt", dress: "bandit", hats: { any: ["wrap"] }, rough: true }],
  [
    "jianjia",
    { home: 1, fac: "han", dress: "servant", hats: { any: ["zeFlat"] }, front: "cudgel" },
  ],
  [
    "xu",
    {
      home: 1,
      fac: "xu",
      dress: "levy",
      hats: { any: ["zeFlat", "zeFlat", "helmOpen"], strategist: ["zeRoof"] },
    },
  ],
  [
    "lu",
    {
      home: 1,
      fac: "lu",
      dress: "bing",
      hats: { any: ["helmLam", "helmFur"], archer: ["zeFlat"], strategist: ["fujin"] },
      rough: true,
    },
  ],
  [
    "xianzhen",
    { home: 1, fac: "lu", dress: "han", hats: { any: ["helmBlack"] }, marks: ["heavy"] },
  ],
  [
    "zhang",
    {
      home: 1,
      fac: "dong",
      dress: "frontier",
      hats: { any: ["helmFur", "helmLam"], archer: ["zeFlat"] },
      rough: true,
    },
  ],
  [
    "jing",
    {
      home: 1,
      fac: "jing",
      dress: "south",
      hats: { any: ["helmLac"], archer: ["zeFlat"], strategist: ["zeRoof"] },
    },
  ],
  [
    "zhong",
    {
      home: 1,
      fac: "zhong",
      dress: "han",
      hats: { any: ["helmGilt", "helmLam"], strategist: ["wuguan"] },
      marks: ["gilt", "brocade"],
    },
  ],
  [
    "wuhuan",
    { home: 1, fac: "north", dress: "steppe", hats: { any: ["shaved"] }, rough: true, young: true },
  ],
  [
    "bing",
    {
      home: 1,
      fac: "yuan",
      dress: "bing",
      hats: { any: ["helmBand", "helmFur"], archer: ["zeFlat"] },
      rough: true,
    },
  ],
  [
    "liu",
    {
      home: 1,
      fac: "liu",
      dress: "levy",
      hats: { any: ["helmOpen"], archer: ["zeFlat"], strategist: ["fujin"] },
    },
  ],
]);

const FAMILY_HINTS: readonly [RegExp, Family][] = [
  [/cav|horse|rider|lancer|knight|charger/, "cavalry"],
  [/arch|bow|shoot|marks|crossbow|sniper/, "archer"],
  [/strat|tact|sage|advis|schol|taoist|mage|shaman|bishop|scribe/, "strategist"],
  [/martial|brawl|fist|monk|wrestl|fighter|guard/, "martialArtist"],
  [/cata|siege|engine|treb|ballist|mangon|thrower|thunder|cart/, "catapult"],
  [/inf|foot|spear|pike|sword|halberd|soldier/, "infantry"],
];

export const TIER2: Record<string, Family> = {
  heavyInfantry: "infantry",
  longbow: "archer",
  shockCavalry: "cavalry",
  tactician: "strategist",
  guardian: "martialArtist",
  thunderCart: "catapult",
};

export { FAMILY_HINTS };

export function familyFromId(id: string): Family {
  const lower = id.toLowerCase();
  for (const [re, fam] of FAMILY_HINTS) if (re.test(lower)) return fam;
  return "infantry";
}

/** The headwear an army gives a man of a class. */
export function armyHat(army: Army | undefined, fam: Family, r: () => number): Hat {
  if (!army) return pick(r, HAN_HATS[fam] ?? (HAN_HATS.any as readonly Hat[]));
  const list = army.hats[fam] ?? army.hats.any ?? HAN_HATS.any ?? ["helmLam"];
  return pick(r, list);
}

export function armyOf(id: string): Army | undefined {
  return ARMIES.get(id.toLowerCase().split("-")[0] ?? "");
}

const GEN_BEARDS: readonly BeardKind[] = [
  "stub",
  "mous",
  "tong",
  "goat",
  "three",
  "spade",
  "full",
  "wild",
  "chops",
  "wisp",
  "none",
];
const GEN_EYES: readonly EyeKind[] = [
  "almond",
  "narrow",
  "round",
  "heavy",
  "small",
  "gentle",
  "large",
  "hooded",
];
const GEN_BROWS: readonly BrowKind[] = ["sword", "curved", "knit", "bushy", "thin", "low"];
const GEN_NOSES: readonly NoseKind[] = ["straight", "straight", "broad", "small", "hook", "long"];
const GEN_FACES: readonly FaceShape[] = ["oval", "oval", "long", "square", "round", "tri", "gaunt"];
const GEN_BUILDS: readonly Build[] = ["lean", "normal", "normal", "broad", "slight", "stout"];
const GEN_TURNS: readonly Turn[] = ["R3", "R3", "L3", "F"];

/** A soldier's face from the hash of the id, in his army's kit. */
export function generic(id: string, fam?: Family): Traits {
  const r = rng(hash(id));
  const army = armyOf(id);
  const cls = fam ?? familyFromId(id);
  const rough = army?.rough ?? false;
  let age = pick(r, [0, 1, 1, 1, 2, 2, 3]);
  if (army?.young) age = Math.min(age, 2);
  let beard: BeardKind =
    age === 0 ? pick(r, ["none", "none", "stub", "goat", "mous"]) : pick(r, GEN_BEARDS);
  if (rough && age > 0 && r() < 0.4) beard = pick(r, ["stub", "wild", "full", "spade"]);
  const marks: Mark[] = [];
  if (r() < (rough ? 0.35 : 0.15)) marks.push("scar");
  if (age >= 2) marks.push("lines");
  if (r() < 0.2) marks.push("frown");
  const skin: SkinTone = rough
    ? pick(r, ["warm", "tan", "tan", "ruddy", "dark"])
    : pick(r, ["fair", "warm", "warm", "tan", "pale"]);
  const hat = armyHat(army, cls, r);
  const t: Traits = {
    cls,
    home: army?.home ?? 0,
    fac: army?.fac ?? "cao",
    age,
    build: pick(r, GEN_BUILDS),
    crop: pick(r, ["mid", "mid", "mid", "high", "low"]),
    turn: pick(r, GEN_TURNS),
    roll: Math.round((r() - 0.5) * 6),
    head: 96 + Math.floor(r() * 8),
    face: pick(r, GEN_FACES),
    w: 94 + Math.floor(r() * 12),
    len: 95 + Math.floor(r() * 10),
    eye: pick(r, GEN_EYES),
    eyeSize: 92 + Math.floor(r() * 14),
    brow: pick(r, GEN_BROWS),
    nose: pick(r, GEN_NOSES),
    mouth: 21 + Math.floor(r() * 6),
    beard,
    stache: STACHE_FOR[beard],
    hair:
      age === 3
        ? pick(r, ["salt", "grey"])
        : age === 2 && r() < 0.3
          ? "salt"
          : r() < 0.15
            ? "brown"
            : "black",
    skin,
    hat,
    dress: army?.dress ?? "han",
    marks: [...marks, ...(army?.marks ?? [])],
    src: {},
  };
  if (army?.front) t.front = army.front;
  return unreserved(t);
}

/** Strip any hero's signature from a generated soldier, whatever his army's kit says. */
function unreserved(t: Traits): Traits {
  if (RESERVED.hats.has(t.hat)) t.hat = "helmLam";
  if (RESERVED.beards.has(t.beard)) {
    t.beard = "full";
    t.stache = STACHE_FOR.full;
  }
  t.marks = t.marks.filter((m) => !RESERVED.marks.has(m));
  if (t.front && RESERVED.props.has(t.front)) delete t.front;
  if (t.back && RESERVED.backs.has(t.back)) delete t.back;
  return t;
}
