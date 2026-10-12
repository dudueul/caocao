using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Teches : PermanentBase
{
	public List<Tech> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Tech> m_teches;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Tech> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Tech get_Item(ObscuredInt id)
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

	private void RefineData(_Teches src)
	{
	}

	public static void Load(ref Teches res)
	{
	}
}
