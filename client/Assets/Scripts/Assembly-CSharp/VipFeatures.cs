using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class VipFeatures : PermanentBase
{
	public List<VipFeature> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, VipFeature> m_vipFeatures;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, VipFeature> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public VipFeature get_Item(ObscuredInt id)
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

	private void RefineData(_VipFeatures src)
	{
	}

	public static void Load(ref VipFeatures res)
	{
	}
}
