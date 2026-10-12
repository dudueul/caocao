using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Protects : PermanentBase
{
	public List<Protect> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Protect> m_protects;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Protect> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Protect get_Item(ObscuredInt id)
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

	private void RefineData(_Protects src)
	{
	}

	public static void Load(ref Protects res)
	{
	}
}
