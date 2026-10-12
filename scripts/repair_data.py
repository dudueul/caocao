#!/usr/bin/env python3
"""repair_data.py — decode CodeStage ObscuredInt/ObscuredString values from
AssetRipper-exported YAML (Lab 08.3 data-repair pipeline).

Stage 1 (now): scan exported .asset/.prefab YAML for obscured fields and
report counts/locations.
Stage 2 (after Phase 2 confirms the exact CodeStage algorithm from the
decompiled getters): emit a plain-JSON item catalog for the server (D1 seed)
and write back plain fields for the Unity client.

Usage: repair_data.py <assets_dir> [--out catalog.json]
"""
import glob, json, os, re, sys

# Serialized shape (kept for compat by our ObscuredTypes.cs shim):
#   currentCryptoKey, hiddenValue, inited, fakeValue
FIELD_RE = re.compile(r"^\s*(m_\w+):\s*$")

def scan(assets_dir):
    stats = {"files_scanned": 0, "files_with_obscured": 0, "obscured_fields": 0}
    examples = []
    for path in glob.glob(os.path.join(assets_dir, "**", "*.asset"), recursive=True):
        stats["files_scanned"] += 1
        text = open(path, errors="ignore").read()
        if "hiddenValue" in text:
            stats["files_with_obscured"] += 1
            stats["obscured_fields"] += text.count("hiddenValue")
            if len(examples) < 5:
                examples.append(path)
    print(json.dumps(stats, indent=1))
    for e in examples:
        print("example:", e)
    return stats


if __name__ == "__main__":
    scan(sys.argv[1])
