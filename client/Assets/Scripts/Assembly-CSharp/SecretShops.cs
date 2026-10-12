using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SecretShops : PermanentBase
{
	public List<SecretShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SecretShop> m_secretShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SecretShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SecretShop get_Item(ObscuredInt id)
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

	private void RefineData(_SecretShops src)
	{
	}

	public static void Load(ref SecretShops res)
	{
	}
}
