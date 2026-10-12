using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossSeasons : PermanentBase
{
	public List<WorldBossSeason> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossSeason> m_worldBossSeasons;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossSeason> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossSeason get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossSeasons src)
	{
	}

	public static void Load(ref WorldBossSeasons res)
	{
	}
}
