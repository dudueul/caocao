using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarPlaces : PermanentBase
{
	public List<GuildWarPlace> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarPlace> m_guildWarPlaces;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarPlace> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarPlace get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarPlaces src)
	{
	}

	public static void Load(ref GuildWarPlaces res)
	{
	}
}
