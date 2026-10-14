# 0022. Medium number scale: HP 100–600, hits of about 20–120

- **Status:** Accepted; floor amended by [0029](0029-benchmark-lessons-adopted.md)
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0006](0006-typescript-everywhere.md), [0020](0020-five-stats-with-distinct-roles.md),
  [0021](0021-class-families-and-tiers.md), [`docs/remake/RULES.md` §2](../remake/RULES.md#2-attack-resolution-draft)

## Context

Stats are 1–100 ([0020](0020-five-stats-with-distinct-roles.md)) and classes
add base values on top ([0021](0021-class-families-and-tiers.md)). The number
scale decides how much a single stat point matters, how readable the numbers
are on a phone, and how damage is shown.

## Options considered

1. **Medium (HP 100–600):** chosen.
2. **Small (HP 20–60, Fire Emblem-like):** very readable, but stats must be divided down, so single points often change nothing.
3. **Large (HP 1,000+, like the 2018 mobile game):** "epic" feel; hard to read and reason about.

## Decision

- **HP** 100–600: class base + 통솔 bonus + level growth.
- **Attack** = class base + 무력 + weapon. **Defense** = class base + 통솔 + armour.
  Stats enter almost 1:1, so +1 무력 is visible in damage.
- **Base damage** = `max(10, ATK − ⌊DEF / 2⌋)`, then the counter triangle
  (×1200/1000 or ×800/1000) and a critical hit (×1500/1000), each floored. A
  basic unit falls in about 3–5 hits.
- Worked example: 관우 (cavalry, tier 3, Lv 30), ATK 60 + 97 + 25 = 182,
  against tier-2 infantry, DEF 50 + 70 + 20 = 140 → base damage 182 − 70 = **112**;
  cavalry is on the losing side of the triangle against infantry, so the hit deals 89 (133 if critical).

## Consequences

- **Positive:** two or three-digit numbers on the unit card; every stat point counts; close to the 1998 feel.
- **Negative:** large defense gaps hit the 10-damage floor; class base values
  need tuning so that weak attackers still matter (chip damage, stratagems).
- **Follow-ups:** class base tables, terrain defense, counter and double-attack rules, stratagem damage.

## Revisit when

Playtests show fights that are too long (more than 6 hits) or too swingy (one-shots outside critical hits).
