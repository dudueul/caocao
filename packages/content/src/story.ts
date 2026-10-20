/**
 * Story content types (ADR 0049): a chapter is a list of steps — scenes,
 * battles and camps — played in order. Battles are scenarios whose player side
 * comes from the roster. Scenes are acted out on small tile maps by a script
 * (ADR 0063), and the camp is a map the player walks (ADR 0064). Choices and
 * battle outcomes set story flags that later steps can test (ADR 0060, 0061).
 */
import type { BurningTile, Cell, Goal, Shift, TileChange, WeatherKind } from "@tk/rules";
import type { MapDef } from "./maps.ts";
import type { ScenarioUnit } from "./scenarios.ts";

/**
 * A test on the story flags (ADR 0060). `is` holds when the flag has that
 * value; `not` holds when it has any other value or none.
 */
export type Cond =
  | { readonly flag: string; readonly is: string }
  | { readonly flag: string; readonly not: string };

/** A portrait's expression for one line. */
export type Mood = "calm" | "smile" | "angry" | "grim" | "hurt";

/** The painted backdrop behind a scene. */
export type Backdrop = "court" | "camp" | "river" | "night" | "field" | "fire" | "dawn";

export interface Line {
  /** Officer id of the speaker; omitted for narration. */
  readonly who?: string;
  /** A name to show instead of the officer's (a messenger, a letter). */
  readonly name?: string;
  /** In a staged scene, the cast member who says it, when that isn't `who` (an extra). */
  readonly actor?: string;
  /** Which army the speaker belongs to, for the portrait colours (default: the officer's usual side). */
  readonly side?: number;
  readonly mood?: Mood;
  readonly text: string;
  /** Said only when this holds (ADR 0060). */
  readonly if?: Cond;
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
  readonly if?: Cond;
}

/** An officer who leaves the roster for good: an id, or an id and a condition. */
export type Leave = string | { readonly officer: string; readonly if: Cond };

// ---------------------------------------------------------------- staged scenes (ADR 0063)

/** Which way an actor looks. */
export type Facing4 = "up" | "down" | "left" | "right";

/** Light over a stage. */
export type StageLight = "day" | "dusk" | "night";

/** Things on a stage that are not ground: furniture, tents, banners (drawn by the client). */
export type PropKind =
  | "throne"
  | "table"
  | "pillar"
  | "banner"
  | "tent"
  | "brazier"
  | "screen"
  | "lantern"
  | "gate"
  | "horse"
  | "cart"
  | "grain"
  | "rack"
  | "well"
  | "fence"
  | "chest"
  | "statue"
  | "bed"
  | "sluice";

export interface PropDef {
  readonly kind: PropKind;
  readonly at: Cell;
  /** For banners and tents: whose colours (default 0, ours). */
  readonly side?: number;
  /** Props never block walking unless they say so (pillars, tents, tables do by default). */
  readonly solid?: boolean;
}

/**
 * The tile map a scene is acted on. Rows use the battle terrain codes plus the
 * stage floors: `_` boards, `=` paving, `r` carpet (see STAGE_CODES).
 */
export interface StageDef {
  readonly id: string;
  readonly rows: readonly string[];
  readonly light?: StageLight;
  readonly weather?: WeatherKind;
  /** What the walls are made of indoors: a tent's canvas or a hall's timber (default: from the floors). */
  readonly walls?: "tent" | "hall";
  readonly props?: readonly PropDef[];
}

/** Someone on a stage: an officer, or an extra in an army's colours. */
export interface Actor {
  /** The name the script uses: an officer id, or a unique name for an extra (`guard-1`). */
  readonly id: string;
  /** Officer (or troop template) whose look and name the actor has; default: `id`. */
  readonly officer?: string;
  /** The figure; default: the officer's class in the roster, else infantry. */
  readonly classId?: string;
  readonly side: number;
  /** Where the actor stands when the scene opens; omitted, the actor is offstage until `enter` or `place`. */
  readonly at?: Cell;
  readonly facing?: Facing4;
  readonly if?: Cond;
}

export type Emote = "!" | "?" | "..." | "sweat" | "anger" | "heart" | "note" | "idea" | "tear";

/** Poses an actor can strike; `kneel`, `down` and `cheer` are held until the next pose or walk. */
export type ActPose =
  | "stand"
  | "bow"
  | "kneel"
  | "down"
  | "nod"
  | "shake"
  | "jump"
  | "attack"
  | "hurt"
  | "guard"
  | "cheer";

