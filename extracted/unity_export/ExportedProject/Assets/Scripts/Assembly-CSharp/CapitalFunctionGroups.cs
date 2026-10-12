using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CapitalFunctionGroups : PermanentBase
{
	public List<CapitalFunctionGroup> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CapitalFunctionGroup> m_capitalFunctionGroups;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CapitalFunctionGroup> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CapitalFunctionGroup get_Item(ObscuredInt id)
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

	private void RefineData(_CapitalFunctionGroups src)
	{
	}

	public static void Load(ref CapitalFunctionGroups res)
	{
	}
}
