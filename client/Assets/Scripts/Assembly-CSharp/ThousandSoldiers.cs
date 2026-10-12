using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ThousandSoldiers : PermanentBase
{
	public List<ThousandSoldier> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ThousandSoldier> m_thousandSoldiers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ThousandSoldier> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ThousandSoldier get_Item(ObscuredInt id)
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

	private void RefineData(_ThousandSoldiers src)
	{
	}

	public static void Load(ref ThousandSoldiers res)
	{
	}
}
