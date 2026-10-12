using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Raids : PermanentBase
{
	public List<Raid> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Raid> m_raids;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Raid> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Raid get_Item(ObscuredInt id)
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

	private void RefineData(_Raids src)
	{
	}

	public static void Load(ref Raids res)
	{
	}
}
