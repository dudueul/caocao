/**
 * Messages between the client and the MatchDO referee (ADR 0037). Every message
 * is a JSON object tagged by `t`. States sent down never carry the Prng.
 */
import type { BattleEvent, BattleState, Cell, Command } from "@tk/rules";

/** Bumped when a message shape changes incompatibly; `hello` carries it. */
export const PROTOCOL_VERSION = 1;

/** Seat A (0) creates the room and acts first; seat B (1) picks first (ADR 0041). */
export type Seat = 0 | 1;
export type RoomPhase = "waiting" | "draft" | "deploy" | "battle" | "over";

export interface PlayerView {
  name: string;
  connected: boolean;
}

export interface DraftView {
  /** Pool entry ids still available, in pool order. */
  available: string[];
  /** Picks so far, by seat. */
  picks: [string[], string[]];
  /** The whole pick order, by seat; the next pick is `order[picks0 + picks1]`. */
  order: Seat[];
  /** When the current pick runs out (ms since epoch, server clock). */
  deadline: number;
}

export interface DeployView {
  /** Each seat's deploy tiles, in slot order. */
  slots: [Cell[], Cell[]];
  /** Each seat's six pool entry ids, in slot order. */
  arrangement: [string[], string[]];
  ready: [boolean, boolean];
  deadline: number;
}

export interface ClockView {
  /** Seat whose activation is running. */
  active: Seat;
  /** When this activation's free time ends, then the reserve starts draining. */
  freeUntil: number;
  /** Reserve left for each seat at the start of this activation (ms). */
  reserveMs: [number, number];
  /** When the AI takes over this activation. */
  deadline: number;
}

export interface ResultView {
  /** Winning seat, or -1 for a draw. */
  winner: number;
  reason: "battle" | "concede";
}

export interface RoomView {
  code: string;
  phase: RoomPhase;
  /** Seat A and seat B; null until someone takes the seat. */
  players: [PlayerView | null, PlayerView | null];
  draft: DraftView | null;
  deploy: DeployView | null;
  result: ResultView | null;
}

// ---------------------------------------------------------------- client → server

export type ClientMessage =
  /**
   * Join or rejoin a room; `lastSeq` is the last batch this client applied.
   * `create` is true only from the "new room" button, so a mistyped code can't
   * open an empty room.
   */
  | {
      t: "hello";
      v: number;
      room: string;
      key: string;
      name: string;
      lastSeq: number | null;
      create: boolean;
    }
  | { t: "pick"; entry: string }
  /** The seat's six entries in deploy-slot order. */
  | { t: "arrange"; order: string[] }
  | { t: "ready" }
  | { t: "command"; command: Command }
  | { t: "concede" }
  | { t: "ping"; at: number };

// ---------------------------------------------------------------- server → client

export type ServerMessage =
  | { t: "welcome"; seat: Seat; room: RoomView; serverTime: number }
  | { t: "room"; room: RoomView }
  /** Full battle state (Prng removed); `seq` is the last batch it includes. */
  | { t: "snapshot"; seq: number; state: BattleState; clock: ClockView | null }
  /** One applied command: its events and the state after it. */
  | {
      t: "batch";
      seq: number;
      by: "player" | "ai";
      events: BattleEvent[];
      state: BattleState;
      clock: ClockView | null;
    }
  | { t: "rejected"; reason: string }
  | { t: "pong"; at: number; serverTime: number };
