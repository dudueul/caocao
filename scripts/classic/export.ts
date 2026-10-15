/**
 * Exports the 1998 PC 조조전 art from YOUR OWN copy of the game into the
 * git-ignored apps/client/classic/ folder, for the local-only classic skin
 * (ADR 0042). Koei Tecmo owns this art: it is never committed and never shipped
 * in a build. Reads the game files only; runs nothing from the game folder.
 *
 *   bun run classic:export "<game folder>" [--out DIR] [--theme MAP] [--contact]
 *
 * Output (formats: archive docs/re/EKD5_FORMATS.md §2b):
 *   units/unit_###_{mov,atk,spc}.png   sprite strips (48 px × 11, 64 px × 12, 48 px × 5)
 *   faces/face_###.png                 64 × 80 portraits
 *   maps/map_##.png, map_##.json       1998 battle maps (48 px tiles) and their grids
 *   terrain.png                        48 px tiles of our 12 terrains, sampled from the maps
 *   manifest.json                      what the client needs: sets, faces, atlas, maps
 *   contact/units.png                  with --contact: every set standing, 10 per row
 */
import { existsSync, mkdirSync, readdirSync, readFileSync, statSync, writeFileSync } from "node:fs";
import { join, resolve } from "node:path";
import { OFFICERS } from "../../packages/content/src/index.ts";
import type { TerrainId } from "../../packages/rules/src/index.ts";
import { decompress } from "./ls12.ts";
import { indexedPng, rgbaPng } from "./png.ts";

const TILE = 48;
const MOV = { size: 48, count: 11 };
const ATK = { size: 64, count: 12 };
const SPC = { size: 48, count: 5 };

/** Our classes → 1998 sprite sets (set + 0 red, + 1 orange, + 2 blue). Our own index table. */
const CLASS_SETS: Record<string, number> = {
  infantry: 0, // 경보병
  archer: 18, // 궁병
  cavalry: 9, // 경기병
  strategist: 63, // 책사
  martialArtist: 36, // 무도가
  catapult: 54, // 경포차
};

/** Officers with their own sprite set (one set for every side). Our own index table. */
const OFFICER_SETS: Record<string, number> = {
  "guan-yu": 125,
};

/** Older Korean readings the 1998 release uses for some names (ours → theirs). */
const NAME_ALIASES: Record<string, readonly string[]> = {
  제갈량: ["제갈양"],
  감녕: ["감영"],
};

/** 1998 terrain ids (EKD5.EXE table, 0–29) → our terrain types. */
const CLASSIC_TERRAIN: readonly TerrainId[] = [
  "plain", // 0 평지
  "grass", // 1 초원
  "forest", // 2 숲
  "plain", // 3 황무지
  "hills", // 4 산지
  "mountain", // 5 바위산
  "wall", // 6 벼랑
  "plain", // 7 설원
  "bridge", // 8 다리
  "ford", // 9 여울
  "ford", // 10 습지
  "water", // 11 연못
  "water", // 12 강
  "water", // 13 대하
  "wall", // 14 울타리
  "wall", // 15 성벽
  "plain", // 16 성내
  "fort", // 17 성문
  "fort", // 18 성
  "fort", // 19 관문
  "fort", // 20 요새
  "village", // 21 마을
  "camp", // 22 병영
  "village", // 23 가옥
  "village", // 24 보물고
  "ford", // 25 도랑
  "plain", // 26 불
  "plain", // 27 배
  "plain", // 28 제단
  "plain", // 29 지중
];

/** Which 1998 terrains to sample tiles from for each of ours (the best match first). */
const SAMPLE_FROM: Record<TerrainId, readonly number[]> = {
  plain: [0],
  grass: [1],
  forest: [2],
  hills: [4],
  mountain: [5],
  ford: [9],
  water: [12, 13],
  bridge: [8],
  village: [21, 23],
  fort: [20, 18, 19],
  wall: [15],
  camp: [22],
};
const TERRAIN_ORDER = Object.keys(SAMPLE_FROM) as TerrainId[];
const VARIANTS = 6;

