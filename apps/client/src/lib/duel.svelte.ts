/**
 * The duel screen's model (ADR 0071): what the 일기토 overlay shows, and when.
 * A battle drives it from the rules events of a duel: the player's own bout by
 * bout, or a watched story duel all at once. A staged scene drives it from
 * bouts drawn from the scene id. It decides nothing: it paces the reveal, the
 * clash and the blows, picks the faces and plays the sounds.
 */
import { OFFICERS } from "@tk/content";
import { Prng, STANCES, type Stance, seedFromString, type Unit } from "@tk/rules";
import { audio, type Sfx } from "./audio/index.ts";
import type { Mood } from "./fx/portrait.ts";
import { josa, T } from "./text.ts";

/** One fighter as the screen shows him. */
export interface Duelist {
  id: string;
  name: string;
  /** The name in hanja, if the officer has one. */
  hanja: string;
  classId: string;
  side: number;
  hp: number;
  maxHp: number;
  martial: number;
  intellect: number;
}

/** A battle unit as a fighter on the duel screen. */
export function duelist(u: Unit): Duelist {
  return {
    id: u.id,
    name: u.name,
    hanja: OFFICERS[u.id]?.hanja ?? "",
    classId: u.classId,
    side: u.side,
    hp: u.hp,
    maxHp: u.maxHp,
    martial: u.stats.martial,
    intellect: u.stats.intellect,
  };
}

/** One bout as the screen shows it: the rules' `bout` event, or one drawn for a scene. */
export interface BoutView {
  n: number;
  /** The challenger who fought this bout (in some story duels several take turns). */
  a: string;
  stanceA: Stance;
  stanceB: Stance;
  damageA: number;
  damageB: number;
  hpA: number;
  hpB: number;
}

/** How a duel ended, as the rules' `duelEnd` event says it. */
export interface DuelOutcome {
  winner: string | null;
  loser: string | null;
  fatal: boolean;
  withdrew: string | null;
}

export type DuelPhase = "intro" | "pick" | "swap" | "reveal" | "clash" | "hit" | "rest" | "end";

export interface DuelEnding {
  title: string;
  tone: "win" | "loss" | "even";
  line: string;
  winner: string | null;
  loser: string | null;
  /** The name of the one who died, for the 전사 seal. */
  fallen: string | null;
}

export interface DuelInit {
  a: Duelist;
  b: Duelist;
  /** Every challenger who may take a bout (the three brothers against 여포); default `[a]`. */
  cast?: Duelist[];
  /** A story duel, which ends as history did. */
  story: boolean;
  /** Fought out on its own (a watched story duel, a scene): there is no stance to pick. */
  watched: boolean;
  /** Most bouts. */
  bouts: number;
  /** Whether a side is the viewer's own or a friend's (for 승리 or 패배, and who stands left). */
  friendly: (side: number) => boolean;
  /** Time factor: 1 at normal speed, less when faster, 0 for no animation. */
  pace: () => number;
}

/** A blow landed in the bout on show. */
export interface Blow {
  on: string;
  amount: number;
  /** A 강공, or the guard turning one back. */
  heavy: boolean;
}

/** What beats what: 강공 breaks 공격, 방어 turns 강공 back, 공격 wears 방어 down. */
function beats(x: Stance, y: Stance): boolean {
  return (
    (x === "power" && y === "attack") ||
    (x === "guard" && y === "power") ||
    (x === "attack" && y === "guard")
  );
}

export class DuelShow {
  a: Duelist = $state.raw(BLANK);
  b: Duelist = $state.raw(BLANK);
  phase: DuelPhase = $state("intro");
  /** Bouts fought so far. */
  n = $state(0);
  stanceA: Stance | null = $state(null);
  stanceB: Stance | null = $state(null);
  /** The name of the challenger stepping in, while he does. */
  incoming: string | null = $state(null);
  /** The stance the player just picked, while the bout is on its way. */
  picked: Stance | null = $state(null);
  blows: Blow[] = $state.raw([]);
  /** Bumped every bout, so the screen restarts its clash animations. */
  beat = $state(0);
  ending: DuelEnding | null = $state.raw(null);
  closing = $state(false);
  skipping = $state(false);
  readonly story: boolean;
  readonly watched: boolean;
  readonly bouts: number;
  /** The viewer's own fighter stands on the left (the challenger if neither is). */
  readonly leftIsA: boolean;
  private readonly cast = new Map<string, Duelist>();
  private readonly init: DuelInit;
  private readonly timers = new Set<{ id: ReturnType<typeof setTimeout>; done: () => void }>();
  private release: (() => void) | null = null;

  constructor(init: DuelInit) {
    this.init = init;
    this.a = init.a;
    this.b = init.b;
    this.story = init.story;
    this.watched = init.watched;
    this.bouts = init.bouts;
    this.leftIsA = init.friendly(init.a.side) || !init.friendly(init.b.side);
    for (const d of [init.a, init.b, ...(init.cast ?? [])]) this.cast.set(d.id, d);
  }

