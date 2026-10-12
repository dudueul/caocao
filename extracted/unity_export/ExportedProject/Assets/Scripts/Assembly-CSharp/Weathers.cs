using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Weathers : PermanentBase
{
	public List<Weather> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Weather> m_weathers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Weather> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Weather get_Item(ObscuredInt id)
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

	private void RefineData(_Weathers src)
	{
	}

	public static void Load(ref Weathers res)
	{
	}
}
