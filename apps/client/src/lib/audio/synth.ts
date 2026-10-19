/**
 * Synth building blocks: a seeded PRNG, the shared noise buffer and string
 * waves, a Voice (one short node graph that cleans itself up when its sources
 * end), envelopes, and the instruments used by both the effects and the music.
 * Everything takes a BaseAudioContext, so the same code renders offline.
 */

export type Rng = () => number;

/** mulberry32: a tiny seeded PRNG returning [0, 1). */
export function rng(seed: number): Rng {
  let s = seed >>> 0;
  return () => {
    s = (s + 0x6d2b79f5) >>> 0;
    let x = s;
    x = Math.imul(x ^ (x >>> 15), x | 1);
    x ^= x + Math.imul(x ^ (x >>> 7), x | 61);
    return ((x ^ (x >>> 14)) >>> 0) / 4294967296;
  };
}

/** Stateless hash of a few integers to [0, 1), for reproducible variation. */
export function hash(...parts: number[]): number {
  let h = 0x811c9dc5;
  for (const p of parts) {
    h = Math.imul(h ^ (p | 0), 0x01000193);
    h ^= h >>> 15;
    h = Math.imul(h, 0x2c1b3c6d);
    h ^= h >>> 12;
  }
  h = Math.imul(h ^ (h >>> 16), 0x297a2d39);
  return ((h ^ (h >>> 15)) >>> 0) / 4294967296;
}

export const midiHz = (m: number): number => 440 * 2 ** ((m - 69) / 12);
export const isPanner = (n: AudioNode): n is StereoPannerNode => "pan" in n;
export const clamp = (x: number, lo: number, hi: number): number => Math.min(hi, Math.max(lo, x));

/** Per-context shared resources. */
export interface Kit {
  readonly ac: BaseAudioContext;
  /** Two seconds of white noise, shared by every noise source. */
  readonly noise: AudioBuffer;
  /** Plucked-string waves: bright metal (zheng, a duller one for high notes), warm silk (qin), and the mellow body they decay into. */
  readonly zheng: PeriodicWave;
  readonly zhengHi: PeriodicWave;
  readonly qin: PeriodicWave;
  readonly body: PeriodicWave;
  /** Triangle with a touch of second harmonic: the bamboo flute. */
  readonly flute: PeriodicWave;
  readonly rnd: Rng;
  readonly nyquist: number;
}

export function makeKit(ac: BaseAudioContext, seed = 1): Kit {
  const rnd = rng(seed);
  const sr = ac.sampleRate;
  const noise = ac.createBuffer(1, Math.floor(sr * 2), sr);
  const d = noise.getChannelData(0);
  for (let i = 0; i < d.length; i++) d[i] = rnd() * 2 - 1;
  return {
    ac,
    noise,
    zheng: stringWave(ac, 0.13, 1.3, 10),
    zhengHi: stringWave(ac, 0.13, 1.6, 5),
    qin: stringWave(ac, 0.21, 1.9, 8),
    body: stringWave(ac, 0.5, 2.2, 4),
    flute: ac.createPeriodicWave(
      new Float32Array(8),
      Float32Array.from([0, 0.81, 0.14, -0.09, 0, 0.032, 0, -0.0165]),
    ),
    rnd,
    nyquist: sr * 0.45,
  };
}

/** Harmonics of a string plucked at fraction `at` of its length. */
function stringWave(ac: BaseAudioContext, at: number, roll: number, n: number): PeriodicWave {
  const real = new Float32Array(n + 1);
  const imag = new Float32Array(n + 1);
  for (let k = 1; k <= n; k++) imag[k] = Math.abs(Math.sin(k * Math.PI * at)) / k ** roll;
  return ac.createPeriodicWave(real, imag);
}

/**
 * One sound's node graph. Every node made through it is disconnected once all
 * of its sources have ended, then `onDone` runs (the engine counts voices).
 */
export class Voice {
  private nodes: AudioNode[] = [];
  private live = 0;
  private done = false;

  constructor(
    readonly kit: Kit,
    private readonly onDone: (() => void) | null = null,
  ) {}

