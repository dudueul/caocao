using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaPassiveLists : PermanentBase
{
	public List<ArenaPassiveList> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaPassiveList> m_arenaPassiveLists;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaPassiveList> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaPassiveList get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaPassiveLists src)
	{
	}

	public static void Load(ref ArenaPassiveLists res)
	{
	}
}
