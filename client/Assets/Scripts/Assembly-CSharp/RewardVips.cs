using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RewardVips : PermanentBase
{
	public List<RewardVip> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RewardVip> m_rewardVips;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RewardVip> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RewardVip get_Item(ObscuredInt id)
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

	private void RefineData(_RewardVips src)
	{
	}

	public static void Load(ref RewardVips res)
	{
	}
}
