# 0058. Call the game 난세의 간웅

- **Status:** Accepted
- **Date:** 2026-10-19
- **Deciders:** project owner
- **Related:** open question 2 in [docs/remake/README.md](../remake/README.md),
  [0054](0054-main-menu-settings-tips.md), [0056](0056-chapter-order-and-carry-over.md)

## Context

The project's working name was `tk`, and the title screen showed the only
chapter, 관도. Once the story starts in 174, the title needs a name for the
whole game.

## Options considered

1. **난세의 간웅 (亂世奸雄):** chosen. 허소's recorded verdict on the young Cao Cao — an able minister in peaceful times, a villain-hero in chaos. It is public-domain history, it fits a game that follows Cao Cao, and it is not any other game's title.
2. **Show the current chapter's name:** leaves the game unnamed.
3. **Keep `tk` until later:** a placeholder on the title screen.

## Decision

- The title screen, the page title and the installed app are called **난세의 간웅**, with 亂世奸雄 above it and "조조의 이야기 · 삼국 전술" below.
- The code keeps the working name `tk` in package names and paths. Renaming those has no value for players.

## Consequences

- **Positive:** the game has a name players can repeat; open question 2 is closed.
- **Negative / costs:** none in code; store listings will need the name checked for trademarks before a public launch.
- **Follow-ups:** a logo when an artist joins.

## Revisit when

The game grows beyond Cao Cao's story (other lords' routes), or a trademark check fails.
