#!/usr/bin/env bash
# Reproducibility harness for the CAOCAO RE lab course.
# Usage: scripts/verify-lab.sh [apk|dsa01|dsa02|dsa03|il2cpp]
set -euo pipefail
CAOCAO_APK="${CAOCAO_APK:-$HOME/works/jojo/_release/caocao.apk}"
WORK="${CAOCAO_WORK:-$HOME/work/caocao}"

case "${1:-apk}" in
apk)
  echo "expecting sha256 3a5bd5bcaecb4cc250695589d98aeff2b63da709fb6e9867afe68467c6c37d06"
  sha256sum "$CAOCAO_APK"
  unzip -l "$CAOCAO_APK" | tail -1
  ;;
dsa01)
  python3 "$WORK/docs/lab/exercises/dsa01-index.py" \
    "$WORK/extracted/apk/assets/bin/Data" "$WORK/analysis/dsa01_reference.json" \
    && echo "dsa01 PASS"
  ;;
dsa02)
  python3 - "$WORK/docs/lab/exercises/flatbuffer_sample.bin" <<'PY'
import struct, sys
b = open(sys.argv[1], "rb").read()
root = struct.unpack_from("<I", b)[0]
t = root
so = struct.unpack_from("<i", b, t)[0]
vt = t - so
vsize, tsize = struct.unpack_from("<HH", b, vt)
f0 = struct.unpack_from("<H", b, vt + 4)[0]
vals = struct.unpack_from("<iii", b, t + 4)
assert vals == (7, 42, 1337), f"wrong fields {vals}"
assert (vsize, tsize, f0) == (10, 16, 4), f"wrong header {(vsize,tsize,f0)}"
print("dsa02 PASS: vtable(10,16) fields=7,42,1337")
PY
  ;;
dsa03)
  python3 - "$WORK/docs/lab/exercises/battlegrid_01.json" <<'PY2'
import json, sys, heapq
data = json.load(open(sys.argv[1]))
g, s, t = data["grid"], tuple(data["start"]), tuple(data["goal"])
def astar(g, s, t):
    H, W = len(g), len(g[0])
    pq = [(abs(s[0]-t[0])+abs(s[1]-t[1]), 0, s)]
    best = {s: 0}
    while pq:
        f, gc, cur = heapq.heappop(pq)
        if gc > best.get(cur, 1e18): continue
        if cur == t: return gc
        for d in ((1,0),(-1,0),(0,1),(0,-1)):
            nr, nc = cur[0]+d[0], cur[1]+d[1]
            if not (0 <= nr < H and 0 <= nc < W) or g[nr][nc] == 9: continue
            ng = gc + g[nr][nc]
            if ng < best.get((nr, nc), 1e18):
                best[(nr, nc)] = ng
                heapq.heappush(pq, (ng + abs(nr-t[0]) + abs(nc-t[1]), ng, (nr,nc)))
    return None
c = astar(g, s, t)
assert c == data["optimal_cost"], f"cost {c} != reference {data['optimal_cost']}"
print(f"dsa03 PASS: optimal cost {c} matches reference")
PY2
  ;;
il2cpp)
  test -s "$WORK/extracted/il2cpp/dump.cs" \
    && test "$(wc -l < "$WORK/extracted/il2cpp/dump.cs")" -gt 700000 \
    && echo "il2cpp dump present (~707k lines)" || echo "il2cpp dump MISSING"
  ;;
lab07)
  ( setsid python3 "$WORK/server/test/echo_server.py" 8791 >/tmp/echo_8791.log 2>&1 < /dev/null & )
  sleep 2
  timeout 15 python3 "$WORK/server/test/echo_client.py" 8791 && pkill -f "echo_server.py 8791"
  ;;
lab02)
  python3 "$WORK/docs/lab/exercises/02-B.py" "$WORK/extracted/apk/assets/bin/Data" 5
  ;;
*)
  echo "unknown lab $1" && exit 1
  ;;
esac