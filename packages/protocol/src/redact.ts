import type { BattleState } from "@tk/rules";

/**
 * The state a client may see: everything but the Prng, so no client can predict
 * the next roll (ADR 0037). Previews and legal commands don't need it.
 */
export function redact(state: BattleState): BattleState {
  return { ...state, prng: [0, 0, 0, 0] };
}
