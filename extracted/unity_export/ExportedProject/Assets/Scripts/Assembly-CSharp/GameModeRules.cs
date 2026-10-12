using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GameModeRules : PermanentBase
{
	public List<GameModeRule> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GameModeRule> m_gameModeRules;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GameModeRule> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GameModeRule get_Item(ObscuredInt id)
	{
		return null;
	}

	public List<ObscuredInt> ids
	{
		get
		{
			return null;
		}
	}

	private void RefineData(_GameModeRules src)
	{
	}

	public static void Load(ref GameModeRules res)
	{
	}
}
