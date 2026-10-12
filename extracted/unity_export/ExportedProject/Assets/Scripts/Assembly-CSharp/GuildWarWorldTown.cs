using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarWorldTown : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_p_guildwar_town_id;

	private ObscuredInt m_p_battleworld_id;

	private ObscuredULong m_owner_guild_id;

	private DateTime m_next_reward_date;

	private ObscuredInt m_status;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
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

	public ulong ownerGuildId
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime nextRewardDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int status
	{
		get
		{
			return 0;
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
