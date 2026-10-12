using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TreasureMixes : PermanentBase
{
	public List<TreasureMix> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TreasureMix> m_treasureMixes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TreasureMix> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TreasureMix get_Item(ObscuredInt id)
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

	private void RefineData(_TreasureMixes src)
	{
	}

	public static void Load(ref TreasureMixes res)
	{
	}
}
