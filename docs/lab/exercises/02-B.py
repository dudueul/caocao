#!/usr/bin/env python3
"""02-B.py — Lab 02 exercise: parse a Unity SerializedFile header (v17).

Usage: 02-B.py <file-or-dir> [count]
Prints: version, data offset, engine version string, platform.

LAYOUT QUIRK you must discover (this is the exercise):
  bytes [ 0.. 4) metadata_size   — u32 BIG-endian (legacy quirk!)
  bytes [ 4.. 8) file_size       — u32 BIG-endian
  bytes [ 8..12) version         — u32 BIG-endian  (17 for this build)
  bytes [12..16) data_offset     — u32 BIG-endian  (0x1000)
  bytes [16..20) reserved        — u32 (0)
  bytes [20..  ) engine string   — NUL-terminated, padded to 4 ("2018.2.16f1\\0")
  then           platform        — u32 LITTLE-endian (13 = Android)
Yes, the header mixes endianness — Unity reads the first fields big-endian
for compatibility, then switches to the file's declared endianness.
"""
import os, struct, sys


def parse(path):
    with open(path, "rb") as f:
        head = f.read(64)
    meta_size, file_size, version, data_off = struct.unpack_from(">iiii", head, 0)
    vstr = head[20:32].split(b"\0")[0].decode(errors="replace")
    platform = struct.unpack_from("<i", head, 32)[0]
    return version, data_off, vstr, platform


def main():
    target = sys.argv[1]
    count = int(sys.argv[2]) if len(sys.argv) > 2 else 10
    files = [target] if os.path.isfile(target) else [
        os.path.join(target, n) for n in sorted(os.listdir(target))
        if len(n) == 32 and all(c in "0123456789abcdef" for c in n)
    ][:count]
    ok = True
    for p in files:
        v, off, vs, plat = parse(p)
        good = v == 17 and vs == "2018.2.16f1" and plat == 13
        ok &= good
        print(f"{os.path.basename(p)}: v={v} off=0x{off:x} "
              f"engine={vs!r} platform={plat} {'OK' if good else 'MISMATCH'}")
    sys.exit(0 if ok else 1)


if __name__ == "__main__":
    main()
