# 0039. Arena armies are drafted: six officers each from a shared pool

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner
- **Related:** [0003](0003-turn-structure.md), [0014](0014-scoped-progression-fair-arena.md),
  [0021](0021-class-families-and-tiers.md), [0041](0041-p1-arena-defaults.md)

## Context

[0014](0014-scoped-progression-fair-arena.md) makes the Arena fair: no
progression carries in, and officers come at a normalized level. P1 still has to
decide how each player gets an army.

## Options considered

1. **Draft six from a shared pool:** chosen. The draft is strategy, and matches vary.
2. **Point-budget rosters:** build privately under a budget; costs need careful balancing.
3. **Mirror presets:** identical armies; pure skill, little variety.

## Decision

- Both players see the same pool (P1: 16 officers, each with a fixed class) and
  pick in turn until each has six. The pick order is a snake that ends evenly:
  1 · 2 2 · 1 1 · 2 2 · 1 1 · 2 2 · 1 (1 is the first picker).
- Every officer enters at the same level with their own stats; nothing from
  Story or World carries in.
- After the draft each player arranges their six on their own deploy tiles, then
  the battle starts with alternating activations ([0003](0003-turn-structure.md)).

## Consequences

- **Positive:** no two matches start the same; counter-picking uses the class
  relations; balance work is on the pool, not on prices.
- **Negative:** a match takes a few extra minutes; the pool must be balanced as a whole.

## Revisit when

Friends find the draft too long, or one officer or class dominates picks.
