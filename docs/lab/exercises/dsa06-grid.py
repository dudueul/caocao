#!/usr/bin/env python3
"""dsa06: uniform-grid bucket vs brute-force radius query on 5,000 units.
Implements the crossover measurement (report, don't assume)."""
import random, time

def brute(units, center, radius):
    r2 = radius * radius
    return [u for u in units if (u[0]-center[0])**2 + (u[1]-center[1])**2 <= r2]

class UniformGrid:
    def __init__(self, units, cell):
        self.cell = cell
        self.buckets = {}
        for u in units:
            k = (u[0] // cell, u[1] // cell)
            self.buckets.setdefault(k, []).append(u)

    def query(self, center, radius):
        r = radius
        out = []
        cx, cy = center[0] // self.cell, center[1] // self.cell
        span = r // self.cell + 1
        for dx in range(-span, span + 1):
            for dy in range(-span, span + 1):
                for u in self.buckets.get((cx + dx, cy + dy), []):
                    if (u[0]-center[0])**2 + (u[1]-center[1])**2 <= r*r:
                        out.append(u)
        return out

random.seed(42)
for n in (200, 5000):
    units = [(random.randint(0, 1000), random.randint(0, 1000)) for _ in range(n)]
    center, radius = (500, 500), 100
    t0 = time.perf_counter(); r1 = brute(units, center, radius); t1 = time.perf_counter()
    g = UniformGrid(units, 32)
    t2 = time.perf_counter(); r2 = g.query(center, radius); t3 = time.perf_counter()
    assert len(r1) == len(r2), "query results differ!"
    print(f"n={n}: brute={1000*(t1-t0):.2f}ms grid={1000*(t3-t2):.2f}ms "
          f"({'grid wins' if t3-t2 < t1-t0 else 'brute wins — know your n'})")
