using System;
using System.Collections.Generic;
using LitJson;

public class UserScenarios : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserScenario> m_id2Scenarios;

	private List<int> m_ids;

	public Action m_deserializeCallback;

	private int m_ambition;

	public Dictionary<int, UserScenario> id2Scenarios
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserScenario get_Item(int id)
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

	public int ScenarioNormalVictoryCount
	{
		get
		{
			return 0;
		}
	}

	public int ScenarioHardVictoryCount
	{
		get
		{
			return 0;
		}
	}

	public int ScenarioClassicVictoryCount
	{
		get
		{
			return 0;
		}
	}

	public int ambition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public int GetAchievedGradesCount(int storybookId, EnumData.ScenarioModeLevel mode, bool isAll = false)
	{
		return 0;
	}

	public int GetAllAchievedGradesCount(EnumData.ScenarioModeLevel mode)
	{
		return 0;
	}

	public int GetHighestCleardScenarioID()
	{
		return 0;
	}

	public bool CanPlayScenario(int scenarioId, EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public bool CanPlayScenarioNew(int scenarioId, EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public EnumData.ScenarioModeUI GetUIState(EnumData.ScenarioModeLevel scenarioModeLevel, int scenarioId)
	{
		return EnumData.ScenarioModeUI.Playable;
	}

	public void SetEnterState(int scenarioId, EnumData.ScenarioModeLevel modeLevel)
	{
	}

	public bool IsAvailableRoot(int scenarioId, EnumData.ScenarioModeLevel scenarioModeLevel = EnumData.ScenarioModeLevel.Normal)
	{
		return false;
	}

	public bool IsAvailableScenario(int scenarioId, int stopScenarioId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return false;
	}

	public List<int> GetPrevScenarioIds(int scenarioId)
	{
		return null;
	}

	public List<UserScenario> GetPlayableStorybookScenarios(int storybookId)
	{
		return null;
	}
}
