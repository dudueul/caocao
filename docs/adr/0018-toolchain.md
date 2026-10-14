# 0018. Toolchain: npm workspaces, TypeScript 7, Vitest, Biome, and a determinism guard

- **Status:** Proposed (in use by the skeleton; the owner to confirm or change)
- **Date:** 2026-10-14
- **Deciders:** Claude (default choice for the skeleton), pending the project owner
- **Related:** [0006](0006-typescript-everywhere.md), [0017](0017-clear-this-repository.md),
  [`ARCHITECTURE.md` §2](../remake/ARCHITECTURE.md#2-code-layout)

## Context

The monorepo needs a package manager, compiler, test runner and linter, kept
light. The rules package needs mechanical guards for determinism
([0006](0006-typescript-everywhere.md)): no clock, I/O, `Math.random`, locale
APIs, or float literals. As of 2026-10-14 the TypeScript compiler is at 7.0
(native), while `typescript-eslint` supports only TypeScript below 6.1.

## Options considered

1. **npm workspaces + TypeScript 7 + Vitest + Biome:** chosen. The fewest tools,
   fast, with no dependency on the TypeScript API.
2. **pnpm + ESLint/typescript-eslint + Prettier:** the most common stack, but
   more tools, and it pins TypeScript to 6.0.
3. **Bun:** fast all-in-one, but a different runtime from Workers and Node for tests.

## Decision

| Concern | Tool | Notes |
|---|---|---|
| Packages | **npm workspaces** (`packages/*`, `apps/*`) | Node ≥ 22.12 (`.nvmrc` 22); internal packages export TypeScript source (`"exports": "./src/index.ts"`), with no build step |
| Types | **TypeScript 7** `tsc --noEmit` | strict, `noUncheckedIndexedAccess`, `exactOptionalPropertyTypes` |
| Tests | **Vitest 5** | golden values for PRNG and hashes |
| Lint + format | **Biome 2** | recommended preset; `noRestrictedGlobals` bans `Date`, `performance`, `crypto`, timers, `fetch`, `Intl` in `packages/rules/src` |
| Determinism | `scripts/check-determinism.mjs` | bans `Math.random`, `localeCompare`, `toLocale*`, float literals in `packages/rules/src` |
| CI | GitHub Actions: `npm ci && npm run check` | typecheck → lint → determinism → tests |
| Later | Vite + PixiJS (`apps/client`, P0 sandbox), Wrangler (`apps/server`, P1) | added when those apps start |

`npm run check` passes on the skeleton (10 tests). Both lint layers were
verified to fire on planted violations.

## Consequences

- **Positive:** three dev dependencies; checks run in about a second; the guards point to ADRs in their messages.
- **Negative:** Biome has fewer rules and plugins than ESLint, and the
  determinism scan is regex-based (golden replays remain the real proof).
- **Watch:** `biome migrate` turned `"recommended": true` into `"preset": "none"`
  (which disables the rules). The config now says `"preset": "recommended"`.

## Revisit when

A needed lint rule exists only in ESLint, or the owner prefers pnpm or ESLint.
