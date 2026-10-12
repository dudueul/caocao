using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarTileTypes : PermanentBase
{
	public List<GuildWarTileType> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarTileType> m_guildWarTileTypes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarTileType> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarTileType get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarTileTypes src)
	{
	}

	public static void Load(ref GuildWarTileTypes res)
	{
	}
}
