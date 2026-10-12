using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWeeklyMissions : PermanentBase
{
	public List<GuildWeeklyMission> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWeeklyMission> m_guildWeeklyMissions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWeeklyMission> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWeeklyMission get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWeeklyMissions src)
	{
	}

	public static void Load(ref GuildWeeklyMissions res)
	{
	}
}
