using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFormations : PermanentBase
{
	public List<BattleFormation> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleFormation> m_battleFormations;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BattleFormation> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleFormation get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFormations src)
	{
	}

	public static void Load(ref BattleFormations res)
	{
	}
}
