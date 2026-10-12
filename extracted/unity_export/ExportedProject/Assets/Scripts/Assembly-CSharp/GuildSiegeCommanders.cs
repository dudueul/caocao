using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeCommanders : PermanentBase
{
	public List<GuildSiegeCommander> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeCommander> m_guildSiegeCommanders;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeCommander> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeCommander get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeCommanders src)
	{
	}

	public static void Load(ref GuildSiegeCommanders res)
	{
	}
}
