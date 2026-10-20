<script lang="ts">
import { friendly, type TurnPolicyId, type WeatherKind } from "@tk/rules";
import { CLASSIC_BATTLES, prepareClassicMap } from "../lib/classic-battle.ts";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import type { Session, Speed } from "../lib/session.svelte.ts";
import { classic } from "../lib/skin.svelte.ts";
import { POLICY, T, WEATHER } from "../lib/text.ts";
import { ui } from "../lib/ui.svelte.ts";
import Clock from "./Clock.svelte";

let { session, online = null }: { session: Session; online?: OnlineRoom | null } = $props();
let confirmConcede = $state(false);
let setupOpen = $state(false);
let policy = $state<TurnPolicyId>("sidePhases");
let weather = $state<WeatherKind>("sunny");
let seed = $state("");
let classicMap = $state<number | null>(null);

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
  classicMap = session.options.classicMap;
  seed = "";
  setupOpen = !setupOpen;
}
async function start() {
  setupOpen = false;
  const map = await prepareClassicMap(session, classicMap);
  const o = { policy, weather, classicMap: map };
  session.newBattle(seed.trim() ? { ...o, seed: seed.trim() } : o);
}
</script>

<header class="top">
  <div class="info">
    <strong>{online ? `${T.arena} ${online.code}` : session.title}</strong>
    <span class="chip">{T.round} {s.round}/{s.roundLimit}</span>
    <span
      class="chip side"
      class:friend={s.active !== session.humanSide && friendly(s, s.active, session.humanSide)}
      class:enemy={!friendly(s, s.active, session.humanSide)}
    >
      {session.sideNames[s.active]}
    </span>
    <span class="chip muted">{WEATHER[s.weather]} · {POLICY[s.policy]}</span>
    {#if online?.clock && s.winner === null}
      <Clock {online} />
    {/if}
    {#if online && online.link !== "open"}
      <span class="chip warn">{T.reconnecting}</span>
    {/if}
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
    {#if online}
      {#if online.room?.phase === "battle"}
        <button
          type="button"
          class:danger={confirmConcede}
          onclick={() => {
            if (confirmConcede) online.concede();
            confirmConcede = !confirmConcede;
          }}
        >
          {confirmConcede ? T.concedeConfirm : T.concede}
        </button>
      {/if}
      <a class="button" href="#/">{T.home}</a>
    {:else}
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
      {#if !session.story}
        <button type="button" onclick={openSetup}>{T.newBattle}</button>
      {/if}
      <button type="button" onclick={() => (ui.help = true)} aria-label="도움말">?</button>
      <button type="button" onclick={() => (ui.settings = true)} aria-label="설정">⚙</button>
      <a class="button" href="#/">{T.home}</a>
    {/if}
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
      {#if classic.skin}
        <label>
          <span>{T.map}</span>
          <select bind:value={classicMap}>
            <option value={null}>{T.defaultMap}</option>
            {#each CLASSIC_BATTLES as b (b.map)}
              <option value={b.map}>{b.name}</option>
            {/each}
          </select>
        </label>
      {/if}
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
  background: linear-gradient(180deg, rgba(14, 12, 9, 0.9), rgba(14, 12, 9, 0.62));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-bottom: 1px solid var(--gold-line);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.35);
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
.warn {
  border-color: #c58cff;
  color: #e2c8ff;
}
.danger {
  border-color: var(--bad);
  color: var(--bad);
}
.side.enemy {
  border-color: var(--enemy);
}
.side.friend {
  border-color: var(--friend);
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
