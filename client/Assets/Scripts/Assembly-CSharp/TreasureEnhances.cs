using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TreasureEnhances : PermanentBase
{
	public List<TreasureEnhance> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TreasureEnhance> m_treasureEnhances;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TreasureEnhance> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TreasureEnhance get_Item(ObscuredInt id)
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

	private void RefineData(_TreasureEnhances src)
	{
	}

	public static void Load(ref TreasureEnhances res)
	{
	}
}
