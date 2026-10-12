using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Clouds : PermanentBase
{
	public List<Cloud> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Cloud> m_clouds;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Cloud> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Cloud get_Item(ObscuredInt id)
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

	private void RefineData(_Clouds src)
	{
	}

	public static void Load(ref Clouds res)
	{
	}
}
