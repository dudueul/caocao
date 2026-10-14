# @tk/protocol (not started)

Typed client–server messages (commands up, events and snapshots down) shared by
`apps/client` and `apps/server`. Starts in **P1** (Arena). Proposed transport:
plain WebSocket with JSON ([ARCHITECTURE.md §5](../../docs/remake/ARCHITECTURE.md#5-server-topology)).
