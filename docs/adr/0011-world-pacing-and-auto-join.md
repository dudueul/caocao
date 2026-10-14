# 0011. World pacing: check-ins, prime-time anchor battles, auto-join on connect

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0009](0009-living-history-anchored-history.md),
  [0012](0012-household-corps-seats.md), [`docs/remake/WORLD_ENGINE.md` §5](../remake/WORLD_ENGINE.md#5-pacing-and-battles)

## Context

At one game month per 2 hours there are 12 decision points a day. Nobody can
attend them all, so orders must queue. Battles between armies need rules for
when they're played live, against an absent player's AI, or auto-resolved.

## Options considered

1. **Check-ins + prime-time battles:** chosen, plus the owner's auto-join rule.
2. **One turn per day:** very casual; nothing live.
3. **Live sessions only:** the clock only runs during scheduled evenings.
4. **Always-on hourly ticks:** classic SLG pressure; burnout for casual friends.

## Decision

- **Check-ins:** about 20–40 minutes a day. Players queue orders (march,
  develop, train, diplomacy, marriage) that execute on the monthly ticks.
- **Skirmishes:** when armies meet, a battle opens with a play window (default
  6 h). Seats are played live if their owners are on, otherwise by the AI. If
  nobody plays before the window closes, the battle is auto-resolved from army strength.
- **Anchor battles** (Guandu, Red Cliffs …) are scheduled in a **prime-time
  slot** (default 21:00 KST), announced a day ahead (one game year), so friends gather live.
- **Auto-join (owner's rule):** when a player connects while their household
  corps is in an active battle, they automatically take over its seat at the
  start of the corps' next activation. Units never change controller
  mid-move. While they're away the AI plays the seat, and a
  *"while you were away"* log shows what happened.

## Consequences

- **Positive:** casual players keep up with short check-ins; key moments
  happen live; nobody's absence stalls a battle.
- **Negative:** the AI must be good enough that friends don't feel cheated
  while away. Auto-resolve needs a fair strength model. Prime-time slots favour one time zone.
- **Effort:** auto-join is small (seat controller switch in `MatchDO`); the AI quality is the real work.

## Revisit when

Prime time doesn't suit the group, or auto-resolve is used for most battles (a
sign the windows are too short or battles too long).
