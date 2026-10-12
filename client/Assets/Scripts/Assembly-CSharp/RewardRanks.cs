using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RewardRanks : PermanentBase
{
	public List<RewardRank> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RewardRank> m_rewardRanks;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RewardRank> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RewardRank get_Item(ObscuredInt id)
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

	private void RefineData(_RewardRanks src)
	{
	}

	public static void Load(ref RewardRanks res)
	{
	}
}
