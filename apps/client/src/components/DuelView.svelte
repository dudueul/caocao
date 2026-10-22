<script lang="ts">
import { STANCES, type Stance } from "@tk/rules";
import { onMount } from "svelte";
import type { Duelist, DuelShow } from "../lib/duel.svelte.ts";
import { OFFICERS as LOOKS } from "../lib/fx/portrait/officers.ts";
import { portraitUrl } from "../lib/fx/portrait.ts";
import { classic } from "../lib/skin.svelte.ts";
import { clashWord, josa, STANCE, T } from "../lib/text.ts";
import Portrait from "./Portrait.svelte";

/**
 * The 일기토 screen (ADR 0071): the two officers face each other over the
 * battle, our man on the left. Each bout both stances are revealed, the two
 * close, and the blows land. The player picks 공격, 강공 or 방어 (keys 1 2 3)
 * or withdraws (Esc) when the duel is his own; a watched duel plays by itself
 * and can be skipped. It ends on a banner: 승리, 패배, 무승부 or 물러남.
 */
let {
  show,
  canPick = false,
  onpick,
  onwithdraw,
  auto = false,
  onauto,
}: {
  show: DuelShow;
  /** The player may pick a stance now. */
  canPick?: boolean;
  onpick?: (s: Stance) => void;
  /** Present when the duel may be left (not a story duel). */
  onwithdraw?: (() => void) | undefined;
  auto?: boolean;
  /** Present when 자동 전투 can be toggled from here. */
  onauto?: ((on: boolean) => void) | undefined;
} = $props();

const SIDE = ["var(--ally)", "var(--enemy)", "var(--friend)", "#c9a24a"];
const sideColor = (side: number) => SIDE[side] ?? "var(--accent)";

const L = $derived(show.left);
const R = $derived(show.right);
const sL = $derived(show.stanceOf(L));
const sR = $derived(show.stanceOf(R));
const phase = $derived(show.phase);
const shown = $derived(phase === "reveal" || phase === "clash" || phase === "hit");
const hitL = $derived(show.blows.some((b) => b.on === L.id));
const hitR = $derived(show.blows.some((b) => b.on === R.id));
/** Whose card won the bout: the one who dealt the only blow (story duels bend the stances). */
const edge = $derived(hitR && !hitL ? "left" : hitL && !hitR ? "right" : "even");
const mine = $derived(!show.watched);
const ready = $derived(canPick && phase === "pick" && !show.picked);
const heavyHit = $derived(show.blows.some((b) => b.heavy));

function flip(d: Duelist, place: "left" | "right"): boolean {
  if (classic.skin?.faceUrl(d.id)) return false;
  const turn = LOOKS[d.id]?.turn ?? "R3";
  return place === "left" ? turn === "L3" : turn === "R3" || turn === "P";
}

function blowOn(d: Duelist) {
  return phase === "hit" || phase === "pick" || phase === "rest"
    ? (show.blows.find((b) => b.on === d.id) ?? null)
    : null;
}

const pct = (d: Duelist) => Math.max(0, Math.min(100, (d.hp * 100) / Math.max(1, d.maxHp)));

function pick(s: Stance) {
  if (ready) onpick?.(s);
}

function onKey(e: KeyboardEvent) {
  if (e.target instanceof HTMLInputElement || e.target instanceof HTMLSelectElement) return;
  if (phase === "end") {
    if (e.key === "Enter" || e.key === " " || e.key === "Escape") show.dismiss();
    else return;
  } else if (e.key === "1" || e.key === "2" || e.key === "3") {
    const s = STANCES[Number(e.key) - 1];
    if (s) pick(s);
  } else if (e.key === "Escape") {
    if (onwithdraw && ready) onwithdraw();
  } else return;
  e.preventDefault();
  e.stopPropagation();
}

// Paint every face the fight may call for ahead of time, a few at a time.
onMount(() => {
  const timers: ReturnType<typeof setTimeout>[] = [];
  const who = [show.a, show.b];
  let k = 0;
  for (const mood of ["hurt", "grim", "smile", "angry"] as const)
    for (const d of who)
      timers.push(
        setTimeout(
          () => portraitUrl({ id: d.id, classId: d.classId, side: d.side, mood }),
          120 + 60 * k++,
        ),
      );
  return () => {
    for (const t of timers) clearTimeout(t);
  };
});
</script>

<svelte:window onkeydown={onKey} />

