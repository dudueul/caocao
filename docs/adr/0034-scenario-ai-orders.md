# 0034. Scenario units carry standing AI orders, written as gambits

- **Status:** Proposed (default chosen while building P0; the owner is asked to confirm)
- **Date:** 2026-10-14
- **Deciders:** Claude (proposal)
- **Related:** [0027](0027-p0-engine-defaults.md), [0030](0030-modern-techniques-adopted.md),
  [0032](0032-content-as-typed-modules.md), [`docs/remake/RULES.md` §9](../remake/RULES.md#9-gambits-ai-rules-for-absent-seats-and-auto-battle)

## Context

The first sandbox runs showed the baseline AI marching every defender out of
the 오소 depot. Raiders that moved first in side phases won 38 of 40 AI-vs-AI
battles; adding a seventh defender didn't help. 조조전 maps give enemies
orders instead ("hold this fort", "guard the lord"). Gambits
([0030](0030-modern-techniques-adopted.md)) already express "condition → behaviour"
rules for the AI.

## Options considered

1. **Orders as gambits on scenario units:** chosen. No new engine code; the same
   gambit list drives absent World seats, auto-battle and scenario enemies.
2. **AI profiles** (aggressive, defensive, support) as weights in the scorer:
   more general, but needs tuning per profile; still on the list (open question 8).
3. **Tune the numbers only** (levels, extra units): measured, and it moves win rates
   without changing the enemies' behaviour.

## Decision

- `ScenarioUnit.orders` is an optional gambit list; `scenarioOrders()` maps unit
  ids to their orders, and the client and soak tests pass them to `chooseCommand`.
- `ORDERS.hold` (attack what comes into reach, otherwise stay put) and
  `ORDERS.healThenHold` (heal an ally under 60% HP first) are the first presets.
- The 오소 defenders hold the camp; 저수 heals.

## Consequences

- **Measured** (40 AI-vs-AI battles each): with holding defenders and a healing 저수,
  the raiders win 24–16 under side phases and 18–22 alternating, against 38–2
  and 29–11 without orders. A human raider should win more often than the AI does.
- Orders stay data, so Story maps get them for free; event scripts can change
  them later (for example "charge once the gate falls").

## Revisit when

AI profiles land, or Story maps need orders that gambits can't express.
