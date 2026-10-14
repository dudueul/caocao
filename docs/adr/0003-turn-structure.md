# 0003. Side phases in PvE, alternating single-unit activations in PvP

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0012](0012-household-corps-seats.md),
  [`docs/remake/ARCHITECTURE.md` §3](../remake/ARCHITECTURE.md#3-rules-engine)

## Context

The 1998 조조전 and the mobile story mode use **side phases**: one side moves all
its units, then the other. The mobile real-time PvP kept phases plus per-unit
turn timers (`BattlePlayManager.ResetUnitTurnTime` / `ChangeTurnTime`). In
online play, side phases mean long waits through the opponent's whole phase and
a strong first-mover advantage (alpha strikes).

## Options considered

1. **Side phases everywhere:** faithful and simplest; slow, unequal PvP.
2. **Phases in PvE, alternating activations in PvP:** chosen.
3. **Simultaneous orders (WEGO):** no waiting, but complex conflict resolution; a big departure from 조조전.
4. **Real-time:** needs tick loops and client prediction, and performance starts to matter. Not 조조전.

## Decision

One rules engine with a pluggable **turn policy**:

- `SidePhases` for Story, Raids and battles against the AI: the player phase, then each AI side in order.
- `AlternatingActivations` for PvP and multi-seat World battles: sides
  alternate, each moving **one unit** per activation with a timer (about 20 s,
  tunable). Each unit acts once per round.

Everything else (movement, attack, skills, terrain, conditions) is identical.

## Consequences

- **Positive:** PvE keeps the 조조전 feel. PvP waits are a single unit's move.
  The battle runs entirely turn-based, so performance isn't a constraint.
- **Negative:** two policies to balance (for example, per-round effects must be
  defined in terms of rounds, not phases). The AI must handle both.
- **Follow-ups:** timer, round-end and pass rules in the rules spec.

## Revisit when

Playtests show alternating activations feel too fragmented in large World
battles. A middle ground is activating small groups of 2–3 units.
