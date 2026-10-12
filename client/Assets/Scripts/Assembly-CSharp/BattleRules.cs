using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleRules : PermanentBase
{
	public List<BattleRule> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleRule> m_battleRules;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleRule> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleRule get_Item(ObscuredInt id)
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

	private void RefineData(_BattleRules src)
	{
	}

	public static void Load(ref BattleRules res)
	{
	}
}
