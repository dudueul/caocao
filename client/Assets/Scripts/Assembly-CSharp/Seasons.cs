using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Seasons : PermanentBase
{
	public List<Season> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Season> m_seasons;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Season> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Season get_Item(ObscuredInt id)
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

	private void RefineData(_Seasons src)
	{
	}

	public static void Load(ref Seasons res)
	{
	}
}
