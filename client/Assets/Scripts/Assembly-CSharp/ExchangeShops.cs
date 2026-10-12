using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ExchangeShops : PermanentBase
{
	public List<ExchangeShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ExchangeShop> m_exchangeShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ExchangeShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ExchangeShop get_Item(ObscuredInt id)
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

	private void RefineData(_ExchangeShops src)
	{
	}

	public static void Load(ref ExchangeShops res)
	{
	}
}
