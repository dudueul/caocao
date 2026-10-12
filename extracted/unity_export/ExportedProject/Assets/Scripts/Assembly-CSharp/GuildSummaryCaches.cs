using System;
using System.Collections.Generic;
using LitJson;

public class GuildSummaryCaches
{
	private Dictionary<ulong, GuildMember> userId2MemberCache;

	private Dictionary<ulong, Action<GuildInfo, GuildMember>> userId2UpdateGuildSummary;

	public void RequestExplicitGuildInfo(ulong user_id)
	{
	}

	private void ResponseCallback(JsonData jsonData)
	{
	}

	public void AddGuildSummaryCallback(ulong user_id, Action<GuildInfo, GuildMember> callback)
	{
	}

	public void RemoveGuildSummaryCallback(ulong user_id, Action<GuildInfo, GuildMember> callback)
	{
	}

	public void CacheGuildInfo(JsonData jsonData)
	{
	}

	public void CacheGuildMember(ulong userId, JsonData memberJsonData)
	{
	}

	public GuildMember GetGuildMember(ulong user_id)
	{
		return null;
	}

	public void Clear()
	{
	}

	public void OnDeserializedMe()
	{
	}
}
