# 0056. Tell Cao Cao's story in order, carrying the roster from chapter to chapter

- **Status:** Accepted; chapter 2 exists since [0059](0059-chapter-two-lu-bu.md), and officers gone for good stay gone ([0060](0060-story-choices-and-flags.md))
- **Date:** 2026-10-19
- **Deciders:** project owner (chose the order and the carry-over), Claude (details)
- **Related:** [0048](0048-story-progression.md), [0049](0049-story-chapter-guandu.md),
  [0051](0051-browser-saves.md), [0057](0057-chapter-one-yellow-turbans.md), [STORY.md](../remake/STORY.md)

## Context

The first chapter written was 관도 (199–200). Asked to start chapter 2, the
owner pointed out that Cao Cao's story begins well before the Yellow
Turbans. Chapters therefore need an order, and a campaign needs a way to
move from one chapter to the next.

## Options considered

1. **Retell in order — prequel chapters first, 관도 becomes chapter 3; carry the roster with a level floor:** chosen.
2. **One long prequel chapter, 관도 as chapter 2:** fewer battles per period.
3. **Keep 관도 first and make the prequel a side story:** the story would run out of order.
4. For carry-over: carry everything with no floor (hard to balance), or start each chapter fresh (growth would not follow the player).

## Decision

- **The order:**
  - Chapter 1, 황건의 난 (174–192).
  - Chapter 2, 여포 (193–198), not written yet.
  - Chapter 3, 관도 (199–200), unchanged.
- **Chapter fields:** each chapter has a `number`, a level `floor`, the `next` chapter and its own ending text.
  - Until chapter 2 exists, chapter 1 leads to 관도, and the ending screen says chapter 2 is still to come.
- **Carry-over** (`continueTo`): when a chapter is finished, the campaign goes on to the next one.
  - Officers, levels, classes, gear, gold, items and the battle record go along.
  - Guests leave, and their gear returns to the inventory.
  - Officers below the floor are raised to it, gaining the growth of every level (`raiseTo`).
  - Officers the new chapter starts with join if they are missing.
- **Fresh starts:** a new game starts at chapter 1. The title's chapter select starts any chapter with its own default roster.
- **Saves:** the save format does not change. A save names its chapter, so saves made in 관도 keep working.

## Consequences

- **Positive:** the player's officers grow across the whole story, and the floor keeps later chapters fair for anyone who falls behind.
- **Negative / costs:** later chapters must be balanced for a roster that may be larger and stronger than their default one; battle ids must stay unique across chapters (a test checks this).
- **Follow-ups:**
  - Write chapter 2 (여포) and point chapter 1's `next` at it.
  - Its level floor sits between those of chapters 1 and 3.

## Revisit when

Players arrive at a chapter far stronger than it was balanced for, or ask to replay a chapter with their carried roster.
