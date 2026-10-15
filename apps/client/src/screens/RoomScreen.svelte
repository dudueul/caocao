<script lang="ts">
import { onDestroy, onMount } from "svelte";
import { OnlineRoom } from "../lib/online.svelte.ts";
import { T } from "../lib/text.ts";
import BattleScreen from "./BattleScreen.svelte";
import Deploy from "./Deploy.svelte";
import Draft from "./Draft.svelte";
import Lobby from "./Lobby.svelte";

let { code, expose = false }: { code: string; expose?: boolean } = $props();
// The room lives as long as this screen (the router remounts it per code).
// svelte-ignore state_referenced_locally
const online = new OnlineRoom(code);
onMount(() => {
  if (expose) (window as unknown as { tk: OnlineRoom }).tk = online;
});
onDestroy(() => online.dispose());

const phase = $derived(online.room?.phase ?? null);
</script>

{#if online.link === "refused"}
  <main class="center">
    <section class="card">
      <h2>{T.roomMissing}</h2>
      <p class="muted">{online.error}</p>
      <a class="button primary" href="#/">{T.home}</a>
    </section>
  </main>
{:else if (phase === "battle" || phase === "over") && online.battleReady}
  <BattleScreen session={online.session} {online} />
{:else}
  <main class="center">
    {#if phase === "draft"}
      <Draft {online} />
    {:else if phase === "deploy"}
      <Deploy {online} />
    {:else if phase === "over"}
      <section class="card">
        <h2>{online.room?.result?.winner === online.seat ? T.victory : T.defeat}</h2>
        <p class="muted">{T.conceded}</p>
        <a class="button primary" href="#/">{T.home}</a>
      </section>
    {:else}
      <Lobby {online} />
    {/if}
    {#if online.link !== "open"}
      <p class="muted">{T.reconnecting}</p>
    {/if}
    {#if online.notice}
      <p class="notice">{online.notice}</p>
    {/if}
    <a class="muted back" href="#/">{T.home}</a>
  </main>
{/if}

<style>
.center {
  position: fixed;
  inset: 0;
  overflow-y: auto;
  display: grid;
  align-content: start;
  justify-items: center;
  gap: 12px;
  padding-block: max(16px, env(safe-area-inset-top, 0px)) 24px;
  padding-left: max(16px, env(safe-area-inset-left, 0px));
  padding-right: max(16px, env(safe-area-inset-right, 0px));
}
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
.notice {
  color: var(--bad);
}
.back {
  font-size: 13px;
}
</style>
