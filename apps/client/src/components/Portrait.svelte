<script lang="ts">
import { classic } from "../lib/skin.svelte.ts";

/** An officer's portrait from the local classic skin (ADR 0042); nothing without it. */
let { id, size = 48 }: { id: string; size?: number } = $props();
const src = $derived(classic.skin?.faceUrl(id) ?? null);
</script>

{#if src}
  <img class="face" {src} alt="" width={size} height={Math.round(size * 1.25)} draggable="false">
{/if}

<style>
.face {
  flex: none;
  border: 1px solid var(--line);
  border-radius: 4px;
  background: #000;
  object-fit: cover;
}
</style>
