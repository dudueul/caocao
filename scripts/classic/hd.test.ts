import { describe, expect, it } from "bun:test";
import { scale2x, smoothStrip } from "./hd.ts";

describe("sprite smoothing (ADR 0044)", () => {
  it("Scale2x rounds a diagonal and keeps flat areas", () => {
    // A 3×3 diagonal line of colour 1 on colour 0.
    const src = new Uint8Array([1, 0, 0, 0, 1, 0, 0, 0, 1]);
    const out = scale2x(3, 3, src);
    const at = (x: number, y: number) => out[y * 6 + x];
    // The step between (0,0) and (1,1) fills in: the bottom-left of pixel (1,0)…
    expect(at(2, 1)).toBe(1);
    // …and the top-right of pixel (0,1).
    expect(at(1, 2)).toBe(1);
    // The far corners stay background.
    expect(at(5, 0)).toBe(0);
    expect(at(0, 5)).toBe(0);
  });

  it("doubles each frame with soft alpha only at the edges", () => {
    // One 4×4 frame: a 2×2 opaque block (index 1) in transparent index 0.
    const px = new Uint8Array(16);
    for (const i of [5, 6, 9, 10]) px[i] = 1;
    const plte = new Uint8Array(768);
    plte.set([200, 100, 50], 3);
    const hd = smoothStrip(4, 1, px, plte, true);
    expect([hd.w, hd.h]).toEqual([8, 8]);
    const alpha = (x: number, y: number) => hd.rgba[(y * 8 + x) * 4 + 3];
    expect(alpha(0, 0)).toBe(0);
    expect(alpha(3, 3)).toBe(255);
    expect(hd.rgba.subarray((3 * 8 + 3) * 4, (3 * 8 + 3) * 4 + 3)).toEqual(
      new Uint8Array([200, 100, 50]),
    );
  });
});
