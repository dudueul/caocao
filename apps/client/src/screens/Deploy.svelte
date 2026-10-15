<script lang="ts">
import { ARENA_FIELD, ARENA_POOL, OFFICERS, P0_RULES, TERRAIN_CODES } from "@tk/content";
import { onDestroy } from "svelte";
import { TERRAIN_COLOR } from "../lib/field.ts";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import { T } from "../lib/text.ts";

/** Arrange your six on your deploy tiles by swapping, then ready up (ADR 0039). */
let { online }: { online: OnlineRoom } = $props();
let first = $state<number | null>(null);
let now = $state(Date.now());
const timer = setInterval(() => (now = online.serverNow()), 250);
onDestroy(() => clearInterval(timer));

const deploy = $derived(online.room?.deploy ?? null);
const me = $derived(online.seat ?? 0);
const them = $derived(me === 0 ? 1 : 0);
const order = $derived(deploy?.arrangement[me] ?? []);
const ready = $derived(deploy?.ready[me] ?? false);
const left = $derived(deploy ? Math.max(0, Math.ceil((deploy.deadline - now) / 1000)) : 0);
const width = ARENA_FIELD.rows[0]?.length ?? 0;

function label(id: string): { name: string; cls: string } {
  const entry = ARENA_POOL.find((e) => e.id === id);
  return {
    name: OFFICERS[id]?.name ?? id,
    cls: (entry && P0_RULES.classes[entry.classId]?.name) ?? "",
  };
}

/** What sits on a map cell: one of my slots (with its officer) or one of theirs. */
function slotAt(x: number, y: number): { seat: number; slot: number } | null {
  for (const seat of [0, 1])
    for (const [slot, c] of (deploy?.slots[seat] ?? []).entries())
      if (c.x === x && c.y === y) return { seat, slot };
  return null;
}

function tap(slot: number) {
  if (ready) return;
  if (first === null) {
    first = slot;
    return;
  }
  if (first !== slot) {
    const next = [...order];
    [next[first], next[slot]] = [next[slot] as string, next[first] as string];
    online.arrange(next);
  }
  first = null;
}

const hex = (n: number) => `#${n.toString(16).padStart(6, "0")}`;
</script>

{#if deploy}
  <section class="deploy">
    <header>
      <strong>{T.deploy}</strong>
      <span class="muted">{ready ? T.waitingReady : T.deployHint}</span>
      <span class="chip timer">⏱ {left}s</span>
    </header>
    <div class="map" style:grid-template-columns="repeat({width}, 1fr)">
      {#each ARENA_FIELD.rows as row, y (y)}
        {#each [...row] as code, x (x)}
          {@const s = slotAt(x, y)}
          {@const terrain = TERRAIN_CODES[code] ?? "plain"}
          <div class="cell" style:background={hex(TERRAIN_COLOR[terrain])}>
            {#if s && s.seat === me}
              <button
                type="button"
                class="unit mine"
                class:picked={first === s.slot}
                onclick={() => tap(s.slot)}
                title={label(order[s.slot] ?? "").name}
              >
                {label(order[s.slot] ?? "").cls.slice(0, 1)}
              </button>
            {:else if s}
              <span class="unit theirs">?</span>
            {/if}
          </div>
        {/each}
      {/each}
    </div>
    <ol class="list">
      {#each order as id, slot (id)}
        <li>
          <button
            type="button"
            class:picked={first === slot}
            disabled={ready}
            onclick={() => tap(slot)}
          >
            <span class="slot">{slot + 1}</span>
            {label(id).name} <span class="muted">{label(id).cls}</span>
          </button>
        </li>
      {/each}
    </ol>
    <div class="row">
      <button type="button" class="primary" disabled={ready} onclick={() => online.ready()}>
        {ready ? T.waitingReady : T.ready}
      </button>
      <span class="muted"
        >{online.session.sideNames[them]}: {deploy.ready[them] ? T.ready : "…"}</span
      >
    </div>
  </section>
{/if}

<style>
.deploy {
  width: min(620px, 100%);
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
.timer {
  margin-left: auto;
  font-variant-numeric: tabular-nums;
}
.map {
  display: grid;
  gap: 1px;
  background: rgba(0, 0, 0, 0.3);
  border: 1px solid var(--line);
  aspect-ratio: 14 / 12;
  max-width: 100%;
}
.cell {
  position: relative;
  display: grid;
  place-items: center;
  min-width: 0;
}
.unit {
  width: 80%;
  aspect-ratio: 1;
  padding: 0;
  border-radius: 50%;
  display: grid;
  place-items: center;
  font-size: 12px;
  font-weight: 700;
}
.unit.mine {
  background: var(--ally);
  border: 2px solid #fff3;
}
.unit.theirs {
  background: var(--enemy);
  opacity: 0.7;
  color: #fff;
}
.picked {
  outline: 3px solid var(--accent);
}
.list {
  margin: 0;
  padding: 0;
  list-style: none;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 6px;
}
.list button {
  width: 100%;
  text-align: left;
}
.slot {
  display: inline-block;
  min-width: 1.4em;
  color: var(--accent);
  font-weight: 700;
}
.row {
  display: flex;
  gap: 10px;
  align-items: center;
}
</style>
