<script lang="ts">
import Backdrop from "../../components/Backdrop.svelte";
import DialogueBox from "../../components/DialogueBox.svelte";
import { speakerOf } from "../../lib/speakers.ts";
import { sceneLines, story } from "../../lib/story.svelte.ts";

/** A story scene: a title card, then the lines over the painted backdrop (ADR 0049). */
const scene = $derived(story.scene);
const line = $derived(scene ? (sceneLines(scene, story.campaign.flags)[story.line] ?? null) : null);
const speaker = $derived(line ? speakerOf(line, story.campaign) : null);
let auto = $state(false);
let card = $state(true);
let cardTimer: ReturnType<typeof setTimeout> | undefined;

$effect(() => {
  void scene?.id;
  card = true;
  clearTimeout(cardTimer);
  cardTimer = setTimeout(() => (card = false), 2200);
  return () => clearTimeout(cardTimer);
});
</script>

{#if scene}
  <main class="scene">
    <Backdrop kind={scene.backdrop} />
    {#if card}
      <button type="button" class="card" onclick={() => (card = false)} aria-label="계속">
        <span class="when">{scene.when} · {scene.place}</span>
        <span class="title">{scene.title}</span>
      </button>
    {:else if line}
      <div class="tools">
        <button type="button" class:on={auto} onclick={() => (auto = !auto)}>자동</button>
        <button type="button" onclick={() => story.endScene()}>건너뛰기</button>
        <a class="button" href="#/" aria-label="타이틀로">☰</a>
      </div>
      <div class="where">{scene.place} · {scene.when}</div>
      <div class="dialogue">
        {#key story.line}
          <DialogueBox {line} {speaker} {auto} onnext={() => story.next()} />
        {/key}
      </div>
    {/if}
  </main>
{/if}

<style>
.scene {
  position: fixed;
  inset: 0;
  overflow: hidden;
}
.card {
  position: absolute;
  inset: 0;
  display: grid;
  place-content: center;
  gap: 14px;
  text-align: center;
  background: radial-gradient(ellipse at center, rgba(0, 0, 0, 0.25), rgba(0, 0, 0, 0.7));
  border: none;
  border-radius: 0;
  box-shadow: none;
  animation: fade 2.2s ease-in-out both;
}
.card:hover:not(:disabled) {
  box-shadow: none;
}
.when {
  font-size: 14px;
  letter-spacing: 0.2em;
  color: #e8d8b0;
}
.title {
  font-family: var(--display);
  font-size: clamp(30px, 6vw, 54px);
  font-weight: 900;
  letter-spacing: 0.18em;
  background: linear-gradient(180deg, #fff3c4, #e8c26a 60%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 3px 10px rgba(0, 0, 0, 0.8));
}
.tools {
  position: absolute;
  top: max(12px, env(safe-area-inset-top, 0px));
  right: max(12px, env(safe-area-inset-right, 0px));
  display: flex;
  gap: 8px;
  z-index: 2;
}
.tools .on {
  border-color: var(--accent);
  color: var(--accent);
}
.where {
  position: absolute;
  top: max(16px, env(safe-area-inset-top, 0px));
  left: max(16px, env(safe-area-inset-left, 0px));
  font-family: var(--display);
  font-size: 14px;
  letter-spacing: 0.12em;
  color: rgba(255, 240, 210, 0.85);
  text-shadow: 0 1px 4px #000;
}
.dialogue {
  position: absolute;
  left: 0;
  right: 0;
  bottom: max(18px, env(safe-area-inset-bottom, 0px));
}
@keyframes fade {
  0% {
    opacity: 0;
  }
  15%,
  80% {
    opacity: 1;
  }
  100% {
    opacity: 0.9;
  }
}
@media (prefers-reduced-motion: reduce) {
  .card {
    animation: none;
  }
}
</style>
