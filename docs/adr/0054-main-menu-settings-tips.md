# 0054. Open on a title menu, with settings and first-battle tips

- **Status:** Accepted (proposed while building, confirmed by the owner 2026-10-19)
- **Date:** 2026-10-19
- **Deciders:** Claude (proposal), project owner (accepted)
- **Related:** [0008](0008-modes-as-main-menu-entries.md), [0031](0031-svelte-dom-overlay.md),
  [0033](0033-p0-sandbox-client-defaults.md), [0044](0044-modern-battlefield-presentation.md),
  [0049](0049-story-chapter-guandu.md), [0052](0052-synthesized-sound.md)

## Context

The client opened on a developer page: an Arena card and a sandbox link. A
complete game opens on a title screen, lets the player change sound and speed,
and teaches the controls in the first battle.

## Options considered

1. **A title menu, a settings panel available everywhere, tips in the first Story battle:** chosen.
2. **A separate tutorial battle before the story:** more teaching, but delays the story; the first battle is easy enough to teach in.
3. **Keep the developer page:** no.

## Decision

- **Routes:**
  - `#/` is the title menu: continue, new game, load, Arena, practice battle, watch the AI, settings, credits.
  - `#/story` is the Story, and `#/arena` is the Arena lobby.
  - `#/sandbox`, `#/demo` and `#/r/CODE` are unchanged.
- **Title art:** our own painted dawn backdrop with the chapter name 관도. The game's name stays open (question 2); the footer says "가제 tk".
- **Settings** are saved in the browser and reachable from the title and the battle bar:
  - volumes: master, music, effects;
  - battle animation speed (1×, 2×, 4×, skip);
  - danger zones on at the start;
  - lighter effects;
  - dialogue text speed;
  - tips on or off.
- **Tips:** in the chapter's first battle, one short tip at a time for what the player is doing. They cover the win and lose conditions, selecting, moving, acting, the forecast, ending the phase and danger zones, and healing tiles. Each shows once, and "팁 끄기" turns them off.
- **Battle screen in the Story:**
  - a goals card (win / lose) under the top bar;
  - ★ over commanders to defeat, ◆ over units to protect, a gold star on tiles to reach;
  - the result card waits until the battle's last lines are read.

## Consequences

- **Positive:** the game reads as a game from the first screen; new players learn in context.
- **Negative / costs:** more UI to keep in both phone and desktop layouts.
- **Follow-ups:** a help page with the rules (triangle, terrain, skills); key rebinding; the game's name.

## Revisit when

Playtesters miss something the tips don't cover, or the owner names the game.
