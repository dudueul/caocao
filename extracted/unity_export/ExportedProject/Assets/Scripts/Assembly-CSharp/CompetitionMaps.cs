using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CompetitionMaps : PermanentBase
{
	public List<CompetitionMap> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CompetitionMap> m_competitionMaps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CompetitionMap> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CompetitionMap get_Item(ObscuredInt id)
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

	private void RefineData(_CompetitionMaps src)
	{
	}

	public static void Load(ref CompetitionMaps res)
	{
	}
}
