<script lang="ts">
import { type Mood, portraitUrl } from "../lib/fx/portrait.ts";
import { classic } from "../lib/skin.svelte.ts";

/**
 * An officer's portrait: the local classic skin's face when there is one
 * (ADR 0042), otherwise our own painted bust in the class's dress and the
 * side's colours.
 */
let {
  id,
  classId,
  side,
  size = 48,
  mood,
}: {
  id: string;
  classId?: string | undefined;
  side?: number | undefined;
  size?: number;
  mood?: Mood | undefined;
} = $props();
const src = $derived(
  classic.skin?.faceUrl(id) ?? (portraitUrl({ id, classId, side, mood }) || null),
);
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
