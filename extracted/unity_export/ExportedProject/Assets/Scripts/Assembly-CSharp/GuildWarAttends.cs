using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarAttends : ITransmittable
{
	private Dictionary<ulong, Dictionary<int, GuildWarAttend>> m_datas;

	private Dictionary<string, List<GuildWarAttend>> m_attendsOnTown;

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarAttend get_Item(ulong guildId, int attendSlot)
	{
		return null;
	}

	public Action<HashSet<GuildWarAttend>> deserializeCallback { get; set; }

	private string getIndexKey(int seasonKey, int seasonDetailKey, int p_battleworldId, int p_guildWarTownId)
	{
		return null;
	}

	public List<GuildWarAttend> GetAttendListOnTown(int seasonKey, int seasonDetailKey, int p_battleworldId, int p_guildWarTownId, bool withoutCanceled = true)
	{
		return null;
	}

	public List<GuildWarAttend> GetAttendListWithGuildId(int seasonKey, int seasonDetailKey, ulong guildId, bool withoutCanceled = true)
	{
		return null;
	}

	public GuildWarAttend GetCurrentSeasonLatestAnyAttend(int seasonKey, ulong guildId)
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
