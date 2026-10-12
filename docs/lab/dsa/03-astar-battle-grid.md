# dsa-03 — A* on the SRPG Battle Grid (grounded in TileData / BattleMap)

**Grounding types (from `dump.cs`):**

```csharp
public abstract class TileData : IDataTableOwner, IDataType<TileData>, ...
public struct TileKey : IKeyType<TileKey>, IEquatable<TileKey>   // composite key!
public class TileData : MonoBehaviour, IComparable               // scene component twin
```

The battle scene (`Assets/Scenes/BattleMap.unity`) is a tile grid — this is a
*Zhōu Cāo Zhuàn*-style tactics game: units move on discrete tiles, initiative
turns, per-tile terrain costs. The decompiled `BattlePlayManager` (Phase 2)
will show us the original pathfinder; meanwhile you will implement the
canonical algorithm and later diff it against the original (the course's
crown-jewel exercise).

## 1. Why A* and what "admissible" buys you

A* = Dijkstra guided by an optimistic heuristic:

```
f(n) = g(n) + h(n)     g: cost so far, h: lower-bound estimate to goal
```

With `h ≤ trueCost` (admissible) and consistent `h(n) ≤ cost(n,n') + h(n')`,
the first time A* pops the goal, `g(goal)` is provably optimal — no relaxation
repeats needed (compare: Dijkstra pops everything; greedy best-first is fast
but wrong). On a 4-connected grid with unit move costs, Manhattan distance is
admissible and consistent; Chebyshev fits 8-connected.

## 2. The priority queue is the actual lesson

A* is boring; the heap is where students learn:

- **Binary heap**: `push/pop = O(log n)`, array-backed, `i → 2i+1, 2i+2`.
- **Decrease-key problem**: lazy approach (push duplicates, skip stale pops)
  vs indexed heap (`Dictionary<Key,int>` pos map + sift-up) — measure both on
  the real grid once Phase 2 recovers map sizes (expect ≤ 30×30 tiles — where
  even a naive O(n²) Dijkstra wins; *know when sophisticated is worse*).
- **Tie-breaking**: stable ordering by (f, h) — without it, paths zigzag;
  with it, straight-line paths emerge from pure priority order (fun to see).

## 3. Exercises

- **3-A** Implement `astar(grid, start, goal, costs)` with a binary heap +
  lazy duplicate skipping. Test on `exercises/battlegrid_01.json`
  (a recovered-style tile layout: move costs 1–3, walls).
- **3-B** Prove to yourself why Manhattan is admissible here: every step
  changes Manhattan distance by ≤ 1 while costing ≥ 1.
- **3-C** Add movement-range highlighting (Dijkstra-limited flood: all tiles
  with `g ≤ movePoints`) — the classic SRPG "blue tiles". Compare with the
  decompiled `BattlePlayManager` implementation in Phase 2 and diff behaviors
  (write findings to `analysis/diff_pathfinding.md`).
- **3-D** Turn order: heap of units by (initiative, speed, unitId) with
  time-skip mechanics (idle units advance the clock) — the "simulated clock"
  priority queue pattern (also used in OS schedulers).

## 4. Reference facts for grading

- Grid exercise: `exercises/battlegrid_01.json` (8×8, walls=9, terrain costs
  1–3), start (0,0) → goal (7,7). Reference optimal cost = **14**
  (measured with the reference A*; path recorded in the file). Your solution
  must match the cost and produce a valid path; `scripts/verify-lab.sh dsa03`
  checks cost exactly and validates every step is 4-connected and passable.
- After Phase 2, `analysis/diff_pathfinding.md` records where the original
  differs (if it does): e.g., diagonals, occupancy blocking, "through-ally"
  rules.

## 5. Bridge to the rebuild

`client/Assets/Scripts/Battle/Pathfinding/` gets your A*; the decompiled
original lives next to it as `_reference/` — the diff IS the lesson.
