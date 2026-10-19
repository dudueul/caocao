/**
 * The music sequencer. A song is a few parts written as jianpu patterns
 * (see jianpu.ts); the Player walks them in sixteenth-note steps on the audio
 * clock and builds each note just ahead of time. A little reproducible
 * variation (velocity, timing, ornaments, dropped or ghost notes) comes from a
 * hash of the song seed, the loop pass and the step, never from Math.random.
 */
import { parseNote, pentSemis } from "./jianpu.ts";
import {
  bell,
  block,
  cymbal,
  drum,
  erhu,
  flute,
  gong,
  hash,
  type Kit,
  midiHz,
  pad,
  pluck,
  Voice,
} from "./synth.ts";

export type Inst = "zheng" | "qin" | "flute" | "erhu" | "pad" | "bell" | "perc";

export interface Part {
  inst: Inst;
  /** Tokens per beat: 1 quarters, 2 eighths, 4 sixteenths. */
  div: 1 | 2 | 4;
  /**
   * Jianpu tokens ("5," "1" "3'!" ...), "-" holds, "." rests, "|" is a bar
   * line for the reader. Percussion parts use the letters in HITS.
   */
  notes: string;
  /** Octave offset. */
  oct?: number;
  /** Pentatonic steps to move the pattern, per bar (repeats). */
  shift?: readonly number[];
  vel?: number;
  pan?: number;
  /** Pluck decay override in seconds. */
  decay?: number;
  /** Chance to skip an unaccented off-beat note. */
  drop?: number;
  /** Chance of a grace note on long flute notes. */
  grace?: number;
  /** Chance of a soft drum on an empty eighth (percussion). */
  ghost?: number;
  /** Play only on loop passes where pass % every[0] === every[1]. */
  every?: readonly [number, number];
}

export interface Song {
  bpm: number;
  beats: number;
  bars: number;
  /** MIDI note of jianpu "1". */
  key: number;
  gain: number;
  seed: number;
  parts: readonly Part[];
}

type Hit = (v: Voice, out: AudioNode, t: number, vel: number, key: number) => void;

/** Percussion letters. */
export const HITS: Record<string, Hit> = {
  D: (v, o, t, g) => drum(v, o, t, g, { f0: 165, f1: 64, decay: 0.7, slap: 0.45, tone: 0.35 }),
  d: (v, o, t, g) =>
    drum(v, o, t, g * 0.55, { f0: 150, f1: 62, decay: 0.6, slap: 0.35, tone: 0.3 }),
  T: (v, o, t, g) => drum(v, o, t, g * 0.55, { f0: 280, f1: 175, decay: 0.22, slap: 0.6 }),
  t: (v, o, t, g) => drum(v, o, t, g * 0.3, { f0: 270, f1: 175, decay: 0.18, slap: 0.5 }),
  k: (v, o, t, g) => block(v, o, t, 1050, g * 0.4, 0.06),
  r: (v, o, t, g) => block(v, o, t, 1650, g * 0.22, 0.03),
  G: (v, o, t, g) => gong(v, o, t, 92, g * 0.5, 4.5, 0.97),
  g: (v, o, t, g) => gong(v, o, t, 400, g * 0.22, 1.4, 1.05, 0.15),
  C: (v, o, t, g) => cymbal(v, o, t, g * 0.22, 1.4),
  c: (v, o, t, g) => cymbal(v, o, t, g * 0.16, 0.25),
  b: (v, o, t, g, key) => bell(v, o, t, midiHz(key + 12), g * 0.25, 1.6),
};

const BASE_VEL: Record<Inst, number> = {
  zheng: 0.3,
  qin: 0.26,
  flute: 0.3,
  erhu: 0.34,
  pad: 0.06,
  bell: 0.2,
  perc: 1,
};

interface NoteEv {
  kind: "note";
  len: number;
  pent: number;
  extra: number;
  accent: boolean;
  soft: boolean;
}
interface HitEv {
  kind: "hit";
  code: string;
}
type Ev = NoteEv | HitEv;

interface Compiled {
  /** Pattern length in sixteenths. */
  len: number;
  events: (Ev | undefined)[];
  errors: string[];
}

