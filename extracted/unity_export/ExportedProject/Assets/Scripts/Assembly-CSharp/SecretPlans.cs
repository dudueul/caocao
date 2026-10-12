using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SecretPlans : PermanentBase
{
	public List<SecretPlan> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SecretPlan> m_secretPlans;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SecretPlan> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SecretPlan get_Item(ObscuredInt id)
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

	private void RefineData(_SecretPlans src)
	{
	}

	public static void Load(ref SecretPlans res)
	{
	}
}
