using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaScenarios : PermanentBase
{
	public List<ArenaScenario> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaScenario> m_arenaScenarios;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaScenario> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaScenario get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaScenarios src)
	{
	}

	public static void Load(ref ArenaScenarios res)
	{
	}
}
