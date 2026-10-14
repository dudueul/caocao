# 0010. Death passes control to an heir (households and lineage)

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0009](0009-living-history-anchored-history.md), [0012](0012-household-corps-seats.md),
  [0014](0014-scoped-progression-fair-arena.md), [`docs/remake/WORLD_ENGINE.md` §4](../remake/WORLD_ENGINE.md#4-households-and-lineage)

## Context

A season covers 120 years and lifespans are about 50–75, so **every player
character dies at least once per season**, in battle, a duel, by execution,
illness or old age. The death rule decides whether a season is one life or a
family saga, and whether an early death ends someone's season.

## Options considered

1. **Lineage / household:** chosen.
2. **New character on each death:** simple; no attachment between lives.
3. **One life per season:** high stakes; dying on day 10 means waiting about 110 days.
4. **Rule set rotating per season:** variety at the cost of triple the balancing.

## Decision

The player's unit of play in the World engine is a **household**:

- The **founder** is a created officer born 145–160 (exact range by playtest).
- **Succession:** on death, control passes to an heir: a child, an adopted son
  or a sworn disciple. The heir inherits a share of fame, wealth, loyal
  retainers and one learned skill. Starting tuning: 50% fame, one skill.
- **Marriage and adoption** (with NPC households or other players' households)
  and **sworn brotherhood** secure succession and create alliances.
- **Extinction:** with no heir, the player founds a new household at the current
  year, as a youth, with legacy points.
- **Season score:** household fame, titles held, and historical feats. The
  household and its members are season-scoped ([0014](0014-scoped-progression-fair-arena.md)).

## Consequences

- **Positive:** death is meaningful but never ends a player's season;
  marriage and succession become strategy (Crusader Kings / RTK13 style).
- **Negative:** systems for ageing, births, marriage, inheritance and
  succession UI. NPC households need the same rules to feel consistent.
- **Follow-ups:** age/time tables (birth to adult takes about 16 game years, or
  16 real days), inheritance tuning, how many heirs a player may keep.

## Revisit when

Players find succession too punishing (abandoning after deaths) or too soft (no fear of death).
