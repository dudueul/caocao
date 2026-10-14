import { describe, expect, it } from "vitest";
import { Prng, seedFromString } from "../src/index.ts";

// Golden values: if these change, every stored replay and seed breaks.
// Change them only with a format-version bump.
describe("Prng (sfc32)", () => {
  it("derives a fixed state from a seed string", () => {
    expect(seedFromString("tk")).toEqual([2077999317, 1792876237, 1431765119, 4168436202]);
  });

  it("produces the golden sequence", () => {
    const p = new Prng(seedFromString("tk"));
    expect([p.nextU32(), p.nextU32(), p.nextU32()]).toEqual([3744344460, 1668071076, 1617151405]);
    expect(Array.from({ length: 8 }, () => p.int(6))).toEqual([4, 0, 5, 5, 0, 2, 3, 0]);
  });

  it("resumes exactly from a stored state", () => {
    const a = new Prng(seedFromString("battle-42"));
    a.nextU32();
    const b = new Prng(a.state);
    expect(Array.from({ length: 100 }, () => a.nextU32())).toEqual(
      Array.from({ length: 100 }, () => b.nextU32()),
    );
  });

  it("keeps int(n) in range and roughly uniform", () => {
    const p = new Prng(seedFromString("uniform"));
    const counts = new Array<number>(6).fill(0);
    for (let i = 0; i < 60_000; i++) {
      const v = p.int(6);
      expect(v).toBeGreaterThanOrEqual(0);
      expect(v).toBeLessThan(6);
      counts[v] = (counts[v] ?? 0) + 1;
    }
    for (const c of counts) expect(Math.abs(c - 10_000)).toBeLessThan(500);
  });

  it("rejects invalid ranges", () => {
    const p = new Prng(seedFromString("x"));
    expect(() => p.int(0)).toThrow(RangeError);
    expect(() => p.int(2.5)).toThrow(RangeError);
  });

  it("chance() uses permille", () => {
    const p = new Prng(seedFromString("chance"));
    expect(Array.from({ length: 50 }, () => p.chance(0)).some(Boolean)).toBe(false);
    expect(Array.from({ length: 50 }, () => p.chance(1000)).every(Boolean)).toBe(true);
  });
});
