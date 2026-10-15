import { mount } from "svelte";
import App from "./App.svelte";
import "./app.css";
import { loadClassicSkin } from "./lib/skin.svelte.ts";

// The owner's local classic skin, if exported (dev server only, ADR 0042).
void loadClassicSkin();

const target = document.getElementById("app");
if (!target) throw new Error("#app is missing");
export default mount(App, { target });

// Installable PWA (ADR 0005). Some hosts (previews, sandboxed frames) refuse
// service workers; the app works the same without one.
if (import.meta.env.PROD && "serviceWorker" in navigator) {
  navigator.serviceWorker.register("./sw.js").catch(() => {});
}
