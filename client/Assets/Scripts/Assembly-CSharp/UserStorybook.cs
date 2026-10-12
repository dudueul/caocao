using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserStorybook : ITransmittable
{
	private ObscuredInt m_id;

	public DateTime purchasedDate;

	public int id
	{
		get
		{
			return 0;
		}
	}

	public Storybook data
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private int GetTotalMissionCount()
	{
		return 0;
	}

	private int GetMissionCompletedCount(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}

	public bool CanUseModeLevel(EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public float GetAchievementRate(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0f;
	}

	public int GetFirstNotClearedScenarioId(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}

	public int GetLastNotClearedScenarioId(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}

	public bool IsCleared(EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public int GetPlayCount(EnumData.ScenarioModeLevel modeLevel)
	{
		return 0;
	}
}
