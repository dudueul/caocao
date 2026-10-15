/**
 * Smooth upscaling for the exported sprites (ADR 0044): Scale2x twice (4×) on
 * the palette indices, then a 2×2 box average down to 2×. The result keeps the
 * 1998 shapes but rounds their stair-step edges with soft anti-aliasing, which
 * reads cleaner at modern screen sizes and densities.
 */

/** One Scale2x (AdvMAME2x) pass over a w×h cell of palette indices. */
export function scale2x(w: number, h: number, src: Uint8Array): Uint8Array {
  const out = new Uint8Array(w * 2 * h * 2);
  const at = (x: number, y: number) =>
    src[Math.min(h - 1, Math.max(0, y)) * w + Math.min(w - 1, Math.max(0, x))] as number;
  for (let y = 0; y < h; y++)
    for (let x = 0; x < w; x++) {
      const p = at(x, y);
      const a = at(x, y - 1);
      const b = at(x + 1, y);
      const c = at(x - 1, y);
      const d = at(x, y + 1);
      const o = y * 2 * w * 2 + x * 2;
      out[o] = c === a && c !== d && a !== b ? a : p;
      out[o + 1] = a === b && a !== c && b !== d ? b : p;
      out[o + w * 2] = d === c && d !== b && c !== a ? c : p;
      out[o + w * 2 + 1] = b === d && b !== a && d !== c ? d : p;
    }
  return out;
}

/**
 * A strip of `count` square frames (indices; index 0 transparent when `keyed`)
 * as RGBA at twice the size, each frame smoothed on its own.
 */
export function smoothStrip(
  size: number,
  count: number,
  px: Uint8Array,
  plte: Uint8Array,
  keyed: boolean,
): { w: number; h: number; rgba: Uint8Array } {
  const W = size * count;
  const out = new Uint8Array(W * 2 * size * 2 * 4);
  const frame = new Uint8Array(size * size);
  for (let f = 0; f < count; f++) {
    for (let y = 0; y < size; y++)
      frame.set(px.subarray(y * W + f * size, y * W + f * size + size), y * size);
    const x4 = scale2x(size * 2, size * 2, scale2x(size, size, frame));
    const s4 = size * 4;
    for (let y = 0; y < size * 2; y++)
      for (let x = 0; x < size * 2; x++) {
        let r = 0;
        let g = 0;
        let b = 0;
        let a = 0;
        for (const [dx, dy] of [
          [0, 0],
          [1, 0],
          [0, 1],
          [1, 1],
        ] as const) {
          const i = x4[(y * 2 + dy) * s4 + x * 2 + dx] as number;
          if (keyed && i === 0) continue;
          r += plte[i * 3] as number;
          g += plte[i * 3 + 1] as number;
          b += plte[i * 3 + 2] as number;
          a += 1;
        }
        const o = (y * W * 2 + f * size * 2 + x) * 4;
        if (a > 0) {
          out[o] = Math.round(r / a);
          out[o + 1] = Math.round(g / a);
          out[o + 2] = Math.round(b / a);
          out[o + 3] = Math.round((255 * a) / 4);
        }
      }
  }
  return { w: W * 2, h: size * 2, rgba: out };
}
