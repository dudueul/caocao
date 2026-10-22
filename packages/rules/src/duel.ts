/**
 * 일기토, the duel (ADR 0071). An officer challenges an adjacent enemy officer;
 * the foe accepts or refuses by the odds and his temper. A duel is up to
 * `duelBouts` bouts. Each bout both pick a stance in secret, and the triangle
 * decides who lands: 강공 (power) breaks 공격 (attack), 방어 (guard) turns 강공
 * back on the striker, and 공격 wears 방어 down. 무력 sets how hard a blow lands.
 * A knockout ends it: the loser retreats (or, in a story duel that history ends
 * so, dies), the winner's side takes heart and the loser's loses it.
 *
 * Story duels (`ScriptedDuel`) end as history did whatever the stances. The
 * player still picks them when the duel is theirs to play, and they decide only
 * how it goes, not how it ends.
 */
import { hostile, isAlive, unitById } from "./board.ts";
import { addCondition, hasBlock } from "./conditions.ts";
import { clamp, permille } from "./math.ts";
import type { Prng } from "./prng.ts";
import type {
  BattleEvent,
  BattleState,
  Cell,
  DuelState,
  Ruleset,
  ScriptedDuel,
  Stance,
  Unit,
} from "./types.ts";

export const STANCES: readonly Stance[] = ["attack", "power", "guard"];

const adjacent = (a: Cell, b: Cell) => Math.abs(a.x - b.x) + Math.abs(a.y - b.y) === 1;

/** Duels are on in this mode (the Arena turns them off). */
export function duelsAllowed(rules: Ruleset): boolean {
  return rules.tuning.duelBouts > 0;
}

/** The story duel `a` would fight against `b` if he challenged from where he stands, if any. */
export function scriptedFor(state: BattleState, a: string, b: string): ScriptedDuel | null {
  return state.duels.find((d) => d.play && d.a[0] === a && d.b === b) ?? null;
}

/**
 * Why `a` cannot challenge `b` from `at`, or null when he can. A challenge
 * needs two living named officers on hostile sides, side by side.
 */
export function duelBlocked(
  rules: Ruleset,
  state: BattleState,
  a: Unit,
  b: Unit,
  at: Cell,
): string | null {
  if (!duelsAllowed(rules)) return "no duels in this mode";
  if (state.duel) return "a duel is under way";
  if (!a.named || !b.named) return "only named officers fight duels";
  if (a.structure || b.structure) return "only named officers fight duels";
  if (!isAlive(b)) return "no such target";
  if (!hostile(state, a.side, b.side)) return "cannot challenge an ally";
  if (!adjacent(at, b)) return "the foe must stand next to you";
  if (hasBlock(rules, a, "act")) return "cannot act";
  // A story duel that is watched, not played, is not the player's to start.
  const story = state.duels.find((d) => d.b === b.id && d.a[0] === a.id);
  if (story && !story.play) return "this duel is history's";
  return null;
}

/** ‰ that `b` takes up `a`'s challenge: more if he is the stronger, more if he is bold. */
export function acceptChance(rules: Ruleset, a: Unit, b: Unit): number {
  const t = rules.tuning;
  return clamp(
    t.duelAcceptBase +
      t.duelAcceptPerMartial * (b.stats.martial - a.stats.martial) +
      t.duelAcceptPerBoldness * (b.stats.martial - b.stats.intellect),
    t.duelAcceptMin,
    t.duelAcceptMax,
  );
}

/** HP a clean blow from `from` takes off `to`, before the stance multiplier. */
export function duelBlow(rules: Ruleset, from: Unit, to: Unit): number {
  const t = rules.tuning;
  const share = clamp(
    t.duelBlowBase + t.duelBlowPerMartial * (from.stats.martial - to.stats.martial),
    t.duelBlowMin,
    t.duelBlowMax,
  );
  return Math.max(1, permille(to.maxHp, share));
}

/**
 * The stance an officer picks by temper: the bold lean to 강공, the clever and
 * the hurt to 방어. `roll` is a number in [0, 1000).
 */
export function stanceByTemper(u: Unit, roll: number): Stance {
  const m = u.stats.martial;
  const i = u.stats.intellect;
  const power = 300 + 4 * Math.max(0, m - i);
  const guard = 300 + 4 * Math.max(0, i - m) + (u.hp * 10 < u.maxHp * 4 ? 300 : 0);
  const attack = 400;
  const total = power + guard + attack;
  const r = Math.floor((roll * total) / 1000);
  if (r < attack) return "attack";
  if (r < attack + power) return "power";
  return "guard";
}

