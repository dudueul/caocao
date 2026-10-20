/**
 * The Story store (ADR 0049): the campaign state, which screen of the chapter
 * is showing, and the battle in progress. One instance lives for the whole
 * visit, so leaving to the title and coming back keeps the place.
 */
import {
  type BattleReport,
  buy,
  type CampaignState,
  checkSortie,
  continueTo,
  currentBattle,
  currentStep,
  defaultSortie,
  emptyLedger,
  equip,
  finishScene,
  type Gear,
  isFinished,
  type Ledger,
  leaveCamp,
  newCampaign,
  nextChapter,
  type Placement,
  promote,
  type Result,
  recordEvents,
  sell,
  settle,
  storyOrders,
  storySetup,
  unequip,
} from "@tk/campaign";
import {
  type CampDef,
  type ChapterDef,
  type ChapterStep,
  type Cmd,
  FIRST_CHAPTER,
  holds,
  type Line,
  type SceneDef,
  type StoryBattleDef,
  type TalkTrigger,
} from "@tk/content";
import type { BattleEvent, BattleState } from "@tk/rules";
import { type SlotId, writeSlot } from "./saves.ts";
import { Session } from "./session.svelte.ts";
import { settings } from "./settings.svelte.ts";

export type Phase = "scene" | "sortie" | "battle" | "result" | "camp" | "end";

/** Lines being shown over the battle, and what to do once they are read. */
export interface Talk {
  lines: readonly Line[];
  index: number;
  done: () => void;
}

function randomSeed(): string {
  return Math.floor(Math.random() * 0xffffffff).toString(36);
}

/** The lines a scene says with these flags, in order (the staged player shows the rest). */
export function sceneLines(scene: SceneDef, flags: Readonly<Record<string, string>>): Line[] {
  const out: Line[] = [];
  const visit = (cmds: readonly Cmd[]) => {
    for (const c of cmds) {
      if (!holds(c.if, flags)) continue;
      if (c.do === "all") visit(c.cmds);
      else if (c.do === "say" && holds(c.line.if, flags)) out.push(c.line);
    }
  };
  visit(scene.script);
  return out;
}

const phaseOf = (step: ChapterStep | null): Phase =>
  step === null ? "end" : step.kind === "battle" ? "sortie" : step.kind;

export class Story {
  campaign = $state.raw<CampaignState>(newCampaign(FIRST_CHAPTER));
  phase = $state<Phase>("scene");
  /** The line of the current scene being read. */
  line = $state(0);
  sortie = $state<Placement[]>([]);
  report = $state.raw<BattleReport | null>(null);
  session = $state.raw<Session | null>(null);
  talk = $state<Talk | null>(null);
  /** The slot this campaign was last saved to or loaded from. */
  slot = $state<SlotId | null>(null);
  /** Shown once per battle when hints are on. */
  tipsSeen = new Set<string>();

  private ledger: Ledger = emptyLedger();
  private played = new Set<string>();

  get step(): ChapterStep | null {
    return currentStep(this.campaign);
  }
  get scene(): SceneDef | null {
    const s = this.step;
    return s?.kind === "scene" ? s.scene : null;
  }
  get battle(): StoryBattleDef | null {
    return currentBattle(this.campaign);
  }
  get camp(): CampDef | null {
    const s = this.step;
    return s?.kind === "camp" ? s.camp : null;
  }

  /** A new campaign from the first scene. */
  start(chapterId: string = FIRST_CHAPTER): void {
    this.load(newCampaign(chapterId), null);
  }

  /** The chapter that follows the finished one, if any (ADR 0056). */
  get upcoming(): ChapterDef | null {
    return isFinished(this.campaign) ? nextChapter(this.campaign) : null;
  }

  /** Carries the finished campaign into the next chapter and saves it. */
  continueNext(): void {
    const next = this.upcoming;
    if (next && this.apply(continueTo(this.campaign, next.id))) {
      this.autosave();
      this.enterStep();
    }
  }

  /** Picks up a campaign (from a save) at its current step. */
  load(state: CampaignState, slot: SlotId | null): void {
    this.campaign = state;
    this.slot = slot;
    this.report = null;
    this.session = null;
    this.talk = null;
    this.enterStep();
  }

  private enterStep(): void {
    this.line = 0;
    this.phase = phaseOf(this.step);
    const b = this.battle;
    if (b) this.sortie = defaultSortie(this.campaign, b);
  }

  private apply(r: Result): boolean {
    if (!r.ok) return false;
    this.campaign = r.state;
    return true;
  }

  // ------------------------------------------------------------ scenes

  /** Next line, or the end of the scene. */
  next(): void {
    const sc = this.scene;
    if (!sc) return;
    if (this.line + 1 < sceneLines(sc, this.campaign.flags).length) this.line += 1;
    else this.endScene();
  }

  endScene(): void {
    if (this.apply(finishScene(this.campaign))) {
      this.autosave();
      this.enterStep();
    }
  }

  // ------------------------------------------------------------ camp

