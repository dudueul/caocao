using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitJobLvs : PermanentBase
{
	public List<UnitJobLv> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitJobLv> m_unitJobLvs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitJobLv> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitJobLv get_Item(ObscuredInt id)
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

	private void RefineData(_UnitJobLvs src)
	{
	}

	public static void Load(ref UnitJobLvs res)
	{
	}
}
