using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TimeBoxOrders : PermanentBase
{
	public List<TimeBoxOrder> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TimeBoxOrder> m_timeBoxOrders;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TimeBoxOrder> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TimeBoxOrder get_Item(ObscuredInt id)
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

	private void RefineData(_TimeBoxOrders src)
	{
	}

	public static void Load(ref TimeBoxOrders res)
	{
	}
}
