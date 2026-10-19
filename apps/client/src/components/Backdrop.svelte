<script lang="ts">
import type { Backdrop } from "@tk/content";

/**
 * A painted scene behind the dialogue (ADR 0049): sky, far hills, a middle
 * layer for the place, mist and light, all drawn here as SVG and CSS.
 */
// `calm` drops the smoke trails (the title screen).
let { kind, calm = false }: { kind: Backdrop; calm?: boolean } = $props();

interface Look {
  sky: [string, string, string];
  far: string;
  near: string;
  glow: string;
  mist: string;
}

const LOOKS: Record<Backdrop, Look> = {
  court: {
    sky: ["#2a1410", "#5a2a1a", "#8a4a2a"],
    far: "#3a1c14",
    near: "#1e0e0a",
    glow: "rgba(255,190,110,0.35)",
    mist: "rgba(255,200,150,0.08)",
  },
  camp: {
    sky: ["#1c2436", "#5a4a5a", "#c88a5a"],
    far: "#3a3440",
    near: "#1a161c",
    glow: "rgba(255,170,90,0.3)",
    mist: "rgba(240,210,190,0.12)",
  },
  river: {
    sky: ["#2a3a4e", "#7a8a9a", "#e0c08a"],
    far: "#4a5868",
    near: "#2a3440",
    glow: "rgba(255,220,150,0.28)",
    mist: "rgba(230,230,220,0.18)",
  },
  night: {
    sky: ["#05070e", "#101a30", "#24304e"],
    far: "#141c30",
    near: "#080b14",
    glow: "rgba(150,180,255,0.18)",
    mist: "rgba(160,180,220,0.08)",
  },
  field: {
    sky: ["#3a5a7a", "#8ab0c8", "#e8dcb0"],
    far: "#5a7064",
    near: "#34402e",
    glow: "rgba(255,240,190,0.3)",
    mist: "rgba(240,240,230,0.16)",
  },
  fire: {
    sky: ["#140806", "#4a1408", "#c0461a"],
    far: "#2a0e08",
    near: "#120604",
    glow: "rgba(255,120,40,0.45)",
    mist: "rgba(80,40,30,0.3)",
  },
  dawn: {
    sky: ["#2a2a4a", "#a8708a", "#f4c890"],
    far: "#6a5a70",
    near: "#3a3040",
    glow: "rgba(255,210,150,0.4)",
    mist: "rgba(255,230,220,0.2)",
  },
};

const look = $derived(LOOKS[kind]);
/** Tent positions and sizes for the camp scenes. */
const TENTS: [number, number][] = [
  [150, 1],
  [420, 1.3],
  [760, 1.1],
  [1030, 0.9],
];
</script>

<div
  class="bd {kind}"
  style:--s0={look.sky[0]}
  style:--s1={look.sky[1]}
  style:--s2={look.sky[2]}
  style:--glow={look.glow}
  style:--mist={look.mist}
  aria-hidden="true"
