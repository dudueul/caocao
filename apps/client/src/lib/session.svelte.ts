/**
 * The battle store (ADR 0031): one object holds the rules state, the player's
 * pending choice and the UI settings. Svelte panels read it; the Pixi field
 * draws it and animates the events of every applied command.
 *
 * Every option the UI offers comes from `legalCommands()`, so the client can't
 * build an illegal command. A move stays pending (and can be changed or undone
 * freely) until an action is confirmed (ADR 0028).
 */

import {
  type Landscape,
  P0_RULES,
  SCENARIOS,
  type ScenarioDef,
  scenarioOrders,
  scenarioSetup,
} from "@tk/content";
import {
  type AttackPreview,
  acceptChance,
  apply,
  type BattleEvent,
  type BattleSetup,
  type BattleState,
  type Cell,
  type Command,
  chooseCommand,
  classOf,
  createBattle,
  dangerTiles,
  duelBlow,
  forMode,
  friendly,
  type Gambit,
  legalCommands,
  previewAttack,
  previewStratagem,
  type Ruleset,
  reachable,
  type Stance,
  type StratagemPreview,
  scriptedFor,
  stratagemDef,
  type TurnPolicyId,
  type Unit,
  type WeatherKind,
} from "@tk/rules";
import { audio } from "./audio/index.ts";
import { CLASSIC_BATTLES, onClassicMap } from "./classic-battle.ts";
import { DuelShow, duelist } from "./duel.svelte.ts";
import type { ClassicMapGrid } from "./skin.svelte.ts";
import { describe, type LogLine } from "./text.ts";

type Act = Extract<Command, { kind: "act" }>;
export type Aim = { kind: "attack" } | { kind: "stratagem"; id: string } | { kind: "duel" };
export type Speed = 0 | 1 | 2 | 4;

export interface BattleOptions {
  scenario: string;
  seed: string;
  policy: TurnPolicyId;
  weather: WeatherKind;
  /** A 1998 battle map to fight on (local classic skin only, ADR 0042). */
  classicMap: number | null;
}

export type Forecast =
  | { kind: "attack"; attacker: Unit; defender: Unit; preview: AttackPreview }
  | {
      kind: "stratagem";
      caster: Unit;
      id: string;
      at: Cell;
      area: Cell[];
      preview: StratagemPreview;
    }
  | {
      /** A 일기토 challenge (ADR 0071): the odds he takes it up, and a clean blow each way. */
      kind: "duel";
      a: Unit;
      b: Unit;
      /** ‰ that the foe accepts; 1000 for a story duel. */
      accept: number;
      blowA: number;
      blowB: number;
      story: boolean;
    };

/** The duel events the field hands to the duel screen. */
export type DuelEvent = Extract<BattleEvent, { kind: "challenge" | "bout" | "duelEnd" }>;

/** Animates the events of one command; resolves when the field shows `after`. */
export type Player = (
  events: BattleEvent[],
  before: BattleState,
  after: BattleState,
) => Promise<void>;

/** A skill cut-in: the caster's banner across the screen while a stratagem goes off. */
export interface CutIn {
  unit: string;
  name: string;
  skill: string;
  color: number;
  side: number;
  mine: boolean;
}

/** Where the field draws the map, in CSS pixels of the battle screen. */
export interface Geometry {
  tile: number;
  ox: number;
  oy: number;
}

export interface Insets {
  top: number;
  right: number;
  bottom: number;
  left: number;
}

const key = (c: Cell, w: number) => c.y * w + c.x;
const same = (a: Cell | null, b: Cell | null) =>
  a !== null && b !== null && a.x === b.x && a.y === b.y;

function randomSeed(): string {
  return Math.floor(Math.random() * 0xffffffff).toString(36);
}

/** Where commands go in online play: the referee decides, the session only shows. */
export interface Remote {
  send(command: Command): void;
}

