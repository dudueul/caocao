using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaNPCPassives : PermanentBase
{
	public List<ArenaNPCPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaNPCPassive> m_arenaNPCPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaNPCPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaNPCPassive get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaNPCPassives src)
	{
	}

	public static void Load(ref ArenaNPCPassives res)
	{
	}
}