// ---------------------------------------------------------------- game files

class Game {
  private cache = new Map<string, Uint8Array[]>();
  constructor(readonly root: string) {}

  path(name: string): string | null {
    const direct = join(this.root, name);
    if (existsSync(direct)) return direct;
    const low = name.toLowerCase();
    const hit = readdirSync(this.root).find((f) => f.toLowerCase() === low);
    return hit ? join(this.root, hit) : null;
  }

  bytes(name: string): Uint8Array {
    const p = this.path(name);
    if (!p) throw new Error(`missing ${name} in ${this.root}`);
    return new Uint8Array(readFileSync(p));
  }

  ls12(name: string): Uint8Array[] {
    let v = this.cache.get(name);
    if (!v) {
      v = decompress(this.bytes(name));
      this.cache.set(name, v);
    }
    return v;
  }
}

const korean = new TextDecoder("euc-kr");

function cstr(b: Uint8Array): string {
  const end = b.indexOf(0);
  return korean.decode(end < 0 ? b : b.subarray(0, end));
}

/** 256 colours stored in the game's byte order → RGB, as the archive exporter verified. */
function palette(raw: Uint8Array): Uint8Array {
  const out = new Uint8Array(768);
  for (let i = 0; i < 256; i++) {
    out[i * 3] = raw[i * 3 + 1] ?? 0;
    out[i * 3 + 1] = raw[i * 3 + 2] ?? 0;
    out[i * 3 + 2] = raw[i * 3] ?? 0;
  }
  return out;
}

/** Frames stored one after another → one row. */
function strip(
  blob: Uint8Array,
  size: number,
  count: number,
): { w: number; h: number; px: Uint8Array } {
  const w = size * count;
  const px = new Uint8Array(w * size);
  for (let f = 0; f < count; f++)
    for (let y = 0; y < size; y++)
      px.set(
        blob.subarray(f * size * size + y * size, f * size * size + (y + 1) * size),
        y * w + f * size,
      );
  return { w, h: size, px };
}

/** HMkk.E5: 48 px tiles one after another, row-major → one picture. */
function tilesToImage(
  blob: Uint8Array,
  wt: number,
  ht: number,
): { w: number; h: number; px: Uint8Array } {
  const w = wt * TILE;
  const h = ht * TILE;
  const px = new Uint8Array(w * h);
  for (let t = 0; t < wt * ht; t++) {
    const tx = t % wt;
    const ty = Math.floor(t / wt);
    for (let y = 0; y < TILE; y++)
      px.set(
        blob.subarray(t * TILE * TILE + y * TILE, t * TILE * TILE + (y + 1) * TILE),
        (ty * TILE + y) * w + tx * TILE,
      );
  }
  return { w, h, px };
}

function battleNames(g: Game): string[] {
  if (!g.path("IMSG.E5")) return [];
  const d = g.bytes("IMSG.E5");
  const names: string[] = [];
  for (let k = 0; k < 64; k++) {
    const off = (250 + k) * 216;
    names.push(off + 216 <= d.length ? cstr(d.subarray(off, off + 216)) : "");
  }
  return names;
}

/** Officer name → portrait index, from DATA.E5 member 0 (512 records of 32 bytes). */
function officerFaces(g: Game): Map<string, number> {
  const d = g.ls12("DATA.E5")[0];
  const out = new Map<string, number>();
  if (!d) return out;
  for (let i = 0; i + 32 <= d.length; i += 32) {
    const name = cstr(d.subarray(i, i + 12)).trim();
    if (name && !out.has(name)) out.set(name, d[i + 13] as number);
  }
  return out;
}

// ---------------------------------------------------------------- terrain atlas

interface ClassicMap {
  id: number;
  wt: number;
  ht: number;
  grid: Uint8Array;
  px: Uint8Array;
  rgb: Uint8Array;
}

interface Pick {
  m: ClassicMap;
  x: number;
  y: number;
  score: number;
  /** Copy the tile turned 90° (a bridge that spans the other way). */
  rotate: boolean;
}

