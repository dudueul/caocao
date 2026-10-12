using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Missions : PermanentBase
{
	public List<Mission> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Mission> m_missions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Mission> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Mission get_Item(ObscuredInt id)
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

	private void RefineData(_Missions src)
	{
	}

	public static void Load(ref Missions res)
	{
	}
}
