using System;
using System.Collections.Generic;
using LitJson;

public class GuildMissions : ITransmittable
{
	public enum FlushType
	{
		Immediately = 0,
		Manually = 1,
		Delay1Sec = 2
	}

	private class UpdateMission
	{
		public EnumData.GuildMissionCheckType checkType;

		public int amount;

		public int[] checkValues;

		public UpdateMission(EnumData.GuildMissionCheckType checkType, int amount, int[] checkValues)
		{
		}
	}

	public class MissionReward
	{
		public EnumData.GoodsType soloGoodsType;

		public int soloGoodsAmount;

		public EnumData.GoodsType[] guildGoodsType;

		public int[] guildGoodsAmount;

		public int[] guildItemsId;

		public int[] guildItemsAmount;
	}

	private Dictionary<int, GuildMission> m_id2GuildBeforeMissions;

	private Dictionary<int, GuildMission> m_id2GuildMissions;

	private List<int> m_ids;

	private bool addedTimer;

	private Dictionary<int, int> m_updatedMissions;

	private Dictionary<int, int> m_updatedMissionsDelay;

	private Dictionary<int, int> curUpdatedMissions;

	private List<UpdateMission> m_storedUpdateMissions;

	private bool updatingMissions;

	private bool retryingUpdateMissions;

	private bool restoringOneMissions;

	public Action deserializeCallback;

	public Action deserializeBeforeWeekMission;

	public Action<int, MissionReward> deserializeMissionReward;

	public Dictionary<int, GuildMission> id2GuildBeforeMissions
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, GuildMission> id2GuildMissions
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildMission get_Item(int id)
	{
		return null;
	}

	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public bool isValid
	{
		get
		{
			return false;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	private DateTime prevWeek
	{
		get
		{
			return default(DateTime);
		}
	}

	public int CompletedCount
	{
		get
		{
			return 0;
		}
	}

	public DateTime mondayOfWeekServer
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime mondayOfWeekLocal
	{
		get
		{
			return default(DateTime);
		}
	}

	public int CalcCompletedCount(Dictionary<int, GuildMission> missionDic)
	{
		return 0;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void RetryUpdateMission()
	{
	}

	public void DeserializeBeforeMissions(JsonData guildJsonData, JsonData userJsonData)
	{
	}

	public void DeserializeUserMissions(JsonData jsonData)
	{
	}

	private void ClearStoredOneMissions()
	{
	}

	private string GenerateStoreOnMissionKey(int missionId)
	{
		return null;
	}

	public GuildMission Get4thStampMission()
	{
		return null;
	}

	public void OnUpdateMission(EnumData.GuildMissionCheckType checkType, int amount, FlushType flushType, params int[] checkValues)
	{
	}

	private void CheckValidMission(EnumData.GuildMissionCheckType checkType, int amount, FlushType flushType, int[] checkValues)
	{
	}

	private void StoreOneMission(GuildWeeklyMission mission, int[] checkValues)
	{
	}

	private bool UpdateValidMission(GuildWeeklyMission mission, EnumData.GuildMissionCheckType checkType, int amount, int[] checkValues)
	{
		return false;
	}

	private bool CheckCondition(int compare, int checkVal, int userValue)
	{
		return false;
	}

	private bool IsCompleted(int missionId, int checkValue, int addValue)
	{
		return false;
	}

	private void UpdatePrepMission(GuildWeeklyMission mission, int increasedValue)
	{
	}

	public void Flush()
	{
	}

	public void FlushDelayedMissions()
	{
	}

	private void _Flush(Dictionary<int, int> updatedMissions)
	{
	}

	public void ShowNotice()
	{
	}

	public int GetNextNotice()
	{
		return 0;
	}

	public void Clear()
	{
	}

	public void DeserializeMissionRewards(JsonData soloReward, JsonData guildRewards, JsonData completeNum)
	{
	}
}