  buy(itemId: string): boolean {
    const camp = this.camp;
    return camp ? this.apply(buy(this.campaign, itemId, camp.shop)) : false;
  }
  sell(itemId: string): boolean {
    return this.apply(sell(this.campaign, itemId));
  }
  equip(officerId: string, itemId: string): boolean {
    return this.apply(equip(this.campaign, officerId, itemId));
  }
  unequip(officerId: string, slot: keyof Gear): boolean {
    return this.apply(unequip(this.campaign, officerId, slot));
  }
  promote(officerId: string): boolean {
    return this.apply(promote(this.campaign, officerId));
  }
  leaveCamp(): void {
    if (this.apply(leaveCamp(this.campaign))) {
      this.autosave();
      this.enterStep();
    }
  }

  // ------------------------------------------------------------ sortie and battle

  sortieError(): string | null {
    const b = this.battle;
    return b ? checkSortie(this.campaign, b, this.sortie) : "전투가 없습니다";
  }

  /** Goes out to battle with the chosen sortie. */
  launch(): void {
    const b = this.battle;
    if (!b || this.sortieError()) return;
    const session = new Session();
    session.speed = settings.speed;
    session.showDanger = settings.danger;
    this.ledger = emptyLedger();
    this.played = new Set();
    session.director = (events, before, after) => this.direct(b, events, before, after);
    session.startStory({
      setup: storySetup(this.campaign, b, this.sortie, randomSeed()),
      orders: storyOrders(b, this.campaign.flags),
      title: b.name,
      sides: [...b.sides],
      goalText: b.goalText,
      light: b.light ?? "day",
      marks: (b.marks ?? []).map((c) => ({ ...c })),
    });
    this.session = session;
    this.phase = "battle";
    void this.playTalk(b, { at: "start" });
  }

  /** Tries the same battle again, from the sortie. */
  retry(): void {
    this.session = null;
    this.talk = null;
    this.phase = "sortie";
  }

  /** Called by the battle screen once the result is in. */
  finishBattle(): void {
    const b = this.battle;
    const s = this.session;
    if (!b || !s || s.state.winner === null) return;
    const out = settle(this.campaign, b, this.sortie, s.state, this.ledger);
    this.report = out.report;
    if (out.report.won) {
      this.campaign = out.state;
      this.autosave();
    }
    this.session = null;
    this.phase = "result";
  }

  /** Leaves the result screen: on to the next step, or back to the sortie after a loss. */
  afterResult(): void {
    const won = this.report?.won ?? false;
    this.report = null;
    if (won) this.enterStep();
    else this.phase = "sortie";
  }

  // ------------------------------------------------------------ in-battle talk

  private matches(t: TalkTrigger, events: readonly BattleEvent[], humanSide: number): boolean {
    switch (t.at) {
      case "start":
        return false;
      case "round":
        return events.some((e) => e.kind === "turn" && e.side === humanSide && e.round === t.round);
      case "defeated":
        return events.some((e) => e.kind === "defeated" && e.unit === t.unit);
      case "arrived":
        return events.some((e) => e.kind === "arrived" && e.unit === t.unit);
      case "shifted":
        return events.some((e) => e.kind === "shifted" && e.shift === t.shift);
      case "end":
        return events.some((e) => e.kind === "battleEnd" && e.winner === t.winner);
    }
  }

  private async direct(
    b: StoryBattleDef,
    events: BattleEvent[],
    before: BattleState,
    after: BattleState,
  ): Promise<void> {
    recordEvents(this.ledger, before, after, events);
    const side = this.session?.humanSide ?? 0;
    for (const [i, t] of b.talk.entries()) {
      const key = `${i}`;
      if (!holds(t.if, this.campaign.flags)) continue;
      if (this.played.has(key) || !this.matches(t.when, events, side)) continue;
      await this.playTalk(b, t.when, i);
    }
  }

  /** Shows a talk's lines over the battle; resolves when the player has read them. */
  async playTalk(b: StoryBattleDef, when: TalkTrigger, index?: number): Promise<void> {
    const flags = this.campaign.flags;
    const i = index ?? b.talk.findIndex((t) => t.when.at === when.at && holds(t.if, flags));
    const t = b.talk[i];
    if (!t || this.played.has(`${i}`)) return;
    this.played.add(`${i}`);
    const lines = t.lines.filter((l) => holds(l.if, flags));
    if (lines.length === 0) return;
    const session = this.session;
    if (session) {
      session.busy = true;
      session.talking = true;
    }
    await new Promise<void>((resolve) => {
      this.talk = { lines, index: 0, done: resolve };
    });
    this.talk = null;
    if (session) session.talking = false;
    if (session && when.at === "start") session.busy = false;
  }

  /** Advances the talk shown over the battle. */
  nextTalk(): void {
    const t = this.talk;
    if (!t) return;
    if (t.index + 1 < t.lines.length) this.talk = { ...t, index: t.index + 1 };
    else t.done();
  }

  skipTalk(): void {
    this.talk?.done();
  }

  // ------------------------------------------------------------ saving

  save(slot: SlotId): boolean {
    const ok = writeSlot(slot, this.campaign);
    if (ok && slot !== "auto") this.slot = slot;
    return ok;
  }

  private autosave(): void {
    writeSlot("auto", this.campaign);
  }
}

/** The one Story of this visit. */
export const story = new Story();
