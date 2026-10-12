using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarSiegeWeapons : PermanentBase
{
	public List<GuildWarSiegeWeapon> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarSiegeWeapon> m_guildWarSiegeWeapons;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarSiegeWeapon> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarSiegeWeapon get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarSiegeWeapons src)
	{
	}

	public static void Load(ref GuildWarSiegeWeapons res)
	{
	}
}
