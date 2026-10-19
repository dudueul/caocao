<script lang="ts">
import { normalizeRoomCode, ROOM_CODE } from "@tk/protocol";
import Credits from "./components/Credits.svelte";
import HelpPanel from "./components/HelpPanel.svelte";
import SettingsPanel from "./components/SettingsPanel.svelte";
import { audio } from "./lib/audio/index.ts";
import { ui } from "./lib/ui.svelte.ts";
import Home from "./screens/Home.svelte";
import RoomScreen from "./screens/RoomScreen.svelte";
import SandboxScreen from "./screens/SandboxScreen.svelte";
import StoryScreen from "./screens/StoryScreen.svelte";
import Title from "./screens/Title.svelte";

/**
 * Hash routes: #/ title · #/story · #/arena · #/sandbox · #/demo · #/r/CODE
 * (ADR 0038 invite links, ADR 0054 main menu).
 */
type Route =
  | { kind: "title" }
  | { kind: "story" }
  | { kind: "arena" }
  | { kind: "sandbox"; demo: boolean }
  | { kind: "room"; code: string };

function parse(hash: string): Route {
  const h = hash.replace(/^#\/?/, "");
  if (h === "story") return { kind: "story" };
  if (h === "arena") return { kind: "arena" };
  if (h === "sandbox") return { kind: "sandbox", demo: false };
  if (h === "demo") return { kind: "sandbox", demo: true };
  const m = h.match(/^r\/([^/]+)$/);
  if (m) {
    const code = normalizeRoomCode(m[1] ?? "");
    if (ROOM_CODE.test(code)) return { kind: "room", code };
  }
  return { kind: "title" };
}

// `?debug` exposes the current store on window.tk; `?demo` keeps the old sandbox demo link working.
const params = new URLSearchParams(location.search);
const expose = params.has("debug");
let hash = $state(params.has("demo") && location.hash === "" ? "#/demo" : location.hash);
const route = $derived(parse(hash));
const key = $derived(JSON.stringify(route));

$effect(() => {
  // Practice battles and the Arena have battle music; the title and Story choose their own.
  if (route.kind === "sandbox" || route.kind === "room") audio.music("battle");
  if (route.kind === "arena") audio.music("camp");
});

/** Browsers start sound only after a gesture; every button press also ticks softly. */
function onPointer(e: PointerEvent) {
  audio.unlock();
  const el = e.target instanceof Element ? e.target.closest("button, a.button") : null;
  if (el && !(el as HTMLButtonElement).disabled) audio.play("click");
}
</script>

<svelte:window
  onhashchange={() => (hash = location.hash)}
  onpointerdown={onPointer}
  onkeydown={() => audio.unlock()}
/>

{#key key}
  {#if route.kind === "room"}
    <RoomScreen code={route.code} {expose} />
  {:else if route.kind === "sandbox"}
    <SandboxScreen demo={route.demo} {expose} />
  {:else if route.kind === "story"}
    <StoryScreen {expose} />
  {:else if route.kind === "arena"}
    <Home />
  {:else}
    <Title />
  {/if}
{/key}

{#if ui.settings}
  <SettingsPanel />
{/if}
{#if ui.credits}
  <Credits />
{/if}
{#if ui.help}
  <HelpPanel />
{/if}
