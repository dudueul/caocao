using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarConstants : PermanentBase
{
	public List<GuildWarConstant> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarConstant> m_guildWarConstants;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarConstant> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarConstant get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarConstants src)
	{
	}

	public static void Load(ref GuildWarConstants res)
	{
	}
}
