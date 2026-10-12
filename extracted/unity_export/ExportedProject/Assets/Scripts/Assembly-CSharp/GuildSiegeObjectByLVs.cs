using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeObjectByLVs : PermanentBase
{
	public List<GuildSiegeObjectByLV> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeObjectByLV> m_guildSiegeObjectByLVs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeObjectByLV> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeObjectByLV get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeObjectByLVs src)
	{
	}

	public static void Load(ref GuildSiegeObjectByLVs res)
	{
	}
}
