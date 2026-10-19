<script lang="ts">
import type { Line } from "@tk/content";
import { audio } from "../lib/audio/index.ts";
import { settings, TEXT_MS } from "../lib/settings.svelte.ts";
import type { Speaker } from "../lib/speakers.ts";
import Portrait from "./Portrait.svelte";

/**
 * One line of dialogue: the speaker's portrait and name plate, and the text
 * typed out (ADR 0049). A tap finishes the line, the next tap moves on.
 */
let {
  line,
  speaker,
  onnext,
  auto = false,
}: { line: Line; speaker: Speaker | null; onnext: () => void; auto?: boolean } = $props();

let shown = $state(0);
let timer: ReturnType<typeof setInterval> | undefined;
let autoTimer: ReturnType<typeof setTimeout> | undefined;
const chars = $derived([...line.text]);
const done = $derived(shown >= chars.length);
// Our side speaks from the left, the other army from the right.
const fromRight = $derived(speaker !== null && speaker.side !== 0);

$effect(() => {
  void line;
  clearInterval(timer);
  clearTimeout(autoTimer);
  const ms = TEXT_MS[settings.textSpeed];
  const total = [...line.text].length;
  if (ms === 0) {
    shown = total;
    return;
  }
  shown = 0;
  timer = setInterval(() => {
    shown = Math.min(total, shown + 1);
    if (shown >= total) clearInterval(timer);
  }, ms);
  return () => clearInterval(timer);
});

$effect(() => {
  if (!auto || !done) return;
  autoTimer = setTimeout(advance, 900 + chars.length * 35);
  return () => clearTimeout(autoTimer);
});

function advance() {
  if (!done) {
    shown = chars.length;
    clearInterval(timer);
    return;
  }
  audio.play("page");
  onnext();
}

function onKey(e: KeyboardEvent) {
  if (e.key === "Enter" || e.key === " ") {
    e.preventDefault();
    advance();
  }
}
</script>

<svelte:window onkeydown={onKey} />

<button
  type="button"
  class="box"
  class:right={fromRight}
  class:narration={speaker === null}
  onclick={advance}
  aria-label="다음"
>
  {#if speaker}
    <div class="face">
      <Portrait
        id={speaker.id}
        classId={speaker.classId}
        side={speaker.side}
        size={132}
        mood={line.mood ?? "calm"}
      />
    </div>
    <div class="plate" class:enemy={speaker.side !== 0}>{speaker.name}</div>
  {/if}
  <p class="text">
    {chars.slice(0, shown).join("")}<span class="rest">{chars.slice(shown).join("")}</span>
  </p>
  {#if done}
    <span class="more" aria-hidden="true">▼</span>
  {/if}
</button>

<style>
.box {
  position: relative;
  display: block;
  width: min(920px, calc(100% - 24px));
  min-height: 132px;
  margin: 0 auto;
  padding: 26px 22px 20px 168px;
  text-align: left;
  font: inherit;
  color: var(--text);
  background: linear-gradient(180deg, rgba(22, 18, 12, 0.88), rgba(10, 9, 7, 0.94));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid rgba(232, 194, 106, 0.55);
  border-radius: 16px;
  box-shadow:
    0 0 0 1px rgba(0, 0, 0, 0.5),
    0 18px 40px rgba(0, 0, 0, 0.55),
    inset 0 1px 0 rgba(255, 240, 200, 0.12);
  cursor: pointer;
}
.box:hover:not(:disabled) {
  border-color: rgba(232, 194, 106, 0.8);
}
.box.right {
  padding: 26px 168px 20px 22px;
}
.box.narration {
  padding: 22px 28px;
  text-align: center;
  font-family: var(--display);
  border-color: rgba(232, 194, 106, 0.3);
}
.face {
  position: absolute;
  left: 18px;
  bottom: 16px;
  filter: drop-shadow(0 8px 16px rgba(0, 0, 0, 0.6));
}
.right .face {
  left: auto;
  right: 18px;
}
.face :global(.face) {
  border: 1px solid rgba(232, 194, 106, 0.6);
  border-radius: 12px;
}
.plate {
  position: absolute;
  top: -15px;
  left: 160px;
  padding: 4px 16px;
  font-family: var(--display);
  font-weight: 800;
  font-size: 16px;
  letter-spacing: 0.08em;
  color: #fff3d0;
  background: linear-gradient(180deg, #8a2a22, #561812);
  border: 1px solid rgba(232, 194, 106, 0.7);
  border-radius: 999px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}
.plate.enemy {
  background: linear-gradient(180deg, #2f4a7c, #1c2c4e);
}
.right .plate {
  left: auto;
  right: 160px;
}
.text {
  margin: 0;
  font-size: 17px;
  line-height: 1.7;
  white-space: pre-wrap;
  word-break: keep-all;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.6);
}
.narration .text {
  font-size: 18px;
  color: #f4e6c4;
}
.rest {
  visibility: hidden;
}
.more {
  position: absolute;
  right: 18px;
  bottom: 10px;
  font-size: 11px;
  color: var(--accent);
  animation: bob 1s ease-in-out infinite;
}
.right .more {
  right: auto;
  left: 18px;
}
@keyframes bob {
  50% {
    transform: translateY(3px);
  }
}
@media (max-width: 600px) {
  .box,
  .box.right {
    padding: 64px 16px 16px;
    min-height: 120px;
  }
  .face,
  .right .face {
    left: 12px;
    right: auto;
    bottom: auto;
    top: -96px;
  }
  .face :global(.face) {
    width: 84px;
    height: 105px;
  }
  .plate,
  .right .plate {
    left: 104px;
    right: auto;
    top: 18px;
  }
  .text {
    font-size: 15px;
  }
}
@media (prefers-reduced-motion: reduce) {
  .more {
    animation: none;
  }
}
</style>