  get left(): Duelist {
    return this.leftIsA ? this.a : this.b;
  }
  get right(): Duelist {
    return this.leftIsA ? this.b : this.a;
  }
  stanceOf(d: Duelist): Stance | null {
    return d.id === this.b.id ? this.stanceB : this.stanceA;
  }

  /** The bout under way or next to be fought (1-based). */
  get current(): number {
    return this.phase === "pick" || this.phase === "intro" || this.phase === "rest"
      ? Math.min(this.bouts, this.n + 1)
      : Math.max(1, this.n);
  }

  /** The time factor now: 0 while skipping. */
  get tempo(): number {
    return this.skipping ? 0 : this.init.pace();
  }

  /** The face a fighter makes: fierce or grim, hurt when struck, a smile for the winner. */
  mood(d: Duelist): Mood {
    const e = this.ending;
    if (e) {
      if (e.winner === d.id) return "smile";
      if (e.loser === d.id) return "hurt";
      return "grim";
    }
    if (this.phase === "hit" && this.blows.some((b) => b.on === d.id)) return "hurt";
    if (d.hp * 10 < d.maxHp * 4) return "grim";
    return d.martial >= d.intellect ? "angry" : "grim";
  }

  /** The fighters walk on and the title strikes. */
  async open(): Promise<void> {
    this.phase = "intro";
    this.sound("cutin");
    await this.wait(480);
    this.sound("turn");
    await this.wait(720);
    this.phase = this.watched ? "rest" : "pick";
  }

  /** The player picked a stance; the bout is on its way through the rules. */
  chosen(s: Stance): void {
    this.picked = s;
  }

  /** Shows one bout: both stances revealed, the clash, the blows. */
  async bout(e: BoutView): Promise<void> {
    if (e.a !== this.a.id) {
      // The next challenger steps in (the three brothers take turns against 여포).
      const next = this.cast.get(e.a);
      if (next) {
        this.phase = "swap";
        this.incoming = next.name;
        this.stanceA = null;
        this.stanceB = null;
        await this.wait(260);
        this.a = { ...next, hp: e.hpA + e.damageA };
        this.sound("cutin");
        await this.wait(700);
        this.incoming = null;
      }
    }
    this.stanceA = e.stanceA;
    this.stanceB = e.stanceB;
    this.picked = null;
    this.blows = [];
    this.n = e.n;
    this.phase = "reveal";
    this.sound("select");
    await this.wait(640);
    this.phase = "clash";
    await this.wait(200);
    const blows: Blow[] = [];
    if (e.damageB > 0)
      blows.push({ on: this.b.id, amount: e.damageB, heavy: heavy(e.stanceA, e.stanceB) });
    if (e.damageA > 0)
      blows.push({ on: e.a, amount: e.damageA, heavy: heavy(e.stanceB, e.stanceA) });
    this.blows = blows;
    this.a = { ...this.a, hp: e.hpA };
    this.b = { ...this.b, hp: e.hpB };
    this.beat += 1;
    this.phase = "hit";
    this.clashSound(e, blows);
    await this.wait(900);
    this.phase = this.watched ? "rest" : "pick";
    if (this.watched) await this.wait(260);
  }

  /** The banner, then the screen closes. */
  async end(o: DuelOutcome): Promise<void> {
    this.picked = null;
    this.ending = this.endingOf(o);
    this.phase = "end";
    this.sound(
      this.ending.tone === "win" ? "promote" : this.ending.tone === "loss" ? "defeat" : "turn",
    );
    if (this.ending.fallen) setTimeout(() => this.sound("defeat"), Math.max(0, 420 * this.tempo));
    const pace = this.tempo;
    await this.hold(pace === 0 ? 700 : Math.max(1300, (this.watched ? 2200 : 2800) * pace));
    this.closing = true;
    await this.wait(300);
  }

  /** Runs ahead: every wait ends at once; the banner shows only briefly. */
  skip(): void {
    this.skipping = true;
    for (const t of this.timers) {
      clearTimeout(t.id);
      t.done();
    }
    this.timers.clear();
    if (this.release) setTimeout(() => this.dismiss(), 500);
  }

  /** Closes the end banner now (a tap, Enter). */
  dismiss(): void {
    const r = this.release;
    this.release = null;
    r?.();
  }

