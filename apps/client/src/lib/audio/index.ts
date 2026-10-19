/**
 * Game audio, all synthesized with the Web Audio API: no files, samples or
 * fetches. Effects are short node graphs built per call; music is a set of
 * original pentatonic pieces played by a lookahead scheduler on the audio
 * clock. Without an AudioContext (SSR, tests, old browsers) every call is a
 * silent no-op, and nothing is created before `unlock()`.
 */
import { applyVolumes, buildMix, DEFAULT_VOLUMES, type Mix } from "./mix.ts";
import { Player } from "./music.ts";
import { type PlayOpts, SFX_NAMES, triggerSfx } from "./sfx.ts";
import { SONGS } from "./songs.ts";

export type Sfx =
  | "click"
  | "confirm"
  | "cancel"
  | "select"
  | "page" // UI: buttons, select a unit, advance dialogue
  | "move"
  | "hoof" // foot march step, horse gallop step
  | "hit"
  | "crit"
  | "miss"
  | "guard" // melee strike landed, critical, whiff, blocked/weak hit
  | "arrow"
  | "stone" // bow release+flight, traction-trebuchet throw+impact
  | "fire"
  | "water"
  | "earth"
  | "wind"
  | "heal"
  | "buff"
  | "debuff" // stratagem schools
  | "defeat" // a unit is routed (low gong / falling)
  | "turn" // a new turn banner (single war-drum hit + small gong)
  | "cutin" // skill cut-in swoosh + accent
  | "arrive" // reinforcements arrive (horn call)
  | "levelUp"
  | "promote" // short bright fanfares (promote is grander)
  | "coin"
  | "equip" // shop purchase, equip an item
  | "victory"
  | "loss"; // battle won / lost stingers (2–4 s)

export type Track = "title" | "story" | "camp" | "battle" | "danger" | "ending";

/** Each 0–100. */
export interface Volumes {
  master: number;
  music: number;
  sfx: number;
}

export interface AudioApi {
  /** Create/resume the AudioContext; call from the first user gesture (autoplay policy). Safe to call often. */
  unlock(): void;
  /** Fire-and-forget one effect. pan −1..1, gain 0..1 multiplier, pitch in semitones. */
  play(name: Sfx, opts?: { pan?: number; gain?: number; pitch?: number }): void;
  /** Crossfade to a looping track (≈1.5 s), or fade out with null. Re-requesting the current track is a no-op. */
  music(track: Track | null): void;
  setVolumes(v: Volumes): void;
  readonly volumes: Volumes;
  /** True once a context exists and is running. */
  readonly ready: boolean;
}

export { DEFAULT_VOLUMES, SFX_NAMES };
export const TRACKS = Object.keys(SONGS) as Track[];

/** How far ahead music is scheduled, and how often the scheduler wakes. */
const LOOKAHEAD = 0.12;
const TICK_MS = 25;
const FADE = 1.5;
/** Time after a fade for ringing notes (gongs) before the old track is torn down. */
const TAIL = 5;
const MAX_VOICES = 24;
/** The same effect starts at most once per this many seconds. */
const MIN_GAP = 0.04;

type Ctor = new (opts?: AudioContextOptions) => AudioContext;

function audioCtor(): Ctor | null {
  if (typeof window === "undefined") return null;
  const g = globalThis as { AudioContext?: Ctor; webkitAudioContext?: Ctor };
  return g.AudioContext ?? g.webkitAudioContext ?? null;
}

interface Fading {
  player: Player;
  stopAt: number;
  killAt: number;
}

class Engine {
  private ac: AudioContext | null = null;
  private mix: Mix | null = null;
  private dead = false;
  private vol: Volumes = { ...DEFAULT_VOLUMES };
  private want: Track | null = null;
  private cur: { track: Track; player: Player } | null = null;
  private fading: Fading[] = [];
  private timer: ReturnType<typeof setInterval> | null = null;
  private voices = 0;
  private last = new Map<Sfx, number>();

  get ready(): boolean {
    return this.ac?.state === "running";
  }

  get volumes(): Volumes {
    return { ...this.vol };
  }

  /** Effects sounding now, and the playing track (for the dev page). */
  stats(): { state: string; voices: number; track: Track | null; fading: number } {
    return {
      state: this.ac?.state ?? (this.dead ? "unavailable" : "locked"),
      voices: this.voices,
      track: this.cur?.track ?? null,
      fading: this.fading.length,
    };
  }

