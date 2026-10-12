using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SeasonRewards : PermanentBase
{
	public List<SeasonReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SeasonReward> m_seasonRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SeasonReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SeasonReward get_Item(ObscuredInt id)
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

	private void RefineData(_SeasonRewards src)
	{
	}

	public static void Load(ref SeasonRewards res)
	{
	}
}
