using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TradeShops : PermanentBase
{
	public List<TradeShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TradeShop> m_tradeShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TradeShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TradeShop get_Item(ObscuredInt id)
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

	private void RefineData(_TradeShops src)
	{
	}

	public static void Load(ref TradeShops res)
	{
	}
}
