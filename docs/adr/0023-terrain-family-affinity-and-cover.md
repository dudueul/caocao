# 0023. Terrain: per-family movement and affinity, plus cover for everyone

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0020](0020-five-stats-with-distinct-roles.md), [0021](0021-class-families-and-tiers.md),
  [0022](0022-medium-number-scale.md), [`docs/remake/RULES.md` §4](../remake/RULES.md#4-terrain)

## Context

Terrain is the last core piece the battle engine needs before stratagems. It
decides movement and changes combat. The hit formula already subtracts a
`terrainEvasion‰`. In 1998 조조전, each class has a per-terrain movement
cost and a terrain effect on attack and defense, which gives classes their
identity on the map. Fire Emblem-style games instead give cover bonuses to
whoever stands on a tile.

## Options considered

1. **Family affinity + cover:** chosen.
2. **Cover only (FE-style):** simpler, less class identity.
3. **Affinity + elevation:** most depth; needs height-aware maps and art.

## Decision

- **About 12 terrain types:** plain, grassland, forest, hills, mountain, ford,
  river or lake, bridge, village, fort or castle, wall, camp.
- **Per family × terrain** (12 × 12 table in `packages/content`):
  - a **movement cost**, or impassable;
  - an **effect‰** that multiplies the unit's ATK and DEF on that tile, floored
    (for example cavalry 1100 on plains and 900 in forest; bandits 1200 in mountains).
- **Cover** (per terrain, any unit): an evasion‰ subtracted from the attacker's
  hit chance. Starting values: forest 150, hills and village 100, fort 200.
- **Healing tiles:** at the start of a unit's side phase or round, it recovers
  a share of max HP on village, fort or camp tiles (starting at 100‰, 100‰ and 50‰).
- No height levels.

## Consequences

- **Positive:** the 조조전 "right unit on the right ground" play; a small table (144 cells × 2 values); no extra art.
- **Negative:** players must learn affinities; the UI shows the effect on tile hover and in the move preview.
- **Follow-ups:** the full P0 table for 6 families × 12 terrains; map format (terrain id per tile).

## Revisit when

Maps feel flat, which would argue for elevation, or affinity numbers are too hard to read.
