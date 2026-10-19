# 0049. Story chapter 1 is Cao Cao's Guandu campaign, run by a pure campaign package

- **Status:** Accepted (proposed while building, confirmed by the owner 2026-10-19)
- **Date:** 2026-10-19
- **Deciders:** Claude (proposal), project owner (accepted); the owner asked for "a complete game"
- **Related:** [0008](0008-modes-as-main-menu-entries.md), [0014](0014-scoped-progression-fair-arena.md),
  [0015](0015-delivery-order-engine-then-arena.md), [0047](0047-battle-goals-and-arrivals.md),
  [0048](0048-story-progression.md), open question 6 in [docs/remake/README.md](../remake/README.md)

## Context

The owner asked for features that make this a complete game. The engine, a
sandbox and the Arena existed, but there was no campaign. ADR 0015 plans P2 as
"Story chapter 1: event VM, dialogue, re-simulation, Story roster, 5–8 scripted
battles playable end to end". Open question 6 (whose story) was still open.

## Options considered

1. **Cao Cao's Guandu campaign (199–200), five battles:** chosen. It is the
   heart of 조조전, compact, well recorded, and our sandbox map is already
   Wuchao. It answers question 6 for chapter 1 only.
2. **A longer chapter (Yellow Turbans to Guandu):** more content than a first
   playable chapter needs.
3. **The player's own officer:** fits Living History, not a first Story.

## Decision

- **Chapter 1, 관도:** a prologue and five battles, with scenes in between and an epilogue.
  1. 백마 구원 — relieve Baima: defeat Yan Liang while the governor holds out.
  2. 연진의 미끼 — the wagons left as bait: defeat Wen Chou.
  3. 관도 사수 — hold the Guandu camp under the earth mounds until round 9.
  4. 오소 야습 — the night raid: reach the grain depot or defeat Chunyu Qiong.
  5. 관도 결전 — storm Yuan Shao's camp; Zhang He and Gao Lan defect and arrive on our side.
- **Content** (`@tk/content`): `ChapterDef` is a list of steps — scene, battle, camp.
  - **Scenes** have lines with a speaker, a mood and an optional side, and add or remove roster officers.
  - **Battles** list their slots, the officers who must go out (`required`), guests, enemies, goals, rewards and in-battle talk.
  - **Talk triggers:** start, round, defeated, arrived, end.
  - **Troop templates** (원소군, 조조군) let many units share one rating line, with their own ids.
  - All text is our own words on public-domain history.
- **Logic** (`@tk/campaign`) is pure and deterministic, like the rules, and covered by the determinism guard:
  - the campaign state and its step flow;
  - recruiting, and guests who leave with their gear returned;
  - the sortie check and `storySetup` (roster officers with their gear);
  - experience from events, and `settle` (pays out a win, changes nothing on a loss).
  - A future `PlayerDO` can re-run it.
- **Client:** the Story screen is one view per phase of the chapter: scene → sortie → battle (talk over the field) → results → camp.
- The guest Guan Yu leaves after Baima, as recorded; Liu Yan is a guest unit the player must keep alive.

## Consequences

- **Positive:** a playable campaign from title to ending; the AI can play the
  whole chapter in tests (all five battles winnable, replays exact).
- **Negative / costs:** dialogue and maps are hand-written content to maintain;
  no branching story yet; Korean only.
- **Follow-ups:** chapter 2; English text (question 10); a server copy of
  progress (`PlayerDO`) with accounts (question 5).

## Revisit when

The owner picks a different story scope for question 6, or chapter 2 needs
branching routes.
