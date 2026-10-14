#!/usr/bin/env python3
"""decrypt_bundle_list.py — decrypt the asset-bundle list (version.json) the client downloads.

[RE] FileUtil.GetDecryptedBytes / DecryptedInfo (libil2cpp.so @0x1a122e8, 0x1a143fc–0x1a14508):
  file  = u64 little-endian plaintext length | 16-byte IV ("salt") | AES-CBC ciphertext (no padding)
  key   = ASCII bytes of the bundle-list password
  plain = first <length> bytes of the decryption, which is gzip-compressed UTF-8 JSON
          {cdnPath, buildVersion, packages[], seriesStoryPackages[], dependency{}}
The password is NOT in the client: the server returns it with the list URL in
POST /security/checkClientSecurity -> packet.AssetBundle {url, password, comicBookNameList}
(it is also sent back as `assetBundle` in /user/heartBeats). Without it, only the
bundles themselves (scripts/bundles/bundle_crypto.py) can be decrypted.

Untested against a real list (none recovered yet); verified only by round-trip.
Needs the `cryptography` package (pip install cryptography).

usage: decrypt_bundle_list.py <list file> <password> [<out.json>]
"""
import gzip, json, struct, sys


def decrypt(blob: bytes, password: str) -> bytes:
    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    (length,) = struct.unpack_from('<Q', blob, 0)
    iv, body = blob[8:24], blob[24:]
    body = body[: len(body) - len(body) % 16]
    dec = Cipher(algorithms.AES(password.encode('ascii')), modes.CBC(iv)).decryptor()
    plain = (dec.update(body) + dec.finalize())[:length]
    return gzip.decompress(plain) if plain[:2] == b'\x1f\x8b' else plain


def main(argv):
    if len(argv) < 2:
        print(__doc__)
        return 2
    data = decrypt(open(argv[0], 'rb').read(), argv[1])
    info = json.loads(data.decode('utf-8'))
    out = argv[2] if len(argv) > 2 else argv[0] + '.json'
    json.dump(info, open(out, 'w', encoding='utf-8'), ensure_ascii=False, indent=1)
    pk = info.get('packages', [])
    print(f"cdnPath={info.get('cdnPath')!r} buildVersion={info.get('buildVersion')} packages={len(pk)} -> {out}")
    return 0


if __name__ == '__main__':
    sys.exit(main(sys.argv[1:]))
