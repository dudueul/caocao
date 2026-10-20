<script lang="ts">
import { chapterOf, type OfficerReport, PROGRESSION, type StatGains } from "@tk/campaign";
import { ITEMS, OFFICERS, P0_RULES, type Slot } from "@tk/content";
import { onMount } from "svelte";
import Portrait from "../../components/Portrait.svelte";
import { audio } from "../../lib/audio/index.ts";
import { story } from "../../lib/story.svelte.ts";
import { STATS } from "../../lib/text.ts";

/** After a battle: the spoils and each officer's experience, level-ups and promotion. */
const L = {
  won: "승리",
  lost: "패배",
  spoils: "전리품",
  gold: "군자금",
  officers: "장수 경험치",
  levelUp: "LEVEL UP!",
  promo: "승급 가능",
  mvp: "수훈",
  next: "계속",
  retry: "다시 도전",
  skip: "건너뛰기",
  lostText: "아군이 물러났다. 편성과 배치를 바꿔 다시 도전하자.",
  loseGoal: "패배 조건",
  noItems: "획득한 물품 없음",
} as const;

const SLOT_NAME: Record<Slot, string> = { weapon: "무기", armour: "갑옷", accessory: "장신구" };
const PER = PROGRESSION.expPerLevel;
/** Exp poured per millisecond, the pause on each level-up, and the gap between rows. */
const RATE = 0.16;
const PAUSE = 520;
const STAGGER = 160;
const GOLD_AT = 380;
const GOLD_MS = 900;
const ROWS_AT = 900;

const report = story.report;
const won = report?.won ?? false;
/** The battle just fought: after a win the campaign has already moved past it. */
const fought = (() => {
  for (const step of chapterOf(story.campaign).steps)
    if (step.kind === "battle" && step.battle.id === report?.battle) return step.battle;
  return story.battle;
})();
/** What the battle decided (ADR 0061), as the result screen says it. */
const fateNotes = (fought?.fates ?? []).flatMap((f) => {
  const v = report?.fates[f.flag];
  if (!v || !f.notes) return [];
  return [{ text: v === f.standing ? f.notes.standing : f.notes.fallen, good: v === f.standing }];
});

interface Segment {
  from: number;
  to: number;
  /** Index of the level-up at the end of this segment, if any. */
  up: number | null;
}

interface Row {
  r: OfficerReport;
  name: string;
  classId: string;
  className: string;
  promoName: string | null;
  start: number;
  segments: Segment[];
  end: number;
}

const clamp = (v: number, lo: number, hi: number) => Math.max(lo, Math.min(hi, v));

function buildRow(r: OfficerReport, i: number): Row {
  const o = story.campaign.roster.find((x) => x.id === r.id);
  const after = clamp(o?.exp ?? 0, 0, PER - 1);
  const before = clamp(after + PER * (r.level - r.levelBefore) - r.exp, 0, PER - 1);
  const segments: Segment[] =
    r.ups.length === 0
      ? [{ from: before, to: Math.max(before, after), up: null }]
      : [
          ...r.ups.map((_, k) => ({ from: k === 0 ? before : 0, to: PER, up: k })),
          { from: 0, to: after, up: null },
        ];
  const start = ROWS_AT + i * STAGGER;
  const length = segments.reduce(
    (t, s) => t + (s.to - s.from) / RATE + (s.up === null ? 0 : PAUSE),
    0,
  );
  const classId = o?.classId ?? "";
  return {
    r,
    name: OFFICERS[r.id]?.name ?? r.id,
    classId,
    className: P0_RULES.classes[classId]?.name ?? classId,
    promoName: r.promotion ? (P0_RULES.classes[r.promotion]?.name ?? r.promotion) : null,
    start,
    segments,
    end: start + length,
  };
}

const rows = won && report ? report.officers.map(buildRow) : [];
const mvp = rows.reduce<Row | null>((a, b) => (!a || b.r.exp > a.r.exp ? b : a), null);
const finish = Math.max(GOLD_AT + GOLD_MS, ...rows.map((r) => r.end));

