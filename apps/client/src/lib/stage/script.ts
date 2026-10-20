/**
 * The scene player (ADR 0063): runs a scene's script on a stage. Steps run in
 * order; `all` runs its steps together and waits for them all. A step, line or
 * actor whose condition fails against the story flags (plus the picks made so
 * far in this scene) is passed over. Lines and choices go to the player
 * through `ScriptUi`; everything else goes to the stage. Skipping runs ahead
 * at once to the next choice (or the end), never past a choice.
 */
import type { CampaignState } from "@tk/campaign";
import {
  type Actor,
  type ChoiceOption,
  type Cmd,
  type Cond,
  type Facing4,
  holds,
  type Line,
  type SceneDef,
} from "@tk/content";
import { usualClass } from "../speakers.ts";
import type { ActorSpec, Stage } from "./stage.ts";

export type ChoiceCmd = Extract<Cmd, { do: "choice" }>;

/** What the script needs from the screen: show a line, ask for a choice. */
export interface ScriptUi {
  /** Resolves once the player has read the line. */
  say(line: Line): Promise<void>;
  /** Resolves with the value the player picked. */
  choose(choice: {
    flag: string;
    prompt: string;
    options: readonly ChoiceOption[];
  }): Promise<string>;
}

/** The figure an actor is drawn as: its own class, the officer's in the roster, else infantry. */
export function castSpecs(
  cast: readonly Actor[],
  flags: Readonly<Record<string, string>>,
  campaign: CampaignState | null,
): ActorSpec[] {
  return cast
    .filter((a) => holds(a.if, flags))
    .map((a) => {
      const officer = a.officer ?? a.id;
      const inRoster = campaign?.roster.find((o) => o.id === officer);
      return {
        id: a.id,
        officer,
        classId: a.classId ?? inRoster?.classId ?? usualClass(officer) ?? "infantry",
        side: a.side,
        at: a.at ?? null,
        facing: a.facing ?? "down",
      };
    });
}

/** Thrown inside the player when its scene is gone (the screen moved on). */
class Stopped extends Error {}

export class ScenePlayer {
  private picks: Record<string, string> = {};
  private stopped = false;
  private skipping = false;
  private readonly cast: Map<string, Actor>;
  private readonly says: Extract<Cmd, { do: "say" }>[] = [];
  /** The last speaker before the current one, for turning toward a listener. */
  private lastSpeaker: string | null = null;

  constructor(
    private readonly scene: SceneDef,
    private readonly flags: Readonly<Record<string, string>>,
    private readonly stage: Stage | null,
    private readonly ui: ScriptUi,
    /** False once the screen has moved on from this scene. */
    private readonly alive: () => boolean = () => true,
  ) {
    this.cast = new Map(scene.cast.map((a) => [a.id, a]));
    const collect = (cmds: readonly Cmd[]) => {
      for (const c of cmds) {
        if (c.do === "say") this.says.push(c);
        else if (c.do === "all") collect(c.cmds);
      }
    };
    collect(scene.script);
  }

  /** Plays the script through; resolves true at its end, false if it was stopped. */
  async run(): Promise<boolean> {
    try {
      await this.steps(this.scene.script);
      return !this.stopped;
    } catch (e) {
      if (e instanceof Stopped) return false;
      throw e;
    } finally {
      // A stopped player leaves the stage alone: the next scene may own it already.
      if (this.stage && !this.stopped) {
        this.stage.speaking(null);
        this.stage.fast = false;
      }
    }
  }

  /** Runs ahead to the next choice, or to the end. */
  skip(): void {
    this.skipping = true;
    if (this.stage) this.stage.fast = true;
  }

  get isSkipping(): boolean {
    return this.skipping;
  }

  /** Abandons the scene (the screen moved on): nothing more is shown or asked. */
  stop(): void {
    this.stopped = true;
    if (this.stage) this.stage.fast = true;
  }

  private now(): Record<string, string> {
    return { ...this.flags, ...this.picks };
  }

  private ok(cond: Cond | undefined): boolean {
    return holds(cond, this.now());
  }

  /** An actor the script may use now: cast, and their condition holds. */
  private here(id: string): boolean {
    const a = this.cast.get(id);
    return a !== undefined && this.ok(a.if) && (this.stage?.has(id) ?? true);
  }

  private check(): void {
    if (this.stopped || !this.alive()) {
      this.stopped = true;
      throw new Stopped();
    }
  }

  private async steps(cmds: readonly Cmd[]): Promise<void> {
    for (const c of cmds) {
      this.check();
      if (!this.ok(c.if)) continue;
      await this.exec(c);
      this.check();
    }
  }