  gain(value = 0): GainNode {
    const g = this.kit.ac.createGain();
    g.gain.value = value;
    this.nodes.push(g);
    return g;
  }

  filter(type: BiquadFilterType, freq: number, q = Math.SQRT1_2): BiquadFilterNode {
    const f = this.kit.ac.createBiquadFilter();
    f.type = type;
    f.frequency.value = this.hz(freq);
    f.Q.value = q;
    this.nodes.push(f);
    return f;
  }

  /** A stereo panner, or a plain gain where the browser has none. */
  panner(pan: number): AudioNode {
    const ac = this.kit.ac;
    if (typeof ac.createStereoPanner !== "function") return this.gain(1);
    const p = ac.createStereoPanner();
    p.pan.value = clamp(pan, -1, 1);
    this.nodes.push(p);
    return p;
  }

  osc(type: Exclude<OscillatorType, "custom">, freq: number, t0: number, t1: number) {
    const o = this.kit.ac.createOscillator();
    o.type = type;
    o.frequency.value = this.hz(freq);
    this.run(o, t0, t1);
    return o;
  }

  wave(w: PeriodicWave, freq: number, t0: number, t1: number): OscillatorNode {
    const o = this.kit.ac.createOscillator();
    o.setPeriodicWave(w);
    o.frequency.value = this.hz(freq);
    this.run(o, t0, t1);
    return o;
  }

  /** Looping white noise from a random point in the shared buffer. */
  noise(t0: number, t1: number): AudioBufferSourceNode {
    const s = this.kit.ac.createBufferSource();
    s.buffer = this.kit.noise;
    s.loop = true;
    this.nodes.push(s);
    this.live++;
    s.onended = () => this.release();
    s.start(t0, this.kit.rnd() * (this.kit.noise.duration - 0.1));
    s.stop(t1);
    return s;
  }

  /** Frequency clamped below Nyquist (browsers warn on out-of-range values). */
  hz(f: number): number {
    return clamp(f, 1, this.kit.nyquist);
  }

  /** Call after building: a voice with no sources is released at once. */
  seal(): void {
    if (this.live === 0) this.dispose();
  }

  dispose(): void {
    if (this.done) return;
    this.done = true;
    for (const n of this.nodes) n.disconnect();
    this.nodes.length = 0;
    this.onDone?.();
  }

  private run(src: AudioScheduledSourceNode, t0: number, t1: number): void {
    this.nodes.push(src);
    this.live++;
    src.onended = () => this.release();
    src.start(t0);
    src.stop(Math.max(t1, t0 + 0.01));
  }

  private release(): void {
    this.live--;
    if (this.live <= 0) this.dispose();
  }
}

// --- Envelopes -------------------------------------------------------------

/** 0 → peak over `a`, then an exponential fall to silence over `d`. Returns the end time. */
export function perc(p: AudioParam, t: number, peak: number, a: number, d: number): number {
  const top = Math.max(peak, 1e-4);
  p.setValueAtTime(0, t);
  p.linearRampToValueAtTime(top, t + a);
  p.exponentialRampToValueAtTime(top * 1e-3, t + a + d);
  p.linearRampToValueAtTime(0, t + a + d + 0.004);
  return t + a + d + 0.004;
}

/** Piecewise-linear envelope from 0 through [offset, value] points. Returns the end time. */
export function shape(p: AudioParam, t: number, pts: readonly (readonly [number, number])[]) {
  p.setValueAtTime(0, t);
  let end = t;
  for (const [dt, v] of pts) {
    end = t + dt;
    p.linearRampToValueAtTime(v, end);
  }
  return end;
}

/** Exponential glide of a frequency from `a` to `b` over `dur`. */
export function glide(p: AudioParam, t: number, a: number, b: number, dur: number): void {
  p.setValueAtTime(Math.max(a, 1), t);
  p.exponentialRampToValueAtTime(Math.max(b, 1), t + Math.max(dur, 0.001));
}

