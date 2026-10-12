using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFieldNpcGroupTypes : PermanentBase
{
	public List<BattleFieldNpcGroupType> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleFieldNpcGroupType> m_battleFieldNpcGroupTypes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleFieldNpcGroupType> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleFieldNpcGroupType get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFieldNpcGroupTypes src)
	{
	}

	public static void Load(ref BattleFieldNpcGroupTypes res)
	{
	}
}