export type StageFx = "flash" | "shake" | "smoke" | "sparks" | "splash" | "dust";

export interface ChoiceOption {
  /** The flag's value if picked. */
  readonly value: string;
  readonly text: string;
  /** Spoils that come with the choice. */
  readonly gold?: number;
  readonly items?: readonly string[];
}

/** One step of a scene script. Steps run one after another; `all` runs its steps together. */
export type Cmd = (
  | { readonly do: "say"; readonly line: Line }
  | { readonly do: "walk"; readonly who: string; readonly to: Cell; readonly run?: boolean }
  /** Appears at `from` (a gate, the map edge) and walks to `to`. */
  | {
      readonly do: "enter";
      readonly who: string;
      readonly from: Cell;
      readonly to: Cell;
      readonly run?: boolean;
    }
  /** Walks to `to` and is gone. */
  | { readonly do: "leave"; readonly who: string; readonly to: Cell; readonly run?: boolean }
  | { readonly do: "place"; readonly who: string; readonly at: Cell; readonly facing?: Facing4 }
  | { readonly do: "remove"; readonly who: string }
  /** Turns to a direction, or toward another actor. */
  | { readonly do: "face"; readonly who: string; readonly to: Facing4 | string }
  | { readonly do: "emote"; readonly who: string; readonly emote: Emote }
  | { readonly do: "act"; readonly who: string; readonly pose: ActPose }
  /** `who` strikes `target`, who reels (and falls, with `fells`). */
  | {
      readonly do: "strike";
      readonly who: string;
      readonly target: string;
      readonly fells?: boolean;
    }
  /** Pans to a tile or an actor; `zoom` in ‰ (1000 = the whole stage fits). */
  | { readonly do: "camera"; readonly at: Cell | string; readonly zoom?: number }
  | { readonly do: "wait"; readonly ms: number }
  | { readonly do: "sound"; readonly sfx: string }
  | { readonly do: "fx"; readonly fx: StageFx; readonly at?: Cell | string }
  /** Sets tiles alight for the rest of the scene. */
  | { readonly do: "fire"; readonly cells: readonly Cell[] }
  /** Changes tiles (a flood, a gate thrown open). */
  | { readonly do: "tiles"; readonly tiles: readonly TileChange[] }
  | { readonly do: "light"; readonly light: StageLight }
  /** The player picks; the pick is stored in `flag` (ADR 0060). */
  | {
      readonly do: "choice";
      readonly flag: string;
      readonly prompt: string;
      readonly options: readonly ChoiceOption[];
    }
  | { readonly do: "all"; readonly cmds: readonly Cmd[] }
) & { readonly if?: Cond };

export interface SceneDef {
  readonly id: string;
  readonly title: string;
  readonly place: string;
  readonly when: string;
  /** The painting behind the title card, and the mood of the music. */
  readonly backdrop: Backdrop;
  readonly stage: StageDef;
  readonly cast: readonly Actor[];
  readonly script: readonly Cmd[];
  /** Roster changes once the scene is over (after its choices). */
  readonly joins?: readonly JoinDef[];
  /** Officers who leave for good (ADR 0060): they are not brought back by a later chapter. */
  readonly leaves?: readonly Leave[];
}

/** When an in-battle talk plays. */
export type TalkTrigger =
  | { readonly at: "start" }
  /** At the start of the player's first turn of this round. */
  | { readonly at: "round"; readonly round: number }
  | { readonly at: "defeated"; readonly unit: string }
  | { readonly at: "arrived"; readonly unit: string }
  /** When a map shift fires (the flood at 하비, ADR 0062). */
  | { readonly at: "shifted"; readonly shift: string }
  /** When the battle ends with this winner (0 = the player). */
  | { readonly at: "end"; readonly winner: number };

export interface BattleTalk {
  readonly when: TalkTrigger;
  readonly lines: readonly Line[];
  readonly if?: Cond;
}

/** A story-battle unit that may depend on the flags (진궁 fights for whoever he serves). */
export type StoryUnit = ScenarioUnit & { readonly if?: Cond };

/** A story-battle goal that may depend on the flags. */
export type StoryGoal = Goal & { readonly if?: Cond };

/** A map change in a story battle (ADR 0062). */
export type StoryShift = Shift & { readonly if?: Cond };

/**
 * A fate decided by play (ADR 0061): once the battle is won, `flag` becomes
 * `standing` if `unit` is still on the field, else `fallen`.
 */
