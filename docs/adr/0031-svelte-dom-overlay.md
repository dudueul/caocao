# 0031. The client UI is a Svelte 5 DOM overlay on the PixiJS battlefield

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0005](0005-web-first-distribution.md), [0006](0006-typescript-everywhere.md),
  [0007](0007-top-down-2d-pixijs.md), [0028](0028-undo-moves-until-action.md),
  [`docs/remake/ARCHITECTURE.md` §7](../remake/ARCHITECTURE.md#7-client)

## Context

The battlefield renders in PixiJS ([0007](0007-top-down-2d-pixijs.md)). Everything
around it needs a UI layer: unit cards, the attack forecast, the danger
toggle, the gambit editor, menus, chat and dialogue. The game is web-first on
phones ([0005](0005-web-first-distribution.md)), built solo in TypeScript, and
needs Korean text input (IME) and screen-reader support where possible.

## Options considered

1. **Svelte 5 DOM overlay:** chosen.
2. **Preact + signals:** React-style, about 4 KB; best for React habits.
3. **SolidJS:** fine-grained JSX; smaller ecosystem.
4. **UI inside the Pixi canvas:** one technology, but weak text input, accessibility and layout.

## Decision

- `apps/client` is a Vite app. A Pixi canvas fills the screen; Svelte 5 (runes)
  components sit in a DOM layer above it.
- One battle store holds the state and the event queue from `@tk/rules`. Pixi
  animates the events, and Svelte renders panels from the same store.
- Versions at decision time: Svelte 5.57, `@sveltejs/vite-plugin-svelte` 7.3,
  Vite 8.3, PixiJS 8.22. Type-checking `.svelte` files uses `svelte-check`.

## Consequences

- **Positive:** native IME, text, scrolling and accessibility; small bundles;
  little code per screen.
- **Negative:** two rendering layers to keep in sync (one store solves it);
  `svelte-check` joins `tsc` in the check pipeline.

## Revisit when

UI performance on low-end phones suffers from DOM updates during animation;
then move the hot panels into Pixi.
