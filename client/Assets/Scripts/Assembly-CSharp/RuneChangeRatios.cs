using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RuneChangeRatios : PermanentBase
{
	public List<RuneChangeRatio> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RuneChangeRatio> m_runeChangeRatios;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RuneChangeRatio> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RuneChangeRatio get_Item(ObscuredInt id)
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

	private void RefineData(_RuneChangeRatios src)
	{
	}

	public static void Load(ref RuneChangeRatios res)
	{
	}
}
