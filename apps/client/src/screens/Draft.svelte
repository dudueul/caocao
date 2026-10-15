<script lang="ts">
import { ARENA_POOL, OFFICERS, P0_RULES } from "@tk/content";
import { onDestroy } from "svelte";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import { STATS, T } from "../lib/text.ts";

/** The snake draft (ADR 0039): tap an officer, then confirm. */
let { online }: { online: OnlineRoom } = $props();
let chosen = $state<string | null>(null);
let now = $state(Date.now());
const timer = setInterval(() => (now = online.serverNow()), 250);
onDestroy(() => clearInterval(timer));

const draft = $derived(online.room?.draft ?? null);
const me = $derived(online.seat ?? 0);
const them = $derived(me === 0 ? 1 : 0);
const turn = $derived(draft ? draft.order[draft.picks[0].length + draft.picks[1].length] : null);
const mine = $derived(turn === me);
const left = $derived(draft ? Math.max(0, Math.ceil((draft.deadline - now) / 1000)) : 0);
const names = $derived(online.session.sideNames);

function info(id: string) {
  const entry = ARENA_POOL.find((e) => e.id === id);
  const officer = OFFICERS[id];
  const cls = entry ? P0_RULES.classes[entry.classId] : undefined;
  return {
    name: officer?.name ?? id,
    hanja: officer?.hanja ?? "",
    cls: cls?.name ?? "",
    stats: officer?.stats,
  };
}

function confirm() {
  if (chosen && mine) online.pick(chosen);
  chosen = null;
}
</script>

{#if draft}
  <section class="draft">
    <header class:mine>
      <strong>{T.draft}</strong>
      <span>{mine ? T.myPick : T.theirPick}</span>
      <span class="chip timer">⏱ {left}s</span>
    </header>
    <div class="teams">
      {#each [me, them] as seat (seat)}
        <div class="team" class:me={seat === me}>
          <div class="muted">{names[seat] ?? ""}{seat === me ? ` (${T.you})` : ""}</div>
          {#each draft.picks[seat] ?? [] as id (id)}
            <div class="picked">{info(id).name} <span class="muted">{info(id).cls}</span></div>
          {/each}
          {#each Array(6 - (draft.picks[seat]?.length ?? 0)) as _, i (i)}
            <div class="picked empty">—</div>
          {/each}
        </div>
      {/each}
    </div>
    <div class="pool">
      {#each draft.available as id (id)}
        {@const o = info(id)}
        <button
          type="button"
          class="officer"
          class:chosen={chosen === id}
          disabled={!mine}
          onclick={() => (chosen = chosen === id ? null : id)}
        >
          <span class="name">{o.name}</span>
          <span class="muted">{o.hanja} · {o.cls}</span>
          <span class="stats">
            {#each STATS as [k, label] (k)}
              <span><span class="muted">{label}</span> {o.stats?.[k]}</span>
            {/each}
          </span>
        </button>
      {/each}
    </div>
    <button type="button" class="primary confirm" disabled={!mine || !chosen} onclick={confirm}>
      {T.pick}{chosen ? `: ${info(chosen).name}` : ""}
    </button>
  </section>
{/if}

<style>
.draft {
  width: min(760px, 100%);
  display: grid;
  gap: 12px;
}
header {
  display: flex;
  gap: 10px;
  align-items: center;
  flex-wrap: wrap;
  padding: 10px 12px;
  background: var(--panel);
  border: 1px solid var(--line);
  border-radius: 8px;
}
header.mine {
  border-color: var(--accent);
}
.timer {
  margin-left: auto;
  font-variant-numeric: tabular-nums;
}
.teams {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
}
.team {
  display: grid;
  gap: 4px;
  padding: 10px;
  background: var(--panel);
  border: 1px solid var(--line);
  border-left: 3px solid var(--enemy);
  border-radius: 8px;
  min-width: 0;
}
.team.me {
  border-left-color: var(--ally);
}
.picked {
  font-size: 13px;
}
.picked.empty {
  color: var(--muted);
}
.pool {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 8px;
}
.officer {
  display: grid;
  gap: 2px;
  text-align: left;
  padding: 8px 10px;
}
.officer.chosen {
  border-color: var(--accent);
  background: #3d321a;
}
.name {
  font-weight: 700;
  font-size: 15px;
}
.stats {
  display: flex;
  flex-wrap: wrap;
  gap: 2px 8px;
  font-size: 11px;
  font-variant-numeric: tabular-nums;
}
.muted {
  font-size: 12px;
}
.confirm {
  position: sticky;
  bottom: 8px;
  padding: 10px;
}
</style>
