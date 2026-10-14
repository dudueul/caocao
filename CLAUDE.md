# Working agreements

## Decisions and documentation (owner's standing rule)

- **Every decision gets its own ADR** in `docs/adr/` (copy `template.md`, next
  free number, add a row to `docs/adr/README.md`). Write it in the **same commit**
  as the change it decides.
- **Keep the technical documents current** in `docs/remake/` (`README.md`,
  `ARCHITECTURE.md`, `WORLD_ENGINE.md`, new files as needed). Use mermaid
  diagrams where they help, and render them to check they parse before committing.
- Accepted ADRs are not rewritten. Only their status and links change; a change
  of mind is a new ADR that supersedes the old one.
- A default chosen without the owner is recorded as `Proposed`, and the owner is asked to confirm.
- Design sessions run as interviews: one question at a time, each with a recommended answer.

## Code rules

- `packages/rules` is pure and deterministic ([ADR 0006](docs/adr/0006-typescript-everywhere.md)):
  no clock, I/O, timers, `Math.random`, locale APIs or float literals.
  Randomness comes from the `Prng` in the state; numbers are integers (fixed-point ×1000).
- Run `npm run check` before every commit (typecheck, Biome, determinism guard, tests).
- Golden values (PRNG sequence, state hashes) change only with a format-version bump.

## IP boundary

The recovery archive at commit `dabaf0a2` (APK dumps, recovered Unity project,
1998 data) is **reference only**. Never copy Koei or Nexon code, data, art or
text into this tree. Write design knowledge in our own words and numbers
([ADR 0002](docs/adr/0002-remake-from-scratch.md)).

## Repository

The owner works directly on `master`. Commit messages are short imperative subjects with a body explaining why.
