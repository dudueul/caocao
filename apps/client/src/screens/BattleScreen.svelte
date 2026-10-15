<script lang="ts">
import ActionMenu from "../components/ActionMenu.svelte";
import BattleField from "../components/BattleField.svelte";
import EventLog from "../components/EventLog.svelte";
import Forecast from "../components/Forecast.svelte";
import TileInfo from "../components/TileInfo.svelte";
import TopBar from "../components/TopBar.svelte";
import UnitCard from "../components/UnitCard.svelte";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import type { Session } from "../lib/session.svelte.ts";
import { T } from "../lib/text.ts";

let { session, online = null }: { session: Session; online?: OnlineRoom | null } = $props();
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
const result = $derived(
  winner === null
    ? null
    : winner < 0
      ? T.draw
      : winner === session.humanSide
        ? T.victory
        : T.defeat,
);
const waitingText = $derived(online ? T.opponentTurn : T.thinking);

function onKey(e: KeyboardEvent) {
  if (e.target instanceof HTMLInputElement || e.target instanceof HTMLSelectElement) return;
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
  {#if session.state.winner === null &&
    (session.busy || session.state.active !== session.humanSide)}
    <div class="pill" style:top="{topH + 8}px">{waitingText}</div>
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
      {#if online}
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
