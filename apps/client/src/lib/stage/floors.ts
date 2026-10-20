/**
 * The ground of a stage (ADR 0063): our terrain painter's tiles plus the three
 * stage floors (wooden boards, stone paving, a red carpet), and walls that
 * look built rather than quarried indoors (timber and plaster in a hall,
 * canvas in a tent). Painted procedurally like `fx/terrain.ts`; our own art.
 */
import type { StageDef, StageTile } from "@tk/content";
import type { BattleMap, TerrainId } from "@tk/rules";
import { Container, Graphics, Sprite, Texture } from "pixi.js";
import { ourTerrain, paintTerrain, type TerrainSet } from "../fx/terrain.ts";

type Paint = (g: CanvasRenderingContext2D, s: number, v: number) => void;

/** Where the stage is: under the sky, in a hall, or in a tent (it changes the walls). */
export type Setting = "outdoor" | "hall" | "tent";

function hash(x: number, y: number, seed: number): number {
  let h = (x * 374761393 + y * 668265263 + seed * 1442695041) | 0;
  h = Math.imul(h ^ (h >>> 13), 1274126177);
  return ((h ^ (h >>> 16)) >>> 0) / 4294967296;
}

function shade(hex: string, k: number): string {
  const n = Number.parseInt(hex.slice(1), 16);
  const c = (v: number) => Math.max(0, Math.min(255, Math.round(v * k)));
  return `rgb(${c((n >> 16) & 255)},${c((n >> 8) & 255)},${c(n & 255)})`;
}

/**
 * Wooden boards running across with staggered joints, grain and pegs. The
 * boards touching the tile's edges keep the row's tone and the grain meets
 * the same height there, so tiles join without a seam.
 */
const boards: Paint = (g, s, v) => {
  const rows = 4;
  const h = s / rows;
  const tones = ["#9a6a3e", "#8e6037", "#a2713f", "#946539"];
  const seam = Math.max(1, s / 64);
  for (let r = 0; r < rows; r++) {
    const y = r * h;
    const tone = tones[r] as string;
    g.fillStyle = tone;
    g.fillRect(0, y, s, h);
    // Two joints per row: the board between them is another plank.
    const j1 = s * (0.08 + hash(r, v, 3) * 0.34);
    const j2 = s * (0.58 + hash(r, v, 13) * 0.34);
    g.fillStyle = shade(tone, 0.93 + hash(r, v, 4) * 0.14);
    g.fillRect(j1, y, j2 - j1, h);
    g.lineWidth = Math.max(1, s / 90);
    for (let i = 0; i < 3; i++) {
      const gy = y + h * (0.25 + i * 0.25);
      const wob = (hash(r, i, v + 9) - 0.5) * h * 0.3;
      g.strokeStyle = `rgba(60,34,14,${0.16 + hash(r, i, v + 5) * 0.12})`;
      g.beginPath();
      g.moveTo(0, gy);
      g.bezierCurveTo(s * 0.3, gy + wob, s * 0.6, gy - wob, s, gy);
      g.stroke();
    }
    if (hash(r, v, 11) < 0.35) {
      const kx = s * (0.15 + hash(r, v, 12) * 0.7);
      g.fillStyle = "rgba(70,40,18,0.35)";
      g.beginPath();
      g.ellipse(kx, y + h * 0.5, h * 0.18, h * 0.1, 0, 0, Math.PI * 2);
      g.fill();
    }
    // Seams: a dark line under each board and at the joints, a light line on top.
    g.fillStyle = "rgba(40,22,10,0.5)";
    g.fillRect(0, y + h - seam, s, seam);
    for (const jx of [j1, j2]) g.fillRect(jx - seam / 2, y, seam, h);
    g.fillStyle = "rgba(255,230,190,0.1)";
    g.fillRect(0, y, s, Math.max(1, s / 96));
    g.fillStyle = "rgba(50,28,12,0.55)";
    for (const jx of [j1, j2])
      for (const py of [0.3, 0.7]) {
        g.beginPath();
        g.arc(jx + s * 0.025, y + h * py, s / 100, 0, Math.PI * 2);
        g.fill();
      }
  }
};

