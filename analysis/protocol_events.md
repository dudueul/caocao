# Socket.IO Event Catalog — Phase 2 confirmed call sites

Evidence: decompiled functions in `analysis/decompiled/` (Ghidra 12.1.4 +
Il2CppDumper renames). Socket layer = Socket.IO EVENT packets
(engine=4/type=2/nsp=""/id=-1), payloads are JSON arrays `[event, {...}]`
built by LitJson (`JsonMapper.ToJson`).

## Confirmed (decompiled call sites)

| Event | Direction | Payload | Evidence |
|---|---|---|---|
| `login` | C→S | `{userId: u64, authKey: string}` | `GuildWar.Backend.PImpl.SessionImpl$$UpdateSocketLogin` (retry ≤ 5s cadence) |
| `enterToWorld` | C→S | room params (world/guild ids) | `SessionImpl$$EnterSocketServerWorldRoom` |
| `packet` | C↔S | generic wrapped payload (8 sites) | SessionImpl/Chatting |
| `want_room_id` | C→S | `room_type` | matchmaking (Multiplay) |
| `room_id`, `room_type`, `leaveRoom`, `notFoundRoomId` | C↔S | room lifecycle | Multiplay/SessionImpl |
| `registerSubscribingRooms`, `updateHistory` | C→S | `world_id`, `from_seq`, `to_seq`, `except_seq` | world history sync |
| `broadcastTo` | S→C/C→S | chat room targeting | `Chat.Chatting$$EmitChattingMessage` |
| `connect` / `disconnect` | both | transport | socket.io standard |
| `_guild_create` / `_guild_get` / `_guild_leave` / `_guild_break` / `_guild_apply` | C→S | guild channels | Chatting room events |
| `_chat_getAuthKey` | C→S | chat auth | Chatting |
| `userRegistered` | S→C | registration ack | SessionImpl |
| `error` | S→C | error envelope | SessionImpl |

## REST surface (POST unless noted) — 492 endpoints

Full list: `analysis/rest_endpoints.txt`. Top prefixes:
guild(47) user(43) guildSiegeWar(29) guildWar(25) arena(24) item(19)
multiplay(19) unit(19) raid(17) pvp(14) worldboss(13) battleField(11)
customEvent(11) town(10) competition(9) battle(8) chat(8) iab(8) instance(8) mail(8)

REST payload convention: `[user_id, ...args]` (via
`Session.addUserIdObjects(object[])`), responses are `JsonData`.

## Server topology (confirmed)

- `ServerSetting` per country: JPN (countryCode 100), GLB (10000), + LOCAL/DEV/STAGE
- LiveGLB: `http://nxm-caocao-ww.nexon.com:9080` + CDN `https://nxm-caocao-ww.akamaized.net`, **USE_PACKET_ENCRYPT=true**
- Socket usage limited to: world rooms (GuildWar), chat, matchmaking/multiplay.
  Everything else = REST.
