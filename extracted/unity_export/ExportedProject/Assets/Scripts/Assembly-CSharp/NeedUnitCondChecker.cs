using System.Collections.Generic;
using System.Collections.ObjectModel;

public class NeedUnitCondChecker : SceneSingleton<NeedUnitCondChecker>
{
	public enum CheckType
	{
		Scenario = 0,
		Battle = 1,
		Storybook = 2
	}

	private Dictionary<string, List<EventCmdBase>> m_scriptName2EventCmds;

	private List<int> m_needUnitIds;

	public CheckType checkType { get; private set; }

	public int checkId { get; private set; }

	public ReadOnlyCollection<int> needUnitIds
	{
		get
		{
			return null;
		}
	}

	public bool CheckScenarioNeedUnitCondition(int scenarioId)
	{
		return false;
	}

	public ReadOnlyCollection<int> GetNeedUnitIdsFromScenario(int scenarioId)
	{
		return null;
	}

	public bool CheckStorybookNeedUnitCondition(int storybookId)
	{
		return false;
	}

	public ReadOnlyCollection<int> GetNeedUnitIdsFromStorybook(int storybookId)
	{
		return null;
	}

	public bool CheckBattlemapNeedUnitCondition(int battlemapId)
	{
		return false;
	}

	public ReadOnlyCollection<int> GetNeedUnitIdsFromBattlemap(int battlemapId)
	{
		return null;
	}
}
