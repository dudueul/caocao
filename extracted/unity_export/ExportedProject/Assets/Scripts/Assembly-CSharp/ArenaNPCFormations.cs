using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaNPCFormations : PermanentBase
{
	public List<ArenaNPCFormation> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaNPCFormation> m_arenaNPCFormations;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaNPCFormation> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaNPCFormation get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaNPCFormations src)
	{
	}

	public static void Load(ref ArenaNPCFormations res)
	{
	}
}
