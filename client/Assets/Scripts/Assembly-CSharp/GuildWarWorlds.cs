using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarWorlds : PermanentBase
{
	public List<GuildWarWorld> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarWorld> m_guildWarWorlds;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarWorld> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarWorld get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarWorlds src)
	{
	}

	public static void Load(ref GuildWarWorlds res)
	{
	}
}
