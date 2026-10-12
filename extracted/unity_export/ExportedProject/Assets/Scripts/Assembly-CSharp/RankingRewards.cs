using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RankingRewards : PermanentBase
{
	public List<RankingReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RankingReward> m_rankingRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RankingReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RankingReward get_Item(ObscuredInt id)
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

	private void RefineData(_RankingRewards src)
	{
	}

	public static void Load(ref RankingRewards res)
	{
	}
}
