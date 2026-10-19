/**
 * The six music tracks: original pentatonic pieces written for this game in
 * jianpu (1 2 3 5 6 = gong shang jue zhi yu; see jianpu.ts). Accompaniment
 * patterns are written once and moved along the scale bar by bar (`shift`);
 * melodies are written out in full. Bars are separated by "|".
 */
import type { Track } from "./index.ts";
import type { Song } from "./music.ts";

/** `bars` bars of rests at `div` tokens per beat (4/4). */
const rest = (bars: number, div = 2): string =>
  Array(bars * 4 * div)
    .fill(".")
    .join(" ");

/** Stately, slow: zither arpeggios over a drone, the flute enters, an occasional gong. Gong mode on D. */
const title: Song = {
  bpm: 70,
  beats: 4,
  bars: 16,
  key: 62,
  gain: 0.7,
  seed: 11,
  parts: [
    {
      inst: "zheng",
      div: 2,
      vel: 0.28,
      pan: -0.2,
      drop: 0.05,
      shift: [0, 0, -1, -1, 1, 1, -2, -2, 0, 0, 2, 2, -1, -1, -2, -2],
      notes: "1, 5, 1 3 5 3 1 5, | 1, 5, 1 2 3 5 3 2",
    },
    {
      inst: "qin",
      div: 1,
      pan: 0.1,
      shift: [0, 0, -1, -1, 1, 1, -2, -2, 0, 0, 2, 2, -1, -1, -2, -2],
      notes: "1, . . 5,,",
    },
    {
      inst: "pad",
      div: 1,
      shift: [0, 0, -1, -1, 1, 1, -2, -2, 0, 0, 2, 2, -1, -1, -2, -2],
      notes: "1,, - - - - - - -",
    },
    {
      inst: "pad",
      div: 1,
      vel: 0.04,
      shift: [0, 0, -1, -1, 1, 1, -2, -2, 0, 0, 2, 2, -1, -1, -2, -2],
      notes: "5,, - - - - - - -",
    },
    {
      inst: "flute",
      div: 2,
      oct: 1,
      pan: 0.15,
      grace: 0.25,
      notes: [
        rest(4),
        "6 - - 5 6 - 1' - | 2' - - - 1' 6 5 - | 5 - 3 5 2 - - - | 1 2 3 - 2 - . .",
        "3 - 5 - 6 5 3 - | 5 - - - 3 2 1 - | 3 - - 5 6 - 1' 6 | 5 - 6 5 3 - - -",
        "1 - 2 3 2 - 1 6, | 1 - - - - - . . | . . 2 3 5 - 3 2 | 2 - - - - - . .",
      ].join(" "),
    },
    {
      inst: "perc",
      div: 1,
      vel: 0.8,
      notes: [
        "G . . . | . . . . | d . . . | . . . . | d . . . | . . . . | d . . . | . . d d",
        "g . . . | . . . . | d . . . | . . . . | d . . . | . . . . | d . . . | . . d d",
      ].join(" "),
    },
  ],
};

/** Soft and reflective: a low flute over a sparse zither. Yu mode on D (key of F). */
const story: Song = {
  bpm: 64,
  beats: 4,
  bars: 12,
  key: 65,
  gain: 0.62,
  seed: 23,
  parts: [
    {
      inst: "zheng",
      div: 2,
      vel: 0.25,
      pan: -0.2,
      drop: 0.14,
      shift: [0, 0, 1, 1, -1, -1, -2, -2, 0, 0, -1, -1],
      notes: "6,, 3, 6, 1 3 1 6, 3,",
    },
    {
      inst: "pad",
      div: 1,
      oct: -1,
      vel: 0.05,
      shift: [0, 0, 1, 1, -1, -1, -2, -2, 0, 0, -1, -1],
      notes: "6, - - - - - - -",
    },
    {
      inst: "flute",
      div: 2,
      pan: 0.15,
      vel: 0.26,
      grace: 0.3,
      notes: [
        ". . . . 3 - 6 - | 5 - 3 - 2 1 2 - | 3 - - - 5 3 2 - | 1 - 2 3 - - . .",
        "2 - 3 5 - - 6 5 | 3 - 2 - - - . . | 5 - 6 1' - 6 5 3 | 2 - - - 3 - . .",
        "6 - 5 6 1' - 6 - | 5 - 3 - 5 6 3 - | 2 - - 3 2 1 6, - | 6, - - - - - . .",
      ].join(" "),
    },
    {
      inst: "perc",
      div: 1,
      vel: 0.7,
      notes: `b . . . ${rest(7, 1)} b . . . ${rest(3, 1)}`,
    },
  ],
};

