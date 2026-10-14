# 0030. Adopt danger zones, gambits, break on advantage, and forced movement with hazards

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0011](0011-world-pacing-and-auto-join.md), [0014](0014-scoped-progression-fair-arena.md),
  [0024](0024-stratagems-elements-and-fortune-twists.md), [0029](0029-benchmark-lessons-adopted.md),
  [`docs/remake/MODERN_TECHNIQUES.md`](../remake/MODERN_TECHNIQUES.md)

## Context

Research into recent tactics games ([MODERN_TECHNIQUES.md](../remake/MODERN_TECHNIQUES.md))
rated techniques for fit. The owner adopted four and asked whether these could
be paid features, perhaps through items.

## Decision

1. **Danger zones + boss intent.** `dangerTiles(state, rules, side)` lists
   every standable tile enemies could attack next turn (from the AI's threat
   map), for a client toggle. Bosses and Raid set pieces can telegraph their
   next action; that lands with Raids (P5).
2. **Gambits.** A unit can carry ordered rules `{ when, behavior }`:
   - conditions: always, own HP below ‰, an ally's HP below ‰, an enemy (of a
     family) in reach, can kill;
   - behaviours: attack best, attack the lowest-HP enemy, attack a family,
     heal an ally, retreat to a healing tile, advance, hold.

   `chooseCommand(state, rules, gambits)` narrows each unit's commands by its
   first applicable gambit and falls back to normal scoring. Gambits drive
   absent seats in World battles ([0011](0011-world-pacing-and-auto-join.md))
   and auto-battle on cleared Story maps; live play stays manual.
3. **Break on advantage.** A landed strike whose relation‰ is at least
   `breakThreshold` (1200) breaks the target. A broken unit can't counter until
   it acts again. Previews show `breaks`, and the AI expects no counter when its
   strike would break.
4. **Forced movement + hazards.** Stratagems may `push` targets away from the
   caster. A pushed unit stopped by a wall or impassable tile takes
   `pushWallDamage` (30). One stopped by another unit takes `pushUnitDamage`
   (20), and so does that unit. Fire stratagems may `ignite`: grass and forest
   in the area and next to it burn for `burnRounds` (1), dealing `burnDamage`
   (20) at the start of the next round.

**Fairness (owner's question about paid features).** All four are core rules
and information, free for every player in every mode. Selling them would be
pay-to-win: danger zones are information, gambits are play while you're away,
and both decide Arena and Living History outcomes ([0014](0014-scoped-progression-fair-arena.md)).
If monetization ever comes, it is cosmetic only. Items **earned in play** may
add extra gambit slots or behaviours in non-competitive Story play, never in
Arena or World.

## Consequences

- **Positive:** threats are readable at a glance; absent friends' corps fight
  by their owners' rules; the relation matrix matters in every exchange;
  terrain and elements gain active counterplay.
- **Negative:** more state (broken flag, burning tiles) and more events for the
  client to animate. Gambits need an editor UI (P3).
- Covered by `packages/rules/test/techniques.test.ts` (10 cases).

## Revisit when

Break makes advantage attacks too dominant in PvP, or gambit-driven absent
seats prove noticeably stronger or weaker than live play.
