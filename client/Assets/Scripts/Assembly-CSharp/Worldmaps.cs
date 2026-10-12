using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Worldmaps : PermanentBase
{
	public List<Worldmap> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Worldmap> m_worldmaps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Worldmap> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Worldmap get_Item(ObscuredInt id)
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

	private void RefineData(_Worldmaps src)
	{
	}

	public static void Load(ref Worldmaps res)
	{
	}
}
