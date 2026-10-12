using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Towns : PermanentBase
{
	public List<Town> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Town> m_towns;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Town> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Town get_Item(ObscuredInt id)
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

	private void RefineData(_Towns src)
	{
	}

	public static void Load(ref Towns res)
	{
	}
}
