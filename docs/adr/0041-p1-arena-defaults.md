# 0041. P1 Arena defaults chosen while building

- **Status:** Proposed (defaults chosen while building P1; the owner is asked to confirm)
- **Date:** 2026-10-15
- **Deciders:** Claude (proposal)
- **Related:** [0004](0004-server-authoritative-referee.md), [0012](0012-household-corps-seats.md),
  [0014](0014-scoped-progression-fair-arena.md), [0037](0037-websocket-json-protocol.md)–[0040](0040-arena-turn-clock.md)

## Context

Building P1 needed answers the owner's decisions leave open. Each is chosen to
get friends playing soon; all can change.

## Decision

| # | Default | Why |
|---|---|---|
| 1 | **One `MatchDO` per room** runs the lobby, draft, deploy and battle. `LobbyDO` (presence, chat, room lists) waits until rooms need discovering. | A room is a single writer from invite to result; one object is simplest to reason about. |
| 2 | **Room codes:** 6 characters from a 31-letter alphabet without look-alikes (no 0/O, 1/I/L). The creator takes seat A, the first joiner seat B. Rooms expire 24 hours after the last activity. | Short enough to read out loud; codes are unguessable enough for friends. |
| 3 | **Who goes first:** seat B picks first in the draft; seat A acts first in round 1. Later rounds alternate the starter ([0027](0027-p0-engine-defaults.md)). | Trades first pick against first move. |
| 4 | **P1 pool:** 16 officers from several factions, each with a fixed tier-1 class, all at **Lv 20**. Ratings are ours, in steps of 5 ([0032](0032-content-as-typed-modules.md)). | Enough choice for a 12-pick draft with 4 left over; levels and classes stay normalized. |
| 5 | **Arena map:** one mirrored 14 × 12 field, six deploy tiles per side; players arrange their six by swapping before "ready". Round limit 20, then a draw. | One fair map before a map pool; arranging is quick on a phone. |
| 6 | **Battle seed:** drawn from `crypto.getRandomValues` in the `MatchDO`, never sent; states to clients carry no `Prng` ([0037](0037-websocket-json-protocol.md)). | Stops clients predicting rolls. |
| 7 | **Leaving:** a disconnected player keeps the seat; the clock and the AI play on ([0040](0040-arena-turn-clock.md)). Either player may concede. | Matches always finish. |
| 8 | **Hidden deployment:** each seat sees only its own arrangement until the battle starts. | The second player to ready can't counter-arrange. |
| 9 | **Test clock:** a `CLOCK_SCALE` variable speeds every clock up for local tests only; production leaves it unset. | Lets the smoke test prove the alarms without waiting minutes. |

## Consequences

- **Positive:** friends can play with nothing to configure beyond a name.
- **Negative:** one map until a map pool exists; no spectators yet.

## Revisit when

The owner changes a row, or friends' play shows a default feels wrong.
