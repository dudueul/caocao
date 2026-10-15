# 0045. Draw our characters as rigged 2D chibi figures at tile scale

- **Status:** Accepted; the class looks are superseded by [0046](0046-period-accurate-dress.md)
- **Date:** 2026-10-15
- **Deciders:** project owner (chose the direction and the scale), Claude (rig and interim art)
- **Related:** [0007](0007-top-down-2d-pixijs.md), [0042](0042-local-classic-skin.md),
  [0044](0044-modern-battlefield-presentation.md); open question 9 (art pipeline)

## Context

[0044](0044-modern-battlefield-presentation.md) modernized everything around the
characters; the characters themselves were still 1998 sprites (local only) or
our medallion tokens. We showed the owner four live samples of what our own
characters could become: rigged 2D chibi, HD-2D pixel art, 3D chibi, and
portraits with tokens. The owner chose the rigged 2D chibi. The owner wanted it
at the battle map's own scale and viewpoint, not the large side-on close-up of
the sample.

## Options considered

1. **Rigged 2D HD chibi:** chosen by the owner.
2. **HD-2D pixel art:** cheapest and closest to 1998, but reads as retro.
3. **3D chibi:** most current, but needs a 3D engine and 3D art for every unit.
4. **Portraits and tokens:** cheap and clean, but no animated characters.

## Decision

- **Scale and view:** a figure stands on one tile, seen from the map's
  bird's-eye three-quarter view, like the 1998 sprites.
  - Big heads (about 0.37 of a tile wide), feet near the bottom of the tile.
  - The head may rise a little above the tile.
  - A team ring at the feet and the HP bar below it.
- **Facings:** front, back and side. Left is the side view mirrored.
  Idle figures face the enemy.
- **Rig:**
  - A few painted parts on a small skeleton: legs, torso, head, both arms, the
    weapon in the hand, and a shield or bow in the other hand.
  - Optional parts: a cape, and a horse for cavalry or a cart and throwing arm
    for the catapult.
  - Poses are procedural: ready (breathing), walk, attack, guard, hurt, slumped
    (wounded or confused) and cheering.
  - Each class has its own attack: a sword slash, a spear lunge from horseback,
    a drawn bow, a fan stroke, a punch, and the catapult's throw.
  - Arrows and stones fly to the target.
- **Class looks:**
  - Infantry wear helmets and capes and carry a sword and shield.
  - Archers wear feathered caps, with a bow and quiver.
  - Cavalry wear plumed helmets and carry a spear on horseback.
  - Strategists wear scholar's caps and long robes and carry a feather fan.
  - Martial artists wear headbands and arm wraps.
  - The catapult is a light cart.
- **Colour and variety:** the side's colour dresses every figure. Each officer
  gets a hair style, a beard or not, and a skin tone from their id.
- **Interim art:**
  - Until an artist draws the parts, they are painted in code. This is our own
    art, so public builds use it; tokens remain behind `?tokens`.
  - The local classic skin ([0042](0042-local-classic-skin.md)) still takes
    precedence where it exists.
  - `/dev/rig-gallery.html` on the dev server shows every class, side, facing
    and pose.

## Consequences

- **Positive:**
  - Friends on the deployed build see characters that walk, swing, shoot, flinch
    and cheer, in the chosen style, instead of discs.
  - Drawn parts can replace the painted ones one class at a time, without
    touching the skeleton or the battle code.
- **Negative / costs:**
  - Code-painted parts are a stand-in; at large zoom they look simple.
  - Front and back views of the horse and the cart are rough.
- **Follow-ups:**
  - Open question 9 narrows to the production pipeline: the rig format and who
    draws.
    - Spine's runtimes need a Spine editor licence.
    - Our own JSON of parts and bones is free and is what the code does today.
  - Portraits for cut-ins and cards in the same style.

## Revisit when

An artist starts on the parts (pick the rig format then), or playtests show the
figures are hard to tell apart at phone size.
