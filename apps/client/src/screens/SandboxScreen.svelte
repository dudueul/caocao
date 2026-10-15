<script lang="ts">
import { onMount } from "svelte";
import { Session } from "../lib/session.svelte.ts";
import BattleScreen from "./BattleScreen.svelte";

let { demo = false, expose = false }: { demo?: boolean; expose?: boolean } = $props();
const session = new Session();

onMount(() => {
  if (expose) (window as unknown as { tk: Session }).tk = session;
  session.newBattle();
  if (demo) session.startDemo();
});
</script>

<BattleScreen {session} />
