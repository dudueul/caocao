using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ShopOrders : PermanentBase
{
	public List<ShopOrder> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ShopOrder> m_shopOrders;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ShopOrder> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ShopOrder get_Item(ObscuredInt id)
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

	private void RefineData(_ShopOrders src)
	{
	}

	public static void Load(ref ShopOrders res)
	{
	}
}