/** A story battle (ADR 0049): its own setup and AI orders instead of a sandbox scenario. */
export interface StoryBattle {
  setup: BattleSetup;
  orders: Record<string, readonly Gambit[]>;
  title: string;
  sides: string[];
  goalText: { win: string; lose: string };
  /** How the field is lit (night battles are darker). */
  light?: "day" | "dusk" | "night";
  /** Where and when it was fought, for the terrain painter (ADR 0075); looks only. */
  landscape?: Landscape;
  /** Tiles to mark ★ for the whole battle. */
  marks?: Cell[];
}

/** Runs after each command is shown, before input resumes (story talk, experience). */
export type Director = (
  events: BattleEvent[],
  before: BattleState,
  after: BattleState,
) => Promise<void>;

export class Session {
  readonly rules: Ruleset = P0_RULES;
  /** The side this player commands (the seat, online). */
  humanSide = $state(0);
  /** Online: commands go to the MatchDO instead of the local rules (ADR 0004). */
  remote: Remote | null = null;
  /** Loaded 1998 map grids, by map id; `create` reads them synchronously. */
  readonly classicGrids = new Map<number, ClassicMapGrid>();
  /** The 1998 map picture under this battle, if it is fought on one. */
  backdrop = $state<number | null>(null);
  /** How the field is lit: story night battles are darker (looks only). */
  light = $state<"day" | "dusk" | "night">("day");
  /** Set for Story battles; the sandbox scenario is ignored then. */
  story: StoryBattle | null = null;
  director: Director | null = null;
  /** Story lines are being read over the field (the battle waits). */
  talking = $state(false);
  /** The 일기토 on the duel screen, if one is shown (ADR 0071). */
  duel = $state.raw<DuelShow | null>(null);
  /**
   * Story talk for the events shown so far, in the middle of a command: before
   * a duel's first bout and after its end, so talk and duel come in order.
   */
  interlude: ((events: BattleEvent[]) => Promise<void>) | null = null;

  options = $state<BattleOptions>({
    scenario: "wuchao-raid",
    seed: randomSeed(),
    policy: "sidePhases",
    weather: "sunny",
    classicMap: null,
  });
  state = $state.raw<BattleState>(this.create());
  /** Start state and command log of this battle: a replay (ADR 0004). */
  start: BattleState = this.state;
  commands: Command[] = [];

  selected = $state<string | null>(null);
  to = $state<Cell | null>(null);
  aim = $state<Aim | null>(null);
  target = $state<Cell | null>(null);
  hover = $state<Cell | null>(null);

  showDanger = $state(false);
  speed = $state<Speed>(1);
  auto = $state(false);
  /** Demo: the AI plays both sides, and a new battle starts after each one. */
  demo = $state(false);
  busy = $state(false);
  log = $state<LogLine[]>([]);
  banner = $state<{ text: string; tone: "turn" | "twist" | "end" } | null>(null);
  insets = $state<Insets>({ top: 0, right: 0, bottom: 0, left: 0 });
  geometry = $state<Geometry>({ tile: 48, ox: 0, oy: 0 });
  cutin = $state<CutIn | null>(null);
  /** The cut-in's progress, 0 to 1, driven by the field's clock. */
  cutinAt = $state(0);
  /** Side names for the log and the top bar (scenario sides, or player names online). */
  sideNames = $state<string[]>(SCENARIOS["wuchao-raid"]?.sides.slice() ?? []);

  player: Player | null = null;
  private logId = 0;
  private pumping = false;
  private bannerTimer: ReturnType<typeof setTimeout> | undefined;

  get scenario(): ScenarioDef {
    const s = SCENARIOS[this.options.scenario];
    if (!s) throw new Error(`unknown scenario ${this.options.scenario}`);
    return s;
  }

  /** The battle's name for the top bar. */
  get title(): string {
    return this.story?.title ?? this.scenario.name;
  }

