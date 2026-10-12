using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GameModeRuleItems : PermanentBase
{
	public List<GameModeRuleItem> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GameModeRuleItem> m_gameModeRuleItems;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GameModeRuleItem> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GameModeRuleItem get_Item(ObscuredInt id)
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

	private void RefineData(_GameModeRuleItems src)
	{
	}

	public static void Load(ref GameModeRuleItems res)
	{
	}
}
