<script lang="ts">
import { speakerOf } from "../lib/speakers.ts";
import { story } from "../lib/story.svelte.ts";
import DialogueBox from "./DialogueBox.svelte";

/** Lines spoken during a battle, over the field; the battle waits until they are read. */
const talk = $derived(story.talk);
const line = $derived(talk ? (talk.lines[talk.index] ?? null) : null);
const speaker = $derived(line ? speakerOf(line, story.campaign) : null);
</script>

{#if talk && line}
  <div class="talk">
    <div class="skip">
      <button type="button" onclick={() => story.skipTalk()}>건너뛰기</button>
    </div>
    <div class="dialogue">
      {#key `${talk.index}:${line.text}`}
        <DialogueBox {line} {speaker} onnext={() => story.nextTalk()} />
      {/key}
    </div>
  </div>
{/if}

<style>
.talk {
  position: fixed;
  inset: 0;
  z-index: 20;
  background: linear-gradient(180deg, transparent 40%, rgba(0, 0, 0, 0.55));
}
.skip {
  position: absolute;
  top: calc(env(safe-area-inset-top, 0px) + 64px);
  right: max(12px, env(safe-area-inset-right, 0px));
}
.dialogue {
  position: absolute;
  left: 0;
  right: 0;
  bottom: max(18px, env(safe-area-inset-bottom, 0px));
}
</style>
