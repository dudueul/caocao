using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Loadings : PermanentBase
{
	public List<Loading> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Loading> m_loadings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Loading> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Loading get_Item(ObscuredInt id)
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

	private void RefineData(_Loadings src)
	{
	}

	public static void Load(ref Loadings res)
	{
	}
}
