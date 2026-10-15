# 0038. P1 players are guests: a display name and a secret device key

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner
- **Related:** [0005](0005-web-first-distribution.md), [0014](0014-scoped-progression-fair-arena.md),
  [0037](0037-websocket-json-protocol.md), [`docs/remake/README.md` open question 5](../remake/README.md#open-questions-next-design-sessions)

## Context

P1 is friends-only Arena play. Players need a stable identity within a room (to
reclaim their seat after a dropped connection) but nothing else yet: no
progression or purchases exist in P1.

## Options considered

1. **Guest name + device key:** chosen. No sign-up; invite links.
2. **Passkeys now:** real accounts from day one; more work before the first match.
3. **Discord or Google login:** familiar, but needs OAuth apps, secrets and a third party.

## Decision

- On first visit the client creates a random 128-bit **device key**, kept in
  `localStorage`, and asks for a **display name** (1–12 characters).
- Rooms are joined by **invite link** (`#/r/CODE`). The first two players to join
  take the seats; the room stores a SHA-256 hash of each seat's device key, never
  the key itself.
- A `hello` with the same key reclaims the seat after a disconnect. A new device
  can't reclaim a seat until accounts exist.
- Accounts (passkeys, open question 5) come with `PlayerDO` in P2; a device key
  can be linked to an account then.

## Consequences

- **Positive:** a friend plays seconds after opening a link; the server stores no personal data.
- **Negative:** clearing browser data or switching phones loses the seat in a running match.

## Revisit when

P2 starts (Story progress needs accounts), or friends lose seats often.
