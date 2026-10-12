using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildDonation : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_guildId;

	private ObscuredInt m_gold;

	private ObscuredInt m_silver;

	private ObscuredString m_expireDate;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
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

	public int gold
	{
		get
		{
			return 0;
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
	}

	public string expireDate
	{
		get
		{
			return null;
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
