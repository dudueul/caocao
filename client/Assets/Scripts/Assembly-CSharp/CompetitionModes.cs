using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CompetitionModes : PermanentBase
{
	public List<CompetitionMode> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CompetitionMode> m_competitionModes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CompetitionMode> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CompetitionMode get_Item(ObscuredInt id)
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

	private void RefineData(_CompetitionModes src)
	{
	}

	public static void Load(ref CompetitionModes res)
	{
	}
}
