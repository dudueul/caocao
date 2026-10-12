using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSupportLog : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredULong m_memberId;

	private ObscuredULong m_guildId;

	private ObscuredULong m_unitSrl;

	private ObscuredULong m_leagueId;

	private DateTime m_regDate;

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public ulong memberId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong unitSrl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong leagueId
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
