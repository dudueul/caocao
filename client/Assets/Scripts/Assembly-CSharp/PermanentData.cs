public class PermanentData
{
	private static PermanentData ms_instance;

	private Mails m_mails;

	private CashShops m_cashShops;

	private GoodsShops m_goodsShops;

	private RecommendShops m_recommendShops;

	private PurchaseEvents m_purchaseEvents;

	private LinkedGoodses m_linkedGoodses;

	private SeasonPassRewards m_seasonPassRewards;

	private RankUpGifts m_rankUpGifts;

	private SeasonPasses m_seasonPasses;

	private RankUpGiftRewards m_rankUpGiftRewards;

	private SelectBoxes m_selectBoxes;

	private BoxShops m_boxShops;

	private ItemRatios m_itemRatios;

	private TimeBoxes m_timeBoxes;

	private TimeBoxRatios m_timeBoxRatios;

	private TimeBoxOrders m_timeBoxOrders;

	private Seasons m_seasons;

	private SeasonRewards m_seasonRewards;

	private StickerSets m_stickerSets;

	private Stickers m_stickers;

	private Items m_items;

	private TreasureMixes m_treasureMixes;

	private TreasureChanges m_treasureChanges;

	private TreasureSales m_treasureSales;

	private ItemLockers m_itemLockers;

	private Shortcuts m_shortcuts;

	private Faces m_faces;

	private Skins m_skins;

	private Heros m_heros;

	private Units m_units;

	private UnitJobLvs m_unitJobLvs;

	private UnitTreeNodes m_unitTreeNodes;

	private UnitTrees m_unitTrees;

	private StartRandomUnits m_startRandomUnits;

	private CharTypes m_charTypes;

	private AutoEquips m_autoEquips;

	private TierUps m_tierUps;

	private Mixes m_mixes;

	private Jobs m_jobs;

	private JobTypes m_jobTypes;

	private JobRelations m_jobRelations;

	private RelationLists m_relationLists;

	private RelationMissions m_relationMissions;

	private UnitRanks m_unitRanks;

	private UnitRankPassives m_unitRankPassives;

	private UnitRankSeasons m_unitRankSeasons;

	private Skills m_skills;

	private LordEffects m_lordEffects;

	private MayorEffects m_mayorEffects;

	private GetSkills m_getSkills;

	private TreasureEnhancePassives m_treasureEnhancePassives;

	private TreasureEnhances m_treasureEnhances;

	private CraftTreasures m_craftTreasures;

	private CraftTreasurePassives m_craftTreasurePassives;

	private Passives m_passives;

	private PassiveLists m_passiveLists;

	private WeakPassives m_weakPassives;

	private JobPassives m_jobPassives;

	private UnitPassives m_unitPassives;

	private ExpandPassiveMelees m_expandPassiveMelees;

	private ExpandPassiveSkills m_expandPassiveSkills;

	private ExpandPassiveItems m_expandPassiveItems;

	private ExpandPassiveDamages m_expandPassiveDamages;

	private EventDailyGifts m_eventDailyGifts;

	private DailyGifts m_dailyGifts;

	private Rewards m_rewards;

	private RewardRanks m_rewardRanks;

	private RewardVips m_rewardVips;

	private RewardLvs m_rewardLvs;

	private SetGoods m_setGoods;

	private SetItems m_setItems;

	private SetTimeBoxes m_setTimeBoxes;

	private BattleMaps m_battleMaps;

	private Clouds m_clouds;

	private Worldmaps m_worldmaps;

	private Towns m_towns;

	private MapObjectLevels m_mapObjectLevels;

	private Storybooks m_storybooks;

	private StoryMissions m_storyMissions;

	private Scenarios m_scenarios;

	private ScenarioFlags m_scenarioFlags;

	private Instances m_instances;

	private SpecialInstances m_specialInstances;

	private WorldBossSeasons m_worldBossSeasons;

	private WorldBossOfferings m_worldBossOfferings;

	private WorldBosses m_worldBosses;

	private WorldBossDmgRewards m_worldBossDmgRewards;

	private WorldBossTiers m_worldBossTiers;

	private WorldBossShops m_worldBossShops;

	private WorldBossPassives m_worldBossPassives;

	private Achievements m_achievements;

	private CommonRewards m_commonRewards;

	private CommonMissions m_commonMissions;

	private Missions m_missions;

	private EventMaps m_eventMaps;

	private MapObjects m_mapObjects;

	private ObjOverrides m_objOverrides;

	private Towers m_towers;

	private AIs m_aIs;

	private AISets m_aISets;

	private AITendencies m_aITendencies;

	private AISkills m_aISkills;

	private SaveFlags m_saveFlags;

	private EventCommands m_eventCommands;

	private TownFunctionGroups m_townFunctionGroups;

	private CapitalFunctionGroups m_capitalFunctionGroups;

	private TownFunctions m_townFunctions;

	private DigitValues m_digitValues;

	private States m_states;

	private Soldiers m_soldiers;

	private SoldierSlots m_soldierSlots;

	private LevelExps m_levelExps;

	private VipFeatures m_vipFeatures;

	private Vips m_vips;

	private Conditions m_conditions;

	private Teches m_teches;

	private Rankings m_rankings;

	private RankingRewards m_rankingRewards;

	private Tiles m_tiles;

	private TileJobDefenses m_tileJobDefenses;

	private TileJobCosts m_tileJobCosts;

	private StringDefines m_stringDefines;

	private ShopOrders m_shopOrders;

	private MarketShops m_marketShops;

	private TradeShops m_tradeShops;

	private VIPShops m_vIPShops;

	private HonorShops m_honorShops;

	private TicketShops m_ticketShops;

	private SecretShops m_secretShops;

	private ExchangeShops m_exchangeShops;

	private Protects m_protects;

	private Weathers m_weathers;

	private Loadings m_loadings;

	private AutoTexts m_autoTexts;

	private Sounds m_sounds;

	private SoundGroups m_soundGroups;

	private TutorialFlows m_tutorialFlows;

	private TutorialUIs m_tutorialUIs;

	private MatchPresets m_matchPresets;

	private BattleFormations m_battleFormations;

	private BattleFormationFuncs m_battleFormationFuncs;

	private AnnihilationWars m_annihilationWars;

	private Tiers m_tiers;

	private Leagues m_leagues;

	private NPCSettings m_nPCSettings;

	private NPCTreasureSettings m_nPCTreasureSettings;

	private NPCNames m_nPCNames;

	private NewThousandUnits m_newThousandUnits;

	private NewThousandBattles m_newThousandBattles;

	private NewBossSettings m_newBossSettings;

	private NewThousandPassives m_newThousandPassives;

	private ThousandBattles m_thousandBattles;

	private BossSettings m_bossSettings;

	private ThousandPassives m_thousandPassives;

	private ThousandSoldiers m_thousandSoldiers;

	private Guilds m_guilds;

	private GuildPresents m_guildPresents;

	private GuildShops m_guildShops;

	private GuildWeeklyMissions m_guildWeeklyMissions;

	private GuildSupports m_guildSupports;

	private BattleFields m_battleFields;

	private BattleFieldNpcGroupTypes m_battleFieldNpcGroupTypes;

	private BattleFieldNpcGroups m_battleFieldNpcGroups;

	private BattleFieldNpcUnits m_battleFieldNpcUnits;

	private SecretPlans m_secretPlans;

	private Raids m_raids;

	private RaidGuides m_raidGuides;

	private RaidTiers m_raidTiers;

	private ReentCosts m_reentCosts;

	private GuildSiegeSlots m_guildSiegeSlots;

	private GuildSiegeTownFacilities m_guildSiegeTownFacilities;

	private GuildSiegeTownFacilityByLVs m_guildSiegeTownFacilityByLVs;

	private GuildSiegeObjectByLVs m_guildSiegeObjectByLVs;

	private GuildSiegeNPCSettings m_guildSiegeNPCSettings;

	private GuildSiegeNPCEquipSettings m_guildSiegeNPCEquipSettings;

	private GuildSiegeCommanders m_guildSiegeCommanders;

	private SpawnerSets m_spawnerSets;

	private BattleRules m_battleRules;

	private Runes m_runes;

	private RunePassives m_runePassives;

	private RunePassiveExps m_runePassiveExps;

	private ReturnSouls m_returnSouls;

	private RuneSets m_runeSets;

	private RuneBoxShops m_runeBoxShops;

	private RuneChangeRatios m_runeChangeRatios;

	private CommonMissiontitles m_commonMissiontitles;

	private ArenaLevels m_arenaLevels;

	private ArenaThemes m_arenaThemes;

	private ArenaStageGroups m_arenaStageGroups;

	private ArenaStages m_arenaStages;

	private ArenaNPCFormations m_arenaNPCFormations;

	private ArenaNPCSettings m_arenaNPCSettings;

	private ArenaNPCTreasures m_arenaNPCTreasures;

	private ArenaPassiveLists m_arenaPassiveLists;

	private ArenaPresets m_arenaPresets;

	private ArenaGetUnits m_arenaGetUnits;

	private ArenaGetTreasures m_arenaGetTreasures;

	private ArenaGetPassives m_arenaGetPassives;

	private ArenaDeckSettings m_arenaDeckSettings;

	private ArenaScenarios m_arenaScenarios;

	private ArenaDialogs m_arenaDialogs;

	private ArenaGetBonuses m_arenaGetBonuses;

	private ArenaAIs m_arenaAIs;

	private ArenaSynergies m_arenaSynergies;

	private ArenaSynergyLists m_arenaSynergyLists;

	private HotTimeRewards m_hotTimeRewards;

	private CompetitionModes m_competitionModes;

	private CompetitionMaps m_competitionMaps;

	private UserSpells m_userSpells;

	private BrawlRotations m_brawlRotations;

	private GameModeRules m_gameModeRules;

	private GameModeRuleItems m_gameModeRuleItems;

	private StaffRolls m_staffRolls;

	private StoryStampBoxes m_storyStampBoxes;

	private GuildWarTiles m_guildWarTiles;

	private GuildWarTileTypes m_guildWarTileTypes;

	private GuildWarTileEffects m_guildWarTileEffects;

	private GuildWarTileCorpsCosts m_guildWarTileCorpsCosts;

	private GuildWarTowns m_guildWarTowns;

	private GuildWarTownRewards m_guildWarTownRewards;

	private GuildWarPlaces m_guildWarPlaces;

	private GuildWarBuildings m_guildWarBuildings;

	private GuildWarBuildingBattleDatas m_guildWarBuildingBattleDatas;

	private GuildWarCorpsJobs m_guildWarCorpsJobs;

	private GuildWarSiegeWeapons m_guildWarSiegeWeapons;

	private GuildWarCombatActiveFunctions m_guildWarCombatActiveFunctions;

	private GuildWarInteractiveFunctions m_guildWarInteractiveFunctions;

	private GuildWarCombatActions m_guildWarCombatActions;

	private GuildWarPassives m_guildWarPassives;

	private GuildWarPassiveAttributes m_guildWarPassiveAttributes;

	private GuildWarPassiveLists m_guildWarPassiveLists;

	private GuildWarLevelExps m_guildWarLevelExps;

	private GuildWarWorlds m_guildWarWorlds;

	private GuildWarContributionRewards m_guildWarContributionRewards;

	private GuildWarConstants m_guildWarConstants;

	private GuildWarCombatMessages m_guildWarCombatMessages;

	private GuildWarPings m_guildWarPings;

	private EventRewards m_eventRewards;

	public static PermanentData instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	public Mails mails
	{
		get
		{
			return null;
		}
	}

	public CashShops cashShops
	{
		get
		{
			return null;
		}
	}

	public GoodsShops goodsShops
	{
		get
		{
			return null;
		}
	}

	public RecommendShops recommendShops
	{
		get
		{
			return null;
		}
	}

	public PurchaseEvents purchaseEvents
	{
		get
		{
			return null;
		}
	}

	public LinkedGoodses linkedGoodses
	{
		get
		{
			return null;
		}
	}

	public SeasonPassRewards seasonPassRewards
	{
		get
		{
			return null;
		}
	}

	public RankUpGifts rankUpGifts
	{
		get
		{
			return null;
		}
	}

	public SeasonPasses seasonPasses
	{
		get
		{
			return null;
		}
	}

	public RankUpGiftRewards rankUpGiftRewards
	{
		get
		{
			return null;
		}
	}

	public SelectBoxes selectBoxes
	{
		get
		{
			return null;
		}
	}

	public BoxShops boxShops
	{
		get
		{
			return null;
		}
	}

	public ItemRatios itemRatios
	{
		get
		{
			return null;
		}
	}

	public TimeBoxes timeBoxes
	{
		get
		{
			return null;
		}
	}

	public TimeBoxRatios timeBoxRatios
	{
		get
		{
			return null;
		}
	}

	public TimeBoxOrders timeBoxOrders
	{
		get
		{
			return null;
		}
	}

	public Seasons seasons
	{
		get
		{
			return null;
		}
	}

	public SeasonRewards seasonRewards
	{
		get
		{
			return null;
		}
	}

	public StickerSets stickerSets
	{
		get
		{
			return null;
		}
	}

	public Stickers stickers
	{
		get
		{
			return null;
		}
	}

	public Items items
	{
		get
		{
			return null;
		}
	}

	public TreasureMixes treasureMixes
	{
		get
		{
			return null;
		}
	}

	public TreasureChanges treasureChanges
	{
		get
		{
			return null;
		}
	}

	public TreasureSales treasureSales
	{
		get
		{
			return null;
		}
	}

	public ItemLockers itemLockers
	{
		get
		{
			return null;
		}
	}

	public Shortcuts shortcuts
	{
		get
		{
			return null;
		}
	}

	public Faces faces
	{
		get
		{
			return null;
		}
	}

	public Skins skins
	{
		get
		{
			return null;
		}
	}

	public Heros heros
	{
		get
		{
			return null;
		}
	}

	public Units units
	{
		get
		{
			return null;
		}
	}

	public UnitJobLvs unitJobLvs
	{
		get
		{
			return null;
		}
	}

	public UnitTreeNodes unitTreeNodes
	{
		get
		{
			return null;
		}
	}

	public UnitTrees unitTrees
	{
		get
		{
			return null;
		}
	}

	public StartRandomUnits startRandomUnits
	{
		get
		{
			return null;
		}
	}

	public CharTypes charTypes
	{
		get
		{
			return null;
		}
	}

	public AutoEquips autoEquips
	{
		get
		{
			return null;
		}
	}

	public TierUps tierUps
	{
		get
		{
			return null;
		}
	}

	public Mixes mixes
	{
		get
		{
			return null;
		}
	}

	public Jobs jobs
	{
		get
		{
			return null;
		}
	}

	public JobTypes jobTypes
	{
		get
		{
			return null;
		}
	}

	public JobRelations jobRelations
	{
		get
		{
			return null;
		}
	}

	public RelationLists relationLists
	{
		get
		{
			return null;
		}
	}

	public RelationMissions relationMissions
	{
		get
		{
			return null;
		}
	}

	public UnitRanks unitRanks
	{
		get
		{
			return null;
		}
	}

	public UnitRankPassives unitRankPassives
	{
		get
		{
			return null;
		}
	}

	public UnitRankSeasons unitRankSeasons
	{
		get
		{
			return null;
		}
	}

	public Skills skills
	{
		get
		{
			return null;
		}
	}

	public LordEffects lordEffects
	{
		get
		{
			return null;
		}
	}

	public MayorEffects mayorEffects
	{
		get
		{
			return null;
		}
	}

	public GetSkills getSkills
	{
		get
		{
			return null;
		}
	}

	public TreasureEnhancePassives treasureEnhancePassives
	{
		get
		{
			return null;
		}
	}

	public TreasureEnhances treasureEnhances
	{
		get
		{
			return null;
		}
	}

	public CraftTreasures craftTreasures
	{
		get
		{
			return null;
		}
	}

	public CraftTreasurePassives craftTreasurePassives
	{
		get
		{
			return null;
		}
	}

	public Passives passives
	{
		get
		{
			return null;
		}
	}

	public PassiveLists passiveLists
	{
		get
		{
			return null;
		}
	}

	public WeakPassives weakPassives
	{
		get
		{
			return null;
		}
	}

	public JobPassives jobPassives
	{
		get
		{
			return null;
		}
	}

	public UnitPassives unitPassives
	{
		get
		{
			return null;
		}
	}

	public ExpandPassiveMelees expandPassiveMelees
	{
		get
		{
			return null;
		}
	}

	public ExpandPassiveSkills expandPassiveSkills
	{
		get
		{
			return null;
		}
	}

	public ExpandPassiveItems expandPassiveItems
	{
		get
		{
			return null;
		}
	}

	public ExpandPassiveDamages expandPassiveDamages
	{
		get
		{
			return null;
		}
	}

	public EventDailyGifts eventDailyGifts
	{
		get
		{
			return null;
		}
	}

	public DailyGifts dailyGifts
	{
		get
		{
			return null;
		}
	}

	public Rewards rewards
	{
		get
		{
			return null;
		}
	}

	public RewardRanks rewardRanks
	{
		get
		{
			return null;
		}
	}

	public RewardVips rewardVips
	{
		get
		{
			return null;
		}
	}

	public RewardLvs rewardLvs
	{
		get
		{
			return null;
		}
	}

	public SetGoods setGoods
	{
		get
		{
			return null;
		}
	}

	public SetItems setItems
	{
		get
		{
			return null;
		}
	}

	public SetTimeBoxes setTimeBoxes
	{
		get
		{
			return null;
		}
	}

	public BattleMaps battleMaps
	{
		get
		{
			return null;
		}
	}

	public Clouds clouds
	{
		get
		{
			return null;
		}
	}

	public Worldmaps worldmaps
	{
		get
		{
			return null;
		}
	}

	public Towns towns
	{
		get
		{
			return null;
		}
	}

	public MapObjectLevels mapObjectLevels
	{
		get
		{
			return null;
		}
	}

	public Storybooks storybooks
	{
		get
		{
			return null;
		}
	}

	public StoryMissions storyMissions
	{
		get
		{
			return null;
		}
	}

	public Scenarios scenarios
	{
		get
		{
			return null;
		}
	}

	public ScenarioFlags scenarioFlags
	{
		get
		{
			return null;
		}
	}

	public Instances instances
	{
		get
		{
			return null;
		}
	}

	public SpecialInstances specialInstances
	{
		get
		{
			return null;
		}
	}

	public WorldBossSeasons worldBossSeasons
	{
		get
		{
			return null;
		}
	}

	public WorldBossOfferings worldBossOfferings
	{
		get
		{
			return null;
		}
	}

	public WorldBosses worldBosses
	{
		get
		{
			return null;
		}
	}

	public WorldBossDmgRewards worldBossDmgRewards
	{
		get
		{
			return null;
		}
	}

	public WorldBossTiers worldBossTiers
	{
		get
		{
			return null;
		}
	}

	public WorldBossShops worldBossShops
	{
		get
		{
			return null;
		}
	}

	public WorldBossPassives worldBossPassives
	{
		get
		{
			return null;
		}
	}

	public Achievements achievements
	{
		get
		{
			return null;
		}
	}

	public CommonRewards commonRewards
	{
		get
		{
			return null;
		}
	}

	public CommonMissions commonMissions
	{
		get
		{
			return null;
		}
	}

	public Missions missions
	{
		get
		{
			return null;
		}
	}

	public EventMaps eventMaps
	{
		get
		{
			return null;
		}
	}

	public MapObjects mapObjects
	{
		get
		{
			return null;
		}
	}

	public ObjOverrides objOverrides
	{
		get
		{
			return null;
		}
	}

	public Towers towers
	{
		get
		{
			return null;
		}
	}

	public AIs aIs
	{
		get
		{
			return null;
		}
	}

	public AISets aISets
	{
		get
		{
			return null;
		}
	}

	public AITendencies aITendencies
	{
		get
		{
			return null;
		}
	}

	public AISkills aISkills
	{
		get
		{
			return null;
		}
	}

	public SaveFlags saveFlags
	{
		get
		{
			return null;
		}
	}

	public EventCommands eventCommands
	{
		get
		{
			return null;
		}
	}

	public TownFunctionGroups townFunctionGroups
	{
		get
		{
			return null;
		}
	}

	public CapitalFunctionGroups capitalFunctionGroups
	{
		get
		{
			return null;
		}
	}

	public TownFunctions townFunctions
	{
		get
		{
			return null;
		}
	}

	public DigitValues digitValues
	{
		get
		{
			return null;
		}
	}

	public States states
	{
		get
		{
			return null;
		}
	}

	public Soldiers soldiers
	{
		get
		{
			return null;
		}
	}

	public SoldierSlots soldierSlots
	{
		get
		{
			return null;
		}
	}

	public LevelExps levelExps
	{
		get
		{
			return null;
		}
	}

	public VipFeatures vipFeatures
	{
		get
		{
			return null;
		}
	}

	public Vips vips
	{
		get
		{
			return null;
		}
	}

	public Conditions conditions
	{
		get
		{
			return null;
		}
	}

	public Teches teches
	{
		get
		{
			return null;
		}
	}

	public Rankings rankings
	{
		get
		{
			return null;
		}
	}

	public RankingRewards rankingRewards
	{
		get
		{
			return null;
		}
	}

	public Tiles tiles
	{
		get
		{
			return null;
		}
	}

	public TileJobDefenses tileJobDefenses
	{
		get
		{
			return null;
		}
	}

	public TileJobCosts tileJobCosts
	{
		get
		{
			return null;
		}
	}

	public StringDefines stringDefines
	{
		get
		{
			return null;
		}
	}

	public ShopOrders shopOrders
	{
		get
		{
			return null;
		}
	}

	public MarketShops marketShops
	{
		get
		{
			return null;
		}
	}

	public TradeShops tradeShops
	{
		get
		{
			return null;
		}
	}

	public VIPShops vIPShops
	{
		get
		{
			return null;
		}
	}

	public HonorShops honorShops
	{
		get
		{
			return null;
		}
	}

	public TicketShops ticketShops
	{
		get
		{
			return null;
		}
	}

	public SecretShops secretShops
	{
		get
		{
			return null;
		}
	}

	public ExchangeShops exchangeShops
	{
		get
		{
			return null;
		}
	}

	public Protects protects
	{
		get
		{
			return null;
		}
	}

	public Weathers weathers
	{
		get
		{
			return null;
		}
	}

	public Loadings loadings
	{
		get
		{
			return null;
		}
	}

	public AutoTexts autoTexts
	{
		get
		{
			return null;
		}
	}

	public Sounds sounds
	{
		get
		{
			return null;
		}
	}

	public SoundGroups soundGroups
	{
		get
		{
			return null;
		}
	}

	public TutorialFlows tutorialFlows
	{
		get
		{
			return null;
		}
	}

	public TutorialUIs tutorialUIs
	{
		get
		{
			return null;
		}
	}

	public MatchPresets matchPresets
	{
		get
		{
			return null;
		}
	}

	public BattleFormations battleFormations
	{
		get
		{
			return null;
		}
	}

	public BattleFormationFuncs battleFormationFuncs
	{
		get
		{
			return null;
		}
	}

	public AnnihilationWars annihilationWars
	{
		get
		{
			return null;
		}
	}

	public Tiers tiers
	{
		get
		{
			return null;
		}
	}

	public Leagues leagues
	{
		get
		{
			return null;
		}
	}

	public NPCSettings nPCSettings
	{
		get
		{
			return null;
		}
	}

	public NPCTreasureSettings nPCTreasureSettings
	{
		get
		{
			return null;
		}
	}

	public NPCNames nPCNames
	{
		get
		{
			return null;
		}
	}

	public NewThousandUnits newThousandUnits
	{
		get
		{
			return null;
		}
	}

	public NewThousandBattles newThousandBattles
	{
		get
		{
			return null;
		}
	}

	public NewBossSettings newBossSettings
	{
		get
		{
			return null;
		}
	}

	public NewThousandPassives newThousandPassives
	{
		get
		{
			return null;
		}
	}

	public ThousandBattles thousandBattles
	{
		get
		{
			return null;
		}
	}

	public BossSettings bossSettings
	{
		get
		{
			return null;
		}
	}

	public ThousandPassives thousandPassives
	{
		get
		{
			return null;
		}
	}

	public ThousandSoldiers thousandSoldiers
	{
		get
		{
			return null;
		}
	}

	public Guilds guilds
	{
		get
		{
			return null;
		}
	}

	public GuildPresents guildPresents
	{
		get
		{
			return null;
		}
	}

	public GuildShops guildShops
	{
		get
		{
			return null;
		}
	}

	public GuildWeeklyMissions guildWeeklyMissions
	{
		get
		{
			return null;
		}
	}

	public GuildSupports guildSupports
	{
		get
		{
			return null;
		}
	}

	public BattleFields battleFields
	{
		get
		{
			return null;
		}
	}

	public BattleFieldNpcGroupTypes battleFieldNpcGroupTypes
	{
		get
		{
			return null;
		}
	}

	public BattleFieldNpcGroups battleFieldNpcGroups
	{
		get
		{
			return null;
		}
	}

	public BattleFieldNpcUnits battleFieldNpcUnits
	{
		get
		{
			return null;
		}
	}

	public SecretPlans secretPlans
	{
		get
		{
			return null;
		}
	}

	public Raids raids
	{
		get
		{
			return null;
		}
	}

	public RaidGuides raidGuides
	{
		get
		{
			return null;
		}
	}

	public RaidTiers raidTiers
	{
		get
		{
			return null;
		}
	}

	public ReentCosts reentCosts
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeSlots guildSiegeSlots
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeTownFacilities guildSiegeTownFacilities
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeTownFacilityByLVs guildSiegeTownFacilityByLVs
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeObjectByLVs guildSiegeObjectByLVs
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeNPCSettings guildSiegeNPCSettings
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeNPCEquipSettings guildSiegeNPCEquipSettings
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeCommanders guildSiegeCommanders
	{
		get
		{
			return null;
		}
	}

	public SpawnerSets spawnerSets
	{
		get
		{
			return null;
		}
	}

	public BattleRules battleRules
	{
		get
		{
			return null;
		}
	}

	public Runes runes
	{
		get
		{
			return null;
		}
	}

	public RunePassives runePassives
	{
		get
		{
			return null;
		}
	}

	public RunePassiveExps runePassiveExps
	{
		get
		{
			return null;
		}
	}

	public ReturnSouls returnSouls
	{
		get
		{
			return null;
		}
	}

	public RuneSets runeSets
	{
		get
		{
			return null;
		}
	}

	public RuneBoxShops runeBoxShops
	{
		get
		{
			return null;
		}
	}

	public RuneChangeRatios runeChangeRatios
	{
		get
		{
			return null;
		}
	}

	public CommonMissiontitles commonMissiontitles
	{
		get
		{
			return null;
		}
	}

	public ArenaLevels arenaLevels
	{
		get
		{
			return null;
		}
	}

	public ArenaThemes arenaThemes
	{
		get
		{
			return null;
		}
	}

	public ArenaStageGroups arenaStageGroups
	{
		get
		{
			return null;
		}
	}

	public ArenaStages arenaStages
	{
		get
		{
			return null;
		}
	}

	public ArenaNPCFormations arenaNPCFormations
	{
		get
		{
			return null;
		}
	}

	public ArenaNPCSettings arenaNPCSettings
	{
		get
		{
			return null;
		}
	}

	public ArenaNPCTreasures arenaNPCTreasures
	{
		get
		{
			return null;
		}
	}

	public ArenaPassiveLists arenaPassiveLists
	{
		get
		{
			return null;
		}
	}

	public ArenaPresets arenaPresets
	{
		get
		{
			return null;
		}
	}

	public ArenaGetUnits arenaGetUnits
	{
		get
		{
			return null;
		}
	}

	public ArenaGetTreasures arenaGetTreasures
	{
		get
		{
			return null;
		}
	}

	public ArenaGetPassives arenaGetPassives
	{
		get
		{
			return null;
		}
	}

	public ArenaDeckSettings arenaDeckSettings
	{
		get
		{
			return null;
		}
	}

	public ArenaScenarios arenaScenarios
	{
		get
		{
			return null;
		}
	}

	public ArenaDialogs arenaDialogs
	{
		get
		{
			return null;
		}
	}

	public ArenaGetBonuses arenaGetBonuses
	{
		get
		{
			return null;
		}
	}

	public ArenaAIs arenaAIs
	{
		get
		{
			return null;
		}
	}

	public ArenaSynergies arenaSynergies
	{
		get
		{
			return null;
		}
	}

	public ArenaSynergyLists arenaSynergyLists
	{
		get
		{
			return null;
		}
	}

	public HotTimeRewards hotTimeRewards
	{
		get
		{
			return null;
		}
	}

	public CompetitionModes competitionModes
	{
		get
		{
			return null;
		}
	}

	public CompetitionMaps competitionMaps
	{
		get
		{
			return null;
		}
	}

	public UserSpells userSpells
	{
		get
		{
			return null;
		}
	}

	public BrawlRotations brawlRotations
	{
		get
		{
			return null;
		}
	}

	public GameModeRules gameModeRules
	{
		get
		{
			return null;
		}
	}

	public GameModeRuleItems gameModeRuleItems
	{
		get
		{
			return null;
		}
	}

	public StaffRolls staffRolls
	{
		get
		{
			return null;
		}
	}

	public StoryStampBoxes storyStampBoxes
	{
		get
		{
			return null;
		}
	}

	public GuildWarTiles guildWarTiles
	{
		get
		{
			return null;
		}
	}

	public GuildWarTileTypes guildWarTileTypes
	{
		get
		{
			return null;
		}
	}

	public GuildWarTileEffects guildWarTileEffects
	{
		get
		{
			return null;
		}
	}

	public GuildWarTileCorpsCosts guildWarTileCorpsCosts
	{
		get
		{
			return null;
		}
	}

	public GuildWarTowns guildWarTowns
	{
		get
		{
			return null;
		}
	}

	public GuildWarTownRewards guildWarTownRewards
	{
		get
		{
			return null;
		}
	}

	public GuildWarPlaces guildWarPlaces
	{
		get
		{
			return null;
		}
	}

	public GuildWarBuildings guildWarBuildings
	{
		get
		{
			return null;
		}
	}

	public GuildWarBuildingBattleDatas guildWarBuildingBattleDatas
	{
		get
		{
			return null;
		}
	}

	public GuildWarCorpsJobs guildWarCorpsJobs
	{
		get
		{
			return null;
		}
	}

	public GuildWarSiegeWeapons guildWarSiegeWeapons
	{
		get
		{
			return null;
		}
	}

	public GuildWarCombatActiveFunctions guildWarCombatActiveFunctions
	{
		get
		{
			return null;
		}
	}

	public GuildWarInteractiveFunctions guildWarInteractiveFunctions
	{
		get
		{
			return null;
		}
	}

	public GuildWarCombatActions guildWarCombatActions
	{
		get
		{
			return null;
		}
	}

	public GuildWarPassives guildWarPassives
	{
		get
		{
			return null;
		}
	}

	public GuildWarPassiveAttributes guildWarPassiveAttributes
	{
		get
		{
			return null;
		}
	}

	public GuildWarPassiveLists guildWarPassiveLists
	{
		get
		{
			return null;
		}
	}

	public GuildWarLevelExps guildWarLevelExps
	{
		get
		{
			return null;
		}
	}

	public GuildWarWorlds guildWarWorlds
	{
		get
		{
			return null;
		}
	}

	public GuildWarContributionRewards guildWarContributionRewards
	{
		get
		{
			return null;
		}
	}

	public GuildWarConstants guildWarConstants
	{
		get
		{
			return null;
		}
	}

	public GuildWarCombatMessages guildWarCombatMessages
	{
		get
		{
			return null;
		}
	}

	public GuildWarPings guildWarPings
	{
		get
		{
			return null;
		}
	}

	public EventRewards eventRewards
	{
		get
		{
			return null;
		}
	}

	private PermanentData()
	{
	}

	[AppSealingSecurityMethod]
	public void Reload(string password)
	{
	}

	public void PostProcess()
	{
	}
}
