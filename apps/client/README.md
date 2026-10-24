# @tk/client: the game client

Vite + PixiJS battlefield ([ADR 0007](../../docs/adr/0007-top-down-2d-pixijs.md)) with a
Svelte 5 DOM overlay ([ADR 0031](../../docs/adr/0031-svelte-dom-overlay.md)). In **P0**
it was a local battle sandbox (you play 조조군 in 오소 습격 against the AI,
[ADR 0033](../../docs/adr/0033-p0-sandbox-client-defaults.md)); **P1** adds the Arena,
live PvP with friends through the `MatchDO` referee
([ADR 0037](../../docs/adr/0037-websocket-json-protocol.md)–[ADR 0041](../../docs/adr/0041-p1-arena-defaults.md)),
and installs as a PWA ([ADR 0005](../../docs/adr/0005-web-first-distribution.md)).
**P2** adds the title menu and the Story's first chapter, with painted portraits,
synthesized sound, saves and settings
([ADR 0047](../../docs/adr/0047-battle-goals-and-arrivals.md)–[ADR 0054](../../docs/adr/0054-main-menu-settings-tips.md)).

```bash
bun run server     # from the repo root: the Worker + rooms on http://127.0.0.1:8787
bun run dev        # http://localhost:5173 (proxies /api to the server)
bun run build      # dist/ (relative asset paths, so it can be served from any folder)
```

## Routes

| Route | Screen |
|---|---|
| `#/` | Title menu (난세의 간웅): continue, new game (chapter 1), load, chapter select, Arena, practice battle, watch, help, settings, credits |
| `#/story` | The Story (chapter 1 황건의 난, chapter 3 관도): scenes, sortie, battles with talk, results, camp, ending and on to the next chapter ([STORY.md](../../docs/remake/STORY.md)) |
| `#/arena` | Arena lobby: your name, create a room, join by code |
| `#/r/CODE` | An Arena room: invite link → draft → deploy → battle |
| `#/sandbox`, `#/demo` | The 오소 sandbox, and the AI-vs-AI demo |

**Arena:** create a room and send the link. Picks alternate 1 · 2 2 · 1 1 · …
(30 s each); then swap your six on the mini-map and press *준비 완료*. In battle
the top bar shows your activation clock (45 s, then the 3-minute reserve) and a
two-tap *기권*. Closing the tab keeps your seat: the same browser rejoins it.

## Playing

| Do | How |
|---|---|
| Move | tap one of your units (ringed in gold when ready), then a blue tile; tap another tile to change your mind, or *이동 취소* |
| Act | the command menu floats beside the unit: *공격*, *책략 ▸* (the unit's skills with MP and what each does), *대기* or *이동 취소*; tap a target to see the forecast beside it, then tap it again or *확인*. Keys: ↑/↓, Enter, →/← for the skills |
| Read threats | tap an enemy to see where it can move and strike; *위험 범위* (D) shows every tile the enemy can hit next turn |
| Read conditions | ▲ (buff) and ▼ (debuff) on a unit; its card lists each condition with every stack's turns left, and hovering a chip shows what it does |
| Step back | Esc, or *뒤로* |
| Turn | *턴 종료* (E) ends your phase; the AI then plays its side |
| Pace | the speed button cycles 1×, 2×, 4× and 생략 (no animation); *자동 전투* lets the AI play your side too |
| New battle | *새 전투*: turn policy (진영 턴 or 교대 행동), weather, and seed |
| Watch | open with `?demo` or `#demo`: the AI plays both sides at 2×, and a new battle starts after each one |
| Story | read scenes with a tap (or Enter/Space); *자동* and *건너뛰기*. Before a battle pick officers and swap slots on the mini map; in battle the goals card shows how to win and lose (★ defeat, ◆ protect, a gold star to reach). After a win: experience and level-ups, then the camp — gear, promotion from Lv 20, the shop, saving |
| Settings | ⚙ in the battle bar or on the title: volumes, speed, danger zones at start, lighter effects, text speed, tips. ? opens the help |

The forecast shows hit and critical chances, damage (×2 for a double attack),
the counter, *와해* (the strike breaks the target, cancelling its counter), and
the damage pipeline step by step.

## Classic skin (local only)

With your own copy of the 1998 PC 조조전 you can see the demo with its sprites,
portraits and terrain ([ADR 0042](../../docs/adr/0042-local-classic-skin.md)):

```bash
bun run classic:export "/path/to/your/game"   # from the repo root; writes apps/client/classic/ (git-ignored)
bun run dev                                   # the dev server picks it up
```

Units then walk, strike, guard, recoil and cheer with the 1998 animations, the
cards and forecasts show portraits, and `/?map=16#/sandbox` (or *새 전투 → 지도*)
fights the 오소 roster on the 1998 백마 map. Builds, deploys and published pages
never contain the skin; without the folder the client draws our own art. The
exporter smooths the sprites to twice their size; `--raw` keeps the 1998 pixels.

## Look and feel

[ADR 0044](../../docs/adr/0044-modern-battlefield-presentation.md):

- **Terrain:** soft terrain edges and shimmering water.
- **Weather:** the colour grade and ambience follow it: sun shafts, cloud
  shadows, rain with lightning, or leaves on the wind.
- **Combat:** the camera eases in on each fight. Hits throw slashes and sparks
  and shake the screen. Each skill school has its own particles.
- **Cut-ins:** a stratagem opens with a cut-in showing the caster's portrait
  (or a crest).
- **Our own art:** without the skin, the terrain is our procedural painting and
  units are sprite figures painted in code in the portraits' style
  ([ADR 0073](../../docs/adr/0073-painted-sprites.md)), each from the same
  traits as its portrait, dressed as late Eastern Han soldiers and officials
  ([ADR 0046](../../docs/adr/0046-period-accurate-dress.md),
  [CHARACTER_LOOK.md](../../docs/remake/CHARACTER_LOOK.md)). They move to the
  classic's frame grammar: a two-frame step, a four-frame attack (wind-up,
  strike, follow-through, recover), one frame each for guard, hurt and cheer,
  a two-frame slump when weak, facing front, back or sideways.
  `/dev/sprite-gallery.html` on the dev server shows every family, tier-2
  class, army kit and hero in every pose and facing.

