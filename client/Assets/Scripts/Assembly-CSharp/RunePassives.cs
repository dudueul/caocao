using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RunePassives : PermanentBase
{
	public List<RunePassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RunePassive> m_runePassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RunePassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RunePassive get_Item(ObscuredInt id)
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

	private void RefineData(_RunePassives src)
	{
	}

	public static void Load(ref RunePassives res)
	{
	}
}
