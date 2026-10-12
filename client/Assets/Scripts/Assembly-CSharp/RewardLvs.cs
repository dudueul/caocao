using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RewardLvs : PermanentBase
{
	public List<RewardLv> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RewardLv> m_rewardLvs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RewardLv> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RewardLv get_Item(ObscuredInt id)
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

	private void RefineData(_RewardLvs src)
	{
	}

	public static void Load(ref RewardLvs res)
	{
	}
}
