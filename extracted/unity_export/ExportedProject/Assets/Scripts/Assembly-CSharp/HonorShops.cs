using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class HonorShops : PermanentBase
{
	public List<HonorShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, HonorShop> m_honorShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, HonorShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public HonorShop get_Item(ObscuredInt id)
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

	private void RefineData(_HonorShops src)
	{
	}

	public static void Load(ref HonorShops res)
	{
	}
}
