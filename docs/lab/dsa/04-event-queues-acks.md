# dsa-04 — Event Queues, Acks & the Single-Consumer Discipline

**Grounding code:** `client/Assets/Scripts/Net/SocketIOMin.cs` (our rebuild)
mirrors the original `SocketIOComponent` queue design:

```csharp
private object eventQueueLock;            private Queue<SocketIOEvent> eventQueue;
private object ackQueueLock;              private Queue<Packet> ackQueue;
private List<Ack> ackList;                private int packetId;
```

The original (2016 Unity, single-threaded game loop + background socket
threads) used **two lock-guarded FIFOs** — the producer (network thread)
enqueues; the consumer (Unity main thread) drains in `Update()`. This is
the *single-consumer pipeline*, the most important concurrency pattern in
game networking, and it is teachable *because it is simple*.

## 1. Why two queues, and why the split is right

| Concern | Queue | Why separate |
|---|---|---|
| Fire-and-forget events | `eventQueue` | ordering matters, response doesn't |
| Request/response acks | `ackQueue`/`ackList` | needs expiry (`ackExpirationTime=180`), id→callback map |

**Exercise 1:** What breaks if events and acks share one queue?
(Answer path: head-of-line blocking — a slow ack consumer delays event
delivery; separate concern lifetimes ⇒ separate structures.)

## 2. The lock-free upgrade path (and when *not* to take it)

- **2-A** Implement `ConcurrentQueue<T>`-drain-into-list (what SocketIOMin.cs
  does) and measure vs `lock + Queue` under 1M ops. Discuss: on modern .NET,
  `ConcurrentQueue` uses segmented lock-free rings — but the *contention
  pattern* (1 producer, 1 consumer) is where a plain ring buffer with head/tail
  indices shines (`System.IO.Pipelines` style). Implement the SPSC ring;
  explain the memory-ordering requirement (write data, then publish index).
- **2-B (timing wheel)** Ack expiry: 10k pending acks, 180s TTL. Sorted-list
  scan = O(n) per tick. Timing wheel: buckets of 1s, advance pointer, O(1)
  amortized. Implement both; plot tick cost vs pending count (the harness
  seeds 10k fake acks — `exercises/dsa04-wheel.py`).

## 3. Idempotency & at-least-once

Engine.IO reconnects can duplicate the last EVENT frame (server retries
before ack). The original game's mitigation: server-side sequence numbers per
room (we infer; Phase 2 confirms in `MultiplayManager` decompile). Design
exercise **3-A:** make `EmitWithAck` idempotent-safe: dedup by (packetId) with
a 60s LRU. What memory does 60s of 100 msg/s traffic need? (Answer in
solutions; it motivates the LRU over HashSet.)

## 4. Backpressure

`eventQueue` is unbounded in the original — a chatty guild-war stream can
OOM a phone (and did, in that era!). Our rebuild adds: coalesce-per-type
(keep only latest `vision` update per entity). Implement **4-A** as a
`Dictionary<Key, T>` "latest-wins" mailbox and explain *when latest-wins is
wrong* (input events! you cannot drop a click).

## 5. Auto-grading

`scripts/verify-lab.sh dsa04` (written in Phase 4 alongside the server test
suite) checks: ring-buffer SPSC invariants, timing-wheel tick complexity
(< 2µs at 10k), LRU dedup correctness.
