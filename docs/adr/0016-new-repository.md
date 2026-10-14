# 0016. The remake lives in a new repository; this one stays the reference archive

- **Status:** Superseded by [0017](0017-clear-this-repository.md)
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0002](0002-remake-from-scratch.md), [0006](0006-typescript-everywhere.md),
  [`docs/remake/ARCHITECTURE.md` §2](../remake/ARCHITECTURE.md#2-code-layout)

## Context

`dudueul/caocao` holds the recovery work: about 1 GB of extracted APK dumps,
the 505 MB recovered Unity project, and data and code derived from Koei and
Nexon (1998 tables, decompiled functions, localization). The remake is new code
([0002](0002-remake-from-scratch.md)) that may become public.

## Options considered

1. **New repository:** chosen. Small clones, simple CI, a clean IP boundary.
2. **A folder in this repository:** one place, but huge clones; the IP boundary is only a folder convention.
3. **Code in a new repository, docs kept here:** the docs would drift from the code.

## Decision

- Create a new repository (name to be chosen) as the TypeScript monorepo of
  [ARCHITECTURE.md §2](../remake/ARCHITECTURE.md#2-code-layout).
- Once it exists, **move** `docs/adr/` and `docs/remake/` there (keeping their
  history), and leave a pointer in this repository.
- `dudueul/caocao` becomes a read-only **reference archive**. Nothing derived
  from Koei or Nexon (extracted code, 1998 data, original art or text) is
  copied into the new repository. Design knowledge moves only as our own
  write-ups (for example a rules spec written in our own words and numbers).

## Consequences

- **Positive:** fast clones and CI; the new repository can be made public
  without an audit of inherited files.
- **Negative:** two repositories to keep in mind; cross-links from the new docs
  to archive evidence point at `dudueul/caocao`.
- **Follow-ups:** choose the name, create the repository, move the docs, add the pointer.

## Revisit when

Never for the code location. The archive could be made smaller or deleted once
the remake no longer needs its evidence.
