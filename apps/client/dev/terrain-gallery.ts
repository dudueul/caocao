// Dev gallery for the terrain painter (ADR 0074), dev server only: /dev/terrain-gallery.html
// Every natural terrain over a wide patch (no two tiles alike), the joins (forest edges,
// hill clusters, mountains, a river with banks, fords and a bridge, a walled town, an open
// village, a camp), and each region in each season, from small maps of our own.
// `?only=bench` paints 업 and 하비 (20 × 14) at 64, 96 and 128 px per tile and reports
// the time and texture memory.
// Query: ?only=terrain|joins|regions|bench  ?tile=48 (display px per tile)  ?px=96 (paint px)
import { CHAPTER_LIST, TERRAIN_CODES } from "@tk/content";
import {
  type Landscape,
  REGIONS,
  renderLandscape,
  SEASONS,
  terrainStats,
} from "../src/lib/fx/terrain/index.ts";

const q = new URLSearchParams(location.search);
const TILE = Number(q.get("tile")) || 48;
const PX = Number(q.get("px")) || 96;
const only = q.get("only");
const out = document.getElementById("out") as HTMLElement;

function parse(rows: readonly string[]) {
  const tiles = rows
    .join("")
    .split("")
    .map((c) => TERRAIN_CODES[c] ?? "plain");
  return { width: rows[0]?.length ?? 0, height: rows.length, tiles };
}

let total = 0;
function panel(
  row: HTMLElement,
  caption: string,
  rows: readonly string[],
  land?: Landscape,
  tile = TILE,
) {
  const map = parse(rows);
  const t0 = performance.now();
  const c = renderLandscape(map, { px: PX, landscape: land ?? null });
  total += performance.now() - t0;
  c.style.width = `${map.width * tile}px`;
  c.style.height = `${map.height * tile}px`;
  const f = document.createElement("figure");
  const cap = document.createElement("figcaption");
  cap.textContent = caption;
  f.append(c, cap);
  row.append(f);
}

function section(title: string): HTMLElement {
  const h = document.createElement("h2");
  h.textContent = title;
  const row = document.createElement("div");
  row.className = "row";
  out.append(h, row);
  return row;
}

const fill = (ch: string, w = 10, h = 4) => Array.from({ length: h }, () => ch.repeat(w));

if (!only || only === "terrain") {
  const row = section("Natural terrain: one patch each (every tile differs)");
  for (const [ch, name] of [
    [".", "plain"],
    ["g", "grass"],
    ["f", "forest"],
    ["h", "hills"],
    ["m", "mountain"],
    ["d", "ford"],
    ["w", "water"],
  ] as const)
    panel(row, name, fill(ch));
  const mix = section("Grass and plain in patches");
  panel(mix, "plain with grass", [
    "....gg....gg",
    "...ggg..g...",
    "..gggg...gg.",
    "....g...ggg.",
    "g.......gg..",
    "gg..g.......",
  ]);
}

