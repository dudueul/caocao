/**
 * The mixer graph: music and effect buses into a master gain, a gentle
 * limiter with a soft ceiling, and a shared generated reverb (mostly for the music).
 *
 *   voices → music bus ─┬────────────────→ master → compressor → ceiling → out
 *                       └→ send → reverb ─┘
 *   effects → sfx bus ──────────────────→ master
 *            └→ per-effect send → sfxWet → reverb
 */
import type { Volumes } from "./index.ts";
import { clamp, type Kit, makeKit, rng } from "./synth.ts";

export interface Mix {
  readonly kit: Kit;
  readonly master: GainNode;
  readonly music: GainNode;
  readonly sfx: GainNode;
  /** Reverb input for effects; follows the sfx volume. */
  readonly sfxWet: GainNode;
}

export const DEFAULT_VOLUMES: Volumes = { master: 70, music: 45, sfx: 70 };

/** How much of the music bus goes to the reverb. */
const MUSIC_SEND = 0.3;

/** 0–100 to gain on a perceptual (square) curve. */
export function volumeGain(v: number): number {
  const x = clamp(Number.isFinite(v) ? v : 0, 0, 100) / 100;
  return x * x;
}

/** `reverb: false` leaves the reverb out (offline length checks). */
export function buildMix(ac: BaseAudioContext, vol: Volumes, reverb = true): Mix {
  const kit = makeKit(ac);
  const comp = ac.createDynamicsCompressor();
  comp.threshold.value = -10;
  comp.knee.value = 8;
  comp.ratio.value = 8;
  comp.attack.value = 0.003;
  comp.release.value = 0.25;
  // Transparent below 0.7; a smooth ceiling at 0.95 for the rare peak the compressor lets through.
  const clip = ac.createWaveShaper();
  clip.curve = ceiling();
  comp.connect(clip).connect(ac.destination);
  const master = ac.createGain();
  master.connect(comp);
  const music = ac.createGain();
  music.connect(master);
  const sfx = ac.createGain();
  sfx.connect(master);

  const sfxWet = ac.createGain();
  if (reverb) {
    const verb = ac.createConvolver();
    verb.buffer = impulse(ac, 2.6);
    const hp = ac.createBiquadFilter();
    hp.type = "highpass";
    hp.frequency.value = 180;
    const lp = ac.createBiquadFilter();
    lp.type = "lowpass";
    lp.frequency.value = 5000;
    verb.connect(hp).connect(lp).connect(master);
    const send = ac.createGain();
    send.gain.value = MUSIC_SEND;
    music.connect(send).connect(verb);
    sfxWet.connect(verb);
  }

  const mix = { kit, master, music, sfx, sfxWet };
  applyVolumes(mix, vol, true);
  return mix;
}

/** Set the bus gains; smoothed unless `now` is set. */
export function applyVolumes(m: Mix, v: Volumes, now = false): void {
  const t = m.kit.ac.currentTime;
  const set = (p: AudioParam, x: number) => {
    if (now) p.value = x;
    else {
      p.cancelScheduledValues(t);
      p.setTargetAtTime(x, t, 0.03);
    }
  };
  set(m.master.gain, volumeGain(v.master));
  set(m.music.gain, volumeGain(v.music));
  set(m.sfx.gain, volumeGain(v.sfx));
  set(m.sfxWet.gain, volumeGain(v.sfx));
}

function ceiling(): Float32Array<ArrayBuffer> {
  const n = 4097;
  const c = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const x = (i / (n - 1)) * 2 - 1;
    const a = Math.abs(x);
    c[i] = a <= 0.7 ? x : Math.sign(x) * (0.7 + 0.25 * Math.tanh((a - 0.7) / 0.25));
  }
  return c;
}

/** Stereo decaying noise that darkens as it fades: a small hall. */
function impulse(ac: BaseAudioContext, seconds: number): AudioBuffer {
  const sr = ac.sampleRate;
  const n = Math.floor(sr * seconds);
  const buf = ac.createBuffer(2, n, sr);
  const pre = Math.floor(sr * 0.012);
  for (let ch = 0; ch < 2; ch++) {
    const rnd = rng(101 + ch);
    const d = buf.getChannelData(ch);
    let lp = 0;
    for (let i = 0; i < n; i++) {
      const x = i / n;
      const smooth = 0.15 + 0.75 * x;
      lp += (rnd() * 2 - 1 - lp) * (1 - smooth);
      const fadeIn = i < pre ? i / pre : 1;
      d[i] = lp * (1 - x) ** 3.2 * fadeIn;
    }
  }
  return buf;
}
