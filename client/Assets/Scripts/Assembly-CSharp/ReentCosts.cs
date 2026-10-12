using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ReentCosts : PermanentBase
{
	public List<ReentCost> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ReentCost> m_reentCosts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ReentCost> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ReentCost get_Item(ObscuredInt id)
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

	private void RefineData(_ReentCosts src)
	{
	}

	public static void Load(ref ReentCosts res)
	{
	}
}
