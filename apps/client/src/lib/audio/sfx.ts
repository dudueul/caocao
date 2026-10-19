/**
 * Sound effects, each a short recipe over the synth instruments. A recipe
 * builds its graph into `out` starting at `t`; `k` scales every pitch.
 * Melodic stingers are original pentatonic phrases written in jianpu.
 */
import type { Sfx } from "./index.ts";
import { jp, jps } from "./jianpu.ts";
import type { Mix } from "./mix.ts";
import {
  bell,
  block,
  burst,
  CLANK,
  CLINK,
  clamp,
  cymbal,
  drum,
  erhu,
  flute,
  glide,
  gong,
  horn,
  isPanner,
  midiHz,
  pluck,
  RING,
  ring,
  shape,
  swish,
  Voice,
} from "./synth.ts";

export interface PlayOpts {
  pan?: number;
  gain?: number;
  pitch?: number;
}

interface Recipe {
  /** Output level into the sfx bus. */
  gain: number;
  /** Reverb send. */
  wet: number;
  /** Random detune range in semitones; 0 keeps tonal sounds in tune. */
  jitter: number;
  run(v: Voice, out: AudioNode, t: number, k: number): void;
}

const hz = (key: number, tok: string): number => midiHz(jp(key, tok));

const RECIPES: Record<Sfx, Recipe> = {
  // --- UI ------------------------------------------------------------------
  click: {
    gain: 0.83,
    wet: 0,
    jitter: 0.4,
    run(v, o, t, k) {
      block(v, o, t, 1300 * k, 1, 0.07);
      burst(v, o, t, "highpass", 2500, 0.7, 0.12, 0.01);
    },
  },
  confirm: {
    gain: 0.29,
    wet: 0.06,
    jitter: 0,
    run(v, o, t, k) {
      pluck(v, o, t, 587 * k, 0.75, { decay: 0.4 });
      pluck(v, o, t + 0.075, 784 * k, 0.9, { decay: 0.5 });
    },
  },
  cancel: {
    gain: 0.45,
    wet: 0.06,
    jitter: 0,
    run(v, o, t, k) {
      pluck(v, o, t, 659 * k, 0.8, { decay: 0.32, bend: 0.89, bendAt: 0.03, bendTime: 0.12 });
      pluck(v, o, t + 0.09, 494 * k, 0.6, { decay: 0.35 });
    },
  },
  select: {
    gain: 0.43,
    wet: 0.08,
    jitter: 0,
    run(v, o, t, k) {
      bell(v, o, t, 1175 * k, 0.9, 0.5);
    },
  },
  page: {
    gain: 0.44,
    wet: 0,
    jitter: 0.5,
    run(v, o, t, k) {
      swish(v, o, t, 0.15, 900 * k, 3000 * k, 0.8, 0.8, 0.35);
      swish(v, o, t + 0.02, 0.1, 2200 * k, 3600 * k, 0.25, 1.5, 0.5);
    },
  },

  // --- Movement --------------------------------------------------------------
  move: {
    gain: 0.9,
    wet: 0,
    jitter: 1,
    run(v, o, t, k) {
      drum(v, o, t, 0.8, { f0: 130 * k, f1: 70 * k, decay: 0.09, slap: 0, tone: 0 });
      burst(v, o, t, "bandpass", 1100 * k, 0.9, 0.4, 0.05);
      burst(v, o, t + 0.022, "bandpass", 1600 * k, 1.2, 0.18, 0.035);
    },
  },
  hoof: {
    gain: 0.4,
    wet: 0,
    jitter: 0.8,
    run(v, o, t, k) {
      for (const [dt, lv, f] of [
        [0, 0.7, 320],
        [0.07, 0.5, 345],
        [0.15, 0.9, 300],
      ] as const) {
        const at = t + dt + v.kit.rnd() * 0.008;
        drum(v, o, at, lv, { f0: f * k, f1: f * k * 0.75, decay: 0.05, slap: 0, tone: 0 });
        burst(v, o, at, "bandpass", 750 * k, 3, lv * 0.4, 0.03);
      }
    },
  },

  // --- Combat --------------------------------------------------------------
  hit: {
    gain: 1.61,
    wet: 0.04,
    jitter: 1,
    run(v, o, t, k) {
      drum(v, o, t, 1, { f0: 170 * k, f1: 60 * k, decay: 0.15, slap: 0.6, tone: 0.3 });
      burst(v, o, t, "lowpass", 700, 0.7, 0.45, 0.07);
      ring(v, o, t + 0.004, 1450 * k, 0.14, 0.12, CLANK);
    },
  },
  crit: {
    gain: 1.43,
    wet: 0.08,
    jitter: 0.5,
    run(v, o, t, k) {
      drum(v, o, t, 1, { f0: 150 * k, f1: 42 * k, decay: 0.32, slap: 0.75, tone: 0.35 });
      burst(v, o, t, "lowpass", 1000, 0.7, 0.6, 0.12);
      ring(v, o, t + 0.004, 1150 * k, 0.24, 0.55, RING, { bend: 0.965, bendTime: 0.45 });
      ring(v, o, t + 0.004, 1700 * k, 0.1, 0.15, CLANK);
    },
  },
  miss: {
    gain: 0.74,
    wet: 0.03,
    jitter: 1,
    run(v, o, t, k) {
      swish(v, o, t, 0.26, 500 * k, 2600 * k, 0.8, 1.4, 0.45);
      swish(v, o, t + 0.02, 0.22, 900 * k, 3800 * k, 0.25, 2.5, 0.5);
    },
  },
  guard: {
    gain: 2.24,
    wet: 0.03,
    jitter: 0.8,
    run(v, o, t, k) {
      block(v, o, t, 420 * k, 0.8, 0.07);
      burst(v, o, t, "bandpass", 700 * k, 2.5, 0.4, 0.05);
      burst(v, o, t, "lowpass", 1300, 0.7, 0.35, 0.03);
      block(v, o, t + 0.035, 330 * k, 0.3, 0.05);
    },
  },
  arrow: {
    gain: 0.84,
    wet: 0.05,
    jitter: 0.6,
    run(v, o, t, k) {
      pluck(v, o, t, 165 * k, 0.9, {
        decay: 0.22,
        bright: 0.7,
        bend: 0.96,
        bendTime: 0.15,
        qin: true,
      });
      drum(v, o, t, 0.25, { f0: 110, f1: 70, decay: 0.05, slap: 0.3, tone: 0 });
      swish(v, o, t + 0.03, 0.48, 3400 * k, 1900 * k, 0.35, 7, 0.3);
      const w = v.osc("sine", 2300 * k, t + 0.05, t + 0.52);
      glide(w.frequency, t + 0.05, 2300 * k, 1650 * k, 0.45);
      const wg = v.gain();
      shape(wg.gain, t + 0.05, [
        [0.12, 0.03],
        [0.45, 0],
      ]);
      w.connect(wg).connect(o);
    },
  },
  stone: {
    gain: 0.44,
    wet: 0.08,
    jitter: 0.5,
    run(v, o, t, k) {
      // The arm creaks under tension while the ratchet clicks.
      const c = v.osc("sawtooth", 62 * k, t, t + 0.3);
      const cf = v.filter("bandpass", 560, 7);
      cf.frequency.setValueAtTime(560, t);
      cf.frequency.linearRampToValueAtTime(900, t + 0.12);
      cf.frequency.linearRampToValueAtTime(700, t + 0.28);
      const cg = v.gain();
      shape(cg.gain, t, [
        [0.06, 0.9],
        [0.22, 0.7],
        [0.28, 0],
      ]);
      c.connect(cf).connect(cg).connect(o);
      for (const dt of [0.02, 0.09, 0.16, 0.22]) block(v, o, t + dt, 900 * k, 0.2, 0.025);
      // The arm hits its stop and the stone flies.
      drum(v, o, t + 0.3, 0.55, { f0: 120 * k, f1: 55 * k, decay: 0.14, slap: 0.4 });
      swish(v, o, t + 0.33, 0.48, 280, 900, 0.3, 0.9, 0.6);
      // Impact.
      const hit = t + 0.82;
      drum(v, o, hit, 1, { f0: 85 * k, f1: 34 * k, decay: 0.5, slap: 0.6 });
      burst(v, o, hit, "lowpass", 320, 0.8, 0.7, 0.45, 0.004);
      burst(v, o, hit + 0.03, "bandpass", 950, 1.5, 0.22, 0.09);
      burst(v, o, hit + 0.1, "bandpass", 700, 1.5, 0.16, 0.08);
    },
  },

  // --- Stratagem schools ----------------------------------------------------
  fire: {
    gain: 0.77,
    wet: 0.1,
    jitter: 0.5,
    run(v, o, t, k) {
      // A rising whoosh over a low roar.
      swish(v, o, t, 1.0, 260 * k, 1700 * k, 0.45, 0.7, 0.45);
      const n = v.noise(t, t + 1.05);
      const lp = v.filter("lowpass", 220, 0.7);
      const g = v.gain();
      shape(g.gain, t, [
        [0.25, 0.7],
        [0.6, 0.45],
        [1.0, 0],
      ]);
      n.connect(lp).connect(g).connect(o);
      // Crackle: one noise source gated by short random spikes.
      const cn = v.noise(t, t + 1.0);
      const hp = v.filter("bandpass", 2400, 0.8);
      const cg = v.gain();
      cg.gain.setValueAtTime(0, t);
      for (let at = t + 0.04; at < t + 0.9; at += 0.035 + v.kit.rnd() * 0.07) {
        const a = 0.15 + v.kit.rnd() * 0.45;
        cg.gain.setValueAtTime(0, at);
        cg.gain.linearRampToValueAtTime(a, at + 0.002);
        cg.gain.exponentialRampToValueAtTime(a * 0.01, at + 0.012 + v.kit.rnd() * 0.014);
        cg.gain.linearRampToValueAtTime(0, at + 0.03);
      }
      cn.connect(hp).connect(cg).connect(o);
    },
  },
  water: {
    gain: 0.5,
    wet: 0.12,
    jitter: 0.5,
    run(v, o, t, k) {
      const n = v.noise(t, t + 1.25);
      const lp = v.filter("lowpass", 300, 0.9);
      lp.frequency.setValueAtTime(300 * k, t);
      lp.frequency.exponentialRampToValueAtTime(1800 * k, t + 0.45);
      lp.frequency.exponentialRampToValueAtTime(400 * k, t + 1.2);
      const g = v.gain();
      shape(g.gain, t, [
        [0.45, 0.8],
        [0.75, 0.5],
        [1.2, 0],
      ]);
      n.connect(lp).connect(g).connect(o);
      swish(v, o, t + 0.1, 1.0, 2600, 3200, 0.12, 0.6, 0.45);
      for (let i = 0; i < 5; i++) {
        const at = t + 0.2 + i * 0.15 + v.kit.rnd() * 0.06;
        const f = (500 + v.kit.rnd() * 500) * k;
        const b = v.osc("sine", f, at, at + 0.07);
        glide(b.frequency, at, f, f * 1.7, 0.05);
        const bg = v.gain();
        bg.gain.setValueAtTime(0, at);
        bg.gain.linearRampToValueAtTime(0.1, at + 0.004);
        bg.gain.exponentialRampToValueAtTime(0.0001, at + 0.06);
        b.connect(bg).connect(o);
      }
    },
  },
  earth: {
    gain: 0.28,
    wet: 0.08,
    jitter: 0.5,
    run(v, o, t, k) {
      const n = v.noise(t, t + 1.25);
      const lp = v.filter("lowpass", 150 * k, 1.2);
      const g = v.gain();
      shape(g.gain, t, [
        [0.2, 1.2],
        [0.6, 0.9],
        [1.2, 0],
      ]);
      n.connect(lp).connect(g).connect(o);
      const sub = v.osc("sine", 44 * k, t, t + 1.2);
      glide(sub.frequency, t, 44 * k, 36 * k, 1.1);
      const sg = v.gain();
      shape(sg.gain, t, [
        [0.15, 0.4],
        [0.7, 0.3],
        [1.15, 0],
      ]);
      sub.connect(sg).connect(o);
      for (let i = 0; i < 7; i++) {
        const at = t + 0.08 + i * 0.13 + v.kit.rnd() * 0.05;
        const f = (180 + v.kit.rnd() * 240) * k;
        const lv = 0.3 + v.kit.rnd() * 0.3;
        drum(v, o, at, lv, { f0: f, f1: f * 0.7, decay: 0.06, slap: 0, tone: 0 });
        burst(v, o, at, "bandpass", 500 + v.kit.rnd() * 600, 2, lv * 0.5, 0.04);
      }
    },
  },
  wind: {
    gain: 0.53,
    wet: 0.12,
    jitter: 0.5,
    run(v, o, t, k) {
      const p = v.panner(-0.5);
      if (isPanner(p)) {
        p.pan.setValueAtTime(-0.5, t);
        p.pan.linearRampToValueAtTime(0.5, t + 1.3);
      }
      p.connect(o);
      for (const [f0, f1, f2, q, lv] of [
        [380, 1400, 600, 4, 0.5],
        [900, 2300, 1000, 9, 0.18],
      ] as const) {
        const n = v.noise(t, t + 1.32);
        const bp = v.filter("bandpass", f0 * k, q);
        bp.frequency.setValueAtTime(f0 * k, t);
        bp.frequency.exponentialRampToValueAtTime(f1 * k, t + 0.5);
        bp.frequency.exponentialRampToValueAtTime(f2 * k, t + 1.25);
        const g = v.gain();
        const top = lv * Math.sqrt(q) * 1.4;
        shape(g.gain, t, [
          [0.4, top],
          [0.85, top * 0.7],
          [1.3, 0],
        ]);
        n.connect(bp).connect(g).connect(p);
      }
    },
  },
  heal: {
    gain: 0.15,
    wet: 0.2,
    jitter: 0,
    run(v, o, t, k) {
      jps(74, "1 2 3 5 6 1'").forEach((m, i) => {
        bell(v, o, t + i * 0.085, midiHz(m) * k, 0.5 + i * 0.06, 0.65);
      });
      swish(v, o, t, 0.9, 3000, 6000, 0.05, 0.7, 0.5);
    },
  },
  buff: {
    gain: 0.3,
    wet: 0.15,
    jitter: 0,
    run(v, o, t, k) {
      const lp = v.filter("lowpass", 4500, 0.5);
      const trem = v.gain(0.7);
      const lfo = v.osc("sine", 11, t, t + 0.9);
      const depth = v.gain(0.3);
      lfo.connect(depth).connect(trem.gain);
      const g = v.gain();
      shape(g.gain, t, [
        [0.12, 0.7],
        [0.55, 0.5],
        [0.85, 0],
      ]);
      for (const m of jps(74, "1 3 5")) {
        const f = midiHz(m) * k;
        const s = v.osc("triangle", f, t, t + 0.9);
        glide(s.frequency, t, f, f * 2, 0.6);
        const sg = v.gain(0.33);
        s.connect(sg).connect(trem);
      }
      trem.connect(lp).connect(g).connect(o);
      bell(v, o, t + 0.5, 1175 * k, 0.35, 0.4);
    },
  },
  debuff: {
    gain: 0.26,
    wet: 0.1,
    jitter: 0,
    run(v, o, t, k) {
      const lp = v.filter("lowpass", 1500, 0.7);
      lp.frequency.setValueAtTime(1500, t);
      lp.frequency.exponentialRampToValueAtTime(450, t + 0.8);
      const g = v.gain();
      shape(g.gain, t, [
        [0.05, 0.55],
        [0.4, 0.45],
        [0.9, 0],
      ]);
      const lfo = v.osc("sine", 6.5, t, t + 0.92);
      const depth = v.gain(30);
      lfo.connect(depth);
      for (const d of [0, 22]) {
        const s = v.osc("sawtooth", 392 * k, t, t + 0.92);
        s.detune.value = d;
        glide(s.frequency, t, 392 * k, 185 * k, 0.75);
        depth.connect(s.detune);
        const sg = v.gain(0.4);
        s.connect(sg).connect(lp);
      }
      const sub = v.osc("sine", 196 * k, t, t + 0.92);
      glide(sub.frequency, t, 196 * k, 92 * k, 0.75);
      const subg = v.gain(0.5);
      sub.connect(subg).connect(lp);
      lp.connect(g).connect(o);
    },
  },

  // --- Events ----------------------------------------------------------------
  defeat: {
    gain: 0.39,
    wet: 0.15,
    jitter: 0,
    run(v, o, t, k) {
      drum(v, o, t, 0.9, { f0: 95 * k, f1: 40 * k, decay: 0.7, slap: 0.4, tone: 0.25 });
      gong(v, o, t + 0.02, 98 * k, 0.65, 1.3, 0.965);
      pluck(v, o, t + 0.05, 147 * k, 0.4, {
        decay: 0.9,
        qin: true,
        bend: 0.89,
        bendAt: 0.15,
        bendTime: 0.4,
      });
    },
  },
  turn: {
    gain: 0.46,
    wet: 0.12,
    jitter: 0,
    run(v, o, t, k) {
      drum(v, o, t, 1, { f0: 125 * k, f1: 48 * k, decay: 0.75, slap: 0.45, tone: 0.3 });
      gong(v, o, t + 0.05, 470 * k, 0.32, 1.0, 1.045, 0.15);
    },
  },
  cutin: {
    gain: 0.43,
    wet: 0.1,
    jitter: 0,
    run(v, o, t, k) {
      swish(v, o, t, 0.3, 380 * k, 3800 * k, 0.7, 1.1, 0.85);
      swish(v, o, t + 0.04, 0.26, 200 * k, 1400 * k, 0.45, 0.8, 0.85);
      cymbal(v, o, t + 0.29, 0.35, 0.55);
      drum(v, o, t + 0.29, 0.9, { f0: 160 * k, f1: 58 * k, decay: 0.32, slap: 0.5, tone: 0.3 });
    },
  },
  arrive: {
    gain: 0.27,
    wet: 0.18,
    jitter: 0,
    run(v, o, t, k) {
      horn(v, o, t, 196 * k, 0.32, 0.8);
      horn(v, o, t + 0.38, 262 * k, 0.7, 0.9);
      drum(v, o, t, 0.45, { f0: 120, f1: 50, decay: 0.5, slap: 0.3 });
      drum(v, o, t + 0.38, 0.55, { f0: 120, f1: 50, decay: 0.5, slap: 0.3 });
    },
  },
  levelUp: {
    gain: 0.24,
    wet: 0.12,
    jitter: 0,
    run(v, o, t, k) {
      jps(72, "1 2 3 5 6 1' 2' 3'").forEach((m, i) => {
        pluck(v, o, t + i * 0.055, midiHz(m) * k, 0.5 + i * 0.05, { decay: 0.42 });
      });
      bell(v, o, t + 0.385, hz(72, "1'") * k, 0.45, 0.45);
    },
  },
  promote: {
    gain: 0.24,
    wet: 0.18,
    jitter: 0,
    run(v, o, t, k) {
      drum(v, o, t, 0.7, { f0: 130, f1: 50, decay: 0.5, slap: 0.4 });
      jps(72, "5, 1 2 3 5").forEach((m, i) => {
        pluck(v, o, t + i * 0.07, midiHz(m) * k, 0.55 + i * 0.05, { decay: 0.4 });
      });
      const hit = t + 0.38;
      jps(72, "1 3 5 1'").forEach((m, i) => {
        pluck(v, o, hit + i * 0.022, midiHz(m) * k, 0.6, { decay: 1.1 });
      });
      flute(v, o, hit, hz(72, "1'") * k, 0.8, 0.45);
      gong(v, o, hit, 180 * k, 0.5, 1.2, 0.98);
      drum(v, o, hit, 0.9, { f0: 140, f1: 50, decay: 0.6, slap: 0.5 });
      cymbal(v, o, hit, 0.15, 0.9);
    },
  },
  coin: {
    gain: 0.56,
    wet: 0.05,
    jitter: 0.3,
    run(v, o, t, k) {
      ring(v, o, t, 2100 * k, 0.5, 0.16, CLINK);
      ring(v, o, t + 0.085, 2550 * k, 0.45, 0.2, CLINK);
    },
  },
  equip: {
    gain: 0.62,
    wet: 0.03,
    jitter: 0.6,
    run(v, o, t, k) {
      // Leather rustle in three quick pulls.
      const n = v.noise(t, t + 0.25);
      const bp = v.filter("bandpass", 1200 * k, 0.9);
      const g = v.gain();
      shape(g.gain, t, [
        [0.01, 0.5],
        [0.04, 0.05],
        [0.055, 0.4],
        [0.09, 0.05],
        [0.115, 0.55],
        [0.2, 0],
      ]);
      n.connect(bp).connect(g).connect(o);
      const c = v.osc("sawtooth", 105 * k, t + 0.02, t + 0.15);
      const cf = v.filter("bandpass", 650, 5);
      const cg = v.gain();
      shape(cg.gain, t + 0.02, [
        [0.03, 0.4],
        [0.12, 0],
      ]);
      c.connect(cf).connect(cg).connect(o);
      ring(v, o, t + 0.14, 1500 * k, 0.3, 0.14, CLINK);
    },
  },
  victory: {
    gain: 0.32,
    wet: 0.2,
    jitter: 0,
    run(v, o, t, k) {
      const line = [
        [0, "5,", 0.15],
        [0.15, "1", 0.15],
        [0.3, "2", 0.15],
        [0.45, "3", 0.3],
        [0.75, "5", 0.15],
        [0.9, "3", 0.15],
        [1.05, "5", 0.15],
        [1.2, "6", 0.3],
        [1.5, "1'", 1.1],
      ] as const;
      for (const [at, tok, dur] of line) {
        const f = hz(72, tok) * k;
        flute(v, o, t + at, f, dur, 0.4, 0, 14);
        pluck(v, o, t + at, f / 2, 0.45, { decay: 0.5 });
      }
      for (const [at, lv] of [
        [0, 0.8],
        [0.45, 0.6],
        [0.9, 0.7],
      ] as const) {
        drum(v, o, t + at, lv, { f0: 140, f1: 52, decay: 0.5, slap: 0.35 });
      }
      for (const at of [1.2, 1.35]) {
        drum(v, o, t + at, 0.45, { f0: 270, f1: 175, decay: 0.18, slap: 0.5 });
      }
      const end = t + 1.5;
      drum(v, o, end, 1, { f0: 150, f1: 50, decay: 0.8, slap: 0.4 });
      jps(60, "1 3 5 1'").forEach((m, i) => {
        pluck(v, o, end + i * 0.025, midiHz(m) * k, 0.55, { decay: 1.4 });
      });
      gong(v, o, end, 110 * k, 0.55, 1.6, 0.97);
      cymbal(v, o, end, 0.2, 1.2);
    },
  },
  loss: {
    gain: 0.42,
    wet: 0.22,
    jitter: 0,
    run(v, o, t, k) {
      drum(v, o, t, 0.7, { f0: 85, f1: 40, decay: 0.8, slap: 0.3 });
      let from = 0;
      for (const [at, tok, dur] of [
        [0, "6", 0.5],
        [0.55, "5", 0.4],
        [1.0, "3", 0.45],
        [1.5, "2", 0.4],
        [1.95, "1", 0.35],
        [2.35, "6,", 0.75],
      ] as const) {
        const f = hz(60, tok) * k;
        erhu(v, o, t + at, f, dur, 0.5, from, 24);
        from = f;
      }
      for (const [at, tok] of [
        [0, "6,,"],
        [1.0, "3,"],
        [2.35, "6,,"],
      ] as const) {
        pluck(v, o, t + at, hz(60, tok) * k, 0.5, { decay: 1.4, qin: true, bright: 0.15 });
      }
      gong(v, o, t + 2.35, 88 * k, 0.4, 1.0, 0.97);
    },
  },
};

export const SFX_NAMES = Object.keys(RECIPES) as Sfx[];

/**
 * Build one effect at time `t` into the mix. `onDone` runs once when the
 * effect has finished (or failed to build). Returns false on failure.
 */
export function triggerSfx(
  mix: Mix,
  name: Sfx,
  t: number,
  o: PlayOpts,
  onDone: (() => void) | null,
): boolean {
  const r = RECIPES[name];
  const v = new Voice(mix.kit, onDone);
  if (!r) {
    v.dispose();
    return false;
  }
  try {
    const jitter = r.jitter > 0 ? (mix.kit.rnd() * 2 - 1) * r.jitter : 0;
    const k = 2 ** (clamp((o.pitch ?? 0) + jitter, -24, 24) / 12);
    const g = v.gain(r.gain * clamp(o.gain ?? 1, 0, 1));
    const p = v.panner(o.pan ?? 0);
    g.connect(p).connect(mix.sfx);
    if (r.wet > 0) p.connect(v.gain(r.wet)).connect(mix.sfxWet);
    r.run(v, g, t, k);
    v.seal();
    return true;
  } catch {
    v.dispose();
    return false;
  }
}
