# 0070. Paint each officer from the record: their own headgear, build, turn, beard and props

- **Status:** Proposed
- **Date:** 2026-10-21
- **Deciders:** project owner (said the portraits all look alike; asked to benchmark other Three Kingdoms games and mix historical facts into the looks), Claude (the method and the invented looks, to confirm)
- **Related:** [0045](0045-rigged-chibi-characters.md), [0046](0046-period-accurate-dress.md),
  [0053](0053-painted-portraits.md), [0055](0055-our-side-red.md),
  [CHARACTER_LOOK.md](../remake/CHARACTER_LOOK.md), [PORTRAITS.md](../remake/PORTRAITS.md)

## Context

The portraits painted in code ([ADR 0053](0053-painted-portraits.md)) gave
every officer a row of traits, but the sheet reads as one face:
- every head is drawn at the same point, the same size and the same turn;
- eyes, nose and mouth sit at fixed points on every face;
- the class chose the headgear, so every infantry or cavalry officer wore the same helmet;
- beards grew from one shape and drew as a black wedge;
- the side's colour filled every background.

The owner asked for a benchmark of other Three Kingdoms games and for looks
grounded in the histories. We studied, for principles only and copying no art:
- Koei's 三國志 series (XI–XIV), including its artists' interviews;
- Dynasty Warriors 7–9;
- Total War: Three Kingdoms;
- Koei's 1990s tactics games;
- arcade and mobile games.

We also read what 三國志 with 裴松之's notes, 後漢書 and 三國演義 say about each officer's looks.

## Options considered

1. **Re-tune the existing traits:** cheap, but the shared head template, the fixed features and class headgear would still make every face read alike.
2. **Widen the painter and repaint everyone from the record:** chosen. This is what the benchmark shows working in games with large casts.
3. **Hand-drawn art:** the long-term plan for an artist ([ADR 0045](0045-rigged-chibi-characters.md)), but not available now.

## Decision

**Principles**, in our words, from the benchmark:
- **Silhouette first:** a top shape (headgear) and a back shape (weapon, plumes, parasol) that no other hero of the same faction has. Checked at 48 px in grey.
- **One recorded cue per person, drawn big**, before anything from the novel. Every trait is tagged `record`, `novel`, `later lore` or `our reading`.
- **Headgear by office and person, not by class**, from the Han dress treatise (續漢書 輿服志).
- **Height, build and head turn are parameters.** Tall men's caps touch the top of the card, short men have sky above them, and heads turn left, right or front.
- **Age by proportion first**, then lines and grey.
- **A library of beards drawn from strands**, with the down-hanging Han moustache as the common grammar. Shapes such as 관우's beard bag and 장비's tiger whiskers are reserved for one person each.
- **Faction by dye and landscape, hero by accent colour and prop.** The robe keeps the side's dye ([ADR 0055](0055-our-side-red.md)). A reserved list keeps signature items off generated soldiers.
- **No caricature of any people.** The 오환 and other northern peoples are told apart by recorded dress and hair only.

**Corrections from the record.** For example:
- 유비 had no beard.
- 조조 wore the 帢 cap he devised and was short.
- The 오환 shaved their heads.
- 원소 wore a silk headcloth.
- 감녕 wore plumes and bells.
- 동탁 wore two quivers.

The full table, with sources, is in [PORTRAITS.md](../remake/PORTRAITS.md) and [CHARACTER_LOOK.md](../remake/CHARACTER_LOOK.md).

## Consequences

- **Positive:**
  - Heroes are recognisable at sortie-card size.
  - Looks carry history the player can learn from.
  - The tags would allow a "records only" look later.
- **Negative / costs:**
  - A much larger painter.
  - Many looks are still our readings, where the record is silent; the owner should confirm them.
- **Follow-ups:**
  - Confirm the invented looks.
  - An artist can use the same table as a brief.

## Revisit when

Playtesters still confuse two officers of one faction at card size, or the
owner rejects a reading. If so, change that officer's row, not the method.
