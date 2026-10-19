# 0053. Paint every officer's portrait in code

- **Status:** Proposed
- **Date:** 2026-10-19
- **Deciders:** Claude (default chosen while building; the owner to confirm)
- **Related:** [0002](0002-remake-from-scratch.md), [0042](0042-local-classic-skin.md),
  [0045](0045-rigged-chibi-characters.md), [0046](0046-period-accurate-dress.md),
  [0049](0049-story-chapter-guandu.md), [CHARACTER_LOOK.md](../remake/CHARACTER_LOOK.md)

## Context

Story scenes need a speaker portrait, and the unit card, forecast and draft
show one too. Until now the only portraits were the classic skin's faces, which
exist only on the owner's machine (ADR 0042); every published build had none.
Open question 9 (who draws) is still open, and the figures on the field are
already painted in code (ADR 0045).

## Options considered

1. **Bust portraits painted in code from a trait table:** chosen. Our own art,
   available everywhere, consistent with the painted figures.
2. **Generated or commissioned images now:** better art, but an asset pipeline
   and licensing before the art direction is settled (question 9).
3. **No portraits in published builds:** scenes read as text only.

## Decision

- `lib/fx/portrait.ts` paints a 256 × 320 bust in three-quarter view. The style matches the field figures:
  - flat cel fills with one shadow and one highlight, ink outlines;
  - light from the upper right and a rim light;
  - a backdrop in the side's Han dye.
- **A trait table** gives every named officer a face shape, eyes, brows, nose, beard, hair, skin, age, headwear and personal marks. Each mark comes from history or the public-domain novel:
  - 하후돈's eye patch;
  - 관우's long beard and head-scarf;
  - 유비's long ear lobes;
  - 순우경's red nose;
  - 황충's white beard.
- **Clothing follows the class** and the period dress of ADR 0046. Tier-2 classes get gold trim.
- **Troops** (`yuan-…`, `cao-…`) get a soldier's face from a hash of their id.
- **Five moods** (calm, smile, angry, grim, hurt) change the brows, eyes and mouth; story lines choose one.
- Each portrait is painted once per officer, class family, tier, side and mood, then cached as an image.
- The classic skin's face still wins on the owner's machine.
- `dev/portrait-gallery.html` shows every officer in every mood.

## Consequences

- **Positive:** every build has portraits, with no assets to license or ship.
- **Negative / costs:** a large painter module (a few thousand lines of drawing
  code); hand-tuned faces to maintain until an artist draws them.
- **Follow-ups:** replace with drawn portraits when question 9 is settled,
  keeping the same `portraitUrl` interface and moods.

## Revisit when

An artist or an art pipeline is chosen, or players find the faces hard to tell apart.
