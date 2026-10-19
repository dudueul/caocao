/**
 * Player settings (ADR 0054): sound volumes, battle speed, effects quality,
 * hints. Kept in this browser's storage; every read and write is guarded, so
 * a private window or blocked storage just means defaults.
 */
import { audio } from "./audio/index.ts";

export type TextSpeed = "slow" | "normal" | "fast" | "instant";

export interface Settings {
  master: number;
  music: number;
  sfx: number;
  /** Battle animation speed: 1, 2 or 4 (0 skips animations). */
  speed: 0 | 1 | 2 | 4;
  /** Lighter effects for slow devices. */
  lite: boolean;
  /** First-battle tips and the objective reminder. */
  hints: boolean;
  /** Show enemy danger zones from the start of each battle. */
  danger: boolean;
  textSpeed: TextSpeed;
}

const KEY = "tk.settings";
const DEFAULTS: Settings = {
  master: 70,
  music: 45,
  sfx: 70,
  speed: 1,
  lite: false,
  hints: true,
  danger: false,
  textSpeed: "normal",
};

function load(): Settings {
  try {
    const raw = localStorage.getItem(KEY);
    if (!raw) return { ...DEFAULTS };
    const v = JSON.parse(raw) as Partial<Settings>;
    const vol = (n: unknown, d: number) =>
      typeof n === "number" && n >= 0 && n <= 100 ? Math.round(n) : d;
    return {
      master: vol(v.master, DEFAULTS.master),
      music: vol(v.music, DEFAULTS.music),
      sfx: vol(v.sfx, DEFAULTS.sfx),
      speed: v.speed === 0 || v.speed === 2 || v.speed === 4 ? v.speed : 1,
      lite: v.lite === true,
      hints: v.hints !== false,
      danger: v.danger === true,
      textSpeed:
        v.textSpeed === "slow" || v.textSpeed === "fast" || v.textSpeed === "instant"
          ? v.textSpeed
          : "normal",
    };
  } catch {
    return { ...DEFAULTS };
  }
}

export const settings = $state<Settings>(load());

/** Milliseconds per character of dialogue. */
export const TEXT_MS: Record<TextSpeed, number> = { slow: 45, normal: 26, fast: 12, instant: 0 };

$effect.root(() => {
  $effect(() => {
    const s = { ...settings };
    audio.setVolumes({ master: s.master, music: s.music, sfx: s.sfx });
    try {
      localStorage.setItem(KEY, JSON.stringify(s));
    } catch {
      // Storage may be blocked; settings then last for this visit only.
    }
  });
});
