using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarContributionRewards : PermanentBase
{
	public List<GuildWarContributionReward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarContributionReward> m_guildWarContributionRewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarContributionReward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarContributionReward get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarContributionRewards src)
	{
	}

	public static void Load(ref GuildWarContributionRewards res)
	{
	}
}
