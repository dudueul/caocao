using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarCombatActiveFunctions : PermanentBase
{
	public List<GuildWarCombatActiveFunction> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarCombatActiveFunction> m_guildWarCombatActiveFunctions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarCombatActiveFunction> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarCombatActiveFunction get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarCombatActiveFunctions src)
	{
	}

	public static void Load(ref GuildWarCombatActiveFunctions res)
	{
	}
}
