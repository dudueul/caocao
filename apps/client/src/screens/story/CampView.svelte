<script lang="ts">
import { chapterOf } from "@tk/campaign";
import {
  CAMP_LINES,
  type CampDef,
  type Facing4,
  holds,
  type Line,
  OFFICERS,
  type StationKind,
} from "@tk/content";
import type { Cell } from "@tk/rules";
import { onMount, untrack } from "svelte";
import DialogueBox from "../../components/DialogueBox.svelte";
import { audio } from "../../lib/audio/index.ts";
import { settings } from "../../lib/settings.svelte.ts";
import { speakerOf } from "../../lib/speakers.ts";
import { besideOf, same } from "../../lib/stage/path.ts";
import { type ActorSpec, facingTo, Stage } from "../../lib/stage/stage.ts";
import { story } from "../../lib/story.svelte.ts";
import CampPanels, { type Tab } from "./CampPanels.svelte";

/**
 * The walkable camp (ADR 0064): 조조 walks the camp ground with the arrow keys,
 * WASD or a tap; his officers loiter and turn to him as he passes, and talk
 * when he walks into them; the stations open the old camp panels (shop,
 * armoury, records) or lead out of camp (the command tent).
 */
const ME = "cao-cao";
const L = {
  camp: "군영",
  go: "출진 준비",
  officers: "장수",
  station: { shop: "상점", armoury: "무기고", records: "기록", council: "군막" } as Record<
    StationKind,
    string
  >,
  hint: "방향키·WASD 또는 탭으로 걷기 · 장수에게 다가가면 대화 · 막사에 들어가면 정비",
  hintTouch: "땅을 눌러 걷기 · 장수를 누르면 대화 · 막사를 누르면 정비",
  council: "군막",
  councilText: "출진 준비를 마쳤습니까? 진영을 떠나 다음 싸움으로 나아갑니다.",
  stay: "머문다",
  march: "출진한다",
} as const;
const GENERIC = [
  "주공, 분부만 내리십시오.",
  "군사들의 사기가 높습니다. 언제든 나설 수 있습니다.",
  "잠시 숨을 돌리고 있었습니다. 곧 채비를 마치겠습니다.",
  "다음 싸움에서도 주공을 따르겠습니다.",
];
const PANEL: Partial<Record<StationKind, Tab>> = {
  shop: "shop",
  armoury: "roster",
  records: "save",
};
const KEYS: Record<string, Facing4> = {
  ArrowUp: "up",
  ArrowDown: "down",
  ArrowLeft: "left",
  ArrowRight: "right",
  w: "up",
  s: "down",
  a: "left",
  d: "right",
  W: "up",
  S: "down",
  A: "left",
  D: "right",
};

const camp = $derived(story.camp);
const campaign = $derived(story.campaign);
const chapter = $derived.by(() => {
  const ch = chapterOf(campaign);
  return `${ch.name} ${ch.title}`;
});

let host: HTMLDivElement;
let stage = $state.raw<Stage | null>(null);
let failed = $state(false);
let built = $state<string | null>(null);
let panel = $state<Tab | null>(null);
let council = $state(false);
let talk = $state<{ id: string; lines: readonly Line[]; index: number } | null>(null);
let hint = $state(true);
const touch = typeof matchMedia === "function" && matchMedia("(pointer: coarse)").matches;
const talkLine = $derived(talk ? (talk.lines[talk.index] ?? null) : null);
const speaker = $derived(talkLine ? speakerOf(talkLine, campaign) : null);

/** Officers standing about: id → their spot and the way they face when 조조 is away. */
let loiter = new Map<string, { at: Cell; facing: Facing4 }>();
const held = new Set<Facing4>();
let order: Facing4[] = [];
let walking = false;
/** Bumped to cut a walk short (a new tap, a panel opening). */
let walkToken = 0;
let hintTimer: ReturnType<typeof setTimeout> | undefined;

const busy = () => panel !== null || council || talk !== null;