  unlock(): void {
    if (this.dead) return;
    if (!this.ac) {
      const Ctor = audioCtor();
      if (!Ctor) {
        this.dead = true;
        return;
      }
      try {
        const ac = new Ctor({ latencyHint: "interactive" });
        this.mix = buildMix(ac, this.vol);
        this.ac = ac;
      } catch {
        this.dead = true;
        this.ac = null;
        this.mix = null;
        return;
      }
      this.ac.addEventListener("statechange", () => this.sync());
      if (typeof document !== "undefined") {
        document.addEventListener("visibilitychange", () => this.sync());
      }
      this.prime();
    }
    const ac = this.ac;
    if (ac.state !== "running" && ac.state !== "closed") {
      ac.resume().then(
        () => this.sync(),
        () => {},
      );
    }
    this.sync();
  }

  play(name: Sfx, o: PlayOpts): void {
    const ac = this.ac;
    const mix = this.mix;
    if (!ac || !mix || ac.state !== "running") return;
    if (this.voices >= MAX_VOICES) return;
    const now = ac.currentTime;
    const prev = this.last.get(name);
    if (prev !== undefined && now - prev < MIN_GAP) return;
    this.last.set(name, now);
    this.voices++;
    triggerSfx(mix, name, now, o, () => {
      this.voices--;
    });
  }

  music(track: Track | null): void {
    if (track !== null && !(track in SONGS)) return;
    this.want = track;
    this.sync();
  }

  setVolumes(v: Volumes): void {
    const pick = (x: number, old: number) =>
      Number.isFinite(x) ? Math.min(100, Math.max(0, x)) : old;
    this.vol = {
      master: pick(v.master, this.vol.master),
      music: pick(v.music, this.vol.music),
      sfx: pick(v.sfx, this.vol.sfx),
    };
    if (this.mix) applyVolumes(this.mix, this.vol);
  }

  /** Bring the players and the scheduler in line with the wanted track, context state and visibility. */
  private sync(): void {
    const ac = this.ac;
    if (!ac || !this.mix) return;
    const running = ac.state === "running";
    if (running && this.want !== (this.cur?.track ?? null)) this.switchTo(this.want);
    const visible = typeof document === "undefined" || !document.hidden;
    const busy = this.cur !== null || this.fading.length > 0;
    if (running && visible && busy) this.start();
    else this.stop();
  }

  private switchTo(track: Track | null): void {
    const ac = this.ac;
    const mix = this.mix;
    if (!ac || !mix) return;
    const now = ac.currentTime;
    if (this.cur) {
      this.cur.player.fadeOut(now, FADE);
      this.fading.push({ player: this.cur.player, stopAt: now + FADE, killAt: now + FADE + TAIL });
      this.cur = null;
    }
    if (track) {
      const player = new Player(mix.kit, SONGS[track], mix.music, now + 0.08);
      player.fadeIn(now, FADE);
      this.cur = { track, player };
    }
  }

  private start(): void {
    if (this.timer !== null || !this.ac) return;
    const now = this.ac.currentTime;
    this.cur?.player.resync(now);
    for (const f of this.fading) f.player.resync(now);
    this.timer = setInterval(() => this.tick(), TICK_MS);
    this.tick();
  }

  private stop(): void {
    if (this.timer === null) return;
    clearInterval(this.timer);
    this.timer = null;
  }

  private tick(): void {
    const ac = this.ac;
    if (!ac) return;
    const now = ac.currentTime;
    const until = now + LOOKAHEAD;
    try {
      this.cur?.player.tick(now, until);
      for (const f of this.fading) {
        if (now < f.stopAt) f.player.tick(now, Math.min(until, f.stopAt));
      }
    } catch {
      // A bad note must not stop the music.
    }
    if (this.fading.some((f) => now >= f.killAt)) {
      for (const f of this.fading) if (now >= f.killAt) f.player.dispose();
      this.fading = this.fading.filter((f) => now < f.killAt);
    }
    if (!this.cur && this.fading.length === 0) this.stop();
  }

  /** A silent one-sample buffer, which some mobile browsers need to unlock output. */
  private prime(): void {
    const ac = this.ac;
    if (!ac) return;
    try {
      const s = ac.createBufferSource();
      s.buffer = ac.createBuffer(1, 1, ac.sampleRate);
      s.connect(ac.destination);
      s.onended = () => s.disconnect();
      s.start();
    } catch {
      // Not needed where it fails.
    }
  }
}

const engine = new Engine();

const guard = (fn: () => void): void => {
  try {
    fn();
  } catch {
    // Audio must never break the game.
  }
};

export const audio: AudioApi = {
  unlock: () => guard(() => engine.unlock()),
  play: (name, opts) => guard(() => engine.play(name, opts ?? {})),
  music: (track) => guard(() => engine.music(track)),
  setVolumes: (v) => guard(() => engine.setVolumes(v)),
  get volumes() {
    return engine.volumes;
  },
  get ready() {
    return engine.ready;
  },
};

/** Live engine state, for the dev page and debugging. */
export const audioStats = (): ReturnType<Engine["stats"]> => engine.stats();
