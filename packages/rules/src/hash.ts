/**
 * Canonical serialization and hashing of rules state, used for replay checks and
 * client/server parity (ADR 0004). Only integers are accepted: a float in the state
 * means a formula escaped fixed-point maths (ADR 0006), so it fails loudly here.
 */
export function canonical(value: unknown): string {
  if (value === null) return "null";
  switch (typeof value) {
    case "boolean":
      return value ? "true" : "false";
    case "string":
      return JSON.stringify(value);
    case "number":
      if (!Number.isSafeInteger(value)) {
        throw new TypeError(`canonical: non-integer number ${value} in rules state`);
      }
      return String(value);
    case "object": {
      if (Array.isArray(value)) return `[${value.map(canonical).join(",")}]`;
      const proto = Object.getPrototypeOf(value);
      if (proto !== Object.prototype && proto !== null) {
        throw new TypeError("canonical: only plain objects and arrays are allowed");
      }
      const entries = Object.entries(value as Record<string, unknown>)
        .filter(([, v]) => v !== undefined)
        .sort(([a], [b]) => (a < b ? -1 : a > b ? 1 : 0));
      return `{${entries.map(([k, v]) => `${JSON.stringify(k)}:${canonical(v)}`).join(",")}}`;
    }
    default:
      throw new TypeError(`canonical: unsupported type ${typeof value}`);
  }
}

/** 64-bit FNV-1a over UTF-16 code units, as 16 hex digits. */
export function hashState(value: unknown): string {
  const text = canonical(value);
  let h = 0xcbf29ce484222325n;
  const prime = 0x100000001b3n;
  const mask = 0xffffffffffffffffn;
  for (let i = 0; i < text.length; i++) {
    const unit = text.charCodeAt(i);
    h = ((h ^ BigInt(unit & 0xff)) * prime) & mask;
    h = ((h ^ BigInt(unit >>> 8)) * prime) & mask;
  }
  return h.toString(16).padStart(16, "0");
}
