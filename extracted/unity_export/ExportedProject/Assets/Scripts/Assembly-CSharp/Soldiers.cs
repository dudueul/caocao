using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Soldiers : PermanentBase
{
	public List<Soldier> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Soldier> m_soldiers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Soldier> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Soldier get_Item(ObscuredInt id)
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

	private void RefineData(_Soldiers src)
	{
	}

	public static void Load(ref Soldiers res)
	{
	}
}
