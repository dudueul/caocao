# Wire Protocol — CAOCAO (client side, evidence-backed)

## 0. Summary

The client talks **Socket.IO v4 protocol (Engine.IO v4) over WebSocket**, with
**JSON** payloads serialized by LitJson. Payload shape is positional JSON arrays:
`[commandId, arg0, arg1, ...]`. Transport-level encryption exists behind
`IServerSetting.USE_PACKET_ENCRYPT` (Rijndael/AES family).

## 1. URL construction

```
ws://{host}/socket.io/?EIO=4&transport=websocket        # literal from stringliteral.json
```
`host` comes from `ServerSetting.instance.GAME_SERVER_HOST_URL`
(see ARCHITECTURE.md §4). Original live hosts used port **9080**.

## 2. Engine.IO v4 framing (client sends / expects)

Engine.IO packets over WS text frames: `<packetTypeChar><payload?>`

| Type | Char | Meaning |
|---|---|---|
| open | `0` | server handshake JSON `{sid, upgrades, pingInterval, pingTimeout, maxPayload}` |
| ping | `2` | client keepalive (in v4 **client** pings) |
| pong | `3` | server reply |
| message | `4` | carries Socket.IO payload |
| close  | `6` | transport close |

Socket.IO v4 payload inside message: `<CONNECT "4" | DISCONNECT "1" | EVENT "2" | ACK "3"><nspace?>[<id?>]<JSON>`

Client state machine in `SocketIOComponent`:
- `pingInterval:int`, `pingTimeout:double`, `reconnectDelay:int`
- `StayConnected(WebSocket)` + `SendPing(WebSocket)` coroutines
- event dispatch: `Dictionary<string, List<Action<SocketIOEvent>>> handlers`,
  acks: `ackList`/`packetId` with `ackExpirationTime`.

## 3. Packet serialization (PacketUtil, TypeDefIndex 10912)

```csharp
public static string JsonString(object[] packet)   // -> "[\"cmd\",arg0,...]"
public static string SerializeArray(string[] args)
public static string SerializeObject(Dictionary<string, string> dic)
```
LitJson `JsonWriter`/`JsonData` throughout (`ITransmittable.Serialize(JsonWriter)`).

Battle commands: `MakeNewCommand(EnumData.DuelCommands cmdType, object[] args)`
— JSON `[cmdEnum, ...]`. Full event-name catalog: pending (Ghidra xrefs to
`Emit`/`On` calls, and `EnumData.*` enums).

## 4. Encryption

`ServerSetting.USE_PACKET_ENCRYPT:bool` per environment. Crypto stack in dump:
`RijndaelManaged`, `DecryptedInfo(byte[] contents, string secretKey)`,
`FileUtil.GetSalts(str, length)`, `GetDecryptedBytes/FromFile/...`.
=> Likely: AES (Rijndael) over the socket.io text payload with a key derived
from a password + salt. Exact mode/derivation: TODO (Ghidra decompile of
`DecryptedInfo..ctor` @0x1A11324, `FileUtil` @0x1A10E10).

## 5. Secondary channels

- `HttpRequest` singleton — REST side calls (notices, NPA auth, CDN).
- `Chatting` singleton (`ChattingMessage`, `ChattingUser`, join/leave events) — chat over same socket.
- `VoiceChatController` + `VoiceNetworkStatus` — voice rooms (libBlueDove/other).
- `MultiplayManager` — matchmaking queue, `ServerInfo`, `RollbackBattle` (state sync strategy).

## 6. Server rebuild mapping (Cloudflare)

| Original | Rebuild target |
|---|---|
| Node Socket.IO v4 server (port 9080) | Worker WS route → **Durable Object** speaking Engine.IO v4 framing (minimal re-implementation) |
| per-region game servers | per-DO sharding by room/region |
| JSON event catalog | TypeScript zod schemas mirroring recovered events |
| USE_PACKET_ENCRYPT | optional AES layer, key derived same as client impl |
| m.nexon.com notice API | Worker REST endpoint |
| CDN (akamaized) | R2 + custom domain |

## 7. Open items

- [ ] event name catalog + payload shapes (Phase 2)
- [ ] packet crypto derivation
- [ ] matchmaking room lifecycle (MultiplayManager decompile)
- [ ] chat channel model
- [ ] FlatBuffers table schemas (.fbs) for `PImpl`/`GuildWarFlatBuffer`

## 8. Heartbeats detail (dump.cs TypeDefIndex 10898)

`HeartBeats : Singleton` — fields: `networkInterval:int`, `aliveInterval:int`,
`lastTimeUserInteraction`, `m_serverNoticeMsg`, `entrustTurn:bool`,
`entrustBattle:bool` (AI-delegation while away), methods `SendHeartBeats()`,
`OnHeartBeats(JsonData response)` (a JSON response handler — the heartbeat is a
request/response event, likely `socket.emit('heartbeats', ack)`), and
`OnApplicationPause` handling. `OnUseOffer(JsonData)` ties offers/ads to the
same channel.

Server rebuild implication: implement `heartbeats` event with a JSON payload
response; track `aliveInterval` for AFK detection; `entrust*` flags drive the
server-side auto-play for absent PvP players (classic KR-MMO concession play).
