using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class HotTimeRewards : PermanentBase
{
	public List<HotTimeReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, HotTimeReward> m_hotTimeRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, HotTimeReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public HotTimeReward get_Item(ObscuredInt id)
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

	private void RefineData(_HotTimeRewards src)
	{
	}

	public static void Load(ref HotTimeRewards res)
	{
	}
}
