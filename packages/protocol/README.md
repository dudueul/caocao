# @tk/protocol

Typed messages between the client and the `MatchDO` referee
([ADR 0037](../../docs/adr/0037-websocket-json-protocol.md)): plain WebSocket, one
JSON object per frame, tagged by `t`.

| Direction | Messages |
|---|---|
| client → server | `hello` (join or rejoin: room, device key, name, last batch seen, create), `pick`, `arrange`, `ready`, `command`, `concede`, `ping` |
| server → client | `welcome` (your seat, the room), `room` (lobby, draft, deploy, result), `snapshot` (state + clock), `batch` (one applied command: `seq`, events, state, clock, `by: player \| ai`), `rejected`, `pong` |

- `parseClientMessage()` rebuilds every client message from scratch and drops
  anything malformed or larger than 4 KB; the referee never sees raw input.
- `redact()` strips the `Prng` from states before they leave the server, so a
  client can't predict rolls.
- Room codes are 6 characters without look-alikes (`ROOM_ALPHABET`); device keys
  are 128-bit hex ([ADR 0038](../../docs/adr/0038-guest-identity-device-key.md)).
