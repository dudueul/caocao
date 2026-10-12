using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CommonRewards : PermanentBase
{
	public List<CommonReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CommonReward> m_commonRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CommonReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CommonReward get_Item(ObscuredInt id)
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

	private void RefineData(_CommonRewards src)
	{
	}

	public static void Load(ref CommonRewards res)
	{
	}
}
