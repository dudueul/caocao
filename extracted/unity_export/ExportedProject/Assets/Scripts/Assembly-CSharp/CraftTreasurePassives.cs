using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CraftTreasurePassives : PermanentBase
{
	public List<CraftTreasurePassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CraftTreasurePassive> m_craftTreasurePassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CraftTreasurePassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CraftTreasurePassive get_Item(ObscuredInt id)
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

	private void RefineData(_CraftTreasurePassives src)
	{
	}

	public static void Load(ref CraftTreasurePassives res)
	{
	}
}
