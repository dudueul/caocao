using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class States : PermanentBase
{
	public List<State> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, State> m_states;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, State> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public State get_Item(ObscuredInt id)
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

	private void RefineData(_States src)
	{
	}

	public static void Load(ref States res)
	{
	}
}
