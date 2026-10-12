using System;
using System.Collections.Generic;
using LitJson;

public class UserRaidInfos : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRaidInfo> m_srl2Raid;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserRaidInfo> dic
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

	public List<UserRaidInfo> GetAllRaidInfos()
	{
		return null;
	}

	public EnumData.RaidType GetCurrentSummonRaidType()
	{
		return EnumData.RaidType.NONE;
	}

	public bool IsCleardToday()
	{
		return false;
	}

	public bool IsEnableResummon()
	{
		return false;
	}

	public UserRaidInfo GetCleardRaidInfo()
	{
		return null;
	}

	public UserRaidInfo GetRaidInfoByType(int raidType)
	{
		return null;
	}

	public UserRaidInfo GetRaidInfo(int raidType, int difficulty)
	{
		return null;
	}
}
