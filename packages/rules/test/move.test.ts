import { describe, expect, it } from "bun:test";
import { createBattle, reachable, unitById } from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

function tiles(rows: string[], units: Parameters<typeof setup>[1], id: string): string[] {
  const s = createBattle(RULES, setup(rows, units));
  return reachable(s, RULES, unitById(s, id)).map((r) => `${r.x},${r.y}`);
}

describe("movement (ADR 0023, ADR 0026)", () => {
  it("spends per-family terrain costs and stops at the move budget", () => {
    // Infantry move 4: forest costs 2.
    const r = tiles([".ff..."], [unit("i", 0, "infantry", 0, 0)], "i");
    expect(r).toEqual(["0,0", "1,0", "2,0"]);
  });

  it("treats water and walls as impassable", () => {
    const r = tiles([".w...", ".#..."], [unit("i", 0, "infantry", 0, 0)], "i");
    expect(r).toEqual(["0,0", "0,1"]);
  });

  it("enemies block; allies can be passed but not stopped on", () => {
    const r = tiles(
      ["....."],
      [unit("i", 0, "infantry", 0, 0), unit("ally", 0, "infantry", 1, 0)],
      "i",
    );
    expect(r).toEqual(["0,0", "2,0", "3,0", "4,0"]);
  });

  it("soft ZOC: entering a tile next to an enemy ends movement", () => {
    const rows = [".....", ".....", "....."];
    const r = tiles(rows, [unit("i", 0, "infantry", 0, 1), unit("e", 1, "infantry", 3, 1)], "i");
    // (2,1), (3,0), (3,2) touch the enemy: reachable, but nothing beyond them.
    expect(r).toContain("2,1");
    expect(r).toContain("3,0");
    expect(r).not.toContain("4,0");
    expect(r).not.toContain("4,1");
  });

  it("skirmishers pass through one ZOC tile", () => {
    const rows = [".....", ".....", "....."];
    const r = tiles(rows, [unit("c", 0, "cavalry", 0, 1), unit("e", 1, "infantry", 3, 1)], "c");
    expect(r).toContain("4,0");
    expect(r).toContain("4,1");
  });

  it("cavalry cannot enter mountains", () => {
    const r = tiles(["m...."], [unit("c", 0, "cavalry", 1, 0)], "c");
    expect(r).not.toContain("0,0");
  });
});
