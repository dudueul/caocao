# 0013. Living History and Warlord Campaign share one World engine

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0008](0008-modes-as-main-menu-entries.md), [0009](0009-living-history-anchored-history.md),
  [`docs/remake/WORLD_ENGINE.md`](../remake/WORLD_ENGINE.md)

## Context

Both the friends' warlord campaign and the public Living History seasons need
the same machinery: a strategic map, households, NPC lords, historical events, a
world clock, and grid battles when armies meet. That is the hardest system in the game.

## Options considered

1. **One World engine with presets:** chosen.
2. **Two separate designs:** a simpler RTK-style lord game plus the role-play world; double the work.
3. **Drop the warlord campaign:** friends would only play public seasons together.

## Decision

One World engine (`WorldDO` per world) configured by a **preset**:

| Setting | Living History (public season) | Warlord Campaign (private world) |
|---|---|---|
| Who joins | Anyone, at season start or later | Invite only |
| Calendar | Fixed: 3 seasons a year ([0009](0009-living-history-anchored-history.md)) | Starts when the host starts it |
| Pace | 1 day = 1 year, monthly tick per 2 h | Configurable: same, slower, or session-based (clock runs only during sessions) |
| Start | 161 | Choose a scenario start (161, 184, 190, 200, 208 …) |
| Role | Created officer and household | Created household, or play as a historical lord |
| End | Day 120 or early end condition | Host-configured |

The main menu still shows them as separate entries ([0008](0008-modes-as-main-menu-entries.md)).

## Consequences

- **Positive:** about half the strategic-layer work. Rules tuned in private games carry over to public seasons.
- **Negative:** the engine must support pausing and variable clock speeds from day one.

## Revisit when

The two modes need rules different enough that presets turn into a fork.
