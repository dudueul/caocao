# 0036. P0 skills and conditions: thirteen skills, eight conditions

- **Status:** Accepted (proposed while building P0, confirmed by the owner 2026-10-15)
- **Date:** 2026-10-15
- **Deciders:** Claude (proposal), project owner (accepted)
- **Related:** [0015](0015-delivery-order-engine-then-arena.md), [0021](0021-class-families-and-tiers.md),
  [0024](0024-stratagems-elements-and-fortune-twists.md), [0027](0027-p0-engine-defaults.md),
  [0034](0034-scenario-ai-orders.md), [0035](0035-stacking-conditions-rolled-durations.md),
  [`docs/remake/RULES.md` §5 and §10](../remake/RULES.md#10-conditions)

## Context

P0 exits with 10+ skills and conditions ([0015](0015-delivery-order-engine-then-arena.md)).
[0035](0035-stacking-conditions-rolled-durations.md) set how conditions work; this
record sets which ones P0 has and who uses them. The support families (도사, 무희)
don't exist yet, so someone has to carry healing and cleansing.

## Decision

| # | Default | Why |
|---|---|---|
| 1 | **Eight conditions:** 혼란 (can only wait), 마비 (can't move), 금책 (no stratagems), 독 (6% max HP per turn), 약화 (ATK and DEF −12%), 고무 (ATK +15%), 견고 (DEF +15%), 신속 (move +1, hit +8%). | Covers every kind the engine supports: blocks, damage over time, stat changes, buffs and debuffs. |
| 2 | **Poison never drops a unit below 1 HP.** | Damage over time weakens; strikes and stratagems finish. Avoids defeats nobody saw coming. |
| 3 | **책사 gets ten stratagems:** 화계, 낙석 (earth), 수계 (water, pushes 1, adds 약화), 풍계, 독연 (light damage plus 독), 혼란, 속박 (마비), 봉책 (금책), 치료 and 정화 (heals and removes debuffs). | One element per school, three disruptions; healing and cleansing move to 도사 when that family arrives. |
| 4 | **Class skills:** 보병 철벽 (견고 on itself and the four tiles around it), 기병 질풍 (신속 on itself), 무도가 기합 (고무 on itself). Officers already have MP (⌊지력/2⌋), so no class MP is added. | Gives melee classes a choice besides attack or wait, and uses MP that low-지력 officers already have. |
| 5 | **Durations** are 1–2 turns for hard blocks (혼란, 마비), 2–3 for buffs, 금책 and 약화 (1–2), and 2–4 for 독. | Hard control stays short; buffs last long enough to plan around. |
| 6 | **The AI values a condition** by its expected effect over the average duration (HP-like points times the landing chance), less for further stacks, nothing for a block that already holds, and nothing for buffs far from enemies. Support casts pay the same approach pull as waiting. | Without the pull, self-buffs beat walking toward the enemy. |

## Consequences

- **Measured** (40 AI-vs-AI 오소 battles each, with the defenders' orders of
  [0034](0034-scenario-ai-orders.md)): the raiders win 14–26 under side phases and
  27–13 alternating (24–16 and 18–22 before conditions). Strategists now mix 화계,
  독연, 치료 and 정화; class skills are used when enemies are near.
- The AI rarely uses 혼란, 속박 or 봉책 while 화계 is available, because a sure hit
  usually outscores a chance to deny one action. Players may find them stronger
  than the AI does.

## Revisit when

The owner changes a row, 도사 or 무희 arrive (healing moves), or playtests show a skill nobody uses.
