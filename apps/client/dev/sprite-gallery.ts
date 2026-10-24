// Dev gallery for the painted figures (ADR 0073), dev server only: /dev/sprite-gallery.html
// Every family and tier-2 class, the army kits and the heroes, in every pose and facing,
// on a grass tile at display size; then the same figures moving on a Pixi stage.
// Query: ?only=families|tier2|armies|heroes|anim  ?ids=cao-cao,guan-yu  ?tile=64
//        ?facings=down,side  ?poses=stand,attack,walk0,attack1 (a pose, or one frame by
//        the name its caption shows)  ?big (frames at sheet size)
import { Application, Container, Graphics, Text } from "pixi.js";
import {
  BATTLE_POSES,
  cacheReport,
  type Facing,
  type FigurePose,
  FRAMES,
  groupOf,
  lookFor,
  type PoseName,
  paintFrame,
  SCENE_POSES,
  SpriteFigure,
  type SpriteLook,
  sheetFor,
  sheetStats,
  U,
} from "../src/lib/fx/sprite/index.ts";

const q = new URLSearchParams(location.search);
const TILE = Number(q.get("tile")) || 64;
const BIG = q.has("big");
const ZOOM = Number(q.get("zoom")) || 1;
const only = q.get("only");
const FACINGS = (q.get("facings")?.split(",") ?? ["down", "side", "up"]) as Facing[];
/** The frames to show: every frame of a pose ("attack"), or one frame by its caption ("attack1"). */
const PICKS: [PoseName, number[]][] = (() => {
  const all = [...BATTLE_POSES, ...SCENE_POSES] as PoseName[];
  const isPose = (s: string): s is PoseName => (all as string[]).includes(s);
  const every = (p: PoseName): number[] => Array.from({ length: FRAMES[p] }, (_, i) => i);
  const asked = q.get("poses")?.split(",").filter(Boolean);
  if (!asked) return all.map((p) => [p, every(p)]);
  const out: [PoseName, number[]][] = [];
  for (const s of asked) {
    const m = /^([a-zA-Z]+?)(\d+)$/.exec(s.trim());
    const name = m ? (m[1] as string) : s.trim();
    if (!isPose(name)) {
      console.warn(`sprite-gallery: no pose "${s}"; poses are ${all.join(", ")}`);
      continue;
    }
    const frames = m ? [Number(m[2])].filter((i) => i < FRAMES[name]) : every(name);
    if (m && frames.length === 0)
      console.warn(`sprite-gallery: ${name} has ${FRAMES[name]} frames`);
    const have = out.find(([p]) => p === name);
    if (have) have[1] = [...new Set([...have[1], ...frames])].sort((a, b) => a - b);
    else out.push([name, frames]);
  }
  return out;
})();

interface Row {
  label: string;
  id: string;
  classId: string;
  side: number;
}

