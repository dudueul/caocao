using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RunePassiveExps : PermanentBase
{
	public List<RunePassiveExp> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RunePassiveExp> m_runePassiveExps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RunePassiveExp> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RunePassiveExp get_Item(ObscuredInt id)
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

	private void RefineData(_RunePassiveExps src)
	{
	}

	public static void Load(ref RunePassiveExps res)
	{
	}
}