/** Calm and warm for menus: zither and flute, woodblock and a light drum. Gong mode on G. */
const camp: Song = {
  bpm: 90,
  beats: 4,
  bars: 16,
  key: 67,
  gain: 0.62,
  seed: 37,
  parts: [
    {
      inst: "zheng",
      div: 2,
      oct: -1,
      vel: 0.26,
      pan: -0.25,
      drop: 0.06,
      decay: 1.1,
      shift: [0, 0, -1, -1, 1, 1, -2, -2],
      notes: "1 3 5 3 6 5 3 2",
    },
    {
      inst: "qin",
      div: 2,
      oct: -1,
      vel: 0.2,
      pan: 0.1,
      shift: [0, 0, -1, -1, 1, 1, -2, -2],
      notes: "1, . . 5,, . . 1, .",
    },
    {
      inst: "flute",
      div: 2,
      pan: 0.2,
      vel: 0.28,
      notes: [
        rest(2),
        "5 - 6 - 1' - 6 5 | 3 - - - 2 - 3 -",
        "5 - - 6 5 3 2 - | 3 - 5 - - - . . | 2 - 3 2 1 - 6, - | 1 - - - - - . .",
        "3 - 5 6 1' - 2' - | 1' - 6 5 6 - - - | 5 - 6 5 3 - 2 3 | 5 - - - - - . .",
        "6 - 5 3 2 - 3 5 | 3 - 2 1 2 - - - | 1 - 2 3 2 1 6, 5, | 2 - - - - - . .",
      ].join(" "),
    },
    { inst: "perc", div: 2, vel: 1.3, notes: "t . . . . . . . | t . . . . . t ." },
    { inst: "perc", div: 2, vel: 1.4, pan: 0.3, notes: "k . . k . . k ." },
  ],
};

/** Driving battle: war drums, a zither ostinato, fiddle lead with flute doubling. Zhi mode on G (key of C). */
const battleShift = [
  0, 0, 1, 1, 0, 0, -2, -2, -1, -1, 1, 1, -2, -2, 0, 0, 1, 1, 0, 0, -1, -1, -2, -2,
];
const battleB = [
  "3' - - 2' 3' - 5' - | 3' 2' 1' 6 5 - - - | 6 - 1' 6 5 - 6 - | 1' - - - 6 5 3 -",
  "2 - 3 5 6 - 5 3 | 2 - - - 1 6, 1 - | 2 - - 3 5 - 6 1' | 5 - - - - - . .",
].join(" ");
const battle: Song = {
  bpm: 116,
  beats: 4,
  bars: 24,
  key: 60,
  gain: 0.63,
  seed: 53,
  parts: [
    {
      inst: "zheng",
      div: 4,
      vel: 0.24,
      pan: -0.25,
      decay: 0.7,
      drop: 0.04,
      shift: battleShift,
      notes: "5,! 2 5 5,! 2 5 5,! 2 5,! 2 5 6 5 2 1 2",
    },
    {
      inst: "qin",
      div: 2,
      vel: 0.3,
      decay: 0.9,
      shift: battleShift,
      notes: "5,, . . 5,, . . 2, .",
    },
    {
      inst: "perc",
      div: 4,
      vel: 0.5,
      ghost: 0.05,
      notes: "D . . d . . D . D . . d . . t t | D . . d . . D . D . t t D t T T",
    },
    { inst: "perc", div: 2, vel: 0.55, pan: 0.25, notes: ". t . t . t . T" },
    {
      inst: "perc",
      div: 4,
      vel: 0.6,
      pan: -0.2,
      notes: `${rest(16, 4)} ${Array(8).fill(". . t . . . t . . . t . . t . t").join(" ")}`,
    },
    {
      inst: "perc",
      div: 1,
      vel: 0.9,
      notes: [
        `G . . . ${rest(6, 1)} . . . c`,
        `C . . . ${rest(6, 1)} . . C .`,
        `G . . . ${rest(6, 1)} . . C C`,
      ].join(" "),
    },
    {
      inst: "erhu",
      div: 2,
      pan: 0.15,
      notes: [
        rest(4),
        "5 - - 6 1' - 2' 1' | 6 - 5 - - - 3 5 | 2 - - 3 5 - 3 2 | 1 2 3 - 2 - - -",
        battleB,
        "6, - 1 - 2 - 3 2 | 1 - 6, - 5, - . . | 5 - 6 5 3 - 2 3 | 5 - - - 6 - 5 -",
        "3 - - 5 3 2 1 2 | 3 - - - . . 3 5 | 6 - 5 6 1' - 6 5 | 2' - - - - - . .",
      ].join(" "),
    },
    { inst: "flute", div: 2, vel: 0.2, pan: -0.1, notes: `${rest(8)} ${battleB} ${rest(8)}` },
    {
      // Every other pass the flute also takes the first phrase an octave up.
      inst: "flute",
      div: 2,
      oct: 1,
      vel: 0.16,
      pan: -0.1,
      every: [2, 1],
      notes: [
        rest(4),
        "5 - - 6 1' - 2' 1' | 6 - 5 - - - 3 5 | 2 - - 3 5 - 3 2 | 1 2 3 - 2 - - -",
        rest(16),
      ].join(" "),
    },
  ],
};

