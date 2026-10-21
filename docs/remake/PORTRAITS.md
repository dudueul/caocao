# Portraits

> Living document for the officer portraits painted in code
> ([ADR 0053](../adr/0053-painted-portraits.md), [ADR 0070](../adr/0070-portraits-from-the-record.md)):
> what other Three Kingdoms games teach about telling a large cast apart, the
> principles we adopted, the painter's parameters, and the corrections the
> record made. Per-officer looks are in [CHARACTER_LOOK.md](CHARACTER_LOOK.md).
> Last updated 2026-10-21.

## Why

The first painter gave every officer a trait row, but every head was drawn at
the same place, size and turn, with features at fixed points:
- the class chose the headgear;
- beards grew from one shape;
- the side's colour filled every background.

The owner found that the portraits all looked alike and asked for a benchmark of
other games, with looks grounded in history.

## Benchmark

We studied how other games treat portraits. **Principles only:** we copy no art
(ADR 0002). What each game does, in our words:

| Game | What it does |
|---|---|
| Koei 三國志 XI–XIV (700–1,000+ officers, a bust each) | About 90% of a look is invented, steered by the person's role. The face comes first and the background exists to make it read. A moustache with ends hanging straight down reads as "Three Kingdoms" at once. Helmets go to fierce generals, caps to statesmen. Rank adds a crown or richer dress. Age changes proportions first (nose, jaw, sagging contour), then adds lines. Long weapons are cropped rather than shrinking the person. 三國志 XIV went back to neutral poses that suit dialogue. Its artists admit the trap of knotting every fierce general's brows the same way |
| Dynasty Warriors 7–9 | A character map keeps personalities from overlapping. The weapon is the identity. Each character exaggerates one facial feature. Brothers are designed as contrasts. Faction colours were added late, so players could pick out enemies |
| Total War: Three Kingdoms | Class colours follow the five phases. A "Records" mode strips the novel's gear from the same people. Only about 113 of 1,000+ characters got unique art, and players complained about generic look-alikes |
| Koei's 1990s tactics games | Small pixel busts are told apart by a few big shapes: the headgear outline, the beard mass, the skin tone, one expression. A 48 px card has the same budget |
| 三國志 覇道, 三国志大戦 | Rarity adds ornament, never a different face. Faction is a frame colour; each game sets its own colour convention |

## Principles we adopted

1. **Silhouette first.**
   - Each hero has a top shape (headgear) and a back shape (weapon, plumes, parasol) that no other hero of the same faction has.
   - Checked at 48 px, in colour and in grey.
2. **One recorded cue per person, drawn big**, before anything from the novel. Every trait is tagged `record`, `novel`, `later lore` or `our reading`.
3. **Headgear by office and person, not by class**, following the Han dress treatise (續漢書 輿服志):
   - 進賢冠 with ridges by rank;
   - 武冠, and 鶡冠 with pheasant tails for 中郎將;
   - 貂蟬冠 for 侍中, 法冠 for law officers;
   - 幘 and 幅巾 for gentlemen;
   - the 帢 that 조조 devised.
4. **Height, build and turn are parameters.** Tall men's caps touch the top edge, short men have sky above them, huge men's shoulders leave the card, and heads turn left, right or front.
5. **Age by proportion first**, then lines and grey. Everyone is drawn at their best moment.
6. **A beard library drawn from strands.** The down-hanging Han moustache is the common grammar. Some shapes are reserved for one person: 관우's beard bag, 장비's tiger whiskers, 조조's slim tail, 황충's white.
7. **Faction by dye and landscape, hero by accent colour and prop.**
   - The robe keeps the side's dye ([ADR 0055](../adr/0055-our-side-red.md)).
   - Each hero adds one accent colour and one prop.
   - A reserved list keeps signature items off generated soldiers.
8. **The background serves the face:** a value step behind the head, a low-contrast landscape, and one motif per hero.
9. **Calm is neutral;** the five moods carry the emotion.
10. **No caricature of any people.** The 오환 and other northern peoples are told apart only by recorded dress and hair.

## The painter

`apps/client/src/lib/fx/portrait.ts` re-exports a painter split into modules
under `portrait/`:
- types and palette;
- drawing helpers;
- the head model, face, hair, beards and headgear;
- body and gear;
- the backdrop;
- the officer and army tables;
- the composer.

Callers use `portraitUrl` and the other exports as before.