| Flag | Effect |
|---|---|
| `?lowfx` | No bloom or water shimmer, lighter ambience (slow devices) |
| `?noskin` | Our own art even when the classic skin is exported |
| `?debug` | `window.tk` (the sandbox store), `window.story` (the Story store), `window.tkField` (`manualClock(true)`, `step(ms)` for frame-by-frame captures) and `window.tkSprites` (the figures' paint time and sheet memory) |

Dev pages (served by `bun run dev`): `/dev/sprite-gallery.html` (every figure, pose and
facing; `?only=anim` moving, `?only=bench` paint times),
`/dev/portrait-gallery.html` (every portrait and mood), `/dev/audio-test.html`
(every sound and track, with levels).

The system's *reduce motion* setting also turns off camera moves, shake and flashes.

## Code

| Path | Holds |
|---|---|
| `src/lib/session.svelte.ts` | The battle store (Svelte 5 runes): state, command log, choices, AI turns |
| `src/lib/field.ts` | Pixi renderer: layers, camera, event scenes (our art, or the classic skin) |
| `src/lib/fx/` | Effect textures, particles, effect recipes, weather, terrain painting, the gatehouse |
| `src/lib/fx/sprite/` | The figures: looks from the portrait traits, key poses, the painter, sheets and their cache, the figure ([ADR 0073](../../docs/adr/0073-painted-sprites.md)) |
| `src/lib/skin.svelte.ts`, `src/lib/classic-battle.ts` | The local classic skin (dev only) and 1998-map sandbox battles |
| `src/lib/text.ts` | Korean UI strings, event-log lines |
| `src/components/` | Top bar (with the Arena clock), unit card, floating command menu and forecast, skill cut-in, portraits, tile info, log |
| `src/lib/online.svelte.ts`, `src/lib/identity.ts` | The Arena room connection; guest key and name |
| `src/screens/` | Title, Story (`story/`: scene, sortie, results, camp, ending), Arena home, sandbox, room (lobby, draft, deploy) and the battle screen |
| `src/lib/story.svelte.ts`, `src/lib/saves.ts`, `src/lib/settings.svelte.ts` | The Story store over `@tk/campaign`; save slots; settings |
| `src/lib/audio/` | Synthesized music and sound effects (Web Audio, no files) |
| `src/lib/fx/portrait.ts`, `src/lib/speakers.ts` | Painted bust portraits with moods; who speaks a line |
| `public/` | PWA manifest, service worker, icons |

`?debug` puts the store on `window.tk`; the canvas carries `data-geometry`
(tile size and offset), so headless browser scripts can play through the UI.
