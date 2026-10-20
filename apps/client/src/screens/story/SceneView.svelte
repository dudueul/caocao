<script lang="ts">
import type { SceneDef } from "@tk/content";
import { onMount, untrack } from "svelte";
import Backdrop from "../../components/Backdrop.svelte";
import ChoiceBox from "../../components/ChoiceBox.svelte";
import DialogueBox from "../../components/DialogueBox.svelte";
import { settings } from "../../lib/settings.svelte.ts";
import { speakerOf } from "../../lib/speakers.ts";
import { castSpecs, ScenePlayer } from "../../lib/stage/script.ts";
import { Stage } from "../../lib/stage/stage.ts";
import { story } from "../../lib/story.svelte.ts";

/**
 * A story scene (ADR 0049, 0063): a title card, then the scene acted out on
 * its stage by our figures, with the lines in the dialogue box and choices in
 * a choice box. 건너뛰기 runs ahead to the next choice or the end.
 */
const scene = $derived(story.scene);
const cue = $derived(story.cue);
const line = $derived(cue?.kind === "say" ? cue.line : null);
const speaker = $derived(line ? speakerOf(line, story.campaign) : null);
let auto = $state(false);
let card = $state(true);
let host: HTMLDivElement;
/** The stage once it is up (null without WebGL: the painted backdrop shows instead). */
let stage = $state.raw<Stage | null>(null);
let ready = $state(false);
/** The current scene's stage failed to build: play it over the backdrop. */
let broken = $state(false);
/**
 * A visit to a scene: the scene and the campaign it was entered with (a new
 * game or a load can enter the same scene again; it then plays again).
 */
interface Visit {
  id: string;
  campaign: object;
}
const visitNow = $derived(scene ? { id: scene.id, campaign: story.campaign } : null);
const sameVisit = (a: Visit | null, b: Visit | null) =>
  a !== null && b !== null && a.id === b.id && a.campaign === b.campaign;
let loaded = $state.raw<Visit | null>(null);
let playing: { visit: Visit; player: ScenePlayer } | null = null;
let cardTimer: ReturnType<typeof setTimeout> | undefined;

onMount(() => {
  const params = new URLSearchParams(location.search);
  const calm = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  const s = new Stage({ lite: params.has("lowfx") || calm || settings.lite, calm });
  if (params.has("debug")) (window as unknown as { tkStage: Stage }).tkStage = s;
  s.mount(host).then(
    () => {
      if (s.gone) return;
      stage = s;
      ready = true;
    },
    (e: unknown) => {
      // No WebGL: the painted backdrop and the dialogue carry the scene.
      console.error(e);
      ready = true;
    },
  );
  const onResize = () => s.setInsets(insets());
  window.addEventListener("resize", onResize);
  return () => {
    window.removeEventListener("resize", onResize);
    playing?.player.stop();
    playing = null;
    s.destroy();
  };
});

/**
 * The stage is framed above the dialogue box (and below the tools), so whoever
 * speaks is never hidden under it. On narrow screens the portrait stands on
 * top of the box, so the box reaches higher.
 */
function insets() {
  const w = window.innerWidth;
  const h = window.innerHeight;
  return { top: h < 500 ? 36 : 44, right: 0, bottom: w <= 600 ? 230 : 152, left: 0 };
}

// A new scene: its title card, and its stage set up behind the card.
$effect(() => {
  void visitNow;
  card = true;
  clearTimeout(cardTimer);
  cardTimer = setTimeout(() => (card = false), 2200);
  return () => clearTimeout(cardTimer);
});

$effect(() => {
  const sc = scene;
  const v = visitNow;
  if (!sc || !v || !ready || sameVisit(loaded, v)) return;
  untrack(() => setUp(sc, v));
});

// Once the card is gone, the scene plays.
$effect(() => {
  const sc = scene;
  const v = visitNow;
  if (!sc || !v || !ready || card || !sameVisit(loaded, v)) return;
  if (sameVisit(playing?.visit ?? null, v)) return;
  untrack(() => play(sc, v));
});

function setUp(sc: SceneDef, v: Visit) {
  playing?.player.stop();
  playing = null;
  story.beginScene();
  broken = false;
  if (stage) {
    try {
      stage.setInsets(insets());
      stage.load(sc.stage, castSpecs(sc.cast, story.campaign.flags, story.campaign));
    } catch (e) {
      // A stage that cannot be built leaves the painted backdrop and the lines.
      console.error(e);
      broken = true;
    }
  }
  loaded = v;
}

