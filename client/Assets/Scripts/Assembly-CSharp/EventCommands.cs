using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class EventCommands : PermanentBase
{
	public List<EventCommand> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, EventCommand> m_eventCommands;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, EventCommand> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public EventCommand get_Item(ObscuredInt id)
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

	private void RefineData(_EventCommands src)
	{
	}

	public static void Load(ref EventCommands res)
	{
	}
}
