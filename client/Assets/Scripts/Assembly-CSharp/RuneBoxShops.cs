using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RuneBoxShops : PermanentBase
{
	public List<RuneBoxShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RuneBoxShop> m_runeBoxShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RuneBoxShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RuneBoxShop get_Item(ObscuredInt id)
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

	private void RefineData(_RuneBoxShops src)
	{
	}

	public static void Load(ref RuneBoxShops res)
	{
	}
}
