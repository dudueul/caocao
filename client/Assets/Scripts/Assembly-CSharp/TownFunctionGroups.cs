using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TownFunctionGroups : PermanentBase
{
	public List<TownFunctionGroup> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TownFunctionGroup> m_townFunctionGroups;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TownFunctionGroup> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TownFunctionGroup get_Item(ObscuredInt id)
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

	private void RefineData(_TownFunctionGroups src)
	{
	}

	public static void Load(ref TownFunctionGroups res)
	{
	}
}
