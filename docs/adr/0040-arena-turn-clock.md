# 0040. Live Arena clock: 45 seconds per activation plus a 3-minute reserve

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner
- **Related:** [0003](0003-turn-structure.md), [0011](0011-world-pacing-and-auto-join.md),
  [0030](0030-modern-techniques-adopted.md), [0037](0037-websocket-json-protocol.md)

## Context

Alternating activations need a clock so an absent or slow player can't stall a
match. [0011](0011-world-pacing-and-auto-join.md) already says an expired timer
hands the activation to the AI rather than forfeiting.

## Options considered

1. **45 s per activation + a 3-minute reserve:** chosen. Hard decisions can take longer.
2. **30 s flat:** brisk and predictable; no long thinks.
3. **No timer:** relaxed; a slow player can stall the match.

## Decision

- Each activation gives **45 seconds**. Time past that drains the player's
  **reserve**, which starts at **3 minutes** for the whole match.
- When the activation time and the reserve are both used up, the **AI plays that
  one activation** for the player (their gambits first, then its scoring). The next
  activation starts with a fresh 45 seconds; the reserve stays at zero.
- The referee keeps the clock with Durable Object alarms; clients only display it.
  Draft picks and the deploy phase have their own timers (picks 30 s, deploy 60 s;
  on expiry the server picks or arranges automatically).

## Consequences

- **Positive:** matches always progress; a dropped player keeps "playing" through the AI.
- **Negative:** the AI's move may disappoint the player it stood in for.

## Revisit when

Friends ask for slower or faster matches, or a correspondence (async) Arena arrives.