/** Grey-buff stone flags in a running bond, with mortar and wear. */
const paving: Paint = (g, s, v) => {
  g.fillStyle = "#7f7a6e";
  g.fillRect(0, 0, s, s);
  const rows = 2;
  const h = s / rows;
  const tones = ["#b3ab98", "#a8a08c", "#bab29f", "#a39b88"];
  for (let r = 0; r < rows; r++) {
    const off = r % 2 ? s / 4 : 0;
    for (let c = -1; c < 2; c++) {
      const x = c * (s / 2) + off;
      // Slabs that cross the tile edge keep a fixed tone, so neighbouring tiles meet cleanly.
      const crosses = x < 0 || x + s / 2 > s;
      const tone = crosses ? (tones[r] as string) : (tones[(r + c + v) % 4] as string);
      const k = crosses ? 1 : 0.95 + hash(r * 3 + c, v, 7) * 0.1;
      const m = Math.max(1, s / 40);
      const grad = g.createLinearGradient(x, r * h, x + s / 2, r * h + h);
      grad.addColorStop(0, shade(tone, k * 1.04));
      grad.addColorStop(1, shade(tone, k * 0.92));
      g.fillStyle = grad;
      g.fillRect(x + m, r * h + m, s / 2 - m * 2, h - m * 2);
      // A highlight on the upper edge, a shadow on the lower.
      g.fillStyle = "rgba(255,250,235,0.18)";
      g.fillRect(x + m, r * h + m, s / 2 - m * 2, m);
      g.fillStyle = "rgba(40,36,30,0.22)";
      g.fillRect(x + m, r * h + h - m * 2, s / 2 - m * 2, m);
    }
  }
  // Wear: pits and a crack or two.
  for (let i = 0; i < 18; i++) {
    g.fillStyle = `rgba(70,64,54,${0.15 + hash(i, v, 21) * 0.2})`;
    g.beginPath();
    g.arc(hash(i, v, 22) * s, hash(i, v, 23) * s, s / 120 + hash(i, v, 24) * (s / 90), 0, 7);
    g.fill();
  }
  if (hash(v, 1, 31) < 0.5) {
    g.strokeStyle = "rgba(60,54,46,0.35)";
    g.lineWidth = Math.max(1, s / 110);
    const cx = s * (0.2 + hash(v, 2, 31) * 0.6);
    const cy = s * (0.2 + hash(v, 3, 31) * 0.6);
    g.beginPath();
    g.moveTo(cx, cy);
    g.lineTo(cx + s * 0.08, cy + s * 0.05);
    g.lineTo(cx + s * 0.12, cy + s * 0.12);
    g.stroke();
  }
};

/** A red woollen carpet: a fine weave and a small gold lozenge repeat. */
const carpet: Paint = (g, s, v) => {
  const grad = g.createLinearGradient(0, 0, s, s);
  grad.addColorStop(0, "#9a2620");
  grad.addColorStop(1, "#86201b");
  g.fillStyle = grad;
  g.fillRect(0, 0, s, s);
  g.strokeStyle = "rgba(255,200,170,0.06)";
  g.lineWidth = Math.max(1, s / 100);
  for (let i = -s; i < s * 2; i += s / 12) {
    g.beginPath();
    g.moveTo(i, 0);
    g.lineTo(i + s, s);
    g.stroke();
  }
  g.strokeStyle = "rgba(40,0,0,0.12)";
  for (let i = -s; i < s * 2; i += s / 12) {
    g.beginPath();
    g.moveTo(i, s);
    g.lineTo(i + s, 0);
    g.stroke();
  }
  // Lozenges at the corners and the middle, so the repeat runs across tiles.
  const loz = (x: number, y: number, r: number) => {
    g.fillStyle = "rgba(226,178,90,0.55)";
    g.beginPath();
    g.moveTo(x, y - r);
    g.lineTo(x + r, y);
    g.lineTo(x, y + r);
    g.lineTo(x - r, y);
    g.closePath();
    g.fill();
    g.fillStyle = "rgba(110,20,16,0.9)";
    g.beginPath();
    g.arc(x, y, r * 0.32, 0, Math.PI * 2);
    g.fill();
  };
  loz(s / 2, s / 2, s * 0.09);
  for (const [x, y] of [
    [0, 0],
    [s, 0],
    [0, s],
    [s, s],
  ] as const)
    loz(x, y, s * 0.06);
  for (let i = 0; i < 20; i++) {
    g.fillStyle = `rgba(0,0,0,${0.04 + hash(i, v, 41) * 0.05})`;
    g.fillRect(hash(i, v, 42) * s, hash(i, v, 43) * s, s / 40, s / 40);
  }
};

