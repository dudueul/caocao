/**
 * Offline rendering through the same mixer graph as the live engine, so
 * levels can be checked without listening (dev page and browser tests).
 */
import type { Sfx, Track, Volumes } from "./index.ts";
import { buildMix } from "./mix.ts";
import { loopSeconds, Player, type Song, validate } from "./music.ts";
import { type PlayOpts, triggerSfx } from "./sfx.ts";
import { SONGS } from "./songs.ts";

export const FULL: Volumes = { master: 100, music: 100, sfx: 100 };

export interface RenderOpts {
  volumes?: Volumes;
  sampleRate?: number;
  /** False renders without the reverb. */
  reverb?: boolean;
}

function context(seconds: number, o: RenderOpts) {
  const sr = o.sampleRate ?? 48000;
  const ac = new OfflineAudioContext(2, Math.ceil(sr * seconds), sr);
  return { ac, mix: buildMix(ac, o.volumes ?? FULL, o.reverb ?? true) };
}

/** One effect, started at 20 ms. */
export function renderSfx(name: Sfx, seconds = 4, o: RenderOpts & PlayOpts = {}) {
  const { ac, mix } = context(seconds, o);
  triggerSfx(mix, name, 0.02, o, null);
  return ac.startRendering();
}

/** The first `seconds` of a track, without the fade-in. */
export function renderTrack(track: Track, seconds: number, o: RenderOpts = {}) {
  return renderSong(SONGS[track], seconds, o);
}

/** The first `seconds` of any song (for trying out song data). */
export function renderSong(song: Song, seconds: number, o: RenderOpts = {}) {
  const { ac, mix } = context(seconds, o);
  new Player(mix.kit, song, mix.music, 0.05).tick(0, seconds);
  return ac.startRendering();
}

/** A track with several effects fired together at `at` seconds. */
export function renderScene(
  track: Track | null,
  sfx: readonly Sfx[],
  at: number,
  seconds: number,
  o: RenderOpts = {},
) {
  const { ac, mix } = context(seconds, o);
  if (track) new Player(mix.kit, SONGS[track], mix.music, 0.05).tick(0, seconds);
  for (const name of sfx) triggerSfx(mix, name, at, {}, null);
  return ac.startRendering();
}

export interface Level {
  peak: number;
  /** RMS over the audible span (start..end), or the whole buffer for music. */
  rms: number;
  /** First and last time above the floor, in seconds. */
  start: number;
  end: number;
  bad: number;
}

/** Peak, RMS and audible span; `floor` is the silence threshold (−60 dBFS by default). */
export function measure(buf: AudioBuffer, floor = 0.001, whole = false): Level {
  const chans = Array.from({ length: buf.numberOfChannels }, (_, c) => buf.getChannelData(c));
  let peak = 0;
  let bad = 0;
  let first = -1;
  let last = -1;
  for (let i = 0; i < buf.length; i++) {
    for (const d of chans) {
      const x = d[i] ?? 0;
      if (!Number.isFinite(x)) bad++;
      const a = Math.abs(x);
      if (a > peak) peak = a;
      if (a > floor) {
        if (first < 0) first = i;
        last = i;
      }
    }
  }
  const from = whole || first < 0 ? 0 : first;
  const to = whole || last < 0 ? buf.length - 1 : last;
  let sum = 0;
  for (let i = from; i <= to; i++) for (const d of chans) sum += (d[i] ?? 0) ** 2;
  const n = Math.max(1, (to - from + 1) * chans.length);
  return {
    peak,
    rms: Math.sqrt(sum / n),
    start: first < 0 ? 0 : first / buf.sampleRate,
    end: last < 0 ? 0 : last / buf.sampleRate,
    bad,
  };
}

export const dbfs = (x: number): number => (x > 0 ? 20 * Math.log10(x) : -Infinity);

/** Song data problems and loop lengths, per track. */
export function songReport(): { track: Track; seconds: number; problems: string[] }[] {
  return (Object.keys(SONGS) as Track[]).map((track) => ({
    track,
    seconds: loopSeconds(SONGS[track]),
    problems: validate(SONGS[track]),
  }));
}
