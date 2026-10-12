using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AutoEquips : PermanentBase
{
	public List<AutoEquip> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AutoEquip> m_autoEquips;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AutoEquip> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AutoEquip get_Item(ObscuredInt id)
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

	private void RefineData(_AutoEquips src)
	{
	}

	public static void Load(ref AutoEquips res)
	{
	}
}
