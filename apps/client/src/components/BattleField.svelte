<script lang="ts">
import { onMount } from "svelte";
import { Field, type OverlayView } from "../lib/field.ts";
import { spriteDebug } from "../lib/fx/sprite/index.ts";
import type { Session } from "../lib/session.svelte.ts";
import { settings } from "../lib/settings.svelte.ts";
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
    aimColor: def ? SCHOOL_COLOR[def.school] : aim?.kind === "duel" ? 0xe8c26a : 0xff3b30,
    target: s.target,
    area: s.forecast?.kind === "stratagem" ? s.forecast.area : [],
    danger: s.danger,
    hover: s.hover,
    ready,
    ghost: s.selected && s.to ? { id: s.selected, to: s.to } : null,
    selected: s.to ?? (s.unit && s.unit.hp > 0 ? { x: s.unit.x, y: s.unit.y } : null),
    goals: [
      ...s.state.goals.flatMap((g) =>
        g.side === s.humanSide && g.when.kind === "reach" ? g.when.cells : [],
      ),
      // Tiles that set off a map change for us: the sluice at 하비 (ADR 0062).
      ...s.state.shifts.flatMap((sh) =>
        sh.when.kind === "reach" && sh.when.side === s.humanSide ? sh.when.cells : [],
      ),
      ...(s.story?.marks ?? []),
    ],
  };
});

onMount(() => {
  const params = new URLSearchParams(location.search);
  const calm = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  const f = new Field(session, {
    lite: params.has("lowfx") || calm || settings.lite,
    calm,
  });
  if (params.has("debug"))
    Object.assign(window as unknown as Record<string, unknown>, {
      tkField: f,
      // Paint time and sheet memory of the figures (ADR 0073).
      tkSprites: spriteDebug,
    });
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
