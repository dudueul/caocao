# 0074. Paint terrain tiles in many variants that join with their neighbours

- **Status:** Accepted
- **Date:** 2026-10-24
- **Deciders:** project owner (asked for terrain to the standard of the existing games; chose tiles with many more variations that still look right in any combination; noted that towns repeat too many houses), Claude (the method)
- **Related:** partly supersedes [0044](0044-modern-battlefield-presentation.md) (its terrain painting); [0002](0002-remake-from-scratch.md),
  [0042](0042-local-classic-skin.md), [0063](0063-staged-scenes.md), [0073](0073-painted-sprites.md)

## Context

The owner compared our terrain with the 1998 game's maps and asked for terrain
to that standard: upscaled, redrawn or smoothed from the existing games.

The 1998 maps are Koei Tecmo's art. They can't ship, even upscaled
([ADR 0002](0002-remake-from-scratch.md)); the owner plays with them locally
through the classic skin ([ADR 0042](0042-local-classic-skin.md)). A private
comparison showed what sets them apart:
- forests are masses with ragged outer edges;
- hills are shaded mounds;
- grass fades into dirt;
- colours are muted;
- a town is a few buildings, not one per tile.

Our painter ([ADR 0044](0044-modern-battlefield-presentation.md)) gave each
terrain four 64 px variants, laid each tile down on its own, and feathered the
edges. Forests showed as square blocks, and every village tile painted the
same cluster of houses, so a town became dozens of identical roofs.

## Options considered

1. **One painted landscape per map:** recommended, not chosen.
2. **Tiles with many more variants that combine well:** chosen. In the owner's words, "tiles having much more variations so combine them still look okay".
3. The classic maps for the owner's own play only: already possible locally.

## Decision

- **Many variants:**
  - Each natural terrain (plain, grass, forest, hills, mountain, ford, water) is painted by our own procedural painter from noise laid over the whole map, so no two tiles are alike and no pattern repeats in sight.
  - Trees, tufts, stones, reeds and crags come from a library of variants, changing with the season.
  - Painted at about twice display resolution, in a muted palette nearer the period landscape.
  - Hills, forests and walls must still read at a glance, at phone size and in every season, because they change movement and defence.
- **Tiles that join:** a tile's look depends on its neighbours (an autotile mask of the 8 around it).
  - Forest tiles knit into one canopy, with ragged crowns only on the mass's outer edge.
  - Hills and mountains shade as continuous mounds and ridges across tiles.
  - Water and fords get banks and shallows where they meet land.
  - Grass and plain fade into each other in patches, not squares.
- **Built terrain:**
  - Walls join at runs, corners and ends, with towers at corners.
  - Bridges span their water.
  - Camps vary their tents and palisades.
  - Towns are composed as a whole: houses, lanes, yards, gardens and one landmark (a well, a watchtower, a granary), with a cap on houses per town, so a walled town reads as one place.
    - An open town gets about one house per three or four village tiles, the rest fields, orchards and threshing yards.
    - Castle grounds (village tiles enclosed by walls) are mostly paved streets and courtyards, with about one compound per six or seven tiles, the keep and one landmark. The owner found a house on every tile cluttered, as in the original.
- **Scene stages** ([ADR 0063](0063-staged-scenes.md)) use the same painter for their terrain.
- **The 1998 maps** may be looked at privately, only to judge composition, density and palette; no pixel, palette or shape of theirs is copied.

## Consequences

- **Positive:**
  - Maps read as landscapes without changing the map format or the rules.
  - Towns stop repeating.
- **Negative / costs:**
  - A bigger painter, and more texture memory: about 12 MB and 0.25–0.3 s to paint a 20 × 14 map at 96 px a tile (measured in headless Chromium with a software canvas).
  - Neighbour-aware tiles must repaint when a map shift changes terrain (the 하비 flood, the 업 trench, the 남피 ice).
- **Follow-ups:**
  - Roads as optional paths in map data, if the owner wants them later.

## Revisit when

Players still see a grid in open ground, or texture memory becomes a problem on
phones.