  private create(): BattleState {
    if (this.story) {
      this.backdrop = null;
      return createBattle(this.rules, { ...this.story.setup, seed: this.options.seed });
    }
    const o = this.options;
    const s = SCENARIOS[o.scenario];
    if (!s) throw new Error(`unknown scenario ${o.scenario}`);
    const setup = scenarioSetup(s, { seed: o.seed, policy: o.policy, weather: o.weather });
    const battle = CLASSIC_BATTLES.find((b) => b.map === o.classicMap);
    const grid = battle && this.classicGrids.get(battle.map);
    this.backdrop = battle && grid ? battle.map : null;
    return createBattle(
      this.rules,
      battle && grid ? onClassicMap(setup, grid, battle, this.rules) : setup,
    );
  }

  // ------------------------------------------------------------ derived view

  /** True while the human may give commands. */
  myTurn = $derived(
    !this.busy && !this.auto && this.state.winner === null && this.state.active === this.humanSide,
  );
  legal = $derived<Command[]>(this.myTurn ? legalCommands(this.state, this.rules) : []);
  unit = $derived<Unit | null>(this.state.units.find((u) => u.id === this.selected) ?? null);
  /** The selected unit's legal commands (empty if it can't act now). */
  unitCommands = $derived<Act[]>(
    this.legal.filter((c): c is Act => c.kind === "act" && c.unit === this.selected),
  );
  reach = $derived<Cell[]>(uniqueCells(this.unitCommands.map((c) => c.to)));
  /** Enemy tiles the selected unit could attack from somewhere it can reach. */
  attackable = $derived.by<Cell[]>(() => {
    const u = this.unit;
    if (!u || this.unitCommands.length === 0) return [];
    const w = this.state.map.width;
    const reachKeys = new Set(this.reach.map((c) => key(c, w)));
    const out = new Map<number, Cell>();
    for (const r of this.reach)
      for (const o of classOf(this.rules, u).range) {
        const c = { x: r.x + o.dx, y: r.y + o.dy };
        if (c.x < 0 || c.y < 0 || c.x >= w || c.y >= this.state.map.height) continue;
        if (!reachKeys.has(key(c, w))) out.set(key(c, w), c);
      }
    return [...out.values()];
  });
  /** For a unit that can't act now (an enemy, or one that already acted): where it could go and strike. */
  inspect = $derived.by<{ reach: Cell[]; strike: Cell[] }>(() => {
    const u = this.unit;
    if (!u || u.hp <= 0 || this.unitCommands.length > 0 || this.busy || this.state.winner !== null)
      return { reach: [], strike: [] };
    const w = this.state.map.width;
    const reach = reachable(this.state, this.rules, u).map((r) => ({ x: r.x, y: r.y }));
    const reachKeys = new Set(reach.map((c) => key(c, w)));
    const strike = new Map<number, Cell>();
    for (const r of reach)
      for (const o of classOf(this.rules, u).range) {
        const c = { x: r.x + o.dx, y: r.y + o.dy };
        if (c.x < 0 || c.y < 0 || c.x >= w || c.y >= this.state.map.height) continue;
        if (!reachKeys.has(key(c, w))) strike.set(key(c, w), c);
      }
    return { reach, strike: [...strike.values()] };
  });
  /** Commands available from the pending tile. */
  here = $derived<Act[]>(this.to ? this.unitCommands.filter((c) => same(c.to, this.to)) : []);
  attackCells = $derived<Cell[]>(
    this.here.flatMap((c) => {
      const a = c.action;
      if (a.kind !== "attack") return [];
      const t = this.state.units.find((u) => u.id === a.target);
      return t ? [{ x: t.x, y: t.y }] : [];
    }),
  );
  stratagemOptions = $derived.by<{ id: string; name: string; mp: number; cells: Cell[] }[]>(() => {
    const u = this.unit;
    if (!u || !this.to) return [];
    return classOf(this.rules, u).stratagems.map((id) => {
      const def = stratagemDef(this.rules, id);
      const cells = this.here.flatMap((c) =>
        c.action.kind === "stratagem" && c.action.id === id ? [c.action.at] : [],
      );
      return { id, name: def.name, mp: def.mp, cells };
    });
  });
  /** Officers the selected unit may challenge to a 일기토 from the pending tile. */
  duelCells = $derived<Cell[]>(
    this.here.flatMap((c) => {
      const a = c.action;
      if (a.kind !== "duel") return [];
      const t = this.state.units.find((u) => u.id === a.target);
      return t ? [{ x: t.x, y: t.y }] : [];
    }),
  );
  /** The foes among them whom history has the selected unit fight (★ 역사의 일기토). */
  storyDuels = $derived<string[]>(
    this.here.flatMap((c) =>
      c.action.kind === "duel" && scriptedFor(this.state, c.unit, c.action.target)
        ? [c.action.target]
        : [],
    ),
  );
  /** Cells the current aim may target. */
  aimCells = $derived<Cell[]>(
    this.aim === null
      ? []
      : this.aim.kind === "attack"
        ? this.attackCells
        : this.aim.kind === "duel"
          ? this.duelCells
          : (this.stratagemOptions.find(
              (s) => this.aim?.kind === "stratagem" && s.id === this.aim.id,
            )?.cells ?? []),
  );
  danger = $derived<Cell[]>(
    this.showDanger ? dangerTiles(this.state, this.rules, this.humanSide) : [],
  );
  forecast = $derived.by<Forecast | null>(() => {
    const u = this.unit;
    const to = this.to;
    const aim = this.aim;
    const at =
      this.target ??
      (this.hover && this.aimCells.some((c) => same(c, this.hover)) ? this.hover : null);
    if (!u || !to || !aim || !at) return null;
    const moved: Unit = { ...u, x: to.x, y: to.y };
    const ghost: BattleState = {
      ...this.state,
      units: this.state.units.map((v) => (v.id === u.id ? moved : v)),
    };
    if (aim.kind === "attack") {
      const d = ghost.units.find((v) => v.hp > 0 && v.x === at.x && v.y === at.y);
      if (!d) return null;
      return {
        kind: "attack",
        attacker: moved,
        defender: d,
        preview: previewAttack(this.rules, ghost, moved, d),
      };
    }
    if (aim.kind === "duel") {
      const d = ghost.units.find((v) => v.hp > 0 && v.x === at.x && v.y === at.y);
      if (!d) return null;
      const rules = forMode(this.rules, this.state.mode);
      const story = scriptedFor(this.state, u.id, d.id) !== null;
      return {
        kind: "duel",
        a: moved,
        b: d,
        accept: story ? 1000 : acceptChance(rules, moved, d),
        blowA: duelBlow(rules, moved, d),
        blowB: duelBlow(rules, d, moved),
        story,
      };
    }
    const def = stratagemDef(this.rules, aim.id);
    return {
      kind: "stratagem",
      caster: moved,
      id: aim.id,
      at,
      area: def.area.map((o) => ({ x: at.x + o.dx, y: at.y + o.dy })),
      preview: previewStratagem(this.rules, ghost, moved, def, at),
    };
  });

