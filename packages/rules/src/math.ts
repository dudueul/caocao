/** Integer helpers. Multipliers are ×1000 fixed-point; results are floored (ADR 0006). */

export function clamp(value: number, min: number, max: number): number {
  return value < min ? min : value > max ? max : value;
}

/** ⌊value × permille / 1000⌋ for non-negative values. */
export function permille(value: number, factor: number): number {
  return Math.floor((value * factor) / 1000);
}

/** ⌊value / 2⌋ */
export function half(value: number): number {
  return Math.floor(value / 2);
}
