# 0042. A local-only classic skin from the owner's own copy of the 1998 game

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner (asked for the old game's art in the demo), Claude (mechanism)
- **Related:** [0002](0002-remake-from-scratch.md), [0007](0007-top-down-2d-pixijs.md),
  [0017](0017-clear-this-repository.md), [0033](0033-p0-sandbox-client-defaults.md),
  archive [`docs/re/CLASSIC_ART.md`](https://github.com/dudueul/caocao/blob/dabaf0a2/docs/re/CLASSIC_ART.md)

## Context

The owner wants the demo to show real characters, actions and terrain instead of
placeholder shapes until our own art exists. The only old art that survives is
the 1998 PC 조조전's (the 2018 game's CDN bundles are gone). It belongs to Koei
Tecmo, and [0002](0002-remake-from-scratch.md) keeps Koei and Nexon material out
of this tree. The archive already solved this once: each developer exported the
art from their own copy of the game into a git-ignored folder and never published
builds containing it.

## Options considered

1. **Local-only skin from the owner's own copy:** chosen. Same rule as the archive.
2. **Commit the art for the demo:** fastest to share, but breaks [0002](0002-remake-from-scratch.md) and redistributes Koei's art.
3. **Better placeholder art of our own now:** no rights question; far from "real" until the art pipeline (open question 9).

## Decision

- `bun run classic:export "<game folder>"` reads the owner's own game files
  (read only, nothing executed) and writes PNG and JSON into
  `apps/client/classic/`, which git ignores. The exporter is our code: a
  TypeScript port of the archive's `export_art.py`.
- The client uses the skin **only when that folder exists**, and only through
  the dev server (`bun run dev`). `vite build`, the Worker deploy and published
  pages never include it; without it the client keeps our placeholder shapes.
- With the skin, units use the 1998 sprite sets by class and side colour (walk,
  attack, guard, hurt, cheer, three facings), a few officers get their own sets,
  the UI shows portraits matched by officer name, and terrain is drawn from tiles
  sampled out of the 1998 battle maps. The sandbox can also fight on a 1998 map.
- Nothing from the game is committed: the class → sprite-set table is our own
  index list, and officer portraits are matched by name at export time.
- Our own art replaces all of it ([0007](0007-top-down-2d-pixijs.md)); the skin
  then goes away.

## Consequences

- **Positive:** the owner sees units walk, strike and fall on real terrain today,
  with no rights risk to the repository or to friends' builds.
- **Negative:** only machines with the game see the skin; Arena friends playing
  the deployed build see placeholders.

## Revisit when

Our own art pipeline starts, or the owner wants the skin in a shared build (that
needs its own decision and the rights holder's permission).
