using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NPCNames : PermanentBase
{
	public List<NPCName> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NPCName> m_nPCNames;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NPCName> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NPCName get_Item(ObscuredInt id)
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

	private void RefineData(_NPCNames src)
	{
	}

	public static void Load(ref NPCNames res)
	{
	}
}
