using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class LordEffects : PermanentBase
{
	public List<LordEffect> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, LordEffect> m_lordEffects;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, LordEffect> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public LordEffect get_Item(ObscuredInt id)
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

	private void RefineData(_LordEffects src)
	{
	}

	public static void Load(ref LordEffects res)
	{
	}
}
