# Lab 07 — Protocol Reconstruction: Engine.IO v4 framing & the JSON event layer

**Goal:** reconstruct an online game's *wire protocol* from static evidence
alone — and learn the layered-protocol thinking that makes every networked app
legible.

---

## 7.1 The evidence that identified the stack

| Evidence | Source | Inference |
|---|---|---|
| `class SocketIOComponent` with `pingInterval:int`, `pingTimeout:double`, `sid`, `ackList`, `eventQueue` | dump.cs (TypeDefIndex 4688) | **Socket.IO client** (the well-known Unity port) |
| literal `ws://{0}/socket.io/?EIO=4&transport=websocket` | stringliteral.json | **Engine.IO v4** — the `EIO=4` query param is the protocol version handshake |
| `EnumData.DuelCommands` StartBattle=0 … EndBattle=8 | dump.cs 6402 | command vocabulary exists as typed enums |
| `PacketUtil.JsonString(object[])` | dump.cs 10912 | payload = positional JSON arrays |
| `ServerSetting.USE_PACKET_ENCRYPT` + `RijndaelManaged` | dump.cs 10916/10919 | optional crypto layer |

**Lesson:** protocol identification is *attribute matching*, not guessing. Each
attribute (URL shape, field names, enum names) votes; when 4/5 agree you have
your protocol.

## 7.2 Engine.IO v4 — the transport grammar

Every WebSocket text frame: `<TYPE_CHAR><payload>`

```
C→S "0"                    (never; only server opens)
S→C "0{sid,pingInterval,pingTimeout,maxPayload}"   handshake JSON
C→S "2"                    PING (v4: client pings — memorize, it flips in v2/v3)
S→C "3"                    PONG
C↔S "4<socket.io packet>"  MESSAGE
S→C "6"                    CLOSE
```

Why a transport layer at all? Because WebSocket gives you *frames*, not
*sessions*: reconnects, heartbeats, buffering policy are transport concerns.
Engine.IO is the session; Socket.IO is the pub/sub semantics on top.

## 7.3 Socket.IO v4 — the event grammar

Inside a `4` frame: `[namespace,][ack_id#]TYPE payload`

| TYPE | Name | Payload |
|---|---|---|
| 0 | CONNECT | (ack from server after client sends `40`) |
| 1 | DISCONNECT | — |
| 2 | EVENT | `["eventname", arg0, arg1, ...]` |
| 3 | ACK | `[ackdata...]` with id |
| 4 | CONNECT_ERROR | error JSON |

Client handshake sequence (what our rebuild does):

```
WS connect → S:"0{...}" → C:"40" → S:"40{sid}" → connected
```

## 7.4 The game's JSON command convention

`MakeNewCommand(EnumData.DuelCommands cmdType, object[] args)` →
`Emit(event, [cmdId, args...])` — positional arrays with the *numeric command
id first*. Recovered vocabulary so far:

- Duel: StartBattle=0, AppearHero=1, ShowStartLabel=2, DuelActions=3,
  DuelAttack1=4, DuelAttack2=5, Dialogue=6, Die=7, EndBattle=8
- Chat: type Channel/Whisper/Notice/Guild/Error(+Text/Emoticon input,
  sub: TREASURE_ENHANCE/GET, MULTIPLAY_SHARE, SKIN_GET)
- User states: Offline/Online/Playing/LeftSeat/Maintaining

**Event-name catalog method:** decompiled `Emit`/`On` call sites (Lab 06)
give the string per site; we log each to `analysis/protocol_events.md` with
the calling class — *the catalog builds itself from xref order*.

## 7.5 Packet encryption (`USE_PACKET_ENCRYPT`)

Crypto inventory in the dump: `RijndaelManaged`, `DecryptedInfo(byte[],
secretKey)`, `FileUtil.GetSalts(str, len)`. Hypothesis: AES-CBC (Rijndael) with
key = derivation(password, salt). Verification plan (Phase 2): decompile
`DecryptedInfo..ctor` @ RVA 0x1A11324 — read the `CipherMode`/`Padding` enum
constants, and find the password literal via xrefs.

**Ethics note (again):** we replicate crypto only to talk to *our own* rebuilt
server; the original servers are dead and were never the target.

## 7.6 The Cloudflare mapping (server side)

```
Node Socket.IO :9080  →  Worker /socket.io route → Durable Object "GameRoom"
                        DO owns: sid table, rooms, heartbeat timers
                        (WS Hibernation API keeps idle rooms nearly free)
```

Our DO speaks the *same grammar* (§7.2–7.3) so any original-protocol client —
including lab-built test harnesses — connects. The protocol doc (PROTOCOL.md)
is the contract; both sides get zod/C# schema checks.

## 7.7 Exercises

- **7-A** Hand-run the handshake: write a 30-line Python that connects to a
  local echo server speaking §7.2–7.3 and completes the dance. Checker:
  `scripts/verify-lab.sh lab07` runs against `server/test/echo_server.py`.
- **7-B** Explain why v4 moved ping responsibility to the client (hint: NAT
  timeouts + battery; who benefits from *sender*-driven keepalive?).
- **7-C** `ackExpirationTime = 180` (original field). Model ack expiry as a
  timing wheel vs sorted list of deadlines; which has better asymptotics for
  10k pending acks? → cross-ref dsa-04.

Solutions: `solutions/07.md`.
