using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RelationMissions : PermanentBase
{
	public List<RelationMission> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RelationMission> m_relationMissions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RelationMission> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RelationMission get_Item(ObscuredInt id)
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

	private void RefineData(_RelationMissions src)
	{
	}

	public static void Load(ref RelationMissions res)
	{
	}
}
