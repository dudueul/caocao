using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class EventDailyGifts : PermanentBase
{
	public List<EventDailyGift> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, EventDailyGift> m_eventDailyGifts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, EventDailyGift> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public EventDailyGift get_Item(ObscuredInt id)
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

	private void RefineData(_EventDailyGifts src)
	{
	}

	public static void Load(ref EventDailyGifts res)
	{
	}
}
