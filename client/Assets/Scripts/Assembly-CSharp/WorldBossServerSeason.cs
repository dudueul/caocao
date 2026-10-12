using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossServerSeason : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_phase;

	private ObscuredInt m_seasonId;

	private DateTime m_startDate;

	private DateTime m_offDate;

	private DateTime m_endDate;

	private DateTime m_updDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public int phase
	{
		get
		{
			return 0;
		}
	}

	public int seasonId
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

	public DateTime offDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime endDate
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsActive(DateTime time)
	{
		return false;
	}
}
