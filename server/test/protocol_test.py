#!/usr/bin/env python3
"""protocol_test.py — Milestone 2 acceptance: full recovered flow over the wire.

login → enterToWorld → broadcastTo (chat) → leaveRoom, with the exact event
names/payload shapes recovered in Phase 2 (analysis/protocol_events.md).
Sequential frame reads (echo_client style) — no background pump.

Usage: protocol_test.py [port]   (default 8787, wrangler dev)
"""
import asyncio, base64, hashlib, json, os, sys

MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"


def encode_frame(text: str) -> bytes:
    data = text.encode()
    head = bytearray([0x81])
    n = len(data)
    if n < 126: head.append(0x80 | n)
    elif n < 65536: head.append(0x80 | 126); head += n.to_bytes(2, "big")
    else: head.append(0x80 | 127); head += n.to_bytes(8, "big")
    mk = os.urandom(4)
    head += mk
    return bytes(head) + bytes(b ^ mk[i % 4] for i, b in enumerate(data))


async def recv_text(reader):
    b1 = (await reader.read(1))[0]
    b2 = (await reader.read(1))[0]
    ln = b2 & 0x7F
    if ln == 126: ln = int.from_bytes(await reader.read(2), "big")
    elif ln == 127: ln = int.from_bytes(await reader.read(8), "big")
    data = b""
    while len(data) < ln:
        chunk = await reader.read(ln - len(data))
        if not chunk: raise ConnectionError("eof")
        data += chunk
    return data.decode()


class Sio:
    def __init__(self, reader, writer):
        self.reader, self.writer = reader, writer

    async def handshake(self):
        key = base64.b64encode(os.urandom(16)).decode()
        self.writer.write((
            "GET /socket.io/?EIO=4&transport=websocket HTTP/1.1\r\n"
            "Host: x\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
            f"Sec-WebSocket-Key: {key}\r\nSec-WebSocket-Version: 13\r\n\r\n").encode())
        status = await self.reader.readline()
        assert "101" in status.decode()
        while (await self.reader.readline()) not in (b"\r\n", b"\n", b""):
            pass
        hs = json.loads((await recv_text(self.reader))[1:])   # engine.io OPEN
        self.writer.write(encode_frame("40"))                 # socket.io CONNECT
        ack = await self.expect_io()                          # 40{sid} → expect_io strips "4"
        assert ack.startswith("0"), ack
        print(f"  handshake ok (sid={hs['sid'][:8]}...)")

    def emit(self, event_name, payload=None):
        body = json.dumps([event_name] + ([payload] if payload is not None else []))
        self.writer.write(encode_frame("42" + body))

    async def expect_io(self):
        """Read frames until a socket.io message ('4...'); return its payload."""
        while True:
            msg = await asyncio.wait_for(recv_text(self.reader), 5)
            if msg and msg[0] == "4":
                return msg[1:]


async def run(port):
    # 0. REST login (Session/HttpRequest path) — obtain the real authKey
    import urllib.request
    req = urllib.request.Request(
        f"http://127.0.0.1:{port}/user/login",
        data=json.dumps(["guest_proto_test"]).encode(),
        headers={"content-type": "application/json"}, method="POST")
    with urllib.request.urlopen(req, timeout=8) as r:
        rest = json.load(r)
    print("  REST login:", rest["userId"])

    reader, writer = await asyncio.open_connection("127.0.0.1", port)
    c = Sio(reader, writer)
    await c.handshake()

    # 1. socket login — [RE] SessionImpl$$UpdateSocketLogin
    c.emit("login", {"userId": rest["userId"], "authKey": rest["authKey"]})
    ev = await c.expect_io()
    print("  login reply:", ev)
    assert ev.startswith('2["login"') and "guest_proto_test" in ev, ev

    # 2. enterToWorld — [RE] SessionImpl$$EnterSocketServerWorldRoom
    WORLD = int(os.urandom(2).hex(), 16) % 100000  # hermetic: unique room per run
    c.emit("enterToWorld", {"world_id": WORLD})
    ev = await c.expect_io()
    print("  enterToWorld reply:", ev)
    assert f"world:{WORLD}" in ev, ev

    # 3. broadcastTo — [RE] Chat.Chatting$$EmitChattingMessage
    # NOTE: produces TWO events: the room broadcast (chattingMessage) then the
    # sender's ack reply. Step boundaries must consume both, in wire order.
    c.emit("broadcastTo", {"room_id": f"world:{WORLD}", "type": 0, "msg": "hello 조조"})
    ev = await c.expect_io()
    print("  chat broadcast:", ev)
    assert "chattingMessage" in ev and "조조" in ev, ev
    ev = await c.expect_io()
    print("  chat ack:", ev)
    assert "success" in ev, ev

    # 4. leaveRoom
    c.emit("leaveRoom", {"room_id": f"world:{WORLD}"})
    ev = await c.expect_io()
    print("  leaveRoom reply:", ev)
    assert f"world:{WORLD}" in ev, ev

    # 5. economy: getUserData carries catalog; buy+sell round-trip
    import urllib.request
    req2 = urllib.request.Request(
        f"http://127.0.0.1:{port}/item/craftItem",
        data=json.dumps([rest["userId"], 10001]).encode(),
        headers={"content-type": "application/json"}, method="POST")
    with urllib.request.urlopen(req2, timeout=8) as r2:
        buy = json.load(r2)
    assert buy.get("success"), buy
    print("  buy:", buy)
    req3 = urllib.request.Request(
        f"http://127.0.0.1:{port}/item/sellItems",
        data=json.dumps([rest["userId"], [10001]]).encode(),
        headers={"content-type": "application/json"}, method="POST")
    with urllib.request.urlopen(req3, timeout=8) as r3:
        sell = json.load(r3)
    assert sell.get("success"), sell
    print("  sell:", sell)

    writer.write(encode_frame("41"))
    writer.close()
    print("PROTOCOL_TEST_PASS")


if __name__ == "__main__":
    asyncio.run(run(int(sys.argv[1]) if len(sys.argv) > 1 else 8787))

# economy extension (append to run() before the disconnect — merged by verify)
