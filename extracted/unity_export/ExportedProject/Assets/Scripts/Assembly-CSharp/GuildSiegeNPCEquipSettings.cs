using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeNPCEquipSettings : PermanentBase
{
	public List<GuildSiegeNPCEquipSetting> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeNPCEquipSetting> m_guildSiegeNPCEquipSettings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeNPCEquipSetting> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeNPCEquipSetting get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeNPCEquipSettings src)
	{
	}

	public static void Load(ref GuildSiegeNPCEquipSettings res)
	{
	}
}