/** Tense: low ostinato, heavier drums, fiddle with passing tones. Yu mode on A (key of C). */
const dangerShift = [0, 0, 0, 0, -1, -1, 0, 0, -2, -2, -3, -3, 1, 1, -1, -1, 0, 0, -1, -1];
const danger: Song = {
  bpm: 128,
  beats: 4,
  bars: 20,
  key: 60,
  gain: 0.6,
  seed: 71,
  parts: [
    {
      inst: "zheng",
      div: 4,
      vel: 0.26,
      pan: -0.2,
      decay: 0.55,
      drop: 0.03,
      shift: dangerShift,
      notes: "6,,! 6,, 3, 6,, 6,,! 6,, 3, 6,, 6,,! 3, 6, 3, 1 6, 3, 6,",
    },
    { inst: "pad", div: 1, vel: 0.06, shift: dangerShift, notes: "6,, - - - - - - -" },
    {
      inst: "perc",
      div: 4,
      vel: 0.45,
      ghost: 0.07,
      notes: "D . . D D . . . D . . D D . T T | D . . D D . . . D . D D T T T T",
    },
    { inst: "perc", div: 2, vel: 0.5, pan: 0.25, notes: "t . t T t . t T" },
    {
      inst: "perc",
      div: 1,
      vel: 0.9,
      notes: [
        `G . . . ${rest(3, 1)} C . . . ${rest(3, 1)}`,
        `G . . . ${rest(3, 1)} C . . . ${rest(3, 1)}`,
        `G . . . ${rest(2, 1)} . . c c`,
      ].join(" "),
    },
    {
      inst: "erhu",
      div: 2,
      pan: 0.15,
      notes: [
        rest(4),
        "3 - - 2 3 5 6 - | 5 - 3 - 2 - - - | 3 - 2 1 7, - 6, - | 6, - - - . . 1 2",
        "3 - - - 5 - 3 2 | 3 - 5 6 5 - - - | 4 - - 3 2 - 1 2 | 2 - - - . . 3 5",
        "6 - - 1' 7 - 6 5 | 3 - - - 5 - 6 - | 1' - - 7 6 - 5 3 | 5 - - - - - . .",
        "6 - 6 - 1' 6 5 3 | 6 - - - . . 6 1' | 2' - 1' 7 6 - 5 - | 3 - - - - - . .",
      ].join(" "),
    },
  ],
};

/** Slow, warm and resolving: flute over zither, pad and bells. Gong mode on E-flat. */
const endingShift = [0, 0, -1, -1, 1, 1, -2, -2, 0, 0, 2, 2, -1, -1, -2, 0];
const ending: Song = {
  bpm: 60,
  beats: 4,
  bars: 16,
  key: 63,
  gain: 0.72,
  seed: 89,
  parts: [
    {
      inst: "zheng",
      div: 2,
      vel: 0.26,
      pan: -0.2,
      drop: 0.08,
      shift: endingShift,
      notes: "1, 5, 1 3 5 3 2 1",
    },
    { inst: "pad", div: 1, vel: 0.05, shift: endingShift, notes: "1,, - - - - - - -" },
    {
      inst: "flute",
      div: 2,
      oct: 1,
      pan: 0.15,
      vel: 0.27,
      grace: 0.25,
      notes: [
        rest(1),
        ". . . . 3 - 5 - | 6 - - - 5 3 1 - | 2 - 3 - - - . .",
        "2 - 3 5 6 - 5 - | 6 - - - 5 3 2 - | 3 - 2 1 2 - - - | 5, - 6, - 1 - 2 -",
        "3 - - - 5 - 6 - | 5 - 3 - 2 3 5 - | 6 - - - 1' - 6 5 | 3 - - - . . 5 6",
        "1' - - - 6 5 3 - | 2 - 1 - 6, - 1 2 | 3 - - - 2 - - - | 1 - - - - - - -",
      ].join(" "),
    },
    {
      inst: "bell",
      div: 1,
      oct: 1,
      vel: 0.16,
      notes: `1' . . . ${rest(7, 1)} 5 . . . ${rest(6, 1)} . . 1' .`,
    },
    { inst: "perc", div: 1, vel: 0.6, notes: `g . . . ${rest(7, 1)} d . . . ${rest(7, 1)}` },
  ],
};

export const SONGS: Record<Track, Song> = { title, story, camp, battle, danger, ending };
