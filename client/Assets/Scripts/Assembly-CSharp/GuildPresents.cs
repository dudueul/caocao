using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildPresents : PermanentBase
{
	public List<GuildPresent> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildPresent> m_guildPresents;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildPresent> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildPresent get_Item(ObscuredInt id)
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

	private void RefineData(_GuildPresents src)
	{
	}

	public static void Load(ref GuildPresents res)
	{
	}
}
