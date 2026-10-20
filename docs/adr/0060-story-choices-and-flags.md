# 0060. Let the player choose at 서주 and 백문루, and remember it in story flags

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (a choice at 서주 that decides 진궁's loyalty; a choice at 백문루 where sparing 여포 has a cost), Claude (the flag model and details)
- **Related:** [0049](0049-story-chapter-guandu.md), [0056](0056-chapter-order-and-carry-over.md),
  [0059](0059-chapter-two-lu-bu.md), [0061](0061-fates-decided-in-battle.md),
  [0065](0065-save-format-two.md), [STORY.md](../remake/STORY.md)

## Context

In 193 Cao Cao avenged his father by massacring the people of Xuzhou's
towns. Asked how the game should treat it, the owner chose a **player
choice** over telling it or leaving it out. Asked what it should change, the
owner chose **진궁's loyalty**. At 백문루 in 198 the captured Lü Bu begs to
serve; the owner chose a second choice there, where **sparing him has a cost**.
Until now chapters were fixed lists of steps.

## Options considered

1. **What the 서주 choice changes:**
   - **진궁's loyalty:** chosen.
   - Only rewards and lines.
   - The difficulty of one later battle.
2. **What 백문루 offers:**
   - **A choice where sparing costs 장료:** chosen.
   - History only.
   - A free choice.
3. **How to model it (Claude):**
   - **Flags with conditions on any step or line:** chosen.
   - Separate copies of the chapter for each path: simpler to read, but every shared scene would be written twice.

## Decision

- **Flags:**
  - The campaign keeps `flags`, a map of flag to value.
  - A scene's `choice` command sets one; a won battle's fates set others (ADR 0061).
  - Each choice option may carry spoils: gold and items.
- **Conditions:**
  - Steps, scene commands, lines, actors, joins, leaves, battle units, goals, map shifts, talk and camp talk can carry `if: { flag, is | not }`.
  - A failed step is passed over.
  - `finishScene(state, picks)` refuses to finish until every choice the scene reaches is answered.
- **Gone for good:**
  - Officers who leave through a scene are recorded in `gone`.
  - A later chapter's starting roster does not bring them back.
  - A scene may still bring someone back explicitly.
- **Fresh starts:** a chapter can `assume` flag values for a fresh start. 관도 assumes history: revenge, 전위 fell, 여포 executed.
- **서주 (`xuzhou`):**
  - **revenge**, as history went:
    - Plunder: 1,500 gold and a 준마. The massacre is told in narration, plainly and soberly.
    - 진궁 turns away. In 194 he joins 장막 in bringing 여포 into 연주, fights against us, and dies at 백문루.
  - **mercy:**
    - No plunder. 진궁 stays with us, and 장막 rebels alone.
    - At 하비, 진등 opens the east gate from inside (a timed map shift).
- **백문루 (`lubu`):**
  - **executed**, as history went: 장료 joins.
  - **spared:** 여포 joins with 방천화극 and 적토마. 장료 refuses to serve beside his lord's shame and is gone for good; 유비 leaves for court.
  - 고순 dies on both paths.
- **Validation:** `validateChapter` walks every path through the flags a chapter sets or tests, with the roster each path would have.

## Consequences

- **Positive:**
  - The player owns the darkest choice of Cao Cao's life, and the story remembers it two chapters later.
  - One chapter file covers every path.
- **Negative / costs:**
  - Each new flag doubles the paths to validate and balance.
  - Writers must remember to guard lines by officers who may be absent (장료, 진궁, 전위, 여포 in 관도).
- **Follow-ups:**
  - Save format 2 (ADR 0065).
  - The balance chain runs both paths.
  - "Branching scenes" leaves the open list in STORY.md.

## Revisit when

A chapter needs more than three or four flags, or two choices that interact.
At that point consider named paths instead of free flags.
