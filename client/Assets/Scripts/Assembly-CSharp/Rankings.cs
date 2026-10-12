using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Rankings : PermanentBase
{
	public List<Ranking> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Ranking> m_rankings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Ranking> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Ranking get_Item(ObscuredInt id)
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

	private void RefineData(_Rankings src)
	{
	}

	public static void Load(ref Rankings res)
	{
	}
}
