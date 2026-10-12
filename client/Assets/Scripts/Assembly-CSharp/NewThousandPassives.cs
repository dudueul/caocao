using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NewThousandPassives : PermanentBase
{
	public List<NewThousandPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NewThousandPassive> m_newThousandPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NewThousandPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NewThousandPassive get_Item(ObscuredInt id)
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

	private void RefineData(_NewThousandPassives src)
	{
	}

	public static void Load(ref NewThousandPassives res)
	{
	}
}