/** A hall's wall seen from above the front: a timber beam, plaster, and a lacquered post. */
const hallWall: Paint = (g, s, v) => {
  const plaster = g.createLinearGradient(0, 0, 0, s);
  plaster.addColorStop(0, "#d9cdb1");
  plaster.addColorStop(1, "#c4b593");
  g.fillStyle = plaster;
  g.fillRect(0, 0, s, s);
  for (let i = 0; i < 14; i++) {
    g.fillStyle = `rgba(120,100,70,${0.05 + hash(i, v, 51) * 0.06})`;
    g.beginPath();
    g.arc(hash(i, v, 52) * s, hash(i, v, 53) * s, s * 0.06, 0, Math.PI * 2);
    g.fill();
  }
  // The roof beam and the eaves' shadow.
  g.fillStyle = "#5a2a1c";
  g.fillRect(0, 0, s, s * 0.2);
  g.fillStyle = "#7a3a26";
  g.fillRect(0, s * 0.03, s, s * 0.08);
  g.fillStyle = "rgba(0,0,0,0.25)";
  g.fillRect(0, s * 0.2, s, s * 0.06);
  // A red lacquered post at the left edge, a skirting board below.
  g.fillStyle = "#8c2a20";
  g.fillRect(0, s * 0.2, s * 0.1, s * 0.8);
  g.fillStyle = "#b5402f";
  g.fillRect(s * 0.02, s * 0.2, s * 0.03, s * 0.8);
  g.fillStyle = "#4a2a1a";
  g.fillRect(0, s * 0.86, s, s * 0.14);
  g.fillStyle = "rgba(255,240,210,0.15)";
  g.fillRect(0, s * 0.86, s, s * 0.02);
};

/** A tent's wall: hemp canvas with seams and a band in the army's red. */
const tentWall: Paint = (g, s, v) => {
  const cloth = g.createLinearGradient(0, 0, 0, s);
  cloth.addColorStop(0, "#d8ccab");
  cloth.addColorStop(1, "#bfb08a");
  g.fillStyle = cloth;
  g.fillRect(0, 0, s, s);
  g.strokeStyle = "rgba(110,90,60,0.35)";
  g.lineWidth = Math.max(1, s / 64);
  for (const x of [s * 0.33, s * 0.83]) {
    g.beginPath();
    g.moveTo(x + (hash(v, x, 61) - 0.5) * 2, 0);
    g.lineTo(x, s);
    g.stroke();
  }
  g.fillStyle = "#9b2f28";
  g.fillRect(0, s * 0.12, s, s * 0.1);
  g.fillStyle = "rgba(0,0,0,0.12)";
  g.fillRect(0, s * 0.22, s, s * 0.03);
  g.fillStyle = "rgba(90,70,40,0.3)";
  g.fillRect(0, s * 0.88, s, s * 0.12);
};

/**
 * The top of a wall run seen from above (where the wall continues toward the
 * viewer): glazed roof tiles on a hall, the canvas roof's edge on a tent, a
 * paved walk behind battlements on a stone wall.
 */
