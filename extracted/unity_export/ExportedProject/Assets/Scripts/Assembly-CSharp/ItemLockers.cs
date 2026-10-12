using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ItemLockers : PermanentBase
{
	public List<ItemLocker> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ItemLocker> m_itemLockers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ItemLocker> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ItemLocker get_Item(ObscuredInt id)
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

	private void RefineData(_ItemLockers src)
	{
	}

	public static void Load(ref ItemLockers res)
	{
	}
}