export interface FateDef {
  readonly flag: string;
  readonly unit: string;
  readonly standing: string;
  readonly fallen: string;
  /** What the result screen says for each outcome. */
  readonly notes?: { readonly standing: string; readonly fallen: string };
}

export interface StoryBattleDef {
  readonly id: string;
  readonly name: string;
  readonly place: string;
  readonly summary: string;
  readonly map: MapDef;
  readonly weather: WeatherKind;
  /** How the field is lit; night battles are darker (looks only). */
  readonly light?: StageLight;
  readonly roundLimit: number;
  /** Side names: 0 is the player, 1 the enemy, 2 the allies (if any). */
  readonly sides: readonly string[];
  /** Where roster officers may be placed, in order of preference. */
  readonly slots: readonly Cell[];
  /** Most roster officers that may sortie. */
  readonly max: number;
  /** Officers who must sortie. */
  readonly required: readonly string[];
  /** Our own units that come with the battle, not from the roster: side 0, ours to command (they earn nothing). */
  readonly guests: readonly StoryUnit[];
  /**
   * Other lords' people fighting on our side (ADR 0066): side 2, orange, moved
   * by their own AI in their phase after ours.
   */
  readonly allies?: readonly StoryUnit[];
  readonly enemies: readonly StoryUnit[];
  readonly goals: readonly StoryGoal[];
  /** Map changes during the battle: the flood at 하비, a gate opened from inside (ADR 0062). */
  readonly shifts?: readonly StoryShift[];
  /** Tiles to mark ★ for the player besides goal tiles (a sluice to open). */
  readonly marks?: readonly Cell[];
  /** Tiles already on fire when the battle begins. */
  readonly burning?: readonly BurningTile[];
  readonly fates?: readonly FateDef[];
  readonly goalText: { readonly win: string; readonly lose: string };
  readonly rewards: { readonly gold: number; readonly items: readonly string[] };
  readonly talk: readonly BattleTalk[];
  /** The tenser music for boss fights. */
  readonly music?: "battle" | "danger";
}

/** Where something happens in the walkable camp (ADR 0064). */
export type StationKind = "shop" | "armoury" | "records" | "council";

export interface StationDef {
  readonly kind: StationKind;
  /** Stepping on (or tapping) this tile opens the station. */
  readonly at: Cell;
}

/** A camp map: a stage, where 조조 starts, the stations, and where officers loiter. */
export interface CampGround {
  readonly stage: StageDef;
  readonly start: Cell;
  readonly stations: readonly StationDef[];
  /** Tiles where roster officers stand, in order of rank. */
  readonly spots: readonly Cell[];
}

/** Something an officer says when 조조 talks to them in camp. */
export interface CampTalk {
  readonly officer: string;
  readonly lines: readonly Line[];
  readonly if?: Cond;
}

export interface CampDef {
  readonly id: string;
  /** Item ids the shop sells here. */
  readonly shop: readonly string[];
  readonly ground: CampGround;
  /** Lines for this camp; officers without one use their usual camp lines. */
  readonly talk?: readonly CampTalk[];
}

export type ChapterStep = (
  | { readonly kind: "scene"; readonly scene: SceneDef }
  | { readonly kind: "battle"; readonly battle: StoryBattleDef }
  | { readonly kind: "camp"; readonly camp: CampDef }
) & {
  /** Steps whose condition fails are passed over (ADR 0060). */
  readonly if?: Cond;
};

export interface ChapterDef {
  readonly id: string;
  /** Place in the story: 1, 2, 3 … (ADR 0056). */
  readonly number: number;
  readonly name: string;
  readonly title: string;
  readonly years: string;
  readonly summary: string;
  /**
   * The roster when the chapter is started fresh. A campaign that carries on
   * from the chapter before keeps its own roster; these officers join it if
   * they are missing (ADR 0056).
   */
  readonly roster: readonly JoinDef[];
  readonly gold: number;
  /** Officers carried in below this level are raised to it, with growth. */
  readonly floor: number;
  /** The chapter that comes next, if it exists yet. */
  readonly next?: string;
  /**
   * Flags a fresh start of this chapter assumes: what history did in the
   * chapters before (ADR 0060). A carried-over campaign keeps its own.
   */
  readonly assumes?: Readonly<Record<string, string>>;
  readonly steps: readonly ChapterStep[];
  /** The closing screen. */
  readonly ending: { readonly title: string; readonly lead: string };
}
