# 0067. Chapter 4, 하북 평정: seven battles from 창정 to 백랑산

- **Status:** Accepted
- **Date:** 2026-10-21
- **Deciders:** project owner (chose the chapter and its years, seven battles with 여남, and 원담 as an ally who turns enemy), Claude (maps, armies, roster and tuning)
- **Related:** [0056](0056-chapter-order-and-carry-over.md), [0059](0059-chapter-two-lu-bu.md),
  [0062](0062-map-shifts.md), [0066](0066-allies.md), [0068](0068-guo-jia-choice.md),
  [0069](0069-breakable-gates.md), [STORY.md](../remake/STORY.md)

## Context

Chapter 3 (관도) ends in 200 with 원소 beaten but alive. The next history
beats are the conquest of the north (201–207) and 적벽 (208). The owner was
asked what chapter 4 should cover, which battles it should have, and how
원담 should appear. 원담 is the son who submitted to 조조 in 203 and was
turned on in 205.

## Options considered

1. **What the chapter covers:**
   - **하북 평정, 201–207:** chosen (recommended). 적벽 becomes chapter 5.
   - 적벽, 208, with the north told in a scene.
   - Both in one long chapter, with the north cut to two or three battles.
2. **Battles:**
   - Six: 창정, 여양, 업, 남피, 호관, 백랑산 (recommended).
   - **Seven, adding 여남 against 유비:** chosen. 조조 turns south in 201 to drive 유비 out before going north.
   - Five, dropping 호관.
3. **원담:**
   - **Orange ally, then enemy:** chosen (recommended). He fights beside us at 업 and commands against us at 남피.
   - Enemy throughout.
   - A choice to accept or refuse his submission.

## Decision

- **Chapter 4, 하북 평정 (201–207)**, floor Lv 18, 1,500 gold to start. A fresh start assumes history's chapter 2 flags.
- **Battles, in order:**

  | # | Year | Battle | Win |
  |---|---|---|---|
  | 1 | 201 | 창정의 결전 | defeat 원소 |
  | 2 | 201 | 여남의 유비 | defeat 유비 |
  | 3 | 203 | 여양의 형제 | defeat 원담 and 원상 |
  | 4 | 204 | 업성 공략 | defeat 심배 in the keep |
  | 5 | 205 | 남피의 얼음 | defeat 원담 |
  | 6 | 206 | 호관의 고간 | defeat 고간 |
  | 7 | 207 | 백랑산의 오환 | defeat 답돈 |

- **업** uses the new breakable gates ([ADR 0069](0069-breakable-gates.md)) and a timed map shift:
  - Before round 5, 심배's men sally from the gates.
  - On round 5 the 장하 fills the trench.
  - Then our stone-throwers and infantry batter a gate open and storm the keep.
- **원담:**
  - At 여양 (203) he fights beside 원상 against us.
  - After 여양, a scene shows him ask to submit, and 조조 and 곽가 decide to use him.
  - At 업 (204) he is an orange ally on side 2 ([ADR 0066](0066-allies.md)), holding off 원상's relief army.
  - Before 남피 (205) he breaks faith, and there he commands the enemy on the river ice. The ice gives way on a set round.
- **호관** has one gate, set in the pass.
- **백랑산** depends on the 곽가 choice ([ADR 0068](0068-guo-jia-choice.md)).
- **Roster:**
  - The chapter opens with the chapter 3 officers at Lv 18–22.
  - 장료 opens it unless 여포 was spared.
  - 신비 joins after 여양, 조순 before 남피, and 전주 before 백랑산.
  - 허유 leaves after 업.
  - 곽가 leaves for good at 역주 if he marched.
- **The chapter ends** with 조조 on 갈석산 looking at the sea. No chapter follows yet.

## Consequences

- **Positive:**
  - The years between 관도 and 적벽 are played rather than told.
  - 원담 shows allies who are not friends.
  - 여남 gives 유비 a last meeting before 적벽.
- **Negative / costs:**
  - Seven battles to map, staff, write and balance, and two new rules (gates, the ice) to test.
- **Follow-ups:**
  - Chapter 5 (적벽) will assume `guojia: "went"` for a fresh start.
  - Portraits for the new officers: 원상, 고간, 답돈, 신비, 조순, 전주 and others.

## Revisit when

Playtests find the chapter too long, or 여남 out of place between 창정 and
여양. If so, fold 여남 into a scene.
