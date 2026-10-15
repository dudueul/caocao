<script lang="ts">
import type { Cell } from "@tk/rules";
import type { Snippet } from "svelte";
import type { Session } from "../lib/session.svelte.ts";

/**
 * A panel that floats beside a map tile: right of it, else left, below or above,
 * whichever first fits the visible map area without covering the `avoid` tiles
 * (the units the panel is about).
 */
let {
  session,
  cell,
  avoid = [],
  width,
  height,
  children,
}: {
  session: Session;
  cell: Cell;
  avoid?: Cell[];
  width: number;
  height: number;
  children: Snippet;
} = $props();
let w = $state(0);
let h = $state(0);

const place = $derived.by(() => {
  const g = session.geometry;
  const i = session.insets;
  const t = g.tile;
  const left = g.ox + cell.x * t;
  const top = g.oy + cell.y * t;
  const minX = i.left + 6;
  const maxX = Math.max(minX, width - i.right - 6 - w);
  const minY = i.top + 6;
  const maxY = Math.max(minY, height - i.bottom - 6 - h);
  const midY = top + t / 2 - h / 2;
  const midX = left + t / 2 - w / 2;
  const clamp = (p: { x: number; y: number }) => ({
    x: Math.max(minX, Math.min(maxX, p.x)),
    y: Math.max(minY, Math.min(maxY, p.y)),
  });
  const candidates = [
    { x: left + t + 8, y: midY },
    { x: left - 8 - w, y: midY },
    { x: midX, y: top + t + 8 },
    { x: midX, y: top - 8 - h },
  ];
  const fits = (p: { x: number; y: number }) =>
    p.x >= minX && p.x <= maxX && p.y >= minY - 1 && p.y <= maxY + 1;
  const covers = (p: { x: number; y: number }) =>
    [cell, ...avoid].some((c) => {
      const cx = g.ox + c.x * t;
      const cy = g.oy + c.y * t;
      return cx < p.x + w && cx + t > p.x && cy < p.y + h && cy + t > p.y;
    });
  const tidy = candidates.map(clamp);
  return (
    tidy.find((p, k) => fits(candidates[k] ?? p) && !covers(p)) ??
    tidy.find((p) => !covers(p)) ??
    tidy[0] ?? { x: minX, y: minY }
  );
});
</script>

<div
  class="floating"
  style:left="{place.x}px"
  style:top="{place.y}px"
  style:visibility={w > 0 ? "visible" : "hidden"}
  bind:clientWidth={w}
  bind:clientHeight={h}
>
  {@render children()}
</div>

<style>
.floating {
  position: absolute;
  z-index: 3;
  max-width: min(340px, calc(100% - 12px));
  padding: 8px;
  background: rgba(20, 19, 16, 0.94);
  border: 1px solid var(--accent);
  border-radius: 8px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.55);
}
</style>
