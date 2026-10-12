# CAOCAO Client Architecture — Evidence Log (Phase 1)

All findings below are extracted from `_release/caocao.apk` and dumped with
Il2CppDumper 6.7.46 / jadx 1.5.6 / AssetRipper 2.0.0. Every claim cites its
source. Evidence sources:

- `E-DUMP`  = `extracted/il2cpp/dump.cs` (707,942 lines, 12,414 types, 163,902 methods)
- `E-LIT`   = `extracted/il2cpp/stringliteral.json` (19,897 literals)
- `E-JADX`  = `extracted/jadx/sources/` (5,205 Java files)
- `E-GGM`   = `extracted/apk/assets/bin/Data/globalgamemanagers`
- `E-MAN`   = binary AndroidManifest

## 1. Build identity

| Fact | Value | Source |
|---|---|---|
| Package | `com.nexon.caocao` | E-MAN |
| Engine | Unity **2018.2.16f1** (serialized file version 17) | E-GGM header, data file headers |
| Scripting backend | **IL2CPP**, metadata version **24** (magic `0xFAB11BAF`) | `global-metadata.dat` bytes 0..4 |
| ABIs | arm64-v8a (75.8MB libil2cpp.so), armv7 | `lib/` |
| CodeRegistration / MetadataRegistration | `0x45d5ce8` / `0x45d5d58` (auto-located) | Il2CppDumper run log |
| Third-party Unity plugins | **PlayMaker** (1,024 types), **tk2d / 2D Toolkit**, iTween, WebSocketSharp, Socket.IO (Unity), LitJson, CodeStage.AntiCheat.ObscuredTypes | E-DUMP namespaces/type grep |
| Java SDKs | Nexon NPA (platform account), Naver GLink, Tapjoy, Facebook, Adjust, Crashlytics/Fabric, Google Billing, Glide, twitter4j, universal-image-loader | E-JADX package census |
| Native plugins | `libngsm.so` (Nexon game security), `libBlueDoveMediaRender.so` (video) | `lib/` |

## 2. Scene inventory (18, from E-GGM)

Login, Loading, Entrance, Town, WorldMap, ScenarioMap, StageSelect, BattleMap,
Instance, Arena, Duel, Competition, GuildWar, GuildWarLobby, GuildSiegeWar,
AnnihilationWar, ThousandBattle, Recon, Restart, Movie.

## 3. Core singletons (E-DUMP `class X : Singleton<T>`)

GameManager, GameFlowController, ResourceManager, BattlePlayManager,
BattleFieldController, SceneDataPipe, CentralTimer, CoordinateSystem,
Chatting, MultiplayManager, HeartBeats, HttpRequest, NPAController,
SoundManager, BGMPlayer, VoiceChatController, WorldMapControlFlow,
GuildSiegeWarFlowController, PendingEffectManager, ControlEventFlow,
CommonMissionsDistribution, MailChecker, FairRuleManager (anti-cheat),
UserOptions, TutorialUIController, ShortCutRun, Periodic, RaidManager,
PedlarStateManager, FontManager, ImageDownloader, SyncStreamingAssetsLoader,
TSIBridge, CaocaoCheatTool (built-in dev cheats), CaocaoToyPushManager,
ZZTapjoyPlacement, NPGpgsController, NetworkIndicator, VideoCaptureCtrl,
ConsumingGoods/Items/PassivePoint popup controllers.

## 4. Networking layer (verified facts)

- Transport: **Socket.IO over WebSocket**, Engine.IO v4.
  - `SocketIOComponent` (namespace `SocketIO`, TypeDefIndex 4688) fields:
    `url`, `autoConnect`, `ackExpirationTime`, `pingInterval:int`,
    `pingTimeout:double`, `sid`, `handlers:Dictionary<string, List<Action<SocketIOEvent>>>`,
    `ackList`, `packetId`, `eventQueue`, `ackQueue`.
  - URL template literal: `ws://{0}/socket.io/?EIO=4&transport=websocket` (E-LIT).
- Packet payload = **JSON** built by `PacketUtil` (`JsonString(object[] packet)`,
  `SerializeArray`, `SerializeObject`) using LitJson `JsonWriter`. Games send
  `object[]` arrays: first element is the command/message id (see `MakeNewCommand(EnumData.DuelCommands cmdType, object[] args)`).
- `IServerSetting`: `NAME`, `GAME_SERVER_HOST_URL`, `CDN_URL`,
  `USE_PACKET_ENCRYPT`, `NPA_SERVICE_ID`, `GOOGLE_PUSH_SENDER_ID`, `S3_QA_URL`.
  Environments: LOCAL, DEV, DEV_AS_LIVE, STAGE, LIVE with `GetFallback()` chain.
- Recovered endpoints (E-LIT):
  - Game: `http://nxm-caocao.nexon.com:9080`, `http://nxm-caocao-ww.nexon.com:9080`,
    JP/TW dev: `http://ec2-13-112-43-40.ap-northeast-1.compute.amazonaws.com:9080`, etc. (port 9080/9082)
  - CDN: `https://nxm-caocao-ww.akamaized.net` (+ `-jp`, `-tw`), QA: `https://nxk-caocao-qa.s3.amazonaws.com`, `https://nxm-caocao-*-qa.s3.amazonaws.com`
  - Notice/terms: `https://m.nexon.com/notice/get/1?client_id=...`, `https://m.nexon.com/probability?client_id=MTI0ODI5NzQwMA`
- `HeartBeats : Singleton<HeartBeats>` — keepalive.
- `MultiplayManager : Singleton<MultiplayManager>` — matchmaking
  (`RequestMatchmaking`, `CancelMatchmaking`, `DeniedInvitingInfo`,
  `RollbackBattle`, `ServerInfo`), realtime PvP.
