// Dev gallery for staged scenes (ADR 0063), dev server only: /dev/stage-gallery.html
// Every prop, the floors and walls, figures of each kind in the scene poses,
// emotes, effects, fire, a flood and the three lights, with buttons to try them.
import "../src/app.css";
import type { ActPose, Emote, PropDef, StageDef, StageFx, StageLight } from "@tk/content";
import { audio } from "../src/lib/audio/index.ts";
import { type ActorSpec, Stage } from "../src/lib/stage/stage.ts";

const c = (x: number, y: number) => ({ x, y });

const STAGE: StageDef = {
  id: "gallery",
  light: "day",
  rows: [
    "######==######",
    "_____r==r____.",
    "_____r==r____.",
    "..............",
    "..........ww..",
    "...gg.....ww..",
    "..ggg.........",
    "..............",
    "==============",
  ],
  props: [
    { kind: "gate", at: c(6, 0), solid: false },
    { kind: "gate", at: c(7, 0), solid: false },
    { kind: "throne", at: c(1, 1) },
    { kind: "screen", at: c(2, 1) },
    { kind: "table", at: c(3, 2) },
    { kind: "table", at: c(4, 2) },
    { kind: "pillar", at: c(5, 1) },
    { kind: "pillar", at: c(8, 1) },
    { kind: "lantern", at: c(9, 1) },
    { kind: "chest", at: c(10, 1) },
    { kind: "bed", at: c(11, 1) },
    { kind: "statue", at: c(12, 1) },
    { kind: "banner", at: c(0, 3) },
    { kind: "banner", at: c(13, 3), side: 1 },
    { kind: "tent", at: c(2, 4), side: 1 },
    { kind: "brazier", at: c(5, 4) },
    { kind: "horse", at: c(12, 6), solid: true },
    { kind: "cart", at: c(8, 6) },
    { kind: "grain", at: c(1, 7) },
    { kind: "rack", at: c(4, 7) },
    { kind: "well", at: c(6, 7) },
    { kind: "fence", at: c(9, 7) },
    { kind: "fence", at: c(10, 7) },
    { kind: "fence", at: c(11, 7) },
    { kind: "sluice", at: c(10, 4) },
  ] satisfies PropDef[],
};

const CAST: ActorSpec[] = [
  { id: "cao-cao", officer: "cao-cao", classId: "cavalry", side: 0, at: c(7, 3), facing: "down" },
  { id: "xun-yu", officer: "xun-yu", classId: "strategist", side: 0, at: c(9, 3), facing: "left" },
  {
    id: "dian-wei",
    officer: "dian-wei",
    classId: "martialArtist",
    side: 0,
    at: c(6, 5),
    facing: "up",
  },
  {
    id: "xiahou-yuan",
    officer: "xiahou-yuan",
    classId: "archer",
    side: 0,
    at: c(8, 5),
    facing: "right",
  },
  {
    id: "rebel",
    officer: "zhang-jiao",
    classId: "infantry",
    side: 1,
    at: c(3, 6),
    facing: "right",
  },
];

const POSES: ActPose[] = [
  "bow",
  "kneel",
  "down",
  "nod",
  "shake",
  "jump",
  "attack",
  "hurt",
  "guard",
  "cheer",
  "stand",
];
const EMOTES: Emote[] = ["!", "?", "...", "sweat", "anger", "heart", "note", "idea", "tear"];
const FX: StageFx[] = ["flash", "shake", "smoke", "sparks", "splash", "dust"];
const LIGHTS: StageLight[] = ["day", "dusk", "night"];

const params = new URLSearchParams(location.search);
const stage = new Stage({ lite: params.has("lowfx"), calm: params.has("calm") });
await stage.mount(document.getElementById("stage") as HTMLElement);
stage.setInsets({ top: 8, right: 8, bottom: 64, left: 8 });
stage.load(STAGE, CAST);
(window as unknown as { tkStage: Stage }).tkStage = stage;

const bar = document.getElementById("bar") as HTMLElement;
function group(label: string) {
  const s = document.createElement("span");
  s.textContent = label;
  bar.appendChild(s);
}
function button(label: string, run: () => void) {
  const b = document.createElement("button");
  b.textContent = label;
  b.onclick = () => {
    audio.unlock();
    run();
  };
  bar.appendChild(b);
}
const everyone = () => CAST.map((a) => a.id);
group("pose");
for (const p of POSES) button(p, () => everyone().forEach((id) => void stage.act(id, p)));
group("emote");
for (const e of EMOTES) button(e, () => everyone().forEach((id) => void stage.emote(id, e)));
group("fx");
for (const f of FX)
  button(f, () => void stage.effect(f, f === "flash" || f === "shake" ? undefined : c(7, 4)));
group("light");
for (const l of LIGHTS) button(l, () => void stage.light(l));
group("stage");
button(
  "walk",
  () => void stage.walk("cao-cao", c(2, 3)).then(() => stage.walk("cao-cao", c(7, 3))),
);
button("strike", () => void stage.strike("dian-wei", "rebel", true));
button("fire", () => stage.fire([c(3, 5), c(4, 5), c(4, 6)]));
button(
  "flood",
  () =>
    void stage.retile(
      [c(9, 4), c(9, 5), c(12, 4)].map((t) => ({ ...t, terrain: "water" as const })),
    ),
);
button("zoom", () => void stage.camera("cao-cao", 1600));
button("wide", () => void stage.camera(c(7, 4), 1000));
