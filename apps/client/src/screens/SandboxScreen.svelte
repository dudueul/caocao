<script lang="ts">
import { onMount } from "svelte";
import { prepareClassicMap } from "../lib/classic-battle.ts";
import { Session } from "../lib/session.svelte.ts";
import BattleScreen from "./BattleScreen.svelte";

let { demo = false, expose = false }: { demo?: boolean; expose?: boolean } = $props();
const session = new Session();

onMount(() => {
  if (expose) (window as unknown as { tk: Session }).tk = session;
  // `?map=16` fights on a 1998 map when the local classic skin has it (ADR 0042).
  const map = Number(new URLSearchParams(location.search).get("map")) || null;
  void prepareClassicMap(session, map).then((classicMap) => {
    session.newBattle({ classicMap });
    if (demo) session.startDemo();
  });
});
</script>

<BattleScreen {session} />
