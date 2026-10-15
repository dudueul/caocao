/**
 * Koei LS11/LS12 archives (1998 PC 조조전 *.E5). A TypeScript port of the
 * archive's ls12.py; reads bytes only.
 *
 *   0x000  16 bytes  magic "Ls12" (or "LS11") padded with spaces
 *   0x010 256 bytes  dictionary: code c < 256 → literal dict[c]
 *   0x110  N × 12    members: big-endian u32 (compressed size, size, offset), then u32 0
 *   data             MSB-first bitstream per member; code = n leading 1 bits, a 0,
 *                    then n + 1 bits v → v + 2^(n+1) − 2. Codes ≥ 256 are
 *                    back-references: distance code − 256, length next code + 3.
 *                    Members whose two sizes match are stored raw.
 */
export interface Member {
  csize: number;
  usize: number;
  offset: number;
}

export function parseArchive(data: Uint8Array): { dict: Uint8Array; members: Member[] } {
  const magic = String.fromCharCode(...data.subarray(0, 4));
  if (!["Ls12", "LS11", "Ls11", "LS12"].includes(magic))
    throw new Error(`not an LS11/LS12 archive: ${magic}`);
  const view = new DataView(data.buffer, data.byteOffset, data.byteLength);
  const dict = data.subarray(16, 272);
  const members: Member[] = [];
  for (let p = 272; p + 4 <= data.length; p += 12) {
    const csize = view.getUint32(p);
    if (csize === 0) break;
    members.push({ csize, usize: view.getUint32(p + 4), offset: view.getUint32(p + 8) });
  }
  return { dict, members };
}

export function decodeMember(data: Uint8Array, dict: Uint8Array, m: Member): Uint8Array {
  if (m.csize === m.usize) return data.slice(m.offset, m.offset + m.csize);
  const src = data.subarray(m.offset, m.offset + m.csize);
  const nbits = src.length * 8;
  let p = 0;
  const bit = (i: number) => ((src[i >> 3] as number) >> (7 - (i & 7))) & 1;
  const code = (): number => {
    let n = 1;
    while (p < nbits && bit(p) === 1) {
      n++;
      p++;
    }
    if (p >= nbits) return -1;
    p++; // the terminating 0
    if (p + n > nbits) return -1;
    let v = 0;
    for (let i = 0; i < n; i++) v = v * 2 + bit(p++);
    return v + 2 ** n - 2;
  };
  const out = new Uint8Array(m.usize);
  let len = 0;
  while (len < m.usize) {
    const c = code();
    if (c < 0) break;
    if (c < 256) {
      out[len++] = dict[c] as number;
      continue;
    }
    const dist = c - 256;
    const l = code();
    if (l < 0) break;
    const start = len - dist;
    if (start < 0) throw new Error(`bad back-reference at ${len} (distance ${dist})`);
    for (let i = 0; i < l + 3 && len < m.usize; i++) out[len++] = out[start + i] as number;
  }
  return out;
}

export function decompress(data: Uint8Array): Uint8Array[] {
  const { dict, members } = parseArchive(data);
  return members.map((m) => decodeMember(data, dict, m));
}
