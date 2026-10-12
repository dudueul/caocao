using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SeasonPasses : PermanentBase
{
	public List<SeasonPass> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SeasonPass> m_seasonPasses;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SeasonPass> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SeasonPass get_Item(ObscuredInt id)
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

	private void RefineData(_SeasonPasses src)
	{
	}

	public static void Load(ref SeasonPasses res)
	{
	}
}
