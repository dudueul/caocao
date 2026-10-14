# 0007. Top-down square-grid 2D art, 3 drawn facings, PixiJS renderer

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0005](0005-web-first-distribution.md), [0006](0006-typescript-everywhere.md),
  [`docs/re/CLASSIC_ART.md`](https://github.com/dudueul/caocao/blob/dabaf0a2/docs/re/CLASSIC_ART.md) (1998 sheet layout used as the benchmark)

## Context

The owner draws all art. The camera and style set the renderer and, more
importantly, the art workload per unit class. The 1998 game uses a top-down
square grid with 3 drawn facings (down, up, side; side mirrored) and 28 frames
per class: walk 2×3, stand ×3, attack 4×3, guard ×3, hurt, cheer, and 2 weak frames.

## Options considered

1. **Top-down 2D grid (PixiJS):** chosen. Smallest art budget and readable on phones.
2. **Isometric 2D:** more depth, but depth sorting, height levels and harder art.
3. **2D skeletal animation (Spine-like):** smooth, but a rigging pipeline and runtime licence.
4. **3D / HD-2D (Three.js/Babylon):** largest art and tech workload; heavy web loads.

## Decision

- Square grid, slightly top-down view. Units have 3 drawn facings, with the side facing mirrored.
- Frame set per class follows the 1998 layout as a baseline: walk, stand,
  attack, guard, hurt, cheer and weak (about 28 frames).
- Renderer: **PixiJS** (WebGL/WebGPU). Sprites are packed into texture atlases
  by the build. Maps are painted backgrounds plus a terrain grid, or tile sets
  (decided per map; the engine supports both).
- Provisional resolution: logical tile 48 pt with art authored at **96 px per
  tile** (2×), matching the mobile game's grid. It will be finalized in an art-pipeline ADR.

## Consequences

- **Positive:** small runtime (about 0.5 MB), fast loads, a simple art pipeline (Aseprite or Krita, then an atlas).
- **Negative:** less spectacle than 3D; skill effects need good 2D VFX.
- **Follow-ups:** art-pipeline ADR (resolution, palette, atlas tooling);
  UI rendered as DOM overlay or in Pixi (open question).

## Revisit when

A large class count makes frame-by-frame art too expensive. Skeletal
animation can then be added for selected units without changing the renderer.
