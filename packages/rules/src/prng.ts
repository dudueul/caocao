/**
 * Seeded PRNG (sfc32) whose state is four uint32s: small, serializable, and identical
 * in browsers, Node and Workers. The rules engine never calls Math.random (ADR 0006).
 */
export type PrngState = readonly [number, number, number, number];

const TWO_POW_32 = 0x1_0000_0000;

export class Prng {
  #a: number;
  #b: number;
  #c: number;
  #d: number;

  constructor(state: PrngState) {
    [this.#a, this.#b, this.#c, this.#d] = state.map((v) => v | 0) as [
      number,
      number,
      number,
      number,
    ];
  }

  /** Snapshot to store in the battle state; `new Prng(state)` resumes the sequence. */
  get state(): PrngState {
    return [this.#a >>> 0, this.#b >>> 0, this.#c >>> 0, this.#d >>> 0];
  }

  nextU32(): number {
    const t = (((this.#a + this.#b) | 0) + this.#d) | 0;
    this.#d = (this.#d + 1) | 0;
    this.#a = this.#b ^ (this.#b >>> 9);
    this.#b = (this.#c + (this.#c << 3)) | 0;
    this.#c = (this.#c << 21) | (this.#c >>> 11);
    this.#c = (this.#c + t) | 0;
    return t >>> 0;
  }

  /** Uniform integer in [0, n) without modulo bias. */
  int(n: number): number {
    if (!Number.isInteger(n) || n < 1 || n > TWO_POW_32) {
      throw new RangeError(`Prng.int: n must be an integer in 1..2^32, got ${n}`);
    }
    const limit = TWO_POW_32 - (TWO_POW_32 % n);
    for (;;) {
      const r = this.nextU32();
      if (r < limit) return r % n;
    }
  }

  /** True with probability permille / 1000 (hit rates are stored in permille). */
  chance(permille: number): boolean {
    return this.int(1000) < permille;
  }
}

/** Derives a well-mixed state from a seed string (battle id, season id …). */
export function seedFromString(text: string): PrngState {
  let h = 1779033703 ^ text.length;
  for (let i = 0; i < text.length; i++) {
    h = Math.imul(h ^ text.charCodeAt(i), 3432918353);
    h = (h << 13) | (h >>> 19);
  }
  const mix = (): number => {
    h = Math.imul(h ^ (h >>> 16), 2246822507);
    h = Math.imul(h ^ (h >>> 13), 3266489909);
    h ^= h >>> 16;
    return h >>> 0;
  };
  const prng = new Prng([mix(), mix(), mix(), mix()]);
  for (let i = 0; i < 15; i++) prng.nextU32();
  return prng.state;
}