  private async exec(c: Cmd): Promise<void> {
    const s = this.stage;
    switch (c.do) {
      case "all":
        await Promise.all(c.cmds.filter((d) => this.ok(d.if)).map((d) => this.exec(d)));
        return;
      case "say":
        return this.say(c);
      case "choice": {
        // Skipping stops here: a choice is always the player's.
        this.skipping = false;
        if (s) s.fast = false;
        s?.speaking(null);
        const value = await this.ui.choose({ flag: c.flag, prompt: c.prompt, options: c.options });
        this.check();
        this.picks[c.flag] = value;
        return;
      }
      default:
        break;
    }
    if (!s) return;
    switch (c.do) {
      case "walk":
        if (this.here(c.who)) await s.walk(c.who, c.to, c.run);
        return;
      case "enter":
        if (this.here(c.who)) await s.enter(c.who, c.from, c.to, c.run);
        return;
      case "leave":
        if (this.here(c.who)) await s.leave(c.who, c.to, c.run);
        return;
      case "place":
        if (this.here(c.who)) s.place(c.who, c.at, c.facing);
        return;
      case "remove":
        if (this.here(c.who)) await s.remove(c.who);
        return;
      case "face":
        if (this.here(c.who)) s.face(c.who, c.to);
        return;
      case "emote":
        if (this.here(c.who)) await s.emote(c.who, c.emote);
        return;
      case "act":
        if (this.here(c.who)) await s.act(c.who, c.pose);
        return;
      case "strike":
        if (this.here(c.who) && this.here(c.target)) await s.strike(c.who, c.target, c.fells);
        return;
      case "camera":
        if (typeof c.at === "string" && !this.here(c.at)) return;
        await s.camera(c.at, c.zoom);
        return;
      case "wait":
        await s.wait(c.ms);
        return;
      case "sound":
        s.sound(c.sfx);
        return;
      case "fx":
        if (typeof c.at === "string" && !this.here(c.at)) return;
        await s.effect(c.fx, c.at);
        return;
      case "fire":
        s.fire(c.cells);
        return;
      case "tiles":
        await s.retile(c.tiles);
        return;
      case "light":
        await s.light(c.light);
        return;
    }
  }

  private async say(c: Extract<Cmd, { do: "say" }>): Promise<void> {
    const line = c.line;
    if (!this.ok(line.if)) return;
    // The figure who speaks: the line's actor (an extra speaking for a troop), else `who`.
    const who = line.actor ?? line.who ?? null;
    if (this.skipping) {
      if (who) this.lastSpeaker = who;
      return;
    }
    const s = this.stage;
    const onStage = who !== null && s !== null && this.here(who) && s.isOn(who);
    if (s && onStage && who) {
      // Turn toward whoever they are talking to, unless the script has just turned them.
      const free = s.untouched(who);
      const listener = this.listenerOf(c, who);
      if (free && listener && !s.isMoving(who)) this.turnToward(who, listener);
      s.speaking(who, listener);
    } else s?.speaking(null);
    await this.ui.say(line);
    this.check();
    s?.speaking(null);
    if (who) this.lastSpeaker = who;
  }

  /** The other party: the next one to speak, else the last one who spoke, if on stage nearby. */
  private listenerOf(c: Extract<Cmd, { do: "say" }>, who: string): string | null {
    const s = this.stage;
    if (!s) return null;
    const near = (id: string | null | undefined): id is string => {
      if (!id || id === who || !this.here(id) || !s.isOn(id)) return false;
      const a = s.cellOf(who);
      const b = s.cellOf(id);
      return a !== null && b !== null && Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= 6;
    };
    const i = this.says.indexOf(c);
    for (let j = i + 1; j < this.says.length; j++) {
      const d = this.says[j];
      if (!d || !this.ok(d.if) || !this.ok(d.line.if)) continue;
      const next = d.line.actor ?? d.line.who;
      if (!next || next === who) continue;
      if (near(next)) return next;
      break;
    }
    return near(this.lastSpeaker) ? this.lastSpeaker : null;
  }

  /** Faces the listener when they are clearly off to one side, or straight ahead or behind. */
  private turnToward(who: string, listener: string): void {
    const s = this.stage;
    if (!s) return;
    const a = s.cellOf(who);
    const b = s.cellOf(listener);
    const f: Facing4 | null = s.facingOf(who);
    if (!a || !b || !f) return;
    const dx = b.x - a.x;
    const dy = b.y - a.y;
    const ahead =
      (f === "left" && dx < 0) ||
      (f === "right" && dx > 0) ||
      (f === "up" && dy < 0) ||
      (f === "down" && dy > 0);
    // Already looking their way (even at a slant): leave the staging alone.
    if (ahead) return;
    s.face(who, listener, false);
  }
}
