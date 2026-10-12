using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BoxShops : PermanentBase
{
	public List<BoxShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BoxShop> m_boxShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BoxShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BoxShop get_Item(ObscuredInt id)
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

	private void RefineData(_BoxShops src)
	{
	}

	public static void Load(ref BoxShops res)
	{
	}
}
