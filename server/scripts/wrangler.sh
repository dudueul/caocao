#!/usr/bin/env bash
# wrangler.sh — run the project's wrangler under real Node.js.
# `bun run` falls back to the Bun runtime when `node` is not on PATH; wrangler
# does not support it (dev reports Ready but every request hangs). Resolve a
# real Node instead: $NODE, PATH, then the fnm / nvm / volta default installs.
#
# Usage: wrangler.sh <wrangler args...>
set -euo pipefail
cd "$(dirname "$0")/.."

is_node() { [ -x "$1" ] && "$1" -e 'process.exit(process.versions.bun ? 1 : 0)' 2>/dev/null; }

for c in "${NODE:-}" "$(command -v node || true)" \
         "$HOME/.local/share/fnm/aliases/default/bin/node" \
         "$(ls -d "$HOME"/.nvm/versions/node/*/bin/node 2>/dev/null | sort -V | tail -1)" \
         "$HOME/.volta/bin/node"; do
  if [ -n "$c" ] && is_node "$c"; then
    exec "$c" node_modules/wrangler/bin/wrangler.js "$@"
  fi
done

echo "wrangler.sh: Node.js not found (wrangler cannot run on Bun) — install Node or set NODE=/path/to/node" >&2
exit 1
