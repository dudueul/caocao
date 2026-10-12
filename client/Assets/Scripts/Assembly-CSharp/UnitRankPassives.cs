using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitRankPassives : PermanentBase
{
	public List<UnitRankPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitRankPassive> m_unitRankPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitRankPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitRankPassive get_Item(ObscuredInt id)
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

	private void RefineData(_UnitRankPassives src)
	{
	}

	public static void Load(ref UnitRankPassives res)
	{
	}
}
