using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ItemRatios : PermanentBase
{
	public List<ItemRatio> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ItemRatio> m_itemRatios;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ItemRatio> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ItemRatio get_Item(ObscuredInt id)
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

	private void RefineData(_ItemRatios src)
	{
	}

	public static void Load(ref ItemRatios res)
	{
	}
}
