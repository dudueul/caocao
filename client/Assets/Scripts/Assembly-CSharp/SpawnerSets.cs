using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SpawnerSets : PermanentBase
{
	public List<SpawnerSet> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SpawnerSet> m_spawnerSets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SpawnerSet> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SpawnerSet get_Item(ObscuredInt id)
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

	private void RefineData(_SpawnerSets src)
	{
	}

	public static void Load(ref SpawnerSets res)
	{
	}
}
