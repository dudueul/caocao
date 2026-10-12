using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaDeckSettings : PermanentBase
{
	public List<ArenaDeckSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaDeckSetting> m_arenaDeckSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaDeckSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaDeckSetting get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaDeckSettings src)
	{
	}

	public static void Load(ref ArenaDeckSettings res)
	{
	}
}
