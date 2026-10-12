using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class LinkedGoodses : PermanentBase
{
	public List<LinkedGoods> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, LinkedGoods> m_linkedGoodses;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, LinkedGoods> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public LinkedGoods get_Item(ObscuredInt id)
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

	private void RefineData(_LinkedGoodses src)
	{
	}

	public static void Load(ref LinkedGoodses res)
	{
	}
}
