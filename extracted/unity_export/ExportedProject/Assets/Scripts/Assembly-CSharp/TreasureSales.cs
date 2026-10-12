using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TreasureSales : PermanentBase
{
	public List<TreasureSale> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TreasureSale> m_treasureSales;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TreasureSale> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TreasureSale get_Item(ObscuredInt id)
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

	private void RefineData(_TreasureSales src)
	{
	}

	public static void Load(ref TreasureSales res)
	{
	}
}
