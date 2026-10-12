using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RecommendShops : PermanentBase
{
	public List<RecommendShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RecommendShop> m_recommendShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RecommendShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RecommendShop get_Item(ObscuredInt id)
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

	private void RefineData(_RecommendShops src)
	{
	}

	public static void Load(ref RecommendShops res)
	{
	}
}
