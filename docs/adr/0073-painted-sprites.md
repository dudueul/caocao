# 0073. Paint every figure as a sprite sheet in the portraits' style, moving to the classic's frame grammar

- **Status:** Accepted
- **Date:** 2026-10-24
- **Deciders:** project owner (found the figures and their movement weird; asked to learn from the existing games; chose painted sprites that move like the classic, used everywhere), Claude (the method)
- **Related:** supersedes [0045](0045-rigged-chibi-characters.md); [0002](0002-remake-from-scratch.md),
  [0042](0042-local-classic-skin.md), [0046](0046-period-accurate-dress.md),
  [0053](0053-painted-portraits.md), [0063](0063-staged-scenes.md), [0064](0064-walkable-camp.md),
  [0070](0070-portraits-from-the-record.md), [CHARACTER_LOOK.md](../remake/CHARACTER_LOOK.md)

## Context

[ADR 0045](0045-rigged-chibi-characters.md) chose rigged 2D chibi figures:
parts painted in code and moved by a skeleton. The owner found the figures and
their movement weird, while liking the portraits painted from the record
([ADR 0070](0070-portraits-from-the-record.md)). They asked us to adopt the
existing games more actively, upscaling, redrawing or smoothing their images.

The 1998 game's sprites and Nexon's are their makers' copyrighted art. An
upscaled, smoothed or traced copy is still their art. Shipping one, or showing
it on the public sandbox page, would distribute it ([ADR 0002](0002-remake-from-scratch.md)).
The owner can already play with them privately through the local classic skin
([ADR 0042](0042-local-classic-skin.md)).

What can be learned from the classic, as with the portraits, is its design:
- how many frames a walk or an attack takes, and how fast they play;
- which key poses read at tile size;
- how big a figure is against its tile (the 1998 figures fit inside it);
- how far an attack frame may reach past it.

## Options considered

1. **Repaint every figure as sprite sheets in the portrait painter's style, moving to the classic's frame grammar:** chosen (recommended).
2. Keep the rig and rework only its timing and poses.
3. Improve the local classic skin for the owner's own play, and leave the published figures as they are.

Where to use them: **everywhere** (battles, staged scenes, the walkable camp),
chosen (recommended) over battle first.

## Decision

- **A sprite painter in code** paints each figure's sheets at runtime, from the same data as its portrait:
  - for a named officer, the portrait trait table: headgear, hair and beard, skin, build, accent colour and signature items;
  - for a troop, its army's kit;
  - the side's dye on the robe.

  Nothing is an image file; sheets are cached per officer, class and side. 관우 in battle is the 관우 of his portrait.
- **The classic's frame grammar**, measured, not copied:
  - three facings (down, up, side), with side frames mirrored for the other way;
  - stand: 1 frame;
  - walk: 2 frames at 4 per second;
  - attack: 4 frames (wind-up, strike, follow-through, recover) ticked at 12 per second, on larger cells so the weapon can reach past the tile. The wind-up holds two ticks, so the blow lands as the field shows the hit;
  - guard, hurt and cheer: 1 frame each;
  - weak: 2 frames at 2 per second.

  Scenes add held poses: bow, kneel, sit, point, fall, and a nod, a shake and a jump played as short sequences.
- **Size:**
  - painted at about twice the displayed size, for sharp edges at today's tiles and on high-density screens;
  - on foot, a figure is about three quarters of a tile wide and 1.25–1.4 tiles tall; mounted, up to about 1.7. That is taller than the classic's figures, which fit inside the tile, so that the painted detail reads; the feet stand in the lower part of the tile.
- **Mounted figures and engines:** cavalry ride horses painted in the same style, and stone-throwers are engines with a crew.
- **The same figure interface** as the rig (size, facing, pose, update, tint), so the battle field, the stage and the camp swap figures without other changes.
- **A private comparison tool** sets our sheets beside the 1998 sprites from the owner's local export, to check proportions and timing. It and its output never enter the repository, the build or the sandbox page.

## Consequences

- **Positive:**
  - Figures match their portraits and read clearly at tile size.
  - Motion has the crisp key-pose rhythm the owner knows, in art that is ours to ship.
- **Negative / costs:**
  - A large painter, a second one next to the portraits.
  - Every pose in the battle and scene lists has to be painted for every facing and kit.
- **Follow-ups:**
  - The rig (`fx/chibi.ts`), its gallery and the `?tokens` flag are removed.
  - Update CHARACTER_LOOK.md and ARCHITECTURE.md.
  - An artist can later replace the painter's sheets with drawn ones in the same layout.

## Revisit when

An artist joins and draws the sheets, or the painted sheets cost too much
memory or start-up time on phones.
