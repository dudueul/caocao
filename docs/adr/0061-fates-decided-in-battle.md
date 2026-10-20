# 0061. Let a battle decide a fate: 전위 lives if he is standing when 조조 escapes 완성

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (chose that 전위 can be saved), Claude (details)
- **Related:** [0059](0059-chapter-two-lu-bu.md), [0060](0060-story-choices-and-flags.md), [STORY.md](../remake/STORY.md)

## Context

At 완성 in 197, 전위 held the gate alone so that Cao Cao could escape, and
died there. Defeated officers in our battles only retreat; nobody dies for
good. The owner was offered three ways to treat 전위:
- history (he joins and then dies whatever happens);
- **can be saved**;
- a guest only.

The owner chose **can be saved**: a second branch, decided by play rather
than a choice.

## Options considered

1. **Joins, then dies:** recommended; the strongest drama, but the player's effort can't change it.
2. **Can be saved:** chosen.
3. **Guest only:** costs the player nothing they built up.

## Decision

- **Fates:**
  - A story battle can list `fates`: `{ flag, unit, standing, fallen }`.
  - When the battle is **won**, each flag becomes `standing` if that unit still stands at the end, else `fallen`.
  - A loss decides nothing, and the battle can be retried.
- **At 완성:**
  - The battle is won the moment 조조 reaches the east edge.
  - 전위 must sortie and starts at the camp gate between the enemy and 조조.
  - `dianwei` becomes `lived` or `fell`.
  - The next scene shows his last stand and death, or him carried out wounded.
  - If he fell, he leaves the roster for good, and his gear returns to the inventory.
  - 조앙 dies on both paths, in the scene before the battle.
- **Balance:** with the AI on both sides, 조조 gets out in nearly every run, and 전위 survives in roughly a third to two thirds of runs. A player who pulls him back in time can save him.

## Consequences

- **Positive:** a real stake in one battle, and a reason to replay it.
- **Negative / costs:** the same history beat now has two scenes to write and test.
- **Follow-ups:** a result-screen line that says which fate the battle decided.

## Revisit when

Players treat a saved 전위 as the only "right" ending and always retry for it.
If so, consider making his death matter in another way.
