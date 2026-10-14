# 0019. Bun as package manager, test runner and script runner; TypeScript 7 and Biome stay

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Supersedes:** [0018](0018-toolchain.md) (proposed npm workspaces + Vitest)
- **Related:** [0004](0004-server-authoritative-referee.md), [0006](0006-typescript-everywhere.md),
  [`ARCHITECTURE.md` §2](../remake/ARCHITECTURE.md#2-code-layout)

## Context

The skeleton shipped with the proposed toolchain of [0018](0018-toolchain.md).
Asked to confirm it, the owner chose an all-in-one runtime instead. The options
were 0018 as is, pnpm, ESLint + Prettier (which pins TypeScript to 6.0), or Bun.

## Decision

| Concern | Tool |
|---|---|
| Install, workspaces, lockfile | **Bun 1.4** (`bun install`, `bun.lock`, version pinned in `.bun-version` and `packageManager`) |
| Tests | **`bun test`** (`import … from "bun:test"`) |
| Scripts | **`bun run`** (`check`, `typecheck`, `lint`, `format`, `determinism`) |
| Types | **TypeScript 7** `tsc --noEmit`; Bun strips types but doesn't type-check |
| Lint + format | **Biome 2**, unchanged from 0018, including the rules-package denied globals |
| Determinism scan | `scripts/check-determinism.mjs`, run with Bun |
| CI | `oven-sh/setup-bun` → `bun install --frozen-lockfile` → `bun run check` |
| Deploy (P1) | Wrangler / `workerd` for the Durable Objects; Vite + PixiJS for the client (P0 sandbox) |

## Consequences

- **Positive:** one fast tool for install, test and scripts, with no Vitest or
  npm lockfile. The test suite runs in about 0.3 s.
- **Cross-engine check:** the rules' golden values (PRNG sequence, state hash)
  were first computed on Node (V8) and pass unchanged on Bun (JavaScriptCore).
  That's evidence the integer-only rules are engine-independent.
- **Negative:** tests run on JavaScriptCore, while production runs on V8
  (Workers, Chrome) and on WebKit or JavaScriptCore (Safari).
- **Follow-ups:** in P1, add a golden-replay test that also runs inside
  `workerd` (Wrangler) so the referee's engine is covered directly.

## Revisit when

Bun and `workerd` disagree on any rules golden value, or a needed tool doesn't work under Bun.
