# 0055. Our side wears red, the enemy blue

- **Status:** Accepted
- **Date:** 2026-10-19
- **Deciders:** project owner ("our side is red and usually enemy side is blue")
- **Related:** supersedes the side order in [0046](0046-period-accurate-dress.md);
  [0044](0044-modern-battlefield-presentation.md), [0045](0045-rigged-chibi-characters.md),
  [0053](0053-painted-portraits.md), [CHARACTER_LOOK.md](../remake/CHARACTER_LOOK.md)

## Context

ADR 0046 dyed the sides with Han colours and gave side 0 (the player)
indigo and side 1 (the enemy) madder red. The owner pointed out that in the
games this one grows from, our side is red and the enemy usually blue, and
players read the map that way.

## Options considered

1. **Swap the first two dyes: ours madder red, the enemy indigo:** chosen.
   Keeps the period dyes and every other part of 0046.
2. **Colour by army (Cao red, Yuan blue, Yellow Turbans ochre):** truer to
   banners, but the player's colour would change between chapters.
3. **Keep indigo for us:** the owner asked for red.

## Decision

- Side 0 (ours) is madder red, side 1 (the enemy) indigo; sides 2 and 3 stay green and ochre.
- This applies to:
  - the figures' robes, lacing and tassels, and the portraits' robes and backdrops;
  - the map tokens and rings, and the unit card;
  - dialogue name plates, the sortie mini map and the top bar;
  - the `--ally` / `--enemy` UI tokens.
- Colours that carry meaning rather than sides stay as they are: red for danger zones, defeat and delete, green for good.

## Consequences

- **Positive:** matches what players of the original games expect.
- **Negative / costs:** screenshots and the demo video from before look reversed.
- **Follow-ups:** none.

## Revisit when

A mode needs more than four sides, or a chapter wants armies coloured by faction.
