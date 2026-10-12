using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Heros : PermanentBase
{
	public List<Hero> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Hero> m_heros;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Hero> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Hero get_Item(ObscuredInt id)
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

	private void RefineData(_Heros src)
	{
	}

	public static void Load(ref Heros res)
	{
	}
}
