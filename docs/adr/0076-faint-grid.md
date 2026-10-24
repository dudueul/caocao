# 0076. Keep the tile grid faint over the painted terrain

- **Status:** Accepted
- **Date:** 2026-10-24
- **Deciders:** project owner (chose to keep the grid faint), Claude (proposed it while repainting the terrain)
- **Related:** [0007](0007-top-down-2d-pixijs.md), [0044](0044-modern-battlefield-presentation.md),
  [0074](0074-terrain-variants-that-join.md)

## Context

The battlefield draws thin lines between tiles so players can count moves and
ranges. The repainted terrain ([ADR 0074](0074-terrain-variants-that-join.md))
paints each map as one landscape. At the old strength (black at 9 % opacity) the
lines cut that landscape back into squares, which the repaint was meant to
undo. The reach, target and danger overlays already show every tile that
matters for an order.

## Options considered

1. **Keep the lines faint** (black at 5 %): chosen (recommended). The grid is still there for counting, but it no longer reads as squares.
2. **As strong as before:** easier to count tiles at a glance; more of a grid look.
3. **Faint by default, with a setting** for a stronger grid: more to build and test for a small gain.

## Decision

The tile grid is drawn in black at 5 % opacity over our painted terrain and
the classic skin alike. The move, reach, target and danger overlays are unchanged.

## Consequences

- **Positive:** the painted landscape reads as one picture.
- **Negative / costs:** counting tiles on open ground relies more on the overlays.

## Revisit when

Players miscount moves or ranges on open ground, or ask for a stronger grid.
Then add the setting (option 3).
