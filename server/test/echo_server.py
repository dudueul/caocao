#!/usr/bin/env python3
"""echo_server.py — Lab 07 exercise target, STDLIB ONLY (no dependencies).

Two protocol layers, both implemented here so you can read every byte:

  Layer 1 (RFC 6455): WebSocket handshake + frame codec
      - handshake: Sec-WebSocket-Accept = b64(SHA1(key + MAGIC_GUID))
      - client frames are MASKED (we unmask); server frames are not
  Layer 2 (Engine.IO v4 + Socket.IO v4): the game's actual protocol
      - "0{handshake}" → "40" → "40{sid}" dance
      - "2" ping / "3" pong
      - '4' + socket.io packets: 2=EVENT (echo back), 0/1 connect/disconnect

Usage: echo_server.py [port]   (default 8790)
Client: ws://127.0.0.1:<port>/socket.io/?EIO=4&transport=websocket
"""
import asyncio
import base64
import hashlib
import json
import sys

MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"
PING_INTERVAL = 1.5


def ws_accept(key: str) -> str:
    return base64.b64encode(hashlib.sha1((key + MAGIC).encode()).digest()).decode()


def decode_frame(buf: bytes):
    """Return (opcode, payload, rest) or (None, None, buf) if incomplete."""
    if len(buf) < 2:
        return None, None, buf
    fin_rsv_op = buf[0]
    opcode = fin_rsv_op & 0x0F
    masked = buf[1] & 0x80
    ln = buf[1] & 0x7F
    idx = 2
    if ln == 126:
        if len(buf) < 4: return None, None, buf
        ln = int.from_bytes(buf[2:4], "big"); idx = 4
    elif ln == 127:
        if len(buf) < 10: return None, None, buf
        ln = int.from_bytes(buf[2:10], "big"); idx = 10
    if masked:
        if len(buf) < idx + 4: return None, None, buf
        mask = buf[idx:idx + 4]; idx += 4
        if len(buf) < idx + ln: return None, None, buf
        payload = bytes(b ^ mask[i % 4] for i, b in enumerate(buf[idx:idx + ln]))
    else:
        if len(buf) < idx + ln: return None, None, buf
        payload = buf[idx:idx + ln]
    return opcode, payload, buf[idx + ln:]


def encode_frame(text: str) -> bytes:
    data = text.encode()
    head = bytearray([0x81])  # FIN + text opcode
    n = len(data)
    if n < 126:
        head.append(n)
    elif n < 65536:
        head.append(126); head += n.to_bytes(2, "big")
    else:
        head.append(127); head += n.to_bytes(8, "big")
    return bytes(head) + data


async def handle(reader, writer):
    # ---- RFC 6455 handshake ----
    request = (await reader.readline()).decode()
    headers = {}
    while True:
        line = (await reader.readline()).decode()
        if line in ("\r\n", "\n", ""):
            break
        k, _, v = line.partition(":")
        headers[k.strip().lower()] = v.strip()
    key = headers.get("sec-websocket-key", "")
    accept = ws_accept(key)
    writer.write((
        "HTTP/1.1 101 Switching Protocols\r\n"
        "Upgrade: websocket\r\n"
        "Connection: Upgrade\r\n"
        f"Sec-WebSocket-Accept: {accept}\r\n\r\n"
    ).encode())
    await writer.drain()

    sid = f"lab{int(writer.get_extra_info('peername')[1])}"
    writer.write(encode_frame("0" + json.dumps({
        "sid": sid, "upgrades": [],
        "pingInterval": int(PING_INTERVAL * 1000),
        "pingTimeout": 20000, "maxPayload": 1000000})))
    await writer.drain()

    buf = b""
    ping_task = asyncio.create_task(keepalive(writer, sid))
    try:
        while True:
            data = await reader.read(65536)
            if not data:
                break
            buf += data
            while True:
                op, payload, buf = decode_frame(buf)
                if op is None:
                    break
                if op == 0x8:            # close
                    ping_task.cancel()
                    writer.write(encode_frame(""))
                    await writer.drain()
                    return
                if op != 0x1:            # only text frames in this protocol
                    continue
                msg = payload.decode(errors="replace")
                if not msg:
                    continue
                t, rest = msg[0], msg[1:]
                if t == "2":             # engine.io ping → pong
                    writer.write(encode_frame("3"))
                elif t == "4":           # socket.io packet
                    if rest.startswith("0"):
                        writer.write(encode_frame("40" + json.dumps({"sid": sid})))
                    elif rest.startswith("2"):
                        body = rest[1:]
                        if "#" in body:
                            body = body.split("#", 1)[1]
                        arr = json.loads(body)
                        if arr and arr[0] == "echo":
                            writer.write(encode_frame("42" + json.dumps(arr)))
                    elif rest.startswith("1"):
                        ping_task.cancel()
                        return
                elif t == "6":
                    ping_task.cancel()
                    return
                await writer.drain()
    finally:
        ping_task.cancel()
        writer.close()
        print("session closed:", sid)


async def keepalive(writer, sid):
    try:
        while True:
            await asyncio.sleep(PING_INTERVAL)
            # Server does not ping in v4; we just observe silence.
    except asyncio.CancelledError:
        pass


async def main(port):
    server = await asyncio.start_server(handle, "127.0.0.1", port)
    print(f"echo server (stdlib WS) on ws://127.0.0.1:{port}/socket.io/?EIO=4")
    async with server:
        await server.serve_forever()


if __name__ == "__main__":
    asyncio.run(main(int(sys.argv[1]) if len(sys.argv) > 1 else 8790))
