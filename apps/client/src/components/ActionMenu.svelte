<script lang="ts">
import type { Session } from "../lib/session.svelte.ts";
import { T } from "../lib/text.ts";

let { session }: { session: Session } = $props();
</script>

<section class="menu">
  {#if session.aim}
    <p class="muted">{T.pickTarget}</p>
    <button type="button" onclick={() => session.back()}>{T.back}</button>
  {:else}
    <p class="muted">{T.pickAction}</p>
    <div class="row">
      <button
        type="button"
        class="primary"
        disabled={session.attackCells.length === 0}
        onclick={() => session.chooseAttack()}
      >
        {T.attack}
      </button>
      <button type="button" onclick={() => session.wait()}>{T.wait}</button>
      <button type="button" onclick={() => session.undoMove()}>{T.undoMove}</button>
    </div>
    {#if session.stratagemOptions.length > 0}
      <div class="row">
        {#each session.stratagemOptions as s (s.id)}
          <button
            type="button"
            disabled={s.cells.length === 0}
            onclick={() => session.chooseStratagem(s.id)}
          >
            {s.name} <span class="muted">{s.mp}</span>
          </button>
        {/each}
      </div>
    {/if}
  {/if}
</section>

<style>
.menu p {
  margin: 0 0 6px;
  font-size: 12px;
}
.row {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin-bottom: 6px;
}
</style>
