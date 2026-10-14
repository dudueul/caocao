#!/usr/bin/env python3
"""Koei LS11/LS12 archive decompressor (Sangokushi Sousouden / EKD5, 1998 Korean PC).

Format (verified against DATA.E5, ITEM.E5, HEXZMAP.E5, EFFAREA.E5, HITAREA.E5 ...):
  0x000  16 bytes  magic "Ls12" padded with spaces (LS11 uses "LS11")
  0x010 256 bytes  dictionary (a byte permutation; code c<256 -> dict[c])
  0x110  N*12      member table: big-endian u32 (comp_size, uncomp_size, offset),
                   terminated by a 4-byte zero word (u32 0)
  data             each member: MSB-first bitstream
     get_code(): n = number of leading 1 bits (terminated by a 0 bit);
                 v = next n+1 bits; code = v + 2^(n+1) - 2
     code < 256  -> literal dict[code]
     code >= 256 -> back-reference: distance = code - 256 (copy from out[len-distance]),
                    length = get_code() + 3
  If comp_size == uncomp_size the member is stored raw.

Usage:
  python3 -I ls12.py list  FILE [FILE...]
  python3 -I ls12.py extract FILE OUTDIR
"""
import os
import struct
import sys


def parse(data):
    magic = data[:4]
    if magic not in (b"Ls12", b"LS11", b"Ls11", b"LS12"):
        raise ValueError("not an LS11/LS12 archive: %r" % magic)
    dic = data[16:272]
    members = []
    p = 272
    while p + 4 <= len(data):
        csize = struct.unpack(">I", data[p:p + 4])[0]
        if csize == 0:
            break
        usize, off = struct.unpack(">II", data[p + 4:p + 12])
        members.append((csize, usize, off))
        p += 12
    return dic, members


def decode_member(data, dic, csize, usize, off, dist_bias=0):
    if csize == usize:
        return bytes(data[off:off + csize])
    # The bitstream as a '0'/'1' string: get_code() becomes one str.find + one int().
    chunk = data[off:off + csize]
    bits = bin(int.from_bytes(chunk, "big"))[2:].zfill(len(chunk) * 8) if chunk else ""
    nbits = len(bits)
    p = 0
    out = bytearray()

    def get_code():
        nonlocal p
        z = bits.find("0", p)
        if z < 0:
            raise EOFError
        n = z - p + 1                      # leading 1 bits + 1 = width of v
        e = z + 1 + n
        if e > nbits:
            raise EOFError
        v = int(bits[z + 1:e], 2)
        p = e
        return v + (1 << n) - 2

    try:
        while len(out) < usize:
            code = get_code()
            if code < 256:
                out.append(dic[code])
            else:
                dist = code - 256 + dist_bias
                length = get_code() + 3
                start = len(out) - dist
                if start < 0:
                    raise ValueError("bad back-reference at out=%d dist=%d" % (len(out), dist))
                if dist >= length:
                    out += out[start:start + length]
                else:                      # overlapping copy repeats the last `dist` bytes
                    for i in range(length):
                        out.append(out[start + i])
    except EOFError:
        pass
    return bytes(out[:usize])


def decompress_file(path):
    with open(path, "rb") as f:
        data = f.read()
    dic, members = parse(data)
    return [decode_member(data, dic, c, u, o) for (c, u, o) in members], members


def main(argv):
    if len(argv) < 3:
        print(__doc__)
        return 1
    cmd = argv[1]
    if cmd == "list":
        for path in argv[2:]:
            with open(path, "rb") as f:
                data = f.read()
            dic, members = parse(data)
            print("%s: %d members" % (os.path.basename(path), len(members)))
            for i, (c, u, o) in enumerate(members):
                print("  #%02d off=0x%06x comp=%7d uncomp=%7d%s" % (i, o, c, u, " (raw)" if c == u else ""))
    elif cmd == "extract":
        path, outdir = argv[2], argv[3]
        os.makedirs(outdir, exist_ok=True)
        outs, members = decompress_file(path)
        base = os.path.splitext(os.path.basename(path))[0]
        for i, (blob, (c, u, o)) in enumerate(zip(outs, members)):
            fn = os.path.join(outdir, "%s_%02d.bin" % (base, i))
            with open(fn, "wb") as f:
                f.write(blob)
            print("%s %d bytes (expected %d)" % (fn, len(blob), u))
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
