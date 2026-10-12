using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeTownFacilityByLVs : PermanentBase
{
	public List<GuildSiegeTownFacilityByLV> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeTownFacilityByLV> m_guildSiegeTownFacilityByLVs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeTownFacilityByLV> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeTownFacilityByLV get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeTownFacilityByLVs src)
	{
	}

	public static void Load(ref GuildSiegeTownFacilityByLVs res)
	{
	}
}
