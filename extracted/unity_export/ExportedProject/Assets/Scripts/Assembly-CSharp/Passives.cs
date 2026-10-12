using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Passives : PermanentBase
{
	public List<Passive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Passive> m_passives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Passive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Passive get_Item(ObscuredInt id)
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

	private void RefineData(_Passives src)
	{
	}

	public static void Load(ref Passives res)
	{
	}
}
