using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GoodsShops : PermanentBase
{
	public List<GoodsShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GoodsShop> m_goodsShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GoodsShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GoodsShop get_Item(ObscuredInt id)
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

	private void RefineData(_GoodsShops src)
	{
	}

	public static void Load(ref GoodsShops res)
	{
	}
}
