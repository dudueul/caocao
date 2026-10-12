#!/usr/bin/env python3
"""echo_client.py — Lab 07 reference solution (stdlib only).

Completes: WS handshake → engine.io OPEN → socket.io CONNECT → EVENT echo →
engine.io ping/pong → clean disconnect, against server/test/echo_server.py.

Run: python3 echo_client.py [port]
"""
import asyncio, base64, hashlib, json, os, sys

MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"


def encode_frame(text: str) -> bytes:
    """Client frames MUST be masked (RFC 6455 §5.3). The masking lesson:
    unmasked client data let middleboxes pattern-match — the protocol authors
    wanted client bytes opaque to intermediaries."""
    data = text.encode()
    head = bytearray([0x81])                     # FIN + text
    n = len(data)
    if n < 126:
        head.append(0x80 | n)
    elif n < 65536:
        head.append(0x80 | 126); head += n.to_bytes(2, "big")
    else:
        head.append(0x80 | 127); head += n.to_bytes(8, "big")
    mk = os.urandom(4)
    head += mk
    data = bytes(b ^ mk[i % 4] for i, b in enumerate(data))
    return bytes(head) + data


async def recv_text(reader) -> str:
    b1 = (await reader.read(1))[0]
    b2 = (await reader.read(1))[0]
    ln = b2 & 0x7F
    if ln == 126: ln = int.from_bytes(await reader.read(2), "big")
    elif ln == 127: ln = int.from_bytes(await reader.read(8), "big")
    data = b""
    while len(data) < ln:
        chunk = await reader.read(ln - len(data))
        if not chunk:
            raise ConnectionError("eof inside frame")
        data += chunk
    return data.decode()


async def ws_client(port):
    reader, writer = await asyncio.open_connection("127.0.0.1", port)
    key = "dGhlIHNhbXBsZSBub25jZQ=="
    writer.write((
        "GET /socket.io/?EIO=4&transport=websocket HTTP/1.1\r\n"
        "Host: x\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
        f"Sec-WebSocket-Key: {key}\r\nSec-WebSocket-Version: 13\r\n\r\n").encode())
    status = (await reader.readline()).decode().strip()
    headers = {}
    while True:
        line = await reader.readline()
        if line in (b"\r\n", b"\n", b""):
            break
        k, _, v = line.decode().partition(":")
        headers[k.strip().lower()] = v.strip()
    expect = base64.b64encode(hashlib.sha1((key + MAGIC).encode()).digest()).decode()
    assert headers.get("sec-websocket-accept") == expect, "bad accept"
    assert "101" in status, status
    print("WS handshake OK")

    hs = json.loads((await recv_text(reader))[1:])
    print("OPEN sid:", hs["sid"])
    writer.write(encode_frame("40"))
    ack = await recv_text(reader)
    print("CONNECT ack:", ack)
    writer.write(encode_frame('42["echo","hello",42]'))
    print("echo:", await recv_text(reader))
    writer.write(encode_frame("2"))
    print("pong:", await recv_text(reader))
    writer.write(encode_frame("41"))
    writer.close()
    print("CLIENT_PASS")


if __name__ == "__main__":
    asyncio.run(ws_client(int(sys.argv[1]) if len(sys.argv) > 1 else 8790))
