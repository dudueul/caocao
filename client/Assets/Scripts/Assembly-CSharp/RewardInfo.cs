using System.Collections.Generic;
using System.IO;
using LitJson;
using UnityEngine;

public class RewardInfo
{
	public enum Type
	{
		Good = 0,
		Item = 1,
		Unit = 2,
		Rune = 3,
		TimeBox = 4
	}

	public Type type;

	public int id;

	public int exp;

	public int level;

	public int count;

	public int setId;

	public int passiveId;

	public int slotIndex;

	public int min;

	public int max;

	public bool isMail;

	public float bonusRate;

	public float countMulti;

	private EnumData.GuildJopType m_guildJopType;

	private static List<RewardInfo> rewardsForAddUI;

	public EnumData.GuildJopType guildJopType
	{
		get
		{
			return (EnumData.GuildJopType)0;
		}
		set
		{
		}
	}

	public static List<RewardInfo> Deserialize(JsonData rewardSet)
	{
		return null;
	}

	public static RewardInfo DeserializeGood(JsonData data)
	{
		return null;
	}

	public static RewardInfo DeserializeItem(JsonData data)
	{
		return null;
	}

	public static RewardInfo CreateItemInfo(int id, int amount, int exp = 0)
	{
		return null;
	}

	public static RewardInfo DeserializeUnit(JsonData data)
	{
		return null;
	}

	public static RewardInfo CreateRuneInfo(int id, int passiveId, int amount)
	{
		return null;
	}

	public static RewardInfo DeserializeRune(JsonData data)
	{
		return null;
	}

	public static RewardInfo CreateTimeBoxInfo(int id, int amount)
	{
		return null;
	}

	public static RewardInfo CreateTimeBoxSlotInfo(int slotIndex, int id)
	{
		return null;
	}

	public static List<RewardInfo> DeserializeReward(JsonData data)
	{
		return null;
	}

	public static List<RewardInfo> GetRewardInfos(int rewardID, int type, float bonusRate = 1f)
	{
		return null;
	}

	public static List<RewardInfo> GetRewardInfos(Instance instance, float bonusRate)
	{
		return null;
	}

	public static List<RewardInfo> GetRewardInfosFromBattlemap(int battlemapId)
	{
		return null;
	}

	public static List<RewardInfo> GetRewardInfosFromScenario(int scenarioId)
	{
		return null;
	}

	public static List<RewardInfo> GetMissionRewardInfosFromArenaLevel(int arenaLevelId)
	{
		return null;
	}

	private static void MergeReward(int rewardID, List<RewardInfo> rewardInfos, float bonusRate = 1f)
	{
	}

	private static void MergeGood(int setGoodID, List<RewardInfo> rewardInfos, float bonusRate = 1f)
	{
	}

	private static void MergeItem(int setItemID, List<RewardInfo> rewardInfos, float bonusRate = 1f)
	{
	}

	private static void MergeTimeBox(int setTimeBoxID, List<RewardInfo> rewardInfos)
	{
	}

	private static void Merge(List<RewardInfo> rewardInfos, Type type, int id, int setId, float bonusRate)
	{
	}

	public static List<RewardInfo> DistinctRewards(List<RewardInfo> rewards)
	{
		return null;
	}

	public static List<RewardInfo> ExceptNotItemRewards(List<RewardInfo> rewards)
	{
		return null;
	}

	public void ReadRewardInfo(BinaryReader r)
	{
	}

	public void WriteRewardInfo(BinaryWriter w)
	{
	}

	public static void AddRewardUI(Transform grid, JsonData rewardSet, Vector3 scale, int depth)
	{
	}

	public static void AddRewardUI(Transform grid, int rewardID, int type, Vector3 scale, int depth)
	{
	}

	public static void AddRewardUI(Transform grid, Instance instance, float bonusRate, Vector3 scale, int depth)
	{
	}

	public static void AddRewardUI(Transform grid, RewardInfo reward, Vector3 scale, int depth)
	{
	}

	public static void AddRewardUI(Transform grid, List<RewardInfo> rewards, Vector3 scale, int depth, int showLimit, bool showFirstOne = false, bool countable = true)
	{
	}

	public static void ShowGoodTooltip(GoodUI goodUI)
	{
	}

	public static void ShowItemTooltip(ItemUI itemUI)
	{
	}

	public static void ShowUnitTooltip(UnitUI unitUI)
	{
	}

	public static void ShowRuneTooltip(RuneUI runeUI)
	{
	}

	public string GetName()
	{
		return null;
	}

	public static void AddRewardUI(Transform grid, BattleField battleFieldData, Vector3 scale, int depth)
	{
	}
}
