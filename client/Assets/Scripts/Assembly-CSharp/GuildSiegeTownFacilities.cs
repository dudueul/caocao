using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildSiegeTownFacilities : PermanentBase
{
	public List<GuildSiegeTownFacility> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildSiegeTownFacility> m_guildSiegeTownFacilities;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildSiegeTownFacility> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeTownFacility get_Item(ObscuredInt id)
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

	private void RefineData(_GuildSiegeTownFacilities src)
	{
	}

	public static void Load(ref GuildSiegeTownFacilities res)
	{
	}
}
