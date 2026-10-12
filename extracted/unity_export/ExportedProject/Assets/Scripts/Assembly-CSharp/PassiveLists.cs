using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class PassiveLists : PermanentBase
{
	public List<PassiveList> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, PassiveList> m_passiveLists;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, PassiveList> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public PassiveList get_Item(ObscuredInt id)
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

	private void RefineData(_PassiveLists src)
	{
	}

	public static void Load(ref PassiveLists res)
	{
	}
}
