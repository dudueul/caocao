<script lang="ts">
import type { ChoiceOption } from "@tk/content";
import { audio } from "../lib/audio/index.ts";

/**
 * A choice in a scene (ADR 0060, 0063): the question and the options, picked
 * with a tap, a click, the arrow keys and Enter, or a number key.
 */
let {
  prompt,
  options,
  onpick,
}: { prompt: string; options: readonly ChoiceOption[]; onpick: (value: string) => void } = $props();

let at = $state(0);
let picked = $state<string | null>(null);

function pick(value: string) {
  if (picked) return;
  picked = value;
  audio.play("confirm");
  // A beat to see the pick light up before the scene goes on.
  setTimeout(() => onpick(value), 260);
}

function onKey(e: KeyboardEvent) {
  if (picked) return;
  const n = options.length;
  if (e.key === "ArrowDown" || e.key === "ArrowRight" || e.key === "s" || e.key === "d") {
    e.preventDefault();
    at = (at + 1) % n;
    audio.play("select");
  } else if (e.key === "ArrowUp" || e.key === "ArrowLeft" || e.key === "w" || e.key === "a") {
    e.preventDefault();
    at = (at - 1 + n) % n;
    audio.play("select");
  } else if (e.key === "Enter" || e.key === " ") {
    e.preventDefault();
    const o = options[at];
    if (o) pick(o.value);
  } else if (/^[1-9]$/.test(e.key)) {
    const o = options[Number(e.key) - 1];
    if (o) {
      e.preventDefault();
      at = Number(e.key) - 1;
      pick(o.value);
    }
  }
}
</script>

<svelte:window onkeydown={onKey} />

<div class="choice" role="dialog" aria-modal="true" aria-label={prompt}>
  <p class="prompt">{prompt}</p>
  <div class="options">
    {#each options as o, i (o.value)}
      <button
        type="button"
        class="option"
        class:at={at === i}
        class:picked={picked === o.value}
        class:dim={picked !== null && picked !== o.value}
        onpointerenter={() => (at = i)}
        onclick={() => pick(o.value)}
      >
        <span class="num" aria-hidden="true">{i + 1}</span>
        <span class="text">{o.text}</span>
      </button>
    {/each}
  </div>
</div>

<style>
.choice {
  width: min(720px, calc(100% - 24px));
  margin: 0 auto;
  padding: 18px 18px 16px;
  display: grid;
  gap: 12px;
  background: linear-gradient(180deg, rgba(22, 18, 12, 0.9), rgba(10, 9, 7, 0.95));
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: 1px solid rgba(232, 194, 106, 0.6);
  border-radius: 16px;
  box-shadow:
    0 0 0 1px rgba(0, 0, 0, 0.5),
    0 18px 40px rgba(0, 0, 0, 0.55),
    0 0 30px rgba(232, 194, 106, 0.12);
  animation: rise 0.28s cubic-bezier(0.2, 0.9, 0.3, 1.1);
}
.prompt {
  margin: 0;
  text-align: center;
  font-family: var(--display);
  font-size: 19px;
  font-weight: 800;
  letter-spacing: 0.04em;
  color: #f4e2b0;
  word-break: keep-all;
  text-shadow: 0 1px 3px #000;
}
.options {
  display: grid;
  gap: 8px;
}
.option {
  display: flex;
  align-items: center;
  gap: 12px;
  min-height: 52px;
  padding: 10px 16px 10px 10px;
  text-align: left;
  border-radius: 12px;
  border: 1px solid rgba(232, 194, 106, 0.25);
  background: linear-gradient(180deg, rgba(60, 50, 34, 0.9), rgba(30, 26, 20, 0.95));
  font-size: 16px;
  font-weight: 700;
  word-break: keep-all;
  transition:
    border-color 0.15s,
    background 0.15s,
    opacity 0.2s,
    transform 0.1s;
}
.option.at {
  border-color: var(--accent);
  background: linear-gradient(180deg, rgba(110, 86, 38, 0.92), rgba(48, 38, 20, 0.95));
  box-shadow: 0 0 14px rgba(232, 194, 106, 0.3);
}
.option.picked {
  color: #22180a;
  background: linear-gradient(180deg, #f6dc94, #d7a84e 55%, #b4852f);
  border-color: #f8e3a8;
}
.option.dim {
  opacity: 0.35;
}
.num {
  flex: none;
  width: 28px;
  height: 28px;
  display: grid;
  place-items: center;
  border-radius: 50%;
  font-family: var(--display);
  font-size: 14px;
  font-weight: 900;
  color: #22180a;
  background: linear-gradient(180deg, #f6dc94, #c9963e);
}
.text {
  flex: 1;
  min-width: 0;
}
@keyframes rise {
  from {
    opacity: 0;
    transform: translateY(16px);
  }
}
@media (max-width: 600px) {
  .prompt {
    font-size: 16px;
  }
  .option {
    min-height: 46px;
    font-size: 14px;
  }
}
@media (prefers-reduced-motion: reduce) {
  .choice {
    animation: none;
  }
}
</style>
