using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NewThousandBattles : PermanentBase
{
	public List<NewThousandBattle> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NewThousandBattle> m_newThousandBattles;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NewThousandBattle> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NewThousandBattle get_Item(ObscuredInt id)
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

	private void RefineData(_NewThousandBattles src)
	{
	}

	public static void Load(ref NewThousandBattles res)
	{
	}
}
