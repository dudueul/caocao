# @tk/rules

The battle rules: one pure TypeScript package imported by the client (solo play,
previews) and by the Durable Objects (the referee). See
[`docs/remake/ARCHITECTURE.md` §3](../../docs/remake/ARCHITECTURE.md#3-rules-engine).

**Purity rules** ([ADR 0006](../../docs/adr/0006-typescript-everywhere.md)), enforced by
`bun run lint` (Biome `noRestrictedGlobals`) and `bun run determinism`:

- No clock, I/O, timers or locale: `Date`, `performance`, `fetch`, `setTimeout`, `Intl`, `localeCompare`.
- No `Math.random`: randomness comes from the `Prng` carried in the state.
- Integers only: fixed-point (×1000) instead of floats; `hashState` rejects floats.

| Module | What |
|---|---|
| `prng.ts` | `Prng` (sfc32, serializable 4×u32 state), `seedFromString` |
| `hash.ts` | `canonical` JSON (sorted keys, integers only), `hashState` (64-bit FNV-1a) |
