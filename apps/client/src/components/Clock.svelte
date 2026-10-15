<script lang="ts">
import { onDestroy } from "svelte";
import type { OnlineRoom } from "../lib/online.svelte.ts";
import { T } from "../lib/text.ts";

/** The running activation's clock (ADR 0040): free time, then the reserve. */
let { online }: { online: OnlineRoom } = $props();
let now = $state(Date.now());
const timer = setInterval(() => (now = online.serverNow()), 250);
onDestroy(() => clearInterval(timer));

const c = $derived(online.clock);
const mine = $derived(c !== null && c.active === online.seat);
const free = $derived(c ? Math.max(0, c.freeUntil - now) : 0);
const reserve = $derived(c ? Math.max(0, Math.min(c.reserveMs[c.active], c.deadline - now)) : 0);

function fmt(ms: number): string {
  const s = Math.ceil(ms / 1000);
  return `${Math.floor(s / 60)}:${String(s % 60).padStart(2, "0")}`;
}
</script>

{#if c}
  <span class="chip clock" class:mine class:low={free === 0}>
    ⏱ {fmt(free)} · {T.reserve} {fmt(free > 0 ? c.reserveMs[c.active] : reserve)}
  </span>
{/if}

<style>
.clock {
  font-variant-numeric: tabular-nums;
}
.mine {
  border-color: var(--accent);
}
.low {
  border-color: var(--bad);
  color: var(--bad);
}
</style>
