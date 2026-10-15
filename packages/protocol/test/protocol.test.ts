import { describe, expect, it } from "bun:test";
import {
  cleanName,
  normalizeRoomCode,
  PROTOCOL_VERSION,
  parseClientMessage,
  ROOM_CODE,
  redact,
  roomCodeFrom,
} from "../src/index.ts";

const KEY = "0123456789abcdef0123456789abcdef";
const hello = (o: Record<string, unknown> = {}) =>
  JSON.stringify({
    t: "hello",
    v: PROTOCOL_VERSION,
    room: "7KQ2MX",
    key: KEY,
    name: "조조팬",
    lastSeq: null,
    create: false,
    ...o,
  });

describe("client message validation (ADR 0037)", () => {
  it("accepts well-formed messages and strips unknown fields", () => {
    expect(parseClientMessage(hello())).toEqual({
      t: "hello",
      v: PROTOCOL_VERSION,
      room: "7KQ2MX",
      key: KEY,
      name: "조조팬",
      lastSeq: null,
      create: false,
    });
    const cmd = {
      t: "command",
      command: {
        kind: "act",
        unit: "guan-yu",
        to: { x: 3, y: 4 },
        action: { kind: "attack", target: "lu-bu" },
        extra: 1,
      },
    };
    expect(parseClientMessage(JSON.stringify(cmd))).toEqual({
      t: "command",
      command: {
        kind: "act",
        unit: "guan-yu",
        to: { x: 3, y: 4 },
        action: { kind: "attack", target: "lu-bu" },
      },
    });
    expect(parseClientMessage('{"t":"pick","entry":"zhuge-liang"}')).toEqual({
      t: "pick",
      entry: "zhuge-liang",
    });
  });

  it("drops malformed, oversized or hostile input", () => {
    for (const raw of [
      "not json",
      "[]",
      '{"t":"nope"}',
      hello({ key: "short" }),
      hello({ room: "ILO012" }),
      hello({ lastSeq: -1 }),
      hello({ lastSeq: 1.5 }),
      hello({ create: "yes" }),
      '{"t":"command","command":{"kind":"act","unit":"a","to":{"x":1.5,"y":0},"action":{"kind":"wait"}}}',
      '{"t":"command","command":{"kind":"act","unit":"a","to":{"x":1,"y":0},"action":{"kind":"teleport"}}}',
      '{"t":"command","command":{"kind":"act","unit":"__proto__ x","to":{"x":1,"y":0},"action":{"kind":"wait"}}}',
      '{"t":"pick","entry":""}',
      JSON.stringify({ t: "arrange", order: new Array(40).fill("a") }),
      JSON.stringify({ t: "pick", entry: "x".repeat(5000) }),
    ])
      expect(parseClientMessage(raw)).toBeNull();
  });
});

describe("codes and names (ADR 0038, ADR 0041)", () => {
  it("room codes use the 31-letter alphabet without look-alikes", () => {
    const code = roomCodeFrom(new Uint8Array([0, 30, 31, 62, 255, 7]));
    expect(ROOM_CODE.test(code)).toBe(true);
    expect(code).not.toMatch(/[01OIL]/);
    expect(normalizeRoomCode(" 7kq2-mx ")).toBe("7KQ2MX");
  });

  it("names are trimmed and 1–12 characters", () => {
    expect(cleanName("  조조   팬 ")).toBe("조조 팬");
    expect(cleanName("   ")).toBeNull();
    expect(cleanName("가".repeat(13))).toBeNull();
  });
});

describe("redaction", () => {
  it("removes the Prng from states sent to clients", () => {
    const state = { prng: [1, 2, 3, 4], round: 3 } as unknown as Parameters<typeof redact>[0];
    expect(redact(state).prng).toEqual([0, 0, 0, 0]);
    expect(state.prng).toEqual([1, 2, 3, 4]);
  });
});
