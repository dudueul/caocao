using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BattleFormationFuncs : PermanentBase
{
	public List<BattleFormationFunc> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BattleFormationFunc> m_battleFormationFuncs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	private Dictionary<ObscuredInt, List<BattleFormationFunc>> m_arrangedDic;

	public Dictionary<ObscuredInt, BattleFormationFunc> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BattleFormationFunc get_Item(ObscuredInt id)
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

	private void RefineData(_BattleFormationFuncs src)
	{
	}

	public static void Load(ref BattleFormationFuncs res)
	{
	}

	public List<BattleFormationFunc> GetBattleFormationFuncs(int battleFormationId)
	{
		return null;
	}

	public void PostProcess()
	{
	}
}
