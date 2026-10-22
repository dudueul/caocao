# 0071. 일기토: challenge an officer, pick a stance each bout; story duels end as history did

- **Status:** Accepted
- **Date:** 2026-10-22
- **Deciders:** project owner (asked for 일기토; chose a command plus story duels, a stance each bout, history's endings for story duels, and the six duels), Claude (the rules model and numbers)
- **Related:** [0008](0008-modes-as-main-menu-entries.md), [0020](0020-five-stats-with-distinct-roles.md),
  [0035](0035-stacking-conditions-rolled-durations.md), [0047](0047-battle-goals-and-arrivals.md),
  [0066](0066-allies.md), [RULES.md](../remake/RULES.md), [STORY.md](../remake/STORY.md)

## Context

The owner noticed there was no 일기토. 무력 was defined as "duel power"
([ADR 0020](0020-five-stats-with-distinct-roles.md)). The original game had
a family of duel commands. Duels were planned only as a later mini-mode
([ADR 0008](0008-modes-as-main-menu-entries.md)). In story battles, the
famous duels of the record and the novel were told in talk, not played.

## Options considered

1. **How duels enter battles:**
   - **A 일기토 command and story duels on the same engine:** chosen (recommended).
   - Story duels only.
   - The command only.
2. **How a duel is decided:**
   - **A stance each bout:** chosen (recommended).
   - Watched, decided by stats.
   - Watched, with a withdraw choice between bouts.
3. **The stakes:** the owner said "follow history". Asked what that means, they chose: **story duels end as history did**, deaths included, while a challenge of the player's own never kills anyone.
4. **Which story duels:** **the famous six** (recommended), with allies' duels watched and ours played.

## Decision

**The command.**
- A named officer (not a troop) standing next to an enemy named officer may challenge him instead of attacking.
- The foe accepts or refuses. The chance is 400‰ + 12‰ for each point of 무력 he has over the challenger, + 4‰ for each point of his 무력 over his 지력 (boldness), kept within 50–950‰.
- A refusal ends the challenger's action and shakes the refuser's side within 3 tiles: 약화 for one turn.
- The AI never challenges on its own. Duels come from the player or the story.
- Duels are off in the Arena: no secret stance for one human against another.

**Bouts.**
- A duel is up to 5 bouts, and the battle waits for it.
- Each bout, the challenger picks 공격 (attack), 강공 (power) or 방어 (guard). The foe's stance is drawn from the battle's PRNG by temper: the bold lean to 강공, the clever and the hurt to 방어.
- The triangle decides who lands:

  | Picks | Result |
  |---|---|
  | 강공 against 공격 | 강공 hits (×1.5) |
  | 방어 against 강공 | the guard turns it back (×1.2) |
  | 공격 against 방어 | 공격 wears it down (×1.0) |
  | the same stance | both 공격 hit; both 강공 hit hard; two 방어 trade nothing |

- A clean blow takes 15% of the loser's max HP, + 0.4% per point of 무력 difference, within 6–35%, ±10%.
- Duel blows use the officers' real HP, so a wounded man is a poor duellist.
- The challenger may withdraw between bouts; nobody loses.

**The end.**
- A knockout ends the duel. The loser leaves the field: a player's challenge retreats him and never kills.
- The winner's side within 3 tiles takes heart (고무, 2 turns), and the loser's side within 3 tiles loses it (약화, 2 turns).
- A duel won counts as defeating the loser, for goals and experience, + 30 experience.
- After 5 bouts with both standing, it is a draw.

**Story duels.**
- A battle may list duels: who (one or more challengers, taking bouts in turn), against whom, when (next to the foe, on a round, or when someone arrives), played or watched, how history ends it, and whether the loser dies.
- They end as history did: the blows bend so the side history lets win cannot fall, and history's loser falls by the last bout.
- A played story duel is always accepted and cannot be left.
- A watched duel fires on its own and is fought out at once.

| Chapter | Duel | How |
|---|---|---|
| 1, 사수관 | 관우 cuts down 화웅 | watched, when 관우 (ally) reaches him; the win |
| 1, 사수관 | 장비, 관우, 유비 against 여포 | watched, when 여포 arrives; he withdraws |
| 2, scene | 허저 and 전위, a draw | in the scene where 허저 joins, moved to 196 so that 전위 is alive on every path |
| 3, 백마 | 관우 cuts down 안량 | played: 관우 (a guest of ours) next to 안량 |
| 3, 연진 | 관우 cuts down 문추 | played; 관우 stays one battle longer to be there |
| 4, 백랑산 | 장료 (or 여포, if spared) kills 답돈 | played |

**State.** Battle state version 7 holds the duel under way and the story duels still to come.

## Consequences

- **Positive:**
  - 무력 gets the role ADR 0020 gave it.
  - The famous duels are played, not told.
  - A strong officer can decide a fight by breaking the enemy's heart.
- **Negative / costs:**
  - A pause in the battle that needs its own screen.
  - Story duels bend the dice, so their bouts are drama rather than contest.
- **Follow-ups:**
  - The enemy challenging the player, with a choice to accept or refuse.
  - Duels in the 일기토 mini-mode ([ADR 0008](0008-modes-as-main-menu-entries.md)).
  - More story duels in later chapters.

## Revisit when

Players find duels decide too many battles, or the stance pick feels like a coin
toss. If so, tune the blow or the acceptance odds, or show the foe's temper.
