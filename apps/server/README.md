# @tk/server

Cloudflare Worker + Durable Objects running `@tk/rules` as the referee
([ADR 0004](../../docs/adr/0004-server-authoritative-referee.md)). In **P1** it hosts
Arena rooms: one `MatchDO` per room code runs the lobby, the draft, deployment
and the battle ([ADR 0037](../../docs/adr/0037-websocket-json-protocol.md)–[ADR 0041](../../docs/adr/0041-p1-arena-defaults.md)).
The Worker also serves the client build (Workers Static Assets).

| File | Holds |
|---|---|
| `src/room.ts` | The room engine, free of Cloudflare APIs: seats, snake draft, deploy, battle, 45 s + reserve clock, reconnect |
| `src/match.ts` | `MatchDO`: WebSocket Hibernation, storage, alarms, delivery |
| `src/worker.ts` | Routes `/api/rooms/CODE/ws` to the room; everything else to the client build |
| `scripts/smoke.ts` | Plays a full match over real WebSockets against a running server |

```bash
bun run build                      # client build, served by the Worker
bun run server                     # wrangler dev on http://127.0.0.1:8787 (local workerd)
bun apps/server/scripts/smoke.ts   # two scripted players: draft, deploy, battle, reconnect

# clocks 100x faster, and seat B goes idle so the alarms and the AI play its turns:
cd apps/server && bunx wrangler dev --var CLOCK_SCALE:0.01
bun apps/server/scripts/smoke.ts http://127.0.0.1:8787 --idle
```

Deploying needs a Cloudflare account: `bun run --cwd apps/server deploy` after
`wrangler login` (or with `CLOUDFLARE_API_TOKEN` set).
