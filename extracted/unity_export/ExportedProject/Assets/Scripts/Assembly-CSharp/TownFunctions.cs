using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TownFunctions : PermanentBase
{
	public List<TownFunction> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TownFunction> m_townFunctions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TownFunction> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TownFunction get_Item(ObscuredInt id)
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

	private void RefineData(_TownFunctions src)
	{
	}

	public static void Load(ref TownFunctions res)
	{
	}
}
