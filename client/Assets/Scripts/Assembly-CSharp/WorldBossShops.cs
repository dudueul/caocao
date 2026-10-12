using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossShops : PermanentBase
{
	public List<WorldBossShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossShop> m_worldBossShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossShop get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossShops src)
	{
	}

	public static void Load(ref WorldBossShops res)
	{
	}
}
