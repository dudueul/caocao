using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NewThousandUnits : PermanentBase
{
	public List<NewThousandUnit> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NewThousandUnit> m_newThousandUnits;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NewThousandUnit> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NewThousandUnit get_Item(ObscuredInt id)
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

	private void RefineData(_NewThousandUnits src)
	{
	}

	public static void Load(ref NewThousandUnits res)
	{
	}
}
