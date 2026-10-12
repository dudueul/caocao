using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class RelationLists : PermanentBase
{
	public List<RelationList> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, RelationList> m_relationLists;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, RelationList> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public RelationList get_Item(ObscuredInt id)
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

	private void RefineData(_RelationLists src)
	{
	}

	public static void Load(ref RelationLists res)
	{
	}
}
