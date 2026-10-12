using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaGetPassives : PermanentBase
{
	public List<ArenaGetPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaGetPassive> m_arenaGetPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaGetPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaGetPassive get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaGetPassives src)
	{
	}

	public static void Load(ref ArenaGetPassives res)
	{
	}
}
