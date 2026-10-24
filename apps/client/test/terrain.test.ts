// The terrain painter's map reading and ground fields (ADR 0074): pure functions,
// checked without a canvas.
import { describe, expect, it } from "bun:test";
import { TERRAIN_CODES } from "@tk/content";
import { computeGround } from "../src/lib/fx/terrain/ground.ts";
import { lookOf, REGIONS, SEASONS } from "../src/lib/fx/terrain/landscape.ts";
import { E, type Feature, N, planMap, S, TOWER, W } from "../src/lib/fx/terrain/plan.ts";

function map(rows: readonly string[]) {
  return {
    width: rows[0]?.length ?? 0,
    height: rows.length,
    tiles: rows
      .join("")
      .split("")
      .map((c) => TERRAIN_CODES[c] ?? "plain"),
  };
}

const TOWN = [
  "..........",
  ".####v###.",
  ".#vv...v#.",
  ".#.v.FF.#.",
  ".F..vFF.v.",
  ".#v...v.#.",
  ".#.vv..v#.",
  ".###F####.",
  "..........",
];

const count = (fs: readonly Feature[], kind: Feature["kind"]) =>
  fs.filter((f) => f.kind === kind).length;

describe("terrain plan", () => {
  it("is a pure function of the map", () => {
    const a = planMap(map(TOWN));
    const b = planMap(map(TOWN));
    expect(JSON.stringify(a.features)).toBe(JSON.stringify(b.features));
    expect(a.sig).toEqual(b.sig);
  });

  it("finds gates in wall lines: towers over forts, lighter towers over villages", () => {
    const p = planMap(map(TOWN));
    const at = (x: number, y: number) => p.gates[(p.gateAt[y * p.w + x] as number) - 1];
    expect(at(5, 1)?.kind).toBe("town");
    expect(at(5, 1)?.run).toBe("ew");
    expect(at(1, 4)?.kind).toBe("tower");
    expect(at(1, 4)?.run).toBe("ns");
    expect(at(8, 4)?.kind).toBe("town");
    expect(at(4, 7)?.kind).toBe("tower");
    expect(p.gates.length).toBe(4);
  });

  it("does not take the inside of a small walled yard for a gate", () => {
    const p = planMap(map([".......", ".#####.", ".#vvv#.", ".#vvv#.", ".#####.", "......."]));
    expect(p.gates.length).toBe(0);
  });

  it("joins walls and puts towers on the corners only", () => {
    const p = planMap(map(TOWN));
    const wall = (x: number, y: number) => p.wall[y * p.w + x] as number;
    expect(wall(1, 1) & TOWER).toBe(TOWER);
    expect(wall(1, 1) & (E | S)).toBe(E | S);
    expect(wall(3, 1) & TOWER).toBe(0);
    expect(wall(3, 1) & (E | W)).toBe(E | W);
    expect(wall(1, 2) & (N | S)).toBe(N | S);
  });

  it("knows castle grounds, even against the map's edge", () => {
    const p = planMap(map(TOWN));
    expect(p.enclosed[4 * p.w + 4]).toBe(1);
    expect(p.enclosed[0]).toBe(0);
    // 남피: walled on three sides, the map's edge on the fourth.
    const nanpi = planMap(
      map([
        "..........",
        "....######",
        "....#vvvvv",
        "....vvvvvv",
        "....#vvvvv",
        "....######",
        "..........",
      ]),
    );
    expect(nanpi.enclosed[3 * nanpi.w + 7]).toBe(1);
    expect(nanpi.enclosed[3 * nanpi.w + 2]).toBe(0);
  });

  it("keeps castle grounds uncluttered: a few compounds among courtyards", () => {
    const p = planMap(map(TOWN));
    const villages = p.cls.filter((c, i) => c === 8 && p.enclosed[i] === 1).length;
    expect(count(p.features, "compound")).toBeLessThanOrEqual(Math.ceil(villages / 5));
    expect(count(p.features, "keep")).toBe(1);
  });

  it("caps an open village at about one house per three or four tiles, with one landmark", () => {
    const rows = [
      "..........",
      "...vv.....",
      "..vvvv....",
      "...vvv....",
      "....v.....",
      "..........",
    ];
    const p = planMap(map(rows));
    const n = rows.join("").split("v").length - 1;
    const houses = count(p.features, "compound");
    expect(houses).toBeGreaterThanOrEqual(1);
    expect(houses).toBeLessThanOrEqual(Math.ceil(n / 3));
    const landmarks = p.features.filter(
      (f) => f.kind === "well" || f.kind === "granary" || f.kind === "watchtower",
    ).length;
    expect(landmarks).toBe(1);
  });

  it("lays bridges along their run and over the water", () => {
    const p = planMap(map(["...w...", "...w...", ".bbbbb.", "...w...", "...w..."]));
    for (let x = 1; x <= 5; x++) expect(p.bridges.get(2 * p.w + x)?.axis).toBe("ew");
    expect(p.bridges.get(2 * p.w + 3)?.wet).toBe(true);
  });

  it("rings a camp with rampart and palisade, leaving an opening", () => {
    const p = planMap(map(["........", ".cccc...", ".cccc...", ".cFcc...", "........"]));
    expect(p.fences.length).toBeGreaterThan(8);
    expect(p.fences.some((f) => f.gap)).toBe(true);
    expect(count(p.features, "stockade")).toBe(1);
  });

  it("changes only the neighbourhood of a map shift", () => {
    const before = map([
      "..........",
      "..........",
      "....gg....",
      "....gg....",
      "..........",
      "..........",
    ]);
    const after = { ...before, tiles: [...before.tiles] };
    after.tiles[2 * 10 + 4] = "water";
    const a = planMap(before);
    const b = planMap(after);
    const changed: number[] = [];
    for (let i = 0; i < a.sig.length; i++) if (a.sig[i] !== b.sig[i]) changed.push(i);
    expect(changed.length).toBeGreaterThan(0);
    for (const i of changed) {
      const x = i % 10;
      const y = Math.floor(i / 10);
      expect(Math.abs(x - 4)).toBeLessThanOrEqual(2);
      expect(Math.abs(y - 2)).toBeLessThanOrEqual(2);
    }
  });
});