const wallTop: Record<Setting, Paint> = {
  hall: (g, s) => {
    g.fillStyle = "#3e3c3b";
    g.fillRect(0, 0, s, s);
    for (let x = 0; x < s; x += s / 6) {
      const grad = g.createLinearGradient(x, 0, x + s / 6, 0);
      grad.addColorStop(0, "#2b2a2a");
      grad.addColorStop(0.5, "#5a5856");
      grad.addColorStop(1, "#2b2a2a");
      g.fillStyle = grad;
      g.fillRect(x + s / 48, 0, s / 6 - s / 24, s);
    }
    g.fillStyle = "rgba(0,0,0,0.3)";
    for (let y = s / 8; y < s; y += s / 4) g.fillRect(0, y, s, s / 40);
  },
  tent: (g, s) => {
    g.fillStyle = "#cbbd98";
    g.fillRect(0, 0, s, s);
    g.strokeStyle = "rgba(110,90,60,0.3)";
    g.lineWidth = Math.max(1, s / 64);
    for (const y of [s * 0.3, s * 0.7]) {
      g.beginPath();
      g.moveTo(0, y);
      g.lineTo(s, y);
      g.stroke();
    }
    g.fillStyle = "#9b2f28";
    g.fillRect(s * 0.42, 0, s * 0.16, s);
  },
  outdoor: (g, s) => {
    g.fillStyle = "#8d877c";
    g.fillRect(0, 0, s, s);
    for (let y = 0; y < s; y += s / 4)
      for (let x = (y / (s / 4)) % 2 ? -s / 6 : 0; x < s; x += s / 3) {
        g.fillStyle = "#9f998d";
        g.fillRect(x + 1, y + 1, s / 3 - 2, s / 4 - 2);
      }
    g.fillStyle = "#6d675d";
    for (let y = 0; y < s; y += s / 3) g.fillRect(0, y, s * 0.16, s / 5);
  },
};

/** Wall faces dressed for where they stand (outdoors the battle painter's stone stays). */
const wallFace: Record<Exclude<Setting, "outdoor">, Paint> = { hall: hallWall, tent: tentWall };

const wallTextures = new Map<string, Texture>();
function wallTexture(style: Setting, part: "face" | "top"): Texture | null {
  if (part === "face" && style === "outdoor") return null;
  const key = `${style}:${part}`;
  let t = wallTextures.get(key);
  if (!t) {
    const c = document.createElement("canvas");
    c.width = 64;
    c.height = 64;
    const g = c.getContext("2d") as CanvasRenderingContext2D;
    if (part === "top") wallTop[style](g, 64, 0);
    else if (style !== "outdoor") wallFace[style](g, 64, 0);
    t = Texture.from(c);
    wallTextures.set(key, t);
  }
  return t;
}

const FLOORS: Record<"boards" | "paving" | "carpet", Paint> = { boards, paving, carpet };

function paintVariants(paint: Paint, size: number): HTMLCanvasElement[] {
  return [0, 1, 2, 3].map((v) => {
    const c = document.createElement("canvas");
    c.width = size;
    c.height = size;
    const g = c.getContext("2d") as CanvasRenderingContext2D;
    paint(g, size, v);
    return c;
  });
}

let terrain: TerrainSet | null = null;

/** Our terrain set with the three floors added. */
export function stageTerrain(): TerrainSet {
  if (terrain) return terrain;
  const base = ourTerrain();
  const variants = new Map(base.variants);
  for (const [id, paint] of Object.entries(FLOORS))
    variants.set(id as TerrainId, paintVariants(paint, base.size));
  terrain = { size: base.size, variants };
  return terrain;
}

/** A tent when the stage says so; indoors when much of the open ground is boards or carpet. */
export function settingOf(def: StageDef, tiles: readonly StageTile[]): Setting {
  // A stage may say what its walls are (a tent's canvas); otherwise a stage with
  // a good share of wooden or carpeted floor is a building.
  const walls = def.walls;
  if (walls === "tent") return "tent";
  if (walls === "hall") return "hall";
  const open = tiles.filter((t) => t !== "wall" && t !== "water" && t !== "mountain");
  const inside = open.filter((t) => t === "boards" || t === "carpet").length;
  return inside * 10 >= open.length * 3 ? "hall" : "outdoor";
}

