using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarPassiveAttributes : PermanentBase
{
	public List<GuildWarPassiveAttribute> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarPassiveAttribute> m_guildWarPassiveAttributes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarPassiveAttribute> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarPassiveAttribute get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarPassiveAttributes src)
	{
	}

	public static void Load(ref GuildWarPassiveAttributes res)
	{
	}
}
