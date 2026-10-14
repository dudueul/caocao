# 0026. Soft zones of control, with skirmisher exceptions

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0021](0021-class-families-and-tiers.md), [0023](0023-terrain-family-affinity-and-cover.md),
  [`docs/remake/RULES.md` §6](../remake/RULES.md#6-movement), `packages/rules/src/move.ts`

## Context

Movement is the biggest rule after combat: it decides whether front lines and
chokepoints matter. Without a zone of control (ZOC), enemies only block their
own tile, so cavalry slips past a thin line to the archers behind it (Fire
Emblem; also most 1998 조조전 play). With ZOC, a line of infantry really protects the back row.

## Options considered

1. **Soft ZOC + skirmisher exceptions:** chosen.
2. **No ZOC:** fast, mobile battles; back lines are hard to protect.
3. **Hard ZOC for everyone:** very positional, slower; cavalry loses its line-breaking role.

## Decision

- Entering a tile **next to a living enemy** ends that unit's movement.
- **Skirmishers** (cavalry, horse archers, bandits; `ClassDef.skirmisher`)
  ignore the first such tile on a move and may continue.
- A unit that starts its move inside a ZOC may leave freely.
- Enemies block their own tile. Allies can be passed through but not stopped on.

## Consequences

- **Positive:** formations and chokepoints matter; cavalry keeps its identity
  as the line-breaker; the rule is easy to show in the move preview (ZOC tiles tinted).
- **Negative:** more cautious play; the AI needs threat awareness (now in place) to use lines well.
- Implemented in `reachable()` as Dijkstra over (tile, exception used); covered by `move.test.ts`.

## Revisit when

Battles stall at chokepoints, or skirmishers are too strong at diving the back line.
