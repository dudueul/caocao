import { describe, expect, it } from "vitest";
import { canonical, hashState } from "../src/index.ts";

describe("canonical / hashState", () => {
  it("ignores object key order", () => {
    expect(canonical({ b: [1, 2], a: "x" })).toBe('{"a":"x","b":[1,2]}');
    expect(hashState({ b: [1, 2], a: "x" })).toBe(hashState({ a: "x", b: [1, 2] }));
  });

  it("has a stable golden hash", () => {
    expect(hashState({ a: "x", b: [1, 2] })).toBe("aa50e42e66f896d5");
  });

  it("skips undefined fields like JSON", () => {
    expect(canonical({ a: 1, b: undefined })).toBe('{"a":1}');
  });

  it("rejects floats and non-plain values (fixed-point only)", () => {
    expect(() => canonical({ hp: 1.5 })).toThrow(TypeError);
    expect(() => canonical(new Map())).toThrow(TypeError);
    expect(() => canonical(10n)).toThrow(TypeError);
  });
});
