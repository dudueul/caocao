using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossDmgRewards : PermanentBase
{
	public List<WorldBossDmgReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WorldBossDmgReward> m_worldBossDmgRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WorldBossDmgReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WorldBossDmgReward get_Item(ObscuredInt id)
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

	private void RefineData(_WorldBossDmgRewards src)
	{
	}

	public static void Load(ref WorldBossDmgRewards res)
	{
	}
}
