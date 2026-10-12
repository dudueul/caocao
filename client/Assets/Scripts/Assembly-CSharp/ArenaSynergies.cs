using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaSynergies : PermanentBase
{
	public List<ArenaSynergy> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaSynergy> m_arenaSynergies;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaSynergy> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaSynergy get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaSynergies src)
	{
	}

	public static void Load(ref ArenaSynergies res)
	{
	}
}