function play(sc: SceneDef, v: Visit) {
  const alive = () =>
    story.scene?.id === sc.id && story.campaign === v.campaign && playing?.player === player;
  const player = new ScenePlayer(
    sc,
    { ...story.campaign.flags },
    broken ? null : stage,
    {
      say: async (l) => {
        await story.ask({ kind: "say", line: l });
      },
      choose: async (c) => {
        const v = await story.ask({ kind: "choice", ...c });
        return v ?? c.options[0]?.value ?? "";
      },
    },
    alive,
  );
  playing = { visit: v, player };
  void player.run().then((done) => {
    if (done && alive()) story.endScene();
  });
}

function skip() {
  if (story.cue?.kind === "choice") return;
  if (playing) {
    playing.player.skip();
    story.next();
  } else story.endScene();
}
</script>

<main class="scene">
  {#if scene && (!stage || broken)}
    <Backdrop kind={scene.backdrop} />
  {/if}
  <div class="stage" class:hidden={broken} bind:this={host}></div>
  {#if scene}
    {#if card}
      <button type="button" class="card" onclick={() => (card = false)} aria-label="계속">
        <span class="when">{scene.when} · {scene.place}</span>
        <span class="title">{scene.title}</span>
      </button>
    {:else}
      <div class="tools">
        <button type="button" class:on={auto} aria-pressed={auto} onclick={() => (auto = !auto)}>
          자동
        </button>
        <button type="button" onclick={skip} disabled={cue?.kind === "choice"}>건너뛰기</button>
        <a class="button" href="#/" aria-label="타이틀로">☰</a>
      </div>
      <div class="where">{scene.place} · {scene.when}</div>
      {#if cue?.kind === "say" && line}
        <div class="dialogue">
          {#key cue.n}
            <DialogueBox {line} {speaker} {auto} onnext={() => story.next()} />
          {/key}
        </div>
      {:else if cue?.kind === "choice"}
        <div class="dialogue">
          {#key cue.n}
            <ChoiceBox prompt={cue.prompt} options={cue.options} onpick={(v) => story.choose(v)} />
          {/key}
        </div>
      {/if}
    {/if}
  {/if}
</main>

<style>
.scene {
  position: fixed;
  inset: 0;
  overflow: hidden;
}
.stage {
  position: absolute;
  inset: 0;
}
.stage.hidden {
  visibility: hidden;
}
.card {
  position: absolute;
  inset: 0;
  display: grid;
  place-content: center;
  gap: 14px;
  text-align: center;
  background: radial-gradient(ellipse at center, rgba(0, 0, 0, 0.45), rgba(0, 0, 0, 0.82));
  border: none;
  border-radius: 0;
  box-shadow: none;
  animation: fade 2.2s ease-in-out both;
}
.card:hover:not(:disabled) {
  box-shadow: none;
}
.when {
  font-size: 14px;
  letter-spacing: 0.2em;
  color: #e8d8b0;
}
.title {
  font-family: var(--display);
  font-size: clamp(30px, 6vw, 54px);
  font-weight: 900;
  letter-spacing: 0.18em;
  background: linear-gradient(180deg, #fff3c4, #e8c26a 60%, #a8792a);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  filter: drop-shadow(0 3px 10px rgba(0, 0, 0, 0.8));
}
.tools {
  position: absolute;
  top: max(8px, env(safe-area-inset-top, 0px));
  right: max(10px, env(safe-area-inset-right, 0px));
  display: flex;
  gap: 8px;
  z-index: 2;
}
.tools .on {
  border-color: var(--accent);
  color: var(--accent);
}
.where {
  position: absolute;
  top: max(14px, env(safe-area-inset-top, 0px));
  left: max(16px, env(safe-area-inset-left, 0px));
  max-width: calc(100% - 240px);
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  font-family: var(--display);
  font-size: 14px;
  letter-spacing: 0.12em;
  color: rgba(255, 240, 210, 0.9);
  text-shadow:
    0 1px 4px #000,
    0 0 10px #000;
  pointer-events: none;
}
.dialogue {
  position: absolute;
  left: 0;
  right: 0;
  bottom: max(14px, env(safe-area-inset-bottom, 0px));
  z-index: 3;
}
@keyframes fade {
  0% {
    opacity: 0;
  }
  15%,
  80% {
    opacity: 1;
  }
  100% {
    opacity: 0.9;
  }
}
@media (prefers-reduced-motion: reduce) {
  .card {
    animation: none;
  }
}
</style>
