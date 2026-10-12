using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleMaps : PermanentBase
{
	public List<BattleMap> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleMap> m_battleMaps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleMap> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleMap get_Item(ObscuredInt id)
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

	private void RefineData(_BattleMaps src)
	{
	}

	public static void Load(ref BattleMaps res)
	{
	}
}
