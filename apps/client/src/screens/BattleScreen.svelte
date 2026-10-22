<script lang="ts">
import { friendly } from "@tk/rules";
import type { Snippet } from "svelte";
import BattleField from "../components/BattleField.svelte";
import Coach from "../components/Coach.svelte";
import CommandMenu from "../components/CommandMenu.svelte";
import CutIn from "../components/CutIn.svelte";
import DuelView from "../components/DuelView.svelte";
import EventLog from "../components/EventLog.svelte";
import Floating from "../components/Floating.svelte";
import Forecast from "../components/Forecast.svelte";
import TileInfo from "../components/TileInfo.svelte";
import TopBar from "../components/TopBar.svelte";
import UnitCard from "../components/UnitCard.svelte";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import type { Session } from "../lib/session.svelte.ts";
import { T } from "../lib/text.ts";

let {
  session,
  online = null,
  outro,
  tips = false,
}: {
  session: Session;
  online?: OnlineRoom | null;
  /** Replaces the result card's buttons (Story battles). */
  outro?: Snippet<[number]>;
  /** First-battle tips (ADR 0054). */
  tips?: boolean;
} = $props();
let goalsOpen = $state(true);
let width = $state(window.innerWidth);
let height = $state(window.innerHeight);
let topH = $state(52);
const wide = $derived(width >= 900);
// The phone panel has a fixed height, so the map never moves under the player's finger.
const panelH = $derived(wide ? 0 : Math.round(height * 0.36));

$effect(() => {
  session.insets = wide
    ? { top: topH, right: 340, bottom: 0, left: 0 }
    : { top: topH, right: 0, bottom: panelH, left: 0 };
});

const shownUnit = $derived(
  session.unit ?? (session.hover ? (session.unitAt(session.hover) ?? null) : null),
);
/** Online results can come from a concession, which the battle state doesn't show. */
const winner = $derived(online?.room?.result?.winner ?? session.state.winner);
// Story talk plays before the result card (the session is busy until it is read).
const result = $derived(
  winner === null || (session.busy && !online)
    ? null
    : winner < 0
      ? T.draw
      : winner === session.humanSide
        ? T.victory
        : T.defeat,
);
const waitingText = $derived(
  online
    ? T.opponentTurn
    : friendly(session.state, session.state.active, session.humanSide)
      ? T.alliesThinking
      : T.thinking,
);
/** The forecast floats beside its target (the chosen one, or the one under the pointer). */
const forecastCell = $derived.by(() => {
  const f = session.forecast;
  if (!f) return null;
  return f.kind === "attack"
    ? { x: f.defender.x, y: f.defender.y }
    : f.kind === "duel"
      ? { x: f.b.x, y: f.b.y }
      : f.at;
});

/** The player picks the stances of a 일기토 of his own; a story duel can't be left. */
const duelMine = $derived(
  session.state.duel !== null && session.state.active === session.humanSide,
);

function onKey(e: KeyboardEvent) {
  // The duel screen takes the keys while it is up (1 2 3, Esc).
  if (session.duel) return;
  if (
    e.target instanceof HTMLInputElement ||
    e.target instanceof HTMLSelectElement ||
    e.target instanceof HTMLButtonElement
  )
    return;
  if (e.key === "Escape") session.back();
  else if (e.key === "Enter") void session.confirm();
  else if (e.key === "d" || e.key === "D") session.showDanger = !session.showDanger;
  else if (!online && (e.key === "e" || e.key === "E")) void session.endPhase();
}
</script>

<svelte:window bind:innerWidth={width} bind:innerHeight={height} onkeydown={onKey} />

