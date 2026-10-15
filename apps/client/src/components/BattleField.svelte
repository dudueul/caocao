<script lang="ts">
import { onMount } from "svelte";
import { Field, type OverlayView } from "../lib/field.ts";
import type { Session } from "../lib/session.svelte.ts";
import { classic } from "../lib/skin.svelte.ts";
import { SCHOOL_COLOR } from "../lib/text.ts";

let { session }: { session: Session } = $props();
let host: HTMLDivElement;
let field: Field | undefined;
let ready = $state(false);

const view = $derived.by<OverlayView>(() => {
  const s = session;
  const aim = s.aim;
  const def = aim?.kind === "stratagem" ? s.rules.stratagems[aim.id] : undefined;
  const ready = s.myTurn
    ? s.state.units
        .filter((u) => u.side === s.humanSide && u.hp > 0 && !u.acted && u.id !== s.selected)
        .map((u) => ({ x: u.x, y: u.y }))
    : [];
  return {
    reach: s.reach.length > 0 ? s.reach : s.inspect.reach,
    attackable: s.to ? [] : s.reach.length > 0 ? s.attackable : s.inspect.strike,
    pending: s.to,
    aimCells: s.aimCells,
    aimColor: def ? SCHOOL_COLOR[def.school] : 0xff3b30,
    target: s.target,
    area: s.forecast?.kind === "stratagem" ? s.forecast.area : [],
    danger: s.danger,
    hover: s.hover,
    ready,
    ghost: s.selected && s.to ? { id: s.selected, to: s.to } : null,
  };
});

onMount(() => {
  const f = new Field(session);
  field = f;
  void f.mount(host).then(() => (ready = true));
  return () => f.destroy();
});

$effect(() => {
  const skin = classic.skin;
  if (ready && field) field.setSkin(skin);
});

$effect(() => {
  const v = view;
  const state = session.state;
  void session.insets;
  void session.backdrop;
  if (ready && field) field.render(state, v);
});
</script>

<div class="field" bind:this={host}></div>

<style>
.field {
  position: absolute;
  inset: 0;
}
</style>
