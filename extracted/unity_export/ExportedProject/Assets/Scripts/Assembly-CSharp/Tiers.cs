using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Tiers : PermanentBase
{
	public List<Tier> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Tier> m_tiers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Tier> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Tier get_Item(ObscuredInt id)
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

	private void RefineData(_Tiers src)
	{
	}

	public static void Load(ref Tiers res)
	{
	}
}
