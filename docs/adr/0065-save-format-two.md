# 0065. Save format 2: story flags and officers gone for good, reading format 1

- **Status:** Proposed
- **Date:** 2026-10-20
- **Deciders:** Claude (default chosen while building; the owner to confirm)
- **Related:** [0051](0051-browser-saves.md), [0060](0060-story-choices-and-flags.md),
  [0061](0061-fates-decided-in-battle.md)

## Context

Story flags (ADR 0060, 0061) and the list of officers gone for good are part
of the campaign now, so saves must keep them. Players already have format-1
saves in their browsers from chapters 1 and 3.

## Options considered

1. **Format 2, reading format-1 saves:** chosen. A format-1 save gets the flags its chapter assumes for a fresh start, and an empty gone list.
2. **Format 2 only:** simpler, but old saves would stop loading.
3. **Keep format 1 and store flags elsewhere:** two things to keep in step.

## Decision

- **What a save gains:**
  - `version: 2`.
  - `flags`: at most 64 entries. Names and values are short lowercase words (`[a-z0-9-]`, up to 40 characters).
  - `gone`: officer ids, each known, none generic, none also in the roster.
- **Reading format 1:**
  - `readSave` accepts version 1 and fills in the chapter's `assumes` and an empty gone list.
  - It returns a version-2 state.
  - Anything else malformed is still refused with a reason.
- **Step indices are unchanged,** so a format-1 save resumes where it was.

## Consequences

- **Positive:** no player loses a save; flags are checked like every other field.
- **Negative / costs:** one more format to keep reading until format 1 can be dropped.
- **Follow-ups:** drop format 1 once server saves (open question 5) exist.

## Revisit when

A later chapter needs a richer state than string flags, such as counters or lists.
