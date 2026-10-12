using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitPassives : PermanentBase
{
	public List<UnitPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitPassive> m_unitPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitPassive get_Item(ObscuredInt id)
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

	private void RefineData(_UnitPassives src)
	{
	}

	public static void Load(ref UnitPassives res)
	{
	}
}