/**
 * Paints a stage's tiles into the layers (as the battle painter does), then
 * the carpet's gold borders and the soft shadow walls cast on the floor.
 */
export function paintStage(
  grid: { width: number; height: number; tiles: readonly StageTile[] },
  tile: number,
  setting: Setting,
  layers: { ground: Container; water: Container; edges: Container },
): void {
  // Floors ride through the painter as extra tile ids: they are not natural
  // terrain, so they are laid as hard tiles and never spill.
  const map = { width: grid.width, height: grid.height, tiles: grid.tiles } as unknown as BattleMap;
  paintTerrain(map, tile, stageTerrain(), layers);
  const at = (x: number, y: number): StageTile | null =>
    x < 0 || y < 0 || x >= grid.width || y >= grid.height
      ? null
      : (grid.tiles[y * grid.width + x] as StageTile);
  const deco = new Container();
  const g = new Graphics();
  const t = tile;
  // Carpet borders: a gold band and a thin dark line where the carpet ends.
  const band = Math.max(2, t * 0.07);
  for (let y = 0; y < grid.height; y++)
    for (let x = 0; x < grid.width; x++) {
      if (at(x, y) !== "carpet") continue;
      const px = x * t;
      const py = y * t;
      const edge = (nx: number, ny: number) => at(nx, ny) !== "carpet";
      if (edge(x - 1, y)) g.rect(px, py, band, t);
      if (edge(x + 1, y)) g.rect(px + t - band, py, band, t);
      if (edge(x, y - 1)) g.rect(px, py, t, band);
      if (edge(x, y + 1)) g.rect(px, py + t - band, t, band);
    }
  g.fill({ color: 0xd8a84e, alpha: 0.9 });
  for (let y = 0; y < grid.height; y++)
    for (let x = 0; x < grid.width; x++) {
      if (at(x, y) !== "carpet") continue;
      const px = x * t;
      const py = y * t;
      const edge = (nx: number, ny: number) => at(nx, ny) !== "carpet";
      const i = band * 1.6;
      if (edge(x - 1, y)) g.rect(px + i, py, 1.5, t);
      if (edge(x + 1, y)) g.rect(px + t - i - 1.5, py, 1.5, t);
      if (edge(x, y - 1)) g.rect(px, py + i, t, 1.5);
      if (edge(x, y + 1)) g.rect(px, py + t - i - 1.5, t, 1.5);
    }
  g.fill({ color: 0xf2d27e, alpha: 0.7 });
  // Walls: built walls (timber and plaster, or canvas in a tent) within two
  // tiles of an indoor floor, stone elsewhere; a run coming toward the viewer
  // shows its top, the others their face.
  const indoor = (x: number, y: number) => {
    for (let dy = -2; dy <= 2; dy++)
      for (let dx = -2; dx <= 2; dx++) {
        const n = at(x + dx, y + dy);
        if (n === "boards" || n === "carpet") return true;
      }
    return false;
  };
  for (let y = 0; y < grid.height; y++)
    for (let x = 0; x < grid.width; x++) {
      if (at(x, y) !== "wall") continue;
      const style: Setting = setting === "tent" ? "tent" : indoor(x, y) ? "hall" : "outdoor";
      const below = at(x, y + 1);
      const part = below === null || below === "wall" ? "top" : "face";
      const tex = wallTexture(style, part);
      if (!tex) continue;
      const sp = new Sprite(tex);
      sp.position.set(x * t, y * t);
      sp.width = t;
      sp.height = t;
      deco.addChild(sp);
    }
  // Walls throw a soft shadow on the tile below them.
  for (let y = 1; y < grid.height; y++)
    for (let x = 0; x < grid.width; x++) {
      const above = at(x, y - 1);
      const here = at(x, y);
      if (above === "wall" && here !== "wall")
        for (let k = 0; k < 5; k++)
          g.rect(x * t, y * t + k * t * 0.06, t, t * 0.06).fill({
            color: 0x000000,
            alpha: 0.22 * (1 - k / 5),
          });
    }
  deco.addChild(g);
  layers.edges.addChild(deco);
}
