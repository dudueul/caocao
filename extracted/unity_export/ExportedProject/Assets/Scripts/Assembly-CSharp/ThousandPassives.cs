using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ThousandPassives : PermanentBase
{
	public List<ThousandPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ThousandPassive> m_thousandPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ThousandPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ThousandPassive get_Item(ObscuredInt id)
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

	private void RefineData(_ThousandPassives src)
	{
	}

	public static void Load(ref ThousandPassives res)
	{
	}
}
