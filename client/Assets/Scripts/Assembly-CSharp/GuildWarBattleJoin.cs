using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarBattleJoin : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_guildId;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_seasonDetailKey;

	private ObscuredInt m_p_guildwar_town_id;

	private ObscuredInt m_p_battleworld_id;

	private DateTime m_join_date;

	private DateTime m_updDate;

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

	public int seasonKey
	{
		get
		{
			return 0;
		}
	}

	public int seasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public int pGuildwarTownId
	{
		get
		{
			return 0;
		}
	}

	public int pBattleWorldId
	{
		get
		{
			return 0;
		}
	}

	public DateTime joinDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
