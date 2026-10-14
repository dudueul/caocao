#!/usr/bin/env bash
# build.sh — regenerate data/tables/ (schemas, load order, localization alignment, templates).
# Inputs are already in the repo: extracted/il2cpp (dump.cs, script.json), the APK's
# libil2cpp.so, client Resources/Localization_0.txt and the curated data/tables/anchors.json.
set -euo pipefail
cd "$(dirname "$0")/../.."
W=$(mktemp -d)
trap 'rm -rf "$W"' EXIT
python3 scripts/tables/extract_schema.py extracted/il2cpp/dump.cs "$W/schema.json"
python3 scripts/tables/load_order.py extracted/apk/lib/arm64-v8a/libil2cpp.so \
  extracted/il2cpp/script.json "$W/schema.json" "$W/load_order.json"
cp data/tables/anchors.json "$W/anchors.json"
python3 scripts/tables/align_localization.py "$W/schema.json" "$W/load_order.json" \
  "$W/anchors.json" client/Assets/Resources/Localization_0.txt "$W/alignment.json"
cp "$W/load_order.json" data/tables/load_order.json
rm -rf data/tables/templates
python3 scripts/tables/gen_templates.py --work "$W" \
  --loc client/Assets/Resources/Localization_0.txt --out data/tables
