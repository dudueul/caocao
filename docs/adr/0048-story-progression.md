# 0048. Story officers grow by experience, steady stat growth and promotion

- **Status:** Proposed
- **Date:** 2026-10-19
- **Deciders:** Claude (default chosen while building; the owner to confirm)
- **Related:** [0014](0014-scoped-progression-fair-arena.md), [0020](0020-five-stats-with-distinct-roles.md),
  [0021](0021-class-families-and-tiers.md), [0022](0022-medium-number-scale.md),
  [0049](0049-story-chapter-guandu.md)

## Context

The Story needs the 조조전 feel of officers getting stronger battle after
battle. ADR 0014 scopes this progression to the Story roster (the Arena stays
normalized), and ADR 0021 promotes classes at Lv 20 and Lv 40. Nothing yet said
how experience is earned, what a level adds, or which tier-2 classes exist.

## Options considered

1. **Experience from battle events; steady class growth; promotion at Lv 20:** chosen.
2. **Random stat rolls per level (Fire Emblem style):** exciting, but adds a
   second random source and save-scumming.
3. **Level-scaled class ATK/DEF in the damage formula:** closer to the 1998
   game, but changes every battle's numbers, the Arena included.

## Decision

- **Experience** is read from each command's events, so a replay earns the same amounts.
  - A landed strike earns 13 ± 2 per level of difference (4–24).
  - Defeating an enemy earns 32 ± 3 per level (10–50), plus 30 for a commander a goal names.
  - A stratagem that harms or hinders an enemy earns 12 ± 2 per level per target; helping an ally earns 8 + heal / 10.
  - One cast earns at most 30.
  - Officers still standing at a victory get 30 more.
  - A loss earns nothing; the battle is simply retried.
- **Levels:** 100 experience a level, up to Lv 50. HP grows through the existing `hpPerLevel`.
- **Growth:** each class has a rate per stat in ‰. Reaching level L adds
  ⌊L·r/1000⌋ − ⌊(L−1)·r/1000⌋ to the stat. This is steady and needs no dice. Stats cap at 100.
- **Promotion:** from Lv 20 an officer may promote in camp to the tier-2 class of the same family:
  - 중보병 (heavy infantry)
  - 강궁병 (strong-bow archers)
  - 돌기병 (shock cavalry)
  - 군사 (tactician)
  - 호위무사 (bodyguard)
  - 벽력거 (traction trebuchet)

  Each tier-2 class has sturdier numbers and one more skill. Promoted figures get gilded trim.
- All numbers are starting tuning in `packages/campaign/src/tuning.ts` and `GROWTH` in content.

## Consequences

- **Positive:** battles stay deterministic; an AI play-through of the chapter
  brings Cao Cao and the front line to promotion around the third or fourth battle.
- **Negative / costs:** benched officers fall behind, as in the original; tier-2
  classes add six rows to balance.
- **Follow-ups:** tier 3 at Lv 40 when a second chapter exists; Arena keeps tier 1 at Lv 20.

## Revisit when

Playtests show levelling too slow or too fast, or strategists falling far behind fighters.