  // ------------------------------------------------------------ input

  unitAt(c: Cell): Unit | undefined {
    return this.state.units.find((u) => u.hp > 0 && u.x === c.x && u.y === c.y);
  }

  private inCells(cells: readonly Cell[], c: Cell): boolean {
    return cells.some((d) => same(d, c));
  }

  /** A tap or click on a tile. */
  click(c: Cell): void {
    if (this.busy) return;
    const clicked = this.unitAt(c);
    if (this.aim) {
      if (this.inCells(this.aimCells, c)) {
        if (same(this.target, c)) void this.confirm();
        else this.target = c;
      } else {
        this.aim = null;
        this.target = null;
      }
      return;
    }
    if (this.to) {
      if (clicked && clicked.side !== this.humanSide && this.inCells(this.attackCells, c)) {
        this.aim = { kind: "attack" };
        this.target = c;
      } else if (clicked && this.inCells(this.duelCells, c)) {
        this.aim = { kind: "duel" };
        this.target = c;
      } else if (this.inCells(this.reach, c) && !same(this.to, c)) this.to = c;
      else this.undoMove();
      return;
    }
    if (this.unitCommands.length > 0 && this.inCells(this.reach, c)) {
      this.to = c;
      return;
    }
    this.selected = clicked ? clicked.id : null;
  }

