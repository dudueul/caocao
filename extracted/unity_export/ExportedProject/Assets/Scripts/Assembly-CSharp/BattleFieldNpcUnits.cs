using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFieldNpcUnits : PermanentBase
{
	public List<BattleFieldNpcUnit> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleFieldNpcUnit> m_battleFieldNpcUnits;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleFieldNpcUnit> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleFieldNpcUnit get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFieldNpcUnits src)
	{
	}

	public static void Load(ref BattleFieldNpcUnits res)
	{
	}
}
