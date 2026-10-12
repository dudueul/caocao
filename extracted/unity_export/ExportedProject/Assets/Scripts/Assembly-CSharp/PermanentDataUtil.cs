using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class PermanentDataUtil
{
	private static Dictionary<int, List<Instance>> m_instListPerStateId;

	private static Dictionary<int, List<Instance>> m_speicalInstList;

	private static List<State> m_sortedStateList;

	private static int maxTreasureEnhanceLevel;

	private static Dictionary<int, Dictionary<int, Dictionary<int, ObscuredInt>>> m_enhanceDataMap;

	private static int m_raidAchievementCount;

	private static Dictionary<int, Dictionary<int, Dictionary<int, Raid>>> m_raidMap;

	private static Dictionary<int, Dictionary<int, int>> m_raidLevelMap;

	private static Dictionary<int, List<Scenario>> m_scenarioListInStorybook;

	private static Dictionary<EnumData.BattleMapType, List<int>> m_battleMapModeIds;

	private static List<TimeBox> ms_sortedTimeBoxList;

	private static Dictionary<int, List<RelationList>> m_relationListPerUnit;

	private static List<ArenaSynergyList> m_frontSynergys;

	private static Dictionary<int, Dictionary<EnumData.ArenaSpeechType, Dictionary<int, Dictionary<int, ArenaDialog>>>> m_arenaDialogs;

	public static int MaxTreasureEnhanceLevel
	{
		get
		{
			return 0;
		}
	}

	public static int RaidAchievementCount
	{
		get
		{
			return 0;
		}
	}

	public static List<Instance> GetNormalInstancesByStateId(int stateId)
	{
		return null;
	}

	public static List<Instance> GetSpecialIntancesByType(int type)
	{
		return null;
	}

	public static List<State> GetSortedStateList()
	{
		return null;
	}

	public static bool CheckInstanceWeekday(Instance inst, int dayOfWeek)
	{
		return false;
	}

	public static TreasureEnhance GetTreasureEnhanceData(int tier, int enhanceType, int enhanceLevel)
	{
		return null;
	}

	public static Raid GetRaidData(int raidType, int useType, int difficulty)
	{
		return null;
	}

	public static int GetRaidHighestDifficulty(int raidType, int useType)
	{
		return 0;
	}

	public static void CorrectStoryBooks(JsonData jsonData)
	{
	}

	public static bool IsStorybookClassicModeOpened(int storybookId)
	{
		return false;
	}

	public static List<Scenario> GetScenarioListInStorybook(int storybookId)
	{
		return null;
	}

	public static DateTime GetOpenDateByEventScriptName(string scriptName)
	{
		return default(DateTime);
	}

	public static bool isRuneShopValid(int id)
	{
		return false;
	}

	public static string GetRuneShopDesc(int id)
	{
		return null;
	}

	public static Reward GetGuildWeeklyMissionUserReward(GuildWeeklyMission missionData, EnumData.GuildJopType guildJopType)
	{
		return null;
	}

	public static GuildSiegeSlot GetGuildSiegeSlotBySlotNo(EnumData.GuildSiegeFacilitySlot slot)
	{
		return null;
	}

	public static List<CommonMission> GetCommonMissions(EnumData.CommonMissionCheckType checkType)
	{
		return null;
	}

	public static List<CommonMission> GetCommonMissionByType(EnumData.CommonMissionType missionType)
	{
		return null;
	}

	public static Rune GetRune(EnumData.RuneType runeType, EnumData.RuneMainStat mainStat, int minTier)
	{
		return null;
	}

	public static List<Rune> GetRunes(EnumData.RuneType runeType, EnumData.RuneMainStat mainStat, int minTier)
	{
		return null;
	}

	public static bool IsBlacklistPassive(int spAction)
	{
		return false;
	}

	public static int GetSkinItemID(int skinId)
	{
		return 0;
	}

	public static List<int> GetSkinItemIDs()
	{
		return null;
	}

	public static List<int> GetUnitSkins(int unitID)
	{
		return null;
	}

	public static int GetFaceIdFromUnitId(int unitId)
	{
		return 0;
	}

	public static int GetFaceIdFromSkinItemId(int unitIdForCheck, int skinItemId)
	{
		return 0;
	}

	public static int GetFaceIdFromUnitIdAndSkinItemId(int unitId, int skinItemId)
	{
		return 0;
	}

	public static int GetFaceIdFromSkinId(int unitIdForCheck, int skinId)
	{
		return 0;
	}

	public static int GetFaceIdFromUnitIdAndSkinId(int unitId, int skinId)
	{
		return 0;
	}

	public static bool IsOnTreasureBox(DateTime now, BoxShop pdBoxShop)
	{
		return false;
	}

	public static CompetitionMode GetCompetitionModeByBattleMapId(int mapId)
	{
		return null;
	}

	public static EnumData.BattleMode GetCompetitionBattleModeByBattleMapId(int mapId)
	{
		return EnumData.BattleMode.Scenario;
	}

	public static List<int> GetBattleMapsByMode(EnumData.BattleMapType mapType)
	{
		return null;
	}

	public static void GetCompetitionTiers(List<Tier> dic)
	{
	}

	public static string GetCompetitionTierIconByTierGrade(int tierGrade)
	{
		return null;
	}

	public static List<TimeBox> GetSortedTimeBoxList()
	{
		return null;
	}

	private static void CheckBuildRelationListPerUnit()
	{
	}

	public static bool IsRelationUnit(int unitId)
	{
		return false;
	}

	public static bool IsRelationUnit(int unitId, int relationId)
	{
		return false;
	}

	public static List<RelationList> GetRelationList(int unitId)
	{
		return null;
	}

	public static List<WorldBossDmgReward> GetWorldbossDmgRewardList(int worldbossId)
	{
		return null;
	}

	private static void CollectFrontSynergies()
	{
	}

	public static List<ArenaSynergyList> GetUnitSynergy(List<UserUnit> units, bool getAll = false)
	{
		return null;
	}

	public static List<ArenaSynergyList> GetSynergyLists(int arenaSynergyId)
	{
		return null;
	}

	public static ArenaDialog GetArenaDialog(int scenarioId, EnumData.ArenaSpeechType speechType, int themeValue, int level)
	{
		return null;
	}
}
