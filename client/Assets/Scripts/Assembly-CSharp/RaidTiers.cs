using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RaidTiers : PermanentBase
{
	public List<RaidTier> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RaidTier> m_raidTiers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RaidTier> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RaidTier get_Item(ObscuredInt id)
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

	private void RefineData(_RaidTiers src)
	{
	}

	public static void Load(ref RaidTiers res)
	{
	}
}
