#!/usr/bin/env python3
"""bundle_crypto.py — undo (or redo) the client's BundleCrypto.Crypt on asset-bundle files.

[RE] BundleCrypto.Crypt @0x15ff054 (libil2cpp.so), key from BundleCrypto..cctor @0x15ff208:
    for i in 0 .. min(len(bytes), len(key)) - 1:
        bytes[i] = bytes[i] ^ (len(bytes) & 0xff) ^ key[i]
Only the first 51 bytes change and the transform is its own inverse, so the same
command decrypts a cached/recovered bundle and re-encrypts a decrypted one.
Bundles with "encrypted": true in the bundle list are stored this way (on the CDN
and in the device cache); decrypt them before opening with AssetRipper/AssetStudio.

usage: bundle_crypto.py <in> [<out>]        (default: write <in>.dec)
       bundle_crypto.py --check <file>      (report whether it looks like a UnityFS bundle)
"""
import sys

KEY = b'tmxbeldhdnjCCvmfhwprxmtkarnrwlwhwhwjsdhsfkdlsdmldka'


def crypt(data: bytes) -> bytes:
    b = bytearray(data)
    n = len(b) & 0xFF
    for i in range(min(len(b), len(KEY))):
        b[i] ^= n ^ KEY[i]
    return bytes(b)


def main(argv):
    if len(argv) >= 2 and argv[0] == '--check':
        head = open(argv[1], 'rb').read(64)
        plain = head.startswith(b'UnityFS')
        print(f'{argv[1]}: ' + ('plain UnityFS bundle' if plain else
              'UnityFS after decrypt' if crypt(open(argv[1], 'rb').read()).startswith(b'UnityFS') else 'not a UnityFS bundle'))
        return 0
    if not argv:
        print(__doc__)
        return 2
    src = argv[0]
    dst = argv[1] if len(argv) > 1 else src + '.dec'
    data = open(src, 'rb').read()
    out = crypt(data)
    open(dst, 'wb').write(out)
    print(f'{src} -> {dst} ({len(data)} bytes, header {out[:7]!r})')
    return 0


if __name__ == '__main__':
    sys.exit(main(sys.argv[1:]))
