using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaThemes : PermanentBase
{
	public List<ArenaTheme> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaTheme> m_arenaThemes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaTheme> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaTheme get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaThemes src)
	{
	}

	public static void Load(ref ArenaThemes res)
	{
	}
}
