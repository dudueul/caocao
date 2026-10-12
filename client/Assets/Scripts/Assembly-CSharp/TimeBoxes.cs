using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TimeBoxes : PermanentBase
{
	public List<TimeBox> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TimeBox> m_timeBoxes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TimeBox> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TimeBox get_Item(ObscuredInt id)
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

	private void RefineData(_TimeBoxes src)
	{
	}

	public static void Load(ref TimeBoxes res)
	{
	}
}
