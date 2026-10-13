#!/usr/bin/env bash
# upload_cdn.sh — WebGL/iOS asset pipeline (Lab 09 §9.3).
# Stages: brotli-compress content → upload to R2 (binding CDN, bucket
# caocao-cdn) → write the manifest the WebGL client fetches at boot.
#
# Requires: wrangler login (owner) — until then this script is a no-op with
# --check. Unity AssetBundle build step lands with the editor milestone;
# today's pipeline handles the flat content we already have.
#
# Usage: upload_cdn.sh <content-dir> [--check]
set -euo pipefail
SRC="${1:?usage: upload_cdn.sh <content-dir> [--check]}"
CHECK="${2:-}"
WRANGLER="${WRANGLER:-$HOME/.local/share/fnm/node-versions/v26.2.0/installation/bin/npx}"

command -v brotli >/dev/null 2>&1 || { echo "apt install brotli (or apt-get --user equivalent)"; exit 1; }

TMP=$(mktemp -d)
trap 'rm -rf "$TMP"' EXIT
MANIFEST="$TMP/manifest.json"
echo "{" > "$MANIFEST"
FIRST=1

find "$SRC" -type f | while read -r f; do
  rel="${f#"$SRC"/}"
  [ "$FIRST" = "0" ] && echo "," >> "$MANIFEST"; FIRST=0
  if [[ "$CHECK" == "--check" ]]; then
    echo "  would upload: $rel (bro: $(brotli -c "$f" | wc -c) bytes)"
  else
    b="$TMP/$(basename "$rel").br"
    brotli -f -q 9 -o "$b" "$f"
    $WRANGLER r2 object put "caocao-cdn/$rel.br" --file "$b" --remote
    size=$(wc -c < "$b")
  fi
  echo "  \"$rel\": {\"encoding\":\"br\",\"size\":$size}" >> "$MANIFEST"
done
echo "}" >> "$MANIFEST"

if [[ "$CHECK" != "--check" ]]; then
  $WRANGLER r2 object put "caocao-cdn/manifest.json" --file "$MANIFEST" --remote
  echo "manifest uploaded — client fetches /cdn/manifest.json at boot"
fi
