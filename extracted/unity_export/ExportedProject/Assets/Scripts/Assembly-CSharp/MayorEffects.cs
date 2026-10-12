using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class MayorEffects : PermanentBase
{
	public List<MayorEffect> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, MayorEffect> m_mayorEffects;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, MayorEffect> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public MayorEffect get_Item(ObscuredInt id)
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

	private void RefineData(_MayorEffects src)
	{
	}

	public static void Load(ref MayorEffects res)
	{
	}
}
