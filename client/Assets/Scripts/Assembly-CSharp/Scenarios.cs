using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Scenarios : PermanentBase
{
	public List<Scenario> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Scenario> m_scenarios;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Scenario> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Scenario get_Item(ObscuredInt id)
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

	private void RefineData(_Scenarios src)
	{
	}

	public static void Load(ref Scenarios res)
	{
	}
}
