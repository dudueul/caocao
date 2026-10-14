# 0014. Scoped progression per mode, with a level-normalized Arena

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0008](0008-modes-as-main-menu-entries.md), [0010](0010-lineage-households.md),
  [`docs/remake/ARCHITECTURE.md` §6](../remake/ARCHITECTURE.md#6-data-and-storage)

## Context

The original mobile game shared one account roster across all modes, then
patched fairness back in per mode (`FairRule` normalized stats in PvP). Between
friends with different amounts of play time, grind deciding PvP is a fast way to lose players.

## Options considered

1. **Scoped progression + fair Arena:** chosen.
2. **One roster everywhere:** strong attachment, but grind decides PvP.
3. **Everything per season:** pure competition, little long-term collection.

## Decision

| Scope | What persists | Used in |
|---|---|---|
| **Story roster** | Officers recruited and trained: levels, equipment, promotions | Story, Raids |
| **Unlocks** | Officers and classes met in Story | Arena pool, at **normalized level and stats** |
| **World household** | Per season or campaign only; resets | Living History, Warlord Campaign |
| **World legacy** | Titles, cosmetics, lineage hall of fame, legacy points | Account-wide, shown everywhere |
| **Cosmetics** | Banners, portrait frames, unit palettes | Everywhere |

## Consequences

- **Positive:** PvP between friends is decided by skill and team building; the balance surface is small per mode.
- **Negative:** Story progress has no effect on Arena power, so Arena needs its
  own rewards (cosmetics, ladder titles) to motivate.
- **Follow-ups:** normalization rules (level, equipment tiers allowed) in the Arena design.

## Revisit when

Players feel their Story progress is worthless outside Story; a limited
carry-over could then be added, such as unlocking build options rather than power.