export function compile(part: Part): Compiled {
  const unit = 4 / part.div;
  const toks = part.notes.split(/\s+/).filter((s) => s !== "" && s !== "|");
  const events: (Ev | undefined)[] = Array(toks.length * unit).fill(undefined);
  const errors: string[] = [];
  let open: NoteEv | null = null;
  toks.forEach((tok, i) => {
    if (tok === "-") {
      if (open) open.len += unit;
      return;
    }
    open = null;
    if (tok === ".") return;
    if (part.inst === "perc") {
      if (HITS[tok]) events[i * unit] = { kind: "hit", code: tok };
      else errors.push(`unknown hit "${tok}"`);
      return;
    }
    const n = parseNote(tok);
    if (!n) {
      errors.push(`bad note "${tok}"`);
      return;
    }
    const ev: NoteEv = { kind: "note", len: unit, ...n };
    events[i * unit] = ev;
    open = ev;
  });
  return { len: toks.length * unit, events, errors };
}

/** Problems with a song's data (pattern lengths that do not divide the loop, bad tokens). */
export function validate(song: Song): string[] {
  const loop = song.bars * song.beats * 4;
  const out: string[] = [];
  song.parts.forEach((p, i) => {
    const c = compile(p);
    if (c.len === 0 || loop % c.len !== 0) out.push(`part ${i} (${p.inst}): ${c.len} steps`);
    for (const e of c.errors) out.push(`part ${i} (${p.inst}): ${e}`);
  });
  return out;
}

export const loopSeconds = (s: Song): number => (s.bars * s.beats * 60) / s.bpm;

interface Lane extends Compiled {
  part: Part;
  out: AudioNode;
  /** Last pitch and end time, for fiddle slides. */
  lastF: number;
  lastEnd: number;
}

/** Plays one song on a loop into `dest` through its own fade gain. */
export class Player {
  readonly out: GainNode;
  private readonly lanes: Lane[];
  private readonly step16: number;
  private readonly loop: number;
  private readonly bar: number;
  private step = 0;
  private next: number;

  constructor(
    private readonly kit: Kit,
    readonly song: Song,
    dest: AudioNode,
    start: number,
  ) {
    const ac = kit.ac;
    this.out = ac.createGain();
    this.out.gain.value = song.gain;
    this.out.connect(dest);
    this.step16 = 60 / song.bpm / 4;
    this.bar = song.beats * 4;
    this.loop = song.bars * this.bar;
    this.next = start;
    this.lanes = song.parts.map((part) => {
      let out: AudioNode;
      if (typeof ac.createStereoPanner === "function") {
        const p = ac.createStereoPanner();
        p.pan.value = part.pan ?? 0;
        out = p;
      } else out = ac.createGain();
      out.connect(this.out);
      return { ...compile(part), part, out, lastF: 0, lastEnd: 0 };
    });
  }

  fadeIn(now: number, dur: number): void {
    const g = this.out.gain;
    g.cancelScheduledValues(now);
    g.setValueAtTime(0, now);
    g.linearRampToValueAtTime(this.song.gain, now + dur);
  }

  fadeOut(now: number, dur: number): void {
    const g = this.out.gain;
    const from = g.value;
    g.cancelScheduledValues(now);
    g.setValueAtTime(from, now);
    g.linearRampToValueAtTime(0, now + dur);
  }

  /** Schedule every step that starts before `until`. */
  tick(now: number, until: number): void {
    // Woken late (throttled timer): skip ahead rather than pile up notes.
    if (this.next < now - 0.05) this.next = now + 0.03;
    while (this.next < until) {
      this.schedule(this.step, this.next, now);
      this.step++;
      this.next += this.step16;
    }
  }

  /** After the scheduler was paused, carry on from the same place in the music. */
  resync(now: number): void {
    if (this.next < now + 0.02) this.next = now + 0.06;
  }

  dispose(): void {
    for (const l of this.lanes) l.out.disconnect();
    this.out.disconnect();
  }

