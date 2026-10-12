using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarTiles : PermanentBase
{
	public List<GuildWarTile> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarTile> m_guildWarTiles;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarTile> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarTile get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarTiles src)
	{
	}

	public static void Load(ref GuildWarTiles res)
	{
	}
}
