<script lang="ts">
import { normalizeRoomCode, ROOM_CODE } from "@tk/protocol";
import Home from "./screens/Home.svelte";
import RoomScreen from "./screens/RoomScreen.svelte";
import SandboxScreen from "./screens/SandboxScreen.svelte";

/** Hash routes: #/ home · #/sandbox · #/demo · #/r/CODE (ADR 0038 invite links). */
type Route = { kind: "home" } | { kind: "sandbox"; demo: boolean } | { kind: "room"; code: string };

function parse(hash: string): Route {
  const h = hash.replace(/^#\/?/, "");
  if (h === "sandbox") return { kind: "sandbox", demo: false };
  if (h === "demo") return { kind: "sandbox", demo: true };
  const m = h.match(/^r\/([^/]+)$/);
  if (m) {
    const code = normalizeRoomCode(m[1] ?? "");
    if (ROOM_CODE.test(code)) return { kind: "room", code };
  }
  return { kind: "home" };
}

// `?debug` exposes the current store on window.tk; `?demo` keeps the old sandbox demo link working.
const params = new URLSearchParams(location.search);
const expose = params.has("debug");
let hash = $state(params.has("demo") && location.hash === "" ? "#/demo" : location.hash);
const route = $derived(parse(hash));
const key = $derived(JSON.stringify(route));
</script>

<svelte:window onhashchange={() => (hash = location.hash)} />

{#key key}
  {#if route.kind === "room"}
    <RoomScreen code={route.code} {expose} />
  {:else if route.kind === "sandbox"}
    <SandboxScreen demo={route.demo} {expose} />
  {:else}
    <Home />
  {/if}
{/key}