onMount(() => {
  const params = new URLSearchParams(location.search);
  const calm = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  const s = new Stage({ lite: params.has("lowfx") || calm || settings.lite, calm });
  if (params.has("debug")) (window as unknown as { tkStage: Stage }).tkStage = s;
  s.mount(host).then(
    () => {
      if (s.gone) return;
      s.setInsets({ top: 60, right: 0, bottom: 0, left: 0 });
      stage = s;
    },
    (e: unknown) => {
      console.error(e);
      failed = true;
    },
  );
  hintTimer = setTimeout(() => (hint = false), 9000);
  return () => {
    clearTimeout(hintTimer);
    walkToken += 1;
    s.destroy();
  };
});

$effect(() => {
  const c = camp;
  const s = stage;
  if (!c || !s || built === c.id) return;
  untrack(() => setUp(c, s));
});

function setUp(c: CampDef, s: Stage) {
  try {
    build(c, s);
  } catch (e) {
    // Without a ground to walk, the stations are offered as plain buttons.
    console.error(e);
    failed = true;
  }
}

function build(c: CampDef, s: Stage) {
  const g = c.ground;
  const roster = campaign.roster;
  const me = roster.find((o) => o.id === ME);
  const cast: ActorSpec[] = [
    { id: ME, officer: ME, classId: me?.classId ?? "cavalry", side: 0, at: g.start, facing: "up" },
  ];
  loiter = new Map();
  const mid = { x: Math.floor(s.size.width / 2), y: Math.floor(s.size.height / 2) };
  const others = roster.filter((o) => o.id !== ME);
  others.forEach((o, i) => {
    const at = g.spots[i];
    if (!at) return;
    const facing = facingTo(at, mid, "down");
    loiter.set(o.id, { at, facing });
    cast.push({ id: o.id, officer: o.id, classId: o.classId, side: 0, at, facing });
  });
  s.load(g.stage, cast, true);
  for (const st of g.stations) s.station(st.at, L.station[st.kind]);
  s.follow(ME);
  s.mark(ME);
  s.onTap = (cell, who) => tap(cell, who);
  built = c.id;
  look();
}

// ------------------------------------------------------------ walking

function onKey(e: KeyboardEvent) {
  if (!stage || busy()) return;
  const dir = KEYS[e.key];
  if (dir) {
    e.preventDefault();
    hint = false;
    if (!held.has(dir)) {
      held.add(dir);
      order = [...order.filter((d) => d !== dir), dir];
    }
    if (!walking) void stepLoop();
    return;
  }
  if (e.key === "Enter" || e.key === " ") {
    e.preventDefault();
    act();
  }
}

function onKeyUp(e: KeyboardEvent) {
  const dir = KEYS[e.key];
  if (!dir) return;
  held.delete(dir);
  order = order.filter((d) => d !== dir);
}

/** Walks one tile at a time while a direction key is held. */
async function stepLoop() {
  const s = stage;
  if (!s || walking) return;
  walking = true;
  walkToken += 1;
  s.target(null);
  try {
    while (!busy()) {
      const dir = order[order.length - 1];
      if (!dir || !held.has(dir)) break;
      const here = s.cellOf(ME);
      if (!here) break;
      const d = { up: [0, -1], down: [0, 1], left: [-1, 0], right: [1, 0] }[dir];
      const next = { x: here.x + (d[0] ?? 0), y: here.y + (d[1] ?? 0) };
      const who = s.actorAt(next, ME);
      const moved = await s.stepTo(ME, dir);
      if (!moved) {
        if (who) talkTo(who);
        break;
      }
      arrived();
    }
  } finally {
    walking = false;
    s.idle(ME);
  }
}

/** Taps: an officer to talk to, a station to visit, or a tile to walk to. */
async function tap(cell: Cell, who: string | null) {
  const s = stage;
  if (!s || busy()) return;
  hint = false;
  const here = s.cellOf(ME);
  if (!here) return;
  if (who === ME) {
    act();
    return;
  }
  const token = ++walkToken;
  if (who) {
    const beside = besideOf(
      cell,
      here,
      (c) => s.open(c),
      (c) => !s.actorAt(c, ME),
    );
    if (beside && !same(beside, here)) await walkTo(beside, token);
    if (token === walkToken && !busy()) talkTo(who);
    return;
  }
  if (!s.open(cell)) return;
  await walkTo(cell, token);
}

