using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NPCSettings : PermanentBase
{
	public List<NPCSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NPCSetting> m_nPCSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NPCSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NPCSetting get_Item(ObscuredInt id)
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

	private void RefineData(_NPCSettings src)
	{
	}

	public static void Load(ref NPCSettings res)
	{
	}
}