/** A delayed vibrato on the given detune params (cents). */
function vibrato(v: Voice, t: number, end: number, rate: number, cents: number, delay: number) {
  const lfo = v.osc("sine", rate, t, end);
  const depth = v.gain(0);
  depth.gain.setValueAtTime(0, t);
  depth.gain.setValueAtTime(0, t + delay);
  depth.gain.linearRampToValueAtTime(cents, t + delay + 0.3);
  lfo.connect(depth);
  return {
    to(p: AudioParam) {
      depth.connect(p);
    },
  };
}

// --- Noise -----------------------------------------------------------------

/** A short filtered noise hit. Band-pass levels are lifted to make up for the narrow band. */
export function burst(
  v: Voice,
  out: AudioNode,
  t: number,
  type: BiquadFilterType,
  freq: number,
  q: number,
  vel: number,
  decay: number,
  attack = 0.001,
): void {
  const n = v.noise(t, t + attack + decay + 0.02);
  const f = v.filter(type, freq, q);
  const g = v.gain();
  perc(g.gain, t, vel * (type === "bandpass" ? Math.sqrt(q) * 1.4 : 1), attack, decay);
  n.connect(f).connect(g).connect(out);
}

/** A band of noise sweeping from f0 to f1, swelling to `vel` at `peakAt` of its length. */
export function swish(
  v: Voice,
  out: AudioNode,
  t: number,
  dur: number,
  f0: number,
  f1: number,
  vel: number,
  q = 1,
  peakAt = 0.5,
): BiquadFilterNode {
  const n = v.noise(t, t + dur + 0.02);
  const bp = v.filter("bandpass", f0, q);
  glide(bp.frequency, t, v.hz(f0), v.hz(f1), dur);
  const g = v.gain();
  shape(g.gain, t, [
    [dur * peakAt, vel * Math.sqrt(q) * 1.4],
    [dur, 0],
  ]);
  n.connect(bp).connect(g).connect(out);
  return bp;
}

// --- Instruments -----------------------------------------------------------

export interface PluckOpts {
  /** Seconds to fall silent. */
  decay?: number;
  /** How long the bright attack lasts, as a fraction of the decay. */
  bright?: number;
  /** Warm silk-string wave instead of the bright one. */
  qin?: boolean;
  /** Pitch ratio to bend to after `bendAt` over `bendTime`. */
  bend?: number;
  bendAt?: number;
  bendTime?: number;
  /** Pick noise level (0 for none). */
  pick?: number;
  /** Delayed vibrato depth in cents. */
  vib?: number;
}

/**
 * Plucked zither: a bright string wave that dies quickly over a mellow one
 * that rings on (two oscillators are much cheaper than a swept filter), with
 * an optional pick click.
 */
export function pluck(
  v: Voice,
  out: AudioNode,
  t: number,
  f: number,
  vel: number,
  o: PluckOpts = {},
): void {
  const k = v.kit;
  const decay = o.decay ?? clamp(2.2 * Math.sqrt(262 / f), 0.5, 3);
  const snap = decay * (o.bright ?? 0.3);
  const end = t + decay + 0.01;
  const top = v.wave(o.qin ? k.qin : f > 700 ? k.zhengHi : k.zheng, f, t, t + snap + 0.01);
  const body = v.wave(k.body, f, t, end);
  const vb = o.vib ? vibrato(v, t, end, 4.8, o.vib, 0.35) : null;
  for (const s of [top, body]) {
    if (o.bend) {
      const at = t + (o.bendAt ?? 0.05);
      s.frequency.setValueAtTime(v.hz(f), at);
      s.frequency.exponentialRampToValueAtTime(v.hz(f * o.bend), at + (o.bendTime ?? 0.12));
    }
    vb?.to(s.detune);
  }
  const gt = v.gain();
  perc(gt.gain, t, vel * 0.6, 0.003, snap);
  top.connect(gt).connect(out);
  const gb = v.gain();
  perc(gb.gain, t, vel * 0.55, 0.004, decay);
  body.connect(gb).connect(out);
  const pick = o.pick ?? 0.22;
  if (pick > 0) burst(v, out, t, "bandpass", clamp(f * 3, 1200, 4500), 1.1, vel * pick, 0.018);
}

