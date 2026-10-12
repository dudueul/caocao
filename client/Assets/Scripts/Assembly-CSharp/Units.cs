using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Units : PermanentBase
{
	public List<Unit> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Unit> m_units;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Unit> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Unit get_Item(ObscuredInt id)
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

	private void RefineData(_Units src)
	{
	}

	public static void Load(ref Units res)
	{
	}
}
