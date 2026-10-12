using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RuneSets : PermanentBase
{
	public List<RuneSet> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RuneSet> m_runeSets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RuneSet> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RuneSet get_Item(ObscuredInt id)
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

	private void RefineData(_RuneSets src)
	{
	}

	public static void Load(ref RuneSets res)
	{
	}
}