/** Bamboo flute: triangle with a little second harmonic, breath noise, scoop and delayed vibrato. */
export function flute(
  v: Voice,
  out: AudioNode,
  t: number,
  f: number,
  dur: number,
  vel: number,
  grace = 0,
  vib = 16,
): void {
  const a = 0.05;
  const r = 0.13;
  const off = t + Math.max(dur, a + 0.04);
  const end = off + r;
  const tone = v.wave(v.kit.flute, f, t, end);
  if (grace > 0) {
    tone.frequency.setValueAtTime(v.hz(grace), t);
    tone.frequency.setValueAtTime(v.hz(grace), t + 0.045);
    tone.frequency.exponentialRampToValueAtTime(v.hz(f), t + 0.075);
  } else glide(tone.frequency, t, v.hz(f * 0.985), v.hz(f), 0.05);
  if (dur > 0.35 && vib > 0) {
    vibrato(v, t, end, 5.1 + v.kit.rnd() * 0.5, vib, 0.22).to(tone.detune);
  }
  const g = v.gain();
  const settle = Math.min(t + a + 0.15, off);
  g.gain.setValueAtTime(0, t);
  g.gain.linearRampToValueAtTime(vel, t + a);
  g.gain.linearRampToValueAtTime(vel * 0.8, settle);
  g.gain.linearRampToValueAtTime(vel * 0.88, off);
  g.gain.linearRampToValueAtTime(0, end);
  const lp = v.filter("lowpass", Math.min(f * 5, 6000), 0.5);
  tone.connect(g).connect(lp).connect(out);
  // Breath: strongest at the attack.
  const n = v.noise(t, end);
  const bp = v.filter("bandpass", Math.min(f * 2.2, 7000), 1.4);
  const bg = v.gain();
  shape(bg.gain, t, [
    [0.015, vel * 0.32],
    [0.12, vel * 0.09],
    [off - t, vel * 0.07],
    [end - t, 0],
  ]);
  n.connect(bp).connect(bg).connect(out);
}

/** Bowed two-string fiddle: a saw through a nasal lowpass, with slides and vibrato. */
export function erhu(
  v: Voice,
  out: AudioNode,
  t: number,
  f: number,
  dur: number,
  vel: number,
  from = 0,
  vib = 20,
): void {
  const a = 0.07;
  const r = 0.14;
  const off = t + Math.max(dur, a + 0.05);
  const end = off + r;
  const lp1 = v.filter("lowpass", Math.min(f * 5, 3000), 0.8);
  const pk = v.filter("peaking", 1100, 1.2);
  pk.gain.value = 5;
  const lp2 = v.filter("lowpass", Math.min(f * 7, 4200), 0.5);
  const g = v.gain();
  const vb = dur > 0.25 ? vibrato(v, t, end, 5.6, vib, 0.14) : null;
  const s = v.osc("sawtooth", f, t, end);
  if (from > 0) glide(s.frequency, t, from, f, 0.09);
  else glide(s.frequency, t, f * 0.985, f, 0.06);
  vb?.to(s.detune);
  const sg = v.gain(0.9);
  s.connect(sg).connect(lp1);
  g.gain.setValueAtTime(0, t);
  g.gain.linearRampToValueAtTime(vel * 0.85, t + a);
  g.gain.linearRampToValueAtTime(vel, off);
  g.gain.linearRampToValueAtTime(0, end);
  lp1.connect(pk).connect(lp2).connect(g).connect(out);
  // Rosin on the bow.
  const n = v.noise(t, end);
  const bp = v.filter("bandpass", 2600, 1.5);
  const bg = v.gain(0.05);
  n.connect(bp).connect(bg).connect(g);
}

