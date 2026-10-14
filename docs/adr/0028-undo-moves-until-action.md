# 0028. Moves can be undone freely until an action is confirmed; nothing after that

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0025](0025-deterministic-double-attacks-and-counters.md),
  [0027](0027-p0-engine-defaults.md), [`docs/remake/BENCHMARK.md`](../remake/BENCHMARK.md)

## Context

The decompiled original replayed the same random numbers after an undo, so it
quietly added a hit bonus per undo to stop players from re-rolling misses. Our
battle draws every roll from one seeded PRNG in the state, and a command is one
unit's whole action, a move plus an action ([0027](0027-p0-engine-defaults.md)).
Movement never rolls; attacks, stratagems and fortune twists do.

## Options considered

1. **Undo moves until an action is confirmed:** chosen.
2. **Plus a limited whole-turn rewind in Story** (same plan would give the same rolls).
3. **Fully committed moves**, final on release.

## Decision

- In the client, a selected unit can be moved and moved back any number of
  times, with exact previews from each tile ([0025](0025-deterministic-double-attacks-and-counters.md)).
- Confirming *attack*, *stratagem* or *wait* sends one `act` command. It is
  final: no undo, no rewind, in every mode, solo and online.
- No hidden bonuses or re-rolls exist anywhere.

## Consequences

- **Positive:** friendly on touch screens, fair, and identical in PvP and solo;
  it falls out of the referee model, where only confirmed commands exist.
- **Negative:** no take-backs after a misjudged attack; previews must be clear
  enough that players rarely need one.

## Revisit when

Story players ask for a rewind; then add it as a separate, limited Story-only feature.