| Part | What varies |
|---|---|
| Head | Turn (front, three-quarter left or right, near profile, look-back), roll, crop (high for tall men, low for short men), head size, seven builds with the neck matching, stoop |
| Face | Seven face shapes, nine eye kinds, seven brow kinds, six noses, mouth; age changes proportions first (jaw, cheek line, nose length), then lines and grey; skins include 관우's red-brown and 진등's flush; eye spacing varies per man |
| Beard | 17 kinds drawn from strands. The hanging Han moustache is the default. The beard bag, tiger whiskers, slim tail and long white beard are each reserved for one man |
| Headgear | 41 shapes on one head model, so every cap turns with the head: court caps by office, cloth caps and headcloths, bands and wraps, shaved scalps, hats and hoods, eleven helmet styles, and crowns |
| Accent and gear | One accent colour per hero on cap, collar, sash or band (the robe keeps the side's dye); about 45 front props and 19 back items, cropped by the frame. A `RESERVED` list keeps signatures off generated soldiers |
| Backdrop | A low-contrast landscape for each of 12 factions; 17 hero motifs in place of the shared halo ring; a lighter glow behind the head so the face reads first |
| Armies | A kit per army and class, varied by a hash of the troop's id |

**Checks.** The dev gallery (`apps/client/dev/portrait-gallery.html`) shows:
- every officer at full size by faction (`?only=factions`);
- every officer at 48 px, in colour and in grey (`?only=small`).

A scratch script compares the 48 px outlines of heroes within each faction; no
pair overlaps past the threshold. Painting one portrait takes about 11 ms
(15.6 ms with the PNG), much as before.

## Corrections the record made

| Officer | Before | Now | Source |
|---|---|---|---|
| 유비 | Short beard | No beard | 三國志 蜀書 周羣傳: 先主無須 |
| 조조 | Cavalry helmet, ordinary size | The 帢 cap, small, low in the frame | 世說新語 容止 with its notes; the 傅子 and 曹瞞傳 notes |
| 악진, 허저, 정욱 | One head position | Small and low; huge; tall | Their recorded sizes |
| 답돈, 누반, 소복연 | Felt caps | Shaved scalps (髡頭) | 後漢書 烏桓傳 |
| 원소 | Court cap | A silk headcloth (縑巾) | 傅子 note in 武帝紀 |
| 장합, 황보숭 | Helmet | 鶡冠 | Both were 中郎將; 輿服志 |
| 순욱 | Court cap | 貂蟬冠 | 侍中 from 196 |
| 감녕 | Bells | Bells and plumes on the back (負毦帶鈴) | 吳書 甘寧傳 |
| 태사자 | Short beard | A full beard, the long arm raised with the bow | 吳書 太史慈傳 |
| 동탁 | Helmet | Two quivers (雙帶兩鞬) | 魏書 董卓傳 |
| 안량 | Helmet | Under his command parasol (麾蓋) | 蜀書 關羽傳 |
| 진등 | Court cap | A flushed face from illness | 魏書 華佗傳 |
| 여포 | — | The pheasant-tailed gold crown and 방천화극 are in the novel (演義 3, 5); only the long opera plumes are later lore | 三國演義 |

## Sources

Game benchmark:
- 4Gamer interviews with Koei's portrait artists 亀井亮太 and 木村勇作 (2025): <https://www.4gamer.net/games/952/G095211/20251121021/>, <https://www.4gamer.net/games/635/G063555/20251031036/>
- Dengeki Online interview on 真・三國無双6 (2011): <https://dengekionline.com/elem/000/000/359/359679/>
- 電ファミニコゲーマー on Musou faction colours (2025): <https://news.denfaminicogamer.jp/news/251008w>
- VGR and PCGamesN interviews on Total War: Three Kingdoms' art: <https://vgr.com/character-depth-total-war-three-kingdoms/>, <https://www.pcgamesn.com/total-war-three-kingdoms/total-war-three-kingdoms-interview-campaign-map-art>
- Koei Tecmo's 三國志 40th-anniversary history: <https://www.gamecity.ne.jp/sangokushi40th/jp/history/>
- Game Watch on 三国志大戦 (2005): <https://game.watch.impress.co.jp/docs/20050516/san.htm>

Histories, read in full on Wikisource:
- 三國志 with 裴松之's notes;
- 後漢書 (including 烏桓傳);
- 續漢書 輿服志;
- 世說新語 容止;
- 三國演義 chapters 1–60.
