using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CashShops : PermanentBase
{
	public List<CashShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CashShop> m_cashShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CashShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CashShop get_Item(ObscuredInt id)
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

	private void RefineData(_CashShops src)
	{
	}

	public static void Load(ref CashShops res)
	{
	}
}
