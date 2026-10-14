# 0033. Battle sandbox client defaults chosen while building P0

- **Status:** Proposed (defaults chosen while building P0; the owner is asked to confirm)
- **Date:** 2026-10-14
- **Deciders:** Claude (proposal)
- **Related:** [0005](0005-web-first-distribution.md), [0007](0007-top-down-2d-pixijs.md),
  [0019](0019-bun-toolchain.md), [0028](0028-undo-moves-until-action.md),
  [0030](0030-modern-techniques-adopted.md), [0031](0031-svelte-dom-overlay.md),
  [`docs/remake/ARCHITECTURE.md` §7](../remake/ARCHITECTURE.md#7-client)

## Context

[ADR 0031](0031-svelte-dom-overlay.md) set the client stack: a Pixi canvas with
a Svelte 5 overlay and one battle store. Building the P0 sandbox needed answers
on layout, input, placeholder art, language and tooling. Each choice below keeps
the sandbox playable on a phone and a desktop; all can change.

## Decision

| # | Default | Why |
|---|---|---|
| 1 | **Layout:** the canvas fills the screen. A top bar holds round, side, weather and the main buttons. A side panel (340 px) on screens 900 px or wider, otherwise a bottom panel of fixed height (36% of the screen). | The map is fitted once and never moves when panels change, so a tile doesn't slide from under a finger. |
| 2 | **Input:** tap a unit to show where it can move (blue) and strike (red). Tap a tile to move there; the move stays pending and can be changed or undone ([0028](0028-undo-moves-until-action.md)). Then pick *attack*, a stratagem, or *wait*. A lone target is pre-selected; the forecast shows; tapping the target again or *confirm* commits. Esc steps back, D toggles the danger zone, E ends the phase. | Two taps to commit, with the forecast in between, works the same with a mouse and with touch. |
| 3 | **Options come only from `legalCommands()`;** the client never assembles a command the rules haven't listed. | The client can't send an illegal command; the referee's job stays simple ([0004](0004-server-authoritative-referee.md)). |
| 4 | **Inspecting** an enemy, or a unit that already acted, shows its move and strike range. | The classic way to read threats, next to the danger zone ([0030](0030-modern-techniques-adopted.md)). |
| 5 | **Placeholder art:** terrain colours with simple marks; units are side-coloured discs showing the first syllable of the class (보 궁 기 책 무 포), ranged classes with an inner ring, HP bars, `?` for confusion and `!` for broken. | Readable until the owner's art exists ([0007](0007-top-down-2d-pixijs.md)); no assets to load. |
| 6 | **Korean-first UI text,** with every string in one module (`src/lib/text.ts`). | Follows the current leaning on languages (open question 10); a second language is one more table. |
| 7 | **Speed** cycles 1×, 2×, 4× and *skip* (no animation). **Auto-battle** hands the player's side to the AI. Turn and twist banners; a battle log. | The pacing controls of [MODERN_TECHNIQUES.md](../remake/MODERN_TECHNIQUES.md). |
| 8 | **Tooling:** `svelte-check` type-checks the client with TypeScript 6, installed only in `apps/client` (svelte-check doesn't support TypeScript 7 alone yet). Biome lints and formats `.svelte` files with its experimental full HTML support. Both run in `bun run check`. | Keeps one `check` command and catches type errors in markup; TypeScript 7 stays for the rest of the repo ([0019](0019-bun-toolchain.md)). |
| 9 | **Debug hooks:** `?debug` exposes the store as `window.tk`; the canvas carries its tile geometry in `data-geometry`. | Lets headless browser tests drive the real UI. |

## Consequences

- **Positive:** a playable sandbox on desktop and phone; tests drive the real UI in headless Chromium.
- **Negative / costs:** tiles are small on a phone in portrait (23 px on a 390 px screen); a
  TypeScript 6 copy lives in the client until svelte-check supports TypeScript 7.
- **Follow-ups:** pinch-zoom and pan on phones; PWA manifest and service worker (P1);
  swap placeholders for atlases when art exists.

## Revisit when

Playtests show the tap flow is slow or error-prone, phones need zoom, or svelte-check supports TypeScript 7.
