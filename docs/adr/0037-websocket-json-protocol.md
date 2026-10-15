# 0037. Clients talk to the referee over plain WebSocket with typed JSON messages

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner
- **Related:** [0004](0004-server-authoritative-referee.md), [0011](0011-world-pacing-and-auto-join.md),
  [`packages/protocol`](../../packages/protocol/README.md), [`docs/remake/ARCHITECTURE.md` §4](../remake/ARCHITECTURE.md#4-online-battles)

## Context

P1 puts friends in live Arena battles refereed by a Durable Object
([0004](0004-server-authoritative-referee.md)). The client and the referee need a
transport and a message format. Players drop and reconnect on phones, and most
rooms sit idle between moves.

## Options considered

1. **Plain WebSocket + typed JSON:** chosen. Our own small protocol; Cloudflare's
   WebSocket Hibernation lets idle rooms cost nothing.
2. **PartyServer:** room objects and an auto-reconnecting socket; less code, one more dependency.
3. **HTTP + Server-Sent Events:** proxy-friendly, but a request per command and an open stream per player.

## Decision

- One WebSocket per player per room. Messages are JSON objects tagged by `t`,
  defined once in `packages/protocol` and checked by hand-written validators on
  both ends.
- **Up:** `hello` (join or rejoin, with the device key of [0038](0038-guest-identity-device-key.md)
  and the last batch the client saw), `pick`, `arrange`, `ready`, `command`, `ping`.
- **Down:** `welcome`, `room` (lobby, draft and deploy state), `snapshot`,
  `batch` (one applied command: its events and the new state, numbered `seq`),
  `rejected`, `pong`.
- **Reconnect:** `hello` with `lastSeq` returns the missed batches when the room
  still holds them, otherwise a fresh `snapshot`.
- States sent to clients have the **`Prng` state removed**, so no client can predict
  the next roll. Clients compute previews and legal commands from the redacted state.

## Consequences

- **Positive:** no runtime library; one message set for the browser, the Worker
  and tests; reconnection is a normal `hello`.
- **Negative / costs:** we write the reconnect logic ourselves; each batch carries
  the full redacted state (a few KB), which is simple and fine for 12 units.
- **Follow-ups:** delta states if battles grow; fog of war will need per-seat redaction.

## Revisit when

Batches grow large (big World battles), or reconnect handling becomes a source of bugs.
