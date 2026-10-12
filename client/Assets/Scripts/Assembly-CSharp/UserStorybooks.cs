using System;
using System.Collections.Generic;
using LitJson;

public class UserStorybooks : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserStorybook> m_id2Storybooks;

	private List<int> m_ids;

	private int m_newCount;

	public Action deserializeCallback;

	private const string PREFS_KEY_STORYBOOK_RECENT_PLAYED = "{0}_storybook_{1}_recentPlayed";

	private const string PREFS_KEY_STORYBOOK_NEW_VIEW = "{0}_storybook_{1}_newView";

	public Dictionary<int, UserStorybook> id2Storybooks
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public int NewCount
	{
		get
		{
			return 0;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserStorybook get_Item(int id)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public static int GetFirstScenarioIdInStorybook(int storybookId)
	{
		return 0;
	}

	public static List<int> GetLastScenarioIdInStorybook(int storybookId)
	{
		return null;
	}

	public static List<int> GetScenarioIdsInStorybook(int storybookId)
	{
		return null;
	}

	public bool CanEnter(int storybookId)
	{
		return false;
	}

	public bool CanPassNeedUnitsCondition(int storybookId)
	{
		return false;
	}

	public bool CanPassPreStorybookClrearCondition(int storybookId)
	{
		return false;
	}

	public bool IsCleared(int storybookId, EnumData.ScenarioModeLevel modeLevel)
	{
		return false;
	}

	public void SetNewCount()
	{
	}

	public void SetIsNewAll(ulong userId)
	{
	}

	public static bool IsNew(ulong userId, int storybookId, UserStorybook userStorybook)
	{
		return false;
	}

	public static string GetPrefsKeyString_StorybookRecentPlayed(ulong userId, int storybookId)
	{
		return null;
	}

	public static string GetPrefsKeyString_StorybookNewView(ulong userId, int storybookId)
	{
		return null;
	}
}
