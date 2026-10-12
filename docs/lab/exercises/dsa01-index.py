#!/usr/bin/env python3
"""dsa01: content-index over Unity data files (Lab dsa-01 exercise starter).

Usage: dsa01-index.py <data_dir> <reference_json>
Prints and validates: file count, unique contents, duplicate ratio.
Implement the TODOs yourself before comparing against the reference.
"""
import hashlib, json, os, sys


def content_index(data_dir):
    """Return {sha256_hex: [filenames]} for every regular file in data_dir."""
    index = {}
    # TODO(student): iterate files, hash with sha256 in 1 MiB chunks,
    #                accumulate names per digest.
    for name in os.listdir(data_dir):
        path = os.path.join(data_dir, name)
        if not os.path.isfile(path):
            continue
        h = hashlib.sha256()
        with open(path, "rb") as f:
            for chunk in iter(lambda: f.read(1 << 20), b""):
                h.update(chunk)
        index.setdefault(h.hexdigest(), []).append(name)
    return index


def main(data_dir, reference_path):
    index = content_index(data_dir)
    files = sum(len(v) for v in index.values())
    unique = len(index)
    dup_copies = files - unique
    total = sum(os.path.getsize(os.path.join(data_dir, n)) for v in index.values() for n in v)
    print(f"files={files} unique={unique} dup_copies={dup_copies} total={total}")
    ref = json.load(open(reference_path))
    ok = files == ref["files"] and unique == ref["unique"]
    print("PASS" if ok else f"MISMATCH vs reference {ref}")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2]))
