# 0005. Web-first (PWA) plus a PC build for friends; store wrappers later

- **Status:** Accepted
- **Date:** 2026-10-14
- **Deciders:** project owner
- **Related:** [0006](0006-typescript-everywhere.md), [0007](0007-top-down-2d-pixijs.md)

## Context

The audience is friends now, with a possible public release later. A public
store launch would require native builds, payments, store review and
anti-cheat from day one. A friends-only game can ship without app stores.

## Options considered

1. **Friends now, maybe public:** web/PWA plus PC first, wrap the same build for stores later. Chosen.
2. **Friends-only forever:** same delivery, but no door left open.
3. **Public store launch first:** heavy compliance and native work before the game is proven.
4. **Web portals first:** strict size limits; apps secondary.

## Decision

- **v1:** the game is a web app installable to the home screen (PWA) on desktop
  and phones, plus a PC build that wraps the same web build in a desktop shell
  (Tauri). Updates are instant, with no store review.
- **Later:** the same build wrapped for iOS/Android (Capacitor or Tauri
  mobile) if the game goes public. Store-only concerns (in-app purchases,
  review) are added then.
- **Budgets:** first load ≤ 10 MB, and interactive in under 3 s on a mid-range phone over 4G.
  Content streams in per chapter or map.

## Consequences

- **Positive:** one build for every platform; friends join with a link.
- **Negative:** iOS PWA limits (no background work; push notifications only for
  installed PWAs since iOS 16.4) and browser storage quotas.
  Battery and performance are worse than native on low-end phones.
- **Follow-ups:** asset streaming and caching (service worker); notification
  strategy for World events ([0011](0011-world-pacing-and-auto-join.md)).

## Revisit when

A public launch is decided, or iOS PWA limits break a core feature such as notifications.
