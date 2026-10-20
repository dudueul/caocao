# 0066. Allies: other lords' people fight beside us in orange, under their own AI

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (asked for allies distinct from our own force, in orange and not controllable; chose who they are and that they move after us), Claude (the rules model)
- **Related:** [0003](0003-turn-structure.md), [0047](0047-battle-goals-and-arrivals.md),
  [0049](0049-story-chapter-guandu.md), [0055](0055-our-side-red.md),
  [RULES.md](../remake/RULES.md), [STORY.md](../remake/STORY.md)

## Context

Story battles brought "guests": units that come with the battle rather
than from the roster. They sat on our side, so the player commanded them
like his own officers. Examples:
- 관우, 장비, 유비 and 손견 at 사수관;
- 황보숭 at 장사;
- 유비's brothers at 하비.

The owner asked for a significant fix: our own force should be told apart
from allies. Allies are other lords' people, in orange, who are not ours to
command but fight on our side under their own AI, as in the 1990s Koei
tactics games.

## Options considered

1. **Who becomes an ally:**
   - **Other lords' people:** chosen. Our own troops that come with a battle (the night ward, the ambushes at 정도 and 안중) stay red and ours.
   - Every guest.
   - Decide per battle.
2. **When allies move:**
   - **Their own phase after ours, before the enemy:** chosen.
   - Before ours.
   - After the enemy.
3. **How to model it (Claude):**
   - **Teams of sides, plus a phase order, in the battle state:** chosen. This is general enough for an enemy with allies later.
   - A special "ally" flag on units: every rule that compares sides would need it too.

## Decision

- **Battle state (version 5)** gains `teams` and `order`:
  - `teams` gives each side its team. Sides on one team never attack each other. They pass through each other, exert no zone of control on each other, and help each other: healing, buffs and goals.
  - `order` lists the sides in phase order.
  - Without them, every side is its own team and sides move by number, as before. Arena and sandbox battles are unchanged.
- **Victory:** a team stays in the battle while its **leader**, its lowest-numbered side, has living units.
  - Routing our own units loses the battle even while allies stand.
  - Routing the enemy wins it.
  - Goals are unchanged and still name sides and units: losing 유연 at 백마 still loses the battle.
- **Story battles:**
  - A new `allies` list holds side-2 units.
  - Teams are always ours with the allies, against the enemy.
  - The phase order is ours, then allies, then the enemy.
  - Allies follow their own standing orders, otherwise the AI. Like guests, they earn no experience.
  - Battles name the allied side: 관군, 연합군, 제북군, 백마 수비군, 귀순군, 유비군.
- **Who became an ally:**
  - Chapter 1: 황보숭 and 주준 (장사); 관우, 장비, 유비 and 손견 (사수관); 위자 and 포신 (변수); 포신 (수장).
  - Chapter 3: 유연 (백마); 장합 and 고람 when they defect (관도 결전).
  - Chapter 2: 유비, 관우 and 장비 (하비).
- **Client:**
  - Allies are orange: figures, portraits, name plates, unit cards, the top-bar chip and the sortie map, which marks them "우군".
  - Their phase opens with a "우군 차례" banner and the status shows "우군 행동 중…".
  - Talk and scenes use the same colours.

## Consequences

- **Positive:**
  - The player commands only Cao Cao's own people, as the story says.
  - Allies help on their own, sometimes well, sometimes not, which makes them feel like other lords.
- **Negative / costs:**
  - The player can no longer steer 관우 at 사수관 or 유비's brothers at 하비 to a precise spot.
  - Balance shifts where guests used to be steered; the balance chain is re-run.
- **Follow-ups:**
  - Requests to allies ("hold the gate", "advance") could become a light command later.
  - An enemy with its own allies (a third army) needs no rules change.

## Revisit when

Playtests find allies wasting themselves, or blocking our units, often enough
to frustrate. At that point give them better orders, or let the player ask them to hold.
