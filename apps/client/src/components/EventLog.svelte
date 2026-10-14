<script lang="ts">
import type { LogLine } from "../lib/text.ts";

let { lines }: { lines: LogLine[] } = $props();
let box: HTMLDivElement;

$effect(() => {
  void lines.length;
  box.scrollTop = box.scrollHeight;
});
</script>

<div class="log" bind:this={box} aria-live="polite">
  {#each lines as line (line.id)}
    <div class={line.tone}>{line.text}</div>
  {/each}
</div>

<style>
.log {
  overflow-y: auto;
  font-size: 12px;
  min-height: 60px;
  flex: 1;
  padding-right: 4px;
}
.good {
  color: var(--good);
}
.bad {
  color: var(--bad);
}
.twist {
  color: var(--twist);
  font-weight: 700;
}
.turn {
  color: var(--accent);
  margin-top: 4px;
  border-top: 1px solid var(--line);
  padding-top: 2px;
}
</style>
