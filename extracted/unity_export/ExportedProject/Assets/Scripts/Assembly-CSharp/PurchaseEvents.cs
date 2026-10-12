using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class PurchaseEvents : PermanentBase
{
	public List<PurchaseEvent> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, PurchaseEvent> m_purchaseEvents;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, PurchaseEvent> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public PurchaseEvent get_Item(ObscuredInt id)
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

	private void RefineData(_PurchaseEvents src)
	{
	}

	public static void Load(ref PurchaseEvents res)
	{
	}
}
