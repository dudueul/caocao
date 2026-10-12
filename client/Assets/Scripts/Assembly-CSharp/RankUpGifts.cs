using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RankUpGifts : PermanentBase
{
	public List<RankUpGift> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RankUpGift> m_rankUpGifts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RankUpGift> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RankUpGift get_Item(ObscuredInt id)
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

	private void RefineData(_RankUpGifts src)
	{
	}

	public static void Load(ref RankUpGifts res)
	{
	}
}
