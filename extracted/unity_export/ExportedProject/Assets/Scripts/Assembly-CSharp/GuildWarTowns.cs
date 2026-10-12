using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarTowns : PermanentBase
{
	public List<GuildWarTown> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarTown> m_guildWarTowns;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarTown> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarTown get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarTowns src)
	{
	}

	public static void Load(ref GuildWarTowns res)
	{
	}
}
