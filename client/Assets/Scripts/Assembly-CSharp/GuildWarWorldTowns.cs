using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarWorldTowns : ITransmittable
{
	private Dictionary<string, GuildWarWorldTown> m_key2GuildWarWorldTown;

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarWorldTown get_Item(int seasonKey, int p_battleworld_id, int p_guildwar_town_id)
	{
		return null;
	}

	public Action<HashSet<GuildWarWorldTown>> deserializeCallback { get; set; }

	public List<GuildWarWorldTown> GetTownsWithGuildId(ulong guildId)
	{
		return null;
	}

	public List<GuildWarWorldTown> GetTownsWithGuildIdBattleGuildId(ulong guildId, int battleWorldId)
	{
		return null;
	}

	public GuildWarWorldTown getFirstTownWithGuildId(ulong guildId)
	{
		return null;
	}

	private string getKey(int seasonKey, int p_battleworld_id, int p_guildwar_town_id)
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
