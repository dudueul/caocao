using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarCorpsJobs : PermanentBase
{
	public List<GuildWarCorpsJob> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarCorpsJob> m_guildWarCorpsJobs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarCorpsJob> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarCorpsJob get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarCorpsJobs src)
	{
	}

	public static void Load(ref GuildWarCorpsJobs res)
	{
	}
}
