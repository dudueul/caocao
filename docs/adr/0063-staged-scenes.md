# 0063. Act story scenes out on small tile maps, from a script

- **Status:** Accepted
- **Date:** 2026-10-20
- **Deciders:** project owner (asked for scenes that show characters moving, talking and acting like the original games; chose staged scenes), Claude (the script format and the player)
- **Related:** [0045](0045-rigged-chibi-characters.md), [0049](0049-story-chapter-guandu.md),
  [0053](0053-painted-portraits.md), [0060](0060-story-choices-and-flags.md),
  [0064](0064-walkable-camp.md), [STORY.md](../remake/STORY.md)

## Context

Scenes between battles were a painted backdrop with a dialogue box and
portraits. The owner asked: "between battles, where scenarios are, can you
actually simulate the characters' movements, talks, actions like the
original games?" The 1990s Koei tactics games played their story on small
maps: officers walk in, bow, argue, react with a bubble over the head, and
sometimes cross blades, with the camera following.

## Options considered

1. **Every scene staged, and the camp walkable too:** chosen (ADR 0064 covers the camp).
2. **Staged scenes only**, keeping the camp a menu.
3. **Stage only the big moments** (복양's fire, 전위 at the gate, 백문루), keeping backdrops for the rest.

## Decision

- **A scene** is a `stage`, a `cast` and a `script`.
  - **The stage:** tile rows, using the battle terrain codes plus three floors (`_` boards, `=` paving, `r` carpet). It has light (day, dusk or night), weather, and props.
  - **Props:** throne, table, pillar, banner, tent, brazier, screen, lantern, gate, horse, cart, grain, rack, well, fence, chest, statue, bed, sluice.
  - **The cast:** officers or extras in an army's colours, on stage or waiting offstage.
- **Script commands:**
  - Talk: `say`.
  - Movement: `walk`, `enter`, `leave`, `place`, `remove`.
  - Acting: `face`, `emote`, `act` and `strike`.
    - Emotes: ! ? … sweat, anger, heart, note, idea, tear.
    - Poses: stand, bow, kneel, down, nod, shake, jump, attack, hurt, guard, cheer.
  - Staging: `camera`, `wait`, `sound`, `fx`, `fire`, `tiles`, `light`.
  - Branching: `choice` (ADR 0060).
  - Grouping: `all` runs steps together.
- **Conditions:** any command or line can carry a flag condition.
- **Writing:** short helpers in `packages/content/src/stage.ts` keep scripts compact (`say`, `walk`, `emote`, `all`, `only`…). Shared stages cover a hall, a command tent, a street, a field, a river bank and a campsite. Scenes are written in our own words.
- **The player:**
  - It lives in `apps/client/src/lib/stage/`, separate from the battle field.
  - Figures are our rigged chibis, with new poses.
  - Ground uses our terrain painter, plus painted floors and props.
  - The dialogue box and portraits are reused.
- **Skip** runs ahead to the next choice, never past it.
- **The validator** checks every cast name, cell, speaker and choice, along every flag path.
- **Older scenes:** scenes written as plain lines get a simple automatic staging (`staged()`). All three chapters are now staged by hand.

## Consequences

- **Positive:** the story is shown, not only told; the same figures appear in scenes and battles.
- **Negative / costs:**
  - Every scene now needs blocking as well as lines, several times the writing per scene.
  - The scene player is a second Pixi renderer to keep fast on phones.
- **Follow-ups:**
  - Staged moments inside battles (units walking and emoting during talk).
  - A scene editor if writers other than Claude join.

## Revisit when

Writing and blocking scenes becomes the bottleneck for new chapters, or the
scene player runs slowly on low-end phones.
