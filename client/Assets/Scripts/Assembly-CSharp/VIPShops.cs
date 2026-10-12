using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class VIPShops : PermanentBase
{
	public List<VIPShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, VIPShop> m_vIPShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, VIPShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public VIPShop get_Item(ObscuredInt id)
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

	private void RefineData(_VIPShops src)
	{
	}

	public static void Load(ref VIPShops res)
	{
	}
}
