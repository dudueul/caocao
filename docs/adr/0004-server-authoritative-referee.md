# 0004. Server-authoritative referee on Durable Objects with one shared rules engine

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0003](0003-turn-structure.md), [0006](0006-typescript-everywhere.md),
  [`docs/server/GAME_SERVER_PRACTICES.md` §4.3](../server/GAME_SERVER_PRACTICES.md),
  [`docs/remake/ARCHITECTURE.md` §4](../remake/ARCHITECTURE.md#4-online-battles)

## Context

The backend is Cloudflare Workers with Durable Objects (DOs), which run
JavaScript/TypeScript or WebAssembly. The recovery memo recommended
server-sequenced **lockstep** (model L) because the recovered client already
had C# rules. The remake has no such constraint. Battles are turn-based
([0003](0003-turn-structure.md)), so validating a command costs milliseconds.

## Options considered

1. **L, lockstep:** clients simulate and the server only orders commands. One
   implementation, but needs bit-exact determinism on every client. A cheater
   can't win but can stall the match, and there's no hidden information.
2. **A, server-authoritative referee:** chosen. Only possible cheaply if the
   server runs the same rules code as the client.
3. **H, dedicated C# host:** containers to run and pay for.

## Decision

- **Live battles (PvP, World battles):** a `MatchDO` per battle holds the
  authoritative state. Clients send **commands** (`move`, `attack`, `skill`,
  `item`, `wait`). The DO validates and applies each one with the shared rules
  package, rolls the dice with its own seeded RNG, and broadcasts the resulting
  **events**. Clients animate events; they never decide outcomes.
- **Solo battles (Story, Raids):** the client runs the same package locally
  with a server-issued seed, then uploads the command log. `PlayerDO`
  re-simulates the log before granting rewards. It is cheap and makes cheating pointless.
- AI seats (NPCs, absent players) run inside the `MatchDO` with the same package.

## Consequences

- **Positive:** cheat-proof outcomes, no desyncs, hidden information possible
  (fog, ambush). Replays and spectating come from the event log.
  Reconnecting just means rejoining the DO.
- **Negative:** the rules package must be strictly deterministic and
  side-effect free (no `Date.now`, `Math.random`, floats in outcomes).
  AI CPU time is spent on the server.
- **Supersedes** for the remake the model-L recommendation in
  `GAME_SERVER_PRACTICES.md` §4.3. That memo still describes the recovered client.

## Revisit when

DO CPU per command or AI turn exceeds budget (target: under 10 ms per command,
under 200 ms per AI activation), or a mode needs real-time combat.
