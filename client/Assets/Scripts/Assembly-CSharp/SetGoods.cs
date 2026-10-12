using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SetGoods : PermanentBase
{
	public List<SetGood> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SetGood> m_setGoods;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SetGood> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SetGood get_Item(ObscuredInt id)
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

	private void RefineData(_SetGoods src)
	{
	}

	public static void Load(ref SetGoods res)
	{
	}
}
