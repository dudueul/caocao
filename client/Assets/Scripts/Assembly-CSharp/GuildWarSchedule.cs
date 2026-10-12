using System;
using CodeStage.AntiCheat.ObscuredTypes;
using GuildWar;
using LitJson;

public class GuildWarSchedule : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_seasonKey;

	private DateTime m_season_start;

	private DateTime m_season_end;

	private ObscuredInt m_current_group_index;

	private ObscuredInt m_max_group_index;

	private GuildWarScheduleStatus m_status;

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

	public DateTime seasonStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime seasonEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public int currentGroupIndex
	{
		get
		{
			return 0;
		}
	}

	public int maxGroupIndex
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

	public GuildWarScheduleStatus status
	{
		get
		{
			return GuildWarScheduleStatus.None;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
