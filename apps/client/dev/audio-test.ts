// Dev page script for /dev/audio-test.html.
import {
  audio,
  audioStats,
  DEFAULT_VOLUMES,
  SFX_NAMES,
  type Sfx,
  TRACKS,
  type Track,
  type Volumes,
} from "../src/lib/audio/index.ts";
import {
  dbfs,
  FULL,
  type Level,
  measure,
  renderScene,
  renderSfx,
  renderSong,
  renderTrack,
  songReport,
} from "../src/lib/audio/offline.ts";
import { SONGS } from "../src/lib/audio/songs.ts";

const $ = (id: string): HTMLElement => {
  const el = document.getElementById(id);
  if (!el) throw new Error(`missing #${id}`);
  return el;
};

const button = (parent: HTMLElement, text: string, onClick: () => void): HTMLButtonElement => {
  const b = document.createElement("button");
  b.type = "button";
  b.textContent = text;
  b.addEventListener("click", onClick);
  parent.appendChild(b);
  return b;
};

for (const ev of ["pointerdown", "keydown"] as const) {
  addEventListener(ev, () => audio.unlock());
}

// --- Mix sliders -------------------------------------------------------------

const opts = { pan: 0, gain: 1, pitch: 0 };
const sliders = $("sliders");
const slider = (name: string, min: number, max: number, step: number, value: number) => {
  const label = document.createElement("label");
  const input = document.createElement("input");
  const out = document.createElement("span");
  Object.assign(input, { type: "range", min, max, step, value: String(value) });
  out.textContent = String(value);
  label.append(name, input, out);
  sliders.appendChild(label);
  return (onInput: (v: number) => void) =>
    input.addEventListener("input", () => {
      out.textContent = input.value;
      onInput(Number(input.value));
    });
};
for (const key of ["master", "music", "sfx"] as const) {
  slider(
    key,
    0,
    100,
    1,
    DEFAULT_VOLUMES[key],
  )((v) => {
    const next: Volumes = { ...audio.volumes, [key]: v };
    audio.setVolumes(next);
  });
}
slider(
  "pan",
  -1,
  1,
  0.1,
  0,
)((v) => {
  opts.pan = v;
});
slider(
  "gain",
  0,
  1,
  0.05,
  1,
)((v) => {
  opts.gain = v;
});
slider(
  "pitch",
  -12,
  12,
  1,
  0,
)((v) => {
  opts.pitch = v;
});

// --- Music and effects -------------------------------------------------------

const trackButtons = new Map<Track | null, HTMLButtonElement>();
const pickTrack = (t: Track | null) => {
  audio.unlock();
  audio.music(t);
  for (const [k, b] of trackButtons) b.classList.toggle("on", k === t && t !== null);
};
for (const t of TRACKS)
  trackButtons.set(
    t,
    button($("tracks"), t, () => pickTrack(t)),
  );
trackButtons.set(
  null,
  button($("tracks"), "stop", () => pickTrack(null)),
);

for (const name of SFX_NAMES) {
  button($("effects"), name, () => {
    audio.unlock();
    audio.play(name, opts);
  });
}

const stress = $("stress");
button(stress, "6 hits in a burst", () => {
  for (let i = 0; i < 6; i++) audio.play("hit", { pan: (i - 2.5) / 3 });
});
button(stress, "hit ×6 over 300 ms", () => {
  for (let i = 0; i < 6; i++) setTimeout(() => audio.play("hit"), i * 50);
});
button(stress, "5 at once", () => {
  for (const s of ["hit", "crit", "fire", "arrow", "turn"] as const) audio.play(s);
});
button(stress, "40 at once (voice cap)", () => {
  for (const s of SFX_NAMES.slice(0, 40)) audio.play(s);
});

setInterval(() => {
  const s = audioStats();
  $("status").textContent =
    `context: ${s.state} · voices: ${s.voices} · track: ${s.track ?? "none"} · fading: ${s.fading}`;
}, 250);

// --- Offline report ------------------------------------------------------------

interface Row extends Level {
  name: string;
  kind: "sfx" | "track" | "scene";
  dryEnd?: number;
  buf?: AudioBuffer;
}

const LONG: readonly Sfx[] = ["victory", "loss", "promote"];

