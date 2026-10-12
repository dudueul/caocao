using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CraftTreasures : PermanentBase
{
	public List<CraftTreasure> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CraftTreasure> m_craftTreasures;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CraftTreasure> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CraftTreasure get_Item(ObscuredInt id)
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

	private void RefineData(_CraftTreasures src)
	{
	}

	public static void Load(ref CraftTreasures res)
	{
	}
}
