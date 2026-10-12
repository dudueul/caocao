using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarRankPoint : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guild_id;

	private ObscuredInt m_rank_id;

	private ObscuredString m_limit_day;

	private ObscuredInt m_point;

	private DateTime m_upd_date;

	private DateTime m_reg_date;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guild_id
	{
		get
		{
			return 0uL;
		}
	}

	public int rank_id
	{
		get
		{
			return 0;
		}
	}

	public string limit_day
	{
		get
		{
			return null;
		}
	}

	public int point
	{
		get
		{
			return 0;
		}
	}

	public DateTime upd_date
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime reg_date
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
