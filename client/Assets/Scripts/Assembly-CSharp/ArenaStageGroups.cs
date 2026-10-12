using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaStageGroups : PermanentBase
{
	public List<ArenaStageGroup> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaStageGroup> m_arenaStageGroups;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaStageGroup> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaStageGroup get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaStageGroups src)
	{
	}

	public static void Load(ref ArenaStageGroups res)
	{
	}
}
