using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitRankSeasons : PermanentBase
{
	public List<UnitRankSeason> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitRankSeason> m_unitRankSeasons;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitRankSeason> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitRankSeason get_Item(ObscuredInt id)
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

	private void RefineData(_UnitRankSeasons src)
	{
	}

	public static void Load(ref UnitRankSeasons res)
	{
	}
}