const FAMILY_ROWS: Row[] = [
  { label: "보병 infantry", id: "cao-infantry-1", classId: "infantry", side: 0 },
  { label: "궁병 archer", id: "cao-archer-1", classId: "archer", side: 0 },
  { label: "기병 cavalry", id: "cao-cavalry-1", classId: "cavalry", side: 0 },
  { label: "책사 strategist", id: "cao-strategist-1", classId: "strategist", side: 0 },
  { label: "무도가 martial artist", id: "cao-martialArtist-1", classId: "martialArtist", side: 0 },
  { label: "포차 stone-thrower", id: "cao-catapult-1", classId: "catapult", side: 0 },
];
const TIER2_ROWS: Row[] = [
  {
    label: "중보병 heavy infantry",
    id: "yuan-elite-infantry-1",
    classId: "heavyInfantry",
    side: 1,
  },
  { label: "강궁병 longbow", id: "yuan-archer-2", classId: "longbow", side: 1 },
  { label: "돌기병 shock cavalry", id: "yuan-cavalry-2", classId: "shockCavalry", side: 1 },
  { label: "군사 tactician", id: "yuan-strategist-2", classId: "tactician", side: 1 },
  { label: "호위무사 guardian", id: "yuan-martialArtist-2", classId: "guardian", side: 1 },
  { label: "벽력거 thunder cart", id: "yuan-catapult-2", classId: "thunderCart", side: 1 },
];
const ARMIES = [
  "cao",
  "ward",
  "yuan",
  "huangjin",
  "qingzhou",
  "dong",
  "heishan",
  "jianjia",
  "xu",
  "lu",
  "xianzhen",
  "zhang",
  "jing",
  "zhong",
  "wuhuan",
  "bing",
  "liu",
];
const ARMY_ROWS: Row[] = ARMIES.flatMap((a, i) =>
  (["infantry", "archer", "cavalry"] as const).map((c) => ({
    label: `${a} ${c}`,
    id: `${a}-${c}-${i}`,
    classId: c,
    side: a === "cao" || a === "ward" ? 0 : a === "liu" ? 2 : 1,
  })),
);
const HEROES: [string, string, number][] = [
  ["cao-cao", "cavalry", 0],
  ["guan-yu", "cavalry", 0],
  ["zhang-fei", "infantry", 2],
  ["liu-bei", "infantry", 2],
  ["lu-bu", "cavalry", 1],
  ["xiahou-dun", "infantry", 0],
  ["xu-chu", "martialArtist", 0],
  ["dian-wei", "martialArtist", 0],
  ["zhang-liao", "cavalry", 0],
  ["zhao-yun", "cavalry", 2],
  ["yuan-shao", "strategist", 1],
  ["dong-zhuo", "cavalry", 1],
];
const HERO_ROWS: Row[] = (
  q
    .get("ids")
    ?.split(",")
    .map((s) => {
      // id (a hero takes its class and side from the table above), or id:class:side
      const [id, cls, side] = s.split(":");
      const known = HEROES.find(([h]) => h === id);
      return [id ?? "", cls || known?.[1] || "", side ? Number(side) : (known?.[2] ?? -1)] as [
        string,
        string,
        number,
      ];
    }) ?? HEROES
).map(([id, classId, side]) => ({
  label: id,
  id,
  classId: classId || "infantry",
  side: side < 0 ? 0 : side,
}));

const app = document.getElementById("app") as HTMLElement;

function section(title: string): HTMLElement {
  const h = document.createElement("h2");
  h.textContent = title;
  app.appendChild(h);
  const box = document.createElement("div");
  box.className = "grid";
  app.appendChild(box);
  return box;
}

/** One frame on a grass tile at display size (or at sheet size with ?big). */
function frameCanvas(L: SpriteLook, pose: PoseName, i: number, facing: Facing): HTMLCanvasElement {
  const k = (BIG ? 1 : TILE / U) * ZOOM;
  const cw = Math.ceil(2.4 * U * k);
  const ch = Math.ceil(2.6 * U * k);
  const c = document.createElement("canvas");
  c.width = cw;
  c.height = ch;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  // The tile, with the feet on its lower third, and the side ring.
  const t = U * k;
  const cx = cw / 2;
  const cy = ch - t * 0.62;
  g.fillStyle = "#6f9a4a";
  g.fillRect(cx - t / 2, cy - t / 2, t, t);
  g.strokeStyle = "rgba(0,0,0,0.25)";
  g.strokeRect(cx - t / 2, cy - t / 2, t, t);
  const ring = ["#d64a3c", "#3b7be8", "#e8892c", "#b08a2e"][L.side] ?? "#888";
  g.strokeStyle = ring;
  g.lineWidth = 2;
  g.beginPath();
  g.ellipse(cx, cy + t * 0.3, t * 0.34, t * 0.13, 0, 0, Math.PI * 2);
  g.stroke();
  // Through the sheet, as the game shows it.
  const sheet = sheetFor(L, facing, groupOf(pose));
  const f = sheet.frames[pose]?.[i];
  if (f) {
    const fr = f.tex.frame;
    const w = fr.width * k;
    const h = fr.height * k;
    g.drawImage(
      sheet.canvas,
      fr.x,
      fr.y,
      fr.width,
      fr.height,
      cx - f.ax * w,
      cy + 0.32 * t - f.ay * h,
      w,
      h,
    );
  }
  return c;
}

