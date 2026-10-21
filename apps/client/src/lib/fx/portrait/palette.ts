/**
 * Colours: Han dyes for the sides (ADR 0046, ADR 0055), skins and hair,
 * materials, and the heroes' accent colours (ADR 0070).
 */
import type { Accent, HairTone, SkinTone } from "./types.ts";

export const IRON = "#767b84";
export const IRON_DARK = "#4c5058";
export const IRON_LIGHT = "#a9aeb6";
export const STEEL = "#a7b0bb";
export const STEEL_DARK = "#6c7682";
export const STEEL_LIGHT = "#e2e7ec";
export const BRONZE = "#c0913e";
export const BRONZE_DARK = "#7f5c22";
export const BRONZE_LIGHT = "#e8c47a";
export const GOLD = "#d9a936";
export const GOLD_DARK = "#8f6519";
export const GOLD_LIGHT = "#f7dd8a";
export const LEATHER = "#6b4529";
export const LEATHER_DARK = "#4a2e1b";
export const LEATHER_LIGHT = "#8d6440";
export const HEMP = "#b8a37a";
export const HEMP_DARK = "#8f7c56";
export const HEMP_LIGHT = "#d3c39c";
export const SILK_CAP = "#26304a";
export const SILK_DARK = "#171d2e";
export const SILK_LIGHT = "#4a5878";
export const GAUZE = "#1f1d22";
export const GAUZE_DARK = "#0e0d10";
export const GAUZE_LIGHT = "#4a4652";
export const GAUZE_THREAD = "#8d8798";
export const LACQUER = "#2b2421";
export const LACQUER_DARK = "#171210";
export const LACQUER_LIGHT = "#6a5a50";
export const LACQUER_RED = "#a33328";
export const FEATHER = "#8a6a44";
export const FEATHER_DARK = "#4e3822";
export const WHITE_CLOTH = "#f1ead9";
export const WHITE_SHADE = "#cdc3ad";
export const SCLERA = "#f8f2e7";
export const MOUTH = "#5c2320";
export const TEETH = "#f6f0e2";
export const BLOOD = "#b3262a";
export const SWEAT = "#cfe8f6";
export const WOOD = "#7a5234";
export const WOOD_DARK = "#54371f";
export const WOOD_LIGHT = "#a07450";
export const BLADE = "#d9e0e7";
export const BLADE_DARK = "#8f9aa6";
export const BLADE_MID = "#b9c2cc";
export const GREEN = "#3f6b46";
export const GREEN_DARK = "#2a4a30";
export const GREEN_LIGHT = "#5f9a68";
/** The Yellow Turbans' head-cloth (黃巾): an ochre yellow, darker in the folds. */
export const YELLOW = "#d8b23a";
export const YELLOW_DARK = "#a07c22";
export const YELLOW_LIGHT = "#f3da84";
export const YELLOW_FOLD = "#7a5a14";
/** A bandit's rough head-wrap. */
export const RAG = "#5c4431";
export const RAG_DARK = "#3b2a1d";
export const RAG_LIGHT = "#86684c";
/** Red caps (赤幘) and red wool. */
export const RED_CAP = "#b5382b";
export const RED_CAP_DARK = "#74201a";
export const RED_CAP_LIGHT = "#de6c5c";
export const SERVANT_CAP = "#3a463f";
export const DRAB = "#7b6a50";
export const DRAB_DARK = "#54462f";
export const DRAB_LIGHT = "#9c8a6c";
/** Sheepskin, for the Liang frontier's fur collars. */
export const FLEECE = "#cdb88f";
export const FLEECE_DARK = "#97815a";
export const FLEECE_LIGHT = "#efe2c2";
export const BAMBOO = "#b9a052";
export const BAMBOO_DARK = "#7c6a2f";
export const BAMBOO_LIGHT = "#e2cf8c";
/** Dark fur (wolf or marten) for the Bing frontier and the north. */
export const FUR: readonly [string, string, string] = ["#5a4636", "#33271d", "#8a7360"];
/** Pale fur (fox or lamb) for a hood. */
export const FUR_PALE: readonly [string, string, string] = ["#b49a78", "#7d6548", "#e2cfb0"];
/** Felt for a Hu warrior's pointed cap and the steppe coats. */
export const FELT = "#9c7c55";
export const FELT_DARK = "#6a5236";
export const FELT_LIGHT = "#c4a57a";
/** Black armour (玄甲): iron left dark, for the elite. */
export const BLACK_IRON = "#50545c";
export const BLACK_IRON_DARK = "#2e3137";
export const BLACK_IRON_LIGHT = "#80868f";
/** The emperor's robe: ochre yellow edged in the Han's red. */
export const IMPERIAL = "#c8952e";
export const IMPERIAL_DARK = "#8c6219";
export const IMPERIAL_LIGHT = "#ecc86e";
export const HAN_RED = "#a8382b";
export const HAN_RED_DARK = "#6c1f17";
export const MIAN_BLACK = "#211c24";
export const MIAN_RED = "#8f2c22";
export const JADE = "#eef2ea";
export const JADE_DARK = "#a3b3a6";
export const SEAL_JADE = "#cfe2cf";
export const SEAL_JADE_DARK = "#8fae96";
export const SEAL_JADE_LIGHT = "#f2faf1";
/** Tiger-striped cloth (虎文), as the Han guard riders' robes were. */
export const TIGER = "#d08a35";
export const TIGER_DARK = "#93561b";
export const TIGER_LIGHT = "#f0c27c";
/** A leopard's pelt. */
export const PELT = "#c99a52";
export const PELT_DARK = "#8a6230";
export const PELT_LIGHT = "#ecc98a";
export const STRAW = "#c9ad6a";
export const STRAW_DARK = "#8e7438";
export const STRAW_LIGHT = "#ead391";
export const VERMILION = "#d4452c";

