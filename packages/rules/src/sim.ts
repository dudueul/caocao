/** Headless AI-vs-AI runner, used by soak tests and balance reports. */
import { chooseCommand } from "./ai.ts";
import { apply } from "./battle.ts";
import type { BattleEvent, BattleState, Command, Ruleset } from "./types.ts";

export interface SimResult {
  state: BattleState;
  commands: Command[];
  events: BattleEvent[];
  steps: number;
}

export function runBattle(start: BattleState, rules: Ruleset, maxSteps = 5000): SimResult {
  let state = start;
  const commands: Command[] = [];
  const events: BattleEvent[] = [];
  let steps = 0;
  while (state.winner === null && steps < maxSteps) {
    const command = chooseCommand(state, rules);
    if (command === null) break;
    const result = apply(state, rules, command);
    if (!result.ok) throw new Error(`AI chose an illegal command: ${result.reason}`);
    commands.push(command);
    events.push(...result.events);
    state = result.state;
    steps++;
  }
  return { state, commands, events, steps };
}

/** Re-applies a command log; used to verify solo results and replays (ADR 0004). */
export function replay(
  start: BattleState,
  rules: Ruleset,
  commands: readonly Command[],
): BattleState {
  let state = start;
  for (const c of commands) {
    const result = apply(state, rules, c);
    if (!result.ok) throw new Error(`replay rejected a command: ${result.reason}`);
    state = result.state;
  }
  return state;
}
