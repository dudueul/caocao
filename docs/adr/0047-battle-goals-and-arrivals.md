# 0047. Let battles have goals, timed arrivals and a guard order

- **Status:** Accepted (proposed while building, confirmed by the owner 2026-10-19)
- **Date:** 2026-10-19
- **Deciders:** Claude (proposal), project owner (accepted)
- **Related:** [0004](0004-server-authoritative-referee.md), [0006](0006-typescript-everywhere.md),
  [0030](0030-modern-techniques-adopted.md), [0034](0034-scenario-ai-orders.md),
  [0049](0049-story-chapter-guandu.md), [RULES.md §7](../remake/RULES.md#7-turns-rounds-and-victory)

## Context

Until now a battle ended only when one side was routed, or in a draw at the
round limit. Story battles need the usual tactics-game objectives: defeat the
enemy commander, hold out for some rounds, reach a place, keep a lord alive.
They also need reinforcements that come in partway. Both change the rules
state, so they belong in the deterministic rules package, where replays and
the referee see them (ADR 0004).

Playtests with the AI on both sides showed a third need: a commander given the
existing "hold" order still rode out of his fort to attack anyone in reach,
and died in round 3.

## Options considered

1. **Goals, reserve units and a guard order in the rules state:** chosen. Pure
   data, checked in `apply`, so replays stay exact.
2. **Script objectives in the client:** quick, but replays and a future
   `PlayerDO` re-simulation would not agree with what the player saw.
3. **A general event VM now:** the P2 plan mentions one, but nothing yet needs
   more than these three goals and round-based arrivals.

## Decision

- `BattleState` gains `goals` and `reserve`; the state **format becomes 3**.
- A goal is `{ side, when }`: that side wins as soon as `when` holds.
  - `defeat` — every listed unit is defeated;
  - `hold` — a given round begins;
  - `reach` — a living unit of a side stands on one of the given tiles.
- Goals are checked in list order:
  - after every action;
  - at the start of each round, before the round-limit draw;
  - after fire damage.
- A side with no living units cannot win by a goal, and a rout still ends the battle.
- `battleEnd` carries the index of the goal that decided it.
- Units with `arrives: N` (N ≥ 2) wait in `reserve` and join at the start of round N.
  - They land on their own tile, or on the nearest free tile they can stand on (searched outward in neighbour order).
  - An `arrived` event announces each one. A side already routed is not saved by its reserve.
- New gambit behaviour **`guard`**: never leave the tile; attack or cast from it,
  otherwise wait. `ORDERS.guard` uses it for commanders in their forts.
- The AI reads goals:
  - damage and kills on a unit its side must defeat count twice;
  - a unit the enemy must defeat weighs its own exposure twice.

## Consequences

- **Positive:** Story maps get real objectives, reinforcements and protected
  lords; replays, hashes and the referee keep working unchanged.
- **Negative / costs:** a state format bump (old v2 states are not loaded; no
  saved battles exist yet); the AI's goal weights are new tuning.
- **Follow-ups:** RULES.md §7 and §9; triggers other than rounds (an arrival when
  a unit falls) when a scenario needs one.

## Revisit when

A scenario needs a trigger or objective these three goals can't express, or
the Arena wants objectives (capture points) — then consider the event VM.
