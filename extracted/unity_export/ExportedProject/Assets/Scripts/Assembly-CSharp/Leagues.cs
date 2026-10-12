using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Leagues : PermanentBase
{
	public List<League> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, League> m_leagues;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, League> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public League get_Item(ObscuredInt id)
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

	private void RefineData(_Leagues src)
	{
	}

	public static void Load(ref Leagues res)
	{
	}
}
