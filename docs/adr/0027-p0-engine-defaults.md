# 0027. Rules-engine defaults chosen while building P0

- **Status:** Proposed (implemented; the owner to confirm or change)
- **Date:** 2026-10-14
- **Deciders:** Claude, pending the project owner
- **Related:** [0003](0003-turn-structure.md), [0004](0004-server-authoritative-referee.md),
  [0020](0020-five-stats-with-distinct-roles.md), [0022](0022-medium-number-scale.md),
  [0024](0024-stratagems-elements-and-fortune-twists.md), [`docs/remake/RULES.md` §7](../remake/RULES.md#7-turns-rounds-and-victory)

## Context

Implementing `packages/rules` needed answers the ADRs leave open. Each choice
below was made to keep the engine simple and deterministic; all can change.

## Decision

| # | Default | Why |
|---|---|---|
| 1 | **One command = one unit's whole action:** move to a tile, then attack, cast or wait. | One activation in alternating play is one command; the referee validates it in one step. The UI still lets players move first and pick an action after. |
| 2 | **Alternating rounds switch the starting side every round** (side 0 starts odd rounds, side 1 even rounds). Side phases always start with the lowest side (the player). | Spreads the first-mover advantage across rounds. |
| 3 | **Max HP = class HP + 통솔 + 4 × level; max MP = class MP + ⌊지력 / 2⌋.** | Matches ADR 0022's HP 100–600 band, and ADR 0020's 지력 → MP. |
| 4 | **Healing tiles** heal at the start of each new round (‰ of max HP, at least 1). | Simple and visible. |
| 5 | **Confusion** makes the unit skip its actions: its only legal command is *wait* in place, which uses up one round of the status. Confused units can't counter. | Clear rule; "rounds" means actions lost, wherever in the round the status landed. |
| 6 | **Round limit → draw** (winner −1). Story maps will override this with objectives (hold N rounds = defender wins). | Neutral default for the sandbox and Arena. |
| 7 | **Officer fate:** escape roll first (`escape‰`), then killed or captured 50 / 50. | ADR 0020 left the split to "the battle and the captor"; 50/50 until capture rules exist. |
| 8 | **Baseline AI:** a deterministic utility scorer over all legal commands. It weighs expected damage, kills and counter damage taken, plus exposure from a threat map of enemy reach next turn, healing, cover near enemies, and an approach pull that grows each round. It uses exact previews only, with no randomness. | Runs identically on client and server (ADR 0004); good enough for soak tests and a first sandbox. |

## Consequences

- **Measured with the baseline AI** on a mirrored 6-vs-6 battle (40 seeds):
  side phases favour the side that moves second (it strikes first after the
  other approaches). Alternating results swing with AI caution (from 28–12 for
  the first mover to 4–36 once the AI weighs exposure). The baseline AI's
  approach timing decides these mirrored battles, so its numbers can't settle
  balance yet. A stronger AI (one-ply lookahead, moving as a group) comes
  before balance reports are trusted.
- Every default is covered by tests in `packages/rules/test/`.

## Revisit when

The owner changes any row, or playtests show a default feels wrong. A change becomes its own ADR.
