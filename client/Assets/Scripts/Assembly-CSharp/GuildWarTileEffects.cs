using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarTileEffects : PermanentBase
{
	public List<GuildWarTileEffect> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarTileEffect> m_guildWarTileEffects;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarTileEffect> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarTileEffect get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarTileEffects src)
	{
	}

	public static void Load(ref GuildWarTileEffects res)
	{
	}
}