const reduce =
  typeof matchMedia === "function" && matchMedia("(prefers-reduced-motion: reduce)").matches;
/** Milliseconds since the screen opened; drives every animation below. */
let t = $state(reduce ? finish : 0);
let skipped = $state(reduce);
const done = $derived(t >= finish);

/** Where a row's bar stands at time `t`: fill %, level, and how many level-ups are shown. */
function rowAt(row: Row, now: number): { pct: number; level: number; ups: number; full: boolean } {
  let left = now - row.start;
  let ups = 0;
  let pct = row.segments[0]?.from ?? 0;
  if (left <= 0) return { pct, level: row.r.levelBefore, ups, full: false };
  for (const s of row.segments) {
    const span = (s.to - s.from) / RATE;
    if (left < span) {
      pct = s.from + left * RATE;
      return { pct, level: row.r.levelBefore + ups, ups, full: false };
    }
    left -= span;
    pct = s.to;
    if (s.up !== null) {
      ups = s.up + 1;
      if (left < PAUSE) return { pct: PER, level: row.r.levelBefore + ups, ups, full: true };
      left -= PAUSE;
    }
  }
  return { pct, level: row.r.level, ups: row.r.ups.length, full: false };
}

const states = $derived(rows.map((row) => rowAt(row, t)));
const shownUps = $derived(states.reduce((n, s) => n + s.ups, 0));
const gold = $derived(report ? Math.round(report.gold * clamp((t - GOLD_AT) / GOLD_MS, 0, 1)) : 0);

let heard = 0;
$effect(() => {
  const n = shownUps;
  if (n > heard) {
    if (!skipped) audio.play("levelUp");
    heard = n;
  }
});

onMount(() => {
  audio.play(won ? "confirm" : "cancel");
  if (reduce) return;
  let raf = 0;
  let coin = false;
  const t0 = performance.now();
  const tick = (now: number) => {
    if (skipped) return;
    t = Math.min(finish, now - t0);
    if (!coin && won && t >= GOLD_AT && (report?.gold ?? 0) > 0) {
      coin = true;
      audio.play("coin");
    }
    if (t < finish) raf = requestAnimationFrame(tick);
  };
  raf = requestAnimationFrame(tick);
  return () => cancelAnimationFrame(raf);
});

function skip() {
  skipped = true;
  t = finish;
}

function next() {
  audio.play("confirm");
  story.afterResult();
}

function gainText(g: StatGains): string {
  return STATS.flatMap(([k, label]) => (g[k] ? [`${label} +${g[k]}`] : [])).join("  ");
}
</script>