/** Side colours, as Han dyes (the same as the rigged figures): ours red, the enemy blue (ADR 0055). */
export interface Pal {
  robe: string;
  robeDark: string;
  edge: string;
  lace: string;
  tassel: string;
}

export const SIDES: Pal[] = [
  { robe: "#9b2f28", robeDark: "#6b1d18", edge: "#dccaa0", lace: "#d65a4c", tassel: "#d8392c" },
  { robe: "#2e4a78", robeDark: "#1f3354", edge: "#dccaa0", lace: "#7096d4", tassel: "#3f74d6" },
  { robe: "#c2661e", robeDark: "#874412", edge: "#efe3c0", lace: "#f2a552", tassel: "#f08a2a" },
  { robe: "#a2772c", robeDark: "#6e501b", edge: "#efe3c0", lace: "#d8aa4c", tassel: "#e2b23c" },
];

/** Base, shadow, highlight. */
export const SKINS: Record<SkinTone, readonly [string, string, string]> = {
  fair: ["#f3cfab", "#dca67e", "#fde6cc"],
  warm: ["#e9c09a", "#cb9269", "#f7d8b6"],
  tan: ["#d8a67b", "#b37b52", "#ebc29c"],
  ruddy: ["#c97750", "#9c4e33", "#df9670"],
  pale: ["#f6dfc8", "#dcb89b", "#fff1e2"],
  /** 重棗: the deep red-brown of a dried jujube (關羽, the novel). */
  jujube: ["#9a3b26", "#6c2416", "#c05a40"],
  dark: ["#b98458", "#8c5a36", "#d39f74"],
  sallow: ["#dcc08e", "#b39a68", "#ecd6ab"],
  flushed: ["#efbe9e", "#d08c6c", "#fbd7c0"],
};

/** Base, shadow, sheen. */
export const HAIRS: Record<HairTone, readonly [string, string, string]> = {
  black: ["#1e1816", "#100c0b", "#4a4250"],
  brown: ["#3a2a20", "#22180f", "#6a5242"],
  salt: ["#4a4542", "#2a2624", "#8e8984"],
  grey: ["#8d8780", "#625d58", "#c4beb6"],
  white: ["#e4dfd6", "#b5aea3", "#fbf8f2"],
};

/** A hero's accent: base, shadow, light. */
export const ACCENTS: Record<Accent, readonly [string, string, string]> = {
  purple: ["#6a3a82", "#43214f", "#9a6fb3"],
  green: ["#3f7a4a", "#26502e", "#6aa874"],
  red: ["#c0392b", "#7a1f17", "#e46a5a"],
  white: ["#eef0ee", "#bfc5c4", "#ffffff"],
  gold: ["#d9a936", "#8f6519", "#f7dd8a"],
  brocade: ["#8a3a5a", "#5a2038", "#c87a96"],
  black: ["#2a2626", "#141212", "#55504e"],
  tiger: ["#d08a35", "#93561b", "#f0c27c"],
  vermilion: ["#d4452c", "#8e2614", "#f07a5c"],
  jade: ["#7fb59a", "#4d806a", "#c4e6d4"],
  yellow: ["#d8b23a", "#a07c22", "#f3da84"],
  teal: ["#2f7a80", "#1c4f54", "#5fb0b4"],
  silver: ["#c9cfd6", "#8d969f", "#f2f5f8"],
};
