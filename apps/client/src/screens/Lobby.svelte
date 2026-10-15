<script lang="ts">
import type { OnlineRoom } from "../lib/online.svelte.ts";
import { T } from "../lib/text.ts";

let { online }: { online: OnlineRoom } = $props();
let copied = $state(false);

async function copy() {
  try {
    await navigator.clipboard.writeText(online.inviteLink());
    copied = true;
  } catch {
    copied = false;
  }
}
</script>

<section class="lobby card">
  <h2>{T.arena} {online.code}</h2>
  <p class="muted">{T.inviteFriend}</p>
  <div class="row">
    <input readonly value={online.inviteLink()} onfocus={(e) => e.currentTarget.select()}>
    <button type="button" onclick={copy}>{copied ? T.copied : T.copy}</button>
  </div>
  <ul>
    {#each online.room?.players ?? [] as p, i (i)}
      <li>
        <span class="dot" class:on={p?.connected}></span>
        {p ? p.name : T.waitingForFriend}
        {#if i === online.seat}
          <span class="chip">{T.you}</span>
        {/if}
      </li>
    {/each}
  </ul>
</section>

<style>
.card {
  width: min(460px, 100%);
  display: grid;
  gap: 10px;
  padding: 16px;
  background: var(--panel);
  border: 1px solid var(--line);
  border-radius: 10px;
}
h2 {
  margin: 0;
}
.row {
  display: flex;
  gap: 8px;
}
.row input {
  flex: 1;
  min-width: 0;
}
ul {
  margin: 0;
  padding: 0;
  list-style: none;
  display: grid;
  gap: 6px;
}
.dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: var(--muted);
  margin-right: 6px;
}
.dot.on {
  background: var(--good);
}
</style>
