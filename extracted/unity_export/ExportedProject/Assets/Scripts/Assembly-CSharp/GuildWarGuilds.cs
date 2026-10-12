using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarGuilds : ITransmittable
{
	private Dictionary<ulong, GuildInfo> m_guilds;

	// C# has no syntax for parameterized property 'Item'.
	public GuildInfo get_Item(ulong guildId)
	{
		return null;
	}

	public Action<HashSet<GuildInfo>> deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
