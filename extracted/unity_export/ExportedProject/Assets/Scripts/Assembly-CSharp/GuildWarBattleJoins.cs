using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarBattleJoins : ITransmittable
{
	private Dictionary<string, List<GuildWarBattleJoin>> m_key2GuildWarBattleJoins;

	// C# has no syntax for parameterized property 'Item'.
	public List<GuildWarBattleJoin> get_Item(int seasonKey, int seasonDetailKey, int p_battleworld_id, int p_guildwar_town_id)
	{
		return null;
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarBattleJoin get_Item(int seasonKey, int seasonDetailKey, int p_battleworld_id, int p_guildwar_town_id, ulong guild_id, ulong user_id)
	{
		return null;
	}

	public Action<HashSet<GuildWarBattleJoin>> deserializeCallback { get; set; }

	private string getKey(int seasonKey, int seasonDetailKey, int p_battleworld_id, int p_guildwar_town_id)
	{
		return null;
	}

	public GuildWarBattleJoin GetAnyBattleJoinWithGuildId(int seasonKey, int seasonDetailKey, ulong guildId)
	{
		return null;
	}

	public GuildWarBattleJoin GetBattleJoinWithUserId(int seasonKey, int seasonDetailKey, ulong guildId, ulong userId)
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