function rowOf(box: HTMLElement, r: Row): void {
  const L = lookFor(r.classId, r.id, r.side);
  const row = document.createElement("div");
  row.className = "row";
  const label = document.createElement("div");
  label.className = "label";
  label.innerHTML = `${r.label}<small>${r.classId} · side ${r.side}</small>`;
  row.appendChild(label);
  for (const facing of FACINGS)
    for (const [pose, frames] of PICKS)
      for (const i of frames) {
        const fig = document.createElement("figure");
        fig.appendChild(frameCanvas(L, pose, i, facing));
        const cap = document.createElement("figcaption");
        cap.textContent = `${facing[0]} ${pose}${FRAMES[pose] > 1 ? i : ""}`;
        fig.appendChild(cap);
        row.appendChild(fig);
      }
  box.appendChild(row);
}

async function anim(rows: Row[]): Promise<void> {
  const h = document.createElement("h2");
  h.textContent =
    "Moving (Pixi): ready, walk, attack, hurt, guard, cheer, weak; scene: bow, kneel, nod, shake, jump, run, sit, point, down";
  app.appendChild(h);
  const host = document.createElement("div");
  host.style.height = `${rows.length * TILE * 1.7 + 40}px`;
  app.appendChild(host);
  const px = new Application();
  await px.init({
    width: 1500,
    height: rows.length * TILE * 1.7 + 40,
    background: 0x1b1a17,
    antialias: true,
    resolution: Math.min(2, devicePixelRatio || 1),
    autoDensity: true,
  });
  host.appendChild(px.canvas);
  const poses: FigurePose[] = [
    "ready",
    "walk",
    "attack",
    "hurt",
    "guard",
    "cheer",
    "weak",
    "bow",
    "kneel",
    "nod",
    "shake",
    "jump",
    "run",
    "sit",
    "point",
    "down",
  ];
  const figs: { f: SpriteFigure; p: FigurePose; age: number }[] = [];
  rows.forEach((r, ri) => {
    const L = lookFor(r.classId, r.id, r.side);
    poses.forEach((p, ci) => {
      const facing: Facing = ci % 3 === 0 ? "down" : ci % 3 === 1 ? "side" : "up";
      const x = 40 + ci * TILE * 1.4;
      const y = 30 + ri * TILE * 1.7 + TILE;
      const tile = new Graphics().rect(-TILE / 2, -TILE / 2, TILE, TILE).fill(0x6f9a4a);
      tile.position.set(x, y);
      const f = new SpriteFigure(L);
      f.setSize(TILE);
      f.setFacing(facing, false);
      f.setPose(p);
      f.root.position.set(x, y);
      const label = new Text({
        text: `${facing[0]} ${p}`,
        style: { fill: 0xa99f8c, fontSize: 10 },
      });
      label.position.set(x - TILE / 2, y + TILE / 2);
      const c = new Container();
      c.addChild(tile, f.root, label);
      px.stage.addChild(c);
      figs.push({ f, p, age: 0 });
    });
  });
  px.ticker.add(() => {
    const ms = px.ticker.deltaMS;
    for (const r of figs) {
      r.age += ms;
      if (
        ["attack", "hurt", "guard", "bow", "nod", "shake", "jump"].includes(r.p) &&
        r.age > 1600
      ) {
        r.age = 0;
        r.f.setPose(r.p);
      }
      r.f.update(ms, ms);
    }
  });
  (window as unknown as { __figs: unknown }).__figs = figs;
}

