using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarSchedules : ITransmittable
{
	private Dictionary<int, GuildWarSchedule> m_key2GuildWarSchedule;

	public int currentSeasonKey
	{
		get
		{
			return 0;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarSchedule get_Item(int seasonKey)
	{
		return null;
	}

	public Action<HashSet<GuildWarSchedule>> deserializeCallback { get; set; }

	public GuildWarSchedule GetNextSchedule(DateTime currentTime)
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
