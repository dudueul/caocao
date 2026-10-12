using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossPassives : PermanentBase
{
	public List<WorldBossPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossPassive> m_worldBossPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossPassive get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossPassives src)
	{
	}

	public static void Load(ref WorldBossPassives res)
	{
	}
}
