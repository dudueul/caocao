using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarLevelExps : PermanentBase
{
	public List<GuildWarLevelExp> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarLevelExp> m_guildWarLevelExps;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarLevelExp> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarLevelExp get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarLevelExps src)
	{
	}

	public static void Load(ref GuildWarLevelExps res)
	{
	}
}
