using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarRankPoints : ITransmittable
{
	private Dictionary<string, GuildWarRankPoint> m_rankPoints;

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarRankPoint get_Item(ulong guildId, int rankId)
	{
		return null;
	}

	public Action<HashSet<GuildWarRankPoint>> deserializeCallback { get; set; }

	public int GetPoint(ulong guildId, int rankId)
	{
		return 0;
	}

	private string GetKey(ulong guildId, int rankId)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
