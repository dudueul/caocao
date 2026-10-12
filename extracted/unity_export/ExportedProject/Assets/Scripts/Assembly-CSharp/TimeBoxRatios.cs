using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TimeBoxRatios : PermanentBase
{
	public List<TimeBoxRatio> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TimeBoxRatio> m_timeBoxRatios;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TimeBoxRatio> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TimeBoxRatio get_Item(ObscuredInt id)
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

	private void RefineData(_TimeBoxRatios src)
	{
	}

	public static void Load(ref TimeBoxRatios res)
	{
	}
}
