using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class WeakPassives : PermanentBase
{
	public List<WeakPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, WeakPassive> m_weakPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, WeakPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public WeakPassive get_Item(ObscuredInt id)
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

	private void RefineData(_WeakPassives src)
	{
	}

	public static void Load(ref WeakPassives res)
	{
	}
}
