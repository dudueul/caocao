using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitRanks : PermanentBase
{
	public List<UnitRank> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitRank> m_unitRanks;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitRank> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitRank get_Item(ObscuredInt id)
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

	private void RefineData(_UnitRanks src)
	{
	}

	public static void Load(ref UnitRanks res)
	{
	}
}