/** ?bench: paint time per frame by family and pose, painted straight to a canvas. */
function bench(): void {
  const c = document.createElement("canvas");
  c.width = 360;
  c.height = 380;
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  const rows: string[] = [];
  for (const r of [...FAMILY_ROWS, ...TIER2_ROWS, ...HERO_ROWS]) {
    const L = lookFor(r.classId, r.id, r.side);
    const per: string[] = [];
    let total = 0;
    let n = 0;
    for (const pose of BATTLE_POSES)
      for (let i = 0; i < FRAMES[pose]; i++) {
        const t = performance.now();
        for (let k = 0; k < 5; k++) {
          g.setTransform(1, 0, 0, 1, 0, 0);
          g.clearRect(0, 0, c.width, c.height);
          g.setTransform(U, 0, 0, U, 170, 220);
          paintFrame(g, L, pose, i, "down");
        }
        const ms = (performance.now() - t) / 5;
        total += ms;
        n++;
        if (i === 0) per.push(`${pose} ${ms.toFixed(1)}`);
      }
    rows.push(`${r.label}: ${(total / n).toFixed(2)} ms/frame (${per.join(", ")})`);
  }
  const pre = document.createElement("pre");
  pre.id = "bench";
  pre.textContent = rows.join("\n");
  app.appendChild(pre);
}
if (only === "bench") bench();

/**
 * ?only=export: frames on a tile grid (2×2-tile cells, the tile at the bottom
 * middle), one row per family, for side-by-side checks in scratch tools.
 */
function exportGrid(): void {
  const T = TILE;
  const picks: [PoseName, number, Facing][] = [
    ["walk", 0, "down"],
    ["walk", 1, "down"],
    ["stand", 0, "down"],
    ["attack", 0, "down"],
    ["attack", 1, "down"],
    ["attack", 2, "down"],
    ["attack", 3, "down"],
    ["walk", 0, "side"],
    ["walk", 1, "side"],
    ["stand", 0, "side"],
    ["attack", 0, "side"],
    ["attack", 1, "side"],
    ["attack", 2, "side"],
    ["attack", 3, "side"],
  ];
  const rows = [...FAMILY_ROWS, ...HERO_ROWS.filter((r) => r.id === "guan-yu")];
  const c = document.createElement("canvas");
  c.width = picks.length * 2 * T;
  c.height = rows.length * 2 * T;
  c.id = "export";
  const g = c.getContext("2d") as CanvasRenderingContext2D;
  rows.forEach((r, ri) => {
    const L = lookFor(r.classId, r.id, r.side);
    picks.forEach(([pose, i, facing], ci) => {
      const sheet = sheetFor(L, facing, groupOf(pose));
      const f = sheet.frames[pose]?.[i];
      if (!f) return;
      const k = T / U;
      const fr = f.tex.frame;
      const cx = ci * 2 * T + T;
      const cy = ri * 2 * T + 1.4 * T;
      g.drawImage(
        sheet.canvas,
        fr.x,
        fr.y,
        fr.width,
        fr.height,
        cx - f.ax * fr.width * k,
        cy + 0.32 * T - f.ay * fr.height * k,
        fr.width * k,
        fr.height * k,
      );
    });
  });
  app.appendChild(c);
}
if (only === "export") exportGrid();
const t0 = performance.now();
if (!only || only === "families") {
  const box = section("Families");
  for (const r of FAMILY_ROWS) rowOf(box, r);
}
if (!only || only === "tier2") {
  const box = section("Tier-2 classes");
  for (const r of TIER2_ROWS) rowOf(box, r);
}
if (!only || only === "heroes") {
  const box = section("Heroes");
  for (const r of HERO_ROWS) rowOf(box, r);
}
if (only === "armies") {
  const box = section("Army kits");
  for (const r of ARMY_ROWS) rowOf(box, r);
}
if (only === "anim") await anim([...FAMILY_ROWS, ...HERO_ROWS.slice(0, 6)]);
const info = document.createElement("p");
const rep = cacheReport();
info.textContent = `${sheetStats.painted} sheets, ${sheetStats.frames} frames painted in ${Math.round(sheetStats.ms)} ms (${Math.round(performance.now() - t0)} ms with the page); cache ${rep.sheets} sheets, ${(rep.bytes / 1048576).toFixed(1)} MB`;
info.id = "stats";
app.prepend(info);
(window as unknown as { __stats: unknown }).__stats = { ...sheetStats, ...rep };
void paintFrame;