  chooseAttack(): void {
    this.aim = { kind: "attack" };
    this.target = this.attackCells.length === 1 ? (this.attackCells[0] ?? null) : null;
  }

  /** 일기토: pick the officer to challenge (straight to the forecast if there is one). */
  chooseDuel(): void {
    this.aim = { kind: "duel" };
    this.target = this.duelCells.length === 1 ? (this.duelCells[0] ?? null) : null;
  }

  chooseStratagem(id: string): void {
    this.aim = { kind: "stratagem", id };
    const cells = this.stratagemOptions.find((s) => s.id === id)?.cells ?? [];
    this.target = cells.length === 1 ? (cells[0] ?? null) : null;
  }

  undoMove(): void {
    this.to = null;
    this.aim = null;
    this.target = null;
  }

  /** Esc: step back one choice. */
  back(): void {
    if (this.target) this.target = null;
    else if (this.aim) this.aim = null;
    else if (this.to) this.to = null;
    else this.selected = null;
  }

  async wait(): Promise<void> {
    const cmd = this.here.find((c) => c.action.kind === "wait");
    if (cmd) await this.commit(cmd);
  }

  async confirm(): Promise<void> {
    const at = this.target;
    const aim = this.aim;
    if (!at || !aim) return;
    const cmd = this.here.find((c) => {
      const a = c.action;
      if (aim.kind === "attack" && a.kind === "attack") {
        const t = this.unitAt(at);
        return t !== undefined && a.target === t.id;
      }
      if (aim.kind === "duel" && a.kind === "duel") {
        const t = this.unitAt(at);
        return t !== undefined && a.target === t.id;
      }
      return (
        aim.kind === "stratagem" && a.kind === "stratagem" && a.id === aim.id && same(a.at, at)
      );
    });
    if (cmd) await this.commit(cmd);
  }

  /** The stance for the next bout of the 일기토 under way. */
  async bout(stance: Stance): Promise<void> {
    const cmd = this.legal.find((c) => c.kind === "bout" && c.stance === stance);
    if (!cmd || this.busy) return;
    const show = this.duel;
    show?.chosen(stance);
    await this.commit(cmd);
    // A refused command leaves the pick open again.
    if (show && show.phase === "pick" && !this.busy) show.picked = null;
  }

  /** Pulls out of the 일기토 under way (not a story duel). */
  async withdraw(): Promise<void> {
    const cmd = this.legal.find((c) => c.kind === "withdraw");
    if (cmd && !this.busy) await this.commit(cmd);
  }

  async endPhase(): Promise<void> {
    if (this.legal.some((c) => c.kind === "endPhase")) await this.commit({ kind: "endPhase" });
  }

  startDemo(): void {
    this.demo = true;
    this.speed = 2;
    this.setAuto(true);
  }

  setAuto(on: boolean): void {
    this.auto = on;
    if (!on) this.demo = false;
    if (on) {
      this.clearChoice();
      void this.pump();
    }
  }

  newBattle(o: Partial<BattleOptions> = {}): void {
    if (this.busy) return;
    this.options = { ...this.options, seed: randomSeed(), ...o };
    this.state = this.create();
    this.start = this.state;
    this.commands = [];
    this.log = [];
    this.duel = null;
    this.clearChoice();
    this.selected = null;
    this.flash(`${this.title} · ${this.sideNames[this.humanSide]}`, "turn");
    void this.pump();
  }

  /** Starts a Story battle; `newBattle()` afterwards retries it with a new seed. */
  startStory(b: StoryBattle): void {
    this.story = b;
    this.light = b.light ?? "day";
    this.sideNames = [...b.sides];
    this.newBattle({ seed: b.setup.seed });
  }

  // ------------------------------------------------------------ applying commands