{#snippet coinIcon()}
  <svg viewBox="0 0 24 24" class="coin" aria-hidden="true">
    <circle cx="12" cy="12" r="10" fill="url(#coin-g)" stroke="#7a5418" stroke-width="1.2" />
    <circle cx="12" cy="12" r="7.6" fill="none" stroke="#a87724" stroke-width="0.8" />
    <rect x="9" y="9" width="6" height="6" fill="#3a2508" stroke="#7a5418" stroke-width="0.8" />
    <defs>
      <radialGradient id="coin-g" cx="0.35" cy="0.3" r="0.8">
        <stop offset="0" stop-color="#fff0b8" />
        <stop offset="0.55" stop-color="#e8c26a" />
        <stop offset="1" stop-color="#a8792a" />
      </radialGradient>
    </defs>
  </svg>
{/snippet}

{#snippet slotIcon(
  slot: Slot,
  kind?: string,
)}
  <svg viewBox="0 0 24 24" class="sicon" aria-hidden="true">
    {#if slot === "weapon" && kind === "polearm"}
      <path d="M4 20L17 7" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
      <path d="M15.5 4.5L21 3l-1.5 5.5-2.5.5-1-1z" fill="currentColor" />
      <path d="M13 8l3 3" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
    {:else if slot === "weapon" && kind === "bow"}
      <path
        d="M8 3c7 2 10 6 10 9s-3 7-10 9"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
      />
      <path d="M8 3v18" stroke="currentColor" stroke-width="1.2" />
      <path d="M3 12h15M15 9.5l3 2.5-3 2.5" fill="none" stroke="currentColor" stroke-width="1.8" />
    {:else if slot === "weapon" && kind === "fan"}
      <path d="M12 21L3.5 9.5a12 12 0 0 1 17 0z" fill="currentColor" opacity="0.85" />
      <path
        d="M12 21L7 7.5M12 21V6.5M12 21l5-13.5"
        stroke="#1a140c"
        stroke-width="1.2"
        opacity="0.6"
      />
    {:else if slot === "weapon" && kind === "shot"}
      <circle cx="9" cy="14" r="5" fill="currentColor" />
      <circle cx="17" cy="8" r="3.5" fill="currentColor" opacity="0.7" />
    {:else if slot === "weapon"}
      <path d="M19 3l2 2-11 11-2-2z" fill="currentColor" />
      <path
        d="M6 13l5 5M4.5 17.5l2 2M7 16l-3.5 3.5"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
      />
    {:else if slot === "armour"}
      <path
        d="M12 3l7 3v5c0 5-3 8.5-7 10-4-1.5-7-5-7-10V6z"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linejoin="round"
      />
      <path d="M8 9h8M8 12.5h8M9 16h6" stroke="currentColor" stroke-width="1.6" />
    {:else}
      <path d="M12 3v4" stroke="currentColor" stroke-width="2" stroke-linecap="round" />
      <circle cx="12" cy="13" r="6" fill="none" stroke="currentColor" stroke-width="2" />
      <circle cx="12" cy="13" r="2" fill="currentColor" />
    {/if}
  </svg>
{/snippet}

{#if report}
  <main class="result" class:won class:lost={!won}>
    <div class="rays" aria-hidden="true"></div>
    <div class="scroll">
      <div class="wrap">
        <header>
          {#if fought}
            <div class="kicker">{fought.name} · {fought.place}</div>
          {/if}
          <h1 class="title">{won ? L.won : L.lost}</h1>
        </header>

        {#if won}
          <section class="spoils">
            <div class="goldline">
              <span class="label">{L.gold}</span>
              <span class="amount">{@render coinIcon()}<b>+{gold.toLocaleString("ko-KR")}</b></span>
            </div>
            <div class="items">
              {#each report.items as id, i (i)}
                {@const it = ITEMS[id]}
                {#if it}
                  <span class="item" style:--d="{GOLD_AT + GOLD_MS + i * 140}ms">
                    {@render slotIcon(it.slot, it.kind)}
                    <span class="iname">{it.name}</span>
                    <span class="islot">{SLOT_NAME[it.slot]}</span>
                  </span>
                {/if}
              {:else}
                <span class="muted">{L.noItems}</span>
              {/each}
            </div>
          </section>

          {#each fateNotes as note, i (i)}
            <p class="fate" class:good={note.good}>{note.text}</p>
          {/each}

          <section class="officers">
            <h2>{L.officers}</h2>
            {#each rows as row, i (row.r.id)}
              {@const s = states[i]}
              {#if s}
                <div class="row" class:leveled={s.ups > 0} style:--d="{row.start - 250}ms">
                  <span class="face">
                    <Portrait
                      id={row.r.id}
                      classId={row.classId}
                      side={0}
                      size={52}
                      mood={s.ups > 0 ? "smile" : "calm"}
                    />
                  </span>
                  <div class="mid">
                    <div class="line">
                      <strong>{row.name}</strong>
                      <span class="cls">{row.className}</span>
                      {#if mvp === row && rows.length > 1}
                        <span class="badge mvp">{L.mvp}</span>
                      {/if}
                      <span class="lv" class:up={s.ups > 0}>Lv {s.level}</span>
                    </div>
                    <div class="barline">
                      <div class="bar" class:full={s.full}>
                        <div class="fill" style:width="{s.pct}%"></div>
                      </div>
                      <span class="exp">+{row.r.exp} EXP</span>
                    </div>
                    {#each row.r.ups.slice(0, s.ups) as up (up.level)}
                      <div class="up">
                        <span class="lvup">{L.levelUp}</span>
                        <span class="to">Lv {up.level}</span>
                        <span class="gains">{gainText(up.gains)}</span>
                      </div>
                    {/each}
                    {#if row.promoName && t >= row.end}
                      <div class="promo">
                        <span class="badge promote">{L.promo}</span>
                        <span>{row.className} → <b>{row.promoName}</b></span>
                      </div>
                    {/if}
                  </div>
                </div>
              {/if}
            {/each}
          </section>
        {:else}
          <section class="lostcard">
            <div class="fallen">
              {#each story.sortie as p, i (p.officer)}
                {@const o = story.campaign.roster.find((x) => x.id === p.officer)}
                <span class="face small" style:--d="{300 + i * 90}ms">
                  <Portrait id={p.officer} classId={o?.classId} side={0} size={40} mood="hurt" />
                </span>
              {/each}
            </div>
            <p>{L.lostText}</p>
            {#if fought}
              <p class="muted">{L.loseGoal}: {fought.goalText.lose}</p>
            {/if}
          </section>
        {/if}
      </div>
    </div>

    <footer>
      {#if won && !done}
        <button type="button" class="skip" onclick={skip}>{L.skip}</button>
      {/if}
      <button type="button" class="primary next" onclick={next}>{won ? L.next : L.retry}</button>
    </footer>
  </main>
{/if}

<style>
.result {
  position: fixed;
  inset: 0;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background:
    radial-gradient(90% 50% at 50% 0%, rgba(232, 194, 106, 0.28), transparent 65%),
    linear-gradient(180deg, #1e1a11, #0d0c09);
}
.result.lost {
  background:
    radial-gradient(90% 50% at 50% 0%, rgba(160, 60, 52, 0.3), transparent 65%),
    linear-gradient(180deg, #171313, #0b0a09);
}
.rays {
  position: absolute;
  left: 50%;
  top: 90px;
  width: 1400px;
  height: 1400px;
  margin: -700px 0 0 -700px;
  pointer-events: none;
  opacity: 0.4;
  background: repeating-conic-gradient(
    from 0deg,
    rgba(255, 226, 150, 0.16) 0deg 6deg,
    transparent 6deg 18deg
  );
  mask-image: radial-gradient(circle, #000 0%, transparent 42%);
  -webkit-mask-image: radial-gradient(circle, #000 0%, transparent 42%);
  animation: spin 60s linear infinite;
}
.lost .rays {
  display: none;
}
.scroll {
  position: relative;
  flex: 1;
  min-height: 0;
  overflow-x: hidden;
  overflow-y: auto;
  padding: max(20px, env(safe-area-inset-top, 0px)) max(16px, env(safe-area-inset-right, 0px)) 20px
    max(16px, env(safe-area-inset-left, 0px));
}
.wrap {
  width: min(680px, 100%);
  margin: 0 auto;
  display: grid;
  gap: 16px;
}
header {
  display: grid;
  justify-items: center;
  gap: 2px;
  padding-top: 8px;
  text-align: center;
}
.kicker {
  font-size: 13px;
  letter-spacing: 0.14em;
  color: #f0dcaa;
  animation: fade 0.5s 0.15s both;
}
.title {
  margin: 0;
  font-family: var(--display);
  font-size: clamp(56px, 17vw, 88px);
  font-weight: 900;
  letter-spacing: 0.24em;
  text-indent: 0.24em;
  line-height: 1.1;
  background: linear-gradient(180deg, #fffbe6, #f2cf75 45%, #b07d28 80%, #e8c26a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 0 18px rgba(232, 194, 106, 0.55)) drop-shadow(0 3px 0 #000);
  animation: stamp 0.6s cubic-bezier(0.2, 1.4, 0.4, 1) both;
}
.lost .title {
  background: linear-gradient(180deg, #f3e7e2, #c98a80 50%, #7a3a33);
  -webkit-background-clip: text;
  background-clip: text;
  filter: drop-shadow(0 0 14px rgba(224, 100, 90, 0.35)) drop-shadow(0 3px 0 #000);
}
h2 {
  margin: 0 0 2px;
  font-size: 13px;
  font-weight: 700;
  letter-spacing: 0.12em;
  color: var(--muted);
}
.spoils {
  display: grid;
  gap: 10px;
  padding: 14px;
  border-radius: 16px;
  background: var(--panel);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid var(--gold-line);
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.4);
  animation: rise 0.45s 0.3s both;
}
.goldline {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.label {
  font-size: 13px;
  letter-spacing: 0.12em;
  color: var(--muted);
}
.amount {
  display: inline-flex;
  align-items: center;
  gap: 8px;
}
.amount b {
  font-size: 28px;
  font-weight: 900;
  font-variant-numeric: tabular-nums;
  color: #ffe7a3;
  text-shadow: 0 0 12px rgba(232, 194, 106, 0.5);
}
.coin {
  width: 26px;
  height: 26px;
  filter: drop-shadow(0 1px 2px rgba(0, 0, 0, 0.6));
}
.items {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}
.item {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 6px 10px 6px 8px;
  border-radius: 10px;
  background: linear-gradient(180deg, rgba(232, 194, 106, 0.16), rgba(232, 194, 106, 0.05));
  border: 1px solid rgba(232, 194, 106, 0.4);
  animation: pop 0.4s var(--d) both;
}
.sicon {
  width: 18px;
  height: 18px;
  color: var(--accent);
}
.iname {
  font-weight: 700;
}
.islot {
  font-size: 11px;
  color: var(--muted);
}
.officers {
  display: grid;
  gap: 8px;
}
.row {
  display: flex;
  gap: 10px;
  align-items: flex-start;
  padding: 10px 12px 10px 10px;
  border-radius: 14px;
  background: linear-gradient(160deg, rgba(52, 47, 37, 0.9), rgba(22, 20, 16, 0.92));
  border: 1px solid var(--line);
  animation: slide 0.4s var(--d) both;
  transition:
    border-color 0.3s,
    box-shadow 0.3s;
}
.row.leveled {
  border-color: rgba(232, 194, 106, 0.6);
  box-shadow: 0 0 18px rgba(232, 194, 106, 0.14);
}
.face {
  flex: none;
  width: 52px;
  height: 65px;
  border-radius: 10px;
  overflow: hidden;
  background: #0006;
  box-shadow:
    0 0 0 1px rgba(232, 194, 106, 0.45),
    0 2px 6px rgba(0, 0, 0, 0.5);
}
.face :global(img),
.face :global(canvas) {
  display: block;
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 0;
}
.mid {
  flex: 1;
  min-width: 0;
  display: grid;
  gap: 5px;
}
.line {
  display: flex;
  align-items: baseline;
  gap: 6px;
  flex-wrap: wrap;
}
.line strong {
  font-family: var(--display);
  font-size: 17px;
}
.cls {
  font-size: 12px;
  color: var(--muted);
}
.lv {
  margin-left: auto;
  font-weight: 800;
  font-variant-numeric: tabular-nums;
}
.lv.up {
  color: #ffe08a;
  text-shadow: 0 0 10px rgba(255, 210, 63, 0.7);
}
.barline {
  display: flex;
  align-items: center;
  gap: 8px;
}
.bar {
  flex: 1;
  height: 9px;
  border-radius: 5px;
  background: rgba(0, 0, 0, 0.55);
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.6);
  overflow: hidden;
}
.fill {
  height: 100%;
  border-radius: 5px;
  background: linear-gradient(90deg, #3d78e0, #7fc2ff);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.45);
}
.bar.full .fill {
  background: linear-gradient(90deg, #f6dc94, #fff6d0);
  box-shadow: 0 0 10px rgba(255, 230, 150, 0.9);
}
.exp {
  flex: none;
  min-width: 70px;
  text-align: right;
  font-size: 12px;
  font-weight: 800;
  color: #9fd0ff;
  font-variant-numeric: tabular-nums;
}
.up {
  display: flex;
  flex-wrap: wrap;
  align-items: baseline;
  gap: 2px 8px;
  animation: pop 0.45s cubic-bezier(0.2, 1.5, 0.4, 1) both;
}
.lvup {
  font-size: 15px;
  font-weight: 900;
  font-style: italic;
  letter-spacing: 0.04em;
  background: linear-gradient(180deg, #fff6c8, #ffd23f 60%, #e08a1f);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 0 6px rgba(255, 210, 63, 0.6));
}
.to {
  font-size: 12px;
  font-weight: 700;
  color: #ffe08a;
}
.gains {
  font-size: 12px;
  color: var(--good);
  white-space: pre-wrap;
}
.promo {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 6px;
  font-size: 12px;
  animation: pop 0.45s both;
}
.badge {
  padding: 1px 8px;
  border-radius: 999px;
  font-size: 11px;
  font-weight: 800;
}
.badge.promote {
  color: #2a1c06;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
  box-shadow: 0 0 10px rgba(232, 194, 106, 0.5);
}
.badge.mvp {
  color: #ffd9a8;
  border: 1px solid rgba(255, 159, 67, 0.7);
  background: rgba(255, 159, 67, 0.14);
}
.lostcard {
  display: grid;
  gap: 6px;
  padding: 16px;
  text-align: center;
  border-radius: 16px;
  background: var(--panel);
  border: 1px solid rgba(224, 100, 90, 0.35);
  animation: rise 0.45s 0.3s both;
}
.lostcard p {
  margin: 0;
  line-height: 1.6;
}
.lost .wrap {
  min-height: 100%;
  align-content: center;
  padding-bottom: 40px;
}
.fallen {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 6px;
  margin-bottom: 6px;
}
.face.small {
  width: 40px;
  height: 50px;
  border-radius: 8px;
  filter: grayscale(0.7) brightness(0.8);
  box-shadow:
    0 0 0 1px rgba(224, 100, 90, 0.45),
    0 2px 6px rgba(0, 0, 0, 0.5);
  animation: fade 0.5s var(--d) both;
}
footer {
  position: relative;
  flex: none;
  display: flex;
  justify-content: center;
  gap: 10px;
  padding: 12px max(16px, env(safe-area-inset-right, 0px))
    max(12px, env(safe-area-inset-bottom, 0px)) calc(64px + env(safe-area-inset-left, 0px));
  background: linear-gradient(180deg, rgba(14, 12, 9, 0.6), rgba(14, 12, 9, 0.95));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-top: 1px solid var(--gold-line);
}
footer button {
  min-height: 50px;
  border-radius: 14px;
}
.next {
  flex: 1;
  max-width: 360px;
  font-family: var(--display);
  font-size: 20px;
  letter-spacing: 0.2em;
  text-indent: 0.2em;
}
.skip {
  flex: none;
  min-width: 96px;
}
@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
@keyframes stamp {
  from {
    opacity: 0;
    transform: scale(1.8);
  }
}
@keyframes fade {
  from {
    opacity: 0;
  }
}
@keyframes rise {
  from {
    opacity: 0;
    transform: translateY(14px);
  }
}
@keyframes slide {
  from {
    opacity: 0;
    transform: translateX(-18px);
  }
}
@keyframes pop {
  from {
    opacity: 0;
    transform: scale(0.6);
  }
}
@media (prefers-reduced-motion: reduce) {
  .rays,
  .kicker,
  .title,
  .spoils,
  .item,
  .row,
  .up,
  .promo,
  .lostcard,
  .face.small {
    animation: none;
  }
}
.fate {
  margin: 0 0 14px;
  padding: 10px 14px;
  border-left: 3px solid #b0453a;
  background: rgba(30, 14, 12, 0.6);
  font-family: var(--display);
  letter-spacing: 0.04em;
  color: #f0d8c8;
  animation: fate-in 0.9s ease-out both;
  animation-delay: 1.4s;
}
.fate.good {
  border-left-color: #d8b25a;
  color: #fff0c8;
}
@keyframes fate-in {
  from {
    opacity: 0;
    transform: translateY(6px);
  }
}
@media (prefers-reduced-motion: reduce) {
  .fate {
    animation: none;
  }
}
</style>
