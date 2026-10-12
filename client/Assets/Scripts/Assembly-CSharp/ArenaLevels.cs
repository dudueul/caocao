using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaLevels : PermanentBase
{
	public List<ArenaLevel> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaLevel> m_arenaLevels;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaLevel> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaLevel get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaLevels src)
	{
	}

	public static void Load(ref ArenaLevels res)
	{
	}
}
