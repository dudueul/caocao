/**
 * Named officers (ADR 0053, ADR 0070): historical people (public domain),
 * drawn our own way from the histories first and the novel second. Each row
 * gives the faction, the home side, the class, and the traits that differ
 * from the defaults; `src` tags where the headgear, beard, build, face, prop or
 * dress comes from (anything unlisted is our reading). The looks and their
 * sources are tabled in docs/remake/CHARACTER_LOOK.md.
 */
import { STACHE_FOR } from "./beard.ts";
import type { Faction, Family, Hat, Traits } from "./types.ts";

type Row = Partial<Omit<Traits, "cls" | "home" | "fac">> & { hat: Hat };

function o(fac: Faction, home: number, cls: Family, r: Row): Traits {
  const beard = r.beard ?? "tong";
  const build = r.build ?? "normal";
  return {
    cls,
    home,
    fac,
    age: 1,
    build,
    crop: "mid",
    turn: "R3",
    roll: 0,
    head: build === "huge" ? 93 : build === "small" ? 102 : 100,
    face: "oval",
    w: 100,
    len: 100,
    eye: "almond",
    eyeSize: 100,
    brow: "sword",
    nose: "straight",
    mouth: 24,
    beard,
    stache: STACHE_FOR[beard],
    hair: "black",
    skin: "warm",
    dress: "han",
    marks: [],
    src: {},
    ...r,
  };
}

