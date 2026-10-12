using System;
using System.Collections.Generic;
using LitJson;

public class UserAchievements : ITransmittable
{
	private Dictionary<int, UserAchievement> m_id2Achievements;

	private List<int> m_ids;

	private Dictionary<int, UserAchievement> m_prepAchievements;

	private List<int> notices;

	public Action updateAchieveFinishCallback;

	public Action deserializeCallback;

	public Dictionary<int, UserAchievement> id2Achievements
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserAchievement get_Item(int id)
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

	public Dictionary<int, UserAchievement> prepAchievements
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

	public void OnUpdateAchievement(EnumData.AchievementCheckType checkType, int checkValue, int amount = 1, bool isOverwrite = false)
	{
	}

	private void CheckValidAchievement(EnumData.AchievementCheckType checkType, bool isOverwrite, int checkValue, int amount = 1)
	{
	}

	private void UpdateValidAchievement(Achievement achievement, EnumData.AchievementCheckType checkType, bool isOverwrite, bool noticed, int checkValue, int limit, int amount = 1)
	{
	}

	private void UpdatePrepAchievement(Achievement achievement, int increasedValue, int limit, bool noticed, bool isOverwrite)
	{
	}

	private int AddPreAchievement(int achievementId, int increasedValue, int goal, bool noticed, bool isOverwrite)
	{
		return 0;
	}

	private int _AddNewPreAchievement(int achievementId, int increasedValue, int goal, bool noticed)
	{
		return 0;
	}

	private bool isCompleted(int achievementId, int checkValue, int addValue)
	{
		return false;
	}

	public void AddToNoticeList(Achievement achievement)
	{
	}

	public void ShowNotice()
	{
	}

	public int GetNextNotice()
	{
		return 0;
	}

	public void UpdateAchievementFinishCallback()
	{
	}

	public int GetFinishedAchievementsCount()
	{
		return 0;
	}

	public int GetFinishedVipAchievementCount()
	{
		return 0;
	}

	public void clearUpdateInfo(List<int> keys)
	{
	}

	public void CheckFinishedUserStatusMission(int achievementId)
	{
	}

	private bool CheckValidUserStatusMission(int category, EnumData.UserStatus userStatus)
	{
		return false;
	}

	public void StoreRequestValues()
	{
	}
}
