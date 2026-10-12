using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TreasureEnhancePassives : PermanentBase
{
	public List<TreasureEnhancePassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TreasureEnhancePassive> m_treasureEnhancePassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TreasureEnhancePassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TreasureEnhancePassive get_Item(ObscuredInt id)
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

	private void RefineData(_TreasureEnhancePassives src)
	{
	}

	public static void Load(ref TreasureEnhancePassives res)
	{
	}
}
