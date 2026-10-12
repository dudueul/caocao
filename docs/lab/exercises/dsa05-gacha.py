#!/usr/bin/env python3
"""dsa05-gacha.py — weighted draw: alias method + chi-square validation.

Reference weights (rarity curve over 1,024 entries):
  60% common, 25% uncommon, 10% rare, 4% epic, 1% legendary (per-slot).

Implement:
  1. prefix-sum + bisect draw          (O(log n))
  2. Vose alias table draw             (O(1) after O(n) build)
  3. chi-square goodness-of-fit on 100k draws

verify-lab.sh dsa05 checks: alias table correctness vs brute-force
probabilities on random vectors + p > 0.01.
"""
import bisect, itertools, random, sys

WEIGHTS = [60, 25, 10, 4, 1] * 204  # 1,020 entries; scale demo only


def prefix_draw(weights, r):
    total = 0
    prefix = []
    for w in weights:
        total += w
        prefix.append(total)
    return bisect.bisect_right(prefix, r * total)


class AliasTable:
    """Vose (1991). build O(n); draw O(1)."""

    def __init__(self, weights):
        total = sum(weights)
        n = len(weights)
        scaled = [w * n / total for w in weights]
        self.prob = [0.0] * n
        self.alias = [0] * n
        small, large = [], []
        for i, p in enumerate(scaled):
            (small if p < 1 else large).append(i)
        while small and large:
            s, l = small.pop(), large.pop()
            self.prob[s] = scaled[s]
            self.alias[s] = l
            scaled[l] = (scaled[l] + scaled[s]) - 1
            (small if scaled[l] < 1 else large).append(l)
        for i in large + small:
            self.prob[i] = 1.0
            self.alias[i] = i

    def draw(self, u1, u2):
        i = int(u1 * len(self.prob))
        return i if u2 < self.prob[i] else self.alias[i]


def chi_square(observed, expected):
    stat = sum((o - e) ** 2 / e for o, e in zip(observed, expected) if e > 0)
    # df = k - 1; for k=5, crit(0.01) = 13.277
    return stat, stat < 13.277


if __name__ == "__main__":
    rng = random.Random(20160401)
    t = AliasTable(WEIGHTS)
    N = 100_000
    draws = [t.draw(rng.random(), rng.random()) for _ in range(N)]
    # collapse 1,020 entries into the 5 rarity buckets for the test
    buckets = [0] * 5
    for d in draws:
        buckets[d % 5] += 1  # WRONG on purpose? — no: WEIGHTS repeat mod 5
    # Class probability = 204 copies × W_k / total(20,400) = W_k / 100.
    # BUG TEACHING NOTE: using N*w/sum(WEIGHTS) here is the classic mistake —
    # you'd be testing the *entry* distribution against the *class* distribution.
    expected = [N * w / 100 for w in [60, 25, 10, 4, 1]]
    stat, ok = chi_square(buckets, expected)
    print(f"buckets={buckets} chi2={stat:.2f} pass={ok}")
    sys.exit(0 if ok else 1)
