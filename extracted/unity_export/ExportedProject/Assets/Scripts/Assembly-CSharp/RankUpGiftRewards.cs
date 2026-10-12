using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RankUpGiftRewards : PermanentBase
{
	public List<RankUpGiftReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RankUpGiftReward> m_rankUpGiftRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RankUpGiftReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RankUpGiftReward get_Item(ObscuredInt id)
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

	private void RefineData(_RankUpGiftRewards src)
	{
	}

	public static void Load(ref RankUpGiftRewards res)
	{
	}
}
