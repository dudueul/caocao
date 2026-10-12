using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SpecialInstances : PermanentBase
{
	public List<SpecialInstance> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SpecialInstance> m_specialInstances;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SpecialInstance> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SpecialInstance get_Item(ObscuredInt id)
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

	private void RefineData(_SpecialInstances src)
	{
	}

	public static void Load(ref SpecialInstances res)
	{
	}
}
