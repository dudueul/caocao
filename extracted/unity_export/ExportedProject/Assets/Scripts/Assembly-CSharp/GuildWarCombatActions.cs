using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarCombatActions : PermanentBase
{
	public List<GuildWarCombatAction> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarCombatAction> m_guildWarCombatActions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarCombatAction> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarCombatAction get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarCombatActions src)
	{
	}

	public static void Load(ref GuildWarCombatActions res)
	{
	}
}
