/**
 * Melodies are written in jianpu, the Chinese numbered notation: 1 2 3 5 6 are
 * the five pentatonic tones (gong shang jue zhi yu), 4 and 7 the passing tones.
 * A trailing "," drops an octave and "'" raises one; "!" accents, "?" softens.
 * Pitches are kept as pentatonic steps so a pattern can be moved along the
 * scale (5 steps to the octave) to follow the harmony.
 */

const PENT = [0, 2, 4, 7, 9] as const;
/** Degree → [pentatonic step, extra semitones]. 4 and 7 ride on 3 and 6. */
const DEGREE: Record<string, readonly [number, number]> = {
  "1": [0, 0],
  "2": [1, 0],
  "3": [2, 0],
  "4": [2, 1],
  "5": [3, 0],
  "6": [4, 0],
  "7": [4, 2],
};

export interface Note {
  pent: number;
  extra: number;
  accent: boolean;
  soft: boolean;
}

const NOTE = /^([1-7])([,']*)([!?]?)$/;

export function parseNote(tok: string): Note | null {
  const m = NOTE.exec(tok);
  const deg = m?.[1] === undefined ? undefined : DEGREE[m[1]];
  if (!m || !deg) return null;
  let oct = 0;
  for (const c of m[2] ?? "") oct += c === "'" ? 1 : -1;
  return { pent: deg[0] + oct * 5, extra: deg[1], accent: m[3] === "!", soft: m[3] === "?" };
}

/** Semitones above "1" for a pentatonic step (may be negative). */
export function pentSemis(pent: number): number {
  const o = Math.floor(pent / 5);
  return o * 12 + (PENT[pent - o * 5] ?? 0);
}

/** MIDI note of a jianpu token in the key whose "1" is MIDI `key`. */
export function jp(key: number, tok: string): number {
  const n = parseNote(tok);
  return n ? key + pentSemis(n.pent) + n.extra : key;
}

/** Space-separated jianpu tokens to MIDI notes. */
export function jps(key: number, line: string): number[] {
  return line
    .split(/\s+/)
    .filter((s) => s !== "")
    .map((s) => jp(key, s));
}