/** Is the tile's average colour clearly blue? */
function bluish(p: { m: ClassicMap; x: number; y: number }): boolean {
  let r = 0;
  let b = 0;
  for (let y = 0; y < TILE; y += 4)
    for (let x = 0; x < TILE; x += 4) {
      const i = p.m.px[(p.y * TILE + y) * p.m.wt * TILE + p.x * TILE + x] as number;
      r += p.m.rgb[i * 3] as number;
      b += p.m.rgb[i * 3 + 2] as number;
    }
  return b > r * 1.1;
}

/** Candidate tiles of one terrain of ours in one map, best surrounded first, spread apart. */
function candidates(m: ClassicMap, terrain: TerrainId, perMap: number): Pick[] {
  const ids = SAMPLE_FROM[terrain];
  const at = (x: number, y: number) =>
    x < 0 || y < 0 || x >= m.wt || y >= m.ht ? undefined : m.grid[y * m.wt + x];
  const isId = (v: number | undefined) => v !== undefined && ids.includes(v);
  const water = (v: number | undefined) => v === 11 || v === 12 || v === 13;
  const found: Pick[] = [];
  for (let y = 0; y < m.ht; y++)
    for (let x = 0; x < m.wt; x++) {
      if (!isId(at(x, y))) continue;
      if (terrain === "bridge") {
        // Our rivers run north–south, so a bridge tile has water above and below.
        if (water(at(x, y - 1)) && water(at(x, y + 1)))
          found.push({ m, x, y, score: 8, rotate: false });
        else if (water(at(x - 1, y)) && water(at(x + 1, y)))
          found.push({ m, x, y, score: 7, rotate: true });
        continue;
      }
      let score = 0;
      for (let dy = -1; dy <= 1; dy++)
        for (let dx = -1; dx <= 1; dx++) if ((dx || dy) && isId(at(x + dx, y + dy))) score++;
      found.push({ m, x, y, score, rotate: false });
    }
  found.sort((a, b) => b.score - a.score || a.y - b.y || a.x - b.x);
  // Some maps paint rivers as sand; water must read as water on our maps.
  const readable = terrain === "water" ? found.filter((f) => bluish(f)) : found;
  const out: Pick[] = [];
  for (const f of readable) {
    if (out.length >= perMap) break;
    if (out.every((o) => Math.abs(o.x - f.x) + Math.abs(o.y - f.y) > 3)) out.push(f);
  }
  return out;
}

/**
 * Picks up to VARIANTS 48 px tiles for each terrain of ours. One map's art should
 * set the look, so maps are ranked by how many of our terrains they show well
 * (tiles whose whole neighbourhood is that terrain), the best map is the theme
 * (or `--theme N`), and each terrain takes its tiles from the theme first, then
 * from the next maps in rank.
 */
function sampleTerrain(
  maps: ClassicMap[],
  theme: number | null,
): { rgba: Uint8Array; w: number; h: number; counts: number[]; theme: number } {
  const coverage = (m: ClassicMap) =>
    TERRAIN_ORDER.filter((t) => (candidates(m, t, 1)[0]?.score ?? -1) >= (t === "bridge" ? 7 : 6))
      .length;
  const ranked = [...maps].sort((a, b) => coverage(b) - coverage(a) || a.id - b.id);
  const first = ranked.findIndex((m) => m.id === theme);
  if (first > 0) ranked.unshift(...ranked.splice(first, 1));
  const w = VARIANTS * TILE;
  const h = TERRAIN_ORDER.length * TILE;
  const rgba = new Uint8Array(w * h * 4);
  const counts: number[] = [];
  TERRAIN_ORDER.forEach((terrain, row) => {
    const chosen: Pick[] = [];
    for (const m of ranked) {
      if (chosen.length >= VARIANTS) break;
      const good = candidates(m, terrain, 3).filter((p) => p.score >= Math.min(6, p.score));
      chosen.push(...good.slice(0, VARIANTS - chosen.length));
    }
    counts.push(chosen.length);
    chosen.forEach((p, col) => {
      for (let y = 0; y < TILE; y++)
        for (let x = 0; x < TILE; x++) {
          // Rotating 90° clockwise: target (x, y) comes from source (y, TILE − 1 − x).
          const sx = p.rotate ? y : x;
          const sy = p.rotate ? TILE - 1 - x : y;
          const i = p.m.px[(p.y * TILE + sy) * p.m.wt * TILE + p.x * TILE + sx] as number;
          const o = ((row * TILE + y) * w + col * TILE + x) * 4;
          rgba[o] = p.m.rgb[i * 3] as number;
          rgba[o + 1] = p.m.rgb[i * 3 + 1] as number;
          rgba[o + 2] = p.m.rgb[i * 3 + 2] as number;
          rgba[o + 3] = 255;
        }
    });
  });
  return { rgba, w, h, counts, theme: ranked[0]?.id ?? -1 };
}

