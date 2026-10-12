using System;
using System.Collections.Generic;
using LitJson;

public class UserRaidAwakenAttackInfos : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRaidAwakenAttackInfo> m_srl2Info;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserRaidAwakenAttackInfo> dic
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

	public UserRaidAwakenAttackInfo GetRaidAwakenAttackInfo(ulong userId, ulong raidId)
	{
		return null;
	}

	public List<UserRaidAwakenAttackInfo> GetRaidAwakenAttackInfoList(ulong guildId, ulong raidId)
	{
		return null;
	}
}
