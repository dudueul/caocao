using System;
using CodeStage.AntiCheat.ObscuredTypes;
using GuildWar;
using LitJson;

public class GuildWarAttend : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredInt m_attend_slot;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_seasonDetailKey;

	private ObscuredInt m_p_guildwar_town_id;

	private ObscuredInt m_p_battleworld_id;

	private ObscuredBool m_is_adjacent_town;

	private DateTime m_attendDate;

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

	public int seasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
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

	public bool isAdjacentTown
	{
		get
		{
			return false;
		}
	}

	public DateTime attendDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public EGuildWarAttendStatus status
	{
		get
		{
			return EGuildWarAttendStatus.Attend;
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
