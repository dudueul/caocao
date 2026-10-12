using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarBuildings : PermanentBase
{
	public List<GuildWarBuilding> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarBuilding> m_guildWarBuildings;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarBuilding> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarBuilding get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarBuildings src)
	{
	}

	public static void Load(ref GuildWarBuildings res)
	{
	}
}