- `USE_PACKET_ENCRYPT` + `RijndaelManaged` / `DecryptedInfo` (TypeDefIndex 10916)
  and `FileUtil.GetDecryptedBytes(bytes, password, encoding)` — AES (Rijndael)
  encryption for packets and/or local files. Key derivation details pending Ghidra.
- `FairRuleManager` + `AntiCheatingObserver` + CodeStage ObscuredTypes — client anti-tamper.

## 5. Battle / duel command protocol (start)

`EnumData.DuelCommands` (TypeDefIndex 6402):
StartBattle=0, AppearHero=1, ShowStartLabel=2, DuelActions=3, DuelAttack1=4,
DuelAttack2=5, Dialogue=6, Die=7, EndBattle=8.

`EnumData.DuelAction` (6403): noneD=0, rotateD, forwardD, forwardSmallD,
backSmallD, winD, defendD, hurtD, castD, attackD, doubleAttackD, frightenD,
tiredD, retreatD, dance1D, dance2D, translate=16, move=17, ...

Domain enums in `EnumData`: Kingdoms, SkillTypes, SkillGrade, SkillEffect,
AttackType/AtkType, damageType/healType/cancelType/accuType, aiType,
ItemSlotType/ItemType/ItemEffect, DamageEffectType, TargetType, CountryCode.

Battle-end senders (PlayMaker actions, E-DUMP): `FinishArena.SendArenaBattleEnd`,
`FinishInstance.SendInstanceBattleEnd`, `FinishRaid.SendRaidBattleEnd`,
`FinishScenario.SendScenarioBattleEnd`, `FinishSiege.SendMultiBattleEnd`,
`FinishGuildWar`, `FinishAnnihilationWar`, `FinishThousandBattle`,
`FinishWorldBoss`, `FinishCompetition`, `FinishBattleField`,
`FinishRelationBattle`, `FinishSham`.

## 6. Data layer

- `PImpl.DataTableImpl<KeyType, DataType, ClientDataType, FlatBufferDataType,
  FlatBufferKeyType> : DataTable<KeyType,DataType>` — game data tables are
  FlatBuffers, loaded by key. `GuildWarFlatBuffer` namespace (67 types) holds
  generated FlatBuffers accessors.
- 3,533 hash-named files in `assets/bin/Data/` — Unity SerializedFiles v17
  (2018.2.16f1) holding assets + likely the FlatBuffer data tables.
- `SyncStreamingAssetsLoader` — loads from StreamingAssets.
- Local persistence encrypted with Rijndael (`GetDecryptedStringFromFile`).

## 7. Open questions (for Phase 2 REA/Ghidra)

1. Socket.IO **event names** (string literals like "login", "move", ...) and
   per-event payload schemas.
2. Packet encryption key derivation when `USE_PACKET_ENCRYPT` is true.
3. Exact battle resolution logic (`BattlePlayManager`, `FairRuleManager`).
4. FlatBuffer schemas for data tables (`GuildWarFlatBuffer`, PImpl) — regenerate `.fbs`.
5. NPA auth flow (Java `NPAccount` + `NPAController` bridge).
6. What `libngsm.so` guards (likely packet sign / anti-tamper).

## 8. Addendum (Phase 1 recon, post-dump findings)

- **GuildWar realtime state uses FlatBuffers in motion.** `GuildWarFlatBuffer`
  (67 wire types) + 71 client cache types implementing `IDataType<T>`
  (TileData, BuildingData, CorpsData, CorpsSiegeWeaponData, VisionData,
  GuildData, UserData, WorldData...). The generic loader
  `PImpl.DataTableImpl<KeyType, DataType, ClientDataType, FlatBufferDataType,
  FlatBufferKeyType> : DataTable<K,D>` converts wire structs to client objects.
  => The Node/Socket.IO server streamed guild-war world state as FlatBuffers.
- General balance data (items/units/skills) is NOT in GuildWarFlatBuffer; it is
  either baked in the 3,518 container files or CDN-delivered — resolve in Phase 2.
- `TileKey` is a struct key type (`IKeyType<TileKey>`) — composite keys, not ints.
- ExportedProject/Assets/Scripts contains 5,978 C# stubs (field-correct,
  body-empty) with original GUIDs — the rebuild skeleton.
- REA Android provider verified end-to-end (jadx-headless-mcp 0.7.1, pinned
  SHA-256 6e5eacf5...). CaoCaoUnityActivity.onCreate: Adjust deep-link handling
  then NPAccount lifecycle registration.

## 9. Data location findings (Phase 1, definitive)

- `Item : PermanentBase` (TypeDefIndex 11545) is a `[SerializeField]` asset:
  m_id, m_tier, m_slot, m_itemType, m_boxType, m_price, m_gold, m_tresureType,
  m_enhanceType, m_unitLv, m_itemEffect, m_effectValue, m_maxValue, m_typeValue,
  m_payType, m_passive:List<ObscuredInt>, m_passiveVal, m_skillId... ALL values
  wrapped in **CodeStage.ObscuredTypes** (ObscuredInt/ObscuredString).
  => Balance data IS baked into the build's serialized files (the 3,518), and
  AssetRipper exports the *obfuscated* fields — rebuild must decode/replace
  ObscuredTypes (their encryption key is derived per-asset; Phase 2 decompiles
  the getter paths).
- `UnitData : UnitDataForRenderer` similarly baked.
- No ItemData/SkillData server-table types exist client-side — only GuildWar
  world-state uses the FlatBuffer DataTable pipeline. Conclusion: **most
  authoritative game data lives in the client build** (typical for this era);
  server sends player-specific state (inventory, quests, gacha results) over
  Socket.IO JSON.
