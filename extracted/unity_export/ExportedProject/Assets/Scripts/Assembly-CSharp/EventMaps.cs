using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class EventMaps : PermanentBase
{
	public List<EventMap> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, EventMap> m_eventMaps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, EventMap> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public EventMap get_Item(ObscuredInt id)
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

	private void RefineData(_EventMaps src)
	{
	}

	public static void Load(ref EventMaps res)
	{
	}
}
