# 0015. Build order: rules engine, then Arena, Story, private World, public seasons

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0008](0008-modes-as-main-menu-entries.md),
  [0013](0013-one-world-engine.md), [`docs/remake/README.md` roadmap](../remake/README.md#roadmap)

## Context

One developer is building six modes ([0008](0008-modes-as-main-menu-entries.md)),
all on the same rules engine. They differ in what they need beyond it:

- **Arena** needs little content but exercises the whole online path.
- **Story** needs the event system and writing.
- **The World engine** is the largest piece and should be tuned in private
  campaigns before a public season ([0013](../adr/0013-one-world-engine.md)).

## Options considered

1. **Engine → Arena first:** chosen.
2. **Engine → Story first:** a polished solo game, but online play comes late.
3. **Engine → World first:** the differentiator first, at the highest early risk.
4. **Story and Arena in parallel:** both grow slowly, with context switching.

## Decision

| Phase | Scope | Exit criterion |
|---|---|---|
| **P0** | Rules engine, AI, local battle sandbox (PixiJS) | 6+ classes, terrain, 10+ skills, conditions; golden replays green; AI-vs-AI soak in CI |
| **P1** | Arena: invite rooms, `MatchDO` referee, alternating activations, timers, reconnect, PWA | Friends play PvP weekly |
| **P2** | Story chapter 1: event VM, dialogue, `PlayerDO` re-simulation, Story roster | 5–8 scripted battles playable end to end |
| **P3** | Warlord Campaign: `WorldDO`, city map, households, variable clock, skirmishes, about 10 anchors, lineage basics | One private campaign completed with friends |
| **P4** | Living History season 1 (public): full anchor set, calendar, recap, archive | A full 120-day season runs |
| **P5** | Raids (`BossDO`), Duels/Board | Each mode playable |

## Consequences

- **Positive:** friends play real PvP after P1; the riskiest technical part,
  the referee, is proven early; the World engine is built on a battle system that's already tested.
- **Negative:** a solo campaign arrives only in P2, and P1 needs a small set of
  officers and classes before any story exists.

## Revisit when

A phase overruns badly (more than double its own estimate once one is set), or
friends' feedback after P1 changes priorities.
