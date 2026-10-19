# 0052. Synthesize the music and sound effects in code

- **Status:** Proposed
- **Date:** 2026-10-19
- **Deciders:** Claude (default chosen while building; the owner to confirm)
- **Related:** [0002](0002-remake-from-scratch.md), [0005](0005-web-first-distribution.md),
  [0044](0044-modern-battlefield-presentation.md), [0054](0054-main-menu-settings-tips.md)

## Context

The game had no sound. A complete game needs music for its screens and effects
for its actions. Nothing may be copied from Koei or Nexon (ADR 0002), the
first load should stay small (ADR 0005), and nobody on the project composes or
records yet.

## Options considered

1. **Synthesize everything with the Web Audio API:** chosen. Our own notes and
   sounds, no files, a few tens of kilobytes of code.
2. **Licensed or commissioned recordings:** richer, but costs, licences and
   megabytes of downloads before the art direction is settled.
3. **No sound until an audio artist joins:** the game feels unfinished.

## Decision

- `apps/client/src/lib/audio/` builds every sound from oscillators, noise and filters. It has no audio files and fetches nothing.
- **Instruments:** a plucked zither (struck and plucked string models), a bamboo flute with breath and vibrato, a bowed fiddle voice, war drums, a gong, bells, a woodblock and a soft pad. A generated room reverb sits on the music bus.
- **Six original tracks:**
  - title, story, camp, battle, danger (boss fights and night scenes) and ending.
  - All are written as notation in Chinese pentatonic modes (gong, shang, jue, zhi, yu), at 60–128 bpm.
  - Each is a seamless loop with a little seeded variation.
  - A lookahead scheduler plays them on the audio clock. It pauses when the page is hidden.
- **About thirty effects:**
  - Interface: click, confirm, cancel, select, page.
  - Movement and combat: a march, hooves, hit, critical, miss, guard, arrow, stone.
  - One sound per skill school: fire, water, earth, wind, heal, buff, debuff.
  - Battle moments: defeat, a war-drum turn signal, the cut-in swoosh, a horn for reinforcements.
  - Rewards and endings: level up, promotion, coins, equip, and victory and defeat stingers.
  - Effects are panned to where they happen on the map, capped in number and throttled when repeated. They are silent when animations are skipped.
- **Mixing and safety:** master, music and effects volumes (defaults 85 / 70 / 80) go through a compressor and a soft ceiling, so five effects at once stay below clipping.
  - The context starts only on the first tap or key, as browsers require.
  - Without Web Audio every call does nothing.
- `dev/audio-test.html` plays each sound and track and measures their levels offline.

## Consequences

- **Positive:** sound everywhere, no download cost, nothing to license; the
  tracks can be edited like text.
- **Negative / costs:** synthesized instruments sound simpler than recordings;
  tuning the mix by ear is still to do.
- **Follow-ups:** recorded or composed music if the project gains an audio
  artist, keeping the `audio.play` / `audio.music` interface.

## Revisit when

Playtesters find the music tiring or the effects unclear, or an audio artist joins.
