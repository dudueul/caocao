using System;
using System.Collections.Generic;
using LitJson;

public class UserInteractives : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserInteractive> m_srl2Interactives;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserInteractive> srl2Interactives
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserInteractive get_Item(ulong srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserInteractive GetInteractive(int storybookId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return null;
	}

	public int GetCurScenarioId(int storybookId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return 0;
	}

	public InteractiveSaveflag GetInteractiveSaveflag(int storybookId, EnumData.ScenarioModeLevel scenarioModeLevel, int saveFlagId)
	{
		return null;
	}

	public int GetInteractiveSaveflagValue(int storybookId, EnumData.ScenarioModeLevel scenarioModeLevel, int saveFlagId)
	{
		return 0;
	}

	public bool IsUsedSaveFlag(int scenarioId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return false;
	}

	public int GetAmbition(int scenarioId, bool include, int storybookId, int scenarioFlagId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return 0;
	}

	public int GetCurrentAmbition(int storybookId, int scenarioFlagId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return 0;
	}

	private List<InteractiveSaveflag> GetSaveFlagsUntilScenario(int scenarioId, bool include, int storybookId, int scenarioFlagId, EnumData.ScenarioModeLevel scenarioModeLevel)
	{
		return null;
	}

	private int CalculateAmbitions(List<InteractiveSaveflag> saveFlags, int scenarioFlagId = 0)
	{
		return 0;
	}
}
