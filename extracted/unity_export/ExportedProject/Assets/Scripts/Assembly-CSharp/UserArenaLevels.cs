using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaLevels : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserArenaLevel> m_lv2ArenaLevel;

	private List<int> m_levels;

	private static int m_maxLevel;

	public Action deserializeCallback;

	public Dictionary<int, UserArenaLevel> lv2Arena
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> lvs
	{
		get
		{
			return null;
		}
	}

	public static int maxLevel
	{
		get
		{
			return 0;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserArenaLevel get_Item(int level)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
