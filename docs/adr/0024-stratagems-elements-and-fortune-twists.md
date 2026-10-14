# 0024. Stratagems: MP and elements shaped by terrain and weather, with rare fortune twists

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0020](0020-five-stats-with-distinct-roles.md), [0021](0021-class-families-and-tiers.md),
  [0023](0023-terrain-family-affinity-and-cover.md), [`docs/remake/RULES.md` §5](../remake/RULES.md#5-stratagems)

## Context

[0020](0020-five-stats-with-distinct-roles.md) gives 지력 stratagem power,
resistance and MP, and gives 운 status resistance. Still open: the shape of
stratagems, whether terrain and weather matter, and cooldowns. In 1998 조조전,
fire is stronger on grass and forest and needs dry weather, which makes map and
weather part of the tactics. The owner chose that, and added: *"sometimes 운
makes an unexpected outcome (more fun and frustration for the user, but not
too frequent)"*.

## Options considered

1. **MP + elements × terrain/weather:** chosen, plus fortune twists.
2. **MP, no elements:** simpler; strategists are less tied to the map.
3. **MP + elements + cooldowns:** smoother PvP, more rules.

## Decision

- **Cost:** stratagems cost MP (no cooldowns). Each has a fixed range and area shape.
- **Schools:** fire, water, earth, wind (damage and terrain effects); support
  (heal, buffs); disruption (status effects: confusion, bind, silence, poison).
- **Power:** damage stratagems use `base + 지력a − ⌊지력d/2⌋`, then
  × terrain‰ (per school × terrain, e.g. fire 1200 on forest or grass, 600 on
  river or ford) × weather‰ (e.g. fire 1100 sunny, 500 rain, 1300 east wind), each floored.
- **Status effects:** `chance‰ = clamp(500 + 5·(지력a − 지력d) − 3·운d, 50, 900)`.
- **Weather:** each map has a weather state (sunny, cloudy, rain, wind with a
  direction). Some maps change it each round from the battle `Prng` or by script.
- **Fortune twist (owner's rule):** after a stratagem resolves, one roll of
  `twist‰ = 40` (about 1 cast in 25). If it triggers:
  - **천운 windfall** with `windfall‰ = clamp(500 + 6·(운caster − 운target), 150, 850)`,
    where 운target is the main target's 운, or 50 with no target. Each stratagem
    defines its windfall: ×1500/1000 power, +1 area ring (fire spreads), or MP refunded.
  - **불운 mishap** otherwise: the stratagem fizzles (MP spent), or for some
    stratagems backfires at half power on the caster's own tile.
  - Shown with a distinct banner and sound; logged in the battle record.
- **Draw order** (determinism, [0004](0004-server-authoritative-referee.md)): success or
  status roll, then twist roll, then direction roll, all from the battle `Prng`.
- `twist‰` is mode configuration (default 40 everywhere). Arena may lower it if
  PvP players find it too swingy.

## Consequences

- **Positive:** map and weather matter; 책사 and 도사 play differently; 운
  gains a memorable, visible role; rare twists create stories ("the fire turned on us at Red Cliffs").
- **Negative:** twists add variance that can feel unfair in PvP, so rate and
  outcomes are tunable per mode. Each stratagem needs authored windfall and mishap variants.
- **Follow-ups:** the stratagem list per school and tier; weather tables per map; UI banners.

## Revisit when

Players report twists as mostly frustrating (lower `twist‰` or soften mishaps),
or they go unnoticed (raise it, or make them more visible).
