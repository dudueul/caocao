using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaSynergyLists : PermanentBase
{
	public List<ArenaSynergyList> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaSynergyList> m_arenaSynergyLists;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaSynergyList> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaSynergyList get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaSynergyLists src)
	{
	}

	public static void Load(ref ArenaSynergyLists res)
	{
	}
}
