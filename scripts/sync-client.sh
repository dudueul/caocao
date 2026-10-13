#!/usr/bin/env bash
# sync-client.sh — repo → editor copy (L: drive).
# IMPORTANT: this is DESTRUCTIVE toward the repo's view of Assets/ only.
# Unity-generated state on the editor copy is NEVER deleted:
#   Library/, Temp/, Logs/, UserSettings/, obj/, Builds/, .vs/, *.csproj, *.sln
# (Lesson from fix-loop crash: rsync --delete without these excludes wiped
# Unity's UserSettings/ and the editor crashed on next start.)
set -euo pipefail
SRC="${CAOCAO_CLIENT:-$HOME/work/caocao/client}"
DST="${1:-/mnt/l/Projects/caocao-client}"
rsync -a --delete \
  --exclude 'Library/' --exclude 'Temp/' --exclude 'Logs/' \
  --exclude 'UserSettings/' --exclude 'obj/' --exclude 'Builds/' \
  --exclude '.vs/' --exclude '*.csproj' --exclude '*.sln' \
  --exclude '.git/' \
  "$SRC/" "$DST/"
echo "synced $SRC → $DST (Unity-generated state preserved)"