/** Soft drone: detuned saws and a triangle under a low lowpass, slow attack and release. */
export function pad(v: Voice, out: AudioNode, t: number, f: number, dur: number, vel: number) {
  const a = Math.min(1.2, Math.max(0.3, dur * 0.35));
  const r = 1.5;
  const off = t + Math.max(dur, a);
  const end = off + r;
  const lp = v.filter("lowpass", 280 + f * 1.2, 0.4);
  const g = v.gain();
  g.gain.setValueAtTime(0, t);
  g.gain.linearRampToValueAtTime(vel, t + a);
  g.gain.setValueAtTime(vel, off);
  g.gain.linearRampToValueAtTime(0, end);
  for (const d of [-9, 9]) {
    const s = v.osc("sawtooth", f, t, end);
    s.detune.value = d;
    s.connect(lp);
  }
  const sub = v.osc("triangle", f, t, end);
  const sg = v.gain(0.7);
  sub.connect(sg).connect(lp);
  lp.connect(g).connect(out);
}

export interface DrumOpts {
  f0: number;
  f1: number;
  decay: number;
  /** Skin-slap noise level. */
  slap?: number;
  /** Overtone level. */
  tone?: number;
}

/** War drum: a sine with a fast pitch drop, a skin overtone and a noise slap. */
export function drum(v: Voice, out: AudioNode, t: number, vel: number, o: DrumOpts): void {
  const end = t + o.decay + 0.01;
  const s = v.osc("sine", o.f0, t, end);
  glide(s.frequency, t, o.f0, o.f1, Math.min(0.11, o.decay * 0.5));
  const g = v.gain();
  perc(g.gain, t, vel, 0.002, o.decay);
  s.connect(g).connect(out);
  const tone = o.tone ?? 0.2;
  if (tone > 0) {
    const d = o.decay * 0.35;
    const s2 = v.osc("triangle", o.f0 * 1.7, t, t + d + 0.01);
    glide(s2.frequency, t, o.f0 * 1.7, o.f1 * 1.9, 0.08);
    const g2 = v.gain();
    perc(g2.gain, t, vel * tone, 0.002, d);
    s2.connect(g2).connect(out);
  }
  const slap = o.slap ?? 0.3;
  if (slap > 0) burst(v, out, t, "lowpass", 1700, 0.8, vel * slap, 0.045);
}

/** Wooden block (bangzi): a band-passed triangle knock with a faint overtone. */
export function block(v: Voice, out: AudioNode, t: number, f: number, vel: number, decay = 0.05) {
  const s = v.osc("triangle", f, t, t + decay + 0.01);
  const bp = v.filter("bandpass", f, 3);
  const g = v.gain();
  perc(g.gain, t, vel, 0.001, decay);
  s.connect(bp).connect(g).connect(out);
  const s2 = v.osc("sine", f * 2.6, t, t + decay * 0.5 + 0.01);
  const g2 = v.gain();
  perc(g2.gain, t, vel * 0.25, 0.001, decay * 0.5);
  s2.connect(g2).connect(out);
}

/** [frequency ratio, amplitude, decay multiplier]. */
export type Partial = readonly [number, number, number];

export const GONG: readonly Partial[] = [
  [1, 1, 1],
  [1.004, 0.5, 0.9],
  [1.48, 0.6, 0.8],
  [1.97, 0.48, 0.65],
  [2.53, 0.36, 0.5],
  [3.06, 0.25, 0.42],
  [3.65, 0.17, 0.33],
  [4.4, 0.11, 0.26],
];
export const BELL: readonly Partial[] = [
  [1, 1, 1],
  [2, 0.16, 0.6],
  [2.76, 0.24, 0.45],
  [5.4, 0.06, 0.25],
];
export const CLINK: readonly Partial[] = [
  [1, 1, 1],
  [1.52, 0.6, 0.7],
  [2.27, 0.45, 0.5],
  [2.95, 0.3, 0.35],
];
export const CLANK: readonly Partial[] = [
  [1, 1, 1],
  [1.47, 0.7, 0.75],
  [2.13, 0.5, 0.5],
];
export const RING: readonly Partial[] = [
  [1, 1, 1],
  [1.003, 0.6, 0.9],
  [2.76, 0.35, 0.5],
];
const CYMBAL: readonly Partial[] = [
  [1, 1, 1],
  [1.41, 0.8, 0.8],
  [1.93, 0.6, 0.6],
  [2.62, 0.5, 0.5],
  [3.3, 0.35, 0.4],
];

