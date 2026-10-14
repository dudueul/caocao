<script lang="ts">
import type { Cell, TerrainId } from "@tk/rules";
import type { Session } from "../lib/session.svelte.ts";
import { T } from "../lib/text.ts";

let { session, cell }: { session: Session; cell: Cell } = $props();
const id = $derived(
  session.state.map.tiles[cell.y * session.state.map.width + cell.x] as TerrainId,
);
const terrain = $derived(session.rules.terrains[id]);
const who = $derived(session.unit ?? session.unitAt(cell));
const family = $derived(who ? session.rules.classes[who.classId]?.family : undefined);
const aff = $derived(family ? session.rules.affinity[family]?.[id] : undefined);
const burning = $derived(session.state.burning.some((b) => b.x === cell.x && b.y === cell.y));
</script>

<section class="tile">
  <strong>{terrain.name}</strong>
  <span class="muted">({cell.x}, {cell.y})</span>
  {#if terrain.cover > 0}
    <span class="chip">{T.cover} {terrain.cover / 10}%</span>
  {/if}
  {#if terrain.heal > 0}
    <span class="chip">{T.heal} {terrain.heal / 10}%</span>
  {/if}
  {#if burning}
    <span class="chip fire">불타는 중</span>
  {/if}
  {#if aff && who}
    <span class="chip">
      {session.rules.classes[who.classId]?.name}:
      {aff.move === null ? T.impassable : `${T.move} ${aff.move}`}
      · {T.effect} {aff.effect / 10}%
    </span>
  {/if}
</section>

<style>
.tile {
  display: flex;
  gap: 6px;
  flex-wrap: wrap;
  align-items: center;
  font-size: 12px;
}
.fire {
  border-color: #ff7a2f;
  color: #ffb27a;
}
</style>
