using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class LevelExps : PermanentBase
{
	public List<LevelExp> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, LevelExp> m_levelExps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, LevelExp> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public LevelExp get_Item(ObscuredInt id)
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

	private void RefineData(_LevelExps src)
	{
	}

	public static void Load(ref LevelExps res)
	{
	}
}
