/**
 * Deterministic hashing and smooth noise for the terrain painter. Every look is
 * a pure function of position: no clock, no `Math.random` (ADR 0074).
 */

/** A well-mixed hash of integer coordinates and a seed, in [0, 1). */
export function hash(x: number, y: number, seed = 0): number {
  let h =
    (Math.imul(x | 0, 0x27d4eb2d) +
      Math.imul(y | 0, 0x165667b1) +
      Math.imul(seed | 0, 0x9e3779b1)) |
    0;
  h = Math.imul(h ^ (h >>> 15), 0x85ebca6b);
  h = Math.imul(h ^ (h >>> 13), 0xc2b2ae35);
  h ^= h >>> 16;
  return (h >>> 0) / 4294967296;
}

const SIZE = 256;
const MASK = SIZE - 1;
const LATTICE = new Float32Array(SIZE * SIZE);
for (let j = 0; j < SIZE; j++)
  for (let i = 0; i < SIZE; i++) LATTICE[j * SIZE + i] = hash(i, j, 977);

/** Smooth value noise in [0, 1]; `seed` shifts to an unrelated field. */
export function vnoise(x: number, y: number, seed = 0): number {
  // Turned off the axes, so the lattice never lines up with the tile grid.
  const sx = x * 0.8660254 - y * 0.5 + seed * 37.17;
  const sy = x * 0.5 + y * 0.8660254 + seed * 71.31;
  const ix = Math.floor(sx);
  const iy = Math.floor(sy);
  const fx = sx - ix;
  const fy = sy - iy;
  const ux = fx * fx * (3 - 2 * fx);
  const uy = fy * fy * (3 - 2 * fy);
  const x0 = ix & MASK;
  const y0 = iy & MASK;
  const x1 = (x0 + 1) & MASK;
  const y1 = (y0 + 1) & MASK;
  const a = LATTICE[y0 * SIZE + x0] as number;
  const b = LATTICE[y0 * SIZE + x1] as number;
  const c = LATTICE[y1 * SIZE + x0] as number;
  const d = LATTICE[y1 * SIZE + x1] as number;
  return a + (b - a) * ux + (c - a) * uy + (a - b - c + d) * ux * uy;
}

/** Fractal value noise in [0, 1]. */
export function fbm(x: number, y: number, seed: number, octaves = 3): number {
  let sum = 0;
  let amp = 1;
  let norm = 0;
  let f = 1;
  for (let o = 0; o < octaves; o++) {
    sum += vnoise(x * f, y * f, seed + o * 13) * amp;
    norm += amp;
    amp *= 0.5;
    f *= 2.03;
  }
  return sum / norm;
}

/** Ridged noise in [0, 1]: sharp crests where plain noise crosses its middle. */
export function ridged(x: number, y: number, seed: number, octaves = 3): number {
  let sum = 0;
  let amp = 1;
  let norm = 0;
  let f = 1;
  for (let o = 0; o < octaves; o++) {
    const n = 1 - Math.abs(vnoise(x * f, y * f, seed + o * 17) * 2 - 1);
    sum += n * n * amp;
    norm += amp;
    amp *= 0.5;
    f *= 2.1;
  }
  return sum / norm;
}

/**
 * Ridged multifractal: each octave is weighted by the one before, so fine
 * ridges branch off the main crests and the valleys stay smooth (mountains).
 */
export function ridgedMF(x: number, y: number, seed: number, octaves = 4): number {
  let sum = 0;
  let amp = 1;
  let norm = 0;
  let f = 1;
  let weight = 1;
  for (let o = 0; o < octaves; o++) {
    let n = 1 - Math.abs(vnoise(x * f, y * f, seed + o * 19) * 2 - 1);
    n = n * n * weight;
    weight = n * 2 > 1 ? 1 : n * 2;
    sum += n * amp;
    norm += amp;
    amp *= 0.5;
    f *= 2.2;
  }
  return sum / norm;
}

export function clamp01(v: number): number {
  return v < 0 ? 0 : v > 1 ? 1 : v;
}

export function smooth(a: number, b: number, v: number): number {
  const t = clamp01((v - a) / (b - a));
  return t * t * (3 - 2 * t);
}

export type RGB = readonly [number, number, number];

export function mixRGB(a: RGB, b: RGB, t: number): RGB {
  return [a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t, a[2] + (b[2] - a[2]) * t];
}

export function scaleRGB(a: RGB, k: number): RGB {
  return [a[0] * k, a[1] * k, a[2] * k];
}

/** A CSS colour from RGB and alpha. */
export function css(c: RGB, a = 1): string {
  const r = Math.round(Math.max(0, Math.min(255, c[0])));
  const g = Math.round(Math.max(0, Math.min(255, c[1])));
  const b = Math.round(Math.max(0, Math.min(255, c[2])));
  return a >= 1 ? `rgb(${r},${g},${b})` : `rgba(${r},${g},${b},${a.toFixed(3)})`;
}

export function hex(s: string): RGB {
  const n = Number.parseInt(s.slice(1), 16);
  return [(n >> 16) & 255, (n >> 8) & 255, n & 255];
}
