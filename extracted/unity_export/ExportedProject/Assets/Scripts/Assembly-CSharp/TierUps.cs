using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TierUps : PermanentBase
{
	public List<TierUp> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TierUp> m_tierUps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TierUp> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TierUp get_Item(ObscuredInt id)
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

	private void RefineData(_TierUps src)
	{
	}

	public static void Load(ref TierUps res)
	{
	}
}
