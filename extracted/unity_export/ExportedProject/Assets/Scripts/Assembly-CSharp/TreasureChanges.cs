using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TreasureChanges : PermanentBase
{
	public List<TreasureChange> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TreasureChange> m_treasureChanges;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TreasureChange> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TreasureChange get_Item(ObscuredInt id)
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

	private void RefineData(_TreasureChanges src)
	{
	}

	public static void Load(ref TreasureChanges res)
	{
	}
}
