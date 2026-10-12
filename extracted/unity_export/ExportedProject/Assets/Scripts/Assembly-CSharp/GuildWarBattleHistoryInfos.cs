using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarBattleHistoryInfos : ITransmittable
{
	private Dictionary<ulong, GuildWarBattleHistoryInfo> m_key2GuildWarBattleHistoryInfos;

	private Dictionary<string, SortedList<ulong, GuildWarBattleHistoryInfo>> m_index2GuildWarBattleHistoryInfos;

	// C# has no syntax for parameterized property 'Item'.
	public SortedList<ulong, GuildWarBattleHistoryInfo> get_Item(int seasonKey, ulong guildId)
	{
		return null;
	}

	public Action<HashSet<GuildWarBattleHistoryInfo>> deserializeCallback { get; set; }

	private string getIndex(int seasonKey, ulong guildId)
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
