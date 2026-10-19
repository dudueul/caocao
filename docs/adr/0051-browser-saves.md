# 0051. Save the Story in the browser: three slots and an autosave

- **Status:** Accepted (proposed while building, confirmed by the owner 2026-10-19)
- **Date:** 2026-10-19
- **Deciders:** Claude (proposal), project owner (accepted)
- **Related:** [0038](0038-guest-identity-device-key.md), [0049](0049-story-chapter-guandu.md),
  open question 5 in [docs/remake/README.md](../remake/README.md)

## Context

A campaign needs saving. P1 players are guests with a device key and no
account (ADR 0038); accounts and a `PlayerDO` are planned for P2.

## Options considered

1. **Browser storage, three slots and an autosave:** chosen. Works offline and
   in the PWA, no server.
2. **Server saves under the device key:** survives a cleared browser, but needs
   storage and an API before accounts exist.
3. **Export and import a save file:** useful as an addition; not the main path.

## Decision

- A save is the `CampaignState` as JSON (format 1) with a little metadata for the list: the chapter, where, gold, the lord's level, the time.
- It is kept in the browser's storage.
- **Autosave** after every scene, camp and battle won; three manual slots in camp; load from the title screen. "Continue" opens the most recent one.
- Saves are made between steps, not inside a battle. Leaving mid-battle and coming back within the visit resumes it.
- **Reading a save is untrusted:** `readSave` checks every field against the content:
  - the chapter and step;
  - officers, classes and levels;
  - stats, gear fit and items.

  It returns a reason instead of throwing. Storage errors never break the game.

## Consequences

- **Positive:** simple, offline, private.
- **Negative / costs:** a cleared browser loses progress; no sync between devices.
- **Follow-ups:** move saves to a `PlayerDO` with accounts (question 5), keeping
  the same format and checks.

## Revisit when

Accounts land, or players lose progress to cleared storage.
