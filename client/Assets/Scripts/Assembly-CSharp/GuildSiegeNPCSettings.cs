using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeNPCSettings : PermanentBase
{
	public List<GuildSiegeNPCSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeNPCSetting> m_guildSiegeNPCSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeNPCSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeNPCSetting get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeNPCSettings src)
	{
	}

	public static void Load(ref GuildSiegeNPCSettings res)
	{
	}
}
