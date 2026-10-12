using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SeasonPassRewards : PermanentBase
{
	public List<SeasonPassReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SeasonPassReward> m_seasonPassRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SeasonPassReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SeasonPassReward get_Item(ObscuredInt id)
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

	private void RefineData(_SeasonPassRewards src)
	{
	}

	public static void Load(ref SeasonPassRewards res)
	{
	}
}
