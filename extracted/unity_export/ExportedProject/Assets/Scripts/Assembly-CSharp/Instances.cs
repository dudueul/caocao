using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Instances : PermanentBase
{
	public List<Instance> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Instance> m_instances;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Instance> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Instance get_Item(ObscuredInt id)
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

	private void RefineData(_Instances src)
	{
	}

	public static void Load(ref Instances res)
	{
	}
}
