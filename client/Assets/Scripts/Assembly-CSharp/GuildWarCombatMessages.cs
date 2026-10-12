using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarCombatMessages : PermanentBase
{
	public List<GuildWarCombatMessage> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarCombatMessage> m_guildWarCombatMessages;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarCombatMessage> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarCombatMessage get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarCombatMessages src)
	{
	}

	public static void Load(ref GuildWarCombatMessages res)
	{
	}
}
