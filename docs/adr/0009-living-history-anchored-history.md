# 0009. Living History: anchored history with what-ifs, 120 years in 120 days, 3 seasons a year

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0010](0010-lineage-households.md), [0011](0011-world-pacing-and-auto-join.md),
  [0013](0013-one-world-engine.md), [`docs/remake/WORLD_ENGINE.md`](../remake/WORLD_ENGINE.md)

## Context

The owner's concept for the seasonal mode: a player is a **fabricated person**
from the late Han; wars and events unfold as in history; fictional characters
can die; a season can end early; about 100 years are compressed into 100 days.
Seasonal SLGs need thousands of players to fill a map. Here the roughly 500
historical officers are AI-run NPCs, so a world with a handful of friends still feels alive.

## Options considered (how much players can change history)

1. **Fixed history (rails):** easy to author; players can't change outcomes.
2. **Anchored history with what-ifs:** chosen.
3. **Sandbox from 184:** history drifts fast, and snowballing ends seasons early.
4. **Weighted history pressure:** emergent but swingy and hard to tune.

## Decision

- **Span:** 161–280 AD, 120 inclusive years, at **1 real day = 1 game year**,
  so day *d* is the year 160 + *d*. Day 120 is 280, when Wu falls and Jin
  unifies China. The 23-year prelude before the Yellow Turbans (day 24 = 184)
  lets created characters build a career first. (An earlier note said
  160–280; this fixes the span to exactly 120 years.)
- **Clock:** one game month per 2 real hours (12 ticks a day), in the season's
  time zone (KST by default).
- **Calendar:** 3 seasons per calendar year, 120 days each, with break days between them:

  | Season | Normal year | Leap year | Break after |
  |---|---|---|---|
  | S1 | Jan 1 – Apr 30 | Jan 1 – Apr 29 | 1 day (2 in a leap year) |
  | S2 | May 2 – Aug 29 | same | 1 day |
  | S3 | Aug 31 – Dec 28 | same | 3 days (Dec 29–31, year-end recap) |

- **History model:** historical lords and officers are NPCs following a timeline of
  **anchor events** (Yellow Turbans 184, Dong Zhuo 189, Guandu 200, Red Cliffs
  208 …). On its day an anchor checks its **preconditions**: if they hold it
  fires historically; if not it runs an authored **what-if variant** or is
  skipped. Battles inside anchors are fought by players and NPCs, and the
  outcome is the result of those battles, not a script.
- **Season end:** day 120, or **early** when a configurable end condition
  hits, such as one faction holding ≥ N% of cities (unification) or a
  collapse rule. The end conditions are season configuration.

## Consequences

- **Positive:** "history goes on" while players' actions count. Every season
  replays the whole era. A fixed calendar makes seasons easy to plan for.
- **Negative:** content authoring: about 40 anchors × (historical version +
  1–2 variants) + ambient events. The NPC AI must drive believable lords.
- **Follow-ups:** anchor list and precondition language
  ([`WORLD_ENGINE.md`](../remake/WORLD_ENGINE.md)); end-condition thresholds by playtest.

## Revisit when

Players consistently break history in the first week of every season (a sign that
anchors are too fragile), or variants pile up beyond what can be authored.
