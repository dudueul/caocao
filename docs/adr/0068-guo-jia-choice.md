# 0068. Let the player choose whether 곽가 marches to 백랑산

- **Status:** Accepted
- **Date:** 2026-10-21
- **Deciders:** project owner (chose a choice with a cost), Claude (how each path plays)
- **Related:** [0060](0060-story-choices-and-flags.md), [0061](0061-fates-decided-in-battle.md),
  [0067](0067-chapter-four-hebei.md), [STORY.md](../remake/STORY.md)

## Context

In 207 곽가 urged the desert march to 백랑산 and its surprise attack on the
오환. He fell ill on the march and died at 38, on the way home. 조조 later
said, after 적벽, that 봉효 would not have let it happen.

The owner was offered three treatments:
- history only;
- **a choice with a cost**;
- a fate saved by play, like 전위 ([ADR 0061](0061-fates-decided-in-battle.md)).

## Options considered

1. **A choice with a cost:** chosen (recommended).
   - Take 곽가, and 백랑산 is easier, but he dies.
   - Send him home, and he lives, but 백랑산 is harder.
2. **History only:** he marches and dies, whatever the player does.
3. **Saved by play:** he must sortie, and lives if he is never hit. This repeats 전위's mechanic and puts a frail strategist in the line.

## Decision

- **The choice:**
  - Before the march (scene 노룡새의 옛길, 207), the player chooses what to do with the sick 곽가.
  - It sets the flag `guojia`.

  | Value | Choice | 백랑산 | After |
  |---|---|---|---|
  | `went` (history) | "봉효의 계책이 필요하다. 함께 간다" | Surprise: fewer 오환 are ready at the start and some arrive late; 답돈's camp is unready | 곽가 dies at 역주 in the epilogue and is gone for good |
  | `stayed` | "봉효는 허로 돌아가 몸을 돌보게 하라" | The 오환 are ready: more cavalry at the start, all in place | 곽가 lives and goes on to the next chapter |

- **At 백랑산:** 곽가 can sortie only if he went.
- **Talk and scenes** follow the flag. Lines depend on it before and after the battle, and the epilogue differs.
- **A fresh start** of a later chapter assumes history: `went`.

## Consequences

- **Positive:**
  - A real price on both sides.
  - Carries forward: 곽가 alive at 적벽 can matter in chapter 5.
- **Negative / costs:**
  - Two versions of 백랑산 to balance, and two endings of the chapter.
- **Follow-ups:**
  - Chapter 5 lines for a living 곽가.
  - Its fresh start assumes `guojia: "went"`.

## Revisit when

Players pick one path almost always. If so, widen the gap between the two
battles or deepen what the other path gives.
