using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class EventRewards : PermanentBase
{
	public List<EventReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, EventReward> m_eventRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, EventReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public EventReward get_Item(ObscuredInt id)
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

	private void RefineData(_EventRewards src)
	{
	}

	public static void Load(ref EventRewards res)
	{
	}
}
