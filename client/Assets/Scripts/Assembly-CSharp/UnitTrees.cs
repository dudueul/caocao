using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitTrees : PermanentBase
{
	public List<UnitTree> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitTree> m_unitTrees;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitTree> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitTree get_Item(ObscuredInt id)
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

	private void RefineData(_UnitTrees src)
	{
	}

	public static void Load(ref UnitTrees res)
	{
	}
}
