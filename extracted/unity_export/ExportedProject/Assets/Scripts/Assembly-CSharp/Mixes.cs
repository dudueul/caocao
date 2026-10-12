using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Mixes : PermanentBase
{
	public List<Mix> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Mix> m_mixes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Mix> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Mix get_Item(ObscuredInt id)
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

	private void RefineData(_Mixes src)
	{
	}

	public static void Load(ref Mixes res)
	{
	}
}
