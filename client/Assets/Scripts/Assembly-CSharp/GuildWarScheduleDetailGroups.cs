using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarScheduleDetailGroups : ITransmittable
{
	private Dictionary<int, Dictionary<int, GuildWarScheduleDetailGroup>> m_key2GuildWarScheduleDetailGroup;

	// C# has no syntax for parameterized property 'Item'.
	public Dictionary<int, GuildWarScheduleDetailGroup> get_Item(int seasonKey)
	{
		return null;
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarScheduleDetailGroup get_Item(int seasonKey, int groupIndex)
	{
		return null;
	}

	public Action<HashSet<GuildWarScheduleDetailGroup>> deserializeCallback { get; set; }

	public GuildWarScheduleDetailGroup GetScheduleDetailGroup(int seasonKey, int groupIndex)
	{
		return null;
	}

	public int GetCurrentSeasonDetailKey(int seasonKey, DateTime time)
	{
		return 0;
	}

	public GuildWarScheduleDetailGroup GetCurrentScheduleDetailGroup(int seasonKey, DateTime time)
	{
		return null;
	}

	public GuildWarScheduleDetailGroup GetLatestScheduleDetailGroup(int seasonKey, DateTime time)
	{
		return null;
	}

	public GuildWarScheduleDetailGroup GetBeforeScheduleDetailGroup(int seasonKey, DateTime time)
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
