using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaGetUnits : PermanentBase
{
	public List<ArenaGetUnit> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaGetUnit> m_arenaGetUnits;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaGetUnit> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaGetUnit get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaGetUnits src)
	{
	}

	public static void Load(ref ArenaGetUnits res)
	{
	}
}
