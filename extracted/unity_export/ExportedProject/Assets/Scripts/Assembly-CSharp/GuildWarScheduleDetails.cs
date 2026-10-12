using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarScheduleDetails : ITransmittable
{
	private Dictionary<string, GuildWarScheduleDetail> m_key2GuildWarScheduleDetail;

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarScheduleDetail get_Item(int seasonKey, int seasonDetailKey, int p_battleworld_id)
	{
		return null;
	}

	public Action<HashSet<GuildWarScheduleDetail>> deserializeCallback { get; set; }

	public int GetCurrentSeasonDetailKey(int seasonKey, int p_battleworld_id)
	{
		return 0;
	}

	public GuildWarScheduleDetail GetNextSeasonDetail(int seasonKey, DateTime currentTime, int p_battleworld_id)
	{
		return null;
	}

	private string getKey(int seasonKey, int seasonDetailKey, int p_battleworld_id)
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
