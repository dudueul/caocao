<script lang="ts">
import { onMount } from "svelte";
import ActionMenu from "./components/ActionMenu.svelte";
import BattleField from "./components/BattleField.svelte";
import EventLog from "./components/EventLog.svelte";
import Forecast from "./components/Forecast.svelte";
import TileInfo from "./components/TileInfo.svelte";
import TopBar from "./components/TopBar.svelte";
import UnitCard from "./components/UnitCard.svelte";
import { Session } from "./lib/session.svelte.ts";
import { T } from "./lib/text.ts";

const session = new Session();
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
const result = $derived(
  session.state.winner === null
    ? null
    : session.state.winner < 0
      ? T.draw
      : session.state.winner === session.humanSide
        ? T.victory
        : T.defeat,
);

function onKey(e: KeyboardEvent) {
  if (e.target instanceof HTMLInputElement || e.target instanceof HTMLSelectElement) return;
  if (e.key === "Escape") session.back();
  else if (e.key === "Enter") void session.confirm();
  else if (e.key === "d" || e.key === "D") session.showDanger = !session.showDanger;
  else if (e.key === "e" || e.key === "E") void session.endPhase();
}

onMount(() => {
  // `?debug` exposes the store in the console (window.tk).
  if (new URLSearchParams(location.search).has("debug"))
    (window as unknown as { tk: Session }).tk = session;
  session.newBattle();
  // `?demo` or `#demo` lets the AI play both sides, battle after battle.
  if (new URLSearchParams(location.search).has("demo") || location.hash === "#demo")
    session.startDemo();
});
</script>

<svelte:window bind:innerWidth={width} bind:innerHeight={height} onkeydown={onKey} />

<main>
  <BattleField {session} />
  <div class="topwrap" bind:clientHeight={topH}><TopBar {session} /></div>
  {#if session.state.winner === null &&
    (session.busy || session.state.active !== session.humanSide)}
    <div class="pill" style:top="{topH + 8}px">{T.thinking}</div>
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
      <p class="muted hint">{session.myTurn ? `${T.yourTurn} · 장수를 선택하세요` : T.thinking}</p>
    {/if}
    {#if session.forecast}
      <Forecast {session} forecast={session.forecast} />
    {:else if session.to}
      <ActionMenu {session} />
    {:else if session.unitCommands.length > 0}
      <p class="muted hint">{T.pickTile}</p>
    {/if}
    {#if session.hover}
      <TileInfo {session} cell={session.hover} />
    {/if}
    <EventLog lines={session.log} />
  </aside>
  {#if session.banner && session.banner.tone !== "end"}
    <div class="banner {session.banner.tone}">{session.banner.text}</div>
  {/if}
  {#if result}
    <div class="result">
      <div class="title">{result}</div>
      <div class="muted">{T.round} {session.state.round} · 명령 {session.commands.length}개</div>
      <button type="button" class="primary" onclick={() => session.newBattle()}>
        {T.newBattle}
      </button>
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
  border-top: 1px solid var(--line);
  overflow-y: auto;
  z-index: 2;
}
.panel.wide {
  left: auto;
  width: 340px;
  border-top: none;
  border-left: 1px solid var(--line);
}
.hint {
  margin: 0;
  font-size: 13px;
}
.pill {
  position: absolute;
  left: 12px;
  padding: 3px 10px;
  font-size: 12px;
  background: rgba(0, 0, 0, 0.65);
  border: 1px solid var(--line);
  border-radius: 999px;
  pointer-events: none;
  z-index: 3;
}
.banner {
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 26px;
  font-size: 22px;
  font-weight: 700;
  background: rgba(0, 0, 0, 0.7);
  border: 1px solid var(--accent);
  border-radius: 8px;
  pointer-events: none;
  z-index: 4;
}
.banner.twist {
  color: var(--twist);
  border-color: var(--twist);
}
.result {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: grid;
  gap: 10px;
  justify-items: center;
  padding: 20px 36px;
  background: rgba(0, 0, 0, 0.82);
  border: 1px solid var(--accent);
  border-radius: 10px;
  z-index: 4;
}
.result .title {
  font-size: 30px;
  font-weight: 800;
  color: var(--accent);
}
</style>
