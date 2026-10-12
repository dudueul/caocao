using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarPassives : PermanentBase
{
	public List<GuildWarPassive> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarPassive> m_guildWarPassives;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarPassive> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarPassive get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarPassives src)
	{
	}

	public static void Load(ref GuildWarPassives res)
	{
	}
}