// biome-ignore format: one officer per line reads as a table
export const OFFICERS: Record<string, Traits> = {
  // ------------------------------------------------------------ 曹操's people (ours)
  "cao-cao": o("cao", 0, "cavalry", { age: 2, build: "small", crop: "low", w: 96, eye: "narrow", brow: "sword", mouth: 23, beard: "slim", hat: "qia", accent: "purple", front: "handJi", marks: ["lines", "smirk", "cocked"], src: { hat: "record", build: "record", beard: "novel", prop: "record" } }),
  "xiahou-dun": o("cao", 0, "infantry", { age: 2, build: "broad", turn: "L3", face: "square", eye: "large", brow: "low", beard: "full", skin: "tan", hat: "helmPlain", accent: "red", motif: "mirror", marks: ["patch"], src: { face: "record" } }),
  "xiahou-yuan": o("cao", 0, "archer", { age: 2, build: "lean", turn: "P", face: "long", eye: "narrow", brow: "sword", nose: "hook", beard: "mous", stache: "long", skin: "tan", hat: "zeFlat", back: "bow", motif: "streaks", marks: ["squint"] }),
  "xu-chu": o("cao", 0, "martialArtist", { build: "huge", crop: "high", turn: "F", head: 86, face: "round", w: 106, eye: "small", brow: "low", nose: "broad", mouth: 19, beard: "spade", skin: "tan", hat: "knot", accent: "tiger", marks: ["bare", "thickNeck", "jowls"], src: { build: "record", dress: "novel" } }),
  "dian-wei": o("cao", 0, "martialArtist", { build: "huge", turn: "L3", face: "square", eye: "round", brow: "knit", nose: "broad", mouth: 27, beard: "wild", stache: "bristle", skin: "tan", hat: "band", accent: "red", back: "twinJi", marks: ["scars", "thickNeck", "frown"], src: { build: "record", prop: "record" } }),
  "yue-jin": o("cao", 0, "infantry", { marks: ["scar"], build: "small", crop: "low", turn: "L3", face: "tri", eye: "large", brow: "sword", beard: "stub", hat: "helmOpen", back: "ladder", src: { build: "record", prop: "record" } }),
  "cao-ren": o("cao", 0, "infantry", { marks: ["jaw"], build: "broad", turn: "F", face: "square", eye: "round", eyeSize: 94, brow: "low", nose: "broad", beard: "spade", hat: "helmBand", front: "shield" }),
  "cao-hong": o("cao", 0, "cavalry", { roll: -2, turn: "L3", crop: "low", build: "stout", face: "round", eye: "heavy", brow: "curved", beard: "tong", skin: "ruddy", hat: "helmFeather", front: "purse", marks: ["gilt", "smirk", "jowls"] }),
  "cao-chun": o("cao", 0, "cavalry", { build: "lean", eye: "almond", brow: "thin", beard: "goat", hat: "helmPelt", front: "whip", marks: ["heavy"] }),
  "cao-pi": o("cao", 0, "strategist", { age: 0, build: "slight", face: "long", eye: "narrow", brow: "sword", beard: "mous", stache: "thin", skin: "pale", hat: "crownSmall", front: "cane", marks: ["gilt"], src: { prop: "record" } }),
  "cao-ang": o("cao", 0, "cavalry", { age: 0, build: "lean", turn: "F", eye: "large", brow: "curved", beard: "none", skin: "fair", hat: "helmPlume", front: "reins", src: { prop: "record" } }),
  "cao-song": o("cao", 0, "strategist", { age: 3, build: "stout", crop: "low", turn: "F", face: "jowly", eye: "gentle", brow: "sparse", nose: "small", beard: "wisp", hair: "white", hat: "jx3", front: "cash", marks: ["civil", "brocade", "gilt"], src: { hat: "record", prop: "record" } }),
  "li-dian": o("cao", 0, "infantry", { build: "lean", eye: "gentle", brow: "thin", beard: "tong", skin: "fair", hat: "wuguan", front: "book", src: { prop: "record" } }),
  "yu-jin": o("cao", 0, "infantry", { age: 2, turn: "F", face: "square", eye: "narrow", brow: "low", mouth: 20, beard: "tong", hat: "helmFlat", front: "rod", back: "banners", src: { prop: "record" } }),
  "zhang-liao": o("cao", 0, "cavalry", { marks: ["brow"], roll: -2, turn: "L3", build: "broad", crop: "high", face: "square", eye: "almond", brow: "sword", beard: "full", hat: "helmFur", dress: "bing", back: "jiPennon", src: { prop: "record" } }),
  "xu-huang": o("cao", 0, "infantry", { roll: 2, marks: ["deepset"], age: 2, build: "broad", face: "square", eye: "round", brow: "low", beard: "full", skin: "tan", hat: "helmLac", back: "axe", src: { prop: "novel" } }),
  "zhang-he": o("cao", 0, "cavalry", { build: "lean", turn: "L3", eye: "almond", brow: "curved", beard: "goat", skin: "fair", hat: "heguan", front: "plan", src: { hat: "record" } }),
  "xun-yu": o("cao", 0, "strategist", { build: "lean", crop: "high", turn: "F", eye: "almond", brow: "sword", beard: "mous", stache: "thin", skin: "fair", hat: "diaochan", front: "tablet", motif: "incense", src: { hat: "record", face: "record" } }),
  "xun-you": o("cao", 0, "strategist", { age: 2, turn: "L3", face: "long", eye: "heavy", brow: "low", beard: "three", hair: "salt", hat: "zeRoof", front: "slips", marks: ["stoop"] }),
  "guo-jia": o("cao", 0, "strategist", { age: 0, build: "slight", roll: 6, face: "tri", eye: "narrow", brow: "thin", beard: "none", skin: "pale", hat: "fujin", front: "cup", marks: ["smirk", "strand"], src: { prop: "record" } }),
  "cheng-yu": o("cao", 0, "strategist", { age: 3, build: "lean", crop: "high", turn: "L3", face: "long", len: 106, eye: "narrow", brow: "knit", beard: "three", hair: "grey", skin: "tan", hat: "jx2", motif: "sun", marks: ["frown"], src: { build: "record", beard: "record" } }),
  "liu-ye": o("cao", 0, "catapult", { eye: "round", eyeSize: 94, brow: "curved", beard: "tong", hat: "jx2", front: "model", marks: ["clan"], src: { hat: "record" } }),
  "man-chong": o("cao", 0, "strategist", { turn: "F", face: "square", eye: "narrow", brow: "low", mouth: 20, beard: "spade", hat: "faguan", front: "slips", marks: ["frown"] }),
  "xu-you": o("cao", 0, "strategist", { age: 3, build: "slight", turn: "L3", face: "tri", eye: "small", brow: "thin", nose: "hook", beard: "goat", hair: "salt", hat: "fujin", front: "map", marks: ["smirk", "cocked"], src: { prop: "record" } }),
  "liu-yan": o("cao", 0, "strategist", { age: 2, turn: "L3", face: "square", eye: "heavy", brow: "knit", beard: "full", hat: "jx2", motif: "battlements", marks: ["dust", "lines"], src: { hat: "record" } }),
  "bao-xin": o("cao", 0, "infantry", { turn: "L3", age: 2, crop: "high", eye: "gentle", brow: "curved", beard: "full", hat: "zeFlat" }),
  "wei-zi": o("cao", 0, "strategist", { age: 3, build: "stout", turn: "F", face: "round", eye: "gentle", brow: "sparse", nose: "small", beard: "three", hair: "salt", hat: "jx1", front: "coins", marks: ["brocade", "lines"] }),
  "chen-gong": o("cao", 0, "strategist", { age: 2, build: "lean", turn: "P", face: "long", eye: "narrow", brow: "sword", beard: "goat", skin: "fair", hat: "jx1", marks: ["frown"] }),
  "cai-yang": o("cao", 0, "cavalry", { age: 3, build: "stout", turn: "L3", face: "square", eye: "round", eyeSize: 94, brow: "bushy", nose: "broad", beard: "three", hair: "salt", skin: "tan", hat: "helmCloth", motif: "drum", marks: ["frown", "mole"] }),
  "xin-pi": o("cao", 0, "strategist", { build: "broad", turn: "F", face: "square", eye: "round", brow: "bushy", beard: "spade", hat: "jx1", front: "jie", marks: ["frown"], src: { prop: "record" } }),
  "tian-chou": o("cao", 0, "strategist", { age: 2, build: "lean", turn: "L3", face: "gaunt", eye: "almond", brow: "sword", beard: "three", skin: "tan", hat: "li", dress: "hemp", motif: "pass", src: { dress: "record" } }),
  "ma-jun": o("cao", 0, "catapult", { face: "round", eye: "small", brow: "low", mouth: 20, beard: "stub", hat: "zeRoof", dress: "hemp", front: "compass", src: { prop: "record" } }),
  // ------------------------------------------------------------ 袁紹 and his people
  "yuan-shao": o("yuan", 1, "strategist", { age: 2, build: "broad", crop: "high", turn: "F", roll: -2, w: 102, eye: "heavy", brow: "curved", beard: "full", skin: "fair", hat: "fujin", accent: "gold", front: "pennon", marks: ["gilt", "brocade"], src: { hat: "record", face: "record" } }),
  "yuan-tan": o("yuan", 1, "cavalry", { marks: ["gilt"], turn: "L3", eye: "gentle", brow: "knit", beard: "goat", skin: "fair", hat: "crownSmall" }),
  "yuan-xi": o("yuan", 1, "cavalry", { marks: ["gilt"], turn: "L3", face: "round", eye: "heavy", brow: "low", beard: "mous", stache: "thin", skin: "fair", hat: "zeFlat" }),
  "yuan-shang": o("yuan", 1, "cavalry", { age: 0, build: "slight", face: "long", eye: "phoenix", brow: "thin", beard: "none", skin: "pale", hat: "helmPlume", marks: ["brocade", "gilt"], src: { face: "record" } }),
  "yan-liang": o("yuan", 1, "cavalry", { roll: -2, age: 2, build: "broad", face: "square", eye: "round", brow: "knit", mouth: 21, beard: "full", stache: "bristle", skin: "tan", hat: "helmTube", crop: "low", back: "parasol", marks: ["gilt", "brow"], src: { prop: "record" } }),
  "wen-chou": o("yuan", 1, "cavalry", { roll: 3, marks: ["jaw"], age: 2, build: "broad", crop: "high", turn: "L3", face: "round", eye: "round", brow: "bushy", nose: "broad", beard: "fork", skin: "tan", hat: "helmHorn", back: "spear" }),
  "tian-feng": o("yuan", 1, "strategist", { age: 3, build: "lean", crop: "high", turn: "F", face: "gaunt", eye: "large", brow: "knit", beard: "full", hair: "grey", hat: "jx2", front: "chain", motif: "bars", src: { prop: "record" } }),
  "ju-shou": o("yuan", 1, "strategist", { age: 2, build: "lean", turn: "L3", face: "long", eye: "heavy", brow: "low", beard: "three", hat: "wuguan", motif: "river", src: { hat: "record" } }),
  "shen-pei": o("yuan", 1, "strategist", { age: 2, build: "broad", turn: "L3", face: "square", eye: "narrow", brow: "knit", beard: "three", hat: "jx1", front: "sword", motif: "wall", marks: ["civil"], src: { prop: "record" } }),
  "guo-tu": o("yuan", 1, "strategist", { age: 2, build: "slight", turn: "P", face: "tri", eye: "narrow", brow: "thin", nose: "hook", beard: "goat", hat: "zeRoof", front: "fan", marks: ["smirk", "sidelong"] }),
  "feng-ji": o("yuan", 1, "strategist", { age: 2, turn: "F", roll: -3, eye: "small", brow: "thin", mouth: 20, beard: "tong", hat: "jx1", marks: ["smirk"] }),
  "han-meng": o("yuan", 1, "infantry", { roll: 4, build: "lean", crop: "high", face: "tri", eye: "small", brow: "sword", beard: "mous", stache: "thin", hat: "helmCap", motif: "wheel", marks: ["gap", "hide"] }),
  "jiang-qi": o("yuan", 1, "infantry", { marks: ["deepset"], turn: "F", eye: "almond", brow: "low", beard: "full", hat: "helmSpike", accent: "white" }),
  "chunyu-qiong": o("yuan", 1, "infantry", { age: 3, build: "stout", crop: "low", face: "jowly", eye: "heavy", brow: "bushy", nose: "bulb", beard: "wild", stache: "tong", hair: "grey", hat: "knot", front: "cup", motif: "fire", marks: ["rednose", "strand"], src: { face: "novel" } }),
  "gao-lan": o("yuan", 0, "infantry", { roll: -3, turn: "L3", age: 2, build: "broad", face: "square", eye: "small", brow: "knit", beard: "stub", skin: "tan", hat: "helmHood", front: "sabre", marks: ["broken"] }),
  "qu-yi": o("yuan", 1, "archer", { age: 2, build: "lean", face: "gaunt", eye: "narrow", brow: "low", beard: "chops", hair: "salt", skin: "tan", hat: "zeRed", front: "crossbow", marks: ["scar"], src: { hat: "record", prop: "record" } }),
  "gao-gan": o("yuan", 1, "cavalry", { roll: -3, marks: ["mole"], age: 2, build: "lean", turn: "L3", face: "long", eye: "narrow", brow: "sword", beard: "goat", stache: "long", hat: "helmFur", dress: "bing" }),
  "ma-yan": o("yuan", 1, "cavalry", { marks: ["thickNeck"], build: "broad", turn: "F", face: "square", eye: "round", brow: "knit", beard: "spade", skin: "ruddy", hat: "helmCloth" }),
  "zhang-yi": o("yuan", 1, "infantry", { turn: "L3", age: 2, build: "lean", face: "long", eye: "narrow", brow: "thin", nose: "hook", beard: "goat", hat: "helmLac", marks: ["sidelong"] }),
  "yin-kai": o("yuan", 1, "infantry", { turn: "L3", eye: "small", brow: "low", beard: "full", skin: "tan", hat: "zeRed", marks: ["scar", "heavy"] }),
  "xin-ping": o("yuan", 1, "strategist", { age: 3, face: "square", eye: "round", eyeSize: 90, brow: "sparse", beard: "three", hair: "grey", hat: "jx2", marks: ["lines"] }),
  "wang-xiu": o("yuan", 1, "strategist", { age: 2, build: "lean", turn: "L3", face: "long", eye: "heavy", brow: "low", beard: "three", hat: "zeRoof", front: "tablet", marks: ["frown"] }),
  "chen-lin": o("yuan", 1, "strategist", { age: 2, build: "slight", eye: "almond", brow: "thin", beard: "goat", skin: "fair", hat: "fujin", front: "brush", src: { prop: "record" } }),
  // ------------------------------------------------------------ 呂布's army and the 兗州 rebels
  "lu-bu": o("lu", 3, "cavalry", { roll: -3, build: "broad", crop: "high", face: "long", eye: "large", brow: "sword", beard: "none", skin: "fair", hat: "crownTails", back: "horse", dress: "bing", marks: ["gilt", "brocade", "frown"], src: { hat: "novel", prop: "novel" } }),
  "gao-shun": o("lu", 1, "infantry", { age: 2, build: "lean", turn: "F", face: "long", eye: "narrow", brow: "low", mouth: 20, beard: "mous", stache: "thin", skin: "tan", hat: "helmBlack", front: "shield", marks: ["heavy", "deepset"], src: { hat: "record" } }),
  "hou-cheng": o("lu", 1, "cavalry", { roll: 3, marks: ["jaw"], age: 2, build: "stout", face: "round", eye: "heavy", brow: "knit", beard: "full", skin: "ruddy", hat: "helmFur", dress: "bing", front: "jar", src: { prop: "record" } }),
  "song-xian": o("lu", 1, "infantry", { marks: ["broken"], build: "lean", face: "tri", eye: "narrow", nose: "hook", beard: "goat", hat: "helmCloth", dress: "bing", front: "rope", src: { prop: "record" } }),
  "wei-xu": o("lu", 1, "infantry", { roll: 3, turn: "F", eye: "small", brow: "thin", beard: "stub", hat: "helmSpike", dress: "bing", marks: ["gap", "redlac"] }),
  "zang-ba": o("lu", 1, "infantry", { build: "broad", turn: "L3", face: "square", eye: "round", brow: "bushy", beard: "wild", stache: "bristle", skin: "tan", hat: "wrap", front: "fetter", marks: ["scar"] }),
  "zhang-miao": o("lu", 1, "strategist", { age: 2, build: "stout", turn: "F", face: "round", eye: "gentle", brow: "curved", beard: "full", skin: "fair", hat: "jx2", front: "coins", marks: ["brocade"] }),
  "tian-shi": o("lu", 1, "strategist", { age: 2, build: "slight", turn: "P", face: "tri", eye: "narrow", brow: "thin", beard: "wisp", skin: "fair", hat: "fujin", accent: "brocade", motif: "burning", marks: ["brocade", "gilt", "smirk", "sidelong"] }),
  // ------------------------------------------------------------ 劉備 and his brothers
  "liu-bei": o("liu", 1, "infantry", { age: 2, turn: "F", eye: "gentle", eyeSize: 104, brow: "curved", beard: "none", skin: "fair", hat: "jx2", front: "tassel", marks: ["ears", "clan", "brocade"], src: { beard: "record", face: "record", hat: "record", prop: "record" } }),
  "guan-yu": o("liu", 0, "cavalry", { age: 2, build: "broad", crop: "high", turn: "L3", face: "long", eye: "phoenix", eyeSize: 106, brow: "curved", beard: "bag", skin: "jujube", hat: "hood", accent: "green", back: "glaive", src: { beard: "record", face: "novel", hat: "later", prop: "novel" } }),
  "zhang-fei": o("liu", 2, "infantry", { build: "broad", turn: "F", face: "round", eye: "round", eyeSize: 108, brow: "bushy", nose: "broad", mouth: 28, beard: "tiger", skin: "dark", hat: "band", accent: "black", back: "snake", marks: ["roar"], src: { face: "novel", prop: "novel" } }),
  "zhao-yun": o("liu", 2, "cavalry", { roll: 2, age: 0, build: "broad", crop: "high", face: "round", w: 104, eye: "large", brow: "curved", beard: "none", skin: "fair", hat: "helmSteel", accent: "white", back: "spear", marks: ["silver"], src: { face: "novel" } }),
  // ------------------------------------------------------------ 江東
  "sun-jian": o("wu", 0, "infantry", { roll: -2, build: "broad", crop: "high", turn: "L3", face: "square", eye: "round", brow: "sword", beard: "spade", skin: "tan", hat: "zeFelt", accent: "red", front: "sabre", src: { hat: "record", prop: "novel" } }),
  "zhou-yu": o("wu", 3, "strategist", { age: 0, build: "lean", turn: "back", eye: "large", brow: "thin", beard: "none", skin: "fair", hat: "crownJade", accent: "jade", back: "zither", src: { face: "record", prop: "record" } }),
  "gan-ning": o("wu", 3, "infantry", { roll: 4, build: "lean", turn: "L3", face: "tri", eye: "narrow", brow: "sword", beard: "stub", skin: "tan", hat: "brocade", accent: "brocade", back: "plumes", front: "bells", marks: ["scar", "smirk"], src: { prop: "record", hat: "record" } }),
  "taishi-ci": o("wu", 3, "archer", { build: "lean", face: "long", eye: "almond", brow: "sword", beard: "full", hat: "zeFlat", back: "bowHigh", src: { build: "record", beard: "record" } }),
  "zhuge-liang": o("wu", 2, "strategist", { age: 0, build: "lean", crop: "high", turn: "F", eye: "almond", brow: "thin", beard: "mous", stache: "thin", skin: "fair", hat: "lunjin", accent: "white", front: "fan", src: { hat: "later", prop: "later", build: "record" } }),
  // ------------------------------------------------------------ 荊州
  "huang-zhong": o("jing", 2, "archer", { roll: -2, age: 3, build: "broad", turn: "L3", face: "square", eye: "narrow", brow: "bushy", beard: "whiteLong", hair: "white", hat: "helmLam", back: "bow", marks: ["squint"], src: { beard: "later" } }),
  "liu-biao": o("jing", 1, "strategist", { age: 3, build: "lean", crop: "high", turn: "F", face: "long", eye: "gentle", brow: "curved", beard: "three", hair: "salt", skin: "fair", hat: "jx2", front: "scroll", marks: ["clan"], src: { build: "record", hat: "record" } }),
  // ------------------------------------------------------------ 董卓 and the 涼州 men
  "dong-zhuo": o("dong", 1, "cavalry", { age: 2, build: "huge", crop: "high", turn: "F", face: "jowly", eye: "small", brow: "bushy", nose: "broad", beard: "full", skin: "ruddy", hat: "jx3", back: "quivers", marks: ["fur", "gilt", "smirk", "civil"], src: { build: "record", prop: "record", hat: "record" } }),
  "hua-xiong": o("dong", 1, "infantry", { roll: 2, build: "huge", face: "round", eye: "round", brow: "bushy", beard: "full", stache: "bristle", skin: "tan", hat: "helmFur", dress: "frontier", back: "dao", src: { prop: "novel" } }),
  "xu-rong": o("dong", 1, "cavalry", { age: 2, build: "lean", face: "long", eye: "narrow", brow: "sword", beard: "tong", hat: "helmLac" }),
  "zhang-xiu": o("dong", 1, "cavalry", { roll: -2, turn: "L3", eye: "round", brow: "sword", beard: "spade", skin: "tan", hat: "helmSpike", dress: "frontier", back: "spear" }),
  "jia-xu": o("dong", 1, "strategist", { age: 2, build: "slight", turn: "L3", face: "long", eye: "hooded", brow: "thin", beard: "wisp", hair: "salt", hat: "fujin", accent: "black", motif: "lattice", marks: ["sanpaku"] }),
  "hu-cheer": o("dong", 1, "martialArtist", { build: "huge", face: "round", eye: "round", brow: "bushy", beard: "braid", skin: "ruddy", hat: "huFelt", dress: "frontier", front: "ingot", marks: ["earring", "thickNeck"], src: { dress: "ours" } }),
  // ------------------------------------------------------------ the Han court and its generals
  "xian-di": o("han", 0, "strategist", { age: 0, build: "slight", crop: "low", turn: "F", eye: "gentle", brow: "thin", nose: "small", beard: "none", skin: "pale", hat: "mian", dress: "imperial", front: "edict", marks: ["civil", "brocade"], src: { hat: "record", dress: "record", prop: "novel" } }),
  "huangfu-song": o("han", 0, "cavalry", { age: 3, build: "broad", turn: "F", face: "square", eye: "heavy", brow: "bushy", beard: "three", hair: "grey", hat: "heguan", front: "torch", motif: "fire", src: { hat: "record", prop: "record" } }),
  "zhu-jun": o("han", 0, "infantry", { roll: 2, age: 2, build: "lean", turn: "L3", face: "long", eye: "narrow", brow: "knit", beard: "three", skin: "tan", hat: "helmLac" }),
  "yang-feng": o("han", 1, "infantry", { age: 2, build: "stout", turn: "L3", face: "round", eye: "small", brow: "bushy", beard: "full", skin: "ruddy", hat: "wrap", accent: "white", dress: "bandit", marks: ["gilt", "scar"] }),
  "xu-shao": o("han", 0, "strategist", { age: 2, build: "slight", turn: "F", eye: "narrow", brow: "thin", beard: "goat", skin: "fair", hat: "jx1", front: "brush", marks: ["squint", "cocked"], src: { prop: "record" } }),
  "jian-tu": o("han", 1, "infantry", { age: 2, build: "stout", turn: "L3", face: "jowly", eye: "small", brow: "thin", beard: "goat", skin: "fair", hat: "fujin", accent: "gold", front: "lantern", marks: ["civil", "brocade", "gilt", "smirk"], src: { prop: "record" } }),
  // ------------------------------------------------------------ the Yellow Turbans, 黑山 and 汝南
  "zhang-jiao": o("yt", 1, "strategist", { age: 3, build: "lean", crop: "high", turn: "F", face: "long", eye: "large", brow: "knit", beard: "three", hair: "salt", hat: "taoist", dress: "rebel", front: "staff", src: { prop: "record", hat: "record" } }),
  "bo-cai": o("yt", 1, "infantry", { roll: 3, age: 2, build: "broad", face: "square", eye: "round", brow: "bushy", beard: "wild", stache: "bristle", skin: "tan", hat: "huangjin", dress: "rebel", motif: "grassFire", src: { hat: "record" } }),
  "bai-rao": o("yt", 1, "infantry", { build: "lean", turn: "L3", face: "tri", eye: "narrow", brow: "sword", nose: "hook", beard: "stub", skin: "tan", hat: "wrap", dress: "bandit", front: "cudgel", marks: ["forelock", "scars"] }),
  "liu-pi": o("yt", 1, "infantry", { age: 2, build: "lean", face: "long", eye: "narrow", nose: "hook", beard: "goat", hair: "salt", skin: "tan", hat: "helmYellow", dress: "rebel", marks: ["scar"], src: { hat: "record" } }),
  "gong-du": o("yt", 1, "infantry", { build: "stout", turn: "F", face: "round", eye: "small", brow: "low", nose: "broad", beard: "stub", skin: "ruddy", hat: "huangjin", dress: "rebel", src: { hat: "record" } }),
  // ------------------------------------------------------------ 徐州
  "tao-qian": o("xu", 1, "strategist", { age: 3, build: "slight", crop: "low", turn: "L3", face: "long", eye: "gentle", brow: "sparse", beard: "wisp", hair: "white", skin: "fair", hat: "jx2", front: "seal", marks: ["stoop", "civil"], src: { prop: "record" } }),
  "cao-bao": o("xu", 1, "cavalry", { age: 2, build: "stout", face: "round", eye: "heavy", brow: "low", beard: "full", skin: "ruddy", hat: "helmLam", marks: ["frown"] }),
  "chen-deng": o("xu", 1, "strategist", { build: "lean", roll: 7, eye: "narrow", brow: "thin", beard: "goat", skin: "flushed", hat: "fujin", front: "fish", marks: ["strand", "smirk"], src: { face: "record", prop: "record" } }),
  // ------------------------------------------------------------ 袁術's 仲
  "yuan-shu": o("zhong", 1, "strategist", { age: 2, build: "stout", crop: "high", turn: "F", face: "round", eye: "heavy", brow: "thin", beard: "goat", skin: "pale", hat: "crownZhong", accent: "gold", front: "jadeSeal", marks: ["civil", "brocade", "gilt", "smirk"], src: { prop: "record" } }),
  "qiao-rui": o("zhong", 1, "infantry", { age: 3, build: "lean", turn: "L3", face: "long", eye: "narrow", brow: "low", beard: "three", hair: "salt", skin: "tan", hat: "helmGilt", marks: ["scar"] }),
  "zhang-xun": o("zhong", 1, "cavalry", { age: 2, build: "stout", face: "round", eye: "heavy", brow: "curved", beard: "tong", hat: "wuguan", marks: ["gilt", "brocade", "smirk"] }),
  "ji-ling": o("zhong", 1, "infantry", { roll: -2, age: 2, build: "huge", turn: "L3", face: "square", eye: "small", brow: "bushy", nose: "broad", beard: "spade", skin: "tan", hat: "helmStrap", back: "trident", marks: ["thickNeck"], src: { prop: "novel" } }),
  // ------------------------------------------------------------ the 烏桓 and 遼東
  "ta-dun": o("north", 1, "cavalry", { roll: -2, age: 2, build: "broad", crop: "high", turn: "L3", face: "square", eye: "round", brow: "knit", beard: "full", skin: "ruddy", hat: "shaved", dress: "steppe", back: "bow", motif: "tents", marks: ["locks", "earring", "gilt"], src: { hat: "record", dress: "record" } }),
  "lou-ban": o("north", 1, "cavalry", { age: 0, build: "slight", eye: "almond", brow: "thin", beard: "none", skin: "tan", hat: "shaved", dress: "steppe", front: "plaque", marks: ["locks", "earring", "gilt"], src: { hat: "record", dress: "record" } }),
  "su-puyan": o("north", 1, "cavalry", { age: 3, build: "stout", turn: "F", face: "square", eye: "small", brow: "sparse", nose: "hook", beard: "braid", hair: "grey", skin: "tan", hat: "furHood", dress: "steppe", marks: ["earring"], src: { dress: "record" } }),
  "gongsun-kang": o("north", 1, "strategist", { age: 2, build: "stout", face: "square", eye: "narrow", brow: "low", beard: "full", skin: "fair", hat: "jx2", motif: "snow", marks: ["fur", "gilt"] }),
};

export const PORTRAIT_OFFICERS: readonly string[] = Object.keys(OFFICERS);
