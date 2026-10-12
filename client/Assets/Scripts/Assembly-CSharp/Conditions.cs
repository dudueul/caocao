using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Conditions : PermanentBase
{
	public List<Condition> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Condition> m_conditions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Condition> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Condition get_Item(ObscuredInt id)
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

	private void RefineData(_Conditions src)
	{
	}

	public static void Load(ref Conditions res)
	{
	}
}
