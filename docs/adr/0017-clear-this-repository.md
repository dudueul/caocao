# 0017. Clear this repository and build the remake in it; the recovery archive is commit `dabaf0a2`

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Supersedes:** [0016](0016-new-repository.md)
- **Related:** [0002](0002-remake-from-scratch.md), [0018](0018-toolchain.md)

## Context

[0016](0016-new-repository.md) chose a new repository (`dudueul/tk`). Creating
it from the design session failed: the GitHub integration may not create
repositories on the owner's account (HTTP 403). The owner then decided to
**clear this repository and start from scratch here**. Once the project has
taken shape, the owner will download a snapshot and restart the session from it.

## Options considered

1. **Owner creates `dudueul/tk` by hand, then the docs move:** the 0016 plan; it waits on a manual step.
2. **Clear this repository and build here:** chosen; no waiting, and the snapshot gives a clean start later.

## Decision

- The last commit of the recovery work, **`dabaf0a2`** on `master`, is the
  **archive**. Everything there (extracted APK dumps, the recovered Unity
  project, the Workers server, data tables, 1998 reference data, recovery docs)
  stays reachable in history:
  `https://github.com/dudueul/caocao/tree/dabaf0a2`.
- The next commit removes all of it from the working tree. Only `docs/adr/`
  and `docs/remake/` remain, and the remake's monorepo skeleton is added
  ([0018](0018-toolchain.md)).
- Doc links into the old tree are rewritten as permalinks at `dabaf0a2`, so they keep working.
- The IP boundary of [0002](0002-remake-from-scratch.md) and [0016](0016-new-repository.md) still applies:
  nothing derived from Koei or Nexon enters the new tree.

## Consequences

- **Positive:** work continues immediately; the tree is small and clean; the
  archive is one permalink away.
- **Negative:** the git history still contains the archive (the pack is about
  206 MB), so clones stay large, and history isn't IP-clean. A public release
  should therefore start from the **snapshot in a fresh repository** (the
  owner's plan), not from this repository's history.
- **Follow-ups:** the owner downloads the snapshot and may start a new
  repository and session from it. The docs then need no change except this archive pointer.

## Revisit when

The project moves to a fresh repository from the snapshot. Record that move as a new ADR.
