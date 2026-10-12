using System;
using System.Collections.Generic;
using LitJson;

public class UserRaidAwakenInfos : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRaidAwakenInfo> m_srl2Info;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserRaidAwakenInfo> dic
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public int GetActiveOrSummonableCount(ulong guildid, bool hasPermission)
	{
		return 0;
	}

	public bool HasActiveRaidAwaken(ulong guildId)
	{
		return false;
	}

	public bool HasSummonableRaidAwaken(ulong guildId)
	{
		return false;
	}

	public UserRaidAwakenInfo GetRaidAwakenInfo(ulong guildId)
	{
		return null;
	}
}
