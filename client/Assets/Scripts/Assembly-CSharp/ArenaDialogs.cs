using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaDialogs : PermanentBase
{
	public List<ArenaDialog> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaDialog> m_arenaDialogs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaDialog> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaDialog get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaDialogs src)
	{
	}

	public static void Load(ref ArenaDialogs res)
	{
	}
}
