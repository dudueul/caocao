# 0020. Officers have five stats, each with one distinct role

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0006](0006-typescript-everywhere.md), [0010](0010-lineage-households.md),
  [0014](0014-scoped-progression-fair-arena.md), [`docs/remake/RULES.md` §1](../remake/RULES.md#1-officer-stats)

## Context

The stat model is the first thing the rules engine needs, and it shapes every
formula, the AI, the unit cards on a phone, PvP balance and Living History
careers. The 1998 조조전 uses 5 officer stats feeding 5 derived battle values.
The 2018 mobile game kept 5 stats with very large numbers. Recent games go
lighter: 3 stats (*Heroes' Vow*) or none (*Three Kingdoms HEROES*).

## Options considered

1. **3 stats + class profile:** lighter; recommended at first, not chosen.
2. **5 stats:** chosen, with the owner's request that each stat has a clear job
   ("LUCK for instance boost accuracy or impact?").
3. **Stat-free, board-like.**
4. **3 stats + traits only.**

For the role mapping, the alternatives were: luck also driving accuracy (it
would dominate offense and leave 민첩 defensive), or a derived-value layer as
in 1998 (more numbers on the card).

## Decision

Officers have five stats, integers from 1 to 100. Each owns one role in battle
and one in the World engine:

| Stat | Battle | World / Living History |
|---|---|---|
| **무력** Martial | attack power, counter damage, duel power | duels, feats of arms |
| **통솔** Command | defense, troop HP bonus, aura for adjacent allies | corps size (retainers), morale, army upkeep |
| **지력** Intellect | stratagem power and resistance, MP | plots, diplomacy, administration |
| **민첩** Agility | hit and evasion, double attack, initiative | march speed, scouting |
| **운** Fortune | critical hits (impact) and their avoidance, status resistance | the fate of a defeated officer: escape, capture or death |

Formulas use the stats directly (no derived-value layer). All maths is in
integers and permille (‰) ([0006](0006-typescript-everywhere.md)). Starting tuning:

- `hit‰ = clamp(850 + 6·(민첩a − 민첩d) − terrainEvasion‰, 250, 990)`
- `crit‰ = clamp(40 + 4·(운a − 운d), 10, 350)`; a critical hit deals ×1.5 (`×1500/1000`)
- `escape‰ = clamp(600 + 4·운, 0, 950)` when an officer's unit is defeated; otherwise
  the officer is captured or killed (the split depends on the battle and the captor)

## Consequences

- **Positive:** every stat matters, both in battle and in the World. 운 gives a
  natural link between battles and the lineage system ([0010](0010-lineage-households.md)).
  Five stats keep the 조조전 identity.
- **Negative:** more numbers than the 3-stat option, so unit cards must lead
  with the two or three that matter for the action in hand. More balance surface.
- **Follow-ups:** class system (what classes add on top of stats), HP and damage
  scale, terrain, Arena normalization rules ([0014](0014-scoped-progression-fair-arena.md)).

## Revisit when

Playtests show a stat that players ignore, or 운-based deaths feel unfair.