<div
  class="duel phase-{phase}"
  class:closing={show.closing}
  class:story={show.story}
  class:skipping={show.skipping}
  style:--pace={show.tempo}
  style:--left={sideColor(L.side)}
  style:--right={sideColor(R.side)}
  role="dialog"
  aria-modal="true"
  aria-label={T.duel}
  tabindex="-1"
>
  <div class="half left" aria-hidden="true"></div>
  <div class="half right" aria-hidden="true"></div>
  <div class="slash" aria-hidden="true"></div>
  {#key show.beat}
    {#if show.beat > 0 && phase === "hit"}
      <div class="flash" class:heavy={heavyHit} aria-hidden="true"></div>
    {/if}
  {/key}

  <header>
    <div class="tools">
      {#if onauto}
        <button type="button" class:on={auto} aria-pressed={auto} onclick={() => onauto?.(!auto)}>
          {T.auto}
        </button>
      {/if}
      {#if (show.watched || auto) && phase !== "end"}
        <button type="button" onclick={() => show.skip()}>건너뛰기</button>
      {/if}
    </div>
    <div class="hanja" aria-hidden="true">一 騎 討</div>
    <h2 class="title">{T.duel}</h2>
    {#if show.story}
      <div class="ribbon">★ {T.storyDuel}</div>
    {/if}
    <div class="bouts">
      {#each Array.from({ length: show.bouts }, (_, k) => k) as k (k)}
        <i class:done={k < show.n} class:now={k + 1 === show.current && phase !== "end"}></i>
      {/each}
      <span>{show.current} / {show.bouts}</span>
    </div>
  </header>

  <div class="arena">
    {#each [
      { d: L, place: "left" as const, s: sL },
      { d: R, place: "right" as const, s: sR },
    ] as f (f.place)}
      {@const blow = blowOn(f.d)}
      <div
        class="fighter {f.place}"
        class:struck={phase === "hit" && blow !== null}
        class:won={show.ending?.winner === f.d.id}
        class:lost={show.ending?.loser === f.d.id}
        style:--side={sideColor(f.d.side)}
      >
        {#key f.d.id}
          <div class="frame" class:flip={flip(f.d, f.place)}>
            <Portrait
              id={f.d.id}
              classId={f.d.classId}
              side={f.d.side}
              size={256}
              mood={show.mood(f.d)}
            />
          </div>
        {/key}
        {#key show.beat}
          {#if blow}
            <div class="dmg" class:heavy={blow.heavy}>-{blow.amount}</div>
          {/if}
        {/key}
        <!-- A new challenger gets his own plate: his bar does not slide from the last man's. -->
        {#key f.d.id}
          <div class="plate">
            <div class="name">
              {f.d.name}
              {#if f.d.hanja}
                <span class="hj">{f.d.hanja}</span>
              {/if}
            </div>
            <div class="stat">{T.martial} <b>{f.d.martial}</b></div>
            <div class="hp" class:low={pct(f.d) < 35}>
              <div class="ghost" style:width="{pct(f.d)}%"></div>
              <div class="bar" style:width="{pct(f.d)}%"></div>
              <span>{f.d.hp} / {f.d.maxHp}</span>
            </div>
          </div>
        {/key}
      </div>
    {/each}

    <div class="mid">
      {#if shown && sL && sR}
        <div class="cards">
          {#each [
            { s: sL, place: "left" },
            { s: sR, place: "right" },
          ] as c (c.place)}
            <div
              class="card {c.place} {c.s}"
              class:win={phase === "hit" && edge === c.place}
              class:lose={phase === "hit" && edge !== "even" && edge !== c.place}
            >
              <span class="glyph">{STANCE[c.s].glyph}</span>
              <span class="cname">{STANCE[c.s].name}</span>
            </div>
          {/each}
        </div>
        <div class="word" class:on={phase === "hit"}>{clashWord(sL, sR, hitL, hitR)}</div>
      {:else if phase !== "end"}
        <div class="vs" aria-hidden="true">對</div>
        {#if mine && phase === "pick"}
          <div class="prompt">{canPick ? T.pickStance : "…"}</div>
        {:else if show.incoming}
          <div class="word on">{josa(show.incoming, "이", "가")} 나선다!</div>
        {/if}
      {/if}
      {#key show.beat}
        {#if phase === "hit"}
          <div class="burst" class:heavy={heavyHit} aria-hidden="true"></div>
        {/if}
      {/key}
    </div>
  </div>

  <footer>
    {#if mine}
      <div class="stances">
        {#each STANCES as s, k (s)}
          <button
            type="button"
            class="stance {s}"
            class:picked={show.picked === s}
            disabled={!ready}
            onclick={() => pick(s)}
          >
            <span class="key">{k + 1}</span>
            <span class="glyph">{STANCE[s].glyph}</span>
            <span class="sname">{STANCE[s].name}</span>
            <span class="hint">{STANCE[s].hint}</span>
          </button>
        {/each}
      </div>
      {#if onwithdraw}
        <button type="button" class="withdraw" disabled={!ready} onclick={() => onwithdraw?.()}>
          {T.withdraw} <span class="esc">Esc</span>
        </button>
      {/if}
    {:else}
      <div class="watching">{T.triangle}</div>
    {/if}
  </footer>

  {#if show.ending}
    {@const end = show.ending}
    <!-- The whole banner is the way on: a tap anywhere on it, Enter or Esc. -->
    <button type="button" class="ending {end.tone}" onclick={() => show.dismiss()}>
      <span class="big">{end.title}</span>
      <span class="line">{end.line}</span>
      {#if end.fallen}
        <span class="seal" title="{end.fallen} {T.fallen}">{T.fallen}</span>
      {/if}
      {#if mine}
        <span class="go">{T.continue}</span>
      {/if}
    </button>
  {/if}
</div>

<style>
.duel {
  --pw: clamp(96px, min(30vw, 37vh), 290px);
  --t: calc(var(--pace, 1) * 1ms);
  position: fixed;
  inset: 0;
  z-index: 12;
  display: grid;
  grid-template-rows: auto 1fr auto;
  gap: 8px;
  padding: max(10px, env(safe-area-inset-top, 0px)) max(14px, env(safe-area-inset-right, 0px))
    max(12px, env(safe-area-inset-bottom, 0px)) max(14px, env(safe-area-inset-left, 0px));
  overflow: hidden;
  background: radial-gradient(ellipse at 50% 46%, rgba(48, 34, 14, 0.62), rgba(8, 6, 4, 0.9) 72%);
  backdrop-filter: blur(5px) saturate(0.8);
  -webkit-backdrop-filter: blur(5px) saturate(0.8);
  animation: fade-in calc(260 * var(--t)) ease-out both;
  user-select: none;
  outline: none;
}
.duel.closing {
  animation: fade-out calc(300 * var(--t)) ease-in forwards;
}
.half {
  position: absolute;
  inset: 0;
  pointer-events: none;
}
.half.left {
  clip-path: polygon(0 0, 56% 0, 44% 100%, 0 100%);
  background: linear-gradient(
    100deg,
    color-mix(in srgb, var(--left) 34%, transparent),
    transparent 62%
  );
  animation: slide-left calc(520 * var(--t)) cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.half.right {
  clip-path: polygon(56% 0, 100% 0, 100% 100%, 44% 100%);
  background: linear-gradient(
    280deg,
    color-mix(in srgb, var(--right) 34%, transparent),
    transparent 62%
  );
  animation: slide-right calc(520 * var(--t)) cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.slash {
  position: absolute;
  inset: 0;
  pointer-events: none;
  clip-path: polygon(55.7% 0, 56.3% 0, 44.3% 100%, 43.7% 100%);
  background: linear-gradient(
    180deg,
    transparent,
    #f6dc94 30%,
    #fff6dc 50%,
    #f6dc94 70%,
    transparent
  );
  opacity: 0.55;
  animation: slash-in calc(700 * var(--t)) ease-out both;
}
.flash {
  position: absolute;
  inset: 0;
  pointer-events: none;
  background: radial-gradient(circle at 50% 48%, rgba(255, 250, 230, 0.55), transparent 60%);
  animation: flash calc(320 * var(--t)) ease-out forwards;
}
.flash.heavy {
  background: radial-gradient(
    circle at 50% 48%,
    rgba(255, 236, 190, 0.85),
    rgba(255, 120, 60, 0.2) 70%
  );
}

header {
  position: relative;
  display: grid;
  justify-items: center;
  gap: 2px;
  z-index: 1;
}
.tools {
  position: absolute;
  top: 0;
  right: 0;
  display: flex;
  gap: 6px;
}
.tools button {
  font-size: 12px;
  padding: 4px 10px;
}
.hanja {
  font-family: var(--display);
  font-size: 12px;
  letter-spacing: 0.6em;
  margin-right: -0.6em;
  color: rgba(242, 213, 138, 0.7);
}
.title {
  margin: 0;
  font-family: var(--display);
  font-size: clamp(28px, 5vw, 46px);
  font-weight: 900;
  line-height: 1.05;
  letter-spacing: 0.32em;
  margin-right: -0.32em;
  background: linear-gradient(180deg, #fff3c4, #e8c26a 58%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 2px 8px rgba(0, 0, 0, 0.8));
  animation: stamp calc(560 * var(--t)) cubic-bezier(0.2, 1.4, 0.4, 1) calc(380 * var(--t)) both;
}
.ribbon {
  padding: 1px 14px;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.18em;
  color: #2a1a06;
  background: linear-gradient(
    90deg,
    transparent,
    #e8c26a 18%,
    #f6dc94 50%,
    #e8c26a 82%,
    transparent
  );
  animation: fade-in calc(400 * var(--t)) ease-out calc(700 * var(--t)) both;
}
.bouts {
  display: flex;
  align-items: center;
  gap: 6px;
  margin-top: 4px;
  font-size: 12px;
  font-variant-numeric: tabular-nums;
  color: var(--muted);
}
.bouts i {
  width: 9px;
  height: 9px;
  transform: rotate(45deg);
  border: 1px solid rgba(232, 194, 106, 0.6);
  background: rgba(0, 0, 0, 0.4);
}
.bouts i.done {
  background: linear-gradient(135deg, #f6dc94, #b4852f);
}
.bouts i.now {
  box-shadow: 0 0 8px rgba(255, 220, 140, 0.9);
  animation: pulse 1.1s ease-in-out infinite;
}
.bouts span {
  margin-left: 4px;
  color: #f2d58a;
  font-weight: 700;
}

.arena {
  position: relative;
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  grid-template-areas: "left mid right";
  align-items: center;
  justify-items: center;
  gap: 8px;
  min-height: 0;
  z-index: 1;
}
.fighter {
  position: relative;
  display: grid;
  justify-items: center;
  gap: 6px;
  width: var(--pw);
  transition: transform calc(180 * var(--t)) cubic-bezier(0.3, 0, 0.2, 1);
}
.fighter.left {
  grid-area: left;
  justify-self: end;
  animation: enter-left calc(560 * var(--t)) cubic-bezier(0.2, 0.9, 0.25, 1) calc(80 * var(--t))
    both;
}
.fighter.right {
  grid-area: right;
  justify-self: start;
  animation: enter-right calc(560 * var(--t)) cubic-bezier(0.2, 0.9, 0.25, 1) calc(80 * var(--t))
    both;
}
.phase-clash .fighter.left {
  transform: translateX(16%) scale(1.04);
}
.phase-clash .fighter.right {
  transform: translateX(-16%) scale(1.04);
}
.frame {
  position: relative;
  width: var(--pw);
  aspect-ratio: 4 / 5;
  border-radius: 12px;
  overflow: hidden;
  border: 2px solid color-mix(in srgb, var(--side) 75%, #f2d58a);
  box-shadow:
    inset 0 0 0 1px rgba(255, 240, 200, 0.35),
    0 0 26px color-mix(in srgb, var(--side) 45%, transparent),
    0 14px 30px rgba(0, 0, 0, 0.65);
  background: #000;
  animation: fade-in calc(360 * var(--t)) ease-out both;
}
.frame :global(img.face) {
  display: block;
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 0;
  object-fit: cover;
}
.frame.flip :global(img.face) {
  transform: scaleX(-1);
}
.struck .frame {
  animation: struck calc(420 * var(--t)) ease-out;
}
.struck .frame::after {
  content: "";
  position: absolute;
  inset: 0;
  background: rgba(255, 60, 40, 0.38);
  mix-blend-mode: screen;
  animation: fade-out calc(420 * var(--t)) ease-out forwards;
}
.won .frame {
  box-shadow:
    inset 0 0 0 1px rgba(255, 240, 200, 0.5),
    0 0 40px rgba(255, 214, 120, 0.7),
    0 14px 30px rgba(0, 0, 0, 0.65);
}
.lost .frame {
  filter: grayscale(0.75) brightness(0.65);
  transition: filter calc(500 * var(--t));
}
.phase-swap .fighter.left .frame,
.phase-swap .fighter.right .frame {
  opacity: 0.25;
  transition: opacity calc(200 * var(--t));
}
.dmg {
  position: absolute;
  top: 28%;
  left: 50%;
  z-index: 2;
  font-size: calc(var(--pw) * 0.24);
  font-weight: 900;
  font-variant-numeric: tabular-nums;
  color: #fff;
  -webkit-text-stroke: 1.5px #2a0d06;
  text-shadow:
    0 0 14px rgba(255, 80, 40, 0.8),
    0 3px 0 #000;
  pointer-events: none;
  animation: dmg calc(1100 * var(--t)) cubic-bezier(0.2, 1.3, 0.4, 1) forwards;
}
.dmg.heavy {
  color: #ffd23f;
  font-size: calc(var(--pw) * 0.3);
  text-shadow:
    0 0 18px rgba(255, 170, 40, 0.95),
    0 3px 0 #000;
}
.plate {
  display: grid;
  justify-items: center;
  gap: 2px;
  width: 100%;
}
.name {
  font-family: var(--display);
  font-size: clamp(15px, 2.2vw, 20px);
  font-weight: 800;
  letter-spacing: 0.08em;
  color: color-mix(in srgb, var(--side) 40%, #fff);
  text-shadow:
    0 0 10px color-mix(in srgb, var(--side) 85%, transparent),
    0 2px 0 #000;
  white-space: nowrap;
}
.name::before {
  content: "";
  display: inline-block;
  width: 8px;
  height: 8px;
  margin-right: 6px;
  vertical-align: 2px;
  border-radius: 2px;
  transform: rotate(45deg);
  background: var(--side);
  box-shadow: 0 0 6px var(--side);
}
.hj {
  margin-left: 6px;
  font-size: 0.7em;
  font-weight: 600;
  color: rgba(242, 213, 138, 0.8);
}
.stat {
  font-size: 12px;
  color: var(--muted);
}
.stat b {
  color: #f2d58a;
  font-variant-numeric: tabular-nums;
}
.hp {
  position: relative;
  width: 100%;
  height: 14px;
  border-radius: 7px;
  overflow: hidden;
  background: rgba(0, 0, 0, 0.6);
  border: 1px solid rgba(232, 194, 106, 0.45);
}
.hp .ghost,
.hp .bar {
  position: absolute;
  top: 0;
  bottom: 0;
}
.hp .ghost {
  background: rgba(255, 240, 220, 0.75);
  transition: width calc(520 * var(--t)) ease-in calc(420 * var(--t));
}
.hp .bar {
  background: linear-gradient(180deg, #9ff09a, #3fae4a);
  transition: width calc(200 * var(--t)) ease-out;
}
.hp.low .bar {
  background: linear-gradient(180deg, #ffb08a, #d4462e);
}
.left .hp .ghost,
.left .hp .bar {
  right: 0;
}
.right .hp .ghost,
.right .hp .bar {
  left: 0;
}
.hp span {
  position: relative;
  display: block;
  font-size: 10.5px;
  line-height: 12px;
  font-weight: 700;
  text-align: center;
  font-variant-numeric: tabular-nums;
  text-shadow: 0 1px 2px #000;
}

.mid {
  grid-area: mid;
  position: relative;
  display: grid;
  justify-items: center;
  align-content: center;
  gap: 8px;
  min-width: clamp(120px, 22vw, 260px);
  min-height: 120px;
}
.vs {
  font-family: var(--display);
  font-size: clamp(34px, 6vw, 60px);
  font-weight: 900;
  line-height: 1;
  color: #f2d58a;
  text-shadow:
    0 0 18px rgba(232, 194, 106, 0.7),
    0 3px 0 #000;
  animation: stamp calc(500 * var(--t)) cubic-bezier(0.2, 1.4, 0.4, 1) both;
}
.prompt {
  font-size: 13px;
  font-weight: 700;
  letter-spacing: 0.1em;
  color: #fff3c4;
  text-shadow: 0 1px 4px #000;
  animation: pulse 1.6s ease-in-out infinite;
}
.cards {
  display: flex;
  gap: clamp(8px, 2vw, 20px);
  perspective: 600px;
}
.card {
  --c: #f0e2c0;
  display: grid;
  justify-items: center;
  align-content: center;
  width: clamp(54px, 9vw, 92px);
  aspect-ratio: 3 / 4;
  border-radius: 10px;
  border: 2px solid var(--c);
  background: radial-gradient(
    circle at 50% 35%,
    color-mix(in srgb, var(--c) 35%, #1a120a),
    #120c07 75%
  );
  box-shadow:
    0 0 16px color-mix(in srgb, var(--c) 45%, transparent),
    0 8px 18px rgba(0, 0, 0, 0.6);
  transition:
    transform calc(220 * var(--t)),
    filter calc(220 * var(--t)),
    opacity calc(220 * var(--t));
}
.card.left {
  animation: card-left calc(420 * var(--t)) cubic-bezier(0.2, 0.9, 0.3, 1.2) both;
}
.card.right {
  animation: card-right calc(420 * var(--t)) cubic-bezier(0.2, 0.9, 0.3, 1.2) calc(120 * var(--t))
    both;
}
.card.power {
  --c: #ff7048;
}
.card.guard {
  --c: #62c6f0;
}
.card.win {
  transform: scale(1.14);
  box-shadow:
    0 0 28px color-mix(in srgb, var(--c) 80%, transparent),
    0 8px 18px rgba(0, 0, 0, 0.6);
}
.card.lose {
  transform: scale(0.88) rotate(-4deg);
  filter: grayscale(0.7) brightness(0.6);
}
.card .glyph {
  font-family: var(--display);
  font-size: clamp(26px, 4.4vw, 44px);
  font-weight: 900;
  line-height: 1.1;
  color: var(--c);
  text-shadow: 0 0 12px color-mix(in srgb, var(--c) 70%, transparent);
}
.cname {
  font-size: clamp(11px, 1.4vw, 14px);
  font-weight: 800;
  letter-spacing: 0.1em;
  color: #fff;
}
.word {
  min-height: 1.4em;
  font-family: var(--display);
  font-size: clamp(13px, 1.8vw, 17px);
  font-weight: 800;
  letter-spacing: 0.06em;
  color: #fff6dc;
  text-align: center;
  text-shadow: 0 2px 6px #000;
  opacity: 0;
  transition: opacity calc(160 * var(--t));
}
.word.on {
  opacity: 1;
}
.burst {
  position: absolute;
  left: 50%;
  top: 42%;
  width: 40px;
  height: 40px;
  margin: -20px 0 0 -20px;
  border-radius: 50%;
  pointer-events: none;
  background:
    radial-gradient(circle, #fff 0 18%, transparent 19%),
    conic-gradient(
      from 10deg,
      transparent 0 8%,
      #ffe7a0 8% 10%,
      transparent 10% 20%,
      #fff 20% 21%,
      transparent 21% 33%,
      #ffe7a0 33% 35%,
      transparent 35% 47%,
      #fff 47% 48%,
      transparent 48% 60%,
      #ffe7a0 60% 62%,
      transparent 62% 74%,
      #fff 74% 75%,
      transparent 75% 87%,
      #ffe7a0 87% 89%,
      transparent 89%
    );
  animation: burst calc(460 * var(--t)) ease-out forwards;
}
.burst.heavy {
  filter: drop-shadow(0 0 10px #ff9a3a);
}

footer {
  position: relative;
  display: grid;
  justify-items: center;
  gap: 8px;
  z-index: 1;
  transition: opacity calc(300 * var(--t));
}
.phase-end footer {
  opacity: 0;
  pointer-events: none;
}
.stances {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 8px;
  width: min(100%, 760px);
}
.stance {
  --c: #f0e2c0;
  position: relative;
  display: grid;
  grid-template-columns: auto 1fr;
  grid-template-areas: "glyph name" "glyph hint";
  align-items: center;
  gap: 0 10px;
  padding: 8px 12px;
  text-align: left;
  border-color: color-mix(in srgb, var(--c) 45%, transparent);
  background: linear-gradient(
    180deg,
    color-mix(in srgb, var(--c) 16%, rgba(40, 34, 26, 0.95)),
    rgba(22, 18, 13, 0.96)
  );
}
.stance.power {
  --c: #ff7048;
}
.stance.guard {
  --c: #62c6f0;
}
.stance:hover:not(:disabled),
.stance:focus-visible {
  border-color: var(--c);
  box-shadow:
    inset 0 1px 0 rgba(255, 255, 255, 0.12),
    0 0 16px color-mix(in srgb, var(--c) 45%, transparent);
}
.stance.picked {
  opacity: 1;
  border-color: var(--c);
  box-shadow: 0 0 22px color-mix(in srgb, var(--c) 70%, transparent);
}
.stance .glyph {
  grid-area: glyph;
  font-family: var(--display);
  font-size: 30px;
  font-weight: 900;
  color: var(--c);
  text-shadow: 0 0 10px color-mix(in srgb, var(--c) 60%, transparent);
}
.sname {
  grid-area: name;
  font-size: 16px;
  font-weight: 800;
  letter-spacing: 0.1em;
}
.hint {
  grid-area: hint;
  font-size: 11.5px;
  line-height: 1.3;
  color: var(--muted);
}
.key {
  position: absolute;
  top: 5px;
  right: 7px;
  min-width: 16px;
  padding: 0 4px;
  font-size: 10px;
  line-height: 15px;
  text-align: center;
  color: var(--muted);
  border: 1px solid var(--line);
  border-radius: 4px;
}
.withdraw {
  font-size: 13px;
  padding: 5px 16px;
}
.esc {
  margin-left: 6px;
  font-size: 10px;
  color: var(--muted);
}
.watching {
  padding: 4px 14px;
  font-size: 12px;
  letter-spacing: 0.08em;
  color: #f2d58a;
  background: rgba(232, 194, 106, 0.08);
  border: 1px solid rgba(232, 194, 106, 0.25);
  border-radius: 999px;
}

.ending {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  z-index: 3;
  display: grid;
  justify-items: center;
  gap: 6px;
  height: fit-content;
  margin: auto 0;
  padding: 18px 20px 20px;
  font: inherit;
  color: inherit;
  text-align: center;
  cursor: pointer;
  border: none;
  border-radius: 0;
  box-shadow: none;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(12, 10, 7, 0.9) 18%,
    rgba(12, 10, 7, 0.9) 82%,
    transparent
  );
  border-top: 1px solid rgba(232, 194, 106, 0.6);
  border-bottom: 1px solid rgba(232, 194, 106, 0.6);
  animation: banner calc(420 * var(--t)) cubic-bezier(0.2, 0.8, 0.2, 1) both;
}
.ending:hover:not(:disabled) {
  border-color: rgba(232, 194, 106, 0.6);
  box-shadow: none;
}
.ending:active:not(:disabled) {
  transform: none;
}
.ending .big {
  font-family: var(--display);
  font-size: clamp(38px, 7vw, 64px);
  font-weight: 900;
  line-height: 1.1;
  letter-spacing: 0.24em;
  margin-right: -0.24em;
  background: linear-gradient(180deg, #fff3c4, #e8c26a 60%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 2px 8px rgba(0, 0, 0, 0.7));
}
.ending.loss .big {
  background: linear-gradient(180deg, #e6ecf5, #8ea4c4 60%, #4d5f7c);
  -webkit-background-clip: text;
  background-clip: text;
}
.ending.even .big {
  background: linear-gradient(180deg, #f1ece1, #c9bfa8 60%, #8a806a);
  -webkit-background-clip: text;
  background-clip: text;
}
.ending .line {
  font-size: 15px;
  letter-spacing: 0.06em;
  color: #fff6dc;
  text-shadow: 0 2px 4px #000;
}
.ending .go {
  margin-top: 6px;
  padding: 6px 26px;
  font-weight: 800;
  color: #22180a;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.35);
  background: linear-gradient(180deg, #f6dc94, #d7a84e 55%, #b4852f);
  border: 1px solid #f8e3a8;
  border-radius: 9px;
  box-shadow:
    inset 0 1px 0 rgba(255, 255, 255, 0.55),
    0 2px 10px rgba(232, 194, 106, 0.25);
}
.seal {
  position: absolute;
  top: 50%;
  left: calc(50% + clamp(80px, 16vw, 160px));
  display: grid;
  place-items: center;
  width: 64px;
  height: 64px;
  margin-top: -40px;
  border: 3px solid #e04a32;
  border-radius: 8px;
  color: #ff6a4a;
  font-family: var(--display);
  font-size: 22px;
  font-weight: 900;
  letter-spacing: 0.04em;
  background: rgba(60, 10, 6, 0.55);
  box-shadow: 0 0 18px rgba(224, 74, 50, 0.6);
  animation: seal calc(380 * var(--t)) cubic-bezier(0.3, 1.6, 0.5, 1) calc(420 * var(--t)) both;
}

@keyframes fade-in {
  from {
    opacity: 0;
  }
}
@keyframes fade-out {
  to {
    opacity: 0;
  }
}
@keyframes slide-left {
  from {
    transform: translateX(-60%);
    opacity: 0;
  }
}
@keyframes slide-right {
  from {
    transform: translateX(60%);
    opacity: 0;
  }
}
@keyframes slash-in {
  0% {
    opacity: 0;
  }
  40% {
    opacity: 1;
  }
}
@keyframes stamp {
  from {
    opacity: 0;
    transform: scale(1.9);
    filter: blur(6px);
  }
}
@keyframes enter-left {
  from {
    opacity: 0;
    transform: translateX(-70%);
  }
}
@keyframes enter-right {
  from {
    opacity: 0;
    transform: translateX(70%);
  }
}
@keyframes card-left {
  from {
    opacity: 0;
    transform: translateX(-60px) rotateY(80deg);
  }
}
@keyframes card-right {
  from {
    opacity: 0;
    transform: translateX(60px) rotateY(-80deg);
  }
}
@keyframes struck {
  0%,
  100% {
    transform: translateX(0);
  }
  15% {
    transform: translateX(-7px) rotate(-1.5deg);
  }
  35% {
    transform: translateX(6px) rotate(1deg);
  }
  55% {
    transform: translateX(-4px);
  }
  75% {
    transform: translateX(2px);
  }
}
@keyframes dmg {
  0% {
    opacity: 0;
    transform: translate(-50%, 10px) scale(1.8);
  }
  15% {
    opacity: 1;
    transform: translate(-50%, 0) scale(1);
  }
  70% {
    opacity: 1;
    transform: translate(-50%, -26px) scale(1);
  }
  100% {
    opacity: 0;
    transform: translate(-50%, -40px) scale(0.95);
  }
}
@keyframes burst {
  from {
    opacity: 1;
    transform: scale(0.3) rotate(0deg);
  }
  to {
    opacity: 0;
    transform: scale(4.2) rotate(30deg);
  }
}
@keyframes flash {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@keyframes pulse {
  50% {
    opacity: 0.45;
  }
}
@keyframes banner {
  from {
    opacity: 0;
    transform: scaleX(0.6);
  }
}
@keyframes seal {
  from {
    opacity: 0;
    transform: scale(2.4) rotate(-24deg);
  }
  to {
    transform: rotate(-12deg);
  }
}

/* Phones held upright: the two stand side by side; the cards come below them. */
@media (max-width: 560px) {
  .duel {
    --pw: clamp(96px, min(42vw, 30vh), 210px);
  }
  .arena {
    grid-template-columns: 1fr 1fr;
    grid-template-areas: "left right" "mid mid";
    align-content: center;
    column-gap: 10px;
    row-gap: 4px;
  }
  .mid {
    min-height: 118px;
    min-width: 0;
  }
  .stance {
    grid-template-columns: 1fr;
    grid-template-areas: "glyph" "name" "hint";
    justify-items: center;
    text-align: center;
    padding: 6px 6px 8px;
  }
  .stance .glyph {
    font-size: 24px;
    line-height: 1.1;
  }
  .sname {
    font-size: 14px;
  }
  .hint {
    font-size: 10.5px;
  }
  .seal {
    left: auto;
    right: 6%;
    width: 52px;
    height: 52px;
    font-size: 18px;
  }
}

/* Short screens (phones on their side): a tighter title, the hints on one line. */
@media (max-height: 520px) {
  .duel {
    gap: 4px;
    --pw: clamp(84px, min(30vw, 36vh), 180px);
  }
  .hanja {
    display: none;
  }
  .title {
    font-size: 22px;
  }
  .ribbon {
    position: absolute;
    top: 4px;
    left: 0;
    font-size: 10.5px;
    padding: 1px 12px 1px 4px;
    background: linear-gradient(90deg, #f6dc94, #e8c26a 70%, transparent);
  }
  .tools {
    top: -2px;
  }
  .fighter {
    gap: 3px;
  }
  .name {
    font-size: 15px;
  }
  .stat {
    display: none;
  }
  .hp {
    height: 12px;
  }
  .stance {
    padding: 4px 10px;
  }
  .stance .glyph {
    font-size: 22px;
  }
  .sname {
    font-size: 14px;
  }
  .hint {
    font-size: 10.5px;
  }
  .withdraw {
    position: absolute;
    right: 0;
    bottom: 0;
    padding: 3px 10px;
    font-size: 12px;
  }
  .stances {
    width: min(100% - 120px, 620px);
  }
}

@media (prefers-reduced-motion: reduce) {
  .duel {
    --t: 0ms;
  }
  .bouts i.now,
  .prompt {
    animation: none;
  }
}
</style>
