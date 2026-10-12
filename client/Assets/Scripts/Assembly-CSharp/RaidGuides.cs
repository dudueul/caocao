using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RaidGuides : PermanentBase
{
	public List<RaidGuide> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RaidGuide> m_raidGuides;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RaidGuide> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RaidGuide get_Item(ObscuredInt id)
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

	private void RefineData(_RaidGuides src)
	{
	}

	public static void Load(ref RaidGuides res)
	{
	}
}
