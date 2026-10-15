# 0044. Give the battlefield a modern presentation: lighting, effects and cinematics

- **Status:** Accepted (proposed while building, confirmed by the owner 2026-10-15)
- **Date:** 2026-10-15
- **Deciders:** Claude (proposal), project owner (accepted)
- **Related:** [0007](0007-top-down-2d-pixijs.md), [0031](0031-svelte-dom-overlay.md),
  [0042](0042-local-classic-skin.md), [0043](0043-floating-command-menu.md)

## Context

The owner asked for graphics that look more natural, and for characters and
images that feel like a 2026 mobile game. Two things stood in the way. First, the
field was flat: hard-edged tiles, still water, no light, and strikes that were a
nudge and a number. Second, we have no modern character art. The classic skin is
48 px pixel art from 1998, and our own build had coloured discs. New
high-resolution characters (2D rigs or 3D models) need an art pipeline, which is
still open question 9. Code alone cannot paint them.

What code can do is the "HD-2D" treatment that recent tactics games use. Pixel or
simple sprites sit in a modern frame: soft light, depth, particles, camera work
and polished UI. That treatment also improves whatever art comes later.

## Options considered

1. **Modern presentation around the art we have:** chosen. Lighting, colour
   grading, soft terrain edges, weather, particles, a cinematic camera, skill
   cut-ins and a restyled UI. It works with both the classic skin and our own art,
   and needs no new assets.
2. **New high-resolution character art now:** the real step to a 2026 look, but
   it needs an artist or a licensed generation pipeline and a style decision.
   That is open question 9, not something to settle inside a code change.
3. **Move to 3D (models on an isometric board):** the most "mobile" option, but
   it reverses [0007](0007-top-down-2d-pixijs.md) and still needs the same art.

## Decision

- **Terrain:**
  - Natural terrains are drawn twice: once hard, then as feathered "splats" 1.5×
    the tile with a noisy edge, layered by height. Grass spills over riverbanks
    and forest edges fade into plain. Seams between tile variants soften too.
    Built structures stay crisp.
  - Water tiles shimmer through a scrolling displacement map. This works on the
    1998 map pictures too, where it is masked to the water tiles.
  - Without the skin, the terrain is **our own procedural painting**: canvas
    textures for each terrain, made at start-up and blended the same way.
- **Light and weather** (purely visual; the rules own the weather):
  - The colour grade follows the weather: warm in sun, cool and dim in rain.
  - The screen edges get a vignette.
  - Sun brings light shafts and drifting motes, clouds bring cloud shadows, rain
    brings rain streaks, splash rings and the odd lightning flash, and wind blows
    leaves across the map.
  - Everything is clipped to the map.
- **Units:**
  - Each unit has a soft contact shadow.
  - Skinned figures stand on a team ring. Without the skin, units are our own
    glossy medallion tokens with a metal rim.
  - Health bars are rounded and leave a pale "drain" segment that shrinks after
    a hit.
  - A gold chevron bobs over the selected unit.
- **Combat:**
  - The camera eases in on the two fighters, then back out.
  - Hits throw a slash arc, sparks and a glow, with a recoil and a small screen
    shake. Critical hits add a white flash and a short hit-stop.
  - Misses kick up dust. Steps leave dust puffs. A defeated unit flickers and
    goes up in smoke and embers.
  - Damage numbers pop in and rise.
  - Each stratagem school has its own particles: fire flames and smoke, water
    splash and ripples, falling rocks and dust, wind spirals and leaves, healing
    glints, and purple smoke for disruption.
  - Conditions land with a rising (buff) or sinking (debuff) aura. Burning tiles
    flicker with flames. The winners sparkle at the end.
  - Bloom applies to the particles only.
- **Skill cut-ins:** before a stratagem goes off, a slanted band crosses the
  screen with the caster's portrait (or a crest in the school's colour without
  the skin), their name and the skill's name. The field's clock drives it, so it
  keeps pace with the battle speed. It is skipped at 4× and 생략.
- **UI:** glass panels (backdrop blur) with gold hairlines, gradient buttons with
  a gold primary, a serif display face for names, banners and results, ribbon
  turn banners, a glowing result card, a framed portrait in the unit card, and
  gradient bars.
- **Classic sprites are smoothed at export:** Scale2x twice (4×) on the palette
  indices, then a 2×2 average down to 2×. This keeps the 1998 shapes but rounds
  their stair-steps with anti-aliasing. `--raw` keeps the original pixels.
- **Comfort and cost:**
  - `?lowfx` drops bloom, water shimmer and some ambience.
  - `prefers-reduced-motion` also turns off camera moves, shake and flashes.
  - The still terrain layers are cached as one texture each.
  - The effects come from `pixi-filters` 6.1.5 (MIT), which adds about 16 KB
    gzipped to the client.
- **Captures:** with `?debug`, `window.tkField.manualClock(true)` and `step(ms)`
  advance the field's clock by hand. A slow, software-rendered browser can then
  record smooth video, one frame per screenshot.

## Consequences

- **Positive:**
  - Battles read better: who hit whom, how hard, and what a skill did.
  - The map looks like a place rather than a grid.
  - Friends on the deployed build (no skin) get our own textured terrain and
    tokens instead of flat colours.
- **Negative / costs:**
  - The full-screen colour grade costs one extra render pass. Very old phones
    may need `?lowfx`; automatic detection is a follow-up.
  - More code in the client: about 1,500 lines of effects.
  - The characters themselves are still 1998 sprites or our tokens. That is the
    honest limit of this change.
- **Follow-ups:**
  - Decide the character art direction (open question 9): high-resolution 2D
    rigs, Live2D-style portraits or 3D models, and who makes them.
  - The renderer already takes sheets at any scale (`scale` in the skin
    manifest) and portraits at any size.

## Revisit when

The owner picks a character art direction, or playtests on low-end phones show
frame drops with the full effects.
