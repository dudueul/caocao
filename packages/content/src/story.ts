/**
 * Story content types (ADR 0049): a chapter is a list of steps — scenes,
 * battles and camps — played in order. Battles are scenarios whose player side
 * comes from the roster; scenes and in-battle talk are lines of dialogue.
 */
import type { Cell, Goal, WeatherKind } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import type { ScenarioUnit } from "./scenarios.ts";

/** A portrait's expression for one line. */
export type Mood = "calm" | "smile" | "angry" | "grim" | "hurt";

/** The painted backdrop behind a scene. */
export type Backdrop = "court" | "camp" | "river" | "night" | "field" | "fire" | "dawn";

export interface Line {
  /** Officer id of the speaker; omitted for narration. */
  readonly who?: string;
  /** A name to show instead of the officer's (a messenger, a letter). */
  readonly name?: string;
  /** Which army the speaker belongs to, for the portrait colours (default: the officer's usual side). */
  readonly side?: number;
  readonly mood?: Mood;
  readonly text: string;
}

/** An officer who joins the roster, with the class, level and gear they arrive with. */
export interface JoinDef {
  readonly officer: string;
  readonly classId: string;
  readonly level: number;
  /** Item ids, equipped on arrival. */
  readonly gear?: readonly string[];
  /** Guests fight for a while and then leave (관우 at 백마). */
  readonly guest?: boolean;
}

export interface SceneDef {
  readonly id: string;
  readonly title: string;
  readonly place: string;
  readonly when: string;
  readonly backdrop: Backdrop;
  readonly lines: readonly Line[];
  /** Roster changes once the scene is over. */
  readonly joins?: readonly JoinDef[];
  readonly leaves?: readonly string[];
}

/** When an in-battle talk plays. */
export type TalkTrigger =
  | { readonly at: "start" }
  /** At the start of the player's first turn of this round. */
  | { readonly at: "round"; readonly round: number }
  | { readonly at: "defeated"; readonly unit: string }
  | { readonly at: "arrived"; readonly unit: string }
  /** When the battle ends with this winner (0 = the player). */
  | { readonly at: "end"; readonly winner: number };

export interface BattleTalk {
  readonly when: TalkTrigger;
  readonly lines: readonly Line[];
}

export interface StoryBattleDef {
  readonly id: string;
  readonly name: string;
  readonly place: string;
  readonly summary: string;
  readonly map: MapDef;
  readonly weather: WeatherKind;
  readonly roundLimit: number;
  /** Side names; side 0 is the player. */
  readonly sides: readonly string[];
  /** Where roster officers may be placed, in order of preference. */
  readonly slots: readonly Cell[];
  /** Most roster officers that may sortie. */
  readonly max: number;
  /** Officers who must sortie. */
  readonly required: readonly string[];
  /** Side-0 units that come with the battle, not from the roster (they earn nothing). */
  readonly guests: readonly ScenarioUnit[];
  readonly enemies: readonly ScenarioUnit[];
  readonly goals: readonly Goal[];
  readonly goalText: { readonly win: string; readonly lose: string };
  readonly rewards: { readonly gold: number; readonly items: readonly string[] };
  readonly talk: readonly BattleTalk[];
  /** The tenser music for boss fights. */
  readonly music?: "battle" | "danger";
}

export interface CampDef {
  readonly id: string;
  /** Item ids the shop sells here. */
  readonly shop: readonly string[];
}

export type ChapterStep =
  | { readonly kind: "scene"; readonly scene: SceneDef }
  | { readonly kind: "battle"; readonly battle: StoryBattleDef }
  | { readonly kind: "camp"; readonly camp: CampDef };

export interface ChapterDef {
  readonly id: string;
  readonly name: string;
  readonly title: string;
  readonly years: string;
  readonly summary: string;
  /** The roster at the start of the chapter. */
  readonly roster: readonly JoinDef[];
  readonly gold: number;
  readonly steps: readonly ChapterStep[];
}
