# 0062. Let the map change during a battle: shifts set off by a round or a reached tile

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (chose that the player opens the sluice at 하비), Claude (the rules model)
- **Related:** [0006](0006-typescript-everywhere.md), [0047](0047-battle-goals-and-arrivals.md),
  [0059](0059-chapter-two-lu-bu.md), [RULES.md](../remake/RULES.md)

## Context

At 하비 in 198 Cao Cao flooded Lü Bu's city by damming the 기수 and 사수.
The owner was offered three options:
- the player opens the sluice;
- the flood comes on a fixed round;
- the map is already flooded.

The owner chose **open the sluice yourself**, which needs terrain that
changes during a battle. Until now the battle map was fixed, except for fire.
Chapter 2 also needs two smaller things: escapes that only 조조 can win by
reaching a tile, and streets already burning when the battle at 복양 starts.

## Options considered

1. **Shifts in the battle state, set off by a round or by a side reaching tiles:** chosen. The same tool serves floods, gates opened from inside, and later bridges that burn.
2. **Scripted map swaps in the client:** no; the rules would not know about the water.
3. **A flood on a fixed round:** simpler, but the owner wanted the player to cause it.

## Decision

- **State:** `BattleState` goes to version 4, adding `shifts`, the changes still to happen. A battle setup can list `shifts` and `burning` (tiles on fire at the start).
- **A shift:**
  - It has an id, a trigger and tile changes.
  - Its `damage` is a ‰ share of max HP.
  - Triggers:
    - `{ kind: "round", round }`: the start of a round, from round 2.
    - `{ kind: "reach", side, cells, units? }`: a living unit of that side, one of `units` if listed, stands on a trigger tile.
- **When a shift fires:**
  - The tiles change, and water puts out fire on them.
  - Events: `shifted`, then `swept` for each unit carried off.
  - A unit left on ground it can't stand on is carried to the nearest tile it can stand on, the same search arrivals use. It loses its `damage` share, never its last point.
- **Checks:** shifts are checked after every action, and after arrivals at the start of a round. A shift can set off another.
- **Reach goals** may name `units`. 복양 and 완성 are won only when 조조 himself reaches the gate.
- **AI:** one runner per side, the unit nearest by movement cost, is pulled toward reach goals and reach-trigger tiles. Our auto-battle opens the sluice and gets 조조 out.
- **Client:**
  - Trigger tiles get the ★ of reach goals until the shift fires.
  - The flood repaints the terrain in place, with splashes and a rumble. Swept units slide to dry land.
  - Story talk can wait for `{ at: "shifted", shift }`.
- **Hashes:** state hashes change with the version bump, which CLAUDE.md allows. No golden state hash is pinned.

## Consequences

- **Positive:**
  - Set-piece battles without client-side tricks.
  - Shifts replay and hash like everything else.
- **Negative / costs:**
  - One more thing to think about when designing maps: where swept units land.
  - The AI only knows one runner per side.
- **Follow-ups:** RULES.md section on shifts; a burning bridge or a breached dam in later chapters.

## Revisit when

A battle needs a shift that depends on more than a round or a tile: a unit defeated, a goal half met.