async function walkTo(to: Cell, token: number) {
  const s = stage;
  if (!s) return;
  while (walking) await s.wait(30);
  if (token !== walkToken) return;
  const path = s.pathFor(ME, to, true);
  if (path.length < 2) {
    arrived();
    return;
  }
  walking = true;
  s.target(to);
  let i = 0;
  try {
    // Step by step, so the camp reacts on the way (officers turn, stations open).
    for (i = 1; i < path.length; i++) {
      if (token !== walkToken || busy()) break;
      const from = path[i - 1] as Cell;
      const next = path[i] as Cell;
      if (s.actorAt(next, ME)) break;
      await s.walkPath(ME, [from, next]);
      if (i < path.length - 1) look();
    }
  } finally {
    walking = false;
    s.target(null);
    s.idle(ME);
  }
  if (token === walkToken && i >= path.length) arrived();
  else look();
}

/** After each step: officers nearby turn to 조조; a station underfoot opens. */
function arrived() {
  look();
  const s = stage;
  const c = camp;
  const here = s?.cellOf(ME);
  if (!s || !c || !here) return;
  const st = c.ground.stations.find((x) => same(x.at, here));
  if (st) visit(st.kind);
}

function look() {
  const s = stage;
  const here = s?.cellOf(ME);
  if (!s || !here) return;
  for (const [id, home] of loiter) {
    const at = s.cellOf(id);
    if (!at) continue;
    const d = Math.abs(at.x - here.x) + Math.abs(at.y - here.y);
    if (d <= 2) s.face(id, ME, false);
    else s.face(id, home.facing, false);
    s.tag(id, d === 1 ? (OFFICERS[id]?.name ?? id) : null);
  }
}

/** Enter or Space: talk to the officer ahead (or beside), or use the station underfoot. */
function act() {
  const s = stage;
  const c = camp;
  const here = s?.cellOf(ME);
  if (!s || !c || !here) return;
  const f = s.facingOf(ME) ?? "up";
  const d = { up: [0, -1], down: [0, 1], left: [-1, 0], right: [1, 0] }[f];
  const ahead = s.actorAt({ x: here.x + (d[0] ?? 0), y: here.y + (d[1] ?? 0) }, ME);
  const near =
    ahead ??
    [...loiter.keys()].find((id) => {
      const at = s.cellOf(id);
      return at !== null && Math.abs(at.x - here.x) + Math.abs(at.y - here.y) === 1;
    });
  if (near) {
    talkTo(near);
    return;
  }
  const st = c.ground.stations.find((x) => same(x.at, here));
  if (st) visit(st.kind);
}

// ------------------------------------------------------------ stations and talk

function visit(kind: StationKind) {
  walkToken += 1;
  held.clear();
  order = [];
  if (kind === "council") {
    council = true;
    audio.play("select");
    return;
  }
  const tab = PANEL[kind];
  if (tab) {
    panel = tab;
    audio.play("page");
  }
}

function linesFor(id: string): readonly Line[] {
  const flags = campaign.flags;
  const own = camp?.talk?.find((t) => t.officer === id && holds(t.if, flags));
  const lines = (own?.lines ?? CAMP_LINES[id] ?? []).filter((l) => holds(l.if, flags));
  if (lines.length > 0) return lines;
  let h = 0;
  for (const ch of id) h = (h * 31 + ch.charCodeAt(0)) >>> 0;
  return [{ who: id, text: GENERIC[h % GENERIC.length] as string }];
}

function talkTo(id: string) {
  const s = stage;
  if (!s || busy() || !loiter.has(id)) return;
  walkToken += 1;
  held.clear();
  order = [];
  s.face(ME, id, false);
  s.face(id, ME, false);
  s.tag(id, null);
  void s.emote(id, "!");
  s.speaking(id);
  talk = { id, lines: linesFor(id), index: 0 };
  // Keep the two of them above the dialogue box.
  s.setCover(window.innerWidth <= 600 ? 230 : 152);
  s.follow([ME, id], false);
}

