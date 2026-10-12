# Socket.IO Event Catalog (auto-built from decompiled Emit/On call sites)

Format: `event | direction | first-seen caller (class$$method @RVA) | args sketch`

This file is populated during Phase 2 (Ghidra/REA). Seed entries inferred
statically; each row must be confirmed by a decompiled call site before being
treated as ground truth.

## Confirmed (call-site evidence)

(none yet — Ghidra analysis in progress)

## Strong candidates (from enums/strings; require call-site confirmation)

| Event | Evidence |
|---|---|
| login / auth variants | NPAController, GFOnLogin PlayMaker actions |
| battle commands (client→server) | MakeNewCommand(DuelCommands, object[]) @ various |
| battle result senders | Finish*.Send*BattleEnd (11 distinct flows) |
| chat: channel/whisper/guild | ChattingMessage.ChattingMessageType |
| matchmaking | MultiplayManager.RequestMatchmaking / CancelMatchmaking |
| heartbeat | HeartBeats singleton |