  private endingOf(o: DuelOutcome): DuelEnding {
    const name = (id: string) => this.cast.get(id)?.name ?? id;
    const side = (id: string) => this.cast.get(id)?.side ?? -1;
    const friendly = this.init.friendly;
    if (o.withdrew) {
      const w = o.withdrew;
      return {
        title: T.withdrew,
        tone: friendly(side(w)) ? "even" : "win",
        line: `${josa(name(w), "이", "가")} 물러난다`,
        winner: null,
        loser: null,
        fallen: null,
      };
    }
    if (!o.winner || !o.loser)
      return {
        title: T.draw,
        tone: "even",
        line: "승부가 나지 않았다",
        winner: null,
        loser: null,
        fallen: null,
      };
    const w = o.winner;
    const l = o.loser;
    const won = friendly(side(w));
    const lost = friendly(side(l));
    return {
      title: won ? T.victory : lost ? T.defeat : `${name(w)} ${T.victory}`,
      tone: won ? "win" : lost ? "loss" : "even",
      line: `${josa(name(w), "이", "가")} ${josa(name(l), "을", "를")} ${o.fatal ? "베었다" : "꺾었다"}`,
      winner: w,
      loser: l,
      fallen: o.fatal ? name(l) : null,
    };
  }

  private clashSound(e: BoutView, blows: Blow[]): void {
    const pan = (id: string) => (id === this.left.id ? -0.45 : 0.45);
    if (blows.length === 0) {
      this.sound("guard", { pitch: -2 });
      return;
    }
    const counter =
      (e.stanceA === "guard" && e.stanceB === "power") ||
      (e.stanceB === "guard" && e.stanceA === "power");
    if (counter) this.sound("guard", { pan: 0 });
    for (const [i, b] of blows.entries()) {
      const name: Sfx = b.heavy ? "crit" : "hit";
      if (i === 0) this.sound(name, { pan: pan(b.on) });
      else setTimeout(() => this.sound(name, { pan: pan(b.on), pitch: -1 }), 90);
    }
  }

  private sound(name: Sfx, opts: { pan?: number; pitch?: number } = {}): void {
    if (this.tempo === 0) return;
    audio.play(name, opts);
  }

  private wait(ms: number): Promise<void> {
    const t = this.skipping ? 0 : ms * this.init.pace();
    if (t <= 0) return Promise.resolve();
    return new Promise((resolve) => {
      const entry = {
        id: setTimeout(() => {
          this.timers.delete(entry);
          resolve();
        }, t),
        done: resolve,
      };
      this.timers.add(entry);
    });
  }

  private hold(ms: number): Promise<void> {
    return new Promise((resolve) => {
      const id = setTimeout(() => this.dismiss(), this.skipping ? 600 : ms);
      this.release = () => {
        clearTimeout(id);
        resolve();
      };
    });
  }
}

const BLANK: Duelist = {
  id: "",
  name: "",
  hanja: "",
  classId: "infantry",
  side: 0,
  hp: 1,
  maxHp: 1,
  martial: 0,
  intellect: 0,
};

/** The blow from a stance against another lands hard: a 강공, or a guard turning one back. */
function heavy(by: Stance, against: Stance): boolean {
  return by === "power" || (by === "guard" && against === "power");
}

/**
 * Bouts for a staged scene's duel (ADR 0071), drawn from `seed` (the scene id),
 * so the scene plays the same every time. Both start at `hp`; the duel ends as
 * `outcome` says by its last bout, and a draw leaves both standing.
 */
export function sceneBouts(
  seed: string,
  a: string,
  count: number,
  outcome: "a" | "b" | "draw",
  hp = 100,
): BoutView[] {
  const prng = new Prng(seedFromString(seed));
  const n = Math.max(1, Math.min(9, Math.floor(count)));
  const floor = outcome === "draw" ? Math.floor(hp / 5) : Math.floor(hp / 10);
  const out: BoutView[] = [];
  let hpA = hp;
  let hpB = hp;
  const pick = () => STANCES[prng.int(STANCES.length)] as Stance;
  for (let k = 1; k <= n; k++) {
    let sa = pick();
    let sb = pick();
    const last = k === n;
    if (last && outcome !== "draw") {
      // The last bout is the winner's: his stance beats the loser's.
      const won = pick();
      const lost = STANCES.find((s) => beats(won, s)) as Stance;
      [sa, sb] = outcome === "a" ? [won, lost] : [lost, won];
    }
    // Base blows of 14–22 in a hundred, ×1.5 for a 강공 and ×1.2 for a guard turning one back.
    const blow = (by: Stance, against: Stance) => {
      const m = by === against ? (by === "power" ? 1500 : by === "attack" ? 1000 : 0) : 0;
      const w = beats(by, against) ? (by === "power" ? 1500 : by === "guard" ? 1200 : 1000) : m;
      return w === 0 ? 0 : Math.floor((Math.floor(((14 + prng.int(9)) * hp) / 100) * w) / 1000);
    };
    let toB = blow(sa, sb);
    let toA = blow(sb, sa);
    // Until the last bout nobody falls; then history's loser does.
    toA = Math.max(0, Math.min(toA, hpA - floor));
    toB = Math.max(0, Math.min(toB, hpB - floor));
    if (last && outcome === "a") toB = hpB;
    if (last && outcome === "b") toA = hpA;
    hpA -= toA;
    hpB -= toB;
    out.push({ n: k, a, stanceA: sa, stanceB: sb, damageA: toA, damageB: toB, hpA, hpB });
  }
  return out;
}
