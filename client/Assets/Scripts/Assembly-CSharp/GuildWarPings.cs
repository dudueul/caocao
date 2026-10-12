using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarPings : PermanentBase
{
	public List<GuildWarPing> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarPing> m_guildWarPings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarPing> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarPing get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarPings src)
	{
	}

	public static void Load(ref GuildWarPings res)
	{
	}
}
