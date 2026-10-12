using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AITendencies : PermanentBase
{
	public List<AITendency> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AITendency> m_aITendencies;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AITendency> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AITendency get_Item(ObscuredInt id)
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

	private void RefineData(_AITendencies src)
	{
	}

	public static void Load(ref AITendencies res)
	{
	}
}