async function report(volumes: Volumes = FULL, keep = false): Promise<Row[]> {
  const rows: Row[] = [];
  for (const name of SFX_NAMES) {
    const buf = await renderSfx(name, 4.5, { volumes });
    // Length is measured dry (no reverb tail) at full volume.
    const dry = measure(await renderSfx(name, 4.5, { volumes: FULL, reverb: false }));
    rows.push({
      name,
      kind: "sfx",
      ...measure(buf),
      dryEnd: dry.end - 0.02,
      ...(keep ? { buf } : {}),
    });
  }
  for (const t of TRACKS) {
    const buf = await renderTrack(t, 12, { volumes });
    rows.push({ name: t, kind: "track", ...measure(buf, 0.001, true), ...(keep ? { buf } : {}) });
  }
  const five = ["hit", "crit", "fire", "arrow", "turn"] as const;
  for (const t of ["battle", "danger"] as const) {
    const buf = await renderScene(t, five, 3, 6, { volumes });
    rows.push({ name: `${t}+5`, kind: "scene", ...measure(buf, 0.001, true) });
  }
  return rows;
}

const fmt = (x: number) => (Number.isFinite(x) ? x.toFixed(1) : "-inf");

function draw(buf: AudioBuffer, peak: number): HTMLCanvasElement {
  const c = document.createElement("canvas");
  c.width = 220;
  c.height = 26;
  const g = c.getContext("2d");
  if (!g) return c;
  const d = buf.getChannelData(0);
  const step = Math.max(1, Math.floor(d.length / c.width));
  g.fillStyle = "#d8b46a";
  for (let x = 0; x < c.width; x++) {
    let m = 0;
    for (let i = x * step; i < (x + 1) * step && i < d.length; i++)
      m = Math.max(m, Math.abs(d[i] ?? 0));
    const h = Math.max(1, (m / Math.max(peak, 1e-6)) * 12);
    g.fillRect(x, 13 - h, 1, h * 2);
  }
  return c;
}

function table(rows: Row[]): HTMLTableElement {
  const t = document.createElement("table");
  t.innerHTML =
    "<tr><th>sound</th><th>peak dBFS</th><th>RMS dBFS</th><th>length ms</th><th>NaN</th><th></th></tr>";
  for (const r of rows) {
    const tr = document.createElement("tr");
    const len = r.kind === "sfx" ? Math.round((r.dryEnd ?? r.end) * 1000) : null;
    const lenBad =
      len !== null && (len < 50 || (len > 1500 && !LONG.includes(r.name as Sfx)) || len > 4000);
    const cells = [
      r.name,
      fmt(dbfs(r.peak)),
      fmt(dbfs(r.rms)),
      len === null ? "" : String(len),
      String(r.bad),
    ];
    cells.forEach((text, i) => {
      const td = document.createElement("td");
      td.textContent = text;
      if ((i === 1 && r.peak > 0.95) || (i === 3 && lenBad) || (i === 4 && r.bad > 0)) {
        td.className = "warn";
      }
      tr.appendChild(td);
    });
    const td = document.createElement("td");
    if (r.buf) td.appendChild(draw(r.buf, r.peak));
    tr.appendChild(td);
    t.appendChild(tr);
  }
  return t;
}

$("report").addEventListener("click", async () => {
  const out = $("out");
  out.textContent = "rendering…";
  const defaults = ($("defaults") as HTMLInputElement).checked;
  const rows = await report(defaults ? DEFAULT_VOLUMES : FULL, true);
  out.textContent = "";
  const problems = songReport().filter((s) => s.problems.length > 0);
  if (problems.length > 0) out.append(`Song data problems: ${JSON.stringify(problems)}`);
  out.appendChild(table(rows));
});

/** For browser tests: the report as plain data, and the render helpers. */
declare global {
  interface Window {
    audioReport?: (defaults?: boolean) => Promise<object>;
    audioDev?: object;
  }
}
window.audioDev = {
  audio,
  audioStats,
  renderSfx,
  renderTrack,
  renderSong,
  renderScene,
  measure,
  SONGS,
  SFX_NAMES,
  TRACKS,
  DEFAULT_VOLUMES,
};
window.audioReport = async (defaults = false) => {
  const rows = await report(defaults ? DEFAULT_VOLUMES : FULL);
  return {
    songs: songReport(),
    rows: rows.map((r) => ({
      name: r.name,
      kind: r.kind,
      peak: +r.peak.toFixed(4),
      peakDb: +dbfs(r.peak).toFixed(1),
      rmsDb: +dbfs(r.rms).toFixed(1),
      lengthMs: r.kind === "sfx" ? Math.round((r.dryEnd ?? r.end) * 1000) : null,
      bad: r.bad,
    })),
  };
};
