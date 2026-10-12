using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AISets : PermanentBase
{
	public List<AISet> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AISet> m_aISets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AISet> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AISet get_Item(ObscuredInt id)
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

	private void RefineData(_AISets src)
	{
	}

	public static void Load(ref AISets res)
	{
	}
}
