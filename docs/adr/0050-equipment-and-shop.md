# 0050. Officers carry a weapon, an armour and an accessory, bought in camp

- **Status:** Proposed
- **Date:** 2026-10-19
- **Deciders:** Claude (default chosen while building; the owner to confirm)
- **Related:** [0014](0014-scoped-progression-fair-arena.md), [0021](0021-class-families-and-tiers.md),
  [0022](0022-medium-number-scale.md), [0046](0046-period-accurate-dress.md),
  open question 7 in [docs/remake/README.md](../remake/README.md)

## Context

The rules already add `atkBonus` and `defBonus` to ATK and DEF, but nothing
set them. Open question 7 (equipment) and RULES.md §11 ("equipment types per
family") were open. A campaign needs something to spend rewards on.

## Options considered

1. **Three slots, kinds per family, a shop per camp:** chosen. Uses the bonuses
   the rules have; no engine change.
2. **Consumable items used in battle (healing beans):** needs a new action in
   the rules and the AI; later.
3. **No equipment, only levels:** simpler, but the camp has nothing to do.

## Decision

- **Slots:** weapon (adds ATK), armour (adds DEF), accessory (adds stats).
  A few weapons and armours add a stat too. Stats with gear cap at 100.
- **Kinds per family:**
  - Weapons: blade, polearm, bow, fan, shot. Infantry and cavalry take blades and polearms, archers bows, strategists fans, catapults shot.
  - Armour: heavy, light, robe.
- **25 items**, named after objects of the period (in the spirit of ADR 0046):
  - 환수도, 마삭, 각궁 and other weapons;
  - 찰갑, 어린갑, 명광개 and other armour;
  - 손자병법, 호부, 준마 and other accessories.

  Three tiers of strength (+8, +16, +26 ATK/DEF). The strongest are battle rewards only.
- **Shop:** each camp sells a list that grows through the chapter; selling returns half the price.
- Gold comes from battle rewards (800–2000 a battle).
- Gear that no longer fits after a class change returns to the inventory.
  Promotions stay in the family, so in practice nothing falls off.
- Arena battles use no equipment (ADR 0039, 0041 unchanged).

## Consequences

- **Positive:** camp choices matter; no rules change; every number is data.
- **Negative / costs:** item balance is new tuning; no consumables yet.
- **Follow-ups:** consumables as a rules action; unique items for named heroes.

## Revisit when

Players hoard gold with nothing worth buying, or one item is always the answer.
