/** Minimal PNG writers: 8-bit indexed (with optional transparent index 0) and RGBA. */
import { deflateSync } from "node:zlib";

const CRC = new Uint32Array(256).map((_, n) => {
  let c = n;
  for (let k = 0; k < 8; k++) c = c & 1 ? 0xedb88320 ^ (c >>> 1) : c >>> 1;
  return c >>> 0;
});

function crc32(bytes: Uint8Array): number {
  let c = 0xffffffff;
  for (const b of bytes) c = (CRC[(c ^ b) & 0xff] as number) ^ (c >>> 8);
  return (c ^ 0xffffffff) >>> 0;
}

function chunk(type: string, body: Uint8Array): Uint8Array {
  const out = new Uint8Array(12 + body.length);
  const view = new DataView(out.buffer);
  view.setUint32(0, body.length);
  for (let i = 0; i < 4; i++) out[4 + i] = type.charCodeAt(i);
  out.set(body, 8);
  view.setUint32(8 + body.length, crc32(out.subarray(4, 8 + body.length)));
  return out;
}

function ihdr(w: number, h: number, colorType: number): Uint8Array {
  const b = new Uint8Array(13);
  const v = new DataView(b.buffer);
  v.setUint32(0, w);
  v.setUint32(4, h);
  b[8] = 8;
  b[9] = colorType;
  return b;
}

/** Rows prefixed with filter byte 0. */
function scanlines(w: number, h: number, bpp: number, pixels: Uint8Array): Uint8Array {
  const stride = w * bpp;
  const raw = new Uint8Array((stride + 1) * h);
  for (let y = 0; y < h; y++)
    raw.set(pixels.subarray(y * stride, (y + 1) * stride), y * (stride + 1) + 1);
  return raw;
}

function join(parts: Uint8Array[]): Uint8Array {
  const out = new Uint8Array(parts.reduce((n, p) => n + p.length, 0));
  let o = 0;
  for (const p of parts) {
    out.set(p, o);
    o += p.length;
  }
  return out;
}

const SIGNATURE = new Uint8Array([0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a]);

/** `plte`: 768 bytes RGB. `keyed`: index 0 is transparent. */
export function indexedPng(
  w: number,
  h: number,
  idx: Uint8Array,
  plte: Uint8Array,
  keyed: boolean,
): Uint8Array {
  return join([
    SIGNATURE,
    chunk("IHDR", ihdr(w, h, 3)),
    chunk("PLTE", plte),
    ...(keyed ? [chunk("tRNS", new Uint8Array([0]))] : []),
    chunk("IDAT", deflateSync(scanlines(w, h, 1, idx), { level: 6 })),
    chunk("IEND", new Uint8Array()),
  ]);
}

export function rgbaPng(w: number, h: number, rgba: Uint8Array): Uint8Array {
  return join([
    SIGNATURE,
    chunk("IHDR", ihdr(w, h, 6)),
    chunk("IDAT", deflateSync(scanlines(w, h, 4, rgba), { level: 6 })),
    chunk("IEND", new Uint8Array()),
  ]);
}
