using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class BossSettings : PermanentBase
{
	public List<BossSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, BossSetting> m_bossSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, BossSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BossSetting get_Item(ObscuredInt id)
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

	private void RefineData(_BossSettings src)
	{
	}

	public static void Load(ref BossSettings res)
	{
	}
}
