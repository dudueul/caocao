# 0057. Chapter 1, 황건의 난: six battles from Luoyang to Yanzhou

- **Status:** Accepted
- **Date:** 2026-10-19
- **Deciders:** project owner (chose the six battles, the coalition at Sishui Pass included), Claude (maps, armies, levels, scenes)
- **Related:** [0047](0047-battle-goals-and-arrivals.md), [0049](0049-story-chapter-guandu.md),
  [0056](0056-chapter-order-and-carry-over.md), [STORY.md](../remake/STORY.md)

## Context

With the story told in order (ADR 0056), chapter 1 covers Cao Cao from his
first post in 174 to becoming governor of Yanzhou in 192. The owner chose a
six-battle lineup that includes the coalition's fight at Sishui Pass. That
battle comes more from the novel than the records; the novel is public
domain, and the credits say where scenes are borrowed.

## Options considered

1. **Six battles, the coalition at Sishui included:** chosen by the owner.
2. **Five battles, youth to Qingzhou:** recommended, but without the coalition's set piece.
3. **Four battles, the war years only:** shorter, with the youth kept as scenes.

## Decision

| # | Battle | Year | Win | Lose | Notes |
|---|---|---|---|---|---|
| 1 | 북문의 오색봉, Luoyang | 174 | arrest 건도 | 조조 falls | The tutorial. 조조 alone with two night-watch guards, against a eunuch's uncle out after curfew |
| 2 | 장사의 불길, Changshe | 184 | defeat 파재 | 조조 or 황보숭 falls | Wind and grass for 황보숭's fire, with Yellow Turbans arriving on round 4 |
| 3 | 사수관의 술, Sishui Pass | 190 | defeat 화웅 | 조조 falls | 관우, 장비, 유비 and 손견 fight as guests. 여포 arrives on round 6 to hold the fort |
| 4 | 변수의 추격, Xingyang | 190 | hold until round 8 | 조조 falls | 서영's ambush and waves of cavalry. 조홍's line: the world can do without him, not without Cao Cao |
| 5 | 흑산의 무리, Puyang | 191 | defeat 백요 | 조조 falls | Bandits in a hill village |
| 6 | 수장의 겨울, Shouzhang | 192 | hold until round 10 | 조조 falls | Waves of Qingzhou Yellow Turbans. 포신 fights beside us and does not come home |

- **The roster:**
  - The chapter starts with 조조 alone, at Lv 3.
  - 하후돈, 하후연, 조인 and 조홍 join in 184.
  - 순욱, 악진 and 이전 join in 191, and 진궁 in 192.
  - 우금 joins after the last battle.
- **The rest:**
  - Three camps with shops that grow.
  - Sixteen new officers and six troop templates: night-watch guards, a household's thugs, Yellow Turbans, Qingzhou Yellow Turbans, Dong Zhuo's army and the Black Mountain bandits.
  - Yellow Turbans wear yellow head-cloths in battle and in their portraits.
- **The ending** hands on to 관도 (floor Lv 14) until chapter 2 exists.

## Consequences

- **Positive:**
  - The story starts where the owner wants it to.
  - The first battle is small enough to teach in.
  - The AI wins every battle of both chapters in balance runs, with 2–3 rounds of slack on the hold battles.
- **Negative / costs:** another ~600 lines of hand-written content.
- **Follow-ups:** chapter 2, 여포 (복양's fire, 완성 and 전위's last stand, 하비).

## Revisit when

Playtests find a battle too long (the tutorial runs about 8 rounds) or a hold battle too easy.