// ---------------------------------------------------------------- main

function main(argv: string[]): void {
  const args = argv.filter(
    (a, i) => !a.startsWith("--") && !["--out", "--theme"].includes(argv[i - 1] ?? ""),
  );
  const game = args[0];
  if (!game) {
    console.error('usage: bun run classic:export "<game folder>" [--out DIR] [--contact]');
    process.exit(1);
  }
  const outFlag = argv.indexOf("--out");
  const repo = resolve(import.meta.dir, "../..");
  const out =
    outFlag >= 0 && argv[outFlag + 1]
      ? resolve(argv[outFlag + 1] as string)
      : join(repo, "apps/client/classic");
  const g = new Game(resolve(game));
  const dir = (...p: string[]) => {
    const d = join(out, ...p);
    mkdirSync(d, { recursive: true });
    return d;
  };
  const t0 = performance.now();

  // Units
  const mov = g.ls12("UNIT_MOV.E5");
  const atk = g.ls12("UNIT_ATK.E5");
  const spc = g.ls12("UNIT_SPC.E5");
  const pals = g.ls12("SPALET.E5");
  const unitPal = palette(pals[0] as Uint8Array);
  const sets = Math.min(mov.length, atk.length, spc.length);
  const units = dir("units");
  for (let i = 0; i < sets; i++)
    for (const [blob, spec, tag] of [
      [mov[i], MOV, "mov"],
      [atk[i], ATK, "atk"],
      [spc[i], SPC, "spc"],
    ] as const) {
      const s = strip(blob as Uint8Array, spec.size, spec.count);
      writeFileSync(
        join(units, `unit_${String(i).padStart(3, "0")}_${tag}.png`),
        indexedPng(s.w, s.h, s.px, unitPal, true),
      );
    }
  console.log(`units: ${sets} sprite sets`);

  // Portraits
  const faces = g.ls12("FACE.E5");
  const faceDir = dir("faces");
  faces.forEach((f, i) => {
    writeFileSync(
      join(faceDir, `face_${String(i).padStart(3, "0")}.png`),
      indexedPng(64, 80, f.subarray(0, 64 * 80), unitPal, false),
    );
  });
  const byName = officerFaces(g);
  const officerFace: Record<string, number> = {};
  for (const o of Object.values(OFFICERS)) {
    const face = [o.name, ...(NAME_ALIASES[o.name] ?? [])]
      .map((n) => byName.get(n))
      .find((f) => f !== undefined);
    if (face !== undefined && face < faces.length) officerFace[o.id] = face;
  }
  console.log(
    `faces: ${faces.length}; matched ${Object.keys(officerFace).length}/${Object.keys(OFFICERS).length} of our officers by name`,
  );

  // Battle maps
  const grids = g.ls12("HEXZMAP.E5");
  const names = battleNames(g);
  const mapDir = dir("maps");
  const maps: ClassicMap[] = [];
  const mapList: { id: number; name: string; width: number; height: number }[] = [];
  grids.forEach((m, k) => {
    const wt = Math.floor((m[0] as number) / 3);
    const ht = Math.floor((m[1] as number) / 3);
    const grid = m.slice(2, 2 + wt * ht);
    const hp = g.path(`HM${String(k).padStart(2, "0")}.E5`);
    if (!hp || statSync(hp).size !== wt * ht * TILE * TILE || k >= pals.length) return;
    const img = tilesToImage(new Uint8Array(readFileSync(hp)), wt, ht);
    const rgb = palette(pals[k] as Uint8Array);
    const tag = String(k).padStart(2, "0");
    writeFileSync(join(mapDir, `map_${tag}.png`), indexedPng(img.w, img.h, img.px, rgb, false));
    const terrain = [...grid].map((v) => CLASSIC_TERRAIN[v] ?? "plain");
    writeFileSync(
      join(mapDir, `map_${tag}.json`),
      JSON.stringify({
        id: k,
        name: names[k] ?? "",
        width: wt,
        height: ht,
        tileSize: TILE,
        classic: [...grid],
        terrain,
      }),
    );
    maps.push({ id: k, wt, ht, grid, px: img.px, rgb });
    mapList.push({ id: k, name: names[k] ?? "", width: wt, height: ht });
  });
  console.log(`maps: ${mapList.length}`);

  // Terrain atlas
  // Default theme: 관도 전투 (map 18), the campaign of our 오소 sandbox.
  const themeFlag = argv.indexOf("--theme");
  const theme = themeFlag >= 0 ? Number(argv[themeFlag + 1]) : 18;
  const atlas = sampleTerrain(maps, Number.isInteger(theme) ? theme : null);
  writeFileSync(join(out, "terrain.png"), rgbaPng(atlas.w, atlas.h, atlas.rgba));
  console.log(
    `terrain (theme map ${atlas.theme} ${names[atlas.theme] ?? ""}): ${TERRAIN_ORDER.map((t, i) => `${t} ${atlas.counts[i]}`).join(", ")}`,
  );

  if (argv.includes("--contact")) {
    const cols = 10;
    const rows = Math.ceil(sets / cols);
    const cw = 50;
    const sheet = new Uint8Array(cols * cw * rows * cw);
    for (let i = 0; i < sets; i++) {
      const frame = (mov[i] as Uint8Array).subarray(6 * 48 * 48, 7 * 48 * 48);
      const x0 = (i % cols) * cw;
      const y0 = Math.floor(i / cols) * cw;
      for (let y = 0; y < 48; y++)
        sheet.set(frame.subarray(y * 48, (y + 1) * 48), (y0 + y) * cols * cw + x0);
    }
    writeFileSync(
      join(dir("contact"), "units.png"),
      indexedPng(cols * cw, rows * cw, sheet, unitPal, false),
    );
  }

  const manifest = {
    about:
      "1998 PC 삼국지 조조전 art (Koei Tecmo), exported locally by scripts/classic/export.ts. Never commit or ship (ADR 0042).",
    tile: TILE,
    sheets: {
      mov: { size: MOV.size, count: MOV.count },
      atk: { size: ATK.size, count: ATK.count },
      spc: { size: SPC.size, count: SPC.count },
    },
    anims: {
      walk: { down: [0, 1], up: [2, 3], side: [4, 5] },
      stand: { down: [6], up: [7], side: [8] },
      weak: [9, 10],
      attack: { down: [0, 1, 2, 3], up: [4, 5, 6, 7], side: [8, 9, 10, 11] },
      guard: { down: [0], up: [1], side: [2] },
      hurt: [3],
      cheer: [4],
    },
    /** Side frames face left; mirror for right. */
    sideFacesLeft: true,
    sets,
    classes: CLASS_SETS,
    officerSets: OFFICER_SETS,
    officerFaces: officerFace,
    faces: faces.length,
    terrain: {
      file: "terrain.png",
      order: TERRAIN_ORDER,
      counts: atlas.counts,
      theme: atlas.theme,
    },
    maps: mapList,
  };
  writeFileSync(join(out, "manifest.json"), JSON.stringify(manifest, null, 1));
  console.log(`wrote ${out} in ${Math.round(performance.now() - t0)} ms`);
}

main(process.argv.slice(2));
