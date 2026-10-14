# 0012. Players control a household corps (one seat per corps) in shared battles

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0003](0003-turn-structure.md), [0010](0010-lineage-households.md),
  [0011](0011-world-pacing-and-auto-join.md), [`docs/remake/ARCHITECTURE.md` §4](../remake/ARCHITECTURE.md#4-online-battles)

## Context

World battles mix several players and NPC armies on each side. The control unit
decides battle length, readability on a phone, and how auto-join hands units over.

## Options considered

1. **Household corps:** chosen.
2. **Your character only:** fast and social, but shallow tactics.
3. **Commander controls all:** closest to 조조전, but other players mostly watch.
4. **Mixed by battle type:** more rules and more social hierarchy.

## Decision

- A **seat** is one corps: a player's character plus retainers (3–5 units,
  growing with fame), or an NPC lord's army. Each seat has a controller: `player` or `ai`.
- Up to **6 seats per side** (about 30 units) per grid battle. Larger
  clashes split into **linked fronts** (for example Red Cliffs: river front and
  land front), each its own grid battle. The war outcome weighs the fronts' results.
- Activations interleave by side ([0003](0003-turn-structure.md)), and each
  seat moves one unit per activation.

## Consequences

- **Positive:** short turns per player (about 1–2 minutes a round), clean AI
  handover, and a natural progression path (more retainers = a bigger corps).
- **Negative:** front-linking and weighted outcomes need careful design; big
  anchor battles need matchmaking of seats to fronts.

## Revisit when

Battles with 6 seats per side run too long. Then reduce seats per side or units per seat.
