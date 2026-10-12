using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFieldNpcGroups : PermanentBase
{
	public List<BattleFieldNpcGroup> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleFieldNpcGroup> m_battleFieldNpcGroups;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleFieldNpcGroup> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleFieldNpcGroup get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFieldNpcGroups src)
	{
	}

	public static void Load(ref BattleFieldNpcGroups res)
	{
	}
}