describe("terrain ground", () => {
  it("is a pure function of plan and look", () => {
    const p = planMap(map(["..ww..", ".hhw..", "ffww.m", "..b..m"]));
    const a = computeGround(p, lookOf(null), 12);
    const b = computeGround(p, lookOf(null), 12);
    expect(a.land).toEqual(b.land);
    expect(a.water).toEqual(b.water);
  });

  it("is open water in a lake and solid land in a field", () => {
    const p = planMap(map(["......", ".wwww.", ".wwww.", ".wwww.", "......"]));
    const g = computeGround(p, lookOf(null), 12);
    const alpha = (u: number, v: number) =>
      g.land[(Math.floor(v * 12) * g.W + Math.floor(u * 12)) * 4 + 3];
    expect(alpha(3, 2.5)).toBe(0);
    expect(alpha(0.3, 0.3)).toBe(255);
  });

  it("has a look for every region and season", () => {
    const keys = new Set<string>();
    for (const r of REGIONS)
      for (const s of SEASONS) {
        const l = lookOf({ region: r, season: s });
        keys.add(l.key);
        for (const c of [...l.loess, ...l.grass, l.water.deep, l.earth])
          for (const v of c) {
            expect(v).toBeGreaterThanOrEqual(0);
            expect(v).toBeLessThanOrEqual(255);
          }
      }
    expect(keys.size).toBe(REGIONS.length * SEASONS.length);
    expect(lookOf(null).key).toBe(lookOf({ region: "plain", season: "summer" }).key);
  });
});
