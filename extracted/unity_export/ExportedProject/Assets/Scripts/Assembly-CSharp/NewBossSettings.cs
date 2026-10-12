using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class NewBossSettings : PermanentBase
{
	public List<NewBossSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, NewBossSetting> m_newBossSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, NewBossSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public NewBossSetting get_Item(ObscuredInt id)
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

	private void RefineData(_NewBossSettings src)
	{
	}

	public static void Load(ref NewBossSettings res)
	{
	}
}
