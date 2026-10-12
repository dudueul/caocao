using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaStages : PermanentBase
{
	public List<ArenaStage> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaStage> m_arenaStages;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaStage> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaStage get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaStages src)
	{
	}

	public static void Load(ref ArenaStages res)
	{
	}
}
