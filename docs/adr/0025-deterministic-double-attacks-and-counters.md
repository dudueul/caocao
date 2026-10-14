# 0025. Double attacks by a fixed 민첩 threshold; one counter at 800‰

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0020](0020-five-stats-with-distinct-roles.md), [0022](0022-medium-number-scale.md),
  [0024](0024-stratagems-elements-and-fortune-twists.md), [`docs/remake/RULES.md` §2](../remake/RULES.md#2-attack-resolution-draft)

## Context

[0020](0020-five-stats-with-distinct-roles.md) gave 무력 counter damage and
민첩 double attacks; the conditions were open. Battles already have three
random layers: hit, critical, and the fortune twist ([0024](0024-stratagems-elements-and-fortune-twists.md)).
Deterministic rules can be shown exactly in the attack preview; random ones can't.

## Options considered

1. **Deterministic thresholds:** chosen.
2. **Random double attack (1998-style)**, with a chance that scales with the 민첩 gap: more surprise, less planning.
3. **Counters only, no double attacks.**

## Decision

- **Double attack:** the attacker strikes twice when `민첩a − 민첩d ≥ 20`
  (`≥ 10` for 무도가). Each strike rolls hit and critical separately.
- **Counter:** if the defender survives and the attacker is inside the
  defender's attack range, the defender strikes back **once per enemy action**
  at 800‰ damage (its own ATK, so 무력 counts). Catapult attacks and stratagems
  never trigger counters; disabled units (bind, confusion) can't counter.
- **Order:** first strike → counter → second strike (only if both are still standing).
- **Prng draw order:** first strike (hit, critical), counter (hit, critical), second strike (hit, critical).
- The attack preview shows `×2`, the counter damage and the hit chances exactly.

## Consequences

- **Positive:** players can plan from the preview; randomness stays in the
  existing layers; the attacker keeps an edge (full damage and the initiative,
  against an 800‰ counter).
- **Negative:** stat thresholds create breakpoints (19 vs 20 민첩), so balance
  must watch units sitting right at them.

## Revisit when

Breakpoints dominate officer and equipment choices, or counters make attacking too costly.
