using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaPresets : PermanentBase
{
	public List<ArenaPreset> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaPreset> m_arenaPresets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaPreset> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaPreset get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaPresets src)
	{
	}

	public static void Load(ref ArenaPresets res)
	{
	}
}