/** The stance each stance beats, and the one that beats it: 강공 > 공격 > 방어 > 강공. */
const VICTIM: Readonly<Record<Stance, Stance>> = {
  power: "attack",
  guard: "power",
  attack: "guard",
};
const KILLER: Readonly<Record<Stance, Stance>> = {
  attack: "power",
  power: "guard",
  guard: "attack",
};

/** Multipliers‰ for one bout: [what a deals to b, what b deals to a]. */
function exchange(rules: Ruleset, sa: Stance, sb: Stance): [number, number] {
  const t = rules.tuning;
  if (sa === sb) {
    if (sa === "attack") return [1000, 1000];
    if (sa === "power") return [t.duelPower, t.duelPower];
    return [0, 0];
  }
  const beats = (x: Stance, y: Stance) =>
    (x === "power" && y === "attack") ||
    (x === "guard" && y === "power") ||
    (x === "attack" && y === "guard");
  const hit = (s: Stance) => (s === "power" ? t.duelPower : s === "guard" ? t.duelCounter : 1000);
  return beats(sa, sb) ? [hit(sa), 0] : [0, hit(sb)];
}

/** The one of a story duel's challengers who fights bout `n` (1-based). */
function challengerOf(script: ScriptedDuel, n: number): string {
  const k = Math.min(script.a.length - 1, Math.floor(((n - 1) * script.a.length) / 5));
  return script.a[k] as string;
}

/**
 * Plays one bout and returns true when the duel is over. `stanceA` is the
 * challenger's; the answerer's comes from the PRNG by his temper. In a story
 * duel the blows are bent so the duel ends as history did by its last bout.
 */
export function playBout(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  duel: DuelState,
  stanceA: Stance,
  events: BattleEvent[],
  stanceBForced?: Stance,
): boolean {
  const t = rules.tuning;
  const script = duel.script ? (state.duels.find((d) => d.id === duel.script) ?? null) : null;
  const n = duel.bout + 1;
  const aId = script && script.a.length > 1 ? challengerOf(script, n) : duel.a;
  const a = unitById(state, aId);
  const b = unitById(state, duel.b);
  const last = n >= t.duelBouts;
  const outcome = script?.outcome ?? null;
  let stanceB = stanceBForced ?? stanceByTemper(b, prng.int(1000));
  // On a story duel's last bout, history's loser picks the stance that loses,
  // so the stances shown always explain the blow that ends it.
  if (last && !stanceBForced && outcome === "a" && isAlive(b)) stanceB = VICTIM[stanceA];
  if (last && !stanceBForced && outcome === "b" && isAlive(a)) stanceB = KILLER[stanceA];
  const [ma, mb] = exchange(rules, stanceA, stanceB);
  const spread = () => 900 + prng.int(201);
  let toB = ma > 0 ? permille(permille(duelBlow(rules, a, b), ma), spread()) : 0;
  let toA = mb > 0 ? permille(permille(duelBlow(rules, b, a), mb), spread()) : 0;
  if (outcome) {
    // The side history lets stand takes no fatal blow; history's loser falls on the last bout.
    if (outcome !== "b") toA = Math.min(toA, a.hp - 1);
    if (outcome !== "a") toB = Math.min(toB, b.hp - 1);
    if (outcome === "a" && last) toB = b.hp;
    if (outcome === "b" && last) toA = a.hp;
  }
  toA = Math.max(0, Math.min(toA, a.hp));
  toB = Math.max(0, Math.min(toB, b.hp));
  a.hp -= toA;
  b.hp -= toB;
  duel.bout = n;
  events.push({
    kind: "bout",
    a: a.id,
    b: b.id,
    n,
    stanceA,
    stanceB,
    damageA: toA,
    damageB: toB,
    hpA: a.hp,
    hpB: b.hp,
  });
  return !isAlive(a) || !isAlive(b) || last;
}

/** Spreads heart or dismay to the living units of `side`'s team within the radius of `around`. */
function morale(
  rules: Ruleset,
  state: BattleState,
  around: Unit,
  condition: string | undefined,
  turns: number,
  events: BattleEvent[],
): void {
  if (!condition || !rules.conditions[condition]) return;
  const r = rules.tuning.duelMoraleRadius;
  const hit = state.units.filter(
    (u) =>
      isAlive(u) &&
      !u.structure &&
      !hostile(state, u.side, around.side) &&
      Math.abs(u.x - around.x) + Math.abs(u.y - around.y) <= r,
  );
  for (const u of hit) addCondition(rules, u, condition, turns, u.side === state.active);
  if (hit.length > 0)
    events.push({ kind: "morale", condition, turns, units: hit.map((u) => u.id) });
}

