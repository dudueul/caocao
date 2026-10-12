using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossTiers : PermanentBase
{
	public List<WorldBossTier> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossTier> m_worldBossTiers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossTier> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossTier get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossTiers src)
	{
	}

	public static void Load(ref WorldBossTiers res)
	{
	}
}
