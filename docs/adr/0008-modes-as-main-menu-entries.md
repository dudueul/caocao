# 0008. Every mode is its own main-menu entry on one battle engine

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0009](0009-living-history-anchored-history.md), [0013](0013-one-world-engine.md),
  [0014](0014-scoped-progression-fair-arena.md), [`docs/remake/README.md`](../remake/README.md)

## Context

Asked which modes belong in v1, the owner wanted **everything the Three Kingdoms
setting allows**, informed by the market: grid-tactics story games (Koei's
licensed *新三國志曹操傳*, 2025), grand strategy (*RTK 8 Remake*,
*Total War: Three Kingdoms*, *Heroes' Vow*), seasonal territory SLGs
(*三國志·戰略版*, *率土之濱*), abstract board play (*Three Kingdoms HEROES*) and
card duels (*三国杀*). The original mobile game had 22 battle modes in 5 server patterns.

## Options considered

1. **One home loop wrapping all modes** (for example a shared warlord campaign).
2. **Separate main-menu entries:** chosen; each mode stands on its own.

## Decision

The main menu offers separate modes, all built on the same battle engine:

| Entry | What it is | Server pieces |
|---|---|---|
| **Story** | 조조전-style chapters with scripted events | `PlayerDO` (progress, seeds, reward checks) |
| **Living History** | Public seasons: create an officer and household in a living 120-year history ([0009](0009-living-history-anchored-history.md)) | `WorldDO` per season + `MatchDO` per battle |
| **Warlord Campaign** | Private, invite-only worlds on the same engine ([0013](0013-one-world-engine.md)) | `WorldDO` per campaign |
| **Arena** | Live PvP rooms and an async ladder against stored defense teams | `LobbyDO`, `MatchDO` |
| **Raids** | Co-op boss battles with shared boss HP | `BossDO` |
| **Duels / Board** | Quick mini-modes (one-on-one duels, debates, a board variant) | `MatchDO` |

## Consequences

- **Positive:** each mode can ship on its own schedule; players pick their
  commitment level (10-minute duel up to a 120-day season).
- **Negative:** a wide surface for one developer. Delivery order is a separate
  decision (roadmap, open). Each mode needs its own UI flow.

## Revisit when

Playtests show a mode nobody plays. Then fold it into another mode or drop it.
