# dsa-06 — Spatial Partitioning: picking units & AoE without dying at 60fps

**Grounding evidence:** `CoordinateSystem : Singleton<CoordinateSystem>`
(TypeDefIndex in dump), tk2d-based tile rendering, and the battle scenes'
unit population (dozens of units × effects × UI). Selection and
area-of-effect queries over a tile grid are the bread-and-butter.

## 1. The query zoo, and the structure each wants

| Query | Best structure | Why |
|---|---|---|
| "what tile is under this tap?" | direct mapping (grid array) | grid IS the index |
| "units within radius R of point" | uniform grid buckets / quadtree | pruning beats scanning |
| "line of sight between tiles" | Bresenham + early-out | no structure needed |
| "all enemies in this skill's cone" | cone mask over grid buckets | precomputed sector templates |

**Exercise 6-A:** implement a uniform grid (cell = 4×4 tiles) that answers
radius queries over 5,000 simulated units; compare with brute force. Plot
t(n). Then shrink n to the *real* scale (≤ 200 units) and observe brute force
win — write the one-paragraph lesson: *structures pay rent only above their
crossover n; game code must know its n.*

## 2. Quadtrees done honestly

- **2-A** Implement point-quadtree insert/query/knn with the textbook
  `Node {center, half, pts[4-cap], children}`; degrade to leaf list under
  capacity 8.
- **2-B** The hard truth: moving units invalidate tree invariants. Compare:
  rebuild-per-frame (measure: 200 units) vs incremental move (bubble-up).
  When is rebuild simpler AND faster? (Answer usually: under ~1k dynamic
  points, rebuild wins — measure it.)

## 3. The AoE templates trick (what games actually ship)

Precompute per skill-shape a **tile mask** (array of (dx,dy) offsets) once;
runtime cost = mask iteration + occupancy test. This is why the decompiled
`EffectSetRandomTable`/`EffectBase` classes will likely reference shape ids —
Phase 2 confirms; our rebuild ships `SkillShape.cs` with mask tables.

**Exercise 3-A:** given `EnumData.SkillTypes` (from dump.cs), design the mask
encoding (bitboard per row) and implement cone/cross/line/3×3 generation.

## 4. Auto-grading

`verify-lab.sh dsa06`: correctness (query set equality vs brute force on
random layouts) + a performance assertion (grid < brute force at n=5,000;
either may win at n=200 — report, don't assert).

## 5. Measured reference (2026-10-12, exercise 6-A reference impl)

`exercises/dsa06-grid.py` — random 2D, cell=32, radius=100:

```
n=200:  brute=0.04ms grid=0.02ms  (grid wins)
n=5000: brute=0.79ms grid=0.13ms  (grid wins)
```

Honest surprise: at n=200 the grid already won in Python (the brute force is
O(n) but Python's per-op overhead is high; the grid's pruning pays). The doc
claim "brute wins under 1k" comes from C# inlining costs — **measure in YOUR
language**, which is the actual lesson.
