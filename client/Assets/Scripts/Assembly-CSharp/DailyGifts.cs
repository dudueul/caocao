using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class DailyGifts : PermanentBase
{
	public List<DailyGift> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, DailyGift> m_dailyGifts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, DailyGift> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public DailyGift get_Item(ObscuredInt id)
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

	private void RefineData(_DailyGifts src)
	{
	}

	public static void Load(ref DailyGifts res)
	{
	}
}
