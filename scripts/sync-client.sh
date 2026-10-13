#!/usr/bin/env bash
# sync-client.sh — bidirectional repo ↔ editor-copy sync (L: drive).
#
# PROTOCOL (learned the hard way — see commit db2ebfb7):
#   1. PULL first (update mode): captures Unity 6 editor-owned changes —
#      API-updater conversions, re-serialized assets, resolved packages.
#   2. PUSH (delete mode): repo → editor copy, for agent-authored code.
#   NEVER push without pulling first — the editor owns conversion results;
#   a blind push reverts them and the two sides fight forever.
#
# Unity-generated state is NEVER deleted on the editor copy:
#   Library/, Temp/, Logs/, UserSettings/, obj/, Builds/, .vs/, *.csproj, *.sln
#
# Usage: scripts/sync-client.sh [editor-path]
#        scripts/sync-client.sh --pull-only [editor-path]
set -euo pipefail
REPO="${CAOCAO_CLIENT:-$HOME/work/caocao/client}"
DST="${2:-${1:-/mnt/l/Projects/caocao-client}}"
PULL_ONLY="${1:-}"

EXCLUDES=(--exclude 'Library/' --exclude 'Temp/' --exclude 'Logs/'
          --exclude 'UserSettings/' --exclude 'obj/' --exclude 'Builds/'
          --exclude '.vs/' --exclude '*.csproj' --exclude '*.sln' --exclude '.git/')

# 1. PULL (update mode: only files newer/edited on the editor side)
rsync -avu "${EXCLUDES[@]}" "$DST/" "$REPO/" | grep -vE '^$|/$' | head -20 || true
echo "── pulled editor-owned changes into repo (commit them!)"

# 2. PUSH unless --pull-only
if [ "$PULL_ONLY" != "--pull-only" ]; then
  rsync -a --delete "${EXCLUDES[@]}" "$REPO/" "$DST/"
  echo "── pushed repo → editor copy (post-pull: conversion-safe)"
fi
