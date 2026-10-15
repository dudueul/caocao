<script lang="ts">
import type { CutIn } from "../lib/session.svelte.ts";
import { classic } from "../lib/skin.svelte.ts";

/**
 * A skill cut-in: a slanted band with the caster's portrait (or a crest) and the
 * skill's name. `at` (0–1) comes from the field's clock, so the cut-in keeps
 * pace with the battle speed.
 */
let { cut, at }: { cut: CutIn; at: number } = $props();
const face = $derived(classic.skin?.faceUrl(cut.unit) ?? null);
const color = $derived(`#${cut.color.toString(16).padStart(6, "0")}`);
const dir = $derived(cut.mine ? 1 : -1);

const clamp = (k: number) => Math.max(0, Math.min(1, k));
const ease = (k: number) => 1 - (1 - clamp(k)) ** 3;
/** 0 → 1 between two moments of the cut-in. */
const span = (a: number, b: number) => ease((at - a) / (b - a));

const out = $derived(span(0.8, 1));
const band = $derived(
  `rotate(${-6 * dir}deg) translateX(${dir * (-110 * (1 - span(0, 0.25)) + 20 * out)}%)`,
);
const who = $derived({
  opacity: span(0, 0.28) * (1 - out),
  transform: `translateX(${dir * (-60 * (1 - span(0, 0.28)) + 20 * out)}px) scale(${1.15 - 0.15 * span(0, 0.28)})`,
});
const words = $derived({
  opacity: span(0.15, 0.35) * (1 - out),
  transform: `translateX(${dir * 30 * (1 - span(0.15, 0.35))}px)`,
});
</script>

<div class="cutin" class:enemy={!cut.mine} style:--school={color} aria-hidden="true">
  <div class="band" style:transform={band} style:opacity={1 - out}>
    <div class="shine" style:transform="translateX({-100 + 200 * clamp(at)}%)"></div>
  </div>
  <div class="who" style:opacity={who.opacity} style:transform={who.transform}>
    {#if face}
      <img src={face} alt="">
    {:else}
      <div class="crest">{cut.name.slice(0, 1)}</div>
    {/if}
  </div>
  <div class="words" style:opacity={words.opacity} style:transform={words.transform}>
    <div class="name">{cut.name}</div>
    <div class="skill">{cut.skill}</div>
  </div>
</div>

<style>
.cutin {
  position: absolute;
  inset: 0;
  z-index: 5;
  pointer-events: none;
  overflow: hidden;
}
.band {
  position: absolute;
  left: -10%;
  right: -10%;
  top: 38%;
  height: 132px;
  overflow: hidden;
  background: linear-gradient(
    90deg,
    rgba(0, 0, 0, 0) 0%,
    rgba(10, 8, 6, 0.86) 14%,
    color-mix(in srgb, var(--school) 38%, #120d08) 50%,
    rgba(10, 8, 6, 0.86) 86%,
    rgba(0, 0, 0, 0) 100%
  );
  border-top: 2px solid color-mix(in srgb, var(--school) 70%, white);
  border-bottom: 2px solid color-mix(in srgb, var(--school) 70%, white);
  box-shadow: 0 0 40px color-mix(in srgb, var(--school) 50%, transparent);
}
.shine {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    100deg,
    transparent 40%,
    rgba(255, 255, 255, 0.35) 50%,
    transparent 60%
  );
}
.who {
  position: absolute;
  top: calc(38% - 46px);
  left: 12%;
  width: 150px;
  height: 188px;
  filter: drop-shadow(0 6px 18px rgba(0, 0, 0, 0.7));
}
.enemy .who {
  left: auto;
  right: 12%;
}
.who img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 2px solid #f2d58a;
  border-radius: 10px;
}
.crest {
  width: 100%;
  height: 150px;
  display: grid;
  place-items: center;
  font-size: 88px;
  font-weight: 900;
  color: #fff;
  border-radius: 50%;
  background: radial-gradient(circle at 35% 30%, var(--school), #1a120a 75%);
  border: 3px solid #f2d58a;
  text-shadow: 0 3px 0 rgba(0, 0, 0, 0.5);
}
.words {
  position: absolute;
  top: calc(38% + 22px);
  left: calc(12% + 170px);
}
.enemy .words {
  left: auto;
  right: calc(12% + 170px);
  text-align: right;
}
.name {
  font-size: 15px;
  letter-spacing: 0.2em;
  color: #f2d58a;
}
.skill {
  font-size: 44px;
  font-weight: 900;
  letter-spacing: 0.08em;
  color: #fff;
  text-shadow:
    0 0 18px var(--school),
    0 2px 0 #000;
}
@media (max-width: 600px) {
  .band {
    height: 96px;
  }
  .who {
    width: 96px;
    height: 120px;
    top: calc(38% - 20px);
    left: 4%;
  }
  .crest {
    height: 96px;
    font-size: 56px;
  }
  .enemy .who {
    right: 4%;
  }
  .words {
    left: calc(4% + 110px);
    top: calc(38% + 18px);
  }
  .enemy .words {
    right: calc(4% + 110px);
  }
  .skill {
    font-size: 30px;
  }
}
</style>
