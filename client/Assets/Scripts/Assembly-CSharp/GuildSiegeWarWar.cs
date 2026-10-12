using System;
using LitJson;

public class GuildSiegeWarWar : ITransmittable
{
	public enum GuildSiegeWarPhase
	{
		Stanby = 0,
		Facility = 1,
		Fortress = 2,
		Result = 3
	}

	private ulong m_warId;

	private ulong m_leagueId;

	private int m_warOrder;

	private ulong m_atkGuildId;

	private ulong m_defGuildId;

	private int m_atkPoint;

	private int m_defPoint;

	private int m_phase;

	private int m_battleCnt;

	private int m_reconNum;

	private DateTime m_startDate;

	private DateTime m_changeDate;

	public ulong warId
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

	public int warOrder
	{
		get
		{
			return 0;
		}
	}

	public ulong atkGuildId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong defGuildId
	{
		get
		{
			return 0uL;
		}
	}

	public int atkPoint
	{
		get
		{
			return 0;
		}
	}

	public int defPoint
	{
		get
		{
			return 0;
		}
	}

	public GuildSiegeWarPhase phase
	{
		get
		{
			return GuildSiegeWarPhase.Stanby;
		}
		set
		{
		}
	}

	public int battleCnt
	{
		get
		{
			return 0;
		}
	}

	public int reconNum
	{
		get
		{
			return 0;
		}
	}

	public DateTime startDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime changeDate
	{
		get
		{
			return default(DateTime);
		}
	}

	protected override void DeserializeImpl(JsonData data)
	{
	}

	public string GetPhaseStr()
	{
		return null;
	}
}
