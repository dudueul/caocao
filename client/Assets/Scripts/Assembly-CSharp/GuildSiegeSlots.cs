using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeSlots : PermanentBase
{
	public List<GuildSiegeSlot> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeSlot> m_guildSiegeSlots;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeSlot> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeSlot get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeSlots src)
	{
	}

	public static void Load(ref GuildSiegeSlots res)
	{
	}
}
