using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaNPCSettings : PermanentBase
{
	public List<ArenaNPCSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaNPCSetting> m_arenaNPCSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaNPCSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaNPCSetting get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaNPCSettings src)
	{
	}

	public static void Load(ref ArenaNPCSettings res)
	{
	}
}
