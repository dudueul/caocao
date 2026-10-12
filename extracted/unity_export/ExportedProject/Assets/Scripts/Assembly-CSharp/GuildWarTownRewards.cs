using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarTownRewards : PermanentBase
{
	public List<GuildWarTownReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarTownReward> m_guildWarTownRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarTownReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarTownReward get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarTownRewards src)
	{
	}

	public static void Load(ref GuildWarTownRewards res)
	{
	}
}
