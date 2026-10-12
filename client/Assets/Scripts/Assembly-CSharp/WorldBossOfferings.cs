using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossOfferings : PermanentBase
{
	public List<WorldBossOffering> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossOffering> m_worldBossOfferings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossOffering> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossOffering get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossOfferings src)
	{
	}

	public static void Load(ref WorldBossOfferings res)
	{
	}
}