<main>
  <BattleField {session} />
  <div class="topwrap" bind:clientHeight={topH}><TopBar {session} {online} /></div>
  {#if session.story && session.state.winner === null}
    <button
      type="button"
      class="goals"
      class:closed={!goalsOpen}
      style:top="{topH + 8}px"
      onclick={() => (goalsOpen = !goalsOpen)}
    >
      {#if goalsOpen}
        <span class="win">승리 · {session.story.goalText.win}</span>
        <span class="lose">패배 · {session.story.goalText.lose}</span>
      {:else}
        <span class="win">목표</span>
      {/if}
    </button>
  {/if}
  {#if session.state.winner === null &&
    !session.talking &&
    (session.busy || session.state.active !== session.humanSide)}
    <div class="pill" style:top="{topH + (session.story ? 66 : 8)}px">{waitingText}</div>
  {/if}
  <aside
    class="panel"
    class:wide
    style:height={wide ? null : `${panelH}px`}
    style:top={wide ? `${topH}px` : null}
  >
    {#if shownUnit}
      <UnitCard {session} unit={shownUnit} />
    {:else}
      <p class="muted hint">{session.myTurn ? `${T.yourTurn} · 장수를 선택하세요` : waitingText}</p>
    {/if}
    {#if session.aim}
      <p class="muted hint">{T.pickTarget}</p>
    {:else if session.to}
      <p class="muted hint">{T.pickAction}</p>
    {:else if session.unitCommands.length > 0}
      <p class="muted hint">{T.pickTile}</p>
    {/if}
    {#if session.hover}
      <TileInfo {session} cell={session.hover} />
    {/if}
    <EventLog lines={session.log} />
  </aside>
  {#if session.myTurn && session.to}
    {#if session.forecast && forecastCell}
      <Floating {session} cell={forecastCell} avoid={[session.to]} {width} {height}>
        <Forecast {session} forecast={session.forecast} />
      </Floating>
    {:else if session.aim}
      <Floating {session} cell={session.to} {width} {height}>
        <div class="aiming">
          <span>{T.pickTarget}</span>
          <button type="button" onclick={() => session.back()}>{T.cancel}</button>
        </div>
      </Floating>
    {:else}
      <Floating {session} cell={session.to} {width} {height}>
        <CommandMenu {session} />
      </Floating>
    {/if}
  {/if}
  {#if tips && session.state.winner === null}
    <Coach {session} top={topH} />
  {/if}
  {#if session.cutin}
    <CutIn cut={session.cutin} at={session.cutinAt} />
  {/if}
  {#if session.duel}
    <DuelView
      show={session.duel}
      canPick={duelMine && session.myTurn}
      onpick={(s) => session.bout(s)}
      onwithdraw={duelMine && !session.state.duel?.script ? () => session.withdraw() : undefined}
      auto={session.auto}
      onauto={duelMine || session.auto ? (on) => session.setAuto(on) : undefined}
    />
  {/if}
  {#if session.banner && session.banner.tone !== "end"}
    <div class="banner {session.banner.tone}">{session.banner.text}</div>
  {/if}
  {#if result}
    <div class="result">
      <div class="title">{result}</div>
      {#if outro && winner !== null}
        <div class="muted">{T.round} {session.state.round}</div>
        {@render outro(winner)}
      {:else if online}
        <div class="muted">
          {T.round} {session.state.round}{online.room?.result?.reason === "concede"
            ? ` · ${T.conceded}`
            : ""}
        </div>
        <a class="button primary" href="#/">{T.home}</a>
      {:else}
        <div class="muted">{T.round} {session.state.round} · 명령 {session.commands.length}개</div>
        <button type="button" class="primary" onclick={() => session.newBattle()}>
          {T.newBattle}
        </button>
      {/if}
    </div>
  {/if}
</main>

<style>
main {
  position: fixed;
  /* Stay clear of phone notches and system bars (viewport-fit=cover). */
  inset: env(safe-area-inset-top, 0px) env(safe-area-inset-right, 0px)
    env(safe-area-inset-bottom, 0px) env(safe-area-inset-left, 0px);
}
.topwrap {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 3;
}
.topwrap :global(.top) {
  position: relative;
}
.panel {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px 12px;
  background: var(--panel);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-top: 1px solid var(--gold-line);
  box-shadow: 0 -8px 24px rgba(0, 0, 0, 0.35);
  overflow-y: auto;
  z-index: 2;
}
.panel.wide {
  left: auto;
  width: 340px;
  border-top: none;
  border-left: 1px solid var(--gold-line);
  box-shadow: -8px 0 24px rgba(0, 0, 0, 0.35);
}
.hint {
  margin: 0;
  font-size: 13px;
}
.aiming {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 13px;
}
.goals {
  position: absolute;
  left: 12px;
  display: grid;
  gap: 2px;
  max-width: min(60vw, 320px);
  padding: 6px 12px;
  text-align: left;
  font-size: 12px;
  background: rgba(10, 9, 7, 0.72);
  border: 1px solid var(--gold-line);
  border-radius: 10px;
  z-index: 3;
}
.goals .win {
  color: var(--accent);
  font-weight: 700;
}
.goals .lose {
  color: var(--bad);
}
.pill {
  position: absolute;
  left: 12px;
  padding: 3px 12px;
  font-size: 12px;
  background: rgba(10, 9, 7, 0.7);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid var(--gold-line);
  border-radius: 999px;
  pointer-events: none;
  z-index: 3;
}
.banner {
  position: absolute;
  top: 30%;
  left: 0;
  right: 0;
  padding: 14px 20px;
  text-align: center;
  font-family: var(--display);
  font-size: 26px;
  font-weight: 800;
  letter-spacing: 0.12em;
  color: #fff6dc;
  text-shadow:
    0 0 16px rgba(232, 194, 106, 0.6),
    0 2px 0 #000;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(12, 10, 7, 0.85) 25%,
    rgba(12, 10, 7, 0.85) 75%,
    transparent
  );
  border-top: 1px solid rgba(232, 194, 106, 0.55);
  border-bottom: 1px solid rgba(232, 194, 106, 0.55);
  pointer-events: none;
  z-index: 4;
  animation: banner-in 0.35s cubic-bezier(0.2, 0.8, 0.2, 1);
}
.banner.twist {
  color: var(--twist);
  text-shadow:
    0 0 18px rgba(255, 210, 63, 0.8),
    0 2px 0 #000;
}
@keyframes banner-in {
  from {
    opacity: 0;
    transform: scaleX(0.6);
    letter-spacing: 0.4em;
  }
}
.result {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: grid;
  gap: 10px;
  justify-items: center;
  padding: 24px 44px;
  background: radial-gradient(
    ellipse at 50% 0%,
    rgba(232, 194, 106, 0.22),
    rgba(10, 9, 7, 0.9) 70%
  );
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid rgba(232, 194, 106, 0.6);
  border-radius: 16px;
  box-shadow:
    0 0 40px rgba(232, 194, 106, 0.25),
    0 20px 50px rgba(0, 0, 0, 0.6);
  z-index: 4;
  animation: result-in 0.5s cubic-bezier(0.2, 0.8, 0.2, 1);
}
.result .title {
  font-family: var(--display);
  font-size: 40px;
  font-weight: 900;
  letter-spacing: 0.2em;
  background: linear-gradient(180deg, #fff3c4, #e8c26a 60%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 2px 6px rgba(0, 0, 0, 0.6));
}
@keyframes result-in {
  from {
    opacity: 0;
    transform: translate(-50%, -50%) scale(0.85);
  }
}
</style>
