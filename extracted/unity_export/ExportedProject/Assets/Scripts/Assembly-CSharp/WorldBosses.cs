using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBosses : PermanentBase
{
	public List<WorldBoss> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBoss> m_worldBosses;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBoss> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBoss get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBosses src)
	{
	}

	public static void Load(ref WorldBosses res)
	{
	}
}
