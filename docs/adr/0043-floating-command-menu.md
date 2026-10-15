# 0043. Float the command menu and the forecast beside the unit

- **Status:** Accepted
- **Date:** 2026-10-15
- **Deciders:** project owner (asked for overlay menus for actions), Claude (layout)
- **Related:** [0028](0028-undo-moves-until-action.md), [0031](0031-svelte-dom-overlay.md),
  [0033](0033-p0-sandbox-client-defaults.md), [0042](0042-local-classic-skin.md)

## Context

In the P0 client the action buttons and the forecast lived in the side panel (the
bottom sheet on phones), away from the unit being ordered. The owner wants the
menus where the action is, as in the classic game: a command window next to the
unit, and skills listed with what they cost and do.

## Options considered

1. **Svelte overlay beside the tile:** chosen. A DOM panel positioned from the
   field's geometry; full text, focus and keyboard for free ([0031](0031-svelte-dom-overlay.md)).
2. **Radial menu drawn in Pixi:** compact and touch-friendly, but icons only,
   and its own focus and accessibility work.
3. **Keep the panel:** no work, but it is what the owner asked to change.

## Decision

- After a unit's move tile is chosen, a **command menu** floats beside that tile:
  공격 · 책략 ▸ · 대기 · 이동 취소. 공격 is disabled with no target in reach.
- **책략 ▸** turns the menu into the unit's skills: school colour, name, MP, and
  one line on what it does (damage or healing, condition and its turn range,
  cleanse, push, ignite, range, area). A skill that can't be cast says why:
  MP 부족, 책략 불가 or 대상 없음.
- While aiming, a small "pick a target · 취소" panel floats by the unit. The
  **forecast** floats beside the target (the chosen one, or the one under the
  pointer), with both officers' portraits when the classic skin is present, and
  확인 / 뒤로.
- A floating panel tries the right of its tile, then the left, below and above,
  and takes the first spot that fits the visible map without covering the units
  it is about.
- Keyboard: the menu takes the focus; ↑/↓ move, Enter picks, → opens the skills,
  ← or Esc closes them, Esc again steps back as before.
- The side panel keeps the unit card, tile info and log, plus a one-line hint.

## Consequences

- **Positive:** orders are given where the eye already is; skills explain
  themselves before they are chosen; phones lose no map height to buttons.
- **Negative:** a floating panel can hide nearby tiles; Esc or 뒤로 clears it.
- **Follow-ups:** client README and ARCHITECTURE §7 describe the menus.

## Revisit when

Playtests show players missing the menu on small phones, or a gamepad needs a
different layout.
