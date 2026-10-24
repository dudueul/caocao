# 0075. Lay out and paint each battlefield as the record describes it

- **Status:** Accepted
- **Date:** 2026-10-24
- **Deciders:** project owner (asked that each location's terrain follow the history of the time; chose both layouts and look), Claude (the method)
- **Related:** [0002](0002-remake-from-scratch.md), [0046](0046-period-accurate-dress.md),
  [0062](0062-map-shifts.md), [0063](0063-staged-scenes.md), [0074](0074-terrain-variants-that-join.md),
  [BATTLEFIELDS.md](../remake/BATTLEFIELDS.md)

## Context

The 25 story battles were laid out from the story: a river here, a walled town
there. Nobody had checked them against the record, and every map was painted
in the same landscape, whether it lay on the Yellow River plain, among the rivers
of 서주 or beyond the frontier in 요서. While the terrain painter was being
rebuilt ([ADR 0074](0074-terrain-variants-that-join.md)), the owner asked that
the terrain of each location follow the history of the time.

## Options considered

1. **Layouts and look:** chosen (recommended).
   - Check every battle against the record and redraw the maps that differ.
   - Paint each map in its region and season.
   - Rebalance the redrawn battles.
2. **Look only:** keep today's layouts and balance; paint each battle by region and season.
3. **Layouts only:** redraw to the record, in one landscape style everywhere.

## Decision

- **The record decides the layout.** Each battle is checked against:
  - the histories: 『삼국지』 with 배송지's notes, 『후한서』 and 『자치통감』;
  - 『수경주』 for rivers, ferries and city walls.

  Where a map differs from the record, it is redrawn: rivers and their banks, fords, ferries and dikes, walls and gates, passes, and the hills and woods the record names, with the camps and earthworks where the armies built them.
- **North is up** on every battle map, so rivers and passes lie as on a map of the period. Distances are compressed to fit the field, but what lies where, and on which bank, holds.
- **What the record leaves out** is filled from the region: fields, villages, woods and the lie of the land as they were around 200 AD.
- **Built terrain is Late Eastern Han**, as the dress is ([ADR 0046](0046-period-accurate-dress.md)):
  - rammed-earth walls with timber gate towers and corner towers;
  - courtyard houses, mostly thatched in villages and more often tiled in cities;
  - granaries, watchtowers and wells;
  - camps of palisades, earth ramparts and tents.
- **Each battle and outdoor stage carries a landscape:** its region and the season it was fought. The painter uses them for palette, plants and water; the rules never read them.
  - **Regions:**
    - `plain`: the Yellow River and the North China plain;
    - `huai`: the rivers and lakes of 서주;
    - `basin`: 남양;
    - `loess`: the loess country around 낙양 and 형양;
    - `taihang`: the 태항 mountains;
    - `frontier`: 요서.
  - **Seasons** follow the weather of the lunar month in the record, which runs about a month behind the solar calendar:
    - months 11–1 winter, so 남피's ice in the 1st month is winter;
    - months 2–4 spring;
    - months 5–7 summer;
    - months 8–10 autumn.
  - A stage shows the moment of its scene. Where a scene's date card names a season, the card agrees with the record.
- **The story stays.** Goals, map shifts, duels and talk keep their meaning. Redrawn battles are rebalanced to the win rates they have today.
- **[BATTLEFIELDS.md](../remake/BATTLEFIELDS.md)** records, for each battle, in our own words:
  - its date and season;
  - its region;
  - what the record says of the ground;
  - what the map shows;
  - what was filled in.
- **No game's maps are a source:** not Koei's, not anyone's. The record and the geography of the period are ([ADR 0002](0002-remake-from-scratch.md)).

## Consequences

- **Positive:**
  - The battles follow the campaigns as they happened, on the right side of the right river.
  - Each region looks like itself.
- **Negative / costs:**
  - Redrawing a map moves its units, slots, gates and shifts, and the battle must be rebalanced.
  - For some battles the record says little about the ground (수장, 안중), so more is filled from the region.
- **Follow-ups:**
  - Scene stages that show a battlefield follow its redrawn map.
  - Roads as optional paths in map data ([ADR 0074](0074-terrain-variants-that-join.md)'s follow-up).

## Revisit when

A source shows a map is wrong, or the owner wants a battle moved from history
for play.
