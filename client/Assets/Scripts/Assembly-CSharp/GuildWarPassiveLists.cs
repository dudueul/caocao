using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarPassiveLists : PermanentBase
{
	public List<GuildWarPassiveList> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarPassiveList> m_guildWarPassiveLists;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarPassiveList> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarPassiveList get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarPassiveLists src)
	{
	}

	public static void Load(ref GuildWarPassiveLists res)
	{
	}
}
