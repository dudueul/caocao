using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SetItems : PermanentBase
{
	public List<SetItem> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SetItem> m_setItems;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SetItem> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SetItem get_Item(ObscuredInt id)
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

	private void RefineData(_SetItems src)
	{
	}

	public static void Load(ref SetItems res)
	{
	}
}
