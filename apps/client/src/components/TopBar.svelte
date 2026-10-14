<script lang="ts">
import type { TurnPolicyId, WeatherKind } from "@tk/rules";
import type { Session, Speed } from "../lib/session.svelte.ts";
import { POLICY, T, WEATHER } from "../lib/text.ts";

let { session }: { session: Session } = $props();
let setupOpen = $state(false);
let policy = $state<TurnPolicyId>("sidePhases");
let weather = $state<WeatherKind>("sunny");
let seed = $state("");

const SPEEDS: Speed[] = [1, 2, 4, 0];
const SPEED_LABEL: Record<Speed, string> = { 1: "1×", 2: "2×", 4: "4×", 0: T.skip };

function cycleSpeed() {
  session.speed = SPEEDS[(SPEEDS.indexOf(session.speed) + 1) % SPEEDS.length] ?? 1;
}
const s = $derived(session.state);
const canEnd = $derived(session.legal.some((c) => c.kind === "endPhase"));

function openSetup() {
  policy = session.options.policy;
  weather = session.options.weather;
  seed = "";
  setupOpen = !setupOpen;
}
function start() {
  setupOpen = false;
  session.newBattle(seed.trim() ? { policy, weather, seed: seed.trim() } : { policy, weather });
}
</script>

<header class="top">
  <div class="info">
    <strong>{session.scenario.name}</strong>
    <span class="chip">{T.round} {s.round}/{s.roundLimit}</span>
    <span class="chip side" class:enemy={s.active !== session.humanSide}>
      {session.scenario.sides[s.active]}
    </span>
    <span class="chip muted">{WEATHER[s.weather]} · {POLICY[s.policy]}</span>
  </div>
  <div class="actions">
    <button
      type="button"
      class:on={session.showDanger}
      onclick={() => (session.showDanger = !session.showDanger)}
      title="D"
    >
      {T.danger}
    </button>
    <button type="button" class:on={session.speed !== 1} onclick={cycleSpeed} title={T.speed}>
      {SPEED_LABEL[session.speed]}
    </button>
    <button type="button" class:on={session.auto} onclick={() => session.setAuto(!session.auto)}>
      {T.auto}
    </button>
    <button
      type="button"
      class="primary"
      disabled={!canEnd}
      onclick={() => session.endPhase()}
      title="E"
    >
      {T.endPhase}
    </button>
    <button type="button" onclick={openSetup}>{T.newBattle}</button>
  </div>
  {#if setupOpen}
    <div class="setup">
      <label>
        <span>턴 방식</span>
        <select bind:value={policy}>
          {#each Object.entries(POLICY) as [k, label] (k)}
            <option value={k}>{label}</option>
          {/each}
        </select>
      </label>
      <label>
        <span>날씨</span>
        <select bind:value={weather}>
          {#each Object.entries(WEATHER) as [k, label] (k)}
            <option value={k}>{label}</option>
          {/each}
        </select>
      </label>
      <label>시드 <input bind:value={seed} placeholder={session.options.seed} size="10"></label>
      <button type="button" class="primary" onclick={start}>{T.newBattle}</button>
    </div>
  {/if}
</header>

<style>
.top {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 6px;
  padding: 8px 10px;
  background: var(--panel);
  border-bottom: 1px solid var(--line);
}
.info,
.actions {
  display: flex;
  gap: 6px;
  align-items: center;
  flex-wrap: wrap;
}
.side {
  border-color: var(--ally);
}
.side.enemy {
  border-color: var(--enemy);
}
.setup {
  position: absolute;
  right: 10px;
  top: 100%;
  margin-top: 4px;
  display: grid;
  gap: 8px;
  padding: 10px;
  background: var(--panel);
  border: 1px solid var(--line);
  border-radius: 8px;
  z-index: 5;
}
.setup label {
  display: flex;
  justify-content: space-between;
  gap: 10px;
  align-items: center;
  font-size: 12px;
}
</style>
