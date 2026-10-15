import { svelte } from "@sveltejs/vite-plugin-svelte";
import { defineConfig } from "vite";

export default defineConfig({
  // Relative asset URLs, so the build works from any path (static hosts, previews).
  base: "./",
  plugins: [svelte()],
  build: { target: "es2023", chunkSizeWarningLimit: 1200 },
});
