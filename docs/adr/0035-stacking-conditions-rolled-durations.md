# 0035. Conditions stack with diminishing weight; each stack's duration is rolled when it lands

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0006](0006-typescript-everywhere.md),
  [0015](0015-delivery-order-engine-then-arena.md), [0024](0024-stratagems-elements-and-fortune-twists.md),
  [0027](0027-p0-engine-defaults.md), [0036](0036-p0-skills-and-conditions.md),
  [`docs/remake/RULES.md` §10](../remake/RULES.md#10-conditions)

## Context

P0's exit criterion includes conditions (buffs and debuffs) and 10+ skills
([0015](0015-delivery-order-engine-then-arena.md)). Until now a unit had at most
one status (confusion) with a fixed length ([0027](0027-p0-engine-defaults.md)).
The owner chose how conditions combine and how long they last.

## Options considered

How conditions combine:

1. **Fixed kinds, no stacking:** re-applying refreshes; simplest to read.
2. **Stacking modifiers, each further stack worth less:** chosen ("stacking, but discounted stacks").
3. **Fixed kinds with ±1 to ±3 stages:** a middle ground, Pokémon-style.

How long each lasts:

1. **Rolled on landing and shown:** chosen. Players see each stack's exact turns left.
2. **A chance to wear off each turn:** more suspense, harder to plan.
3. **Rolled on landing but hidden:** same odds, less information.

## Decision

- A condition is data (`ConditionDef`): ATK‰, DEF‰, hit‰ and move modifiers,
  damage over time (‰ of max HP), or a block on acting, moving or casting.
- A unit can carry up to 3 stacks of each condition. Each further stack of the
  same condition counts half the one before (weights 1000, 500 and 250‰, tuning
  `stackDiscount` and `maxStacks`). A new stack beyond the limit replaces the
  shortest one if it would last longer. Different conditions add up; ATK and DEF
  never drop below 300‰ (tuning `modifierFloor`).
- **Every stack has its own duration,** rolled from the skill's `[min, max]` range
  with the battle `Prng` when it lands. The UI shows each stack's turns left and the
  range in the forecast. A windfall twist adds one turn.
- A **turn** is one of the bearer's activations. Stacks count down when the bearer
  finishes acting, or is passed over by `endPhase`. A stack that lands on the caster
  during its own turn starts counting from the next one. Damage over time hits at
  the same moment.
- Debuffs on enemies roll to land (the status chance of [RULES.md §5](../remake/RULES.md#5-stratagems));
  buffs on allies always land.
- `Prng` draw order for a cast: per target by unit id, the landing roll (debuffs on
  enemies only), then the duration roll if it landed; then the twist rolls.
- The battle state format becomes **version 2** (`Unit.conditions` replaces
  `Unit.status`). Replays from version 1 don't load.

## Consequences

- **Positive:** stacking rewards combos without runaway numbers (three stacks of
  +15% ATK give +26%, not +45%); random lengths add the uncertainty the owner wants,
  while the visible count keeps it plannable; durations replay exactly.
- **Negative / costs:** a stack's length is one more number on the unit card;
  the AI must value conditions over their expected duration.
- **Follow-ups:** the P0 conditions and skills are in [0036](0036-p0-skills-and-conditions.md);
  items and terrain may apply conditions later.

## Revisit when

Playtests show stacking that is too strong or too weak, or the rolled lengths feel
arbitrary, which would argue for narrower ranges.
