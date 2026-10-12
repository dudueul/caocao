using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaAIs : PermanentBase
{
	public List<ArenaAI> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaAI> m_arenaAIs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaAI> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaAI get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaAIs src)
	{
	}

	public static void Load(ref ArenaAIs res)
	{
	}
}
