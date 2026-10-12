using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CommonMissions : PermanentBase
{
	public List<CommonMission> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CommonMission> m_commonMissions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CommonMission> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CommonMission get_Item(ObscuredInt id)
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

	private void RefineData(_CommonMissions src)
	{
	}

	public static void Load(ref CommonMissions res)
	{
	}
}
