# 0064. Make the camp a place to walk: 조조 visits the shop, the armoury and his officers

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (chose staged scenes and a walkable camp), Claude (layout and details)
- **Related:** [0050](0050-equipment-and-shop.md), [0051](0051-browser-saves.md),
  [0063](0063-staged-scenes.md), [STORY.md](../remake/STORY.md)

## Context

The camp between battles was a tabbed menu: roster and gear, shop, saving,
and a button to march. With scenes now staged on tile maps (ADR 0063), the
owner chose to make the camp a map as well, as in the original games, where
the commander walks around the camp and talks to his officers.

## Options considered

1. **A walkable camp:** chosen, together with staged scenes.
2. **Keep the menu camp.**

## Decision

- **The ground:** each camp has a `ground`, with these parts:
  - a stage map;
  - 조조's starting tile;
  - four **stations**: `shop` (상점), `armoury` (무기고: gear and promotion), `records` (기록: saving) and `council` (군막: march on);
  - **spots** where the roster's officers loiter.
- **Moving:** 조조 walks with the arrow keys or WASD, or by tapping a tile.
- **Stations:** stepping onto one, or tapping it, opens that station's panel. The panels are the old camp menu's tabs, unchanged in what they do.
- **Talk:**
  - An officer can be talked to when 조조 walks into them, presses the action key, or taps them.
  - Lines come from the camp's own `talk` (with flag conditions), else from that officer's usual camp lines, else a generic line.
- **Shortcuts:** a 출진 준비 button and the gold display stay on screen.

## Consequences

- **Positive:**
  - Downtime between battles becomes part of the story: officers react to what just happened.
  - The menus are kept, so nothing is lost.
- **Negative / costs:**
  - A few more steps to reach the shop than a tab.
  - Camp talk to write for each camp.
- **Follow-ups:**
  - Optional events in camp: a visitor, a duel to watch.
  - Remember the last station used.

## Revisit when

Playtesters skip the walking and only use the 출진 준비 button.
