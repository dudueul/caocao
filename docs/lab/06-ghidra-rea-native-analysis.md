# Lab 06 — Native Analysis: Ghidra + REA on libil2cpp.so

**Goal:** go from *names* (Lab 04) to *behavior*. Method bodies live only in
machine code; we make 163,902 functions findable, then read decompiled C for
the ~dozens that matter.

---

## 6.1 The Ghidra mental model (first-contact tour)

Ghidra is a *program*: it loads an ELF into a **project database**, then a
battery of **analyzers** disassembles and cross-references it. Three concepts
you must own:

1. **Function boundaries.** IL2CPP is C compiled as ARM64. Ghidra finds
   function starts by scanning for prologues + call targets. A 75 MB IL2CPP
   library has ~163k functions — Ghidra's decompiler then lifts each to C.
2. **Cross-references (xrefs).** *"Who calls `ServerSetting.get_GAME_SERVER_HOST_URL`?"*
   is one query. Xref walking is how you'll find the packet-encryption code.
3. **Scripting.** Ghidra headless + scripts = automation. We inject Il2CppDumper's
   `script.json` — renaming every function to `Namespace.Class$$Method`.

### The run (this course's actual command)

```bash
analyzeHeadless ~/work/caocao/analysis/ghidra_proj caocao \
  -import extracted/apk/lib/arm64-v8a/libil2cpp.so \
  -scriptPath scripts \
  -postScript il2cpp_rename.py extracted/il2cpp/script.json
```

Real-world notes (2026-10-12 runs): 75.8 MB AARCH64 ELF. **War stories from
this very project** — read them as incident reports:

1. *The analyzer that never ends.* First headless run burned ~2h and never
   finished: the default analyzer set includes **Decompiler Parameter ID**,
   which recompiles every function to guess signatures — worthless for IL2CPP
   (script.json *gives us* signatures). Fix: `-preScript` that sets
   `setAnalysisOption(currentProgram, "Decompiler Parameter ID", "false")`.
   Lesson: know your analyzer pipeline; on huge binaries the defaults are for
   small programs.
2. *Python scripts need PyGhidra.* Ghidra 12 headless rejected our `.py`
   scripts: "Ghidra was not started with PyGhidra". Rather than bootstrap a
   Python environment into Ghidra, we ported both scripts to **Java
   GhidraScripts** (compiled natively by headless, zero dependencies).
   Lesson: prefer the scripting runtime that *your tool invocation already
   has*; bootstrap costs compound silently.
3. *Headless transactions.* Killing the analyzer mid-run lost all progress:
   Ghidra commits the program transaction at task end. Budget your runs, and
   prefer "disable the slow analyzer" over "wait longer".
4. The `GccExceptionAnalyzer` emits ~120k benign `LSDACallSiteTable` warnings
   on this binary. Triage rule: grep the log for *non-benign* signatures
   (`SCRIPT ERROR`, `Failed to import`) rather than eyeballing 120k lines.

## 6.2 Why the rename script transforms everything

Before: a function is `FUN_0045d5ce8` — you know nothing.
After: `ServerSetting$$get_GAME_SERVER_HOST_URL` — you know *everything about
the interface*; only the body needs reading.

The script (scripts/il2cpp_rename.py, ~60 lines of PyGhidra) does:
- `ScriptMethod[]`: for each of the 163,902 entries — `createFunction` if
  absent, then `setName(..., USER_DEFINED)`.
- `ScriptString[]`: label the 19,897 literal blobs (`str_login`, ...).
- `ScriptMetadata[]`: label Il2CppClass structures (`class_...`).

**Performance discussion (DSA tie-in, dsa-01):** renaming is 163k symbol-table
mutations; Ghidra batches them internally. Our measured rename throughput goes
here once the run completes: `analysis/ghidra_rename_stats.md`.

## 6.3 REA as your analysis copilot

REA wraps Ghidra headless behind an evidence-returning CLI. The difference
between *reading Ghidra yourself* and *driving REA*: REA returns **Evidence
bundles** — every claim (pseudocode, xrefs, strings) is bound to the binary's
SHA-256 and the query that produced it. For a reconstruction project that
means: the "why" of every rebuilt method is citable.

Core verbs:

```bash
rea function  <binary> <name-or-address> --provider ghidra --json   # decompile one
rea xrefs     <binary> <name> --provider ghidra --json              # who calls it
rea strings   <binary> --provider ghidra --json                     # searchable strings
rea analyze   <binary> --provider ghidra --json                     # overview
```

(Exact verb list: `rea --help`; provider pinned via `--provider ghidra`.)

**Investigation pattern** (used repeatedly in Phase 2):

1. Pick a *question* ("where does the client encrypt packets?").
2. Seed: `dump.cs` gives the class (`FileUtil.GetDecryptedBytes`, RVA `0x1A122E8`).
3. `rea function` on that address → decompiled C.
4. `rea xrefs` on it → callers = the packet layer.
5. For each caller, repeat — the call graph *is* the protocol.

## 6.4 Reading decompiled IL2CPP C (survival guide)

Real excerpt patterns you will meet:

```c
// method call
SocketIOComponent_Emit(_this, (String_t*)_stringLiteral_login, args, 0);
// field access via il2cpp offsets
int v3 = *(_DWORD *)(this + 0x2C);     // pingInterval — see dump.cs field offsets!
// string compare
if ( sub_1234(a1, str_login) ) ...
```

Decode rules:
- `this + 0xNN` → match the offset against `dump.cs` field comments — instant
  field identification.
- `String_t*` args → named labels (`str_login`) from ScriptString.
- `MethodInfo*` trailing params → ignore (IL2CPP bookkeeping).
- Generics appear as shared code with `_cctor`-style context params.

## 6.5 Exercises

- **6-A** After the course's Ghidra run completes: use `rea function` to
  decompile `ServerSetting$$get_GAME_SERVER_HOST_URL` and quote the returned
  field read. Then walk xrefs until you find where `SocketIOComponent.url`
  is assigned — paste the decompiled snippet into your lab notebook.
- **6-B** Write a 20-line PyGhidra script that prints every function whose
  name contains `Encrypt` (expect the Rijndael wrappers).
- **6-C** Decompile `HeartBeats` — explain its ping cadence vs
  `SocketIOComponent.pingInterval`.

Solutions: `solutions/06.md` (post-analysis).
