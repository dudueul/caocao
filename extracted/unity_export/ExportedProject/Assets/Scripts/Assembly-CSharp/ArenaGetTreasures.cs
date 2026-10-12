using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaGetTreasures : PermanentBase
{
	public List<ArenaGetTreasure> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaGetTreasure> m_arenaGetTreasures;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaGetTreasure> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaGetTreasure get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaGetTreasures src)
	{
	}

	public static void Load(ref ArenaGetTreasures res)
	{
	}
}