if (!only || only === "joins") {
  const row = section("Joins");
  panel(row, "forest masses: lone trees to a wood", [
    "............",
    ".f....ff....",
    "......ff..f.",
    "..fff.......",
    ".fffff..ffff",
    ".fff.f..ffff",
    "..ff....ff..",
    "............",
  ]);
  panel(row, "hills: a lone hill, a ridge, a cluster", [
    "............",
    ".h...hhhhh..",
    "............",
    "..hhh.......",
    ".hhhhh...h..",
    "..hhh...hh..",
    "........h...",
    "............",
  ]);
  panel(row, "mountains with foothills", [
    "mmmmmhh.....",
    "mmmmhh......",
    "mmmhh...hh..",
    "mmh....hmmh.",
    "hh....hmmmh.",
    "......hmmh..",
    "........h...",
    "hh..........",
  ]);
  panel(row, "a river: banks, a ford, a bridge, a lake", [
    "...ww.......",
    "...ww.......",
    "...dd....ww.",
    "...ww...wwww",
    "....ww..wwww",
    ".....bb..ww.",
    ".....ww.....",
    "......ww....",
  ]);
  panel(row, "a walled town: gates, keep, few buildings", [
    "..........",
    ".####v###.",
    ".#vv...v#.",
    ".#.v.FF.#.",
    ".F..vFF.v.",
    ".#v...v.#.",
    ".#.vv..v#.",
    ".###F####.",
    "..........",
  ]);
  panel(row, "an open village and its fields", [
    "..........",
    "...vv.....",
    "..vvvv.g..",
    "...vvv....",
    "....v.....",
    "......vv..",
    "......vv..",
    "..........",
  ]);
  panel(row, "a camp: tents, palisade and rampart", [
    "..........",
    ".cccc.....",
    ".cccc..cc.",
    ".cFcc..ccc",
    ".cccc.....",
    "..........",
  ]);
  panel(row, "a pass: wall, gate tower, forts", [
    "mmmm#mmmm",
    "hhm.#.hmm",
    "....#F...",
    "....F.FF.",
    "....#.FF.",
    "hh..#..hh",
    "mmhh#hmmm",
  ]);
  const ice = section("Ice (남피 in the first month) and the thaw");
  const river = [
    "hh..ddd..gg",
    "...bbbb....",
    "....ddd....",
    "..f.ddd..v.",
    ".ff.ddd.vvv",
    "....bbb....",
    "....ddd....",
  ];
  panel(ice, "frozen fords (ice: true)", river, { region: "plain", season: "winter", ice: true });
  panel(
    ice,
    "after the thaw",
    river.map((r) => r.replaceAll("d", "w")),
    { region: "plain", season: "winter" },
  );
}

if (!only || only === "regions") {
  const sample = [
    "mmhh....ff..ww",
    "mh....gg.fffww",
    "h...v.ggg..dww",
    "...vvv....wwwb",
    "..ggv...hh..ww",
    "ff.....hhh..ww",
    "fff..cc......w",
    ".ff..cc..gg..w",
  ];
  for (const region of REGIONS) {
    const row = section(`Region: ${region}`);
    for (const season of SEASONS)
      panel(row, `${region} · ${season}`, sample, { region, season }, 32);
  }
}

if (only === "bench") {
  // Story battle maps by id, from the chapters.
  const maps = new Map<string, readonly string[]>();
  const walk = (o: unknown): void => {
    if (!o || typeof o !== "object") return;
    if (Array.isArray(o)) {
      for (const x of o) walk(x);
      return;
    }
    const r = o as { id?: string; map?: { rows?: readonly string[] }; roundLimit?: number };
    if (r.id && r.map?.rows && r.roundLimit) maps.set(r.id, r.map.rows);
    for (const v of Object.values(o)) walk(v);
  };
  for (const ch of CHAPTER_LIST) walk(ch.steps);
  const row = section("Paint time and texture memory (20 × 14)");
  const lines: string[] = [];
  for (const id of ["ye", "xiapi"]) {
    const rows = maps.get(id);
    if (!rows) continue;
    for (const px of [64, 96, 128]) {
      const runs: number[] = [];
      let field = 0;
      for (let k = 0; k < 3; k++) {
        renderLandscape(parse(rows), { px });
        runs.push(terrainStats.ms);
        field = terrainStats.field;
      }
      lines.push(
        `${id} at ${px} px: ${runs.map((r) => r.toFixed(0)).join(" / ")} ms (field ${field.toFixed(0)} ms), ` +
          `${(terrainStats.bytes / 1048576).toFixed(1)} MB textures, library ${(terrainStats.library / 1048576).toFixed(1)} MB; ` +
          `ground ${terrainStats.ground.toFixed(0)}, detail ${terrainStats.detail.toFixed(0)}, forest ${terrainStats.forest.toFixed(0)}, built ${terrainStats.built.toFixed(0)} ms`,
      );
    }
    panel(row, id, rows);
  }
  const pre = document.createElement("pre");
  pre.id = "bench";
  pre.textContent = lines.join("\n");
  out.append(pre);
}

(document.getElementById("stats") as HTMLElement).textContent =
  `painted in ${total.toFixed(0)} ms at ${PX} px per tile`;
