using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ExpandPassiveItems : PermanentBase
{
	public List<ExpandPassiveItem> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ExpandPassiveItem> m_expandPassiveItems;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ExpandPassiveItem> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ExpandPassiveItem get_Item(ObscuredInt id)
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

	private void RefineData(_ExpandPassiveItems src)
	{
	}

	public static void Load(ref ExpandPassiveItems res)
	{
	}
}
