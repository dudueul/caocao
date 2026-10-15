import { svelte } from "@sveltejs/vite-plugin-svelte";
import { defineConfig } from "vite";

export default defineConfig({
  // Relative asset URLs, so the build works from any path (static hosts, previews).
  base: "./",
  plugins: [svelte()],
  build: { target: "es2023", chunkSizeWarningLimit: 1200 },
  // `bun run dev` talks to `bun run server` (wrangler dev) for rooms.
  server: { proxy: { "/api": { target: "http://127.0.0.1:8787", ws: true } } },
});