export interface RingOpts {
  bend?: number;
  bendTime?: number;
  lp?: number;
  /** Extra attack per ratio step: higher partials bloom later. */
  bloom?: number;
}

/** Sum of decaying sine partials (gongs, bells, metal). `vel` is roughly the peak. */
export function ring(
  v: Voice,
  out: AudioNode,
  t: number,
  f: number,
  vel: number,
  decay: number,
  table: readonly Partial[],
  o: RingOpts = {},
): void {
  let total = 0;
  for (const p of table) total += p[1];
  const sum = v.gain(1.6 / total);
  const lp = v.filter("lowpass", o.lp ?? 6000, 0.5);
  sum.connect(lp).connect(out);
  for (const [r, a, m] of table) {
    const fr = f * r;
    if (fr >= v.kit.nyquist) continue;
    const s = v.osc("sine", fr, t, t + decay * m + 0.06);
    if (o.bend) glide(s.frequency, t, fr, fr * o.bend, o.bendTime ?? 0.4);
    const g = v.gain();
    perc(g.gain, t, vel * a, 0.002 + (o.bloom ?? 0) * (r - 1), decay * m);
    s.connect(g).connect(sum);
  }
}

/** Gong: inharmonic partials with a long decay, a pitch bend, and a strike noise. */
export function gong(
  v: Voice,
  out: AudioNode,
  t: number,
  f: number,
  vel: number,
  decay: number,
  bend = 1,
  bendTime = 0.6,
): void {
  ring(v, out, t, f, vel, decay, GONG, { bend, bendTime, lp: 4200, bloom: 0.012 });
  burst(v, out, t, "lowpass", 2400, 0.7, vel * 0.35, 0.07);
}

/** Small bronze bell. */
export function bell(v: Voice, out: AudioNode, t: number, f: number, vel: number, decay: number) {
  ring(v, out, t, f, vel, decay, BELL, { lp: 6500 });
}

/** Hand cymbals (bo): high noise with a metallic body. */
export function cymbal(v: Voice, out: AudioNode, t: number, vel: number, decay: number): void {
  const n = v.noise(t, t + decay + 0.02);
  const hp = v.filter("highpass", 2800, 0.5);
  const pk = v.filter("peaking", 5200, 1);
  pk.gain.value = 4;
  const lp = v.filter("lowpass", 8500, 0.5);
  const g = v.gain();
  perc(g.gain, t, vel, 0.003, decay);
  n.connect(hp).connect(pk).connect(lp).connect(g).connect(out);
  ring(v, out, t, 640, vel * 0.5, decay * 0.7, CYMBAL, { lp: 7000 });
}

/** Military horn: two detuned saws, a brassy opening filter, a scoop up to pitch. */
export function horn(v: Voice, out: AudioNode, t: number, f: number, dur: number, vel: number) {
  const a = 0.08;
  const r = 0.22;
  const off = t + Math.max(dur, a + 0.05);
  const end = off + r;
  const lp = v.filter("lowpass", 250, 1.1);
  lp.frequency.setValueAtTime(250, t);
  lp.frequency.exponentialRampToValueAtTime(Math.min(f * 6, 2400), t + 0.12);
  lp.frequency.exponentialRampToValueAtTime(Math.min(f * 4, 1700), t + 0.4);
  const g = v.gain();
  g.gain.setValueAtTime(0, t);
  g.gain.linearRampToValueAtTime(vel * 0.85, t + a);
  g.gain.linearRampToValueAtTime(vel, off);
  g.gain.linearRampToValueAtTime(0, end);
  const vb = vibrato(v, t, end, 5, 8, 0.3);
  for (const d of [-7, 7]) {
    const s = v.osc("sawtooth", f, t, end);
    s.detune.value = d;
    glide(s.frequency, t, f * 0.93, f, 0.07);
    vb.to(s.detune);
    const sg = v.gain(0.5);
    s.connect(sg).connect(lp);
  }
  lp.connect(g).connect(out);
}
