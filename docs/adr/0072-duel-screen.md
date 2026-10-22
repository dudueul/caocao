# 0072. Show a 일기토 on its own screen, and let a story duel's last bout explain its ending

- **Status:** Proposed
- **Date:** 2026-10-22
- **Deciders:** Claude (defaults chosen while building; the owner to confirm)
- **Related:** [0054](0054-main-menu-settings-tips.md), [0063](0063-staged-scenes.md),
  [0071](0071-duels.md), [ARCHITECTURE.md](../remake/ARCHITECTURE.md)

## Context

[ADR 0071](0071-duels.md) decided how duels work. Showing them, and one detail
of story duels, were left open:
- how the duel looks;
- how watched duels behave at the fastest battle speed;
- whether 물러나기 asks before it acts;
- how a story duel's bouts read when the rules bend them to history's ending.

At first, a story duel could show both fighters on 방어 while one still took a
blow, because every bout was bent toward the ending.

## Options considered

1. **Where the duel plays:**
   - **Its own screen over the battle:** chosen. Two facing portraits, HP bars, the bout counter, stance cards, then the end banner.
   - On the battle map, between the two figures: too small to read the stances.
2. **Watched duels at skip speed:**
   - **Not shown; their talk and log still are:** chosen. The player's own duel always shows.
   - Always shown.
3. **물러나기 (Esc):**
   - **Acts at once:** chosen.
   - Asks first.
4. **Story duels' bouts:**
   - **Bend only the last bout, and set the loser's stance so the triangle explains it:** chosen.
   - Bend every bout: no shown stance can be trusted.

## Decision

- **The duel screen** (`DuelView`) overlays the battle. Our officer stands left, the foe right, and portraits change mood with the fight.
  - Stances are three cards: 攻 공격, 強 강공, 防 방어, with keys 1, 2 and 3. 물러나기 (Esc) is hidden in story duels.
  - Each bout reveals both stances, then the clash, the damage and the sounds.
  - The banner reads 승리, 패배, 무승부 or 물러남, with a 전사 seal when a story duel kills.
  - Story duels carry a ★ 역사의 일기토 ribbon, scene duels too.
  - The command menu shows 일기토 only when it is legal, with ★ for a story duel. The forecast gives the acceptance chance (반드시 응한다 for a story duel), both 무력 and the blow each way.
- **Watched duels** play back on the same screen with a skip button. At the fastest battle speed they are not shown. Talk set on a duel plays before the first bout and after the end.
- **물러나기** acts at once.
- **Story duels:** only the last bout is bent. Before it, blows fall as the stances say, except that the side history lets stand never falls. On the last bout, history's loser picks the stance that loses to the winner's, and falls. Every bout shown is one the triangle explains.

## Consequences

- **Positive:**
  - Duels read as events, not log lines.
  - Story duels look fair while still ending as history did.
- **Negative / costs:**
  - A second full-screen layer on top of the battle, to keep working at phone width and in landscape.
- **Follow-ups:**
  - The owner confirms these defaults, or changes them.

## Revisit when

Players skip duels so often that they stop reading them. If so, shorten the
animation, or show watched duels only once per battle.
