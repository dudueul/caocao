using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildShops : PermanentBase
{
	public List<GuildShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildShop> m_guildShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildShop get_Item(ObscuredInt id)
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

	private void RefineData(_GuildShops src)
	{
	}

	public static void Load(ref GuildShops res)
	{
	}
}
