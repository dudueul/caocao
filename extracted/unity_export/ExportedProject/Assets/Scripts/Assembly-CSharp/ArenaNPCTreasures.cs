using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaNPCTreasures : PermanentBase
{
	public List<ArenaNPCTreasure> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaNPCTreasure> m_arenaNPCTreasures;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaNPCTreasure> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaNPCTreasure get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaNPCTreasures src)
	{
	}

	public static void Load(ref ArenaNPCTreasures res)
	{
	}
}
