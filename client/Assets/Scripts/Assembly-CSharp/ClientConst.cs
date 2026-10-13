using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class ClientConst
{
	public static class CombatPower
	{
		public static class Almighty
		{
			// [RE] recovered from decompiled cctor @0x210a88c: {100,100,75,65,60}
			public static readonly ObscuredInt[] coef = new ObscuredInt[] { 100, 100, 75, 65, 60 };
		}

		public static class MilitaryOfficier
		{
			// [RE] recovered from decompiled cctor: {100,100,25,85,85}
			// (atk/def-dominant — military officers)
			public static readonly ObscuredInt[] coef = new ObscuredInt[] { 100, 100, 25, 85, 85 };
		}

		public static class CivilOfficer
		{
			// [RE] recovered from decompiled cctor: {10,75,135,50,100}
			// (intelligence-dominant 1.35 — civil officers)
			public static readonly ObscuredInt[] coef = new ObscuredInt[] { 10, 75, 135, 50, 100 };
		}

		public static class ETC
		{
			// [RE] recovered from decompiled cctor: flat 100s
			public static readonly ObscuredInt[] coef = new ObscuredInt[] { 100, 100, 100, 100, 100 };
		}
	}

	public const string titleGameObjectName = "Title_KR";

	public const int goodConditionClearMaxRate = 10;

	public const int goodConditionClearMinRate = 60;

	public const int conditionPhaseMin = 10;

	public const int conditionPhaseMax = 15;

	public const int minPoisonHp = 1;

	public const int maxTurnHealHp = 100;

	public const int maxTurnHealHpPercent = 100;

	public const int maxTurnHealMp = 100;

	public const int maxTurnHealMpPercent = 100;

	public const int doubleAttackRate = 100;

	public const int doubleAttackEffectRate = 75;

	public const int doubleSkillRate = 100;

	public const int doubleSkillEffectRate = 75;

	public const int minHitRatio = 30;

	public const int maxHitRatio = 100;

	public const int minSkillRatio = 30;

	public const int maxSkillRatio = 100;

	public const int maxUndoActionVisualRatio = 95;

	public const float maxUndoActionSuccessRate = 30f;

	public const int missingNormalATTDamage = 1;

	public const int getArmorExpNormalATTDefSuxX = 1;

	public const int getArmorExpNormalATTATLVLessThanDELV = 3;

	public const int getArmorExpNormalATTATLVHighThanDELV = 4;

	public const int getArmorExpARTDefSuxX = 1;

	public const int getArmorExpARTATTATLVLessThanDELV = 2;

	public const int getArmorExpARTATTATLVHighThanDELV = 3;

	public const int getWeaponExpNormalATTFail = 1;

	public const int getWeaponExpNormalATTATLVLessThanDELV = 2;

	public const int getWeaponExpNormalATTATLVHighThanDELV = 3;

	public const int getWeaponExpARTATTFail = 1;

	public const int getWeaponExpARTATTATLVLessThanDELV = 2;

	public const int getWeaponExpARTATTATLVHighThanDELV = 3;

	public const float Exp_NoHit = 1f;

	public const float Exp_ArtImitation = 2f;

	public const float MinExp_DamageType_Atk_L_Def = 20f;

	public const float MaxExp_DamageType_Atk_L_Def = 150f;

	public const float MinExp_DamageType_Atk_GE_Def = 2f;

	public const float MaxExp_DamageType_Atk_GE_Def = 20f;

	public const float MinExp_DamageType_Alive_Atk_L_Def = 10f;

	public const float MaxExp_DamageType_Alive_Atk_L_Def = 50f;

	public const float MinExp_DamageType_Alive_Atk_GE_Def = 2f;

	public const float MaxExp_DamageType_Alive_Atk_GE_Def = 10f;

	public const float MinExp_NoDamage_Atk_L_Def = 10f;

	public const float MaxExp_NoDamage_Atk_L_Def = 25f;

	public const float MinExp_NoDamage_Atk_GE_Def = 2f;

	public const float MaxExp_NoDamage_Atk_GE_Def = 10f;

	public const float BaseExp_DamageType_Atk_L_Def = 16f;

	public const float BaseExp_DamageType_Atk_GE_Def = 24f;

	public const float BaseExp_DamageType_TargetAlive_Atk_L_Def = 8f;

	public const float BaseExp_DamageType_TargetAlive_Atk_GE_Def = 10f;

	public const float BaseExp_NoDamage = 10f;

	public const float Exp_TargetType_All = 20f;

	public const float ExpMultiFactor_Defender_Is_Leader = 2f;

	public const float ExpMultiFactor_DamageType_Less = 8f;

	public const float ExpMultiFactor_DamageType_TargetAlive_Less = 2f;

	public const float ExpMultiFactor_DamageType_TargetAlive_GreaterEqual = 1f;

	public const float ExpMultiFactor_DamageType_GreaterEqual = 4f;

	public const float ExpMultiFactor_NoDamage_Less = 2f;

	public const float ExpMultiFactor_NoDamage_GreaterEqual = 1f;

	public const int defaultHp = 100;

	public const int minDmgHp = 1;

	public const int minPoisionHp = 1;

	public const int poisonHpDmgRate = 10;

	public const int deadlyPoisonHpDmgRate = 20;

	public const int burnsHpDmgRate = 10;

	public const int bleedingHpDmgRate = 10;

	public const int deadlyWoundHpDmgRate = 100;

	public const int skillAtk17BonusDmgRate = 20;

	public const int turnHealHp = 20;

	public const int turnHealOtherHp = 10;

	public const int incMaxHp = 15;

	public const int groggyHp = 10;

	public const float groggyHpPct = 0.3f;

	public const int regenerationHpRate = 10;

	public const float lowManaPct = 0.2f;

	public const int defaultMp = 10;

	public const int turnHealMp = 10;

	public const int turnHealOtherMp = 5;

	public const int incMaxMp = 15;

	public const int minExp = 0;

	public const int maxExp = 100;

	public const int minItemExp = 0;

	public const int maxItemExp = 100;

	public const int incExp = 15;

	public const int upperMinGetExp = 10;

	public const int upperMaxGetExp = 100;

	public const int lowerMinGetExp = 1;

	public const int lowerMaxGetExp = 8;

	public const int noHitGetWeaponExp = 1;

	public const int minGetWeaponExp = 2;

	public const int maxGetWeaponExp = 3;

	public const int minLevel = 1;

	public const int minStr = 1;

	public const int maxStr = 255;

	public const int minCha = 1;

	public const int maxCha = 255;

	public const int minWis = 1;

	public const int maxWis = 255;

	public const int minDex = 1;

	public const int maxDex = 255;

	public const int minLuck = 1;

	public const int maxLuck = 255;

	public const int maxTrainingStat = 500;

	public const int maxStrForRune = 200;

	public const int maxChaForRune = 200;

	public const int maxWisForRune = 200;

	public const int maxDexForRune = 200;

	public const int maxLuckForRune = 200;

	public const int maxRuneEnhanceLevel = 5;

	public const int maxRuneSlotCount = 3;

	public const int minMove = 1;

	public const int maxUnitMove = 9;

	public const int maxAddMove = 3;

	public const int incMove = 1;

	public const int ikkitosenRate = 10;

	public const int cohesionPerUnitRate = 3;

	public const int cohesionMaxRate = 24;

	public const int struggleRate = 2;

	public const int struggleMaxRate = 48;

	public const int angerRate = 10;

	public const int SKILL_TYPE_1ST_NO = 9000010;

	public const int ITEM_EFFECT_1ST_NO = 9010200;

	public const float SELECTION_SIZE = 0.96f;

	public static readonly Vector3 UNIT_SPRITE_OFFSET;

	public static readonly Vector3 UNIT_SHADOW_OFFSET;

	public static readonly Vector3 MO_SPRITE_OFFSET;

	public const int PIXELS_PER_UNIT = 100;

	public const float DOUBLE_TAB_INTERVAL = 0.25f;

	public const int SCREEN_WIDTH = 1280;

	public const int SCREEN_HEIGHT = 720;

	public const float BASE_SCREEN_RATIO = 1.7777778f;

	public const float ORTHOGRAPHICSIZE_ZOOM_IN = 1.8f;

	public const float ORTHOGRAPHICSIZE_DEFAULT = 3.6f;

	public const float ORTHOGRAPHICSIZE_ZOOM_OUT = 5f;

	public const float ZOOM_ORTHOGRAPHIC_VALUE = 0.2f;

	public const float ZOOM_NORMAL_RETURN_VALUE = 0.1f;

	public const float ZOOM_SPEED = 0.8f;

	public const float ORTHOGRAPHIC_INIT = 3.4f;

	public const int MINIMAP_CAMERA_VALUE = 360;

	public const float MOUSE_UP_MIN_TIME = 0.5f;

	public const float UNIT_SIZE = 0.96f;

	public const float UNIT_SIZE_INVERSE = 1.0416667f;

	public const float CAMERA_BOARD_SIZE = 2.5f;

	public const int MAX_EVENT_BFINDEX = 20;

	public const int EVENT_UNIT_NOTHING = 65535;

	public const float EVENT_SCRIPT_TIME_INTERVAL_BASE = 0.05f;

	public const int MAX_ADDITIONAL_SLOT_COUNT = 3;

	public const int MIN_TEAMFACTION_VALUE = 1;

	public const int MAX_TEAMFACTION_VALUE = 16;

	public const int MIN_GROUP_VALUE = 1;

	public const int MAX_GROUP_VALUE = 10;

	public const int MAX_SLOT_NUMBER = 99;

	public const int MAX_COORD_COUNT = 10;

	public const int MAX_AIUNITS_NEIGHBOR_DISTANCE_X = 5;

	public const int MAX_AIUNITS_NEIGHBOR_DISTANCE_Y = 3;

	public const int AI_MOVE_PINCERRATE = 100;

	public const int AI_RECOVERTILE_DISTANCE = 50;

	public const int AI_RECOVERTILE_GOAL = 50;

	public const int AI_RUNAWAY_DST = -50;

	public const int TILE_FLATLAND = 4200001;

	public const int TILE_GRASSLAND = 4200002;

	public const int TILE_WASTELAND = 4200004;

	public const int TILE_PALACE_INSIDE = 4200017;

	public const int MAP_OBJECT_FIRE = 4300165;

	public const string COLOR_RGB_WHITE = "#FFFFFF";

	public const float constStatX = 5f;

	public const float constStatS = 4.5f;

	public const float constStatA = 3.5f;

	public const float constStatB = 1.5f;

	public const int AUTOEQUIPTEIPCONST = 3;

	public const int userFoodStorage = 5000;

	public const int userSilverStorage = 5000;

	public const int userFoodPerHour = 800;

	public const int LOOTING_VALUE = 30;

	public const int CLOUD_LEVEL_GAP = 100;

	public const int TownRankPoint = 20;

	public const int TownGradeRank = 50;

	public const int resourceLow = 500;

	public const int resourceHigh = 3000;

	public const int resourceTotal = 5;

	public const int USER_TOTAL_FOOD_TOWNFUNCTIONGROUP_ID = 1;

	public const int USER_TOTAL_SILVER_TOWNFUNCTIONGROUP_ID = 1001;

	public const int WORLDMAP_BATTLEFIELD_SHORTCUT_ID = 5500005;

	public const int GM_UNIT_ID = 1100001;

	public const int CAOCAO = 1100001;

	public const int JENAM = 4500014;

	public const float TURN_PANEL_LIVE_TIME = 2f;

	public const float TURN_INFO_LIVE_TIME = 6.5f;

	public const float BATTLE_MAP_OPENING = 8f;

	public const float BATTLE_MAP_OPENING_CLOUD_REMOVE = 5f;

	public const int addCostByChange = 2;

	public static readonly int[] addCostByUnitPromotion;

	public const int maxJobLv = 4;

	public static readonly int[] silverByClassUp;

	public const int userMaxLevel = 255;

	public const int MAX_UNIT_REINFORCE_LEVEL = 11;

	public const int SOLDIER_TRAINING_NEXT_LEVEL = 1;

	public const int scenario1stId = 4110001;

	public const int RESOURCE_TOOLTIP_TEXT_KEY_PRODUCTION = 9009131;

	public const int RESOURCE_TOOLTIP_TEXT_KEY_HOLDING_AMOUNT = 9009132;

	public const int RESOURCE_TOOLTIP_TEXT_KEY_HOLDING_AMOUNT_MAX = 9009133;

	public const int ITEM_TOOLTIP_TEXT_KEY_WHATKINDOF = 9009141;

	public const int ITEM_TOOLTIP_TEXT_KEY_EXP = 9009142;

	public const int ITEM_TOOLTIP_TEXT_KEY_CONDITION = 9009143;

	public const int ITEM_TOOLTIP_TEXT_KEY_EFFECT = 9009144;

	public const int ITEM_TOOLTIP_TEXT_KEY_EXPLAIN = 9009145;

	public const int ITEM_TOOLTIP_TEXT_KEY_ATT = 9009146;

	public const int ITEM_TOOLTIP_TEXT_KEY_DEF = 9009147;

	public const int ITEM_TOOLTIP_TEXT_KEY_EACH_LEVEL = 9009148;

	public const float EVENT_UNIT_MOVE_SPEED_FACTOR = 1.25f;

	public const float BATTLE_OWN_UNIT_MOVE_SPEED_FACTOR = 1f;

	public const float BATTLE_OTHER_UNIT_MOVE_SPEED_FACTOR = 1.5f;

	public const float FLOW_DOUBLECLICK_TIME = 0.4f;

	public const int AUTO_BATTLE_PLAY = 2;

	public const float SELECTION_SPREAD_INTERVAL_TIME = 0.035f;

	public const float SELECTION_SPREAD_FAST_SPEED_LIMITATION = 3.98f;

	public const float DIMMING_DIRECTION_TIME = 0.3f;

	public const float DIMMING_FACTOR_LIGHT = 0.8f;

	public const float DIMMING_FACTOR_MIDDLE = 0.6f;

	public const float DIMMING_FACTOR_DARK = 0.4f;

	public const float DIMMING_FAST_SPEED_LIMITATION = 3.98f;

	public const float UNIT_FLICKERING_TIME = 1f;

	public const string UNIT_FLICKERING_EASETYPE = "easeinoutcirc";

	public const float EXPECTED_DAMAGE_FLICKERING_TIME = 0.5f;

	public const int ITEM_ID_GOLD_CHARGED = 3090001;

	public const int ITEM_ID_GOLD_FREE = 3090002;

	public const int ITEM_ID_SILVER = 3090003;

	public const int ITEM_ID_FOOD = 3090004;

	public const int ITEM_ID_STAMP = 3090005;

	public const int ITEM_ID_EXP = 3090006;

	public const int ITEM_ID_EXPLOIT = -1;

	public const int ITEM_ID_FAME = -1;

	public const int ITEM_ID_ALLIENCE_POINT = 3090014;

	public const int ITEM_ID_TRACE = 3100041;

	public const int ITEM_ID_SILVER_FREE = 3090003;

	public const int ITEM_ID_GUILD_MONEY = -1;

	public const float PANELFACE_ATTACK_CRIT_DELAY_TIME = 0.4f;

	public const float PANELFACE_ARTIFICE_DELAY_TIME = 0f;

	public const float PANELFACE_DAMAGED_DELAY_TIME = 0.5f;

	public const float PANELFACE_DAMAGED_CRIT_ADD_DELAY_TIME = 1f;

	public const float PANELFACE_SHAKE_FACTOR = 10f;

	public const float PANELFACE_SHAKE_DURATION = 0.5f;

	public const string PANELFACE_SHAKE_EASETYPE = "linear";

	public const float PANELFACE_SCALE_FACTOR = 1.1f;

	public const float PANELFACE_SCALE_DURATION = 0.6f;

	public const string PANELFACE_SCALE_EASETYPE = "easeoutquint";

	public const string SHOP_LIST = "ShopList";

	public const string SERVER_DATA_VERSION = "Version";

	public const int BATTLEMAPFADE_SORTINGORDER = 3000;

	public const int MAX_AMBITION = 100;

	public const int MIN_AMBITION = 0;

	public const int AMBITION_DEFAULT = 50;

	public const int MAX_LIKEABILITY = 100;

	public const int ITEM_STR_UP = 3090007;

	public const int ITEM_WIS_UP = 3090008;

	public const int ITEM_CHA_UP = 3090009;

	public const int ITEM_DEX_UP = 3090010;

	public const int ITEM_LUCK_UP = 3090011;

	public const int RESET_TRAINED_UNIT = 3100030;

	public const int REDISTRIBUTION_TRAIN_COST = 1000;

	public const int REDISTRIBUTION_TICKET_ID = 3100167;

	public const int ADD_STAT_PER_STAT_UP = 1;

	public const int COST_GOLD_PER_STAT_UP = 200;

	public const int STAT_PER_JOBLEVEL = 20;

	public const float goldExchangeRate = 250f;

	public const float watingTimeOnAutoprocess = 1.5f;

	public const float m_pressableBtnActivateTimeGap = 0.45f;

	public const float m_pressableBtnFastStepTime = 1.5f;

	public const float m_pressableLogicElapsingTime = 0.08f;

	public const int MAX_UNIT_NOTIFICATION_FLICKER_COUNT = 50;

	public const int m_scenarioHardModeModifyLv = 5;

	public const int m_scenarioHardModeModifyMinLv = 20;

	public const int m_scenarioHardModeModifyMaxLv = 20;

	public const float scenarioHardModeAttackFood1 = 1.5f;

	public const int scenarioHardModeAttackFood2 = 15000;

	public const float scenarioClassicModeAttackFood1 = 1.5f;

	public const int scenarioClassicModeAttackFood2 = 15000;

	public const int m_constWorldSearchGold = 300;

	public const int DEFAULT_NORMAL_ITEM_MAX_LEVEL = 5;

	public const int DEFAULT_TREASURE_ITEM_MAX_LEVEL = 9;

	public const int TREASUREBOX_TICKET_ID = 3050001;

	public const int BIGWARBOX_TICKET_ID = 3100297;

	public const int WEAPONBOX_TICKET_ID = 3100298;

	public const string RECON_REMAIN_TIMER_TEXT_FLICKER_EASETYPE = "linear";

	public const float UNIT_SELECT_GUIDE_TIME = 4f;

	public const int constWorldSearchGold = 300;

	public const int constSearchPay = 1;

	public const int SECONDS_OF_A_WEEK = 604800;

	public const int CHECK_NEW_MAIL_SECONDS = 600;

	public const int CHECK_NEW_MAIL_MIN_SECONDS = 10;

	public const int CHECK_UPDATE_GM_MAIL_SECONDS = 3600;

	public const int LOADING_TIP_START = 9100000;

	public const int LOADING_TIP_END = 9100039;

	public const float LOADINGSCENE_MIN_TIME = 0f;

	public const int RANK_SIEGE = 7300002;

	public const int RANK_SHAM = 7300003;

	public const int RANK_BOSS = 7300004;

	public const int RANK_DEFENSE = 7300005;

	public const int RANK_COMPETITION = 7300011;

	public const int RANK_COMPETITION_GUILD_RANKER = 7300013;

	public const int RANK_ANNIHILATION_GUILD_RANKER = 7300014;

	public const int RANK_GUILD_WAR_RANKER = 7300016;

	public const int MAX_EPIC_COMMONMISSION = 1;

	public const int MAX_NORMAL_COMMONMISSION = 2;

	public const int MAX_ALL_COMMONMISSION = 2;

	public const int MAX_ALL_MISSION_IN_BATTLE = 3;

	public const int MAX_DIFFICULTY = 5;

	public const int MAX_REWARD_EXPLOIT = 4;

	public const int MAX_COMMON_MISSION_RESET_COUNT = 3;

	public const int MAX_EXPLOIT = 100;

	public const int MAX_MISSION_SEAL = 3;

	public const int LIMIT_ACHIEVEMENT_UNIT_TREE = 6;

	public static readonly string NOTICE_OBJ_NAME;

	public const int CHANGE_NATION_NAME_COST = 1000;

	public const int SPACTION_ITEM_TURN_DURATION = 3;

	public const int MAX_FOLLOW_USER_NUM = 20;

	public static readonly Vector2[] INSTANCE_BOUND_BASE;

	public static readonly Vector2 INSTANCE_BOUND_X;

	public static readonly Vector2 INSTANCE_BOUND_Y;

	public static readonly string WORLDMAP_TUTO_SCRIPT;

	public static readonly string CHRONICLE_TUTO_SCRIPT;

	public static readonly string BATTLE_END_TUTO_SCRIPT;

	public static readonly string TOWN_TUTO_SCRIPT;

	public static readonly string RECON_TUTO_SCRIPT;

	public static readonly string ANNIHILATION_WAR_TUTO_SCRIPT;

	public static readonly string THOUSAND_BATTLE_TUTO_SCRIPT;

	public static readonly string INSTANCE_TUTO_SCRIPT;

	public static readonly string GUILD_SIEGEWAR_TUTO_SCRIPT;

	public static readonly string COMPETITION_TUTO_SCRIPT;

	public static readonly string ARENA_TUTO_SCRIPT;

	public static readonly string GUILDWAR_TUTO_SCRIPT_1;

	public static readonly string GUILDWAR_TUTO_SCRIPT_2;

	public static int SHAM_BATTLE_COOLTIME;

	public static readonly int[] UNIT_REINFORCE_COST;

	public static readonly Color OCCUPIED_COLOR_ONTACTICMAP;

	public static readonly string LOADING_SCENE_PATH;

	public static readonly string loadingSceneName;

	public static readonly string[] failErrorType;

	public static readonly string[] retryErrorType;

	public const int AUTOUSE_ITEM_PASSIVE_ID = 2200091;

	public const int AI_FOR_VIP = 9500008;

	public const int UNIT_MAX_ENERGY = 100;

	public const int ENERGY_RECOVER_TIME = 120;

	public const float ENERGY_RECOVER_GOLD = 0.1f;

	public const int LOOTING_BONUS_PER = 4500;

	public static readonly int[] VOICE_BASE;

	public static readonly int[] VOICE_CHRISTMAS;

	public static readonly int[] VOICE_NEWYEAR;

	public static int RECON_ATTACK_MIN_LEVEL;

	public static int attackFoodLevelCorrection;

	public static int attackFoodMinCorrection;

	public static int PASSIVE_EQUIP_COST;

	public static string TOY_LOGIN_MAIL_ADDR;

	public static string TOY_LOGIN_TYPE_GUEST;

	public static string STORED_UID_KEY;

	public static double battleFastForwardCoef;

	public static int UNIFICATION_ID;

	public static int BATTLEFIELD_ID;

	public static readonly int[] EventListFilterRange;

	public static int pedlarAlivenessTime;

	public static readonly int[] mayorEffectBonus;

	public const int STORYBOOK_CAOCAO = 6100002;

	public const int GuildCreationCost = 1500;

	public const int GuildMarkCost = 1000;

	public const int GuildMasterMinRank = 75;

	public const int GuildJoinMinRank = 15;

	public const int GuildRecruitMsgLength = 300;

	public const int GuildNameCost = 4000;

	public const int GuildRankMarkMin = 10000;

	public const int GuildDonationTermMinute = 1440;

	public const int GuildDonationGoldCapacity = 10000;

	public const int GuildDonationSilverCapacity = 1000000;

	public const int GuildDonationGoldStep = 100;

	public const int GuildDonationSilverStep = 10000;

	public const int GuildSiegeWarDefaultDefenceDeckNum = 3;

	public const int GuildSiegeWarFortressDefDeckNum = 10;

	public const int GuildSiegeWarFacilityDefDeckNum = 6;

	public const int GuildSiegeWarFortressAtkDeckNum = 10;

	public const int GuildSiegeWarFacilityAtkDeckNum = 6;

	public const int GuildSiegeWarFortressPositionNum = 1;

	public const int GuildSiegeWarFacilityPositionNum = 2;

	public const int GuildSiegeWarSupportNum = 4;

	public const int GuildSiegeWarFacilityTotalNum = 9;

	public const int GuildSiegeWarReconNum = 5;

	public const int GuildSiegeWarAttackDeckUnit = 16;

	public const int GuildSiegeWarFortressMemberNum = 16;

	public const int GuildSiegeWarFacilityMemberNum = 8;

	public const int GuildSiegeWarFacilityLvUpCostPerExp = 10;

	public const int GuildSiegeWarSiegePerLeague = 2;

	public const int GuildSiegeWarBattleExpireTime = 30;

	public const int GuildSiegeWarBattleAlarmTime = 300;

	public const int ProloguBattleDoneFlag = 3015;

	public const int AllTutorialDoneFlag = 3014;

	public const int QuickEquipSlotNum = 6;

	public const int QuickEquipFreeNum = 2;

	public const int QuickSlotCost = 500;

	public const int QuickSlotNameLen = 10;

	public const string assaultShipPrefabName = "tk2dMasking_Water_AssaultShip";

	public const string assaultShipPrefixFormat = "AssaultShip_{0}";

	public const float tileUnitLvHp = 5f;

	public const float tileUnitLvMp = 7f;

	public const float tileAddHp = 0.05f;

	public const float tileAddMp = 0.05f;

	public const int turnAddEp = 10;

	public const int avrgLevAdjust = 15;

	public const int maxLevAdjust = 4;

	public static string DATE_STRING_FORMAT;

	public static int maxTurnInBattle;

	public static int maxExtendTurnOnce;

	public const int annihilationWarExpireTime = 20;

	public const int annihilationWarAlarmTime = 180;

	public const int annihilationWarDeckMaxUnitCount = 5;

	public const int annihilationWarMaxUnitCost = 99;

	public const int AWTicket_MaxCount = 5;

	public const int AnnihilationWarRankingRewardsID = 7300001;

	public const int AnnihilationWarEnterRemainingTime = 25;

	public const int annihilationWar_eloWinningPercentage = 380;

	public const int annihilationWar_eloWinFactor = 15;

	public const int annihilationWar_eloWinMin = 5;

	public const int annihilationWar_eloWinMax = 40;

	public const int nationPopularityFoodAdd = 200;

	public const int popularityPerTownFood = 15000;

	public const int nationPopularitySilverAdd = 400;

	public const int popularityPerTownSilver = 20000;

	public const int nationPopularityItemAdd = 400;

	public const int popularityPerTownItem = 1;

	public static readonly int[] popularityRewardTerm;

	public static readonly int[][] townPopularitySectionByGrade;

	public static readonly Color[] popularityGradeColor;

	public static int PopularityCheckInterval;

	public static int CheckReturnUserConditionDays;

	public const float thousandBattleMpAttackMultiplier = 0.5f;

	public const string versionTW = "35814";

	public static float AutoInstanceActionDelay;

	public const int MaxConsecutivePlay = 30;

	public const int InstanceExpeditionUnitCount = 4;

	public const int InstanceExpeditionLimitLevel = 20;

	public static string iOSAppStoreUrl;

	public static int costUnitExpBoost;

	public static int percentUnitExpBoost;

	public const bool withoutServer = false;

	public const int maxTreasureEnhanceLevel = 12;

	public const int maxTreasureEnhancePassive = 2;

	public static int[] treasureEnhancePassiveSetCost;

	public static int[] treasureEnhancePassiveSetCostTicket;

	public static int[] treasureEnhancePassiveUnlockLevel;

	public const int treasureEnhanceTicketId = 3100028;

	public const int treasureEnhanceKeeperId = 3100029;

	public const int treasureCreateTicketId = 3043622;

	public const int treasureEnhanceGuaranteeId = 3100060;

	public const int treasureEnhanceFixedLimitLevel = 7;

	public const int treasureEnhancePassiveTicketId = 3100318;

	public static int[] paybackPassiveTicketCnt;

	public const int ChattingNoticeBoxOpenTreasureItemTierLimit = 7;

	public const int ChattingNoticeTreasureEnhanceLevelLimit = 12;

	public const double ChattingNormalChatIntervalSec = 30.0;

	public const int ChattingNormalChatSendCountLimit = 5;

	public const int ChattingMakeRoomUserCount = 3;

	public const int ChattingReconnectDelaySec = 5;

	public static int ChattingAllSendCost;

	public static readonly float[] ChattingOneLineVisibleTime;

	public const int ChattingMsgMaxSaveValue = 200;

	public const int ChattingBlockMaxUserValue = 30;

	public static int ChattingAllSendMinLevel;

	public static float ChattingNotificationViewDuration;

	public const string SettlementActUrl = "http://m.nexon.com/terms/197";

	public const string SpcCommTransActIOSUrl = "http://m.nexon.com/terms/198";

	public const string SpcCommTransActAOSUrl = "http://m.nexon.com/terms/199";

	public const string TreasureProbabilityUrl = "https://m.nexon.com/notice/get/1?client_id=MTU5MjY1OTQ0OQ";

	public const string ChargeItemChanceInfoUrl = "https://m.nexon.com/probability?client_id=MTI0ODI5NzQwMA";

	public static int BattleFieldAttackCostMultiple;

	public static int BattleFieldReconDuration;

	public static int BattleFieldCheckWaitUserDuration;

	public static int BattleFieldExpireTime;

	public static int BattleFieldAlarmTime;

	public static int BattleFieldMaxSoldierCount;

	public static int BattleFieldOccupiedSaveflagId;

	public static int BattleFieldDefenseDeckMinCount;

	public static int BattleFieldRematchingTerm;

	public static int CONDITION_ROTATION_TERM;

	public const int incCastleUnitLevel = 8;

	public const int QUICK_BATTLEDECK_DEF_BATTLEFIELD_COST = 1000;

	public const int QUICK_BATTLEDECK_DEF_MAX_SLOT = 3;

	public const int QUICK_BATTLEDECK_NAME_MAX_LENGTH = 10;

	public const int QUICK_BATTLEDECK_WORLDBOSS_FACE_LENTGH = 20;

	public const int QUICK_BATTLEDECK_SEQUENTIAL_INSTANCE_FACE_LENTGH = 6;

	public const int QUICK_BATTLEDECK_DEF_GUILDWAR_COST = 0;

	public const int QUICK_BATTLEDECK_DEF_GUILDWAR_MAX_SLOT = 1;

	public const int QUICK_BATTLEDECK_NAME_GUILDWAR_MAX_LENGTH = 10;

	public static float RaidExchangeRate;

	public static int RaidRefreshUnitsCost;

	public const int RaidStuffSoulBD = 3100042;

	public const int RaidStuffSoulVB = 3100058;

	public const int RaidStuffSoulWT = 3100090;

	public const int RaidStuffSoulBT = 3100092;

	public const int RaidStuffSteleBD = 3100043;

	public const int RaidStuffSteleVB = 3100059;

	public const int RaidStuffSteleWT = 3100091;

	public const int RaidStuffSteleBT = 3100093;

	public const int RaidStuffTicket = 3100477;

	public static int RaidMaxRage;

	public static float Raid_BirdFly_Zoom;

	public static float Raid_BirdFly_ZoomTime;

	public static int MaxSoulAmount;

	public const int MultiplayReconnectDelaySec = 3;

	public const double MultiplayPingTimeout = 5.0;

	public const int MultiplayPingInterval = 5;

	public const float multiplayWaitSeconds = 60f;

	public const int CompetitionMinLevel = 90;

	public const int competitionEnterRemainingTime = 10;

	public const int competitionMatchingExpiredTime = 5;

	public const int competitionAlarmTime = 5;

	public const int competitionFastStep = 3;

	public const float competitionContinueTimeScale = 64f;

	public const int CompetitionMatchmakingCheckMin = 5;

	public const int competitionInitTierNeedBattleCntMin = 5;

	public const int competitionInitTierNeedBattleCntMax = 10;

	public const int emoticonAllowTime = 5;

	public const int emoticonAllowCount = 3;

	public const int emoticonBlockTime = 10;

	public const float competitionBanTime = 20f;

	public const float competitionFormationTime = 40f;

	public const float competitionOrderTime = 20f;

	public const int competitionMinimumMatchesForReward = 10;

	public static int UserSpellInitPayWallet;

	public static int UserSpellMaxPossessionWallet;

	public static int UserSpellAutoPayWallet;

	public const int competitionBrawlGoingToWarCnt = 3;

	public const int competitionBrawlBloodyStartTurn = 5;

	public const int competitionBrawlBloodyMax = 3;

	public const int competitionBrawlAllInStartTurn = 10;

	public const int competitionBrawlAllInMax = 2;

	public const float SceneLoadingPerOnContinue = 0.68f;

	public const float CompetitionOpponentWaitExpireSec = 10f;

	public const float CompetitionOpponentWaitActiveDelaySec = 3f;

	public const string COMPETITION_FRIENDLY_MAP_ID_KEY = "C_F_MAP_ID_{0}";

	public const string COMPETITION_FRIENDLY_MODE_ID_KEY = "C_F_MODE_ID_{0}";

	public const string COMPETITION_FRIENDLY_UNITS_SRL_KEY = "C_F_U_SRL_{0}_{1}";

	public const int competitionDeniedInvitingDelaySec = 300;

	public const int competitionResponseInvitingWaitSec = 5;

	public const int TIMEBOX_COST_GOLD_PER_MIN = 5;

	public const int TIMEBOX_FREE_SLOT_COUNT = 3;

	public const int TIMEBOX_MAX_SLOT_COUNT = 4;

	public const int TIMEBOX_SLOT_ADD_GOLD_COST = 10000;

	public const int TIMEBOX_RESERVED_SLOT_START_INDEX = 100;

	public const int TIMEBOX_MAIL_SLOT_START_INDEX = 200;

	public const int TIMEBOX_WEEKLY_BOX_SLOT_COUNT = 4;

	public const int TIMEBOX_RESERVED_ARENA_SLOT_START_INDEX = 300;

	public const int TIMEBOX_ARENA_BOX_SLOT_COUNT = 2;

	public const int TIMEBOX_ARENA_SLOT_ADD_GOLD_COST = 15000;

	public const int ScenarioClassicStampItemID = 3100108;

	public const int ScenarioClassicResetItemID = 3100109;

	public const int ScenarioClassicStampBoxID_1 = 6210001;

	public const int ScenarioClassicStampBoxID_10 = 6210002;

	public const int SecretGemItemBD = 3028704;

	public const int SecretGemItemVB = 3028701;

	public const int SecretGemItemWT = 3028703;

	public const int SecretGemItemBT = 3028702;

	public const int SecretGemItemBD_WakeUP = 3100209;

	public const int SecretGemItemVB_WakeUP = 3100210;

	public const int SecretGemItemWT_WakeUP = 3100211;

	public const int SecretGemItemBT_WakeUP = 3100212;

	public const int ExpItemPassiveId = 2200130;

	public const int WeaponExpItemPassiveId = 2200131;

	public const int ArmorExpItemPassiveId = 2200132;

	public const int MaxPassiveResetCount = 2;

	public static readonly int[] PassiveResetRefillCost;

	public const int RepresentUnitCount = 7;

	public const int GuildWarMaxLordLevel = 20;

	public const int GuildWarMaxUnitCount = 20;

	public const int GuildWarMaxRequestCollectFoodUnit = 5;

	public const int DiplomaItemId = 3043623;

	public const int GuildWarWarPointRankingID = 7300017;

	public const int MaxWeaknessCount = 5;

	public const int MaxRandomPassiveCount = 5;

	public static int WorldBossTicketPrice;

	public const int WorldBossTokenItemId = 3100271;

	public const int FreeParticipationCountBySeasonPass = 2;

	public const int WorldBossLimitLevel = 99;

	public const int ArenaBattleUnitNum = 5;

	public const int ArenaAIFastButtonTurn = 0;

	public const int ArenaBattleAbleCount = 1;

	public const int ArenaUnitPassiveNum = 5;

	public const int ArenaTotalLockRecordNum = 5;

	public const int ArenaCoinItemId = 3100362;

	public const int attendanceContinueCostPerDay = 300;

	public const int GrowthPackageId = 9900001;

	public static readonly string[] VoiceChatCompetitionChannelUIName;

	public static readonly string[] VoiceChatGuildWarChannelUIName;

	public const float VoiceChatSpeakingDefaultVolume = 0.5f;

	public const float VoiceChatListeningDefaultVolume = 0.5f;
}