  private schedule(step: number, time: number, now: number): void {
    const { song } = this;
    const pos = step % this.loop;
    const pass = Math.floor(step / this.loop);
    const barNo = Math.floor(pos / this.bar);
    this.lanes.forEach((lane, li) => {
      const p = lane.part;
      if (p.every && pass % p.every[0] !== p.every[1]) return;
      const at = pos % lane.len;
      const ev = lane.events[at];
      const r = (salt: number) => hash(song.seed, pass, pos, li, salt);
      const vel = (p.vel ?? BASE_VEL[p.inst]) * (0.9 + 0.2 * r(1));
      if (!ev) {
        if (p.ghost && at % 2 === 0 && r(2) < p.ghost) this.hit(lane, "t", time, vel * 0.5);
        return;
      }
      if (ev.kind === "hit") {
        this.hit(lane, ev.code, Math.max(now, time + (r(3) - 0.5) * 0.006), vel);
        return;
      }
      // Gentle metric accent: beats > eighths > sixteenths.
      const metric = at % 4 === 0 ? 1 : at % 2 === 0 ? 0.9 : 0.82;
      if (p.drop && !ev.accent && at % 4 !== 0 && r(4) < p.drop) return;
      const shift = p.shift ? (p.shift[barNo % p.shift.length] ?? 0) : 0;
      const base = song.key + 12 * (p.oct ?? 0) + ev.extra;
      const f = midiHz(base + pentSemis(ev.pent + shift));
      const t = Math.max(now, time + (r(5) - 0.5) * 0.012);
      const dur = ev.len * this.step16;
      const v = vel * metric * (ev.accent ? 1.2 : 1) * (ev.soft ? 0.6 : 1);
      const voice = new Voice(this.kit);
      try {
        this.note(voice, lane, ev, t, f, dur, v, r, shift);
      } catch {
        voice.dispose();
        return;
      }
      voice.seal();
    });
  }

  private note(
    voice: Voice,
    lane: Lane,
    ev: NoteEv,
    t: number,
    f: number,
    dur: number,
    v: number,
    r: (salt: number) => number,
    shift: number,
  ): void {
    const p = lane.part;
    const key = this.song.key;
    switch (p.inst) {
      case "zheng":
        pluck(voice, lane.out, t, f, v, {
          decay: p.decay ?? Math.min(2.6, 2 * Math.sqrt(262 / f)),
          vib: dur >= 0.6 ? 10 : 0,
          // No pick click on fast ostinatos: cheaper, and less clatter.
          pick: p.div === 4 ? 0 : 0.18,
        });
        break;
      case "qin":
        pluck(voice, lane.out, t, f, v, {
          qin: true,
          bright: 0.15,
          pick: p.div === 4 ? 0 : 0.12,
          decay: p.decay ?? Math.min(3, 2.6 * Math.sqrt(196 / f)),
        });
        break;
      case "flute": {
        const grace =
          ev.len >= 4 && r(6) < (p.grace ?? 0.2)
            ? midiHz(key + 12 * (p.oct ?? 0) + pentSemis(ev.pent + shift + 1))
            : 0;
        flute(voice, lane.out, t, f, dur * 0.95, v, grace);
        break;
      }
      case "erhu": {
        const leap = lane.lastF > 0 && Math.abs(Math.log2(f / lane.lastF)) > 0.2;
        const from = leap && t - lane.lastEnd < 0.3 && r(7) < 0.6 ? lane.lastF : 0;
        erhu(voice, lane.out, t, f, dur * 0.97, v, from);
        lane.lastF = f;
        lane.lastEnd = t + dur;
        break;
      }
      case "pad":
        pad(voice, lane.out, t, f, dur, v);
        break;
      case "bell":
        bell(voice, lane.out, t, f, v, p.decay ?? 1.8);
        break;
      case "perc":
        break;
    }
  }

  private hit(lane: Lane, code: string, t: number, vel: number): void {
    const h = HITS[code];
    if (!h) return;
    const v = new Voice(this.kit);
    try {
      h(v, lane.out, t, vel, this.song.key);
      v.seal();
    } catch {
      v.dispose();
    }
  }
}