  private clearChoice(): void {
    this.to = null;
    this.aim = null;
    this.target = null;
  }

  private async commit(cmd: Command): Promise<void> {
    if (this.busy) return;
    if (this.remote) {
      // Online: wait for the referee's batch (or rejection) before taking input again.
      this.busy = true;
      this.clearChoice();
      this.remote.send(cmd);
      return;
    }
    await this.step(cmd);
    await this.pump();
  }

  // ------------------------------------------------------------ online (ADR 0037)

  private shown: Promise<void> = Promise.resolve();

  /** A batch from the referee: animate its events, then show its state. Batches play in order. */
  receive(events: BattleEvent[], after: BattleState): void {
    this.shown = this.shown.then(() => this.show(events, after));
  }

  /** A full state from the referee (battle start, or a reconnect gap). */
  loadSnapshot(state: BattleState): void {
    this.shown = this.shown.then(() => {
      this.state = state;
      this.start = state;
      this.commands = [];
      this.duel = null;
      this.clearChoice();
      this.syncDuel();
      this.busy = false;
    });
  }

  /** The referee refused our command. */
  rejected(reason: string): void {
    this.addLog({ text: `거부됨: ${reason}`, tone: "bad" });
    this.busy = false;
  }

  /** Applies one command, animates it, then shows the new state. */
  private async step(cmd: Command): Promise<boolean> {
    this.busy = true;
    this.clearChoice();
    const r = apply(this.state, this.rules, cmd);
    if (!r.ok) {
      this.addLog({ text: `거부됨: ${r.reason}`, tone: "bad" });
      this.busy = false;
      return false;
    }
    this.commands.push(cmd);
    await this.show(r.events, r.state);
    return true;
  }

  /** Animates events from the current state, then shows `after`, logs and flashes. */
  private async show(events: BattleEvent[], after: BattleState): Promise<void> {
    this.busy = true;
    const before = this.state;
    const r = { events, state: after };
    if (this.player) await this.player(r.events, before, r.state);
    this.state = r.state;
    for (const e of r.events) {
      // Alternating activations hand the turn over every command: mark only new rounds.
      const quietTurn =
        e.kind === "turn" && after.policy === "alternating" && e.round === before.round;
      const line = quietTurn
        ? null
        : describe(e, r.state, this.rules, this.sideNames, this.humanSide);
      if (line) this.addLog(line);
      if (e.kind === "twist") this.flash(e.outcome === "windfall" ? "천운!" : "불운!", "twist");
      if (e.kind === "turn" && !quietTurn && e.side === this.humanSide && !this.auto) {
        this.flash(`라운드 ${e.round} · 아군 차례`, "turn");
        audio.play("turn");
      } else if (
        e.kind === "turn" &&
        !quietTurn &&
        e.side !== this.humanSide &&
        friendly(r.state, e.side, this.humanSide)
      ) {
        // Allies move by their own AI in their phase after ours (ADR 0066).
        this.flash(`우군 차례 · ${this.sideNames[e.side] ?? ""}`, "turn");
      }
      if (e.kind === "battleEnd") {
        audio.play(e.winner === this.humanSide ? "victory" : "loss");
        this.flash(e.winner < 0 ? "무승부" : e.winner === this.humanSide ? "승리" : "패배", "end");
        if (this.demo)
          setTimeout(() => {
            if (this.demo) {
              this.newBattle();
              this.setAuto(true);
            }
          }, 4000);
      }
    }
    if (this.selected && !this.state.units.some((u) => u.id === this.selected && u.hp > 0))
      this.selected = null;
    this.syncDuel();
    if (this.director) await this.director(r.events, before, r.state);
    this.busy = false;
  }

  // ------------------------------------------------------------ the duel screen (ADR 0071)

