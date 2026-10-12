using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AIs : PermanentBase
{
	public List<AI> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AI> m_aIs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AI> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AI get_Item(ObscuredInt id)
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

	private void RefineData(_AIs src)
	{
	}

	public static void Load(ref AIs res)
	{
	}
}
