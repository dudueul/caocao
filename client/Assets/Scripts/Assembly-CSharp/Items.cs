using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Items : PermanentBase
{
	public List<Item> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Item> m_items;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Item> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Item get_Item(ObscuredInt id)
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

	private void RefineData(_Items src)
	{
	}

	public static void Load(ref Items res)
	{
	}
}