  /** The field reached a duel's moment: shows it on the duel screen; resolves once shown. */
  async stageDuel(
    e: DuelEvent,
    before: BattleState,
    after: BattleState,
    sofar: BattleEvent[],
  ): Promise<void> {
    if (e.kind === "challenge") {
      if (!e.accepted) return;
      // Story talk before the first bout (the challenge shouted across the field).
      await this.interlude?.(sofar);
      // A watched duel is fought out within this one command.
      const watched = after.duel === null;
      if (watched && this.speed === 0) return;
      try {
        this.duel = this.showFor([before, after], e.a, e.b, e.script, watched);
      } catch (err) {
        // The battle goes on without the duel screen rather than stall.
        console.error(err);
        this.duel = null;
        return;
      }
      await this.duel.open();
      return;
    }
    const show = this.duel;
    if (!show) return;
    if (e.kind === "bout") await show.bout(e);
    else {
      await show.end(e);
      if (this.duel === show) this.duel = null;
    }
  }

  /** Story talk after a duel ends: the field calls it once the loser has left the field. */
  async afterDuel(sofar: BattleEvent[]): Promise<void> {
    await this.interlude?.(sofar);
  }

  /**
   * The duel screen for a duel between `a` and `b`, with the fighters as they
   * stand in the first of `states` that has them (one who arrived this very
   * command is still in the reserve before it).
   */
  private showFor(
    states: readonly BattleState[],
    a: string,
    b: string,
    script: string | null,
    watched: boolean,
  ): DuelShow {
    const state = states[0] ?? this.state;
    const find = (id: string) => {
      for (const s of states) {
        const u = s.units.find((v) => v.id === id) ?? s.reserve.find((r) => r.unit.id === id)?.unit;
        if (u) return u;
      }
      return null;
    };
    const unit = (id: string) => {
      const u = find(id);
      if (!u) throw new Error(`no unit ${id} for the duel`);
      return duelist(u);
    };
    const story = script ? state.duels.find((d) => d.id === script) : undefined;
    return new DuelShow({
      a: unit(a),
      b: unit(b),
      cast: (story?.a ?? [a]).filter((id) => find(id) !== null).map(unit),
      story: script !== null,
      watched,
      bouts: forMode(this.rules, state.mode).tuning.duelBouts,
      friendly: (side) => friendly(state, side, this.humanSide),
      pace: () => (this.speed === 0 ? 0 : 1 / Math.min(2, this.speed)),
    });
  }

  /** A duel under way always has its screen (after a load or a reconnect too). */
  private syncDuel(): void {
    const d = this.state.duel;
    if (d && (!this.duel || this.duel.watched)) {
      const show = this.showFor([this.state], d.a, d.b, d.script, false);
      show.n = d.bout;
      show.phase = "pick";
      this.duel = show;
    } else if (!d && this.duel && !this.duel.watched) this.duel = null;
  }

  /** Lets the AI play every activation that isn't the human's. */
  private async pump(): Promise<void> {
    if (this.pumping || this.remote) return;
    this.pumping = true;
    try {
      while (this.state.winner === null && (this.auto || this.state.active !== this.humanSide)) {
        await nextFrame();
        const orders = this.story ? this.story.orders : scenarioOrders(this.scenario);
        const cmd = chooseCommand(this.state, this.rules, orders);
        if (!cmd || !(await this.step(cmd))) break;
      }
    } finally {
      this.pumping = false;
    }
  }

  private addLog(line: Omit<LogLine, "id">): void {
    this.log = [...this.log.slice(-199), { id: this.logId++, ...line }];
  }

  private flash(text: string, tone: "turn" | "twist" | "end"): void {
    this.banner = { text, tone };
    clearTimeout(this.bannerTimer);
    if (tone !== "end") this.bannerTimer = setTimeout(() => (this.banner = null), 1400);
  }
}

function uniqueCells(cells: readonly Cell[]): Cell[] {
  const seen = new Set<string>();
  const out: Cell[] = [];
  for (const c of cells) {
    const k = `${c.x},${c.y}`;
    if (!seen.has(k)) {
      seen.add(k);
      out.push(c);
    }
  }
  return out;
}

function nextFrame(): Promise<void> {
  return new Promise((resolve) => requestAnimationFrame(() => resolve()));
}
