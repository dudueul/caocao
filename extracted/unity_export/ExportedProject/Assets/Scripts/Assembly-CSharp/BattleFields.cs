using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFields : PermanentBase
{
	public List<BattleField> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleField> m_battleFields;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleField> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleField get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFields src)
	{
	}

	public static void Load(ref BattleFields res)
	{
	}
}