function nextTalk() {
  const t = talk;
  if (!t) return;
  if (t.index + 1 < t.lines.length) {
    talk = { ...t, index: t.index + 1 };
    const who = t.lines[t.index + 1]?.who ?? null;
    stage?.speaking(who && stage.has(who) ? who : null);
    return;
  }
  talk = null;
  stage?.speaking(null);
  stage?.setCover(0);
  stage?.follow(ME, false);
  look();
}

function closePanel() {
  panel = null;
}

function stay() {
  council = false;
  audio.play("cancel");
}

function march() {
  council = false;
  audio.play("confirm");
  story.leaveCamp();
}

function leave() {
  audio.play("confirm");
  story.leaveCamp();
}
</script>

<svelte:window onkeydown={onKey} onkeyup={onKeyUp} onblur={() => held.clear()} />

{#snippet coinIcon()}
  <svg viewBox="0 0 24 24" class="coin" aria-hidden="true">
    <circle cx="12" cy="12" r="10" fill="url(#campmap-coin)" stroke="#7a5418" stroke-width="1.2" />
    <circle cx="12" cy="12" r="7.6" fill="none" stroke="#a87724" stroke-width="0.8" />
    <rect x="9" y="9" width="6" height="6" fill="#3a2508" stroke="#7a5418" stroke-width="0.8" />
  </svg>
{/snippet}

<svg width="0" height="0" class="defs" aria-hidden="true">
  <defs>
    <radialGradient id="campmap-coin" cx="0.35" cy="0.3" r="0.8">
      <stop offset="0" stop-color="#fff0b8" />
      <stop offset="0.55" stop-color="#e8c26a" />
      <stop offset="1" stop-color="#a8792a" />
    </radialGradient>
  </defs>
</svg>

<main class="campmap">
  <div class="stage" bind:this={host}></div>
  <header class="bar">
    <div class="titles">
      <div class="kicker">{chapter}</div>
      <h1>{L.camp}</h1>
    </div>
    <div class="gold" title="군자금">
      {@render coinIcon()}
      {#key campaign.gold}
        <b class="pulse">{campaign.gold.toLocaleString("ko-KR")}</b>
      {/key}
    </div>
    <div class="actions">
      <button type="button" class="roster" onclick={() => visit("armoury")}>{L.officers}</button>
      <button type="button" class="primary go" onclick={leave}>{L.go}</button>
      <a class="button home" href="#/" aria-label="타이틀로">☰</a>
    </div>
  </header>

  {#if failed}
    <div class="fallback">
      {#each camp?.ground.stations ?? [] as st (st.kind)}
        <button type="button" onclick={() => visit(st.kind)}>{L.station[st.kind]}</button>
      {/each}
    </div>
  {/if}

  {#if hint && !talk && !panel}
    <div class="hint" role="status">{touch ? L.hintTouch : L.hint}</div>
  {/if}

  {#if talk && talkLine}
    <div class="dialogue">
      {#key `${talk.id}:${talk.index}`}
        <DialogueBox line={talkLine} {speaker} onnext={nextTalk} />
      {/key}
    </div>
  {/if}

  {#if council}
    <div class="confirm">
      <button type="button" class="scrim" aria-label={L.stay} onclick={stay}></button>
      <div class="sheet" role="dialog" aria-modal="true" aria-labelledby="council-title">
        <h2 id="council-title">{L.council}</h2>
        <p>{L.councilText}</p>
        <div class="choices">
          <button type="button" onclick={stay}>{L.stay}</button>
          <button type="button" class="primary" onclick={march}>{L.march}</button>
        </div>
      </div>
    </div>
  {/if}

  {#if panel}
    <CampPanels tab={panel} onclose={closePanel} />
  {/if}
</main>

<style>
.campmap {
  position: fixed;
  inset: 0;
  overflow: hidden;
  background: #14130f;
}
.defs {
  position: absolute;
}
.stage {
  position: absolute;
  inset: 0;
}
.bar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 5;
  display: flex;
  align-items: center;
  gap: 12px;
  min-height: 56px;
  padding: max(6px, env(safe-area-inset-top, 0px)) max(12px, env(safe-area-inset-right, 0px)) 6px
    max(14px, env(safe-area-inset-left, 0px));
  background: linear-gradient(180deg, rgba(14, 12, 9, 0.92), rgba(14, 12, 9, 0.6) 70%, transparent);
  pointer-events: none;
}
.bar > * {
  pointer-events: auto;
}
.titles {
  min-width: 0;
}
.kicker {
  font-size: 11px;
  letter-spacing: 0.14em;
  color: var(--accent);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
h1 {
  margin: 0;
  font-family: var(--display);
  font-size: 22px;
  font-weight: 900;
  letter-spacing: 0.12em;
  line-height: 1.1;
  background: linear-gradient(180deg, #fff6d6, #e8c26a 62%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}
.gold {
  margin-left: auto;
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 5px 12px 5px 7px;
  border-radius: 999px;
  background: rgba(0, 0, 0, 0.5);
  border: 1px solid var(--gold-line);
}
.gold b {
  font-size: 16px;
  font-weight: 900;
  color: #ffe7a3;
  font-variant-numeric: tabular-nums;
}
.pulse {
  animation: pulse 0.5s;
}
.coin {
  width: 18px;
  height: 18px;
  flex: none;
}
.actions {
  display: flex;
  gap: 8px;
}
.actions button,
.actions .home {
  min-height: 40px;
  white-space: nowrap;
}
.actions .home {
  display: grid;
  place-items: center;
  min-width: 40px;
}
.go {
  padding: 6px 16px;
  font-family: var(--display);
  letter-spacing: 0.1em;
}
.hint {
  position: absolute;
  left: 50%;
  bottom: max(16px, env(safe-area-inset-bottom, 0px));
  transform: translateX(-50%);
  max-width: calc(100% - 120px);
  padding: 8px 16px;
  border-radius: 999px;
  font-size: 13px;
  text-align: center;
  color: #f4e6c4;
  background: rgba(10, 9, 7, 0.75);
  border: 1px solid var(--gold-line);
  pointer-events: none;
  animation: hintIn 0.6s ease-out both;
}
.dialogue {
  position: absolute;
  left: 0;
  right: 0;
  bottom: max(14px, env(safe-area-inset-bottom, 0px));
  z-index: 6;
}
.fallback {
  position: absolute;
  inset: 70px 16px auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
}
.fallback button {
  min-width: 120px;
  min-height: 56px;
  font-size: 18px;
}
.confirm {
  position: fixed;
  inset: 0;
  z-index: 30;
  display: grid;
  place-items: center;
}
.scrim {
  position: absolute;
  inset: 0;
  padding: 0;
  border: none;
  border-radius: 0;
  background: rgba(0, 0, 0, 0.55);
  box-shadow: none;
  cursor: default;
}
.scrim:hover:not(:disabled) {
  border: none;
  box-shadow: none;
}
.sheet {
  position: relative;
  width: min(420px, calc(100% - 24px));
  display: grid;
  gap: 12px;
  padding: 20px;
  border-radius: 18px;
  text-align: center;
  background:
    radial-gradient(120% 70% at 50% 0%, rgba(232, 194, 106, 0.2), transparent 70%), #16130e;
  border: 1px solid rgba(232, 194, 106, 0.6);
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.7);
  animation: sheetIn 0.26s cubic-bezier(0.2, 0.9, 0.3, 1.1);
}
.sheet h2 {
  margin: 0;
  font-family: var(--display);
  font-size: 22px;
  letter-spacing: 0.14em;
  color: #f4e2b0;
}
.sheet p {
  margin: 0;
  color: #e8dfcc;
  word-break: keep-all;
}
.choices {
  display: grid;
  grid-template-columns: 1fr 1.5fr;
  gap: 8px;
}
.choices button {
  min-height: 48px;
  border-radius: 12px;
}
@keyframes pulse {
  40% {
    transform: scale(1.18);
    color: #fff;
  }
}
@keyframes hintIn {
  from {
    opacity: 0;
    transform: translate(-50%, 10px);
  }
}
@keyframes sheetIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
}
@media (max-width: 600px) {
  h1 {
    font-size: 18px;
  }
  .kicker {
    display: none;
  }
  .roster {
    display: none;
  }
}
@media (prefers-reduced-motion: reduce) {
  .pulse,
  .hint,
  .sheet {
    animation: none;
  }
}
</style>
