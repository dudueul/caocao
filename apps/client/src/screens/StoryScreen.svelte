<script lang="ts">
import TalkOverlay from "../components/TalkOverlay.svelte";
import { audio, type Track } from "../lib/audio/index.ts";
import { story } from "../lib/story.svelte.ts";
import BattleScreen from "./BattleScreen.svelte";
import CampView from "./story/CampView.svelte";
import EndingView from "./story/EndingView.svelte";
import ResultView from "./story/ResultView.svelte";
import SceneView from "./story/SceneView.svelte";
import SortieView from "./story/SortieView.svelte";

/** The Story mode (ADR 0049): one screen per phase of the chapter. */
let { expose = false }: { expose?: boolean } = $props();

$effect(() => {
  if (expose) (window as unknown as { story: typeof story }).story = story;
});

const track = $derived.by<Track | null>(() => {
  switch (story.phase) {
    case "scene":
      return story.scene?.backdrop === "fire" || story.scene?.backdrop === "night"
        ? "danger"
        : "story";
    case "battle":
      return story.battle?.music ?? "battle";
    case "end":
      return "ending";
    default:
      return "camp";
  }
});

$effect(() => {
  audio.music(track);
});

// The battle's first tips only in the first battle of the chapter.
const firstBattle = $derived(story.campaign.cleared.length === 0);
</script>

{#if story.phase === "scene"}
  <SceneView />
{:else if story.phase === "sortie"}
  <SortieView />
{:else if story.phase === "battle" && story.session}
  <BattleScreen session={story.session} tips={firstBattle}>
    {#snippet outro(
      winner: number,
    )}
      <button type="button" class="primary" onclick={() => story.finishBattle()}>
        {winner === 0 ? "전과 보기" : "결과 보기"}
      </button>
    {/snippet}
  </BattleScreen>
  <TalkOverlay />
{:else if story.phase === "result"}
  <ResultView />
{:else if story.phase === "camp"}
  <CampView />
{:else if story.phase === "end"}
  <EndingView />
{/if}
{#if story.phase !== "battle" && story.phase !== "scene"}
  <a class="button home" href="#/" aria-label="타이틀로">☰</a>
{/if}

<style>
.home {
  position: fixed;
  left: max(10px, env(safe-area-inset-left, 0px));
  bottom: max(10px, env(safe-area-inset-bottom, 0px));
  z-index: 30;
  padding: 6px 12px;
  opacity: 0.85;
}
</style>
