using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Achievements : PermanentBase
{
	public List<Achievement> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Achievement> m_achievements;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Achievement> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Achievement get_Item(ObscuredInt id)
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

	private void RefineData(_Achievements src)
	{
	}

	public static void Load(ref Achievements res)
	{
	}
}