>
  <div class="sky"></div>
  {#if kind === "night"}
    <div class="moon"></div>
    <div class="stars"></div>
  {:else if kind !== "court"}
    <div class="sun"></div>
  {/if}
  <svg class="layer far" aria-hidden="true" viewBox="0 0 1200 400" preserveAspectRatio="none">
    <path
      d="M0 260 L90 200 L170 235 L260 150 L340 210 L430 170 L520 230 L610 140 L700 205 L790 160 L880 220 L980 150 L1080 210 L1200 170 L1200 400 L0 400 Z"
      fill={look.far}
    />
  </svg>
  <svg
    class="layer mid"
    aria-hidden="true"
    viewBox="0 0 1200 400"
    preserveAspectRatio="xMidYMax slice"
  >
    {#if kind === "court"}
      <!-- pillars and a lacquered hall -->
      {#each [80, 300, 900, 1120] as x (x)}
        <rect x={x - 26} y="40" width="52" height="360" fill="#5a1a12" />
        <rect x={x - 34} y="30" width="68" height="20" fill="#2a0c08" />
      {/each}
      <rect x="0" y="0" width="1200" height="40" fill="#2a0c08" />
      <path d="M380 140 L600 90 L820 140 L820 400 L380 400 Z" fill="#3a140e" />
      <rect x="470" y="200" width="260" height="200" fill="#1a0806" />
      {#each [220, 980] as x (x)}
        <ellipse cx={x} cy="120" rx="26" ry="34" fill="#e8743a" opacity="0.85" />
      {/each}
    {:else if kind === "camp" || kind === "fire"}
      <!-- tents, a palisade and banners -->
      <path d="M0 330 L1200 330 L1200 400 L0 400 Z" fill={look.near} />
      {#each TENTS as [x, k] (x)}
        <path
          d="M{x - 110 * k} 332 L{x} {332 - 120 * k} L{x + 110 * k} 332 Z"
          fill={kind === "fire" ? "#2a0a06" : "#2a2228"}
        />
        <line
          x1={x}
          y1={332 - 120 * k}
          x2={x}
          y2={332 - 165 * k}
          stroke="#141014"
          stroke-width="5"
        />
        <path
          d="M{x} {332 - 165 * k} L{x + 46 * k} {332 - 152 * k} L{x} {332 - 138 * k} Z"
          fill={kind === "fire" ? "#a83a1a" : "#2e4a78"}
        />
      {/each}
      {#each Array.from({ length: 40 }, (_, i) => i) as i (i)}
        <rect x={i * 30} y="300" width="12" height="40" fill={look.near} />
      {/each}
    {:else if kind === "river"}
      <!-- the Yellow River and reeds -->
      <path d="M0 250 Q600 220 1200 250 L1200 400 L0 400 Z" fill="#6a6450" opacity="0.9" />
      <path d="M0 270 Q600 245 1200 272 L1200 300 Q600 280 0 300 Z" fill="#c8b48a" opacity="0.35" />
      <path d="M0 330 L1200 330 L1200 400 L0 400 Z" fill={look.near} />
      {#each Array.from({ length: 60 }, (_, i) => i) as i (i)}
        <line
          x1={i * 20 + 5}
          y1="335"
          x2={i * 20 + 5 + ((i * 7) % 11) - 5}
          y2={290 + ((i * 13) % 30)}
          stroke={look.near}
          stroke-width="3"
        />
      {/each}
    {:else if kind === "night"}
      <!-- a dark road, torches, riders' banners -->
      <path d="M0 320 Q600 300 1200 330 L1200 400 L0 400 Z" fill={look.near} />
      {#each [200, 380, 560, 740, 920] as x, i (x)}
        <line
          x1={x}
          y1={330 - (i % 2) * 6}
          x2={x}
          y2={250 - (i % 2) * 6}
          stroke="#0a0c12"
          stroke-width="5"
        />
        <circle cx={x} cy={246 - (i % 2) * 6} r="9" fill="#ffb050" class="torch" />
      {/each}
    {:else if kind === "field"}
      <!-- a walled town on the plain -->
      <path d="M700 250 L1150 250 L1150 320 L700 320 Z" fill="#5a5448" />
      {#each Array.from({ length: 16 }, (_, i) => i) as i (i)}
        <rect x={700 + i * 28} y="240" width="16" height="12" fill="#5a5448" />
      {/each}
      <path d="M880 250 L920 205 L960 250 Z" fill="#3a342c" />
      <path d="M0 315 Q600 290 1200 320 L1200 400 L0 400 Z" fill={look.near} />
    {:else}
      <!-- dawn: smoke trails over the abandoned camp -->
      <path d="M0 310 Q600 280 1200 315 L1200 400 L0 400 Z" fill={look.near} />
      {#each calm ? [] : [260, 640, 980] as x (x)}
        <path
          d="M{x} 300 q-30 -60 0 -110 q30 -50 -10 -110"
          stroke="rgba(60,50,60,0.45)"
          stroke-width="18"
          fill="none"
        />
      {/each}
    {/if}
  </svg>
  {#if kind === "fire"}
    <div class="embers"></div>
  {/if}
  <div class="mist"></div>
  <div class="vignette"></div>
</div>

<style>
.bd {
  position: absolute;
  inset: 0;
  overflow: hidden;
  background: var(--s0);
}
.sky {
  position: absolute;
  inset: 0;
  background: linear-gradient(180deg, var(--s0) 0%, var(--s1) 55%, var(--s2) 100%);
}
.sun {
  position: absolute;
  left: 62%;
  top: 34%;
  width: 22vmin;
  height: 22vmin;
  border-radius: 50%;
  background: radial-gradient(circle, var(--glow) 0%, transparent 70%);
  filter: blur(4px);
  animation: breathe 9s ease-in-out infinite;
}
.moon {
  position: absolute;
  left: 70%;
  top: 14%;
  width: 9vmin;
  height: 9vmin;
  border-radius: 50%;
  background: radial-gradient(circle at 40% 40%, #f4f0e0, #b8b4a8);
  box-shadow: 0 0 40px rgba(220, 230, 255, 0.35);
}
.stars {
  position: absolute;
  inset: 0 0 50% 0;
  background-image:
    radial-gradient(1px 1px at 12% 20%, #fff, transparent),
    radial-gradient(1px 1px at 33% 12%, #dde, transparent),
    radial-gradient(1px 1px at 48% 30%, #fff, transparent),
    radial-gradient(1px 1px at 81% 8%, #eef, transparent),
    radial-gradient(1px 1px at 91% 26%, #fff, transparent),
    radial-gradient(1px 1px at 22% 40%, #ccd, transparent);
  opacity: 0.8;
}
.layer {
  position: absolute;
  left: -4%;
  width: 108%;
  bottom: 0;
}
.far {
  height: 62%;
  opacity: 0.85;
  animation: drift 40s ease-in-out infinite alternate;
}
.mid {
  height: 70%;
}
.torch {
  filter: drop-shadow(0 0 10px #ff9a30);
  animation: flicker 0.8s ease-in-out infinite alternate;
}
.mist {
  position: absolute;
  inset: 40% -20% 0 -20%;
  background: radial-gradient(ellipse at 50% 70%, var(--mist), transparent 60%);
  animation: drift 22s ease-in-out infinite alternate-reverse;
}
.embers {
  position: absolute;
  inset: 0;
  background-image:
    radial-gradient(2px 2px at 20% 80%, #ffb050, transparent),
    radial-gradient(2px 2px at 40% 70%, #ff8030, transparent),
    radial-gradient(2px 2px at 65% 85%, #ffd070, transparent),
    radial-gradient(2px 2px at 85% 75%, #ff9040, transparent);
  animation: rise 6s linear infinite;
}
.vignette {
  position: absolute;
  inset: 0;
  background: radial-gradient(ellipse at 50% 45%, transparent 50%, rgba(0, 0, 0, 0.55) 100%);
}
@keyframes drift {
  to {
    transform: translateX(-2.5%);
  }
}
@keyframes breathe {
  50% {
    transform: scale(1.08);
    opacity: 0.85;
  }
}
@keyframes flicker {
  to {
    opacity: 0.7;
    transform: scale(0.9);
  }
}
@keyframes rise {
  from {
    transform: translateY(10%);
    opacity: 0.9;
  }
  to {
    transform: translateY(-40%);
    opacity: 0;
  }
}
@media (prefers-reduced-motion: reduce) {
  .far,
  .mist,
  .sun,
  .torch,
  .embers {
    animation: none;
  }
}
</style>
