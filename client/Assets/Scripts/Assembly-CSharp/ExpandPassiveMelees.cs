using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ExpandPassiveMelees : PermanentBase
{
	public List<ExpandPassiveMelee> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ExpandPassiveMelee> m_expandPassiveMelees;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ExpandPassiveMelee> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ExpandPassiveMelee get_Item(ObscuredInt id)
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

	private void RefineData(_ExpandPassiveMelees src)
	{
	}

	public static void Load(ref ExpandPassiveMelees res)
	{
	}
}
