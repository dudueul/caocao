using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ThousandBattles : PermanentBase
{
	public List<ThousandBattle> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ThousandBattle> m_thousandBattles;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ThousandBattle> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ThousandBattle get_Item(ObscuredInt id)
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

	private void RefineData(_ThousandBattles src)
	{
	}

	public static void Load(ref ThousandBattles res)
	{
	}
}
