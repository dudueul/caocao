using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BrawlRotations : PermanentBase
{
	public List<BrawlRotation> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BrawlRotation> m_brawlRotations;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BrawlRotation> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BrawlRotation get_Item(ObscuredInt id)
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

	private void RefineData(_BrawlRotations src)
	{
	}

	public static void Load(ref BrawlRotations res)
	{
	}
}
