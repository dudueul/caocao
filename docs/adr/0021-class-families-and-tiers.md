# 0021. Twelve class families with three tiers each, plus unique lines

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0007](0007-top-down-2d-pixijs.md), [0015](0015-delivery-order-engine-then-arena.md),
  [0020](0020-five-stats-with-distinct-roles.md), [`docs/remake/RULES.md` §3](../remake/RULES.md#3-classes)

## Context

With the five stats settled ([0020](0020-five-stats-with-distinct-roles.md)),
a class supplies everything else: HP, move, attack-range shape, terrain costs
and bonuses, base attack and defense, stat growth, equipment types and skills.
The 1998 game has about 53 classes in three-tier promotion lines. The owner
draws all art, and a class costs about 28 animation frames ([0007](0007-top-down-2d-pixijs.md)),
so the class count is mostly an art-budget decision.

## Options considered

1. **12 families × 3 tiers + unique lines:** chosen.
2. **Branching promotions** (8 families, two paths at tier 2): about 40 classes; the most art and balance work.
3. **Compact, 8 families × 2 tiers:** about 16 classes; fastest, but less of the promotion feel.
4. **The 1998 layout as is:** about 53 classes with near-duplicates; the heaviest load.

## Decision

- **12 families**, each with **3 tiers**, promoted by level (tier 2 at Lv 20,
  tier 3 at Lv 40; *tunable*). Tiers 2 and 3 reuse the tier-1 animation with new
  details (armour, weapon, mount), so 3 tiers cost about 1.5× the art of one, not 3×.
- **Unique lines** for lords and named heroes (about 6), drawn individually.
- **Counter triangle:** infantry ▶ cavalry ▶ archers ▶ infantry, at ±200‰
  damage. Strategist families sit outside it and act through stratagems.
- The family list and each family's role are in [RULES.md §3](../remake/RULES.md#3-classes).
  One slot (spearmen) is provisional.
- **P0** implements six tier-1 classes: infantry, archer, cavalry, strategist,
  martial artist, catapult ([0015](0015-delivery-order-engine-then-arena.md)).

## Consequences

- **Positive:** keeps the 조조전 feel of steady promotion at about 500–600
  frames of art; balance lives in 36 class rows plus unique lines.
- **Negative:** 12 distinct silhouettes to design; promotion levels interact with
  Arena normalization ([0014](0014-scoped-progression-fair-arena.md)).
- **Follow-ups:** per-class tables (HP, move, range shape, terrain), the
  equipment types per family, and skills per tier.

## Revisit when

Art throughput makes 12 families unrealistic for P2/P3, or playtests show families that overlap.
