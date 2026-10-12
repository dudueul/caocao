using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NPCTreasureSettings : PermanentBase
{
	public List<NPCTreasureSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NPCTreasureSetting> m_nPCTreasureSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NPCTreasureSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NPCTreasureSetting get_Item(ObscuredInt id)
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

	private void RefineData(_NPCTreasureSettings src)
	{
	}

	public static void Load(ref NPCTreasureSettings res)
	{
	}
}
