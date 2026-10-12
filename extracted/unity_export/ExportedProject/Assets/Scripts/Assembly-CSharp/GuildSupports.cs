using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSupports : PermanentBase
{
	public List<GuildSupport> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSupport> m_guildSupports;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSupport> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSupport get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSupports src)
	{
	}

	public static void Load(ref GuildSupports res)
	{
	}
}
