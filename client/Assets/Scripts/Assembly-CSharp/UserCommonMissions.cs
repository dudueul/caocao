using System;
using System.Collections.Generic;
using LitJson;

public class UserCommonMissions : ITransmittable
{
	private Dictionary<int, UserCommonMission> m_id2CommonMissions;

	private List<int> m_ids;

	private DateTime m_dailyMissionRefreshDatetime;

	private Dictionary<int, UserCommonMission> m_prepMIssions;

	private List<int> notices;

	private Dictionary<int, CommonMission> epicDatas;

	private int maxEpicOrder;

	public Action deserializeCallback;

	public Action updateCallback;

	public Dictionary<int, UserCommonMission> id2CommonMissions
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserCommonMission get_Item(int id)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, UserCommonMission> prepMIssions
	{
		get
		{
			return null;
		}
	}

	public bool isUpdatableState()
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void OnCheckCommonMission()
	{
	}

	public void OnUpdateCommonMission(EnumData.CommonMissionCheckType checkType, int checkValue, int amount = 1, bool isOverwrite = false)
	{
	}

	public void OnUpdateEpicMission(int missionId)
	{
	}

	private void CheckAvailableCommonMission()
	{
	}

	public List<UserCommonMission> GetNormalMissions()
	{
		return null;
	}

	public UserCommonMission GetEpicMission()
	{
		return null;
	}

	private List<UserCommonMission> GetEpicMissions()
	{
		return null;
	}

	private List<CommonMission> GetDataOnEpicMissions()
	{
		return null;
	}

	public List<UserCommonMission> GetUserDailyMissions(bool includeDone)
	{
		return null;
	}

	public List<UserCommonMission> GetUserSeasonPassMissions(bool includeDone)
	{
		return null;
	}

	public List<CommonMission> GetMissionData(EnumData.CommonMissionType missionType)
	{
		return null;
	}

	public int GetFinishedMissionCount(int missionType, int subcategoy)
	{
		return 0;
	}

	private bool CheckAvailableNormalMission(List<UserCommonMission> normals, int userLev)
	{
		return false;
	}

	private bool CheckAvailableEpicMission(int userLev)
	{
		return false;
	}

	private bool CheckAvailableMission(EnumData.CommonMissionType type, int userLev)
	{
		return false;
	}

	private void CheckValidMission(EnumData.CommonMissionCheckType checkType, bool isOverwrite, int checkValue, int amount = 1)
	{
	}

	private void UpdateValidMission(CommonMission mission, EnumData.CommonMissionCheckType checkType, bool isOverwrite, int checkValue, int amount = 1)
	{
	}

	private void UpdatePrepMission(CommonMission mission, int increasedValue, bool isOverwrite)
	{
	}

	private int AddPreCommonMission(int missionId, int increasedValue, bool isOverwrite)
	{
		return 0;
	}

	private int _AddNewPreCommonMission(int missionId, int increasedValue)
	{
		return 0;
	}

	public UserCommonMission GetPrevEpicMission(UserCommonMission userMission)
	{
		return null;
	}

	public bool IsCompleted(UserCommonMission userMission)
	{
		return false;
	}

	private bool isCompleted(int missionId, int checkValue, int addValue, bool checkEpicPrev = false)
	{
		return false;
	}

	public bool IsCompletedCategory(EnumData.CommonMissionType category)
	{
		return false;
	}

	public void RequestCommonMissionTriggerCheck()
	{
	}

	private void OnCommonMissionTriggerCheck(JsonData response)
	{
	}

	public void clearUpdateInfo(List<int> keys)
	{
	}

	public void UpdateCallback()
	{
	}

	public void ShowNotice()
	{
	}

	public void AddMissionToNoticeList(CommonMission mission)
	{
	}

	public int GetNextNotice()
	{
		return 0;
	}

	public int GetCompleteMissionsCount()
	{
		return 0;
	}

	public int GetCompleteCategoryMissionsCount(EnumData.CommonMissionType missionType)
	{
		return 0;
	}

	public int GetCompleteMissionsCountExceptCategory(params int[] missionTypes)
	{
		return 0;
	}

	public bool CheckCondition(CommonMission mission)
	{
		return false;
	}

	public void RefreshDailyMissions()
	{
	}

	public void StoreRequestValues()
	{
	}
}
