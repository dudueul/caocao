using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class MarketShops : PermanentBase
{
	public List<MarketShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, MarketShop> m_marketShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, MarketShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public MarketShop get_Item(ObscuredInt id)
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

	private void RefineData(_MarketShops src)
	{
	}

	public static void Load(ref MarketShops res)
	{
	}
}
