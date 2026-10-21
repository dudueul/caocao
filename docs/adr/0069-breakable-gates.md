# 0069. Breakable gates: works that block a tile until they are broken

- **Status:** Accepted
- **Date:** 2026-10-21
- **Deciders:** project owner (chose breakable gates for the siege of 업), Claude (the rules model and tuning)
- **Related:** [0029](0029-benchmark-lessons-adopted.md), [0062](0062-map-shifts.md),
  [0066](0066-allies.md), [0067](0067-chapter-four-hebei.md),
  [RULES.md](../remake/RULES.md)

## Context

Until now, sieges had no gates to break. 관도's camp gates and 하비's city
gates were open tiles in a wall. The owner was offered three ways to play
the siege of 업 (204):
- **breakable gates**;
- the 하비 flood tools only;
- a long hold against the relief army.

The owner chose breakable gates. Gates and breaches are objects with HP that
block the way until broken, and stone-throwers hit them hardest. Later
sieges can reuse them.

## Options considered

1. **How to model a gate:**
   - **A unit of a new family, `structure`:** chosen. It has HP and a defence, sits on a side, and never acts. Damage, previews, kill events, the side panel and the hash all work as they are.
   - A tile with HP: a new kind of tile state, plus its own attack command, previews and events.
   - A map shift on a trigger: no battering, only "reach the gate".
2. **Whose side a gate is on:**
   - **The defenders':** chosen. Their units pass through their own gate's tile as it is their team's, but cannot stop on it, since it is occupied.
   - Neutral: every rule would need a third kind of unit.

## Decision

- **Classes:**
  - A class of family `structure` makes a **work**.
  - `createBattle` marks such units `structure: true`.
  - Content has one: **성문** (gate): HP 240 plus the troop's 통솔 (60) and 4 a level (380 at Lv 20), ATK 0, DEF 110, move 0, no attack range.
- **Rules for works:**
  - A work never gets a turn: it is not in `readyUnits`, and its side's phase skips it.
  - It does not count for victory. A side left with only works is routed, and a team is alive only while its leader has living non-work units.
  - It exerts no zone of control.
  - It takes no conditions.
  - It is never pushed or swept by a map shift.
  - It is not healed by terrain.
  - It never counters, as it has no range.
  - It holds its tile. Enemies cannot pass; its own team can pass through.
  - When its HP reaches 0 it is broken, and the tile opens like any other.
- **Damage against works** comes from the family × family relation matrix ([ADR 0029](0029-benchmark-lessons-adopted.md)), with a new `structure` column:

  | Attacker | ‰ |
  |---|---|
  | catapult | 2500 |
  | infantry, martial artist | 1000 |
  | cavalry, strategist | 600 |
  | archer | 400 |

  Gates also stand firmer on fort and wall tiles (+20%).
- **AI:** works are targets like any enemy unit, so the AI batters a gate that stands in its way.
- **Battle state version 6.** The `structure` mark is part of a unit, so old states and replays do not change meaning; the version marks that units can carry it.
- **Client:**
  - A work is drawn as a gate in its side's colour, with no chibi figure.
  - The unit card shows 門.
  - Breaking one shows "파괴".
  - The sortie map pins gates.

## Consequences

- **Positive:**
  - Sieges get a goal that is not a commander: open the way.
  - Stone-throwers get a job no other class does as well.
  - No new command, preview or event type.
- **Negative / costs:**
  - Every rule that walks over units has to remember works: victory, readiness, ZOC, conditions, push, shift sweeps, healing. A test covers each.
- **Follow-ups:**
  - Other works later: siege towers, barricades, a fire-ship hulk at 적벽.
  - Ladders or breaches as tile shifts set off by a broken gate.

## Revisit when

Gates feel like damage sponges that slow a battle without a choice in it.
If so, give them weak points or let a 책사's fire burn them.
