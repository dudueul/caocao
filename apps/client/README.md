# @tk/client: battle sandbox

Vite + PixiJS battlefield ([ADR 0007](../../docs/adr/0007-top-down-2d-pixijs.md)) with a
Svelte 5 DOM overlay ([ADR 0031](../../docs/adr/0031-svelte-dom-overlay.md)). In **P0**
it was a local battle sandbox (you play 조조군 in 오소 습격 against the AI,
[ADR 0033](../../docs/adr/0033-p0-sandbox-client-defaults.md)); **P1** adds the Arena,
live PvP with friends through the `MatchDO` referee
([ADR 0037](../../docs/adr/0037-websocket-json-protocol.md)–[ADR 0041](../../docs/adr/0041-p1-arena-defaults.md)),
and installs as a PWA ([ADR 0005](../../docs/adr/0005-web-first-distribution.md)).

```bash
bun run server     # from the repo root: the Worker + rooms on http://127.0.0.1:8787
bun run dev        # http://localhost:5173 (proxies /api to the server)
bun run build      # dist/ (relative asset paths, so it can be served from any folder)
```

## Routes

| Route | Screen |
|---|---|
| `#/` | Home: your name, create an Arena room, join by code, sandbox |
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
| Act | *공격*, a stratagem (화계, 풍계, 혼란, 치료), or *대기*; tap a target to see the forecast, then tap it again or *확인* |
| Read threats | tap an enemy to see where it can move and strike; *위험 범위* (D) shows every tile the enemy can hit next turn |
| Read conditions | ▲ (buff) and ▼ (debuff) on a unit; its card lists each condition with every stack's turns left, and hovering a chip shows what it does |
| Step back | Esc, or *뒤로* |
| Turn | *턴 종료* (E) ends your phase; the AI then plays its side |
| Pace | the speed button cycles 1×, 2×, 4× and 생략 (no animation); *자동 전투* lets the AI play your side too |
| New battle | *새 전투*: turn policy (진영 턴 or 교대 행동), weather, and seed |
| Watch | open with `?demo` or `#demo`: the AI plays both sides at 2×, and a new battle starts after each one |

The forecast shows hit and critical chances, damage (×2 for a double attack),
the counter, *와해* (the strike breaks the target, cancelling its counter), and
the damage pipeline step by step.

## Code

| Path | Holds |
|---|---|
| `src/lib/session.svelte.ts` | The battle store (Svelte 5 runes): state, command log, choices, AI turns |
| `src/lib/field.ts` | Pixi renderer and event animations (placeholder shapes) |
| `src/lib/text.ts` | Korean UI strings, event-log lines |
| `src/components/` | Top bar (with the Arena clock), unit card, action menu, forecast, tile info, log |
| `src/lib/online.svelte.ts`, `src/lib/identity.ts` | The Arena room connection; guest key and name |
| `src/screens/` | Home, sandbox, room (lobby, draft, deploy) and the battle screen |
| `public/` | PWA manifest, service worker, icons |

`?debug` puts the store on `window.tk`; the canvas carries `data-geometry`
(tile size and offset), so headless browser scripts can play through the UI.