/** A refused challenge shakes the refuser's side a little. */
export function refused(rules: Ruleset, state: BattleState, b: Unit, events: BattleEvent[]): void {
  morale(rules, state, b, rules.duelMorale?.lose, 1, events);
}

/**
 * Ends the duel under way: the loser leaves the field (dead for good in a
 * fatal story duel, else retreated), and hearts rise and fall. A draw or a
 * withdrawal leaves both standing.
 */
export function endDuel(
  rules: Ruleset,
  state: BattleState,
  duel: DuelState,
  withdrew: string | null,
  events: BattleEvent[],
): void {
  const script = duel.script ? (state.duels.find((d) => d.id === duel.script) ?? null) : null;
  const team = script ? script.a.map((id) => unitById(state, id)) : [unitById(state, duel.a)];
  const b = unitById(state, duel.b);
  const fallenA = team.find((u) => !isAlive(u)) ?? null;
  let winner: Unit | null = null;
  let loser: Unit | null = null;
  if (!isAlive(b) && !fallenA) {
    winner = team.find(isAlive) ?? null;
    loser = b;
  } else if (fallenA && isAlive(b)) {
    winner = b;
    loser = fallenA;
  }
  if (!withdrew && script?.outcome === "bWithdraws" && isAlive(b)) withdrew = b.id;
  const fatal = loser !== null && script?.fatal === true;
  if (loser) {
    loser.fate = fatal ? "killed" : "escaped";
    loser.conditions = [];
    loser.broken = false;
    events.push({ kind: "defeated", unit: loser.id, fate: loser.fate });
  }
  events.push({
    kind: "duelEnd",
    a: duel.a,
    b: duel.b,
    winner: winner?.id ?? null,
    loser: loser?.id ?? null,
    fatal,
    withdrew,
    script: script?.id ?? null,
  });
  // A foe who withdraws by history's account leaves the field without a fall.
  if (withdrew === b.id && script?.outcome === "bWithdraws") {
    b.hp = 0;
    b.fate = "escaped";
    b.conditions = [];
    events.push({ kind: "defeated", unit: b.id, fate: "escaped" });
  }
  if (winner && loser) {
    const turns = rules.tuning.duelMoraleTurns;
    morale(rules, state, winner, rules.duelMorale?.win, turns, events);
    morale(rules, state, loser, rules.duelMorale?.lose, turns, events);
  }
  if (script) state.duels = state.duels.filter((d) => d.id !== script.id);
  state.duel = null;
}

/** Every participant of a story duel is on the field and alive. */
function ready(state: BattleState, d: ScriptedDuel): boolean {
  const here = (id: string) => state.units.some((u) => u.id === id && isAlive(u));
  return here(d.b) && d.a.every(here);
}

/**
 * Fights the watched story duels whose moment has come, start to finish, with
 * stances from the PRNG. `arrived` lists units that joined just now.
 */
export function fireDuels(
  rules: Ruleset,
  state: BattleState,
  prng: Prng,
  events: BattleEvent[],
  arrived: readonly string[] = [],
): void {
  if (!duelsAllowed(rules) || state.duel || state.winner !== null) return;
  for (const d of [...state.duels]) {
    if (d.play || !ready(state, d)) continue;
    const w = d.when;
    const now =
      w.kind === "round"
        ? state.round >= w.round
        : w.kind === "arrived"
          ? arrived.includes(w.unit)
          : adjacent(unitById(state, d.a[0] as string), unitById(state, d.b));
    if (!now) continue;
    const duel: DuelState = { a: d.a[0] as string, b: d.b, bout: 0, script: d.id };
    state.duel = duel;
    events.push({ kind: "challenge", a: duel.a, b: duel.b, accepted: true, script: d.id });
    for (;;) {
      const s = STANCES[prng.int(3)] as Stance;
      if (playBout(rules, state, prng, duel, s, events)) break;
    }
    endDuel(rules, state, duel, null, events);
  }
}

/** The stance the AI picks for its challenger in a duel under way, without drawing from the PRNG. */
export function aiStance(state: BattleState): Stance {
  const duel = state.duel;
  if (!duel) return "attack";
  const a = unitById(state, duel.a);
  const roll = (state.prng[0] ^ (duel.bout * 2654435761)) >>> 0;
  return stanceByTemper(a, roll % 1000);
}
