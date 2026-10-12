# dsa-05 — Weighted Random, Gacha & Fairness (grounded in the dump's RNG surface)

**Grounding evidence:** `EnumData.ItemEffect`, gacha-related managers and the
`TRESURE_GET`/`TRESURE_ENHANCE` chat sub-events prove a treasure/gacha economy;
the exact draw routine is decompiled in Phase 2 (target:
`FinishWorldBoss`/loot paths + any class named `Random`/`Gacha`/`Lottery`).
This lab teaches the DSA *before* reading the original, then diffs.

## 1. The three honest ways to do weighted draws

Given weights `w[0..n)`, draw index i with probability w[i]/Σw:

1. **Prefix sums + binary search** — build once O(n), draw O(log n).
   `prefix = [w0, w0+w1, ...]`; draw `r = U(0, Σw)`; first prefix > r.
2. **Alias method** (Vose) — build O(n) table of (prob, alias); draw O(1)
   after one uniform sample. The classic interview answer, and the right one
   for 10k-draw-per-second servers.
3. **Reservoir/streaming** — when the pool arrives as a stream (loot drop
   tables streamed from server), weighted reservoir sampling.

**Exercise 5-A:** implement all three over a real 1,024-entry weight vector
(rarity curve: 60/25/10/4/1). Measure build+draw costs; report in a table.

## 2. Statistical testing — proving your RNG isn't rigged

- **2-A** chi-square goodness-of-fit on 100k draws vs expected frequencies.
  Degrees of freedom = categories−1; report the p-value; explain why p < 0.01
  over *many* runs (not one) is the criterion.
- **2-B** Pity systems ("guaranteed rare every 50 draws") are *dependent*
  trials — why does chi-square no longer apply directly? (Answer: draws are
  not i.i.d.; test the *marginal* distribution per position or simulate.)
- **2-C** The original game's `FairRuleManager` + `AntiCheatingObserver`
  + `ObscuredInt` on every loot field exist because **client RNG was trusted
  for nothing**: draws happen server-side (Phase 2 confirms the send/receive
  split). Write the security argument in 5 sentences.

## 3. Deterministic replay (the server's obligation)

Cloudflare rebuild requirement: same seed + same action history ⇒ same
inventory. Implement **3-A**: `SplitMix64`-seeded PRNG stream per player,
persisted `(seed, counter)` in D1; every draw = `next(seed, counter++)`.
Discuss: why per-player streams beat one global RNG (no cross-user
correlation, replayable support tickets), and the storage trick
(counter in a DO's storage, seed in D1).

## 4. Auto-grading

`verify-lab.sh dsa05`: runs your implementation against the reference weight
vector; asserts chi-square p > 0.01 and exact alias-table correctness
(brute-force cross-check on 20 random weight vectors).
